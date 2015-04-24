package com.kangdainfo.tcbw.view.medin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.BeanUtil;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0007Db;
import com.kangdainfo.tcbw.model.bo.Med0008Db;
import com.kangdainfo.tcbw.model.bo.Med0009Db;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN0301Q extends SuperBean
{
	private String caseType;
	private String id;//序號	NUMERIC(19,0)
	private String doType;
	private String isInOrOutPerson;
    private String inOrOut;//分內外網
    private String talbe3;
    private String talbe4;
    
    private String statusType;
    
    private String[] q_caseType;//通報案件類型
    private String[] q_caseSource;//國內/國外
    private String q_drugEventsSources;//通報來源
    private String q_notifierType;//通報者屬性
    private String q_notifyDept;//通報單位
    private String q_cName;//醫療器材品名
    private String q_medPermit;//許可證字號-字
    private String q_medPermitNumber;//許可證字號-號
    private String q_medFactory;//製造廠
    private String q_medCountry;//製造廠國別
    private String q_permitFirm;//許可證申請商
    private String q_medMainCategory;//醫材主類別
    private String q_medSecCategory;//醫材次類別
    private String[] q_bulletinQuality;//通報品質
    private String[] reportField;//列印報表欄位
    private String q_badReactionResultsAD1;//原始不良反應for MED2001
    private String q_badReactionResultsAD2;//原始不良反應for MED0001
    private String q_adverseReactionsResult;//醫材與不良反應關聯性
    private String q_medicalIssues;//醫材問題代碼
    private String q_patientIssues;//病人問題名稱
    private String q_occurDateS;//發生日期起
    private String q_occurDateE;//發生日期迄
    private String q_notifierRepDateS;//通報日期起
    private String q_notifierRepDateE;//通報日期迄
    private String q_ncarResult;//NCAR通報狀況
    private String q_caseStatus_eventType_1;//案件狀態-不良反應
    private String q_caseStatus_eventType_2;//案件狀態-不良品
    private String q_caseStatus_MED2001;//案件狀態-臨床
    private String[] q_eventClass;//不良品事件等級
    private String q_approvedUnits;//列管單位
    private String q_approvedUnitsOther;//列管單位其他

	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql_Med0001Db = getMED0001DBHql(); 
		String hql_Med0201Db = getMED2001DBHql();

		java.util.List objList_Med0001Db = ServiceGetter.getInstance().getTcbwService().load("select distinct med0001 " + hql_Med0001Db + " order by med0001.id");
		java.util.List objList_Med2001Db = ServiceGetter.getInstance().getTcbwService().load("select distinct med2001 " + hql_Med0201Db + " order by med2001.id");
		
		int count = (objList_Med0001Db!=null?objList_Med0001Db.size():0) + (objList_Med2001Db!=null?objList_Med2001Db.size():0);
		this.processCurrentPageAttribute(count);
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			if (objList_Med0001Db != null && objList_Med0001Db.size() > 0) 
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("MEDSTATUS2");
				java.util.Map<String, String> caseSourceMap = TCBWCommon.getCaseSourceName();
				for(Object dtlObj : objList_Med0001Db) 
				{				
					Med0001Db dtl = (Med0001Db)dtlObj;
					String[] rowArray = new String[10];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getOccurDate());
					rowArray[2] = Common.get(dtl.getApplNo());
					rowArray[3] = Common.get(TCBWCommon.transMED0001CaseType(dtl));
					rowArray[4] = Common.get(caseSourceMap.get(dtl.getCaseSource()));
					rowArray[5] = Common.get(dtl.getMedCname());
					rowArray[6] = Common.get(dtl.getMedPermitFirm());
					rowArray[7] = Common.get(statusMap.get(dtl.getStatus()));
					rowArray[8] = Common.get("明細");
					rowArray[9] = Common.get(dtl.getEventKind()); //案件類別
					
					arrList.add(rowArray);
				}
			}
			if (objList_Med2001Db != null && objList_Med2001Db.size() > 0) 
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("MEDSTATUS2");
				java.util.Map<String, String> caseSourceMap = TCBWCommon.getCaseSourceName();
				for(Object dtlObj : objList_Med2001Db) 
				{				
					Med2001Db dtl = (Med2001Db)dtlObj;
					String[] rowArray = new String[10];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getOccurDate());
					rowArray[2] = Common.get(dtl.getApplNo());
					rowArray[3] = Common.get(TCBWCommon.transMED2001CaseType(dtl));
					rowArray[4] = Common.get(caseSourceMap.get(dtl.getCaseSource()));
					rowArray[5] = Common.get(dtl.getMedTestMedical());
					rowArray[6] = Common.get(dtl.getMedFactory());
					rowArray[7] = Common.get(statusMap.get(dtl.getStatus()));
					rowArray[8] = Common.get("明細");
					rowArray[9] = "3"; //案件類別
					
					arrList.add(rowArray);
				}
			}
		}
		java.util.ArrayList<String[]> tempList = new java.util.ArrayList<String[]>();
		for(int i = this.getRecordStart() ; i < this.getRecordStart()+this.getPageSize() ; i ++){
			if(i < arrList.size())
				tempList.add(arrList.get(i));
		}
		arrList = tempList;
		return arrList;
	}

	private String getMED0001DBHql() {
		String hql = " from Med0001Db med0001 left join med0001.med0007Dbs med0007 left join med0001.med0008Dbs med0008 left join med0001.med0009Dbs med0009 where 1 = 1 ";
		//只查有案號
		hql += " and med0001.applNo is not null ";
		//檢查是否有輸入通報案件類型，A1,A2,A3,A4查MED00XX，B1查MED20XX，有勾才查該Table,MED0001-eventKind 1查不良反應(國內國外就查caseSource,medPermit = 'Z0'),2查不良品
		if(getQ_caseType() != null && getQ_caseType().length > 0){
			StringBuilder sb = new StringBuilder();
			for(String caseType : getQ_caseType()){
				if(caseType != null && caseType.indexOf("A1") > -1){
					if(sb.length() > 0)sb.append(" or ");
					sb.append("( med0001.eventKind = '1'");
					sb.append(" and med0001.caseSource = 'in')");
				}
				if(caseType != null && caseType.indexOf("A2") > -1){
					if(sb.length() > 0)sb.append(" or ");
					sb.append("( med0001.eventKind = '1'");
					sb.append(" and med0001.caseSource = 'out')");
				}
				if(caseType != null && caseType.indexOf("A3") > -1){
					if(sb.length() > 0)sb.append(" or ");
					sb.append("( med0001.eventKind = '1'");
					sb.append(" and med0001.medPermit = 'Z0')");
				}
				if(caseType != null && caseType.indexOf("A4") > -1){
					if(sb.length() > 0)sb.append(" or ");
					sb.append("( med0001.eventKind = '2')");
				}	
			}
			if(sb.length() <= 0){hql += " and 1 = 2";return hql;}
			hql += " and ( " + sb.toString() + " ) ";
		}else{
			hql += " and 1 = 2";
			return hql;
		}
		//國內/國外
		if(getQ_caseSource() != null && getQ_caseSource().length > 0){
			hql += " and med0001.caseSource in (";
			for(int i = 0 ; i < getQ_caseSource().length ; i ++)
				if(getQ_caseSource()[i] != null && !"".equals(getQ_caseSource()[i])){
					hql += Common.sqlChar(getQ_caseSource()[i]) ;
					hql += (i < getQ_caseSource().length -1)?",":"";
				}
			hql += ")";
		}
		//通報來源 01?但是med20XX又是1
		if(getQ_drugEventsSources() != null && !"".equals(getQ_drugEventsSources()) && getQ_drugEventsSources().length() > 1)
			hql += " and med0001.drugEventsSources = " + Common.sqlChar(getQ_drugEventsSources().substring(1,2));
		//通報者屬性
		if(getQ_notifierType() != null && !"".equals(getQ_notifierType()))
			hql += " and med0001.notifierType = " + Common.sqlChar(getQ_notifierType());
		//通報單位
		if(getQ_notifyDept() != null && !"".equals(getQ_notifyDept()))
			hql += " and med0001.notifierDept like " + Common.likeSqlChar(getQ_notifyDept());
		//醫療器材品名
		if(getQ_cName() != null && !"".equals(getQ_cName()))
			hql += " and med0001.medCname like " + Common.likeSqlChar(getQ_cName());
		//許可證字號-字
		if(getQ_medPermit() != null && !"".equals(getQ_medPermit()))
			hql += " and med0001.medPermit = " + Common.sqlChar(getQ_medPermit());
		//許可證字號-號
		if(getQ_medPermitNumber() != null && !"".equals(getQ_medPermitNumber()))
			hql += " and med0001.medPermitNumber = " + Common.sqlChar(getQ_medPermitNumber());
		//製造廠
		if(getQ_medFactory() != null && !"".equals(getQ_medFactory()))
			hql += " and med0001.medFactory = " + Common.sqlChar(getQ_medFactory());
		//製造廠國別
		if(getQ_medCountry() != null && !"".equals(getQ_medCountry()))
			hql += " and med0001.medCountry = " + Common.sqlChar(getQ_medCountry());
		//許可證申請商
		if(getQ_permitFirm() != null && !"".equals(getQ_permitFirm()))
			hql += " and med0001.medPermitFirm like " + Common.likeSqlChar(getQ_permitFirm());
		//醫材主類別
		if(getQ_medMainCategory() != null && !"".equals(getQ_medMainCategory()))
			hql += " and med0001.medMainCategory = " + Common.sqlChar(getQ_medMainCategory());
		//醫材次類別
		if(getQ_medSecCategory() != null && !"".equals(getQ_medSecCategory()))
			hql += " and med0001.medSecCategory = " + Common.sqlChar(getQ_medSecCategory());
		//通報品質
		if(getQ_bulletinQuality() != null && getQ_bulletinQuality().length > 0){
			hql += " and (med0007.bulletinQuality in (";
			for(int i = 0 ; i < getQ_bulletinQuality().length ; i ++)
				if(getQ_bulletinQuality()[i] != null && !"".equals(getQ_bulletinQuality()[i])){
					hql += Common.sqlChar(getQ_bulletinQuality()[i]) ;
					hql += (i < getQ_bulletinQuality().length -1)?",":"";
				}
			hql += ")";
			hql += " or med0008.bulletinQuality in (";
			for(int i = 0 ; i < getQ_bulletinQuality().length ; i ++)
				if(getQ_bulletinQuality()[i] != null && !"".equals(getQ_bulletinQuality()[i])){
					hql += Common.sqlChar(getQ_bulletinQuality()[i]) ;
					hql += (i < getQ_bulletinQuality().length -1)?",":"";
				}
			hql += ")";
			hql += ")";
		}
		//原始不良反應
		if(getQ_badReactionResultsAD2() != null && !"".equals(getQ_badReactionResultsAD2()))
			hql += " and med0001.badReactionResults = " + Common.sqlChar(getQ_badReactionResultsAD2());
		//醫材問題代碼名稱(模糊)
		if(getQ_medicalIssues() != null && !"".equals(getQ_medicalIssues())){
			List med1006code = getMed1006Code(getQ_medicalIssues());//輸入的查詢條件先到設定檔查詢
			if(null != med1006code && med1006code.size() > 0) {
				hql += " and (";
				for(int i = 0; i < med1006code.size(); i++) {
					Object code = med1006code.get(i);
					for(int j = 0; j < 3; j++) {
						if(j==0) {
							hql += " med0007.patientIssues like " + Common.likeSqlChar(Common.get(code)) + " or";//07檔醫材問題代碼欄位為patientIssues，病人問題代碼欄位為medicalIssues
						} else if(j==1) {
							hql += " med0008.medicalIssues like " + Common.likeSqlChar(Common.get(code)) + " or";
						} else if(j==2) {
							hql += " med0009.medicalIssues like " + Common.likeSqlChar(Common.get(code));
							if(i != med1006code.size() - 1) {
								hql += " or";
							}
						}
					}
				}
			}
			else {
				hql += " and (1=2";
			}
			hql += ")";
		}
		//病人問題代碼名稱(模糊)
		if(getQ_patientIssues() != null && !"".equals(getQ_patientIssues())) {
			
			List med1005code = getMed1005Code(getQ_patientIssues());//輸入的查詢條件先到設定檔查詢
			if(null != med1005code && med1005code.size() > 0) {
				hql += " and (";
				for(int i = 0; i < med1005code.size(); i++) {
					Object code = med1005code.get(i);
					hql += " med0007.medicalIssues like " + Common.likeSqlChar(Common.get(code));
					if(i != med1005code.size() - 1) {
						hql += " or";
					}
				}
			} else {
				hql += " and (1=2";
			}
			hql += ")";
		}
		//醫材與不良反應關聯性
		if(getQ_adverseReactionsResult() != null && !"".equals(getQ_adverseReactionsResult()))
			hql += " and med0007.adverseReactionsResult = " + Common.sqlChar(getQ_adverseReactionsResult());
		//不良品事件等級
		if(getQ_eventClass() != null && getQ_eventClass().length > 0){
			hql += " and (med0008.eventClass in (";
			for(int i = 0 ; i < getQ_eventClass().length ; i ++)
				if(getQ_eventClass()[i] != null && !"".equals(getQ_eventClass()[i])){
					hql += Common.sqlChar(getQ_eventClass()[i]) ;
					hql += (i < getQ_eventClass().length -1)?",":"";
				}
			hql += ")";
			hql += " or med0009.eventClass in (";
			for(int i = 0 ; i < getQ_eventClass().length ; i ++)
				if(getQ_eventClass()[i] != null && !"".equals(getQ_eventClass()[i])){
					hql += Common.sqlChar(getQ_eventClass()[i]) ;
					hql += (i < getQ_eventClass().length -1)?",":"";
				}
			hql += ")";
			hql += ")";
		}
		//發生日期起
		if(getQ_occurDateS() != null && !"".equals(getQ_occurDateS()))
			hql += " and med0001.occurDate >= " + Common.sqlChar(getQ_occurDateS());
		//發生日期迄
		if(getQ_occurDateE() != null && !"".equals(getQ_occurDateE()))
			hql += " and med0001.occurDate <= " + Common.sqlChar(getQ_occurDateE());
		//通報日期起
		if(getQ_notifierRepDateS() != null && !"".equals(getQ_notifierRepDateS()))
			hql += " and med0001.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		//通報日期迄
		if(getQ_notifierRepDateE() != null && !"".equals(getQ_notifierRepDateE()))
			hql += " and med0001.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		//案件狀態
		if((getQ_caseStatus_eventType_1() != null && !"".equals(getQ_caseStatus_eventType_1()))
				|| getQ_caseStatus_eventType_2() != null && !"".equals(getQ_caseStatus_eventType_2())){
			hql += " and (";
			//案件狀態-不良反應
			if(getQ_caseStatus_eventType_1() != null && !"".equals(getQ_caseStatus_eventType_1()))
				hql += "(med0001.eventKind = '1' and med0001.status = " + Common.sqlChar(getQ_caseStatus_eventType_1()) + ")";
			//案件狀態-不良品
			if(getQ_caseStatus_eventType_2() != null && !"".equals(getQ_caseStatus_eventType_2())){
				if(getQ_caseStatus_eventType_1() != null && !"".equals(getQ_caseStatus_eventType_1()))
					hql += " or " ;
				hql += "(med0001.eventKind = '2' and med0001.status = " + Common.sqlChar(getQ_caseStatus_eventType_2()) + ")";
			}
			hql += " )";
		}
		//NCAR通報狀況
		if(getQ_ncarResult() != null && !"".equals(getQ_ncarResult())){
			hql += " and ( med0007.ncarResult = " + Common.sqlChar(getQ_ncarResult());
			hql += " or med0008.ncarResult = " + Common.sqlChar(getQ_ncarResult());
			hql += " or med0009.ncarResult = " + Common.sqlChar(getQ_ncarResult()) + ")";
		}
		return hql;
	}
	
	private String getMED2001DBHql() {
		String hql = " from Med2001Db med2001 where 1 = 1 ";
		//只查有案號
		hql += " and med2001.applNo is not null ";
		//檢查是否有輸入通報案件類型，B1則查此Table，並無特別條件
		if(getQ_caseType() != null && getQ_caseType().length > 0){
			StringBuilder sb = new StringBuilder();
			for(String caseType : getQ_caseType()){
				if(caseType != null && caseType.indexOf("B1") > -1){
					sb.append(" and 1 = 1 ");
				}
			}
			if(sb.length() <= 0){hql += " and 1 = 2";return hql;}
			hql += sb.toString() ;
		}else{
			hql += " and 1 = 2";
			return hql;
		}
		//國內/國外
		if(getQ_caseSource() != null && getQ_caseSource().length > 0){
			hql += " and med2001.caseSource in (";
			for(int i = 0 ; i < getQ_caseSource().length ; i ++)
				if(getQ_caseSource()[i] != null && !"".equals(getQ_caseSource()[i])){
					hql += Common.sqlChar(getQ_caseSource()[i]) ;
					hql += (i < getQ_caseSource().length -1)?",":"";
				}
			hql += ")";
		}
		//通報者屬性
		if(getQ_notifierType() != null && !"".equals(getQ_notifierType()))
			hql += " and med2001.drugEventsSources = " + Common.sqlChar(getQ_notifierType());
		//通報單位
		if(getQ_notifyDept() != null && !"".equals(getQ_notifyDept()))
			hql += " and med2001.notifierDept like " + Common.likeSqlChar(getQ_notifyDept());
		//列管單位
		if(getQ_approvedUnits() != null && !"".equals(getQ_approvedUnits())){
			if("3".equals(getQ_approvedUnits()))
				hql += " and med2001.approvedUnitsOther like " + Common.likeSqlChar(getQ_approvedUnitsOther());
			else
				hql += " and med2001.approvedUnits = " + Common.sqlChar(getQ_approvedUnits());
		}
		//醫療器材品名
		if(getQ_cName() != null && !"".equals(getQ_cName()))
			hql += " and med2001.medTestMedical like " + Common.likeSqlChar(getQ_cName());
		//許可證字號-字
		if(getQ_medPermit() != null && !"".equals(getQ_medPermit()))
			hql += " and med2001.medPermit = " + Common.sqlChar(getQ_medPermit());
		//許可證字號-號
		if(getQ_medPermitNumber() != null && !"".equals(getQ_medPermitNumber()))
			hql += " and med2001.medPermitNumber = " + Common.sqlChar(getQ_medPermitNumber());
		//製造商
		if(getQ_medFactory() != null && !"".equals(getQ_medFactory()))
			hql += " and med2001.medFactory = " + Common.sqlChar(getQ_medFactory());
		//製造廠國別
		if(getQ_medCountry() != null && !"".equals(getQ_medCountry()))
			hql += " and med2001.medCountry = " + Common.sqlChar(getQ_medCountry());
		//供應商
		if(getQ_permitFirm() != null && !"".equals(getQ_permitFirm()))
			hql += " and med2001.medSupplier = " + Common.sqlChar(getQ_permitFirm());
		//醫材主類別
		if(getQ_medMainCategory() != null && !"".equals(getQ_medMainCategory()))
			hql += " and med2001.medMainCategoryCode = " + Common.sqlChar(getQ_medMainCategory());
		//醫材次類別
		if(getQ_medSecCategory() != null && !"".equals(getQ_medSecCategory()))
			hql += " and med2001.medSecCategoryCode = " + Common.sqlChar(getQ_medSecCategory());
		//原始不良反應
		if(getQ_badReactionResultsAD1() != null && !"".equals(getQ_badReactionResultsAD1()))
			hql += " and med2001.badReactionResults = " + Common.sqlChar(getQ_badReactionResultsAD1());
		//發生日期起
		if(getQ_occurDateS() != null && !"".equals(getQ_occurDateS()))
			hql += " and med2001.occurDate >= " + Common.sqlChar(getQ_occurDateS());
		//發生日期迄
		if(getQ_occurDateE() != null && !"".equals(getQ_occurDateE()))
			hql += " and med2001.occurDate <= " + Common.sqlChar(getQ_occurDateE());
		//通報日期起
		if(getQ_notifierRepDateS() != null && !"".equals(getQ_notifierRepDateS()))
			hql += " and med2001.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		//通報日期迄
		if(getQ_notifierRepDateE() != null && !"".equals(getQ_notifierRepDateE()))
			hql += " and med2001.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		//案件狀態-臨床
		if(getQ_caseStatus_MED2001() != null && !"".equals(getQ_caseStatus_MED2001()))
			hql += " and med2001.status = " + Common.sqlChar(getQ_caseStatus_MED2001());
		System.out.println("[MEDIN0301Q]-[MED2001DBHql]：" + hql);
		return hql;
	}
	
	//取得病人問題代碼code
	private List getMed1005Code(String name) {
		List code = null;
		String sql = "select med1005.code from Med1005Db med1005 where med1005.name like " + Common.likeSqlChar(name);
		code = ServiceGetter.getInstance().getTcbwService().load(sql);
		return code;
	}
	//取得醫材問題代碼code
	private List getMed1006Code(String name) {
		List code = null;
		String sql = "select med1006.code from Med1006Db med1006 where med1006.name like " + Common.likeSqlChar(name);
		code = ServiceGetter.getInstance().getTcbwService().load(sql);
		return code;
	}

	/**
	 * 傳回List<Object[]>
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> getTableModel() throws Exception{
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		
		String hql_Med0001Db = getMED0001DBHql(); 
		String hql_Med0201Db = getMED2001DBHql();

		java.util.List objList_Med0001Db = ServiceGetter.getInstance().getTcbwService().load("select distinct med0001 " + hql_Med0001Db + " order by med0001.id");
		java.util.List objList_Med2001Db = ServiceGetter.getInstance().getTcbwService().load("select distinct med2001 " + hql_Med0201Db + " order by med2001.id");
		
		int count = (objList_Med0001Db!=null?objList_Med0001Db.size():0) + (objList_Med2001Db!=null?objList_Med2001Db.size():0);
		String[] reportField = getReportField();
		if(count > 0 && reportField != null && reportField.length > 0){
			//初始化代碼
			initCodeMap();
			ArrayList<String> row = new ArrayList<String>();
			insertRow(arrList,row,getFieldNameMap(),reportField,true);
			if (objList_Med0001Db != null && objList_Med0001Db.size() > 0){				
				for(Object dtlObj : objList_Med0001Db)
					insertRow(arrList,row,dtlObj,reportField,false);				
			}
			if (objList_Med2001Db != null && objList_Med2001Db.size() > 0){
				for(Object dtlObj : objList_Med2001Db)
					insertRow(arrList,row,dtlObj,reportField,false);
			}
		}
		return arrList;
	}
	
	private Map<String,String> getFieldNameMap() {
		Map<String,String> fMap = new HashMap<String,String>();
		fMap.put("applNo", "案件編號");
		fMap.put("caseType", "通報案件類型");
		fMap.put("caseSource", "國內/國外");
		fMap.put("drugEventsSources", "通報來源");
		fMap.put("notifierType", "通報者屬性");
		fMap.put("notifierDept", "通報單位");
		fMap.put("approvedUnits", "列管單位");
		fMap.put("medCname", "醫療器材品名");
		fMap.put("medPermit", "許可證字號");
		fMap.put("medFactory", "製造廠");
		fMap.put("medCountry", "製造廠國別");
		fMap.put("medPermitFirm", "許可證申請商");
		fMap.put("medMainCategory", "醫材主類別");
		fMap.put("medSecCategory", "醫材次類別");
		fMap.put("bulletinQuality", "通報品質");
		fMap.put("badReactionResults", "原始不良反應結果");
		fMap.put("", "評估後不良反應結果");
		fMap.put("medicalIssues", "醫材問題代碼");
		fMap.put("patientIssues", "病人問題代碼");
		fMap.put("adverseReactionsResult", "醫材與不良反應關聯性");
		fMap.put("eventClass", "不良品事件等級");
		fMap.put("occurDate", "發生日期");
		fMap.put("notifierRepDate", "通報日期");
		fMap.put("status", "案件狀態");
		fMap.put("ncarResult", "NCAR通報狀況");
		return fMap;
	}

	private void insertRow(ArrayList<Object[]> arrList, ArrayList<String> row,Object dtlObj, String[] reportField, boolean isFirstRow)throws Exception {
		row.clear();
		for(String fieldName : reportField){
			String value = BeanUtil.getPropertyByBeanUtils(dtlObj, fieldName);
			if(!isFirstRow){
				//如果需要轉換代碼或查子table，請在這裡判斷
				if("caseSource".equals(fieldName))
					value = caseSourceMap.get(value);
				if("drugEventsSources".equals(fieldName))
					value = MEDNFSMap.get(value);
				if("notifierType".equals(fieldName))
					value = MEDNFT1Map.get(value);
				if("notifierType".equals(fieldName))
					value = MEDNFT1Map.get(value);
				if("medMainCategory".equals(fieldName))
					value = MEDMCTMap.get(value);
				if("medSecCategory".equals(fieldName))
					value = MEDSCTMap.get(value);
				if(dtlObj instanceof Med0001Db){
					if("badReactionResults".equals(fieldName))
						value = MEDAD2Map.get(value);
					if("status".equals(fieldName))
						value = MEDSTATUS2.get(value);
				}
				if(dtlObj instanceof Med2001Db){
					if("badReactionResults".equals(fieldName))
						value = MEDAD1Map.get(value);
					if("status".equals(fieldName))
						value = MEDSTATUS1.get(value);
				}
				if("approvedUnits".equals(fieldName)){
					value = ApprovedUnitsMap.get(value);
					if("3".equals(value))
						value += ":" + BeanUtil.getPropertyByBeanUtils(dtlObj, "approvedUnitsOther");
				}
				//許可證字號，字與號合併起來
				if("medPermit".equals(fieldName))
					value = MEDPKID.get(value)+BeanUtil.getPropertyByBeanUtils(dtlObj, "medPermitNumber");
				
				//子table查詢 查最新一筆
				if(dtlObj instanceof Med0001Db){
					Med0001Db med0001 = (Med0001Db)dtlObj;
					if(med0001.getMed0007Dbs() != null && med0001.getMed0007Dbs().size() > 0){
						Object o = TCBWCommon.getMaxObjectById(med0001.getMed0007Dbs());
						Med0007Db med0007 = (Med0007Db)o;
						if("bulletinQuality".equals(fieldName)){
							value = med0007.getBulletinQuality();
							value = MEDNTQLMap.get(value);
						}
						if("medicalIssues".equals(fieldName)){
							value = med0007.getPatientIssues();
							String[] medicalIssues07 = null;
							if(null != value && value.length() > 0) {
								medicalIssues07 = value.split(",");
								for(int i = 0; i < medicalIssues07.length; i++) { //醫材問題代碼欄位資料的值為多筆型態：0001,0002...
									if(i==0) {
										value = "";
									}
									value += MED1006Map.get(medicalIssues07[i]);
									if(value.equals("null")) {
										value = "";
									} else {
										if(i != medicalIssues07.length - 1) {
											value += ",";
										}
									}
									
								}
							}
						}
						if("patientIssues".equals(fieldName)){
							value = med0007.getMedicalIssues();
							String[] medicalIssues07 = null;
							if(null != value && value.length() > 0) { //病人問題代碼欄位資料的值為多筆型態：0001,0002...
								medicalIssues07 = value.split(",");
								for(int i = 0; i < medicalIssues07.length; i++) {
									if(i==0) {
										value = "";
									}
									value += MED1005Map.get(medicalIssues07[i]);
									if(value.equals("null")) {
										value = "";
									} else {
										if(i != medicalIssues07.length - 1) {
											value += ",";
										}
									}
								}
							}
						}
						if("adverseReactionsResult".equals(fieldName)){
							value = med0007.getAdverseReactionsResult();
							value = AdverseReactionsResultMap.get(value);
						}
						if("ncarResult".equals(fieldName)){
							value = med0007.getNcarResult();
							value = NCTRResultMap.get(value);
						}
					}
					if(med0001.getMed0008Dbs() != null && med0001.getMed0008Dbs().size() > 0){
						Object o = TCBWCommon.getMaxObjectById(med0001.getMed0008Dbs());
						Med0008Db med0008 = (Med0008Db)o;
						if("bulletinQuality".equals(fieldName)){
							value = med0008.getBulletinQuality();
							value = MEDNTQLMap.get(value);
						}
						if("medicalIssues".equals(fieldName)){
							value = med0008.getMedicalIssues();
							String[] medicalIssues08 = null;
							if(null != value && value.length() > 0) {
								medicalIssues08 = value.split(",");
								for(int i = 0; i < medicalIssues08.length; i++) {
									if(i==0) {
										value = "";
									}
									value += MED1006Map.get(medicalIssues08[i]);
									if(i != medicalIssues08.length - 1) {
										value += "、";
									}
								}
							}
						}
						if("eventClass".equals(fieldName)){
							value = med0008.getEventClass();
							value = MEDEVCMap.get(value);
						}
						if("ncarResult".equals(fieldName)){
							value = med0008.getNcarResult();
							value = NCTRResultMap.get(value);
						}
					}
					if(med0001.getMed0009Dbs() != null && med0001.getMed0009Dbs().size() > 0){
						Object o = TCBWCommon.getMaxObjectById(med0001.getMed0009Dbs());
						Med0009Db med0009 = (Med0009Db)o;
						if("medicalIssues".equals(fieldName)){
							value = med0009.getMedicalIssues();
							String[] medicalIssues09 = null;
							if(null != value && value.length() > 0) {
								medicalIssues09 = value.split(",");
								for(int i = 0; i < medicalIssues09.length; i++) {
									if(i==0) {
										value = "";
									}
									value += MED1006Map.get(medicalIssues09[i]);
									if(i != medicalIssues09.length - 1) {
										value += "、";
									}
								}
							}
							
						}
						if("eventClass".equals(fieldName)){
							value = med0009.getEventClass();
							value = MEDEVCMap.get(value);
						}
						if("ncarResult".equals(fieldName)){
							value = med0009.getNcarResult();
							value = NCTRResultMap.get(value);
						}
					}
				}
			}
			row.add(value);
		}
		arrList.add(row.toArray());
	}

	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		
	}

	public String getCaseType() {
		return checkGet(caseType);
	}

	public void setCaseType(String caseType) {
		this.caseType = checkSet(caseType);
	}

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getDoType() {
		return checkGet(doType);
	}

	public void setDoType(String doType) {
		this.doType = checkSet(doType);
	}

	public String getIsInOrOutPerson() {
		return checkGet(isInOrOutPerson);
	}

	public void setIsInOrOutPerson(String isInOrOutPerson) {
		this.isInOrOutPerson = checkSet(isInOrOutPerson);
	}

	public String getInOrOut() {
		return checkGet(inOrOut);
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = checkSet(inOrOut);
	}

	public String getTalbe3() {
		return checkGet(talbe3);
	}

	public void setTalbe3(String talbe3) {
		this.talbe3 = checkSet(talbe3);
	}

	public String getTalbe4() {
		return checkGet(talbe4);
	}

	public void setTalbe4(String talbe4) {
		this.talbe4 = checkSet(talbe4);
	}

	public String getStatusType() {
		return checkGet(statusType);
	}

	public void setStatusType(String statusType) {
		this.statusType = checkSet(statusType);
	}

	public String getQ_drugEventsSources() {
		return checkGet(q_drugEventsSources);
	}

	public void setQ_drugEventsSources(String q_drugEventsSources) {
		this.q_drugEventsSources = checkSet(q_drugEventsSources);
	}

	public String getQ_notifierType() {
		return checkGet(q_notifierType);
	}

	public void setQ_notifierType(String q_notifierType) {
		this.q_notifierType = checkSet(q_notifierType);
	}

	public String getQ_notifyDept() {
		return checkGet(q_notifyDept);
	}

	public void setQ_notifyDept(String q_notifyDept) {
		this.q_notifyDept = checkSet(q_notifyDept);
	}

	public String getQ_cName() {
		return checkGet(q_cName);
	}

	public void setQ_cName(String q_cName) {
		this.q_cName = checkSet(q_cName);
	}

	

	public String getQ_medPermit() {
		return checkGet(q_medPermit);
	}

	public void setQ_medPermit(String q_medPermit) {
		this.q_medPermit = checkSet(q_medPermit);
	}

	public String getQ_medPermitNumber() {
		return checkGet(q_medPermitNumber);
	}

	public void setQ_medPermitNumber(String q_medPermitNumber) {
		this.q_medPermitNumber = checkSet(q_medPermitNumber);
	}

	public String getQ_medFactory() {
		return checkGet(q_medFactory);
	}

	public void setQ_medFactory(String q_medFactory) {
		this.q_medFactory = checkSet(q_medFactory);
	}

	public String getQ_medCountry() {
		return checkGet(q_medCountry);
	}

	public void setQ_medCountry(String q_medCountry) {
		this.q_medCountry = checkSet(q_medCountry);
	}

	public String getQ_permitFirm() {
		return checkGet(q_permitFirm);
	}

	public void setQ_permitFirm(String q_permitFirm) {
		this.q_permitFirm = checkSet(q_permitFirm);
	}

	public String getQ_medMainCategory() {
		return checkGet(q_medMainCategory);
	}

	public void setQ_medMainCategory(String q_medMainCategory) {
		this.q_medMainCategory = checkSet(q_medMainCategory);
	}

	public String getQ_medSecCategory() {
		return checkGet(q_medSecCategory);
	}

	public void setQ_medSecCategory(String q_medSecCategory) {
		this.q_medSecCategory = checkSet(q_medSecCategory);
	}

	public String[] getQ_caseType() {
		return checkGet(q_caseType);
	}

	public void setQ_caseType(String[] q_caseType) {
		this.q_caseType = checkSet(q_caseType);
	}

	public String[] getQ_caseSource() {
		return checkGet(q_caseSource);
	}

	public void setQ_caseSource(String[] q_caseSource) {
		this.q_caseSource = checkSet(q_caseSource);
	}

	public String[] getQ_bulletinQuality() {
		return checkGet(q_bulletinQuality);
	}

	public void setQ_bulletinQuality(String[] q_bulletinQuality) {
		this.q_bulletinQuality = checkSet(q_bulletinQuality);
	}

	public String[] getReportField() {
		return checkGet(reportField);
	}

	public void setReportField(String[] reportField) {
		this.reportField = checkSet(reportField);
	}

	public String getQ_badReactionResultsAD1() {
		return checkGet(q_badReactionResultsAD1);
	}

	public void setQ_badReactionResultsAD1(String q_badReactionResultsAD1) {
		this.q_badReactionResultsAD1 = checkSet(q_badReactionResultsAD1);
	}

	public String getQ_badReactionResultsAD2() {
		return checkGet(q_badReactionResultsAD2);
	}

	public void setQ_badReactionResultsAD2(String q_badReactionResultsAD2) {
		this.q_badReactionResultsAD2 = checkSet(q_badReactionResultsAD2);
	}

	public String getQ_adverseReactionsResult() {
		return checkGet(q_adverseReactionsResult);
	}

	public void setQ_adverseReactionsResult(String q_adverseReactionsResult) {
		this.q_adverseReactionsResult = checkSet(q_adverseReactionsResult);
	}

	public String getQ_medicalIssues() {
		return checkGet(q_medicalIssues);
	}

	public void setQ_medicalIssues(String q_medicalIssues) {
		this.q_medicalIssues = checkSet(q_medicalIssues);
	}

	public String getQ_patientIssues() {
		return checkGet(q_patientIssues);
	}

	public void setQ_patientIssues(String q_patientIssues) {
		this.q_patientIssues = checkSet(q_patientIssues);
	}

	public String getQ_occurDateS() {
		return checkGet(q_occurDateS);
	}

	public void setQ_occurDateS(String q_occurDateS) {
		this.q_occurDateS = checkSet(q_occurDateS);
	}

	public String getQ_occurDateE() {
		return checkGet(q_occurDateE);
	}

	public void setQ_occurDateE(String q_occurDateE) {
		this.q_occurDateE = checkSet(q_occurDateE);
	}

	public String getQ_notifierRepDateS() {
		return checkGet(q_notifierRepDateS);
	}

	public void setQ_notifierRepDateS(String q_notifierRepDateS) {
		this.q_notifierRepDateS = checkSet(q_notifierRepDateS);
	}

	public String getQ_notifierRepDateE() {
		return checkGet(q_notifierRepDateE);
	}

	public void setQ_notifierRepDateE(String q_notifierRepDateE) {
		this.q_notifierRepDateE = checkSet(q_notifierRepDateE);
	}

	public String getQ_ncarResult() {
		return checkGet(q_ncarResult);
	}

	public void setQ_ncarResult(String q_ncarResult) {
		this.q_ncarResult = checkSet(q_ncarResult);
	}

	public String getQ_caseStatus_eventType_1() {
		return checkGet(q_caseStatus_eventType_1);
	}

	public void setQ_caseStatus_eventType_1(String q_caseStatus_eventType_1) {
		this.q_caseStatus_eventType_1 = checkSet(q_caseStatus_eventType_1);
	}

	public String getQ_caseStatus_eventType_2() {
		return checkGet(q_caseStatus_eventType_2);
	}

	public void setQ_caseStatus_eventType_2(String q_caseStatus_eventType_2) {
		this.q_caseStatus_eventType_2 = checkSet(q_caseStatus_eventType_2);
	}

	public String getQ_caseStatus_MED2001() {
		return checkGet(q_caseStatus_MED2001);
	}

	public void setQ_caseStatus_MED2001(String q_caseStatus_MED2001) {
		this.q_caseStatus_MED2001 = checkSet(q_caseStatus_MED2001);
	}

	public String[] getQ_eventClass() {
		return q_eventClass;
	}

	public void setQ_eventClass(String[] q_eventClass) {
		this.q_eventClass = q_eventClass;
	}


	public String getQ_approvedUnits() {
		return checkGet(q_approvedUnits);
	}

	public void setQ_approvedUnits(String q_approvedUnits) {
		this.q_approvedUnits = checkSet(q_approvedUnits);
	}

	public String getQ_approvedUnitsOther() {
		return checkGet(q_approvedUnitsOther);
	}

	public void setQ_approvedUnitsOther(String q_approvedUnitsOther) {
		this.q_approvedUnitsOther = checkSet(q_approvedUnitsOther);
	}

	//--------------------------------設定代碼
	private void initCodeMap()throws Exception{
		caseSourceMap = TCBWCommon.getCaseSourceName();
		MEDNFSMap = TCBWCommon.getCommonCodeMap("MEDNFS");
		MEDNFT1Map = TCBWCommon.getCommonCodeMap("MEDNFT1");
		MEDMCTMap = TCBWCommon.getCommonCodeMap("MEDMCT");
		MEDSCTMap = TCBWCommon.getCommonCodeMap("MEDSCT");
		MEDNTQLMap = TCBWCommon.getCommonCodeMap("MEDNTQL");
		MEDAD2Map = TCBWCommon.getCommonCodeMap("MEDAD2");
		MEDAD1Map = TCBWCommon.getCommonCodeMap("MEDAD1");
		MED1005Map = TCBWCommon.getCodeNameMED1005();
		MED1006Map = TCBWCommon.getCodeNameMED1006();
		AdverseReactionsResultMap = TCBWCommon.getCodeNameAdverseReactionsResult();
		MEDEVCMap = TCBWCommon.getCommonCodeMap("MEDEVC");
		MEDSTATUS1 = TCBWCommon.getCommonCodeMap("MEDSTATUS1");
		MEDSTATUS2 = TCBWCommon.getCommonCodeMap("MEDSTATUS2");
		NCTRResultMap = TCBWCommon.getCodeNameNCTRResult();
		ApprovedUnitsMap = TCBWCommon.getCodeNameApprovedUnitsMap();
		MEDPKID = TCBWCommon.getCommonCodeMap("MEDPKID");
	}
	
	private java.util.Map<String, String> caseSourceMap ;
	private java.util.Map<String, String> MEDNFSMap ;
	private java.util.Map<String, String> MEDNFT1Map ; 
	private java.util.Map<String, String> MEDMCTMap ;
	private java.util.Map<String, String> MEDSCTMap ; 
	private java.util.Map<String, String> MEDNTQLMap ; 
	private java.util.Map<String, String> MEDAD2Map ; 
	private java.util.Map<String, String> MEDAD1Map ; 
	private java.util.Map<String, String> MED1005Map ; 
	private java.util.Map<String, String> MED1006Map ; 
	private java.util.Map<String, String> AdverseReactionsResultMap ; 
	private java.util.Map<String, String> MEDEVCMap ; 
	private java.util.Map<String, String> MEDSTATUS1 ; 
	private java.util.Map<String, String> MEDSTATUS2 ; 
	private java.util.Map<String, String> NCTRResultMap ;
	private java.util.Map<String, String> ApprovedUnitsMap ;
	private java.util.Map<String, String> MEDPKID ;
	
}
