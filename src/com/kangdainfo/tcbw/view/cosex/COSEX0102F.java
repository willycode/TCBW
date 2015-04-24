package com.kangdainfo.tcbw.view.cosex;

import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Cos4001Db;
import com.kangdainfo.tcbw.model.bo.Cos4002Db;
import com.kangdainfo.tcbw.model.bo.Cos4003Db;
import com.kangdainfo.tcbw.model.bo.Cos4004Db;
import com.kangdainfo.tcbw.model.bo.Cos4005Db;

public class COSEX0102F extends COSEX0101F {
		
	public void doInsert() throws Exception {
		ServiceGetter.getInstance().getTcbwService().getCosexDao().insertByCOSEX0102F(this);
	}
	
	// 設定子報表路徑
	public void setParameter(java.util.HashMap<String, Object> params){
		String subreportDetail0FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/cos/COSIN0401R_DETAIL0.jasper");
		String subreportDetail1FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/cos/COSIN0401R_DETAIL1.jasper");
		params.put("subreport0", subreportDetail0FilePath);
		params.put("subreport1", subreportDetail1FilePath);
	}
	
	public boolean checkIsNeedSubReport(Cos4001Db obj){
		if(obj != null){
			if(Common.get(obj.getOtherExplain()).length() > 15){
				return true;
			}
			
			// 不良反應
			Cos4002Db cos4002Db = null;
			if(obj.getCos4002Dbs()!=null && obj.getCos4002Dbs().size()>0){
				for(Object dtl02Obj : obj.getCos4002Dbs()){
					Cos4002Db dtl02 = (Cos4002Db)dtl02Obj;
					cos4002Db = dtl02;
					
					if(cos4002Db != null){
						break;
					}
				}
			}
			if(cos4002Db != null){
				if(Common.get(cos4002Db.getNonSeriousOther()).length() > 18){
					return true;
				}
				if(Common.get(cos4002Db.getNonSeriousDis()).length() > 25){
					return true;
				}
				
				// 長度判斷由手動計算取得，之後有變動，此處也要跟著變
				if(Common.get(getCos4004Db(cos4002Db)).length() > 46){
					return true;
				}
				if(Common.get(getUseStatus(cos4002Db)).length() > 43){
					return true;
				}
				
				if(cos4002Db.getCos4005Dbs()!=null && cos4002Db.getCos4005Dbs().size()>0){
					return true;
				}
			}
		}
		return false;
	}
	
