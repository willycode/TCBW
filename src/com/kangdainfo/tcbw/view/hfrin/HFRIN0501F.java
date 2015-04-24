package com.kangdainfo.tcbw.view.hfrin;

import java.util.Map;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;
import com.kangdainfo.tcbw.model.bo.Hfr0003Db;
import com.kangdainfo.tcbw.model.bo.Hfr0004Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFRIN0501F extends SuperBean {
	
	// 歷史通報Query
	private String q_notifierRepDateSHis;
	private String q_notifierRepDateEHis;
	private String q_unReactionResultsHis;
	private String q_productHis;

	public String getQ_notifierRepDateSHis() {return checkGet(q_notifierRepDateSHis);}
	public void setQ_notifierRepDateSHis(String qNotifierRepDateSHis) {q_notifierRepDateSHis = checkSet(qNotifierRepDateSHis);}
	public String getQ_notifierRepDateEHis() {return checkGet(q_notifierRepDateEHis);}
	public void setQ_notifierRepDateEHis(String qNotifierRepDateEHis) {q_notifierRepDateEHis = checkSet(qNotifierRepDateEHis);}
	public String getQ_unReactionResultsHis() {return checkGet(q_unReactionResultsHis);}
	public void setQ_unReactionResultsHis(String qUnReactionResultsHis) {q_unReactionResultsHis = checkSet(qUnReactionResultsHis);}
	public String getQ_productHis() {return checkGet(q_productHis);}
	public void setQ_productHis(String qProductHis) {q_productHis = checkSet(qProductHis);}
	
	public String getFUROption(String className, String checkBoxFieldName, String selectedCheckBox){
    	StringBuilder sb = new StringBuilder();
    	java.util.Map<String, String> list = new java.util.TreeMap<String, String>(); 
    	ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("HFRURCR", list);
    	if(list!=null && list.size()>0){
    		for(Map.Entry<String, String> dtl : list.entrySet()){
    			sb.append("<input class=\"" ).append( className ).append( "\" type=\"radio\" name=\"" ).append( checkBoxFieldName ).append( "\" value=\"" ).append( dtl.getKey() ).append( "\"");
    			if(selectedCheckBox != null && !"".equals(selectedCheckBox)){
        				if(Common.get(dtl.getKey()).equals(selectedCheckBox)) sb.append(" checked");
    			}
    			sb.append(">").append(dtl.getValue());
    			sb.append("<br>");
    		}
    	}
        return sb.toString();    	
    }
	
	public Object doQueryHistory() throws Exception {
		String hql = getHqlHistory();
		
	//	System.out.println("[TCBW]-[HFRIN0102Q]-[食品-doQueryHistory] : " + hql);		
	//	System.out.println("報表類別： " + getHfrType());
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id DESC", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> ResMap = TCBWCommon.getCommonCodeMap("HFRURCR");
				for(Object dtlObj : objList) {				
					Hfr0002Db dtl = (Hfr0002Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getHfr0001Db().getId());
					rowArray[1] = Common.formatYYYMMDD(Common.get(dtl.getHfr0001Db().getNotifierRevDate()),4);
					rowArray[2] = Common.get(dtl.getHfr0001Db().getApplNo());
					rowArray[3] = Common.get(dtl.getChProduct());
					rowArray[4] = "Y".equals(dtl.getHfr0001Db().getUnHealthIsYes())?"是":"N".equals(dtl.getHfr0001Db().getUnHealthIsYes())?"否":"";					
					rowArray[5] = ResMap.get(dtl.getHfr0001Db().getUnReactionResults());
					rowArray[6] = "附件";
					arrList.add(rowArray);
				}
				objList.clear();			
			}	
		}
		return arrList;
	}
	
	public String getHqlHistory(){
		String permit1 = "", permit2 = "";
		String hql = "from Hfr0002Db where hfr0001Db.id = " + Common.getLong(getId());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql);
		if(objList == null){
			return null;
		}
		
		String notifierRepDate = "";
		for(Object dtlObj : objList){
			Hfr0002Db dtl = (Hfr0002Db)dtlObj;
			if(!"".equals(Common.get(dtl.getPermitKey())))
				permit1 += Common.sqlChar(dtl.getPermitKey()+dtl.getPermitNo()) + ",";
			else
				permit2 += Common.sqlChar(dtl.getPermitNo()) + ",";
			
			if("".equals(notifierRepDate)){
				if(dtl.getHfr0001Db()!=null){
					notifierRepDate = Common.get(dtl.getHfr0001Db());
				}
			}
		}
		objList.clear();
		
		hql = "from Hfr0002Db where hfr0001Db.applNo is not null and hfr0001Db.notifierRevDate <= " + Common.sqlChar(notifierRepDate);
		if(!"".equals(permit1) && !"".equals(permit2)){
			hql += " and ((permitKey is not null and permitKey||permitNo in("+permit1.substring(0, permit1.length()-1)+"))";
			hql += " or (permitKey is null and permitNo in("+permit2.substring(0, permit2.length()-1)+")))";
		}else if(!"".equals(permit1)){
			hql += " and (permitKey is not null and permitKey||permitNo in("+permit1.substring(0, permit1.length()-1)+"))";
		}else if(!"".equals(permit2)){
			hql += " and (permitKey is null and permitNo in("+permit2.substring(0, permit2.length()-1)+"))";
		}
		
		if(!"".equals(getQ_notifierRepDateSHis()))
			hql += " and hfr0001Db.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateSHis());
		if(!"".equals(getQ_notifierRepDateEHis()))
			hql += " and hfr0001Db.notifierRevDate <= " + Common.sqlChar(getQ_notifierRepDateEHis());
		if(!"".equals(getQ_unReactionResultsHis()))
			hql += " and hfr0001Db.unReactionResults = " + Common.sqlChar(getQ_unReactionResultsHis());
		if(!"".equals(getQ_productHis()))
			hql += " and (chProduct like " + Common.sqlChar("%" + getQ_productHis() + "%") + " or enProduct like " + Common.sqlChar("%" + getQ_productHis() + "%") + ")";
		
		return hql;
	}
	
	private String q_notifierRepDateS;
	private String q_notifierRepDateE;
	private String q_permitKey;
	private String q_permitNo;
	private String q_product;
	private String q_assignMan;
	private String q_status;
	
	public String getQ_notifierRepDateS() {		return checkGet(q_notifierRepDateS);	}
	public void setQ_notifierRepDateS(String q_notifierRepDateS) {		this.q_notifierRepDateS = checkSet(q_notifierRepDateS);	}
	public String getQ_notifierRepDateE() {		return checkGet(q_notifierRepDateE);	}
	public void setQ_notifierRepDateE(String q_notifierRepDateE) {		this.q_notifierRepDateE = checkSet(q_notifierRepDateE);	}
	public String getQ_permitKey() {		return checkGet(q_permitKey);	}
	public void setQ_permitKey(String q_permitKey) {		this.q_permitKey = checkSet(q_permitKey);	}
	public String getQ_permitNo() {		return checkGet(q_permitNo);	}
	public void setQ_permitNo(String q_permitNo) {		this.q_permitNo = checkSet(q_permitNo);	}
	public String getQ_product() {		return checkGet(q_product);	}
	public void setQ_product(String q_product) {		this.q_product = checkSet(q_product);	}
	public String getQ_assignMan() {		return checkGet(q_assignMan);	}
	public void setQ_assignMan(String q_assignMan) {		this.q_assignMan = checkSet(q_assignMan);	}
	public String getQ_status() {		return checkGet(q_status);	}
	public void setQ_status(String q_status) {		this.q_status = checkSet(q_status);	}
	
	private String id;		
	private String hfr40001Id;
	private String hfrType;
	private String status;
	private String applNo;
	private String assignMan;
	
	public String getId() {		return checkGet(id);	}
	public void setId(String id) {		this.id = checkSet(id);	}
	public String getHfr40001Id() {		return checkGet(hfr40001Id);	}
	public void setHfr40001Id(String hfr40001Id) {		this.hfr40001Id = checkSet(hfr40001Id);	}
	public String getHfrType() {		return checkGet(hfrType);	}
	public void setHfrType(String hfrType) {		this.hfrType = checkSet(hfrType);	}
	public String getStatus() {		return checkGet(status);	}
	public void setStatus(String status) {		this.status = checkSet(status);	}
	public String getApplNo() {		return checkGet(applNo);	}
	public void setApplNo(String applNo) {		this.applNo = checkSet(applNo);	}
	public String getAssignMan() {		return checkGet(assignMan);	}	
	public void setAssignMan(String assignMan) {		this.assignMan = assignMan;	}

	public String getCommonUserOption(String selectedOne){
		return View.getOption(" select userId, userName from CommonUser where inORout = 'in' order by userId", selectedOne, false, 1);
	}
	
	// FOR 簡表、一般表使用(健康食品與核備食品、案件本身相關附件)
	private String[] foodFileRow;
	private String[] GFoodRow;
	private String[] NFoodRow;
	
	public String[] getFoodFileRow() {		return checkGet(foodFileRow);	}
	public void setFoodFileRow(String[] foodFileRow) {		this.foodFileRow = checkSet(foodFileRow);	}
	public String[] getGFoodRow() {		return checkGet(GFoodRow);	}
	public void setGFoodRow(String[] gFoodRow) {		GFoodRow = checkSet(gFoodRow);	}
	public String[] getNFoodRow() {		return checkGet(NFoodRow);	}
	public void setNFoodRow(String[] nFoodRow) {		NFoodRow = checkSet(nFoodRow);	}
	
	private String FoodFileSBuilder;
	private String GFoodSBuilder;
	private String NFoodSBuilder;
	
	public String getFoodFileSBuilder() {		return get(FoodFileSBuilder);	}
	public void setFoodFileSBuilder(String foodFileSBuilder) {		FoodFileSBuilder = checkSet(foodFileSBuilder);	}
	public String getGFoodSBuilder() {		return get(GFoodSBuilder);	}
	public void setGFoodSBuilder(String gFoodSBuilder) {		GFoodSBuilder = checkSet(gFoodSBuilder);	}
	public String getNFoodSBuilder() {		return get(NFoodSBuilder);	}
	public void setNFoodSBuilder(String nFoodSBuilder) {		NFoodSBuilder = checkSet(nFoodSBuilder);	}

	public final String[] arrHFileFieldName = {"foodFileName", "foodFileNameRoute", "foodFileExplan"};
	public final String[] arrGFoodFieldName = {"gPermitKey", "gPermitNo", "gChProduct", "gEnProduct", "gBuySource", "gIngredient", "gDoseDay", "gDoseNum", "gFrequency", "gFrequencyUnit", "gEdibleDateS", "gEdibleDateE"};
	public final String[] arrNFoodFieldName = {"nPermitKey", "nPermitNo", "nChProduct", "nEnProduct", "nBuySource", "nIngredient", "nDoseDay", "nDoseNum", "nFrequency", "nFrequencyUnit", "nEdibleDateS", "nEdibleDateE"};
	
	// FOR 一般表使用
	private String[] discriptRow;
	private String[] doctorRow;
	private String[] drRow;
	private String[] odrRow;
	
	public String[] getDiscriptRow() {		return checkGet(discriptRow);	}
	public void setDiscriptRow(String[] discriptRow) {		this.discriptRow = checkSet(discriptRow);	}
	public String[] getDoctorRow() {		return checkGet(doctorRow);	}
	public void setDoctorRow(String[] doctorRow) {		this.doctorRow = checkSet(doctorRow);	}
	public String[] getDrRow() {		return checkGet(drRow);	}
	public void setDrRow(String[] drRow) {		this.drRow = checkSet(drRow);	}
	public String[] getOdrRow() {		return checkGet(odrRow);	}
	public void setOdrRow(String[] odrRow) {		this.odrRow = checkSet(odrRow);	}
	
	private String discriptSBuilder;
	private String doctorSBuilder;
	private String drugSBuilder;
	private String ODrugSBuilder;
	
	public String getDiscriptSBuilder() {		return get(discriptSBuilder);	}
	public void setDiscriptSBuilder(String discriptSBuilder) {		this.discriptSBuilder = checkSet(discriptSBuilder);	}
	public String getDoctorSBuilder() {		return get(doctorSBuilder);	}	
	public void setDoctorSBuilder(String doctorSBuilder) {		this.doctorSBuilder = checkSet(doctorSBuilder);	}
	public String getDrugSBuilder() {		return get(drugSBuilder);	}
	public void setDrugSBuilder(String drugSBuilder) {		this.drugSBuilder = checkSet(drugSBuilder);	}
	public String getODrugSBuilder() {		return get(ODrugSBuilder);	}
	public void setODrugSBuilder(String oDrugSBuilder) {		ODrugSBuilder = checkSet(oDrugSBuilder);	}
	
	public final String[] arrDrugFieldName = {"drChProduct", "drEnProduct", "drContent", "drMedModel", "drDoseNum", "drDoseDay", "drFrequency", "drFrequencyUnit", "drEdibleDateS", "drEdibleDateE", "drBrand","drPermitNo" };
	public final String[] arrODrugFieldName = {"odrChProduct", "odrEnProduct", "odrContent", "odrMedModel", "odrDoseNum", "odrDoseDay", "odrFrequency", "odrFrequencyUnit", "odrEdibleDateS", "odrEdibleDateE", "odrBrand","odrPermitNo" };
	public final String[] discriptFieldName = {"discriptDate", "position", "symptom", "severity", "doResponse"};
	public final String[] doctorFieldName = {"doctorDate", "bloodIndex", "liverIndex"};

	private String isNeedBackQuery;
	private javax.servlet.ServletRequest httpRequest;
		
	public String getIsNeedBackQuery() {		return checkGet(isNeedBackQuery);	}
	public void setIsNeedBackQuery(String isNeedBackQuery) {		this.isNeedBackQuery = checkSet(isNeedBackQuery);	}
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	public String genFileRowItemSet(java.util.List<Con0001Db> dtlList, String fileType) throws Exception{
		if(dtlList!=null && dtlList.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for(Con0001Db dtl : dtlList){
	    		sb.append("addFile").append(fileType).append("('tabFile" + fileType + "'");
	    		sb.append(",").append(Common.sqlChar(dtl.getFileName()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFileRoute()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFileExplan()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setFoodFileSBuilder(sb.toString());
		}
		return "";
	}
	
	public String genGHfr0002DbItemSet(java.util.List<Hfr0002Db> dtlList) throws Exception{
	    if(dtlList!=null && dtlList.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for(Hfr0002Db dtl : dtlList){
	    		sb.append("addGFoodRow('gFood'");
	    		sb.append(",").append(Common.sqlChar(dtl.getPermitKey()));
	    		sb.append(",").append(Common.sqlChar(dtl.getPermitNo()));
	    		sb.append(",").append(Common.sqlChar(dtl.getChProduct()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEnProduct()));
	    		sb.append(",").append(Common.sqlChar(dtl.getBuySource()));
	    		sb.append(",").append(Common.sqlChar(dtl.getIngredient()));
	    		sb.append(",").append(Common.sqlChar(dtl.getDoseDay()));
	    		sb.append(",").append(Common.sqlChar(dtl.getDoseNum()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFrequency()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFrequencyUnit()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEdibleDateS()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEdibleDateE()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setGFoodSBuilder(sb.toString());
	    }
	    return "";
	}
	
	public String genNHfr0002DbItemSet(java.util.List<Hfr0002Db> dtlList) throws Exception{
	    if(dtlList!=null && dtlList.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for(Hfr0002Db dtl : dtlList){
	    		sb.append("addNFoodRow('nFood'");
	    		sb.append(",").append(Common.sqlChar(dtl.getPermitKey()));
	    		sb.append(",").append(Common.sqlChar(dtl.getPermitNo()));
	    		sb.append(",").append(Common.sqlChar(dtl.getChProduct()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEnProduct()));
	    		sb.append(",").append(Common.sqlChar(dtl.getBuySource()));
	    		sb.append(",").append(Common.sqlChar(dtl.getIngredient()));
	    		sb.append(",").append(Common.sqlChar(dtl.getDoseDay()));
	    		sb.append(",").append(Common.sqlChar(dtl.getDoseNum()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFrequency()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFrequencyUnit()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEdibleDateS()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEdibleDateE()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setNFoodSBuilder(sb.toString());
	    }
	    return "";
	}
	
	public String genHfr0003DbItemSet(java.util.Set dtlSet) throws Exception{
	    if(dtlSet!=null && dtlSet.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);
	    	for(Object dtlObj : dtlSet){
	    		Hfr0003Db dtl = (Hfr0003Db)dtlObj;
	    		sb.append("addDiscript('tabDiscript'");
	    		sb.append(",").append(Common.sqlChar(dtl.getDiscriptDate()));
	    		sb.append(",").append(Common.sqlChar(dtl.getPosition()));
	    		sb.append(",").append(Common.sqlChar(dtl.getSymptom()));
	    		sb.append(",").append(Common.sqlChar(dtl.getSeverity()));
	    		sb.append(",").append(Common.sqlChar(dtl.getDoResponse()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setDiscriptSBuilder(sb.toString());	
	    }
	    return "";
	}
	
	public String genHfr0004DbItemSet(java.util.Set dtlSet) throws Exception{
	    if(dtlSet!=null && dtlSet.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);
	    	for(Object dtlObj : dtlSet){
	    		Hfr0004Db dtl = (Hfr0004Db)dtlObj;
	    		sb.append("addDoctor('tabDoctor'");
	    		sb.append(",").append(Common.sqlChar(dtl.getDoctorDate()));
	    		sb.append(",").append(Common.sqlChar(dtl.getBloodIndex()));
	    		sb.append(",").append(Common.sqlChar(dtl.getLiverIndex()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setDoctorSBuilder(sb.toString());
	    }
	    return "";
	}
	
	public String genDrHfr0002DbItemSet(java.util.List<Hfr0002Db> dtlList, String type) throws Exception{
	    if(dtlList!=null && dtlList.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for(Hfr0002Db dtl : dtlList){
	    		sb.append("addDrugRow").append(type).append("('tabDrug" + type + "'");
	    		sb.append(",").append(Common.sqlChar(dtl.getChProduct()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEnProduct()));
	    		sb.append(",").append(Common.sqlChar(dtl.getContent()));
	    		sb.append(",").append(Common.sqlChar(dtl.getMedModel()));
	    		sb.append(",").append(Common.sqlChar(dtl.getDoseNum()));
	    		sb.append(",").append(Common.sqlChar(dtl.getDoseDay()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFrequency()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFrequencyUnit()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEdibleDateS()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEdibleDateE()));
	    		sb.append(",").append(Common.sqlChar(dtl.getBrand()));
	    		sb.append(",").append(Common.sqlChar(dtl.getPermitNo()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	if("dr".equals(type)){
	    		this.setDrugSBuilder(sb.toString());
	    	}else if("odr".equals(type)){
	    		this.setODrugSBuilder(sb.toString());
	    	}
	    }
	    return "";
	}
	
	// 非預期反應結果
	public String getHFRURCRRadioOption(String className, String checkBoxFieldName, String selectedOne, String deathDate, String deathReason, String eReason, String oReason){
    	StringBuilder sb = new StringBuilder();
    	java.util.Map<String, String> list = new java.util.TreeMap<String, String>(); 
    	ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("HFRURCR", list);
    	if(list!=null && list.size()>0){
    		for(Map.Entry<String, String> dtl : list.entrySet()){
    			sb.append("<input class=\"" ).append( className ).append( "\" type=\"radio\" name=\"" ).append( checkBoxFieldName ).append( "\" value=\"" ).append( dtl.getKey() ).append( "\"");
    			if(Common.get(dtl.getKey()).equals(Common.get(selectedOne))){
    				sb.append(" checked");
    			}
    			sb.append(">").append(dtl.getValue());
    			
    			if("1".equals(Common.get(dtl.getKey()))){
					sb.append("，").append(View.getPopCalendar(className, "deathDate", deathDate))
					  .append("，死亡原因 : ").append("<input class=\"").append(className).append("\" type=\"text\" name=\"deathResult\" size=25 maxlength=50 value=\"").append(deathReason).append("\">")
					  .append("，或危急生命 : ").append("<input class=\"").append(className).append("\" type=\"text\" name=\"endangerLife\" size=25 maxlength=50 value=\"").append(eReason).append("\">");
				}else if("7".equals(Common.get(dtl.getKey()))){
					sb.append(" ").append("<input class=\"").append(className).append("\" type=\"text\" name=\"otherNonResponse\" size=30 maxlength=50 value=\"").append(oReason).append("\">");
				}
    			sb.append("<br>");
    		}
    	}
        return sb.toString();    	
    }
	
	public String getFORCheckBoxOption(String className, String checkBoxFieldName, String[] selectedCheckBox, String allergicDescription, String othersDescribeHistory){
		StringBuilder sb = new StringBuilder();
    	java.util.Map<String, String> list = new java.util.TreeMap<String, String>(); 
    	ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("HFRFOR", list);
    	if(list!=null && list.size()>0){
    		for(Map.Entry<String, String> dtl : list.entrySet()){
    			sb.append("<input class=\"" ).append( className ).append( "\" type=\"checkbox\" name=\"" ).append( checkBoxFieldName ).append( "\" value=\"" ).append( dtl.getKey() ).append( "\"");
    			if(selectedCheckBox!=null && selectedCheckBox.length>0){
    				for(int j=0; j<selectedCheckBox.length; j++) {
        				if(Common.get(dtl.getKey()).equals(selectedCheckBox[j])) sb.append(" checked");
        			}
    			}
    			sb.append(">").append(dtl.getValue());
    			
    			if("1".equals(Common.get(dtl.getKey()))){
    				sb.append("，").append("<input class=\"").append(className).append("\" type=\"text\" name=\"allergicDescription\" size=40 maxlength=50 value=\"").append(allergicDescription).append("\">");
				}else if("6".equals(Common.get(dtl.getKey()))){
					sb.append(" ").append("<input class=\"").append(className).append("\" type=\"text\" name=\"othersDescribeHistory\" size=40 maxlength=50 value=\"").append(othersDescribeHistory).append("\">");
				}
    			sb.append("<br>");
    		}
    	}
    	return sb.toString();
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " select distinct a from Hfr0001Db a left join a.hfr0002Dbs b where 1=1 ";
		
		if(!"".equals(getQ_status())){
			hql += " and a.status = " + Common.sqlChar(getQ_status());
		}else{
			hql += " and a.status in ('10', '03') ";
		}
		
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and a.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and a.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(getQ_permitKey()))
			hql += " and b.permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and b.permitNo = " + Common.sqlChar(getQ_permitNo());
		if(!"".equals(getQ_product()))
			hql += " and b.chProduct like " + Common.sqlChar("%" + getQ_product() + "%");
		if(!"".equals(getQ_assignMan()))
			hql += " and a.assignMan = " + Common.sqlChar(getQ_assignMan());
		System.out.println("[TCBW]-[HFRIN0501F]-[QUERYALL] : " + hql + " order by a.id ");
		
		// 103.01.27 - 不分頁修正
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(objList!=null && objList.size()>0){
			java.util.Map<String, String> FCSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("FCS", null);
			java.util.Map<String, String> HFRNFSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("HFRNFS", null);
			java.util.Map<String, String> HFRPKIDMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("HFRPKID", null);
			java.util.Map<String, String> hfrUserMap = new java.util.HashMap<String, String>();
			java.util.List userList = ServiceGetter.getInstance().getTcbwService().load("select userId, userName from CommonUser where inORout = 'in'");
			if(userList!=null && userList.size()>0){
				for(Object dtlObj : userList){
					Object[] dtl = (Object[])dtlObj;
					hfrUserMap.put(Common.get(dtl[0]), Common.get(dtl[1]));
				}
				userList.clear();
			}
			for(Object dtlObj : objList){
				Hfr0001Db dtl = (Hfr0001Db)dtlObj;
				String[] rowArray = new String[9];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getNotifierRepDate());
				rowArray[2] = Common.get(HFRNFSMap.get(Common.get(dtl.getInformedSources()))).equals("")?Common.get(dtl.getInformedSources()):HFRNFSMap.get(Common.get(dtl.getInformedSources()));
				
				rowArray[3] = "";
				rowArray[4] = "";
				rowArray[5] = "";
				if(dtl.getHfr0002Dbs()!=null && dtl.getHfr0002Dbs().size()>0){
					boolean flag = true;
					for(Object dObj : dtl.getHfr0002Dbs()){
						if(flag){
							Hfr0002Db d = (Hfr0002Db)dObj;
							rowArray[3] = ( Common.get(HFRPKIDMap.get(Common.get(d.getPermitKey()))).equals("")?Common.get(d.getPermitKey()):Common.get(HFRPKIDMap.get(Common.get(d.getPermitKey()))) ) + Common.get(d.getPermitNo());
							rowArray[4] = Common.get(d.getChProduct());
							rowArray[5] = Common.get(d.getEnProduct());
							flag = false;
						}else{
							break;
						}
					}
				}
				rowArray[6] = Common.get(FCSMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(FCSMap.get(Common.get(dtl.getStatus())));
				rowArray[7] = Common.get(hfrUserMap.get(Common.get(dtl.getAssignMan()))).equals("")?Common.get(dtl.getAssignMan()):Common.get(hfrUserMap.get(Common.get(dtl.getAssignMan())));
				rowArray[8] = Common.get(dtl.getHfrType());
				arrList.add(rowArray);
			}
			
			objList.clear();
			FCSMap.clear();
			HFRNFSMap.clear();
			HFRPKIDMap.clear();
			hfrUserMap.clear();
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
