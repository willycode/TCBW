package com.kangdainfo.tcbw.view.medin;



import java.util.List;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0003Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0007Db;
import com.kangdainfo.tcbw.model.bo.Med1002Db;
import com.kangdainfo.tcbw.model.bo.Med1003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;



public class MEDIN0402F extends MEDIN0101F
{
	
	private String id2;//序號	NUMERIC(19,0)
	private String bulletinQuality;//通報品質
	private String instructionSheet;//仿單
	private String documentRecords;//文獻紀錄
	private String documentNum;//文獻紀錄-件數
	private String[] policeQuery;//警訊查詢
	private String policeFdaNum;//警訊查詢-FDA件數
	private String policeMhraNum;//警訊查詢-MHRA件數
	private String policeEcriNum;//警訊查詢-ECRI件數
	private String medicalIssues;//病人問題代碼
	private String patientIssues;//醫材問題代碼
	private String med1005DbTypeCode[];
	private String med1005DbTypeId[];
	private String med1006_0403DbTypeCode[];
	private String med1006_0403DbTypeId[];
	    
	private String assessAdverseReactions;//評估不良反應
	private String adverseReactionsResult;//評估不良反應結果
	private String adverseTotal;//總分
	private String eventSeverity;//事件嚴重程度
	private String commentOpinion;//初評意見
	private String commentExplanation1;//初評意見說明1
	private String commentExplanation2;//初評意見說明2
	private String otherOpinion;//其他意見
	    
	private String ncarOptions;//Ncar通報篩選
	private String ncarResult;//Ncar通報篩選結果
	private String ncarTotal;//總分
	private String resultsNotification;//結果通知

	private String eventDetails;//需在取得事件詳細說明
	
	private String changeTabValue;//
	
	private String statDateS;//統計區間起
	private String statDateE;//統計區間迄
	private String statEventKind1;//統計不良反應通報件數
	private String statEventKind2;//統計不良品通報件數
	
	private String isCloseUserInfo;		//是否遮蔽個資
	public String getIsCloseUserInfo() {return checkGet(isCloseUserInfo);}
	public void setIsCloseUserInfo(String isCloseUserInfo) {this.isCloseUserInfo = checkSet(isCloseUserInfo);}
	
	//由後端取得病人問題代碼塞回前端
	public String getMed1005DbItemSet(String id2) throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		String hql = "select medicalIssues from Med0007Db where 1=1";
		if(!"".equals(id2) && id2.length() > 0) {
			hql += " and id = " + Common.getLong(id2);
		} else {
			hql += " and id = (select max(id) from Med0007Db where med0001Db.id = " + getId() + ")";
		}
		
