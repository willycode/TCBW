package com.kangdainfo.tcbw.view.hfrin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.BeanUtil;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;
import com.kangdainfo.tcbw.model.bo.Hfr0003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFRIN0201Q extends SuperBean {

	private String q_notifierRevDateS;
	private String q_notifierRevDateE;
	private String q_informedSources;
	private String q_notifierType;
	private String q_notifierTitle;
	private String q_eatersSex;
	private String q_eatersAgeS;
	private String q_eatersAgeE;
	private String q_permitKey;
	private String q_permitNo;
	private String q_buySource;
	private String q_unExpectedClassify;
	private String q_unExpectedReason;
	private String q_evidentiary;
	private String q_thiSeverity;
	private String q_administrativeLevel;
	private String[] reportField;
	private String[] q_type;
	private String q_chProduct;
	private String q_status;

	//食品統計筆數
	private int healthyCount, unHealthyCount, generalCount;

	public String getQ_notifierRevDateS() {
		return checkGet(q_notifierRevDateS);
	}
	public void setQ_notifierRevDateS(String qNotifierRevDateS) {
		q_notifierRevDateS = checkSet(qNotifierRevDateS);
	}
	public String getQ_notifierRevDateE() {
		return checkGet(q_notifierRevDateE);
	}
	public void setQ_notifierRevDateE(String qNotifierRevDateE) {
		q_notifierRevDateE = checkSet(qNotifierRevDateE);
	}
	public String getQ_informedSources() {
		return checkGet(q_informedSources);
	}
	public void setQ_informedSources(String qInformedSources) {
		q_informedSources = checkSet(qInformedSources);
	}
	public String getQ_notifierType() {
		return checkGet(q_notifierType);
	}
	public void setQ_notifierType(String qNotifierType) {
		q_notifierType = checkSet(qNotifierType);
	}
	public String getQ_notifierTitle() {
		return checkGet(q_notifierTitle);
	}
	public void setQ_notifierTitle(String qNotifierTitle) {
		q_notifierTitle = checkSet(qNotifierTitle);
	}
	public String getQ_eatersSex() {
		return checkGet(q_eatersSex);
	}
	public void setQ_eatersSex(String qEatersSex) {
		q_eatersSex = checkSet(qEatersSex);
	}
	public String getQ_eatersAgeS() {
		return checkGet(q_eatersAgeS);
	}
	public void setQ_eatersAgeS(String qEatersAgeS) {
		q_eatersAgeS = checkSet(qEatersAgeS);
	}
	public String getQ_eatersAgeE() {
		return checkGet(q_eatersAgeE);
	}
	public void setQ_eatersAgeE(String qEatersAgeE) {
		q_eatersAgeE = checkSet(qEatersAgeE);
	}
	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}
	public void setQ_permitKey(String qPermitKey) {
		q_permitKey = checkSet(qPermitKey);
	}
	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}
	public void setQ_permitNo(String qPermitNo) {
		q_permitNo = checkSet(qPermitNo);
	}
	public String getQ_buySource() {
		return checkGet(q_buySource);
	}
	public void setQ_buySource(String qBuySource) {
		q_buySource = checkSet(qBuySource);
	}
	public String getQ_unExpectedClassify() {
		return checkGet(q_unExpectedClassify);
	}
	public void setQ_unExpectedClassify(String qUnExpectedClassify) {
		q_unExpectedClassify = checkSet(qUnExpectedClassify);
	}
	public String getQ_unExpectedReason() {
		return checkGet(q_unExpectedReason);
	}
	public void setQ_unExpectedReason(String qUnExpectedReason) {
		q_unExpectedReason = checkSet(qUnExpectedReason);
	}
	public String getQ_evidentiary() {
		return checkGet(q_evidentiary);
	}
	public void setQ_evidentiary(String qEvidentiary) {
		q_evidentiary = checkSet(qEvidentiary);
	}
	public String getQ_thiSeverity() {
		return checkGet(q_thiSeverity);
	}
	public void setQ_thiSeverity(String qThiSeverity) {
		q_thiSeverity = checkSet(qThiSeverity);
	}
	public String getQ_administrativeLevel() {
		return checkGet(q_administrativeLevel);
	}
	public void setQ_administrativeLevel(String qAdministrativeLevel) {
		q_administrativeLevel = checkSet(qAdministrativeLevel);
	}
	public String getQ_status() {
		return checkGet(q_status);
	}
	public void setQ_status(String q_status) {
		this.q_status = checkSet(q_status);
	}
	public String[] getReportField() {
		return reportField;
	}
	public void setReportField(String[] reportField) {
		this.reportField = reportField;
	}
	
	public String[] getQ_type() {
		return q_type;
	}
	public void setQ_type(String[] q_type) {
		this.q_type = q_type;
	}
	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}
	public void setQ_chProduct(String q_chProduct) {
		this.q_chProduct = checkSet(q_chProduct);
	}
	@Override
	public Object doQueryAll() throws Exception {		
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = getHql();	
		System.out.println("[TCBW]-[HFRIN0201Q]-[食品-doQueryAll] : " + hql);	
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by a.id ", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("FCS");
				java.util.Map<String, String> permitMap = TCBWCommon.getCommonCodeMap("HFRPKID");
				for(Object dtlObj : objList) {				
					Hfr0001Db dtl = (Hfr0001Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getHfrType());
					rowArray[2] = Common.get(dtl.getApplNo());
					rowArray[3] = Common.formatYYYMMDD(Common.get(dtl.getNotifierRevDate()),4);
					if(dtl.getHfr0002Dbs()!= null && dtl.getHfr0002Dbs().size() > 0){
						String chpro="";
						for(Object o : dtl.getHfr0002Dbs()){
							Hfr0002Db d = (Hfr0002Db)o;
							if(chpro.length() > 0) chpro+="、";
							chpro += Common.get(d.getChProduct());
							if(!"".equals(Common.get(d.getPermitKey())))
								rowArray[4] = permitMap.get(d.getPermitKey()) + "字第" + Common.get(d.getPermitNo()) + "號";
							else if(!"".equals(Common.get(d.getPermitNo())))
								rowArray[4] = Common.get(d.getPermitNo())  + "號";
							else 
								rowArray[4] = "";
							
							rowArray[5] = chpro;
							//break;
						}
					}
					rowArray[6] = statusMap.get(dtl.getStatus());
					arrList.add(rowArray);
				}
			}
			
		}
		return arrList;
	}
	
	public List<Object[]> getTableModel() throws Exception{
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		String hql = getHql();
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by a.id");
		if(getReportField() != null && getReportField().length >0){
			if(objList!=null && objList.size()>0){
				ArrayList<String> row = new ArrayList<String>();
				java.util.Map<String, String> title = getOutputTitle();
				java.util.Map<String, String> nfrMap = TCBWCommon.getCommonCodeMap("HFRNFS");
				java.util.Map<String, String> NotifierTypeMap = TCBWCommon.getCommonCodeMap("CONNFT1");
				java.util.Map<String, String> ResMap = TCBWCommon.getCommonCodeMap("HFRURCR");
				java.util.Map<String, String> buyMap = TCBWCommon.getCommonCodeMap("HFRBYS");
				java.util.Map<String, String> fucMap = TCBWCommon.getCommonCodeMap("HFRFUC");
				java.util.Map<String, String> nrsMap = TCBWCommon.getCommonCodeMap("HFRNRS");
				java.util.Map<String, String> fevMap = TCBWCommon.getCommonCodeMap("HFRFEV");
				java.util.Map<String, String> svrMap = TCBWCommon.getCommonCodeMap("HFRSVR");
				java.util.Map<String, String> drspMap = TCBWCommon.getCommonCodeMap("HFRDRSP");
				java.util.Map<String, String> permitMap = TCBWCommon.getCommonCodeMap("HFRPKID");
				java.util.Map<String, String> titletMap = TCBWCommon.getCommonCodeMap("TITLE");
				java.util.Map<String, String> cityMap = TCBWCommon.getCommonCodeMap("CTY");
				java.util.Map<String, String> zipMap = View.getZipCode();
				healthyCount = 0;
				unHealthyCount = 0; 
				generalCount = 0;
				//title
				for(String t : getReportField())
					row.add(BeanUtil.getPropertyByBeanUtils(title, t));
				arrList.add(row.toArray());
				//details
				for(Object dtlObj : objList){
					row.clear();
					Hfr0001Db dtl = (Hfr0001Db)dtlObj;
					//先將Hfr0002Db資料撈出來
					String tPermit="", tProduct="", tBuy="", tIngredient="", tUseDrugs="", tUseFoods="";
					if(dtl.getHfr0002Dbs() != null && dtl.getHfr0002Dbs().size() > 0){
						for(Object o : dtl.getHfr0002Dbs()){
							Hfr0002Db d = (Hfr0002Db)o;
							if("G".equals(Common.get(d.getType()))) {
								tPermit += permitMap.get(d.getPermitKey()) + "字 第" + Common.get(d.getPermitNo()) + "號,";
								healthyCount++;
								if(!"".equals(Common.get(d.getChProduct())))
									tProduct += Common.get(d.getChProduct()) + ",";							
								if(!"".equals(Common.get(d.getBuySource())))
									tBuy += buyMap.get(Common.get(d.getBuySource())) + ",";
								if(!"".equals(Common.get(d.getIngredient())))
									tIngredient += Common.get(d.getIngredient()) + ",";
							} else if("N".equals(Common.get(d.getType()))) {
								if(!"".equals(Common.get(d.getPermitNo()))){
									tPermit += Common.get(d.getPermitNo()) + "號,";
									unHealthyCount++;
								}else{
									generalCount++;
								}
								if(!"".equals(Common.get(d.getChProduct())))
									tProduct += Common.get(d.getChProduct()) + ",";							
								if(!"".equals(Common.get(d.getBuySource())))
									tBuy += buyMap.get(Common.get(d.getBuySource())) + ",";
								if(!"".equals(Common.get(d.getIngredient())))
									tIngredient += Common.get(d.getIngredient()) + ",";
							} else if("D".equals(Common.get(d.getType()))){
								//併用藥品
								if(!"".equals(Common.get(d.getChProduct())))
									tUseDrugs += Common.get(d.getChProduct()) + ",";		
							} else if("O".equals(Common.get(d.getType()))){
								//併用其他錠、膠劑型食品
								if(!"".equals(Common.get(d.getChProduct())))
									tUseFoods += Common.get(d.getChProduct()) + ",";
							}
						}
					}
					if(!"".equals(tPermit) && tPermit.length() > 0) tPermit = tPermit.substring(0, tPermit.length() -1); 	
					if(!"".equals(tProduct) && tProduct.length() > 0) tProduct = tProduct.substring(0, tProduct.length() -1); 	
					if(!"".equals(tBuy) && tBuy.length() > 0) tBuy = tBuy.substring(0, tBuy.length() -1); 	
					if(!"".equals(tIngredient) && tIngredient.length() > 0) tIngredient = tIngredient.substring(0, tIngredient.length() -1);
					if(!"".equals(tUseDrugs) && tUseDrugs.length() > 0) tUseDrugs = tUseDrugs.substring(0, tUseDrugs.length() -1); 	
					if(!"".equals(tUseFoods) && tUseFoods.length() > 0) tUseFoods = tUseFoods.substring(0, tUseFoods.length() -1); 	
					for(String t : getReportField()){
						String value = BeanUtil.getPropertyByBeanUtils(dtlObj, t);
						if("notifierRepDate".equals(t)){
							value = Common.formatYYYMMDD(Common.get(value),4);
						}else if("informedSources".equals(t)){
							value = nfrMap.get(value);
						}else if("notifierType".equals(t)){
							value = NotifierTypeMap.get(value);
						}else if("notifierTitle".equals(t)){
							value = titletMap.get(value);
						}else if("address".equals(t)){
							value = Common.get(cityMap.get(dtl.getNotifierArea())) + Common.get(zipMap.get(dtl.getNotifierZipCode()));
						}else if("eatersSex".equals(t)){
							value = "M".equals(value)?"男":"F".equals(value)?"女":"";
						}else if("eatersAgeLevel".equals(t)){
							if("1".equals(dtl.getHfrType()))
								value = getEatersAgeLevel(Common.get(dtl.getEatersAge()), dtl.getHfrType());
							else 
								value = getEatersAgeLevel(Common.get(dtl.getEatersBirthYear()), dtl.getHfrType());
						}else if("unHealthIsYes".equals(t)){
							value = "Y".equals(value)?"是":"N".equals(value)?"否":"";
						}else if("unReactionResults".equals(t)){
							value = ResMap.get(value);	
						}else if("unReactionDetails".equals(t)){
							if("1".equals(dtl.getHfrType())){
								value = "發生經過：" + Common.get(dtl.getOccurredAfter()) + ", " +
									"症狀：" + Common.get(dtl.getSymptom()) + ", " +
									"嚴重度：" + Common.get(dtl.getSeverity()) + ", " +
									"症狀維持時間：" + Common.get(dtl.getSymptomDuration());
							}else{
								if(dtl.getHfr0003Dbs()!=null && dtl.getHfr0003Dbs().size() > 0){
									String str = "";
									for(Object o : dtl.getHfr0003Dbs()){
										Hfr0003Db d = (Hfr0003Db)o;
										str += "發生日期：" + Common.formatYYYMMDD(d.getDiscriptDate(),4) + ", " + 
											"部位：" + Common.get(d.getPosition()) + ", " + 
											"症狀：" + Common.get(d.getSymptom()) + ", " + 
											"嚴重程度：" + Common.get(d.getSeverity()) + ", " + 
											"處置：" + Common.get(d.getDoResponse()) + "\n" + "1234";
									}
									value = str;
								}
							}
						}else if("permit".equals(t)){
							value = tPermit;
						}else if("Product".equals(t)){
							value = tProduct;
						}else if("buySource".equals(t)){
							value = tBuy;	
						}else if("ingredient".equals(t)){
							value = tIngredient;
						}else if("useDrugs".equals(t)){
							value = tUseDrugs;
						}else if("useFoods".equals(t)){
							value = tUseFoods;
						}else if("againEatingHealthFood".equals(t)){
							value = "Y".equals(value)?"是":"N".equals(value)?"否":"";
						}else if("stopEatingReaction".equals(t)){
							if("2".equals(dtl.getHfrType()))
								value = "Y".equals(value)?"是":"N".equals(value)?"否":"";
						}else if("againEatingReaction".equals(t)){
							value = "Y".equals(value)?"是":"N".equals(value)?"否":"";
						}else if("unExpectedClassify".equals(t)){
							value = fucMap.get(value);
						}else if("unExpectedReason".equals(t)){
							value = nrsMap.get(value);
						}else if("evidentiary".equals(t)){
							value = fevMap.get(value);
						}else if("recentlySeverity".equals(t)){
							value = svrMap.get(value);
						}else if("administrativeLevel".equals(t)){
							value = drspMap.get(value);
						}
						row.add(value);
					}
					arrList.add(row.toArray());
				}
			}
		}
		return arrList;
	}
	
	private String getHql() {
		String hql = "select distinct a from Hfr0001Db a, Hfr0002Db b where 1=1";
//		if(!"".equals(getQ_permitKey()) || !"".equals(getQ_permitNo()) || !"".equals(getQ_buySource())){
//			hql += " where a.id = b.hfr0001Db.id ";
//		}else{
//			hql += " where 1 = 1 ";
//		}
		hql += " and isnull(a.applNo,'') != ''";
		if(!"".equals(getQ_notifierRevDateS())) 
			hql += " and a.notifierRepDate >= " + Common.sqlChar(getQ_notifierRevDateS());
		if(!"".equals(getQ_notifierRevDateE())) 
			hql += " and a.notifierRepDate <= " + Common.sqlChar(getQ_notifierRevDateE());
		
		if(!"".equals(getQ_informedSources())) 
			hql += " and a.informedSources = " + Common.sqlChar(getQ_informedSources());
		
		if(!"".equals(getQ_notifierType())) 
			hql += " and a.notifierType = " + Common.sqlChar(getQ_notifierType());

		if(!"".equals(getQ_notifierTitle())) 
			hql += " and a.notifierTitle = " + Common.sqlChar(getQ_notifierTitle());
		
		if(!"".equals(getQ_eatersSex())) 
			hql += " and a.eatersSex = " + Common.sqlChar(getQ_eatersSex());
		
		if(!"".equals(getQ_eatersAgeS())) 
			hql += " and a.eatersAge >= " + Common.sqlChar(getQ_eatersAgeS());
		if(!"".equals(getQ_eatersAgeE())) 
			hql += " and a.eatersAge <= " + Common.sqlChar(getQ_eatersAgeE());
		
		if(!"".equals(getQ_permitKey())) 
			hql += " and b.permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo())) 
			hql += " and b.permitNo = " + Common.sqlChar(getQ_permitNo());
		
		if(!"".equals(getQ_buySource())) 
			hql += " and b.buySource = " + Common.sqlChar(getQ_buySource());
		
		if(!"".equals(getQ_unExpectedClassify())) 
			hql += " and a.unExpectedClassify = " + Common.sqlChar(getQ_unExpectedClassify());
		
		if(!"".equals(getQ_unExpectedReason())) 
			hql += " and a.unExpectedReason = " + Common.sqlChar(getQ_unExpectedReason());
		
		if(!"".equals(getQ_evidentiary())) 
			hql += " and a.evidentiary = " + Common.sqlChar(getQ_evidentiary());
		
		if(!"".equals(getQ_thiSeverity())) 
			hql += " and a.recentlySeverity = " + Common.sqlChar(getQ_thiSeverity());
		
		if(!"".equals(getQ_administrativeLevel())) 
			hql += " and a.administrativeLevel = " + Common.sqlChar(getQ_administrativeLevel());
		
		if(!"".equals(getQ_status()))
			hql += " and a.status = " + Common.sqlChar(getQ_status());
		
		//食品類別規則：核備食品沒有許可證字但有許可證號；一般食品沒有許可證字與許可證號
		if(!"".equals(getQ_type()) && null != getQ_type()) {
			boolean isG = false;
			boolean isN1 = false;
			boolean isN2 = false;
			//判斷查詢選取的食品類別
			for(String o: getQ_type()) {
				if("G".equals(o)) {
					isG = true;
				} else if("N1".equals(o)) {
					isN1 = true;
				} else if("N2".equals(o)) {
					isN2 = true;
				}
			}
			//全選
			if(isG && isN1 && isN2) {
				hql += " and b.type in ('G','N')";
				System.out.println("全選: " + hql);
			}
			//健康食品+核備食品
			if(isG && isN1 && (false == isN2)) {
				hql += " and b.type = 'G' or (b.type = 'N' and ISNULL(b.permitKey,'') = '' and ISNULL(b.permitNo,'') != '' and isnull(a.applNo,'') != '')";
				System.out.println("健康食品+核備食品: " + hql);
			}
			//健康食品+一般食品
			if(isG && isN2 && (false == isN1)) {
				hql += " and b.type = 'G' or (b.type = 'N' and ISNULL(b.permitKey,'') = '' and ISNULL(b.permitNo,'') = '' and isnull(a.applNo,'') != '')";
				System.out.println("健康食品+一般食品: " + hql);
			}
			//核備食品+一般食品
			if(isN1 && isN2 && (false == isG)) {
				hql += " and b.type = 'N'";
				System.out.println("核備食品+一般食品: " + hql);
			}
			//健康食品
			if(isG && (false == isN1) && (false == isN2)) {
				hql += " and b.type = 'G'";
				System.out.println("健康食品: " + hql);
			}
			//核備食品
			if(isN1 && (false == isG) && (false == isN2)) {
				hql += " and b.type = 'N' and ISNULL(b.permitKey,'') = '' and ISNULL(b.permitNo,'') != '' ";
				System.out.println("核備食品: " + hql);
			}
			//一般食品
			if(isN2 && (false == isG) && (false == isN1)) {
				hql += " and b.type = 'N' and ISNULL(b.permitKey,'') = '' and ISNULL(b.permitNo,'') = '' ";
				System.out.println("一般食品: " + hql);
			}
			
			hql += " and a.id = b.hfr0001Db.id";
		}
		
		if(!"".equals(getQ_chProduct()))
			hql += " and b.chProduct like " + Common.sqlChar("%"+getQ_chProduct()+"%");
		
		return hql;
	}
	
	public String getOutputTitleOption(String className, String checkBoxFieldName, String selectedCheckBox, int row){
    	StringBuilder sb = new StringBuilder();
    	java.util.Map<String, String> title = getOutputTitle();
    	java.util.Iterator it = title.entrySet().iterator();
    	int i = 1;
    	while (it.hasNext()){
    		java.util.Map.Entry dtl = (java.util.Map.Entry)it.next();
    		sb.append("<input class=\"").append(className).append("\" type=\"checkbox\" name=\"").append(checkBoxFieldName).append("\" value=\"").append(dtl.getKey()).append( "\"");
			if(selectedCheckBox != null && !"".equals(selectedCheckBox)){
				if(Common.get(dtl.getKey()).equals(selectedCheckBox)) sb.append(" checked");
			}
			sb.append(">").append(dtl.getValue()).append("&nbsp;");
			if(i%row == 0)
				sb.append("<br>");
			i++;
    		
    	}
        return sb.toString();    	
    }
	
	public java.util.Map<String,String> getOutputTitle(){
		java.util.Map<String, String> title = new java.util.TreeMap<String, String>();
		title.put("applNo", "案件編號");
		title.put("notifierRevDate", "通報日期");
		title.put("informedSources", "通報來源");
		title.put("notifierType", "通報者屬性");
		title.put("notifierTitle", "通報者職稱");
		title.put("address", "通報案件縣市別");
		title.put("eatersSex", "食用者性別");
		title.put("eatersAge", "食用者年齡");
		title.put("eatersAgeLevel", "食用者年齡級距");
		title.put("unHealthIsYes", "健康食品未達宣稱之保健功效");
		title.put("unReactionResults", "非預期反應結果");
		title.put("unReactionDetails", "非預期反應描述");
		title.put("permit", "食品字號");
		title.put("Product", "食品品名");
		title.put("buySource", "購買來源");
		title.put("ingredient", "食品成分");
		title.put("useDrugs", "併用藥品品名");
		title.put("useFoods", "併用食品品名");
		title.put("againEatingHealthFood", "曾食用同類健康食品之經驗");
		title.put("stopEatingReaction", "停止食用健康食品後反應是否減輕");
		title.put("againEatingReaction", "再次食用是否出現同樣反應");
		title.put("unExpectedClassify", "非預期反應分類");
		title.put("unExpectedReason", "非預期反應原因");
		title.put("evidentiary", "證據性");
		title.put("recentlySeverity", "嚴重程度");
		title.put("administrativeLevel", "行政處置層級");
		return title;
	}
	
	public java.util.Map<String,String> getNotifierTypeMap(){
		java.util.Map<String, String> Map = new java.util.TreeMap<String, String>();
		List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find("select shortCode, department from CommonDepartment order by shortCode");
		
    	if (list!=null && list.size()>0) {
    		for (int i=0; i<list.size(); i++) {
    			Object[] obj = (Object[])list.get(i);
                String id = Common.get(obj[0]);
                String name = Common.get(obj[1]);
                Map.put(id, name);
    		}
    	}
		return Map;
	}
	
	public String getEatersAgeLevel(String age, String hfrType){
		java.util.regex.Pattern p = java.util.regex.Pattern.compile("^[-\\+]?[\\d]*$");
		if("2".equals(hfrType) && p.matcher(age).matches() && !"".equals(age)){
			int year = Integer.valueOf(Datetime.getYYY()) - Integer.valueOf(age);
			age = String.valueOf(year);
		}
		if(p.matcher(age).matches() && !"".equals(age)){
			if(Integer.valueOf(age) <= 10)
				age = "0~10";
			else if(Integer.valueOf(age) <= 20)
				age = "11~20";
			else if(Integer.valueOf(age) <= 30)
				age = "21~30";
			else if(Integer.valueOf(age) <= 40)
				age = "31~40";
			else if(Integer.valueOf(age) <= 50)
				age = "41~50";
			else if(Integer.valueOf(age) <= 60)
				age = "51~60";
			else if(Integer.valueOf(age) <= 70)
				age = "61~70";
			else if(Integer.valueOf(age) <= 80)
				age = "71~80";
			else if(Integer.valueOf(age) <= 90)
				age = "81~90";
			else if(Integer.valueOf(age) <= 100)
				age = "91~100";
			else
				age = "101以上";
		}else{
			age = "";
		}
		return age;
    }
	
	public List<Object[]> getHealthCout(){
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		arrList.add(new String[]{"健康食品",String.valueOf(healthyCount)});
		arrList.add(new String[]{"核備食品",String.valueOf(unHealthyCount)});
		arrList.add(new String[]{"一般食品",String.valueOf(generalCount)});
		return arrList;
    }
	
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}
	
	@Override
	public void doCreate() throws Exception {}

	@Override
	public void doUpdate() throws Exception {}

	@Override
	public void doDelete() throws Exception {}
	
}
	