package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class COSIN0101Q extends SuperBean {
	
	private String q_isQuery;
	private String q_id;				//ID
	private String q_applNoS;			//案件編號S
	private String q_applNoE;			//案件編號E
	private String q_occurDateS;		//發生日期S
	private String q_occurDateE;		//發生日期E
	private String q_notifierRevDateS;	//通報日期S
	private String q_notifierRevDateE;	//通報日期E
	private String q_notifierRepDateS;	//收案日期S
	private String q_notifierRepDateE;	//收案日期E
	private String q_notifierSource;  	//通報來源
	private String q_notifierType;		//通報單位
	private String q_notifierDept;		//通報者服務機構
	private String q_permitKey;			//許可證字
	private String q_permitNo;			//許可證號
	private String q_chProduct;			//化粧品品名
	private String[] q_cosType;			//不良事件類別
	private String q_manufactorName;	//製造廠/進品代理商
	private String q_status;			//案件狀態
	private String[] q_mainCode;		//不良品缺陷
	private String q_preResult;
	private String q_isTrans;  	        //是否為歷史移轉資料

	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}
	
	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String q_id) {
		this.q_id = checkSet(q_id);
	}

	public String getQ_applNoS() {
		return checkGet(q_applNoS);
	}

	public void setQ_applNoS(String q_applNoS) {
		this.q_applNoS = checkSet(q_applNoS);
	}
	
	public String getQ_applNoE() {
		return checkGet(q_applNoE);
	}

	public void setQ_applNoE(String q_applNoE) {
		this.q_applNoE = checkSet(q_applNoE);
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

	public String getQ_notifierRevDateS() {
		return checkGet(q_notifierRevDateS);
	}

	public void setQ_notifierRevDateS(String q_notifierRevDateS) {
		this.q_notifierRevDateS = checkSet(q_notifierRevDateS);
	}
	
	public String getQ_notifierRevDateE() {
		return checkGet(q_notifierRevDateE);
	}

	public void setQ_notifierRevDateE(String q_notifierRevDateE) {
		this.q_notifierRevDateE = checkSet(q_notifierRevDateE);
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

	public String getQ_notifierSource() {
		return checkGet(q_notifierSource);
	}

	public void setQ_notifierSource(String q_notifierSource) {
		this.q_notifierSource = checkSet(q_notifierSource);
	}

	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}

	public void setQ_permitKey(String q_permitKey) {
		this.q_permitKey = checkSet(q_permitKey);
	}
	
	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}

	public void setQ_permitNo(String q_permitNo) {
		this.q_permitNo = checkSet(q_permitNo);
	}

	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}

	public void setQ_chProduct(String q_chProduct) {
		this.q_chProduct = checkSet(q_chProduct);
	}

	public String getQ_manufactorName() {
		return checkGet(q_manufactorName);
	}

	public void setQ_manufactorName(String q_manufactorName) {
		this.q_manufactorName = checkSet(q_manufactorName);
	}

	public String getQ_status() {
		return checkGet(q_status);
	}

	public void setQ_status(String q_status) {
		this.q_status = checkSet(q_status);
	}
	
	public String getQ_notifierDept() {
		return checkGet(q_notifierDept);
	}

	public void setQ_notifierDept(String qNotifierDept) {
		q_notifierDept = checkSet(qNotifierDept);
	}

	public String[] getQ_cosType() {
		return q_cosType;
	}

	public void setQ_cosType(String[] qCosType) {
		q_cosType = qCosType;
	}

	public String[] getQ_mainCode() {
		return q_mainCode;
	}

	public void setQ_mainCode(String[] qMainCode) {
		q_mainCode = qMainCode;
	}
	
	public String getQ_notifierType() {
		return checkGet(q_notifierType);
	}

	public void setQ_notifierType(String qNotifierType) {
		q_notifierType = checkSet(qNotifierType);
	}
	
	public String getQ_preResult() {
		return checkGet(q_preResult);
	}

	public void setQ_preResult(String q_preResult) {
		this.q_preResult = checkSet(q_preResult);
	}
	
	public String getQ_isTrans() {
		return checkGet(q_isTrans);
	}

	public void setQ_isTrans(String q_isTrans) {
		this.q_isTrans = checkSet(q_isTrans);
	}

	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Cos0001Db where 1 = 1";

		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_applNoS())) 
				hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
			if(!"".equals(getQ_applNoE())) 
				hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
			
			if(!"".equals(getQ_occurDateS())) 
				hql += " and occurDate >= " + Common.sqlChar(getQ_occurDateS());
			if(!"".equals(getQ_occurDateE())) 
				hql += " and occurDate <= " + Common.sqlChar(getQ_occurDateE());
			
			if(!"".equals(getQ_notifierRevDateS())) 
				hql += " and notifierRevDate >= " + Common.sqlChar(getQ_notifierRevDateS());
			if(!"".equals(getQ_notifierRevDateE())) 
				hql += " and notifierRevDate <= " + Common.sqlChar(getQ_notifierRevDateE());
			
			if(!"".equals(getQ_notifierRepDateS())) 
				hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
			if(!"".equals(getQ_notifierRepDateE())) 
				hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
			
			if(!"".equals(getQ_notifierSource())) 
				hql += " and notifierSource = " + Common.sqlChar(getQ_notifierSource());
			
			if(!"".equals(getQ_notifierDept())) 
				hql += " and notifierDept like " + Common.sqlChar("%" + getQ_notifierDept() + "%");
			
			if(!"".equals(getQ_permitKey()))
				hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
			
			if(!"".equals(getQ_permitNo())) 
				hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
			
			if(!"".equals(getQ_chProduct())) 
				hql += " and (chProduct like " + Common.sqlChar("%" + getQ_chProduct() + "%") + " or enProduct like " + Common.sqlChar("%" + getQ_chProduct() + "%") + ")";
			
			if(!"".equals(getQ_manufactorName())) 
				hql += " and manufactorName like " + Common.sqlChar("%" + getQ_manufactorName() + "%");
			
			if(!"".equals(Common.get(getQ_cosType()))){
				if(getQ_cosType().length == 1){
					for(String a: getQ_cosType()){
						hql += " and cosType = " + Common.sqlChar(a);
					}
				}else{
					hql += " and cosType in ('1','2','3')";
				}
			}	
			
			if(!"".equals(Common.get(getQ_mainCode()))){
				String type = "";
				for(String a: getQ_mainCode()){
					type += "subCode like " + Common.sqlChar("%" + a + "%") + " or ";
				}
				if(!"".equals(type))
					hql += " and id in (select cos0001Db.id from Cos0003Db where 1 = 1 and " + type.substring(0, type.length()-3) + ")";
			}
			
			if(!"".equals(getQ_status()))
				hql += "and status = " + Common.sqlChar(getQ_status());
			if(null != getQ_notifierType() && !"".equals(getQ_notifierType())){
				hql += "and notifierType = " + Common.sqlChar(getQ_notifierType());
			}
			
			if(!"".equals(getQ_preResult())){
				hql += " and (applNo in (select distinct applNo from Cos0006Db where firstResult = '03') or applNo in (select distinct applNo from Cos0008Db where preResult = '02'))";
			}
			if(!"".equals(getQ_isTrans())){
				if("Y".equals(getQ_isTrans())) hql += " and trans='Y' ";
				else if ("N".equals(getQ_isTrans())) hql += " and ( trans is null or trans='' ) ";
			}
		}
		System.out.println("[TCBW]-[COSIN0101Q]-[化粧品-QUERYALL] : " + hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("CCS");
				java.util.Map<String, String> cosMap = TCBWCommon.getCommonCodeMap("CCT");
				java.util.Map<String, String> permitMap = TCBWCommon.getCommonCodeMap("CPT");
				java.util.Map<String, String> cacMap = TCBWCommon.getCommonCodeMap("CAC");
				java.util.Map<String, String> mainCodeMap = TCBWCommon.getCommonCodeMap("COSDPD");
				java.util.Map<String, String> subCodeMap = getCos101DbCode();
				
				for(Object dtlObj : objList) {				
					Cos0001Db dtl = (Cos0001Db)dtlObj;

					String[] rowArray = new String[11];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRepDate());
					rowArray[3] = Common.get(cosMap.get(Common.get(dtl.getCosType()))).equals("")?Common.get("不良品與不良反應"):cosMap.get(Common.get(dtl.getCosType()));
					rowArray[4] = "";
					if(!"".equals(Common.get(dtl.getPermitKey())) || !"".equals(Common.get(dtl.getPermitNo()))){
						rowArray[4] = (Common.get(permitMap.get(Common.get(dtl.getPermitKey()))).equals("")?Common.get(dtl.getPermitKey()):Common.get(permitMap.get(Common.get(dtl.getPermitKey())))) + 
									  " 字第 " + Common.get(dtl.getPermitNo()) + " 號";
					}
					rowArray[5] = Common.get(dtl.getChProduct());
					rowArray[6] = Common.get(dtl.getEnProduct());
					rowArray[7] = Common.get(dtl.getManufactorName());
					rowArray[8] = "";
					if(dtl.getCos0002Dbs() != null && dtl.getCos0002Dbs().size() > 0){
						for(Object o:dtl.getCos0002Dbs()){
							Cos0002Db a = (Cos0002Db)o;
							rowArray[8] = Common.get(cacMap.get(a.getAdverseCondition()));
						}
					}
					rowArray[9] = "";
					if(dtl.getCos0003Dbs() != null && dtl.getCos0003Dbs().size() > 0){
						String tempCode = "";
						for(Object o:dtl.getCos0003Dbs()){
							Cos0003Db a = (Cos0003Db)o;
							if(!"".equals(Common.get(a.getSubCode()))){
								String[] tempSubCode = a.getSubCode().split(",");
								tempCode += Common.get(mainCodeMap.get(a.getMainCode())) + ":";
								for(String s:tempSubCode){									
									tempCode += Common.get(subCodeMap.get(s)) + ",";
								}
								tempCode = tempCode.substring(0, tempCode.length()-1) + "、";
							}
						}
						if(tempCode.length()>0) tempCode = tempCode.substring(0, tempCode.length()-1);
						rowArray[9] = tempCode;
					}
					rowArray[10] = Common.get(statusMap.get(dtl.getStatus()));
					arrList.add(rowArray);
				}
				objList.clear();
			}		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {}

	@Override
	public void doUpdate() throws Exception {}

	@Override
	public void doDelete() throws Exception {}
	
	public String getCOS0003DbCheckBoxOption(String className, String checkBoxFieldName, String[] checkedOne) throws Exception{
		StringBuilder sb = new StringBuilder();		
		java.util.List<CommonCode> commonCodeList = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("COSDPD");
		if(commonCodeList!=null && commonCodeList.size()>0){
			java.util.Map<String, String> selectOneMap = new java.util.HashMap<String, String>();
			if(checkedOne!=null && checkedOne.length>0){
				for(String rid : checkedOne){
					selectOneMap.put(Common.get(rid), "Y");
				}
			}
			for(CommonCode dtl : commonCodeList){
				sb.append(Common.get(dtl.getCodeName())).append(" : ");
				java.util.List<Cos1001Db> cosList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where cdpCode = " + Common.sqlChar(dtl.getCodeId()) + " and isStop = 'N' order by dpdKind ");
				if(cosList!=null && cosList.size()>0){
					for(Cos1001Db cos : cosList){
						sb.append("<input class='" + className + "'").append(" type='checkbox' name='" + checkBoxFieldName + "' value='" + cos.getDpdKind() + "' ");
						sb.append( Common.get(selectOneMap.get(Common.get(cos.getDpdKind()))).equals("")?"":"checked" );
						sb.append(">");
						sb.append(Common.get(cos.getDpdKindName()));
					}
					cosList.clear();
				}
				sb.append("<br>");
			}
			selectOneMap.clear();
		}
		return sb.toString();
	}
	
	public java.util.Map<String,String> getCos101DbCode() throws Exception{
		java.util.Map<String, String> codeMap = new java.util.HashMap<String, String>();
		java.util.List<Cos1001Db> cosList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where isStop = 'N' order by dpdKind ");
		if(cosList!=null && cosList.size()>0){
			for(Cos1001Db cos : cosList){
				codeMap.put(cos.getDpdKind(), cos.getDpdKindName());
			}
			cosList.clear();
		}
		return codeMap;
		
	}
}
