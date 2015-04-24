package com.kangdainfo.persistence.hibernate3.query.params;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.kangdainfo.persistence.hibernate3.QueryCondition;
import com.kangdainfo.util.DateUtil;
import com.kangdainfo.util.TaiwanDateUtil;
import com.kangdainfo.util.lang.StringUtil;


public class QueryParams {

	private static Logger logger = Logger.getLogger(QueryParams.class);
	
	private List queryParamList;
	private boolean distinct;
	private String distinctFieldName;
	

	public QueryParams() {
		this.queryParamList = new ArrayList();
	}
	public QueryParams(List queryParamList) {
		this();
		for(int i=0; i < queryParamList.size(); i++) {
			QueryParam queryParam = (QueryParam) queryParamList.get(i);
			add(queryParam);
		}
	}

	public boolean isEmpty() {
		return queryParamList.isEmpty();
	}
	
	public List getQueryParamList() { return queryParamList; }
	public void setQueryParamList(List queryParamList) { this.queryParamList = queryParamList; }
	public boolean isDistinct() { return distinct; }
	public void setDistinct(boolean distinct) { this.distinct = distinct; }
	public String getDistinctFieldName() { return distinctFieldName; }
	public void setDistinctFieldName(String distinctFieldName) { this.distinctFieldName = distinctFieldName; }
	
	
	public void add(QueryParam queryParam) {
		queryParam.setSafeName(getSafeParameterName(queryParam).replace('.', '_'));
		this.queryParamList.add(queryParam);
	}
	
	// To avoid parameter name clash
	public String getSafeParameterName(QueryParam qp) {
		int sameNameCount = 0;
		for(int i=0; i < queryParamList.size(); i++) {
			QueryParam queryParam = (QueryParam) queryParamList.get(i);
			if(qp.getName().equals(queryParam.getName())) {
				if(qp.hashCode()==queryParam.hashCode() && sameNameCount>0) {
					return qp.getName() + i;
				}
				sameNameCount++;
			}
		}
		return qp.getName();
	}
	
	public QueryParam get(int i) {
		return (QueryParam) queryParamList.get(i);
	}
	
	public Object clone() {
		if(queryParamList==null) {
			return null;
		}
		List newQueryParamList = new ArrayList();
		for(int i=0; i < queryParamList.size(); i++) {
			QueryParam queryParam = (QueryParam) queryParamList.get(i);
			if(queryParam!=null) {
				newQueryParamList.add(queryParam.clone());
			}
			else {
				newQueryParamList.add(null);
			}
		}
		QueryParams queryParams = new QueryParams(newQueryParamList);
		return queryParams;
	}


	public boolean isEmpty(String s) {
		return StringUtil.isNullOrTrailingWhiteSpace(s);
	}
	public void addTaiwanDateBetweenQueryParam(String field, Date begin, Date end) {
		if(begin!=null && end!=null) {			
			Date dates[] = new Date[2];
			dates[0] = begin;
			dates[1] = end;
			add(new QueryParam(QueryCondition.AND,field, QueryCondition.BETWEEN, dates));
			
			/*
			add(new QueryParam(QueryCondition.AND,field, QueryCondition.GE_EQ, begin));
			add(new QueryParam(QueryCondition.AND,field, QueryCondition.LE_EQ, end));
			*/
		}
		else if(begin!=null) {
			add(new QueryParam(QueryCondition.AND,field, QueryCondition.GE_EQ, begin));
		}
		else if(end!=null) {
			add(new QueryParam(QueryCondition.AND,field, QueryCondition.LE_EQ, end));
		}
	}
	
	public void addTaiwanDateBetweenQueryParam(String field, String yyymmddBegin, String yyymmddEnd) {
		Date beginDate = StringUtil.isNullOrTrailingWhiteSpace(yyymmddBegin)?null:TaiwanDateUtil.parseToDate(yyymmddBegin);
		Date endDate = StringUtil.isNullOrTrailingWhiteSpace(yyymmddEnd)?null:TaiwanDateUtil.parseToDate(yyymmddEnd);
		if(beginDate!=null) {
			beginDate = DateUtil.getStartOfDay(beginDate);
		}
		if(endDate!=null) {
			endDate = DateUtil.getEndOfDay(endDate);		
		}
		logger.info("beginDate=" + beginDate);
		logger.info("endDate=" + endDate);
		addTaiwanDateBetweenQueryParam(field,beginDate,endDate);
	}

	public void addTaiwanDateQueryParam(String field, Date targetDate) {
		if(targetDate!=null) {
			Date beginDate = DateUtil.getStartOfDay(targetDate);
			Date endDate = DateUtil.getEndOfDay(targetDate);
			
			Date dates[] = new Date[2];
			dates[0] = beginDate;
			dates[1] = endDate;
			//add(new QueryParam(QueryCondition.AND,field, QueryCondition.GE_EQ, beginDate));
			//add(new QueryParam(QueryCondition.AND,field, QueryCondition.LE_EQ, endDate));
			add(new QueryParam(QueryCondition.AND,field, QueryCondition.BETWEEN, dates));	
		}
	}
	public void addTaiwanDateQueryParam(String field, String yyymmdd) {
		Date targetDate = StringUtil.isNullOrTrailingWhiteSpace(yyymmdd)?null:TaiwanDateUtil.parseToDate(yyymmdd);
		addTaiwanDateQueryParam(field,targetDate);	
	}
	
	public void addDepartmentFullCodeBetweenQueryParam(String field, String departmentFullCodeBegin, String departmentFullCodeEnd) {
		if(!StringUtil.isNullOrEmpty(departmentFullCodeBegin) && StringUtil.isNullOrEmpty(departmentFullCodeEnd)) {
			add(new QueryParam(QueryCondition.AND,field, QueryCondition.GE_EQ, departmentFullCodeBegin));
		}
		else if(StringUtil.isNullOrEmpty(departmentFullCodeBegin) && !StringUtil.isNullOrEmpty(departmentFullCodeEnd)) {
			add(new QueryParam(QueryCondition.AND,field, QueryCondition.LE_EQ, departmentFullCodeEnd));
		}
		else if(!StringUtil.isNullOrEmpty(departmentFullCodeBegin) && !StringUtil.isNullOrEmpty(departmentFullCodeEnd)) {
			String departmentCodes[] = new String[2];
			departmentCodes[0] = departmentFullCodeBegin;
			departmentCodes[1] = departmentFullCodeEnd;
			add(new QueryParam(QueryCondition.AND,field, QueryCondition.BETWEEN, departmentCodes));
			
			
			//add(new QueryParam(QueryCondition.AND,field, QueryCondition.GE_EQ, departmentFullCodeBegin));
			//add(new QueryParam(QueryCondition.AND,field, QueryCondition.LE_EQ, departmentFullCodeEnd));
		}
	}
	
	
}