	public DefaultTableModel getTableModel() throws Exception {
		DefaultTableModel model = null;
		String[] cols = new String[]{ "applNo", "occurDate", "notifierRevDate", "notifierRepDate", "notifierSource",
									  "notifierName", "notifierTel", "notifierEamil", "address", "isContactYn",
									  "permitKey", "productName", "traffickWay", "businessName", "tradePlace", "manufactorName", "manufactorPlace", "manufactorTel",
									  "manufactorNo", "expirationDate", "tradeDate", "isSampleYn", "evenContactYn", "dealWith", "isRecurrenceYn", "isSimilarYn", "isOtherDeptYn",
									  "cos03", "isDamageYn", "otherExplain", "nonSeriousOther", "nonSeriousDis", "doc", "adverseCondition", 
									  "useStatus", "cos05", "isMitigateYn", "isRecurYn", "obj", "isCh" };
		
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		String hql = "from Cos4001Db where 1=1 ";
		if(!"".equals(getId()))
			hql += " and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += " and applNo = " + Common.sqlChar(getApplNo());
		System.out.println("[TCBW]-[COSEX0102F]-[QUERY] : " + hql);
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(list!=null && list.size()>0){
			for(Object dtlObj : list){
				Cos4001Db dtl = (Cos4001Db)dtlObj;
				
				boolean isChange = checkIsNeedSubReport(dtl);
				
				Object[] rowArray = new Object[cols.length];
				rowArray[0] = Common.get(dtl.getApplNo());
				rowArray[1] = Common.formatYYYMMDD(dtl.getOccurDate(), 2);
				rowArray[2] = Common.formatYYYMMDD(dtl.getNotifierRevDate(), 2);
				rowArray[3] = Common.formatYYYMMDD(dtl.getNotifierRepDate(), 2);
				rowArray[4] = getNotifierSource(dtl.getNotifierSource(), dtl.getNotifierSourceOther());
				rowArray[5] = Common.get(dtl.getNotifierName());
				rowArray[6] = Common.get(dtl.getNotifierTel());
				rowArray[7] = Common.get(dtl.getNotifierEamil());
				rowArray[8] = View.getLookupField("select codeName from CommonCode where commonCodeKind.codeKindId = 'CTY' and codeId = " + Common.sqlChar(dtl.getNotifierArea())) +
							  View.getLookupField("select zipname from Con1002Db where zipcode = " + Common.sqlChar(dtl.getNotifierZipCode())) + 
							  Common.get(dtl.getAddress());
				if("Y".equals(Common.get(dtl.getIsContactYn()))){
					rowArray[9] = "■是　□否";
				}else if("N".equals(Common.get(dtl.getIsContactYn()))){
					rowArray[9] = "□是　■否";
				}else{
					rowArray[9] = "□是　□否";
				}
				
				if(!"".equals(Common.get(dtl.getPermitKey()))){
					rowArray[10] = "□ 無" + "\n" + "■ 有 : " + getPermitKey(Common.get(dtl.getPermitKey())) + " 字第 " + Common.get(dtl.getPermitNo()) + " 號";
				}else{
					rowArray[10] = "■ 無" + "\n" + "■ 有 : " + "_____字第__________號";
				}
				rowArray[11] = "(中 文) " + Common.get(dtl.getChProduct()) + "\n" + "(英 文) " + Common.get(dtl.getEnProduct());
				rowArray[12] = getTraffickWay(Common.get(dtl.getTraffickWay()), Common.get(dtl.getTraffickWayOther()));
				rowArray[13] = Common.get(dtl.getBusinessName());
				rowArray[14] = View.getLookupField("select codeName from CommonCode where commonCodeKind.codeKindId = 'CTY' and codeId = " + Common.sqlChar(dtl.getTradePlace())) + 
				   			   View.getLookupField("select zipname from Con1002Db where zipcode = " + Common.sqlChar(dtl.getTradePlaceZipCode())) + 
				   			   Common.get(dtl.getTradePlaceAddr());
				rowArray[15] = Common.get(dtl.getManufactorName());
				rowArray[16] = View.getLookupField("select codeName from CommonCode where commonCodeKind.codeKindId = 'CTY' and codeId = " + Common.sqlChar(dtl.getManufactorArea())) + 
	   			   			   View.getLookupField("select zipname from Con1002Db where zipcode = " + Common.sqlChar(dtl.getManufactorZipCode())) + 
	   			   			   Common.get(dtl.getManufactorAddr());
				rowArray[17] = (Common.get(dtl.getManufactorTelArea()).equals("")?"":Common.get(dtl.getManufactorTelArea())) + 
				   			   (Common.get(dtl.getManufactorTel()).equals("")?"":("-" + Common.get(dtl.getManufactorTel()))) + 
				   			   (Common.get(dtl.getManufactorTelExt()).equals("")?"":("#" + Common.get(dtl.getManufactorTelExt())));
				rowArray[18] = Common.get(dtl.getManufactorNo());
				rowArray[19] = Common.formatYYYMMDD(dtl.getExpirationDate());
				rowArray[20] = Common.formatYYYMMDD(dtl.getTradeDate(), 3);
				
				if("Y".equals(Common.get(dtl.getIsSampleYn()))){
					rowArray[21] = "■ 是　□ 否";
				}else{
					rowArray[21] = "□ 是　■ 否";
				}
				
				if("Y".equals(Common.get(dtl.getEvenContactYn()))){
					rowArray[22] = "■ 是　□ 否";
				}else{
					rowArray[22] = "□ 是　■ 否";
				}
				if("N".equals(Common.get(dtl.getDealWith()))){
					rowArray[23] = "■無處理  □單一換貨  □整批換貨";
				} else if("O".equals(Common.get(dtl.getDealWith()))){
					rowArray[23] = "□無處理  ■單一換貨  □整批換貨";
				}else if("A".equals(Common.get(dtl.getDealWith()))){
					rowArray[23] = "□無處理  □單一換貨  ■整批換貨";
				}else{
					rowArray[23] = "□無處理  □單一換貨  □整批換貨";
				}
				if("Y".equals(Common.get(dtl.getIsRecurrenceYn()))){
					rowArray[24] = "■ 是   □ 否";
				}else{
					rowArray[24] = "□ 是   ■ 否";
				}
				
				if("Y".equals(Common.get(dtl.getIsSimilarYn()))){
					rowArray[25] = "■ 是	 □ 否";
				}else{
					rowArray[25] = "□ 是	 ■ 否";
				}
				
				if("Y".equals(Common.get(dtl.getIsOtherDeptYn()))) {
					rowArray[26] = "■ 是 : " + Common.get(dtl.getOtherDpetName()) + "\n" + "□ 否 ";
				} else {
					rowArray[26] = "□ 是 : __________" + "\n" + "■ 否 ";
				}
				
				String main = "";
				String sub = "";
				if(dtl.getCos4003Dbs()!=null && dtl.getCos4003Dbs().size()>0){
					for(Object dtl03Obj : dtl.getCos4003Dbs()){
						Cos4003Db cos4003Db = (Cos4003Db)dtl03Obj;
						main += cos4003Db.getMainCode() + ",";
						sub += cos4003Db.getSubCode() + ",";
					}
					
					String[] maincode = main.split(",");
					String[] subcode = sub.split(",");
					rowArray[27] = getCheckBoxOption2("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='COSDPD' order by codeId", maincode, subcode, Common.get(dtl.getId()));
				}else{
					rowArray[27] = getCheckBoxOption2("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='COSDPD' order by codeId", null, null, Common.get(dtl.getId()));
				}
				
				if("Y".equals(dtl.getIsDamageYn())){
					rowArray[28] = "■ 是，請描述：" + Common.get(dtl.getOtherInformation()) + " □ 否" + "\n" + "□ 其他，請描述 : _____";
				}else if("N".equals(dtl.getIsDamageYn())){
					rowArray[28] = "□ 是，請描述：_____　■ 否" + "\n" + "□ 其他，請描述 : _____";
				}else if("O".equals(dtl.getIsDamageYn())){
					rowArray[28] = "□ 是，請描述：_____　□ 否" + "\n" + "■ 其他，請描述：" + Common.get(dtl.getOtherIsDamageYn());
				}else{
					rowArray[28] = "□ 是，請描述：_____　□ 否" + "\n" + "□ 其他，請描述 : _____";
				}
				
				if(Common.get(dtl.getOtherExplain()).length() > 15){
					rowArray[29] = Common.get(dtl.getOtherExplain()).substring(0, 5) + "(完整內容詳見第二頁)";
				}else{
					rowArray[29] = Common.get(dtl.getOtherExplain());
				}
				
				Cos4002Db cos4002Db = null;
				if(dtl.getCos4002Dbs()!=null && dtl.getCos4002Dbs().size()>0){
					for(Object dtl02Obj : dtl.getCos4002Dbs()){
						Cos4002Db dtl02 = (Cos4002Db)dtl02Obj;
						cos4002Db = dtl02;
						
						if(cos4002Db != null){
							break;
						}
					}
				}
				if(cos4002Db != null){
					if(Common.get(cos4002Db.getNonSeriousOther()).length() > 18){
						rowArray[30] = Common.get(cos4002Db.getNonSeriousOther()).substring(0, 5) + "(完整內容詳見第二頁)";
					}else{
						rowArray[30] = Common.get(cos4002Db.getNonSeriousOther());
					}
					if(Common.get(cos4002Db.getNonSeriousDis()).length() > 25){
						rowArray[31] = Common.get(cos4002Db.getNonSeriousDis()).substring(0, 5) + "(完整內容詳見第二頁)";
					}else{
						rowArray[31] = Common.get(cos4002Db.getNonSeriousDis());
					}
					
					String doc = getCos4004Db(cos4002Db);
					if(doc.length() > 46){
						rowArray[32] = doc.substring(0,9) + "(完整內容詳見第二頁)";
					}else{
						rowArray[32] = "";
					}
					rowArray[33] = getAdverseCondition(Common.get(cos4002Db.getAdverseCondition()), "");
					
					String useStatus = getUseStatus(cos4002Db);
					if(useStatus.length() > 43){
						rowArray[34] = getPresentUseStatus(cos4002Db);//useStatus.substring(0,5) + "(完整內容詳見第二頁)";
					}else{
						rowArray[34] = useStatus;
					}
					
					if(cos4002Db.getCos4005Dbs()!=null && cos4002Db.getCos4005Dbs().size()>0){
						rowArray[35] = "(完整內容詳見第二頁)";
					}else{
						rowArray[35] = "無";
					}
					
					rowArray[36] = getYNU(Common.get(cos4002Db.getIsMitigateYn()));
					rowArray[37] = getYNU(Common.get(cos4002Db.getIsRecurYn()));
				}else{
					rowArray[30] = "";
					rowArray[31] = "";
					rowArray[32] = "";
					rowArray[33] = getAdverseCondition("", "");
					rowArray[34] = getUseStatus(null);
					rowArray[35] = "無";
					rowArray[36] = getYNU("");
					rowArray[37] = getYNU("");
				}
				
				if(isChange){
					rowArray[38] = new JRTableModelDataSource(getPage2TableModel(dtl));
					rowArray[39] = "Y";
				}else{
					rowArray[38] = null;
					rowArray[39] = "N";
				}
				arrList.add(rowArray);
			}
			list.clear();
		}
		list = null;
		
		if(arrList!=null && arrList.size()>0){
			model = new javax.swing.table.DefaultTableModel();
			Object[][] rs = new Object[0][0];
			rs = (Object[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		
		return model;
	}
	
	public DefaultTableModel getPage2TableModel(Cos4001Db obj) throws Exception {
		if(obj == null){
			return null;
		}
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		String[] cols = new String[]{ "otherExplain", "nonSeriousOther", "nonSeriousDis", "doc", "useStatus", "dtl", "isShow" };
		
		Object[] rowArray = new Object[cols.length];
		rowArray[0] = Common.get(obj.getOtherExplain());
		
		// 不良反應
		Cos4002Db cos4002Db = null;
		if(obj.getCos4002Dbs()!=null && obj.getCos4002Dbs().size()>0){
			for(Object dtl02Obj : obj.getCos4002Dbs()){
				Cos4002Db dtl02 = (Cos4002Db)dtl02Obj;
				cos4002Db = dtl02;
				
				if(cos4002Db != null){
					break;
				}
			}
		}
		
		if(cos4002Db != null){
			rowArray[1] = Common.get(cos4002Db.getNonSeriousOther());
			rowArray[2] = Common.get(cos4002Db.getNonSeriousDis());
			
			String doc = getCos4004Db(cos4002Db);
			if(doc.length() > 46){
				rowArray[3] = doc;
			}else{
				rowArray[3] = "";
			}
			if(cos4002Db.getCos4005Dbs()!=null && cos4002Db.getCos4005Dbs().size()>0){
				rowArray[5] = new JRTableModelDataSource(getCos05SubModel(cos4002Db.getCos4005Dbs()));
				rowArray[6] = "Y";
			}else{
				rowArray[5] = null;
				rowArray[6] = "N";
			}
		}else{
			rowArray[1] = "";
			rowArray[2] = "";
			rowArray[3] = "";
			rowArray[5] = null;
			rowArray[6] = "N";
		}
		rowArray[4] = getUseStatus(cos4002Db);
		arrList.add(rowArray);
		
		if(arrList!=null && arrList.size()>0){
			Object[][] rs = new Object[0][0];
			rs = (Object[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		return model;
	}
	
	public String getCos4004Db(Cos4002Db cos4002Db){
		StringBuffer doc = new StringBuffer();
		if(cos4002Db != null){
			doc.append("醫師診斷證明 : ").append(Common.get(cos4002Db.getDiagnosisProof()).equals("")?"":Common.get(cos4002Db.getDiagnosisProof()));
			doc.append("\n");
			doc.append("就醫記錄(病歷報告) : ").append(Common.get(cos4002Db.getDiagnosisReport()).equals("")?"":Common.get(cos4002Db.getDiagnosisReport()));
			doc.append("\n");
			doc.append("其他相關資料 : ").append(Common.get(cos4002Db.getDiagnosisOther()).equals("")?"":Common.get(cos4002Db.getDiagnosisOther()));
			doc.append("\n");
			doc.append("相關檢查及檢驗數據 : ");
			if(cos4002Db.getCos4004Dbs()!=null && cos4002Db.getCos4004Dbs().size()>0){
				for(Object dtl04Obj : cos4002Db.getCos4004Dbs()){
					Cos4004Db cos4004Db = (Cos4004Db)dtl04Obj;
					if("".equals(Common.get(cos4004Db.getTestDate())) && "".equals(Common.get(cos4004Db.getTestItems())) && "".equals(Common.get(cos4004Db.getTestNum()))){
						continue;
					}
					doc.append("\n");
					doc.append(Common.get(cos4004Db.getTestDate()))
					   .append(Common.get(cos4004Db.getTestItems()).equals("")?"":("、" + Common.get(cos4004Db.getTestItems())))
					   .append(Common.get(cos4004Db.getTestNum()).equals("")?"":("、" + Common.get(cos4004Db.getTestNum())));
				}
			}
			
		}
		
		return doc.toString();
	}
	
	public String getUseStatus(Cos4002Db cos4002Db){
		StringBuffer sb = new StringBuffer();
		if(cos4002Db != null){
			sb.append("起迄時間 : ")
			  .append(Common.get(cos4002Db.getUseDateS()).equals("")?"  年  月  日" : Common.formatYYYMMDD(cos4002Db.getUseDateS(), 2))
			  .append(" ~")
			  .append(Common.get(cos4002Db.getUseDateE()).equals("")?"  年  月  日" : Common.formatYYYMMDD(cos4002Db.getUseDateE(), 2));
			sb.append("\n");
			sb.append("使用方法 : ").append(Common.get(cos4002Db.getUseMethod()).equals("")?"":("\n" + Common.get(cos4002Db.getUseMethod())));
			sb.append("\n");
			sb.append("使用頻率 : ").append(Common.get(cos4002Db.getUseRate()).equals("")?"":("\n" + Common.get(cos4002Db.getUseRate())));
		}else{
			sb.append("起迄時間 : ")
			  .append("  年  月  日")
			  .append(" ~")
			  .append("  年  月  日");
			sb.append("\n");
			sb.append("使用方法 : ");
			sb.append("\n");
			sb.append("使用頻率 : ");
		}
		return sb.toString();
	}
	
	public String getPresentUseStatus(Cos4002Db cos4002Db){
		StringBuffer sb = new StringBuffer();
		if(cos4002Db != null){
			sb.append("起迄時間 : ")
			  .append(Common.get(cos4002Db.getUseDateS()).equals("")?"  年  月  日" : Common.formatYYYMMDD(cos4002Db.getUseDateS(), 2))
			  .append(" ~")
			  .append(Common.get(cos4002Db.getUseDateE()).equals("")?"  年  月  日" : Common.formatYYYMMDD(cos4002Db.getUseDateE(), 2));
			sb.append("\n");
			
			String method = Common.get(cos4002Db.getUseMethod()).length()>5?Common.get(cos4002Db.getUseMethod()).substring(0,5):Common.get(cos4002Db.getUseMethod());
			sb.append("使用方法 : ").append(method.equals("")?"":(method + "(完整內容詳見第二頁)"));
			sb.append("\n");
			
			String rate = Common.get(cos4002Db.getUseRate()).length()>5?Common.get(cos4002Db.getUseRate()).substring(0,5):Common.get(cos4002Db.getUseRate());
			sb.append("使用頻率 : ").append(rate.equals("")?"":( rate + "(完整內容詳見第二頁)"));
		}
		return sb.toString();
	}
	
	
	public String getFieldCommonCodeKindHQL(String codeKindId) {
		return "select codeId, codeName from CommonCode where commonCodeKind.codeKindId = " + Common.sqlChar(codeKindId);
	}

	// 通報來源
	public String getNotifierSource(String NotifierSource, String NotifierSourceOther) {	
		String hql = getFieldCommonCodeKindHQL("CIS");
		StringBuffer sb = new StringBuffer();
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Object[] dtl = (Object[])list.get(i);
				
				String checkbox = "□";
				if(Common.get(dtl[0]).equals(Common.get(NotifierSource))){
					checkbox = "■" + Common.get(dtl[1]);
					if("07".equals(Common.get(NotifierSource))){
						checkbox += "，" + NotifierSourceOther;
					}
				}else if("07".equals(Common.get(dtl[0]))){
					checkbox += Common.get(dtl[1]) + "，" + "_______";
				}else{
					checkbox += Common.get(dtl[1]);
				}
				
				if(i!=0 && i%3==0){
					sb.append(checkbox).append("\n");
				}else{
					sb.append(checkbox).append("　");
				}
			}
			list.clear();
		}
		list = null;
		
		return sb.toString();
	}
	
	// 許可證字
	public String getPermitKey(String PermitKey) {
		String hql = getFieldCommonCodeKindHQL("CPT");
		StringBuffer sb = new StringBuffer();
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Object[] dtl = (Object[])list.get(i);
				String checkbox = "";				
				if(Common.get(dtl[0]).equals(PermitKey)){
					checkbox = Common.get(dtl[1]);
				}
				sb.append(checkbox);
				if(!"".equals(checkbox)){
					break;
				}
			}
			list.clear();
		}
		list = null;
		
		return sb.toString();
	}
	
	// 販賣通路
	public String getTraffickWay(String TraffickWay, String TraffickWayOther){
		String hql = getFieldCommonCodeKindHQL("CSP");
		StringBuffer sb = new StringBuffer();
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Object[] dtl = (Object[])list.get(i);
				
				String checkbox = "□";
				if(Common.get(dtl[0]).equals(Common.get(TraffickWay))){
					checkbox = "■" + Common.get(dtl[1]);
					if("10".equals(Common.get(TraffickWay))) {
						checkbox += "，" + Common.get(TraffickWayOther);
					}
				}else if("10".equals(dtl[0])) {
					checkbox = checkbox + Common.get(dtl[1]) + "，" + "_______";
				}else{
					checkbox = checkbox + Common.get(dtl[1]);
				}
				if((i+1)%2==0){
					sb.append(checkbox).append("\n");
				}else{
					sb.append(checkbox).append("　");
				}
			}
			list.clear();
		}
		list = null;
		
		return sb.toString();
	}
	
