package com.kangdainfo.tcbw.view.trans;

import java.io.File;


import org.apache.commons.collections.set.ListOrderedSet;



import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.XlsUtil;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0004Db;
import com.kangdainfo.tcbw.model.bo.Med0006Db;
import com.kangdainfo.tcbw.model.bo.Med0008Db;
import com.kangdainfo.tcbw.model.bo.Med0009Db;
import com.kangdainfo.tcbw.model.bo.Med0010Db;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med7002Db;
import com.kangdainfo.tcbw.model.bo.Med7005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.util.ListUtil;




public class TRANSMED02 extends SuperBean
{

	
	private String q_filePath;
	private String msg;
	private String people;
	
	
	private StringBuilder transMsg;
	
	String fds[];
	public String[] getFds(){return fds;	}
	public void setFds(String[] s){ fds = s;}
	
	public String getMsg() {return checkGet(msg);}
	public void setMsg(String msg) {this.msg = checkSet(msg);}
	
	public StringBuilder getTransMsg() {return transMsg;}
	public void setTransMsg(StringBuilder transMsg) {this.transMsg = transMsg;}
	public String getQ_filePath() {return this.checkGet(q_filePath);}
	public void setQ_filePath(String qFilePath) {q_filePath = checkSet(qFilePath);}
	
