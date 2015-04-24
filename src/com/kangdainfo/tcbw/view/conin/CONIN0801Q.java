package com.kangdainfo.tcbw.view.conin;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonLog;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONIN0801Q extends SuperBean
{

	private String id;
	private String code;//通報表代碼
	private String openDate;//開啟日期
	private String openTime;//開啟時間
	private String userId;//開始人員
	private String ip;//開啟人員IP
	private String methodName;//方法
	private String db;//資料表
	private String dbId;//資料表IP
	private String applNo;//
	private String openUserId;//被開啟USERID
	private String openUserName;
	
	private String q_openDateS;//開啟日期
	private String q_openDateE;//開啟日期
	private String q_openUserId;//被開啟USERID
	private String q_openUserName;
	private String q_userId;
	

	@Override
	public void doCreate() throws Exception 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public Object doQueryAll() throws Exception 
	{
        java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql =" from CommonLog where 1=1";
		       
		if(!"".equals(getQ_openDateS()))
		{
			hql+=" and openDate >="+Common.sqlChar(getQ_openDateS());
		}
		
		if(!"".equals(getQ_userId()))
		{
			hql+=" and userId like "+TCBWCommon.likeSqlChar(getQ_userId());
		}
			
		if(!"".equals(getQ_openUserId()))
		{
			hql+=" and openUserId like "+TCBWCommon.likeSqlChar(getQ_openUserId());
		}
		
		if(!"".equals(getQ_openUserName()))
		{
			hql+=" and openUserName like "+TCBWCommon.likeSqlChar(getQ_openUserName());
		}
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		
		System.out.println("CONIN0801Q="+hql);
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();

			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql, this.getRecordStart(), this.getPageSize());
			
			if (objList!=null && objList.size()>0) 
			{
				
				java.util.Map<String,String> formdNameMap=TCBWCommon.getMap("select formCode,formdName from Con1007Db");
				java.util.Map<String,String> nameMap=TCBWCommon.getCapitalMap();
				
				for (int i=0; i<objList.size(); i++) 
				{	
					CommonLog o = (CommonLog) objList.get(i);
					
					String rowArray[]=new String[6];					
					
					rowArray[0]=Common.get(o.getId());
					
					String name=formdNameMap.get(o.getCode());
					
					if(null==name)
					{
						name=nameMap.get(o.getCode());
					}

					rowArray[1]=Common.get(name);//名稱
					rowArray[2]=Common.get(o.getOpenDate());//開啟日期
					rowArray[3]=Common.get(o.getUserId());//查詢人員
					rowArray[4]=Common.get(o.getOpenUserId());//被開啟通報者帳號
					rowArray[5]=Common.get(o.getOpenUserName());//被開啟通報者姓名
				
					arrList.add(rowArray);					
				}
			}
		} 
		
		
		return arrList;
	}
	
	
	
	@Override
	public Object doQueryOne() throws Exception 
	{
        CONIN0801Q obj = this;
		
		CommonLog c = (CommonLog) View.getObject("from CommonLog where id = " + getId());

		if (c != null) 
		{
			obj.setCode(c.getCode());//通報表代碼
			obj.setOpenDate(c.getOpenDate());//開啟日期
			obj.setOpenTime(c.getOpenTime());//開啟時間
			obj.setUserId(c.getUserId());//開始人員
			obj.setIp(c.getIp());//開啟人員IP
			obj.setMethodName(c.getMethodName());//方法
			obj.setDb(c.getDb());//資料表
			obj.setDbId(c.getDbId());//資料表IP
			obj.setApplNo(c.getApplNo());//
			obj.setOpenUserId(c.getOpenUserId());//被開啟ID
			obj.setOpenUserName(c.getOpenUserName());
		}
	    else
	    {
		  throw new Exception("查無該筆資料！");
	    }
		
		return obj;
	}
	
	
	@Override
	public void doUpdate() throws Exception 
	{
		// TODO Auto-generated method stub
		
	}
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
	public String getOpenDate() {
		return checkGet(openDate);
	}
	public void setOpenDate(String openDate) {
		this.openDate = checkSet(openDate);
	}
	public String getOpenTime() {
		return checkGet(openTime);
	}
	public void setOpenTime(String openTime) {
		this.openTime = checkSet(openTime);
	}
	public String getUserId() {
		return checkGet(userId);
	}
	public void setUserId(String userId) {
		this.userId = checkSet(userId);
	}
	public String getIp() {
		return checkGet(ip);
	}
	public void setIp(String ip) {
		this.ip = checkSet(ip);
	}
	public String getMethodName() {
		return checkGet(methodName);
	}
	public void setMethodName(String methodName) {
		this.methodName = checkSet(methodName);
	}
	public String getDb() {
		return checkGet(db);
	}
	public void setDb(String db) {
		this.db = checkSet(db);
	}
	public String getDbId() {
		return checkGet(dbId);
	}
	public void setDbId(String dbId) {
		this.dbId = checkSet(dbId);
	}
	public String getApplNo() {
		return checkGet(applNo);
	}
	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}
	public String getOpenUserId() {
		return checkGet(openUserId);
	}
	public void setOpenUserId(String openUserId) {
		this.openUserId = checkSet(openUserId);
	}
	public String getOpenUserName() {
		return checkGet(openUserName);
	}
	public void setOpenUserName(String openUserName) {
		this.openUserName = checkSet(openUserName);
	}
	public String getQ_openDateS() {
		return checkGet(q_openDateS);
	}
	public void setQ_openDateS(String qOpenDateS) {
		q_openDateS = checkSet(qOpenDateS);
	}
	public String getQ_openDateE() {
		return checkGet(q_openDateE);
	}
	public void setQ_openDateE(String qOpenDateE) {
		q_openDateE = checkSet(qOpenDateE);
	}
	public String getQ_openUserId() {
		return checkGet(q_openUserId);
	}
	public void setQ_openUserId(String qOpenUserId) {
		q_openUserId = checkSet(qOpenUserId);
	}
	public String getQ_openUserName() {
		return checkGet(q_openUserName);
	}
	public void setQ_openUserName(String qOpenUserName) {
		q_openUserName = checkSet(qOpenUserName);
	}
	public String getQ_userId() {
		return checkGet(q_userId);
	}
	public void setQ_userId(String qUserId) {
		q_userId = checkSet(qUserId);
	}
	

   
	


}
