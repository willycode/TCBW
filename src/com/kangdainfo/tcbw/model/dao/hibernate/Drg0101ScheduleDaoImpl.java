package com.kangdainfo.tcbw.model.dao.hibernate;

import java.util.HashMap;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Con1006Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0008Db;
import com.kangdainfo.tcbw.model.bo.MsgObject;
import com.kangdainfo.tcbw.model.dao.ScheduleDao;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class Drg0101ScheduleDaoImpl extends AbstractScheduleDaoImpl implements 	ScheduleDao {

	@Override
	public MsgObject executeSchedule() throws Exception {

		MsgObject r = new MsgObject();
		HashMap<String,String> resultMap = new HashMap<String,String>();
		logger.info("======== 啟動Drg0101藥品不良品廠商回覆稽催作業排程 ========");
		try {			
			String today = Datetime.getYYYMMDD();		
			
			/**
			 *   案件狀態為「案件評估中(交付中)」
			 *   回覆作業期限為7天，以通知日期(取「交付CAPA日期、展延日期、補件日期」最近者)起算，後續每週持續發送稽催信件。
			 **/			
			String hql1 =" select a.applNo,b.payDate,b.delayDate,b.addDocDate,b.modifyDate,b.fdaPostNo from Drg0001Db a,Drg0008Db b where 1=1"+ 
			             " and a.applNo=b.applNo and a.status ='41' and b.payDate < "+Common.sqlChar(today);
			java.util.List objLists = ServiceGetter.getInstance().getTcbwService().load(hql1);
			if(objLists != null && objLists.size()>0){				
				java.util.Iterator it = objLists.iterator();
				String days = View.getLookupField("select field2 from Sys0001Db where id=3"); //藥品廠商回覆稽催期限
				if("".equals(Common.get(days))) days ="7"; //預設7天
				
				while (it.hasNext()) {
					boolean isMail = false;
					String dayType ="";
					int addDocDays,delayDays,payDays = 0;
					Object[] obj = (Object[])it.next();					
					if(!"".equals(Common.get(obj[3]))){ //補件日期
						addDocDays = Common.getInt(Datetime.DateSubtraction(Common.get(obj[3]), today));
						if(addDocDays > Common.getInt(days) && addDocDays%7==1){
							isMail=true;
							dayType="3";
						}
					}
					else if(!"".equals(Common.get(obj[2]))){ //展延日期
						delayDays = Common.getInt(Datetime.DateSubtraction(Common.get(obj[2]), today));
						if(delayDays >= 1 && delayDays%7==1){
							isMail=true;
							dayType="2";
						}
					}
					else{ //交付CAPA日期
						payDays = Common.getInt(Datetime.DateSubtraction(Common.get(obj[1]), today));
						if(payDays >= 1 && payDays%7==1){
							isMail=true;
							dayType="1";
						}
					}
					//發送稽催通知
					if(isMail){
						Drg0001Db drg01 = (Drg0001Db)View.getObject(" from Drg0001Db where applNo="+Common.sqlChar(Common.get(obj[0])));
						if(drg01 != null){
							String drgLev = View.getLookupField("select drgLev from Drg0004Db where applNo="+Common.sqlChar(drg01.getApplNo()));
							java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
							Con1001Db c = null;
							if("02,03".indexOf(Common.get(drgLev)) != -1){
								c = (Con1001Db)View.getObject("from Con1001Db where mailID='DRG010011'");	//A+,A								
							}else{
								c = (Con1001Db)View.getObject("from Con1001Db where mailID='DRG010010'");	//B								
							}							
				    		String mailBody="",title="",mail="";				    		
				    		if(c!=null)
				    		{
								//找出廠商Mail				    			
				    			java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(" from Con1006Db where con1005Db.compegno="+Common.sqlChar(drg01.getApplicationID()));
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
				    			
				    			String applNo ="";
								if("02,03".indexOf(Common.get(drgLev)) != -1){
									applNo = drg01.getApplNo();						
								}else{
									//一年內本藥品「同批號的各案件編號」       
									String sql8 = " select manufactorNo,applNo from Drg0001Db where permitKey="+Common.sqlChar(drg01.getPermitKey())+" and permitNo="+Common.sqlChar(drg01.getPermitNo())+                      
						        	              " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()));	
						            java.util.List objList3 = ServiceGetter.getInstance().getCommonService().load(sql8+" order by id asc");						            
						            if(objList3!=null && objList3.size()>0)		
						            {        	
						            	java.util.Iterator it2 = objList3.iterator();       	
						            	while (it.hasNext())       	
						            	{       		
						            		Object[] itobj = (Object[])it2.next();       		
						            		applNo += itobj[1]+",";        	
						            	}       
						            }
						            if(objList3 !=null) objList3.clear();
						            if(applNo.length()>1) applNo = applNo.substring(0,applNo.length()-1); 
								}
				    			
				    			String notDate = "";
				    			if(!"".equals(Common.get(obj[4]))) 
				    				notDate = Common.get(obj[4]).substring(0,3)+"年"+Common.get(obj[4]).substring(3,5)+"月"+Common.get(obj[4]).substring(5,7)+"日";
				    			String finalDate = "";
				    			if("1".equals(dayType)){
				    				finalDate = Common.get(obj[1]).substring(0,3)+"年"+Common.get(obj[1]).substring(3,5)+"月"+Common.get(obj[1]).substring(5,7)+"日";
				    			}else if ("2".equals(dayType)){
				    				finalDate = Common.get(obj[2]).substring(0,3)+"年"+Common.get(obj[2]).substring(3,5)+"月"+Common.get(obj[2]).substring(5,7)+"日";
				    			}else{
				    				String addDocDate = Datetime.getDateAdd("d", Common.getInt(days), Common.get(obj[3]));
				    				finalDate = Common.get(addDocDate).substring(0,3)+"年"+Common.get(addDocDate).substring(3,5)+"月"+Common.get(addDocDate).substring(5,7)+"日";
				    			}
				    			java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID"); //許可證字
				    			
				    			//帶入相關欄位
				    			title = title.replace("[T_案號]", drg01.getApplNo());				    			
				    			mailBody=mailBody.replace("[F_案號]", applNo);
				    			mailBody=mailBody.replace("[F_公司名稱]", drg01.getApplicationName());
				    			mailBody=mailBody.replace("[F_產品名稱]", drg01.getChProduct()+" "+drg01.getEnProduct());
				    			mailBody=mailBody.replace("[F_許可證字號]", pkidMap.get(drg01.getPermitKey())+drg01.getPermitNo());
				    			mailBody=mailBody.replace("[F_通知日期]", notDate);
				    			mailBody=mailBody.replace("[F_FDA發文字號]", Common.get(obj[5]));
				    			mailBody=mailBody.replace("[F_回覆日期]", finalDate);
				    			
				    			pkidMap.clear();
				    			
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
				    			ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true, null, null, null,"DRG01",Common.get(drg01.getApplNo()).equals("")?Common.get(drg01.getId()):drg01.getApplNo());
				    			//TCBWCommon.setMailbackup("DRG01", Common.get(drg01.getId()), title , mailBody, drg01.getApplNo(), drg01.getStatus(), "SYS");	
				    		}				    		
						}else{
							logger.info("異常-Drg0001Db無此案件:"+obj[0]);
						}
					}
				}
				objLists.clear();
			}else{
				logger.info("無藥品不良品廠商回覆稽催案件");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "啟動Drg0101排程失敗" + e.getMessage();
			logger.info(errorMessage);
			resultMap.put("2", errorMessage);			
			r.setReturnMap(resultMap);
			r.setReturnMsg(errorMessage);
		}
		logger.info("======== 結束Drg0101藥品不良品廠商回覆稽催作業排程 ========");
		return r;
	}

}
