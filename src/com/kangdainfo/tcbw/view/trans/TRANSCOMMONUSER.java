package com.kangdainfo.tcbw.view.trans;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class TRANSCOMMONUSER extends SuperBean
{
	private String msg;	
	private StringBuilder transMsg;		
	public String getMsg() {return checkGet(msg);}
	public void setMsg(String msg) {this.msg = checkSet(msg);}	
	public StringBuilder getTransMsg() {return transMsg;}
	public void setTransMsg(StringBuilder transMsg) {this.transMsg = transMsg;}
	
	
	//CommonUser
	public void trans() throws Exception 
	{		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(" from CommonUser where trans='Y' ");
		if(objList!=null && objList.size()>0)
		{
			java.util.List<CommonUser> updateList = new java.util.ArrayList<CommonUser>();
			for(Object dtlObj : objList)
			{
				CommonUser obj = (CommonUser)dtlObj;
				
				//System.out.println("ID=="+obj.getUserId());
				//如果帳號是身分證字號，強制改帳號
				if(TCBWCommon.checkIdNum(Common.get(obj.getUserId()))){
					//System.out.println("IsGetNewID==Y");
					obj.setIsGetNewID("Y");
				}
				//密碼加密
				if(!"".equals(Common.get(obj.getUserPwd()))){				
					obj.setUserPwd(Common.getDigestString(obj.getUserPwd(), "SHA-1"));
				}
				//身分證字號加密
				if(!"".equals(Common.get(obj.getPersonalId()))){
					obj.setPersonalId(TCBWCommon.getEncodeString(obj.getPersonalId()));
				}
				//System.out.println("PersonalId=="+obj.getPersonalId());
				//System.out.println("UserPwd=="+obj.getUserPwd());
				updateList.add(obj);
			}
			
			if (updateList!=null && updateList.size()>0) 
				ServiceGetter.getInstance().getTcbwService().updateBatch(updateList);
			
			this.transMsg = new StringBuilder();			
			transMsg.append("<font color='#0000CC'>更新筆數：</font><font color='red'>").append(updateList.size()).append("</font><br>");
			setMsg("匯入成功!");
		}
	}
	
	
	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
