package com.kangdainfo.tcbw.view.medse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med1005Db;
import com.kangdainfo.tcbw.model.bo.Med1006Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class MEDSE0004F extends SuperBean{

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
	
	@Override
	public Object doQueryOne() throws Exception {
		MEDSE0004F obj = this;
		Med1006Db m = (Med1006Db)View.getObject("from Med1006Db where id = " + Common.getLong(getId()));		
		
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
		String hql = " from Med1006Db where 1 = 1 ";
		hql += "and codelevel = '1'";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
			
		}else{
			if(!"".equals(getQ_code()))
				hql += " and code like " + Common.sqlChar("%" + getQ_code() + "%");
			if(!"".equals(getQ_name()))
				hql += " and name like " + Common.sqlChar("%" + getQ_name() + "%");
			if(!"".equals(getQ_fdaCode()))
				hql += " and fdaCode like " + Common.sqlChar("%" + getQ_fdaCode() + "%");
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
					Med1006Db dtl = (Med1006Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getCode());
					rowArray[2] = Common.get(dtl.getName());
					rowArray[3] = Common.get(dtl.getTerm());
					if(dtl.getDefinition().length() > 100) {
						rowArray[4] = Common.get(dtl.getDefinition().substring(0,100));
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
		Med1006Db obj = new Med1006Db();		
		obj.setCode(getCode());
		obj.setName(getName());
		obj.setTerm(getTerm());
		obj.setDefinition(getDefinition());
		obj.setFdacode(getFdaCode());
		obj.setFdaname(getFdaName());
	    obj.setCreator(getEditID());
	    obj.setCreatedate(getEditDate());
	    obj.setCreatetime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifydate(getEditDate());
	    obj.setModifytime(getEditTime());
	    obj.setCodelevel("1");
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}

	@Override
	public void doUpdate() throws Exception {
		Med1006Db obj = (Med1006Db)View.getObject(" from Med1006Db where id = " + Common.getLong(getId()));
		String lv1Code = obj.getCode();
		if(obj != null){		
			obj.setCode(getCode());
			obj.setName(getName());
			obj.setTerm(getTerm());
			obj.setDefinition(getDefinition());
			obj.setFdacode(getFdaCode());
			obj.setFdaname(getFdaName());
		    obj.setModifier(getEditID());
		    obj.setModifydate(getEditDate());
		    obj.setModifytime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
			
			//修改第一層的code時一併修改第二層的level1code
			java.util.List listLv2 = ServiceGetter.getInstance().getCommonService().load(" from Med1006Db where level1Code = " + Common.sqlChar(lv1Code));
			if(listLv2 != null && listLv2.size() > 0) {
				for (int i=0; i<listLv2.size(); i++) {
			    	Med1006Db objLv2 = (Med1006Db) listLv2.get(i);
			    	objLv2.setLevel1code(getCode());
			    	ServiceGetter.getInstance().getTcbwService().update(obj);
			    }
			}
		}
	}

	@Override
	public void doDelete() throws Exception {
		Med1006Db obj = (Med1006Db)View.getObject(" from Med1006Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
}
