package com.kangdainfo.tcbw.view.trans;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.set.ListOrderedSet;



import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.XlsUtil;
import com.kangdainfo.common.util.filestore.ContentTypeConfiguration;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med7002Db;
import com.kangdainfo.tcbw.model.bo.Med7005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.util.ListUtil;




public class TRANSVMED extends SuperBean
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
			
					java.util.List<Med7001Db> saveList = new java.util.ArrayList<Med7001Db>();
					java.util.List<Med7001Db> updateList = new java.util.ArrayList<Med7001Db>();
					
					if (list!=null && list.size()>0) 
					{		
					   //publDept 發佈單位
					   java.util.Map<String,String>  publDeptMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='CONPUBLDEPT' ");
					   
					   //permitKey	許可證字
					   java.util.Map<String,String>  permitKeyMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='MEDPKID' ");
						
					   for(int i=0;i<list.size();i++)
					   {	
						   boolean isUpdate = true;
						   
						   String[] rs = list.get(i);

						   if(!"".equals(Common.get(rs[0])) && !"_".equals(Common.get(rs[0])))
						   {
						       Med7001Db obj = (Med7001Db)View.getObject("from Med7001Db where applNo="+Common.sqlChar(Common.get(rs[0])));
						   
						       if(obj==null)
						       {
							      obj = new Med7001Db();
							      isUpdate=false;
						       }	   
						   
						       Med7002Db obj7002 = (Med7002Db) ListUtil.getFirstObject(obj.getMed7002Dbs());
						   
						       if(obj7002==null)
						       {
							      obj7002 = new Med7002Db();
							      obj7002.setMed7001Db(obj);
						       }
	
						       //obj7002.setMed7001Db(obj);
						   
						   
						       java.util.Set dtlSet7002 = new ListOrderedSet();
						       java.util.Set dtlSet7005 = new ListOrderedSet();
						   
						   
                               Med7005Db obj7005 = (Med7005Db) ListUtil.getFirstObject(obj.getMed7005Dbs());
						   
						       if(obj7005==null)
						       {
							      obj7005 = new Med7005Db();
							      obj7005.setMed7001Db(obj);
						       }
						   
						       if(isUpdate)
						       {
							      obj.setModifier(getPeople());
							      obj.setModifyDate(Datetime.getYYYMMDD());
							      obj.setModifyTime(Datetime.getHHMMSS());
							      obj7002.setModifier(getPeople());
							      obj7002.setModifyDate(Datetime.getYYYMMDD());
							      obj7002.setModifyTime(Datetime.getHHMMSS());
							      obj7005.setModifier(getPeople());
							      obj7005.setModifyDate(Datetime.getYYYMMDD());
							      obj7005.setModifyTime(Datetime.getHHMMSS());
						       }
						       else
						       {
							      obj.setCreator(getPeople());
							      obj.setCreateDate(Datetime.getYYYMMDD());
							      obj.setCreateTime(Datetime.getHHMMSS());
							      obj7002.setCreator(getPeople());
							      obj7002.setCreateDate(Datetime.getYYYMMDD());
							      obj7002.setCreateTime(Datetime.getHHMMSS());
							      obj7005.setCreator(getPeople());
							      obj7005.setCreateDate(Datetime.getYYYMMDD());
							      obj7005.setCreateTime(Datetime.getHHMMSS());
						       }	
						   
						       obj.setTrans("Y");
						       obj.setStatus("90");
						       obj7002.setTrans("Y");
						       obj7005.setTrans("Y");
	
						       //EXCEL(A)  applNo varchar(11) 案件編號---NO  
						       if(!"_".equals(Common.get(rs[0])))
						         obj.setApplNo(Common.get(rs[0]));
						   
						       System.out.println("案件編號==="+Common.get(rs[0]));
						   
						       //EXCEL(B) publDept 發佈單位 varchar(2) CODEKIND=CONPUBLDEPT---Original Source
						       if(!"_".equals(Common.get(rs[1])))
						         obj.setPublDept(Common.get(publDeptMap.get(Common.get(rs[1]))));
						       System.out.println("發佈單位==="+Common.get(rs[1]));
						       System.out.println("發佈單位==="+obj.getPublDept());
						   
						       //EXCEL(C) situation 警訊類別varchar(2)CODEKIND=CONWARNING	--Recall/Alert/News
						       //01	警訊  02回收
						       String situationType=Common.get(rs[2]);
						       if("alert".equals(Common.get(situationType)))
							     obj.setSituation("01");
						       else  if("recall".equals(Common.get(situationType)))
							     obj.setSituation("02");
						   
						       System.out.println("警訊類別==="+Common.get(situationType));
						   
	
						       //EXCEL(D) medMainCategory醫材主類別varchar(10)-----Category
						       String medMainCategory=Common.get(rs[3]);
						       if(!"".equals(medMainCategory))
						       {
							     obj.setMedMainCategory(medMainCategory.substring(0,1));
						       }
						   
						       System.out.println("醫材主類別==="+Common.get(obj.getMedMainCategory()));
						   
						   
						       //EXCEL(E) chProduct	產品名稱 text---Trade Name/Product
						       if(!"_".equals(Common.get(rs[4])))
						         obj.setChProduct(Common.get(rs[4]));
                               System.out.println("產品名稱==="+Common.get(rs[4]));
						   
						       //EXCEL(F) recycleclass國外回收等級 varchar(2)	CODEKIND=MEDRCCLASS	--Class	
                               if(!"_".equals(Common.get(rs[5])) && !"".equals(Common.get(rs[5])))
                                  obj.setRecycleclass(Common.get("0"+rs[5]));
                               System.out.println("國外回收等級==="+Common.get(rs[5]));
						   
						       //EXCEL(G) dataRevDate資料收集日期 varchar(7)--Date Monitored
                               if(!"_".equals(Common.get(rs[6])))
                                 obj.setDataRevDate(Common.get(Datetime.getRocDateTransFromYYYYMMDD(rs[6])));
                               System.out.println("資料收集日期==="+Common.get(obj.getDataRevDate()));
						   
						       //EXCEL(H) publDate發佈日期	varchar(7)--Posted
                               if(!"_".equals(Common.get(rs[7])))
                                  obj.setPublDate(Common.get(Datetime.getRocDateTransFromYYYYMMDD(rs[7])));
                           
                               System.out.println("發佈日期==="+Common.get(obj.getPublDate()));
						   
						       //EXCEL(I) manufactorName	製造廠 text--Recalling Manufacturer
                               if(!"_".equals(Common.get(rs[8])))
                                 obj.setManufactorName(Common.get(rs[8]));
                               System.out.println("製造廠==="+Common.get(rs[8]));
                           
                           
						       //EXCEL(J) contextsummary	警訊摘要  text --Reason
                               if(!"_".equals(Common.get(rs[9])))
                                 obj.setContextsummary(Common.get(rs[9]));
                               System.out.println("警訊摘要==="+Common.get(rs[9]));
                           
    
						       //EXCEL(K) productlotNo 產品型號及批號	text--Code Information	
                               if(!"_".equals(Common.get(rs[10])))
                                 obj.setProductlotNo(Common.get(rs[10]));
                               System.out.println("產品型號及批號==="+Common.get(rs[10]).length());
						   
						       //EXCEL(L) applicationaction 廠商行動 text--Action	
                               if(!"_".equals(Common.get(rs[11])))
                                 obj.setApplicationaction(Common.get(rs[11]));
                               System.out.println("廠商行動==="+Common.get(rs[11]).length());


						       //EXCEL(M) effectarea	影響地區	text--Distribution	
                               if(!"_".equals(Common.get(rs[12])))
                                 obj.setEffectarea(Common.get(rs[12]));
                               System.out.println("影響地區==="+Common.get(rs[12]).length());
						   
						       //EXCEL(N) effectnum	影響數量	varchar(50)--Quantity in Commerce	
                               if(!"_".equals(Common.get(rs[13])))
                                 obj.setEffectnum(Common.get(rs[13]));
                               System.out.println("影響數量==="+Common.get(rs[13]).length());
						   
                           
                               //EXCEL(O) permitKey	許可證字	varchar(2)   國內許可證類別	rs[14]
                               String permitKeyStr=Common.get(rs[14]);
                               permitKeyStr=permitKeyStr.replace("字","");
                               obj7002.setPermitKey(Common.get(permitKeyMap.get(Common.get(permitKeyStr))));
                               System.out.println("許可證字==="+Common.get(obj7002.getPermitKey()));
                               
                               //EXCEL(P) permitNo	許可證號	varchar(14)  國內許可證號碼	rs[15]
                               String permitNoStr=Common.get(rs[15]);
                               if(permitNoStr.indexOf("第")!=-1 && permitNoStr.indexOf("號")!=-1)
                               {
                            	 obj7002.setPermitNo(permitNoStr.substring(permitNoStr.indexOf("第")+1,permitNoStr.indexOf("號")));
                               }
                           
                               System.out.println("許可證號==="+Common.get(obj7002.getPermitNo()));
                              
                             
                               //EXCEL(Q) manufactorName	製造商/製造廠  text  國內廠商	rs[16]
                               if(!"_".equals(Common.get(rs[16])))
                                 obj.setManufactorName(Common.get(rs[16]));
                               System.out.println("製造商/製造廠 ==="+Common.get(obj.getManufactorName()));
                           
                               //EXCEL(R) 不良反應通報紀錄 rs[17]text  加開此欄位(僅限顯示)
                               if(!"_".equals(Common.get(rs[17])))
                                 obj7002.setAdverseRecord(Common.get(rs[17]));
                               System.out.println("不良反應通報紀錄 ==="+Common.get(rs[17]));

                               //EXCEL(S) 不良品通報紀錄 rs[18] text 加開此欄位(僅限顯示)
                               if(!"_".equals(Common.get(rs[18])))
                                 obj7002.setDefRecord(Common.get(rs[18]));
                               System.out.println("不良品通報紀錄 ==="+Common.get(rs[18]));
                           
                               //EXCEL(T) istranslate	是否摘譯	varchar	(1)--	是否摘譯
                               if(!"_".equals(Common.get(rs[19])) && !"".equals(Common.get(rs[19])))
                                 obj.setIstranslate(Common.get("Y"));
                               else
                                 obj.setIstranslate(Common.get(""));
                               
                               System.out.println("是否摘譯 ==="+Common.get(obj.getIstranslate()));
						   
						   
						       //EXCEL(U) translateman	摘譯人員	nvarchar	(300)--	摘譯人員
                               if(!"_".equals(Common.get(rs[20])))
                                 obj.setTranslateman(Common.get(rs[20]));
                               System.out.println("摘譯人員 ==="+Common.get(rs[20]));
                           
                           
                              //EXCEL(V) incountrysituation	國內情形 text--	廠商確認譯文及國內情形
                              if(!"_".equals(Common.get(rs[21])))
                                obj.setIncountrysituation(Common.get(rs[21]));
                           
                              //EXCEL(W)  replysummary 回覆摘要	text---廠商確認譯文及國內情形
                              if(!"_".equals(Common.get(rs[21])))
                                obj7002.setReplysummary(Common.get(rs[21]));
               
                              System.out.println("國內情形==回覆摘要==="+Common.get(rs[21]));
             
                              //加開此欄位 --通知廠商/確認日期	通知日期 rs[22]
                               System.out.println("通知廠商/確認日期==="+Common.get(Common.get(rs[22])));
                           
                           
                              //EXCEL(X)  publdate公告日期 varchar(7)----網站公告日期
                              if(!"_".equals(Common.get(rs[23])))
                                 obj7005.setPubldate(Common.get(Common.get(Datetime.getRocDateTransFromYYYYMMDD(rs[23]))));
                              System.out.println("發佈日期==="+Common.get(Common.get(Datetime.getRocDateTransFromYYYYMMDD(rs[23]))));
                           
                           
                              //EXCEL(Y)  加開此欄位  	ADR公告網址
                              if(!"_".equals(Common.get(rs[24])))
                        	     obj7005.setAdrweb(Common.get(rs[24]));
                              System.out.println("ADR公告網址==="+Common.get(rs[24]));
                           
						      //EXCEL(Z)  datasourWebSite 原始網頁	text--原始網頁
                              if(!"_".equals(Common.get(rs[25])))
                                 obj.setDatasourWebSite(Common.get(rs[25]));
                              System.out.println("原始網頁==="+Common.get(rs[25]));


                              dtlSet7002.add(obj7002);		
						      obj.setMed7002Dbs(dtlSet7002);
						      dtlSet7005.add(obj7005);		
						      obj.setMed7005Dbs(dtlSet7005);

						      if(isUpdate)
						      {
							      updateList.add(obj);
						      }
						      else
						      {
							     saveList.add(obj);
						      }	   
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
