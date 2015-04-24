package com.kangdainfo.tcbw.view.trans;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.XlsUtil;
import com.kangdainfo.tcbw.model.bo.Drg7001Db;
import com.kangdainfo.tcbw.model.bo.Drg7002Db;
import com.kangdainfo.tcbw.model.bo.Drg7003Db;
import com.kangdainfo.tcbw.model.bo.Med7005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;





public class TRANSVDRG extends SuperBean
{

	private String transType;
	private String q_filePath;
	private String msg;
	private String people;
	private String deleteYM;
	
	
	private StringBuilder transMsg;
	
	String fds[];
	public String[] getFds(){return fds;	}
	public void setFds(String[] s){ fds = s;}
	
	public String getMsg() {return checkGet(msg);}
	public void setMsg(String msg) {this.msg = checkSet(msg);}
	public String getTransType() {return checkGet(transType);}
	public void setTransType(String transType) {this.transType = checkSet(transType);}
	
	public StringBuilder getTransMsg() {return transMsg;}
	public void setTransMsg(StringBuilder transMsg) {this.transMsg = transMsg;}
	public String getQ_filePath() {return this.checkGet(q_filePath);}
	public void setQ_filePath(String qFilePath) {q_filePath = checkSet(qFilePath);}
	
	public String getPeople() {return checkGet(people);}
	public void setPeople(String people) {this.people = checkSet(people);}
	public String getDeleteYM() {return checkGet(deleteYM);}
	public void setDeleteYM(String deleteYM) {this.deleteYM = checkSet(deleteYM);}
	
	
	// 103年3月前
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
					XlsUtil xlsUtil = new XlsUtil();
					java.util.List<String[]> list = xlsUtil.getJExcelModel(f.getPath(),0,false,29);
			
