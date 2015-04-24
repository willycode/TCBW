package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;

public class COSIN0401F extends COSIN0400F {
	
	private String q_applNo;
	private String q_notifierRepDateS;
	private String q_notifierRepDateE;
	private String q_permitKey;
	private String q_permitNo;
	private String q_chProduct;
	private String q_manufactorName;
	private String q_status;
	private String q_chargeMan;
	private String q_adverseCondition;
	private String[] q_subCode;
	private String[] q_cosType;
	
	public String getQ_applNo() {		return checkGet(q_applNo);	}
	public void setQ_applNo(String q_applNo) {		this.q_applNo = checkSet(q_applNo);	}
	public String getQ_notifierRepDateS() {		return checkGet(q_notifierRepDateS);	}
	public void setQ_notifierRepDateS(String q_notifierRepDateS) {		this.q_notifierRepDateS = checkSet(q_notifierRepDateS);	}
	public String getQ_notifierRepDateE() {		return checkGet(q_notifierRepDateE);	}
	public void setQ_notifierRepDateE(String q_notifierRepDateE) {		this.q_notifierRepDateE = checkSet(q_notifierRepDateE);	}
	public String getQ_permitKey() {		return checkGet(q_permitKey);	}
	public void setQ_permitKey(String q_permitKey) {		this.q_permitKey = checkSet(q_permitKey);	}
	public String getQ_permitNo() {		return checkGet(q_permitNo);	}
	public void setQ_permitNo(String q_permitNo) {		this.q_permitNo = checkSet(q_permitNo);	}
	public String getQ_chProduct() {		return checkGet(q_chProduct);	}
	public void setQ_chProduct(String q_chProduct) {		this.q_chProduct = checkSet(q_chProduct);	}
	public String getQ_manufactorName() {		return checkGet(q_manufactorName);	}
	public void setQ_manufactorName(String q_manufactorName) {		this.q_manufactorName = checkSet(q_manufactorName);	}
	public String getQ_status() {		return checkGet(q_status);	}
	public void setQ_status(String q_status) {		this.q_status = checkSet(q_status);	}
	public String getQ_chargeMan() {		return checkGet(q_chargeMan);	}
	public void setQ_chargeMan(String q_chargeMan) {		this.q_chargeMan = checkSet(q_chargeMan);	}
	public String getQ_adverseCondition() {		return checkGet(q_adverseCondition);	}
	public void setQ_adverseCondition(String q_adverseCondition) {		this.q_adverseCondition = checkSet(q_adverseCondition);	}
	public String[] getQ_subCode() {		return checkGet(q_subCode);	}
	public void setQ_subCode(String[] q_subCode) {		this.q_subCode = checkSet(q_subCode);	}
	public String[] getQ_cosType() {		return checkGet(q_cosType);	}
	public void setQ_cosType(String[] q_cosType) {		this.q_cosType = checkSet(q_cosType);	}
	
	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Cos0001Db where status = '10' ";
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
		
		if(getQ_cosType()!=null && getQ_cosType().length>0){
			if(getQ_cosType().length == 2){
				hql += " and cosType in('1','2','3') ";
			}else{
				hql += " and cosType = " + Common.sqlChar(getQ_cosType()[0]);
			}
		}
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
		System.out.println("[TCBW]-[COSIN0401F]-[QUERYALL] : " + hql + " order by id ");
		
		// 103.01.28 取消分頁
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc ");
		if (objList != null && objList.size() > 0) {
			java.util.Map<String, String> CCTMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CCT", null);
			java.util.Map<String, String> CPTMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CPT", null);
			java.util.Map<String, String> CCSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CCS", null);
			java.util.Map<String, String> CACMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CAC", null);
			java.util.Map<String, String> subCodeNameMap = new java.util.HashMap<String, String>(); 
			java.util.List<Cos1001Db> cos1001DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where isStop = 'N'");
			if(cos1001DbList!=null && cos1001DbList.size()>0){
				for(Cos1001Db cos1001Db : cos1001DbList){
					subCodeNameMap.put(Common.get(cos1001Db.getDpdKind()), Common.get(cos1001Db.getDpdKindName()));
				}
				cos1001DbList.clear();
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
				
				String[] rowArray = new String[12];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getApplNo());
				rowArray[2] = Common.get(dtl.getNotifierRepDate());
				rowArray[3] = Common.get(CCTMap.get(Common.get(dtl.getCosType()))).equals("")?Common.get("不良品與不良反應"):CCTMap.get(Common.get(dtl.getCosType()));
				rowArray[4] = "";
				if(!"".equals(Common.get(dtl.getPermitKey())) || !"".equals(Common.get(dtl.getPermitNo()))){
					rowArray[4] = (Common.get(CPTMap.get(Common.get(dtl.getPermitKey()))).equals("")?Common.get(dtl.getPermitKey()):Common.get(CPTMap.get(Common.get(dtl.getPermitKey())))) + 
								  " 字第 " + Common.get(dtl.getPermitNo()) + " 號";
				}
				rowArray[5] = Common.get(dtl.getChProduct());
				rowArray[6] = Common.get(dtl.getEnProduct());
				rowArray[7] = Common.get(dtl.getManufactorName());
				
				rowArray[8] = "";
				if(dtl.getCos0002Dbs()!=null && dtl.getCos0002Dbs().size()>0){
					StringBuffer sb = new StringBuffer();
					for(Object cos0002DbObj : dtl.getCos0002Dbs()){
						Cos0002Db cos0002Db = (Cos0002Db)cos0002DbObj;
						if(sb.toString().length() > 0){
							sb.append("、");
						}
						sb.append(Common.get(CACMap.get(Common.get(cos0002Db.getAdverseCondition()))).equals("")?Common.get(cos0002Db.getAdverseCondition()):Common.get(CACMap.get(Common.get(cos0002Db.getAdverseCondition()))));
					}
					rowArray[8] = sb.toString();
				}
				
				rowArray[9] = "";
				if(dtl.getCos0003Dbs()!=null && dtl.getCos0003Dbs().size()>0){
					StringBuffer sb = new StringBuffer();
					for(Object cos0003DbObj : dtl.getCos0003Dbs()){
						Cos0003Db cos0003Db = (Cos0003Db)cos0003DbObj;
						if(sb.toString().length() > 0){
							sb.append("、");
						}
						sb.append(Common.get(subCodeNameMap.get(Common.get(cos0003Db.getSubCode()))));
					}
				}
				rowArray[10] = Common.get(CCSMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(CCSMap.get(Common.get(dtl.getStatus())));
				rowArray[11] = Common.get(cosUserMap.get(Common.get(dtl.getChargeMan()))).equals("")?Common.get(dtl.getChargeMan()):Common.get(cosUserMap.get(Common.get(dtl.getChargeMan())));
				arrList.add(rowArray);
			}
			objList.clear();
			
			CCTMap.clear();
			CPTMap.clear();
			CCSMap.clear();
			CACMap.clear();
			subCodeNameMap.clear();
			cosUserMap.clear();
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
