package com.kangdainfo.tcbw.view.drgin;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Con2001Db;
import com.kangdainfo.tcbw.model.bo.Cos4001Db;
import com.kangdainfo.tcbw.model.bo.Drg0004Db;

import com.kangdainfo.tcbw.util.TCBWCommon;



public class DRGIN0109F extends DRGIN0106F{	
	
	public void doUpdateDrg0109() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().updateByDrgIN0109F(this);
	}
	
	public void doGradeDrg0109() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().gradeByDrgIN0109F(this);
	}
	
	public void backAccessDrg0109() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().backAccessByDrgIN0109F(this);
	}

	//檢查是否有分派權限
	public  String competenceDrg0109() throws Exception
	{
		
	  String hql="  from Con1015Db ";
             hql+=" where con1014Db.code = "+ Common.sqlChar("03");   //分級確認
             hql+=" and   con1014Db.con1007Db.formCode="+Common.sqlChar("DRG01");
             hql+=" and   competence like "+TCBWCommon.likeSqlChar("4");
             hql+=" and   commonUser.userId="+Common.sqlChar(getUserID());
    
        Con1015Db  c =(Con1015Db)View.getObject(hql);
             
        if(c!=null)
        {
        	return "Y";
        }	
        else
        {
        	return null;
        }	
	}
	
	
	//夾帶檔案清單
	public java.util.ArrayList<String[]> queryAll_mailFile() throws Exception 
	{		
		//附件上傳
		String hql = " from Con0001Db where fileKind='DRG' and dbName='Drg0001Db' and upLoadId="+Common.getLong(getId());		
		       hql+= " and systemType like 'DRG01%'";
		System.out.println("hql=="+hql);
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().getCommonCodeDao().loadCount(hql));

		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query") < 0)
			{			  
				ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getHibernateTemplate().clear();
			}
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+ " order by id");

			try 
			{
				if (objList != null && objList.size() > 0) 
				{
					for (int i = 0; i < objList.size(); i++) 
					{
						Con0001Db o = (Con0001Db) objList.get(i);
						String rowArray[] = new String[4];						

						rowArray[0] = Common.get(o.getId());
						rowArray[1] = Common.get(i+1);
						rowArray[2] = Common.get(o.getFileName());
						rowArray[3] = Common.get(o.getFileExplan());
						arrList.add(rowArray);
					}
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return arrList;
	}
	
	
	public void doCopyFileToCon0002() throws Exception{
		DRGIN0109F obj = this;
		if(getFds()!=null && getFds().length>0){
			StringBuffer sb = new StringBuffer();
			for(String rid : getFds()){
				if(!"".equals(Common.get(rid))){
					if(sb.toString().length() > 0){
						sb.append(",");
					}
					sb.append(Common.getLong(rid));
				}
			}
			if(sb.toString().length() > 0){
				java.util.List objList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where id in (" + sb.toString() + ")");
				if(objList!=null && objList.size()>0){					
					String fileData ="";
					String uploadCaseID ="";
					for (int i = 0; i < objList.size(); i++) 
					{
						Con0001Db o = (Con0001Db) objList.get(i);
						String filestoreLocation = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter(o.getFileKind());
						String fileRoute = o.getFileRoute().replace(":;:", "\\");
						java.io.File oldFile = new java.io.File(filestoreLocation+java.io.File.separator+fileRoute+java.io.File.separator+o.getFileName());						
						uploadCaseID = new java.rmi.dgc.VMID().toString();
				        uploadCaseID = uploadCaseID.replaceAll(":","_");
				        java.io.File newPath = new java.io.File(filestoreLocation+java.io.File.separator+ uploadCaseID);				        
				        TCBWCommon.copyto(oldFile, newPath, false);				        
						if(Common.get(fileData).length()>0) fileData +="|";						
						fileData += uploadCaseID+":;:"+o.getFileName();						
					}
					obj.setFileData(fileData);
				}
			}
		}
	}	
}
