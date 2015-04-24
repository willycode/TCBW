package com.kangdainfo.tcbw.view.cosin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.BeanUtil;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos0005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class COSIN0201Q extends SuperBean {
	
	private String caseType;
	private String id;								// 序號	NUMERIC(19,0)
	private String doType;
	private String isInOrOutPerson;
    private String inOrOut;							// 分內外網
    private String talbe3;
    private String talbe4;
    private String statusType;
    
    public String getCaseType() {		return checkGet(caseType);	}
	public void setCaseType(String caseType) {		this.caseType = checkSet(caseType);	}
	public String getId() {		return checkGet(id);	}
	public void setId(String id) {		this.id = checkSet(id);	}
	public String getDoType() {		return checkGet(doType);	}
	public void setDoType(String doType) {		this.doType = checkSet(doType);	}
	public String getIsInOrOutPerson() {	return checkGet(isInOrOutPerson);	}
	public void setIsInOrOutPerson(String isInOrOutPerson) {		this.isInOrOutPerson = checkSet(isInOrOutPerson);	}
	public String getInOrOut() {		return checkGet(inOrOut);	}
	public void setInOrOut(String inOrOut) {		this.inOrOut = checkSet(inOrOut);	}
	public String getTalbe3() {		return checkGet(talbe3);	}
	public void setTalbe3(String talbe3) {		this.talbe3 = checkSet(talbe3);	}
	public String getTalbe4() {		return checkGet(talbe4);	}
	public void setTalbe4(String talbe4) {		this.talbe4 = checkSet(talbe4);	}
	public String getStatusType() {		return checkGet(statusType);	}
	public void setStatusType(String statusType) {		this.statusType = checkSet(statusType);	}
    
    private String q_applNoS;						// 案件編號起
    private String q_applNoE;						// 案件編號迄
    private String q_notifierRepDateS;				// 通報日期起
    private String q_notifierRepDateE;				// 通報日期迄
    private String q_notifierSource;				// 通報來源
    private String q_notifierType;					// 通報者屬性
    private String q_notifierTitle;					// 通報者職稱
    private String[] q_cosType;						// 不良事件類別
    private String q_permitKey;						// 許可證字
    private String q_permitNo;						// 許可證號
    private String q_caseNo;						// 登錄編號
    private String q_preResult;
    private String[] reportField;
    
	public String getQ_applNoS() {		return checkGet(q_applNoS);	}
	public void setQ_applNoS(String q_applNoS) {		this.q_applNoS = checkSet(q_applNoS);	}
	public String getQ_applNoE() {		return checkGet(q_applNoE);	}
	public void setQ_applNoE(String q_applNoE) {		this.q_applNoE = checkSet(q_applNoE);	}
	public String getQ_notifierRepDateS() {		return checkGet(q_notifierRepDateS);	}
	public void setQ_notifierRepDateS(String q_notifierRepDateS) {		this.q_notifierRepDateS = checkSet(q_notifierRepDateS);	}
	public String getQ_notifierRepDateE() {		return checkGet(q_notifierRepDateE);	}
	public void setQ_notifierRepDateE(String q_notifierRepDateE) {		this.q_notifierRepDateE = checkSet(q_notifierRepDateE);	}
	public String getQ_notifierSource() {		return checkGet(q_notifierSource);	}
	public void setQ_notifierSource(String q_notifierSource) {		this.q_notifierSource = checkSet(q_notifierSource);	}
	public String getQ_notifierType() {		return checkGet(q_notifierType);	}
	public void setQ_notifierType(String q_notifierType) {		this.q_notifierType = checkSet(q_notifierType);	}
	public String getQ_notifierTitle() {		return checkGet(q_notifierTitle);	}
	public void setQ_notifierTitle(String q_notifierTitle) {		this.q_notifierTitle = checkSet(q_notifierTitle);	}
	public String[] getQ_cosType() {		return checkGet(q_cosType);	}
	public void setQ_cosType(String[] q_cosType) {		this.q_cosType = checkSet(q_cosType);	}
	public String getQ_permitKey() {		return checkGet(q_permitKey);	}
	public void setQ_permitKey(String q_permitKey) {		this.q_permitKey = checkSet(q_permitKey);	}
	public String getQ_permitNo() {		return checkGet(q_permitNo);	}
	public void setQ_permitNo(String q_permitNo) {		this.q_permitNo = checkSet(q_permitNo);	}
	public String getQ_caseNo() {		return checkGet(q_caseNo);	}
	public void setQ_caseNo(String q_caseNo) {		this.q_caseNo = checkSet(q_caseNo);	}
	public String getQ_preResult() {		return checkGet(q_preResult);	}
	public void setQ_preResult(String q_preResult) {		this.q_preResult = checkSet(q_preResult);	}
	public String[] getReportField() {		return checkGet(reportField);	}
	public void setReportField(String[] reportField) {		this.reportField = checkSet(reportField);	}

	private java.util.Map<String, String> CCTMap ;
	private java.util.Map<String, String> CISMap ;
	private java.util.Map<String, String> CONNFT1Map ;
	private java.util.Map<String, String> TITLEMap ;
	private java.util.Map<String, String> CFRMap ;
	private java.util.Map<String, String> CACMap ;
	private java.util.Map<String, String> COSTIMMap ;
	private java.util.Map<String, String> CSPMap ;
	private java.util.Map<String, String> CTYMap ;
	private java.util.Map<String, String> COSFRMap ;
	private java.util.Map<String, String> COSCORMap ;
	private java.util.Map<String, String> resultMap ;
	private java.util.Map<String, String> CMSMap ;
	private java.util.Map<String, String> CCIMap ;
	
	//--------------------------------設定代碼
	private void initCodeMap() throws Exception {
		CCTMap = TCBWCommon.getCommonCodeMap("CCT");
		CISMap = TCBWCommon.getCommonCodeMap("CIS");
		CONNFT1Map = TCBWCommon.getCommonCodeMap("CONNFT1");
		TITLEMap = TCBWCommon.getCommonCodeMap("TITLE");
		CFRMap = TCBWCommon.getCommonCodeMap("CFR");
		CACMap = TCBWCommon.getCommonCodeMap("CAC");
		COSTIMMap = TCBWCommon.getCommonCodeMap("COSTIM");
		CSPMap = TCBWCommon.getCommonCodeMap("CSP");
		CTYMap = TCBWCommon.getCommonCodeMap("CTY");
		COSFRMap = TCBWCommon.getCommonCodeMap("COSFR");
		COSCORMap = TCBWCommon.getCommonCodeMap("COSCOR");
		CMSMap = TCBWCommon.getCommonCodeMap("CMS");
		CCIMap = TCBWCommon.getCommonCodeMap("CCI");
		
		resultMap = new java.util.HashMap<String, String>();
		resultMap.put("Y", "是");
		resultMap.put("N", "否");
		resultMap.put("U", "無法得知");
	}
	
	private void closeMap() throws Exception {
		if(CCTMap != null)	CCTMap.clear();
		if(CISMap != null)	CISMap.clear();
		if(CONNFT1Map != null)	CONNFT1Map.clear();
		if(TITLEMap != null)	TITLEMap.clear();
		if(CFRMap != null)	CFRMap.clear();
		if(CACMap != null)	CACMap.clear();
		if(COSTIMMap != null)	COSTIMMap.clear();
		if(CSPMap != null)	CSPMap.clear();
		if(CTYMap != null)	CTYMap.clear();
		if(COSFRMap != null)	COSFRMap.clear();
		if(COSCORMap != null)	COSCORMap.clear();
		if(CMSMap != null)	CMSMap.clear();	
		if(CCIMap != null)	CCIMap.clear();	
		if(resultMap != null)	resultMap.clear();
	}
	
    
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}
	
	private String getHql() {
		String hql = " from Cos0001Db cos0001 where cos0001.applNo is not null ";
		
		// 案件編號
		if(!"".equals(getQ_applNoS()))
			hql += " and cos0001.applNo >= " + Common.sqlChar(getQ_applNoS());
		
		if(!"".equals(getQ_applNoE()))
			hql += " and cos0001.applNo <= " + Common.sqlChar(getQ_applNoE());
		
		// 通報日期起
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and cos0001.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		
		// 通報日期迄
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and cos0001.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		
		// 通報來源
		if(!"".equals(getQ_notifierSource()))
			hql += " and cos0001.notifierSource = " + Common.sqlChar(getQ_notifierSource());
		
		// 通報者屬性
		if(!"".equals(getQ_notifierType()))
			hql += " and cos0001.notifierType = " + Common.sqlChar(getQ_notifierType());
		
		// 通報者職稱
		if(!"".equals(getQ_notifierTitle()))
			hql += " and cos0001.notifierTitle = " + Common.sqlChar(getQ_notifierTitle());
		
		// 不良事件類別
		if(getQ_cosType()!=null && getQ_cosType().length > 0){
			hql += " and (cos0001.cosType in (";
			for(int i = 0 ; i < getQ_cosType().length ; i ++)
				if(getQ_cosType()[i] != null && !"".equals(getQ_cosType()[i])){
					hql += Common.sqlChar(getQ_cosType()[i]) ;
					hql += (i < getQ_cosType().length -1)?",":"";
				}
			hql += ")";
			hql += ")";
		}
		
		// 許可證字
		if(!"".equals(getQ_permitKey()))
			hql += " and cos0001.permitKey = " + Common.sqlChar(getQ_permitKey());
		
		// 許可證號
		if(!"".equals(getQ_permitNo()))
			hql += " and cos0001.permitNo = " + Common.sqlChar(getQ_permitNo());
		
		// 登錄編號
		if(!"".equals(getQ_caseNo()))
			hql += " and cos0001.caseNo = " + Common.sqlChar(getQ_caseNo());
		
		// 留案備查
		if(!"".equals(getQ_preResult())){
			hql += " and (cos0001.applNo in (select distinct applNo from Cos0006Db where firstResult = '03') or cos0001.applNo in (select distinct applNo from Cos0008Db where preResult = '02'))";
		}
		
		return hql;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = getHql(); 
		System.out.println("[TCBW]-[COSIN0201Q]-[QUERYALL] : " + hql);
		
		this.processCurrentPageAttribute(Common.getInt(View.getLookupField(" select count(distinct cos0001.id) " + hql)));
		if(getTotalRecord() > 0){
			if(getState().indexOf("query")<0)
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList_Cos0001Db = ServiceGetter.getInstance().getTcbwService().load(hql + " order by cos0001.id", this.getRecordStart(), this.getPageSize());
			if(objList_Cos0001Db!=null && objList_Cos0001Db.size()>0){
				java.util.Map<String, String> cosTypeMap = TCBWCommon.getCommonCodeMap("CCT");
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("CCS");
				
				for(Object dtlObj : objList_Cos0001Db){				
					Cos0001Db dtl = (Cos0001Db)dtlObj;
					
					String[] rowArray = new String[9];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getOccurDate());
					rowArray[2] = Common.get(dtl.getApplNo());
					rowArray[3] = Common.get(cosTypeMap.get(dtl.getCosType()));
					rowArray[4] = Common.get(dtl.getChProduct());
					rowArray[5] = Common.get(dtl.getEnProduct());
					rowArray[6] = Common.get(dtl.getManufactorName());
					rowArray[7] = Common.get(statusMap.get(dtl.getStatus()));
					rowArray[8] = Common.get("明細");
					arrList.add(rowArray);
				}
				cosTypeMap.clear();
				statusMap.clear();
				objList_Cos0001Db.clear();
			}
			objList_Cos0001Db = null;
		}
		
		return arrList;
	}

	

	/**
	 * 傳回List<Object[]>
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> getTableModel() throws Exception{
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		
		String hql = getHql(); 
		java.util.List objList_Cos0001Db = ServiceGetter.getInstance().getTcbwService().load("select distinct cos0001 " + hql + " order by cos0001.id");
		if(objList_Cos0001Db!=null && objList_Cos0001Db.size()>0){
			initCodeMap();
			String[] reportField = getReportField();
			
			java.util.ArrayList<String> row = new java.util.ArrayList<String>();
			insertRow(arrList, row, getFieldNameMap(), reportField, true);
			
			for(Object dtlObj : objList_Cos0001Db){
				insertRow(arrList, row, dtlObj, reportField, false);
			}
			
			objList_Cos0001Db.clear();
		}
		objList_Cos0001Db = null;
		closeMap();
		
		return arrList;
	}
	
	private Map<String,String> getFieldNameMap() {
		Map<String,String> fMap = new HashMap<String,String>();
		fMap.put("applNo", "案件編號");
		fMap.put("notifierRepDate", "接獲通報日期");
		fMap.put("notifierSource", "通報來源");
		fMap.put("notifierType", "通報者屬性");
		fMap.put("notifierTitle", "通報者職稱");
		fMap.put("cosType", "不良事件類別");
		fMap.put("permitKey", "許可證字號");
		fMap.put("caseNo", "登錄編號");
		fMap.put("chProduct", "化粧品品名");
		fMap.put("ingredient", "化粧品項目");
		fMap.put("traffickWay", "販賣通路");
		fMap.put("tradePlace", "購買地點");
		fMap.put("manufactorName", "製造廠/進口代理商");
		fMap.put("manufactorNo", "製造批號或製造日期");
		fMap.put("subCode", "不良品缺陷描述");
		fMap.put("adverseCondition", "不良反應結果");
		fMap.put("isMitigateYn", "停用後不良反應是否減輕");
		fMap.put("isRecurYn", "再使用是否出現同樣反應");
		fMap.put("cName", "併用化粧品品名");
		fMap.put("firstResult", "不良品初步判定結果");
		fMap.put("preResult", "不良反應初步判定結果");
		fMap.put("measure", "採取措施");
		fMap.put("timingLev", "時序性");
		fMap.put("dealWith", "後續處理");
		fMap.put("dept1", "衛生單位回覆狀況");
		fMap.put("dept2", "廠商回覆狀況");
		fMap.put("cosCorrelation", "不良反應與可疑化粧品相關性");
		return fMap;
	}

	private void insertRow(ArrayList<Object[]> arrList, ArrayList<String> row, Object dtlObj, String[] reportField, boolean isFirstRow)throws Exception {
		row.clear();	
		for(String fieldName : reportField){
			String value = BeanUtil.getPropertyByBeanUtils(dtlObj, fieldName);
			if(!isFirstRow){
				if("notifierSource".equals(fieldName))
					value = CISMap.get(value);
				if("notifierType".equals(fieldName))
					value = CONNFT1Map.get(value);
				if("notifierTitle".equals(fieldName))
					value = TITLEMap.get(value);
				if("cosType".equals(fieldName))
					value = CCTMap.get(value);
				if("permitKey".equals(fieldName))
					value = value + BeanUtil.getPropertyByBeanUtils(dtlObj, "permitNo");
				if("traffickWay".equals(fieldName))
					value = CSPMap.get(value);
				if("tradePlace".equals(fieldName))
					value = CTYMap.get(value);
				if("ingredient".equals(fieldName))
					value = CCIMap.get(value);
				
				if(dtlObj instanceof Cos0001Db){
					Cos0001Db cos0001 = (Cos0001Db)dtlObj;
					
					// 不良品缺陷描述
					if("subCode".equals(fieldName)){
						if(cos0001.getCos0003Dbs() != null && cos0001.getCos0003Dbs().size() > 0){
							Object o = TCBWCommon.getMaxObjectById(cos0001.getCos0003Dbs());
							Cos0003Db cos0003 = (Cos0003Db)o;
							value = TCBWCommon.getCos0003SubCode(cos0003.getMainCode(), cos0003.getSubCode());
						}
					}
					
					// 不良品初步判定結果
					if("firstResult".equals(fieldName)){
						String firstResult = View.getLookupField("select firstResult from Cos0006Db where applNo = " + Common.sqlChar(cos0001.getApplNo()) + " order by id desc ");
						value = CFRMap.get(firstResult);
					}
					
					// 不良反應初步判定結果
					if("preResult".equals(fieldName)){
						String preResult = View.getLookupField("select preResult from Cos0008Db where applNo = "+ Common.sqlChar(cos0001.getApplNo()) + " order by id desc ");
						value = COSFRMap.get(preResult);
					}
					
					// 不良反應狀況，停用後不良反應是否減輕，再使用是否出現同樣反應
					if("adverseCondition".equals(fieldName) || "isMitigateYn".equals(fieldName) || "isRecurYn".equals(fieldName) || "cName".equals(fieldName)){
						if(cos0001.getCos0002Dbs()!=null && cos0001.getCos0002Dbs().size()>0){
							Cos0002Db cos0002 = null;
							for(Object dtl02Obj : cos0001.getCos0002Dbs()){
								cos0002 = (Cos0002Db)dtl02Obj;
								if(cos0002 != null){
									break;
								}
							}
							if(cos0002 != null){
								if("adverseCondition".equals(fieldName))
									value = CACMap.get(cos0002.getAdverseCondition());
								if("isMitigateYn".equals(fieldName))
									value = resultMap.get(cos0002.getIsMitigateYn());
								if("isRecurYn".equals(fieldName))
									value = resultMap.get(cos0002.getIsRecurYn());
								
								// 併用化粧品品名
								if("cName".equals(fieldName)){
									if(cos0002.getCos0005Dbs() != null && cos0002.getCos0005Dbs().size() > 0){
										long id = 0l;
										Cos0005Db cos0005 = null;
										for(Object dtl05Obj : cos0002.getCos0005Dbs()){
											Cos0005Db dtl05 = (Cos0005Db)dtl05Obj;
											if(Common.getLong(dtl05.getId()) >= id){
												cos0005 = dtl05;
												id = Common.getLong(dtl05.getId());
											}
										}
										if(cos0005 != null){
											value = cos0005.getcName();
										}
									}
								}
							}
						}
					}
					
					// 時序性
					if("timingLev".equals(fieldName)){
						String timeLev = View.getLookupField("select timingLev from Cos0008Db where applNo = " + Common.sqlChar(cos0001.getApplNo()) + " order by id desc");
						value = COSTIMMap.get(timeLev);
					}
					
					// 後續處置
					if("dealWith".equals(fieldName)){
						String dealWith = View.getLookupField("select dealWith from Cos0014Db where applNo = " + Common.sqlChar(cos0001.getApplNo()) + " order by id desc");
						value =  TCBWCommon.getCodeNameDealWithMap().get(dealWith);
					}
					
					// 不良反應與可疑化粧品相關性
					if("cosCorrelation".equals(fieldName)){
						String cosCorrelation = View.getLookupField("select cosCorrelation from Cos0015Db where applNo = " + Common.sqlChar(cos0001.getApplNo()) + " order by id desc");
						value =  COSCORMap.get(cosCorrelation);
					}
					
					// 採取措施
					if("measure".equals(fieldName)){
						if("1".equals(Common.get(cos0001.getCosType()))){
							String measure = View.getLookupField("select measure from Cos0006Db where applNo = " + Common.sqlChar(cos0001.getApplNo()) + " order by id desc ");
							if(!"".equals(measure)){
								String[] m = measure.split(",");
								if(m!=null && m.length>0){
									for(String s : m){
										if(value.length() > 0){
											value += "、";
										}
										value += Common.get(CMSMap.get(s));
									}
								}
							}
						}else if("2".equals(Common.get(cos0001.getCosType()))){
							value = "寄發廠商詢問函";
						}
					}
					
					// 衛生單位回覆狀況
					if("dept1".equals(fieldName)){
						if("1".equals(Common.get(cos0001.getCosType()))){
							String postMemo = View.getLookupField(" select a.postMemo from Cos0013Db a " +
																  " where a.cos0011Db.id = (select max(b.id) from Cos0011Db b where b.applNo = " + Common.sqlChar(cos0001.getApplNo()) + ") " +
																  " order by a.id desc ");
							if("".equals(postMemo)){
								value = "尚未回覆";
							}else{
								value = postMemo;
							}
						}else if("2".equals(Common.get(cos0001.getCosType()))){
							value = "";
						}
					}
					
					// 廠商回覆狀況
					if("dept2".equals(fieldName)){
						if("1".equals(Common.get(cos0001.getCosType()))){
							boolean isBussiness = false;
							String measure = View.getLookupField("select measure from Cos0006Db where applNo = " + Common.sqlChar(cos0001.getApplNo()) + " order by id desc ");
							if(!"".equals(measure)){
								String[] m = measure.split(",");
								if(m!=null && m.length>0){
									for(String s : m){
										if("2".equals(s)){
											isBussiness = true;
										}
										if(isBussiness){
											break;
										}
									}
								}else{
									isBussiness = false;
								}
							}else{
								isBussiness = false;
							}
							if(isBussiness){
								value = View.getLookupField(" select replyMemo from Cos0011Db where applNo = " + Common.sqlChar(cos0001.getApplNo()) + " order by id desc ");
								if("".equals(value)){
									value = "尚未回覆";
								}
							}else{
								value = "不需廠商回覆";
							}
						}else{
							value = View.getLookupField(" select replyMemo from Cos0014Db where applNo = " + Common.sqlChar(cos0001.getApplNo()) + " order by id desc ");
							if("".equals(value)){
								value = "尚未回覆";
							}
						}
					}
					
					
				}
			}
			
			row.add(value==null?"":value);
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

	
}
