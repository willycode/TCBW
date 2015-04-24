package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.model.bo.Cos4001Db;
import com.kangdainfo.tcbw.model.bo.Cos4002Db;
import com.kangdainfo.tcbw.model.bo.Cos4003Db;
import com.kangdainfo.tcbw.view.cosex.COSEX0101F;

public class COSIN0301F extends COSEX0101F {

	private String caseOwner;
	private String notifierDeptID;
	private String manufactorID;
	
	public String getCaseOwner() {		return checkGet(caseOwner);	}
	public void setCaseOwner(String caseOwner) {		this.caseOwner = checkSet(caseOwner);	}
	public String getNotifierDeptID() {		return checkGet(notifierDeptID);	}
	public void setNotifierDeptID(String notifierDeptID) {		this.notifierDeptID = checkSet(notifierDeptID);	}
	public String getManufactorID() {		return checkGet(manufactorID);	}
	public void setManufactorID(String manufactorID) {		this.manufactorID = checkSet(manufactorID);	}
	
	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Cos4001Db where creator = " + Common.sqlChar(getUserID());
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
		if(!"".equals(getQ_status()))
			hql += " and status = " + Common.sqlChar(getQ_status());
		
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
			hql += " and id in (select distinct cos4001Db.id from Cos4003Db where " + sb.toString() + ") ";
		}
		System.out.println("[TCBW]-[COSIN0301F]-[QUERYALL] : " + hql + " order by id ");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(), this.getPageSize());
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
					rowArray[2] = Common.get(dtl.getNotifierRevDate());
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
					}
					rowArray[10] = Common.get(CCSMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(CCSMap.get(Common.get(dtl.getStatus())));
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