		String[] med1005DbTypeCode = View.getLookupField(hql).split(",");
		String[] med1005DbTypeName = new String[med1005DbTypeCode.length];	
		if(med1005DbTypeCode!=null && med1005DbTypeCode.length>0)
		{
			for(int i = 0; i < med1005DbTypeCode.length; i++) {
				med1005DbTypeName[i] = View.getOneCodeName(med1005DbTypeCode[i],1);
				
				sb.append("addMed1005Db('").append(i).append("'");
				sb.append(",'").append(med1005DbTypeCode[i]).append("'");
				sb.append(",'").append(med1005DbTypeName[i]).append("');\n");
			}
		}
		return sb.toString(); 
	}
	
	//由後端取得醫材問題代碼塞回前端
	public String getMed1006DbItemSet(String id2) throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		String hql = "select patientIssues from Med0007Db where 1=1";
		if(!"".equals(id2) && id2.length() > 0) {
			hql += " and id = " + Common.getLong(id2);
		} else {
			hql += " and id = (select max(id) from Med0007Db where med0001Db.id = " + getId() + ")";
		}
		String[] med1006DbTypeCode = View.getLookupField(hql).split(",");
		String[] med1006DbTypeName = new String[med1006DbTypeCode.length];	
		if(med1006DbTypeCode!=null && med1006DbTypeCode.length>0)
		{
			for(int i = 0; i < med1006DbTypeCode.length; i++) {
				med1006DbTypeName[i] = View.getOneCodeName(med1006DbTypeCode[i],2);
				
				sb.append("addMed1006_0403Db('").append(i).append("'");
				sb.append(",'").append(med1006DbTypeCode[i]).append("'");
				sb.append(",'").append(med1006DbTypeName[i]).append("');\n");
			}
		}
		return sb.toString(); 
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		

		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med0007Db  where med0001Db.id="+getId();

		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		
		System.out.println("[TCBW]-[MEDIN0402F]-[QUERYALL] : " + hql);
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				java.util.Map<String, String> ntqlMap = TCBWCommon.getCommonCodeMap("MEDNTQL");
				
				for(Object dtlObj : objList) 
				{				
					Med0007Db dtl = (Med0007Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());
					setApplNo(Common.get(dtl.getMed0001Db().getApplNo()));
					rowArray[1] = Common.get(dtl.getEvaluationDate());
					rowArray[2] = Common.get(ntqlMap.get(dtl.getBulletinQuality()));
					if("1".equals(Common.get(dtl.getAdverseReactionsResult()))) {
						rowArray[3] = "存疑";
					} else if ("2".equals(Common.get(dtl.getAdverseReactionsResult()))) {
						rowArray[3] = "可能相關";
					} else if ("3".equals(Common.get(dtl.getAdverseReactionsResult()))) {
						rowArray[3] = "極有可能相關";
					} else if ("4".equals(Common.get(dtl.getAdverseReactionsResult()))) {
						rowArray[3] = "確定相關";
					} else {
						rowArray[3] = "";
					}
					
					String ncarResult="";
					if("1".equals(dtl.getNcarResult()))
					{
						ncarResult="通報NCAR";
					}
					if("2".equals(dtl.getNcarResult()))
					{
						ncarResult="不通報NCAR";
					} 
							
					rowArray[4] = Common.get(ncarResult);
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	
	@Override
	public Object doQueryOne() throws Exception 
	{
		MEDIN0402F obj = this;

		Med0007Db c =null;
				
		Med0001Db med0001db = (Med0001Db)View.getObject(" from Med0001Db where applNo="+Common.sqlChar(getApplNo()));
		if(getId2() != null && !"".equals(getId2()))
		  c = (Med0007Db)View.getObject(" from Med0007Db where id = " + Common.getLong(getId2()));
		//else if(getApplNo() != null && !"".equals(getApplNo()))
		//  c = (Med0007Db)View.getObject(" from Med0007Db where med0001Db.applNo="+Common.sqlChar(getApplNo()));
		else
		    c = (Med0007Db)View.getObject(" from Med0007Db where med0001Db.id="+Common.sqlChar(getId())+"order by id desc");
		
		System.out.println("[TCBW]-[MEDIN0402F]-[QueryOne] : " + c);
		
//		String hql = " select a.publDept,count(*) as c from Med7001Db a, Med7002Db b where 1=1";
//			   hql+= " and a.id = b.med7001Db.id";
//			   hql+= " and b.permitKey = '05'"; //+ Common.sqlChar(getMedPermit());
//			   hql+= " and b.permitNo = '000001'"; //+ Common.sqlChar(getMedPermitNumber());
//			   hql+= " and a.publDept in ('08','11')";
//			   hql+= " group by a.publDept";
//			   hql+= " order by a.publDept";
//			   
//		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);//計算警訊查詢FDA與MHRA的件數

		if(c!=null)
		{
			obj.setId2(Common.get(c.getId()));
		    obj.setBulletinQuality(c.getBulletinQuality());//通報品質
			obj.setInstructionSheet(c.getInstructionSheet());//仿單
			obj.setDocumentRecords(c.getDocumentRecords());//文獻紀錄
			obj.setDocumentNum(c.getDocumentNum());//文獻紀錄-件數
			String[] policeQuery = null;
			
			if(c.getPoliceQuery() != null) {
				policeQuery = c.getPoliceQuery().split(",");
			}
			obj.setPoliceQuery(policeQuery);//警訊查詢
			obj.setPoliceFdaNum(c.getPoliceFdaNum());//警訊查詢-FDA件數
			obj.setPoliceMhraNum(c.getPoliceMhraNum());//警訊查詢-MHRA件數
			obj.setPoliceEcriNum(c.getPoliceEcriNum());//警訊查詢-ECRI件數
			
			obj.setAssessAdverseReactions(c.getAssessAdverseReactions());//評估不良反應
			obj.setResultsNotification(c.getResultsNotification());//結果通知
			obj.setAdverseTotal(c.getAdverseTotal());
			
			obj.setAdverseReactionsResult(c.getAdverseReactionsResult());//評估不良反應結果
			obj.setEventSeverity(c.getEventSeverity());//事件嚴重程度
			obj.setCommentOpinion(c.getCommentOpinion());//初評意見
			obj.setCommentExplanation1(c.getCommentExplanation1());//初評意見說明1
			obj.setCommentExplanation2(c.getCommentExplanation2());//初評意見說明2
			obj.setOtherOpinion(c.getOtherOpinion());//其他意見
			
			obj.setNcarOptions(c.getNcarOptions());//Ncar通報篩選
			obj.setNcarResult(c.getNcarResult());//Ncar通報篩選結果
			obj.setNcarTotal(c.getNcarTotal());//Ncar通報總分
			obj.setResultsNotification(c.getResultsNotification());//結果通知
			obj.setEventDetails(c.getEventDetails());//需在取得事件詳細說明
			
//			obj.setMedicalIssues(c.getMedicalIssues());//病人問題代碼
//			obj.setPatientIssues(c.getPatientIssues());//醫材問題代碼
		}
//		} else {
//			if(null != objList && objList.size() > 0) {
//				String pq = "";
//				for(int i = 0; i < objList.size(); i++) {
//					Object numbers[] = (Object[])objList.get(i);
////					String[] numbers = (String[]) objList.get(i);
//					if("08".equals(Common.get(numbers[0]))) {
//						pq+="08";
//						obj.setPoliceFdaNum(Common.get(numbers[1]));//警訊查詢-FDA件數
//					} else if("11".equals(Common.get(numbers[0]))) {
//						pq+="11";
//						obj.setPoliceMhraNum(Common.get(numbers[1]));//警訊查詢-MHRA件數
//					} else if("未定code".equals(Common.get(numbers[0]))) {
//						//policeQuery+="??";
//						obj.setPoliceMhraNum(Common.get(numbers[1]));//警訊查詢-ECRI件數
//					}
//					if(i < objList.size() - 1) {
//						pq+=",";
//					}
//				}
//				String[] policeQuery = pq.split(",");
//				obj.setPoliceQuery(policeQuery);
//			}
//		}
		//顯示統計區間
		if(getId() != null && !"".equals(getId())){
			Med0001Db med0001 = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.sqlChar(getId()));
			if(med0001 != null){
				obj.setStatDateE(med0001.getNotifierRevDate());
				if(med0001.getNotifierRevDate() != null && !"".equals(med0001.getNotifierRevDate())){
					int diffYear = Common.getInt(View.getLookupField("select field1 from Sys0001Db where id = 4"));
					obj.setStatDateS(Datetime.getDateAdd("y", -diffYear, med0001.getNotifierRevDate()));
				}
				obj.setStatEventKind1(Common.get(ServiceGetter.getInstance().getTcbwService().getMedin2Dao().getStatEventKind1(
						med0001.getMedPermit(), med0001.getMedPermitNumber(), med0001.getMedCname(), obj.getStatDateS(), obj.getStatDateE())));
				obj.setStatEventKind2(Common.get(ServiceGetter.getInstance().getTcbwService().getMedin2Dao().getStatEventKind2(
						med0001.getMedPermit(), med0001.getMedPermitNumber(), med0001.getMedCname(), obj.getStatDateS(), obj.getStatDateE())));
			}
		}
		
		return obj;
	}
	
	public void doUpdateType0402() throws Exception 
	{
		
		String hql=" from Med0007Db where ";
		       hql+=" id=(select max(id) from  Med0007Db ";
		       hql+=" where  med0001Db.id = " + Common.getLong(getId());
		       hql+=" ) order by id desc" ;
		
		Med0007Db obj = (Med0007Db)View.getObject(hql);
		
		System.out.println("[TCBW]-[MEDIN0402F]-[doUpdateType0402] : "+hql);
		
		if(obj!=null)
		{	
			
		   if("".equals(Common.get(obj.getCreator())))
		   {
			  obj.setCreator(getEditID());
			  obj.setCreateDate(getEditDate());
			  obj.setCreateTime(getEditTime());
		   }	  
		  
		  obj.setBulletinQuality(getBulletinQuality());//通報品質
		  obj.setInstructionSheet(getInstructionSheet());//仿單
		  obj.setDocumentRecords(getDocumentRecords());//文獻紀錄
		  obj.setDocumentNum(getDocumentNum());//文獻紀錄-件數
		  String policeQuery = "";
		  if(null!=getPoliceQuery() && getPoliceQuery().length > 0) {
			  for(int i = 0; i < getPoliceQuery().length; i++) {
				  policeQuery += getPoliceQuery()[i];
				  if(i < getPoliceQuery().length - 1) {
					  policeQuery+=",";
				  }
			  }
			  
		  }
		//obj.setPoliceQuery(policeQuery);//警訊查詢
			
		  obj.setPoliceQuery(policeQuery);//警訊查詢
		  obj.setPoliceFdaNum(getPoliceFdaNum());//警訊查詢-FDA件數
		  obj.setPoliceMhraNum(getPoliceMhraNum());//警訊查詢-MHRA件數
		  obj.setPoliceEcriNum(getPoliceEcriNum());//警訊查詢-ECRI件數
		  
		  String med1005DbTypeCode = "";
		  if(getMed1005DbTypeId() != null) {
			  for(int i = 0; i < getMed1005DbTypeId().length; i++) {
//				  System.out.println("ID: " + getMed1005DbTypeId()[i]);
//				  System.out.println("CODE: " + getMed1005DbTypeCode()[i]);
//				  System.out.println("NAME: " + getMedicalIssuesName()[i]);

				  med1005DbTypeCode+= getMed1005DbTypeCode()[i];
				  if(i != getMed1005DbTypeId().length-1) {
					  med1005DbTypeCode+=",";
				  }
			  }
		  }
		  obj.setMedicalIssues(med1005DbTypeCode);//病人問題代碼
		  
		  String med1006DbTypeCode = "";
		  if(getMed1006_0403DbTypeId() != null) {
			  for(int i = 0; i < getMed1006_0403DbTypeId().length; i++) {
				  med1006DbTypeCode+= getMed1006_0403DbTypeCode()[i];
				  if(i != getMed1006_0403DbTypeId().length-1) {
					  med1006DbTypeCode+=",";
				  }
			  }
		  }
		  obj.setPatientIssues(med1006DbTypeCode);//醫材問題代碼
		  
//		  obj.setMedicalIssues(getMedicalIssues());//病人問題代碼
//		  obj.setPatientIssues(getPatientIssues());//醫材問題代碼
		  obj.setAssessAdverseReactions(getAssessAdverseReactions());//評估不良反應
		  obj.setAdverseReactionsResult(getAdverseReactionsResult());//評估不良反應結果
		  obj.setAdverseTotal(getAdverseTotal());
		  
		  obj.setEventSeverity(getEventSeverity());//事件嚴重程度
		  obj.setCommentOpinion(getCommentOpinion());//初評意見
		  obj.setCommentExplanation1(getCommentExplanation1());//初評意見說明1
		  obj.setCommentExplanation2(getCommentExplanation2());//初評意見說明2
		  obj.setOtherOpinion(getOtherOpinion());//其他意見
			    
		  obj.setNcarOptions(getNcarOptions());//Ncar通報篩選
		  obj.setNcarResult(getNcarResult());//Ncar通報篩選結果
		  obj.setNcarTotal(getNcarTotal());//Ncar通報總分
		  
		  obj.setResultsNotification(getResultsNotification());//結果通知
		  obj.setEventDetails(getEventDetails());//需在取得事件詳細說明
		  
		  obj.setModifier(getEditID());
		  obj.setModifyDate(getEditDate());
		  obj.setModifyTime(getEditTime());
		  
	      ServiceGetter.getInstance().getTcbwService().update(obj);
		  setId2(Common.get(obj.getId()));
		}
	}
	
	//評估完成
	public void doCompleted() throws Exception 
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateBydoCompletedMedIN0402F(this);
	}
	
	//評估確認
	public void doCompletedConfirmed() throws Exception 
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateBydoCompletedConfirmedMedIN0402F(this);
	}
	
	
    //可能性：評估不良反應，請回答下列問題，並勾選適當的答案
	public String getMed1002Db(String id2) throws Exception
	{
		String html="",value="",value1="";
		
		java.util.Map<String,String> h = new java.util.HashMap<String,String>();
		
		String[] name=null,name1=null;
		
//		String med0007Db=" from Med0007Db where ";
//		       med0007Db+=" id=(select max(id) from  Med0007Db ";
//		       med0007Db+=" where  med0001Db.id = " + Common.getLong(getId());
//		       med0007Db+=" ) order by id desc" ;
		String med0007Db=" from Med0007Db where ";
	       med0007Db+=" id=" + Common.getLong(id2);
	       med0007Db+="order by id desc";
		
		Med0007Db c = (Med0007Db)View.getObject(med0007Db);
		
		if(c!=null)
		{
			value=c.getAssessAdverseReactions();
			
			if(c.getAssessAdverseReactions()!=null && c.getAssessAdverseReactions().length()>0)
			{
			  name=value.split(";");
			  for(int x=0;x<name.length;x++)
			  {
				value1=name[x];
				
				if(value1.length()>0)
				{
				  name1=value1.split(":");
				  String map1="",map2="";
				
				  int z=1;
				  for(int y=0;y<name1.length;y++)
				  {
					if(z%2==1)
					  map1=name1[y];
					
					if(z%2==0)
					  map2=name1[y];
					
					z++;
				  }
				
				  h.put(map1,map2);
			    }
			  }
			}
		}

		String hql="from Med1002Db where isStop='N' ";
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
		
		if (objList != null && objList.size() > 0) 
		{
			int i=0;
			int q=1;
			
			for(Object dtlObj : objList) 
			{				
				String checkedY="",checkedN="",checkedO="";
				
				Med1002Db dtl = (Med1002Db)dtlObj;
			
				html+="<tr>";
				html+="<td>";
				html+=dtl.getMatter();
				html+="</td>";
				
				if("Y".equals(h.get(String.valueOf(q))))
				{
					checkedY="checked";
				}
				else if("N".equals(h.get(String.valueOf(q))))
				{
					checkedN="checked";
				}
				else if("O".equals(h.get(String.valueOf(q))))
				{
					checkedO="checked";
				}
				
                html+="<td align='center'>";
                html+="<input type='radio' onClick='aarTotal()' name='assessAdverseReactions"+i+"' "+checkedY+" value='Y'>";
				html+="</td>";
				
                html+="<td align='center'>";
                html+="<input type='radio' onClick='aarTotal()' name='assessAdverseReactions"+i+"' "+checkedN+" value='N'>";
				html+="</td>";
				
                html+="<td align='center'>";
                html+="<input type='radio' onClick='aarTotal()' name='assessAdverseReactions"+i+"' "+checkedO+" value='O'>";
				html+="</td>";
				
				html+="</tr>";
				
				i++;
				
				q++;
			}
			
			//System.out.println(html);
			
			objList.clear();
		}
		
		
		return html;
		
	}
	
	//NCAR通報篩選
	public String getMed1003Db(String id2) throws Exception
	{
		String html="",value="",value1="";
		
		java.util.Map<String,String> h = new java.util.HashMap<String,String>();
		
		String[] name=null,name1=null;
		
//		String med0007Db=" from Med0007Db where ";
//		       med0007Db+=" id=(select max(id) from  Med0007Db ";
//		       med0007Db+=" where  med0001Db.id = " + Common.getLong(getId());
//		       med0007Db+=" ) order by id desc" ;
		       
		String med0007Db=" from Med0007Db where ";
			   med0007Db+=" id = "+Common.getLong(id2);
			   med0007Db+=" order by id desc" ;		       
		
		Med0007Db c = (Med0007Db)View.getObject(med0007Db);
		
		if(c!=null)
		{
			value=c.getNcarOptions();
			
			if(c.getNcarOptions()!=null && c.getNcarOptions().length()>0)
			{  
			  name=value.split(";");
			
			  for(int x=0;x<name.length;x++)
			  {
		
				value1=name[x];
				if(value1.length()>0)
				{
				  name1=value1.split(":");
				  String map1="",map2="";
				
				  int z=1;
				  for(int y=0;y<name1.length;y++)
				  {
					if(z%2==1)
					  map1=name1[y];
					
					if(z%2==0)
					  map2=name1[y];
					
					z++;
				  }
				  h.put(map1,map2);
			    }
			  }
			}
		}

		String hql="from Med1003Db where isStop='N' ";
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
		
		if (objList != null && objList.size() > 0) 
		{
			int i=0;
			int q=1;
			
			for(Object dtlObj : objList) 
			{				
				String checkedY="",checkedN="";
				
				Med1003Db dtl = (Med1003Db)dtlObj;
			
				html+="<tr>";
				html+="<td>";
				
				if(!"1".equals(dtl.getNum()) && !"3".equals(dtl.getNum()))
				{
				   html+=dtl.getMatter();
				}
				else
				{
				   html+="<font color='red'>"+dtl.getMatter()+"</font>";
				}
				
				html+="</td>";
				
				if("Y".equals(h.get(String.valueOf(q))))
				{
					checkedY="checked";
				}
				else if("N".equals(h.get(String.valueOf(q))))
				{
					checkedN="checked";
				}

				
				if(!"3".equals(dtl.getNum()))
				{
                  html+="<td align='center'>";
                  html+="<input type='radio' onClick='totalNcar()' name='ncarOptions"+i+"' "+checkedY+" value='Y'>";
				  html+="</td>";
				
                  html+="<td align='center'>";
                  html+="<input type='radio' onClick='totalNcar()' name='ncarOptions"+i+"' "+checkedN+" value='N'>";
				  html+="</td>";
				  
				  i++;
				  q++;
				}
				else
				{
				  html+="<td align='center'>";
				  html+="</td>";
					
	              html+="<td align='center'>";           
			      html+="</td>";
				}
				
				html+="</tr>";

			}
			
			objList.clear();
		}
		
		
		return html;
		
	}
	
	
	
	public void doDeleteCon0003Db() throws Exception
	{
		String hql = "from Con0003Db where systemType='MED' ";
        hql +=" and formCode='MED01' ";
        hql +=" and stateus= "+ Common.sqlChar(getStatus());
        hql +=" and dbID=" + Common.sqlChar(getId());

        Con0003Db con0003Db = (Con0003Db)View.getObject(hql);	

        if (con0003Db != null) 
        { 
	       ServiceGetter.getInstance().getTcbwService().delete(con0003Db);
        }
	}
	
	
	
	@Override
	public void doCreate() throws Exception 
	{
		
	}
	
	@Override
	public void doDelete() throws Exception {
		
	}
	
	public String getEventDetails() {
		return checkGet(eventDetails);
	}

	public void setEventDetails(String eventDetails) {
		this.eventDetails = checkSet(eventDetails);
	}

	public String getId2() {
		return checkGet(id2);
	}

	public void setId2(String id2) {
		this.id2 = checkSet(id2);
	}

	public String getBulletinQuality() {
		return checkGet(bulletinQuality);
	}

	public void setBulletinQuality(String bulletinQuality) {
		this.bulletinQuality = checkSet(bulletinQuality);
	}

	public String getInstructionSheet() {
		return checkGet(instructionSheet);
	}

	public void setInstructionSheet(String instructionSheet) {
		this.instructionSheet = checkSet(instructionSheet);
	}

	public String getDocumentRecords() {
		return checkGet(documentRecords);
	}

	public void setDocumentRecords(String documentRecords) {
		this.documentRecords = checkSet(documentRecords);
	}

	public String getDocumentNum() {
		return checkGet(documentNum);
	}

	public void setDocumentNum(String documentNum) {
		this.documentNum = checkSet(documentNum);
	}

	public String[] getPoliceQuery() {
		return checkGet(policeQuery);
	}

	public void setPoliceQuery(String[] policeQuery) {
		this.policeQuery = checkSet(policeQuery);
	}

	public String getPoliceFdaNum() {
		return checkGet(policeFdaNum);
	}

	public void setPoliceFdaNum(String policeFdaNum) {
		this.policeFdaNum = checkSet(policeFdaNum);
	}

	public String getPoliceMhraNum() {
		return checkGet(policeMhraNum);
	}

	public void setPoliceMhraNum(String policeMhraNum) {
		this.policeMhraNum = checkSet(policeMhraNum);
	}

	public String getPoliceEcriNum() {
		return checkGet(policeEcriNum);
	}

	public void setPoliceEcriNum(String policeEcriNum) {
		this.policeEcriNum = checkSet(policeEcriNum);
	}

	public String getMedicalIssues() {
		return checkGet(medicalIssues);
	}

	public void setMedicalIssues(String medicalIssues) {
		this.medicalIssues = checkSet(medicalIssues);
	}

	public String getPatientIssues() {
		return checkGet(patientIssues);
	}

	public void setPatientIssues(String patientIssues) {
		this.patientIssues = checkSet(patientIssues);
	}

	public String getAssessAdverseReactions() {
		return checkGet(assessAdverseReactions);
	}

	public void setAssessAdverseReactions(String assessAdverseReactions) {
		this.assessAdverseReactions = checkSet(assessAdverseReactions);
	}

	public String getAdverseReactionsResult() {
		return checkGet(adverseReactionsResult);
	}

	public void setAdverseReactionsResult(String adverseReactionsResult) {
		this.adverseReactionsResult = checkSet(adverseReactionsResult);
	}

	public String getEventSeverity() {
		return checkGet(eventSeverity);
	}

	public void setEventSeverity(String eventSeverity) {
		this.eventSeverity = checkSet(eventSeverity);
	}

	public String getCommentOpinion() {
		return checkGet(commentOpinion);
	}

	public void setCommentOpinion(String commentOpinion) {
		this.commentOpinion = checkSet(commentOpinion);
	}

	public String getCommentExplanation1() {
		return checkGet(commentExplanation1);
	}

	public void setCommentExplanation1(String commentExplanation1) {
		this.commentExplanation1 = checkSet(commentExplanation1);
	}

	public String getCommentExplanation2() {
		return checkGet(commentExplanation2);
	}

	public void setCommentExplanation2(String commentExplanation2) {
		this.commentExplanation2 = checkSet(commentExplanation2);
	}

	public String getOtherOpinion() {
		return checkGet(otherOpinion);
	}

	public void setOtherOpinion(String otherOpinion) {
		this.otherOpinion = checkSet(otherOpinion);
	}

	public String getNcarOptions() {
		return checkGet(ncarOptions);
	}

	public void setNcarOptions(String ncarOptions) {
		this.ncarOptions = checkSet(ncarOptions);
	}

	public String getNcarResult() {
		return checkGet(ncarResult);
	}

	public void setNcarResult(String ncarResult) {
		this.ncarResult = checkSet(ncarResult);
	}

	public String getResultsNotification() {
		return checkGet(resultsNotification);
	}

	public void setResultsNotification(String resultsNotification) {
		this.resultsNotification = checkSet(resultsNotification);
	}

	public String getChangeTabValue() {
		return checkGet(changeTabValue);
	}

	public void setChangeTabValue(String changeTabValue) {
		this.changeTabValue = checkSet(changeTabValue);
	}

	public String getAdverseTotal() {
		return checkGet(adverseTotal);
	}

	public void setAdverseTotal(String adverseTotal) {
		this.adverseTotal = checkSet(adverseTotal);
	}

	public String getNcarTotal() {
		return checkGet(ncarTotal);
	}

	public void setNcarTotal(String ncarTotal) {
		this.ncarTotal = checkSet(ncarTotal);
	}


	public String getStatDateS() {
		return checkGet(statDateS);
	}


	public void setStatDateS(String statDateS) {
		this.statDateS = checkSet(statDateS);
	}


	public String getStatDateE() {
		return checkGet(statDateE);
	}


	public void setStatDateE(String statDateE) {
		this.statDateE = checkSet(statDateE);
	}


	public String getStatEventKind1() {
		return checkGet(statEventKind1);
	}


	public void setStatEventKind1(String statEventKind1) {
		this.statEventKind1 = checkSet(statEventKind1);
	}


	public String getStatEventKind2() {
		return checkGet(statEventKind2);
	}


	public void setStatEventKind2(String statEventKind2) {
		this.statEventKind2 = checkSet(statEventKind2);
	}
	
	public String[] getMed1005DbTypeCode() {
		return med1005DbTypeCode;
	}

	public void setMed1005DbTypeCode(String[] med1005DbTypeCode) {
		this.med1005DbTypeCode = med1005DbTypeCode;
	}
	
	public String[] getMed1005DbTypeId() {
		return med1005DbTypeId;
	}

	public void setMed1005DbTypeId(String[] med1005DbTypeId) {
		this.med1005DbTypeId = med1005DbTypeId;
	}
	
	public String[] getMed1006_0403DbTypeCode() {
		return med1006_0403DbTypeCode;
	}

	public void setMed1006_0403DbTypeCode(String[] med1006_0403DbTypeCode) {
		this.med1006_0403DbTypeCode = med1006_0403DbTypeCode;
	}
	
	public String[] getMed1006_0403DbTypeId() {
		return med1006_0403DbTypeId;
	}

	public void setMed1006_0403DbTypeId(String[] med1006_0403DbTypeId) {
		this.med1006_0403DbTypeId = med1006_0403DbTypeId;
	}

}
