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
import com.kangdainfo.tcbw.model.bo.Drg8001Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.model.bo.Med9002Db;
import com.kangdainfo.tcbw.model.bo.MsgObject;
import com.kangdainfo.tcbw.model.bo.Sys0001Db;
import com.kangdainfo.tcbw.model.dao.ScheduleDao;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class Med0501ScheduleDaoImpl extends AbstractScheduleDaoImpl implements 	ScheduleDao {

	@Override
	public MsgObject executeSchedule() throws Exception {

		MsgObject r = new MsgObject();
		HashMap<String,String> resultMap = new HashMap<String,String>();
		logger.info("======== 啟動Med0501醫療器材定期安全監視表報告繳交通知(到期前)作業排程 ========");
		try {
			String today = Datetime.getYYYMMDD();			
			//讀取PSUR報告繳交預告通知期限
			String dateLine = View.getLookupField("select field9 from Sys0001Db where id = '4' ");
			if("".equals(Common.get(dateLine)))  dateLine ="1"; //預設1個月
			//計算需通知的日期
			String notifyDate = Datetime.getDateAdd("m", Common.getInt(dateLine), today);			
			String hql =" from Med9002Db where handstatus='01' and prehanddate="+Common.sqlChar(notifyDate);
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
			if(objList != null && objList.size()>0){
				java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");
				for(Object dtlObj : objList)
				{
					Med9002Db Med9002 = (Med9002Db)dtlObj;
					
					java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
					Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='MED050001'");									
												
		    		String mailBody="",title="",mail="";				    		
		    		if(c!=null)
		    		{                                                            
						//找出廠商Mail				    			
		    			java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(" from Con1006Db where con1005Db.compegno="+Common.sqlChar(Med9002.getMed9001Db().getApplicationID()));
						if(objList2!=null && objList2.size()>0)
						{
							for(Object dtlObj2 : objList2)
							{
								Con1006Db dtl = (Con1006Db)dtlObj2;
								mail += dtl.getCommonUser().getUserEmail()+",";
							}
						}
						if(objList2 !=null) objList2.clear();
						if(mail.length()>1) mail = mail.substring(0,mail.length()-1);
                        
		    			mailBody=c.getMailBody();
		    			title=c.getTitle();    			
		    			
		    			String permitNo = (!"".equals(pkidMap.get(Med9002.getMed9001Db().getPermitKey()))?pkidMap.get(Med9002.getMed9001Db().getPermitKey())+"字第":"")
		    			                  +Med9002.getMed9001Db().getPermitNo()+"號";
		    			//帶入相關欄位
		    			title = title.replace("[T_許可證字號]", permitNo);				    			
		    			mailBody=mailBody.replace("[F_許可證字號]", permitNo);
		    			mailBody=mailBody.replace("[F_中文品名]", Med9002.getMed9001Db().getChProduct());
		    			mailBody=mailBody.replace("[F_期數]", Common.get(Med9002.getReportIssueno()));
		    			mailBody=mailBody.replace("[F_繳交期限]", Common.formatYYYMMDD(Med9002.getPrehanddate(),4));
		    			
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
				}				
			}else{
				logger.info("無醫療器材定期安全監視表報告繳交通知(到期前)案件");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "啟動Med0501排程失敗" + e.getMessage();
			logger.info(errorMessage);
			resultMap.put("2", errorMessage);			
			r.setReturnMap(resultMap);
			r.setReturnMsg(errorMessage);
		}
		logger.info("======== 結束Med0501醫療器材定期安全監視表報告繳交通知(到期前)作業排程 ========");
		return r;
	}

}
