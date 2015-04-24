package com.kangdainfo.tcbw.view.sdrg;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg8001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class SDRG0501Q extends SuperBean{
	
	private String id;//序號	NUMERIC(19,0)
	
	private String doType;
	public String getDoType() {return checkGet(doType);}
	public void setDoType(String doType) {this.doType = checkSet(doType);}
	
	String caseType ;
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}

	private String q_applNoS;      //案件編號起
	private String q_applNoE;      //案件編號迄
	private String q_chProduct;    //中文品名
	private String q_permitKey;    //許可證字
	private String q_permitNo;     //許可證號
	private String q_status;       //狀態
	private String q_appName;      //許可證持有商
	private String q_manufactorName; //製造商/製造廠
	private String q_postDateS;    //發文日期起
	private String q_postDateE;    //發文日期迄
	private String q_postNo;  //回收文號 
	private String[] q_recycleclass;	//回收分級
	private String[] q_msgsource;	//訊息來源
	private String q_cureapplno;	 //通報編號
	private String q_quaapplno;	//警訊編號
	private String q_recycledeadlineS;	//回收期限
	private String q_recycledeadlineE;	//回收期限
	private String q_healthbureau; //主管衛生局
	private String q_healthbureauName; //主管衛生局
	
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}
	
	@Override
	public Object doQueryAll() throws Exception {		
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Drg8001Db a  where  1=1 ";		
		
		hql += " and a.id in (select drg8001Db.id from Drg8004Db where status in ('40','50') )";
		
		if(!"".equals(getQ_applNoS()))
			hql += " and a.applNo >= " + Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE()))
			hql += " and a.applNo <= " + Common.sqlChar(getQ_applNoE());
		if(!"".equals(getQ_chProduct()))
			hql += " and ( a.chProduct like " + Common.sqlChar("%"+getQ_chProduct()+"%")+
		           " or a.enProduct like "+ Common.sqlChar("%"+getQ_chProduct()+"%")+ ")";
		if(!"".equals(getQ_permitKey()))
			hql += " and a.permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and a.permitNo = " + Common.sqlChar(getQ_permitNo());
//		if(!"".equals(getQ_status()))
//			hql += " and a.status = " + Common.sqlChar(getQ_status());
		if(!"".equals(getQ_appName()))
			hql += " and a.appName like " + Common.sqlChar("%"+getQ_appName()+"%");
		if(!"".equals(getQ_manufactorName()))
			hql += " and a.manufactorName like " + Common.sqlChar("%"+getQ_manufactorName()+"%");
		if(!"".equals(getQ_postDateS()))
			hql += " and a.postDate >= " + Common.sqlChar(getQ_postDateS());
		if(!"".equals(getQ_postDateE()))
			hql += " and a.postDate <= " + Common.sqlChar(getQ_postDateE());
		if(!"".equals(getQ_postNo()))
			hql += " and a.postNo = " + Common.sqlChar(getQ_postNo());
//		if(!"".equals(getQ_cureapplno()))
//			hql += " and a.cureapplno = " + Common.sqlChar(getQ_cureapplno());
//		if(!"".equals(getQ_quaapplno()))
//			hql += " and a.quaapplno = " + Common.sqlChar(getQ_quaapplno());
		if(!"".equals(getQ_recycledeadlineS()))
			hql += " and a.recycledeadline >= " + Common.sqlChar(getQ_recycledeadlineS());
		if(!"".equals(getQ_recycledeadlineE()))
			hql += " and a.recycledeadline <= " + Common.sqlChar(getQ_recycledeadlineE());
		if(!"".equals(getQ_healthbureau()))
			hql += " and ( a.healthbureau1=" + Common.sqlChar(getQ_healthbureau())+
	               " or a.healthbureau2="+ Common.sqlChar(getQ_healthbureau())+" )";		
