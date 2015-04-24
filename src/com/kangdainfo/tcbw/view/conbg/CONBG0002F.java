package com.kangdainfo.tcbw.view.conbg;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.Validate;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con1004Db;
import com.kangdainfo.tcbw.model.bo.Con1006Db;
import com.kangdainfo.tcbw.model.bo.Con1013Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos0009Db;
import com.kangdainfo.tcbw.model.bo.Cos0010Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0007Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.model.bo.Drg7001Db;
import com.kangdainfo.tcbw.model.bo.Drg7003Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med7002Db;
import com.kangdainfo.tcbw.model.bo.Med9002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONBG0002F{
	
	public static String getTitle(String mailID, String id, String title) throws Exception
	{
		java.util.Map<String, String> cosMap = TCBWCommon.getCommonCodeMap("CCT");
		//【案件評估通知】不良反應案件審核
		if("MED010005".equals(Common.get(mailID)))
		{
			Med0001Db med0001Db = (Med0001Db)View.getObject(" from Med0001Db where id in(" + id+")");
			
			String field="";
			
			if(med0001Db!=null)
			{
				field=med0001Db.getApplNo();
			}
			title = Common.get(title).replace("[T_案號]",field);
		}
		
		
		//【廠商通知】不良品通知
		if("MED020004".equals(Common.get(mailID)))
		{
			Med0001Db med0001Db = (Med0001Db)View.getObject(" from Med0001Db where id in(" + id+")");
			
			String field="";
			
			if(med0001Db!=null)
			{
				field="R"+Datetime.getYYYMMDD()+"-"+med0001Db.getMedPermitFirm();
			}
			
			title = Common.get(title).replace("[T_信件編號-公司名稱]",field);
		}
		
		// 化粧品退件通知
		if("COS010001".equals(Common.get(mailID))){
			String chProduct = View.getLookupField(" select chProduct from Cos0001Db where id = " + Common.getLong(id));
			title = Common.get(title).replace("[T_品名]", chProduct);
		}
		
		if("COS020004".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db) View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(null != cos0001Db){
				String applNoCOS = Common.get(cosMap.get(Common.get(cos0001Db.getCosType()))).equals("")?Common.get("不良品與不良反應"):cosMap.get(Common.get(cos0001Db.getCosType())) + Common.get(cos0001Db.getApplNo());
				title = Common.get(title).replace("[T_案號]", applNoCOS);
			}
		}
		
		if("COS020005".equals(Common.get(mailID)) || "COS030002".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db) View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(null != cos0001Db){
				String applNoCOS = Common.get(cosMap.get(Common.get(cos0001Db.getCosType()))).equals("")?Common.get("不良品與不良反應"):cosMap.get(Common.get(cos0001Db.getCosType())) + Common.get(cos0001Db.getApplNo());
				title = Common.get(title).replace("[T_案號]", applNoCOS);
			}
		}
		
		if("COS020006".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db) View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(null != cos0001Db){
				String applNoCOS = Common.get(cosMap.get(Common.get(cos0001Db.getCosType()))).equals("")?Common.get("不良品與不良反應"):cosMap.get(Common.get(cos0001Db.getCosType())) + Common.get(cos0001Db.getApplNo());
				title = Common.get(title).replace("[T_案號]", applNoCOS);
			}
			
		}
		if("COS020007".equals(Common.get(mailID)) || "COS020008".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db) View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(null != cos0001Db){
				String applNoCOS = Common.get(cosMap.get(Common.get(cos0001Db.getCosType()))).equals("")?Common.get("不良品與不良反應"):cosMap.get(Common.get(cos0001Db.getCosType())) + Common.get(cos0001Db.getApplNo());
				title = Common.get(title).replace("[T_案號]", applNoCOS);
			}
		}
		if("DRG010006".equals(Common.get(mailID)))
		{
			Drg0001Db drg0001Db = (Drg0001Db)View.getObject(" from Drg0001Db where id ="+ Common.getLong(id));
			
			String field="";
			
			if(drg0001Db!=null)
			{
				field=drg0001Db.getApplNo();
			}
			title = Common.get(title).replace("[T_案號]",field);
		}
		if("DRG010007".equals(Common.get(mailID)))
		{
			Drg0001Db drg0001Db = (Drg0001Db)View.getObject(" from Drg0001Db where id ="+ Common.getLong(id));
			
			String field="";
			
			if(drg0001Db!=null)
			{
				field=drg0001Db.getApplNo();
			}
			title = Common.get(title).replace("[T_案號]",field);
		}
		if("DRG010008".equals(Common.get(mailID)))
		{
			Drg0001Db drg0001Db = (Drg0001Db)View.getObject(" from Drg0001Db where id ="+ Common.getLong(id));
			
			String field="";
			
			if(drg0001Db!=null)
			{
				field=drg0001Db.getApplNo();
			}
			title = Common.get(title).replace("[T_案號]",field);
		}
		if("MED060001".equals(Common.get(mailID)) || "MED060002".equals(Common.get(mailID)))
		{
			Med7001Db med7001Db = (Med7001Db)View.getObject(" from Med7001Db where id in (select med7001Db.id from Med7002Db where id = "+ Common.getLong(id)+")");
			title = Common.get(title).replaceAll("#medwapplno#",med7001Db.getApplNo())
									.replaceAll("#medwchproduct#", med7001Db.getChProduct())
									.replaceAll("#medwsituation#", View.getCommonCodeName("CONWARNING", med7001Db.getSituation()));
		}
		
		if("MED060004".equals(mailID))
		{
			Med7001Db obj = (Med7001Db)View.getObject(" from Med7001Db where id in (select med7001Db.id from Med7002Db where id = " + Common.getLong(id)+ ")");
			
			if(obj!=null)
			{
				title=Common.get(title).replace("[T_警訊編號]", Common.get(obj.getApplNo()))
				                       .replace("[T_產品中文品名]",obj.getChProduct());
			}
		}
		
		return title;
	}
	
	// 不良品缺陷描述
	public static String getDiscriptCos0003Db(Cos0001Db cos0001Db) throws Exception {
		StringBuffer sb = new StringBuffer();
		if(cos0001Db.getCos0003Dbs()!=null && cos0001Db.getCos0003Dbs().size()>0){
			java.util.Map<String, String> subCodeNameMap = new java.util.HashMap<String, String>(); 
			java.util.List<Cos1001Db> cos1001DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where isStop = 'N'");
			if(cos1001DbList!=null && cos1001DbList.size()>0){
				for(Cos1001Db cos1001Db : cos1001DbList){
					subCodeNameMap.put(Common.get(cos1001Db.getDpdKind()), Common.get(cos1001Db.getDpdKindName()));
				}
				cos1001DbList.clear();
			}
			for(Object cos0003DbObj : cos0001Db.getCos0003Dbs()){
				Cos0003Db cos0003Db = (Cos0003Db)cos0003DbObj;
				if(sb.toString().length() > 0){
					sb.append("、");
				}
				sb.append(Common.get(subCodeNameMap.get(Common.get(cos0003Db.getSubCode()))));
			}
			subCodeNameMap.clear();
		}
		return sb.toString();
	}
	
	//藥品警訊通知函，因底下多筆的部份需過濾不同廠商，所以抽出來另外寫
	public static String getMailBody(String mailID,String id,String mailBody, String url,String applicationId) throws Exception
	{
		if("DRG030001".equals(mailID)){
			Drg7001Db obj = (Drg7001Db)View.getObject(" from Drg7001Db where id = " + Common.getLong(id));
			if(obj!=null){
				mailBody=mailBody.replace("#drgwpubldept#", View.getCommonCodeName("CONPUBLDEPT", Common.get(obj.getPublDept())));
				mailBody=mailBody.replace("#drgwwebs#", Common.get(obj.getDatasourWebSite()));
				mailBody=mailBody.replace("#drgwchproduct#", Common.get(obj.getChProduct()));
				mailBody=mailBody.replace("#drgwdruggist#", Common.get(obj.getDruggist()));
				mailBody=mailBody.replace("#drgwmanufactory#", Common.get(obj.getManufactorName()));
				mailBody=mailBody.replace("#drgwlotno#", Common.get(obj.getLotNo()));
				mailBody=mailBody.replace("#drgweventdesc#", Common.get(obj.getEventDesc()));
				mailBody=mailBody.replace("#drgwlotno#", Common.get(obj.getLotNo()));
				
				String details = "";
				if(null != obj.getDrg7003Dbs() && !obj.getDrg7003Dbs().isEmpty()){
					int c = 1;
					for(Object dtlObj:obj.getDrg7003Dbs()){
						Drg7003Db drg73 = (Drg7003Db)dtlObj;
						if(applicationId.equals(drg73.getApplicationId())) {
							List<String[]> rs = getVW_ForADR_TBMLIC(Common.get(drg73.getPermitKey()),Common.get(drg73.getPermitNo()));
							if(null != rs && rs.size() > 0) {
								String LICEKC="";//許可證字
								String LICID1="";//許可證號
								String CHNAME="";//中文品名
								String ENNAME="";//英文品名
								String APPNAME="";//申請商
								String FACTNAME="";//製造廠
								for (int i=0;i<rs.size();i++){
									Object[] dtl = rs.get(i);
									LICEKC = Common.get(dtl[0]);
									LICID1 = Common.get(dtl[1]);
									CHNAME = Common.get(dtl[2]);
									ENNAME = Common.get(dtl[3]);
									APPNAME = Common.get(dtl[4]);
									FACTNAME = Common.get(dtl[5]);
								}
								details += "<p>(" + c + ") " + LICEKC +"字第"+ LICID1 + "號，" + CHNAME + "，" + ENNAME + "，持有商：" + APPNAME + "，製造廠：" + FACTNAME + "。</p>";
							}
							c++;
						}
					}
				}
				
				mailBody=mailBody.replace("#datas#", details);
			}
		}
		return mailBody;
	}
	
	
	
	public static String getMailBody(String mailID,String id,String mailBody, String url) throws Exception
	{
		java.util.Map<String, String> cosMap = TCBWCommon.getCommonCodeMap("CCT");
		if("COS010001".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(cos0001Db != null){
				mailBody = Common.get(mailBody).replace("[F_通報者]", Common.get(cos0001Db.getNotifierName()));
				mailBody = Common.get(mailBody).replace("[F_日期]", Common.get(cos0001Db.getNotifierRevDate()));
				mailBody = Common.get(mailBody).replace("[F_品名]", Common.get(cos0001Db.getChProduct()));
				mailBody = Common.get(mailBody).replace("[F_不良品缺陷描述]", getDiscriptCos0003Db(cos0001Db));
			}
		}
		
		if("COS020001".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(cos0001Db != null){
				mailBody = Common.get(mailBody).replace("[F_通報者]", Common.get(cos0001Db.getNotifierName()));
				mailBody = Common.get(mailBody).replace("[F_日期]", Common.formatYYYMMDD(Common.get(cos0001Db.getNotifierRepDate()), 2));
			}
		}
		
		if("COS020005".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(cos0001Db != null){
				mailBody = Common.get(mailBody).replace("[F_公司名稱]", Common.get(cos0001Db.getManufactorName()));
				mailBody = Common.get(mailBody).replace("[F_發文日期]", Common.formatYYYMMDD(Datetime.getYYYMMDD(), 2));
				mailBody = Common.get(mailBody).replace("[F_案號]", Common.get(cos0001Db.getApplNo()));
				mailBody = Common.get(mailBody).replace("[F_日期]", Common.formatYYYMMDD(Common.get(cos0001Db.getNotifierRepDate()), 2));
				mailBody = Common.get(mailBody).replace("[F_商品名]", Common.get(cos0001Db.getChProduct()));
				mailBody = Common.get(mailBody).replace("[F_製造日期]", Common.get(cos0001Db.getManufactorNo()));
				mailBody = Common.get(mailBody).replace("[F_購買日期]", Common.get(cos0001Db.getTradeDate()));
				mailBody = Common.get(mailBody).replace("[F_販賣通路]", 
						Common.get(cos0001Db.getTraffickWay()).equals("10")?Common.get(cos0001Db.getTraffickWayOther()):View.getCommonCodeName("CSP", cos0001Db.getTraffickWay()));
				mailBody = Common.get(mailBody).replace("[F_商家名稱]", Common.get(cos0001Db.getBusinessName()));
				mailBody = Common.get(mailBody).replace("[F_商家所在地]", View.getCommonCodeName("CTY", cos0001Db.getTradePlace()));
				mailBody = Common.get(mailBody).replace("[F_不良品缺陷之描述]", getDiscriptCos0003Db(cos0001Db));
				mailBody = Common.get(mailBody).replace("[F_詳述不良品缺陷狀況]", Common.get(cos0001Db.getOtherExplain()));
				
				boolean flag = true;
				if(!"".equals(Common.get(cos0001Db.getForeignApplNo()))){
					Cos0001Db anotherCos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where applNo = " + Common.sqlChar(cos0001Db.getForeignApplNo()));
					if(anotherCos0001Db!=null && anotherCos0001Db.getCos0002Dbs()!=null && anotherCos0001Db.getCos0002Dbs().size()>0){
						for(Object dtlObj : anotherCos0001Db.getCos0002Dbs()){
							if(flag){
								Cos0002Db dtl = (Cos0002Db)dtlObj;
								mailBody = Common.get(mailBody).replace("[F_不良反應症狀]", Common.get(dtl.getNonSeriousOther()));
								mailBody = Common.get(mailBody).replace("[F_不良反應結果]", View.getCommonCodeName("CAC", Common.get(dtl.getAdverseCondition())));
								mailBody = Common.get(mailBody).replace("[F_不良反應描述]", Common.get(dtl.getNonSeriousDis()));
								flag = false;
							}
						}
					}
				}
				if(flag){
					mailBody = Common.get(mailBody).replace("[F_不良反應症狀]", "");
					mailBody = Common.get(mailBody).replace("[F_不良反應結果]", "");
					mailBody = Common.get(mailBody).replace("[F_不良反應描述]", "");
				}
			}
		}
		
		if("COS020006".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(cos0001Db != null){
				String applNoCOS = Common.get(cosMap.get(Common.get(cos0001Db.getCosType()))).equals("")?Common.get("不良品與不良反應"):cosMap.get(Common.get(cos0001Db.getCosType())) + Common.get(cos0001Db.getApplNo());
				mailBody = Common.get(mailBody).replace("[F_公司名稱]", Common.get(cos0001Db.getManufactorName()));
				
				Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where applNo = " + Common.sqlChar(cos0001Db.getApplNo()) + " order by id desc ");
				if(cos0009Db != null){
					mailBody = Common.get(mailBody).replace("[F_回覆日期]", Common.formatYYYMMDD(Common.get(cos0009Db.getReplyDate()), 2));
				}else{
					mailBody = Common.get(mailBody).replace("[F_回覆日期]", "");
				}
				
				mailBody = Common.get(mailBody).replace("[F_案號]", applNoCOS);
			}
		}
		
		if("COS020007".equals(Common.get(mailID)) || "COS020008".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(cos0001Db != null){
				mailBody = Common.get(mailBody).replace("[F_案號]", Common.get(cos0001Db.getApplNo()));
				mailBody = Common.get(mailBody).replace("[F_公司名稱]", Common.get(cos0001Db.getManufactorName()));
			}
		}
		
		if("COS030001".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(cos0001Db != null){
				mailBody = Common.get(mailBody).replace("[F_通報者]", Common.get(cos0001Db.getNotifierName()));
				mailBody = Common.get(mailBody).replace("[F_日期]", Common.formatYYYMMDD(Common.get(cos0001Db.getNotifierRepDate()), 2));
			}
		}
		
		if("COS030002".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(cos0001Db != null){
				mailBody = Common.get(mailBody).replace("[F_公司名稱]", Common.get(cos0001Db.getManufactorName()));
				mailBody = Common.get(mailBody).replace("[F_發文日期]", Common.formatYYYMMDD(Datetime.getYYYMMDD(), 2));
				mailBody = Common.get(mailBody).replace("[F_案號]", Common.get(cos0001Db.getApplNo()));
				mailBody = Common.get(mailBody).replace("[F_日期]", Common.formatYYYMMDD(Common.get(cos0001Db.getNotifierRepDate()), 2));
				mailBody = Common.get(mailBody).replace("[F_商品名]", Common.get(cos0001Db.getChProduct()));
				mailBody = Common.get(mailBody).replace("[F_製造日期]", Common.get(cos0001Db.getManufactorNo()));
				mailBody = Common.get(mailBody).replace("[F_購買日期]", Common.get(cos0001Db.getTradeDate()));
				mailBody = Common.get(mailBody).replace("[F_販賣通路]", 
						Common.get(cos0001Db.getTraffickWay()).equals("10")?Common.get(cos0001Db.getTraffickWayOther()):View.getCommonCodeName("CSP", cos0001Db.getTraffickWay()));
				mailBody = Common.get(mailBody).replace("[F_商家名稱]", Common.get(cos0001Db.getBusinessName()));
				mailBody = Common.get(mailBody).replace("[F_商家所在地]", View.getCommonCodeName("CTY", cos0001Db.getTradePlace()));
				
				if(cos0001Db.getCos0002Dbs()!=null && cos0001Db.getCos0002Dbs().size()>0){
					boolean flag1 = true;
					for(Object dtlObj : cos0001Db.getCos0002Dbs()){
						if(flag1){
							Cos0002Db dtl = (Cos0002Db)dtlObj;
							mailBody = Common.get(mailBody).replace("[F_不良反應症狀]", Common.get(dtl.getNonSeriousOther()));
							mailBody = Common.get(mailBody).replace("[F_不良反應結果]", View.getCommonCodeName("CAC", Common.get(dtl.getAdverseCondition())));
							mailBody = Common.get(mailBody).replace("[F_不良反應描述]", Common.get(dtl.getNonSeriousDis()));
							flag1 = false;
						}
					}
				}else{
					mailBody = Common.get(mailBody).replace("[F_不良反應症狀]", "");
					mailBody = Common.get(mailBody).replace("[F_不良反應結果]", "");
					mailBody = Common.get(mailBody).replace("[F_不良反應描述]", "");
				}
				
				boolean flag2 = true;
				if(!"".equals(Common.get(cos0001Db.getForeignApplNo()))){
					Cos0001Db anotherCos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where applNo = " + Common.sqlChar(cos0001Db.getForeignApplNo()));
					if(anotherCos0001Db != null){
						mailBody = Common.get(mailBody).replace("[F_不良品缺陷之描述]", getDiscriptCos0003Db(anotherCos0001Db));
						mailBody = Common.get(mailBody).replace("[F_詳述不良品缺陷狀況]", Common.get(anotherCos0001Db.getOtherExplain()));
						flag2 = false;
					}	
				}
				if(flag2){
					mailBody = Common.get(mailBody).replace("[F_不良品缺陷之描述]", "");
					mailBody = Common.get(mailBody).replace("[F_詳述不良品缺陷狀況]", "");
				}
			}
		}
		
		if("COS030003".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(cos0001Db != null){
				mailBody = Common.get(mailBody).replace("[F_公司名稱]", Common.get(cos0001Db.getManufactorName()));
				mailBody = Common.get(mailBody).replace("[F_發文日期]", Common.formatYYYMMDD(Datetime.getYYYMMDD(), 2));
				mailBody = Common.get(mailBody).replace("[F_日期]", Common.formatYYYMMDD(Common.get(cos0001Db.getNotifierRepDate()), 2));
				mailBody = Common.get(mailBody).replace("[F產品名]", Common.get(cos0001Db.getChProduct()));
				
				Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where applNo = " + Common.sqlChar(cos0001Db.getApplNo()) + " order by id desc ");
				if(cos0010Db != null){
					mailBody = Common.get(mailBody).replace("[F_發送日期]", Common.formatYYYMMDD(Common.get(cos0010Db.getNotifyDate()), 2));
					mailBody = Common.get(mailBody).replace("[F_回覆日期]", Common.formatYYYMMDD(Common.get(cos0010Db.getReplyDate()), 2));
				}else{
					mailBody = Common.get(mailBody).replace("[F_發送日期]", "___年___月___日");
					mailBody = Common.get(mailBody).replace("[F_回覆日期]", "___年___月___日");
				}
			}
		}
		
		//醫療器材不良事件通報 > > 受理 > >【不良品通報】退件通知
		if("MED010002".equals(mailID))
		{
			Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				mailBody=mailBody.replace("[F_通報中心接獲通報日期]", Common.formatYYYMMDD(obj.getNotifierRevDate(),2));
				mailBody=mailBody.replace("[F_件數]", "1");
			}	
		}
		
		//醫療器材不良事件通報 > > 受理 > >【不良品通報】不予收案
		if("MED010003".equals(mailID))
		{
			Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				mailBody=mailBody.replace("[F_通報中心接獲通報日期]",  Common.formatYYYMMDD(obj.getNotifierRevDate(),2));
				mailBody=mailBody.replace("[F_件數]", "1");
			}	
		}
		
		//醫療器材不良事件通報 > > 處理中 > >補件
		if("MED010004".equals(mailID))
		{
			Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				mailBody=mailBody.replace("[F_通報中心接獲通報日期]", Common.formatYYYMMDD(obj.getNotifierRevDate(),2));
				mailBody=mailBody.replace("[F_件數]", "1");
				String date=Datetime.getDateSubtraction("d",-7,Datetime.getYYYMMDD());
				mailBody=mailBody.replace("[F_回覆日期]", Common.formatYYYMMDD(date,2));
			}	
		}
		
		//醫療器材不良事件通報 > > 處理中 > >補件
		if("MED010010".equals(mailID))
		{
			Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				mailBody=mailBody.replace("[F_通報中心接獲通報日期]", Common.formatYYYMMDD(obj.getNotifierRevDate(),2));
				mailBody=mailBody.replace("[F_件數]", "1");
				String date=Datetime.getDateSubtraction("d",-7,Datetime.getYYYMMDD());
				mailBody=mailBody.replace("[F_回覆日期]", Common.formatYYYMMDD(date,2));
			}	
		}
		
		//醫療器材不良事件通報 > > 處理中 > >補件
		if("MED010011".equals(mailID))
		{
			Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				mailBody=mailBody.replace("[F_通報中心接獲通報日期]", Common.formatYYYMMDD(obj.getNotifierRevDate(),2));
				mailBody=mailBody.replace("[F_件數]", "1");
				String date=Datetime.getDateSubtraction("d",-7,Datetime.getYYYMMDD());
				mailBody=mailBody.replace("[F_回覆日期]", Common.formatYYYMMDD(date,2));
			}	
		}
		
		//【廠商通知】不良品通知
		if("MED020004".equals(mailID))
		{
			
		   String hql=" from Med0001Db where id in(" + id+")";
			
		   java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql);
		  
		   String str ="";
		   
		   if(objList!=null && objList.size()>0)
		   {
			  java.util.Iterator it = objList.iterator();
							
			  while (it.hasNext()) 
			  {					
				  Med0001Db o = (Med0001Db) it.next();
				  str+=o.getApplNo()+",";			
			  }    	
			  
			  if(str.length()>0)
				 str=str.substring(0, str.length()-1);
			  
		   }
		   
		   mailBody=mailBody.replace("[F_年度]", Datetime.getYYY());
		   mailBody=mailBody.replace("[F_案號]", str);
		}
		
		
		//醫療器材臨床試驗不良事件通報--受理
		if("MED030001".equals(mailID))
		{
			Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				mailBody=mailBody.replace("[F_通報中心接獲通報日期]", Common.formatYYYMMDD(obj.getNotifierRevDate(),2));
				mailBody=mailBody.replace("[F_件數]", "1");
				mailBody=mailBody.replace("[F_案號]", obj.getApplNo());
			}	
		}
		
		//醫療器材臨床試驗不良事件通報--退件
		if("MED030002".equals(mailID))
		{
			Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				mailBody=mailBody.replace("[F_通報中心接獲通報日期]", Common.formatYYYMMDD(obj.getNotifierRevDate(),2));
				mailBody=mailBody.replace("[F_件數]", "1");
			}	
		}
		
		//醫療器材臨床試驗不良事件通報--補件
		if("MED030003".equals(mailID))
		{
			Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				mailBody=mailBody.replace("[F_通報中心接獲通報日期]", Common.formatYYYMMDD(obj.getNotifierRevDate(),2));
				mailBody=mailBody.replace("[F_件數]", "1");
				String date=Datetime.getDateSubtraction("d",-7,Datetime.getYYYMMDD());
				mailBody=mailBody.replace("[F_回覆日期]", Common.formatYYYMMDD(date,2));
			}	
		}
		
		
		if("DRG010002".equals(mailID) || "DRG010003".equals(mailID))
		{
			Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
    			java.util.Map<String, String> drgdpdMap = TCBWCommon.getCommonCodeMap("DRGDPD");
    			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(" from Drg0002Db where 1=1 and drg0001Db.id="+Common.getLong(obj.getId())+" order by id asc");
    			String mainCode ="";
    			if(objList!=null && objList.size()>0)
    			{
    				java.util.Iterator it = objList.iterator();
    								
    				while (it.hasNext()) 
    				{					
    					Drg0002Db o = (Drg0002Db) it.next();
    					mainCode += Common.get(drgdpdMap.get(o.getMainCode())+",");    					
    				}    				
    			}
    			drgdpdMap.clear();
    			if(mainCode !=null && !"".equals(mainCode) && mainCode.length() > 0) mainCode = mainCode.substring(0,mainCode.length()-1);
    			
    			String notdate = "";
    			if(obj.getNotifierRepDate()!=null)  notdate = obj.getNotifierRepDate().substring(3,5)+"月"+obj.getNotifierRepDate().substring(5,7)+"日";
    			
				mailBody=mailBody.replace("[F_接獲通報日期]", notdate);
				mailBody=mailBody.replace("[F_中文品名]", obj.getChProduct());
				mailBody=mailBody.replace("[F_英文品名]", obj.getEnProduct());
				mailBody=mailBody.replace("[F_不良品缺陷]", mainCode);
			}	
		}
		
		if("DRG010004".equals(mailID))
		{
			Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
    			java.util.Map<String, String> drgdpdMap = TCBWCommon.getCommonCodeMap("DRGDPD");
    			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(" from Drg0002Db where 1=1 and drg0001Db.id="+Common.getLong(obj.getId())+" order by id asc");
    			String mainCode ="";
    			if(objList!=null && objList.size()>0)
    			{
    				java.util.Iterator it = objList.iterator();
    								
    				while (it.hasNext()) 
    				{					
    					Drg0002Db o = (Drg0002Db) it.next();
    					mainCode += Common.get(drgdpdMap.get(o.getMainCode())+",");    					
    				}    				
    			}
    			drgdpdMap.clear();
    			if(mainCode !=null && !"".equals(mainCode) && mainCode.length() > 0) mainCode = mainCode.substring(0,mainCode.length()-1);
    			
    			String notpDate = "";
    			if(obj.getNotifierRepDate()!=null && !"".equals(obj.getNotifierRepDate()))  notpDate = obj.getNotifierRepDate().substring(3,5)+"月"+obj.getNotifierRepDate().substring(5,7)+"日";
    			
    			String enrollDate = "";
    			if(obj.getEnrolledDate()!=null && !"".equals(obj.getEnrolledDate()))  enrollDate = obj.getEnrolledDate().substring(3,5)+"月"+obj.getEnrolledDate().substring(5,7)+"日";
    			
				mailBody=mailBody.replace("[F_通報日期]", notpDate);
				mailBody=mailBody.replace("[F_中文品名]", obj.getChProduct());
				mailBody=mailBody.replace("[F_英文品名]", obj.getEnProduct());
				mailBody=mailBody.replace("[F_不良品缺陷]", mainCode);
				mailBody=mailBody.replace("[F_案號]", obj.getApplNo());
				mailBody=mailBody.replace("[F_接獲通報日期]", enrollDate);
			}	
		}
		
		if("DRG010006".equals(mailID))
		{
			String yyymmdd = Datetime.getYYYMMDD();
			
			Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				String sql = " from Drg0002Db where 1=1 and drg0001Db.id="+Common.getLong(obj.getId());				
			    java.util.List objList = ServiceGetter.getInstance().getCommonService().load(sql+" order by id desc");				
			    String subCodeList =""; 				
			    if(objList!=null && objList.size()>0)				
			    {
			    	 java.util.Iterator it = objList.iterator();
			    	 while (it.hasNext()){					
							Drg0002Db o = (Drg0002Db) it.next();						
							String[] subList = o.getSubCode().split(",");
							for(int j=0; j<subList.length; j++){
								if(Common.get(subList[j]) != ""){							
									subCodeList += "subCode like ('%"+subList[j]+"%') or ";
								}						
							}				
			    	 }		     
			    }
			    if(objList!=null) objList.clear();
			    if(subCodeList.length() > 3) subCodeList = subCodeList.substring(0, subCodeList.length()-3);
				
				String hql = " select id,applNo from Drg0001Db where permitKey="+Common.sqlChar(obj.getPermitKey())+" and permitNo="+Common.sqlChar(obj.getPermitNo())+
                             " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()));
				if(null != subCodeList && !"".equals(subCodeList)){
					hql +=  " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
				}
				java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(hql);
				String applNoList ="";
				if(objList2!=null && objList2.size()>0)				
			    {
					 java.util.Iterator it = objList2.iterator();
			    	 while (it.hasNext()){					
			    		 Object[] obj2 = (Object[])it.next();
			    		 applNoList += obj2[1]+"、";
			    	 }
			    }
				if(objList2!=null) objList2.clear();
				if(applNoList.length() > 0) applNoList = applNoList.substring(0, applNoList.length()-1);
				
				String notpDate = "";
    			if(obj.getNotifierRepDate()!=null)  notpDate = obj.getNotifierRepDate().substring(0,3)+"年"+obj.getNotifierRepDate().substring(3,5)+"月"+obj.getNotifierRepDate().substring(5,7)+"日";
    			
    			String replyDate = Datetime.getDateAdd("m", 1, yyymmdd);
    			if(replyDate!=null)  replyDate = replyDate.substring(0,3)+"年"+replyDate.substring(3,5)+"月"+replyDate.substring(5,7)+"日";
    			    			
    			mailBody=mailBody.replace("[F_案號]", obj.getApplNo());
    			mailBody=mailBody.replace("[F_接獲通報日期]", notpDate);
				mailBody=mailBody.replace("[F_案件編號]", applNoList);
				mailBody=mailBody.replace("[F_回覆日期]", replyDate);
			}	
		}
		
		if("DRG010007".equals(mailID) || "DRG010008".equals(mailID))
		{
			String yyymmdd = Datetime.getYYYMMDD();
			
			Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				
				String notpDate = "";
    			if(obj.getNotifierRepDate()!=null)  notpDate = obj.getNotifierRepDate().substring(0,3)+"年"+obj.getNotifierRepDate().substring(3,5)+"月"+obj.getNotifierRepDate().substring(5,7)+"日";
    			
   			    			
    			mailBody=mailBody.replace("[F_案號]", obj.getApplNo());    			
    			mailBody=mailBody.replace("[F_公司名稱]", obj.getApplicationName());
				mailBody=mailBody.replace("[F_接獲通報日期]", notpDate);
			}	
		}

		
		if("DRG010009".equals(mailID))
		{
			String yyymmdd = Datetime.getYYYMMDD();
			
			Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				java.util.Map<String, String> drgpkMap = TCBWCommon.getCommonCodeMap("DRGPKID");
    			
    			String payDate ="",survey="",precaution="",replyDate="";
    			
    			Drg0007Db drg07 = (Drg0007Db)View.getObject(" from Drg0007Db where applNo="+Common.sqlChar(obj.getApplNo())+" order by id desc");
    			if(drg07 != null){    			
    				if(drg07.getAssessDate()!=null)  
    					payDate = drg07.getAssessDate().substring(0,3)+"年"+drg07.getAssessDate().substring(3,5)+"月"+drg07.getAssessDate().substring(5,7)+"日";
    				survey = drg07.getSurvey();   
        			precaution = drg07.getPrecaution();
    			}
    			
    			replyDate = Datetime.getDateAdd("d", 7, yyymmdd);
    			if(replyDate.length()>=7) replyDate = replyDate.substring(0,3)+"年"+replyDate.substring(3,5)+"月"+replyDate.substring(5,7)+"日";
    			
    			mailBody=mailBody.replace("[F_公司名稱]", obj.getApplicationName());
    			mailBody=mailBody.replace("[F_中文品名]", obj.getChProduct());
				mailBody=mailBody.replace("[F_英文品名]", obj.getEnProduct());
				mailBody=mailBody.replace("[F_許可證字號]", drgpkMap.get(obj.getPermitKey())+"第"+obj.getPermitNo()+"號");
				mailBody=mailBody.replace("[F_案號]", obj.getApplNo());
				mailBody=mailBody.replace("[F_評估日期]", payDate);
				mailBody=mailBody.replace("[F_調查結果]", survey);
				mailBody=mailBody.replace("[F_預防矯正措施及改善時程]", precaution);
				mailBody=mailBody.replace("[F_回覆日期]", replyDate);
			}	
		}
		
		if("DRG02".equals(Common.get(mailID.substring(0,5)))){
			if("DRG020007".equals(mailID)){
				List<String> objList = ServiceGetter.getInstance().getTcbwService().load(" select DISTINCT ingredient from Drg4009Db where drg4005Db.id = " + Common.getLong(id));
				String ingredientStr = "";
				if(null != objList && !objList.isEmpty()){
					for(String ingredient : objList){
						if(ingredientStr.length()>0)	ingredientStr += ",";
						ingredientStr += ingredient;
					}
					mailBody=mailBody.replace("#ingredient#", ingredientStr);
				}
				String link = url+"/index.jsp?logout=drgex0308f";
				mailBody=mailBody.replace("#link#", "<a href="+link+">"+link+"</a>");
			}else{
				Drg4001Db c = (Drg4001Db)View.getObject(" from Drg4001Db where id = " + Common.getLong(id));
				if(c!=null){
					mailBody=mailBody.replace("#systemDate#", Common.formatYYYMMDD(Datetime.getYYYMMDD(),2));
					mailBody=mailBody.replace("#notifierRepDate#", Common.formatYYYMMDD(c.getNotifierRepDate(),2));
					mailBody=mailBody.replace("#applNo#", Common.get(c.getApplNo()));
					mailBody=mailBody.replace("#sysDate#", Common.formatYYYMMDD(Datetime.getYYYMMDD(),2));
					if(null != c.getDrg4003Dbs() && !c.getDrg4003Dbs().isEmpty()){
						for(Object dtlObj:c.getDrg4003Dbs()){
							Drg4003Db drg43 = (Drg4003Db)dtlObj;
							if("02".equals(drg43.getMedType())){
								mailBody=mailBody.replace("#scientificName#", Common.get(drg43.getScientificName()));
								mailBody=mailBody.replace("#productName#", Common.get(drg43.getProductName()));
								mailBody=mailBody.replace("#applName#", Common.get(drg43.getManufactorName()));
								mailBody=mailBody.replace("#permitKeyNo#", Common.get(View.getCommonCodeName("DRGPKID", drg43.getPermitKey()))+Common.get(drg43.getPermitNo()));
								mailBody=mailBody.replace("#manufactorNo#", Common.get(drg43.getManufactorNo()));
								mailBody=mailBody.replace("#medModel#", Common.get(View.getCommonCodeName("DRGFLN", drg43.getMedModel())));
							}
						}
					}
					if("DRG020004".equals(mailID)){
						Drg4004Db drg4004 = (Drg4004Db)View.getObject(" from Drg4004Db where applNo = " + Common.sqlChar(c.getApplNo())+" order by id desc");						
						if(drg4004!=null){						
							if("1".equals(drg4004.getConSequence())){
								mailBody=mailBody.replace("#conSequence1#", "■");
								mailBody=mailBody.replace("#conSequence2#", "□");
								if("1".equals(drg4004.getEffectChangeDesc())){
									mailBody=mailBody.replace("#effectChangeDesc1#", "■");
									mailBody=mailBody.replace("#effectChangeDesc2#", "□");
								}else if("2".equals(drg4004.getEffectChangeDesc())){
									mailBody=mailBody.replace("#effectChangeDesc1#", "□");
									mailBody=mailBody.replace("#effectChangeDesc2#", "■");
								}else{
									mailBody=mailBody.replace("#effectChangeDesc1#", "□");
									mailBody=mailBody.replace("#effectChangeDesc2#", "□");
								}
							}
							else if("2".equals(drg4004.getConSequence())){
								mailBody=mailBody.replace("#conSequence1#", "□");
								mailBody=mailBody.replace("#conSequence2#", "■");
								mailBody=mailBody.replace("#badReactionDesc#", Common.get(drg4004.getBadReactionDesc()));
								mailBody=mailBody.replace("#effectChangeDesc1#", "□");
								mailBody=mailBody.replace("#effectChangeDesc2#", "□");
							}
							else{
								mailBody=mailBody.replace("#conSequence1#", "■");
								mailBody=mailBody.replace("#conSequence2#", "■");
								mailBody=mailBody.replace("#badReactionDesc#", Common.get(drg4004.getBadReactionDesc()));
								if("1".equals(drg4004.getEffectChangeDesc())){
									mailBody=mailBody.replace("#effectChangeDesc1#", "■");
									mailBody=mailBody.replace("#effectChangeDesc2#", "□");
								}else if("2".equals(drg4004.getEffectChangeDesc())){
									mailBody=mailBody.replace("#effectChangeDesc1#", "□");
									mailBody=mailBody.replace("#effectChangeDesc2#", "■");
								}else{
									mailBody=mailBody.replace("#effectChangeDesc1#", "□");
									mailBody=mailBody.replace("#effectChangeDesc2#", "□");
								}						
							}
							String badReactionLevStr = "";
							List<CommonCode>codList = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRG0308");
							if(null != codList && !codList.isEmpty()){
								for(CommonCode code:codList){
									if(code.getCodeId().equals(Common.get(drg4004.getBadReactionLev()))){
										badReactionLevStr += "■";
									}else{
										badReactionLevStr += "□";
									}
									badReactionLevStr += code.getCodeName();
								}
							}
							mailBody=mailBody.replace("#badReactionLev#", Common.get(badReactionLevStr));
							mailBody=mailBody.replace("#badReactionDesc#", Common.get(drg4004.getBadReactionDesc()));					
						}
					}										
				}
			}
		}
		
		if("MED060001".equals(Common.get(mailID)) || "MED060002".equals(Common.get(mailID)))
		{
			
			List<Med7002Db> medList = ServiceGetter.getInstance().getCommonService().load(" from Med7002Db where med7001Db.id in (select med7001Db.id from Med7002Db where id = " + Common.getLong(id)+ ")" +
					" and applicationId in (select applicationId from Med7002Db where id ="+ Common.getLong(id)+")");
			
			String permitStr = "";
			for(Med7002Db med72:medList){
				mailBody = mailBody.replace("#medwappName#", Common.get(med72.getApplicationName()))
							.replace("#medwpubldept#", View.getCommonCodeName("CONPUBLDEPT", med72.getMed7001Db().getPublDept()))
							.replace("#medwwebs#", Common.get(med72.getMed7001Db().getDatasourWebSite()))
							.replace("#medwwebs#", Common.get(med72.getMed7001Db().getDatasourWebSite()));
				
				if(permitStr.length() > 0) permitStr += "、";
				permitStr += Common.get(View.getCommonCodeName("MEDPKID", med72.getPermitKey()))+"第"+Common.get(med72.getPermitNo())+"號";
			}	
			mailBody = mailBody.replace("#medwpermit#", Common.get(permitStr));
		}
		
		if("MED050004".equals(Common.get(mailID))) {
			Med9002Db obj = (Med9002Db)View.getObject(" from Med9002Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				java.util.Map<String, String> medpkMap = TCBWCommon.getCommonCodeMap("MEDPKID");
    			
    			mailBody=mailBody.replace("[F_報告繳交日期]", Common.formatYYYMMDD(obj.getHanddate(),2));
    			mailBody=mailBody.replace("[F_許可證字號]", medpkMap.get(Common.get(obj.getMed9001Db().getPermitKey())) + "第" + Common.get(obj.getMed9001Db().getPermitNo() + "號"));
				mailBody=mailBody.replace("[F_中文品名]", Common.get(obj.getMed9001Db().getChProduct()));
				mailBody=mailBody.replace("[F_期數]", Common.get(obj.getReportIssueno()));
			}
		}
		
		if("MED050005".equals(Common.get(mailID))) {
			Med9002Db obj = (Med9002Db)View.getObject(" from Med9002Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				java.util.Map<String, String> medpkMap = TCBWCommon.getCommonCodeMap("MEDPKID");
    			
    			mailBody=mailBody.replace("[F_報告繳交日期]", Common.formatYYYMMDD(obj.getHanddate(),2));
    			mailBody=mailBody.replace("[F_許可證字號]", medpkMap.get(Common.get(obj.getMed9001Db().getPermitKey())) + "第" + Common.get(obj.getMed9001Db().getPermitNo() + "號"));
				mailBody=mailBody.replace("[F_中文品名]", Common.get(obj.getMed9001Db().getChProduct()));
			}
		}
		
		if("MED060004".equals(mailID))
		{
			Med7001Db obj = (Med7001Db)View.getObject(" from Med7001Db where id in (select med7001Db.id from Med7002Db where id = " + Common.getLong(id)+ ")");
			
			if(obj!=null)
			{
				mailBody=mailBody.replace("[F_警訊編號]", Common.get(obj.getApplNo()));
			}
		}
		

		return mailBody;
	}
	
	public static String getMailBodyByCOS(String mailID, String id, String mailBody, String field) throws Exception {
		
		if("COS020004".equals(Common.get(mailID))){
			Cos0001Db obj = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(obj != null){
				mailBody = Common.get(mailBody).replace("[F_案號]", Common.get(obj.getApplNo()));
				
				// 不良缺陷描述
				StringBuffer sb = new StringBuffer();
				if(obj.getCos0003Dbs()!=null && obj.getCos0003Dbs().size()>0){
					java.util.Map<String, String> subCodeNameMap = new java.util.HashMap<String, String>(); 
					java.util.List<Cos1001Db> cos1001DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where isStop = 'N'");
					if(cos1001DbList!=null && cos1001DbList.size()>0){
						for(Cos1001Db cos1001Db : cos1001DbList){
							subCodeNameMap.put(Common.get(cos1001Db.getDpdKind()), Common.get(cos1001Db.getDpdKindName()));
						}
						cos1001DbList.clear();
					}
					for(Object cos0003DbObj : obj.getCos0003Dbs()){
						Cos0003Db cos0003Db = (Cos0003Db)cos0003DbObj;
						if(sb.toString().length() > 0){
							sb.append("、");
						}
						sb.append(Common.get(subCodeNameMap.get(Common.get(cos0003Db.getSubCode()))));
					}
					subCodeNameMap.clear();
				}
				mailBody = Common.get(mailBody).replace("[F_不良品狀況]", sb.toString());
				
				String uName = View.getLookupField(" select unionName from Con1003Db where id = " + Common.getLong(field) );
				mailBody = Common.get(mailBody).replace("[F_衛生單位]", uName);
			}
		}
		
		return mailBody;
	}
	
	public static String getEmail(String mailID,String id,String mailBody,String worker) throws Exception
	{
		String email="";
		
		//化粧品不良品-廠商通知
		if("COS020005".equals(mailID) || "COS020006".equals(mailID))
		{
	
			Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id in(" + id+")");
			
			if(cos0001Db!=null)
			{
				String hql="from Con1006Db where con1005Db.compegno="+Common.sqlChar(cos0001Db.getManufactorID());
				java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
			
				if (objList != null && objList.size() > 0) 
				{
					for(Object dtlObj : objList) 
					{	
						Con1006Db dtl = (Con1006Db)dtlObj;
						email+=dtl.getCommonUser().getUserEmail()+",";
					}
				}
			}
			
			if(email.length()>0)
				email=email.substring(0,email.length()-1);

		}
		
		//醫療器材不良事件通報-退件
		if("MED010002".equals(mailID))
		{
			Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				email=obj.getNotifierEamil();
			}	
		}
		
		if("MED010004".equals(mailID))
		{
			Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				email=obj.getNotifierEamil();
			}	
		}
		
		//【案件評估通知】不良反應案件審核
		if("MED010005".equals(Common.get(mailID)))
		{
			Med0001Db med0001Db = (Med0001Db)View.getObject(" from Med0001Db where id in(" + id+")");
			String mail=View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(worker));
			if(med0001Db!=null)
			{
			  email=mail;
			}
		}
		
		if("MED020004".equals(mailID))
		{
	
			Med0001Db med0001Db = (Med0001Db)View.getObject(" from Med0001Db where id in(" + id+")");
			
			if(med0001Db!=null)
			{
				String hql="from Con1006Db where con1005Db.compegno="+Common.sqlChar(med0001Db.getMedPermitFirmCode());
				java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
			
				if (objList != null && objList.size() > 0) 
				{
					for(Object dtlObj : objList) 
					{	
						Con1006Db dtl = (Con1006Db)dtlObj;
						email+=dtl.getCommonUser().getUserEmail()+",";
					}
				}
			}
			
			if(email.length()>0)
				email=email.substring(0,email.length()-1);

		}
		
		//醫療器材臨床試驗不良事件通報--退件
		if("MED030002".equals(mailID))
		{
			Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				email=obj.getNotifierEamil();
			}	
		}

		//醫療器材臨床試驗不良事件通報--補件
		if("MED030003".equals(mailID))
		{
			Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(id));
			if(obj!=null)
			{
				email=obj.getNotifierEamil();
			}	
		}
		
		//醫療器材醫療器材定期安全監視報告--補件
		if("MED050004".equals(mailID))
		{
			String hql = " from CommonUser where id in (select commonUser.id from Con1006Db where con1005Db.compegno in ("
						+" select med9001Db.applicationID from Med9002Db where id = " + Common.getLong(id)+"))";
				CommonUser user = (CommonUser)View.getObject(hql);
				if(null != user && null != user.getUserEmail() && !"".equals(user.getUserEmail())){
					email = user.getUserEmail();
				}
		}
		
		
		
		if("DRG010002".equals(Common.get(mailID)) 
				|| "DRG010003".equals(Common.get(mailID))
				   || "DRG010004".equals(Common.get(mailID)) )
		{
			Drg0001Db drg0001Db = (Drg0001Db)View.getObject(" from Drg0001Db where id ="+Common.getLong(id));		
			
			if(drg0001Db!=null)
			{
				String mail = drg0001Db.getNotifierEmail();
			    email=mail;
			}			
		}		
		
		
		
		if("COS010001".equals(Common.get(mailID)) || "COS020001".equals(Common.get(mailID)) || "COS030001".equals(Common.get(mailID))){
			Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(id));
			if(cos0001Db != null){
				email = Common.get(cos0001Db.getNotifierEamil());
			}
		}
		
		if("COS020004".equals(Common.get(mailID))){
			StringBuffer sb = new StringBuffer();
			String hql = " from Con1004Db where 1 = 1 and con1003Db.id = " + Common.getLong(id) + " and formType like " + Common.sqlChar("%COS02%");
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
			if(objList != null && objList.size() > 0){
				for(Object dtlObj : objList) {	
					Con1004Db dtl = (Con1004Db)dtlObj;
					if(sb.toString().length() > 0){
						sb.append(",");
					}
					sb.append(Common.get(dtl.getCommonUser().getUserEmail()));
				}
				objList.clear();
			}
			email = sb.toString();
		}
		
		if("COS020007".equals(Common.get(mailID)) || "COS020008".equals(Common.get(mailID))){
			StringBuffer sb = new StringBuffer();
			java.util.List<Con1013Db> con1013DbList = ServiceGetter.getInstance().getTcbwService().load(" from Con1013Db where con1012Db.systemType = 'COS' and con1012Db.code = 'TFDA01'");
			if(con1013DbList!=null && con1013DbList.size()>0){
				for(Con1013Db dtl : con1013DbList){
					CommonUser user = dtl.getCommonUser();
					if(user!=null && !"".equals(Common.get(user.getUserEmail())) && Validate.checkEmail(Common.get(user.getUserEmail()))){
						if(sb.toString().length() > 0){
							sb.append(",");
						}
						sb.append(Common.get(user.getUserEmail()));
					}
				}
				con1013DbList.clear();
			}
			email = sb.toString();
		}
		
		if("DRG010006".equals(Common.get(mailID)) || "DRG010007".equals(Common.get(mailID)) || "DRG010008".equals(Common.get(mailID)) || "DRG010009".equals(Common.get(mailID))){
			
            Drg0001Db drg0001Db = (Drg0001Db)View.getObject(" from Drg0001Db where id ="+Common.getLong(id));		
			
			if(drg0001Db!=null)
			{			
				//找出廠商Mail
				java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(" from Con1006Db where con1005Db.compegno="+Common.sqlChar(drg0001Db.getApplicationID()));			
				if(objList2!=null && objList2.size()>0)			
				{				
					for(Object dtlObj : objList2)				
					{					
						Con1006Db dtl = (Con1006Db)dtlObj;
						if(email.length() > 0){							
							email += ",";					
						}
						email += dtl.getCommonUser().getUserEmail();			
					}			
					if(objList2 !=null) objList2.clear();		
				}							
			}
		}
		
		if("DRG02".equals(Common.get(mailID.substring(0,5)))){
			
			//DRG020004  案件初評完成，送廠商
			//DRG020008 廠商補件		
			if("DRG020004".equals(mailID) || "DRG020008".equals(mailID)){
				String hql = " from CommonUser where id in (select commonUser.id from Con1006Db where con1005Db.compegno in ("
						+" select manufactorID from Drg4003Db where medType='02' and drg4001Db.id = " + Common.getLong(id)+"))";
				CommonUser user = (CommonUser)View.getObject(hql);
				if(null != user && null != user.getUserEmail() && !"".equals(user.getUserEmail())){
					email = user.getUserEmail();
				}
			}else if("DRG020007".equals(mailID)){	//DRG020007 發送醫院詢問函
				String mail = View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(worker));
				if(!Common.get(mail).equals(""))
				{
				  email=mail;
				}
			}else{
				//DRG020002 案件退件，送通報者
				//DRG020003 案件徹案，送通報者
				//DRG020005 案件退件補件，送通報者
				Drg4001Db obj = (Drg4001Db)View.getObject(" from Drg4001Db where id = " + Common.getLong(id));
				if(obj!=null){
					email = Common.get(View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(obj.getNotifierUserID())));
				}
				
			}
		}
		if("DRG030001".equals(mailID)){
			String hql = " from CommonUser where id in (select commonUser.id from Con1006Db where con1005Db.compegno in ("
				+" select applicationId from Drg7003Db where drg7001Db.id = " + Common.getLong(id)+"))";
			
			List<CommonUser> userList = ServiceGetter.getInstance().getTcbwService().load(hql);
			if(null != userList && !userList.isEmpty()){
				for(CommonUser user : userList){
					if(null != user.getUserEmail() && !"".equals(user.getUserEmail())){
						if(email.length()>0)	email += ",";
						email += user.getUserEmail();
					}
				}
			}
		}
		
		if("MED060001".equals(mailID) || "MED060002".equals(mailID) || "MED060004".equals(mailID)){
			String hql = " from CommonUser where id in (select commonUser.id from Con1006Db where con1005Db.compegno in ("
				+" select applicationId from Med7002Db where id = " + Common.getLong(id)+"))";
			
			List<CommonUser> userList = ServiceGetter.getInstance().getTcbwService().load(hql);
			if(null != userList && !userList.isEmpty()){
				for(CommonUser user : userList){
					if(null != user.getUserEmail() && !"".equals(user.getUserEmail())){
						if(email.length()>0)	email += ",";
						email += user.getUserEmail();
					}
				}
			}
		}
		
		if("HFR010002".equals(mailID)){
			email = View.getLookupField(" select notifierEamil from Hfr0001Db where id = " + Common.getLong(id));
		}
		
		return email;
	}

	public static String getCCEmail(String mailID,String id,String mailBody,String worker) throws Exception
	{
		String email="";
		if("DRG010006".equals(Common.get(mailID)) || "DRG010007".equals(Common.get(mailID)) || "DRG010008".equals(Common.get(mailID))){					
			//FDA人員			
			java.util.List<Con1013Db> con1013DbList = ServiceGetter.getInstance().getTcbwService().load(" from Con1013Db where con1012Db.systemType = 'DRG' and con1012Db.code = 'DRG01'");			
			if(con1013DbList!=null && con1013DbList.size()>0){				
				for(Con1013Db dtl : con1013DbList){					
					CommonUser user = dtl.getCommonUser();					
					if(user!=null && !"".equals(Common.get(user.getUserEmail())) && Validate.checkEmail(Common.get(user.getUserEmail()))){						
						if(email.length() > 0){							
							email += ",";					
						}						
						email += Common.get(user.getUserEmail());					
					}				
				}				
				con1013DbList.clear();			
			}						
			
		}
		
		return email;
	}
	
	public static String getBCCEmail(String mailID,String id,String mailBody,String worker) throws Exception
	{
		String email="";
		if("DRG020007".equals(mailID)){	//DRG020007 發送醫院詢問函寄送mail				
			List<CommonUser> userList = ServiceGetter.getInstance().getTcbwService().load(" from CommonUser where id in (select commonUser.id from Con1010Db where con1009Db.id in (select con1009Db.id from Drg4009Db where drg4005Db.id = " + Common.getLong(id)+"))");
			String conMialStr = "";
			if(null != userList && !userList.isEmpty()){
				for(CommonUser user : userList){
					if(null != user.getUserEmail() && !"".equals(user.getUserEmail())){
						if(conMialStr.length()>0)	conMialStr += ",";
						conMialStr += user.getUserEmail();
					}
				}
				userList.clear();
			}
			email = conMialStr;				
		}		
		return email;
	}
	
	//取回藥證資料
	public static List<String[]> getVW_ForADR_TBMLIC(String permitKey, String permitNo) throws Exception{
		Database db = new Database("MLMS");
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		if(!"".equals(permitKey) && !"".equals(permitNo)) {		
			java.util.List<Object> parameter = new java.util.ArrayList<Object>();
			String hql = " select LICEKC,LICID1,CHNAME,ENNAME,APPNAME,FACTNAME from VW_ForADR_TBMLIC where 1=1";
			if (!"".equals(permitNo)){	
				hql += " and LICID1 = ?";
				parameter.add(permitNo);
			}
			if (!"".equals(permitKey)){		
				hql += " and LICEKID = ?";
				parameter.add(permitKey);
			}	
			ResultSet rs = db.querySQLByPrepareStatement(hql, parameter);
			try {
				if(rs!=null){
					while (rs.next()){			
						String[] rowArray = new String[6];	
						rowArray[0] = Common.get(rs.getString("LICEKC"));
						rowArray[1] = Common.get(rs.getString("LICID1"));
						rowArray[2] = Common.get(rs.getString("CHNAME"));
						rowArray[3] = Common.get(rs.getString("ENNAME"));
						rowArray[4] = Common.get(rs.getString("APPNAME"));
						rowArray[5] = Common.get(rs.getString("FACTNAME"));
											
						arrList.add(rowArray);				
					}
				}
			}catch (Exception e) {			
				e.printStackTrace();
			}finally{
				db.closeAll();
			}
			return arrList;
		}
		return null;
	}
}
