package com.kangdainfo.tcbw.view.medin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med0001Db;

import com.kangdainfo.tcbw.util.TCBWCommon;

public class MEDIN0501F extends SuperBean{

	private String[] fds;
	public String[] getFds(){	return fds;		}
	public void setFds(String[] s){ 	fds = s;	}

	private javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	public void doSend() throws Exception
	{
		StringBuffer sb = new StringBuffer();
		if(getFds()!=null && getFds().length>0)
		{
			
			String x="N";
			String str="";
			
			int i=0;
			for(String rid : getFds())
			{	
				if(!"".equals(Common.get(rid)))
				{
					String medPermitFirmCode=View.getLookupField("select medPermitFirmCode from Med0001Db where id="+rid);
					
					if(i>0 && !str.equals(medPermitFirmCode))
					{
						x="Y";
					}	
					
					if(sb.toString().length() > 0)
					{
						sb.append(",");
					}
					sb.append(Common.getLong(rid));

					str=medPermitFirmCode;
	
					i++;
				}
			}
			
			if("Y".equals(x))
			{	
			  setErrorMsg("許可證申請場不相同，請重新勾選!");
			}
			else
			{
				
			  ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateSendByMEDIN0501F(this,sb.toString());
				
			  setErrorMsg("廠商通知完成!");
			}
		}
		
		System.out.println(sb.toString());
		
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med0001Db where status = '50' " ;
			
		System.out.println("[TCBW]-[MEDIN0501F]-[QUERYALL] : " + hql + " order by id ");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			//java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc");
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("MEDSTATUS2");
				
				java.util.Map<String, String> medPermitMap = TCBWCommon.getCommonCodeMap("MEDPKID");
				
				for(Object dtlObj : objList)
				{
					Med0001Db dtl = (Med0001Db)dtlObj;
					
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					
					if(!"".equals(Common.get(dtl.getMedPermit())))
						rowArray[2] = Common.get(medPermitMap.get(dtl.getMedPermit()))+"字第"+ Common.get(dtl.getMedPermitNumber())+"號";	
					else
						rowArray[2] ="";
					
					rowArray[3] = Common.get(dtl.getMedPermitFirmCode());
					rowArray[4] = Common.get(dtl.getMedPermitFirm());
					arrList.add(rowArray);
				}
				statusMap.clear();
				objList.clear();
			}
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
