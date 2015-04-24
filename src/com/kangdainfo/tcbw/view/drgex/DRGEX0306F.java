package com.kangdainfo.tcbw.view.drgex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.model.bo.Drg4006Db;
import com.kangdainfo.tcbw.model.bo.Drg4008Db;
import com.kangdainfo.tcbw.model.bo.Drg4010Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

/**
*<br>程式目的：藥品療效不等-廠商回覆
*<br>程式代號：drgex0306f
*<br>程式日期：1021126
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  DRGEX0306F extends DRGEX0305F{

	private String actionType;
	private String review1;			//	製程管制與最終產品之結果檢討
    private String review2;			//	原料(含來源)/包材或配方變更之檢討
    private String review3;			//	製程/規格或分析方法變更之檢討
    private String review4;			//	安定性監測計畫的結果與任何不良趨勢之檢討
    private String review5;			//	一切顯著偏離或不相符(包括品質相關之客訴/回收…等)之調查及實施CAPA後成果之檢討
    private String review6;			//	任何其他先前產品製程/設備或環境改正行動之適當性檢討
    
    
    public String getActionType() {return checkGet(actionType);}
	public void setActionType(String s) {this.actionType = checkSet(s);}
	public String getReview1() {return checkGet(review1);}
	public void setReview1(String s) {this.review1 = checkSet(s);}
	public String getReview2() {return checkGet(review2);}
	public void setReview2(String s) {this.review2 = checkSet(s);}
	public String getReview3() {return checkGet(review3);}
	public void setReview3(String s) {this.review3 = checkSet(s);}
	public String getReview4() {return checkGet(review4);}
	public void setReview4(String s) {this.review4 = checkSet(s);}
	public String getReview5() {return checkGet(review5);}
	public void setReview5(String s) {this.review5 = checkSet(s);}
	public String getReview6() {return checkGet(review6);}
	public void setReview6(String s) {this.review6 = checkSet(s);}

@Override
public Object doQueryOne() throws Exception {
	DRGEX0306F obj = this;
	Drg4006Db c = (Drg4006Db) View.getObject(" from Drg4006Db where id=" + Common.getInt(obj.getId()));
	if (c!=null) {
		obj.setReview1(c.getReview1());
		obj.setReview2(c.getReview2());
		obj.setReview3(c.getReview3());
		obj.setReview4(c.getReview4());
		obj.setReview5(c.getReview5());
		obj.setReview6(c.getReview6());
	}
	return obj;
}

@Override
public Object doQueryAll() throws Exception {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	String hql = " from Drg4010Db where drg4006Db.id = "+getId();
	this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
	if (getTotalRecord() > 0) 
	{
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
		
		List objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id ", this.getRecordStart(), this.getPageSize());
		if(objList!=null && objList.size()>0)
		{
			for(Object dtlObj : objList)
			{
				Drg4010Db dtl = (Drg4010Db)dtlObj;
				String[] rowArray = new String[7];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getDrg4001Db().getApplNo());
				rowArray[2] = Common.get(dtl.getDrg4001Db().getNotifierRepDate());
				rowArray[3] = "";
				rowArray[4] = "";
				rowArray[5] = "";
				rowArray[6] = "";
				Drg4004Db drg04 = (Drg4004Db) View.getObject(" from Drg4004Db where applNo=" + Common.sqlChar(dtl.getDrg4001Db().getApplNo()));
				if(null != drg04){
					if("Y".equals(drg04.getMedNti())){
						rowArray[3] = "NTI Drugs";
					}
					if(null != drg04.getMedAtcCode() && !"".equals(drg04.getMedAtcCode())){
						rowArray[3] += "藥理治療分類(ATC code)："+drg04.getMedAtcCode();
					}
					
					if(!StringUtils.contains(drg04.getConSequence(), "1")) {
						rowArray[4] += "藥效改變";
					}
					if(!StringUtils.contains(drg04.getConSequence(), "2")) {
						rowArray[4] += "不良反應發生、強度增強或頻率增加";
						rowArray[5] =  Common.get(View.getCommonCodeName("DRG0308", drg04.getBadReactionLev()));
					}
					rowArray[6] = Common.get(View.getCommonCodeName("DRG2RKL", drg04.getAssessResult()));
				}
				arrList.add(rowArray);
			}
		}
	}
	return arrList;
}

@Override
public void doCreate() throws Exception {
	// TODO Auto-generated method stub
	
}

@Override
public void doUpdate() throws Exception {
	List updList = new ArrayList();
	Map<Long, String> sendMap = new HashMap<Long, String>();
	Drg4006Db obj = (Drg4006Db) View.getObject(" from Drg4006Db where id=" + Common.getInt(getId()));
	if (obj!=null) {
		obj.setReview1(getReview1());
		obj.setReview2(getReview2());
		obj.setReview3(getReview3());
		obj.setReview4(getReview4());
		obj.setReview5(getReview5());
		obj.setReview6(getReview6());
		obj.setModifier(getUserID());
		obj.setModifyDate(Datetime.getYYYMMDD());
		obj.setModifyTime(Datetime.getHHMMSS());
		
		if("1".equals(getActionType())){	//回覆完成
			
			obj.setReplyDate(Datetime.getYYYMMDD());
			obj.setIsClose("Y");
			if(null != obj.getDrg4010Dbs() && !obj.getDrg4010Dbs().isEmpty()){
				java.util.Set dtlSet = new ListOrderedSet();
				for(Object dtlObj:obj.getDrg4010Dbs()){
					Drg4010Db drg410 = (Drg4010Db)dtlObj;
					if(null != drg410.getDrg4001Db()){
						drg410.getDrg4001Db().setStatus("50");
						sendMap.put(drg410.getDrg4001Db().getId(), drg410.getDrg4001Db().getApplNo()+","+drg410.getDrg4001Db().getChargeMan());
						//歷程紀錄
						ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG2",drg410.getDrg4001Db().getId(), drg410.getDrg4001Db().getApplNo(),"50", "評估作業 - 廠商回覆", getUserID());
						//同步更新外部案件狀態
						if(null != drg410.getDrg4001Db()){
							Drg6001Db drg61 = (Drg6001Db)ServiceGetter.getInstance().getTcbwService().getObject(" from Drg6001Db where drg4001Id = " + drg410.getDrg4001Db().getId()+" order by revision desc ");
							if(null != drg61){
								drg61.setStatus(drg410.getDrg4001Db().getStatus());
								updList.add(drg61);
							}
						}
					}
					dtlSet.add(drg410);
				}
				obj.setDrg4010Dbs(dtlSet);
			}
			
		}
		ServiceGetter.getInstance().getTcbwService().update(obj);
		if(null != updList && !updList.isEmpty()){
			ServiceGetter.getInstance().getTcbwService().updateBatch(updList);
		}
		if(null != sendMap && !sendMap.isEmpty()){
			for(Long id:sendMap.keySet()){
				String[] mailStr = sendMap.get(id).split(",");
				//發送信件通知		    		
	    		java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
	    		
	    		String mailBody="案件"+mailStr[0]+"，廠商已完成回覆。\n\n謝謝!!";
	    		String title = "廠商回覆完成通知- 案號:"+mailStr[0];
	    		String mail = "";	    				
	    		//取得作業人員Email
	    		mail = View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(mailStr[1]));
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
	    		ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true, null, null, null,"DRG02",mailStr[0]);
	    		TCBWCommon.setMailbackup("DRG2",Common.get(id),title, mailBody, mailStr[0],"", TCBWCommon.getCurrentUserId(),"");	
			}
		}
		setId(Common.get(obj.getId()));
	}
}

@Override
public void doDelete() throws Exception {
	// TODO Auto-generated method stub
	
}


}


