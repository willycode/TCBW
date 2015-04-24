package com.kangdainfo.tcbw.view.vcos;

import java.io.File;

import jxl.Workbook;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Con1013Db;
import com.kangdainfo.tcbw.model.bo.Cos7001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class VCOS0201F extends SuperBean
{
	private String id;//序號	NUMERIC(19,0)
	private String[] fds;
	public String[] getFds(){	return fds;		}
	public void setFds(String[] s){ 	fds = s;	}
	private String con1012Id;
	
	
	private String q_applNoS;
	private String q_applNoE;
	private String q_chProduct;//商品名稱中文
	private String q_dataRevDateS;//資料收集日期
	private String q_dataRevDateE;//資料收集日期
	private String q_publDept;//發佈單位
    private String q_publDeptCodeId;
    private String q_publDeptName;
	private String q_ingredient;//化粧品項目
	private String q_publDateS;//發佈日期
	private String q_publDateE;//發佈日期
	
	
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	public void doSend() throws Exception
	{
		String id="";
		
		for(int i=0;i<fds.length;i++)
		{
			if(id.length()>0) id+=",";
			
			id+=fds[i];
		}	
		
		
		
		if(!"".equals(id))
		{	
		    String hql = " from Cos7001Db where id in ("+id+")";
		
		    java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		    
		    String subject="",content="";//主旨
		    
		    File outputFile = new File(System.getProperty("java.io.tmpdir")+File.separator+Datetime.getYYYMMDD()+"_"+Datetime.getHHMMSS()+".xls");
			WritableWorkbook workbook = Workbook.createWorkbook(outputFile);	
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			
			WritableFont Font10 = new WritableFont(WritableFont.ARIAL,12);
			WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
			detailCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
			detailCenter.setAlignment(jxl.format.Alignment.CENTRE);
		    
			//表頭
			int[] tmpMaxLength = new int[15];
			
			sheet.addCell(new Label(0,0,"編號類別", detailCenter));
			tmpMaxLength[0]=8;
			sheet.addCell(new Label(1,0,"產品類別", detailCenter));
			tmpMaxLength[1]=8;
			sheet.addCell(new Label(2,0,"品名", detailCenter));
			tmpMaxLength[2]=4;
			sheet.addCell(new Label(3,0,"資料收集日期", detailCenter));
			tmpMaxLength[3]=12;
			sheet.addCell(new Label(4,0,"發佈單位", detailCenter));
			tmpMaxLength[4]=8;
			sheet.addCell(new Label(5,0,"發佈單位國家", detailCenter));
			tmpMaxLength[5]=12;
			sheet.addCell(new Label(6,0,"產地", detailCenter));
			tmpMaxLength[6]=4;
			sheet.addCell(new Label(7,0,"化妝品項目", detailCenter));
			tmpMaxLength[7]=10;
			sheet.addCell(new Label(8,0,"品牌/廠商", detailCenter));
			tmpMaxLength[8]=10;
			sheet.addCell(new Label(9,0,"狀況", detailCenter));
			tmpMaxLength[9]=4;
			sheet.addCell(new Label(10,0,"發佈日期", detailCenter));
			tmpMaxLength[10]=8;
			sheet.addCell(new Label(11,0,"訊息主題", detailCenter));
			tmpMaxLength[11]=8;
			sheet.addCell(new Label(12,0,"資訊內容摘要", detailCenter));
			tmpMaxLength[12]=12;
			sheet.addCell(new Label(13,0,"產品批號", detailCenter));
			tmpMaxLength[13]=8;
			sheet.addCell(new Label(14,0,"資料來源", detailCenter));
			tmpMaxLength[14]=8;
		
			
		    if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> situationMap = TCBWCommon.getCommonCodeMap("CONWARNING");
				java.util.Map<String, String> subjecttypeMap = TCBWCommon.getCommonCodeMap("COSSJTYPE");
				java.util.Map<String, String> publDeptMap = TCBWCommon.getCommonCodeMap("CONPUBLDEPT");
				java.util.Map<String, String> publCountryMap = TCBWCommon.getCommonCodeMap("COUC");
				java.util.Map<String, String> ingredientMap = TCBWCommon.getCommonCodeMap("CCI");
				
				
				content+="<table class='table_form' width='100%' border='1' cellspacing='0' cellpadding='0'>";
				
				String bgcolor="bgcolor='#FFFFCC'";
				
				int no=1,recoverNum=0,policeNum=0,count=1;
				
				for(Object dtlObj : objList)
				{
					Cos7001Db dtl = (Cos7001Db)dtlObj;
					
					//excel
					//編號類別
					sheet.addCell(new Label(0,count,Common.get(no), detailCenter));
					
					//產品類別
					sheet.addCell(new Label(1,count,Common.get("化粧品"), detailCenter));
					if(Common.get("化粧品").length() >= tmpMaxLength[1])
					{
						tmpMaxLength[1] = Common.get("化粧品").length(); 
					}
					
					//品名
					String chProduct=Common.get(dtl.getChProduct());
					sheet.addCell(new Label(2,count,chProduct, detailCenter));
					if(Common.get(chProduct).length() >= tmpMaxLength[2])
					{
						tmpMaxLength[2] = Common.get(chProduct).length(); 
					}

					//資料收集日期
					String dataRevDate=Common.get(dtl.getDataRevDate());
					sheet.addCell(new Label(3,count,dataRevDate, detailCenter));
					if(Common.get(dataRevDate).length() >= tmpMaxLength[3])
					{
						tmpMaxLength[3] = Common.get(dataRevDate).length(); 
					}
					
					//發佈單位
					String publDept=Common.get(publDeptMap.get(dtl.getPublDept()));
					sheet.addCell(new Label(4,count,publDept, detailCenter));
					if(Common.get(publDept).length() >= tmpMaxLength[4])
					{
						tmpMaxLength[4] = Common.get(publDept).length(); 
					}
					
					//發佈單位國家
					String publCountry=Common.get(publCountryMap.get(dtl.getPublCountry()));
					sheet.addCell(new Label(5,count,publCountry, detailCenter));
					if(Common.get(publCountry).length() >= tmpMaxLength[5])
					{
						tmpMaxLength[5] = Common.get(publCountry).length(); 
					}
					
					//產地
					String manufactorCountry=Common.get(dtl.getManufactorCountry());
					sheet.addCell(new Label(6,count,publCountry, detailCenter));
					if(Common.get(manufactorCountry).length() >= tmpMaxLength[6])
					{
						tmpMaxLength[6] = Common.get(manufactorCountry).length(); 
					}
					
					//化粧品項目
					String ingredient=Common.get(ingredientMap.get(dtl.getIngredient()));
					sheet.addCell(new Label(7,count,publCountry, detailCenter));
					if(Common.get(ingredient).length() >= tmpMaxLength[7])
					{
						tmpMaxLength[7] = Common.get(ingredient).length(); 
					}
					
					//品牌/廠商
					String brand=Common.get(dtl.getBrand());
					sheet.addCell(new Label(8,count,brand, detailCenter));
					if(Common.get(brand).length() >= tmpMaxLength[8])
					{
						tmpMaxLength[8] = Common.get(brand).length(); 
					}
					
					//狀況
					String situation=Common.get(situationMap.get(dtl.getSituation()));
					sheet.addCell(new Label(9,count,situation, detailCenter));
					if(Common.get(situation).length() >= tmpMaxLength[9])
					{
						tmpMaxLength[9] = Common.get(situation).length(); 
					}
					
					//發佈日期
					String publDate=Common.get(situationMap.get(dtl.getPublDate()));
					sheet.addCell(new Label(10,count,Common.get(dtl.getPublDate()), detailCenter));
					if(Common.get(publDate).length() >= tmpMaxLength[10])
					{
						tmpMaxLength[10] = Common.get(publDate).length(); 
					}
					
					//訊息主題
					String subjecttype=Common.get(subjecttypeMap.get(dtl.getSubjecttype()));
					sheet.addCell(new Label(11,count,subjecttype, detailCenter));
					if(Common.get(subjecttype).length() >= tmpMaxLength[11])
					{
						tmpMaxLength[11] = Common.get(subjecttype).length(); 
					}
					
					//資訊內容摘要
					String contextsummary=Common.get(dtl.getContextsummary());
					sheet.addCell(new Label(12,count,contextsummary, detailCenter));
					
					//產品批號
					String lotNo=Common.get(dtl.getLotNo());
					sheet.addCell(new Label(13,count,lotNo, detailCenter));
					
					//資料來源
					String datasourWebSite=Common.get(dtl.getDatasourWebSite());
					sheet.addCell(new Label(14,count,datasourWebSite, detailCenter));
		
					count++;
					
	
					//信件
					if("01".equals(dtl.getSituation()))
						policeNum=policeNum+1;
					else if("02".equals(dtl.getSituation()))
						recoverNum=recoverNum+1;
					
					
					content+="<tr bgcolor='#FFD78C'>";
					content+="<td width='15%'>編號類別:</td>";
					content+="<td width='85%'>"+Common.get(no)+"</td>";
					content+="</tr>";
					
					content+="<tr>";
					content+="<td "+bgcolor+">狀況:</td>";
					content+="<td>"+Common.get(situationMap.get(dtl.getSituation()))+"</td>";
					content+="</tr>";
					
					content+="<tr>";
					content+="<td "+bgcolor+">發佈單位:</td>";
					content+="<td>"+Common.get(publDeptMap.get(dtl.getPublDept()))+"</td>";
					content+="</tr>";
					
					content+="<tr>";
					content+="<td "+bgcolor+">資料來源:</td>";
					content+="<td><a href=\""+dtl.getDatasourWebSite()+"\">"+dtl.getDatasourWebSite()+"</a></td>";
					content+="</tr>";
					
					content+="<tr>";
					content+="<td "+bgcolor+">發佈單位國家:</td>";
					content+="<td>"+Common.get(publCountryMap.get(dtl.getPublCountry()))+"</td>";
					content+="</tr>";
					
					content+="<tr>";
					content+="<td "+bgcolor+">品名:</td>";
					content+="<td>"+Common.get(dtl.getChProduct())+"</td>";
					content+="</tr>";
					
					content+="<tr>";
					content+="<td "+bgcolor+">品牌/廠商:</td>";
					content+="<td>"+Common.get(dtl.getBrand())+"</td>";
					content+="</tr>";
					
					content+="<tr>";
					content+="<td "+bgcolor+">產地:</td>";
					content+="<td>"+Common.get(dtl.getManufactorCountry())+"</td>";
					content+="</tr>";
					
					content+="<tr>";
					content+="<td "+bgcolor+">產品批號:</td>";
					content+="<td>"+Common.get(dtl.getLotNo())+"</td>";
					content+="</tr>";
						
					content+="<tr>";
					content+="<td "+bgcolor+">資訊內容摘要:</td>";
					content+="<td>"+Common.get(dtl.getContextsummary())+"</td>";
					content+="</tr>";
					
					no++;
				}
				content+="</table>";
				
				subject+="回收訊息"+recoverNum+"件、警訊"+policeNum+"件。"+Datetime.getYYYMMDD()+"發送";

				
				for(int i=0; i<tmpMaxLength.length; i++)
				{
					sheet.setColumnView(i, tmpMaxLength[i]*2);
				}
				
				workbook.write();			
				workbook.close();
				
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
				 
				String mailHql = " from Con1013Db where con1012Db.id = '12'";
					
				java.util.List objListMail = ServiceGetter.getInstance().getTcbwService().load(mailHql);
			
				
			    if(objListMail!=null && objListMail.size()>0)
				{
					for(Object dtlObj : objListMail)
					{
						Con1013Db dtl = (Con1013Db)dtlObj;
						
						javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
						
						if(null!=dtl.getCommonUser())
						{
							p.setAddress(dtl.getCommonUser().getUserEmail());
						}
						else
						{
							p.setAddress(dtl.getEmail());
						}	
						
						//System.out.println(p.getAddress());
						
						mailList.add(p);
					}
				}
				
			   
			    java.util.List<String> attFile = new java.util.ArrayList<String>();
			  
			    attFile.add(outputFile.toString());
				
			    ServiceGetter.getInstance().getTcbwService().sendEmail(subject, content, mailList, attFile, true,null, null, null);
				
			    outputFile.delete();
				
				
				objList.clear();
				situationMap.clear();
			
			}
		
		
		}
		
		
		
	}
	
	@Override
	public Object doQueryAll() throws Exception 
	{		
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Cos7001Db ";

		hql += " where status in ('10','90') ";
		
		if(!"".equals(getQ_applNoS()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
		
		if(!"".equals(getQ_applNoE()))
			hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
		
		if(!"".equals(getQ_chProduct()))
		    hql += " and chProduct like "+ Common.likeSqlChar(getQ_chProduct());
		
		if(!"".equals(getQ_dataRevDateS()))
		{
			hql += " and dataRevDate >= " + Common.sqlChar(getQ_dataRevDateS());
		} 
			
		
		if(!"".equals(getQ_dataRevDateE()))
		{
			hql += " and dataRevDate <= " + Common.sqlChar(getQ_dataRevDateE());
		} 
		
		if(!"".equals(getQ_ingredient()))
			hql += " and ingredient = " + Common.sqlChar(getQ_ingredient());
		
		if(!"".equals(getQ_publDeptCodeId()))
			hql += " and publDept = " + Common.sqlChar(getQ_publDeptCodeId());
		
		if(!"".equals(getQ_publDateS()))
			hql += " and publDate >= " + Common.sqlChar(getQ_publDateS());

		if(!"".equals(getQ_publDateE()))
			hql += " and publDate <= " + Common.sqlChar(getQ_publDateE());
		
		System.out.println("[TCBW]-[VCOS0201F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by applNo");
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> situationMap = TCBWCommon.getCommonCodeMap("CONWARNING");
				java.util.Map<String, String> ingredientMap = TCBWCommon.getCommonCodeMap("CCI");
				java.util.Map<String, String> subjecttypeMap = TCBWCommon.getCommonCodeMap("COSSJTYPE");
				
				for(Object dtlObj : objList)
				{
					Cos7001Db dtl = (Cos7001Db)dtlObj;
					
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getChProduct());//品名
					rowArray[3] = Common.get(dtl.getDataRevDate());//資料收集日期
					rowArray[4] = Common.get(ingredientMap.get(dtl.getIngredient()));//化粧品項目
					rowArray[5] = Common.get(situationMap.get(dtl.getSituation()));//狀況
					rowArray[6] = Common.get(dtl.getPublDate());//發布日期
					rowArray[7] = Common.get(subjecttypeMap.get(dtl.getSubjecttype()));//訊息主題
					arrList.add(rowArray);
				}
				objList.clear();
				situationMap.clear();
			
			}
		}
		return arrList;
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
	public String getQ_applNoS() {
		return checkGet(q_applNoS);
	}
	public void setQ_applNoS(String qApplNoS) {
		q_applNoS = checkSet(qApplNoS);
	}
	public String getQ_applNoE() {
		return checkGet(q_applNoE);
	}
	public void setQ_applNoE(String qApplNoE) {
		q_applNoE = checkSet(qApplNoE);
	}
	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}
	public void setQ_chProduct(String qChProduct) {
		q_chProduct = checkSet(qChProduct);
	}
	
	public String getQ_dataRevDateS() {
		return checkGet(q_dataRevDateS);
	}
	public void setQ_dataRevDateS(String qDataRevDateS) {
		q_dataRevDateS = checkSet(qDataRevDateS);
	}
	public String getQ_dataRevDateE() {
		return checkGet(q_dataRevDateE);
	}
	public void setQ_dataRevDateE(String qDataRevDateE) {
		q_dataRevDateE = checkSet(qDataRevDateE);
	}
	public String getQ_publDept() {
		return checkGet(q_publDept);
	}
	public void setQ_publDept(String qPublDept) {
		q_publDept = checkSet(qPublDept);
	}
	public String getQ_ingredient() {
		return checkGet(q_ingredient);
	}
	public void setQ_ingredient(String qIngredient) {
		q_ingredient = checkSet(qIngredient);
	}
	public String getQ_publDeptCodeId() {return checkGet(q_publDeptCodeId);}
	public void setQ_publDeptCodeId(String q_publDeptCodeId) {this.q_publDeptCodeId = checkSet(q_publDeptCodeId);}
	public String getQ_publDeptName() {return checkGet(q_publDeptName);}
	public void setQ_publDeptName(String q_publDeptName) {this.q_publDeptName = checkSet(q_publDeptName);}
	

	public String getQ_publDateS() {
		return checkGet(q_publDateS);
	}
	public void setQ_publDateS(String qPublDateS) {
		q_publDateS = checkSet(qPublDateS);
	}
	public String getQ_publDateE() {
		return checkGet(q_publDateE);
	}
	public void setQ_publDateE(String qPublDateE) {
		q_publDateE = checkSet(qPublDateE);
	}

	private String doType;
	public String getDoType() {return checkGet(doType);}
	public void setDoType(String doType) {this.doType = checkSet(doType);}
	
	
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	
	public String getCon1012Id() {return checkGet(con1012Id);}
	public void setCon1012Id(String con1012Id) {this.con1012Id = checkSet(con1012Id);}
	
	
	
	
}
