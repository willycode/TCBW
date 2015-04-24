package com.kangdainfo.tcbw.model.dao.hibernate;

import java.util.HashMap;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Con1006Db;
import com.kangdainfo.tcbw.model.bo.Con1013Db;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.MsgObject;
import com.kangdainfo.tcbw.model.dao.ScheduleDao;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class Cos0202ScheduleDaoImpl extends AbstractScheduleDaoImpl implements 	ScheduleDao {

	@Override
	public MsgObject executeSchedule() throws Exception {

		MsgObject r = new MsgObject();
		HashMap<String,String> resultMap = new HashMap<String,String>();
		logger.info("======== 啟動Cos0202化粧品不良品轉知TFDA作業排程 ========");
		try{
			/**
			 *   案件狀態為「案件追蹤中」，不良事件類別為「不良品」。(案件編號CA01開頭)
			 *   如果案件有通知衛生單位：(以通知日期起算，超過3個月，案件仍未結案者。) -- > 103.04.16 變更為超過九個月
			 *   如果案件僅通知廠商：  以通知日期(最新一筆)起算，超過20天，廠商仍未完成回覆者。  
			 **/
			String today = Datetime.getYYYMMDD();
			String hql1 = " select a.applNo, b.processDate from Cos0001Db a, Con2001Db b where 1=1 "+ 
			              " and a.applNo = b.applNo and a.status = '50' and a.applNo like 'CA01%' and b.systemType = 'COS02' " +
			              " and b.id = (select max(id) from Cos0009Db  where b.applNo = applNo )";
			
			java.util.List objLists = ServiceGetter.getInstance().getTcbwService().load(hql1);
			if(objLists != null && objLists.size()>0){
				java.util.Iterator it = objLists.iterator();
				
				// 衛生單位回覆稽催期限
				String mons = View.getLookupField("select field5 from Sys0001Db where id = 1"); 
				if("".equals(Common.get(mons))){
					mons = "9";
				}
				
				while(it.hasNext()){
					boolean isMail = false;
					Object[] obj = (Object[])it.next();
					
					String dueDay = Datetime.getDateAdd("m", Common.getInt(mons), Common.get(obj[1]));
					if(Common.getInt(today) > Common.getInt(dueDay)){
						int processDays = Common.getInt(Datetime.DateSubtraction(dueDay, today));
						
						// 只發一次
						if(processDays == 1){ 
							isMail = true;
						}
					}
					
					Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='COS020007'");
					if(c != null && isMail){
						java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
						String mailBody = "", title = "", mail = "";
						Cos0001Db cos01 = (Cos0001Db)View.getObject(" from Cos0001Db where applNo = " + Common.sqlChar(Common.get(obj[0])));
						if(cos01 != null){
                        	java.util.List objList3 = ServiceGetter.getInstance().getCommonService().load(" from Con1013Db where con1012Db.code='TFDA01' and con1012Db.systemType ='COS'");
							if(objList3!=null && objList3.size()>0){
								for(Object dtlObj : objList3){
									Con1013Db dtl = (Con1013Db)dtlObj;
									if(!"".equals(Common.get(dtl.getCommonUser())))
									  mail += dtl.getCommonUser().getUserEmail()+",";
								}
							}
							if(objList3 !=null){
								objList3.clear();
							}
							if(mail.length()>1) mail = mail.substring(0,mail.length()-1);
			    			
			    			mailBody = c.getMailBody();
			    			title = c.getTitle();

			    			// 帶入相關欄位
			    			title = title.replace("[T_案號]", cos01.getApplNo());
			    			mailBody = mailBody.replace("[F_案號]", cos01.getApplNo());	
			    			mailBody = mailBody.replace("[F_公司名稱]", cos01.getManufactorName());
			    			
			    			String[] mailAddress = mail.split(",");
			    			if(mailAddress!=null && mailAddress.length>0){
			    				for(String s : mailAddress){
			    					javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
			    				    p.setAddress(s);
			    				    mailList.add(p);
			    				}
			    			}
			    			if(mailAddress != null){			    			
			    				ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true, null, null, null);			    			
			    				//TCBWCommon.setMailbackup("COS02", Common.get(cos01.getId()), title , mailBody, cos01.getApplNo(), cos01.getStatus(), "SYS");
			    			}			    			
						}else{
							logger.info("異常-Cos0001Db無此案件:"+obj[0]);
						}
					}
				}
				
				objLists.clear();
			}else{
				logger.info("無衛生單位回覆稽催轉知TFDA案件");
			}
			objLists = null;
			
			String hql2 = " select a.applNo, b.notifyDate from Cos0001Db a, Cos0010Db b where 1=1"+ 
            			  " and a.applNo = b.applNo and a.status = '50' and a.applNo like 'CA01%' and b.isClose = 'N' " +
            			  " and b.id = (select max(id) from Cos0010Db  where b.applNo = applNo )";
			java.util.List objLists2 = ServiceGetter.getInstance().getTcbwService().load(hql2);
			if(objLists2 != null && objLists2.size()>0){	
				java.util.Iterator it = objLists2.iterator();				
				
				// 廠商回覆稽催期限(第二次)
				String days = View.getLookupField("select field3 from Sys0001Db where id = 1");
				if("".equals(Common.get(days))){
					days = "20"; //預設20天
				}
				
				while(it.hasNext()){
					boolean isMail = false;					
					Object[] obj = (Object[])it.next();		
	
					String dueDay = Datetime.getDateAdd("d", Common.getInt(days), Common.get(obj[1]));						
					if(Common.getInt(today) > Common.getInt(dueDay)){
						int notifyDays = Common.getInt(Datetime.DateSubtraction(dueDay, today));
						if(notifyDays == 1){ //只發一次
							isMail=true;
						}
					}
					
					Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='COS020008'");	
					if(c != null && isMail){		
						java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();			
						String mailBody = "", title = "", mail = "";			
						Cos0001Db cos01 = (Cos0001Db)View.getObject(" from Cos0001Db where applNo = " + Common.sqlChar(Common.get(obj[0])));			
						if(cos01 != null){
                        	java.util.List objList4 = ServiceGetter.getInstance().getCommonService().load(" from Con1013Db where con1012Db.code='TFDA01' and con1012Db.systemType ='COS'");
							if(objList4!=null && objList4.size()>0){
								for(Object dtlObj : objList4){
									Con1013Db dtl = (Con1013Db)dtlObj;
									if(!"".equals(Common.get(dtl.getCommonUser())))
										mail += dtl.getCommonUser().getUserEmail() + ",";
								}
							}
							if(objList4 != null){
								objList4.clear();
							}
							if(mail.length()>1) mail = mail.substring(0,mail.length()-1);			
   			
							mailBody = c.getMailBody();   			
							title = c.getTitle();
   			
							// 帶入相關欄位   			
							title = title.replace("[T_案號]", cos01.getApplNo());
							mailBody = mailBody.replace("[F_公司名稱]", cos01.getManufactorName());  			
							String[] mailAddress = mail.split(",");   			
							if(mailAddress!=null && mailAddress.length>0){
								for(String s : mailAddress){
									javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();   				   
									p.setAddress(s);
									mailList.add(p);
								}
							}
							if(mailAddress != null){
								ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true, null, null, null);  				
								//TCBWCommon.setMailbackup("COS02", Common.get(cos01.getId()), title , mailBody, cos01.getApplNo(), cos01.getStatus(), "SYS");   			
							}			
						}else{				
							logger.info("異常-Cos0001Db無此案件:"+obj[0]);			
						}		
					}	
				}
				
				objLists2.clear();
			}else{	
				logger.info("無廠商回覆稽催轉知TFDA案件");
			}
			
			objLists2 = null;
		}catch(Exception e){
			e.printStackTrace();
			
			String errorMessage = "啟動Cos0202排程失敗" + e.getMessage();
			logger.info(errorMessage);
			resultMap.put("2", errorMessage);			
			r.setReturnMap(resultMap);
			r.setReturnMsg(errorMessage);
		}
		logger.info("======== 結束Cos0202化粧品不良品轉知TFDA作業排程 ========");
		return r;
	}

}
