package com.kangdainfo.tcbw.view.drgex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con0004Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGEX0106F extends DRGEX0101F
{
	private String id1;
	private String applNo;
	private String notifyDate;
	private String notifyBody;
	private String replyDate;
	private String replyBody;
	
	public String getNotifyDate() {return checkGet(notifyDate);}
	public void setNotifyDate(String notifyDate) {this.notifyDate = checkSet(notifyDate);}
	public String getApplNo() {return checkGet(applNo);}
	public void setApplNo(String applNo) {this.applNo = checkSet(applNo);}
	public String getNotifyBody() {return checkGet(notifyBody);}
	public void setNotifyBody(String notifyBody) {this.notifyBody = checkSet(notifyBody);}
	public String getReplyDate() {return checkGet(replyDate);}
	public void setReplyDate(String replyDate) {this.replyDate = checkSet(replyDate);}
	public String getReplyBody() {return checkGet(replyBody);}
	public void setReplyBody(String replyBody) {this.replyBody = checkSet(replyBody);}
	public String getId1() {return checkGet(id1);}
	public void setId1(String id1) {this.id1 = checkSet(id1);}
	
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	//========== 附件上傳 =================
	String[] conRow;
	public String[] getConRow() {return conRow;}
	public void setConRow(String[] conRow) {this.conRow = conRow;}
	public final String[] arrConFieldName = {"fileName","fileExplan","fileRoute"};
	String conJSBuilder;
	public String getConJSBuilder() {
		if (conJSBuilder!=null) return conJSBuilder;
		else return "";
	}
	public void setConJSBuilder(String conJSBuilder) {this.conJSBuilder = conJSBuilder;}
	public String genCon0001DbSet(Long id) throws Exception {
		String hql = " from Con0001Db where fileKind='DRG' and systemType = 'DRG010001' and dbName='Con0004Db' and upLoadId="+id;
		List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
	    if (null != conList && !conList.isEmpty()) {
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for (Con0001Db dtl : conList) {       		
	    		sb.append("addConRow('conTable'");
				for (int j=0; j<arrConFieldName.length; j++) {
					if(j == 2){
						String attFile = Common.get(dtl.getFileRoute())+":;:"+Common.get(dtl.getFileName());
						sb.append(",").append(Common.sqlChar(attFile));
					}else{
						sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl, arrConFieldName[j])),true)));
					}
				}        
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setConJSBuilder(sb.toString());
	    	return sb.toString();    	
	    }
	    return "";
	}
	
	public void doReplyUpdate(String type) throws Exception 
	{
		List delList = new ArrayList();
		java.util.List<Con0001Db> conNewList = new java.util.ArrayList<Con0001Db>();
		Con0004Db obj = (Con0004Db)View.getObject(" from Con0004Db where id= " + Common.sqlChar(getId1()));
		
		String title="【補件回覆】",mailBody="",mail="";
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		if(obj!=null)
		{
			obj.setReplyBody(getReplyBody());
			obj.setReplyDate(getReplyDate());
			obj.setModifier(getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setState("updateSuccess");	
			
			//附件上傳
			if("send".equals(type)) {
	    		java.util.Set dtlSet = new ListOrderedSet();
	    		if (getHttpRequest()!=null && getConRow()!=null && getConRow().length>0) {
	    			StringBuilder dtlKey = new StringBuilder("-2,-1");		
	    			for (int i=0; i<getConRow().length; i++) {
	    				String rid = getConRow()[i];
	    				Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+Common.getLong(getHttpRequest().getParameter("conId" + rid)));							
	    				dtlKey.append(",").append(dtl.getId());
	    			}
	    			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType ='DRG010001' and dbName ='Con0004Db' and upLoadId= " + Common.getLong(obj.getId()) + " and id not in (" + dtlKey.toString() + ")"));		
	    		} else {
	    			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType ='DRG010001' and dbName ='Con0004Db' and upLoadId= " + Common.getLong(obj.getId())));
	    		}
	    	}
			if(null != delList && !delList.isEmpty()){
				ServiceGetter.getInstance().getCommonService().deleteBatch(delList);
			}
			
			
			String hql="from Drg5001Db where ";
			       hql+=" id=(select max(id) from Drg5001Db where applNo="+ Common.sqlChar(getApplNo())+")";
			
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(hql);
			
			if(drg5001Db!=null)
			{				
				Drg0001Db drg0001Db = (Drg0001Db)View.getObject(" from Drg0001Db where applNo= " + Common.sqlChar(getApplNo()));
				if("send".equals(type)){
					List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='DRG' and systemType = 'DRG010001' and dbName='Con0004Db' and upLoadId="+obj.getId());
					if(null != conList && !conList.isEmpty()){
						for(Con0001Db con : conList){
							Con0001Db newCon01 = new Con0001Db();
							org.springframework.beans.BeanUtils.copyProperties(con, newCon01, new String[]{"id","upLoadId"});
							newCon01.setDbName("Drg0001Db");
							newCon01.setUpLoadId(drg0001Db.getId());
							newCon01.setFileExplan(Common.formatYYYMMDD(obj.getReplyDate(),4)+"補件回覆");
							conNewList.add(newCon01);
						}
					}
					if(null != conNewList && !conNewList.isEmpty()){
						ServiceGetter.getInstance().getCommonService().saveBatch(conNewList);
					}
					
					if("23".equals(drg0001Db.getStatus())){				
						drg0001Db.setStatus("24");//案件分級中(已補件)			
						drg5001Db.setStatus("24");//案件分級中(已補件)
					}
					ServiceGetter.getInstance().getTcbwService().update(drg0001Db);
					ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
					
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"24", "通知補件 - 補件完成", getUserID());
					
					String email=View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(drg0001Db.getChargeMan()));
					       mail=email;
					
					title+="案號:"+drg5001Db.getApplNo();
					mailBody=getReplyBody();
					
					java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
					String[] mailAddress = mail.split(",");
					
					if(mailAddress!=null && mailAddress.length>0)
					{
						for(String s : mailAddress)
						{
							javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
						    p.setAddress(s);
						    mailList.add(p);
						}
					}
					List<String> attFilePathList = new ArrayList<String>();
					if(null != conNewList && !conNewList.isEmpty()){
						for(Con0001Db con:conNewList){
							String fileID = java.net.URLDecoder.decode(con.getFileRoute(),"UTF-8");
							if (null !=fileID && !"".equals(fileID)){
								String[] arrFileName = fileID.split(":;:"); 
					        	if(arrFileName.length>2){
					        		arrFileName[0] = Common.isValidChildFilePath(arrFileName[0]);
					        		arrFileName[1] = Common.isValidChildFilePath(arrFileName[1]);
					        		arrFileName[2] = Common.isValidChildFilePath(arrFileName[2]);

					        		attFilePathList.add(Common.isValidFilePath("./upload/DRG" +File.separator+arrFileName[0]+File.separator+arrFileName[1]));
					        	}
							}
						}
					}
					ServiceGetter.getInstance().getTcbwService().sendEmail(title, mailBody, mailList, attFilePathList, false,null, null, null,"DRG01",Common.get(getApplNo()));
					TCBWCommon.setMailbackup("DRG",Common.get(drg5001Db.getDrg0001Id()),title, mailBody, getApplNo(),"", getUserID(),"");
				}				
			}			
		}		
	}

	@Override
	public Object doQueryOne() throws Exception 
	{
	  DRGEX0106F obj = this;
		
	  Con0004Db c = (Con0004Db)View.getObject(" from Con0004Db where applNo= " + Common.sqlChar(obj.getApplNo())+" order by id desc");
	  if(c!=null)
	  {
		  System.out.println("[TCBW]-[DRGEX0106F]-[doQueryOne] : " + c.getId());
		  obj.setNotifyDate(c.getNotifyDate());
		  obj.setApplNo(c.getApplNo());
		  obj.setNotifyBody(Common.get(c.getNotifyBody()));
		  obj.setReplyDate(c.getReplyDate());
		  obj.setReplyBody(c.getReplyBody());
		  obj.setId1(Common.get(c.getId()));
		  obj.setConJSBuilder(this.genCon0001DbSet(c.getId()));
	  }	  
	  
	  return obj;

	}
	
	public void doDeleteFile(){
		List delList = new ArrayList();
		try {
			Con0004Db obj = (Con0004Db)View.getObject(" from Con0004Db where id= " + Common.sqlChar(getId1()));
			if(obj!=null)
			{
				obj.setReplyBody(getReplyBody());
				ServiceGetter.getInstance().getTcbwService().update(obj);
				//附件上傳
				if (true) {
		    		java.util.Set dtlSet = new ListOrderedSet();
		    		if (getHttpRequest()!=null && getConRow()!=null && getConRow().length>0) {
		    			StringBuilder dtlKey = new StringBuilder("-2,-1");		
		    			for (int i=0; i<getConRow().length; i++) {
		    				String rid = getConRow()[i];
		    				Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+Common.getLong(getHttpRequest().getParameter("conId" + rid)));							
		    				dtlKey.append(",").append(dtl.getId());
		    			}
		    			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType ='DRG010001' and dbName ='Con0004Db' and upLoadId= " + Common.getLong(obj.getId()) + " and id not in (" + dtlKey.toString() + ")"));		
		    		} else {
		    			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType ='DRG010001' and dbName ='Con0004Db' and upLoadId= " + Common.getLong(obj.getId())));
		    		}
		    	}
				if(null != delList && !delList.isEmpty()){
					if(delList!=null && delList.size()>0)			
					{				
						java.util.Iterator it = delList.iterator();				
						while (it.hasNext())				
						{					
							Con0001Db o = (Con0001Db) it.next();				
							ServiceGetter.getInstance().getTcbwService().getConinDao().deleteCon0001Db(o.getId());				
						}
					}					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
