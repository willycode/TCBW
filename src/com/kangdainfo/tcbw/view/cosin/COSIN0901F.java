package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos0015Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;

public class COSIN0901F extends COSIN0601F {
	
	// COS0015_DB
	private String cos0015DbId;
	private String cosCorrelation;					// 不良反應與可疑化粧品相關性
    private String feedBack;						// 對通報者的回饋
    private String recordOpinion;					// 紀錄者意見
    
	public String getCos0015DbId() {		return checkGet(cos0015DbId);	}
	public void setCos0015DbId(String cos0015DbId) {		this.cos0015DbId = checkSet(cos0015DbId);	}
	public String getCosCorrelation() {		return checkGet(cosCorrelation);	}
	public void setCosCorrelation(String cosCorrelation) {		this.cosCorrelation = checkSet(cosCorrelation);	}
	public String getFeedBack() {		return checkGet(feedBack);	}
	public void setFeedBack(String feedBack) {		this.feedBack = checkSet(feedBack);	}
	public String getRecordOpinion() {		return checkGet(recordOpinion);	}
	public void setRecordOpinion(String recordOpinion) {		this.recordOpinion = checkSet(recordOpinion);	}
	
	// 結案頁籤
	public void setEndCasePage(COSIN0901F obj, Cos0001Db c) throws Exception {
		Cos0015Db cos0015Db = (Cos0015Db)View.getObject(" from Cos0015Db where applNo = " + Common.sqlChar(c.getApplNo()) + 
														" and cosType = " + Common.sqlChar(c.getCosType()));
		if(cos0015Db != null){
			obj.setCos0015DbId(Common.get(cos0015Db.getId()));
			obj.setCosCorrelation(cos0015Db.getCosCorrelation());
			obj.setFeedBack(cos0015Db.getFeedBack());
			obj.setRecordOpinion(cos0015Db.getRecordOpinion());
		}else{
			obj.setCos0015DbId("");
		}
	}

	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Cos0001Db where status = '70' ";
		if("1E".equals(getQ_type())){
			hql += " and cosType = '1' ";
		}else{
			hql += " and cosType = '2' ";
		}
		if(!"".equals(getQ_applNo()))
			hql += " and applNo = " + Common.sqlChar(getQ_applNo());
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(getQ_permitKey()))
			hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
		if(!"".equals(getQ_chProduct()))
			hql += " and chProduct like " + Common.sqlChar("%" + getQ_chProduct() + "%");
		if(!"".equals(getQ_manufactorName()))
			hql += " and manufactorName like " + Common.sqlChar("%" + getQ_manufactorName() + "%");
		if(!"".equals(getQ_chargeMan()))
			hql += " and chargeMan = " + Common.sqlChar(getQ_chargeMan());
		
		// 不良品
		if("1S".equals(getQ_type())){
			if(getQ_subCode()!=null && getQ_subCode().length>0){
				StringBuffer sb = new StringBuffer();
				sb.append("(");
				
				boolean flag = true;
				for(String rid : getQ_subCode()){	
					if(flag){
						sb.append(" subCode like " + Common.sqlChar("%" + rid + "%"));
						flag = false;
					}else{
						sb.append(" or subCode like " + Common.sqlChar("%" + rid + "%"));
					}
				}
				sb.append(")");
				hql += " and id in (select distinct cos0001Db.id from Cos0003Db where " + sb.toString() + ") ";
			}
		}else{
			if(!"".equals(getQ_adverseCondition())){
				hql += " and id in (select distinct cos0001Db.id from Cos0002Db where adverseCondition = " + Common.sqlChar(getQ_adverseCondition()) + ")";
			}
		}
		System.out.println("[TCBW]-[COSIN0901F]-[QUERYALL] : " + hql + " order by id ");
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc");
		if (objList != null && objList.size() > 0) {
			java.util.Map<String, String> CPTMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CPT", null);
			java.util.Map<String, String> CCSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CCS", null);
			java.util.Map<String, String> CACMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CAC", null);
			
			java.util.Map<String, String> subCodeNameMap = new java.util.HashMap<String, String>();
			if("1E".equals(getQ_type())){
				java.util.List<Cos1001Db> cos1001DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where isStop = 'N'");
				if(cos1001DbList!=null && cos1001DbList.size()>0){
					for(Cos1001Db cos1001Db : cos1001DbList){
						subCodeNameMap.put(Common.get(cos1001Db.getDpdKind()), Common.get(cos1001Db.getDpdKindName()));
					}
					cos1001DbList.clear();
				}
			}
			
			java.util.Map<String, String> cosUserMap = new java.util.HashMap<String, String>();
			java.util.List userList = ServiceGetter.getInstance().getTcbwService().load("select userId, userName from CommonUser where inORout = 'in'");
			if(userList!=null && userList.size()>0){
				for(Object dtlObj : userList){
					Object[] dtl = (Object[])dtlObj;
					cosUserMap.put(Common.get(dtl[0]), Common.get(dtl[1]));
				}
				userList.clear();
			}
			
			for(Object dtlObj : objList) {				
				Cos0001Db dtl = (Cos0001Db)dtlObj;
				
				String[] rowArray = new String[10];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getApplNo());
				rowArray[2] = Common.get(dtl.getNotifierRepDate());
				rowArray[3] = "";
				if(!"".equals(Common.get(dtl.getPermitKey())) || !"".equals(Common.get(dtl.getPermitNo()))){
					rowArray[3] = (Common.get(CPTMap.get(Common.get(dtl.getPermitKey()))).equals("")?Common.get(dtl.getPermitKey()):Common.get(CPTMap.get(Common.get(dtl.getPermitKey())))) + 
								  " 字第 " + Common.get(dtl.getPermitNo()) + " 號";
				}
				rowArray[4] = Common.get(dtl.getChProduct());
				rowArray[5] = Common.get(dtl.getEnProduct());
				rowArray[6] = Common.get(dtl.getManufactorName());
				
				rowArray[7] = "";
				if("1E".equals(getQ_type())){
					if(dtl.getCos0003Dbs()!=null && dtl.getCos0003Dbs().size()>0){
						StringBuffer sb = new StringBuffer();
						for(Object cos0003DbObj : dtl.getCos0003Dbs()){
							Cos0003Db cos0003Db = (Cos0003Db)cos0003DbObj;
							if(sb.toString().length() > 0){
								sb.append("、");
							}
							sb.append(Common.get(subCodeNameMap.get(Common.get(cos0003Db.getSubCode()))));
						}
						rowArray[7] = sb.toString();
					}
				}else{
					if(dtl.getCos0002Dbs()!=null && dtl.getCos0002Dbs().size()>0){
						StringBuffer sb = new StringBuffer();
						for(Object cos0002DbObj : dtl.getCos0002Dbs()){
							Cos0002Db cos0002Db = (Cos0002Db)cos0002DbObj;
							if(sb.toString().length() > 0){
								sb.append("、");
							}
							sb.append(Common.get(CACMap.get(Common.get(cos0002Db.getAdverseCondition()))).equals("")?Common.get(cos0002Db.getAdverseCondition()):Common.get(CACMap.get(Common.get(cos0002Db.getAdverseCondition()))));
						}
						rowArray[7] = sb.toString();
					}
				}
				
				rowArray[8] = Common.get(CCSMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(CCSMap.get(Common.get(dtl.getStatus())));
				rowArray[9] = Common.get(cosUserMap.get(Common.get(dtl.getChargeMan()))).equals("")?Common.get(dtl.getChargeMan()):Common.get(cosUserMap.get(Common.get(dtl.getChargeMan())));
				arrList.add(rowArray);
			}
			objList.clear();
			
			CPTMap.clear();
			CCSMap.clear();
			CACMap.clear();
			subCodeNameMap.clear();
			cosUserMap.clear();
		}
		return arrList;
	}
	
}
