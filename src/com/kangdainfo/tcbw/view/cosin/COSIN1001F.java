package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos0006Db;
import com.kangdainfo.tcbw.model.bo.Cos0008Db;
import com.kangdainfo.tcbw.model.bo.Cos0016Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;

public class COSIN1001F extends COSIN0801F {

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Cos0001Db where 1 = 1 ";
		if("1D".equals(getQ_type()))
			hql += " and status = '20' and cosType = '1' ";
		else if("1A".equals(getQ_type()))
			hql += " and status = '30' and cosType = '1' ";
		else if("2D".equals(getQ_type()))
			hql += " and status = '20' and cosType = '2' ";
		else if("2A".equals(getQ_type()))
			hql += " and status = '30' and cosType = '2' ";
		
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
		if("1D".equals(getQ_type()) || "1A".equals(getQ_type())){
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
		}
		
		// 不良反應
		if("2D".equals(getQ_type()) || "2A".equals(getQ_type())){
			if(!"".equals(getQ_adverseCondition())){
				hql += " and id in (select distinct cos0001Db.id from Cos0002Db where adverseCondition = " + Common.sqlChar(getQ_adverseCondition()) + ")";
			}
		}
		System.out.println("[TCBW]-[COSIN1001F]-[QUERYALL] : " + hql + " order by id ");
		
