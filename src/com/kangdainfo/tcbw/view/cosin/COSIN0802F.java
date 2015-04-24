package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0006Db;
import com.kangdainfo.tcbw.model.bo.Cos0016Db;

public class COSIN0802F extends COSIN0801F {
	
	private String isCloseUserInfo;		// 列印時是否遮蔽個資
	public String getIsCloseUserInfo() {		return checkGet(isCloseUserInfo);	}
	public void setIsCloseUserInfo(String isCloseUserInfo) {		this.isCloseUserInfo = checkSet(isCloseUserInfo);	}
	
	public Object doQueryOne() throws Exception {
		COSIN0802F obj = this;
		Cos0001Db c = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(getId()));
		if(c != null){
			
			// 基本頁籤
			setBaseData(obj, c);
			
			// 分類頁籤
			java.util.List<Cos0016Db> cos0016DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos0016Db where (cos0006Db.disDate <> '' or cos0006Db.disDate is not null) " +
																										" and cos0006Db.applNo = " + Common.sqlChar(c.getApplNo()) + 
																										" order by cos0006Db.disDate ");
			
			if(cos0016DbList!=null && cos0016DbList.size()>0){
				java.util.Map<String, String> CFRNameMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CFR", null);
				java.util.Map<String, String> CMSNameMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CMS", null);
				
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
			
			Cos0006Db cos0006Db = (Cos0006Db)View.getObject(" from Cos0006Db where (disDate = '' or disDate is null) " +
															" and applNo = " + Common.sqlChar(Common.get(c.getApplNo())));
			if(cos0006Db != null){
				obj.setCos0006DbId(Common.get(cos0006Db.getId()));
				obj.setFirstResult(cos0006Db.getFirstResult());
				obj.setLeaveCaseReason(cos0006Db.getLeaveCaseReason());
				obj.setNonDefective(cos0006Db.getNonDefective());
				obj.setdMarked(cos0006Db.getMarked());
				
				obj.setdLawlessIng(cos0006Db.getLawlessIng());
				obj.setdLawlessIngOther(cos0006Db.getLawlessIngOther());
				obj.setdExteriorError(cos0006Db.getExteriorError());
				obj.setdExteriorErrorOther(cos0006Db.getExteriorErrorOther());
				obj.setdPackageError(cos0006Db.getPackageError());
				obj.setdPackageErrorOther(cos0006Db.getPackageErrorOther());
				obj.setdExpired(cos0006Db.getExpired());
				obj.setdExpiredOther(cos0006Db.getExpiredOther());
				obj.setdOthers(cos0006Db.getOthers());
				obj.setdOthersDesc(cos0006Db.getOthersDesc());
				if(Common.get(cos0006Db.getMeasure()).length() > 0){
					String[] tmp = Common.get(cos0006Db.getMeasure()).split(",");
					obj.setMeasure(tmp);
				}else{
					obj.setMeasure(null);
				}
			}else{
				obj.setCos0006DbId("");
			}
			
			// 評估頁籤
			setAssessPageData(obj, c);
		}
		return obj;
	}
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOSIN0802F(this);
	}
	

}
