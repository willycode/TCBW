package com.kangdainfo.tcbw.view.trans;

import java.io.File;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.XlsUtil;
import com.kangdainfo.tcbw.model.bo.Con1003Db;
import com.kangdainfo.tcbw.model.bo.Drg8001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;





public class TRANSSDRG extends SuperBean
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
					XlsUtil xlsUtil = new XlsUtil();
					java.util.List<String[]> list = xlsUtil.getJExcelModel(f.getPath(),0,false,29);
			
					java.util.List<Drg8001Db> saveList = new java.util.ArrayList<Drg8001Db>();
					java.util.List<Drg8001Db> updateList = new java.util.ArrayList<Drg8001Db>();
					String wrongString = "";
					int wrong =0;
					if (list!=null && list.size()>0) 
					{					   
					   //permitKey	許可證字
					   java.util.Map<String,String>  permitKeyMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGPKID' ");
					   //後續處理方式DRGRECCS 
					   java.util.Map<String,String>  reccsMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGRECCS' ");
					   //回收等級DRGRECCLAS
					   java.util.Map<String,String>  classMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGRECCLAS' ");
					   
					   for(int i=0;i<list.size();i++)
					   {						   
						   boolean isUpdate = true;						   
						   String[] rs = list.get(i);
						   String permitKey="",permitNo="";
						   //許可證(Product license)
					       //System.out.println("許可證==="+Common.get(rs[3]));
					       if(!"".equals(Common.get(rs[3]))){
					    	   if("專案進口".equals(Common.get(rs[3])) || "其他".equals(Common.get(rs[3]))){
					    		   permitKey = Common.get(permitKeyMap.get(Common.get(rs[3])));
					    	   }
					    	   else{
					    		   if(Common.get(rs[3]).indexOf("字")!=-1)
	                               {						    		   
					    			   //System.out.println("字=="+Common.get(Common.get(rs[3]).substring(0, Common.get(rs[3]).indexOf("字"))));
					    			   permitKey = Common.get(permitKeyMap.get(Common.get(rs[3]).substring(0, Common.get(rs[3]).indexOf("字"))));
	                               }
					    		   if(Common.get(rs[3]).indexOf("第")!=-1 && Common.get(rs[3]).indexOf("號")!=-1)
	                               {	
					    			   //System.out.println("號=="+Common.get(Common.get(rs[3]).substring(Common.get(rs[3]).indexOf("第")+1, Common.get(rs[3]).indexOf("號"))));
					    			   permitNo = Common.get(Common.get(rs[3]).substring(Common.get(rs[3]).indexOf("第")+1, Common.get(rs[3]).indexOf("號")));
	                               }
					    	   }						    	   
					       }
					       //許可證資料長度大於13不轉
						   if(Common.get(rs[3]).length()<=13)
						   {
							   Drg8001Db obj = (Drg8001Db)View.getObject("from Drg8001Db where applNo="+Common.sqlChar(Common.get(rs[0])));						   
						       if(obj==null)
						       {
							      obj = new Drg8001Db();
							      isUpdate=false;
						       }						     
						       //編號(No.)						       
						       //System.out.println("編號==="+Common.get(rs[0]));	
						       obj.setApplNo(Common.get(rs[0]));
						       
						       //回收等級(Ranking of the recall)						       
						       //System.out.println("回收等級==="+Common.get(rs[1]));
						       obj.setRecycleclass(classMap.get(Common.get(rs[1])));
						       
                               //訊息(Source)						       
						       if(!"".equals(Common.get(rs[2]))){						    	   
						    	   String source = Common.get(rs[2].replaceAll("[\\r\\n]",""));
						    	   //System.out.println("訊息==="+Common.get(source));
						    	   if("不良品通報The reporting system".equals(source)){
						    		   obj.setMsgsource("01");
						    	   }						    	   
						    	   else if("後市場監測post market surveillance".equals(source)){
						    		   obj.setMsgsource("02");
						    	   }
						    	   else if("國外警訊".equals(source)){
						    		   obj.setMsgsource("03");
						    	   }
						    	   else if("廠商主動通報Voluntary report by the company".equals(source)
						    			   || "廠商主動通報Voluntary report by the company(塑化劑)".equals(source)){
						    		   obj.setMsgsource("04");
						    	   }
						    	   else{
						    		   obj.setMsgsource("90");
						    		   obj.setMsgsourcedesc(source);
						    	   }
						       }
						       
						       //許可證(Product license)
						       obj.setPermitKey(permitKey);
						       obj.setPermitNo(permitNo);
						       
						       //中文品名
						       //System.out.println("中文品名==="+Common.get(rs[4]));
						       obj.setChProduct(Common.get(rs[4]));						       
						       
						       //英文品名(Brand Name)
						       //System.out.println("英文品名==="+Common.get(rs[5]));
						       obj.setEnProduct(Common.get(rs[5]));						       
						       
						       //許可證持有廠商(Product license holder)
						       //System.out.println("許可證持有廠商==="+Common.get(rs[6]));
						       obj.setAppName(Common.get(rs[6]));						       
						       
						       //製造廠（Manufacturer）						       
						       //System.out.println("製造廠==="+Common.get(rs[7]));
						       obj.setManufactorName(Common.get(rs[7]));
						       
						       //批號（Lot Number）
						       //System.out.println("批號==="+Common.get(rs[8]));
						       obj.setOld_LotNumber(Common.get(rs[8]));
						       
						       //YN輸出國外(Export or not)
						       //System.out.println("輸出國外==="+Common.get(rs[9]));
						       if(!"".equals(Common.get(rs[9])) && Common.get(rs[9]).length()>=1){
						    	   if("Y".equals(Common.get(rs[9]).substring(0,1))){
						    		   obj.setIsabroad("Y");
						    		   if(Common.get(rs[9]).indexOf("(")!=-1 || Common.get(rs[9]).indexOf(")")!=-1)
		                               {		                            	 
						    			   //obj.setAbroadCountry(Common.get(rs[9]).substring(Common.get(rs[9]).indexOf("(")+1,Common.get(rs[9]).indexOf(")")));
		                               }
						    	   }else if("N".equals(Common.get(rs[9]).substring(0,1)) || "否".equals(Common.get(rs[9]).substring(0,1))){
						    		   obj.setIsabroad("N");
						    	   }
						       }
						      
						       //回收原因（Recall reason）
						       //System.out.println("回收原因==="+Common.get(rs[10]));
						       if(!"".equals(Common.get(rs[10]))){
						    	   obj.setApprecyclereason("90");
						    	   obj.setApprecyclersdesc(Common.get(rs[10]));
						       }
						       
						       //回收月份(Month to start the recall)
						       //System.out.println("回收月份==="+Common.get(rs[11]));
						       obj.setOld_startMonth(Common.get(rs[11]));
						       
						       //啟動回收日期
						       //System.out.println("啟動回收日期==="+Common.get(rs[12]));
						       obj.setOld_appRecDateS(Common.get(rs[12]));
						       
						       //廠商完成回收日期
						       //System.out.println("完成回收日期==="+Common.get(rs[13]));
						       if(!"".equals(Common.get(rs[13]))){
						    	   if(Common.get(rs[13]).indexOf("年")!=-1 && Common.get(rs[13]).indexOf("月")!=-1 && Common.get(rs[13]).indexOf("日")!=-1){
						    		   String yyy = Common.formatFrontZero(Common.get(rs[13]).substring(0,Common.get(rs[13]).indexOf("年")),3);
							    	   String mm = Common.formatFrontZero(Common.get(rs[13]).substring(Common.get(rs[13]).indexOf("年")+1,Common.get(rs[13]).indexOf("月")),2);
							    	   String dd = Common.formatFrontZero(Common.get(rs[13]).substring(Common.get(rs[13]).indexOf("月")+1,Common.get(rs[13]).indexOf("日")),2);
							    	   obj.setAppRecDate(yyy+mm+dd);
						    	   }
						       }
						       
						       //公布於網站(Publish on the website )
						       //System.out.println("公布於網站==="+Common.get(rs[14]));
						       if("Y".equals(Common.get(rs[14])) || "ok".equals(Common.get(rs[14]))){
						    	   obj.setOld_pubweb("Y");
						       }else{
						    	   obj.setOld_pubweb("N");
						       }
						       
						       //發布新聞(Issue press releases )
						       //System.out.println("發布新聞==="+Common.get(rs[15]));
						       if("Y".equals(Common.get(rs[15]))){
						    	   obj.setOld_pubweb("Y");
						       }else if("N".equals(Common.get(rs[15]))){
						    	   obj.setOld_pubweb("N");
						       }
						       
						       //副知風管組(Notice the GMP Inspectorate)
						       //System.out.println("副知風管組==="+Common.get(rs[16]));
						       if("Y".equals(Common.get(rs[16]))){
						    	   obj.setOld_noticeGMP("Y");
						       }else if("N".equals(Common.get(rs[16]))){
						    	   obj.setOld_noticeGMP("N");
						       }						       
						       
						       //所轄衛生局
						       //System.out.println("所轄衛生局==="+Common.get(rs[17]));
						       if(!"".equals(Common.get(rs[17]))){
						    	   String[] healthbureau = new String[2];									
						    	   healthbureau = Common.get(rs[17]).split(",");				
								   if(!"".equals(Common.get(healthbureau[0]))){
									   String healthbureau1 = View.getLookupField("select id from Con1003Db where unionName like "+Common.sqlChar(Common.get(healthbureau[0])+"%"));
									   obj.setHealthbureau1(healthbureau1);
								   }
								   if(healthbureau.length>1){
									   String healthbureau2 = View.getLookupField("select id from Con1003Db where unionName like "+Common.sqlChar(Common.get(healthbureau[1])+"%"));
									   obj.setHealthbureau2(healthbureau2);
								   }
						       }
						       
						       //衛生局聯絡人
						       //System.out.println("衛生局聯絡人==="+Common.get(rs[18]));
						       obj.setOld_healContact(Common.get(rs[18]));
						       
						       //回收品後續處理方式
						       //System.out.println("回收品後續處理方式==="+Common.get(rs[19]));
						       if(!"".equals(Common.get(rs[19]))){
						    	   obj.setCheckcyclestorage(reccsMap.get(Common.get(rs[19])));
						       }
						       
						       //是否裁罰
						       //System.out.println("是否裁罰==="+Common.get(rs[20]));
						       
						       //是否請辦風管組
						       //System.out.println("是否請辦風管組==="+Common.get(rs[21]));
						       if("Y".equals(Common.get(rs[21])) || "是".equals(Common.get(rs[21]))){						       
						    	   obj.setOld_isDoGMP("Y");
						       }
						       
						       //廠商主動/被動回收(Voluntary recall/TFDA start the recall)
						       //System.out.println("廠商主動/被動回收==="+Common.get(rs[22]));
						       obj.setOld_recall(Common.get(rs[22]));
						       
						       //回收啟動文號(FDA藥字)(The number of the official letter)
						       //System.out.println("回收啟動文號==="+Common.get(rs[23]));
						       obj.setOld_recallNumber(Common.get(rs[23]));
						       
						       //要求完成回收時間(The deadline that recall should be completed)
						       //System.out.println("要求完成回收時間==="+Common.get(rs[24]));
						       obj.setOld_recalldeadline(Common.get(rs[24]));
						       
						       //國外衛生主管機關警訊發布情形(Inform members in Rapid Alert Contact list or not)
						       //System.out.println("國外衛生主管機關警訊發布情形==="+Common.get(rs[25]));
						       if("Y".equals(Common.get(rs[25])) || "是".equals(Common.get(rs[25]))){						       
						    	   obj.setOld_informMembers("Y");
						       }else if("N".equals(Common.get(rs[25])) || "否".equals(Common.get(rs[25]))) {
						    	   obj.setOld_informMembers("N");
						       }
						      
						       obj.setStatus("90");
						       obj.setIsTransferData("Y");
						      
						       if(isUpdate)	 
						    	   updateList.add(obj);					       				      
						       else	 
						    	   saveList.add(obj);						       					 
						   }
						   else{
							   if(wrongString.length()>0) wrongString += ",";
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
					transMsg.append("<font color='#0000CC'>不更新筆數：</font><font color='red'>").append(wrong).append("</font><br>");
					transMsg.append("<font color='#0000CC'>不更新項次：</font><font color='red'>").append(wrongString).append("</font><br>");
					setMsg("匯入成功!");
					dir.delete();
				}
				
			}	
			
			
		}
		
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
