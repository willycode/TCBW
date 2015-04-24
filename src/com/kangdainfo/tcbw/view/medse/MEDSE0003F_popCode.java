package com.kangdainfo.tcbw.view.medse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1012Db;
import com.kangdainfo.tcbw.model.bo.Med1005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class MEDSE0003F_popCode extends SuperBean{

	private String id;
	private String code;	//代碼
	private String name;	//名稱
	private String term;
	private String definition;
	private String fdaCode;		//FDA代碼
	private String fdaName;		//FDA名稱
	
	private String q_isQuery;
	private String q_id;
	private String q_code;
	private String q_name;
	private String q_fdaCode;
	private String q_fdaName;

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getCode() {
		return checkGet(code);
	}

	public void setCode(String code) {
		this.code = checkSet(code);
	}
	
	public String getName() {
		return checkGet(name);
	}

	public void setName(String name) {
		this.name = checkSet(name);
	}

	public String getTerm() {
		return checkGet(term);
	}

	public void setTerm(String term) {
		this.term = checkSet(term);
	}
	
	public String getDefinition() {
		return checkGet(definition);
	}

	public void setDefinition(String definition) {
		this.definition = checkSet(definition);
	}
	public String getFdaCode() {
		return checkGet(fdaCode);
	}

	public void setFdaCode(String fdaCode) {
		this.fdaCode = checkSet(fdaCode);
	}
	public String getFdaName() {
		return checkGet(fdaName);
	}

	public void setFdaName(String fdaName) {
		this.fdaName = checkSet(fdaName);
	}


	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}

	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String qId) {
		q_id = checkSet(qId);
	}
	
	public String getQ_code() {
		return checkGet(q_code);
	}

	public void setQ_code(String qCode) {
		q_code = checkSet(qCode);
	}

	public String getQ_name() {
		return checkGet(q_name);
	}

	public void setQ_name(String qName) {
		q_name = checkSet(qName);
	}
	
	public String getQ_fdaCode() {
		return checkGet(q_fdaCode);
	}
	
	public void setQ_fdaCode(String qFDACode) {
		q_fdaCode = checkSet(qFDACode);
	}
	
	public String getQ_fdaName() {
		return checkGet(q_fdaName);
	}
	
	public void setQ_fdaName(String qFDAName) {
		q_fdaName = checkSet(qFDAName);
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		MEDSE0003F_popCode obj = this;
		Med1005Db m = (Med1005Db)View.getObject("from Med1005Db where id = " + Common.getLong(getId()));		
		
		if(m != null){
			obj.setId(Common.get(m.getId()));
			obj.setCode(m.getCode());
			obj.setName(m.getName());
			obj.setTerm(m.getTerm());
			obj.setDefinition(m.getDefinition());
			obj.setFdaCode(m.getFdacode());
			obj.setFdaName(m.getFdaname());
			obj.setEditID(m.getModifier());
			obj.setEditDate(m.getModifydate());		
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Med1005Db where 1 = 1 ";
		
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
			
		}else{
			if(!"".equals(getQ_code()))
				hql += " and code like " + Common.sqlChar("%" + getQ_code() + "%");
			if(!"".equals(getQ_name()))
				hql += " and name like " + Common.sqlChar("%" + getQ_name() + "%");
			if(!"".equals(getQ_fdaCode()))
				hql += " and fdaCode like " + Common.sqlChar("%" + getQ_fdaCode() + "%");
			if(!"".equals(getQ_fdaName()))
				hql += " and fdaName like " + Common.sqlChar("%" + getQ_fdaName() + "%");
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
//				java.util.Map<String, String> sysMap = TCBWCommon.getCommonCodeMap("SYS");

				for(Object dtlObj : objList) {	
					Med1005Db dtl = (Med1005Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getCode());
					rowArray[2] = Common.get(dtl.getName());
					rowArray[3] = Common.get(dtl.getTerm());
					if(dtl.getDefinition().length() > 50) {
						rowArray[4] = Common.get(dtl.getDefinition().subSequence(0, 50));
					} else {
						rowArray[4] = Common.get(dtl.getDefinition());
					}
					
					rowArray[5] = Common.get(dtl.getFdacode());
					rowArray[6] = Common.get(dtl.getFdaname());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
	}

	@Override
	public void doUpdate() throws Exception {
	}

	@Override
	public void doDelete() throws Exception {
	}
}
