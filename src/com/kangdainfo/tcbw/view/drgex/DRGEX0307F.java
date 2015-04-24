package com.kangdainfo.tcbw.view.drgex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con0004Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGEX0307F extends SuperBean
{
	private String id;
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
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	
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
		String hql = " from Con0001Db where fileKind='DRG' and systemType = 'DRG020001' and dbName='Con0004Db' and upLoadId="+id;
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
	
	@Override
	public Object doQueryOne() throws Exception 
	{
		DRGEX0307F obj = this;
		Con0004Db c = (Con0004Db)View.getObject(" from Con0004Db where applNo= " + Common.sqlChar(obj.getApplNo())+" order by id desc");
		if(c!=null)
		{
			obj.setNotifyDate(c.getNotifyDate());
			obj.setApplNo(c.getApplNo());
			obj.setNotifyBody(Common.get(c.getNotifyBody()));
			obj.setReplyDate(c.getReplyDate());
			obj.setReplyBody(c.getReplyBody());
			obj.setId(Common.get(c.getId()));
			obj.setConJSBuilder(this.genCon0001DbSet(c.getId()));
		}
		return obj;
	}
	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doUpdate() throws Exception {
		List delList = new ArrayList();
		java.util.List<Con0001Db> conNewList = new java.util.ArrayList<Con0001Db>();
		List<String> attFilePathList = new ArrayList<String>();
		
		Con0004Db obj = (Con0004Db)View.getObject(" from Con0004Db where id= " + Common.sqlChar(getId()));
		if(obj!=null)
		{
			obj.setReplyBody(getReplyBody());
			obj.setReplyDate(Datetime.getYYYMMDD());
			obj.setNotifyBody(Common.formatYYYMMDD(obj.getReplyDate(),4)+"補件回覆");
			obj.setModifier(getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMM());
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
				ServiceGetter.getInstance().getCommonService().deleteBatch(delList);
			}

			Drg6001Db drg61 = (Drg6001Db)ServiceGetter.getInstance().getTcbwService().getObject(" from Drg6001Db where applNo = "+Common.sqlChar(getApplNo())+" order by revision desc");
			if(null != drg61){
				Drg4001Db drg41 = (Drg4001Db)View.getObject(" from Drg4001Db where id = " + drg61.getDrg4001Id());
				if(null != drg41){
					drg41.setStatus("23");
					drg61.setStatus("23");
					
					ServiceGetter.getInstance().getTcbwService().update(drg41);
					ServiceGetter.getInstance().getTcbwService().update(drg61);
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG2",drg41.getId(), obj.getApplNo(),"23", "通知補件 - 補件完成", getUserID());
					
					List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='DRG' and systemType = 'DRG020001' and dbName='Con0004Db' and upLoadId="+obj.getId());
					if(null != conList && !conList.isEmpty()){
						for(Con0001Db con : conList){
							Con0001Db newCon01 = new Con0001Db();
							org.springframework.beans.BeanUtils.copyProperties(con, newCon01, new String[]{"id","upLoadId"});
							newCon01.setDbName("Drg4001Db");
							newCon01.setUpLoadId(drg41.getId());
							newCon01.setFileExplan(Common.formatYYYMMDD(obj.getReplyDate(),4)+"補件回覆");
							conNewList.add(newCon01);
							
							//記錄檔案路徑，以便寄送信件時有附件。
							String fileID = java.net.URLDecoder.decode(newCon01.getFileRoute(),"UTF-8");
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
						if(null != conNewList && !conNewList.isEmpty()){
							ServiceGetter.getInstance().getCommonService().saveBatch(conNewList);
						}
					}
					//ServiceGetter.getInstance().getTcbwService().getDrgexDao().sendDrgMailToChargeMan(drg41, getReplyBody(), attFilePathList);
				}
			}
		}	
	}
	
	public void doUpdateFile(){
		List delList = new ArrayList();
		try {
			Con0004Db obj = (Con0004Db)View.getObject(" from Con0004Db where id= " + Common.sqlChar(getId()));
			if(obj!=null)
			{
				obj.setReplyBody(getReplyBody());
				ServiceGetter.getInstance().getTcbwService().update(obj);
			}
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
	    			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType ='DRG020001' and dbName ='Con0004Db' and upLoadId= " + Common.getLong(getId()) + " and id not in (" + dtlKey.toString() + ")"));		
	    		} else {
	    			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType ='DRG020001' and dbName ='Con0004Db' and upLoadId= " + Common.getLong(getId())));
	    		}
	    	}
			if(null != delList && !delList.isEmpty()){
				ServiceGetter.getInstance().getCommonService().deleteBatch(delList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
