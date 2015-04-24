package com.kangdainfo.tcbw.view.conbg;

import java.io.File;
import java.util.List;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.Validate;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con0002Db;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Con1013Db;
import com.kangdainfo.tcbw.model.bo.Drg8002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class CONBG0001F extends SuperBean{
	
	private String id;
	private String title;
	private String mailAddress;
	private String ccmailAddress;
	private String bccmailAddress;
	private String mailBody;
	private String fileData;
	private String isAdd;
	private String systemType2;
	private String formID2;
	private String mailID;
	private String applNo;
	private String state2;
	private String isShow;
	private String worker;
	private String con1003Id;
	private String url;
	private String manyMailID;
	private String popMailID;
	private String kind;
	private String applicationId;
	private String systemTypeTemp;	//隱藏欄位，用來判斷系統別，在queryone時寫入，避免動到原本的選單
	
	@Override
	public Object doQueryOne() throws Exception 
	{
		CONBG0001F obj = this;
		
		String hql = "from Con1001Db where 1 = 1";		
		
			
		if (null != getManyMailID() && !"".equals(getManyMailID()) && !"undefined".equals(getManyMailID()))
		{
			String splitString = getManyMailID();
			String mailID="";
			String[] mailIDs = null;
			if(getManyMailID()!=null)
				mailIDs = splitString.split(",");
			
			for(int i=0;i<mailIDs.length;i++)
			{
				mailID+="'"+mailIDs[i]+"',";
			}	

			hql += " and mailID in ("+mailID.substring(0, mailID.length()-1)+")";
		}	
		
		if(!"".equals(getPopMailID()))
		{
			hql += " and mailID = " + Common.sqlChar(getPopMailID());
		}
		else
		{
			hql += " and mailID = " + Common.sqlChar(getMailID());
		}	
		
		
		if(!"".equals(getFormID2()))
		{
			hql += " and formID = " + Common.sqlChar(getFormID2());
		}
		
		if(!"".equals(getSystemType2()))
		{
			hql += " and systemType = " + Common.sqlChar(getSystemType2());
		}
		
		Con1001Db c = (Con1001Db)View.getObject(hql + " order by systemType");	
		
		System.out.println("xxx=="+hql);
		
		if(c != null)
		{
			CommonUser user = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
			obj.setWorker(user.getUserId());
			user = null;
			obj.setSystemType2(c.getSystemType());
			
			//醫材要再區分不良品或不良反應
			if("1".equals(getKind()) && "MED".equals(c.getSystemType())) {	//不良反應
				obj.setSystemTypeTemp(c.getSystemType()+"01");
			} else if("2".equals(obj.getKind()) && "MED".equals(c.getSystemType())) {	//不良品
				obj.setSystemTypeTemp(c.getSystemType()+"02");
			} else {
				obj.setSystemTypeTemp(c.getSystemType());
			}

			obj.setFormID2(c.getFormID());
			
			if("".equals(getPopMailID()))
			  obj.setMailAddress(CONBG0002F.getEmail(c.getMailID(), getId(), c.getTitle(), obj.getWorker()));
			
			obj.setTitle(CONBG0002F.getTitle(c.getMailID(), getId(), c.getTitle()));
			
			obj.setMailBody(CONBG0002F.getMailBody(c.getMailID(), getId(), c.getMailBody(), getUrl()));
			
			if("COS020004".equals(Common.get(c.getMailID())))
			{
				obj.setCcmailAddress(getEmailGroupData("COS", "TFDA01"));
				obj.setMailAddress(CONBG0002F.getEmail(c.getMailID(), getCon1003Id(), "", ""));
				obj.setMailBody(CONBG0002F.getMailBodyByCOS(c.getMailID(), getId(), c.getMailBody(), getCon1003Id()));
			}
			else if("COS020005".equals(Common.get(c.getMailID())))
			{
				obj.setCcmailAddress(getEmailGroupData("COS", "TFDA01"));
			}
			else if("DRG030001".equals(Common.get(c.getMailID())))
			{
				obj.setMailBody(CONBG0002F.getMailBody(c.getMailID(), getId(), c.getMailBody(), getUrl(),getApplicationId()));
			}
			else if("DRG010006".equals(Common.get(c.getMailID())) 
					|| "DRG010007".equals(Common.get(c.getMailID())) 
					   || "DRG010008".equals(Common.get(c.getMailID())))
			{
				
				obj.setCcmailAddress(CONBG0002F.getCCEmail(c.getMailID(), getId(), c.getTitle(), obj.getWorker()));
			}
			else if("DRG020007".equals(mailID)){	//DRG020007 發送醫院詢問函寄送mail密本			
				obj.setBccmailAddress(CONBG0002F.getBCCEmail(c.getMailID(), getId(), c.getTitle(), obj.getWorker()));		
			}
		}
		else
		{
			obj.setFormID2("");
			obj.setTitle("");
			obj.setMailBody("");
		}
		return obj;
	}
	
	public String getEmailGroupData(String systemType, String mailGroupCode) throws Exception {
		StringBuffer sb = new StringBuffer();
		java.util.List<Con1013Db> con1013DbList = ServiceGetter.getInstance().getTcbwService().load(" from Con1013Db where con1012Db.systemType = " + Common.sqlChar(systemType) + 
													   												" and con1012Db.code = " + Common.sqlChar(mailGroupCode));
		
		if(con1013DbList!=null && con1013DbList.size()>0)
		{
			for(Con1013Db dtl : con1013DbList)
			{
				CommonUser user = dtl.getCommonUser();
				if(user!=null && !"".equals(Common.get(user.getUserEmail())) && Validate.checkEmail(Common.get(user.getUserEmail()))){
					if(sb.toString().length() > 0)
					{
						sb.append(",");
					}
					sb.append(Common.get(user.getUserEmail()));
				}
			}
			con1013DbList.clear();
		}
		return sb.toString();
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
	public void doUpdate() throws Exception 
	{
		
		if(!"".equals(getMailBody()) && getMailBody()!=null)
		{
			java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
			String[] mailAddress = getMailAddress().split(",");
			
			java.util.List<javax.mail.internet.InternetAddress> ccmailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
			String[] ccmailAddress = getCcmailAddress().split(",");

			java.util.List<javax.mail.internet.InternetAddress> bccmailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
			String[] bccmailAddress = getBccmailAddress().split(",");
			 
			if(mailAddress!=null && mailAddress.length>0)
			{
				for(String s : mailAddress)
				{
					javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
				    p.setAddress(s);
				    mailList.add(p);
				    
				}
			}
			if(ccmailAddress!=null && ccmailAddress.length>0)
			{
				for(String s : ccmailAddress)
				{
					javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
				    p.setAddress(s);
				    ccmailList.add(p);
				}
			}
			
			if(bccmailAddress!=null && bccmailAddress.length>0)
			{
				for(String s : bccmailAddress)
				{
					javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
				    p.setAddress(s);
				    bccmailList.add(p);
				}
			}
		
			java.util.List<String> attFile = new java.util.ArrayList<String>();
			String filestoreLocation = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter(getSystemType2());
			java.util.List<Con0001Db> saveCon0001DbList = new java.util.ArrayList<Con0001Db>();            
			// 夾帶附件
			if(!"".equals(getFileData()))
			{
				String[] tempFilePath = getFileData().substring(0, getFileData().length()-1).split("\\|");
				for(String o: tempFilePath)
				{
					String[] arrFileName = o.split(":;:");
					if(arrFileName!=null && arrFileName.length>0)
					{
						String f = filestoreLocation + File.separator + arrFileName[0] + File.separator + arrFileName[1];
						//System.out.println(f);
						attFile.add(f);
					}									
				}
			}
			
			if("MED060004".equals(Common.get(getMailID())))
			{
				String med7001Id=View.getLookupField("select med7001Db.id from Med7002Db where id="+Common.sqlChar(id));
				
				Con0001Db o = (Con0001Db)View.getObject("from Con0001Db where fileKind='MED' and  systemType='MED060004' and dbName='Med7005Db' and upLoadId="+Common.sqlChar(med7001Id));
				
				if(o!=null)
				{	
				   String file = filestoreLocation + File.separator + "MED060004"+ File.separator +"word"+med7001Id+File.separator +o.getFileName();
				   attFile.add(file);
				}
			}
			
			if(mailList.size() > 0)
			{

				ServiceGetter.getInstance().getTcbwService().saveBatch(saveCon0001DbList);
				
				String systemType2 = getSystemType2();
				
				if("DRG".equals(getSystemType2()) && "2".equals(getFormID2()))
				{
					systemType2 = systemType2+"2";
				}
				
				if("DRG".equals(getSystemType2()) && "11".equals(getFormID2()))
				{
					systemType2 = systemType2+"3";
				}
				
				if("MED".equals(getSystemType2()) && "1".equals(getKind()))
				{
					systemType2 = systemType2+"01";
				}
				
				if("MED".equals(getSystemType2()) && "2".equals(getKind()))
				{
					systemType2 = systemType2+"02";
				}
				
				if("MED".equals(getSystemType2()) && "3".equals(getKind()))
				{
					systemType2 = systemType2+"03";
				}
				
                String value=getApplNo();
				
				if("".equals(Common.get(value)))
				{
					value=getId();
				}
				
				ServiceGetter.getInstance().getTcbwService().sendEmail(getTitle(), getMailBody(), mailList, attFile, true,null, ccmailList, bccmailList,systemType2,value,"N");
			
				TCBWCommon.setMailbackup(systemType2, getId(), getTitle(), getMailBody(), getApplNo(), getState2(), getUserID(), getFileData());
								
				if("Y".equals(getIsAdd()))
				   TCBWCommon.addocuments(value,getEditDate(),getMailBody(),getUserID());
			}
			
			mailList.clear();
		}
		else
		{
			throw new Exception("信件內容空値，請重新輸入");
		}
	}

	@Override
	public void doDelete() throws Exception 
	{
		// TODO Auto-generated method stub
		
	}
	
	
	public String doSetFileData(String fileData) throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024);
		if(!"".equals(Common.get(fileData))){		
			String[] fileTmp = 	Common.get(fileData).split("\\|");			
			for(int i=0; i < fileTmp.length ; i++){
				if(!"".equals(Common.get(fileTmp[i]))){
					String[] obj = 	Common.get(fileTmp[i]).split(":;:");
					sb.append("addAtt('").append(obj[0]).append("'");					
					sb.append(",'").append(obj[0]+":;:"+obj[1]).append("');\n");            //filePath
				}			
			}
		}
		return sb.toString(); 
	}

	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	public String getTitle() {		return checkGet(title);	}
	public void setTitle(String title) {		this.title = checkSet(title);	}
	public String getMailAddress() {		return checkGet(mailAddress);	}
	public void setMailAddress(String mailAddress) {		this.mailAddress = checkSet(mailAddress);	}
	public String getCcmailAddress() {return checkGet(ccmailAddress);}
	public void setCcmailAddress(String ccmailAddress) {this.ccmailAddress = checkSet(ccmailAddress);}
	public String getBccmailAddress() {return checkGet(bccmailAddress);}
	public void setBccmailAddress(String bccmailAddress) {this.bccmailAddress = checkSet(bccmailAddress);}
	
	public String getMailBody() {		return get(mailBody);	}
	public void setMailBody(String mailBody) {		this.mailBody = mailBody;	}
	
	public String getFileData() {		return checkGet(fileData);	}
	public void setFileData(String fileData) {		this.fileData = checkSet(fileData);	}
	public String getSystemType2() {		return checkGet(systemType2);	}
	public void setSystemType2(String systemType) {	this.systemType2 = checkSet(systemType);	}
	public String getFormID2() {		return checkGet(formID2);	}
	public void setFormID2(String formID) {		this.formID2 = checkSet(formID);	}
	public String getApplNo() {		return checkGet(applNo);	}
	public void setApplNo(String applNo) {		this.applNo = checkSet(applNo);	}
	public String getState2() {		return checkGet(state2);	}
	public void setState2(String state) {		this.state2 = checkSet(state);	}
	public String getIsShow() {		return checkGet(isShow);	}
	public void setIsShow(String isShow) {		this.isShow = checkSet(isShow);	}
	public String getMailID() {		return checkGet(mailID);	}
	public void setMailID(String mailID) {		this.mailID = checkSet(mailID);	}
	public String getIsAdd() {		return checkGet(isAdd);	}
	public void setIsAdd(String isAdd) {		this.isAdd = checkSet(isAdd);	}
	public String getWorker() {		return checkGet(worker);	}
	public void setWorker(String worker) {		this.worker = checkSet(worker);	}
	public String getCon1003Id() {		return checkGet(con1003Id);	}
	public void setCon1003Id(String con1003Id) {		this.con1003Id = checkSet(con1003Id);	}
	public String getUrl() {return checkGet(url);}
	public void setUrl(String s) {this.url = checkSet(s);}
	public String getManyMailID() {return checkGet(manyMailID);}
	public void setManyMailID(String manyMailID) {this.manyMailID = checkSet(manyMailID);}

	public String getPopMailID() {return checkGet(popMailID);}
	public void setPopMailID(String popMailID) {this.popMailID = checkSet(popMailID);}

	public String getKind() {return checkGet(kind);}
	public void setKind(String kind) {this.kind = checkSet(kind);}

	public String getApplicationId() {return checkGet(applicationId);}
	public void setApplicationId(String applicationId) {this.applicationId = checkSet(applicationId);}

	public String getSystemTypeTemp() {
		return checkGet(systemTypeTemp);
	}

	public void setSystemTypeTemp(String systemTypeTemp) {
		this.systemTypeTemp = checkSet(systemTypeTemp);
	}
	

	
}
