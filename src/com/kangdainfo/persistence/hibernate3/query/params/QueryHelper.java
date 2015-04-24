package com.kangdainfo.persistence.hibernate3.query.params;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.kangdainfo.persistence.hibernate3.QueryCondition;
import com.kangdainfo.util.DateUtil;
import com.kangdainfo.util.TaiwanDateUtil;
import com.kangdainfo.util.lang.reflect.ReflectUtil;

/**
 * QueryUtil
 * 
 * @author catty.liao
 * @version 1.0, Created on 2006/8/23
 */
public class QueryHelper {

	private static Logger logger = Logger.getLogger(QueryHelper.class);

	public static final String SELECT = "select";

	public static final String FROM = "from";

	public static final String WHERE = "where";

	public static final String AND = "and";

	public static final String NOT = "not";

	public static final String BETWEEN = "between";

	public static final String ORDERBY = "order by";

	public static final String ASC = "asc";

	public static final String DESC = "desc";

	public static final String DEFAULT_ALIAS = "a";

	public static Query createQuery(Session session, Class objClass,
			QueryParams queryParams) {
		return createQuery(session, objClass, queryParams, null, false);

	}

	public static Query createQuery(Session session, Class objClass,
			QueryParams queryParams, final String orderBy,
			final boolean isAscending) {

		StringBuffer hql = new StringBuffer();
		if (queryParams.isDistinct()
				&& queryParams.getDistinctFieldName() != null) {
			hql.append("select distinct(" + DEFAULT_ALIAS + "."
					+ queryParams.getDistinctFieldName() + ")");
		}
		hql.append("from " + objClass.getSimpleName() + " " + DEFAULT_ALIAS);

		StringBuffer sb = new StringBuffer(hql);
		String alias = findAlias(hql.toString());

		appendParams(hql.toString(), queryParams, sb, alias);

		if (orderBy != null) {
			addOrderBy(orderBy, isAscending, sb, alias);
		}
		logger.info("[create Query] hql = " + sb.toString());

		Query query = session.createQuery(sb.toString());

		applyParams(objClass, queryParams, query);

		return query;

	}

	public static Query createQuery(Session session, Class objClass,
			QueryParams queryParams, int startAt, int size, String orderBy,
			boolean isAscending) {

		Query query = createQuery(session, objClass, queryParams, orderBy,
				isAscending);
		query.setFirstResult(startAt);
		query.setMaxResults(size);

		return query;
	}

	public static Query createCountQuery(Session session, Class objClass,
			QueryParams queryParams) {
		String hql = "from " + objClass.getSimpleName() + " " + DEFAULT_ALIAS;
		return createCountQuery(session, objClass, hql, queryParams);
	}

	private static Query createCountQuery(Session session, Class objClass,
			String hql, QueryParams queryParams) {

		StringBuffer sb = new StringBuffer(hql);
		String alias = findAlias(hql);

		appendSelectCount(sb);

		appendParams(hql, queryParams, sb, alias);

		logger.info("[create Count Query] hql = " + sb.toString());

		Query query = session.createQuery(sb.toString());

		applyParams(objClass, queryParams, query);

		return query;
	}

	private static void appendParams(String hql, QueryParams queryParams,
			StringBuffer sb, String alias) {
		if (queryParams.getQueryParamList() != null) {
			boolean hasWhere = (hql.indexOf(WHERE) > -1) ? true : false;
			if (!hasWhere) {
				hasWhere = true;
				sb.append(" " + WHERE + " 1=1");
			}
			for (int i = 0; i < queryParams.getQueryParamList().size(); i++) {
				QueryParam param = (QueryParam) queryParams.get(i);
				sb.append(param.toSql(alias));
			}
		}
	}

	public static void appendSelectCount(StringBuffer sb) {
		if (isBeginWithFrom(sb.toString())) {
			sb.insert(0, "select count(*) ");
		}
	}

	private static void applyParams(Class objClass, QueryParams queryParams,
			Query query) {
		List parameterList = queryParams.getQueryParamList();
		if (parameterList != null) {
			List newParamList = warpParmas(objClass, parameterList);
			QueryParams newQueryParams = new QueryParams(newParamList);
			for (int i = 0; i < newParamList.size(); i++) {

				QueryParam param = newQueryParams.get(i);

				if (param instanceof QueryHqlParam) {
					QueryHqlParam theParam = (QueryHqlParam) param;
					String paramNames[] = theParam.getNames();
					Object paramValues[] = theParam.getValues();
					for (int j = 0, maxj = paramNames.length; j < maxj; j++) {
						String name = paramNames[j];
						Object value = paramValues[j];
						query.setParameter(name, value);
					}
				} else {
					String compare = param.getCompare();
					String name = param.getName();
					String safeName = param.getSafeName();

					logger.debug("newParam safeName="
							+ safeName
							+ ", name="
							+ name
							+ ", compare="
							+ compare
							+ ", value0="
							+ ((param.getValues() == null) ? null : param
									.getValues()[0]));

					if (compare.equals(QueryCondition.BETWEEN)
							|| compare.equals(QueryCondition.NOT_BETWEEN)) {
						query
								.setParameter(safeName + "0",
										param.getValues()[0]);
						query
								.setParameter(safeName + "1",
										param.getValues()[1]);
					} else if (compare.equals(QueryCondition.IN)
							|| compare.equals(QueryCondition.NOT_IN)) {
						query.setParameterList(safeName, param.getValues());
					} else if (compare.equals(QueryCondition.LIKE_MORE)
							|| compare.equals(QueryCondition.NOT_LIKE_MORE)) {
						query.setParameter(safeName, "%" + param.getValues()[0]
								+ "%");
					} else if (compare.equals(QueryCondition.LIKE)
							|| compare.equals(QueryCondition.NOT_LIKE)) {
						query
								.setParameter(safeName, param.getValues()[0]
										+ "%");
					} else if (compare.equals(QueryCondition.IS_NULL)
							|| compare.equals(QueryCondition.IS_NOT_NULL)) {
						// do nothing....
					} else {
						Object paramValue0 = param.getValues()[0];
						query.setParameter(safeName, paramValue0);
					}
				}
			}
		}
	}