	// 不良品缺陷之描述
	public static String getCheckBoxOption2(String sql, String[] selectedCheckBox1, String[] selectedCheckBox2, String id){
		StringBuilder sb = new StringBuilder();
	    java.util.List list1 = ServiceGetter.getInstance().getTcbwService().load(sql);
	    if(list1!=null && list1.size()>0){
	        for(int i=0; i<list1.size(); i++){
	        	Object[] o = (Object[]) list1.get(i);
	        	if(Common.get(o[0]).length() < 2){
        			continue;
        		}
	        	
	        	// 為了報表漂亮
	        	if(i == 0){
	        		sb.append("(").append(i+1).append(")").append(Common.get(o[1])).append(" : ");
	        	}else if(i==1 || i==2 || i==3){
	        		sb.append("(").append(i+1).append(")").append(Common.get(o[1])).append(" : ");
	        	}else if(i==4 || i==5){
	        		sb.append("(").append(i+1).append(")").append(Common.get(o[1])).append("   ");
	        	}
	        	
        		String showType = "1"; 						// 描述說明顯示方式

        		// 第2層 
        		java.util.List list2 = ServiceGetter.getInstance().getTcbwService().load(" select dpdKind, dpdKindName from Cos1001Db where substring(dpdKind,1,2) = " + Common.sqlChar(Common.get(o[0]).substring(0,2)) +   
        																				 " and isStop = 'N' order by dpdKind");
        		if(list2!=null && list2.size()>0){
        			for(int k=0; k<list2.size(); k++){
        				boolean isSelect = false;
        				Object[] o2 = (Object[]) list2.get(k);
                		if(selectedCheckBox2!=null && selectedCheckBox2.length>0){
                			for(int l=0; l<selectedCheckBox2.length; l++){
                				if(Common.get(o2[0]).equals(selectedCheckBox2[l])) {
                					sb.append("■");
                					isSelect = true;
                				}
                			}
                		}
                		if(!isSelect){
                			sb.append("□");
                		}
                		
                		// 為了報表漂亮
                		if(i==2 && k==3){
                			sb.append(o2[1]).append("\n").append("              ");
                		}else{
                			sb.append(o2[1]).append(" ");
                		}
                		
        			}
        			list2.clear();
        		}else{
        			showType = "2";
        		}
        		list2 = null;
        		
        		// 放置說明的value
        		String otherDescribeValue = View.getLookupField(" select otherDescribe from Cos4003Db where cos4001Db.id = " + Common.getLong(id) + 
        														" and mainCode = " + Common.sqlChar(Common.get(o[0])));	
        		if("2".equals(showType)){
        			if(!"".equals(otherDescribeValue)) {
	        		    sb.append("請描述：" ).append(otherDescribeValue).append("\n");
        			}else{
        				sb.append("請描述：" ).append("_____").append("\n");
        			}
        		}else{
        			if(!"".equals(otherDescribeValue)) {
	        			sb.append(otherDescribeValue).append( "\n");
        			}else{
	        			sb.append("_____").append( "\n");
        			}
        		}
        	}    		
	        list1.clear();
    	}
	    list1 = null;
	    
        return sb.toString();
	}
	