					java.util.List<Drg7001Db> saveList = new java.util.ArrayList<Drg7001Db>();
					java.util.List<Drg7001Db> updateList = new java.util.ArrayList<Drg7001Db>();
					String wrongString = "";
					int wrong =0;
					if (list!=null && list.size()>0) 
					{	
					   String yyymmdd = Datetime.getYYYMMDD();
					   String hhmmss = Datetime.getHHMMSS();
					   //許可證字
					   java.util.Map<String,String>  permitKeyMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGPKID' ");
					   //發佈單位 CONPUBLDEPT
					   java.util.Map<String,String>  pubDeptMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='CONPUBLDEPT' ");
					   //事件原因 (品質異常型態 DRGWNTP)
					   java.util.Map<String,String>  wntpMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGWNTP' ");
					   //回收型態DRGRECTP
					   java.util.Map<String,String>  rectpMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGRECTP' ");
					   //劑型DRGFORM
					   java.util.Map<String,String>  formMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGFORM' ");
					   
					   String dataRevDate ="";  //獲知日期
					   String msgNum ="";       //訊息數
					   
					   for(int i=0;i<list.size();i++)
					   {	
						   boolean isUpdate = false;						   
						   String[] rs = list.get(i);						   
						   
						   // 訊息數=0; 不轉入
						   if(!"0".equals(Common.get(rs[1])))
						   {						    	   
							   Drg7001Db  obj = new Drg7001Db();
							   java.util.Set dtlSet7002 = new ListOrderedSet();
							   java.util.Set dtlSet7003 = new ListOrderedSet();
						       
							   if(!"".equals(Common.get(rs[0])) && !"".equals(Common.get(rs[1]))){
								   dataRevDate = Common.get(rs[0]);
								   msgNum = Common.get(rs[1]);
							   }
							   //System.out.println("獲知日期=="+Common.get(dataRevDate));
							   //獲知日期
							   obj.setDataRevDate(Common.get(Common.get(Datetime.getRocDateTransFromYYYYMMDD(dataRevDate))));						       
						       //訊息數
						       obj.setMsgNum(Common.getLong(msgNum));							   
						       //訊息來源
						       String publDept ="";
							   if(!"".equals(Common.get(rs[2]))){
								   if(Common.get(Common.get(rs[2])).indexOf("明報")!=-1 || Common.get(Common.get(rs[2])).indexOf("香港")!=-1){
									   publDept = "香港";
								   }else if (Common.get(Common.get(rs[2])).indexOf("新加坡")!=-1 ){
									   publDept = "HSA";
								   }else{
									   publDept = Common.get(rs[2]);
								   }
							   }
						       obj.setPublDept(pubDeptMap.get(publDept));						       
						       //分類
						       if("recall".equals(Common.get(rs[3]))){
						    	   obj.setSituation("02");  //回收
						       }else if ("alert".equals(Common.get(rs[3]))){
						    	   obj.setSituation("01"); //警訊
						       }						     
						       //發生事件簡述
						       obj.setEventDesc(Common.get(rs[4]));						     
						       //商品名/學名
						       obj.setChProduct(Common.get(rs[5]));
						       obj.setScientificName(Common.get(rs[5]));						     
						       //廠商
						       obj.setDruggist(Common.get(rs[6]));						     
						       //製造廠
						       obj.setManufactorName(Common.get(rs[7]));						     
						       //批號
						       obj.setLotNo(Common.get(rs[8]));					       
						       
						       //System.out.println("許可證字號=="+Common.get(rs[10]));
						       //國內許可證持有商,許可證字號/專案進口,查有藥證，廠商回覆內容
						       if(!"".equals(Common.get(rs[10])) && !"無".equals(Common.get(rs[10]))){
						    	   String[] permitArray = new String[Common.get(rs[10]).split("、").length];						    							
						    	   permitArray = Common.get(rs[10]).split("、");
						    	   //drg702Map
					    		   java.util.Map<String, Drg7002Db> drg702Map = new java.util.HashMap<String, Drg7002Db>();	
						    	   for(int j=0;j<Common.get(rs[10]).split("、").length;j++){						    		   					    		   
						    		   String permitKey ="",permitNo="";
						    		   String appunNo = "",appName="";
						    		   if("專案進口".equals(Common.get(permitArray[j])) || "其他".equals(Common.get(permitArray[j]))){
						    			   permitKey = Common.get(permitKeyMap.get(permitArray[j]));
						    			   
						    			   Drg7002Db obj7002 = new Drg7002Db();					    				 
						    			   obj7002.setDrg7001Db(obj);
					    				   obj7002.setReplyDesc(Common.get(rs[11]));
					    				   obj7002.setCreator("SYS");
					    				   obj7002.setCreateDate(yyymmdd);
					    				   obj7002.setCreateTime(hhmmss);
					    				   dtlSet7002.add(obj7002);	
						    			   
						    			   Drg7003Db obj7003 = new Drg7003Db();								    		   
						    			   obj7003.setDrg7001Db(obj);
						    			   obj7003.setPermitNo(permitNo);
						    			   obj7003.setPermitKey(permitKey);
						    			   obj7003.setApplicationId(null);						    			
						    			   obj7003.setApplicationName(Common.get(rs[9]));					    				
						    			   obj7003.setDrg7002Db(obj7002);
						    			   obj7003.setCreator("SYS");
					    				   obj7003.setCreateDate(yyymmdd);
					    				   obj7003.setCreateTime(hhmmss);
						    				
						    			   dtlSet7003.add(obj7003);
						    			   
							    	   }
							    	   else{
							    		   if(Common.get(permitArray[j]).indexOf("字")!=-1)
			                               {
							    			   permitKey = Common.get(permitKeyMap.get(Common.get(permitArray[j]).substring(0, Common.get(permitArray[j]).indexOf("字"))));
			                               }
							    		   if(Common.get(permitArray[j]).indexOf("第")!=-1 && Common.get(permitArray[j]).indexOf("號")!=-1)
			                               {
							    			   permitNo = Common.get(permitArray[j]).substring(Common.get(permitArray[j]).indexOf("第")+1, Common.get(permitArray[j]).indexOf("號"));
			                               }else if(Common.get(permitArray[j]).indexOf("第")!=-1){
			                            	   permitNo = Common.get(permitArray[j]).substring(Common.get(permitArray[j]).indexOf("第")+1);
			                               }
							    		   //System.out.println("許可證字=="+permitKey+"  許可證號=="+permitNo);
							    		   //依據許可證查詢藥證系統(國內許可證持有商)							    		   
							    		   Database db2 = new Database("MLMS");
							    		   try{
							    				List<Object> parameter = new ArrayList<Object>();
							    				String hql = " select APPUNNO,APPNAME from VW_ForADR_TBMLIC where 1=1 ";			
							    				if (!"".equals(permitNo)){	
							    					hql += " and LICID1 = ?";
							    					parameter.add(permitNo);
							    				}
							    				if (!"".equals(permitKey)){		
							    					hql += " and LICEKID = ?";
							    					parameter.add(permitKey);
							    				}
							    				//System.out.println("SQL=="+hql);
							    			    ResultSet rsT = db2.querySQLByPrepareStatement(hql, parameter);							    				
							    				if (rsT!=null){							    				
							    					while (rsT.next()){							    					
							    						appunNo = Common.get(rsT.getString("APPUNNO"));							    					
							    						appName = Common.get(rsT.getString("APPNAME"));							    				
							    					}						    					
							    				}
							    			} catch (Exception e) {
							    				e.printStackTrace();
							    			} finally {
							    				db2.closeAll();
							    			}
							    			//----------------------end MLMS-----------------------------------
							    			if(!"".equals(Common.get(appunNo)) && !"".equals(Common.get(appName))){					    				
							    				
							    				String key = Common.get(appunNo);						    			
							    				if(!drg702Map.containsKey(key)){						    				
							    					Drg7002Db obj7002 = new Drg7002Db();						    				
							    					obj7002.setDrg7001Db(obj);						    				
							    					obj7002.setReplyDesc(Common.get(rs[11]));
							    					obj7002.setCreator("SYS");
								    				obj7002.setCreateDate(yyymmdd);
								    				obj7002.setCreateTime(hhmmss);
							    				
							    					drg702Map.put(key, obj7002);						    				
							    					dtlSet7002.add(obj7002);						    			
							    				}
							    				
							    				Drg7003Db obj7003 = new Drg7003Db();								    		   
							    				obj7003.setDrg7001Db(obj);
							    				obj7003.setPermitNo(permitNo);
							    				obj7003.setPermitKey(permitKey);
							    				obj7003.setApplicationId(appunNo);						    			
							    				obj7003.setApplicationName(appName);						    			
							    				obj7003.setDrg7002Db(drg702Map.get(key));
							    				obj7003.setCreator("SYS");
							    				obj7003.setCreateDate(yyymmdd);
							    				obj7003.setCreateTime(hhmmss);
							    			
							    				dtlSet7003.add(obj7003);
							    			}
							    	   }					    			
						    	   }						    	   
						       }    
						     
						       //網址
						       obj.setDatasourWebSite(Common.get(rs[12]));						     
						       //事件原因
						       obj.setQualitywarningtype(wntpMap.get(Common.get(rs[13])));						       
						       //回收型態
						       obj.setRecycleType(rectpMap.get(Common.get(rs[14])));						     
						       //劑型
						       obj.setWarningmedModel(formMap.get(Common.get(rs[15])));
						      
						       obj.setStatus("90");
						       obj.setIsTransferData("Y");
						       obj.setCreator("SYS");
			    			   obj.setCreateDate(yyymmdd);
			    			   obj.setCreateTime(hhmmss);
			    			   
			    			   obj.setDrg7003Dbs(dtlSet7003);
						       obj.setDrg7002Dbs(dtlSet7002);						       
						      
						       if(isUpdate)	 
						    	   updateList.add(obj);					       				      
						       else	 
						    	   saveList.add(obj);						       					 
						   }else{
							   wrongString += "<br>";
							   wrongString += (i+2);
							   wrong ++;
						   }
					   }					   
					}
					