//		if(getQ_recycleclass()!=null && getQ_recycleclass().length>0){			
//			boolean flag = true;
//			for(String rid : getQ_recycleclass()){	
//				if(flag){
//					hql += " and ( a.recycleclass like "+ Common.sqlChar("%" + rid + "%");					
//					flag = false;
//				}else{
//					hql += " or a.recycleclass like " + Common.sqlChar("%" + rid + "%");
//				}
//			}
//			hql += " )";
//		}
		if(getQ_msgsource()!=null && getQ_msgsource().length>0){			
			boolean flag = true;
			for(String rid : getQ_msgsource()){	
				if(flag){
					hql += " and ( a.msgsource like "+ Common.sqlChar("%" + rid + "%");					
					flag = false;
				}else{
					hql += " or a.msgsource like " + Common.sqlChar("%" + rid + "%");
				}
			}
			hql += " )";
		}
		System.out.println("[TCBW]-[SDRG0501Q]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by a.id desc");
			
			if(objList!=null && objList.size()>0)
			{				
				java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
				java.util.Map<String, String> healthMap = TCBWCommon.getMap("select id,unionName from Con1003Db");
				java.util.Map<String, String> ecsrMap = TCBWCommon.getCommonCodeMap("DRGRECSR");
				for(Object dtlObj : objList)
				{
					Drg8001Db dtl = (Drg8001Db)dtlObj;
					//回收期限
					String recycledeadline = dtl.getRecycledeadline(); 
					//調查報告展延期限
					String assessextendate = View.getLookupField("select assessextendate from Drg8004Db where drg8001Db.id="+Common.getLong(dtl.getId())); 
					//回覆日期 (若未有回覆日期，以系統日代替)
					String appreplydate = !"".equals(Common.get(dtl.getAppreplydate()))?Common.get(dtl.getAppreplydate()):Common.get(Datetime.getYYYMMDD());   				
					
					String light ="";
					String days ="";
					
					if(!"".equals(Common.get(assessextendate))){
						days = Datetime.getDateDiff("d",assessextendate,appreplydate);
					}else{
						days = Datetime.getDateDiff("d",recycledeadline,appreplydate);
					}					
										
					if(Common.getInt(days)>7 )
					{	
						//紅
						light="<img width=\"30px\" src=\"../../images/ballRed.png\">";
					}	
					else if (Common.getInt(days) > 0 && Common.getInt(days) <= 7) 
					{
						//黃
						light="<img width=\"30px\" src=\"../../images/ballYellow.png\">";
					}
					
					String[] rowArray = new String[13];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(light);
					rowArray[2] = Common.get(dtl.getApplNo());					
					rowArray[3] = Common.get(dtl.getChProduct());
					rowArray[4] = Common.get(dtl.getEnProduct());
					rowArray[5] = Common.get(!"".equals(Common.get(dtl.getPermitKey()))?(pkidMap.get(dtl.getPermitKey())+"-"+dtl.getPermitNo()):"");					
					rowArray[6] = Common.get(dtl.getAppName());
					rowArray[7] = Common.get(dtl.getPostDate());
					rowArray[8] = Common.get(dtl.getPostNo());
					rowArray[9] = !"".equals(Common.get(dtl.getMsgsource()))?ecsrMap.get(Common.get(dtl.getMsgsource())):"";
					rowArray[10] = Common.get(dtl.getRecycledeadline());
					rowArray[11] = !"".equals(Common.get(dtl.getHealthbureau1()))?healthMap.get(Common.get(dtl.getHealthbureau1())):"";
					rowArray[12] =  "<div class='discolor'><a href='sdrg0501f.jsp?id="+Common.get(dtl.getId())+"'>"+"明細"+"</a></div>";

					arrList.add(rowArray);
				}
				objList.clear();
				pkidMap.clear();
				healthMap.clear();
				ecsrMap.clear();
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

	
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	
	public String getQ_applNoS() {
		return checkGet(q_applNoS);
	}
	public void setQ_applNoS(String qApplNoS) {
		this.q_applNoS = checkSet(qApplNoS);
	}
	public String getQ_applNoE() {
		return checkGet(q_applNoE);
	}
	public void setQ_applNoE(String qApplNoE) {
		this.q_applNoE = checkSet(qApplNoE);
	}
	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}
	public void setQ_chProduct(String q_chProduct) {
		this.q_chProduct = checkSet(q_chProduct);
	}
	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}
	public void setQ_permitKey(String q_permitKey) {
		this.q_permitKey = checkSet(q_permitKey);
	}
	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}
	public void setQ_permitNo(String q_permitNo) {
		this.q_permitNo = checkSet(q_permitNo);
	}
	public String getQ_status() {
		return checkGet(q_status);
	}
	public void setQ_status(String q_status) {
		this.q_status = checkSet(q_status);
	}
	public String getQ_appName() {
		return checkGet(q_appName);
	}
	public void setQ_appName(String q_appName) {
		this.q_appName = checkSet(q_appName);
	}
	public String getQ_manufactorName() {
		return checkGet(q_manufactorName);
	}
	public void setQ_manufactorName(String q_manufactorName) {
		this.q_manufactorName = checkSet(q_manufactorName);
	}
	public String getQ_postDateS() {
		return checkGet(q_postDateS);
	}
	public void setQ_postDateS(String q_postDateS) {
		this.q_postDateS = checkSet(q_postDateS);
	}
	public String getQ_postDateE() {
		return checkGet(q_postDateE);
	}
	public void setQ_postDateE(String q_postDateE) {
		this.q_postDateE = checkSet(q_postDateE);
	}
	public String getQ_postNo() {
		return checkGet(q_postNo);
	}
	public void setQ_postNo(String q_postNo) {
		this.q_postNo = checkSet(q_postNo);
	}
	public String[] getQ_recycleclass() {
		return q_recycleclass;
	}
	public void setQ_recycleclass(String[] q_recycleclass) {
		this.q_recycleclass = q_recycleclass;
	}
	public String[] getQ_msgsource() {
		return q_msgsource;
	}
	public void setQ_msgsource(String[] q_msgsource) {
		this.q_msgsource = q_msgsource;
	}
	public String getQ_cureapplno() {
		return checkGet(q_cureapplno);
	}
	public void setQ_cureapplno(String q_cureapplno) {
		this.q_cureapplno = checkSet(q_cureapplno);
	}
	public String getQ_quaapplno() {
		return checkGet(q_quaapplno);
	}
	public void setQ_quaapplno(String q_quaapplno) {
		this.q_quaapplno = checkSet(q_quaapplno);
	}
	public String getQ_recycledeadlineS() {
		return checkGet(q_recycledeadlineS);
	}
	public void setQ_recycledeadlineS(String q_recycledeadlineS) {
		this.q_recycledeadlineS = checkSet(q_recycledeadlineS);
	}
	public String getQ_recycledeadlineE() {
		return checkGet(q_recycledeadlineE);
	}
	public void setQ_recycledeadlineE(String q_recycledeadlineE) {
		this.q_recycledeadlineE = checkSet(q_recycledeadlineE);
	}
	public String getQ_healthbureau() {
		return checkGet(q_healthbureau);
	}
	public void setQ_healthbureau(String q_healthbureau) {
		this.q_healthbureau = checkSet(q_healthbureau);
	}
	public String getQ_healthbureauName() {
		return checkGet(q_healthbureauName);
	}
	public void setQ_healthbureauName(String q_healthbureauName) {
		this.q_healthbureauName = checkSet(q_healthbureauName);
	}
}