	// 不良反應結果
	public String getAdverseCondition(String AdverseCondition, String nonSeriousOther) {
		String hql = getFieldCommonCodeKindHQL("CAC");
		StringBuilder sb = new StringBuilder();
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Object[] obj = (Object[]) list.get(i);
				String checkbox = "□";
				if(Common.get(obj[0]).equals(Common.get(AdverseCondition))){
					checkbox = "(" + (i+1) + ") " + "■" + Common.get(obj[1]);
				}else{
					checkbox = "(" + (i+1) + ") " + checkbox + Common.get(obj[1]);
				}
				if(sb.toString().length() > 0){
					sb.append("\n");
				}
				sb.append(checkbox);
			}
		}
		
		return sb.toString();
	}
	
	// 併用其它化粧品
	public DefaultTableModel getCos05SubModel(java.util.Set cos4005Dbs) throws Exception {
		String[] col = new String[]{ "cName", "manufactorName", "useDate",
									   "useRate", "useMethod", "manufactorNo", "expirationDate", "tradeDate"};
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		if(cos4005Dbs!=null && cos4005Dbs.size()>0){
			for(Object dtlObj : cos4005Dbs){
				Cos4005Db cos4005Db = (Cos4005Db)dtlObj;
				
				String[] rowArray = new String[col.length];
				rowArray[0] = Common.get(cos4005Db.getcName());
				rowArray[1] = Common.get(cos4005Db.getManufactorName());
				if(!"".equals(Common.get(cos4005Db.getUseDateS())) && !"".equals(Common.get(cos4005Db.getUseDateE()))){
					rowArray[2] = Common.formatYYYMMDD(cos4005Db.getUseDateS(), 2) + "\n" + Common.formatYYYMMDD(cos4005Db.getUseDateE(), 2);
				}else{
					rowArray[2] = Common.formatYYYMMDD(cos4005Db.getUseDateS(), 2) + Common.formatYYYMMDD(cos4005Db.getUseDateE(), 2);
				}
				rowArray[3] = Common.get(cos4005Db.getUseRate());
				rowArray[4] = Common.get(cos4005Db.getUseMethod());
				rowArray[5] = Common.get(cos4005Db.getManufactorNo());
				rowArray[6] = Common.formatYYYMMDD(cos4005Db.getExpirationDate(),2);
				rowArray[7] = Common.formatYYYMMDD(cos4005Db.getTradeDate(),2);
				arrList.add(rowArray);
			}
		}
		
		if(null != arrList && arrList.size() >0){
			String[][] rs = new String[0][0];
			rs = (String[][])arrList.toArray(rs);
			model.setDataVector(rs, col);
		}
		return model;
	}
	
	public String getYNU(String YNU){
		StringBuffer sb = new StringBuffer();
		if("Y".equals(Common.get(YNU))){
			sb.append("■ 是    □ 否    □ 無法得知");
		}else if("N".equals(Common.get(YNU))){
			sb.append("□ 是    ■ 否    □ 無法得知");
		}else if("U".equals(Common.get(YNU))){
			sb.append("□ 是    □ 否    ■ 無法得知");
		}else{
			sb.append("□ 是    □ 否    □ 無法得知");
		}
		return sb.toString();
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		COSEX0102F obj = this;
		Cos4001Db c = (Cos4001Db)ServiceGetter.getInstance().getCommonService().getObject(" from Cos4001Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setApplNo(c.getApplNo());
			obj.setStatus(c.getStatus());
			obj.setCosType(c.getCosType());
			obj.setNotifierType(c.getNotifierType());
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
								COSIDFileList.clear();
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
		ServiceGetter.getInstance().getTcbwService().getCosexDao().updateByCOSEX0102F(this, false);
	}
	
	@Override
	public void doDelete() throws Exception {
		ServiceGetter.getInstance().getTcbwService().getCosexDao().deleteByCOSEX0102F(this);
	}
	
	
	/*	
	public DefaultTableModel getDefaultTableModel() throws Exception
	{
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[] 
    	        {"id","applNo","status","occurDate","chargeMan","notifierRevDate","notifierRepDate","notifierSource",
    			"notifierName","notifierTitle","notifierDept","notifierTel",
    			"notifierPhone","notifierFax","address","notifierEmail","notifierType","isContactYn",
    			"cosType","permitKey","permitNo","chProduct","enProduct","caseNo","ingredient",
    			"traffickWay","tradePlace","tradeDate","BusinessName","manufactorName",
    			"manufactorAddr","manufactorTel","manufactorNo","expirationDate","isSampleYn",
    			"evenContactYn","dealWith","isSimilarYn","isRecurrenceYn","isOtherDeptYn",
    			"AdverseCondition","useDateS","useDateE","useMethod","useRate","isMitigateYn","isRecurYn",
    			"diagnosisProof","diagnosisReport","diagnosisOther","obj2","obj1","defectDesc","isDamageYn",
    	        };
    	java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		
		String hql = "from Cos4001Db where 1=1 ";
		
		if(!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				Cos4001Db obj = (Cos4001Db) list.get(i);
				Object rowArray[]=new Object[cols.length];
				rowArray[0]=Common.get(obj.getId());
				rowArray[1]=Common.get(obj.getApplNo());
				rowArray[2]=Common.get(obj.getStatus());
				rowArray[3]=Common.formatYYYMMDD(obj.getOccurDate(),2);
				rowArray[4]=Common.get(obj.getChargeMan());
				rowArray[5]=Common.formatYYYMMDD(obj.getNotifierRevDate(),2); 
				rowArray[6]=Common.formatYYYMMDD(obj.getNotifierRepDate(),2); 
				rowArray[7]=getNotifierSource(Common.get(obj.getNotifierSource()),Common.get(obj.getNotifierSourceOther()));
				rowArray[8]=Common.get(obj.getNotifierName());
				rowArray[9]=Common.get(obj.getNotifierTitle());
				rowArray[10]=Common.get(obj.getNotifierDept());
				rowArray[11]=Common.get(obj.getNotifierTel());
				rowArray[12]=Common.get(obj.getNotifierPhone());
				rowArray[13]=Common.get(obj.getNotifierFax());
				rowArray[14]=Common.get(obj.getAddress());
				rowArray[15]=Common.get(obj.getNotifierEamil());
				rowArray[16]=Common.get(obj.getNotifierType());
				if("Y".equals(Common.get(obj.getIsContactYn()))) {
					rowArray[17]="■是　　□否";
				} else {
					rowArray[17]="□是　　■否";
				}
				rowArray[18]=getCosType(Common.get(obj.getCosType()));
				
				if(!"".equals(Common.get(obj.getPermitKey()))) {
					rowArray[19]="□無　■有　" + getPermitKey(Common.get(obj.getPermitKey()));
					rowArray[20]=Common.get(obj.getPermitNo());
				} else {
					rowArray[19]="■無　□有　" + getPermitKey(Common.get(obj.getPermitKey()));
					rowArray[20]=Common.get(obj.getPermitNo());
				}
				System.out.println("許可證字: " + Common.get(obj.getPermitKey()));
				
				rowArray[21]=Common.get(obj.getChProduct());
				rowArray[22]=Common.get(obj.getEnProduct());
				rowArray[23]=Common.get(obj.getCaseNo());
				
				rowArray[24]=Common.get(obj.getIngredient());
				rowArray[25]=getTraffickWay(Common.get(obj.getTraffickWay()),Common.get(obj.getTraffickWayOther()));

				rowArray[26]=getTradePlace(Common.get(obj.getTradePlace()));
				rowArray[27]=Common.formatYYYMMDD(obj.getTradeDate(),2);
				rowArray[28]=Common.get(obj.getBusinessName());
				rowArray[29]=Common.get(obj.getManufactorName());
				rowArray[30]=Common.get(obj.getManufactorAddr());
				rowArray[31]=Common.get(obj.getManufactorTel());
				rowArray[32]=Common.get(obj.getManufactorNo());
				rowArray[33]=Common.formatYYYMMDD(obj.getExpirationDate());
				if("Y".equals(Common.get(obj.getIsSampleYn()))) {
					rowArray[34]="■是　　□否";
				} else {
					rowArray[34]="□是　　■否";
				}
				if("Y".equals(Common.get(obj.getEvenContactYn()))) {
					rowArray[35]="■是　　□否";
				} else {
					rowArray[35]="□是　　■否";
				}
				if("N".equals(Common.get(obj.getDealWith()))) {
					rowArray[36]="■無處理　　□單一換貨　　□整批換貨";
				} else if("O".equals(Common.get(obj.getDealWith()))) {
					rowArray[36]="□無處理　　■單一換貨　　□整批換貨";
				} else if("A".equals(Common.get(obj.getDealWith()))) {
					rowArray[36]="□無處理　　□單一換貨　　■整批換貨";
				} else {
					rowArray[36]="□無處理　　□單一換貨　　□整批換貨";
				}
				if("Y".equals(Common.get(obj.getIsSimilarYn()))) {
					rowArray[37]="■是　　□否";
				} else {
					rowArray[37]="□是　　■否";
				}
				if("Y".equals(Common.get(obj.getIsRecurrenceYn()))) {
					rowArray[38]="■是　　□否";
				} else {
					rowArray[38]="□是　　■否";
				}
				if("Y".equals(Common.get(obj.getIsOtherDeptYn()))) {
					rowArray[39]="■是" + Common.get(obj.getOtherDpetName()) +  "　　□否";
				} else {
					rowArray[39]="□是　　■否";
				}
				
				if(obj.getCos4002Dbs()!=null && obj.getCos4002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getCos4002Dbs().iterator();
					while(it2.hasNext())
					{
						Cos4002Db cos4002Db = (Cos4002Db)it2.next();
						rowArray[40]=getAdverseCondition(cos4002Db.getAdverseCondition(),cos4002Db.getNonSeriousOther());
						if(!"".equals(Common.formatYYYMMDD(cos4002Db.getUseDateS())) && !"".equals(Common.formatYYYMMDD(cos4002Db.getUseDateE()))) {
							rowArray[41]=Common.formatYYYMMDD(cos4002Db.getUseDateS(),2) + "　~　";
							rowArray[42]=Common.formatYYYMMDD(cos4002Db.getUseDateE(),2);
						} else {
							rowArray[41]="";
							rowArray[42]="";
						}
						rowArray[43]=Common.get(cos4002Db.getUseMethod());
						rowArray[44]=Common.get(cos4002Db.getUseRate());
						if("Y".equals(Common.get(cos4002Db.getIsMitigateYn()))) {
							rowArray[45]="■是　□否　□無法得知";
						} else if("N".equals(Common.get(cos4002Db.getIsMitigateYn()))) {
							rowArray[45]="□是　■否　□無法得知";
						} else if("U".equals(Common.get(cos4002Db.getIsMitigateYn()))) {
							rowArray[45]="□是　□否　■無法得知";
						} else {
							rowArray[45]="□是　□否　□無法得知";
						}
						
						if("Y".equals(Common.get(cos4002Db.getIsRecurYn()))) {
							rowArray[46]="■是　□否　□無法得知";
						} else if("N".equals(Common.get(cos4002Db.getIsRecurYn()))) {
							rowArray[46]="□是　■否　□無法得知";
						} else if("U".equals(Common.get(cos4002Db.getIsRecurYn()))) {
							rowArray[46]="□是　□否　■無法得知";
						} else {
							rowArray[46]="□是　□否　□無法得知";
						}
						
						rowArray[47]=Common.get(cos4002Db.getDiagnosisProof());
						rowArray[48]=Common.get(cos4002Db.getDiagnosisReport());
						rowArray[49]=Common.get(cos4002Db.getDiagnosisOther());
						if(cos4002Db.getCos4005Dbs()!=null && cos4002Db.getCos4005Dbs().size()>0)
						{
							java.util.Iterator it3 = cos4002Db.getCos4005Dbs().iterator();
							rowArray[50] = new JRTableModelDataSource(getSubModel02(it3));
						}
						if(cos4002Db.getCos4004Dbs()!=null && cos4002Db.getCos4004Dbs().size()>0)
						{
							java.util.Iterator it4 = cos4002Db.getCos4004Dbs().iterator();
							rowArray[51] = new JRTableModelDataSource(getSubModel01(it4));
						}

					}
				} else {
					rowArray[40]="";
					rowArray[41]="";
					rowArray[42]="";
					rowArray[43]="";
					rowArray[44]="";
					rowArray[45]="";
					rowArray[46]="";
					rowArray[47]="";
					rowArray[48]="";
					rowArray[49]="";
					rowArray[50]=null;
					rowArray[51]=null;
				}
				//不良品缺陷資料
				if(obj.getCos4003Dbs()!=null && obj.getCos4003Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getCos4003Dbs().iterator();
					
					String[] maincode = null;
					String[] subcode = null;
					String main = "";
					String sub = "";
					
					while(it2.hasNext())
					{
						Cos4003Db cos4003Db = (Cos4003Db)it2.next();
						
						main += cos4003Db.getMainCode() + ",";
						sub += cos4003Db.getSubCode() + ",";
					}
					maincode = main.split(",");
					subcode = sub.split(",");
					
					rowArray[52]=getCheckBoxOption2("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='COSDPD' order by codeId",maincode,subcode,Common.get(obj.getId()));
				} else {
					rowArray[52]="";
				}
				
				
				if("Y".equals(obj.getIsDamageYn())) {
					rowArray[53]="■是，請描述：" + obj.getOtherInformation() + "　□否　□其他";
				} else if("N".equals(obj.getIsDamageYn())) {
					rowArray[53]="□是，請描述：_____　■否　□其他";
				} else if("O".equals(obj.getIsDamageYn())) {
					rowArray[53]="□是，請描述：_____　□否　■其他，請描述：" + obj.getOtherIsDamageYn();
				} else {
					rowArray[53]="□是，請描述：_____　□否　□其他";
				}
				
				arrList.add(rowArray);
			}
		}
		if (arrList!=null && arrList.size()>0) {
			Object[][] rs = new Object[0][0];
			rs = (Object[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		
		return model;
		
	}
	*/

}
