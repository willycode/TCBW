package com.kangdainfo.persistence.hibernate3;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.hql.classic.ParserHelper;

/**
 * HQL object stores HQL string and provides some convenient methods. (This
 * object is immutable.)
 * 
 * @author Bear
 * @version 1.0
 */
public class HQL {
	
	private Logger logger = Logger.getLogger(this.getClass());
    
    
    private final String hql;

    private final String[] parameters;

    public static final String LOGIC_AND = "and";

    public static final String LOGIC_OR = "or";

    public static final String LOGIC_NOT = "not";

    public HQL(String hql) {
        this.hql = hql;

        StringTokenizer st = new StringTokenizer(hql,
                ParserHelper.HQL_SEPARATORS);
        List parameterList = new ArrayList();

        while(st.hasMoreTokens()) {
            String string = st.nextToken();
            if(string.startsWith(ParserHelper.HQL_VARIABLE_PREFIX)) {
                parameterList.add(string.substring(1));
            }
        }

        parameters = (String[])parameterList.toArray(new String[parameterList
                .size()]);
    }

    /**
     * Get alias in HQL according given class.
     * 
     * @param queryClass Class object of alias class.
     * @return String array of alias.
     */
    public String[] getAlias(Class queryClass) {
        List aliasList = new ArrayList();

        String className = queryClass.getName().substring(
                queryClass.getPackage().getName().length() + 1,
                queryClass.getName().length());

        int strIdx = 0;
        int aliasIdx = hql.indexOf(" " + className + " ", strIdx);
        while(aliasIdx != -1) {
            StringBuffer alias = new StringBuffer();
            strIdx = aliasIdx + 1 + className.length();

            while(strIdx < hql.length()
                    && ParserHelper.isWhitespace(Character.toString(hql
                            .charAt(strIdx)))) {
                strIdx++;
            }
            while(strIdx < hql.length()
                    && !(ParserHelper.isWhitespace(Character.toString(hql
                            .charAt(strIdx))) || (ParserHelper.HQL_SEPARATORS
                            .indexOf(hql.charAt(strIdx)) != -1))) {
                alias.append(hql.charAt(strIdx));
                strIdx++;
            }

            if(alias.length() != 0)
                aliasList.add(alias.toString());

            aliasIdx = hql.indexOf(" " + className + " ", strIdx);
        }

        return (String[])aliasList.toArray(new String[aliasList.size()]);
    }

    /**
     * Return a new HQL object which appended order by statement from current
     * HQL statement.
     * 
     * @param target Target class.
     * @param orderBy Order by member.
     * @param isAscending Ascending or not.
     * @return HQL
     */
    public HQL appendOrderBy(Class target, String orderBy, boolean isAscending) {
        return appendOrderBy(getAlias(target)[0] + "." + orderBy, isAscending);
    }

    /**
     * Return a new HQL object which appended order by statement from current
     * HQL statement.
     * 
     * @param orderBy Order by member.
     * @param isAscending Ascending or not.
     * @return HQL
     */
    public HQL appendOrderBy(String orderBy, boolean isAscending) {
        StringBuffer newHql = new StringBuffer(hql);

        if(orderBy != null) {
            newHql.append(" order by ").append(orderBy);
            if(isAscending) {
                newHql.append(" asc");
            } else {
                newHql.append(" desc");
            }
        }

        return new HQL(newHql.toString());
    }

    /**
     * Return a new HQL object which appended select count(*) statement from
     * current HQL statement.
     * 
     * @return HQL
     */
    public HQL appendSelectCount() {
        StringBuffer newHql = new StringBuffer(hql);

        if(isBeginWithFrom())
            newHql.insert(0, "select count(*) ");

        return new HQL(newHql.toString());
    }

    /**
     * Is HQL begin with from?
     * 
     * @return true or false
     */
    public boolean isBeginWithFrom() {
        return (hql.trim().toLowerCase().indexOf("from") == 0 ? true : false);
    }

    /**
     * Is HQL begin with select?
     * 
     * @return true or false
     */
    public boolean isBeginWithSelect() {
        return (hql.trim().toLowerCase().indexOf("select") == 0 ? true : false);
    }

    /**
     * Return a new HQL object which appended select alias.field statement from
     * current HQL statement.
     * 
     * @param target Target class.
     * @param field Field string.
     * @return HQL
     */
    public HQL appendSelect(Class target, String field) {
        return appendSelect(getAlias(target)[0] + "." + field);
    }

    /**
     * Return a new HQL object which appended select alias.field statement from
     * current HQL statement.
     * 
     * @param selectTarget Target string of select.
     * @return HQL
     */
    public HQL appendSelect(String selectTarget) {
        StringBuffer newHql = new StringBuffer(hql);
        String mustAppend = selectTarget + " ";
        if(isBeginWithFrom())
            newHql.insert(0, "select " + mustAppend);
        else if(isBeginWithSelect())
            newHql.insert(hql.toLowerCase().indexOf("from"), mustAppend);
        else
            ; // ???

        return new HQL(newHql.toString());
    }

    /**
     * Return a new HQL object which appended where expression statement from
     * current HQL statement.
     * 
     * @param whereCondition Where condition string.
     * @return HQL
     */
    public HQL appendWhere(String whereCondition) {
        return appendWhere(whereCondition, HQL.LOGIC_AND);
    }

    /**
     * Return a new HQL object which appended where expression statement from
     * current HQL statement.
     * 
     * @param whereCondition Where condition string.
     * @param logicSymbol And, Or, Not ... etc.
     * @return HQL
     */
    public HQL appendWhere(String whereCondition, String logicSymbol) {
        StringBuffer newHql = new StringBuffer(hql);

        int insertPoint;
        if(hql.toLowerCase().indexOf("group by") != -1) {
            insertPoint = hql.toLowerCase().indexOf("group by");
        } else if(hql.toLowerCase().indexOf("order by") != -1) {
            insertPoint = hql.toLowerCase().indexOf("order by");
        } else {
            insertPoint = hql.length();
        }

        // where clause existed.
        if(hql.toLowerCase().indexOf("where") != -1) {
            newHql.insert(insertPoint, " " + logicSymbol + " " + whereCondition
                    + " ");
        } else {
            newHql.insert(insertPoint, " where " + whereCondition + " ");
        }

        return new HQL(newHql.toString());
    }

    /**
     * Get hql parameters.
     * 
     * @return String array of name of parameters.
     */
    public String[] getParameters() {
        return parameters;
    }

    public String toString() {
    	logger.info("hql=" + hql);
        return hql;
    }
}