package com.kangdainfo.tcbw.view.vdrg;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.*;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.*;
import com.kangdainfo.tcbw.util.TCBWCommon;

/**
*<br>程式目的：國內外藥品品質警訊登錄作業
*<br>程式代號：vdrg0100f
*<br>程式日期：1030424
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class VDRG0100F extends SuperBean{


String id;
String applNo;
String status;
String dataRevDate;
String publDept;
String publDeptName;
String publDeptCodeId;
String istransfer;
String transferdept;
String transferdeptName;
String transferdeptCodeId;
String datasourWebSite;
String situation;
String publDate;
String recycleNum;
String chProduct;
String scientificName;
String[] warningmedModel;
String druggist;
String manufactorName;
String lotNo;
String[] qualitywarningtype;
String eventDesc;
String recycleType;
String indication;
String iswarning;
String warningremark;
String actionType;
String formType;
String formInsert;
String tabId;
String drg72Id;

//廠商回覆資料
String permitStr;
String replyDate;
String replyDesc;
String isImport;
String nonImportReason;
String nonImportReasonDesc;
String eventapplNo;

//評估資料
String estimateDate;
String ispublnews;
String ispublconsumer;
String publconsumerDate;
String lamp;
String aftereffect, aftereffectCodeId, aftereffectName;
String aftereffectOther;
String estimateremark;
String isShowT2Tab;

String toUpdate;   //維持update狀態

String q_lampPrint;

String q_applNoS, q_applNoE;
String q_dataRevDateS, q_dataRevDateE;
String[] q_status;
String q_publDateS, q_publDateE;
String q_publDept;
String q_publDeptName;
String q_publDeptCodeId;
String q_istransfer;
String[] q_situation;
String q_chProduct;
String q_scientificName;
String q_druggist;
String q_permitKey;
String q_permitNo;
String q_manufactorName;
String q_applicationName;
String q_lotNo;
String[] q_qualitywarningtype;
String q_isImport;
String[] q_recycleType;
String[] q_lamp;
String q_aftereffect;
String q_iswarning;
String[] q_isReply;
String q_isTrans;  	//是否為歷史移轉資料


public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }
public String getApplNo(){ return checkGet(applNo); }
public void setApplNo(String s){ applNo=checkSet(s); }
public String getStatus(){ return checkGet(status); }
public void setStatus(String s){ status=checkSet(s); }
public String getDataRevDate(){ return checkGet(dataRevDate); }
public void setDataRevDate(String s){ dataRevDate=checkSet(s); }
public String getPublDept(){ return checkGet(publDept); }
public void setPublDept(String s){ publDept=checkSet(s); }
public String getPublDeptName(){ return checkGet(publDeptName); }
public void setPublDeptName(String s){ publDeptName=checkSet(s); }
public String getPublDeptCodeId(){ return checkGet(publDeptCodeId); }
public void setPublDeptCodeId(String s){ publDeptCodeId=checkSet(s); }
public String getIstransfer(){ return checkGet(istransfer); }
public void setIstransfer(String s){ istransfer=checkSet(s); }
public String getTransferdept(){ return checkGet(transferdept); }
public void setTransferdept(String s){ transferdept=checkSet(s); }
public String getTransferdeptName(){ return checkGet(transferdeptName); }
public void setTransferdeptName(String s){ transferdeptName=checkSet(s); }
public String getTransferdeptCodeId(){ return checkGet(transferdeptCodeId); }
public void setTransferdeptCodeId(String s){ transferdeptCodeId=checkSet(s); }
public String getDatasourWebSite(){ return checkGet(datasourWebSite); }
public void setDatasourWebSite(String s){ datasourWebSite=checkSet(s); }
public String getSituation(){ return checkGet(situation); }
public void setSituation(String s){ situation=checkSet(s); }
public String getPublDate(){ return checkGet(publDate); }
public void setPublDate(String s){ publDate=checkSet(s); }
public String getRecycleNum(){ return checkGet(recycleNum); }
public void setRecycleNum(String s){ recycleNum=checkSet(s); }
public String getChProduct(){ return checkGet(chProduct); }
public void setChProduct(String s){ chProduct=checkSet(s); }
public String getScientificName(){ return checkGet(scientificName); }
public void setScientificName(String s){ scientificName=checkSet(s); }
public String[] getWarningmedModel() {return checkGet(warningmedModel);}
public void setWarningmedModel(String[] warningmedModel) {this.warningmedModel = checkSet(warningmedModel);}
public String getDruggist(){ return checkGet(druggist); }
public void setDruggist(String s){ druggist=checkSet(s); }
public String getManufactorName(){ return checkGet(manufactorName); }
public void setManufactorName(String s){ manufactorName=checkSet(s); }
public String getLotNo(){ return checkGet(lotNo); }
public void setLotNo(String s){ lotNo=checkSet(s); }
public String[] getQualitywarningtype() {return checkGet(qualitywarningtype);}
public void setQualitywarningtype(String[] qualitywarningtype) {this.qualitywarningtype = checkSet(qualitywarningtype);}
public String getEventDesc(){ return checkGet(eventDesc); }
public void setEventDesc(String s){ eventDesc=checkSet(s); }
public String getRecycleType(){ return checkGet(recycleType); }
public void setRecycleType(String s){ recycleType=checkSet(s); }
public String getIndication(){ return checkGet(indication); }
public void setIndication(String s){ indication=checkSet(s); }
public String getIswarning(){ return checkGet(iswarning); }
public void setIswarning(String s){ iswarning=checkSet(s); }
public String getWarningremark(){ return checkGet(warningremark); }
public void setWarningremark(String s){ warningremark=checkSet(s); }
public String getActionType() {return checkGet(actionType);}
public void setActionType(String s) {this.actionType = checkSet(s);}
public String getFormType() {return checkGet(formType);}
public void setFormType(String s) {this.formType = checkSet(s);}
public String getTabId() {return checkGet(tabId);}
public void setTabId(String s) {this.tabId = checkSet(s);}
public String getDrg72Id() {return checkGet(drg72Id);}
public void setDrg72Id(String s) {this.drg72Id = checkSet(s);}
public String getPermitStr() {return checkGet(permitStr);}
public void setPermitStr(String s) {this.permitStr = checkSet(s);}
public String getReplyDate() {return checkGet(replyDate);}
public void setReplyDate(String s) {this.replyDate = checkSet(s);}
public String getReplyDesc() {return checkGet(replyDesc);}
public void setReplyDesc(String s) {this.replyDesc = checkSet(s);}
public String getIsImport() {return checkGet(isImport);}
public void setIsImport(String s) {this.isImport = checkSet(s);}
public String getNonImportReason() {return checkGet(nonImportReason);}
public void setNonImportReason(String s) {this.nonImportReason = checkSet(s);}
public String getNonImportReasonDesc() {return checkGet(nonImportReasonDesc);}
public void setNonImportReasonDesc(String s) {this.nonImportReasonDesc = checkSet(s);}
public String getEventapplNo() {return checkGet(eventapplNo);}
public void setEventapplNo(String s) {this.eventapplNo = checkSet(s);}
public String getEstimateDate() {return checkSet(estimateDate);}
public void setEstimateDate(String s) {this.estimateDate = checkSet(s);}
public String getIspublnews() {return checkSet(ispublnews);}
public void setIspublnews(String s) {this.ispublnews = checkSet(s);}
public String getIspublconsumer() {return checkSet(ispublconsumer);}
public void setIspublconsumer(String s) {this.ispublconsumer = checkSet(s);}
public String getPublconsumerDate() {return checkSet(publconsumerDate);}
public void setPublconsumerDate(String s) {this.publconsumerDate = checkSet(s);}
public String getLamp() {return checkSet(lamp);}
public void setLamp(String s) {this.lamp = checkSet(s);}
public String getAftereffect() {return checkSet(aftereffect);}
public void setAftereffect(String s) {this.aftereffect = checkSet(s);}
public String getAftereffectCodeId() {return checkSet(aftereffectCodeId);}
public void setAftereffectCodeId(String s) {this.aftereffectCodeId = checkSet(s);}
public String getAftereffectName() {return checkSet(aftereffectName);}
public void setAftereffectName(String s) {this.aftereffectName = checkSet(s);}
public String getAftereffectOther() {return checkSet(aftereffectOther);}
public void setAftereffectOther(String s) {this.aftereffectOther = checkSet(s);}
public String getEstimateremark() {return checkSet(estimateremark);}
public void setEstimateremark(String s) {this.estimateremark = checkSet(s);}
public String getIsShowT2Tab() {return checkSet(isShowT2Tab);}
public void setIsShowT2Tab(String s) {this.isShowT2Tab = checkSet(s);}

public String getToUpdate() {return toUpdate;}
public void setToUpdate(String toUpdate) {this.toUpdate = toUpdate;}

public String getQ_lampPrint() {return checkSet(q_lampPrint);}
public void setQ_lampPrint(String s) {this.q_lampPrint = checkSet(s);}


public String getFormInsert() {return checkGet(formInsert);}
public void setFormInsert(String formInsert) {this.formInsert = checkSet(formInsert);}

public String getQ_applNoS(){ return checkGet(q_applNoS); }
public void setQ_applNoS(String s){ q_applNoS=checkSet(s); }
public String getQ_applNoE(){ return checkGet(q_applNoE); }
public void setQ_applNoE(String s){ q_applNoE=checkSet(s); }
public String getQ_dataRevDateS(){ return checkGet(q_dataRevDateS); }
public void setQ_dataRevDateS(String s){ q_dataRevDateS=checkSet(s); }
public String getQ_dataRevDateE(){ return checkGet(q_dataRevDateE); }
public void setQ_dataRevDateE(String s){ q_dataRevDateE=checkSet(s); }
public String[] getQ_status() {return q_status;}
public void setQ_status(String[] s) {this.q_status = checkSet(s);}
public String getQ_publDateS(){ return checkGet(q_publDateS); }
public void setQ_publDateS(String s){ q_publDateS=checkSet(s); }
public String getQ_publDateE(){ return checkGet(q_publDateE); }
public void setQ_publDateE(String s){ q_publDateE=checkSet(s); }
public String getQ_publDept(){ return checkGet(q_publDept); }
public void setQ_publDept(String s){ q_publDept=checkSet(s); }
public String getQ_publDeptName(){ return checkGet(q_publDeptName); }
public void setQ_publDeptName(String s){ q_publDeptName=checkSet(s); }
public String getQ_publDeptCodeId(){ return checkGet(q_publDeptCodeId); }
public void setQ_publDeptCodeId(String s){ q_publDeptCodeId=checkSet(s); }
public String getQ_istransfer(){ return checkGet(q_istransfer); }
public void setQ_istransfer(String s){ q_istransfer=checkSet(s); }
public String[] getQ_situation(){ return q_situation; }
public void setQ_situation(String[] s){ q_situation = s; }
public String getQ_chProduct(){ return checkGet(q_chProduct); }
public void setQ_chProduct(String s){ q_chProduct=checkSet(s); }
public String getQ_scientificName(){ return checkGet(q_scientificName); }
public void setQ_scientificName(String s){ q_scientificName=checkSet(s); }
public String getQ_druggist(){ return checkGet(q_druggist); }
public void setQ_druggist(String s){ q_druggist=checkSet(s); }
public String getQ_permitKey(){ return checkGet(q_permitKey); }
public void setQ_permitKey(String s){ q_permitKey=checkSet(s); }
public String getQ_permitNo(){ return checkGet(q_permitNo); }
public void setQ_permitNo(String s){ q_permitNo=checkSet(s); }
public String getQ_manufactorName(){ return checkGet(q_manufactorName); }
public void setQ_manufactorName(String s){ q_manufactorName=checkSet(s); }
public String getQ_applicationName(){ return checkGet(q_applicationName); }
public void setQ_applicationName(String s){ q_applicationName=checkSet(s); }
public String getQ_lotNo(){ return checkGet(q_lotNo); }
public void setQ_lotNo(String s){ q_lotNo=checkSet(s); }

public String[] getQ_qualitywarningtype() {return checkGet(q_qualitywarningtype);}
public void setQ_qualitywarningtype(String[] qQualitywarningtype) {q_qualitywarningtype = checkSet(qQualitywarningtype);}
public String getQ_isImport(){ return checkGet(q_isImport); }
public void setQ_isImport(String s){ q_isImport=checkSet(s); }
public String[] getQ_recycleType(){ return q_recycleType; }
public void setQ_recycleType(String[] s){ q_recycleType=s; }
public String[] getQ_lamp(){ return q_lamp; }
public void setQ_lamp(String[] s){ q_lamp=s; }
public String getQ_aftereffect(){ return checkGet(q_aftereffect); }
public void setQ_aftereffect(String s){ q_aftereffect=checkSet(s); }
public String getQ_iswarning(){ return checkGet(q_iswarning); }
public void setQ_iswarning(String s){ q_iswarning=checkSet(s); }
public String[] getQ_isReply() {return q_isReply;}
public void setQ_isReply(String[] q_isReply) {this.q_isReply = q_isReply;}
public String getQ_isTrans() {	return checkGet(q_isTrans);	}
public void setQ_isTrans(String q_isTrans) { this.q_isTrans = checkSet(q_isTrans);	}

javax.servlet.ServletRequest httpRequest;
public javax.servlet.ServletRequest getHttpRequest() {return httpRequest;}
public void setHttpRequest(javax.servlet.ServletRequest r) {this.httpRequest = r;}

//========== 國內外藥品品質警訊許可證資料檔 =================
String[] drg73Row;
public String[] getDrg73Row() {return drg73Row;}
public void setDrg73Row(String[] drg73Row) {this.drg73Row = drg73Row;}
String drg73JSBuilder;
public String getDrg73JSBuilder() {
	if (drg73JSBuilder != null)	return drg73JSBuilder;
	else return "";
}
public void setDrg73JSBuilder(String drg73JSBuilder) {this.drg73JSBuilder = drg73JSBuilder;}
public final String[] arrDrg73FieldName = {"permitKey","permitNo","applicationId","applicationName"};

public String genDrg7003DbSet(java.util.Set dtlSet) throws Exception {
	if (dtlSet != null && dtlSet.size() > 0) {
		StringBuilder sb = new StringBuilder(1024);
		int j = 0;
		for (Object dtlObj : dtlSet) {
			Drg7003Db dtl = (Drg7003Db) dtlObj;
			sb.append("addDrg73Row('drg73Table'");
			for (j = 0; j < arrDrg73FieldName.length; j++) {
				sb.append(",").append(Common.sqlChar(checkGet(Common.escapeJavaScript(BeanUtils.getProperty(dtl, arrDrg73FieldName[j])))));
			}
			sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
			sb.append(");\n");
		}
		this.setDrg73JSBuilder(sb.toString());
		return sb.toString();
	} else if (httpRequest != null && this.getDrg73Row() != null&& this.getDrg73Row().length > 0) {
		String v = "";
		StringBuilder sb = new StringBuilder(1024);
		for (int i = 0; i < getDrg73Row().length; i++) {
			String rid = getDrg73Row()[i];
			sb.append("addDrg73Row('drg73Table'");
			for (int j = 0; j < arrDrg73FieldName.length; j++) {
				v = Common.escapeReturnChar(checkGet(httpRequest.getParameter(arrDrg73FieldName[j] + rid)), true);
				sb.append(",").append(Common.sqlChar(v));
			}
			sb.append(",'").append(checkGet(httpRequest.getParameter("drg73Id"+ rid))).append("'");
			sb.append(");\n");

		}
		this.setDrg73JSBuilder(sb.toString());
		return sb.toString();
	}
	return "";
}

//========== 附件上傳 =================
String[] conRow;
public String[] getConRow() {return conRow;}
public void setConRow(String[] conRow) {this.conRow = conRow;}
public final String[] arrConFieldName = { "fileName", "fileExplan","fileRoute"};
String conJSBuilder;
public String getConJSBuilder() {
	if (conJSBuilder != null) return conJSBuilder;
	else return "";
}
public void setConJSBuilder(String conJSBuilder) {this.conJSBuilder = conJSBuilder;}
public String genCon0001DbSet(Long drg7001DbId) throws Exception {
	StringBuilder sb = new StringBuilder("");
	//查詢評估附件,紅綠燈初稿附件
	List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='DRG' and systemType in ('DRG030001','DRG030003') and dbName='Drg7001Db' and upLoadId="+ drg7001DbId + " order by id asc");
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
	
	//查詢廠商回覆附件
	List<Con0001Db> con72List = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='DRG' and systemType ='DRG030002' and dbName='Drg7002Db' and upLoadId="+ Common.getLong(getDrg72Id()) + " order by id asc");
	if (null != con72List && !con72List.isEmpty()) {
		for (Con0001Db dtl : con72List) {
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

@Override
public Object doQueryOne() throws Exception {
	VDRG0100F obj = this;
	Drg7001Db c = (Drg7001Db)ServiceGetter.getInstance().getCommonService().load(Drg7001Db.class,Common.getLong(getId()));
	if (c!=null) {
        obj.setId(Common.get(c.getId()));
        obj.setApplNo(Common.get(c.getApplNo()));
        obj.setStatus(Common.get(c.getStatus()));
        obj.setDataRevDate(Common.get(c.getDataRevDate()));
        obj.setPublDept(Common.get(c.getPublDept()));
        obj.setPublDeptCodeId(Common.get(c.getPublDept()));
        obj.setPublDeptName(View.getCommonCodeName("CONPUBLDEPT", c.getPublDept()));
        obj.setIstransfer(Common.get(c.getIstransfer()));
        obj.setTransferdept(Common.get(c.getTransferdept()));
        obj.setTransferdeptCodeId(Common.get(c.getTransferdept()));
        obj.setTransferdeptName(View.getCommonCodeName("CONPUBLDEPT", c.getTransferdept()));
        obj.setDatasourWebSite(c.getDatasourWebSite());
        obj.setSituation(Common.get(c.getSituation()));
        obj.setPublDate(Common.get(c.getPublDate()));
        obj.setRecycleNum(Common.get(c.getRecycleNum()));
        obj.setChProduct(Common.get(c.getChProduct()));
        obj.setScientificName(Common.get(c.getScientificName()));
        

        //obj.setWarningmedModel(Common.get(c.getWarningmedModel()));
        
        String warningmedModelString = c.getWarningmedModel();
		String[] values = null;
		if(c.getWarningmedModel()!=null)
			values = warningmedModelString.split(",");
		obj.setWarningmedModel(values);
		  
        
        obj.setDruggist(Common.get(c.getDruggist()));
        obj.setManufactorName(Common.get(c.getManufactorName()));
        obj.setLotNo(Common.get(c.getLotNo()));
        
        
        //obj.setQualitywarningtype(Common.get(c.getQualitywarningtype()));
        
        String qualitywarningtypeString = c.getQualitywarningtype();
		
        String[] qualitywarningtypeValues = null;
		
		if(c.getQualitywarningtype()!=null)
			qualitywarningtypeValues = qualitywarningtypeString.split(",");
		
		obj.setQualitywarningtype(qualitywarningtypeValues);
        
        obj.setEventDesc(Common.get(c.getEventDesc()));
        obj.setRecycleType(Common.get(c.getRecycleType()));
        obj.setIndication(Common.get(c.getIndication()));
        obj.setIswarning(Common.get(c.getIswarning()));
        obj.setWarningremark(Common.get(c.getWarningremark()));
        obj.setEditID(Common.get(c.getModifier()));
        obj.setEditDate(Common.get(c.getModifyDate()));
        obj.setEditTime(Common.get(c.getModifyTime()));
        obj.genDrg7003DbSet(c.getDrg7003Dbs());
        obj.genCon0001DbSet(c.getId());
        
    
        //if(null != getDrg72Id() && !"".equals(getDrg72Id()))
        //{
        	String hql="from Drg7002Db where drg7001Db.id="+Common.getLong(c.getId());
        	
        	if(null != getDrg72Id() && !"".equals(getDrg72Id()))
            {
        		hql+=" and  id="+Common.sqlChar(getDrg72Id());
            }
        	
 
        	Drg7002Db drg72 = (Drg7002Db)View.getObject(hql);
        	
        	if(null != drg72)
        	{
        		String permitStrs = "";
        		if(null != drg72.getDrg7003Dbs() && !drg72.getDrg7003Dbs().isEmpty())
        		{
        			for(Object dtlObj:drg72.getDrg7003Dbs())
        			{
        				Drg7003Db drg73 = (Drg7003Db)dtlObj;
        				if(permitStrs.length() > 0) permitStrs += "、";
        				permitStrs += Common.get(View.getCommonCodeName("DRGPKID", drg73.getPermitKey()))+"第"+Common.get(drg73.getPermitNo())+"號";
        			}
        		}
        		obj.setPermitStr(permitStrs);
        		obj.setReplyDate(Common.get(drg72.getReplyDate()));
        		obj.setReplyDesc(Common.get(drg72.getReplyDesc()));
        		obj.setIsImport(Common.get(drg72.getIsImport()));
        		obj.setNonImportReason(Common.get(drg72.getNonImportReason()));
        		obj.setNonImportReasonDesc(Common.get(drg72.getNonImportReasonDesc()));
        		obj.setEventapplNo(Common.get(drg72.getEventapplNo()));
        		obj.setDrg72Id(Common.get(drg72.getId()));
        	}
        //}
        
        obj.setEstimateDate(Common.get(c.getEstimateDate()));
        obj.setIspublnews(Common.get(c.getIspublnews()));
        obj.setIspublconsumer(Common.get(c.getIspublconsumer()));
        obj.setPublconsumerDate(Common.get(c.getPublconsumerDate()));
        obj.setLamp(Common.get(c.getLamp()));
        obj.setAftereffect(Common.get(c.getAftereffect()));
        obj.setAftereffectCodeId(Common.get(c.getAftereffect()));
        obj.setAftereffectName(View.getCommonCodeName("DRGEFFECT", c.getAftereffect()));
        obj.setAftereffectOther(Common.get(c.getAftereffectOther()));
        obj.setEstimateremark(Common.get(c.getEstimateremark()));
        
        if(null != c.getDrg7003Dbs() && !c.getDrg7003Dbs().isEmpty()){
        	obj.setIsShowT2Tab("Y");
        }else{
        	obj.setIsShowT2Tab("N");
        }
	}
	return obj;
}
@Override
public Object doQueryAll() throws Exception {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

	String hql = " from Drg7001Db where 1=1 ";
	this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
	if (getTotalRecord() > 0) {
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
		if (objList != null && objList.size() > 0) {
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) {
				Drg7001Db o = (Drg7001Db) it.next();
				String rowArray[] = new String[1];
				rowArray[0] = Common.get(o.getId());
				arrList.add(rowArray);					
			}
		}
	}
	return arrList;
}
@Override
public void doCreate() throws Exception {}

public void doInsert() throws Exception {
	Drg7001Db obj = new Drg7001Db();
	obj.setStatus("00");
	obj.setDataRevDate(Datetime.getYYYMMDD());
	obj.setPublDept(getPublDeptCodeId());
	obj.setTransferdept(getTransferdeptCodeId());
	obj.setIstransfer(getIstransfer());
	obj.setDatasourWebSite(getDatasourWebSite());
	obj.setSituation(getSituation());
	obj.setPublDate(getPublDate());
	obj.setRecycleNum(Common.getLong("1"));
	obj.setChProduct(getChProduct());
	obj.setScientificName(getScientificName());
	
    String warningmedModel="";
	
	if (getWarningmedModel()!=null && getWarningmedModel().length>0) 
	{
		for (int i=0; i<getWarningmedModel().length; i++) 
		{
			warningmedModel+=getWarningmedModel()[i]+","; 
		}
	}
	
	if(warningmedModel.length()>0)
	  obj.setWarningmedModel(warningmedModel.substring(0, warningmedModel.length()-1));//不良反應結果	VARCHAR(2)
	else
	  obj.setWarningmedModel("");
	
	//obj.setWarningmedModel(getWarningmedModel());
	
	
	obj.setDruggist(getDruggist());
	obj.setManufactorName(getManufactorName());
	obj.setLotNo(getLotNo());
	
	//obj.setQualitywarningtype(getQualitywarningtype());
	
	String qualitywarningtypeModel="";
		
    if (getQualitywarningtype()!=null && getQualitywarningtype().length>0) 
	{
		for (int i=0; i<getQualitywarningtype().length; i++) 
		{
			qualitywarningtypeModel+=getQualitywarningtype()[i]+","; 
		}
	}
		
	if(qualitywarningtypeModel.length()>0)
	   obj.setQualitywarningtype(qualitywarningtypeModel.substring(0, qualitywarningtypeModel.length()-1));//不良反應結果	VARCHAR(2)
	else
	   obj.setQualitywarningtype("");
	
	
	obj.setEventDesc(getEventDesc());
	obj.setRecycleType(getRecycleType());
	obj.setIndication(getIndication());
	obj.setIswarning(getIswarning());
	obj.setWarningremark(View.getCommonCodeName("DRGWAREMARK","05"));
	obj.setCreator(getEditID());
	obj.setCreateDate(getEditDate());
	obj.setCreateTime(getEditTime());
	obj.setModifier(getEditID());
	obj.setModifyDate(getEditDate());
	obj.setModifyTime(getEditTime());

	//國內外藥品品質警訊許可證資料檔
	if (true) {
		java.util.Set dtlSet = new ListOrderedSet();
		if (getHttpRequest() != null && getDrg73Row() != null && getDrg73Row().length > 0) {
			for (int i = 0; i < getDrg73Row().length; i++) {
				String rid = getDrg73Row()[i];
				Drg7003Db dtl = new Drg7003Db();
				dtl.setDrg7001Db(obj);
				for (int j = 0; j < arrDrg73FieldName.length; j++) {
					BeanUtils.setProperty(dtl,arrDrg73FieldName[j],getHttpRequest().getParameter(arrDrg73FieldName[j] + rid));
				}
				dtl.setCreator(getEditID());
				dtl.setCreateDate(getEditDate());
				dtl.setCreateTime(getEditTime());
				dtl.setModifier(getUserID());
				dtl.setModifyDate(Datetime.getYYYMMDD());
				dtl.setModifyTime(Datetime.getHHMMSS());
				dtlSet.add(dtl);
			}
		}
		obj.setDrg7003Dbs(dtlSet);
	}
	
	ServiceGetter.getInstance().getCommonService().save(obj);
	setId(obj.getId().toString());
}
@Override
public void doUpdate() throws Exception 
{
	List delList = new ArrayList();
	String procDesc = "";
	Drg7001Db obj = null;
	if(null != getId() && !"".equals(getId()))
	{
		obj = (Drg7001Db) View.getObject(" from Drg7001Db where id=" + Common.sqlChar(getId()));
	}
	
	if (obj == null) 
	{
		obj = new Drg7001Db();
		obj.setCreator(getEditID());
		obj.setCreateDate(getEditDate());
		obj.setCreateTime(getEditTime());
	}
	obj.setStatus(getStatus());
	obj.setDataRevDate(getDataRevDate());
	obj.setPublDept(getPublDeptCodeId());
	obj.setTransferdept(getTransferdeptCodeId());
	obj.setIstransfer(getIstransfer());
	obj.setDatasourWebSite(getDatasourWebSite());
	obj.setSituation(getSituation());
	obj.setPublDate(getPublDate());
	obj.setRecycleNum(Common.getLong(getRecycleNum()));
	obj.setChProduct(getChProduct());
	obj.setScientificName(getScientificName());
	
	
	//obj.setWarningmedModel(getWarningmedModel());
	
    String warningmedModel="";
	
	if (getWarningmedModel()!=null && getWarningmedModel().length>0) 
	{
		for (int i=0; i<getWarningmedModel().length; i++) 
		{
			warningmedModel+=getWarningmedModel()[i]+","; 
		}
	}
	
	if(warningmedModel.length()>0)
	  obj.setWarningmedModel(warningmedModel.substring(0, warningmedModel.length()-1));//不良反應結果	VARCHAR(2)
	else
	  obj.setWarningmedModel("");
	
	obj.setDruggist(getDruggist());
	obj.setManufactorName(getManufactorName());
	obj.setLotNo(getLotNo());
	
	//obj.setQualitywarningtype(getQualitywarningtype());
	
	String qualitywarningtypeModel="";
	
    if (getQualitywarningtype()!=null && getQualitywarningtype().length>0) 
	{
		for (int i=0; i<getQualitywarningtype().length; i++) 
		{
			qualitywarningtypeModel+=getQualitywarningtype()[i]+","; 
		}
	}
		
	if(qualitywarningtypeModel.length()>0)
	   obj.setQualitywarningtype(qualitywarningtypeModel.substring(0, qualitywarningtypeModel.length()-1));//不良反應結果	VARCHAR(2)
	else
	   obj.setQualitywarningtype("");
	
	
	obj.setEventDesc(getEventDesc());
	obj.setRecycleType(getRecycleType());
	obj.setIndication(getIndication());
	obj.setIswarning(getIswarning());
	obj.setWarningremark(getWarningremark());
	obj.setModifier(getEditID());
	obj.setModifyDate(getEditDate());
	obj.setModifyTime(getEditTime());
	
	//國內外藥品品質警訊許可證資料檔
	if (true) {
		java.util.Set dtlSet = new ListOrderedSet();
		if (getHttpRequest() != null && getDrg73Row() != null && getDrg73Row().length > 0) {
			StringBuilder dtlKey = new StringBuilder("-2,-1");
			for (int i = 0; i < getDrg73Row().length; i++) {
				String rid = getDrg73Row()[i];
				Drg7003Db dtl = (Drg7003Db) View.getObject("from Drg7003Db where id="+ Common.getLong(getHttpRequest().getParameter("drg73Id" + rid)));
				if (dtl == null) {
					dtl = new Drg7003Db();
					dtl.setDrg7001Db(obj);
					dtl.setCreator(getEditID());
					dtl.setCreateDate(getEditDate());
					dtl.setCreateTime(getEditTime());
				}
				for (int j = 0; j < arrDrg73FieldName.length; j++) {
					BeanUtils.setProperty(dtl,arrDrg73FieldName[j],getHttpRequest().getParameter(arrDrg73FieldName[j] + rid));
				}
				dtl.setModifier(getUserID());
				dtl.setModifyDate(Datetime.getYYYMMDD());
				dtl.setModifyTime(Datetime.getHHMMSS());
				dtlSet.add(dtl);

				dtlKey.append(",").append(dtl.getId());
			}
			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg7003Db where drg7001Db.id="+ Common.getLong(obj.getId())+ " and id not in ("+ dtlKey.toString() + ")"));
		} else {
			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg7003Db where drg7001Db.id="+ Common.getLong(obj.getId())));
		}
		obj.setDrg7003Dbs(dtlSet);
	}
	
	if(null != getDrg72Id() && !"".equals(getDrg72Id())){
		java.util.Set dtlSet = new ListOrderedSet();
		if(null != obj.getDrg7002Dbs() && !obj.getDrg7002Dbs().isEmpty()){
			for(Object dtlObj:obj.getDrg7002Dbs()){
				Drg7002Db drg72 = (Drg7002Db)dtlObj;
				if(getDrg72Id().equals(Common.get(drg72.getId()))){
					drg72.setReplyDate(getReplyDate());
					drg72.setReplyDesc(getReplyDesc());
					drg72.setIsImport(getIsImport());
					drg72.setNonImportReason(getNonImportReason());
					drg72.setNonImportReasonDesc(getNonImportReasonDesc());
					drg72.setEventapplNo(Common.get(getEventapplNo()));
					drg72.setModifier(getEditID());
					drg72.setModifyDate(getEditDate());
					drg72.setModifyTime(getEditTime());
				}
				dtlSet.add(drg72);
			}
		}
		obj.setDrg7002Dbs(dtlSet);
	}

	//評估資料
	obj.setEstimateDate(getEstimateDate());
    obj.setIspublnews(getIspublnews());
    obj.setIspublconsumer(getIspublconsumer());
    obj.setPublconsumerDate(getPublconsumerDate());
    obj.setLamp(getLamp());
    obj.setAftereffect(getAftereffectCodeId());
    obj.setAftereffectOther(getAftereffectOther());
    obj.setEstimateremark(getEstimateremark());
	
	if("maintain1".equals(getActionType())){		//送件
		//系統給號
		String newApplNo = TCBWCommon.getApplNo("DRG03","A03",Datetime.getYYY(),true);
		if(null != newApplNo && !"".equals(newApplNo)){
			obj.setApplNo(newApplNo);
			obj.setStatus("10");					//警訊評估中
			
			Map<String, Drg7002Db> drg72Map = new HashMap<String, Drg7002Db>();
			java.util.Set dtl73AllSet = new ListOrderedSet();
			if(null != obj.getDrg7003Dbs() && !obj.getDrg7003Dbs().isEmpty()){
				for(Object dtlObj : obj.getDrg7003Dbs()){
					Drg7003Db drg73 = (Drg7003Db)dtlObj;
					Drg7002Db drg72 = drg72Map.get(drg73.getApplicationId());
					java.util.Set dtl73Set = new ListOrderedSet();
					if(null == drg72){
						drg72 = new Drg7002Db();
						drg72.setDrg7001Db(obj);
						drg72.setCreator(getEditID());
						drg72.setCreateDate(getEditDate());
						drg72.setCreateTime(getEditTime());
						drg72.setModifier(getUserID());
						drg72.setModifyDate(Datetime.getYYYMMDD());
						drg72.setModifyTime(Datetime.getHHMMSS());
					}
					if(null != drg72.getDrg7003Dbs() && !drg72.getDrg7003Dbs().isEmpty()){
						dtl73Set = drg72.getDrg7003Dbs();
					}
					drg73.setDrg7002Db(drg72);
					dtl73Set.add(drg73);
					dtl73AllSet.add(drg73);
					
					drg72.setDrg7003Dbs(dtl73Set);
					drg72Map.put(drg73.getApplicationId(), drg72);
				}
			}	
			obj.setDrg7003Dbs(dtl73AllSet);
			if(null != drg72Map && !drg72Map.isEmpty()){
				java.util.Set dtl72Set = new ListOrderedSet();
				for(String applicationId:drg72Map.keySet()){
					dtl72Set.add(drg72Map.get(applicationId));
				}
				obj.setDrg7002Dbs(dtl72Set);
			}
			procDesc = "案件評估者 - 警訊評估中";
		}else throw new Exception("給號發生錯誤，送件失敗！");
	}else if("maintain3".equals(getActionType())){		//回覆完成
		if(null != getDrg72Id() && !"".equals(getDrg72Id())){
			java.util.Set dtlSet = new ListOrderedSet();
			if(null != obj.getDrg7002Dbs() && !obj.getDrg7002Dbs().isEmpty()){
				for(Object dtlObj:obj.getDrg7002Dbs()){
					Drg7002Db drg72 = (Drg7002Db)dtlObj;
					if(getDrg72Id().equals(Common.get(drg72.getId()))){
						drg72.setReplyDate(Datetime.getYYYMMDD());
					}
					dtlSet.add(drg72);
				}
			}
			obj.setDrg7002Dbs(dtlSet);
		}
	}else if("maintain4".equals(getActionType())){		//評估完成
		obj.setStatus("90");							//結案
		procDesc = "案件評估者 - 結案";
	}
	if(null != obj.getId() && obj.getId() > 0){
		ServiceGetter.getInstance().getCommonService().update(obj);
	}else{
		ServiceGetter.getInstance().getCommonService().save(obj);
	}
    
    if (null != delList && !delList.isEmpty()) {
		ServiceGetter.getInstance().getTcbwService().deleteBatch(delList);
	}
    
    if(null != procDesc && !"".equals(procDesc)){
    	//歷程紀錄
		ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG3",obj.getId(), obj.getApplNo(), obj.getStatus(), procDesc, TCBWCommon.getCurrentUserId());
	}
}
@Override
public void doDelete() throws Exception {
	ServiceGetter.getInstance().getCommonService().delete(Drg7001Db.class, Common.getLong(id));
}

public java.util.ArrayList<String[]> doQueryAllDrg71(String drg7001DbId) throws Exception {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	
	String hql = " from " + Drg7002Db.class.getCanonicalName() +
		         " where drg7001Db.id = " + Common.getLong(drg7001DbId) ;
	
	if(!"in".equals(TCBWCommon.getCurrentInORout()))
	{
		hql += " and id in (select drg7002Db.id from Drg7003Db where applicationId in (select con1005Db.compegno from Con1006Db where commonUser.userId="+Common.sqlChar(TCBWCommon.getCurrentUserId())+"))";
	}
	
	int count = ServiceGetter.getInstance().getTcbwService().loadCount(hql);
	this.setPageSize(count);
	this.processCurrentPageAttribute(count);
	if (getTotalRecord() > 0) {
		if (getState().indexOf("query") < 0)
			ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
		List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(),this.getPageSize());
		if (objList != null && objList.size() > 0) {
			Iterator it = objList.iterator();
			while (it.hasNext()) {
				Drg7002Db o = (Drg7002Db) it.next();
				String rowArray[] = new String[3];
				rowArray[0] = Common.get(o.getId());
				rowArray[1] = View.getLookupField("select applicationName from Drg7003Db where drg7002Db.id = "+o.getId());
				rowArray[2] = "drg72";
				arrList.add(rowArray);
			}
		}
	}
	return arrList;
}

public Object[] getDefaultTableModelForWord() throws Exception
{
	Object[] rowArray = null;		//word 替換欄位數
	List list = ServiceGetter.getInstance().getCommonService().load(" from Drg7001Db where id = " + getId());
	if(list!=null && list.size() > 0){	
		Iterator it = list.iterator();
		while(it.hasNext()){
			Drg7001Db o = (Drg7001Db) it.next();
			
			if("01".equals(getQ_lampPrint())){
				rowArray = new String[11];		//word 替換欄位數
				String permitStr = "";
				String appname = "";
				String permitData = "";
				String factname = "";
				String ingrma = "";
				String indications = "";
				if(null != o.getDrg7003Dbs() && !o.getDrg7003Dbs().isEmpty()){
					for(Object dtlObj:o.getDrg7003Dbs()){
						String LICEKC="";//許可證字
						String LICID1="";//許可證號
						String CHNAME="";//中文品名
						String ENNAME="";//英文品名
						String APPNAME="";//申請商
						String FACTNAME="";//製造廠
						String INGRMA="";//主成份
						String INDICATION =""; //適應症
						Drg7003Db drg73 = (Drg7003Db)dtlObj;
						List<String[]> perList = getVW_ForADR_TBMLIC(Common.get(drg73.getPermitKey()),Common.get(drg73.getPermitNo()));
						if(null != perList && perList.size() > 0) {
							for (int i=0;i<perList.size();i++){
								Object[] dtl = perList.get(i);
								LICEKC = Common.get(dtl[0]);
								LICID1 = Common.get(dtl[1]);
								CHNAME = Common.get(dtl[2]);
								ENNAME = Common.get(dtl[3]);
								APPNAME = Common.get(dtl[4]);
								FACTNAME = Common.get(dtl[5]);
								INGRMA = Common.get(dtl[6]);
							}							
						}
						//適應症取網頁資料			
						INDICATION = parseFeeRateFromWeb(Common.get(drg73.getPermitKey())+Common.get(drg73.getPermitNo()));
						
						if(permitStr.length() > 0) permitStr += "、";
						permitStr += LICEKC+"字第"+LICID1+"號";
						if(appname.length() > 0) appname += "、";
						appname += APPNAME;
						if(permitData.length() > 0) permitData += "；";
						permitData += CHNAME+"，"+ENNAME+"("+LICEKC+"字第"+LICID1+"號)";
						if(factname.length() > 0) factname += "、";
						factname += FACTNAME;
						if(ingrma.length() > 0) ingrma += "、";
						ingrma += INGRMA;
						if(indications.length() > 0) indications += "；";
						indications += INDICATION;
					}
				}
				//許可證字號、品名、持有商、製造廠、主成分、適應症改為使用藥證資料
				rowArray[0] = Common.get(o.getDruggist());						//##1##【廠商】
				rowArray[1] = Common.get(o.getLotNo());							//##2##【產品批號】
				rowArray[2] = Common.get(o.getChProduct());						//##3##【商品名】
				rowArray[3] = permitStr;										//##4##【許可證字號】
				rowArray[4] = Common.formatYYYMMDD(Datetime.getYYYMMDD(),1);	//##5##【系統日期】
				rowArray[5] = Common.get(o.getEventDesc());						//##6##【事件簡述】
				rowArray[6] = permitData;										//##7##【許可證字號+中文品名+英文品名】
				rowArray[7] = appname;											//##8##【許可證申請商】
				rowArray[8] = factname;											//##9##【許可證製造廠】				
				rowArray[9] = ingrma;					                        //##10##【許可證主成分略述】
				rowArray[10] = Common.get(indications);					        //##11##【許可證適應症】
			}else if("02".equals(getQ_lampPrint())){
				rowArray = new String[12];		//word 替換欄位數
				String permitStr = "";
				String permitData = "";
				String appname = "";
				String factname = "";
				String ingrma = "";
				String indications = "";
				if(null != o.getDrg7003Dbs() && !o.getDrg7003Dbs().isEmpty()){
					for(Object dtlObj:o.getDrg7003Dbs()){
						String LICEKC="";//許可證字
						String LICID1="";//許可證號
						String CHNAME="";//中文品名
						String ENNAME="";//英文品名
						String APPNAME="";//申請商
						String FACTNAME="";//製造廠
						String INGRMA="";//主成份
						String INDICATION =""; //適應症
						Drg7003Db drg73 = (Drg7003Db)dtlObj;
						List<String[]> perList = getVW_ForADR_TBMLIC(Common.get(drg73.getPermitKey()),Common.get(drg73.getPermitNo()));
						if(null != perList && perList.size() > 0) {
							for (int i=0;i<perList.size();i++){
								Object[] dtl = perList.get(i);
								LICEKC = Common.get(dtl[0]);
								LICID1 = Common.get(dtl[1]);
								CHNAME = Common.get(dtl[2]);
								ENNAME = Common.get(dtl[3]);
								APPNAME = Common.get(dtl[4]);
								FACTNAME = Common.get(dtl[5]);
								INGRMA = Common.get(dtl[6]);
							}							
						}
						//適應症取網頁資料			
						INDICATION = parseFeeRateFromWeb(Common.get(drg73.getPermitKey())+Common.get(drg73.getPermitNo()));
						
						if(permitStr.length() > 0) permitStr += "、";
						permitStr += LICEKC+"字第"+LICID1+"號";
						if(appname.length() > 0) appname += "、";
						appname += APPNAME;
						if(permitData.length() > 0) permitData += "；";
						permitData += CHNAME+"，"+ENNAME+"("+LICEKC+"字第"+LICID1+"號)";
						if(factname.length() > 0) factname += "、";
						factname += FACTNAME;
						if(ingrma.length() > 0) ingrma += "、";
						ingrma += INGRMA;
						if(indications.length() > 0) indications += "；";
						indications += INDICATION;
					}
				}
				//許可證字號、品名、持有商、製造廠、主成分、適應症改為使用藥證資料
				rowArray[0] = Common.get(o.getDruggist());						//##1##【廠商】
				rowArray[1] = Common.get(o.getChProduct());                     //##2##【商品名】
				rowArray[2] = Common.get(o.getLotNo());							//##3##【產品批號】
				rowArray[3] = Common.formatYYYMMDD(Datetime.getYYYMMDD(),1);	//##4##【系統日期】
				rowArray[4] = View.getCommonCodeName("CONPUBLDEPT", o.getPublDept());	//##5##【發布單位】
				rowArray[5] = Common.formatYYYMMDD(o.getPublDate(),2);			//##6##【發布日期】
				rowArray[6] = Common.get(o.getEventDesc());						//##7##【事件簡述】
				rowArray[7] = permitData;						                //##8##【許可證字號+中文品名+英文品名】
				rowArray[8] = appname;											//##9##【許可證申請商】
				rowArray[9] = factname;										    //##10##【許可證製造廠】
				rowArray[10] = ingrma;					                        //##11##【許可證主成分略述】
				rowArray[11] = Common.get(indications);					        //##12##【許可證適應症】
			}else if("03".equals(getQ_lampPrint())){
				rowArray = new String[9];		//word 替換欄位數
				rowArray[0] = View.getCommonCodeName("CONPUBLDEPT", o.getPublDept());	//##1##
				rowArray[1] = Common.get(o.getDruggist());						//##2##
				rowArray[2] = Common.get(o.getLotNo());							//##3##
				rowArray[3] = Common.get(o.getChProduct());						//##4##
				rowArray[4] = Common.formatYYYMMDD(Datetime.getYYYMMDD(),1);	//##5##
				rowArray[5] = Common.formatYYYMMDD(o.getPublDate(),2);			//##6##
				rowArray[6] = Common.get(o.getScientificName());				//##7##
				rowArray[7] = Common.get("");					//##8##
				rowArray[8] = Common.get(o.getEventDesc());						//##9##				
			}
		}
	}
	return rowArray;
}

//取回藥證資料
public List<String[]> getVW_ForADR_TBMLIC(String permitKey, String permitNo) throws Exception{
	Database db = new Database("MLMS");
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	if(!"".equals(permitKey) && !"".equals(permitNo)) {		
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		String hql = " select LICEKC,LICID1,CHNAME,ENNAME,APPNAME,FACTNAME,INGRMA from VW_ForADR_TBMLIC where 1=1";
		if (!"".equals(permitNo)){	
			hql += " and LICID1 = ?";
			parameter.add(permitNo);
		}
		if (!"".equals(permitKey)){		
			hql += " and LICEKID = ?";
			parameter.add(permitKey);
		}	
		ResultSet rs = db.querySQLByPrepareStatement(hql, parameter);
		try {
			if(rs!=null){
				while (rs.next()){			
					String[] rowArray = new String[7];	
					rowArray[0] = Common.get(rs.getString("LICEKC"));
					rowArray[1] = Common.get(rs.getString("LICID1"));
					rowArray[2] = Common.get(rs.getString("CHNAME"));
					rowArray[3] = Common.get(rs.getString("ENNAME"));
					rowArray[4] = Common.get(rs.getString("APPNAME"));
					rowArray[5] = Common.get(rs.getString("FACTNAME"));
					rowArray[6] = Common.get(rs.getString("INGRMA"));
										
					arrList.add(rowArray);				
				}
			}
		}catch (Exception e) {			
			e.printStackTrace();
		}finally{
			db.closeAll();
		}
		return arrList;
	}
	return null;
}

public String parseFeeRateFromWeb(String licId) throws Exception {	
	URL url = new java.net.URL("http://www.fda.gov.tw/MLMS/(S(adwpxe45smeagxark2fzfa55))/H0001D.aspx?Type=Lic&LicId="+licId);	
	String indications = "";
	if (url!=null) {
        Source source = new Source(url);
        List<Element> tbs = source.getAllElements(HTMLElementName.TABLE);
        if (tbs!=null && tbs.size()>0) 
        {       	 	
        	int r=0;  
        	//第一個Table才是許可證詳細內容
        	Segment ctx = (Segment) tbs.get(0).getContent();        		
        	List<Element> trs = ctx.getAllElements(HTMLElementName.TR);        	       			       				
        	//適應症為第8個tr
        	Segment trContent = trs.get(8).getContent();        			
        	List<Element> tds = trContent.getAllElements(HTMLElementName.TD); 
        	//只有一個td
        	Segment spanContent = tds.get(0).getContent(); 
        	List<Element> spans = spanContent.getAllElements(HTMLElementName.SPAN);
        	//取<span></span>內容
        	indications = Common.get(spans.get(0).getContent().toString());       	       	
        }			
	}
    return indications;    
}
}


