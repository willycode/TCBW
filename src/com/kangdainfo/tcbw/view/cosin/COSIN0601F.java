package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos0009Db;
import com.kangdainfo.tcbw.model.bo.Cos0010Db;
import com.kangdainfo.tcbw.model.bo.Cos0011Db;
import com.kangdainfo.tcbw.model.bo.Cos0012Db;
import com.kangdainfo.tcbw.model.bo.Cos0013Db;
import com.kangdainfo.tcbw.model.bo.Cos0014Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.model.bo.Sys0001Db;

public class COSIN0601F extends COSIN1001F {
	
	private String con1003DbId;					// FOR 轉送單位的ID	
	private String isNeedShowSendTFDA;
	private String isCombineBussinessPage;
	
	public String getCon1003DbId() {		return checkGet(con1003DbId);	}
	public void setCon1003DbId(String con1003DbId) {		this.con1003DbId = checkSet(con1003DbId);	}
	public String getIsNeedShowSendTFDA() {		return checkGet(isNeedShowSendTFDA);	}
	public void setIsNeedShowSendTFDA(String isNeedShowSendTFDA) {		this.isNeedShowSendTFDA = checkSet(isNeedShowSendTFDA);	}
	public String getIsCombineBussinessPage() {		return checkGet(isCombineBussinessPage);	}
	public void setIsCombineBussinessPage(String isCombineBussinessPage) {		this.isCombineBussinessPage = checkSet(isCombineBussinessPage);	}

	// COS0009_DB 
	private String cos0009DbId; 
	private String notifyDate9;					// 通知日期
	private String handling;					// 目前處理情形/調查報告
	private String precaution9;					// 預防矯正措施
	private String similarComplaint;			// 類似客訴
	private String remark9;						// 補充說明
	private String predictDate;					// 預計完成日期
	private String replyDate9;					// 回覆日期
	private String isNeedShowCos0009DbPage;
	private String isClose09;					// 是否已結案
	
	private java.util.ArrayList cos0009DbList;
	public java.util.ArrayList getCos0009DbList() {		return cos0009DbList;	}
	public void setCos0009DbList(java.util.ArrayList cos0009DbList) {		this.cos0009DbList = cos0009DbList;	}
	
	public String getCos0009DbId() {		return checkGet(cos0009DbId);	}
	public void setCos0009DbId(String cos0009DbId) {		this.cos0009DbId = checkSet(cos0009DbId);	}
	public String getNotifyDate9() {		return checkGet(notifyDate9);	}
	public void setNotifyDate9(String notifyDate9) {		this.notifyDate9 = checkSet(notifyDate9);	}
	public String getHandling() {		return checkGet(handling);	}
	public void setHandling(String handling) {		this.handling = checkSet(handling);	}
	public String getPrecaution9() {		return checkGet(precaution9);	}
	public void setPrecaution9(String precaution9) {	this.precaution9 = checkSet(precaution9);	}
	public String getSimilarComplaint() {		return checkGet(similarComplaint);	}
	public void setSimilarComplaint(String similarComplaint) {		this.similarComplaint = checkSet(similarComplaint);	}
	public String getRemark9() {		return checkGet(remark9);	}
	public void setRemark9(String remark9) {		this.remark9 = checkSet(remark9);	}
	public String getPredictDate() {		return checkGet(predictDate);	}
	public void setPredictDate(String predictDate) {		this.predictDate = checkSet(predictDate);	}
	public String getReplyDate9() {		return checkGet(replyDate9);	}
	public void setReplyDate9(String replyDate9) {		this.replyDate9 = checkSet(replyDate9);	}
	public String getIsNeedShowCos0009DbPage() {		return checkGet(isNeedShowCos0009DbPage);	}
	public void setIsNeedShowCos0009DbPage(String isNeedShowCos0009DbPage) {		this.isNeedShowCos0009DbPage = checkSet(isNeedShowCos0009DbPage);	}
	public String getIsClose09() {		return checkGet(isClose09);	}
	public void setIsClose09(String isClose09) {		this.isClose09 = checkSet(isClose09);	}

	// COS0010_DB
	private String cos0010DbId;
	private String notifyDate10;				// 通知日期
	private String isComplaintYn10;				// 是否接獲該產品其他消費者客訴
	private String complaintNum;				// 客訴件數
	private String dealWith10;					// 類似客訴案件之後續處理情形
	private String replyOther10;				// 其他回覆
	private String replyDate10;					// 回覆日期
	private String isClose10;					// 是否已結案
	
	public String getCos0010DbId() {		return checkGet(cos0010DbId);	}
	public void setCos0010DbId(String cos0010DbId) {		this.cos0010DbId = checkSet(cos0010DbId);	}
	public String getNotifyDate10() {		return checkGet(notifyDate10);	}
	public void setNotifyDate10(String notifyDate10) {		this.notifyDate10 = checkSet(notifyDate10);	}
	public String getIsComplaintYn10() {		return checkGet(isComplaintYn10);	}
	public void setIsComplaintYn10(String isComplaintYn10) {		this.isComplaintYn10 = checkSet(isComplaintYn10);	}
	public String getComplaintNum() {		return checkGet(complaintNum);	}
	public void setComplaintNum(String complaintNum) {		this.complaintNum = checkSet(complaintNum);	}
	public String getDealWith10() {		return checkGet(dealWith10);	}
	public void setDealWith10(String dealWith10) {		this.dealWith10 = checkSet(dealWith10);	}
	public String getReplyOther10() {		return checkGet(replyOther10);	}
	public void setReplyOther10(String replyOther10) {		this.replyOther10 = checkSet(replyOther10);	}
	public String getReplyDate10() {		return checkGet(replyDate10);	}
	public void setReplyDate10(String replyDate10) {		this.replyDate10 = checkSet(replyDate10);	}
	public String getIsClose10() {return checkGet(isClose10);}
	public void setIsClose10(String isClose10) {this.isClose10 = checkSet(isClose10);}

	private java.util.ArrayList cos0010DbList;
	public java.util.ArrayList getCos0010DbList() {		return cos0010DbList;	}
	public void setCos0010DbList(java.util.ArrayList cos0010DbList) {		this.cos0010DbList = cos0010DbList;	}
	