	public String getPeople() {return checkGet(people);}
	public void setPeople(String people) {this.people = checkSet(people);}
	
	
	public void trans() throws Exception 
	{	

		if (!"".equals(getQ_filePath())) 
		{
			
			String[] arrFileName=null;
			arrFileName=getQ_filePath().split(":;:");

			if (arrFileName.length>1) 
			{
		
			  String filestoreLocation = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("filestoreLocation");
			  File f = new File(filestoreLocation+File.separator+arrFileName[0]+File.separator+arrFileName[1]);
			  File dir = new File(filestoreLocation+File.separator+arrFileName[0]);
				
		      if (f.isFile() && f.exists() && f.canRead()) 
		      {
			
//			    java.util.Map<String, String> countyMap = TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='CTY' ");
//			    countyMap.put("臺北市","02");countyMap.put("臺中市","08");countyMap.put("臺南市","14");countyMap.put("臺東縣","17");	
			
		    	//醫材主類別代碼
			    java.util.Map<String, String> medMainCategoryCodeMap = TCBWCommon.getCommonCodeMap("MEDMCT");	
			    
			    //permitKey	許可證字
				java.util.Map<String,String>  permitKeyMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='MEDPKID' ");
				
			
			    XlsUtil xlsUtil = new XlsUtil();
			    java.util.List<String[]> list = xlsUtil.getJExcelModel(f.getPath(),0,false,100);
			
			    java.util.List<Med0001Db> updateList = new java.util.ArrayList<Med0001Db>();
			    String wrongString = "";
				int wrong =0;
			    for(int i=0;i<list.size();i++)
			    {	
				  
				
				   String[] rs = list.get(i);
				   
				   System.out.println("i=="+rs[0]);//
				   
                   Med0001Db med01 = (Med0001Db)View.getObject("from Med0001Db where applNo="+Common.sqlChar(Common.get(rs[0])));
				   
                   if(med01!=null)
                   {
                	 System.out.println("案件編號=="+rs[0]);//A 案件編號
                     
                     //notifierType	通報者-屬性
                     String notifierType=Common.get(rs[16]);//Q 通報身分別
                     
                     if("醫院".equals(notifierType) || "診所".equals(notifierType) || "藥局".equals(notifierType))
                     {
                     	med01.setNotifierType("03");
                     }
                     else if("民眾".equals(notifierType))
                     {
                    	 med01.setNotifierType("01");
                     }	
                     else if("廠商".equals(notifierType))
                     {
                    	 med01.setNotifierType("02");
                     }	
                     else if("衛生單位".equals(notifierType))
                     {
                    	 med01.setNotifierType("06");
                     }
                     
                     System.out.println("通報者-屬性=="+med01.getNotifierType());//Q 通報身分別
                     
                     
                     //notifierCounty	通報者-縣市別
//                     med01.setNotifierCounty(Common.get(countyMap.get(rs[48])));
//                     System.out.println("通報單位所在地區=="+med01.getNotifierCounty());//AW 通報單位所在地區
                     
                     
                     //medPermit 許可證字號
                     String medPermit=Common.get(rs[2]);//C 許可證字號

		    		   //許可證字
		    		   if(medPermit.indexOf("衛")!=-1 && medPermit.indexOf("字")!=-1)
                     {						    		   
		    			   System.out.println("PermitKey=="+Common.get(permitKeyMap.get(medPermit.substring(medPermit.indexOf("衛"), medPermit.indexOf("字")))));
		    			   med01.setMedPermit(Common.get(permitKeyMap.get(medPermit.substring(medPermit.indexOf("衛"), medPermit.indexOf("字")))));
                     }
		    		   //許可證號
		    		   if(medPermit.indexOf("第")!=-1 && medPermit.indexOf("號")!=-1)
                     {
		    			   System.out.println("PermitNumber=="+Common.get(medPermit));
		    			   med01.setMedPermitNumber(medPermit.substring(medPermit.indexOf("第")+1, medPermit.indexOf("號")));
                     }
                     
                     System.out.println("許可證字=="+med01.getMedPermit());
                     System.out.println("許可證號=="+med01.getMedPermitNumber());
                     
                     
                     //medCname 懷疑的醫療器材-中文品名
                     med01.setMedCname(Common.get(rs[4]));
                     System.out.println("中文品名=="+med01.getMedCname());////E 醫材品名
                     
                     //medMainCategoryCode	懷疑的醫療器材-醫材主類別代碼
                     String medMainCategoryCode=Common.get(rs[5]);//F 醫材主類別
                     
                     if(medMainCategoryCode.length()>0)
                     {
                     	medMainCategoryCode=medMainCategoryCode.substring(0,1);
                     	if(medMainCategoryCodeMap.get(medMainCategoryCode)!=null)
                     	{
                     		med01.setMedMainCategoryCode(medMainCategoryCode);
                     	}	
                     }	
                     System.out.println("醫材主類別=="+med01.getMedMainCategoryCode());
                     
                     //medSecCategoryCode	懷疑的醫療器材-醫材次類別代碼

                     String medSecCategoryCode=Common.get(rs[6]);//G 次類別代碼
                     
                     if(!"".equals(medSecCategoryCode))
                     {
                     	med01.setMedSecCategoryCode(medSecCategoryCode.replace(".",""));
                     }	
                     
                     System.out.println("次類別代碼=="+med01.getMedSecCategoryCode());
                     
                     //medPermitFirm	懷疑的醫療器材-許可證申請商
                     med01.setMedPermitFirm(Common.get(rs[8]));
                     System.out.println("許可證持有商=="+med01.getMedPermitFirm());//I 許可證持有商
                     
                     //medFactory	懷疑的醫療器材-製造廠
                     med01.setMedFactory(Common.get(rs[9]));
                     System.out.println("製造廠=="+med01.getMedFactory());//J 製造廠
                     
                     //medCountry	懷疑的醫療器材-製造國別
                     med01.setMedCountry(Common.get(rs[11]));
                     System.out.println("製造國別=="+med01.getMedCountry());//L 產地
                     
                     //badReactionPatientCode	不良反應相關資料-病人識別代號
                     med01.setBadReactionPatientCode(Common.get(rs[12]));
                     System.out.println("病人識別代號=="+med01.getBadReactionPatientCode());//M 識別代號
                     
                     
					   // 檢核發生日期為數值或者為作廢字樣						
//					   boolean checkDate = true;						
//					   try {								
//						   if(Double.isNaN(Double.parseDouble(Common.get(Datetime.getRocDateTransFromYYYYMMDD(Common.get(rs[13])))))) checkDate = false;							
//					   } catch (NumberFormatException e){								
//						   checkDate = false;							
//					   }
					   
					   //occurDate	發生日期
                       
                       if("NI".equals(Common.get(rs[13])) || Common.get(rs[13]).indexOf("日") == -1) {
                    	   med01.setOccurDate("");
                       } else {
                    	   rs[13] = Common.get(rs[13]).replace("年", "/");
                           rs[13] = Common.get(rs[13]).replace("月", "/");
                           rs[13] = Common.get(rs[13]).replace("日", "");
                           if(checkDate(Common.get(rs[13]))) { // 檢核發生日期為數值或者為作廢字樣	
    						   med01.setOccurDate(Common.get(Datetime.getRocDateTransFromYYYYMMDD(Common.get(rs[13]))));
    					   }
                       }
                       System.out.println("發生日期=="+med01.getOccurDate());//N 發生日期
					   
					   //otherRelatedData	其他相關資料
					   med01.setOtherRelatedData(Common.get(rs[14]));
					   System.out.println("其他相關資料=="+med01.getOtherRelatedData());//O 問題描述
					   
					   
					   //notifierRepDate	通報中心接獲通報日期
                       
					   if("NI".equals(Common.get(rs[15]))  || Common.get(rs[15]).indexOf("日") == -1) {
                    	   med01.setNotifierRepDate("");
                       } else {
                    	   rs[15] = Common.get(rs[15]).replace("年", "/");
                           rs[15] = Common.get(rs[15]).replace("月", "/");
                           rs[15] = Common.get(rs[15]).replace("日", "");
                           if(checkDate(Common.get(rs[15]))) { // 檢核發生日期為數值或者為作廢字樣	
    						   med01.setNotifierRepDate(Common.get(Datetime.getRocDateTransFromYYYYMMDD(Common.get(rs[15]))));
    					   }
                       }
                       
					   System.out.println("發生日期=="+med01.getNotifierRepDate());//P 通報日期
					   
					   //notifierDept	通報者-服務機構名稱
					   med01.setNotifierDept(Common.get(rs[17]));
					   System.out.println("服務機構名稱=="+med01.getNotifierDept());//R 服務機構

					   //badReactionSex	不良反應相關資料-性別
					   if("男".equals(Common.get(rs[18]))) {
						   med01.setBadReactionSex("M");
					   } else if ("女".equals(Common.get(rs[18]))) {
						   med01.setBadReactionSex("F");
					   }
					   System.out.println("性別=="+med01.getBadReactionSex());//S 病人性別
					   
					   //badReactionAge	不良反應相關資料-年齡
					   med01.setBadReactionAge(Common.get(rs[19]));
					   System.out.println("年齡=="+med01.getBadReactionAge());//T 年齡
					   
					   //badReactionWeight	不良反應相關資料-體重
					   med01.setBadReactionWeight(Common.get(rs[20]));
					   System.out.println("體重=="+med01.getBadReactionWeight());//U	體重
					   
					   //badReactionHeight	不良反應相關資料-身高
					   med01.setBadReactionHeight(Common.get(rs[21]));
					   System.out.println("身高=="+med01.getBadReactionHeight());//V	身高
					   
					   //badReactionResults	不良反應結果
					   if("先天性畸形".equals(Common.get(rs[22]))) {
						   med01.setBadReactionResults("04");
					   } else if("危及生命".equals(Common.get(rs[22]))) {
						   med01.setBadReactionResults("02");
					   } else if("死亡".equals(Common.get(rs[22]))) {
						   med01.setBadReactionResults("01");
					   } else if("延長病人住院時間".equals(Common.get(rs[22])) || "導致病人住院".equals(Common.get(rs[21]))) {
						   med01.setBadReactionResults("05");
					   } else if("非嚴重不良反應".equals(Common.get(rs[22]))) {
						   med01.setBadReactionResults("07");
					   } else if("造成永久性殘廢".equals(Common.get(rs[22]))) {
						   med01.setBadReactionResults("03");
					   } else if("需作處置以防永久性傷害".equals(Common.get(rs[22]))) {
						   med01.setBadReactionResults("06");
					   }
					   System.out.println("不良反應結果=="+med01.getBadReactionResults());//W	不良反應後果
					   
					   //medModel	懷疑的醫療器材-型號
					   med01.setMedModel(Common.get(rs[23]));
					   System.out.println("型號=="+med01.getMedModel());//X	型號
					   
					   //medNo	懷疑的醫療器材-序號
					   med01.setMedNo(Common.get(rs[24]));
					   System.out.println("序號=="+med01.getMedNo());//Y	序號
					   
					   //medLotNum	懷疑的醫療器材-批號
					   med01.setMedLotNum(Common.get(rs[25]));
					   System.out.println("批號=="+med01.getMedLotNum());//Z	批號

                     //要開欄位
//                     System.out.println("醫療器材管理分類等級=="+rs[43]);//AR 醫療器材管理分類等級
                     
                     //isTransferAdr 是否轉ADR評估  AdrCaseNo ADR案號
//                     med01.setIsTransferAdr(Common.get(rs[54]));
//		             System.out.println("是否轉ADR評估=="+med01.getIsTransferAdr());//BC 是否轉ADR評估
//		             
//		             med01.setAdrCaseNo(Common.get(rs[55]));
//		             System.out.println("ADR案號=="+med01.getAdrCaseNo());//BD ADR案號
                     
                    
//		             java.util.Set dtlSetMed06 = new ListOrderedSet();
//		             
//		             
//		             if(med01.getMed0006Dbs().size()>0)
//		             {
//		            	 for(int j=0;j<med01.getMed0006Dbs().size();j++)
//		            	 {
//		            		 Med0006Db m06= (Med0006Db) med01.getMed0006Dbs().iterator().next();
//		            		 
//		            		 Med0006Db med06 = (Med0006Db) View.getObject("from Med0006Db where id="+m06.getId());
//		            		 
//		            		 if(med06==null)
//						     {
//						    	med06 = new Med0006Db();
//						    	med06.setMed0001Db(med01);
//						     }
//		            		 
//		            		 //MED0006_DB
//						     //System.out.println("通報月份=="+rs[51]);//AZ 通報月份
//						     
//				             //bulletinKind	通報方式
//				             String bulletinKind=Common.get(rs[53]);
//				             
//				             if("電郵".equals(bulletinKind))
//				             {
//				            	 med06.setBulletinKind("02");
//				             }
//				             else if("傳真".equals(bulletinKind))
//				             {
//				            	 med06.setBulletinKind("03");
//				             }	 
//				             else if(!"".equals(bulletinKind))
//				             {
//				            	 med06.setBulletinKind("01");
//				             }	 
//				             
//				             System.out.println("通報方式=="+med06.getBulletinKind());//BB 通報方式
//				                
//				             //transDate	轉送日期
//				             if("2".equals(med06.getTransType()))
//				                med06.setTransDate(Datetime.getRocDateTransFromYYYYMMDD(rs[56]));
//				             else if("3".equals(med06.getTransType()))
//				                med06.setTransDate(Datetime.getRocDateTransFromYYYYMMDD(rs[76]));
//				             
//
//				             System.out.println("轉評日期=="+med06.getTransDate());//BE 轉評日期
//				                
//						     dtlSetMed06.add(med06);
//		            	 }
//		             }	 
//		             else
//		             {
//		            	 
//		            	 Med0006Db med06 = new Med0006Db();
//					     med06 = new Med0006Db();
//					     med06.setMed0001Db(med01);
//					
//					     
//	            		 med06.setTransType("2");
//	            		 
//	            		 //bulletinKind	通報方式
//			             String bulletinKind=Common.get(rs[53]);
//			             
//			             if("電郵".equals(bulletinKind))
//			             {
//			            	 med06.setBulletinKind("02");
//			             }
//			             else if("傳真".equals(bulletinKind))
//			             {
//			            	 med06.setBulletinKind("03");
//			             }	 
//			             else if(!"".equals(bulletinKind))
//			             {
//			            	 med06.setBulletinKind("01");
//			             }	 
//			             
//			             System.out.println("通報方式=="+med06.getBulletinKind());//BB 通報方式
//			                
//			             //transDate	轉送日期
//			             med06.setTransDate(Datetime.getRocDateTransFromYYYYMMDD(rs[56]));
//	
//	
//			             System.out.println("轉評日期=="+med06.getTransDate());//BE 轉評日期
//			                
//					     dtlSetMed06.add(med06);
//		            	 
//					     //有就是複評
//			             System.out.println("二次評估轉送日期=="+Datetime.getRocDateTransFromYYYYMMDD(rs[76]));//BY 二次評估轉送日期
//					     
//					     //transDate	二次評估轉送日期
//					     if(!"".equals(Datetime.getRocDateTransFromYYYYMMDD(rs[76])))
//					     { 	 
//					    	
//					    	Med0006Db med06_2 = new Med0006Db();
//						    med06_2 = new Med0006Db();
//						    med06_2.setMed0001Db(med01);
//					    	 
//					    	med06_2.setTransDate(Datetime.getRocDateTransFromYYYYMMDD(rs[76]));
//			                System.out.println("二次評估轉送日期=="+med06.getTransDate());//BE 轉評日期
//					     
//					        //transType	轉送種類(評估/初評/複評)
//			                med06_2.setTransType("3");
//			                
//			                
//			                //bulletinKind	通報方式
//				       
//				            if("電郵".equals(bulletinKind))
//				            {
//				            	med06_2.setBulletinKind("02");
//				            }
//				            else if("傳真".equals(bulletinKind))
//				            {
//				            	med06_2.setBulletinKind("03");
//				            }	 
//				            else if(!"".equals(bulletinKind))
//				            {
//				            	med06_2.setBulletinKind("01");
//				            }	 
//			                
//			                System.out.println("通報方式=="+med06_2.getBulletinKind());//BB 通報方式
//			                
//					        dtlSetMed06.add(med06_2);
//					        
//					     }
//
//		             }	 
//                	
//		             med01.setMed0006Dbs(dtlSetMed06);
//				   
//
//                     java.util.Set dtlSetMed08 = new ListOrderedSet();
//		             
//                	 Med0008Db med08 = (Med0008Db) ListUtil.getFirstObject(med01.getMed0008Dbs());
//					   
//				     if(med08==null)
//				     {
//				    	med08 = new Med0008Db();
//				    	med08.setMed0001Db(med01);
//				     }
//
//		             //bulletinQuality	通報品質
//				     med08.setBulletinQuality(Common.get(rs[52]));
//		             System.out.println("通報品質=="+med08.getBulletinQuality());//BA 通報品質
//		                
//		             //evaluationDate	評估日期
//		             med08.setEvaluationDate(Datetime.getRocDateTransFromYYYYMMDD(rs[58]));
//		             System.out.println("評估回覆日期=="+med08.getEvaluationDate());//BG 評估回覆日期
//		                
//		             
//		             
//		             System.out.println("醫材問題代碼-1=="+rs[60]);//BI 醫材問題代碼-1
//		             System.out.println("醫材問題代碼說明-1=="+rs[61]);//BJ 醫材問題代碼說明-1
//		             System.out.println("醫材問題代碼-2=="+rs[62]);//BK 醫材問題代碼-2
//		             System.out.println("醫材問題代碼說明-2=="+rs[63]);//BL 醫材問題代碼說明-2
//		             System.out.println("醫材問題代碼-3=="+rs[64]);//BM 醫材問題代碼-3
//		             System.out.println("醫材問題代碼說明-3=="+rs[65]);//BN 醫材問題代碼說明-3
//		             
//		             //medicalIssues	醫材問題代碼
//		             String medicalIssues="";
//		             
//		             if(!"".equals(Common.get(rs[60])))
//		             {
//		            	 medicalIssues+=Common.get(rs[60]);
//		             }
//		             
//		             if(!"".equals(Common.get(rs[62])))
//		             {
//		            	 if(medicalIssues.length()>0)medicalIssues+=",";
//		            	 medicalIssues+=Common.get(rs[62]);
//		             }
//		             
//		             if(!"".equals(Common.get(rs[64])))
//		             {
//		            	 if(medicalIssues.length()>0)medicalIssues+=",";
//		            	 medicalIssues+=Common.get(rs[64]);
//		             }
//		             
//		             med08.setMedicalIssues(medicalIssues);
//		             System.out.println("醫材問題代碼=="+med08.getMedicalIssues());// 醫材問題代碼
//		                
//		             //eventClass	事件等級
//		             med08.setEventClass(Common.get(rs[59]));
//		             System.out.println("事件等級=="+med08.getEventClass());//BH 事件等級
//		                
//		             //assessProposal	評估建議
//		             med08.setAssessProposal(Common.get(rs[66]));
//		             System.out.println("初步評估建議=="+med08.getAssessProposal());//BO 初步評估建議
//		                
//		             //System.out.println("初評追蹤進度=="+rs[57]);//BF 初評追蹤進度
//				     
//		             dtlSetMed08.add(med08);
//		             
//		             med01.setMed0008Dbs(dtlSetMed08);
//		             
//		           
//                     java.util.Set dtlSetMed09 = new ListOrderedSet();
//		             
//                	 Med0009Db med09 = (Med0009Db) ListUtil.getFirstObject(med01.getMed0009Dbs());
//					   
//				     if(med09==null)
//				     {
//				    	med09 = new Med0009Db();
//				    	med09.setMed0001Db(med01);
//				     }
//		             
//				     //evaluationDate	評估日期
//				     med09.setEvaluationDate(Datetime.getRocDateTransFromYYYYMMDD(rs[77]));
//		             System.out.println("二次評估回覆日期=="+med09.getEvaluationDate());//BZ 二次評估回覆日期
//		                
//		             //assessProposal	評估建議
//		             med09.setAssessProposal(Common.get(rs[78]));
//		             System.out.println("二次評估建議=="+med09.getAssessProposal());//CA 二次評估建議	
//		                
//		             //System.out.println("改級日期=="+Datetime.getRocDateTransFromYYYYMMDD(rs[79]));//CB 改級日期
//
//				     dtlSetMed09.add(med09);
//		             med01.setMed0009Dbs(dtlSetMed09);
//		             
//		             
//                     java.util.Set dtlSetMed10 = new ListOrderedSet();
//		             
//                	 Med0010Db med010 = (Med0010Db) ListUtil.getFirstObject(med01.getMed0010Dbs());
//					   
//				     if(med010==null)
//				     {
//				    	 med010 = new Med0010Db();
//				    	 med010.setMed0001Db(med01);
//				     }
//				     
//				     //noticeDate	通知日期
//				     med010.setNoticeDate(Datetime.getRocDateTransFromYYYYMMDD(rs[70]));
//				     System.out.println("通知廠商日=="+med010.getNoticeDate());//BS 通知廠商日
//		             //replyDate	回覆日期
//				     med010.setReplyDate(Datetime.getRocDateTransFromYYYYMMDD(rs[74]));
//		             System.out.println("廠商回覆日=="+med010.getReplyDate());//BW 廠商回覆日
//		             //replyContent	回覆內容
//		             med010.setReplyContent(Common.get(rs[75]));
//		             System.out.println("廠商回覆摘要=="+med010.getReplyContent());//BX 廠商回覆摘要
//				     
//				     dtlSetMed10.add(med010);
//				     
//		             med01.setMed0010Dbs(dtlSetMed10);
	                
		             med01.setIsExcelUpdate("Y");	//記錄有Excel匯入補強資料
                	 updateList.add(med01);
                } else {
                	//轉入失敗筆數
                	   wrongString += "<br>";
					   wrongString += (i+2);
					   wrong ++;
                }
                
                if (updateList!=null && updateList.size()>0) 
					ServiceGetter.getInstance().getTcbwService().updateBatch(updateList);
				
                //傳換狀態
//                setStatus();
                
				this.transMsg = new StringBuilder();
				
				transMsg.append("<font color='#0000CC'>匯入成功</font>").append("<br>");
				transMsg.append("<font color='#0000CC'>筆數：</font><font color='red'>").append(updateList.size()).append("</font><br>");
				transMsg.append("<font color='#0000CC'>未匯入筆數：</font><font color='red'>").append(wrong).append("</font><br>");
				transMsg.append("<font color='#0000CC'>未匯入項次：</font><font color='red'>").append(wrongString).append("</font><br>");
				setMsg("匯入成功!");
				f.delete();
				dir.delete();
				
			  }
		    }
		}
	   }
		

		
		
	}
	
	
	public void setStatus()
	{
		 String hql=" from Med0001Db where trans='Y' and eventKind='2' ";
		
		 java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		 if(objList!=null && objList.size()>0)
		 {
			java.util.List<Med0001Db> updateList = new java.util.ArrayList<Med0001Db>();
			 
			java.util.Iterator it = objList.iterator();
			
			while (it.hasNext()) 
			{
				Med0001Db med01 = (Med0001Db) it.next();
				
				String status="";
				
				if(med01.getMed0009Dbs().size()>0)
				{
					status="90";//如果有 med0009Db 90
				}
				else if(med01.getMed0006Dbs().size()>0)
				{
					String value="70"; //如果有 轉送日期 70
					for(int i=0;i<med01.getMed0006Dbs().size();i++)
					{
						Med0006Db med06= (Med0006Db) med01.getMed0006Dbs().iterator().next();
					    if("3".equals(med06.getTransType()))
					    {
					    	value="75";//如果有 二次轉送日期 75
					    }
					}	
					status=value;
				}	
				else if(med01.getMed0010Dbs().size()>0)
				{
					for(int i=0;i<med01.getMed0010Dbs().size();i++)
					{
						Med0010Db med10= (Med0010Db) med01.getMed0010Dbs().iterator().next();
						if(!"".equals(med10.getReplyDate()))
						{
							status="60";//如果有 med0010Db  廠商回覆日  60
						}	
						else if(!"".equals(med10.getNoticeDate()))
					    {
					    	status="51";//如果有 med0010Db  通知廠商日  51
					    }
					}	
				}
				else if(med01.getMed0008Dbs().size()>0)
				{
					for(int i=0;i<med01.getMed0008Dbs().size();i++)
					{
						Med0008Db med08= (Med0008Db) med01.getMed0008Dbs().iterator().next();
						if("A".equals(med08.getEventClass()) || "B".equals(med08.getEventClass()) )
						{
							status="50";//如果有 med0008Db  案件等級 AB 
						}	
						else if("C".equals(med08.getEventClass()))
					    {
					    	status="90";//如果有 med0008Db   C 90
					    }
					}	
				}
				else
				{
					status="40";//如果都沒有 40
				}	
				
				
				med01.setStatus(status);
				
				updateList.add(med01);
				
			}
			
			if (updateList!=null && updateList.size()>0) 
				ServiceGetter.getInstance().getTcbwService().updateBatch(updateList);
		 }
		
	}
	
	public boolean checkDate(String date) {
		if(Double.isNaN(Double.parseDouble(Common.get(Datetime.getRocDateTransFromYYYYMMDD(Common.get(date)))))) return false;
		else return true;
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
