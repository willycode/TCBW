package com.kangdainfo.tcbw.view.sdrg;

import java.util.List;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;

import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg8001Db;
import com.kangdainfo.tcbw.model.bo.Drg8002Db;
import com.kangdainfo.tcbw.model.bo.Drg8003Db;
import com.kangdainfo.tcbw.model.bo.Drg8004Db;
import com.kangdainfo.tcbw.model.bo.Drg8005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class SDRG0401F extends SDRG0301F{
	
	//Drg8001Db
	private String checkpunishdate;	//回收確認-處置日期
	private String checkcyclestorage;	//回收確認-回收品及庫存品處置方式
	private String checkcyclestorageOther;	//回收確認-回收品及庫存品處置方式說明
	private String checktotalNum;	//回收確認-總數量
	private String checktotalUnit;	//回收確認-總數量單位
	private String checkhealthbureau;	//回收確認-查核衛生局
	private String ischeckmatchnum;	//回收確認-是否與回收報告書所載數量相符
	private String checknonmatchreason;	//回收確認-數量不符之理由
	private String checkmanudate;	//回收確認-查核日期
	private String checkcontactman;	//回收確認-聯絡窗口
	private String checkcontacttel;	//回收確認-聯絡電話 
	private String checkUpdateYn;	//回收確認-廠商確認完成 (不讓廠商一改再改)

	public void doUpdate0401() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getSdrgDao().updateBySdrg0401F(this);
	}
    
	public Object doQueryOne0401() throws Exception {
		SDRG0401F obj = this;	
		
		Drg8001Db c = (Drg8001Db) View.getObject(" from Drg8001Db where id=" + Common.getInt(obj.getId()));
		
		//System.out.println("[TCBW]-[SDRG0401F]-[doQueryOne]- Drg8001Db.id : " + obj.getId());
		
		if (c!=null) {
			
			obj.setStatus(c.getStatus());	
			obj.setCheckpunishdate(Common.get(c.getCheckpunishdate()));	                //回收確認-處置日期
			obj.setCheckcyclestorage(Common.get(c.getCheckcyclestorage()));	            //回收確認-回收品及庫存品處置方式
			obj.setCheckcyclestorageOther(Common.get(c.getCheckcyclestorageOther()));	//回收確認-回收品及庫存品處置方式說明
			obj.setChecktotalNum(Common.get(c.getChecktotalNum()));	                    //回收確認-總數量
			obj.setChecktotalUnit(Common.get(c.getChecktotalUnit()));	                //回收確認-總數量單位
			obj.setCheckhealthbureau(Common.get(c.getCheckhealthbureau()));	            //回收確認-查核衛生局
			obj.setIscheckmatchnum(Common.get(c.getIscheckmatchnum()));	                //回收確認-是否與回收報告書所載數量相符
			obj.setChecknonmatchreason(Common.get(c.getChecknonmatchreason()));	        //回收確認-數量不符之理由
			obj.setCheckmanudate(Common.get(c.getCheckmanudate()));                     //回收確認-查核日期
			obj.setCheckcontactman(Common.get(c.getCheckcontactman()));	                //回收確認-聯絡窗口
			obj.setCheckcontacttel(Common.get(c.getCheckcontacttel()));	                //回收確認-聯絡電話
			obj.setCheckUpdateYn(Common.get(c.getCheckUpdateYn()));	                    //回收確認-廠商確認完成
		}
		this.setState("queryOneSuccess");
		return obj;
	}	
	
	//附件(銷燬紀錄)
	public String getAddFileDrg0409() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String ststus = View.getLookupField("select status from Drg8001Db where id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG040009' and dbName='Drg8001Db' and upLoadId="+Common.getLong(getId());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				String prop="";
			    prop=prop+"toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,";
			    prop=prop+"width=450,";
			    prop=prop+"height=160";
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");			
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=DRG&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");				
				sb.append("<td style='text-align:center'>");
				if(Common.getInt(ststus) <= 31){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</a></td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	//附件(稽查紀錄)
	public String getAddFileDrg0410() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String ststus = View.getLookupField("select status from Drg8001Db where id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG040010' and dbName='Drg8001Db' and upLoadId="+Common.getLong(getId());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				String prop="";
			    prop=prop+"toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,";
			    prop=prop+"width=450,";
			    prop=prop+"height=160";
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");			
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=DRG&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");				
				sb.append("<td style='text-align:center'>");
				if(Common.getInt(ststus) <= 31){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</a></td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	//衛生局下拉選單
	public static String getOptionHealthbureau(String id,String checkhealthbureau) {	
		 StringBuilder sb = new StringBuilder();
		 sb.append("<option value=''>請選擇</option>");
	     try {        
	         String sql = "select healthbureau1,healthbureau2 from Drg8001Db where id="+Common.getLong(id);    	 

	    	 List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
	         if (list!=null && list.size()>0) {
	        	 java.util.Map<String, String> healthMap = TCBWCommon.getMap("select id,unionName from Con1003Db");
	        	 for (int i=0; i<list.size(); i++) {	            			
	        		 Object[] obj = (Object[])list.get(i);	                        
	        		 String index1 = Common.get(obj[0]);	                        
	        		 String name1 = !"".equals(Common.get(obj[0]))?healthMap.get(Common.get(obj[0])):"";                    
	        		 String index2 = Common.get(obj[1]);	                        
	        		 String name2 = !"".equals(Common.get(obj[1]))?healthMap.get(Common.get(obj[1])):"";      
	        		 sb.append("<option value='").append(index1).append("' ");
	        		 if (checkhealthbureau!= null && checkhealthbureau.equals(index1)) {
                         sb.append(" selected ");
                     }
	        		 sb.append(">").append(name1).append("</option>");	                        
	        		 if(!"".equals(index2)){
	        			 sb.append("<option value='").append(index2).append("' ");
	        			 if (checkhealthbureau!= null && checkhealthbureau.equals(index2)) {
	                            sb.append(" selected ");
	                     }
	        			 sb.append(">").append(name2).append("</option>");
	        		 }	            		
	        	 }
	        	 healthMap.clear();
	         }        
	     } catch (Exception ex) {
	            sb.append("<option value=''>查詢錯誤</option>");
	            ex.printStackTrace();
	     }
	     return sb.toString();
    }

	public String getCheckpunishdate() {
		return checkGet(checkpunishdate);
	}

	public void setCheckpunishdate(String checkpunishdate) {
		this.checkpunishdate = checkSet(checkpunishdate);
	}

	public String getCheckcyclestorage() {
		return checkGet(checkcyclestorage);
	}

	public void setCheckcyclestorage(String checkcyclestorage) {
		this.checkcyclestorage = checkSet(checkcyclestorage);
	}

	public String getCheckcyclestorageOther() {
		return checkGet(checkcyclestorageOther);
	}

	public void setCheckcyclestorageOther(String checkcyclestorageOther) {
		this.checkcyclestorageOther = checkSet(checkcyclestorageOther);
	}

	public String getChecktotalNum() {
		return checkGet(checktotalNum);
	}

	public void setChecktotalNum(String checktotalNum) {
		this.checktotalNum = checkSet(checktotalNum);
	}

	public String getChecktotalUnit() {
		return checkGet(checktotalUnit);
	}

	public void setChecktotalUnit(String checktotalUnit) {
		this.checktotalUnit = checkSet(checktotalUnit);
	}

	public String getCheckhealthbureau() {
		return checkGet(checkhealthbureau);
	}

	public void setCheckhealthbureau(String checkhealthbureau) {
		this.checkhealthbureau = checkSet(checkhealthbureau);
	}

	public String getIscheckmatchnum() {
		return checkGet(ischeckmatchnum);
	}

	public void setIscheckmatchnum(String ischeckmatchnum) {
		this.ischeckmatchnum = checkSet(ischeckmatchnum);
	}

	public String getChecknonmatchreason() {
		return checkGet(checknonmatchreason);
	}

	public void setChecknonmatchreason(String checknonmatchreason) {
		this.checknonmatchreason = checkSet(checknonmatchreason);
	}

	public String getCheckmanudate() {
		return checkGet(checkmanudate);
	}

	public void setCheckmanudate(String checkmanudate) {
		this.checkmanudate = checkSet(checkmanudate);
	}

	public String getCheckcontactman() {
		return checkGet(checkcontactman);
	}

	public void setCheckcontactman(String checkcontactman) {
		this.checkcontactman = checkSet(checkcontactman);
	}

	public String getCheckcontacttel() {
		return checkGet(checkcontacttel);
	}

	public void setCheckcontacttel(String checkcontacttel) {
		this.checkcontacttel = checkSet(checkcontacttel);
	}

	public String getCheckUpdateYn() {
		return checkGet(checkUpdateYn);
	}

	public void setCheckUpdateYn(String checkUpdateYn) {
		this.checkUpdateYn = checkSet(checkUpdateYn);
	}
	
	
	
}