	private static void addOrderBy(final String orderBy,
			final boolean isAscending, StringBuffer sb, String alias) {
		// added by kim 2007/03/26
		if(orderBy!=null && orderBy.indexOf(',')!=-1) {
			sb.append(" " + ORDERBY + " " + orderBy);
			return;
		}
		sb.append(" " + ORDERBY + " " + alias + orderBy);
		if (isAscending) {
			sb.append(" " + ASC);
		} else {
			sb.append(" " + DESC);
		}
	}

	public static boolean isBeginWithFrom(String hql) {
		return (hql.trim().toLowerCase().indexOf("from") == 0 ? true : false);
	}

	public static String findAlias(String hql) {
		String alias = DEFAULT_ALIAS;
		String ary[] = hql.split(" ");
		for (int i = 0, max = ary.length; i < max; i++) {
			if (ary[i].equalsIgnoreCase(FROM)) {
				if (i + 2 < max)
					alias = ary[i + 2];
			}
		}
		return alias + ".";
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// warp Parmas (translate from String to )
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private static Class[] number1 = new Class[] { short.class, int.class,
			long.class, float.class, double.class };

	private static Class[] number2 = new Class[] { Short.class, Integer.class,
			Long.class, Float.class, Double.class };

	private static Class[] boolean1 = new Class[] { boolean.class };

	private static Class[] boolean2 = new Class[] { Boolean.class };

	private static List warpParmas(Class objClass, List params) {
		List list = new ArrayList();
		for (int i = 0, max = params.size(); i < max; i++) {
			QueryParam param = (QueryParam) params.get(i);
			String name = param.getName();
			Class type = getParamType(objClass, name);
			Object values[] = param.getValues();
			if (values != null && values.length > 0) {
				Object newValues[] = new Object[values.length];
				for (int j = 0, maxj = values.length; j < maxj; j++) {
					Object value = null;
					try {
						value = transObject(values[j], type);
					} catch (Exception e) {
						logger.error("name=" + name + ", value=" + values[j]
								+ ", type=" + type, e);
					}
					newValues[j] = value;
				}
				param.setValues(newValues);
			}
			list.add(param);
		}
		return list;
	}

	public static Object transObject(Object value, Class targetClass) {
		Object result = value;
		String from = value.getClass().getSimpleName();
		String to = targetClass.getSimpleName();
		Class wrapClass = null;
		// when Sample Class, do nothing
		if (from.equals(to)) {
			return value;
		}
		try {
			if (ArrayUtils.contains(number1, targetClass)) {
				int idx = ArrayUtils.indexOf(number1, targetClass);
				wrapClass = number2[idx];
				result = wrapClass.getConstructor(new Class[] { String.class })
						.newInstance(new Object[] { value });
			} else if (ArrayUtils.contains(boolean1, targetClass)) {
				int idx = ArrayUtils.indexOf(boolean1, targetClass);
				wrapClass = boolean2[idx];
				result = wrapClass.getConstructor(new Class[] { String.class })
						.newInstance(new Object[] { value });
			} else if (ArrayUtils.contains(number2, targetClass)
					|| ArrayUtils.contains(boolean2, targetClass)) {
				result = targetClass.getConstructor(
						new Class[] { String.class }).newInstance(
						new Object[] { value });
			} else if (targetClass.equals(Date.class)) {
				if (TaiwanDateUtil.validate(value.toString())) {
					result = TaiwanDateUtil.parseToDate(value.toString());
				} else {
					result = DateUtil.parseToDate(value.toString());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("trans Object:'" + value + "' from '"
					+ from + "' to '" + to + "' failure !!", e);
		}
		return result;
	}

	private static Class getParamType(Class objClass, String fieldName) {
		Class result = String.class;
		try {
			Field field = ReflectUtil.getField(objClass, fieldName);
			result = field.getType();
		} catch (Exception e) {
		}
		return result;
	}

}
