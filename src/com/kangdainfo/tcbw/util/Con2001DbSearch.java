package com.kangdainfo.tcbw.util;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Con2001Db;

public class Con2001DbSearch extends SuperBean {

	private String systemType;
	private String formID;
	private String applNo;

	public String getSystemType() {		return checkGet(systemType);	}
	public void setSystemType(String systemType) {		this.systemType = checkSet(systemType);	}
	public String getFormID() {		return checkGet(formID);	}
	public void setFormID(String formID) {		this.formID = checkSet(formID);	}
	public String getApplNo() {		return checkGet(applNo);	}
	public void setApplNo(String applNo) {		this.applNo = checkSet(applNo);	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Con2001Db where systemType like " + Common.sqlChar(getSystemType() + "%");
	//	System.out.println("formID: " + Common.sqlChar(getFormID()));
	//	System.out.println("applNo: " + Common.sqlChar(getApplNo()));
		if(!"\'\'".equals(Common.sqlChar(getFormID()))) {
			hql += " and formID = " + Common.sqlChar(getFormID());
		}
		if(!"\'\'".equals(Common.sqlChar(getApplNo()))) {
			hql += " and applNo = " + Common.sqlChar(getApplNo());
		}
		System.out.println("[TCBW]-[Con2001DbSearch]-[QueryAll]: " + hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if(getTotalRecord() > 0){
			if(getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			if(getSystemType().startsWith("COS")){
				hql += " order by processDate, processTime ";
			}else{
				hql += " order by id ";
			}
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql, this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				String TempCode = "";
				if("DRG1".equals(Common.get(getSystemType())))
					TempCode = "DRGST1";
				else if("DRG2".equals(Common.get(getSystemType())))
					TempCode = "DRG0310";
				else if("HFR".equals(Common.get(getSystemType())))
					TempCode = "FCS";	
				else if("COS".equals(Common.get(getSystemType())))
					TempCode = "CCS";
				else if("MED01".equals(Common.get(getSystemType())))
					TempCode = "MEDSTATUS2";
				else if("MED02".equals(Common.get(getSystemType())))
					TempCode = "MEDSTATUS3";
				else if("MED03".equals(Common.get(getSystemType())))
					TempCode = "MEDSTATUS1";
				else if("MED06".equals(Common.get(getSystemType())))
					TempCode = "MEDQTSTATUS";
				else if("DRG4".equals(Common.get(getSystemType())))
					TempCode = "DRGEVSTATUS";
				
				
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap(TempCode);
				for(Object dtlObj : objList) {
					Con2001Db dtl = (Con2001Db)dtlObj;
					
					String[] rowArray = new String[6];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.formatYYYMMDD(Common.get(dtl.getProcessDate()),4);	
					rowArray[2] = Common.formatHHMMSS(Common.get(dtl.getProcessTime()));	
					rowArray[3] = Common.get(statusMap.get(dtl.getState()));
					rowArray[4] = Common.get(dtl.getUserID());
					rowArray[5] = Common.get(dtl.getProcessDesc());
					arrList.add(rowArray);
				}
				statusMap.clear();
				objList.clear();
			}		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception{}

	@Override
	public void doUpdate() throws Exception{}

	@Override
	public void doDelete() throws Exception{}
	
}


