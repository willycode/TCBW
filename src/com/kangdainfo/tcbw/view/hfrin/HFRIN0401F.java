package com.kangdainfo.tcbw.view.hfrin;

import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4002Db;
import com.kangdainfo.tcbw.model.bo.Hfr4003Db;
import com.kangdainfo.tcbw.model.bo.Hfr4004Db;

public class HFRIN0401F extends SuperBean {
	
	private String q_applNo;
	private String q_notifierRepDateS;
	private String q_notifierRepDateE;
	private String q_permitKey;
	private String q_permitNo;
	private String q_product;
	private String q_status;
	
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
	public String getQ_product() {		return checkGet(q_product);	}
	public void setQ_product(String q_product) {		this.q_product = checkSet(q_product);	}
	public String getQ_status() {		return checkGet(q_status);	}
	public void setQ_status(String q_status) {		this.q_status = checkSet(q_status);	}
	
	
	// FOR 案件本身區隔資料
	private String id;		
	private String hfr40001Id;
	private String hfrType;
	private String status;
	private String applNo;
	
	public String getId() {		return checkGet(id);	}
	public void setId(String id) {		this.id = checkSet(id);	}
	public String getHfrType() {		return checkGet(hfrType);	}
	public void setHfrType(String hfrType) {		this.hfrType = checkSet(hfrType);	}
	public String getStatus() {		return checkGet(status);	}
	public void setStatus(String status) {		this.status = checkSet(status);	}
	public String getApplNo() {		return checkGet(applNo);	}
	public void setApplNo(String applNo) {		this.applNo = checkSet(applNo);	}
	public String getHfr40001Id() {		return checkGet(hfr40001Id);	}
	public void setHfr40001Id(String hfr40001Id) {		this.hfr40001Id = checkSet(hfr40001Id);	}
	
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
	
	public final String[] arrDrugFieldName = {"drChProduct", "drEnProduct", "drContent", "drMedModel", "drDoseNum", "drDoseDay", "drFrequency", "drFrequencyUnit", "drEdibleDateS", "drEdibleDateE", "drBrand","drpermitNo" };
	public final String[] arrODrugFieldName = {"odrChProduct", "odrEnProduct", "odrContent", "odrMedModel", "odrDoseNum", "odrDoseDay", "odrFrequency", "odrFrequencyUnit", "odrEdibleDateS", "odrEdibleDateE", "odrBrand","odrpermitNo" };
	public final String[] discriptFieldName = {"discriptDate", "position", "symptom", "severity", "doResponse"};
	public final String[] doctorFieldName = {"doctorDate", "bloodIndex", "liverIndex"};

	private String doType;
	private String isNeedBackQuery;
	private javax.servlet.ServletRequest httpRequest;
		
	public String getDoType() {		return checkGet(doType);	}
	public void setDoType(String doType) {		this.doType = checkSet(doType);	}
	public String getIsNeedBackQuery() {		return checkGet(isNeedBackQuery);	}
	public void setIsNeedBackQuery(String isNeedBackQuery) {		this.isNeedBackQuery = checkSet(isNeedBackQuery);	}

	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	
	// FOR 簡表、一般表使用
	public String genGHfr4002DbItemSet(java.util.List<Hfr4002Db> dtlList) throws Exception{
	    if(dtlList!=null && dtlList.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for(Hfr4002Db dtl : dtlList){
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
	
	public String genNHfr4002DbItemSet(java.util.List<Hfr4002Db> dtlList) throws Exception{
	    if(dtlList!=null && dtlList.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for(Hfr4002Db dtl : dtlList){
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
	
	// FOR 一般表使用
	public String genHfr4003DbItemSet(java.util.Set dtlSet) throws Exception{
	    if(dtlSet!=null && dtlSet.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);
	    	for(Object dtlObj : dtlSet){
	    		Hfr4003Db dtl = (Hfr4003Db)dtlObj;
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
	
	public String genHfr4004DbItemSet(java.util.Set dtlSet) throws Exception{
	    if(dtlSet!=null && dtlSet.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);
	    	for(Object dtlObj : dtlSet){
	    		Hfr4004Db dtl = (Hfr4004Db)dtlObj;
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
	
	public String genDrHfr4002DbItemSet(java.util.List<Hfr4002Db> dtlList, String type) throws Exception{
	    if(dtlList!=null && dtlList.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for(Hfr4002Db dtl : dtlList){
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
		String hql = " select distinct a from Hfr4001Db a ";
		if(!"".equals(getQ_permitKey()) || !"".equals(getQ_permitNo()) || !"".equals(getQ_product())){
			hql += ", Hfr4002Db b where a.id = b.hfr4001Db.id ";
		}else{
			hql += " where 1 = 1 ";
		}
		hql += " and a.creator = " + Common.sqlChar(getUserID()); 
		
		if(!"".equals(getQ_applNo()))
			hql += " and a.applNo = " + Common.sqlChar(getQ_applNo());
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
		if(!"".equals(getQ_status()))
			hql += " and status = " + Common.sqlChar(getQ_status());
		System.out.println("[TCBW]-[HFRIN0401F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by a.id DESC", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0){
				java.util.Map<String, String> FCSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("FCS", null);
				java.util.Map<String, String> HFRPKIDMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("HFRPKID", null);
				
				for(Object dtlObj : objList){
					Hfr4001Db dtl = (Hfr4001Db)dtlObj;
					
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getOccurDate());
					
					// 取第一項顯示即可
					rowArray[3] = "";
					rowArray[4] = "";
					if(dtl.getHfr4002Dbs()!=null && dtl.getHfr4002Dbs().size()>0){
						boolean flag = true;
						for(Object dObj : dtl.getHfr4002Dbs()){
							if(flag){
								Hfr4002Db d = (Hfr4002Db)dObj;
								rowArray[3] = ( Common.get(HFRPKIDMap.get(Common.get(d.getPermitKey()))).equals("")?Common.get(d.getPermitKey()):Common.get(HFRPKIDMap.get(Common.get(d.getPermitKey()))) ) + Common.get(d.getPermitNo());
								rowArray[4] = Common.get(d.getChProduct());
								flag = false;
							}else{
								break;
							}
						}
					}
					rowArray[5] = Common.get(FCSMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(FCSMap.get(Common.get(dtl.getStatus())));
					rowArray[6] = Common.get(dtl.getHfrType());
					arrList.add(rowArray);
				}
				FCSMap.clear();
				HFRPKIDMap.clear();
				objList.clear();
			}
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