	// COS0009_DB合併COS0010_DB
	private String cos0910Id;
	private String notifyDate0910;				// 通知日期
	private String replyDate0910;				// 回覆日期		
	private String isComplaintYn0910;			// 是否接獲該產品其他消費者客訴
	private String complaintNum0910;			// 客訴件數
	private String similarComplaint0910;		// 類似客訴案件之後續處理情況
	private String precaution0910;				// 預防矯正措施
	private String handling0910;				// 目前處理情形/調查報告
	private String remark0910;					// 補充說明
	private String predictDate0910;				// 預計完成日期
	private String isClose0910;					// 是否已結案
	
	public String getCos0910Id() {		return checkGet(cos0910Id);	}
	public void setCos0910Id(String cos0910Id) {		this.cos0910Id = checkSet(cos0910Id);	}
	public String getNotifyDate0910() {		return checkGet(notifyDate0910);	}
	public void setNotifyDate0910(String notifyDate0910) {		this.notifyDate0910 = checkSet(notifyDate0910);	}
	public String getReplyDate0910() {		return checkGet(replyDate0910);	}
	public void setReplyDate0910(String replyDate0910) {		this.replyDate0910 = checkSet(replyDate0910);	}
	public String getIsComplaintYn0910() {		return checkGet(isComplaintYn0910);	}
	public void setIsComplaintYn0910(String isComplaintYn0910) {		this.isComplaintYn0910 = checkSet(isComplaintYn0910);	}
	public String getComplaintNum0910() {		return checkGet(complaintNum0910);	}
	public void setComplaintNum0910(String complaintNum0910) {		this.complaintNum0910 = checkSet(complaintNum0910);	}
	public String getSimilarComplaint0910() {		return checkGet(similarComplaint0910);	}
	public void setSimilarComplaint0910(String similarComplaint0910) {		this.similarComplaint0910 = checkSet(similarComplaint0910);	}
	public String getPrecaution0910() {		return checkGet(precaution0910);	}
	public void setPrecaution0910(String precaution0910) {		this.precaution0910 = checkSet(precaution0910);	}
	public String getHandling0910() {		return checkGet(handling0910);	}
	public void setHandling0910(String handling0910) {		this.handling0910 = checkSet(handling0910);	}
	public String getRemark0910() {		return checkGet(remark0910);	}
	public void setRemark0910(String remark0910) {		this.remark0910 = checkSet(remark0910);	}
	public String getPredictDate0910() {		return checkGet(predictDate0910);	}
	public void setPredictDate0910(String predictDate0910) {		this.predictDate0910 = checkSet(predictDate0910);	}
	public String getIsClose0910() {		return checkGet(isClose0910);	}
	public void setIsClose0910(String isClose0910) {		this.isClose0910 = checkSet(isClose0910);	}

	private java.util.ArrayList cos0910DbList;
	public java.util.ArrayList getCos0910DbList() {		return cos0910DbList;	}
	public void setCos0910DbList(java.util.ArrayList cos0910DbList) {		this.cos0910DbList = cos0910DbList;	}

	// COS0011_DB
	private String isLetterYn11;				// 是否再次發函詢問廠商
	private String remark11;					// 處理備註
	private String replyMemo11;					// 廠商回覆摘要

	public String getIsLetterYn11() {		return checkGet(isLetterYn11);	}
	public void setIsLetterYn11(String isLetterYn11) {		this.isLetterYn11 = checkSet(isLetterYn11);	}
	public String getRemark11() {		return checkGet(remark11);	}
	public void setRemark11(String remark11) {		this.remark11 = checkSet(remark11);	}
	public String getReplyMemo11() {		return checkGet(replyMemo11);	}
	public void setReplyMemo11(String replyMemo11) {		this.replyMemo11 = checkSet(replyMemo11);	}

	// COS0014_DB
	private String isLetterYn14;				// 是否再次發函詢問廠商
	private String dealWith14;					// 後續處置
	private String dealWithOther14;				// 後續處置(其他)
	private String assess;						// 針對廠商回覆之評估
	private String summary;						// 調查摘要
	private String resolution;					// 會議決議簡述
	private String result;						// 審查結果簡述
	private String precaution14;				// 預防矯正措施
	private String recycling;					// 產品回收報告
	private String otherDisposal;				// 其他處置紀錄
	private String remark14;					// 處理備註
	private String replyMemo14;					// 廠商回覆摘要
	
	public String getIsLetterYn14() {		return checkGet(isLetterYn14);	}
	public void setIsLetterYn14(String isLetterYn14) {		this.isLetterYn14 = checkSet(isLetterYn14);	}
	public String getDealWith14() {		return checkGet(dealWith14);	}
	public void setDealWith14(String dealWith14) {		this.dealWith14 = checkSet(dealWith14);	}
	public String getDealWithOther14() {		return checkGet(dealWithOther14);	}
	public void setDealWithOther14(String dealWithOther14) {		this.dealWithOther14 = checkSet(dealWithOther14);	}
	public String getAssess() {		return checkGet(assess);	}
	public void setAssess(String assess) {		this.assess = checkSet(assess);	}
	public String getSummary() {		return checkGet(summary);	}
	public void setSummary(String summary) {		this.summary = checkSet(summary);	}
	public String getResolution() {		return checkGet(resolution);	}
	public void setResolution(String resolution) {		this.resolution = checkSet(resolution);	}
	public String getResult() {		return checkGet(result);	}
	public void setResult(String result) {		this.result = checkSet(result);	}
	public String getPrecaution14() {		return checkGet(precaution14);	}
	public void setPrecaution14(String precaution14) {		this.precaution14 = checkSet(precaution14);	}
	public String getRecycling() {		return checkGet(recycling);	}
	public void setRecycling(String recycling) {		this.recycling = checkSet(recycling);	}
	public String getOtherDisposal() {		return checkGet(otherDisposal);	}
	public void setOtherDisposal(String otherDisposal) {		this.otherDisposal = checkSet(otherDisposal);	}
	public String getRemark14() {		return checkGet(remark14);	}
	public void setRemark14(String remark14) {		this.remark14 = checkSet(remark14);	}
	public String getReplyMemo14() {		return checkGet(replyMemo14);	}
	public void setReplyMemo14(String replyMemo14) {		this.replyMemo14 = checkSet(replyMemo14);	}