					if (updateList!=null && updateList.size()>0) 
						ServiceGetter.getInstance().getTcbwService().updateBatch(updateList);
					
					if (saveList!=null && saveList.size()>0) 
						ServiceGetter.getInstance().getTcbwService().saveBatch(saveList);
					
					this.transMsg = new StringBuilder();
					
					transMsg.append("<font color='#0000CC'>匯入成功</font>").append("<br>");
					transMsg.append("<font color='#0000CC'>新增筆數：</font><font color='red'>").append(saveList.size()).append("</font><br>");
					transMsg.append("<font color='#0000CC'>更新筆數：</font><font color='red'>").append(updateList.size()).append("</font><br>");
					transMsg.append("<font color='#0000CC'>不匯入筆數：</font><font color='red'>").append(wrong).append("</font><br>");
					transMsg.append("<font color='#0000CC'>不匯入項次：</font><font color='red'>").append(wrongString).append("</font><br>");
					setMsg("匯入成功!");
					dir.delete();
				}				
			}		
		}	
	}
	
	// 103年3月後
	public void trans02() throws Exception 
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
					XlsUtil xlsUtil = new XlsUtil();
					java.util.List<String[]> list = xlsUtil.getJExcelModel(f.getPath(),0,false,29);
			
					java.util.List<Drg7001Db> saveList = new java.util.ArrayList<Drg7001Db>();
					java.util.List<Drg7001Db> updateList = new java.util.ArrayList<Drg7001Db>();				
					String wrongString = "";
					int wrong =0;
					if (list!=null && list.size()>0) 
					{	
					   String yyymmdd = Datetime.getYYYMMDD();
					   String hhmmss = Datetime.getHHMMSS();
					   //許可證字
					   java.util.Map<String,String>  permitKeyMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGPKID' ");
					   //發佈單位 CONPUBLDEPT
					   java.util.Map<String,String>  pubDeptMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='CONPUBLDEPT' ");
					   //事件原因 (品質異常型態 DRGWNTP)
					   java.util.Map<String,String>  wntpMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGWNTP' ");
					   //回收型態DRGRECTP
					   java.util.Map<String,String>  rectpMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGRECTP' ");
					   //劑型DRGFORM
					   java.util.Map<String,String>  formMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGFORM' ");
					   //燈號CONLAMP
					   java.util.Map<String,String>  lampMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='CONLAMP' ");					   
					   //後續處理DRGEFFECT
					   java.util.Map<String,String>  effectMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='CONLAMP' ");
					   
					   String dataRevDate ="";  //獲知日期
					   String msgNum ="";       //訊息數
					   
					   for(int i=0;i<list.size();i++)
					   {	
						   boolean isUpdate = false;						   
						   String[] rs = list.get(i);						   
						   
						   // 發生事件簡述為空不轉入
						   if(!"".equals(Common.get(rs[16])))
						   {
							   java.util.Set dtlSet7002 = new ListOrderedSet();
							   java.util.Set dtlSet7003 = new ListOrderedSet();
							   
							   Drg7001Db  obj = new Drg7001Db();					        
						       
							   if(!"".equals(Common.get(rs[1]))){
								   dataRevDate = Common.get(rs[1]);
								   msgNum = Common.get(rs[15]);
							   }						   
							 
							   //產品類別(不轉入)
							 
							   //資料收集日期(獲知日期)
							   //System.out.println("獲知日期=="+Common.get(dataRevDate));
							   obj.setDataRevDate(Common.get(Common.get(Datetime.getRocDateTransFromYYYYMMDD(dataRevDate))));
							 
							   //發佈單位【訊息來源】(明報：轉香港；香港-政府新聞網：轉香港；香港衛生署：轉香港；新加坡hsa：轉Hsa)
							   String publDept ="";
							   if(!"".equals(Common.get(rs[2]))){
								   if(Common.get(Common.get(rs[2])).indexOf("明報")!=-1 || Common.get(Common.get(rs[2])).indexOf("香港")!=-1){
									   publDept = "香港";
								   }else if (Common.get(Common.get(rs[2])).indexOf("新加坡")!=-1 ){
									   publDept = "HSA";
								   }
							   }
						       obj.setPublDept(pubDeptMap.get(publDept));							 
							   //狀況【分類】
						       if("recall".equals(Common.get(rs[3]))){
						    	   obj.setSituation("02");  //回收
						       }else if ("alert".equals(Common.get(rs[3]))){
						    	   obj.setSituation("01"); //警訊
						       }							 
							   //發佈日期
						       obj.setPublDate(Common.get(Common.get(Datetime.getRocDateTransFromYYYYMMDD(Common.get(rs[4])))));						   
						       //訊息主題
						       obj.setSubject(Common.get(rs[5]));						       
							   //資訊內容摘要
						       obj.setContextsummary(Common.get(rs[6]));						       
							   //商品名/學名
						       obj.setChProduct(Common.get(rs[7]));
						       obj.setScientificName(Common.get(rs[7]));						       
							   //產品批號
						       obj.setLotNo(Common.get(rs[8]));						       
							   //資料來源【網址】
						       obj.setDatasourWebSite(Common.get(rs[9]));						       
							 
							   //是否發佈新聞稿
						       if("Y".equals(Common.get(rs[11])) || "是".equals(Common.get(rs[11]))){						       
						    	   obj.setIspublnews("Y");
						       }else if("N".equals(Common.get(rs[11])) || "否".equals(Common.get(rs[11])) || "無".equals(Common.get(rs[11])) || "".equals(Common.get(rs[11]))){
						    	   obj.setIspublnews("N");
						       }
						       
							   //燈號
						       String lamp = "";
						       if("無".equals(Common.get(rs[12])) || "".equals(Common.get(rs[12]))){
						    	   lamp = "綠燈";
						       }else{
						    	   lamp = Common.get(rs[12]);
						       }						       
							   obj.setLamp(lampMap.get(lamp));
							   
							   //後續處理
							   if(!"".equals(Common.get(effectMap.get(Common.get(rs[14]))))){
								   obj.setAftereffect(effectMap.get(Common.get(rs[14])));
							   }else{
								   obj.setAftereffect("90");
								   obj.setAftereffectOther(Common.get(rs[14]));	
							   }
							   					 
							   //訊息數 rs[15]
						       obj.setMsgNum(Common.getLong(msgNum));							 
							   //發生事件簡述
						       obj.setEventDesc(Common.get(rs[16]));						 
							   //廠商
						       obj.setDruggist(Common.get(rs[17]));								 
							   //製造廠
						       obj.setManufactorName(Common.get(rs[18]));
						       
							   //是否草擬紅綠燈初搞
						       if("Y".equals(Common.get(rs[21])) || "是".equals(Common.get(rs[21]))){						       
						    	   obj.setIswarning("Y");
						       }else {
						    	   obj.setIswarning("N");
						       }				   
							   //事件原因
						       obj.setQualitywarningtype(wntpMap.get(Common.get(rs[22])));						       
						       //回收型態
						       obj.setRecycleType(rectpMap.get(Common.get(rs[23])));						     
						       //劑型
						       obj.setWarningmedModel(formMap.get(Common.get(rs[24])));	
						       
						       //System.out.println("許可證字號"+i+"=="+Common.get(rs[13]));
						       //產品是否進口(7002) rs[10],許可證號碼(7003) rs[13],國內許可證持有商(7003)  rs[19],廠商回覆內容(7002) rs[20]
						       if(!"".equals(Common.get(rs[13])) && !"無".equals(Common.get(rs[13]))){
						    	   String[] permitArray = new String[Common.get(rs[13]).split("、").length];						    							
						    	   permitArray = Common.get(rs[13]).split("、");
						    	   //drg702Map
					    		   java.util.Map<String, Drg7002Db> drg702Map = new java.util.HashMap<String, Drg7002Db>();	
						    	   for(int j=0;j<Common.get(rs[13]).split("、").length;j++){						    		   					    		   
						    		   String permitKey ="",permitNo="";
						    		   String appunNo = "",appName="";
						    		   if("專案進口".equals(Common.get(permitArray[j])) || "其他".equals(Common.get(permitArray[j]))){
						    			   permitKey = Common.get(permitKeyMap.get(permitArray[j]));
						    			   
						    			   Drg7002Db obj7002 = new Drg7002Db();
						    			   obj7002.setDrg7001Db(obj);
					    				   obj7002.setReplyDesc(Common.get(rs[20]));  //廠商回覆內容					    				 
					    				   if("Y".equals(Common.get(rs[10])) || "是".equals(Common.get(rs[10]))){						       
					    					   obj7002.setIsImport("Y");  //產品是否進口
									       }else {
									    	   obj7002.setIsImport("N");  //產品是否進口
									       }
					    				   obj7002.setCreator("SYS");
					    				   obj7002.setCreateDate(yyymmdd);
					    				   obj7002.setCreateTime(hhmmss);
					    				   dtlSet7002.add(obj7002);	
						    			   
						    			   Drg7003Db obj7003 = new Drg7003Db();
						    			   obj7003.setDrg7001Db(obj);
						    			   obj7003.setPermitNo(permitNo);
						    			   obj7003.setPermitKey(permitKey);
						    			   obj7003.setApplicationId(null);						    			
						    			   obj7003.setApplicationName(Common.get(rs[19]));					    				
						    			   obj7003.setDrg7002Db(obj7002);
						    			   obj7003.setCreator("SYS");
					    				   obj7003.setCreateDate(yyymmdd);
					    				   obj7003.setCreateTime(hhmmss);
						    				
						    			   dtlSet7003.add(obj7003);
						    			   
							    	   }
							    	   else{
							    		   if(Common.get(permitArray[j]).indexOf("字")!=-1)
			                               {
							    			   permitKey = Common.get(permitKeyMap.get(Common.get(permitArray[j]).substring(0, Common.get(permitArray[j]).indexOf("字"))));
			                               }
							    		   if(Common.get(permitArray[j]).indexOf("第")!=-1 && Common.get(permitArray[j]).indexOf("號")!=-1)
			                               {
							    			   permitNo = Common.get(permitArray[j]).substring(Common.get(permitArray[j]).indexOf("第")+1, Common.get(permitArray[j]).indexOf("號"));
			                               }else if(Common.get(permitArray[j]).indexOf("第")!=-1){
			                            	   permitNo = Common.get(permitArray[j]).substring(Common.get(permitArray[j]).indexOf("第")+1);
			                               }
							    		   //System.out.println("許可證字=="+permitKey+"  許可證號=="+permitNo);
							    		   //依據許可證查詢藥證系統(國內許可證持有商)							    		   
							    		   Database db2 = new Database("MLMS");
							    		   try{
							    				List<Object> parameter = new ArrayList<Object>();
							    				String hql = " select APPUNNO,APPNAME from VW_ForADR_TBMLIC where 1=1 ";			
							    				if (!"".equals(permitNo)){	
							    					hql += " and LICID1 = ?";
							    					parameter.add(permitNo);
							    				}
							    				if (!"".equals(permitKey)){		
							    					hql += " and LICEKID = ?";
							    					parameter.add(permitKey);
							    				}
							    				//System.out.println("SQL=="+hql);
							    			    ResultSet rsT = db2.querySQLByPrepareStatement(hql, parameter);							    				
							    				if (rsT!=null){							    				
							    					while (rsT.next()){							    					
							    						appunNo = Common.get(rsT.getString("APPUNNO"));							    					
							    						appName = Common.get(rsT.getString("APPNAME"));							    				
							    					}						    					
							    				}
							    			} catch (Exception e) {
							    				e.printStackTrace();
							    			} finally {
							    				db2.closeAll();
							    			}
							    			//----------------------end MLMS-----------------------------------
							    			if(!"".equals(Common.get(appunNo)) && !"".equals(Common.get(appName))){					    				
							    				
							    				String key = Common.get(appunNo);
							    				if(!drg702Map.containsKey(key)){
							    					Drg7002Db obj7002 = new Drg7002Db();
							    					obj7002.setDrg7001Db(obj);						    				
							    					obj7002.setReplyDesc(Common.get(rs[20]));  //廠商回覆內容					    				 
								    				   if("Y".equals(Common.get(rs[10])) || "是".equals(Common.get(rs[10]))){						       
								    					   obj7002.setIsImport("Y");  //產品是否進口
												       }else {
												    	   obj7002.setIsImport("N");  //產品是否進口
												       }
							    					obj7002.setCreator("SYS");
								    				obj7002.setCreateDate(yyymmdd);
								    				obj7002.setCreateTime(hhmmss);
							    				
							    					drg702Map.put(key, obj7002);						    				
							    					dtlSet7002.add(obj7002);						    			
							    				}
							    				
							    				Drg7003Db obj7003 = new Drg7003Db();
							    				obj7003.setDrg7001Db(obj);
							    				obj7003.setPermitNo(permitNo);
							    				obj7003.setPermitKey(permitKey);
							    				obj7003.setApplicationId(appunNo);						    			
							    				obj7003.setApplicationName(appName);						    			
							    				obj7003.setDrg7002Db(drg702Map.get(key));
							    				obj7003.setCreator("SYS");
							    				obj7003.setCreateDate(yyymmdd);
							    				obj7003.setCreateTime(hhmmss);
							    			
							    				dtlSet7003.add(obj7003);
							    			}
							    	   }					    			
						    	   }						    	   
						       }				       
						      
						       obj.setStatus("90");
						       obj.setIsTransferData("Y");
						       obj.setCreator("SYS");
			    			   obj.setCreateDate(yyymmdd);
			    			   obj.setCreateTime(hhmmss);
			    			   
			    			   
			    			   obj.setDrg7002Dbs(dtlSet7002);
			    			   obj.setDrg7003Dbs(dtlSet7003);						       						       
						      
						       if(isUpdate)	 
						    	   updateList.add(obj);					       				      
						       else	 
						    	   saveList.add(obj);						       					 
						   }else{
							   wrongString += "<br>";
							   wrongString += (i+2);
							   wrong ++;
						   }
					   }					   
					}
					
					if (updateList!=null && updateList.size()>0) 
						ServiceGetter.getInstance().getTcbwService().updateBatch(updateList);
					
					if (saveList!=null && saveList.size()>0) 
						ServiceGetter.getInstance().getTcbwService().saveBatch(saveList);
					
					this.transMsg = new StringBuilder();
					
					transMsg.append("<font color='#0000CC'>匯入成功</font>").append("<br>");
					transMsg.append("<font color='#0000CC'>新增筆數：</font><font color='red'>").append(saveList.size()).append("</font><br>");
					transMsg.append("<font color='#0000CC'>更新筆數：</font><font color='red'>").append(updateList.size()).append("</font><br>");
					transMsg.append("<font color='#0000CC'>不匯入筆數：</font><font color='red'>").append(wrong).append("</font><br>");
					transMsg.append("<font color='#0000CC'>不匯入項次：</font><font color='red'>").append(wrongString).append("</font><br>");					
					setMsg("匯入成功!");
					dir.delete();
				}				
			}		
		}	
	}
	
	public void deleteTrans() throws Exception {
		java.util.List<Drg7001Db> deleteList01 = new java.util.ArrayList<Drg7001Db>();
		java.util.List<Drg7002Db> deleteList02 = new java.util.ArrayList<Drg7002Db>();
		java.util.List<Drg7003Db> deleteList03 = new java.util.ArrayList<Drg7003Db>();
		String hql03 = " from Drg7003Db where drg7001Db.isTransferData='Y' ";
		if(!"".equals(getDeleteYM())) hql03 += "and drg7001Db.dataRevDate like"+Common.sqlChar(getDeleteYM()+"%");
		//System.out.println("hql03=="+hql03);
		String hql02 = " from Drg7002Db where drg7001Db.isTransferData='Y' ";
		if(!"".equals(getDeleteYM())) hql02 += "and drg7001Db.dataRevDate like"+Common.sqlChar(getDeleteYM()+"%");
		//System.out.println("hql02=="+hql02);
		String hql01 = " from Drg7001Db where isTransferData='Y' ";		
		if(!"".equals(getDeleteYM())) hql01 += "and dataRevDate like"+Common.sqlChar(getDeleteYM()+"%");
		//System.out.println("hql01=="+hql01);
		deleteList03.addAll(ServiceGetter.getInstance().getCommonService().load(hql03));
		deleteList02.addAll(ServiceGetter.getInstance().getCommonService().load(hql02));
		deleteList01.addAll(ServiceGetter.getInstance().getCommonService().load(hql01));		
		ServiceGetter.getInstance().getCommonService().deleteBatch(deleteList03);
		ServiceGetter.getInstance().getCommonService().deleteBatch(deleteList02);
		ServiceGetter.getInstance().getCommonService().deleteBatch(deleteList01);
		this.transMsg = new StringBuilder();
		setMsg("刪除成功!");
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
