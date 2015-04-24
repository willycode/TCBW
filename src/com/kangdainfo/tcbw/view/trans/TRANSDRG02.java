package com.kangdainfo.tcbw.view.trans;

import java.io.File;

import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.XlsUtil;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;
import com.kangdainfo.tcbw.model.bo.Drg6003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;





public class TRANSDRG02 extends SuperBean
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
			
					java.util.List<Drg4001Db> saveDrg4001List = new java.util.ArrayList<Drg4001Db>();
					java.util.List<Drg4001Db> updateDrg4001List = new java.util.ArrayList<Drg4001Db>();
					java.util.List<Drg6001Db> saveDrg6001List = new java.util.ArrayList<Drg6001Db>();
					java.util.List<Drg6001Db> updateDrg6001List = new java.util.ArrayList<Drg6001Db>();
					java.util.List<Drg4004Db> saveDrg4004List = new java.util.ArrayList<Drg4004Db>();
					String wrongString = "";
					int wrong =0;
					if (list!=null && list.size()>0) 
					{					   
					   //permitKey	許可證字
					   java.util.Map<String,String>  permitKeyMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGPKID' ");
					   //後續處置 DRG0301
					   java.util.Map<String,String>  dealWithMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRG0301' ");
					   //通報來源 DRGNFS
					   java.util.Map<String,String>  nfsMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='DRGNFS' ");
					   
					   for(int i=0;i<list.size();i++)
					   {						   
						   String[] rs = list.get(i);						  
					       
						   // 檢核通報日期為數值或者為作廢字樣						
						   boolean checkDate = true;						
						   try {								
							   if(Double.isNaN(Double.parseDouble(Common.get(Datetime.getRocDateTransFromYYYYMMDD(Common.get(rs[1])))))) checkDate = false;							
						   } catch (NumberFormatException e){								
							   checkDate = false;							
						   }					 
						
						   int permitNolen = Common.getInt(Common.get(rs[3]).length()) - Common.getInt(Common.get(rs[3]).replace("號", "").length());
						   
						   //excel案件編號大於10不轉 && 通報日期不為日期格式不轉  && 許可證欄位出現2個以上的"號"
						   if(Common.get(rs[0]).length()<=10 && checkDate && permitNolen <= 1)
						   {
							   boolean isUpdateIn = true;
							   boolean isUpdateOut = true;						   
							   java.util.Set dtlSet4003 = new ListOrderedSet();
							   java.util.Set dtlSet6003 = new ListOrderedSet();
							   
							   //內部資料
							   Drg4001Db objIn = (Drg4001Db)View.getObject("from Drg4001Db where trans='Y' and old_CaseNoD="+Common.sqlChar(Common.get(rs[13])));						   
						       if(objIn==null)
						       {   	   
						    	   objIn = new Drg4001Db();							      
						    	   isUpdateIn=false;
						       }
						       //外部資料
						       Drg6001Db objOut = (Drg6001Db)View.getObject("from Drg6001Db where trans='Y' and old_CaseNoD="+Common.sqlChar(Common.get(rs[13])));						   
						       if(objOut==null)
						       {   	   
						    	   objOut = new Drg6001Db();							      
						    	   isUpdateOut=false;
						       }
						       //內部資料4004
							   Drg4004Db drg04 = new Drg4004Db();					       

						       //案件編號_Excel
						       objIn.setApplNo(Common.get(rs[0]));
						       objOut.setApplNo(Common.get(rs[0]));						       
						       objIn.setOld_CaseNoE(Common.get(rs[0]));
						       objOut.setOld_CaseNoE(Common.get(rs[0]));
						       drg04.setApplNo(Common.get(rs[0]));
						       drg04.setOld_CaseNoE(Common.get(rs[0]));						       
						       
						       //通報日期	DRG4001_DB.notifierRepDa
						       objIn.setNotifierRepDate(Common.get(Datetime.getRocDateTransFromYYYYMMDD(Common.get(rs[1]))));
						       objOut.setNotifierRepDate(Common.get(Datetime.getRocDateTransFromYYYYMMDD(Common.get(rs[1]))));						       
						       
						       //藥品成分rs[2],轉換前商品名rs[3],轉換後商品名rs[4],批號rs[11]
						       if(!"".equals(Common.get(2)) || !"".equals(Common.get(3)) || !"".equals(Common.get(4)) || !"".equals(Common.get(11))){						    	   
						    	   Drg4003Db dtl01In = (Drg4003Db)View.getObject("from Drg4003Db where medType='01' and old_CaseNoD="+Common.sqlChar(Common.get(rs[13])));
						    	   if(dtl01In == null){
						    		   dtl01In = new Drg4003Db();
						    	   }
						    	   Drg6003Db dtl01Out = (Drg6003Db)View.getObject("from Drg6003Db where medType='01' and old_CaseNoD="+Common.sqlChar(Common.get(rs[13])));
						    	   if(dtl01Out == null){
						    		   dtl01Out = new Drg6003Db();
						    	   }
						    	   Drg4003Db dtl02In = (Drg4003Db)View.getObject("from Drg4003Db where medType='02' and old_CaseNoD="+Common.sqlChar(Common.get(rs[13])));
						    	   if(dtl02In == null){
						    		   dtl02In = new Drg4003Db();
						    	   }						    	   
						    	   Drg6003Db dtl02Out = (Drg6003Db)View.getObject("from Drg6003Db where medType='02' and old_CaseNoD="+Common.sqlChar(Common.get(rs[13])));
						    	   if(dtl02Out == null){
						    		   dtl02Out = new Drg6003Db();
						    	   }
						    	   //藥品成分DRG4003_DB.scientificName_02
						    	   dtl02In.setScientificName(Common.get(rs[2]));
						    	   dtl02Out.setScientificName(Common.get(rs[2]));
						    	   
						    	   //轉換前商品名 Drg4003_db.productName_01；Drg4003_db.permitKey_01；Drg4003_db.permitNo_01
						    	   if(!"".equals(Common.get(rs[3]))){
						    		   //商品名:取衛字前的資料
						    		   if(Common.get(rs[3]).indexOf("衛")!=-1){
						    			   dtl01In.setProductName(Common.get(rs[3]).substring(0,Common.get(rs[3]).indexOf("衛")));
						    			   dtl01Out.setProductName(Common.get(rs[3]).substring(0,Common.get(rs[3]).indexOf("衛")));
						    		   }
						    		   //許可證字
						    		   if(Common.get(rs[3]).indexOf("衛")!=-1 && Common.get(rs[3]).indexOf("字")!=-1)
		                               {						    		   
						    			   //System.out.println("PermitKey=="+Common.get(permitKeyMap.get(Common.get(rs[3]).substring(Common.get(rs[3]).indexOf("衛"), Common.get(rs[3]).indexOf("字")))));
						    			   dtl01In.setPermitKey(Common.get(permitKeyMap.get(Common.get(rs[3]).substring(Common.get(rs[3]).indexOf("衛"), Common.get(rs[3]).indexOf("字")))));
						    			   dtl01Out.setPermitKey(Common.get(permitKeyMap.get(Common.get(rs[3]).substring(Common.get(rs[3]).indexOf("衛"), Common.get(rs[3]).indexOf("字")))));
		                               }
						    		   //許可證號
						    		   if(Common.get(rs[3]).indexOf("第")!=-1 && Common.get(rs[3]).indexOf("號")!=-1)
		                               {
						    			   //System.out.println("xxx=="+Common.get(Common.get(rs[3])));
						    			   dtl01In.setPermitNo(Common.get(rs[3]).substring(Common.get(rs[3]).indexOf("第")+1, Common.get(rs[3]).indexOf("號")));
						    			   dtl01Out.setPermitNo(Common.get(rs[3]).substring(Common.get(rs[3]).indexOf("第")+1, Common.get(rs[3]).indexOf("號")));
		                               }
						    	   }
						    	   
						    	   //轉換後商品名 Drg4003_db.productName_02；Drg4003_db.permitKey_02；Drg4003_db.permitNo_02
						    	   if(!"".equals(Common.get(rs[4]))){
						    		   //商品名:取衛字前的資料
						    		   if(Common.get(rs[4]).indexOf("衛")!=-1){
						    			   dtl02In.setProductName(Common.get(rs[4]).substring(0,Common.get(rs[4]).indexOf("衛")));
						    			   dtl02Out.setProductName(Common.get(rs[4]).substring(0,Common.get(rs[4]).indexOf("衛")));
						    		   }
						    		   //許可證字
						    		   if(Common.get(rs[4]).indexOf("衛")!=-1 && Common.get(rs[4]).indexOf("字")!=-1)
		                               {
						    			   dtl02In.setPermitKey(Common.get(permitKeyMap.get(Common.get(rs[4]).substring(Common.get(rs[4]).indexOf("衛"), Common.get(rs[4]).indexOf("字")))));
						    			   dtl02Out.setPermitKey(Common.get(permitKeyMap.get(Common.get(rs[4]).substring(Common.get(rs[4]).indexOf("衛"), Common.get(rs[4]).indexOf("字")))));
		                               }
						    		   //許可證號
						    		   if(Common.get(rs[4]).indexOf("第")!=-1 && Common.get(rs[4]).indexOf("號")!=-1)
		                               {
						    			   dtl02In.setPermitNo(Common.get(rs[4]).substring(Common.get(rs[4]).indexOf("第")+1, Common.get(rs[4]).indexOf("號")));
						    			   dtl02Out.setPermitNo(Common.get(rs[4]).substring(Common.get(rs[4]).indexOf("第")+1, Common.get(rs[4]).indexOf("號")));
		                               }
						    	   }
						    	   
						    	   //批號
						    	   dtl02In.setManufactorNo(Common.get(rs[11]));
						    	   dtl02Out.setManufactorNo(Common.get(rs[11]));
						    	   
						    	   dtl01In.setTrans("Y");
						    	   dtl02In.setTrans("Y");
						    	   dtl01Out.setTrans("Y");
						    	   dtl02Out.setTrans("Y");
						    	   dtlSet4003.add(dtl01In);
						    	   dtlSet4003.add(dtl02In);
						    	   dtlSet6003.add(dtl01Out);
						    	   dtlSet6003.add(dtl02Out);
						       }

						       //案件分類	Drg4001_db.conSequence；DRG4001_DB.effectChangeDesc；DRG4001_DB.badReactionDesc
						       if(!"".equals(Common.get(rs[5]))){
						    	   boolean type01 = false; //藥效改變
						    	   boolean type02 = false; //不良反應					    	   
						    	   if(Common.get(rs[5]).indexOf("療效減弱")!=-1){
						    		   type01 = true;
						    		   objIn.setEffectChangeDesc("2");
						    		   objOut.setEffectChangeDesc("2");
						    		   drg04.setEffectChangeDesc("2");
						    	   }
						    	   if(Common.get(rs[5]).indexOf("療效增強")!=-1){
						    		   type01 = true;
						    		   objIn.setEffectChangeDesc("1");
						    		   objOut.setEffectChangeDesc("1");
						    		   drg04.setEffectChangeDesc("1");
						    	   }
						    	   if(Common.get(rs[5]).indexOf("不良反應")!=-1){
						    		   type02 = true;
						    		   objIn.setBadReactionDesc(Common.get(rs[5]).substring(Common.get(rs[5]).indexOf("(")+1,Common.get(rs[5]).length()-1));
						    		   objOut.setBadReactionDesc(Common.get(rs[5]).substring(Common.get(rs[5]).indexOf("(")+1,Common.get(rs[5]).length()-1));
						    		   drg04.setBadReactionDesc(Common.get(rs[5]).substring(Common.get(rs[5]).indexOf("(")+1,Common.get(rs[5]).length()-1));
						    	   }
						    	   if(type01 && type02){
						    		   objIn.setConSequence("1,2");
						    		   objOut.setConSequence("1,2");
						    		   drg04.setConSequence("1,2");
						    	   }else if(type01){
						    		   objIn.setConSequence("1");
						    		   objOut.setConSequence("1");
						    		   drg04.setConSequence("1");
						    	   }else if(type02){
						    		   objIn.setConSequence("2");
						    		   objOut.setConSequence("2");
						    		   drg04.setConSequence("2");
						    	   }						    	   
						       }

						       //後續處置	Drg4001_db.deal_with
						       String dealWith = Common.get(dealWithMap.get(Common.get(rs[6])));
						       if("".equals(Common.get(dealWith)) || "ZZ".equals(Common.get(dealWith))){
						    	   objIn.setDealWith("ZZ");
						    	   objOut.setDealWith("ZZ");
						    	   objIn.setDealWithOther(Common.get(rs[6]));
						    	   objOut.setDealWithOther(Common.get(rs[6]));
						       }else{
						    	   objIn.setDealWith(dealWith);
						    	   objOut.setDealWith(dealWith);
						       }

						       //通報機構	Drg4001_db.OLD_notifierDept
						       objIn.setOld_notifierDept(Common.get(rs[7]));
						       objOut.setOld_notifierDept(Common.get(rs[7]));

						       //案例來源	Drg4001_db.notifierSource
						       objIn.setNotifierSource(Common.get(nfsMap.get(Common.get(rs[8]))));
						       objOut.setNotifierSource(Common.get(nfsMap.get(Common.get(rs[8]))));

						       //中心初評	DRG4004_DB.assessResult
						       String assessResult = View.getLookupField("select codeId from CommonCode where commonCodeKind.codeKindId='DRGNFS' and codeName like"+Common.sqlChar("%"+rs[9]+"%"));
                               drg04.setAssessResult(assessResult);
						       
						       //備註	DRG4004_DB.remark
                               drg04.setRemark(Common.get(rs[10]));
                               
						       //資料來源	drg4001_db.OLD_dataSource
                               objIn.setOld_dataSource(Common.get(rs[12]));
                               objOut.setOld_dataSource(Common.get(rs[12]));
						      
						       objIn.setStatus("90");
						       objIn.setTrans("Y");
						       objOut.setStatus("90");
						       objOut.setTrans("Y");						       
						       drg04.setTrans("Y");
						       
						       if(isUpdateIn){ 
						    	   updateDrg4001List.add(objIn);					       				      
						       }else{ 
						    	   saveDrg4001List.add(objIn);
						       }
						       saveDrg4004List.add(drg04);
						       
						       if(isUpdateOut){	 
						    	   updateDrg6001List.add(objOut);
						       }else{ 
						    	   saveDrg6001List.add(objOut);
						       }
						   }else{
							   wrongString += "<br>";
							   wrongString += (i+2);
							   wrong ++;
						   }
					   }					   
					}
					
					if (updateDrg4001List!=null && updateDrg4001List.size()>0) 
						ServiceGetter.getInstance().getTcbwService().updateBatch(updateDrg4001List);					
					if (saveDrg4001List!=null && saveDrg4001List.size()>0) 
						ServiceGetter.getInstance().getTcbwService().saveBatch(saveDrg4001List);
					if (updateDrg6001List!=null && updateDrg6001List.size()>0) 
						ServiceGetter.getInstance().getTcbwService().updateBatch(updateDrg6001List);					
					if (saveDrg6001List!=null && saveDrg6001List.size()>0) 
						ServiceGetter.getInstance().getTcbwService().saveBatch(saveDrg6001List);
					if (saveDrg4004List!=null && saveDrg4004List.size()>0) 
						ServiceGetter.getInstance().getTcbwService().saveBatch(saveDrg4004List);
					
					this.transMsg = new StringBuilder();
					
					transMsg.append("<font color='#0000CC'>匯入成功</font>").append("<br>");
					transMsg.append("<font color='#0000CC'>新增4001筆數：</font><font color='red'>").append(saveDrg4001List.size()).append("</font><br>");
					transMsg.append("<font color='#0000CC'>新增6001筆數：</font><font color='red'>").append(saveDrg6001List.size()).append("</font><br>");
					transMsg.append("<font color='#0000CC'>更新4001筆數：</font><font color='red'>").append(updateDrg4001List.size()).append("</font><br>");
					transMsg.append("<font color='#0000CC'>更新6001筆數：</font><font color='red'>").append(updateDrg6001List.size()).append("</font><br>");
					transMsg.append("<font color='#0000CC'>新增4004筆數：</font><font color='red'>").append(saveDrg4004List.size()).append("</font><br>");
					transMsg.append("<font color='#0000CC'>不匯入筆數：</font><font color='red'>").append(wrong).append("</font><br>");
					transMsg.append("<font color='#0000CC'>不匯入項次：</font><font color='red'>").append(wrongString).append("</font><br>");
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
