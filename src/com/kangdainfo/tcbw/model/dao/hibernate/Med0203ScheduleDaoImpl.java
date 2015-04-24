package com.kangdainfo.tcbw.model.dao.hibernate;

import java.util.HashMap;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Con1006Db;
import com.kangdainfo.tcbw.model.bo.Con1013Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.MsgObject;
import com.kangdainfo.tcbw.model.dao.ScheduleDao;


public class Med0203ScheduleDaoImpl extends AbstractScheduleDaoImpl implements 	ScheduleDao {

	@Override
	public MsgObject executeSchedule() throws Exception {

		MsgObject r = new MsgObject();
		HashMap<String,String> resultMap = new HashMap<String,String>();
		logger.info("======== 啟動Med0203醫療器材不良品廠商回覆稽催作業排程 ========");
		try {			
			String today = Datetime.getYYYMMDD();		
			
			/**
			 *   案件狀態為「廠商回覆中」，不良事件類別為「不良品」。(案件編號MR02開頭)
			 *   A 級案件的回覆作業期限為15天，以通知日期(最新一筆)起算，後續每日持續發送稽催信件。
			 *   B 級案件的回覆作業期限為1個月，以通知日期(最新一筆)起算，後續每週持續發送稽催信件。
			 *   系統發送「廠商回覆稽催通知」給廠商
             *   如果案件的不良品事件等級為「A級」，郵件需副件密本給TFDA人員。
			 **/			
			String hql1 =" select a.med0001Db.applNo,a.noticeDate from Med0010Db a where 1=1"+ 
			             " and a.med0001Db.status ='51' and a.med0001Db.applNo like 'MR02%' "+
			             " and a.id = (select max(id) from Med0010Db where a.med0001Db.id = med0001Db.id )";
			
			java.util.List objLists = ServiceGetter.getInstance().getTcbwService().load(hql1);
			if(objLists != null && objLists.size()>0){
				java.util.Iterator it = objLists.iterator();
				String days = View.getLookupField("select field5 from Sys0001Db where id=4"); //不良品廠商回覆稽催期限(A級)
				String mons = View.getLookupField("select field6 from Sys0001Db where id=4"); //不良品廠商回覆稽催期限(B級)
				if("".equals(Common.get(days))) days ="15"; //預設15天
				if("".equals(Common.get(mons))) mons ="1"; //預設1個月
				
				while (it.hasNext()) {
					boolean isMail = false;
					Object[] obj = (Object[])it.next();
					
					//不良品事件等級(取初評)
					String eventClass = View.getLookupField("select eventClass from Med0008Db where med0001Db.applNo="+Common.sqlChar(Common.get(obj[0]))+" order by id desc");
					
					if("1".equals(Common.get(eventClass))){ //A
						int noticeDays= Common.getInt(Datetime.DateSubtraction(Common.get(obj[1]), today));
						if(noticeDays > Common.getInt(days)){
							isMail=true;
						}
					}
					else{ //B
						String dueDay = Datetime.getDateAdd("m", Common.getInt(mons), Common.get(obj[1]));						
						if(Common.getInt(today) > Common.getInt(dueDay)){
							int noticeDays = Common.getInt(Datetime.DateSubtraction(dueDay, today));
							if(noticeDays%7==1){ //每週持續發送稽催信件
								isMail=true;
							}
						}	
					}
					
					//發送稽催通知
					if(isMail){
						Med0001Db med01 = (Med0001Db)View.getObject(" from Med0001Db where applNo="+Common.sqlChar(Common.get(obj[0])));
						if(med01 != null){
							
							java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
							Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='MED020007'");									
														
				    		String mailBody="",title="",mail="";				    		
				    		if(c!=null)
				    		{                                                            
								//找出廠商Mail				    			
				    			java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(" from Con1006Db where con1005Db.compegno="+Common.sqlChar(med01.getMedPermitFirmCode()));
								if(objList2!=null && objList2.size()>0)
								{
									for(Object dtlObj : objList2)
									{
										Con1006Db dtl = (Con1006Db)dtlObj;
										mail += dtl.getCommonUser().getUserEmail()+",";
									}
								}
								if(objList2 !=null) objList2.clear();
								if(mail.length()>1) mail = mail.substring(0,mail.length()-1);
                                
                                if("1".equals(Common.get(eventClass))){ //郵件需副件密本給TFDA人員。
                                	java.util.List objList3 = ServiceGetter.getInstance().getCommonService().load(" from Con1013Db where con1012Db.code='MED02' ");
    								if(objList3!=null && objList3.size()>0)
    								{
    									for(Object dtlObj : objList3)
    									{
    										Con1013Db dtl = (Con1013Db)dtlObj;
    										if(!"".equals(Common.get(dtl.getCommonUser())))
    										   mail += ","+dtl.getCommonUser().getUserEmail();
    									}
    								}
    								if(objList3 !=null) objList3.clear();
                                }
                                
				    			mailBody=c.getMailBody();
				    			title=c.getTitle();    			
				    			
				    			//帶入相關欄位
				    			title = title.replace("[T_事件編號]", med01.getApplNo());				    			
				    			mailBody=mailBody.replace("[F_事件編號]", med01.getApplNo());
				    			
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
				    			if(mailList != null){				    			
				    				ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true, null, null, null);				    			
				    				//TCBWCommon.setMailbackup("MED02", Common.get(med01.getId()), title , mailBody, med01.getApplNo(), med01.getStatus(), "SYS");
				    			}
				    		}				    		
						}else{
							logger.info("異常-Med0001Db無此案件:"+obj[0]);
						}
					}
				}
				objLists.clear();
			}else{
				logger.info("無醫療器材不良品廠商回覆稽催案件");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "啟動Med0203排程失敗" + e.getMessage();
			logger.info(errorMessage);
			resultMap.put("2", errorMessage);			
			r.setReturnMap(resultMap);
			r.setReturnMsg(errorMessage);
		}
		logger.info("======== 結束Med0203醫療器材不良品廠商回覆稽催作業排程 ========");
		return r;
	}

}
