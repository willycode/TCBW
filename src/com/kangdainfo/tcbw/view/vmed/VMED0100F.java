package com.kangdainfo.tcbw.view.vmed;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.set.ListOrderedSet;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.*;
import com.kangdainfo.common.util.report.TableModelReportEnvironment;
import com.kangdainfo.common.util.report.TableModelReportGenerator;
import com.kangdainfo.tcbw.model.bo.*;
import com.kangdainfo.tcbw.util.TCBWCommon;



/**
*<br>程式目的：國內外藥品品質警訊警訊登錄作業
*<br>程式代號：vmed0100f
*<br>程式日期：1030512
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  VMED0100F extends SuperBean{


String id;
String changeTabValue;

String applNo;
String status;
String publDept;
String publDeptName;
String publDeptCodeId;
String situation;
String medMainCategory;
String medMainCategoryName;
String medMainCategoryCodeId;
String chProduct;
String recycleclass;
String dataRevDate;
String publDate;
String manufactorName;
String contextsummary;
String productlotNo;
String applicationaction;
String effectarea;
String effectnum;
String datasourWebSite;
String tabId;
String actionType;
String formType;
String isrecycle;//是否涉及回收
String istranslate;//是否摘譯
String translateman;//摘譯人員
String medModel;//醫材型號
String lotNo;//醫材批號
String incountrysituation;//國內情形

String fdareceiveDate;//fda收文日期
String fdareceiveNo;//fda收文文號
String receivesummary;//收文摘要
String postdate;//FDA回文日期
String posesummary;//回文摘要
String completerecycledate;//廠商回收完成日期
String completesummary;//廠商回收摘要


String q_permitKeyNo;

String id7002;
String permitKey7002;
String permitNo7002;
String chProduct7002;
String enProduct7002;
String medapprovedate7002;
String medEffectiveDate7002;
String applicationId7002;
String applicationName7002;
String manufactorName7002;
String manufactorCountry7002;
String medclass7002;
String medMainCategory7002;
String medSecCategory7002;

String replydate7002;//回覆日期
String iswarning7002;//本許可證是否為警訊內容產品  Y:是、N:否、W:本公司未登記此產品、O:其他
String iswarningOther7002;//O:其他
String iseffectinternal7002;//是否國內有受影響產品	Y/N
String replysummary7002;//回覆摘要	
String medModel7002;//醫材型號	
String lotNo7002;//醫材批號	
String effectnum7002;//受影響數量	
String checkcontactman7002;//聯絡窗口	
String checkcontacttel7002;//聯絡電話	
String checkcontactemail7002;//聯絡信箱
String applyObject7002;//施行對象
String proposedAction7002;//建議行動
String excerptDraft7002;//摘譯初稿


String id7005;
String translatedate7005;//摘譯日期	VARCHAR(7)
String translatecontext7005;//摘譯內容	NVARCHAR(150)
String recheckdate7005;//複審日期	VARCHAR(7)

String completecheckdate7005;//內部人員確認日期
String publdate7005;//公告日期
String subject7005;//標題
String context7005;//內容
String remark7005;//備註
String datasource7005;//資料來源
String ispublnews7005;//是否發佈新聞稿
String ispublconsumer7005;//是否發佈消費者知識服務網
String publconsumerDate7005;//消費者知識服務網上架日期
String isfdaweb7005;//是否發佈署網
String lamp7005;//燈號


String id7004;
String applicationID7004;//	許可證持有商統編
String applicationName7004;//許可證持有商
String noticetranslatedate7004;//廠商通知日期	VARCHAR(7)
String checktranslatedate7004;//廠商確認日期	VARCHAR(7)
String checkcontextdesc7004;//廠商確認摘要	NVARCHAR(150)

String id7003;
String changeVersion7003;//版本
String changeMaxVersion7003;//
String changedate7003;//改版日期
String changereason7003;//	改版原因
String changesubject7003;//改版標題
String changecontext7003;//改版內容
String changeremark7003;//改版備註
String changerecheckdate7003;//複審日期

String showFileName;
String fileName;
String fileNameRoute;

javax.servlet.ServletRequest httpRequest;
public javax.servlet.ServletRequest getHttpRequest() {return httpRequest;}
public void setHttpRequest(javax.servlet.ServletRequest r) {this.httpRequest = r;}

//========== 附件上傳 =================
String[] conRowMED060001;


public String[] getConRowMED060001() {return checkGet(conRowMED060001);}
public void setConRowMED060001(String[] conRowMED060001) {this.conRowMED060001 = checkSet(conRowMED060001);}

public final String[] arrConFieldName = { "fileName", "fileExplan","fileRoute"};
String conJSBuilder;
public String getConJSBuilder() {
	if (conJSBuilder != null) return conJSBuilder;
	else return "";
}
public void setConJSBuilder(String conJSBuilder) {this.conJSBuilder = conJSBuilder;}

public String genCon0001DbSet(Long med7001DbId) throws Exception 
{
	StringBuilder sb = new StringBuilder("");
	//查詢附件
	List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType in ('MED060001') and dbName='Med7001Db' and upLoadId="+ med7001DbId + " order by id asc");
	if (null != conList && !conList.isEmpty()) {
		for (Con0001Db dtl : conList) {
			sb.append("addConRow").append(dtl.getSystemType()).append("('conTable").append(dtl.getSystemType()).append("'");
			for (int j = 0; j < arrConFieldName.length; j++) {
				if (j == 2) {
					String attFile = Common.get(dtl.getFileRoute()) + ":;:"+ Common.get(dtl.getFileName());
					sb.append(",").append(Common.sqlChar(attFile));
				} else {
					sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl,arrConFieldName[j])), true)));
				}
			}
			sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
			sb.append(");\n");
		}
	}
	this.setConJSBuilder(sb.toString());
	return sb.toString();
}

//========== 廠商回覆  附件上傳 =================
String[] conRowMED060002;

public String[] getConRowMED060002() {return checkGet(conRowMED060002);}
public void setConRowMED060002(String[] conRowMED060002) {this.conRowMED060002 = checkSet(conRowMED060002);}

public final String[] arrConMED060002FieldName = { "fileName", "fileExplan","fileRoute"};
String conMED060002JSBuilder;
public String getConMED060002JSBuilder() {
	if (conMED060002JSBuilder != null) return conMED060002JSBuilder;
	else return "";
}
public void setConMED060002JSBuilder(String conMED060002JSBuilder) {this.conMED060002JSBuilder = conMED060002JSBuilder;}

public String genCon0001DbMED060002Set(Long med7002DbId) throws Exception 
{
	StringBuilder sb = new StringBuilder("");
	//查詢附件
	List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType in ('MED060002') and dbName='Med7002Db' and upLoadId="+ med7002DbId + " order by id asc");
	if (null != conList && !conList.isEmpty()) 
	{
		for (Con0001Db dtl : conList) 
		{
			sb.append("addConRow").append(dtl.getSystemType()).append("('conTable").append(dtl.getSystemType()).append("'");
			for (int j = 0; j < arrConFieldName.length; j++) 
			{
				if (j == 2) 
				{
					String attFile = Common.get(dtl.getFileRoute()) + ":;:"+ Common.get(dtl.getFileName());
					sb.append(",").append(Common.sqlChar(attFile));
				}
                else 
                {
					sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl,arrConMED060002FieldName[j])), true)));
				}
			}
			sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
			sb.append(");\n");
		}
	}
	this.setConMED060002JSBuilder(sb.toString());
	return sb.toString();
}

//========== 摘譯確認中   附件上傳 =================
String[] conRowMED060003;

public String[] getConRowMED060003() {return checkGet(conRowMED060003);}
public void setConRowMED060003(String[] conRowMED060003) {this.conRowMED060003 = checkSet(conRowMED060003);}

public final String[] arrConMED060003FieldName = { "fileName", "fileExplan","fileRoute"};
String conMED060003JSBuilder;
public String getConMED060003JSBuilder() {
	if (conMED060003JSBuilder != null) return conMED060003JSBuilder;
	else return "";
}
public void setConMED060003JSBuilder(String conMED060003JSBuilder) {this.conMED060003JSBuilder = conMED060003JSBuilder;}

public String genCon0001DbMED060003Set(Long med7004DbId) throws Exception 
{
	StringBuilder sb = new StringBuilder("");
	//查詢附件
	List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType in ('MED060003') and dbName='Med7004Db' and upLoadId="+ med7004DbId + " order by id asc");
	if (null != conList && !conList.isEmpty()) 
	{
		for (Con0001Db dtl : conList) 
		{
			sb.append("addConRow").append(dtl.getSystemType()).append("('conTable").append(dtl.getSystemType()).append("'");
			for (int j = 0; j < arrConFieldName.length; j++) 
			{
				if (j == 2) 
				{
					String attFile = Common.get(dtl.getFileRoute()) + ":;:"+ Common.get(dtl.getFileName());
					sb.append(",").append(Common.sqlChar(attFile));
				}
                else 
                {
					sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl,arrConMED060002FieldName[j])), true)));
				}
			}
			sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
			sb.append(");\n");
		}
	}
	this.setConMED060003JSBuilder(sb.toString());
	return sb.toString();
}

//========== 摘譯確認中   附件上傳 =================
String[] conRowMED060004;

public String[] getConRowMED060004() {return checkGet(conRowMED060004);}
public void setConRowMED060004(String[] conRowMED060004) {this.conRowMED060004 = checkSet(conRowMED060004);}

public final String[] arrConMED060004FieldName = { "fileName", "fileExplan","fileRoute"};
String conMED060004JSBuilder;
public String getConMED060004JSBuilder() {
	if (conMED060004JSBuilder != null) return conMED060004JSBuilder;
	else return "";
}
public void setConMED060004JSBuilder(String conMED060004JSBuilder) {this.conMED060004JSBuilder = conMED060004JSBuilder;}

public String genCon0001DbMED060004Set(Long med7005DbId) throws Exception 
{
	StringBuilder sb = new StringBuilder("");
	//查詢附件
	List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType in ('MED060004') and dbName='Med7005Db' and upLoadId="+ med7005DbId + " order by id asc");
	if (null != conList && !conList.isEmpty()) 
	{
		for (Con0001Db dtl : conList) 
		{
			sb.append("addConRow").append(dtl.getSystemType()).append("('conTable").append(dtl.getSystemType()).append("'");
			for (int j = 0; j < arrConFieldName.length; j++) 
			{
				if (j == 2) 
				{
					String attFile = Common.get(dtl.getFileRoute()) + ":;:"+ Common.get(dtl.getFileName());
					sb.append(",").append(Common.sqlChar(attFile));
				}
                else 
                {
					sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl,arrConMED060002FieldName[j])), true)));
				}
			}
			sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
			sb.append(");\n");
		}
	}
	this.setConMED060004JSBuilder(sb.toString());
	return sb.toString();
}

//========== 醫療器材回收   收文附件上傳 =================
String[] conRowMED060005;

public String[] getConRowMED060005() {return checkGet(conRowMED060005);}
public void setConRowMED060005(String[] conRowMED060005) {this.conRowMED060005 = checkSet(conRowMED060005);}

public final String[] arrConMED060005FieldName = { "fileName", "fileExplan","fileRoute"};
String conMED060005JSBuilder;
public String getConMED060005JSBuilder() {
	if (conMED060005JSBuilder != null) return conMED060005JSBuilder;
	else return "";
}
public void setConMED060005JSBuilder(String conMED060005JSBuilder) {this.conMED060005JSBuilder = conMED060005JSBuilder;}

public String genCon0001DbMED060005Set(Long med7001DbId) throws Exception 
{
	StringBuilder sb = new StringBuilder("");
	//查詢附件
	List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType in ('MED060005') and dbName='Med7001Db' and upLoadId="+ med7001DbId + " order by id asc");
	System.out.println("conList=="+" from Con0001Db where fileKind='MED' and systemType in ('MED060005') and dbName='Med7001Db' and upLoadId="+ med7001DbId + " order by id asc");
	if (null != conList && !conList.isEmpty()) 
	{
		for (Con0001Db dtl : conList) 
		{
			sb.append("addConRow").append(dtl.getSystemType()).append("('conTable").append(dtl.getSystemType()).append("'");
			for (int j = 0; j < arrConFieldName.length; j++) 
			{
				if (j == 2) 
				{
					String attFile = Common.get(dtl.getFileRoute()) + ":;:"+ Common.get(dtl.getFileName());
					sb.append(",").append(Common.sqlChar(attFile));
				}
                else 
                {
					sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl,arrConMED060002FieldName[j])), true)));
				}
			}
			sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
			sb.append(");\n");
		}
	}
	this.setConMED060005JSBuilder(sb.toString());
	return sb.toString();
}

//========== 醫療器材回收   回文附件上傳 =================
String[] conRowMED060006;

public String[] getConRowMED060006() {return checkGet(conRowMED060006);}
public void setConRowMED060006(String[] conRowMED060006) {this.conRowMED060006 = checkSet(conRowMED060006);}

public final String[] arrConMED060006FieldName = { "fileName", "fileExplan","fileRoute"};
String conMED060006JSBuilder;
public String getConMED060006JSBuilder() {
	if (conMED060006JSBuilder != null) return conMED060006JSBuilder;
	else return "";
}
public void setConMED060006JSBuilder(String conMED060006JSBuilder) {this.conMED060006JSBuilder = conMED060006JSBuilder;}

public String genCon0001DbMED060006Set(Long med7001DbId) throws Exception 
{
	StringBuilder sb = new StringBuilder("");
	//查詢附件
	List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType in ('MED060006') and dbName='Med7001Db' and upLoadId="+ med7001DbId + " order by id asc");
	if (null != conList && !conList.isEmpty()) 
	{
		for (Con0001Db dtl : conList) 
		{
			sb.append("addConRow").append(dtl.getSystemType()).append("('conTable").append(dtl.getSystemType()).append("'");
			for (int j = 0; j < arrConFieldName.length; j++) 
			{
				if (j == 2) 
				{
					String attFile = Common.get(dtl.getFileRoute()) + ":;:"+ Common.get(dtl.getFileName());
					sb.append(",").append(Common.sqlChar(attFile));
				}
                else 
                {
					sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl,arrConMED060002FieldName[j])), true)));
				}
			}
			sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
			sb.append(");\n");
		}
	}
	this.setConMED060006JSBuilder(sb.toString());
	return sb.toString();
}

//========== 醫療器材回收   廠商回收狀況附件上傳 =================
String[] conRowMED060007;

public String[] getConRowMED060007() {return checkGet(conRowMED060007);}
public void setConRowMED060007(String[] conRowMED060007) {this.conRowMED060007 = checkSet(conRowMED060007);}

public final String[] arrConMED060007FieldName = { "fileName", "fileExplan","fileRoute"};
String conMED060007JSBuilder;
public String getConMED060007JSBuilder() {
	if (conMED060007JSBuilder != null) return conMED060007JSBuilder;
	else return "";
}
public void setConMED060007JSBuilder(String conMED060007JSBuilder) {this.conMED060007JSBuilder = conMED060007JSBuilder;}

public String genCon0001DbMED060007Set(Long med7001DbId) throws Exception 
{
	StringBuilder sb = new StringBuilder("");
	//查詢附件
	List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType in ('MED060007') and dbName='Med7001Db' and upLoadId="+ med7001DbId + " order by id asc");
	if (null != conList && !conList.isEmpty()) 
	{
		for (Con0001Db dtl : conList) 
		{
			sb.append("addConRow").append(dtl.getSystemType()).append("('conTable").append(dtl.getSystemType()).append("'");
			for (int j = 0; j < arrConFieldName.length; j++) 
			{
				if (j == 2) 
				{
					String attFile = Common.get(dtl.getFileRoute()) + ":;:"+ Common.get(dtl.getFileName());
					sb.append(",").append(Common.sqlChar(attFile));
				}
                else 
                {
					sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl,arrConMED060002FieldName[j])), true)));
				}
			}
			sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
			sb.append(");\n");
		}
	}
	this.setConMED060007JSBuilder(sb.toString());
	return sb.toString();
}

//========== 廠商確認許可證明細 =================
String[] medRow;
public String[] getMedRow() {return medRow;}
public void setMedRow(String[] medRow) {this.medRow = medRow;}
String medJSBuilder;
public String getMedJSBuilder() {
	if (medJSBuilder!=null) return medJSBuilder;
	else return "";
}
public void setMedJSBuilder(String medJSBuilder) {this.medJSBuilder = medJSBuilder;}
public final String[] arrMedFieldName = {"permitKey","permitNo","chProduct","enProduct","medapprovedate","medEffectiveDate","applicationId","applicationName","manufactorName","manufactorCountry","medclass","medMainCategory","medSecCategory"};

public String genMed7002DbItemSet(java.util.Set dtlSet) throws Exception 
{
	
    if (dtlSet!=null && dtlSet.size()>0) 
    {
    	java.util.Map<String, String> medMainCategoryMap = TCBWCommon.getCommonCodeMap("MEDMCT");
    	java.util.Map<String, String> medSecCategoryMap = TCBWCommon.getCommonCodeMap("MEDSCT");
    	StringBuilder sb = new StringBuilder(1024);      	
    	for (Object dtlObj : dtlSet) 
    	{
    		Med7002Db dtl = (Med7002Db) dtlObj;
    		sb.append("addMedRow('medTable'");
			for (int j=0; j<arrMedFieldName.length; j++) 
			{
				sb.append(",").append(Common.sqlChar(checkGet(Common.escapeJavaScript(BeanUtils.getProperty(dtl, arrMedFieldName[j])))));
			}        
			sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");

			sb.append(",'").append(checkGet(medMainCategoryMap.get(BeanUtils.getProperty(dtl,arrMedFieldName[11])))).append("'");
			sb.append(",'").append(checkGet(medSecCategoryMap.get(BeanUtils.getProperty(dtl,arrMedFieldName[12])))).append("'");
			
			sb.append(");\n");
    	}
    	this.setMedJSBuilder(sb.toString());
    	return sb.toString();    	
    } 
    else if (httpRequest!=null && this.getMedRow()!=null && this.getMedRow().length>0) 
    {
		StringBuilder sb = new StringBuilder(1024);
		for (int i=0; i<medRow.length; i++) {
			String rid = medRow[i];
			sb.append("addMedRow('medTable'");
			for (int j=0; j<arrMedFieldName.length; j++) {	
				sb.append(",").append(Common.sqlChar(checkGet(Common.escapeReturnChar(httpRequest.getParameter(arrMedFieldName[j] + rid),true))));			
			}
			sb.append(",'").append(checkGet(httpRequest.getParameter("medId" + rid))).append("'");
			sb.append(");\n");
			
		}
		this.setMedJSBuilder(sb.toString());
		return sb.toString();
	}
    return "";
}

@Override
public Object doQueryOne() throws Exception 
{
	VMED0100F obj = this;
	Med7001Db c = (Med7001Db)ServiceGetter.getInstance().getCommonService().load(Med7001Db.class,Common.getLong(getId()));
	
	if (c!=null) 
	{
		
        obj.setId(Common.get(c.getId()));
        obj.setApplNo(Common.get(c.getApplNo()));
        obj.setStatus(Common.get(c.getStatus()));
        obj.setPublDept(Common.get(c.getPublDept()));
        obj.setPublDeptCodeId(Common.get(c.getPublDept()));
        obj.setPublDeptName(View.getCommonCodeName("CONPUBLDEPT", c.getPublDept()));
        obj.setSituation(Common.get(c.getSituation()));
        obj.setMedMainCategory(Common.get(c.getMedMainCategory()));
        obj.setMedMainCategoryCodeId(Common.get(c.getMedMainCategory()));
        obj.setMedMainCategoryName(View.getCommonCodeName("MEDMCT", c.getMedMainCategory()));
        obj.setChProduct(Common.get(c.getChProduct()));
        obj.setRecycleclass(Common.get(c.getRecycleclass()));
        obj.setDataRevDate(Common.get(c.getDataRevDate()));
        obj.setPublDate(Common.get(c.getPublDate()));
        obj.setManufactorName(Common.get(c.getManufactorName()));
        obj.setContextsummary(Common.get(c.getContextsummary()));
        obj.setProductlotNo(Common.get(c.getProductlotNo()));
        obj.setApplicationaction(Common.get(c.getApplicationaction()));
        obj.setEffectarea(Common.get(c.getEffectarea()));
        obj.setEffectnum(Common.get(c.getEffectnum()));
        obj.setDatasourWebSite(c.getDatasourWebSite());
        obj.setEditID(Common.get(c.getModifier()));
        obj.setEditDate(Common.get(c.getModifyDate()));
        obj.setEditTime(Common.get(c.getModifyTime()));
        obj.genCon0001DbSet(c.getId());
        obj.genMed7002DbItemSet(c.getMed7002Dbs());
        
        obj.setIsrecycle(c.getIsrecycle());//是否涉及回收
        obj.setIstranslate(c.getIstranslate());//是否摘譯
        obj.setTranslateman(c.getTranslateman());//摘譯人員
        obj.setMedModel(c.getMedModel());//醫材型號
        obj.setLotNo(c.getLotNo());//醫材批號
        obj.setIncountrysituation(c.getIncountrysituation());//國內情形
        
        obj.setFdareceiveDate(c.getFdareceiveDate());//fda收文日期
        obj.setFdareceiveNo(c.getFdareceiveNo());//fda收文文號
        obj.setReceivesummary(c.getReceivesummary());//收文摘要
        obj.setPostdate(c.getPostdate());//FDA回文日期
        obj.setPosesummary(c.getPosesummary());//回文摘要
        obj.setCompleterecycledate(c.getCompleterecycledate());//廠商回收完成日期
        obj.setCompletesummary(c.getCompletesummary());//廠商回收摘要
        
        obj.genCon0001DbMED060005Set(Common.getLong(obj.getId()));
		obj.genCon0001DbMED060006Set(Common.getLong(obj.getId()));
		obj.genCon0001DbMED060007Set(Common.getLong(obj.getId()));
        
        String hql=" from Med7002Db where med7001Db.id="+c.getId();	
        	
        if(!"".equals(getId7002()))
        	hql+="and id="+getId7002();
          
        if("0301".equals(getFormType()) ||  "0701".equals(getFormType())  )
    	{    
    	   String compegno=View.getLookupField(" select con1005Db.compegno from Con1006Db where commonUser.userId="+ Common.sqlChar(getUserID()));
    	   hql += " and applicationID= " + Common.sqlChar(compegno);
    	}
    	
        Med7002Db med7002Db = (Med7002Db)View.getObject(hql);
		
        if (med7002Db!=null) 
		{
           obj.setId7002(Common.get(med7002Db.getId()));
	       obj.setPermitKey7002(med7002Db.getPermitKey());
	       obj.setPermitNo7002(med7002Db.getPermitNo());
	       obj.setChProduct7002(med7002Db.getChProduct());
	       obj.setEnProduct7002(med7002Db.getEnProduct());
	       obj.setMedapprovedate7002(med7002Db.getMedapprovedate());
	       obj.setMedEffectiveDate7002(med7002Db.getMedEffectiveDate());
	       obj.setApplicationId7002(med7002Db.getApplicationId());
	       obj.setApplicationName7002(med7002Db.getApplicationName());
	       obj.setManufactorName7002(med7002Db.getManufactorName());
	       obj.setManufactorCountry7002(med7002Db.getManufactorCountry());
	       obj.setMedclass7002(med7002Db.getMedclass());
	       obj.setMedMainCategory7002(med7002Db.getMedMainCategory());
	       obj.setMedSecCategory7002(med7002Db.getMedSecCategory());
	       
	       obj.setReplydate7002(med7002Db.getReplydate());
		   obj.setIswarning7002(med7002Db.getIswarning());
		   obj.setIseffectinternal7002(med7002Db.getIseffectinternal());//是否國內有受影響產品
		   obj.setReplysummary7002(med7002Db.getReplysummary());
		   obj.setMedModel7002(med7002Db.getMedModel());
		   obj.setLotNo7002(med7002Db.getLotNo());
		   obj.setEffectnum7002(med7002Db.getEffectnum());
		   obj.setCheckcontactman7002(med7002Db.getCheckcontactman());
		   obj.setCheckcontacttel7002(med7002Db.getCheckcontacttel());
		   obj.setCheckcontactemail7002(med7002Db.getCheckcontactemail());
		   
		   obj.setApplyObject7002(med7002Db.getApplyObject());//施行對象
		   obj.setProposedAction7002(med7002Db.getProposedAction());//建議行動
		   obj.setExcerptDraft7002(med7002Db.getExcerptDraft());//摘譯初稿
		   
		   obj.genCon0001DbMED060002Set(med7002Db.getId());
		   
		  
		 }
        
        String hql7003=" from Med7003Db where med7001Db.id="+c.getId();
              
		if(!"".equals(Common.get(getId7003())))
			  hql7003+=" and id=" + getId7003();
			 
		hql7003+=" order by id desc";
		
		Med7003Db obj7003 = (Med7003Db) View.getObject(hql7003);

		if(obj7003!=null)
		{	
			String maxVersion7003=" select max(id) from Med7003Db where med7001Db.id="+c.getId();
			
			String maxVersion = View.getLookupField(maxVersion7003);
			
			obj.setChangeMaxVersion7003(maxVersion);
			
			obj.setChangeVersion7003("V"+obj7003.getChangeVersion());//版本
			obj.setChangedate7003(obj7003.getChangedate());//改版日期
			obj.setChangereason7003(obj7003.getChangereason());//改版原因
			obj.setChangesubject7003(obj7003.getChangesubject());//改版標題
			obj.setChangecontext7003(obj7003.getChangecontext());//改版內容
			obj.setChangeremark7003(obj7003.getChangeremark());//改版備註
			obj.setChangerecheckdate7003(obj7003.getChangerecheckdate());//複審日期
			obj.setId7003(Common.get(obj7003.getId()));
		}
 
		
		String hql7004=" from Med7004Db where med7001Db.id="+c.getId();
			  
		if(!"".equals(Common.get(getId7004())))
			  hql7004+=" and id=" + getId7004();
			       
		Med7004Db obj7004 = (Med7004Db) View.getObject(hql7004);

		if(obj7004!=null)
		{	
			 obj.setApplicationID7004(obj7004.getApplicationID());//許可證持有商統編
			 obj.setApplicationName7004(obj7004.getApplicationName());//許可證持有商
			 obj.setNoticetranslatedate7004(obj7004.getNoticetranslatedate());//廠商通知日期
			 obj.setChecktranslatedate7004(obj7004.getChecktranslatedate());//廠商確認日期
			 obj.setCheckcontextdesc7004(obj7004.getCheckcontextdesc());//廠商確認摘要
			 obj.setId7004(Common.get(obj7004.getId()));
			  
			 obj.genCon0001DbMED060003Set(obj7004.getId());
		}
			
		String hql7005=" from Med7005Db where med7001Db.id="+c.getId();
	       
		if(!"".equals(Common.get(getId7005())))
			  hql7005+=" and id=" + getId7005();
			
		Med7005Db obj7005 = (Med7005Db) View.getObject(hql7005);

		if(obj7005!=null)
		{	
			 obj.setCompletecheckdate7005(obj7005.getCompletecheckdate());//完成確認日期：
			 
			 obj.setTranslatedate7005(obj7005.getTranslatedate());//摘譯日期
			 obj.setRecheckdate7005(obj7005.getRecheckdate());//複審日期
			 obj.setTranslatecontext7005(obj7005.getTranslatecontext());//摘譯內容
			 obj.setPubldate7005(obj7005.getPubldate());//公告日期
		     obj.setSubject7005(obj7005.getSubject());//標題
		     obj.setContext7005(obj7005.getContext());//內容
		     obj.setRemark7005(obj7005.getRemark());//備註
		     obj.setDatasource7005(obj7005.getDatasource());//資料來源
		     obj.setIspublnews7005(obj7005.getIspublnews());//是否發佈新聞稿
		     obj.setIspublconsumer7005(obj7005.getIspublconsumer());//是否發佈消費者知識服務網
		     obj.setPublconsumerDate7005(obj7005.getPublconsumerDate());//消費者知識服務網上架日期
		     obj.setIsfdaweb7005(obj7005.getIsfdaweb());//是否發佈署網
		     obj.setLamp7005(obj7005.getLamp());//燈號
		     obj.setId7005(Common.get(obj7005.getId()));
		     
		     obj.genCon0001DbMED060004Set(obj7005.getId());
		}
			
		Con0001Db o = (Con0001Db)View.getObject("from Con0001Db where fileKind='MED' and  systemType='MED060004' and dbName='Med7005Db' and upLoadId="+Common.sqlChar(id));
		if(o!=null)
		{
			obj.setFileName(o.getFileName());
			obj.setFileNameRoute(o.getFileRoute());
			obj.setShowFileName(o.getFileName());
		}
		
	}
	return obj;
}

@Override
public Object doQueryAll() throws Exception 
{
	// TODO Auto-generated method stub
	return null;
}

@Override
public void doCreate() throws Exception 
{
	Med7001Db obj = new Med7001Db();
	obj.setApplNo(getApplNo());
    obj.setStatus(getStatus());
    obj.setPublDept(getPublDeptCodeId());
    obj.setSituation(getSituation());
    obj.setMedMainCategory(getMedMainCategoryCodeId());
    obj.setChProduct(getChProduct());
    obj.setRecycleclass(getRecycleclass());
    obj.setDataRevDate(getDataRevDate());
    obj.setPublDate(getPublDate());
    obj.setManufactorName(getManufactorName());
    obj.setContextsummary(getContextsummary());
    obj.setProductlotNo(getProductlotNo());
    obj.setApplicationaction(getApplicationaction());
    obj.setEffectarea(getEffectarea());
    obj.setEffectnum(getEffectnum());
    obj.setDatasourWebSite(getDatasourWebSite());
    obj.setCreator(getEditID());
	obj.setCreateDate(getEditDate());
	obj.setCreateTime(getEditTime());
	obj.setModifier(getEditID());
	obj.setModifyDate(getEditDate());
	obj.setModifyTime(getEditTime());
	ServiceGetter.getInstance().getCommonService().save(obj);
	setId(obj.getId().toString());
	
	String procDesc = "";
	
	if("maintain1".equals(getActionType()))
	{	
		//送件
		//系統給號
		String newApplNo = TCBWCommon.getApplNo("MED06","T05",Datetime.getYYY(),false);
		if(null != newApplNo && !"".equals(newApplNo))
		{
			obj.setApplNo(newApplNo);
			obj.setStatus("10");					//廠商確認中				
			procDesc = "案件評估者 - 警訊評估中";
		}else throw new Exception("給號發生錯誤，送件失敗！");
	}
	
	if(null != procDesc && !"".equals(procDesc))
	{
    	//歷程紀錄
		ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED06",obj.getId(), obj.getApplNo(), obj.getStatus(), procDesc, TCBWCommon.getCurrentUserId());
	}	
}

@Override
public void doUpdate() throws Exception 
{
	System.out.println("xxx=="+getFormType());
	List delList = new ArrayList();
	String procDesc = "";
	
	Med7001Db obj = (Med7001Db) View.getObject(" from Med7001Db where id=" + Common.getLong(getId()));
	
	if (obj != null) 
	{
	
		obj.setApplNo(getApplNo());
	    obj.setStatus(getStatus());
	    obj.setPublDept(getPublDeptCodeId());
	    obj.setSituation(getSituation());
	    obj.setMedMainCategory(getMedMainCategoryCodeId());
	    obj.setChProduct(getChProduct());
	    obj.setRecycleclass(getRecycleclass());
	    obj.setDataRevDate(getDataRevDate());
	    obj.setPublDate(getPublDate());
	    obj.setManufactorName(getManufactorName());
	    obj.setContextsummary(getContextsummary());
	    obj.setProductlotNo(getProductlotNo());
	    obj.setApplicationaction(getApplicationaction());
	    obj.setEffectarea(getEffectarea());
	    obj.setEffectnum(getEffectnum());
	    obj.setDatasourWebSite(getDatasourWebSite());
		obj.setModifier(getEditID());
		obj.setModifyDate(getEditDate());
		obj.setModifyTime(getEditTime());
		
		
		
		//附件上傳
		if (true && getHttpRequest() != null) 
		{
			if (getHttpRequest() != null && getConRowMED060001() != null && getConRowMED060001().length > 0) 
			{
				StringBuilder dtlKey = new StringBuilder("-2,-1");
				for (int i = 0; i < getConRowMED060001().length; i++) 
				{
					String rid = getConRowMED060001()[i];
					Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+ Common.getLong(getHttpRequest().getParameter("conId" + rid)));
					dtlKey.append(",").append(dtl.getId());
				}
				delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060001') and dbName in('Med7001Db') and upLoadId="+Common.getLong(getId())+" and id not in ("+ dtlKey.toString() + ")"));
			} 
			else 
			{
				delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060001') and dbName in('Med7001Db') and upLoadId="+Common.getLong(getId())));
			}
		}
		
		//廠商確認許可證明細
		if (true && getHttpRequest()!=null) 
		{
			java.util.Set dtlSet = new ListOrderedSet();
    		
			if (getHttpRequest()!=null && getMedRow()!=null && getMedRow().length>0) 
    		{
    			StringBuilder dtlKey = new StringBuilder("-2,-1");		
    			for (int i=0; i<getMedRow().length; i++) 
    			{
    				String rid = getMedRow()[i];
    				Med7002Db dtl = (Med7002Db) View.getObject("from Med7002Db where id="+Common.getLong(getHttpRequest().getParameter("medId" + rid)));
    				if (dtl==null) 
    				{
    					dtl = new Med7002Db();
    					dtl.setMed7001Db(obj);
    					dtl.setCreator(getEditID());
    					dtl.setCreateDate(getEditDate());
    					dtl.setCreateTime(getEditTime());
    				}
    				for (int j=0; j<arrMedFieldName.length; j++) 
    				{
    					BeanUtils.setProperty(dtl, arrMedFieldName[j], Common.get(getHttpRequest().getParameter(arrMedFieldName[j] + rid)));
    				}		
    				dtl.setModifier(getEditID());
    				dtl.setModifyDate(getEditDate());
    				dtl.setModifyTime(getEditTime());
    				dtlSet.add(dtl);
    				
    				if(Common.getLong(dtl.getId()) > 0)	dtlKey.append(",").append(dtl.getId());
    			}
    			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Med7002Db where med7001Db.id=" + Common.getLong(getId()) + " and id not in (" + dtlKey.toString() + ")"));
    		} 
    		else 
    		{
    			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Med7002Db where med7001Db.id=" + Common.getLong(getId())));
    		}	
    		obj.setMed7002Dbs(dtlSet);
    		genMed7002DbItemSet(dtlSet);	
    	}
		
		if("0301".equals(getFormType()) || "0401".equals(getFormType()))	
		{	
			
		   //內網回覆可修改以下欄位	
		   if("0401".equals(getFormType()))
		   {   
		     obj.setIsrecycle(getIsrecycle());//是否涉及回收
	         obj.setIstranslate(getIstranslate());//是否摘譯
	         obj.setTranslateman(getTranslateman());//摘譯人員
	         obj.setMedModel(getMedModel());//醫材型號
	         obj.setLotNo(getLotNo());//醫材批號
	         obj.setIncountrysituation(getIncountrysituation());//國內情形	
		   }
		   
		   
		   Med7002Db obj7002 = (Med7002Db) View.getObject(" from Med7002Db where id=" + getId7002());
		
		   if (obj7002 != null) 
		   {
			 obj7002.setIswarning(getIswarning7002());//本許可證是否為警訊內容產品
			 
			 if("O".equals(getIswarning7002()))
			 {		 
			   obj7002.setIswarningOther(getIswarningOther7002());
			 }
			 else
			 {
			   obj7002.setIswarningOther("");
			 }	 
			 obj7002.setIseffectinternal(getIseffectinternal7002());//是否國內有受影響產品
			 obj7002.setReplysummary(getReplysummary7002());
			 obj7002.setMedModel(getMedModel7002());
			 obj7002.setLotNo(getLotNo7002());
			 obj7002.setEffectnum(getEffectnum7002());
			 obj7002.setCheckcontactman(getCheckcontactman7002());
			 obj7002.setCheckcontacttel(getCheckcontacttel7002());
			 obj7002.setCheckcontactemail(getCheckcontactemail7002());

			 obj7002.setApplyObject(getApplyObject7002());//施行對象
			 obj7002.setProposedAction(getProposedAction7002());//建議行動
			 obj7002.setExcerptDraft(getExcerptDraft7002());//摘譯初稿
			 
			 
		     if("0301".equals(getFormType()) && "maintain20".equals(getActionType()))
		     {	
			   //外網人員按下回覆完成按鈕，系統自動產生回覆日期
			   obj7002.setReplydate(Datetime.getYYYMMDD());
		     }
		     
		     if("0401".equals(getFormType())) 
		     {
		       obj7002.setReplydate(getReplydate7002()); 
		     }	
		     obj7002.setModifier(getEditID());
		     obj7002.setModifyDate(getEditDate());
		     obj7002.setModifyTime(getEditTime());
		     
		     
		     
		   }
		   ServiceGetter.getInstance().getCommonService().update(obj7002);
		
		   
		   //附件上傳
		   if (true && getHttpRequest() != null) 
		   {
			    if (getHttpRequest() != null && getConRowMED060002() != null && getConRowMED060002().length > 0) 
			    {
					
					StringBuilder dtlKey = new StringBuilder("-2,-1");
					for (int i = 0; i < getConRowMED060002().length; i++) 
					{
						String rid = getConRowMED060002()[i];
						Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+ Common.getLong(getHttpRequest().getParameter("conId" + rid)));
						dtlKey.append(",").append(dtl.getId());
					}
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060002') and dbName in('Med7002Db') and upLoadId="+Common.getLong(getId7002())+" and id not in ("+ dtlKey.toString() + ")"));
				} 
				else 
				{
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060002') and dbName in('Med7002Db') and upLoadId="+Common.getLong(getId7002())));
				}
			}
		}
		
		//摘譯完成
		if("0501".equals(getFormType()))	
		{	
			String hql7005=" from Med7005Db where ";
			       
			if(!"".equals(Common.get(getId7005())))
			   hql7005+=" id=" + getId7005();
			else
			   hql7005+=" 1=2";
				
			Med7005Db obj7005 = (Med7005Db) View.getObject(hql7005);

			if(obj7005==null)
			{
				obj7005=new Med7005Db();
				Med7001Db master=new Med7001Db();
				master.setId(obj.getId());
				obj7005.setMed7001Db(master);
				obj7005.setCreator(getEditID());
				obj7005.setCreateDate(getEditDate());
				obj7005.setCreateTime(getEditTime());
			}	
			else
			{
				obj7005.setModifier(getEditID());
				obj7005.setModifyDate(getEditDate());
				obj7005.setModifyTime(getEditTime());
			}	
			
			obj7005.setTranslatedate(getTranslatedate7005());//摘譯日期
			obj7005.setTranslatecontext(getTranslatecontext7005());//摘譯內容
		
			ServiceGetter.getInstance().getCommonService().saveOrUpdate(obj7005);
		}
		
		//複審完成
		if("0601".equals(getFormType()))	
		{	
			String hql7005=" from Med7005Db where ";    
			       hql7005+=" id=" + getId7005();
				
			Med7005Db obj7005 = (Med7005Db) View.getObject(hql7005);
			
			obj7005.setTranslatedate(getTranslatedate7005());//摘譯日期
			obj7005.setTranslatecontext(getTranslatecontext7005());//摘譯內容
			
			if(getHttpRequest()!= null)
			{	
			  obj7005.setRecheckdate(Datetime.getYYYMMDD());//複審日期---系統自動產生
			
			  obj7005.setModifier(getEditID());
			  obj7005.setModifyDate(getEditDate());
			  obj7005.setModifyTime(getEditTime());
			
			  ServiceGetter.getInstance().getCommonService().update(obj7005);
			
			 
			
			  Map<String,String> med72Map = new HashMap<String,String>();
			  
			  java.util.List<Med7004Db> saveListMed7004Db = new java.util.ArrayList<Med7004Db>();
			
			  if(null != obj.getMed7002Dbs() && !obj.getMed7002Dbs().isEmpty())
			  {
				for(Object dtlObj:obj.getMed7002Dbs())
				{
					Med7002Db med7002 = (Med7002Db)dtlObj;
					
					if(!"".equals(med7002.getApplicationId()))
					  med72Map.put(med7002.getApplicationId(),med7002.getApplicationName());
				}
			  }
			
			  for(Map.Entry<String, String> e : med72Map.entrySet()) 
			  {
				Med7004Db obj7004 =new Med7004Db();
					
				Med7001Db master =new Med7001Db();
				master.setId(obj.getId());
				obj7004.setMed7001Db(master);
				  
				obj7004.setApplicationID(e.getKey());
				obj7004.setApplicationName(e.getValue());
				
				obj7004.setNoticetranslatedate(Datetime.getYYYMMDD());//廠商通知日期---系統自動產生
				obj7004.setCreator(getEditID());
				obj7004.setCreateDate(getEditDate());
				obj7004.setCreateTime(getEditTime());
				
				saveListMed7004Db.add(obj7004);
			  } 
			  
			  ServiceGetter.getInstance().getCommonService().saveBatch(saveListMed7004Db);
			  
			  Con0001Db o = (Con0001Db)View.getObject("from Con0001Db where fileKind='MED' and  systemType='MED060004' and dbName='Med7005Db' and upLoadId="+Common.sqlChar(id));
			  if(o!=null)
			  {
					o.setFileName(getFileName());
					o.setFileRoute(getFileNameRoute());
					o.setFileName(getShowFileName());
					
					String med = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("MED");
					
					File dir = new File(med+"\\"+"MED060004"+"\\"+"word"+id);
					
					File[] children = dir.listFiles();
				       
					if(children!=null)
					{	
				      for ( int i = 0; i < children.length; i++ ) 
				      {
				        // 刪除檔案
				        if ( children[i].isFile() && !getShowFileName().equals(children[i].getName()))
				           children[i].delete();
				      }
					}
			   }
			  
			}
			
			
			
		}
		
		if("0701".equals(getFormType()))//摘譯確認中(外部)	
		{	
			
		   Med7004Db obj7004 = (Med7004Db) View.getObject(" from Med7004Db where id=" + getId7004());
				
		   if (obj7004 != null) 
		   {
			  if("maintain9".equals(getActionType()))
			      obj7004.setChecktranslatedate(Datetime.getYYYMMDD());//完成確認日期---系統自動產生
			  
			  obj7004.setCheckcontextdesc(getCheckcontextdesc7004());//廠商確認摘要
			  obj7004.setModifier(getEditID());
			  obj7004.setModifyDate(getEditDate());
			  obj7004.setModifyTime(getEditTime());
			  ServiceGetter.getInstance().getCommonService().update(obj7004);
		   }
			
		   //附件上傳
		   if (true && getHttpRequest() != null) 
		   {

				if (getHttpRequest() != null && getConRowMED060003() != null && getConRowMED060003().length > 0) 
				{
					
					StringBuilder dtlKey = new StringBuilder("-2,-1");
					for (int i = 0; i < getConRowMED060003().length; i++) 
					{
						String rid = getConRowMED060003()[i];
						Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+ Common.getLong(getHttpRequest().getParameter("conId" + rid)));
						dtlKey.append(",").append(dtl.getId());
					}
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060003') and dbName in('Med7004Db') and upLoadId="+Common.getLong(getId7004())+" and id not in ("+ dtlKey.toString() + ")"));
				} 
				else 
				{
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060003') and dbName in('Med7004Db') and upLoadId="+Common.getLong(getId7004())));
				}
	        }
		}
		
		if("0801".equals(getFormType()))//摘譯確認中
		{	
			
		   Med7004Db obj7004 = (Med7004Db) View.getObject(" from Med7004Db where id=" + getId7004());
				
		   if (obj7004 != null) 
		   {
			  //obj7004.setChecktranslatedate(getChecktranslatedate7004());//完成確認日期
			  obj7004.setCheckcontextdesc(getCheckcontextdesc7004());//廠商確認摘要
			  obj7004.setModifier(getEditID());
			  obj7004.setModifyDate(getEditDate());
			  obj7004.setModifyTime(getEditTime());
			  ServiceGetter.getInstance().getCommonService().update(obj7004);
			  
			  
			  updateMed7005Db(obj.getId(),getCompletecheckdate7005());

			  //附件上傳
			  if (true && getHttpRequest() != null) 
			  {

				  if (getHttpRequest() != null && getConRowMED060003() != null && getConRowMED060003().length > 0) 
				  {	
						StringBuilder dtlKey = new StringBuilder("-2,-1");
						for (int i = 0; i < getConRowMED060003().length; i++) 
						{
							String rid = getConRowMED060003()[i];
							Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+ Common.getLong(getHttpRequest().getParameter("conId" + rid)));
							dtlKey.append(",").append(dtl.getId());
						}
						delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060003') and dbName in('Med7004Db') and upLoadId="+Common.getLong(getId7004())+" and id not in ("+ dtlKey.toString() + ")"));
				  } 
				  else 
				  {
					 delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060003') and dbName in('Med7004Db') and upLoadId="+Common.getLong(getId7004())));
				  }
		      }
			  
			  
		   }

		}
		
		if("0901".equals(getFormType()))//警訊公告
		{	
			String hql7005=" from Med7005Db where med7001Db.id="+obj.getId();
			
			Med7005Db obj7005 = (Med7005Db) View.getObject(hql7005);

			if(obj7005!=null)
			{	
			    obj7005.setPubldate(getPubldate7005());//公告日期
			    obj7005.setSubject(getSubject7005());//標題
			    obj7005.setContext(getContext7005());//內容
			    obj7005.setRemark(getRemark7005());//備註
			    obj7005.setDatasource(getDatasource7005());//資料來源
			    obj7005.setIspublnews(getIspublnews7005());//是否發佈新聞稿
			    obj7005.setIspublconsumer(getIspublconsumer7005());//是否發佈消費者知識服務網
			    obj7005.setPublconsumerDate(getPublconsumerDate7005());//消費者知識服務網上架日期
			    obj7005.setIsfdaweb(getIsfdaweb7005());//是否發佈署網
			    obj7005.setLamp(getLamp7005());//燈號
			    ServiceGetter.getInstance().getCommonService().update(obj7005);
		    }
		   
		   //附件上傳
		   if (true && getHttpRequest() != null) 
		   {
				if (getHttpRequest() != null && getConRowMED060004() != null && getConRowMED060004().length > 0) 
				{
					
					StringBuilder dtlKey = new StringBuilder("-2,-1");
					for (int i = 0; i < getConRowMED060004().length; i++) 
					{
						String rid = getConRowMED060004()[i];
						Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+ Common.getLong(getHttpRequest().getParameter("conId" + rid)));
						dtlKey.append(",").append(dtl.getId());
					}
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060004') and dbName in('Med7005Db') and upLoadId="+Common.getLong(getId7005())+" and id not in ("+ dtlKey.toString() + ")"));
				} 
				else 
				{
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060004') and dbName in('Med7005Db') and upLoadId="+Common.getLong(getId7005())));
				}
	        }
		}
		
		if("1001".equals(getFormType()))//警訊公告改版 > > 改版中
		{	
		   String hql7003=" from Med7003Db where med7001Db.id="+obj.getId();
                  hql7003+=" and id=(select max(id) from Med7003Db where med7001Db.id="+obj.getId()+")";

	       Med7003Db obj7003 = (Med7003Db) View.getObject(hql7003);

	       if(obj7003!=null)
	       {	
	    	   obj7003.setChangedate(getChangedate7003());//改版日期
	    	   obj7003.setChangereason(getChangereason7003());//改版原因
	    	   obj7003.setChangesubject(getChangesubject7003());//改版標題
	    	   obj7003.setChangecontext(getChangecontext7003());//改版內容
	    	   obj7003.setChangeremark(getChangeremark7003());//改版備註
	    	   ServiceGetter.getInstance().getCommonService().update(obj7003);
	       }
		}
		
		if("1101".equals(getFormType()))//警訊公告改版 > > 複審中
		{	
		   String hql7003=" from Med7003Db where med7001Db.id="+obj.getId();
                  hql7003+=" and id=(select max(id) from Med7003Db where med7001Db.id="+obj.getId()+")";

	       Med7003Db obj7003 = (Med7003Db) View.getObject(hql7003);

	       if(obj7003!=null)
	       {	
	    	   obj7003.setChangereason(getChangereason7003());//改版原因
	    	   obj7003.setChangesubject(getChangesubject7003());//改版標題
	    	   obj7003.setChangecontext(getChangecontext7003());//改版內容
	    	   obj7003.setChangeremark(getChangeremark7003());//改版備註
	    	   obj7003.setChangerecheckdate(Datetime.getYYYMMDD());//複審日期
	    	   ServiceGetter.getInstance().getCommonService().update(obj7003);
	       }
		}
		
		if("1201".equals(getFormType()))//警訊公告改版 > > 複審中
		{
		   obj.setFdareceiveDate(getFdareceiveDate());//fda收文日期
           obj.setFdareceiveNo(getFdareceiveNo());//fda收文文號
           obj.setReceivesummary(getReceivesummary());//收文摘要
           obj.setPostdate(getPostdate());//FDA回文日期
           obj.setPosesummary(getPosesummary());//回文摘要
           obj.setCompleterecycledate(getCompleterecycledate());//廠商回收完成日期
           obj.setCompletesummary(getCompletesummary());//廠商回收摘要
           //收文附件上傳
		   if (true && getHttpRequest() != null) 
		   {
				if (getHttpRequest() != null && getConRowMED060005() != null && getConRowMED060005().length > 0) 
				{
					
					StringBuilder dtlKey = new StringBuilder("-2,-1");
					for (int i = 0; i < getConRowMED060005().length; i++) 
					{
						String rid = getConRowMED060005()[i];
						Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+ Common.getLong(getHttpRequest().getParameter("conId" + rid)));
						dtlKey.append(",").append(dtl.getId());
					}
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060005') and dbName in('Med7001Db') and upLoadId="+Common.getLong(getId())+" and id not in ("+ dtlKey.toString() + ")"));
				} 
				else 
				{
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060005') and dbName in('Med7001Db') and upLoadId="+Common.getLong(getId())));
				}
	        }
		    //回文附件上傳
		    if (true && getHttpRequest() != null) 
		    {
				if (getHttpRequest() != null && getConRowMED060006() != null && getConRowMED060006().length > 0) 
				{
					
					StringBuilder dtlKey = new StringBuilder("-2,-1");
					for (int i = 0; i < getConRowMED060006().length; i++) 
					{
						String rid = getConRowMED060006()[i];
						Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+ Common.getLong(getHttpRequest().getParameter("conId" + rid)));
						dtlKey.append(",").append(dtl.getId());
					}
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060006') and dbName in('Med7001Db') and upLoadId="+Common.getLong(getId())+" and id not in ("+ dtlKey.toString() + ")"));
				} 
				else 
				{
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060006') and dbName in('Med7001Db') and upLoadId="+Common.getLong(getId())));
				}
	        }
		    //廠商回收狀況附件上傳
		    if (true && getHttpRequest() != null) 
		    {
				if (getHttpRequest() != null && getConRowMED060007() != null && getConRowMED060007().length > 0) 
				{
					
					StringBuilder dtlKey = new StringBuilder("-2,-1");
					for (int i = 0; i < getConRowMED060007().length; i++) 
					{
						String rid = getConRowMED060007()[i];
						Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+ Common.getLong(getHttpRequest().getParameter("conId" + rid)));
						dtlKey.append(",").append(dtl.getId());
					}
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060007') and dbName in('Med7001Db') and upLoadId="+Common.getLong(getId())+" and id not in ("+ dtlKey.toString() + ")"));
				} 
				else 
				{
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType in ('MED060007') and dbName in('Med7001Db') and upLoadId="+Common.getLong(getId())));
				}
	        }
		}
		

		if("maintain1".equals(getActionType()))
		{	
			//送件
			//系統給號
			String newApplNo = TCBWCommon.getApplNo("MED06","T05",Datetime.getYYY(),false);
			if(null != newApplNo && !"".equals(newApplNo))
			{
				obj.setApplNo(newApplNo);
				obj.setStatus("10");//廠商確認中				
				procDesc = "案件評估者 - 警訊評估中";
			}else throw new Exception("給號發生錯誤，送件失敗！");
		}
		else if("maintain3".equals(getActionType()))//廠商確認
		{	
			//產生公告改版內容
			updateMed7005Db(obj.getId(),"");
			

			//確認完成
			if("20".equals(obj.getPublDept()) || "21".equals(obj.getPublDept()))
			{
				obj.setStatus("50");					//案件公告中
				procDesc = "案件公告者 - 案件公告中";
			}
			else if(null != obj.getMed7002Dbs() && !obj.getMed7002Dbs().isEmpty())
			{
				obj.setStatus("20");					//廠商回覆中
				procDesc = "案件報告者 - 廠商回覆中";
			}
			else
			{
				obj.setStatus("90");					//結案
				procDesc = "結案";
			}
			
		}
		else if("maintain6".equals(getActionType()))
		{		
            String num=View.getLookupField("select count(*) from Med7002Db where med7001Db.id="+Common.getLong(getId())+" and ( iswarning <> '' and iswarning is not null ) ");
			
			int iswarningNum=0;
			
			if(!"".equals(Common.get(num)))
			{
				iswarningNum=Common.getInt(num);
			}

			if(iswarningNum!=obj.getMed7002Dbs().size())
			{
				throw new Exception("本許可證是否為警訊內容產品欄位需填寫!");
			}	

			if("Y".equals(getIstranslate()) )
			{
				obj.setStatus("30");				
				procDesc = "案件摘譯中";
				
				String hql7005=" from Med7005Db where med7001Db.id="+obj.getId();
				
				Med7005Db obj7005 = (Med7005Db) View.getObject(hql7005);

				if(obj7005!=null)
				{	
					
					if(obj.getMed7002Dbs().size()>1)
					{
						obj7005.setTranslatecontext("此警訊案件涉及多家許可證廠商，請參考各廠商摘譯初稿");
					}	
					else
					{	
					   obj7005.setTranslatecontext(getExcerptDraft7002());
					}
					
					ServiceGetter.getInstance().getCommonService().update(obj7005);
				}
				
			}
			else if("N".equals(getIstranslate()) )
			{
				obj.setStatus("90");				
				procDesc = "結案";
			}
		}
		else if("maintain7".equals(getActionType()))
		{		
			obj.setStatus("31");				
			procDesc = "案件摘譯審核中";
		}
		else if("maintain8".equals(getActionType()))
		{		
			obj.setStatus("40");				
			procDesc = "廠商摘譯確認中";
		}
		else if("maintain9".equals(getActionType()))
		{					
			procDesc = "廠商摘譯確認完成";
		}
		else if("maintain10".equals(getActionType()))
		{		
			obj.setStatus("50");
			procDesc = "廠商摘譯確認完成";
			print(getId7005());//PDF
		}
		else if("maintain11".equals(getActionType()))
		{		
			obj.setStatus("90");
			procDesc = "公告完成";
		}
		else if("maintain14".equals(getActionType()))
		{		
			obj.setStatus("61");
			procDesc = "公告改版送出完成";
		}
		else if("maintain15".equals(getActionType()))
		{		
			obj.setStatus("90");
			procDesc = "公告改版複審完成";
			
			String hql7005=" from Med7005Db where med7001Db.id="+obj.getId();
				
			Med7005Db obj7005 = (Med7005Db) View.getObject(hql7005);

			if(obj7005!=null)
			{	
				obj7005.setSubject(getChangesubject7003());//標題
				obj7005.setContext(getChangecontext7003());//內容
				obj7005.setRemark(getChangeremark7003());//備註
				ServiceGetter.getInstance().getCommonService().update(obj7005);
			}
		}
		
		
		ServiceGetter.getInstance().getCommonService().update(obj);
	
		
		
		if (null != delList && !delList.isEmpty()) 
		{
			ServiceGetter.getInstance().getTcbwService().deleteBatch(delList);
		}
		
		if(null != procDesc && !"".equals(procDesc))
		{
	    	//歷程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED06",obj.getId(), obj.getApplNo(), obj.getStatus(), procDesc, TCBWCommon.getCurrentUserId());
		}		
	}
	else 
	{
		throw new Exception("查無資料，無法更新");
	}
}



//產生公告改版內容
public void updateMed7005Db(Long id,String completecheckdate7005) throws Exception 
{
	//Med7005Db obj7005=new Med7005Db();
	
	Med7005Db obj7005= (Med7005Db) View.getObject(" from Med7005Db where med7001Db.id=" + id);
	
	if(obj7005==null)
	{	
	  obj7005=new Med7005Db();
	  Med7001Db master=new Med7001Db();
	  master.setId(id);
	  obj7005.setMed7001Db(master); ;
	}
	
	Med7001Db o = (Med7001Db) View.getObject(" from Med7001Db where id="+id);
	
	String subjectStr="",permitKeyStr="",contextStr="",remarkStr="";
	String chProduct="",enProduct="",manufactorName="",contactStr="";
	
	java.util.Map<String, String> medPermitMap = TCBWCommon.getCommonCodeMap("MEDPKID");
	java.util.Map<String, String> publDeptMap = TCBWCommon.getCommonCodeMap("CONPUBLDEPT");
	  
	if(null != o.getMed7002Dbs() && !o.getMed7002Dbs().isEmpty())
	{
		int i=1;
		
		for(Object dtlObj:o.getMed7002Dbs())
		{
			Med7002Db med7002 = (Med7002Db)dtlObj;
				
			if(subjectStr.length()>0)subjectStr+="、";
					
			subjectStr+=o.getChProduct();//中文品名

			//許可證字號
			if(o.getMed7002Dbs().size()>1)
			{
			   permitKeyStr+="("+i+")"+Common.get(medPermitMap.get(med7002.getPermitKey()))+"字";
			}
			else
			{
			   permitKeyStr+=Common.get(medPermitMap.get(med7002.getPermitKey()))+"字";
			}	

			if(!"".equals(Common.get(med7002.getPermitNo())))
				permitKeyStr+=Common.get(med7002.getPermitNo())+"號";
			
			if(o.getMed7002Dbs().size()>1)
			{
				if(i!=o.getMed7002Dbs().size())
					permitKeyStr+=" ; ";
				
				//產品中文名稱
				chProduct+="("+i+")"+Common.get(med7002.getChProduct());
				
				if(i!=o.getMed7002Dbs().size())
					chProduct+=" ; ";
						
				//產品英文名稱
				enProduct+="("+i+")"+Common.get(med7002.getEnProduct());
				
				if(i!=o.getMed7002Dbs().size())
					enProduct+=" ; ";
				
				//製造廠
				manufactorName+="("+i+")"+Common.get(med7002.getManufactorName());
				
				if(i!=o.getMed7002Dbs().size())
					manufactorName+=" ; ";
				
				//廠商聯繫資訊
				contactStr+="("+i+")"+Common.get(med7002.getApplicationName());
				
			}
			else
			{
				//產品中文名稱
				chProduct+=Common.get(med7002.getChProduct());
				//產品英文名稱
				enProduct+=Common.get(med7002.getEnProduct());
				//製造廠
				manufactorName+=Common.get(med7002.getManufactorName());
				//廠商聯繫資訊
				contactStr+=Common.get(med7002.getApplicationName());
			}	
			
			if(o.getMed7002Dbs().size()==i)
			{	
				permitKeyStr+="\r\n";
				chProduct+="\r\n";
				enProduct+="\r\n";
				manufactorName+="\r\n";
			}
			
			//聯絡窗口
			if(!"".equals(Common.get(med7002.getCheckcontactman())))
				contactStr+="，";
			
			contactStr+=Common.get(med7002.getCheckcontactman());
			
			//聯絡電話
			if(!"".equals(Common.get(med7002.getCheckcontacttel())))
				contactStr+="，";
			
			contactStr+=Common.get(med7002.getCheckcontacttel());
			
			//聯絡信箱
			if(!"".equals(Common.get(med7002.getCheckcontactemail())))
				contactStr+="，";
			
			contactStr+=Common.get(med7002.getCheckcontactemail());
			
			if(i!=o.getMed7002Dbs().size())
				contactStr+=" ; ";
			
			i++;
	
		}
		
		contextStr+="許可證字號:"+Common.get(permitKeyStr);
		contextStr+="製造商:"+Common.get(manufactorName)+"\r\n";
		contextStr+="產品中文名稱:"+Common.get(chProduct)+"\r\n";
		contextStr+="產品英文名稱:"+Common.get(enProduct)+"\r\n";
		contextStr+="受影響規格/型號/批號:"+Common.get(o.getProductlotNo())+"\r\n";
		
		String translatecontext="";
		
		for(Object dtlObj:o.getMed7005Dbs())
		{
			Med7005Db med7005 = (Med7005Db)dtlObj;
			translatecontext=Common.get(med7005.getTranslatecontext());
		}
		
		contextStr+="警訊說明:"+"\r\n"+Common.get(translatecontext)+"\r\n";
		contextStr+="國內矯正措施:"+"\r\n"+Common.get(o.getIncountrysituation())+"\r\n";
		
		remarkStr+="廠商聯繫資訊:"+"\r\n"+Common.get(contactStr)+"\r\n";
	}
	

    remarkStr+="資料來源:"+Common.get(publDeptMap.get(o.getPublDept()))+"\r\n";
    remarkStr+=o.getDatasourWebSite();

	System.out.println("completecheckdate7005==="+completecheckdate7005);
    
	obj7005.setCompletecheckdate(completecheckdate7005);
	
	
	obj7005.setSubject(subjectStr+"回收訊息/安全警訊");//標題
	obj7005.setContext(contextStr);//內容
	obj7005.setRemark(remarkStr);//備註
	
	obj7005.setDatasource(o.getDatasourWebSite());//資料來源
	obj7005.setModifier(getEditID());
	obj7005.setModifyDate(getEditDate());
	obj7005.setModifyTime(getEditTime());

    ServiceGetter.getInstance().getCommonService().saveOrUpdate(obj7005);
	
	
	
}





@Override
public void doDelete() throws Exception 
{
	ServiceGetter.getInstance().getCommonService().delete(Med7001Db.class, Common.getLong(id));
}

public java.util.ArrayList<String[]> doQueryAll0301(String med7001Id)throws Exception 
{
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	
	String hql = " from  Med7002Db  where 1=1 ";
	       hql += " and med7001Db.id=" + Common.getLong(med7001Id);
	
	if("0301".equals(getFormType()) || "0701".equals(getFormType()))
	{    
	   String compegno=View.getLookupField(" select con1005Db.compegno from Con1006Db where commonUser.userId="+ Common.sqlChar(getUserID()));
	   hql += " and applicationID= " + Common.sqlChar(compegno);
	}
	
	System.out.println("doQueryAll0301=="+hql);
	
	int count = ServiceGetter.getInstance().getTcbwService().loadCount(hql);
	
    this.setPageSize(count);
	
	this.processCurrentPageAttribute(count);

	
	if (getTotalRecord() > 0) 
	{
		if (getState().indexOf("query") < 0)
			ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
		
		List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(),this.getPageSize());
		
		if (objList != null && objList.size() > 0) 
		{
			Iterator it = objList.iterator();
			
			java.util.Map<String, String> medpkindMap = TCBWCommon.getCommonCodeMap("MEDPDKIND");
			java.util.Map<String, String> iswarningMap=new java.util.HashMap<String, String>();
			iswarningMap.put("Y","是");iswarningMap.put("N","否");
			iswarningMap.put("W","本公司未登記此產品");iswarningMap.put("O","其他");
			while (it.hasNext()) 
			{
				Med7002Db o = (Med7002Db) it.next();
				String rowArray[] = new String[7];
				rowArray[0] = Common.get(o.getId());
				rowArray[1] = Common.get(o.getReplydate());
				rowArray[2] = Common.get(medpkindMap.get(o.getPermitKey()))+Common.get(o.getPermitNo());
				rowArray[3] = Common.get(o.getChProduct());
				rowArray[4] = Common.get(o.getApplicationName());
				rowArray[5] = Common.get(iswarningMap.get(o.getIswarning()));
				rowArray[6] = Common.get(iswarningMap.get(o.getIseffectinternal()));
				arrList.add(rowArray);
			}
		}
	}
	return arrList;
}

public java.util.ArrayList<String[]> doQueryAll0701(String med7001Id)throws Exception 
{
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	
	String hql = " from  Med7004Db  where 1=1 ";
	       hql += " and med7001Db.id=" + Common.getLong(med7001Id);
	
	       if("0701".equals(getFormType()))
	       {    	   
	          String compegno=View.getLookupField(" select con1005Db.compegno from Con1006Db where commonUser.userId="+ Common.sqlChar(getUserID()));
	          hql += " and applicationID= " + Common.sqlChar(compegno);
	       }
	
	System.out.println("doQueryAll0701=="+hql);
	
	int count = ServiceGetter.getInstance().getTcbwService().loadCount(hql);
	
	this.setPageSize(count);
	
	this.processCurrentPageAttribute(count);
	
	if (getTotalRecord() > 0) 
	{
		if (getState().indexOf("query") < 0)
			ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
		
		List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(),this.getPageSize());
		
		if (objList != null && objList.size() > 0) 
		{
			Iterator it = objList.iterator();

			while (it.hasNext()) 
			{
				Med7004Db o = (Med7004Db) it.next();
				String rowArray[] = new String[5];
				rowArray[0] = Common.get(o.getId());
				rowArray[1] = Common.get(o.getApplicationID());//許可證持有商統編
				rowArray[2] = Common.get(o.getApplicationName());//許可證持有商
				rowArray[3] = Common.get(o.getNoticetranslatedate());//廠商通知日期
				rowArray[4] = Common.get(o.getChecktranslatedate());//廠商確認日期
				arrList.add(rowArray);
			}
		}
	}
	return arrList;
}

public java.util.ArrayList<String[]> doQueryAll1001(String med7001Id)throws Exception 
{
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	
	String hql = " from  Med7003Db  where 1=1 ";
	       hql += " and med7001Db.id=" + Common.getLong(med7001Id);
	
	System.out.println("doQueryAll1001=="+hql);
	
	int count = ServiceGetter.getInstance().getTcbwService().loadCount(hql);
	
	this.setPageSize(count);
	
	this.processCurrentPageAttribute(count);
	
	if (getTotalRecord() > 0) 
	{
		if (getState().indexOf("query") < 0)
			ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
		
		List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(),this.getPageSize());
		
		if (objList != null && objList.size() > 0) 
		{
			Iterator it = objList.iterator();

			while (it.hasNext()) 
			{
				Med7003Db o = (Med7003Db) it.next();
				String rowArray[] = new String[5];
				rowArray[0] = Common.get(o.getId());
				rowArray[1] = Common.get("V"+o.getChangeVersion());
				rowArray[2] = Common.get(o.getChangedate());//改版日期
				rowArray[3] = Common.get(o.getChangereason());//改版理由
				rowArray[4] = Common.get(o.getChangesubject());//改版標題
				arrList.add(rowArray);
			}
		}
	}
	return arrList;
}

//產生備份PDF
public void print(String id) throws Exception 
{
	java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
	
	TableModelReportEnvironment env = new TableModelReportEnvironment();
	
	javax.swing.table.DefaultTableModel model = getDefaultTableModel();

	env.setTableModel(model);
	env.setHtmlImagePattern(Common.getReportImageCachePath());
	env.setJasperFile(ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/vmed/VMED0101.jasper"));
	env.setFormat("PDF");	
    
    TableModelReportGenerator generator = new TableModelReportGenerator(env);
    
    String med = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("MED");
    
    String fileName="";//檔案名稱
    
    String fileDir="MED060004";//存放資料夾
    
    if(!"".equals(getApplNo()))fileName=getApplNo();
   
    File meddir = new File(med);
    
    //判斷資料夾是否存在，若不存在則建立
    if(!meddir.isDirectory())
    {
    	meddir.mkdir();
    }	
    
    File medFileDir = new File(med+"\\"+fileDir);
	
	//判斷資料夾是否存在，若不存在則建立
	if(!medFileDir.isDirectory())
	{
		medFileDir.mkdir();
	}	
	    

    String uploadCaseID = new java.rmi.dgc.VMID().toString();
    
    uploadCaseID = uploadCaseID.replaceAll(":","_");
    
    File dir = new File(med+"\\"+fileDir+"\\"+uploadCaseID);
    
    //判斷資料夾是否存在，若不存在則建立
    if(!dir.isDirectory())
    {
    	dir.mkdir();
    }	
  
    File output = new File(med+"\\"+fileDir+"\\"+uploadCaseID+"\\"+fileName+".pdf");
    
    //產生檔案存放
    generator.reportToFile(output, parms);
    
    Con0001Db o = new Con0001Db();
    
    o.setFileKind("MED");
    o.setUpLoadId(Common.getLong(id));
    o.setFileRoute(fileDir+":;:"+uploadCaseID);
    o.setFileName(fileName+".pdf");
    o.setFileExplan("PDF");
    o.setIsInsert("N");
    o.setIsDelete("N");
    o.setSystemType("MED060004");
    o.setDbName("Med7005Db");
    o.setCreator(getUserID());
    o.setCreateDate(Datetime.getYYYMMDD());
    o.setCreateTime(Datetime.getHHMMSS());

    ServiceGetter.getInstance().getCommonService().save(o);

    env.clear();

}

//案件存檔
public DefaultTableModel getDefaultTableModel() throws Exception
{		
	DefaultTableModel model = new javax.swing.table.DefaultTableModel();
	String[] cols = new String[] {"id","context","title","under"};	
	
	java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();


	Med7001Db obj = (Med7001Db) View.getObject(" from Med7001Db where id=" + Common.getLong(getId()));
	
	if (obj!=null) 
	{
		
		  Object rowArray[]=new Object[cols.length];
		
		  String title="",contextStr="",under="";
		
		  String subjectStr="",permitKeyStr="";
		  String chProduct="",enProduct="",manufactorName="",contactStr="";
		  
		  java.util.Map<String, String> medPermitMap = TCBWCommon.getCommonCodeMap("MEDPKID");
		  java.util.Map<String, String> publDeptMap = TCBWCommon.getCommonCodeMap("CONPUBLDEPT");
		  
		  if(null != obj.getMed7002Dbs() && !obj.getMed7002Dbs().isEmpty())
		  {
			  
			int i=1;  
			
			for(Object dtlObj:obj.getMed7002Dbs())
			{
				Med7002Db med7002 = (Med7002Db)dtlObj;
					
				
				//許可證字號
				if(obj.getMed7002Dbs().size()>1)
				{
				  permitKeyStr+="("+i+")"+Common.get(medPermitMap.get(med7002.getPermitKey()))+"字";
				}
				else
				{
				  permitKeyStr+=Common.get(medPermitMap.get(med7002.getPermitKey()))+"字";
				}	
				
				if(!"".equals(Common.get(med7002.getPermitNo())))
					permitKeyStr+=Common.get(med7002.getPermitNo())+"號";
				
					
				//中文品名
				if(subjectStr.length()>0)subjectStr+="、";
				subjectStr+=obj.getChProduct();
				
				if(obj.getMed7002Dbs().size()>1)
				{
					if(i!=obj.getMed7002Dbs().size())
						permitKeyStr+=" ; ";
					
					//產品中文名稱
					chProduct+="("+i+")"+med7002.getChProduct();
					if(i!=obj.getMed7002Dbs().size())
						chProduct+=" ; ";
					
					//產品英文名稱
					enProduct+="("+i+")"+med7002.getEnProduct();
					if(i!=obj.getMed7002Dbs().size())
						enProduct+=" ; ";
					
					//製造廠
					manufactorName+="("+i+")"+med7002.getManufactorName();
					if(i!=obj.getMed7002Dbs().size())
						manufactorName+=" ; ";
					
					//廠商聯繫資訊
					contactStr+="("+i+")"+med7002.getApplicationName();
					
				}
				else
				{
					//產品中文名稱
					chProduct+=med7002.getChProduct();
					
					//產品英文名稱
					enProduct+=med7002.getEnProduct();
					
					//製造廠
					manufactorName+=med7002.getManufactorName();
					
					//廠商聯繫資訊
					contactStr+=med7002.getApplicationName();
					
				}	
				//聯絡窗口
				if(!"".equals(med7002.getCheckcontactman()))
					contactStr+="，";
				
				contactStr+=med7002.getCheckcontactman();
				
				//聯絡電話
				if(!"".equals(med7002.getCheckcontacttel()))
					contactStr+="，";
				
				contactStr+=med7002.getCheckcontacttel();
				
				//聯絡信箱
				if(!"".equals(med7002.getCheckcontactemail()))
					contactStr+="，";

				contactStr+=med7002.getCheckcontactemail();

				if(i!=obj.getMed7002Dbs().size())
					contactStr+=" ; ";
				
				i++;
			}
			
			String translatecontext=View.getLookupField("select translatecontext from Med7005Db where med7001Db.id="+Common.getLong(id));
			
			contextStr+="許可證字號:"+permitKeyStr+"\n\n";
			contextStr+="製造商:"+manufactorName+"\n\n";
			contextStr+="產品中文名稱:"+chProduct+"\n\n";
			contextStr+="產品英文名稱:"+enProduct+"\n\n";
			contextStr+="受影響規格\\型號\\批號:"+Common.get(obj.getMedModel())+"\n\n";
			contextStr+="警訊說明:"+"\n\n"+Common.get(translatecontext)+"\n\n";
			contextStr+="國內矯正措施:"+"\n\n"+Common.get(obj.getIncountrysituation())+"\n\n";
			contextStr+="廠商聯繫資訊:"+"\n\n"+contactStr;
			contextStr+="\n\n"+"附件(提供下載)";
		  }
			

		under+=Common.get(publDeptMap.get(obj.getPublDept()));
		under+="\n"+obj.getDatasourWebSite();

		
		if("Y".equals(obj.getIsrecycle())) {
			title+=subjectStr+"回收訊息";
		} else if("N".equals(obj.getIsrecycle())) {
			title+=subjectStr+"安全警訊";
		}
		

		rowArray[0]=Common.get(id);
		rowArray[1]=Common.get(contextStr);
		rowArray[2]=Common.get(title);
		rowArray[3]=Common.get(under);
		arrList.add(rowArray);
		
		
	}
	if (arrList!=null && arrList.size()>0) 
	{
		Object[][] rs = new Object[0][0];
		rs = (Object[][])arrList.toArray(rs);
		model.setDataVector(rs, cols);
	}
	
	return model;
	
}



public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }
public String getApplNo(){ return checkGet(applNo); }
public void setApplNo(String s){ applNo=checkSet(s); }
public String getStatus(){ return checkGet(status); }
public void setStatus(String s){ status=checkSet(s); }
public String getPublDept(){ return checkGet(publDept); }
public void setPublDept(String s){ publDept=checkSet(s); }
public String getPublDeptName(){ return checkGet(publDeptName); }
public void setPublDeptName(String s){ publDeptName=checkSet(s); }
public String getPublDeptCodeId(){ return checkGet(publDeptCodeId); }
public void setPublDeptCodeId(String s){ publDeptCodeId=checkSet(s); }
public String getSituation(){ return checkGet(situation); }
public void setSituation(String s){ situation=checkSet(s); }
public String getMedMainCategory(){ return checkGet(medMainCategory); }
public void setMedMainCategory(String s){ medMainCategory=checkSet(s); }
public String getMedMainCategoryName(){ return checkGet(medMainCategoryName); }
public void setMedMainCategoryName(String s){ medMainCategoryName=checkSet(s); }
public String getMedMainCategoryCodeId(){ return checkGet(medMainCategoryCodeId); }
public void setMedMainCategoryCodeId(String s){ medMainCategoryCodeId=checkSet(s); }
public String getChProduct(){ return checkGet(chProduct); }
public void setChProduct(String s){ chProduct=checkSet(s); }
public String getRecycleclass(){ return checkGet(recycleclass); }
public void setRecycleclass(String s){ recycleclass=checkSet(s); }
public String getDataRevDate(){ return checkGet(dataRevDate); }
public void setDataRevDate(String s){ dataRevDate=checkSet(s); }
public String getPublDate(){ return checkGet(publDate); }
public void setPublDate(String s){ publDate=checkSet(s); }
public String getManufactorName(){ return checkGet(manufactorName); }
public void setManufactorName(String s){ manufactorName=checkSet(s); }
public String getContextsummary(){ return checkGet(contextsummary); }
public void setContextsummary(String s){ contextsummary=checkSet(s); }
public String getProductlotNo(){ return checkGet(productlotNo); }
public void setProductlotNo(String s){ productlotNo=checkSet(s); }
public String getApplicationaction(){ return checkGet(applicationaction); }
public void setApplicationaction(String s){ applicationaction=checkSet(s); }
public String getEffectarea(){ return checkGet(effectarea); }
public void setEffectarea(String s){ effectarea=checkSet(s); }
public String getEffectnum(){ return checkGet(effectnum); }
public void setEffectnum(String s){ effectnum=checkSet(s); }
public String getDatasourWebSite(){ return datasourWebSite; }
public void setDatasourWebSite(String s){ this.datasourWebSite=s; }
public String getTabId() {return checkGet(tabId);}
public void setTabId(String s) {this.tabId = checkSet(s);}
public String getActionType() {return checkGet(actionType);}
public void setActionType(String s) {this.actionType = checkSet(s);}
public String getFormType() {return checkGet(formType);}
public void setFormType(String s) {this.formType = checkSet(s);}
public String getQ_permitKeyNo() {return checkGet(q_permitKeyNo);}
public void setQ_permitKeyNo(String s) {this.q_permitKeyNo = checkSet(s);}
public String getPermitKey7002() {
	return checkGet(permitKey7002);
}
public void setPermitKey7002(String permitKey7002) {
	this.permitKey7002 = checkSet(permitKey7002);
}
public String getPermitNo7002() {
	return checkGet(permitNo7002);
}
public void setPermitNo7002(String permitNo7002) {
	this.permitNo7002 = checkSet(permitNo7002);
}
public String getChProduct7002() {
	return checkGet(chProduct7002);
}
public void setChProduct7002(String chProduct7002) {
	this.chProduct7002 = checkSet(chProduct7002);
}
public String getEnProduct7002() {
	return checkGet(enProduct7002);
}
public void setEnProduct7002(String enProduct7002) {
	this.enProduct7002 = checkSet(enProduct7002);
}
public String getMedapprovedate7002() {
	return checkGet(medapprovedate7002);
}
public void setMedapprovedate7002(String medapprovedate7002) {
	this.medapprovedate7002 = checkSet(medapprovedate7002);
}
public String getMedEffectiveDate7002() {
	return checkGet(medEffectiveDate7002);
}
public void setMedEffectiveDate7002(String medEffectiveDate7002) {
	this.medEffectiveDate7002 = checkSet(medEffectiveDate7002);
}
public String getApplicationId7002() {
	return checkGet(applicationId7002);
}
public void setApplicationId7002(String applicationId7002) {
	this.applicationId7002 = checkSet(applicationId7002);
}
public String getApplicationName7002() {
	return checkGet(applicationName7002);
}
public void setApplicationName7002(String applicationName7002) {
	this.applicationName7002 = checkSet(applicationName7002);
}
public String getManufactorName7002() {
	return checkGet(manufactorName7002);
}
public void setManufactorName7002(String manufactorName7002) {
	this.manufactorName7002 = checkSet(manufactorName7002);
}
public String getManufactorCountry7002() {
	return checkGet(manufactorCountry7002);
}
public void setManufactorCountry7002(String manufactorCountry7002) {
	this.manufactorCountry7002 = checkSet(manufactorCountry7002);
}
public String getMedclass7002() {
	return checkGet(medclass7002);
}
public void setMedclass7002(String medclass7002) {
	this.medclass7002 = checkSet(medclass7002);
}
public String getMedMainCategory7002() {
	return checkGet(medMainCategory7002);
}
public void setMedMainCategory7002(String medMainCategory7002) {
	this.medMainCategory7002 = checkSet(medMainCategory7002);
}
public String getMedSecCategory7002() {
	return checkGet(medSecCategory7002);
}
public void setMedSecCategory7002(String medSecCategory7002) {
	this.medSecCategory7002 = checkSet(medSecCategory7002);
}
public String getReplydate7002() {
	return checkGet(replydate7002);
}
public void setReplydate7002(String replydate7002) {
	this.replydate7002 = checkSet(replydate7002);
}
public String getId7002() {
	return checkGet(id7002);
}
public void setId7002(String id7002) {
	this.id7002 = checkSet(id7002);
}
public String getChangeTabValue() {
	return checkGet(changeTabValue);
}
public void setChangeTabValue(String changeTabValue) {
	this.changeTabValue = checkSet(changeTabValue);
}
public String getReplysummary7002() {
	return checkGet(replysummary7002);
}
public void setReplysummary7002(String replysummary7002) {
	this.replysummary7002 = checkSet(replysummary7002);
}
public String getMedModel7002() {
	return checkGet(medModel7002);
}
public void setMedModel7002(String medModel7002) {
	this.medModel7002 = checkSet(medModel7002);
}
public String getLotNo7002() {
	return checkGet(lotNo7002);
}
public void setLotNo7002(String lotNo7002) {
	this.lotNo7002 = checkSet(lotNo7002);
}
public String getEffectnum7002() {
	return checkGet(effectnum7002);
}
public void setEffectnum7002(String effectnum7002) {
	this.effectnum7002 = checkSet(effectnum7002);
}
public String getCheckcontactman7002() {
	return checkGet(checkcontactman7002);
}
public void setCheckcontactman7002(String checkcontactman7002) {
	this.checkcontactman7002 = checkSet(checkcontactman7002);
}
public String getCheckcontacttel7002() {
	return checkGet(checkcontacttel7002);
}
public void setCheckcontacttel7002(String checkcontacttel7002) {
	this.checkcontacttel7002 = checkSet(checkcontacttel7002);
}
public String getCheckcontactemail7002() {
	return checkGet(checkcontactemail7002);
}
public void setCheckcontactemail7002(String checkcontactemail7002) {
	this.checkcontactemail7002 = checkSet(checkcontactemail7002);
}
public String getIswarning7002() {
	return checkGet(iswarning7002);
}
public void setIswarning7002(String iswarning7002) {
	this.iswarning7002 = checkSet(iswarning7002);
}
public String getIseffectinternal7002() {
	return checkGet(iseffectinternal7002);
}
public void setIseffectinternal7002(String iseffectinternal7002) {
	this.iseffectinternal7002 = checkSet(iseffectinternal7002);
}
public String getIsrecycle() {
	return checkGet(isrecycle);
}
public void setIsrecycle(String isrecycle) {
	this.isrecycle = checkSet(isrecycle);
}
public String getIstranslate() {
	return checkGet(istranslate);
}
public void setIstranslate(String istranslate) {
	this.istranslate = checkSet(istranslate);
}
public String getTranslateman() {
	return checkGet(translateman);
}
public void setTranslateman(String translateman) {
	this.translateman = checkSet(translateman);
}
public String getMedModel() {
	return checkGet(medModel);
}
public void setMedModel(String medModel) {
	this.medModel = checkSet(medModel);
}
public String getLotNo() {
	return checkGet(lotNo);
}
public void setLotNo(String lotNo) {
	this.lotNo = checkSet(lotNo);
}
public String getIncountrysituation() {
	return checkGet(incountrysituation);
}
public void setIncountrysituation(String incountrysituation) {
	this.incountrysituation = checkSet(incountrysituation);
}
public String getTranslatedate7005() {
	return checkGet(translatedate7005);
}
public void setTranslatedate7005(String translatedate7005) {
	this.translatedate7005 = checkSet(translatedate7005);
}
public String getTranslatecontext7005() {
	return checkGet(translatecontext7005);
}
public void setTranslatecontext7005(String translatecontext7005) {
	this.translatecontext7005 = checkSet(translatecontext7005);
}
public String getRecheckdate7005() {
	return checkGet(recheckdate7005);
}
public void setRecheckdate7005(String recheckdate7005) {
	this.recheckdate7005 = checkSet(recheckdate7005);
}
public String getId7005() {
	return checkGet(id7005);
}
public void setId7005(String id7005) {
	this.id7005 = checkSet(id7005);
}
public String getId7004() {
	return checkGet(id7004);
}
public void setId7004(String id7004) {
	this.id7004 = checkSet(id7004);
}
public String getNoticetranslatedate7004() {
	return checkGet(noticetranslatedate7004);
}
public void setNoticetranslatedate7004(String noticetranslatedate7004) {
	this.noticetranslatedate7004 = checkSet(noticetranslatedate7004);
}
public String getChecktranslatedate7004() {
	return checkGet(checktranslatedate7004);
}
public void setChecktranslatedate7004(String checktranslatedate7004) {
	this.checktranslatedate7004 = checkSet(checktranslatedate7004);
}
public String getCheckcontextdesc7004() {
	return checkGet(checkcontextdesc7004);
}
public void setCheckcontextdesc7004(String checkcontextdesc7004) {
	this.checkcontextdesc7004 = checkSet(checkcontextdesc7004);
}
public String getApplicationID7004() {
	return checkGet(applicationID7004);
}
public void setApplicationID7004(String applicationID7004) {
	this.applicationID7004 = checkSet(applicationID7004);
}
public String getApplicationName7004() {
	return checkGet(applicationName7004);
}
public void setApplicationName7004(String applicationName7004) {
	this.applicationName7004 = checkSet(applicationName7004);
}
public String getPubldate7005() {
	return checkGet(publdate7005);
}
public void setPubldate7005(String publdate7005) {
	this.publdate7005 = checkSet(publdate7005);
}
public String getSubject7005() {
	return checkGet(subject7005);
}
public void setSubject7005(String subject7005) {
	this.subject7005 = checkSet(subject7005);
}
public String getContext7005() {
	return checkGet(context7005);
}
public void setContext7005(String context7005) {
	this.context7005 = checkSet(context7005);
}
public String getRemark7005() {
	return checkGet(remark7005);
}
public void setRemark7005(String remark7005) {
	this.remark7005 = checkSet(remark7005);
}
public String getDatasource7005() {
	return checkGet(datasource7005);
}
public void setDatasource7005(String datasource7005) {
	this.datasource7005 = checkSet(datasource7005);
}
public String getIspublnews7005() {
	return checkGet(ispublnews7005);
}
public void setIspublnews7005(String ispublnews7005) {
	this.ispublnews7005 = checkSet(ispublnews7005);
}
public String getIspublconsumer7005() {
	return checkGet(ispublconsumer7005);
}
public void setIspublconsumer7005(String ispublconsumer7005) {
	this.ispublconsumer7005 = checkSet(ispublconsumer7005);
}
public String getPublconsumerDate7005() {
	return checkGet(publconsumerDate7005);
}
public void setPublconsumerDate7005(String publconsumerDate7005) {
	this.publconsumerDate7005 = checkSet(publconsumerDate7005);
}
public String getIsfdaweb7005() {
	return checkGet(isfdaweb7005);
}
public void setIsfdaweb7005(String isfdaweb7005) {
	this.isfdaweb7005 = checkSet(isfdaweb7005);
}
public String getLamp7005() {
	return checkGet(lamp7005);
}
public void setLamp7005(String lamp7005) {
	this.lamp7005 = checkSet(lamp7005);
}
public String getId7003() {
	return checkGet(id7003);
}
public void setId7003(String id7003) {
	this.id7003 = checkSet(id7003);
}
public String getChangedate7003() {
	return checkGet(changedate7003);
}
public void setChangedate7003(String changedate7003) {
	this.changedate7003 = checkSet(changedate7003);
}
public String getChangereason7003() {
	return checkGet(changereason7003);
}
public void setChangereason7003(String changereason7003) {
	this.changereason7003 = checkSet(changereason7003);
}
public String getChangesubject7003() {
	return checkGet(changesubject7003);
}
public void setChangesubject7003(String changesubject7003) {
	this.changesubject7003 = checkSet(changesubject7003);
}
public String getChangecontext7003() {
	return checkGet(changecontext7003);
}
public void setChangecontext7003(String changecontext7003) {
	this.changecontext7003 = checkSet(changecontext7003);
}
public String getChangeremark7003() {
	return checkGet(changeremark7003);
}
public void setChangeremark7003(String changeremark7003) {
	this.changeremark7003 = checkSet(changeremark7003);
}
public String getChangerecheckdate7003() {
	return checkGet(changerecheckdate7003);
}
public void setChangerecheckdate7003(String changerecheckdate7003) {
	this.changerecheckdate7003 = checkSet(changerecheckdate7003);
}
public String getChangeVersion7003() {
	return checkGet(changeVersion7003);
}
public void setChangeVersion7003(String changeVersion7003) {
	this.changeVersion7003 = checkSet(changeVersion7003);
}
public String getChangeMaxVersion7003() {
	return checkGet(changeMaxVersion7003);
}
public void setChangeMaxVersion7003(String changeMaxVersion7003) {
	this.changeMaxVersion7003 = checkSet(changeMaxVersion7003);
}
public String getFdareceiveDate() {
	return checkGet(fdareceiveDate);
}
public void setFdareceiveDate(String fdareceiveDate) {
	this.fdareceiveDate = checkSet(fdareceiveDate);
}
public String getFdareceiveNo() {
	return checkGet(fdareceiveNo);
}
public void setFdareceiveNo(String fdareceiveNo) {
	this.fdareceiveNo = checkSet(fdareceiveNo);
}
public String getReceivesummary() {
	return checkGet(receivesummary);
}
public void setReceivesummary(String receivesummary) {
	this.receivesummary = checkSet(receivesummary);
}
public String getPostdate() {
	return checkGet(postdate);
}
public void setPostdate(String postdate) {
	this.postdate = checkSet(postdate);
}
public String getPosesummary() {
	return checkGet(posesummary);
}
public void setPosesummary(String posesummary) {
	this.posesummary = checkSet(posesummary);
}
public String getCompleterecycledate() {
	return checkGet(completerecycledate);
}
public void setCompleterecycledate(String completerecycledate) {
	this.completerecycledate = checkSet(completerecycledate);
}
public String getCompletesummary() {
	return checkGet(completesummary);
}
public void setCompletesummary(String completesummary) {
	this.completesummary = checkSet(completesummary);
}
public String getShowFileName() {
	return checkGet(showFileName);
}
public void setShowFileName(String showFileName) {
	this.showFileName = checkSet(showFileName);
}
public String getFileName() {
	return checkGet(fileName);
}
public void setFileName(String fileName) {
	this.fileName = checkSet(fileName);
}
public String getFileNameRoute() {
	return checkGet(fileNameRoute);
}
public void setFileNameRoute(String fileNameRoute) {
	this.fileNameRoute = checkSet(fileNameRoute);
}
public String getCompletecheckdate7005() {
	return checkGet(completecheckdate7005);
}
public void setCompletecheckdate7005(String completecheckdate7005) {
	this.completecheckdate7005 = checkSet(completecheckdate7005);
}
public String getIswarningOther7002() {
	return checkGet(iswarningOther7002);
}
public void setIswarningOther7002(String iswarningOther7002) {
	this.iswarningOther7002 = checkSet(iswarningOther7002);
}
public String getApplyObject7002() {
	return checkGet(applyObject7002);
}
public void setApplyObject7002(String applyObject7002) {
	this.applyObject7002 = checkSet(applyObject7002);
}
public String getProposedAction7002() {
	return checkGet(proposedAction7002);
}
public void setProposedAction7002(String proposedAction7002) {
	this.proposedAction7002 = checkSet(proposedAction7002);
}
public String getExcerptDraft7002() {
	return checkGet(excerptDraft7002);
}
public void setExcerptDraft7002(String excerptDraft7002) {
	this.excerptDraft7002 = checkSet(excerptDraft7002);
}






}


