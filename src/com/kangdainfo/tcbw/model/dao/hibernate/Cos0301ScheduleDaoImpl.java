package com.kangdainfo.tcbw.model.dao.hibernate;

import java.util.HashMap;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Con1006Db;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.MsgObject;
import com.kangdainfo.tcbw.model.dao.ScheduleDao;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class Cos0301ScheduleDaoImpl extends AbstractScheduleDaoImpl implements 	ScheduleDao {

	@Override
	public MsgObject executeSchedule() throws Exception {

		MsgObject r = new MsgObject();
		HashMap<String,String> resultMap = new HashMap<String,String>();
		logger.info("======== 啟動Cos0301化粧品不良反應廠商回覆稽催作業排程 ========");
		try {			
			String today = Datetime.getYYYMMDD();		
			
			/**
			 *   案件狀態為「案件追蹤中」，不良事件類別為「不良反應」。(案件編號CB01開頭)
			 *   回覆作業期限為10天，系統發送「廠商回覆稽催通知」給廠商
			 **/			
			String hql1 =" select a.applNo,b.notifyDate from Cos0001Db a,Cos0010Db b where 1=1"+ 
			             " and a.applNo=b.applNo and a.status ='50' and a.applNo like 'CB01%' and b.isClose='N' "+
			             " and b.id = (select max(id) from Cos0009Db  where b.applNo=applNo )";
			java.util.List objLists = ServiceGetter.getInstance().getTcbwService().load(hql1);
			if(objLists != null && objLists.size()>0){
				java.util.Iterator it = objLists.iterator();
				String days = View.getLookupField("select field1 from Sys0001Db where id=1"); //廠商回覆稽催期限
				if("".equals(Common.get(days))) days ="10"; //預設10天
				while (it.hasNext()) {
					Object[] obj = (Object[])it.next();
					int notifyDays = 0;
					notifyDays = Common.getInt(Datetime.DateSubtraction(Common.get(obj[1]), today));
					Con1001Db c = null;
					if(notifyDays == Common.getInt(days)){ //廠商回覆稽催期限
						c = (Con1001Db)View.getObject("from Con1001Db where mailID='COS030004'");
					}
					if(c != null){
						java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
						String mailBody="",title="",mail="";
						Cos0001Db cos01 = (Cos0001Db)View.getObject(" from Cos0001Db where applNo="+Common.sqlChar(Common.get(obj[0])));
						if(cos01 != null){
							
							//找出廠商Mail				    			
			    			java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(" from Con1006Db where con1005Db.compegno="+Common.sqlChar(cos01.getManufactorID()));
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
			    			
			    			mailBody=c.getMailBody();
			    			title=c.getTitle();
			    			
  			
			    			String notRepDate = "";
			    			if(!"".equals(Common.get(cos01.getNotifierRepDate()))) 
			    				notRepDate = Common.get(cos01.getNotifierRepDate()).substring(0,3)+"年"+Common.get(cos01.getNotifierRepDate()).substring(3,5)+"月"+Common.get(cos01.getNotifierRepDate()).substring(5,7)+"日";
                            String notifyDate ="";
			    			if(!"".equals(Common.get(obj[1]))) 
			    				notifyDate = Common.get(obj[1]).substring(0,3)+"年"+Common.get(obj[1]).substring(3,5)+"月"+Common.get(obj[1]).substring(5,7)+"日";
			    			String finalDate = Datetime.getDateAdd("d", Common.getInt(days), Common.get(obj[1]));
			    			if(!"".equals(Common.get(finalDate)))
		    				    finalDate = Common.get(finalDate).substring(0,3)+"年"+Common.get(finalDate).substring(3,5)+"月"+Common.get(finalDate).substring(5,7)+"日";
			    			
			    			//帶入相關欄位
			    			title = title.replace("[T_案號]", cos01.getApplNo());				    			
			    			mailBody=mailBody.replace("[F_公司名稱]", cos01.getManufactorName());
			    			mailBody=mailBody.replace("[F_接獲通報日期]", notRepDate);
			    			mailBody=mailBody.replace("[F_品名中文]", cos01.getChProduct());
			    			mailBody=mailBody.replace("[F_通知日期]", notifyDate);
			    			mailBody=mailBody.replace("[F_回覆日期]", finalDate);
			    					    				
			    			
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
			    			if(mailAddress != null){			    			
			    				ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true, null, null, null);			    			
			    				//TCBWCommon.setMailbackup("COS03", Common.get(cos01.getId()), title , mailBody, cos01.getApplNo(), cos01.getStatus(), "SYS");
			    			}			    			
						}else{
							logger.info("異常-Cos0001Db無此案件:"+obj[0]);
						}
					}
				}
			}else{
				logger.info("無化粧品不良反應廠商回覆稽催案件");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "啟動Cos0301排程失敗" + e.getMessage();
			logger.info(errorMessage);
			resultMap.put("2", errorMessage);			
			r.setReturnMap(resultMap);
			r.setReturnMsg(errorMessage);
		}
		logger.info("======== 結束Cos0301化粧品不良反應廠商回覆稽催作業排程 ========");
		return r;
	}

}
