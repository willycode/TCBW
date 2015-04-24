package com.kangdainfo.tcbw.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Drg7001Db;
import com.kangdainfo.tcbw.model.bo.Drg7003Db;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med7002Db;

public class SendMailSearch extends SuperBean {

	
	private String id;
	private String applNo;
	private String systemType;
	private String mailID;
	private String isAdd;
	private String isJS;
	private String sendApplId;
	private String isClose;
	

	public String getId() {return checkGet(id);}
	public void setId(String s) {this.id = checkSet(s);}
	public String getApplNo() {return checkGet(applNo);}
	public void setApplNo(String s) {this.applNo = checkSet(s);}
	public String getSystemType() {return checkGet(systemType);	}
	public void setSystemType(String s) {this.systemType = checkSet(s);	}
	public String getMailID() {return checkGet(mailID);	}
	public void setMailID(String s) {this.mailID = checkSet(s);	}
	public String getIsAdd() {return checkGet(isAdd);}
	public void setIsAdd(String s) {this.isAdd = checkSet(s);}
	public String getIsJS() {return checkGet(isJS);}
	public void setIsJS(String s) {this.isJS = checkSet(s);}
	public String getSendApplId() {return checkGet(sendApplId);}
	public void setSendApplId(String s) {this.sendApplId = checkSet(s);}
	public String getIsClose() {return checkGet(isClose);}
	public void setIsClose(String s) {this.isClose = checkSet(s);}
	
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		Map<String,List<Object>> applMap = new HashMap<String,List<Object>>();
		setIsClose("Y");
		if("DRG03".equals(getMailID().substring(0, 5))){
			Object obj = ServiceGetter.getInstance().getTcbwService().getObject(" from Drg7001Db where id = " + Common.sqlChar(getId()));
			Drg7001Db drg71 = (Drg7001Db)obj;
			if(null != drg71.getDrg7003Dbs() && !drg71.getDrg7003Dbs().isEmpty()){
				for(Object dtlObj:drg71.getDrg7003Dbs()){
					Map<String,String> drg73Map = new HashMap<String,String>();
					Drg7003Db drg73 = (Drg7003Db)dtlObj;
					List<Object> applList = applMap.get(drg73.getApplicationId());
					if(null == applList || applList.isEmpty()){
						applList = new ArrayList<Object>();
					}
					drg73Map.put("id", Common.get(drg71.getId()));
					drg73Map.put("applicationId", Common.get(drg73.getApplicationId()));
					drg73Map.put("applicationName", Common.get(drg73.getApplicationName()));
					applList.add(drg73Map);
					applMap.put(drg73.getApplicationId(), applList);
				}
			}
		}else if("MED06".equals(getMailID().substring(0, 5))){
			Object obj = ServiceGetter.getInstance().getTcbwService().getObject(" from Med7001Db where id = " + Common.sqlChar(getId()));
			Med7001Db med71 = (Med7001Db)obj;
			if(null != med71.getMed7002Dbs() && !med71.getMed7002Dbs().isEmpty()){
				for(Object dtlObj:med71.getMed7002Dbs()){
					Med7002Db med72 = (Med7002Db)dtlObj;
					List<Object> applList = applMap.get(med72.getApplicationId());
					if(null == applList || applList.isEmpty()){
						applList = new ArrayList<Object>();
					}
					applList.add(med72);
					applMap.put(med72.getApplicationId(), applList);
				}
			}
		}
		
		this.setPageSize(applMap.size());
		this.processCurrentPageAttribute(applMap.size());
		if(getTotalRecord() > 0){
			if (applMap != null && applMap.size() > 0) {
				for(String applicationId:applMap.keySet()){
					List<Object> objList = applMap.get(applicationId);
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(BeanUtils.getProperty(objList.get(0), "id"));
					rowArray[1] = Common.get(BeanUtils.getProperty(objList.get(0), "applicationId"));
					rowArray[2] = Common.get(BeanUtils.getProperty(objList.get(0), "applicationName"));
					rowArray[3] = "N";
					if(null != getSendApplId() && !"".equals(getSendApplId())){
						for(String applicationSendId : getSendApplId().split(",")){
							if(applicationSendId.equals(applicationId)){
								rowArray[3] = "Y";
							}
						}
					}
					rowArray[4]="";
					if(!"Y".equals(rowArray[3])){
						setIsClose("N");
						rowArray[4]="<input class=\"field_Q\"  type=\"button\" value=\"寄送郵件\" onclick=\"queryOne('"+Common.sqlChar(Common.get(BeanUtils.getProperty(objList.get(0), "id")))+"')\">" ;
					}
					arrList.add(rowArray);
				}
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