		// 103.01.28 取消分頁
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc");
		if (objList != null && objList.size() > 0) {
			java.util.Map<String, String> CPTMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CPT", null);
			java.util.Map<String, String> CCSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CCS", null);
			java.util.Map<String, String> CACMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CAC", null);
			
			java.util.Map<String, String> subCodeNameMap = new java.util.HashMap<String, String>();
			if("1D".equals(getQ_type()) || "1A".equals(getQ_type())){
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
				if("1D".equals(getQ_type()) || "1A".equals(getQ_type())){
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
	
	// 分類頁籤資料
	public void setDisPageData(COSIN1001F obj, Cos0001Db c) throws Exception {
		// 分類頁籤
		if("1".equals(Common.get(c.getCosType()))){
			Cos0006Db cos0006DbFirst = null;
			
			// 先行查詢分類歷史資料
			java.util.List<Cos0016Db> cos0016DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos0016Db where (cos0006Db.disDate <> '' or cos0006Db.disDate is not null) " +
																										" and cos0006Db.applNo = " + Common.sqlChar(c.getApplNo()) + 
																										" order by cos0006Db.disDate ");
			
			if(cos0016DbList!=null && cos0016DbList.size()>0){
				java.util.Map<String, String> CFRNameMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CFR", null);
				java.util.Map<String, String> CMSNameMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CMS", null);
			//	cos0006DbFirst = cos0016DbList.get(0).getCos0006Db();
				
				java.util.ArrayList<String[]> showList = new java.util.ArrayList<String[]>();
				for(Cos0016Db cos0016Db : cos0016DbList){
					Cos0006Db cos0006Db = cos0016Db.getCos0006Db();
					if(cos0006Db != null){
						String[] rowArray = new String[4];
						rowArray[0] = Common.get(cos0006Db.getId());
						rowArray[1] = Common.get(CFRNameMap.get(Common.get(cos0006Db.getFirstResult()))).equals("")?Common.get(cos0006Db.getFirstResult()):Common.get(CFRNameMap.get(Common.get(cos0006Db.getFirstResult())));
						rowArray[2] = "";
						if(Common.get(cos0006Db.getMeasure()).length() > 0){
							StringBuffer sb = new StringBuffer();
							String[] tmp = Common.get(cos0006Db.getMeasure()).split(",");
							if(tmp!=null && tmp.length>0){
								for(String rid : tmp){
									if(sb.toString().length() > 0){
										sb.append("、");
									}
									sb.append( Common.get(CMSNameMap.get(rid)).equals("")?rid:Common.get(CMSNameMap.get(rid)) );
								}
							}
							rowArray[2] = sb.toString();
						}
						rowArray[3] = Common.get(cos0006Db.getDisDate());
						showList.add(rowArray);
					}
				}
				obj.setIsHasHistory("Y");
				obj.setCos0006DbList(showList);
				
				cos0016DbList.clear();
				CFRNameMap.clear();
				CMSNameMap.clear();
			}else{
				obj.setIsHasHistory("N");
				obj.setCos0006DbList(null);
			}
			
		//	改為取最新一筆	
		//	if(cos0006DbFirst == null){
				cos0006DbFirst = (Cos0006Db)View.getObject(" from Cos0006Db where applNo = " + Common.sqlChar(c.getApplNo()) + " order by id desc ");
		//	}
			
			if(cos0006DbFirst != null){
				obj.setCos0006DbId(Common.get(cos0006DbFirst.getId()));
				obj.setFirstResult(cos0006DbFirst.getFirstResult());
				obj.setLeaveCaseReason(cos0006DbFirst.getLeaveCaseReason());
				obj.setNonDefective(cos0006DbFirst.getNonDefective());
				obj.setdMarked(cos0006DbFirst.getMarked());
				obj.setdLawlessIng(cos0006DbFirst.getLawlessIng());
				obj.setdLawlessIngOther(cos0006DbFirst.getLawlessIngOther());
				obj.setdExteriorError(cos0006DbFirst.getExteriorError());
				obj.setdExteriorErrorOther(cos0006DbFirst.getExteriorErrorOther());
				obj.setdPackageError(cos0006DbFirst.getPackageError());
				obj.setdPackageErrorOther(cos0006DbFirst.getPackageErrorOther());
				obj.setdExpired(cos0006DbFirst.getExpired());
				obj.setdExpiredOther(cos0006DbFirst.getExpiredOther());
				obj.setdOthers(cos0006DbFirst.getOthers());
				obj.setdOthersDesc(cos0006DbFirst.getOthersDesc());
				if(Common.get(cos0006DbFirst.getMeasure()).length() > 0){
					String[] tmp = Common.get(cos0006DbFirst.getMeasure()).split(",");
					obj.setMeasure(tmp);
				}else{
					obj.setMeasure(null);
				}
			}else{
				obj.setCos0006DbId("");
			}
		}else if("2".equals(Common.get(c.getCosType()))){
			
			// 最新一筆
			Cos0008Db cos0008Db = (Cos0008Db)View.getObject(" from Cos0008Db where applNo = " + Common.sqlChar(Common.get(c.getApplNo())) + " order by id desc ");
			if(cos0008Db != null){
				obj.setCos0008DbId(Common.get(cos0008Db.getId()));
				obj.setPreResult(cos0008Db.getPreResult());
				obj.setLeftCaseReason(cos0008Db.getLeftCaseReason());
				obj.setIsCompleteYn(cos0008Db.getIsCompleteYn());
				obj.setdIsContactYn(cos0008Db.getIsContactYn());
				obj.setReactionLev(cos0008Db.getReactionLev());
				obj.setTimingLev(cos0008Db.getTimingLev());
				obj.setPreviousNotify(cos0008Db.getPreviousNotify());
				obj.setIsComplaintYn(cos0008Db.getIsComplaintYn());
				obj.setdIsOtherDeptYn(cos0008Db.getIsOtherDeptYn());
				
				
				// 查尋歷史資料
				java.util.List<Cos0008Db> cos0008DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos0008Db where applNo = " + Common.sqlChar(Common.get(c.getApplNo())) +  
																											" and id <> " + Common.getLong(cos0008Db.getId()) + " order by disDate ");
				if(cos0008DbList!=null && cos0008DbList.size()>0){
					java.util.Map<String, String> COSFRNameMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("COSFR", null);
					
					java.util.ArrayList<String[]> showList = new java.util.ArrayList<String[]>();
					for(Cos0008Db dtlCos0008Db : cos0008DbList){
						String[] rowArray = new String[4];
						
						rowArray[0] = Common.get(dtlCos0008Db.getId());
						rowArray[1] = Common.get(COSFRNameMap.get(Common.get(dtlCos0008Db.getPreResult()))).equals("")?Common.get(dtlCos0008Db.getPreResult()):Common.get(COSFRNameMap.get(Common.get(dtlCos0008Db.getPreResult())));
						rowArray[2] = Common.get(dtlCos0008Db.getIsCompleteYn()).equals("Y")?"是":Common.get(dtlCos0008Db.getIsCompleteYn()).equals("N")?"否":"";
						rowArray[3] = Common.get(dtlCos0008Db.getIsCompleteYn()).equals("Y")?"是":Common.get(dtlCos0008Db.getIsCompleteYn()).equals("N")?"否":"";
						showList.add(rowArray);
					}
					setCos0008DbList(showList);
					
					COSFRNameMap.clear();
					COSFRNameMap = null;
					
					cos0008DbList.clear();
				}else{
					setCos0008DbList(null);
				}
				cos0008DbList = null;
			}else{
				setCos0008DbList(null);
			}
		}
		
		// 不良品、不良反應通報件數
		int num1 = Common.getInt(View.getLookupField("select count(id) from Cos0001Db where status > '02' and cosType = '1' and id <> " + Common.getLong(c.getId())));
		int num2 = Common.getInt(View.getLookupField("select count(id) from Cos0001Db where status > '02' and cosType = '2' and id <> " + Common.getLong(c.getId())));
		
		obj.setNum1(Common.MoneyFormat(num1));
		obj.setNum2(Common.MoneyFormat(num2));
	}
	
	
	
	
}
