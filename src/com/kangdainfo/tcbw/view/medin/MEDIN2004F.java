package com.kangdainfo.tcbw.view.medin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.tcbw.model.bo.Med0010Db;



public class MEDIN2004F extends MEDIN0101F
{
	
	private String id2;//序號	NUMERIC(19,0)
	private String q_notifierRevDateS;//通報日期	
	private String q_notifierRevDateE;//通報日期
	private String[] q_eventKind;
	private String[] q_badReactionResults;
	private String[] q_eventClass;
	private String q_notifyDept;
	private String changeTabValue;
	@Override
	public Object doQueryAll() throws Exception 
	{		

java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med0010Db where 1=1 ";
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		System.out.println("MEDEX0301F=="+hql);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id ", this.getRecordStart(), this.getPageSize());
			
			if(objList!=null && objList.size()>0)
			{
				for(Object dtlObj : objList)
				{
					Med0010Db dtl = (Med0010Db)dtlObj;
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getMed0001Db().getApplNo());
					rowArray[2] = Common.get(dtl.getMed0001Db().getOccurDate());
					rowArray[3] =  "<input type='button' class='toolbar_default' name='btn_Data' value='廠商回覆' onClick=\"listContainerRowClick('"+dtl.getId()+"');queryData('" + dtl.getId() + "');\"> ";
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
	}


	@Override
	public Object doQueryOne() throws Exception 
	{
		MEDIN2004F obj = this;
		return obj;
	}
	
	

	@Override
	public void doDelete() throws Exception {
		
	}
	
	@Override
	public void doCreate() throws Exception 
	{
		
	}
	

	public String getId2() {
		return checkGet(id2);
	}

	public void setId2(String id2) {
		this.id2 = checkSet(id2);
	}

	public String getQ_notifierRevDateS() {
		return checkGet(q_notifierRevDateS);
	}

	public void setQ_notifierRevDateS(String qNotifierRevDateS) {
		q_notifierRevDateS = checkSet(qNotifierRevDateS);
	}

	public String getQ_notifierRevDateE() {
		return checkGet(q_notifierRevDateE);
	}

	public void setQ_notifierRevDateE(String qNotifierRevDateE) {
		q_notifierRevDateE = checkSet(qNotifierRevDateE);
	}

	public String[] getQ_eventKind() {
		return checkGet(q_eventKind);
	}

	public void setQ_eventKind(String[] qEventKind) {
		q_eventKind = checkSet(qEventKind);
	}

	public String[] getQ_badReactionResults() {
		return checkGet(q_badReactionResults);
	}

	public void setQ_badReactionResults(String[] qBadReactionResults) {
		q_badReactionResults = checkSet(qBadReactionResults);
	}

	public String[] getQ_eventClass() {
		return checkGet(q_eventClass);
	}

	public void setQ_eventClass(String[] q_eventClass) {
		this.q_eventClass = checkSet(q_eventClass);
	}

	public String getQ_notifyDept() {
		return checkGet(q_notifyDept);
	}

	public void setQ_notifyDept(String q_notifyDept) {
		this.q_notifyDept = checkSet(q_notifyDept);
	}

	public String getChangeTabValue() {
		return checkGet(changeTabValue);
	}

	public void setChangeTabValue(String changeTabValue) {
		this.changeTabValue = checkSet(changeTabValue);
	}

	

	
	

	

	


	

}
