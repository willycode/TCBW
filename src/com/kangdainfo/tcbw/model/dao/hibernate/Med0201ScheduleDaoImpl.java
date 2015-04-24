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
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.model.bo.MsgObject;
import com.kangdainfo.tcbw.model.dao.ScheduleDao;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class Med0201ScheduleDaoImpl extends AbstractScheduleDaoImpl implements 	ScheduleDao {

	@Override
	public MsgObject executeSchedule() throws Exception {

		MsgObject r = new MsgObject();
		HashMap<String,String> resultMap = new HashMap<String,String>();
		logger.info("======== 啟動Med0201醫療器材不良品案件初評稽催作業排程 ========");
		try {			
			String today = Datetime.getYYYMMDD();		
			
			/**
			 *   案件狀態為「案件評估中(初評)」，不良事件類別為「不良品」。(案件編號MR02開頭)
			 *   評估作業期限為1個月，以轉送日期(最新一筆)起算，後續每日持續發送稽催信件。
			 *   系統發送「案件初評稽催通知」給案件評估者。
             *   如果案件的不良品事件等級為「A級」，郵件需副件密本給TFDA人員。
			 **/			
			String hql1 =" select a.med0001Db.applNo,a.med0001Db.badReactionResults,a.transDate from Med0006Db a where 1=1"+ 
			             " and a.med0001Db.status ='70' and a.med0001Db.applNo like 'MR02%' and a.transType='2' "+
			             " and a.id = (select max(id) from Med0006Db where a.med0001Db.id = med0001Db.id )";
			
			java.util.List objLists = ServiceGetter.getInstance().getTcbwService().load(hql1);
			if(objLists != null && objLists.size()>0){
				java.util.Iterator it = objLists.iterator();				
				String mons = View.getLookupField("select field4 from Sys0001Db where id=4"); //不良品初評稽催期限
				if("".equals(Common.get(mons))) mons ="1"; //預設1個月
				
				while (it.hasNext()) {
					boolean isMail = false;
					Object[] obj = (Object[])it.next();
					
					String dueDay = Datetime.getDateAdd("m", Common.getInt(mons), Common.get(obj[2]));						
					if(Common.getInt(today) > Common.getInt(dueDay)){
						isMail=true;						
					}	
					
					//發送稽催通知
					if(isMail){
						Med0001Db med01 = (Med0001Db)View.getObject(" from Med0001Db where applNo="+Common.sqlChar(Common.get(obj[0])));
						if(med01 != null){
							
							java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
							Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='MED020005'");									
														
				    		String mailBody="",title="",mail="";				    		
				    		if(c!=null)
				    		{
                                mail = View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(med01.getWorkers()));
                                
                                String eventClass = View.getLookupField("select eventClass from Med0008Db where med0001Db.id="+Common.getLong(med01.getId())+" order by id desc");
                                
                                if("1".equals(Common.get(eventClass))){ //郵件需副件密本給TFDA人員。
                                	java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(" from Con1013Db where con1012Db.code='MED02' ");
    								if(objList2!=null && objList2.size()>0)
    								{
    									for(Object dtlObj : objList2)
    									{
    										Con1013Db dtl = (Con1013Db)dtlObj;
    										if(!"".equals(Common.get(dtl.getCommonUser())))
    										   mail += ","+dtl.getCommonUser().getUserEmail();
    									}
    								}
    								if(objList2 !=null) objList2.clear();
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
				logger.info("無醫療器材不良品案件初評稽催案件");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "啟動Med0201排程失敗" + e.getMessage();
			logger.info(errorMessage);
			resultMap.put("2", errorMessage);			
			r.setReturnMap(resultMap);
			r.setReturnMsg(errorMessage);
		}
		logger.info("======== 結束Med0201醫療器材不良品案件初評稽催作業排程 ========");
		return r;
	}

}
