package com.kangdainfo.tcbw.view.cosex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.model.bo.Cos4001Db;
import com.kangdainfo.tcbw.model.bo.Cos4002Db;
import com.kangdainfo.tcbw.model.bo.Cos4003Db;

public class COSEX0101F extends COSEX0100F {
	
	private String q_type;								// FOR 程式區隔
	private String q_notifierType;						// FOR 登入者屬性
	private String q_notifierDept;						// FOR 登入者服務機構(暫不使用)
	private String q_notifierDeptID;					// FOR 登入者服務機構(103.07.05改成已ID去查詢)
	private String q_userJobModDate;					// FOR 登入者單位異動日期
	private String q_applNo;
	private String q_notifierRepDateS;
	private String q_notifierRepDateE;
	private String q_permitKey;
	private String q_permitNo;
	private String q_chProduct;
	private String q_manufactorName;
	private String q_status;
	private String[] q_subCode;
	private String[] q_cosType;
	
	public String getQ_type() {		return checkGet(q_type);	}
	public void setQ_type(String q_type) {		this.q_type = checkSet(q_type);	}
	public String getQ_notifierType() {		return checkGet(q_notifierType);	}
	public void setQ_notifierType(String q_notifierType) {		this.q_notifierType = checkSet(q_notifierType);	}
	public String getQ_notifierDept() {		return checkGet(q_notifierDept);	}
	public void setQ_notifierDept(String q_notifierDept) {		this.q_notifierDept = checkSet(q_notifierDept);	}
	public String getQ_notifierDeptID() {		return checkGet(q_notifierDeptID);	}
	public void setQ_notifierDeptID(String q_notifierDeptID) {		this.q_notifierDeptID = checkSet(q_notifierDeptID);	}
	public String getQ_userJobModDate() {		return checkGet(q_userJobModDate);	}
	public void setQ_userJobModDate(String q_userJobModDate) {		this.q_userJobModDate = checkSet(q_userJobModDate);	}
	public String getQ_applNo() {		return checkGet(q_applNo);	}
	public void setQ_applNo(String q_applNo) {		this.q_applNo = checkSet(q_applNo);	}
	public String getQ_notifierRepDateS() {		return checkGet(q_notifierRepDateS);	}
	public void setQ_notifierRepDateS(String q_notifierRepDateS) {		this.q_notifierRepDateS = checkSet(q_notifierRepDateS);	}
	public String getQ_notifierRepDateE() {		return checkGet(q_notifierRepDateE);	}
	public void setQ_notifierRepDateE(String q_notifierRepDateE) {		this.q_notifierRepDateE = checkSet(q_notifierRepDateE);	}
	public String[] getQ_cosType() {		return q_cosType;	}
	public void setQ_cosType(String[] q_cosType) {		this.q_cosType = q_cosType;	}
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
	public String[] getQ_subCode() {		return q_subCode;	}
	public void setQ_subCode(String[] q_subCode) {		this.q_subCode = q_subCode;	}
	
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {	
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Cos4001Db where 1 = 1 ";
		if("02".equals(getQ_notifierType())){
			hql += " and notifierDeptId = " + Common.sqlChar(getQ_notifierDeptID());
		}else{
			hql += " and (creator = " + Common.sqlChar(getUserID()) + " or caseOwner = " + Common.sqlChar(getUserID()) + ")"; 
		}
		if(!"".equals(getQ_applNo()))
			hql += " and applNo = " + Common.sqlChar(getQ_applNo());
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		
		if(getQ_cosType()!=null && getQ_cosType().length>0){
			if(getQ_cosType().length == 2){
				hql += " and cosType in('1','2','3') ";
			}else{
				hql += " and cosType = " + Common.sqlChar(getQ_cosType()[0]);
			}
		}
			
		if(!"".equals(getQ_permitKey()))
			hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
		if(!"".equals(getQ_chProduct()))
			hql += " and chProduct like " + Common.sqlChar("%" + getQ_chProduct() + "%");
		if(!"".equals(getQ_manufactorName()))
			hql += " and manufactorName like " + Common.sqlChar("%" + getQ_manufactorName() + "%");
		
		if("A".equals(getQ_type())){
			hql += " and status = '30' ";
		}else{
			if(!"".equals(getQ_status())){
				if("A".equals(getQ_status())){
					hql += " and status not in ('00','01','02','30')";
				}else{
					hql += " and status = " + Common.sqlChar(getQ_status());
				}
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
			hql += " and id in (select distinct cos4001Db.id from Cos4003Db where " + sb.toString() + ") ";
		}
		
		System.out.println("[TCBW]-[COSEX0101F]-[QUERYALL] : " + hql + " order by id ");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id DESC", this.getRecordStart(), this.getPageSize());
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
				
				for(Object dtlObj : objList) {				
					Cos4001Db dtl = (Cos4001Db)dtlObj;
					
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
					if(dtl.getCos4002Dbs()!=null && dtl.getCos4002Dbs().size()>0){
						StringBuffer sb = new StringBuffer();
						for(Object cos4002DbObj : dtl.getCos4002Dbs()){
							Cos4002Db cos4002Db = (Cos4002Db)cos4002DbObj;
							if(sb.toString().length() > 0){
								sb.append("、");
							}
							sb.append(Common.get(CACMap.get(Common.get(cos4002Db.getAdverseCondition()))).equals("")?Common.get(cos4002Db.getAdverseCondition()):Common.get(CACMap.get(Common.get(cos4002Db.getAdverseCondition()))));
						}
						rowArray[8] = sb.toString();
					}
					
					rowArray[9] = "";
					if(dtl.getCos4003Dbs()!=null && dtl.getCos4003Dbs().size()>0){
						StringBuffer sb = new StringBuffer();
						for(Object cos4003DbObj : dtl.getCos4003Dbs()){
							Cos4003Db cos4003Db = (Cos4003Db)cos4003DbObj;
							if(sb.toString().length() > 0){
								sb.append("、");
							}
							sb.append(Common.get(subCodeNameMap.get(Common.get(cos4003Db.getSubCode()))));
						}
						rowArray[9] = sb.toString();
					}
					
					rowArray[10] = "";
					if("00,01,02,30".indexOf(Common.get(dtl.getStatus())) == -1)
					{
						rowArray[10] = "通報完成";
					}
					else
					{
						rowArray[10] = Common.get(CCSMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(CCSMap.get(Common.get(dtl.getStatus())));
					}
					rowArray[11] = Common.get(dtl.getCosType());
					arrList.add(rowArray);
				}
				objList.clear();
				
				CCTMap.clear();
				CPTMap.clear();
				CCSMap.clear();
				CACMap.clear();
				subCodeNameMap.clear();
			}
		
		}
		return arrList;
	}
	
	

}
