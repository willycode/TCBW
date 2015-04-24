package com.kangdainfo.tcbw.view.drgin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1013Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg1001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


import common.Logger;

public class DRGIN0002F 
{

	private static Logger logger = Logger.getLogger(DRGIN0002F.class);
	private HashMap<String,String> resultMap;

	public HashMap<String, String> run(String yesterDay) throws Exception 
	{
		resultMap = new HashMap<String,String>();

		String hql = " from Drg0001Db a where a.notifierRepDate ="+Common.sqlChar(yesterDay);
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql+" order by a.id ");
		
		System.out.println(hql);	
		
		if (list != null && list.size() > 0) 
		{			
			String feilName="不良品通報案件統計資料";	
			WritableWorkbook wb = null;		
			
			//建立檔案、可工作的工作簿
     		File outputFile = new java.io.File(System.getProperty("java.io.tmpdir"),feilName+"_"+yesterDay+".xls");
     		
     		java.util.List<String> attFile = new java.util.ArrayList<String>();
			String f = System.getProperty("java.io.tmpdir") + File.separator + feilName+"_"+yesterDay+".xls";			
			//System.out.println(f);
			attFile.add(f);
			
     		WorkbookSettings wbSettings = new WorkbookSettings();
			
			try 
			{
				wb = Workbook.createWorkbook(outputFile, wbSettings);
			
				wb.createSheet("新增不良品通報案件統計資料",0);				
				WritableSheet sheet0 = wb.getSheet(0);
				
				//不良品通報案件統計資料表頭
				sheet0.addCell(new Label(0,0,"序號"));
				sheet0.setColumnView(0,10);
				
				sheet0.addCell(new Label(1,0,"案件編號"));
				sheet0.setColumnView(1,15);
				
				sheet0.addCell(new Label(2,0,"英文品名"));
				sheet0.setColumnView(2,25);
				
				sheet0.addCell(new Label(3,0,"許可證字號"));
				sheet0.setColumnView(3,15);
				
				sheet0.addCell(new Label(4,0,"劑型"));
				sheet0.setColumnView(4,10);
				
				sheet0.addCell(new Label(5,0,"包裝形式"));
				sheet0.setColumnView(5,10);
				
				sheet0.addCell(new Label(6,0,"申請商"));
				sheet0.setColumnView(6,25);
				
				sheet0.addCell(new Label(7,0,"不良品缺陷描述(細項)"));
				sheet0.setColumnView(7,50);		
			    
				java.util.List<Drg1001Db> drg1001Dbs = ServiceGetter.getInstance().getTcbwService().load("from Drg1001Db");
				java.util.Map<String, String> subCodeMap = new java.util.HashMap<String, String>(); //不良品缺陷子代碼
				if(drg1001Dbs!=null && drg1001Dbs.size()>0){
					for(Drg1001Db drg1001Db : drg1001Dbs){
						subCodeMap.put(drg1001Db.getDpdKind(), drg1001Db.getDpdKindName());
					}
					drg1001Dbs.clear();
				}
				java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID"); //許可證字
				java.util.Map<String, String> flnMap = TCBWCommon.getCommonCodeMap("DRGFLN"); //劑型
				java.util.Map<String, String> drg0102Map = TCBWCommon.getCommonCodeMap("DRG0102"); //包裝形式

				
				int s0=1,x=0;				 
				for (Object drg01Obj : list)
			    {
			    	Drg0001Db obj = (Drg0001Db) drg01Obj;
			    	
					//抓取2檔不良品缺陷
					String subCode02="";			
					if (obj.getDrg0002Dbs()!=null && obj.getDrg0002Dbs().size()>0){
						for (Object drg02Obj : obj.getDrg0002Dbs()) {
							Drg0002Db obj02 = (Drg0002Db) drg02Obj;						
							String[] subList = obj02.getSubCode().split(",");
							for(int j=0; j<subList.length; j++){
								if(!"".equals(Common.get(subList[j]))){
									subCode02 += subCodeMap.get(subList[j])+",";
								}
							}
						}
					}
			    	
			    	sheet0.addCell(new Label(0,x+1,String.valueOf((s0)))); //序號                	 
			    	sheet0.addCell(new Label(1,x+1,obj.getApplNo())); //案件編號                	  
			    	sheet0.addCell(new Label(2,x+1,obj.getEnProduct()));  //英文品名                	 
			    	sheet0.addCell(new Label(3,x+1,pkidMap.get(obj.getPermitKey())+obj.getPermitNo()));//許可證字號                	  
			    	sheet0.addCell(new Label(4,x+1,flnMap.get(obj.getMedModel())));//劑型                	  
			    	sheet0.addCell(new Label(5,x+1,drg0102Map.get(obj.getMedPackage())));//包裝形式          	  
			    	sheet0.addCell(new Label(6,x+1,obj.getApplicationName()));//申請商               	  
			    	sheet0.addCell(new Label(7,x+1,Common.get(subCode02)));//不良品缺陷描述(細項)		    		
			    	s0++;               	  
			    	x++;			    				    	
			    }
				wb.write();			
				wb.close();	
				
				if(subCodeMap != null) subCodeMap.clear();
				if(pkidMap != null) pkidMap.clear();
				if(flnMap != null) flnMap.clear();
				if(drg0102Map != null) drg0102Map.clear();
				
				
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();				
                
				String mailBody="",title="",mail="";
				title = Datetime.getYYYMMDD()+"新增不良品通報案件統計資料";
				mailBody = "各位長官您好\n\n"+yesterDay+"新增不良品通報案件資料，如附件。\n\n謝謝!";
                java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(" from Con1013Db where con1012Db.code='DRG01' ");
				if(objList2!=null && objList2.size()>0)
				{
					for(Object dtlObj : objList2)
					{
						Con1013Db dtl = (Con1013Db)dtlObj;
						if(!"".equals(Common.get(dtl.getCommonUser())))
						   mail += dtl.getCommonUser().getUserEmail()+",";
					}
				}
				if(objList2 !=null) objList2.clear();
				
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
				
				if(mailList.size()>0){				
					ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, attFile, false, null, null, null);	
				}
			
			}
			catch (IOException e){
				e.printStackTrace();
			}			
	    }else{ //無案件資料
	    	
			java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();				
			StringBuilder mailBody = new StringBuilder();
			String title="",mail="";
			title = Datetime.getYYYMMDD()+"新增不良品通報案件統計資料";
			mailBody.append("各位長官您好\n\n"+yesterDay+"無任何新增不良品通報案件資料\n\n謝謝!");
			
            java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(" from Con1013Db where con1012Db.code='DRG01' ");
			if(objList2!=null && objList2.size()>0)
			{
				for(Object dtlObj : objList2)
				{
					Con1013Db dtl = (Con1013Db)dtlObj;
					if(!"".equals(Common.get(dtl.getCommonUser())))
					  mail += dtl.getCommonUser().getUserEmail()+",";
				}
			}
			if(objList2 !=null) objList2.clear();
			
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
			
			if(mailList.size()>0){			
				ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody.toString(), mailList, null, false, null, null, null);	
			}
	    }
		return resultMap;
	}

}
