package com.kangdainfo.tcbw.view.prcond;

import java.io.File;
import java.util.Map;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.BeanUtil;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;


/**
 *<br>程式目的：
 *<br>程式代號：
 *<br>程式日期：
 *<br>程式作者：
 *<br>--------------------------------------------------------
 *<br>修改作者　　修改日期　　　修改目的
 *<br>--------------------------------------------------------
 */
public class  PROCOND0102F extends SuperBean
{

	private String userId;
	private String userName;
	private String type;
	private String js;
	private String popId;
	private String popCodeName;
	private String popCode;
	
	private String q_userId;
	private String q_userName;
	
	@Override
	public void doCreate() throws Exception 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void doDelete() throws Exception 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public Object doQueryAll() throws Exception 
	{
        
		
		StringBuilder sb = new StringBuilder(300).append("from CommonUser where 1=1  ");
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String[] splitStr;
		
		String userId="";
		
		splitStr = getUserId().split(",");	 
		
		for(int j = 0 ; j < splitStr.length ; j++)
        {
			userId+="'"+splitStr[j]+"',";
        }
		
		userId=userId.substring(0,userId.length()-1);
	
	
		if(!"".equals(getUserId()) && "1".equals(getType())  )
		{
			sb.append(" and userId not in (").append(userId).append(")");		
		}
		
		if(!"".equals(getUserId()) && "2".equals(getType())  )
		{
			sb.append(" and userId  in (").append(userId).append(")");		
		}
			
		if(!"".equals(getQ_userId()))
		{
			sb.append(" and userId  like '%").append(getQ_userId()).append("%'");		
		}
		
		if(!"".equals(getQ_userName()))
		{
			sb.append(" and userName  like '%").append(getQ_userName()).append("%'");		
		}
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().getCommonCodeDao().loadCount(sb.toString()));
		
		
		System.out.println(sb.toString());
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getHibernateTemplate().clear();			
		
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(sb.toString()+" order by id ");
			
			try 
			{
				if (objList!=null && objList.size()>0) 
				{
					for (int i=0; i<objList.size(); i++) 
					{
						
						CommonUser o = (CommonUser) objList.get(i);		
						
						String rowArray[]=new String[3];	
						rowArray[0]=Common.get(o.getId());
						rowArray[1]=Common.get(o.getUserId());
						rowArray[2]=Common.get(o.getUserName());
						
						arrList.add(rowArray);					
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return arrList;
	}


	@Override
	public Object doQueryOne() throws Exception 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void doUpdate() throws Exception 
	{
		// TODO Auto-generated method stub
		
	}


	public String getUserId() {
		return checkGet(userId);
	}


	public void setUserId(String userId) {
		this.userId = checkSet(userId);
	}


	public String getType() {
		return checkGet(type);
	}


	public void setType(String type) {
		this.type = checkSet(type);
	}


	public String getJs() {
		return checkGet(js);
	}


	public void setJs(String js) {
		this.js = checkSet(js);
	}


	public String getPopId() {
		return checkGet(popId);
	}


	public void setPopId(String popId) {
		this.popId = checkSet(popId);
	}


	public String getPopCodeName() {
		return checkGet(popCodeName);
	}


	public void setPopCodeName(String popCodeName) {
		this.popCodeName = checkSet(popCodeName);
	}


	public String getPopCode() {
		return checkGet(popCode);
	}


	public void setPopCode(String popCode) {
		this.popCode = checkSet(popCode);
	}


	public String getUserName() {
		return checkGet(userName);
	}


	public void setUserName(String userName) {
		this.userName = checkSet(userName);
	}


	public String getQ_userId() {
		return checkGet(q_userId);
	}


	public void setQ_userId(String qUserId) {
		q_userId = checkSet(qUserId);
	}


	public String getQ_userName() {
		return checkGet(q_userName);
	}


	public void setQ_userName(String qUserName) {
		q_userName = checkSet(qUserName);
	}


	




	
	

      
}
