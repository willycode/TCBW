package com.kangdainfo.tcbw.view.vmed;


import java.io.File;
import java.util.Iterator;
import java.util.List;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.report.ReportWordEnvironment;
import com.kangdainfo.common.util.report.TableModelReportWordGenerator;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med7002Db;
import com.kangdainfo.tcbw.model.bo.Med7005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class VMED0601F extends SuperBean
{
	String id;
	
	String q_applNoS;
	String q_applNoE;
	String q_publDept, q_publDeptName, q_publDeptCodeId;
	String q_situation;
	String q_medMainCategory, q_medMainCategoryName, q_medMainCategoryCodeId;
	String q_chProduct;
	String q_publDateS, q_publDateE;
	String q_permitKey;
	String q_permitNo;
	String q_chProduct72;
	String q_applicationName;
	String q_manufactorName;

	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		
		VMED0601F obj = this;
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

		String hql = " from Med7001Db where status ='31'  ";
		
		if (!"".equals(getQ_applNoS()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
		if (!"".equals(getQ_applNoE()))
			hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
		if (!"".equals(getQ_publDeptCodeId()))
			hql += " and publDept=" + Common.sqlChar(getQ_publDeptCodeId());
		if (!"".equals(getQ_situation()))
			hql += " and situation=" + Common.sqlChar(getQ_situation());
		if (!"".equals(getQ_medMainCategory()))
			hql += " and medMainCategory=" + Common.sqlChar(getQ_medMainCategory());
		if (!"".equals(getQ_chProduct()))
			hql += " and chProduct like " + Common.likeSqlChar(getQ_chProduct());
		if (!"".equals(getQ_publDateS()))
			hql += " and publDate >= " + Common.sqlChar(getQ_publDateS());
		if (!"".equals(getQ_publDateE()))
			hql += " and publDate <= " + Common.sqlChar(getQ_publDateE());
		if (!"".equals(getQ_manufactorName()))
			hql += " and manufactorName like " + Common.likeSqlChar(getQ_manufactorName());
		if (!"".equals(getQ_permitKey()))
			hql += " and id in (select med7001Db.id from Med7002Db where permitKey= " + Common.sqlChar(getQ_permitKey()) + ")";
		if (!"".equals(getQ_permitNo()))
			hql += " and id in (select med7001Db.id from Med7002Db where permitNo= " + Common.sqlChar(getQ_permitNo()) + ")";
		if (!"".equals(getQ_applicationName()))
			hql += " and id in (select med7001Db.id from Med7002Db where applicationName like " + Common.likeSqlChar(getQ_applicationName()) + ")";
		
		System.out.println("VMED0601F====="+hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Iterator it = objList.iterator();
				while (it.hasNext()) {
					Med7001Db o = (Med7001Db) it.next();
					String rowArray[] = new String[7];
					rowArray[0] = Common.get(o.getId());
					rowArray[1] = Common.get(o.getApplNo());
					rowArray[2] = View.getCommonCodeName("CONPUBLDEPT",o.getPublDept());
					rowArray[3] = View.getCommonCodeName("CONWARNING",o.getSituation());
					rowArray[4] = View.getCommonCodeName("MEDMCT",o.getMedMainCategory());
					rowArray[5] = Common.get(o.getChProduct());
					rowArray[6] = Common.get(o.getPublDate());
					arrList.add(rowArray);					
				}
			}
		}
		return arrList;
	}

	
	//產生備份Word
	public void print(String id,String applNo) throws Exception 
	{
		
		ReportWordEnvironment env = new ReportWordEnvironment();
		
		Object[] rowArray = getDefaultTableModelForWord();
		
		env.setWordTableModel(rowArray);
		env.setWordFile(ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/word/vmed/vmed0601.mht"));
		env.setFormat(ReportWordEnvironment.VAL_FORMAT_DOC);
		
		TableModelReportWordGenerator generator = new TableModelReportWordGenerator(env);
	
		
		String med = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("MED");
		    
		String fileName="";//檔案名稱
		    
		String fileDir="MED060004";//存放資料夾
		    
		fileName=applNo;
		   
		File meddir = new File(med);
		
		//判斷資料夾是否存在，若不存在則建立
		if(!meddir.isDirectory())
		{
		    meddir.mkdir();
		}	
		    
        File medFileDir = new File(med+"\\"+fileDir);
		
		//判斷資料夾是否存在，若不存在則建立
		if(!medFileDir.isDirectory())
		{
			medFileDir.mkdir();
		}	
		    
		String uploadCaseID = new java.rmi.dgc.VMID().toString();
		uploadCaseID = uploadCaseID.replaceAll(":","_");
		    
		File dir = new File(med+"\\"+fileDir+"\\"+"word"+id);
		    
		//判斷資料夾是否存在，若不存在則建立
		if(!dir.isDirectory())
		{
		   dir.mkdir();
		}	
		
		Con0001Db o = (Con0001Db)View.getObject("from Con0001Db where fileKind='MED' and  systemType='MED060004' and dbName='Med7005Db' and upLoadId="+Common.sqlChar(id));

		if(o==null)
		{
		   o = new Con0001Db();
		}	
		else
		{

			File[] children = dir.listFiles();
		       
		    for ( int i = 0; i < children.length; i++ ) 
		    {
		       // 刪除檔案
		       if ( children[i].isFile() )
		           children[i].delete();
		    }
			
		}	
		
		o.setFileKind("MED");
		o.setUpLoadId(Common.getLong(id));
		o.setFileRoute(fileDir+":;:"+"word"+id);
		o.setFileName(fileName+".doc");
		o.setFileExplan("WORD");
		o.setIsInsert("N");
		o.setIsDelete("N");
		o.setSystemType("MED060004");
		o.setDbName("Med7005Db");
		o.setCreator(getUserID());
		o.setCreateDate(Datetime.getYYYMMDD());
		o.setCreateTime(Datetime.getHHMMSS());

		ServiceGetter.getInstance().getCommonService().saveOrUpdate(o);

		
		
		File output = new File(med+"\\"+fileDir+"\\"+"word"+id+"\\"+fileName+".doc");
		
		//產生檔案存放
		generator.reportToWord(output);
	
		
		env.clear();

	}
	
	
	
	
	public Object[] getDefaultTableModelForWord() throws Exception
	{
		
		Object[] rowArray = null;//word 替換欄位數
		
		List list = ServiceGetter.getInstance().getCommonService().load(" from Med7001Db where id = "+getId());
		
		if(list!=null && list.size() > 0)
		{

			Iterator it = list.iterator();
			
			String subjectStr="",permitKeyStr="";
			String chProduct="",enProduct="",manufactorName="",contactStr="";
			java.util.Map<String, String> medPermitMap = TCBWCommon.getCommonCodeMap("MEDPKID");
			java.util.Map<String, String> publDeptMap = TCBWCommon.getCommonCodeMap("CONPUBLDEPT");
			  
			while(it.hasNext())
			{
				Med7001Db o = (Med7001Db) it.next();
				
				rowArray = new String[12];//word 替換欄位數
			
				rowArray[0] = Common.get(o.getChProduct());//##1## 產品名稱
	
				int i=1; 
				
				for(Object dtlObj:o.getMed7002Dbs())
				{
					Med7002Db med7002 = (Med7002Db)dtlObj;
					
					//許可證字號
					if(o.getMed7002Dbs().size()>1)
					{
					   permitKeyStr+="("+i+")"+Common.get(medPermitMap.get(med7002.getPermitKey()))+"字";
					}
					else
					{
					   permitKeyStr+=Common.get(medPermitMap.get(med7002.getPermitKey()))+"字";
					}	
					
					if(!"".equals(Common.get(med7002.getPermitNo())))
						permitKeyStr+=Common.get(med7002.getPermitNo())+"號";
					
	
					//中文品名
					if(subjectStr.length()>0)subjectStr+="、";
					subjectStr+=o.getChProduct();
					
					
					if(o.getMed7002Dbs().size()>1)
					{
						if(i!=o.getMed7002Dbs().size())
							permitKeyStr+=" ; ";
						
						//產品中文名稱
						chProduct+="("+i+")"+med7002.getChProduct();
						
						if(i!=o.getMed7002Dbs().size())
							chProduct+=" ; ";
								
						//產品英文名稱
						enProduct+="("+i+")"+med7002.getEnProduct();
						
						if(i!=o.getMed7002Dbs().size())
							enProduct+=" ; ";
						
						//製造廠
						manufactorName+="("+i+")"+med7002.getManufactorName();
						
						if(i!=o.getMed7002Dbs().size())
							manufactorName+=" ; ";
						
						//廠商聯繫資訊
						contactStr+="("+i+")"+med7002.getApplicationName();
						
					}	
					else
					{
						//產品中文名稱
						chProduct+=med7002.getChProduct();
						//產品英文名稱
						enProduct+=med7002.getEnProduct();
						//製造廠
						manufactorName+=med7002.getManufactorName();
						//廠商聯繫資訊
						contactStr+=med7002.getApplicationName();

					}	
					
					//聯絡窗口
					if(!"".equals(med7002.getCheckcontactman()))
						contactStr+="，";
					
					contactStr+=med7002.getCheckcontactman();
					
					//聯絡電話
					if(!"".equals(med7002.getCheckcontacttel()))
						contactStr+="，";
					
					contactStr+=med7002.getCheckcontacttel();
					
					//聯絡信箱
					if(!"".equals(med7002.getCheckcontactemail()))
						contactStr+="，";
					
					contactStr+=med7002.getCheckcontactemail();
					
					if(i!=o.getMed7002Dbs().size())
						contactStr+=" ; ";
					
					contactStr+="\n\n";
					
					i++;

				}
				
				rowArray[1] = Common.get(permitKeyStr);//##2##  許可證字號
				
				rowArray[2] = Common.get(manufactorName);//##3##   製造商
				
				rowArray[3]=Common.get(chProduct);//##4##  產品中文品名
				
				rowArray[4]=Common.get(enProduct);//##5##  產品英文品名
				
				rowArray[5]=Common.get(o.getProductlotNo());//##6## 受影響型號
				
				String translatecontext="";
				
				for(Object dtlObj:o.getMed7005Dbs())
				{
					Med7005Db med7005 = (Med7005Db)dtlObj;
					translatecontext=med7005.getTranslatecontext();
				}
				
				rowArray[6]=Common.get(translatecontext);//##7## 警訊說明
				
				rowArray[7]=Common.get(o.getIncountrysituation());//##8## 國內矯正措施
				
				rowArray[8]=Common.get(contactStr);//##9## 廠商聯繫資訊
				
				rowArray[9]=Common.get(publDeptMap.get(o.getPublDept()));//##10## 資料來源
				rowArray[10]=Common.get(o.getDatasourWebSite());//##11##
				if("Y".equals(o.getIsrecycle())) {
					rowArray[11]="回收訊息";
				} else if("N".equals(o.getIsrecycle())) {
					rowArray[11]="安全警訊";
				}
				
			}
		}
	
		return rowArray;
		
		
		
		
		
		
		
	}
	
	
	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		
	}

	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	public String getQ_applNoS(){ return checkGet(q_applNoS); }
	public void setQ_applNoS(String s){ q_applNoS=checkSet(s); }
	public String getQ_applNoE(){ return checkGet(q_applNoE); }
	public void setQ_applNoE(String s){ q_applNoE=checkSet(s); }
	public String getQ_publDept(){ return checkGet(q_publDept); }
	public void setQ_publDept(String s){ q_publDept=checkSet(s); }
	public String getQ_publDeptName(){ return checkGet(q_publDeptName); }
	public void setQ_publDeptName(String s){ q_publDeptName=checkSet(s); }
	public String getQ_publDeptCodeId(){ return checkGet(q_publDeptCodeId); }
	public void setQ_publDeptCodeId(String s){ q_publDeptCodeId=checkSet(s); }
	public String getQ_situation(){ return checkGet(q_situation); }
	public void setQ_situation(String s){ q_situation=checkSet(s); }
	public String getQ_medMainCategory(){ return checkGet(q_medMainCategory); }
	public void setQ_medMainCategory(String s){ q_medMainCategory=checkSet(s); }
	public String getQ_medMainCategoryName(){ return checkGet(q_medMainCategoryName); }
	public void setQ_medMainCategoryName(String s){ q_medMainCategoryName=checkSet(s); }
	public String getQ_medMainCategoryCodeId(){ return checkGet(q_medMainCategoryCodeId); }
	public void setQ_medMainCategoryCodeId(String s){ q_medMainCategoryCodeId=checkSet(s); }
	public String getQ_chProduct(){ return checkGet(q_chProduct); }
	public void setQ_chProduct(String s){ q_chProduct=checkSet(s); }
	public String getQ_publDateS(){ return checkGet(q_publDateS); }
	public void setQ_publDateS(String s){ q_publDateS=checkSet(s); }
	public String getQ_publDateE(){ return checkGet(q_publDateE); }
	public void setQ_publDateE(String s){ q_publDateE=checkSet(s); }
	public String getQ_permitKey() {return checkGet(q_permitKey);}
	public void setQ_permitKey(String s) {this.q_permitKey = checkSet(s);}
	public String getQ_permitNo() {return checkGet(q_permitNo);}
	public void setQ_permitNo(String s) {this.q_permitNo = checkSet(s);}
	public String getQ_chProduct72(){ return checkGet(q_chProduct72); }
	public void setQ_chProduct72(String s){ q_chProduct72=checkSet(s); }
	public String getQ_applicationName() {return checkGet(q_applicationName);}
	public void setQ_applicationName(String s) {this.q_applicationName = checkSet(s);}
	public String getQ_manufactorName(){ return checkGet(q_manufactorName); }
	public void setQ_manufactorName(String s){ q_manufactorName=checkSet(s); }
}
