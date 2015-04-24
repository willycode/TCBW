package com.kangdainfo.tcbw.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.XlsUtil;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Cos7001Db;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;
import com.kangdainfo.tcbw.model.bo.Drg5002Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;
import com.kangdainfo.tcbw.model.bo.Drg6002Db;
import com.kangdainfo.tcbw.model.bo.Drg6003Db;
import com.kangdainfo.tcbw.model.bo.Drg7001Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.model.bo.Med4002Db;
import com.kangdainfo.tcbw.model.bo.Med4003Db;
import com.kangdainfo.tcbw.model.bo.Med4004Db;
import com.kangdainfo.tcbw.model.bo.Med4005Db;
import com.kangdainfo.tcbw.model.bo.Med5001Db;
import com.kangdainfo.tcbw.model.bo.Med5002Db;
import com.kangdainfo.tcbw.model.bo.Med5003Db;
import com.kangdainfo.tcbw.model.bo.Med5004Db;
import com.kangdainfo.tcbw.model.bo.Med5005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.web.listener.MyServletContext;


/**
*<br>程式目的：藥品療效不等資料EXCEL匯入作業 
*<br>程式代號：DRGEX0309F
*<br>程式日期：1021217
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class PopImportExcel extends SuperBean{

String js;
String srcFilePath;
String fromType;
int failedData = 0;

public String getJs() {	return Common.get(js);	}
public void setJs(String js) {this.js = Common.get(js);	}	
public String getSrcFilePath() {return checkGet(srcFilePath);}
public void setSrcFilePath(String s) {this.srcFilePath = checkSet(s);}
public String getFromType() {return checkGet(fromType);}
public void setFromType(String s) {this.fromType = checkSet(s);}

@Override
public Object doQueryOne() throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Object doQueryAll() throws Exception {
	// TODO Auto-generated method stub
	return null;
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
private Map<String,String> dr61NamesMap = new HashMap<String,String>();
{
	dr61NamesMap = new HashMap<String,String>(); 
	dr61NamesMap.put("發生日期"								, "occurDate");
	dr61NamesMap.put("通報者獲知日期"							, "notifierRepDate");
	dr61NamesMap.put("通報來源"								, "notifierSource");
	dr61NamesMap.put("通報來源說明"							, "");
	dr61NamesMap.put("通報者帳號"								, "notifierUserID");
	dr61NamesMap.put("病人識別代號"							, "patientId");
	dr61NamesMap.put("性別"									, "patientSex");
	dr61NamesMap.put("出生日期"								, "patientBirth");
	dr61NamesMap.put("年齡"									, "patientAge");
	dr61NamesMap.put("身高"									, "patientHeight");
	dr61NamesMap.put("體重"									, "patientWeight");
	dr61NamesMap.put("通報事件後果"							, "conSequence");
	dr61NamesMap.put("藥效改變狀況"							, "effectChangeDesc");
	dr61NamesMap.put("不良反應等級"							, "badReactionLev");
	dr61NamesMap.put("不良反應狀況"							, "badReactionDesc");
	dr61NamesMap.put("事件前"								, "beforeDesc");
	dr61NamesMap.put("藥品轉換"								, "changeDesc");
	dr61NamesMap.put("發生事件"								, "occurDesc");
	dr61NamesMap.put("事件後"								, "afterDesc");
	dr61NamesMap.put("其他相關資料"							, "otherDesc");
	dr61NamesMap.put("發生事件後之處置"						, "dealWith");
	dr61NamesMap.put("發生事件後之處置說明"					, "dealWithOther");
	dr61NamesMap.put("病人恢復原藥或轉換同成分藥品其症狀是否改善", "isImproveYn");
	dr61NamesMap.put("醫師對換藥的態度"						, "dressingAttitude");
	dr61NamesMap.put("病人服藥順從性"							, "obedienceLev");

}

private Map<String,String> dr62NamesMap = new HashMap<String,String>();
{
	dr62NamesMap = new HashMap<String,String>(); 
	dr62NamesMap.put("檢驗項目", "testItems");
	dr62NamesMap.put("檢驗數據", "testNum");
	dr62NamesMap.put("檢驗日期", "testDate");
}

private Map<String,String> dr63NamesMap = new HashMap<String,String>();
{
	dr63NamesMap = new HashMap<String,String>(); 
	dr63NamesMap.put("藥品類別", 		"medType");
	dr63NamesMap.put("許可證字", 		"permitKey");
	dr63NamesMap.put("許可證號", 		"permitNo");
	dr63NamesMap.put("學名", 		"scientificName");
	dr63NamesMap.put("商品名", 		"productName");
	dr63NamesMap.put("申請商", 		"applicationName");
	dr63NamesMap.put("製造廠", 		"manufactorName");
	dr63NamesMap.put("批號", 		"manufactorNo");
	dr63NamesMap.put("含量", 		"content");
	dr63NamesMap.put("劑型", 		"medModel");
	dr63NamesMap.put("給藥途徑", 		"medPath");
	dr63NamesMap.put("劑量", 		"dosage");
	dr63NamesMap.put("頻率", 		"frequency");
	dr63NamesMap.put("使用期間(起)", "startDare");
	dr63NamesMap.put("使用期間(訖)", "endDate");
	dr63NamesMap.put("適應症", 		"indication");
}

private Map<String,String> dr51NamesMap = new HashMap<String,String>();
{
	dr51NamesMap = new HashMap<String,String>(); 
	dr51NamesMap.put("發生日期"							 , "occurDate");
	dr51NamesMap.put("通報者獲知日期"						 , "notifierRevDate");
	dr51NamesMap.put("通報來源"							 , "notifierSource");
	dr51NamesMap.put("通報來源說明"						 , "");
	dr51NamesMap.put("通報者帳號"							 , "notifierUserID");
	dr51NamesMap.put("許可證字"							 , "permitKey");
	dr51NamesMap.put("許可證號"							 , "permitNo");
	dr51NamesMap.put("中文品名"							 , "chProduct");
	dr51NamesMap.put("英文品名"							 , "enProduct");
	dr51NamesMap.put("有效成分名稱"						 , "ingredient");
	dr51NamesMap.put("有效成分單位含量"					 , "content");
	dr51NamesMap.put("劑型"								 , "medModel");
	dr51NamesMap.put("劑型說明"							 , "medModelOther");
	dr51NamesMap.put("包裝形式"							 , "medPackage");
	dr51NamesMap.put("包裝形式說明"						 , "medPackageOther");
	dr51NamesMap.put("申請商"							 , "applicationName");
	dr51NamesMap.put("製造廠"							 , "manufactorName");
	dr51NamesMap.put("製造批號"							 , "manufactorNo");
	dr51NamesMap.put("製造日期"							 , "manufactorDate");
	dr51NamesMap.put("保存期限"							 , "expirationDate");
	dr51NamesMap.put("儲存環境"							 , "storage");
	dr51NamesMap.put("儲存環境說明"						 , "storageOther");
	dr51NamesMap.put("本次通報事件是否為單一個案"			 , "isSingleYn");
	dr51NamesMap.put("同批號件數"							 , "sameNum");
	dr51NamesMap.put("不同批號件數"						 , "diffNum");
	dr51NamesMap.put("是否一經拆封即發現本不良品缺陷"		 , "isFindYn");
	dr51NamesMap.put("是否為病人使用後發現不良品向醫療人員反應", "isUsedYn");
	dr51NamesMap.put("是否已對人體健康產生危害"				 , "isHarmYn");
	dr51NamesMap.put("已連絡廠商"							 , "evenContactYn");
	dr51NamesMap.put("後續處理"							 , "dealWith");
	dr51NamesMap.put("是否提供聯絡資訊供廠商後續調查評估"		 , "isContactYn");
	dr51NamesMap.put("不良品原因"							 , "firstResult");
	dr51NamesMap.put("不良品原因說明"						 , "firstRemark");
}

private Map<String,String> dr52NamesMap = new HashMap<String,String>();
{
	dr52NamesMap = new HashMap<String,String>(); 
	dr52NamesMap.put("不良品缺陷大類", "mainCode");
	dr52NamesMap.put("不良品缺陷小類", "subCode");
	dr52NamesMap.put("不良品缺陷說明", "otherDescribe");
}

private Map<String,String> me41NamesMap = new HashMap<String,String>();
{
	me41NamesMap = new HashMap<String,String>(); 
	me41NamesMap.put("發生日期"								  , "occurDate");
	me41NamesMap.put("通報者獲知日期"							  , "notifierRevDate");
	me41NamesMap.put("通報來源"								  , "drugEventsSources");
//	me41NamesMap.put("通報來源說明"							  , "medicalStaffOther");
	me41NamesMap.put("案例來源"								  , "caseSource");
	me41NamesMap.put("國家"									  , "caseSourceOutCountry");
	me41NamesMap.put("報告類別"								  , "reportKind");
	me41NamesMap.put("次數"									  , "trackingNum");
	me41NamesMap.put("矯正措施"								  , "correctiveAction");
	me41NamesMap.put("產品經公告列入藥物安全監視"				  , "drugSafetyMonitoring");
//	me41NamesMap.put("通報者帳號"								  , "notifierUserID");
	me41NamesMap.put("是否願意提供廠商個人聯絡資訊"				  , "isContactYn");
	me41NamesMap.put("病人識別代號"							  , "badReactionPatientCode");
	me41NamesMap.put("性別"									  , "badReactionSex");
	me41NamesMap.put("出生日期"								  , "badReactionBirthday");
	me41NamesMap.put("年齡"									  , "badReactionAge");
	me41NamesMap.put("身高"									  , "badReactionHeight");
	me41NamesMap.put("體重"									  , "badReactionWeight");
	me41NamesMap.put("不良事件類別"							  , "eventKind");
	me41NamesMap.put("品名"									  , "medCname");
	me41NamesMap.put("許可證字"								  , "medPermit");
	me41NamesMap.put("許可證號"								  , "medPermitNumber");
//	me41NamesMap.put("製造廠"								  , "medFactory");
//	me41NamesMap.put("製造廠國別"								  , "medCountry");
//	me41NamesMap.put("許可證申請商"							  , "medPermitFirm");
//	me41NamesMap.put("醫材主類別"								  , "medMainCategoryCode");
//	me41NamesMap.put("醫材次類別"								  , "medSecCategoryCode");
	me41NamesMap.put("型號"									  , "medModel");
	me41NamesMap.put("序號"									  , "medNo");
	me41NamesMap.put("批號"									  , "medLotNum");
	me41NamesMap.put("軟體版本"								  , "medSoftwareVersion");
	me41NamesMap.put("製造日期"								  , "medManufactureDate");
	me41NamesMap.put("有效日期/保存期限"						  , "medEffectiveDate");
	me41NamesMap.put("採購日期"								  , "medPurchaseDate");
	me41NamesMap.put("使用日期"								  , "medUseDate");
	me41NamesMap.put("使用原因"								  , "medUseReason");
	me41NamesMap.put("不良反應結果"							  , "badReactionResults");
	me41NamesMap.put("死亡日期"								  , "badReactionResultsDeathDate");
	me41NamesMap.put("死亡原因"								  , "badReactionResultsDeathReason");
	me41NamesMap.put("非嚴重不良反應說明"						  , "badReactionResultsOther");
	me41NamesMap.put("器材操作者"								  , "medOperator");
	me41NamesMap.put("器材處置現況"							  , "medDisposalStatus");
	me41NamesMap.put("器材使用"								  , "medUse");
	me41NamesMap.put("器材使用說明"							  , "medUseOther");
	me41NamesMap.put("曾使用同類醫材之經驗"					  , "onceUseMed");
	me41NamesMap.put("醫材品名"								  , "onceUseMedName");
	me41NamesMap.put("不良反應敘述"							  , "onceUseMedOther");
	me41NamesMap.put("停用後不良反應是否減輕"                    , "stopMedMitigate");
	me41NamesMap.put("再使用是否出現同樣反應"                    , "sameReaction");
	me41NamesMap.put("不良反應其他資料"						  , "otherRelatedData");
	me41NamesMap.put("不良品缺陷描述"							  , "");
	me41NamesMap.put("不良品其他資料"							  , "");
}

private Map<String,String> me42NamesMap11 = new HashMap<String,String>();
{
	me42NamesMap11.put("不良事件描述1-日期", "occurDate");
	me42NamesMap11.put("不良事件描述1-部位", "position");
	me42NamesMap11.put("不良事件描述1-症狀", "symptom");
	me42NamesMap11.put("不良事件描述1-嚴重程度", "severity");
	me42NamesMap11.put("不良事件描述1-處置", "dealWith");
}

private Map<String,String> me42NamesMap12 = new HashMap<String,String>();
{
	me42NamesMap12.put("不良事件描述2-日期", "occurDate");
	me42NamesMap12.put("不良事件描述2-部位", "position");
	me42NamesMap12.put("不良事件描述2-症狀", "symptom");
	me42NamesMap12.put("不良事件描述2-嚴重程度", "severity");
	me42NamesMap12.put("不良事件描述2-處置", "dealWith");
}

private Map<String,String> me43NamesMap = new HashMap<String,String>();
{
	me43NamesMap = new HashMap<String,String>(); 
	me43NamesMap.put("檢驗項目", "testItems");
	me43NamesMap.put("檢驗數據", "testNum");
	me43NamesMap.put("檢驗日期", "testDate");
}
private Map<String,String> me44NamesMap = new HashMap<String,String>();
{
	me44NamesMap = new HashMap<String,String>(); 
	me44NamesMap.put("品名"			, "cName");
	me44NamesMap.put("許可證字"		, "permit");
	me44NamesMap.put("許可證號"		, "permitNumber");
	me44NamesMap.put("許可證申請商"	, "permitFirm");
	me44NamesMap.put("型號"			, "model");
	me44NamesMap.put("醫材主類別"		, "mainCategory");
	me44NamesMap.put("使用日期"		, "useDate");
	me44NamesMap.put("使用原因"		, "useReason");
}
private Map<String,String> me45NamesMap = new HashMap<String,String>();
{
	me45NamesMap = new HashMap<String,String>(); 
	me45NamesMap.put("品名"			, "cName");
	me45NamesMap.put("含量"			, "content");
	me45NamesMap.put("劑型"			, "formulation");
	me45NamesMap.put("給藥途徑"		, "drgApproach");
	me45NamesMap.put("劑量"			, "dose");
	me45NamesMap.put("頻率"			, "frequency");
	me45NamesMap.put("使用期間(起)"	, "sDate");
	me45NamesMap.put("使用期間(訖)"	, "eDate");
	me45NamesMap.put("使用原因"		, "medCauses");
}

private Map<String,String> me51NamesMap = new HashMap<String,String>();
{
	me51NamesMap.put("發生日期","occurDate");
	me51NamesMap.put("通報者獲知日期","notifierRevDate");
	me51NamesMap.put("通報來源","");
//	me51NamesMap.put("通報來源說明","");
	me51NamesMap.put("案例來源","caseSource");
	me51NamesMap.put("國家","caseSourceOutCountry");
	me51NamesMap.put("試驗醫院","caseSourceInHospital");
	me51NamesMap.put("試驗醫師","caseSourceInDoctor");
	me51NamesMap.put("報告類別","reportKind");
	me51NamesMap.put("次數","trackingNum");
	me51NamesMap.put("試驗名稱","testName");
	me51NamesMap.put("臨床試驗主管機關核准函文號","fdaNum");
	me51NamesMap.put("核准用途","");
	me51NamesMap.put("核准單位","approvedUnits");
	me51NamesMap.put("核准單位說明","approvedUnitsOther");
	me51NamesMap.put("廠商試驗編號","firmTestNo");
//	me51NamesMap.put("通報者帳號","notifierUserID");
	me51NamesMap.put("病人識別代號","patientId");
	me51NamesMap.put("性別","patientSex");
	me51NamesMap.put("出生日期","patientBirth");
	me51NamesMap.put("年齡","patientAge");
	me51NamesMap.put("身高","patientHeigth");
	me51NamesMap.put("體重","patientWeight");
	me51NamesMap.put("不良反應結果","badReactionResults");
	me51NamesMap.put("死亡日期","badReactionResultsDeathDate");
	me51NamesMap.put("死亡原因","badReactionResultsDeathReason");
	me51NamesMap.put("非嚴重不良反應說明","badReactionResultsOther");
	me51NamesMap.put("是否為非預期不良事件","isAdverseEvents");
	//不良事件描述兩組
	//檢驗資料一組
	me51NamesMap.put("其他相關資料","otherDesc");
	me51NamesMap.put("試驗醫材名稱","medTestMedical");
	me51NamesMap.put("許可證字","medPermit");
	me51NamesMap.put("許可證號","medPermitNumber");
	me51NamesMap.put("製造廠","medFactory");
	me51NamesMap.put("供應商","medSupplier");
//	me51NamesMap.put("醫材主類別","medMainCategory");
//	me51NamesMap.put("醫材次類別","medSecCategory");
	me51NamesMap.put("型號","medModel");
	me51NamesMap.put("序號","medNo");
	me51NamesMap.put("批號","medLotNum");
	me51NamesMap.put("製造日期","medManufactureDate");
	me51NamesMap.put("使用日期","medUseDate");
	me51NamesMap.put("停用日期","medUseDate");
	me51NamesMap.put("使用原因","medUseReason");
	me51NamesMap.put("器材操作者","medOperator");
	me51NamesMap.put("是否可提供器材作評估","medUseIsYn");
	me51NamesMap.put("取得來源","medYesSoruce");
	me51NamesMap.put("歸還日期","medNoReturnDate");
	me51NamesMap.put("曾使用同類醫材之經驗","medOnceUseMed");
	me51NamesMap.put("同類醫材品名稱","medOnceUseMedName");
	me51NamesMap.put("不良反應敘述","medOnceUseBadReaction");
	me51NamesMap.put("停用後不良反應是否減輕","medStopMedMitigate");
	me51NamesMap.put("再使用是否出現同樣反應","onceSameReaction");
	me51NamesMap.put("是否同時併用其他藥品/食品","sameTimeUse");
	me51NamesMap.put("併用藥品/食品說明","sameTimeUseOther");
	me51NamesMap.put("試驗醫師評估醫材與SAE之因果關係","medSea");
	me51NamesMap.put("驗醫師評估手續程序與SAE之因果關係","procedureSea");
//	me51NamesMap.put("立即通知試驗委託者","noticeSponsor");
//	me51NamesMap.put("提供詳細書面報告","noticeSponsorWritten");
//	me51NamesMap.put("立即通知人體試驗委員會","noticeIRB");
//	me51NamesMap.put("提供詳細書面報告","noticeIRBWritten");
//	me51NamesMap.put("立即通知試驗核准單位","noticeApprovedUnits");
//	me51NamesMap.put("提供詳細書面報告","noticeApprovedUnitsWritten");

}

private Map<String,String> me52NamesMap = new HashMap<String,String>();
{
	me52NamesMap.put("不良事件描述1-日期", "bulletinDate");
	me52NamesMap.put("不良事件描述1-部位", "position");
	me52NamesMap.put("不良事件描述1-症狀", "symptom");
	me52NamesMap.put("不良事件描述1-嚴重程度", "severity");
	me52NamesMap.put("不良事件描述1-處置", "dealWith");
}
private Map<String,String> me52NamesMap2 = new HashMap<String,String>();
{
	me52NamesMap2.put("不良事件描述2-日期", "bulletinDate");
	me52NamesMap2.put("不良事件描述2-部位", "position");
	me52NamesMap2.put("不良事件描述2-症狀", "symptom");
	me52NamesMap2.put("不良事件描述2-嚴重程度", "severity");
	me52NamesMap2.put("不良事件描述2-處置", "dealWith");
}

private Map<String,String> me53NamesMap = new HashMap<String,String>();
{
	me53NamesMap = new HashMap<String,String>(); 
	me53NamesMap.put("檢驗項目", "testItems");
	me53NamesMap.put("檢驗數據", "testNum");
	me53NamesMap.put("檢驗日期", "testDate");
}

private Map<String,String> me54NamesMap = new HashMap<String,String>();
{
	me54NamesMap = new HashMap<String,String>(); 
	me54NamesMap.put("品名"			, "cName");
	me54NamesMap.put("許可證字"		, "permit");
	me54NamesMap.put("許可證號"		, "permitNumber");
	me54NamesMap.put("許可證申請商"	, "permitFirm");
	me54NamesMap.put("型號"			, "model");
	me54NamesMap.put("醫材主類別"		, "mainCategory");
	me54NamesMap.put("使用日期"		, "useDate");
	me54NamesMap.put("使用原因"		, "useReason");
}

private Map<String,String> me55NamesMap = new HashMap<String,String>();
{
	me55NamesMap = new HashMap<String,String>(); 
	me55NamesMap.put("品名"			, "cName");
	me55NamesMap.put("含量"			, "content");
	me55NamesMap.put("劑型"			, "formulation");
	me55NamesMap.put("給藥途徑"		, "drgApproach");
	me55NamesMap.put("劑量"			, "dose");
	me55NamesMap.put("頻率"			, "frequency");
	me55NamesMap.put("使用期間(起)"	, "sDate");
	me55NamesMap.put("使用期間(訖)"	, "eDate");
	me55NamesMap.put("使用原因"		, "medCauses");
}

private Map<String,String> dr71NamesMap = new HashMap<String,String>();
{
	dr71NamesMap = new HashMap<String,String>(); 
	dr71NamesMap.put("資料收集日期"		,"dataRevDate");
	dr71NamesMap.put("發佈單位"			,"publDept");
	dr71NamesMap.put("是否為轉發"			,"istransfer");
	dr71NamesMap.put("轉發單位"			,"transferdept");
	dr71NamesMap.put("資料來源"			,"datasourWebSite");
	dr71NamesMap.put("狀況"				,"situation");
	dr71NamesMap.put("原始發佈日期"		,"publDate");
	dr71NamesMap.put("訊息回收品項數"		,"recycleNum");
	dr71NamesMap.put("商品名"			,"chProduct");
	dr71NamesMap.put("學名"				,"scientificName");
	dr71NamesMap.put("劑型"				,"warningmedModel");
	dr71NamesMap.put("廠商"				,"druggist");
	dr71NamesMap.put("製造廠"			,"manufactorName");
	dr71NamesMap.put("產品批號"			,"lotNo");
	dr71NamesMap.put("事件簡述"			,"eventDesc");
	dr71NamesMap.put("品質異常型態"		,"qualitywarningtype");
	dr71NamesMap.put("回收型態"			,"recycleType");
	dr71NamesMap.put("適應症"			,"indication");
	dr71NamesMap.put("是否草擬紅綠燈初稿"	,"iswarning");
	dr71NamesMap.put("警訊備註"			,"warningremark");

}

private Map<String,String> co71NamesMap = new HashMap<String,String>();
{
	co71NamesMap = new HashMap<String,String>(); 
	co71NamesMap.put("案件編號"		,"applNo");
	co71NamesMap.put("案件狀態"			,"status");
	co71NamesMap.put("品名"			,"chProduct");
	co71NamesMap.put("資料收集日期"			,"dataRevDate");
	co71NamesMap.put("發佈單位"			,"publDept");
	co71NamesMap.put("發佈單位國家"				,"publCountry");
	co71NamesMap.put("產地"		,"manufactorCountry");
	co71NamesMap.put("化粧品項目"		,"ingredient");
	co71NamesMap.put("品牌/廠商"			,"brand");
	co71NamesMap.put("狀況"				,"situation");
	co71NamesMap.put("發佈日期"				,"publDate");
	co71NamesMap.put("訊息主題"				,"subjecttype");
	co71NamesMap.put("資訊內容摘要"			,"contextsummary");
	co71NamesMap.put("產品批號"			,"lotNo");
	co71NamesMap.put("資料來源"			,"datasourWebSite");
}


public void doImportProcess() {
	try {
		StringBuilder msg = new StringBuilder();
		int insertCount = 0;
		if (!"".equals(getSrcFilePath())) {
			String[] arrFileName=getSrcFilePath().split(":;:");
			if (arrFileName.length>1) {
				java.io.File parentFile = new java.io.File(MyServletContext.getInstance().getServletContext().getInitParameter("filestoreLocation")+java.io.File.separator+arrFileName[0]);
				java.io.File srcFile = new java.io.File(parentFile,arrFileName[1]);					
				XlsUtil xlsUtil = new XlsUtil();
				Map<String,List<Map<String,String>>> drgMap = xlsUtil.getMasterDetailExcel(srcFile.getPath());
				if("DRGEX0101".equals(getFromType())){			//藥品不良品資料轉入作業;
					insertCount = doImportDRGEX0101(drgMap);
				}else if("DRGEX0301".equals(getFromType())){	//藥品療效不等資料轉入作業
					insertCount = doImportDRGEX0301(drgMap);
				}else if("MEDEX0101".equals(getFromType())){	//醫療器材不良事件資料轉入作業
					insertCount = doImportMEDEX0101(drgMap);
				}else if("MEDEX5101".equals(getFromType())){	//醫療器材臨床試驗不良事件資料轉入作業
					insertCount = doImportMEDEX5101(drgMap);
				}else if("VDRG0301".equals(getFromType())){		//國內外藥品品質警訊資料轉入作業
					insertCount = doImportVDRG0301(drgMap);
				}else if("VCOS0101".equals(getFromType())){		//國內外化粧品品質警訊資料轉入作業
					insertCount = doImportVCOS0101(drgMap);
				}
				Common.RemoveDirectory(parentFile);
			}
		}
		if(getState().contains("有誤")) {
			msg.append("新增筆數：共　").append(insertCount).append("　筆<br>");
			msg.append("失敗筆數：共　").append(failedData).append("　筆<br>");
			msg.append(getState());
			setState("doTransferSuccess");
			this.setJs("<br><br>資料匯入成功，其結果如下：<br>" + msg.toString());
		} else {
		setState("doTransferSuccess");	
		msg.append("新增筆數：共　").append(insertCount).append("　筆<br>");
		msg.append("失敗筆數：共　0　筆<br>");
		this.setJs("<br><br>資料匯入成功，其結果如下：<br>" + msg.toString());
		}
	} catch (Exception e) {
		e.printStackTrace();
		setState("doTransferFail");	
		this.setJs("<br><br>資料匯入失敗，請洽詢系統管理者或相關承辦人員！<br>錯誤訊息如下：<br>" + " : " + e.getMessage());	
	}
}


public int doImportDRGEX0301(Map<String,List<Map<String,String>>> drgMap){
	int insertCount = 0;
	try {
		if(null != drgMap && !drgMap.isEmpty()){
			Map<String, Drg6001Db> drg61Map = new HashMap<String, Drg6001Db>();
			//1.先找出master的資料
			List<Map<String,String>> dataMapList = drgMap.get("master");
			//1.1依對應的名稱找出欄位
			for(Map<String,String> dataMap:dataMapList){
				Drg6001Db drg61 = new Drg6001Db();
				drg61.setCreator(TCBWCommon.getCurrentUserId());
				drg61.setStatus("00");
				drg61.setRevision("01");
				drg61.setCreator(TCBWCommon.getCurrentUserId());
				drg61.setCreateDate(Datetime.getYYYMMDD());
				drg61.setCreateTime(Datetime.getHHMMSS());
				for(String fieldName:dataMap.keySet()){
					if(null != dr61NamesMap.get(fieldName) && !"".equals(dr61NamesMap.get(fieldName))){
						BeanUtils.setProperty(drg61, dr61NamesMap.get(fieldName), dataMap.get(fieldName));
					}
					if("notifierUserID".equals(dr61NamesMap.get(fieldName))){
						//1.2 寫入通報者基本資料
						CommonUser user = ServiceGetter.getInstance().getCommonService().getCommonUserDao().getCommonUserByUserId(drg61.getNotifierUserID());
						if(null != user){
							drg61.setNotifierName(user.getUserName());
							drg61.setNotifierDept(user.getUserJob());
							drg61.setNotifierTel(user.getUserTel());
							drg61.setNotifierEmail(user.getUserEmail());
							drg61.setNotifierPhone(user.getUserMobile());
							drg61.setNotifierFax(user.getUserFax());
							drg61.setNotifierAddress(user.getUserAddr());
							drg61.setNotifierTitle(user.getJobTitle());
							drg61.setNotifierType(user.getCommonDepartment().getDepartmentCode());
						}
					}
				}
				drg61Map.put(dataMap.get("NO"), drg61);
			}
			
			//2.寫入DRG6002Db的資料
			List<Map<String,String>> data1MapList = drgMap.get("detail1");
			if(null != data1MapList && !data1MapList.isEmpty()){
				//2.1 依對應的名稱找出欄位
				for(Map<String,String> data1Map:data1MapList){
					Drg6001Db drg61 = drg61Map.get(data1Map.get("NO"));
					if(null != drg61){
						java.util.Set dtlSet = new ListOrderedSet();
						if(null != drg61.getDrg6002Dbs() && !drg61.getDrg6002Dbs().isEmpty()){
							dtlSet.addAll(drg61.getDrg6002Dbs());
						}
						Drg6002Db drg62 = new Drg6002Db();
						drg62.setDrg6001Db(drg61);
						drg62.setCreator(TCBWCommon.getCurrentUserId());
						drg62.setCreateDate(Datetime.getYYYMMDD());
						drg62.setCreateTime(Datetime.getHHMMSS());
						for(String fieldName:data1Map.keySet()){
							if(null != dr62NamesMap.get(fieldName) && !"".equals(dr62NamesMap.get(fieldName))){
								BeanUtils.setProperty(drg62, dr62NamesMap.get(fieldName), data1Map.get(fieldName));
							}
						}
						dtlSet.add(drg62);
						drg61.setDrg6002Dbs(dtlSet);
						drg61Map.put(data1Map.get("NO"), drg61);
					}
					
				}
			}
			
			//3.寫入DRG6003Db的資料
			List<Map<String,String>> data2MapList = drgMap.get("detail2");
			if(null != data2MapList && !data2MapList.isEmpty()){
				//3.1 依對應的名稱找出欄位
				for(Map<String,String> data2Map:data2MapList){
					Drg6001Db drg61 = drg61Map.get(data2Map.get("NO"));
					if(null != drg61){
						java.util.Set dtlSet = new ListOrderedSet();
						if(null != drg61.getDrg6003Dbs() && !drg61.getDrg6003Dbs().isEmpty()){
							dtlSet.addAll(drg61.getDrg6003Dbs());
						}
						Drg6003Db drg63 = new Drg6003Db();
						drg63.setDrg6001Db(drg61);
						drg63.setCreator(TCBWCommon.getCurrentUserId());
						drg63.setCreateDate(Datetime.getYYYMMDD());
						drg63.setCreateTime(Datetime.getHHMMSS());
						for(String fieldName:data2Map.keySet()){
							if(null != dr63NamesMap.get(fieldName) && !"".equals(dr63NamesMap.get(fieldName))){
								BeanUtils.setProperty(drg63, dr63NamesMap.get(fieldName), data2Map.get(fieldName));
							}
						}
						dtlSet.add(drg63);
						drg61.setDrg6003Dbs(dtlSet);
						drg61Map.put(data2Map.get("NO"), drg61);
					}						
				}
			}
			
			//4. 批次寫入db
			if(null != drg61Map && !drg61Map.isEmpty()){
				for(String no:drg61Map.keySet()){
					Drg6001Db drg61 = drg61Map.get(no);
					ServiceGetter.getInstance().getTcbwService().save(drg61);
					insertCount++;
				}
			}
		}
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return insertCount;
}

public int doImportDRGEX0101(Map<String,List<Map<String,String>>> drgMap){
	int insertCount = 0;
	try {
		if(null != drgMap && !drgMap.isEmpty()){
			Map<String, Drg5001Db> drg51Map = new HashMap<String, Drg5001Db>();
			//1.先找出master的資料
			List<Map<String,String>> dataMapList = drgMap.get("master");
			//1.1依對應的名稱找出欄位
			for(Map<String,String> dataMap:dataMapList){
				Drg5001Db drg51 = new Drg5001Db();
				drg51.setCreator(TCBWCommon.getCurrentUserId());
				drg51.setStatus("00");
				drg51.setRevision("1");
				drg51.setCreator(TCBWCommon.getCurrentUserId());
				drg51.setCreateDate(Datetime.getYYYMMDD());
				drg51.setCreateTime(Datetime.getHHMMSS());
				for(String fieldName:dataMap.keySet()){
					if(null != dr51NamesMap.get(fieldName) && !"".equals(dr51NamesMap.get(fieldName))){
						BeanUtils.setProperty(drg51, dr51NamesMap.get(fieldName), dataMap.get(fieldName));
					}
					if("notifierUserID".equals(dr51NamesMap.get(fieldName))){
						//1.2 寫入通報者基本資料
						CommonUser user = ServiceGetter.getInstance().getCommonService().getCommonUserDao().getCommonUserByUserId(drg51.getNotifierUserID());
						if(null != user){
							drg51.setNotifierName(Common.get(user.getUserName()));
							drg51.setNotifierDeptID(Common.get(user.getUserJob()));		
							drg51.setNotifierDept(Common.get(TCBWCommon.getNotifierDeptName(user.getCommonDepartment().getDepartmentCode(),user.getUserJob())));
							drg51.setNotifierTelArea(Common.get(user.getUserTelArea()));
							drg51.setNotifierTel(Common.get(user.getUserTel()));
							drg51.setNotifierTelExt(Common.get(user.getUserTelExt()));
							drg51.setNotifierCounty(Common.get(user.getUserCounty()));
							drg51.setNotifierZipCode(Common.get(user.getUserZipCode()));
							drg51.setNotifierAddress(Common.get(user.getUserAddr()));
							drg51.setNotifierEmail(Common.get(user.getUserEmail()));
							drg51.setNotifierType(Common.get(user.getCommonDepartment().getDepartmentCode()));
							drg51.setNotifierTitle(Common.get(user.getJobTitle()));
						}
					}
				}
				drg51Map.put(dataMap.get("NO"), drg51);
			}
			
			//2.寫入DRG5002Db的資料
			List<Map<String,String>> data1MapList = drgMap.get("detail1");
			if(null != data1MapList && !data1MapList.isEmpty()){
				//2.1 依對應的名稱找出欄位
				for(Map<String,String> data1Map:data1MapList){
					Drg5001Db drg51 = drg51Map.get(data1Map.get("NO"));
					if(null != drg51){
						java.util.Set dtlSet = new ListOrderedSet();
						if(null != drg51.getDrg5002Dbs() && !drg51.getDrg5002Dbs().isEmpty()){
							dtlSet.addAll(drg51.getDrg5002Dbs());
						}
						Drg5002Db drg52 = new Drg5002Db();
						drg52.setDrg5001Db(drg51);
						drg52.setCreator(TCBWCommon.getCurrentUserId());
						drg52.setCreateDate(Datetime.getYYYMMDD());
						drg52.setCreateTime(Datetime.getHHMMSS());
						for(String fieldName:data1Map.keySet()){
							if(null != dr52NamesMap.get(fieldName) && !"".equals(dr52NamesMap.get(fieldName))){
								BeanUtils.setProperty(drg52, dr52NamesMap.get(fieldName), data1Map.get(fieldName));
							}
						}
						dtlSet.add(drg52);
						drg51.setDrg5002Dbs(dtlSet);
						drg51Map.put(data1Map.get("NO"), drg51);
					}
					
				}
			}
			
			//3. 批次寫入db
			if(null != drg51Map && !drg51Map.isEmpty()){
				for(String no:drg51Map.keySet()){
					Drg5001Db drg51 = drg51Map.get(no);
					ServiceGetter.getInstance().getTcbwService().save(drg51);
					insertCount++;
				}
			}
		}
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return insertCount;
}

public int doImportMEDEX0101(Map<String,List<Map<String,String>>> drgMap){
	int insertCount = 0;
	try {
		if(null != drgMap && !drgMap.isEmpty()){
			Map<String, Med4001Db> med41Map = new HashMap<String, Med4001Db>();
			//1.先找出master的資料
			List<Map<String,String>> dataMapList = drgMap.get("master");
			//1.1依對應的名稱找出欄位
			for(Map<String,String> dataMap:dataMapList){
				Med4001Db med41 = new Med4001Db();
				
				med41.setCreator(TCBWCommon.getCurrentUserId());
				med41.setStatus("00");
				med41.setRevision("01");
				CommonUser currentUser = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
				if("out".equals(currentUser.getInORout())){
					med41.setInOrOutcreator(currentUser.getUserId());
				}else if("in".equals(currentUser.getInORout())){
					med41.setCreator(currentUser.getUserId());
				}
				med41.setInOrOutcreator(TCBWCommon.getCurrentUserId());
				med41.setCreateDate(Datetime.getYYYMMDD());
				med41.setCreateTime(Datetime.getHHMMSS());

				
				for(String fieldName:dataMap.keySet()){

					if(null != me41NamesMap.get(fieldName) && !"".equals(me41NamesMap.get(fieldName))){
						BeanUtils.setProperty(med41, me41NamesMap.get(fieldName), dataMap.get(fieldName));
					}
//					if("notifierUserID".equals(me41NamesMap.get(fieldName))){
						//1.2 寫入通報者基本資料
					CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();

//						CommonUser user = ServiceGetter.getInstance().getCommonService().getCommonUserDao().getCommonUserByUserId(dataMap.get(fieldName));
						if(null != c){
							med41.setNotifierName(c.getUserName());
							med41.setNotifierAreaCode(c.getUserTelArea());
							med41.setNotifierTel(c.getUserTel());
							med41.setNotifierTelExt(c.getUserTelExt());
							med41.setNotifierEamil(c.getUserEmail());
							med41.setNotifierAddress(c.getUserAddr());
							med41.setNotifierCounty(c.getUserCounty());
							med41.setNotifierZipCode(c.getUserZipCode());
							med41.setNotifierType(c.getCommonDepartment()!=null?Common.get(c.getCommonDepartment().getShortCode()):"");
						}
//					}
				}
				//依許可證字號自動帶出其他資訊，醫材主類別、次類別為尋找醫材不良事件/醫材臨床不良事件最新一筆案件的醫材主/次類別，可參考jsonMedPermitObjects.jsp
				if(!"".equals(dataMap.get("許可證字")) && !"".equals(dataMap.get("許可證號"))) {
					String hql=" select distinct a.CHNAME,a.APPUNNO,a.APPNAME,a.FACTCIDMA,";
					hql += " a.FACTNAME,b.DOCKNDID,b.DOCKNDMA,b.MSKNDID,b.MSKNDMA,a.FACTCIDMA ";
					hql += " from VW_ForADR_TBMLIC a ,VW_ForADR_BIGKND2 b  ";
					hql += " where a.LICEKID+a.LICID1=b.LICID ";
				    hql += " and a.LICEKID=" + Common.sqlChar(dataMap.get("許可證字"));
				    hql += "and a.LICID1=" + Common.sqlChar(dataMap.get("許可證號"));
				    List<Map<String,Object>> objList = PersistenceServiceGetter.getInstance().getJdbcTemplate().queryForList(hql);
				        
				    String sql="select TOP 1 * from";
					sql +="(";
					sql +=" select a.applNo,a.medPermit,a.medPermitNumber,a.medMainCategoryCode,a.medMainCategory,a.medSecCategoryCode,a.medSecCategory,a.notifierRepDate";
					sql +=" from MED0001_DB a union";
					sql +=" select b.applNo,b.medPermit,b.medPermitNumber,b.medMainCategoryCode,b.medMainCategory,b.medSecCategoryCode,b.medSecCategory,b.notifierRepDate";
					sql +=" from MED2001_DB b";
					sql +=" ) as temp";
					sql +=" where medPermit = " + Common.sqlChar(dataMap.get("許可證字"));
					sql +=" and medPermitNumber = " + Common.sqlChar(dataMap.get("許可證號"));
					sql +=" and ISnull(applNo,'') <> ''";
					sql +=" order by notifierRepDate desc";
					List<Map<String,Object>> objList2 = PersistenceServiceGetter.getInstance().getJdbcTemplate().queryForList(sql);

				   	if(objList!=null && objList.size()>0) {
				   		for (Map<String, Object> result : objList){	
				   			med41.setMedCname((null != result.get("CHNAME"))?Common.get(result.get("CHNAME")):"");//中文品名
				   			med41.setMedPermitFirmCode((null != result.get("APPUNNO"))?Common.get(result.get("APPUNNO")):"");//許可證申請商代碼
				   			med41.setMedPermitFirm((null != result.get("APPNAME"))?Common.get(result.get("APPNAME")):"");//許可證申請商
				   			med41.setMedFactory((null != result.get("FACTNAME"))?Common.get(result.get("FACTNAME")):"");//製造廠
				   			med41.setMedCountry((null != result.get("FACTCIDMA"))?Common.get(result.get("FACTCIDMA")):"");//製造廠國別
				   			med41.setMedMainCategoryCode((null != result.get("DOCKNDID"))?Common.get(result.get("DOCKNDID")):"");//醫材主類別代碼
				   			med41.setMedMainCategory((null != result.get("DOCKNDMA"))?Common.get(result.get("DOCKNDMA")):"");//醫材主類別
				   			med41.setMedSecCategoryCode((null != result.get("MSKNDID"))?Common.get(result.get("MSKNDID")):"");//醫材次類別代碼
				   			med41.setMedSecCategory((null != result.get("MSKNDMA"))?Common.get(result.get("MSKNDMA")):"");//醫材次類別
				   			if(objList2!=null && objList2.size()>0) {
				   				for(Map<String, Object> result2 : objList2) {
				   					med41.setMedMainCategoryCode((null != result2.get("medMainCategoryCode"))?Common.get(result2.get("medMainCategoryCode")):"");//醫材主類別代碼
					   				med41.setMedMainCategory((null != result2.get("medMainCategory"))?Common.get(result2.get("medMainCategory")):"");//醫材主類別
					   				med41.setMedSecCategoryCode((null != result2.get("medSecCategoryCode"))?Common.get(result2.get("medSecCategoryCode")):"");//醫材次類別代碼
					   				med41.setMedSecCategory((null != result2.get("medSecCategory"))?Common.get(result2.get("medSecCategory")):"");//醫材次類別
				   				}
				   			}
				   		}
				   	}
			}
				//依匯入之EXCEL「產品問題大類」區分資料
				String p1="",p2="",p3="",p4="",p5="";
				if(null != dataMap.get("產品問題大類1")) {
					if("01".equals(dataMap.get("產品問題大類1")) && (!"".equals(dataMap.get("產品問題小類1")) || null != dataMap.get("產品問題小類1"))) {
						if(!"".equals(p1)) {
							p1 += ",";
						}
						p1 += dataMap.get("產品問題小類1");
					} else if("02".equals(dataMap.get("產品問題大類1")) && (!"".equals(dataMap.get("產品問題小類1")) || null != dataMap.get("產品問題小類1"))) {
						if(!"".equals(p2)) {
							p2 += ",";
						}
						p2 += dataMap.get("產品問題小類1");
					} else if("03".equals(dataMap.get("產品問題大類1")) && (!"".equals(dataMap.get("產品問題小類1")) || null != dataMap.get("產品問題小類1"))) {
						if(!"".equals(p3)) {
							p3 += ",";
						}
						p3 += dataMap.get("產品問題小類1");
					} else if("04".equals(dataMap.get("產品問題大類1")) && (!"".equals(dataMap.get("產品問題小類1")) || null != dataMap.get("產品問題小類1"))) {
						if(!"".equals(p4)) {
							p4 += ",";
						}
						p4 += dataMap.get("產品問題小類1");
					} else if("05".equals(dataMap.get("產品問題大類1")) && (!"".equals(dataMap.get("產品問題小類1")) || null != dataMap.get("產品問題小類1"))) {
						if(!"".equals(p5)) {
							p5 += ",";
						}
						p5 += dataMap.get("產品問題小類1");
					}
				} 
				if(null != dataMap.get("產品問題大類2")) {
					if("01".equals(dataMap.get("產品問題大類2")) && (!"".equals(dataMap.get("產品問題小類2")) || null != dataMap.get("產品問題小類2"))) {
						if(!"".equals(p1)) {
							p1 += ",";
						}
						p1 += dataMap.get("產品問題小類2");
					} else if("02".equals(dataMap.get("產品問題大類2")) && (!"".equals(dataMap.get("產品問題小類2")) || null != dataMap.get("產品問題小類2"))) {
						if(!"".equals(p2)) {
							p2 += ",";
						}
						p2 += dataMap.get("產品問題小類2");
					} else if("03".equals(dataMap.get("產品問題大類2")) && (!"".equals(dataMap.get("產品問題小類2")) || null != dataMap.get("產品問題小類2"))) {
						if(!"".equals(p3)) {
							p3 += ",";
						}
						p3 += dataMap.get("產品問題小類2");
					} else if("04".equals(dataMap.get("產品問題大類2")) && (!"".equals(dataMap.get("產品問題小類2")) || null != dataMap.get("產品問題小類2"))) {
						if(!"".equals(p4)) {
							p4 += ",";
						}
						p4 += dataMap.get("產品問題小類2");
					} else if("05".equals(dataMap.get("產品問題大類2")) && (!"".equals(dataMap.get("產品問題小類2")) || null != dataMap.get("產品問題小類2"))) {
						if(!"".equals(p5)) {
							p5 += ",";
						}
						p5 += dataMap.get("產品問題小類2");
					}
				} 
				if(null != dataMap.get("產品問題大類3")) {
					if("01".equals(dataMap.get("產品問題大類3")) && (!"".equals(dataMap.get("產品問題小類3")) || null != dataMap.get("產品問題小類3"))) {
						if(!"".equals(p1)) {
							p1 += ",";
						}
						p1 += dataMap.get("產品問題小類3");
					} else if("02".equals(dataMap.get("產品問題大類3")) && (!"".equals(dataMap.get("產品問題小類3")) || null != dataMap.get("產品問題小類3"))) {
						if(!"".equals(p2)) {
							p2 += ",";
						}
						p2 += dataMap.get("產品問題小類3");
					} else if("03".equals(dataMap.get("產品問題大類3")) && (!"".equals(dataMap.get("產品問題小類3")) || null != dataMap.get("產品問題小類3"))) {
						if(!"".equals(p3)) {
							p3 += ",";
						}
						p3 += dataMap.get("產品問題小類3");
					} else if("04".equals(dataMap.get("產品問題大類3")) && (!"".equals(dataMap.get("產品問題小類3")) || null != dataMap.get("產品問題小類3"))) {
						if(!"".equals(p4)) {
							p4 += ",";
						}
						p4 += dataMap.get("產品問題小類3");
					} else if("05".equals(dataMap.get("產品問題大類3")) && (!"".equals(dataMap.get("產品問題小類3")) || null != dataMap.get("產品問題小類3"))) {
						if(!"".equals(p5)) {
							p5 += ",";
						}
						p5 += dataMap.get("產品問題小類3");
					}
				} 
				if(null != dataMap.get("產品問題大類4")) {
					if("01".equals(dataMap.get("產品問題大類4")) && (!"".equals(dataMap.get("產品問題小類4")) || null != dataMap.get("產品問題小類4"))) {
						if(!"".equals(p1)) {
							p1 += ",";
						}
						p1 += dataMap.get("產品問題小類4");
					} else if("02".equals(dataMap.get("產品問題大類4")) && (!"".equals(dataMap.get("產品問題小類4")) || null != dataMap.get("產品問題小類4"))) {
						if(!"".equals(p2)) {
							p2 += ",";
						}
						p2 += dataMap.get("產品問題小類4");
					} else if("03".equals(dataMap.get("產品問題大類4")) && (!"".equals(dataMap.get("產品問題小類4")) || null != dataMap.get("產品問題小類4"))) {
						if(!"".equals(p3)) {
							p3 += ",";
						}
						p3 += dataMap.get("產品問題小類4");
					} else if("04".equals(dataMap.get("產品問題大類4")) && (!"".equals(dataMap.get("產品問題小類4")) || null != dataMap.get("產品問題小類4"))) {
						if(!"".equals(p4)) {
							p4 += ",";
						}
						p4 += dataMap.get("產品問題小類4");
					} else if("05".equals(dataMap.get("產品問題大類4")) && (!"".equals(dataMap.get("產品問題小類4")) || null != dataMap.get("產品問題小類4"))) {
						if(!"".equals(p5)) {
							p5 += ",";
						}
						p5 += dataMap.get("產品問題小類4");
					}
				} 
				if(null != dataMap.get("產品問題大類5")) {
					if("01".equals(dataMap.get("產品問題大類5")) && (!"".equals(dataMap.get("產品問題小類5")) || null != dataMap.get("產品問題小類5"))) {
						if(!"".equals(p1)) {
							p1 += ",";
						}
						p1 += dataMap.get("產品問題小類5");
					} else if("02".equals(dataMap.get("產品問題大類5")) && (!"".equals(dataMap.get("產品問題小類5")) || null != dataMap.get("產品問題小類5"))) {
						if(!"".equals(p2)) {
							p2 += ",";
						}
						p2 += dataMap.get("產品問題小類5");
					} else if("03".equals(dataMap.get("產品問題大類5")) && (!"".equals(dataMap.get("產品問題小類5")) || null != dataMap.get("產品問題小類5"))) {
						if(!"".equals(p3)) {
							p3 += ",";
						}
						p3 += dataMap.get("產品問題小類5");
					} else if("04".equals(dataMap.get("產品問題大類5")) && (!"".equals(dataMap.get("產品問題小類5")) || null != dataMap.get("產品問題小類5"))) {
						if(!"".equals(p4)) {
							p4 += ",";
						}
						p4 += dataMap.get("產品問題小類5");
					} else if("05".equals(dataMap.get("產品問題大類5")) && (!"".equals(dataMap.get("產品問題小類5")) || null != dataMap.get("產品問題小類5"))) {
						if(!"".equals(p5)) {
							p5 += ",";
						}
						p5 += dataMap.get("產品問題小類5");
					}
				}
				//依大類代碼區分寫入不同欄位
				if(!"".equals(p1) && p1.length() > 0) {
					med41.setProductProblemKind1(p1);
				}
				if(!"".equals(p2) && p2.length() > 0) {
					med41.setProductProblemKind2(p2);
				}
				if(!"".equals(p3) && p3.length() > 0) {
					med41.setProductProblemKind3(p3);
				}
				if(!"".equals(p4) && p4.length() > 0) {
					med41.setProductProblemKind4(p4);
				}
				if(!"".equals(p5) && p5.length() > 0) {
					med41.setProductProblemKindOther(p5);
				}

				med41Map.put(dataMap.get("NO"), med41);
				
			}
			//2.寫入Med4002Db的資料
			List<Map<String,String>> data1MapList = drgMap.get("master");
			if(null != data1MapList && !data1MapList.isEmpty()){
				//2.1 依對應的名稱找出欄位
				for(Map<String,String> data1Map:data1MapList){
					Med4001Db med41 = med41Map.get(data1Map.get("NO"));
					if(null != med41){
						java.util.Set dtlSet = new ListOrderedSet();
//						if(null != med41.getMed4002Dbs() && !med41.getMed4002Dbs().isEmpty()){
//							dtlSet.addAll(med41.getMed4002Dbs());
//						}
						if(null != data1Map.get("不良事件描述1-日期") && !"".equals(data1Map.get("不良事件描述1-日期"))) {
							Med4002Db med42 = new Med4002Db();
							med42.setMed4001Db(med41);
							med42.setCreator(TCBWCommon.getCurrentUserId());
							med42.setCreateDate(Datetime.getYYYMMDD());
							med42.setCreateTime(Datetime.getHHMMSS());
							for(String fieldName:data1Map.keySet()){
								if(null != me42NamesMap11.get(fieldName) && !"".equals(me42NamesMap11.get(fieldName))){
									BeanUtils.setProperty(med42, me42NamesMap11.get(fieldName), data1Map.get(fieldName));
								}
							}
							dtlSet.add(med42);
							med41.setMed4002Dbs(dtlSet);
							med41Map.put(data1Map.get("NO"), med41);
						}
						if(null != data1Map.get("不良事件描述2-日期") && !"".equals(data1Map.get("不良事件描述2-日期"))) {
							Med4002Db med42 = new Med4002Db();
							med42.setMed4001Db(med41);
							med42.setCreator(TCBWCommon.getCurrentUserId());
							med42.setCreateDate(Datetime.getYYYMMDD());
							med42.setCreateTime(Datetime.getHHMMSS());
							for(String fieldName:data1Map.keySet()){
								if(null != me42NamesMap12.get(fieldName) && !"".equals(me42NamesMap12.get(fieldName))){
									BeanUtils.setProperty(med42, me42NamesMap12.get(fieldName), data1Map.get(fieldName));
								}
							}
							dtlSet.add(med42);
							med41.setMed4002Dbs(dtlSet);
							med41Map.put(data1Map.get("NO"), med41);
						}
					}
					
				}
			}
			
			//3.寫入Med4003Db的資料
			List<Map<String,String>> data2MapList = drgMap.get("master");
			if(null != data2MapList && !data2MapList.isEmpty()){
				//3.1 依對應的名稱找出欄位
				for(Map<String,String> data2Map:data2MapList){
					Med4001Db med41 = med41Map.get(data2Map.get("NO"));
					if(null != med41){
						java.util.Set dtlSet = new ListOrderedSet();
						if(null != med41.getMed4003Dbs() && !med41.getMed4003Dbs().isEmpty()){
							dtlSet.addAll(med41.getMed4003Dbs());
						}
						if(null != data2Map.get("檢驗項目") && !"".equals(data2Map.get("檢驗項目"))) {
							Med4003Db med43 = new Med4003Db();
							med43.setMed4001Db(med41);
							med43.setCreator(TCBWCommon.getCurrentUserId());
							med43.setCreateDate(Datetime.getYYYMMDD());
							med43.setCreateTime(Datetime.getHHMMSS());
							for(String fieldName:data2Map.keySet()){
								if(null != me43NamesMap.get(fieldName) && !"".equals(me43NamesMap.get(fieldName))){
									BeanUtils.setProperty(med43, me43NamesMap.get(fieldName), data2Map.get(fieldName));
								}
							}
							dtlSet.add(med43);
							med41.setMed4003Dbs(dtlSet);
							med41Map.put(data2Map.get("NO"), med41);
						}
						
					}
					
				}
			}
			
//			//4.寫入Med4004Db的資料
//			List<Map<String,String>> data3MapList = drgMap.get("detail3");
//			if(null != data3MapList && !data3MapList.isEmpty()){
//				//4.1 依對應的名稱找出欄位
//				for(Map<String,String> data3Map:data3MapList){
//					Med4001Db med41 = med41Map.get(data3Map.get("NO"));
//					if(null != med41){
//						java.util.Set dtlSet = new ListOrderedSet();
//						if(null != med41.getMed4004Dbs() && !med41.getMed4004Dbs().isEmpty()){
//							dtlSet.addAll(med41.getMed4004Dbs());
//						}
//						Med4004Db med44 = new Med4004Db();
//						med44.setMed4001Db(med41);
//						med44.setCreator(TCBWCommon.getCurrentUserId());
//						med44.setCreateDate(Datetime.getYYYMMDD());
//						med44.setCreateTime(Datetime.getHHMMSS());
//						for(String fieldName:data3Map.keySet()){
//							if(null != me44NamesMap.get(fieldName) && !"".equals(me44NamesMap.get(fieldName))){
//								BeanUtils.setProperty(med44, me44NamesMap.get(fieldName), data3Map.get(fieldName));
//							}
//						}
//						dtlSet.add(med44);
//						med41.setMed4004Dbs(dtlSet);
//						med41Map.put(data3Map.get("NO"), med41);
//					}
//					
//				}
//			}
//			
//			//5.寫入Med4005Db的資料
//			List<Map<String,String>> data4MapList = drgMap.get("detail4");
//			if(null != data4MapList && !data4MapList.isEmpty()){
//				//5.1 依對應的名稱找出欄位
//				for(Map<String,String> data4Map:data4MapList){
//					Med4001Db med41 = med41Map.get(data4Map.get("NO"));
//					if(null != med41){
//						java.util.Set dtlSet = new ListOrderedSet();
//						if(null != med41.getMed4005Dbs() && !med41.getMed4005Dbs().isEmpty()){
//							dtlSet.addAll(med41.getMed4005Dbs());
//						}
//						Med4005Db med45 = new Med4005Db();
//						med45.setMed4001Db(med41);
//						med45.setCreator(TCBWCommon.getCurrentUserId());
//						med45.setCreateDate(Datetime.getYYYMMDD());
//						med45.setCreateTime(Datetime.getHHMMSS());
//						for(String fieldName:data4Map.keySet()){
//							if(null != me45NamesMap.get(fieldName) && !"".equals(me45NamesMap.get(fieldName))){
//								BeanUtils.setProperty(med45, me45NamesMap.get(fieldName), data4Map.get(fieldName));
//							}
//						}
//						dtlSet.add(med45);
//						med41.setMed4005Dbs(dtlSet);
//						med41Map.put(data4Map.get("NO"), med41);
//					}
//					
//				}
//			}
			
			//6. 批次寫入db
			if(null != med41Map && !med41Map.isEmpty()){
				for(String no:med41Map.keySet()){
					Med4001Db med41 = med41Map.get(no);
					ServiceGetter.getInstance().getTcbwService().save(med41);
					insertCount++;
				}
			}
		}
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return insertCount;
}

public int doImportMEDEX5101(Map<String,List<Map<String,String>>> drgMap){
	int insertCount = 0;
	try {
		if(null != drgMap && !drgMap.isEmpty()){
			Map<String, Med5001Db> med51Map = new HashMap<String, Med5001Db>();
			//1.先找出master的資料
			List<Map<String,String>> dataMapList = drgMap.get("master");
			//1.1依對應的名稱找出欄位
			for(Map<String,String> dataMap:dataMapList){
				Med5001Db med51 = new Med5001Db();
				med51.setCreator(TCBWCommon.getCurrentUserId());
				med51.setStatus("00");
				med51.setRevision("01");
				CommonUser currentUser = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
				if("out".equals(currentUser.getInORout())){
					med51.setInOrOutcreator(currentUser.getUserId());
				}else if("in".equals(currentUser.getInORout())){
					med51.setCreator(currentUser.getUserId());
				}
				med51.setInOrOutcreator(TCBWCommon.getCurrentUserId());
				med51.setCreateDate(Datetime.getYYYMMDD());
				med51.setCreateTime(Datetime.getHHMMSS());
				for(String fieldName:dataMap.keySet()){
					if(null != me51NamesMap.get(fieldName) && !"".equals(me51NamesMap.get(fieldName))){
						BeanUtils.setProperty(med51, me51NamesMap.get(fieldName), dataMap.get(fieldName));
					}
//					if("notifierUserID".equals(me51NamesMap.get(fieldName))){
						//1.2 寫入通報者基本資料
						CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
//						CommonUser user = ServiceGetter.getInstance().getCommonService().getCommonUserDao().getCommonUserByUserId(dataMap.get(fieldName));
						if(null != c){
							med51.setNotifierName(c.getUserName());
							med51.setNotifierAreaCode(c.getUserTelArea());
							med51.setNotifierTel(c.getUserTel());
							med51.setNotifierTelExt(c.getUserTelExt());
							med51.setNotifierEamil(c.getUserEmail());
							med51.setNotifierAddress(c.getUserAddr());
							med51.setNotifierType(c.getCommonDepartment()!=null?Common.get(c.getCommonDepartment().getShortCode()):"");
						}
//					}
				}
				//依案例來源來分別儲存資料
				if("out".equals(med51.getCaseSource())){	
					med51.setCaseSourceInHospital("");//案例來源-國內-試驗醫院	NVARCHAR(10)
					med51.setCaseSourceInDoctor("");//案例來源-國內-試驗醫師	NVARCHAR(30)
				}
				else
				{
					med51.setCaseSourceOutCountry("");//案例來源-國外-國家	NVARCHAR(20)
				}
				
				//依許可證字號自動帶出其他資訊，醫材主類別、次類別為尋找醫材不良事件/醫材臨床不良事件最新一筆案件的醫材主/次類別，可參考jsonMedPermitObjects.jsp
				if(!"".equals(dataMap.get("許可證字")) && !"".equals(dataMap.get("許可證號"))) {
					String hql=" select distinct a.CHNAME,a.APPUNNO,a.APPNAME,a.FACTCIDMA,";
					hql += " a.FACTNAME,b.DOCKNDID,b.DOCKNDMA,b.MSKNDID,b.MSKNDMA,a.FACTCIDMA ";
					hql += " from VW_ForADR_TBMLIC a ,VW_ForADR_BIGKND2 b  ";
					hql += " where a.LICEKID+a.LICID1=b.LICID ";
				    hql += " and a.LICEKID=" + Common.sqlChar(dataMap.get("許可證字"));
				    hql += "and a.LICID1=" + Common.sqlChar(dataMap.get("許可證號"));
				    List<Map<String,Object>> objList = PersistenceServiceGetter.getInstance().getJdbcTemplate().queryForList(hql);
				        
				    String sql="select TOP 1 * from";
					sql +="(";
					sql +=" select a.applNo,a.medPermit,a.medPermitNumber,a.medMainCategoryCode,a.medMainCategory,a.medSecCategoryCode,a.medSecCategory,a.notifierRepDate";
					sql +=" from MED0001_DB a union";
					sql +=" select b.applNo,b.medPermit,b.medPermitNumber,b.medMainCategoryCode,b.medMainCategory,b.medSecCategoryCode,b.medSecCategory,b.notifierRepDate";
					sql +=" from MED2001_DB b";
					sql +=" ) as temp";
					sql +=" where medPermit = " + Common.sqlChar(dataMap.get("許可證字"));
					sql +=" and medPermitNumber = " + Common.sqlChar(dataMap.get("許可證號"));
					sql +=" and ISnull(applNo,'') <> ''";
					sql +=" order by notifierRepDate desc";
					List<Map<String,Object>> objList2 = PersistenceServiceGetter.getInstance().getJdbcTemplate().queryForList(sql);

				   	if(objList!=null && objList.size()>0) {
				   		for (Map<String, Object> result : objList){	
				   			med51.setMedTestMedical((null != result.get("CHNAME"))?Common.get(result.get("CHNAME")):"");//中文品名
				   			med51.setMedFactory((null != result.get("FACTNAME"))?Common.get(result.get("FACTNAME")):"");//製造廠
				   			med51.setMedMainCategoryCode((null != result.get("DOCKNDID"))?Common.get(result.get("DOCKNDID")):"");//醫材主類別代碼
				   			med51.setMedMainCategory((null != result.get("DOCKNDMA"))?Common.get(result.get("DOCKNDMA")):"");//醫材主類別
				   			med51.setMedSecCategoryCode((null != result.get("MSKNDID"))?Common.get(result.get("MSKNDID")):"");//醫材次類別代碼
				   			med51.setMedSecCategory((null != result.get("MSKNDMA"))?Common.get(result.get("MSKNDMA")):"");//醫材次類別
				   			if(objList2!=null && objList2.size()>0) {
				   				for(Map<String, Object> result2 : objList2) {
				   					med51.setMedMainCategoryCode((null != result2.get("medMainCategoryCode"))?Common.get(result2.get("medMainCategoryCode")):"");//醫材主類別代碼
				   					med51.setMedMainCategory((null != result2.get("medMainCategory"))?Common.get(result2.get("medMainCategory")):"");//醫材主類別
				   					med51.setMedSecCategoryCode((null != result2.get("medSecCategoryCode"))?Common.get(result2.get("medSecCategoryCode")):"");//醫材次類別代碼
				   					med51.setMedSecCategory((null != result2.get("medSecCategory"))?Common.get(result2.get("medSecCategory")):"");//醫材次類別
				   				}
				   			}
				   		}
				   	}			
				}
				med51Map.put(dataMap.get("NO"), med51);
			}
			
			//2.寫入Med5002Db的資料
			List<Map<String,String>> data1MapList = drgMap.get("master");
			if(null != data1MapList && !data1MapList.isEmpty()){
				//2.1 依對應的名稱找出欄位
				for(Map<String,String> data1Map:data1MapList){
					Med5001Db med51 = med51Map.get(data1Map.get("NO"));
					if(null != med51){
						java.util.Set dtlSet = new ListOrderedSet();
//						if(null != med51.getMed5002Dbs() && !med51.getMed5002Dbs().isEmpty()){
//							dtlSet.addAll(med51.getMed5002Dbs());
//						}
						if(null != data1Map.get("不良事件描述1-日期") && !"".equals(data1Map.get("不良事件描述1-日期"))) {
							Med5002Db med52 = new Med5002Db();
							med52.setMed5001Db(med51);
							med52.setCreator(TCBWCommon.getCurrentUserId());
							med52.setCreateDate(Datetime.getYYYMMDD());
							med52.setCreateTime(Datetime.getHHMMSS());
							for(String fieldName:data1Map.keySet()){
								if(null != me52NamesMap.get(fieldName) && !"".equals(me52NamesMap.get(fieldName))){
									BeanUtils.setProperty(med52, me52NamesMap.get(fieldName), data1Map.get(fieldName));
								}
							}
							dtlSet.add(med52);
							med51.setMed5002Dbs(dtlSet);
							med51Map.put(data1Map.get("NO"), med51);
						}
						if(null != data1Map.get("不良事件描述2-日期") && !"".equals(data1Map.get("不良事件描述2-日期"))) {
							Med5002Db med52 = new Med5002Db();
							med52.setMed5001Db(med51);
							med52.setCreator(TCBWCommon.getCurrentUserId());
							med52.setCreateDate(Datetime.getYYYMMDD());
							med52.setCreateTime(Datetime.getHHMMSS());
							for(String fieldName:data1Map.keySet()){
								if(null != me52NamesMap2.get(fieldName) && !"".equals(me52NamesMap2.get(fieldName))){
									BeanUtils.setProperty(med52, me52NamesMap2.get(fieldName), data1Map.get(fieldName));
								}
							}
							dtlSet.add(med52);
							med51.setMed5002Dbs(dtlSet);
							med51Map.put(data1Map.get("NO"), med51);
						}
						
					}
					
				}
			}
			
			//3.寫入Med5003Db的資料
			List<Map<String,String>> data2MapList = drgMap.get("master");
			if(null != data2MapList && !data2MapList.isEmpty()){
				//3.1 依對應的名稱找出欄位
				for(Map<String,String> data2Map:data2MapList){
					Med5001Db med51 = med51Map.get(data2Map.get("NO"));
					if(null != med51){
						java.util.Set dtlSet = new ListOrderedSet();
//						if(null != med51.getMed5003Dbs() && !med51.getMed5003Dbs().isEmpty()){
//							dtlSet.addAll(med51.getMed5003Dbs());
//						}
						if(null != data2Map.get("檢驗項目") && !"".equals(data2Map.get("檢驗項目"))) {
							Med5003Db med53 = new Med5003Db();
							med53.setMed5001Db(med51);
							med53.setCreator(TCBWCommon.getCurrentUserId());
							med53.setCreateDate(Datetime.getYYYMMDD());
							med53.setCreateTime(Datetime.getHHMMSS());
							for(String fieldName:data2Map.keySet()){
								if(null != me53NamesMap.get(fieldName) && !"".equals(me53NamesMap.get(fieldName))){
									BeanUtils.setProperty(med53, me53NamesMap.get(fieldName), data2Map.get(fieldName));
								}
							}
							dtlSet.add(med53);
							med51.setMed5003Dbs(dtlSet);
							med51Map.put(data2Map.get("NO"), med51);
						}

					}
					
				}
			}
			
			//4.寫入Med5004Db的資料
			List<Map<String,String>> data3MapList = drgMap.get("detail3");
			if(null != data3MapList && !data3MapList.isEmpty()){
				//4.1 依對應的名稱找出欄位
				for(Map<String,String> data3Map:data3MapList){
					Med5001Db med51 = med51Map.get(data3Map.get("NO"));
					if(null != med51){
						java.util.Set dtlSet = new ListOrderedSet();
						if(null != med51.getMed5004Dbs() && !med51.getMed5004Dbs().isEmpty()){
							dtlSet.addAll(med51.getMed5004Dbs());
						}
						Med5004Db med54 = new Med5004Db();
						med54.setMed5001Db(med51);
						med54.setCreator(TCBWCommon.getCurrentUserId());
						med54.setCreateDate(Datetime.getYYYMMDD());
						med54.setCreateTime(Datetime.getHHMMSS());
						for(String fieldName:data3Map.keySet()){
							if(null != me54NamesMap.get(fieldName) && !"".equals(me54NamesMap.get(fieldName))){
								BeanUtils.setProperty(med54, me54NamesMap.get(fieldName), data3Map.get(fieldName));
							}
						}
						dtlSet.add(med54);
						med51.setMed5004Dbs(dtlSet);
						med51Map.put(data3Map.get("NO"), med51);
					}
					
				}
			}
			
			//5.寫入Med5005Db的資料
			List<Map<String,String>> data4MapList = drgMap.get("detail4");
			if(null != data4MapList && !data4MapList.isEmpty()){
				//5.1 依對應的名稱找出欄位
				for(Map<String,String> data4Map:data4MapList){
					Med5001Db med51 = med51Map.get(data4Map.get("NO"));
					if(null != med51){
						java.util.Set dtlSet = new ListOrderedSet();
						if(null != med51.getMed5005Dbs() && !med51.getMed5005Dbs().isEmpty()){
							dtlSet.addAll(med51.getMed5005Dbs());
						}
						Med5005Db med55 = new Med5005Db();
						med55.setMed5001Db(med51);
						med55.setCreator(TCBWCommon.getCurrentUserId());
						med55.setCreateDate(Datetime.getYYYMMDD());
						med55.setCreateTime(Datetime.getHHMMSS());
						for(String fieldName:data4Map.keySet()){
							if(null != me55NamesMap.get(fieldName) && !"".equals(me55NamesMap.get(fieldName))){
								BeanUtils.setProperty(med55, me55NamesMap.get(fieldName), data4Map.get(fieldName));
							}
						}
						dtlSet.add(med55);
						med51.setMed5005Dbs(dtlSet);
						med51Map.put(data4Map.get("NO"), med51);
					}
					
				}
			}
			
			//6. 批次寫入db
			if(null != med51Map && !med51Map.isEmpty()){
				for(String no:med51Map.keySet()){
					Med5001Db med51 = med51Map.get(no);
					ServiceGetter.getInstance().getTcbwService().save(med51);
					insertCount++;
				}
			}
		}
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return insertCount;
}

public int doImportVDRG0301(Map<String,List<Map<String,String>>> drgMap){
	int insertCount = 0;
	try {
		if(null != drgMap && !drgMap.isEmpty()){
			Map<String, Drg7001Db> drg71Map = new HashMap<String, Drg7001Db>();
			//1.先找出master的資料
			List<Map<String,String>> dataMapList = drgMap.get("master");
			//1.1依對應的名稱找出欄位
			for(Map<String,String> dataMap:dataMapList){
				if(!"".equals(Common.get(dataMap.get("NO")))){
					Drg7001Db drg71 = new Drg7001Db();
					drg71.setCreator(TCBWCommon.getCurrentUserId());
					drg71.setCreateDate(getEditDate());
					drg71.setCreateTime(getEditTime());
					drg71.setModifier(TCBWCommon.getCurrentUserId());
					drg71.setModifyDate(getEditDate());
					drg71.setModifyTime(getEditTime());
					drg71.setStatus("00");
					for(String fieldName:dataMap.keySet()){
						if(null != dr71NamesMap.get(fieldName) && !"".equals(dr71NamesMap.get(fieldName))){
							BeanUtils.setProperty(drg71, dr71NamesMap.get(fieldName), dataMap.get(fieldName));
						}
					}
					drg71Map.put(dataMap.get("NO"), drg71);
				}
			}
			
			//2. 批次寫入db
			if(null != drg71Map && !drg71Map.isEmpty()){
				for(String no:drg71Map.keySet()){
					Drg7001Db drg71 = drg71Map.get(no);
					ServiceGetter.getInstance().getTcbwService().save(drg71);
					insertCount++;
				}
			}
		}
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return insertCount;
}

public int doImportVCOS0101(Map<String,List<Map<String,String>>> cosMap){
	int insertCount = 0;
	java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("COSQTSTATUS");	//案件狀態
	java.util.Map<String, String> publDeptMap = TCBWCommon.getCommonCodeMap("CONPUBLDEPT");	//發佈單位
	java.util.Map<String, String> publCountryMap = TCBWCommon.getCommonCodeMap("COUC");		//發佈單位國家
	java.util.Map<String, String> cciMap = TCBWCommon.getCommonCodeMap("CCI");	  //化粧品項目
	java.util.Map<String, String> situationMap = TCBWCommon.getCommonCodeMap("CONWARNING");	//狀況
	java.util.Map<String, String> subjecttypeMap = TCBWCommon.getCommonCodeMap("COSSJTYPE");//訊息主題
	
	failedData = cosMap.get("master").size();
	try {
		if(null != cosMap && !cosMap.isEmpty()){
			Map<String, Cos7001Db> cos71Map = new HashMap<String, Cos7001Db>();
			//1.先找出master的資料
			List<Map<String,String>> dataMapList = cosMap.get("master");
			//1.1依對應的名稱找出欄位
			String errorInfo = "";
			boolean isSuccessful = true;
			for(Map<String,String> dataMap:dataMapList){
				if(!"".equals(Common.get(dataMap.get("NO")))){
					Cos7001Db cos71 = new Cos7001Db();
					cos71.setCreator(TCBWCommon.getCurrentUserId());
					cos71.setCreateDate(Datetime.getYYYMMDD());
					cos71.setCreateTime(Datetime.getHHMMSS());
					cos71.setModifier(TCBWCommon.getCurrentUserId());
					cos71.setModifyDate(Datetime.getYYYMMDD());
					cos71.setModifyTime(Datetime.getHHMMSS());
					cos71.setProdtype("C01");

					for(String fieldName:dataMap.keySet()){
						if(null != co71NamesMap.get(fieldName) && !"".equals(co71NamesMap.get(fieldName))){
							BeanUtils.setProperty(cos71, co71NamesMap.get(fieldName), dataMap.get(fieldName));
						}
					}
					cos71Map.put(dataMap.get("NO"), cos71);
					if(statusMap.containsKey(cos71Map.get(dataMap.get("NO")).getStatus())) {
						if("00".equals(cos71Map.get(dataMap.get("NO")).getStatus()) && !"".equals(cos71Map.get(dataMap.get("NO")).getApplNo())) {
							//顯示不應有案號
							errorInfo += "第" + dataMap.get("NO") + "筆資料案件編號有誤，該案件狀態不應有案件編號<br>";
							isSuccessful = false;
						} else if(!"00".equals(cos71Map.get(dataMap.get("NO")).getStatus()) && "".equals(cos71Map.get(dataMap.get("NO")).getApplNo())) {
							//顯示應有案號
							errorInfo += "第" + dataMap.get("NO") + "筆資料案件編號有誤，該案件狀態應有案件編號<br>";
							isSuccessful = false;
						}
					} else {
						//代碼不存在
						errorInfo += "第" + dataMap.get("NO") + "筆資料案件狀態欄位代碼有誤<br>";
						isSuccessful = false;
					}
					if(!publDeptMap.containsKey(cos71Map.get(dataMap.get("NO")).getPublDept())) {
						//代碼不存在
						errorInfo += "第" + dataMap.get("NO") + "筆資料發佈單位欄位代碼有誤<br>";
						isSuccessful = false;
					}
					if(!publCountryMap.containsKey(cos71Map.get(dataMap.get("NO")).getPublCountry())) {
						//代碼不存在
						errorInfo += "第" + dataMap.get("NO") + "筆資料發佈單位國家欄位代碼有誤<br>";
						isSuccessful = false;
					}
					if(!cciMap.containsKey(cos71Map.get(dataMap.get("NO")).getIngredient())) {
						//代碼不存在
						errorInfo += "第" + dataMap.get("NO") + "筆資料化粧品項目欄位代碼有誤<br>";
						isSuccessful = false;
					}
					if(!situationMap.containsKey(cos71Map.get(dataMap.get("NO")).getSituation())) {
						//代碼不存在
						errorInfo += "第" + dataMap.get("NO") + "筆資料狀況欄位代碼有誤<br>";
						isSuccessful = false;
					}
					if(!subjecttypeMap.containsKey(cos71Map.get(dataMap.get("NO")).getSubjecttype())) {
						//代碼不存在
						errorInfo += "第" + dataMap.get("NO") + "筆資料訊息主題欄位代碼有誤<br>";
						isSuccessful = false;
					}
					
				}
				//若有錯誤的資料，則將其刪除
				if(!isSuccessful) {
					cos71Map.remove(dataMap.get("NO"));
				}
			}
			
			//2. 批次寫入db
			if(null != cos71Map && !cos71Map.isEmpty()){
				for(String no:cos71Map.keySet()){
					Cos7001Db cos71 = cos71Map.get(no);
					if(!"".equals(cos71.getApplNo())) {
						if(null != View.getObject("from Cos7001Db where applNo = " + Common.sqlChar(cos71.getApplNo()))) {
							ServiceGetter.getInstance().getTcbwService().update(cos71);
							failedData = failedData - insertCount;
						} 
					} else {
						ServiceGetter.getInstance().getTcbwService().save(cos71);
						insertCount++;
						failedData = failedData - insertCount;
					}
					
				}
			}
			//3.回傳錯誤訊息
			if(!isSuccessful) {
				setState(errorInfo);
			}
		}
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return insertCount;
}

}