	private String[] COS0012DbRow;
	private String[] COS0013DbRow;
	
	public String[] getCOS0012DbRow() {		return checkGet(COS0012DbRow);	}
	public void setCOS0012DbRow(String[] cOS0012DbRow) {		COS0012DbRow = checkSet(cOS0012DbRow);	}
	public String[] getCOS0013DbRow() {		return checkGet(COS0013DbRow);	}
	public void setCOS0013DbRow(String[] cOS0013DbRow) {		COS0013DbRow = checkSet(cOS0013DbRow);	}

	private String COS0012DbRowSBuilder;
	private String COS0013DbRowSBuilder;
	
	public String getCOS0012DbRowSBuilder() {		return get(COS0012DbRowSBuilder);	}
	public void setCOS0012DbRowSBuilder(String cOS0012DbRowSBuilder) {		COS0012DbRowSBuilder = checkSet(cOS0012DbRowSBuilder);	}
	public String getCOS0013DbRowSBuilder() {		return get(COS0013DbRowSBuilder);	}
	public void setCOS0013DbRowSBuilder(String cOS0013DbRowSBuilder) {		COS0013DbRowSBuilder = checkSet(cOS0013DbRowSBuilder);	}

	public final String[] arrCOS0012DbFieldName = {"postDate12","postNo12","postDept12","postMemo12"};
	public final String[] arrCOS0013DbFieldName = {"receiptDate","postDate13","postDept13","postNo13","postMemo13"};
	
