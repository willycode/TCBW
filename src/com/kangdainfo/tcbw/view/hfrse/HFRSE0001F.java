package com.kangdainfo.tcbw.view.hfrse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Hfr1006Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFRSE0001F extends SuperBean{

	private String id;
	private String handleLevel;
	private String[] evidentiary;
	private String[] severity;
	private String type;
	private String caseNum;
	
	private String q_isQuery;
	private String q_id;
	private String q_handleLevel;
	private String q_evidentiary;
	private String q_severity;

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getHandleLevel() {
		return checkGet(handleLevel);
	}

	public void setHandleLevel(String handleLevel) {
		this.handleLevel = checkSet(handleLevel);
	}

	public String[] getEvidentiary() {
		return evidentiary;
	}

	public void setEvidentiary(String[] evidentiary) {
		this.evidentiary = evidentiary;
	}

	public String[] getSeverity() {
		return severity;
	}

	public void setSeverity(String[] severity) {
		this.severity = severity;
	}

	public String getType() {
		return checkGet(type);
	}

	public void setType(String type) {
		this.type = checkSet(type);
	}

	public String getCaseNum() {
		return checkGet(caseNum);
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = checkSet(caseNum);
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

	public String getQ_handleLevel() {
		return checkGet(q_handleLevel);
	}

	public void setQ_handleLevel(String qHandleLevel) {
		q_handleLevel = checkSet(qHandleLevel);
	}
	
	public String getQ_evidentiary() {
		return checkGet(q_evidentiary);
	}

	public void setQ_evidentiary(String qEvidentiary) {
		q_evidentiary = checkSet(qEvidentiary);
	}
	
	public String getQ_severity() {
		return checkGet(q_severity);
	}

	public void setQ_severity(String qSeverity) {
		q_severity = checkSet(qSeverity);
	}

	@Override
	public Object doQueryOne() throws Exception {
		HFRSE0001F obj = this;
		Hfr1006Db c = (Hfr1006Db)View.getObject("from Hfr1006Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setHandleLevel(c.getHandleLevel());
			if(!"".equals(Common.get(c.getEvidentiary())))
	    		this.evidentiary = c.getEvidentiary().split(",");
	    	else
	    		this.evidentiary = null;
			if(!"".equals(Common.get(c.getSeverity())))
	    		this.severity = c.getSeverity().split(",");
	    	else
	    		this.severity = null;
			obj.setType(c.getType());
			obj.setCaseNum(c.getCaseNum());
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());		
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Hfr1006Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_handleLevel()))
				hql += " and handleLevel = " + Common.sqlChar(getQ_handleLevel());
			if(!"".equals(getQ_evidentiary()))
				hql += " and evidentiary = " + Common.sqlChar(getQ_evidentiary());
			if(!"".equals(getQ_severity()))
				hql += " and severity = " + Common.sqlChar(getQ_severity());
		}
		System.out.println("HQL:" + hql);
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> handleMap = TCBWCommon.getCommonCodeMap("HFRDRSP");
				java.util.Map<String, String> evidentiaryMap = TCBWCommon.getCommonCodeMap("HFRFEV");
				java.util.Map<String, String> severityMap = TCBWCommon.getCommonCodeMap("HFRSVR");
				
				for(Object dtlObj : objList) {				
					Hfr1006Db dtl = (Hfr1006Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(handleMap.get(dtl.getHandleLevel()));	
					String[] tempItem;
					String tempEvidentiary = "", tempSeverity = "";
					if(!"".equals(Common.get(dtl.getEvidentiary()))){
						tempItem = dtl.getEvidentiary().split(",");
						for(String o: tempItem){
							tempEvidentiary += Common.get(evidentiaryMap.get(o)) + ",";
						}
						rowArray[2] = tempEvidentiary.substring(0, tempEvidentiary.length()-1);
					}else{
						rowArray[2] = tempEvidentiary;
					}
					if(!"".equals(Common.get(dtl.getSeverity()))){
						tempItem = dtl.getSeverity().split(",");
						for(String o: tempItem){
							tempSeverity += Common.get(severityMap.get(o)) + ",";
						}
						rowArray[3] = tempSeverity.substring(0, tempSeverity.length()-1);
					}else{
						rowArray[3] = tempSeverity;
					}
					String tempType = Common.get(dtl.getType()).equals("1")?">":Common.get(dtl.getType()).equals("2")?">=":Common.get(dtl.getType()).equals("3")?"<":Common.get(dtl.getType()).equals("4")?"<=":"";
					rowArray[4] = tempType + Common.get(dtl.getCaseNum());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}
	
	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
		/*
	 	checkSQLArray[0][0] = "select count(*) from Cos1001Db where dpdKind = " + Common.sqlChar(getDpdKindB() + getDpdKindS());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該不良品缺陷代碼已存在，請重新輸入！";
		*/
		return checkSQLArray;
	}

	@Override
	public void doCreate() throws Exception {
		Hfr1006Db obj = new Hfr1006Db();
	    obj.setHandleLevel(getHandleLevel());
	    
	    String tempEvidentiary = "", tempSeverity = "";
		for(String o: getEvidentiary()){
			tempEvidentiary += o + ",";
		}
		if(!"".equals(tempEvidentiary))
			tempEvidentiary = tempEvidentiary.substring(0, tempEvidentiary.length()-1);
	    obj.setEvidentiary(tempEvidentiary);
		
	    for(String o: getSeverity()){
	    	tempSeverity += o + ",";
		}
		if(!"".equals(tempSeverity))
			tempSeverity = tempSeverity.substring(0, tempSeverity.length()-1);	    
	    obj.setSeverity(tempSeverity);
	    
	    obj.setType(getType());
	    obj.setCaseNum(getCaseNum());
	    obj.setCreator(getEditID());
	    obj.setCreateDate(getEditDate());
	    obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}
	
	protected String[][] getUpdateCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
		/*
		checkSQLArray[0][0] = "select count(*) from Cos1001Db where dpdKind = " + Common.sqlChar(getDpdKindB() + getDpdKindS()) + " and id != " + Common.getLong(getId()); 	
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該不良品缺陷代碼已存在，請重新輸入！";	
		*/
		return checkSQLArray;		
	}

	@Override
	public void doUpdate() throws Exception {
		Hfr1006Db obj = (Hfr1006Db)View.getObject(" from Hfr1006Db where id = " + Common.getLong(getId()));
		if(obj != null){		
		    obj.setHandleLevel(getHandleLevel());
		    
		    String tempEvidentiary = "", tempSeverity = "";
			for(String o: getEvidentiary()){
				tempEvidentiary += o + ",";
			}
			if(!"".equals(tempEvidentiary))
				tempEvidentiary = tempEvidentiary.substring(0, tempEvidentiary.length()-1);
		    obj.setEvidentiary(tempEvidentiary);
			
		    for(String o: getSeverity()){
		    	tempSeverity += o + ",";
			}
			if(!"".equals(tempSeverity))
				tempSeverity = tempSeverity.substring(0, tempSeverity.length()-1);	    
		    obj.setSeverity(tempSeverity);
		    
		    obj.setType(getType());
		    obj.setCaseNum(getCaseNum());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Hfr1006Db obj = (Hfr1006Db)View.getObject(" from Hfr1006Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
