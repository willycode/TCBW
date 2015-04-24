package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Cos4001Db;
import com.kangdainfo.tcbw.model.bo.Cos4002Db;
import com.kangdainfo.tcbw.model.bo.Cos4003Db;
import com.kangdainfo.tcbw.model.bo.Cos4004Db;
import com.kangdainfo.tcbw.model.bo.Cos4005Db;

public class COSIN0302F extends COSIN0301F {

	public void doInsert() throws Exception {
		ServiceGetter.getInstance().getTcbwService().getCosinDao().insertByCOSIN0302F(this);
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		COSIN0302F obj = this;
		
		Cos4001Db c = (Cos4001Db)View.getObject(" from Cos4001Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setApplNo(c.getApplNo());
			obj.setStatus(c.getStatus());
			obj.setCosType(c.getCosType());
			obj.setCaseOwner(c.getCaseOwner());
			obj.setNotifierType(c.getNotifierType());
			obj.setNotifierTitle(c.getNotifierTitle());
			obj.setNotifierDeptID(c.getNotifierDeptId());
			obj.setNotifierDept(View.getNotifierDeptName(obj.getNotifierDeptID(), obj.getNotifierType()));
			obj.setOccurDate(c.getOccurDate());
			obj.setNotifierRevDate(c.getNotifierRevDate());
			obj.setNotifierRepDate(c.getNotifierRepDate());
			obj.setNotifierSource(c.getNotifierSource());
			obj.setNotifierSourceOther(c.getNotifierSourceOther());
			obj.setNotifierName(c.getNotifierName());
			obj.setNotifierTelArea(c.getNotifierTelArea());
			obj.setNotifierTel(c.getNotifierTel());
			obj.setNotifierTelExt(c.getNotifierTelExt());
			obj.setNotifierEamil(c.getNotifierEamil());
			obj.setNotifierArea(c.getNotifierArea());
			obj.setNotifierZipCode(c.getNotifierZipCode());
			obj.setAddress(c.getAddress());
			obj.setIsContactYn(c.getIsContactYn());
			
			if("3".equals(Common.get(c.getCosType()))){
				obj.setShowCosType(new String[]{"1","2"});
			}else{
				obj.setShowCosType(new String[]{Common.get(c.getCosType())});
			}
			
			obj.setChProduct(c.getChProduct());
			obj.setEnProduct(c.getEnProduct());
			obj.setPermitKey(c.getPermitKey());
			obj.setPermitNo(c.getPermitNo());
			obj.setTraffickWay(c.getTraffickWay());
			obj.setTraffickWayOther(c.getTraffickWayOther());
			obj.setTradePlace(c.getTradePlace());
			obj.setTradePlaceZipCode(c.getTradePlaceZipCode());
			obj.setTradePlaceAddr(c.getTradePlaceAddr());
			obj.setBusinessName(c.getBusinessName());
			obj.setManufactorID(c.getManufactorID());
			obj.setManufactorName(c.getManufactorName());
			obj.setManufactorTelArea(c.getManufactorTelArea());
			obj.setManufactorTel(c.getManufactorTel());
			obj.setManufactorTelExt(c.getManufactorTelExt());
			obj.setManufactorArea(c.getManufactorArea());
			obj.setManufactorZipCode(c.getManufactorZipCode());
			obj.setManufactorAddr(c.getManufactorAddr());
			obj.setManufactorNo(c.getManufactorNo());
			obj.setExpirationDate(c.getExpirationDate());
			obj.setTradeDate(c.getTradeDate());
			obj.setIsSampleYn(c.getIsSampleYn());
			obj.setEvenContactYn(c.getEvenContactYn());
			obj.setDealWith(c.getDealWith());
			obj.setIsSimilarYn(c.getIsSimilarYn());
			obj.setIsRecurrenceYn(c.getIsRecurrenceYn());
			obj.setIsOtherDeptYn(c.getIsOtherDeptYn());
			obj.setOtherDpetName(c.getOtherDpetName());
			obj.setCaseNo(c.getCaseNo());
			obj.setIngredient(c.getIngredient());
			
			if("1".equals(Common.get(c.getCosType())) || "3".equals(Common.get(c.getCosType()))){
				obj.setIsDamageYn(c.getIsDamageYn());
				obj.setOtherIsDamageYn(c.getOtherIsDamageYn());
				obj.setOtherInformation(c.getOtherInformation());
				obj.setOtherExplain(c.getOtherExplain());
				
				if(c.getCos4003Dbs()!=null && c.getCos4003Dbs().size()>0){
					java.util.List<String> subCodeList = new java.util.ArrayList<String>(); 
					for(Object dtlObj : c.getCos4003Dbs()){
						Cos4003Db dtl = (Cos4003Db)dtlObj;
						
						String[] tmp = Common.get(dtl.getSubCode()).split(",");
						if(tmp!=null && tmp.length>0){
							for(String codeId : tmp){
								subCodeList.add(codeId);
							}
						}
					}
					if(subCodeList.size() > 0){
						String[] codeArray = new String[subCodeList.size()];
						for(int i=0; i<subCodeList.size(); i++){
							codeArray[i] = Common.get(subCodeList.get(i)); 
						}
						subCodeList.clear();
						
						obj.setSubCode(codeArray);
					}
				}
			}
			
			if("2".equals(Common.get(c.getCosType())) || "3".equals(Common.get(c.getCosType()))){
				if(c.getCos4002Dbs()!=null && c.getCos4002Dbs().size()>0){
					boolean flag = true;
					for(Object dtlObj : c.getCos4002Dbs()){
						if(flag){
							Cos4002Db dtl = (Cos4002Db)dtlObj;
							obj.setAdverseCondition(dtl.getAdverseCondition());
							obj.setNonSeriousOther(dtl.getNonSeriousOther());
							obj.setNonSeriousDis(dtl.getNonSeriousDis());
							obj.setUseDateS(dtl.getUseDateS());
							obj.setUseDateE(dtl.getUseDateE());
							obj.setUseMethod(dtl.getUseMethod());
							obj.setUseRate(dtl.getUseRate());
							obj.setIsMitigateYn(dtl.getIsMitigateYn());
							obj.setIsRecurYn(dtl.getIsRecurYn());
							obj.setDiagnosisProof(dtl.getDiagnosisProof());
							obj.setDiagnosisReport(dtl.getDiagnosisReport());
							obj.setDiagnosisOther(dtl.getDiagnosisOther());
							
							java.util.List<Cos4004Db> cos04List = ServiceGetter.getInstance().getTcbwService().load(" from Cos4004Db where cos4002Db.id = " + dtl.getId());
							this.genCos4004DbItemSet(cos04List);
							
							java.util.List<Cos4005Db> cos05List = ServiceGetter.getInstance().getTcbwService().load(" from Cos4005Db where cos4002Db.id = " + dtl.getId());
							this.genCos4005DbItemSet(cos05List);
							
							java.util.List<Con0001Db> COSSDFileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSSD' and dbName = 'COS4002DB' and upLoadId = " + dtl.getId());
							this.genFileRowItemSet(COSSDFileList, "COSSD");
							
							java.util.List<Con0001Db> COSDPFileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSDP' and dbName = 'COS4002DB' and upLoadId = " + dtl.getId());
							this.genFileRowItemSet(COSDPFileList, "COSDP");
							
							java.util.List<Con0001Db> COSIDFileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSID' and dbName = 'COS4002DB' and upLoadId = " + dtl.getId());
							this.genFileRowItemSet(COSIDFileList, "COSID");
							
							if(cos04List!=null && cos04List.size()>0){
								cos04List.clear();
							}
							if(cos05List!=null && cos05List.size()>0){
								cos05List.clear();
							}
							if(COSSDFileList!=null && COSSDFileList.size()>0){
								COSSDFileList.clear();
							}
							if(COSDPFileList!=null && COSDPFileList.size()>0){
								COSDPFileList.clear();
							}
							if(COSIDFileList!=null && COSIDFileList.size()>0){
								COSDPFileList.clear();
							}
							flag = false;
						}
						if(!flag)	break;
					}
				}else{
					this.genCos4004DbItemSet(null);
					this.genCos4005DbItemSet(null);
				}
			}else{
				this.genCos4004DbItemSet(null);
				this.genCos4005DbItemSet(null);
			}
			
			// 相關資料頁籤
			java.util.List<Con0001Db> CFileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'C' and dbName = 'COS4001DB' and upLoadId = " + c.getId());
			this.genFileRowItemSet(CFileList, "C");
			if(CFileList!=null && CFileList.size()>0){
				CFileList.clear();
			}
		}
		return obj;
	}
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOSIN0302F(this);
	}
	
	@Override
	public void doDelete() throws Exception {
		ServiceGetter.getInstance().getTcbwService().getCosinDao().deleteByCOSIN0302F(this);
	}
	
	
}