	public String genCos0012DbItemSet(java.util.List<Cos0012Db> dtlList) throws Exception{
		if(dtlList!=null && dtlList.size()>0){
			StringBuilder sb = new StringBuilder(1024);
			for(Cos0012Db dtl : dtlList){
				sb.append("addCOS0012DbRow('tabCOS0012Db'");
				sb.append(",").append(Common.sqlChar(dtl.getPostDate()));
				sb.append(",").append(Common.sqlChar(dtl.getPostNo()));
				sb.append(",'").append(dtl.getCon1003DbId()).append("'");
				sb.append(",").append(Common.sqlChar(dtl.getPostMemo()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
			}
			this.setCOS0012DbRowSBuilder(sb.toString());
		}
		return "";
	}
	
	public String genCos0013DbItemSet(java.util.List<Cos0013Db> dtlList) throws Exception{
		if(dtlList!=null && dtlList.size()>0){
			StringBuilder sb = new StringBuilder(1024);
			for(Cos0013Db dtl : dtlList){
				sb.append("addCOS0013DbRow('tabCOS0013Db'");
				sb.append(",").append(Common.sqlChar(dtl.getReceiptDate()));
				sb.append(",").append(Common.sqlChar(dtl.getPostDate()));
				sb.append(",").append(Common.sqlChar(dtl.getPostDept()));
				sb.append(",").append(Common.sqlChar(dtl.getPostNo()));
				sb.append(",").append(Common.sqlChar(dtl.getPostMemo()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
			}
			this.setCOS0013DbRowSBuilder(sb.toString());
		}
		return "";
	}
	
	private String[] COSVRFileRow;					// FOR 不良反應、不良品(不良品與不良反應合併也是此代號)-廠商回覆-相關資料
	private String[] COSPTFileRow;					// FOR 不良品-處理頁籤-發文資訊-相關資料
	private String[] COSRTFileRow;					// FOR 不良品-處理頁籤-收文資訊-相關資料
	private String[] COSHAFileRow;					// FOR 不良反應-處理頁籤-衛生諮詢-相關資料
	private String[] COSEBFileRow;					// FOR 不良反應-處理頁籤-專家書審-相關資料
	private String[] COSMSFileRow;					// FOR 不良反應-處理頁籤-廠商提交-相關資料
	
	public String[] getCOSVRFileRow() {		return checkGet(COSVRFileRow);	}
	public void setCOSVRFileRow(String[] cOSVRFileRow) {		COSVRFileRow = checkSet(cOSVRFileRow);	}
	public String[] getCOSPTFileRow() {		return checkGet(COSPTFileRow);	}
	public void setCOSPTFileRow(String[] cOSPTFileRow) {		COSPTFileRow = checkSet(cOSPTFileRow);	}
	public String[] getCOSRTFileRow() {		return checkGet(COSRTFileRow);	}
	public void setCOSRTFileRow(String[] cOSRTFileRow) {		COSRTFileRow = checkSet(cOSRTFileRow);	}
	public String[] getCOSHAFileRow() {		return checkGet(COSHAFileRow);	}
	public void setCOSHAFileRow(String[] cOSHAFileRow) {		COSHAFileRow = checkSet(cOSHAFileRow);	}
	public String[] getCOSEBFileRow() {		return checkGet(COSEBFileRow);	}
	public void setCOSEBFileRow(String[] cOSEBFileRow) {		COSEBFileRow = checkSet(cOSEBFileRow);	}
	public String[] getCOSMSFileRow() {		return checkGet(COSMSFileRow);	}
	public void setCOSMSFileRow(String[] cOSMSFileRow) {		COSMSFileRow = checkSet(cOSMSFileRow);	}

	private String COSVRFileRowSBuilder;
	private String COSPTFileRowSBuilder;
	private String COSRTFileRowSBuilder;
	private String COSHAFileRowSBuilder;
	private String COSEBFileRowSBuilder;
	private String COSMSFileRowSBuilder;
	
	public String getCOSVRFileRowSBuilder() {		return get(COSVRFileRowSBuilder);	}
	public void setCOSVRFileRowSBuilder(String cOSVRFileRowSBuilder) {		COSVRFileRowSBuilder = checkSet(cOSVRFileRowSBuilder);	}
	public String getCOSPTFileRowSBuilder() {		return get(COSPTFileRowSBuilder);	}
	public void setCOSPTFileRowSBuilder(String cOSPTFileRowSBuilder) {		COSPTFileRowSBuilder = checkSet(cOSPTFileRowSBuilder);	}
	public String getCOSRTFileRowSBuilder() {		return get(COSRTFileRowSBuilder);	}
	public void setCOSRTFileRowSBuilder(String cOSRTFileRowSBuilder) {		COSRTFileRowSBuilder = checkSet(cOSRTFileRowSBuilder);	}
	public String getCOSHAFileRowSBuilder() {		return get(COSHAFileRowSBuilder);	}
	public void setCOSHAFileRowSBuilder(String cOSHAFileRowSBuilder) {		COSHAFileRowSBuilder = checkSet(cOSHAFileRowSBuilder);	}
	public String getCOSEBFileRowSBuilder() {		return get(COSEBFileRowSBuilder);	}
	public void setCOSEBFileRowSBuilder(String cOSEBFileRowSBuilder) {		COSEBFileRowSBuilder = checkSet(cOSEBFileRowSBuilder);	}
	public String getCOSMSFileRowSBuilder() {		return get(COSMSFileRowSBuilder);	}
	public void setCOSMSFileRowSBuilder(String cOSMSFileRowSBuilder) {		COSMSFileRowSBuilder = checkSet(cOSMSFileRowSBuilder);	}

	public final String[] arrCOSVRFileFieldName = {"COSVRFileName", "COSVRFileNameRoute", "COSVRFileExplan"};
	public final String[] arrCOSPTFileFieldName = {"COSPTFileName", "COSPTFileNameRoute", "COSPTFileExplan"};
	public final String[] arrCOSRTFileFieldName = {"COSRTFileName", "COSRTFileNameRoute", "COSRTFileExplan"};
	public final String[] arrCOSHAFileFieldName = {"COSHAFileName", "COSHAFileNameRoute", "COSHAFileExplan"};
	public final String[] arrCOSEBFileFieldName = {"COSEBFileName", "COSEBFileNameRoute", "COSEBFileExplan"};
	public final String[] arrCOSMSFileFieldName = {"COSMSFileName", "COSMSFileNameRoute", "COSMSFileExplan"};
	
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
	    	
	    	if("C".equals(fileType)){
	    		this.setCFileRowSBuilder(sb.toString());
	    	}else if("COSSD".equals(fileType)){
	    		this.setCOSSDFileRowSBuilder(sb.toString());
	    	}else if("COSDP".equals(fileType)){
	    		this.setCOSDPFileRowSBuilder(sb.toString());
	    	}else if("COSVR".equals(fileType)){
	    		this.setCOSVRFileRowSBuilder(sb.toString());
	    	}else if("COSPT".equals(fileType)){
	    		this.setCOSPTFileRowSBuilder(sb.toString());
	    	}else if("COSRT".equals(fileType)){
	    		this.setCOSRTFileRowSBuilder(sb.toString());
	    	}else if("COSHA".equals(fileType)){
	    		this.setCOSHAFileRowSBuilder(sb.toString());
	    	}else if("COSEB".equals(fileType)){
	    		this.setCOSEBFileRowSBuilder(sb.toString());
	    	}else if("COSMS".equals(fileType)){
	    		this.setCOSMSFileRowSBuilder(sb.toString());
	    	}
	    }
		return "";
	}
	public void setProcessPage(COSIN0601F obj, Cos0001Db c, boolean isBussiness) throws Exception {
		setProcessPage(obj, c, isBussiness, false);
	}
	
	public void setProcessPage(COSIN0601F obj, Cos0001Db c, boolean isBussiness, boolean isSpecialFlow) throws Exception {
		// 檢核是否廠商回覆作業的呼叫
		boolean isContinue = true;
		if(isBussiness){
			Cos0001Db anotherCos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where applNo = " + Common.sqlChar(c.getForeignApplNo()));
			if(anotherCos0001Db != null){
				if("1".equals(Common.get(c.getCosType()))){
					Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where (isClose is null or isClose <> 'Y') and applNo = " + Common.sqlChar(c.getApplNo()) + " order by id desc ");
					if(cos0009Db != null){
						Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where (isClose is null or isClose <> 'Y') and applNo = " + Common.sqlChar(anotherCos0001Db.getApplNo()) + " order by id desc ");
						if(cos0010Db != null){
							isContinue = false;
							
							obj.setIsCombineBussinessPage("Y");
							obj.setCos0910Id(Common.get(cos0009Db.getId()) + ";" + Common.get(cos0010Db.getId()));
							obj.setNotifyDate0910(cos0009Db.getNotifyDate());
							obj.setReplyDate0910(cos0009Db.getReplyDate());
							obj.setIsComplaintYn0910(cos0010Db.getIsComplaintYn());
							obj.setComplaintNum0910(cos0010Db.getComplaintNum());
							obj.setSimilarComplaint0910(cos0009Db.getSimilarComplaint());
							obj.setPrecaution0910(cos0009Db.getPrecaution());
							obj.setHandling0910(cos0009Db.getHandling());
							obj.setRemark0910(cos0009Db.getRemark());
							obj.setPredictDate0910(cos0009Db.getPredictDate());
							
							if("Y".equals(Common.get(cos0009Db.getIsClose())) && "Y".equals(Common.get(cos0010Db.getIsClose()))){
								obj.setIsClose0910("Y");
							}
							
							// 檔案處理為撰寫
							this.Combine0910File(obj, cos0009Db, cos0010Db);
						}else{
							isContinue = true;
						}
					}else{
						isContinue = true;
					}
				}else{
					Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where (isClose is null or isClose <> 'Y') and applNo = " + Common.sqlChar(c.getApplNo()) + " order by id desc ");
					if(cos0010Db != null){
						Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where (isClose is null or isClose <> 'Y') and applNo = " + Common.sqlChar(anotherCos0001Db.getApplNo()) + " order by id desc ");
						if(cos0009Db != null){
							isContinue = false;
							
							obj.setIsCombineBussinessPage("Y");
							obj.setCos0910Id(Common.get(cos0009Db.getId()) + ";" + Common.get(cos0010Db.getId()));
							obj.setNotifyDate0910(cos0010Db.getNotifyDate());
							obj.setReplyDate0910(cos0010Db.getReplyDate());
							obj.setIsComplaintYn0910(cos0010Db.getIsComplaintYn());
							obj.setComplaintNum0910(cos0010Db.getComplaintNum());
							obj.setSimilarComplaint0910(cos0010Db.getDealWith());
							obj.setPrecaution0910(cos0009Db.getPrecaution());
							obj.setHandling0910(cos0009Db.getHandling());
							obj.setRemark0910(cos0009Db.getRemark());
							obj.setPredictDate0910(cos0009Db.getPredictDate());
							
							if("Y".equals(Common.get(cos0009Db.getIsClose())) && "Y".equals(Common.get(cos0010Db.getIsClose()))){
								obj.setIsClose0910("Y");
							}
							
							// 檔案處理為撰寫
							this.Combine0910File(obj, cos0009Db, cos0010Db);
						}else{
							isContinue = true;
						}
					}else{
						isContinue = true;
					}
				}
				
				// 歷史紀錄
				if(!isContinue){
					java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
					if("1".equals(Common.get(c.getCosType()))){
						java.util.List<Cos0009Db> cos0009DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos0009Db where isClose = 'Y' and applNo = " + Common.sqlChar(c.getApplNo()) + " order by id desc ");
						if(cos0009DbList!=null && cos0009DbList.size()>0){
							for(Cos0009Db dtl09 : cos0009DbList){
								String[] row1 = new String[6];
								row1[0] = Common.get(dtl09.getId());
								row1[1] = Common.get(dtl09.getNotifyDate());
								row1[2] = Common.get(dtl09.getReplyDate());
								if(dtl09.getCos0010DbId() != null){
									row1[3] = Common.get(dtl09.getCos0010DbId());
									row1[4] = "0910";
									row1[5] = "09";
								}else{
									row1[3] = "";
									row1[4] = "09";
									row1[5] = "";
								}
								arrList.add(row1);
							}
							cos0009DbList.clear();
						}
						cos0009DbList = null;
					}else{
						java.util.List<Cos0010Db> cos0010DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos0010Db where isClose = 'Y' and applNo = " + Common.sqlChar(c.getApplNo()) + " order by id desc ");
						if(cos0010DbList!=null && cos0010DbList.size()>0){
							for(Cos0010Db dtl10 : cos0010DbList){
								String[] row2 = new String[6];
								row2[0] = Common.get(dtl10.getId());
								row2[1] = Common.get(dtl10.getNotifyDate());
								row2[2] = Common.get(dtl10.getReplyDate());
								if(dtl10.getCos0009DbId() != null){
									row2[3] = Common.get(dtl10.getCos0009DbId());
									row2[4] = "0910";
									row2[5] = "10";
								}else{
									row2[3] = "";
									row2[4] = "10";
									row2[5] = "";
								}
								arrList.add(row2);
							}
							cos0010DbList.clear();
						}
						cos0010DbList = null;
					}
					setCos0910DbList(arrList);
				}
			}else{
				isContinue = true;
			}
		}
		
		if(isContinue){
			java.util.List<Con0001Db> fileList = new java.util.ArrayList<Con0001Db>();
			obj.setIsCombineBussinessPage("N");
			
			if("1".equals(Common.get(c.getCosType()))){
				
				// 不良品廠商回覆
				java.util.List<Cos0009Db> cos0009DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos0009Db where applNo = " + Common.sqlChar(c.getApplNo()) + " order by id desc ");
				if(cos0009DbList!=null && cos0009DbList.size()>0){
					boolean flag = true;
					java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
					for(Cos0009Db dtl : cos0009DbList){
						if("Y".equals(Common.get(dtl.getIsClose()))){
							String[] rowArray = new String[3];
							rowArray[0] = Common.get(dtl.getId());
							rowArray[1] = Common.get(dtl.getNotifyDate());
							rowArray[2] = Common.get(dtl.getReplyDate());
							arrList.add(rowArray);
						}
						if(flag){
							obj.setCos0009DbId(Common.get(dtl.getId()));
							obj.setNotifyDate9(dtl.getNotifyDate());
							obj.setReplyDate9(dtl.getReplyDate());
							obj.setHandling(dtl.getHandling());
							obj.setPrecaution9(dtl.getPrecaution());
							obj.setSimilarComplaint(dtl.getSimilarComplaint());
							obj.setRemark9(dtl.getRemark());
							obj.setPredictDate(dtl.getPredictDate());
							
							if("Y".equals(Common.get(dtl.getIsClose()))){
								obj.setIsClose09("Y");
							}
							
							fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSVR' and dbName = 'COS0009DB' and upLoadId = " + Common.getLong(dtl.getId()));
							if(fileList!=null && fileList.size()>0){
								obj.genFileRowItemSet(fileList, "COSVR");				
							}
							fileList.clear();
							flag = false;
						}
					}
					if(flag){
						obj.setCos0009DbId("");
					}
					obj.setIsNeedShowCos0009DbPage("Y");
					obj.setCos0009DbList(arrList);
					cos0009DbList.clear();
				}else{
					if(isSpecialFlow){
						obj.setIsNeedShowCos0009DbPage("Y");
					}else{
						obj.setIsNeedShowCos0009DbPage("N");
					}
					obj.setCos0009DbId("");
					obj.setCos0009DbList(null);
				}
				
				// 不良品處理頁籤
				Cos0011Db cos0011Db = (Cos0011Db)View.getObject(" from Cos0011Db where applNo = " + Common.sqlChar(c.getApplNo()));
				if(cos0011Db != null){
					obj.setIsLetterYn11(Common.get(cos0011Db.getIsLetterYn()));
					obj.setRemark11(Common.get(cos0011Db.getRemark()));
					obj.setReplyMemo11(Common.get(cos0011Db.getReplyMemo()));
					
					java.util.List<Cos0012Db> con0012DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos0012Db where cos0011Db.id = " + cos0011Db.getId());
					genCos0012DbItemSet(con0012DbList);
					
					java.util.List<Cos0013Db> con0013DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos0013Db where cos0011Db.id = " + cos0011Db.getId());
					genCos0013DbItemSet(con0013DbList);
					
					fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSPT' and dbName = 'COS0011DB' and upLoadId = " + Common.getLong(cos0011Db.getId()));
					if(fileList!=null && fileList.size()>0){
						obj.genFileRowItemSet(fileList, "COSPT");				
					}
					fileList.clear();
					
					fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSRT' and dbName = 'COS0011DB' and upLoadId = " + Common.getLong(cos0011Db.getId()));
					if(fileList!=null && fileList.size()>0){
						obj.genFileRowItemSet(fileList, "COSRT");				
					}
					fileList.clear();
				}
				
				// 轉發的衛生單位
				obj.setCon1003DbId(Common.get(c.getCon1003DbId()));
				
				// 判斷是否顯示轉送TFDA
				Sys0001Db sys0001Db = (Sys0001Db)View.getObject(" from Sys0001Db where id = 1 ");
				if(sys0001Db == null){
					sys0001Db = new Sys0001Db();
					sys0001Db.setField1("10");						// 預設 10 天
					sys0001Db.setField2("3");						// 預設 3 個月 -藍燈 
					sys0001Db.setField4("6");						// 預設 6 個月 -黃燈
					sys0001Db.setField5("9");						// 預設 9 個月 -紅燈
				}
				if(Common.getLong(c.getCon1003DbId()) != 0){		// 判斷衛生單位是否預期
					String tMon = "0";
					String pDate = View.getLookupField(" select processDate from Con2001Db where systemType = 'COS02' " +
													   " and formID = " + Common.getLong(c.getId()) + 
													   " and applNo = " + Common.sqlChar(c.getApplNo()) + 
													   " and state = '50' ");
					tMon = Datetime.getDateDiff("m", pDate, Datetime.getYYYMMDD());
					
					// 103.04.16 由3個月，改成九個月
					if(Common.getInt(tMon) > Common.getInt(sys0001Db.getField5())){
						obj.setIsNeedShowSendTFDA("Y");
					}else{
						obj.setIsNeedShowSendTFDA("N");
					}
				}else{
					Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where applNo = " + Common.sqlChar(c.getApplNo()) + " order by id desc ");
					if(cos0009Db != null){
						String tDay = "0";
						if("Y".equals(Common.get(cos0009Db.getIsClose()))){					// 廠商已回覆
							tDay = Datetime.getDateDiff("d", Common.get(cos0009Db.getNotifyDate()), Common.get(cos0009Db.getReplyDate()));
						}else{
							tDay = Datetime.getDateDiff("d", Common.get(cos0009Db.getNotifyDate()), Datetime.getYYYMMDD());
						}
						if(Common.getInt(tDay) > Common.getInt(sys0001Db.getField1())){
							obj.setIsNeedShowSendTFDA("Y");
						}else{
							obj.setIsNeedShowSendTFDA("N");
						}
					}else{
						if(isSpecialFlow){
							System.out.println("[TCBW]-[COSIN0601F]-[不良品追蹤作業]-[異常，無廠商回覆資料檔(至少為2擇一)，不該無COS0009_DB資料]");
						}
						obj.setIsNeedShowSendTFDA("N");
					}
				}
			}else{
				
				// 不良反應廠商回覆
				java.util.List<Cos0010Db> cos0010DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos0010Db where applNo = " + Common.sqlChar(c.getApplNo()) + " order by id desc ");
				if(cos0010DbList!=null && cos0010DbList.size()>0){
					boolean flag = true;
					java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
					for(Cos0010Db dtl : cos0010DbList){
						if("Y".equals(Common.get(dtl.getIsClose()))){
							String[] rowArray = new String[3];
							rowArray[0] = Common.get(dtl.getId());
							rowArray[1] = Common.get(dtl.getNotifyDate());
							rowArray[2] = Common.get(dtl.getReplyDate());
							arrList.add(rowArray);
						}
						if(flag){
							obj.setCos0010DbId(Common.get(dtl.getId()));
							obj.setNotifyDate10(dtl.getNotifyDate());
							obj.setIsComplaintYn10(dtl.getIsComplaintYn());
							obj.setComplaintNum(dtl.getComplaintNum());
							obj.setDealWith10(dtl.getDealWith());
							obj.setReplyOther10(dtl.getReplyOther());
							obj.setReplyDate10(dtl.getReplyDate());
							
							if("Y".equals(Common.get(dtl.getIsClose()))){
								obj.setIsClose10("Y");
							}
							
							fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSVR' and dbName = 'COS0010DB' and upLoadId = " + Common.getLong(dtl.getId()));
							if(fileList!=null && fileList.size()>0){
								obj.genFileRowItemSet(fileList, "COSVR");				
							}
							fileList.clear();
							flag = false;
						}
					}
					if(flag){
						obj.setCos0010DbId("");
					}
					obj.setCos0010DbList(arrList);
					cos0010DbList.clear();
				}else{
					obj.setCos0010DbId("");
					obj.setCos0010DbList(null);
				}
				
				// 不良反應-處理頁籤
				Cos0014Db cos0014Db = (Cos0014Db)View.getObject(" from Cos0014Db where applNo = " + Common.sqlChar(c.getApplNo()));
				if(cos0014Db != null){
					obj.setIsLetterYn14(cos0014Db.getIsLetterYn());
					obj.setDealWith14(cos0014Db.getDealWith());
					obj.setDealWithOther14(cos0014Db.getDealWithOther());
					obj.setAssess(cos0014Db.getAssess());
					obj.setSummary(cos0014Db.getSummary());
					obj.setResolution(cos0014Db.getResolution());
					obj.setResult(cos0014Db.getResult());
					obj.setPrecaution14(cos0014Db.getPrecaution());
					obj.setRecycling(cos0014Db.getRecycling());
					obj.setOtherDisposal(cos0014Db.getOtherDisposal());
					obj.setRemark14(cos0014Db.getRemark());
					obj.setReplyMemo14(Common.get(cos0014Db.getReplyMemo()));
					
					fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSHA' and dbName = 'COS0014DB' and upLoadId = " + Common.getLong(cos0014Db.getId()));
					if(fileList!=null && fileList.size()>0){
						obj.genFileRowItemSet(fileList, "COSHA");				
					}
					fileList.clear();
					
					fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSEB' and dbName = 'COS0014DB' and upLoadId = " + Common.getLong(cos0014Db.getId()));
					if(fileList!=null && fileList.size()>0){
						obj.genFileRowItemSet(fileList, "COSEB");				
					}
					fileList.clear();
					
					fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSMS' and dbName = 'COS0014DB' and upLoadId = " + Common.getLong(cos0014Db.getId()));
					if(fileList!=null && fileList.size()>0){
						obj.genFileRowItemSet(fileList, "COSMS");				
					}
					fileList.clear();
				}
				
				obj.setIsNeedShowSendTFDA("N");
			}
			
			fileList = null;
		}else{
			System.out.println("[TCBW]-[COSIN0601F]-[廠商回覆頁籤-為同時須顯示合併的廠商回覆]");
		}
	}
	
	// FOR 廠商回覆作業合併畫面使用
	public void Combine0910File(COSIN0601F obj, Cos0009Db cos0009Db, Cos0010Db cos0010Db) throws Exception {
		java.util.List<Con0001Db> file09List = null;
		if(cos0009Db != null){
			file09List = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSVR' and dbName = 'COS0009DB' and upLoadId = " + Common.getLong(cos0009Db.getId()));
		}
		
		java.util.List<Con0001Db> file10List = null;
		if(cos0010Db != null){
			file10List = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSVR' and dbName = 'COS0010DB' and upLoadId = " + Common.getLong(cos0010Db.getId()));
		}
		
		java.util.List<Con0001Db> file0910List = new java.util.ArrayList<Con0001Db>();
		if(file09List!=null && file09List.size()>0 && file10List!=null && file10List.size()>0){
			file0910List.addAll(file09List);
			for(Con0001Db dtl10 : file10List){
				boolean flag = false;
				for(Con0001Db dtl09 : file09List){
					if(!Common.get(dtl10.getFileRoute()).equals(Common.get(dtl09.getFileRoute()))){
						file0910List.add(dtl10);
						flag = true;
					}
					if(flag){
						break;
					}
				}
			}
		}else{
			if(file09List!=null && file09List.size()>0){
				file0910List.addAll(file09List);
			}
			if(file10List!=null && file10List.size()>0){
				file0910List.addAll(file10List);
			}
		}
		
		if(file09List != null){
			file09List.clear();
		}
		file09List = null;
		
		if(file10List != null){
			file10List.clear();
		}
		file10List = null;
		
		if(file0910List!=null && file0910List.size()>0){
			obj.genFileRowItemSet(file0910List, "COSVR");				
		}
		
	}
	
	
	
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Cos0001Db where status = '50' ";
		if("1S".equals(getQ_type()))
			hql += " and cosType = '1' ";
		else
			hql += " and cosType = '2' ";
		if(!"".equals(getQ_applNo()))
			hql += " and applNo = " + Common.sqlChar(getQ_applNo());
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(getQ_permitKey()))
			hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
		if(!"".equals(getQ_chProduct()))
			hql += " and chProduct like " + Common.sqlChar("%" + getQ_chProduct() + "%");
		if(!"".equals(getQ_manufactorName()))
			hql += " and manufactorName like " + Common.sqlChar("%" + getQ_manufactorName() + "%");
		if(!"".equals(getQ_chargeMan()))
			hql += " and chargeMan = " + Common.sqlChar(getQ_chargeMan());
		
		// 不良品
		if("1S".equals(getQ_type())){
			if(getQ_subCode()!=null && getQ_subCode().length>0){
				StringBuffer sb = new StringBuffer();
				sb.append("(");
				
				boolean flag = true;
				for(String rid : getQ_subCode()){	
					if(flag){
						sb.append(" subCode like " + Common.sqlChar("%" + rid + "%"));
						flag = false;
					}else{
						sb.append(" or subCode like " + Common.sqlChar("%" + rid + "%"));
					}
				}
				sb.append(")");
				hql += " and id in (select distinct cos0001Db.id from Cos0003Db where " + sb.toString() + ") ";
			}
		}else{
			if(!"".equals(getQ_adverseCondition())){
				hql += " and id in (select distinct cos0001Db.id from Cos0002Db where adverseCondition = " + Common.sqlChar(getQ_adverseCondition()) + ")";
			}
		}
		System.out.println("[TCBW]-[COSIN0601F]-[QUERYALL] : " + hql + " order by id ");
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc");
		if (objList != null && objList.size() > 0) {
			java.util.Map<String, String> CPTMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CPT", null);
			java.util.Map<String, String> CCSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CCS", null);
			java.util.Map<String, String> CACMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CAC", null);
			
			java.util.Map<String, String> subCodeNameMap = new java.util.HashMap<String, String>();
			if("1S".equals(getQ_type())){
				java.util.List<Cos1001Db> cos1001DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where isStop = 'N'");
				if(cos1001DbList!=null && cos1001DbList.size()>0){
					for(Cos1001Db cos1001Db : cos1001DbList){
						subCodeNameMap.put(Common.get(cos1001Db.getDpdKind()), Common.get(cos1001Db.getDpdKindName()));
					}
					cos1001DbList.clear();
				}
			}
			
			java.util.Map<String, String> cosUserMap = new java.util.HashMap<String, String>();
			java.util.List userList = ServiceGetter.getInstance().getTcbwService().load("select userId, userName from CommonUser where inORout = 'in'");
			if(userList!=null && userList.size()>0){
				for(Object dtlObj : userList){
					Object[] dtl = (Object[])dtlObj;
					cosUserMap.put(Common.get(dtl[0]), Common.get(dtl[1]));
				}
				userList.clear();
			}
			Sys0001Db sys0001Db = (Sys0001Db)View.getObject(" from Sys0001Db where id = 1 ");
			if(sys0001Db == null){
				sys0001Db = new Sys0001Db();
				sys0001Db.setField1("10");						// 預設 10 天
				sys0001Db.setField2("3");						// 預設 3 個月 - 藍燈
				sys0001Db.setField1("20");						// 預設 20 天
				sys0001Db.setField4("6");						// 預設 3 個月 - 黃燈
				sys0001Db.setField5("9");						// 預設 3 個月 - 紅燈
			}
			
			for(Object dtlObj : objList) {				
				Cos0001Db dtl = (Cos0001Db)dtlObj;
				
				String[] rowArray = new String[12];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[3] = Common.get(dtl.getApplNo());
				rowArray[4] = Common.get(dtl.getNotifierRepDate());
				rowArray[5] = "";
				if(!"".equals(Common.get(dtl.getPermitKey())) || !"".equals(Common.get(dtl.getPermitNo()))){
					rowArray[5] = (Common.get(CPTMap.get(Common.get(dtl.getPermitKey()))).equals("")?Common.get(dtl.getPermitKey()):Common.get(CPTMap.get(Common.get(dtl.getPermitKey())))) + 
								  " 字第 " + Common.get(dtl.getPermitNo()) + " 號";
				}
				rowArray[6] = Common.get(dtl.getChProduct());
				rowArray[7] = Common.get(dtl.getEnProduct());
				rowArray[8] = Common.get(dtl.getManufactorName());
				
				rowArray[9] = "";
				if("1S".equals(getQ_type())){
					if(dtl.getCos0003Dbs()!=null && dtl.getCos0003Dbs().size()>0){
						StringBuffer sb = new StringBuffer();
						for(Object cos0003DbObj : dtl.getCos0003Dbs()){
							Cos0003Db cos0003Db = (Cos0003Db)cos0003DbObj;
							if(sb.toString().length() > 0){
								sb.append("、");
							}
							sb.append(Common.get(subCodeNameMap.get(Common.get(cos0003Db.getSubCode()))));
						}
						rowArray[9] = sb.toString();
					}
				}else{
					if(dtl.getCos0002Dbs()!=null && dtl.getCos0002Dbs().size()>0){
						StringBuffer sb = new StringBuffer();
						for(Object cos0002DbObj : dtl.getCos0002Dbs()){
							Cos0002Db cos0002Db = (Cos0002Db)cos0002DbObj;
							if(sb.toString().length() > 0){
								sb.append("、");
							}
							sb.append(Common.get(CACMap.get(Common.get(cos0002Db.getAdverseCondition()))).equals("")?Common.get(cos0002Db.getAdverseCondition()):Common.get(CACMap.get(Common.get(cos0002Db.getAdverseCondition()))));
						}
						rowArray[9] = sb.toString();
					}
				}
				
				rowArray[10] = Common.get(CCSMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(CCSMap.get(Common.get(dtl.getStatus())));
				rowArray[11] = Common.get(cosUserMap.get(Common.get(dtl.getChargeMan()))).equals("")?Common.get(dtl.getChargeMan()):Common.get(cosUserMap.get(Common.get(dtl.getChargeMan())));
				
				// 燈號處理
				String bussinessLight = "";
				String HLight = "";
				if("1S".equals(getQ_type())){
					Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where applNo = " + Common.sqlChar(dtl.getApplNo()) + " order by id desc ");
					if(cos0009Db != null){
						String tDay = "0";
						if("Y".equals(Common.get(cos0009Db.getIsClose()))){					// 廠商已回覆
							tDay = Datetime.getDateDiff("d", Common.get(cos0009Db.getNotifyDate()), Common.get(cos0009Db.getReplyDate()));
						}else{
							tDay = Datetime.getDateDiff("d", Common.get(cos0009Db.getNotifyDate()), Datetime.getYYYMMDD());
						}
						
						// 廠商回覆大於廠商回覆稽催期限(第一次) - 黃燈，若逾期後，再大於廠商回覆稽催期限(第二次)-紅燈
						if(Common.getInt(tDay) > Common.getInt(sys0001Db.getField1())){
							bussinessLight = "yellow";
							
							if(Common.getInt(tDay) > (Common.getInt(sys0001Db.getField3()))){
								bussinessLight = "red";
							}
						}
					}else{
						System.out.println("[TCBW]-[COSIN0601F]-[不良品追蹤作業]-[無廠商回覆資料檔，由衛生單位判別燈號]");
					}
					
					// 表示有轉發衛生單位
					if(dtl.getCon1003DbId() != null){
						String tMon = "0";
						String pDate = View.getLookupField(" select processDate from Con2001Db where systemType = 'COS02' " +
														   " and formID = " + Common.getLong(dtl.getId()) + 
														   " and applNo = " + Common.sqlChar(dtl.getApplNo()) + 
														   " and state = '50' ");
						tMon = Datetime.getDateDiff("m", pDate, Datetime.getYYYMMDD());
						
						if(Common.getInt(tMon) > Common.getInt(sys0001Db.getField5())){
							HLight = "red";
						}else if(Common.getInt(sys0001Db.getField5())>Common.getInt(tMon) && Common.getInt(tMon)>=Common.getInt(sys0001Db.getField4())){
							HLight = "yellow";
						}else if(Common.getInt(sys0001Db.getField4())>Common.getInt(tMon) && Common.getInt(tMon)>=Common.getInt(sys0001Db.getField2())){
							HLight = "blue";
						}
					}
				}else{
					Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where applNo = " + Common.sqlChar(dtl.getApplNo()) + " order by id desc ");
					if(cos0010Db != null){
						String tDay = "0";
						if("Y".equals(Common.get(cos0010Db.getIsClose()))){					
							tDay = Datetime.getDateDiff("d", Common.get(cos0010Db.getNotifyDate()), Common.get(cos0010Db.getReplyDate()));
						}else{
							tDay = Datetime.getDateDiff("d", Common.get(cos0010Db.getNotifyDate()), Datetime.getYYYMMDD());
						}
						
						// 廠商回覆大於廠商回覆稽催期限(第一次) - 黃燈，若逾期後，再大於廠商回覆稽催期限(第二次)-紅燈
						if(Common.getInt(tDay) > Common.getInt(sys0001Db.getField1())){
							bussinessLight = "yellow";
							
							if(Common.getInt(tDay) > (Common.getInt(sys0001Db.getField3()))){
								bussinessLight = "red";
							}
						}
					}else{
						System.out.println("[TCBW]-[COSIN0601F]-[不良反應追蹤作業]-[無廠商回覆資料檔，無法判別燈號]");
					}
				}
				
				if("yellow".equals(bussinessLight)){
					rowArray[1] = "<img width='30px' src='../../images/ballYellow.png'>";
				}else if("red".equals(bussinessLight)){
					rowArray[1] = "<img width='30px' src='../../images/ballRed.png'>";
				}else{
					rowArray[1] = "";
				}
				
				if("yellow".equals(HLight)){
					rowArray[2] = "<img width='30px' src='../../images/ballYellow.png'>";
				}else if("red".equals(HLight)){
					rowArray[2] = "<img width='30px' src='../../images/ballRed.png'>";
				}else if("blue".equals(HLight)){
					rowArray[2] = "<img width='30px' src='../../images/ballBlue.png'>";
				}else{
					rowArray[2] = "";
				}
				arrList.add(rowArray);
			}
			objList.clear();
			
			CPTMap.clear();
			CCSMap.clear();
			CACMap.clear();
			subCodeNameMap.clear();
			cosUserMap.clear();
		}
		return arrList;
	}
	
	

}
