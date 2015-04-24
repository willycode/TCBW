package com.kangdainfo.tcbw.view.medbak;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med5001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class MEDIN0501Q extends SuperBean{
	
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
	private String q_通報來源;  			//通報來源
	private String q_通報單位;				//通報單位
	private String q_caseSource;		//案例來源
	private String q_核準文號;				//核準文號
	private String q_列管單位;				//列管單位
	private String q_permitNumber;		//許可證字號  MED5004Db
	private String q_medTestMedical;	//醫材品名
	private String q_medFactory;		//製造廠
	private String q_醫材主類別;			//醫材主類別
	private String q_醫材次類別;			//醫材次類別
	private String q_badReactionResults;//不良反應結果
	private String q_status;			//案件狀態

	
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

	public String getQ_通報來源() {
		return checkGet(q_通報來源);
	}

	public void setQ_通報來源(String q_通報來源) {
		this.q_通報來源 = checkSet(q_通報來源);
	}

	public String getQ_通報單位() {
		return checkGet(q_通報單位);
	}

	public void setQ_通報單位(String q_通報單位) {
		this.q_通報單位 = checkSet(q_通報單位);
	}

	public String getQ_caseSource() {
		return checkGet(q_caseSource);
	}

	public void setQ_caseSource(String q_caseSource) {
		this.q_caseSource = checkSet(q_caseSource);
	}

	public String getQ_核準文號() {
		return checkGet(q_核準文號);
	}

	public void setQ_核準文號(String q_核準文號) {
		this.q_核準文號 = checkSet(q_核準文號);
	}
	
	public String getQ_列管單位() {
		return checkGet(q_列管單位);
	}

	public void setQ_列管單位(String q_列管單位) {
		this.q_列管單位 = checkSet(q_列管單位);
	}

	public String getQ_permitNumber() {
		return checkGet(q_permitNumber);
	}

	public void setQ_permitNumber(String q_permitNumber) {
		this.q_permitNumber = checkSet(q_permitNumber);
	}

	public String getQ_medTestMedical() {
		return checkGet(q_medTestMedical);
	}

	public void setQ_medTestMedical(String q_medTestMedical) {
		this.q_medTestMedical = checkSet(q_medTestMedical);
	}
	
	public String getQ_medFactory() {
		return checkGet(q_medFactory);
	}

	public void setQ_medFactory(String q_medFactory) {
		this.q_medFactory = checkSet(q_medFactory);
	}

	public String getQ_醫材主類別() {
		return checkGet(q_醫材主類別);
	}

	public void setQ_醫材主類別(String q_醫材主類別) {
		this.q_醫材主類別 = checkSet(q_醫材主類別);
	}

	public String getQ_醫材次類別() {
		return checkGet(q_醫材次類別);
	}

	public void setQ_醫材次類別(String q_醫材次類別) {
		this.q_醫材次類別 = checkSet(q_醫材次類別);
	}

	public String getQ_badReactionResults() {
		return checkGet(q_badReactionResults);
	}

	public void setQ_badReactionResults(String q_badReactionResults) {
		this.q_badReactionResults = checkSet(q_badReactionResults);
	}

	public String getQ_status() {
		return checkGet(q_status);
	}

	public void setQ_status(String q_status) {
		this.q_status = checkSet(q_status);
	}

	@Override
	public Object doQueryOne() throws Exception {
		MEDIN0501Q obj = this;
//		Drg0001Db c = (Drg0001Db)View.getObject("from Drg0001Db where id = " + Common.getLong(getId()));
//		Drg0004Db drg0004db = (Drg0004Db)View.getObject("from Drg0004Db where applNo = " + Common.sqlChar(getApplNo()));	//取得同案件編號的風險等級
//		Drg0006Db drg0006db = (Drg0006Db)View.getObject("from Drg0006Db where applNo = " + Common.sqlChar(getApplNo()));	//取得同案件編號的不良品缺陷
//
//		if(c != null){
//			obj.setId(Common.get(c.getId()));
//			obj.setApplNo(c.getApplNo());
//			obj.setOccurDate(c.getOccurDate());
//			obj.setNotifierRevDate(c.getNotifierRevDate());
//			obj.setNotifierRepDate(c.getNotifierRepDate());
//			obj.setNotifierSource(c.getNotifierSource());
//			obj.setNotifierDept(c.getNotifierDept());
//			obj.setPermitNo(c.getPermitNo());
//			obj.setChProduct(c.getChProduct());
//			obj.setIngredient(c.getIngredient());
//			obj.setApplicationName(c.getApplicationName());
//			obj.setStatus(c.getStatus());
//			obj.setManufactorName(c.getManufactorName());
//			obj.setDrgLev(Common.get(drg0004db.getDrgLev()));
//			obj.setDefect(Common.get(drg0006db.getDefect()));
//			
//			obj.setEditID(c.getModifier());
//			obj.setEditDate(c.getModifyDate());
//		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		
		//Drg0004Db f= (Drg0004Db) o.Drg0004Dbs().iterator().next();
		
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Med5001Db as a where 1 = 1 ";

		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_applNoS())) 
				hql += "and applNo >= " +  Common.sqlChar(getQ_applNoS()) + "and applNo <= " + Common.sqlChar(getQ_applNoE());
			
			if(!"".equals(getQ_occurDateS())) 
				hql += "and occurDate >= " + Common.sqlChar(getQ_occurDateS()) + "and occurDate <= " + Common.sqlChar(getQ_occurDateE());
			
			if(!"".equals(getQ_notifierRevDateS())) 
				hql += "and notifierRevDate >= " + Common.sqlChar(getQ_notifierRevDateS()) + "and notifierRevDate <= " + Common.sqlChar(getQ_notifierRevDateE());
			
			if(!"".equals(getQ_notifierRepDateS())) 
				hql += "and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS()) + "and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
			
//			if(!"".equals(getQ_通報來源())) 
//				hql += "and 通報來源 = " + Common.sqlChar(getQ_通報來源());
//			
//			if(!"".equals(getQ_通報單位())) 
//				hql += "and 通報單位 like " + Common.sqlChar("%" + getQ_通報單位() + "%");
			
			if(!"".equals(getQ_caseSource())) 
				hql += "and caseSource  = " + Common.sqlChar(getQ_caseSource());
			
//			if(!"".equals(getQ_核準文號())) 
//				hql += "and 核準文號 = " + Common.sqlChar(getQ_核準文號());
			
//			if(!"".equals(getQ_列管單位())) 
//				hql += "and 列管單位 like " + Common.sqlChar("%" + getQ_列管單位() + "%");
			
//			if(!"".equals(getQ_permitNumber())) 
//				hql += "and a.applNo in (select permitNumber from Med5004Db as b where b.permitNumber = " + Common.sqlChar(getQ_permitNumber()) + ")";
			
			if(!"".equals(getQ_medTestMedical())) 
				hql += "and medTestMedical like " + Common.sqlChar("%" + getQ_medTestMedical() + "%");
			
			if(!"".equals(getQ_medFactory())) 
				hql += "and medFactory like" + Common.sqlChar("%" + getQ_medFactory() + "%");
			
//			if(!"".equals(getQ_醫材主類別()))
//				hql += "and 醫材主類別 = " + Common.sqlChar(getQ_醫材主類別());
//			
//			if(!"".equals(getQ_醫材次類別()))
//				hql += "and 醫材次類別 = " + Common.sqlChar(getQ_醫材次類別());
			
			if(!"".equals(getQ_badReactionResults()))
				hql += "and badReactionResults = " + Common.sqlChar(getQ_badReactionResults());
			
//			if(!"".equals(getQ_NCAR通報狀況()))
//				hql += "and NCAR通報狀況 = " + Common.sqlChar(getQ_NCAR通報狀況());
//			
//			if(!"".equals(getQ_不良品事件等級()))
//				hql += "and 不良品事件等級 = " + Common.sqlChar(getQ_不良品事件等級());
			
			if(!"".equals(getQ_status()))
				hql += "and status = " + Common.sqlChar(getQ_status());
		}
		System.out.println("HQL: " + hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				//java.util.Map<String, String> sysKindMap = TCBWCommon.getCommonCodeMap("SYS");
				
				for(Object dtlObj : objList) {				
					Med5001Db dtl = (Med5001Db)dtlObj;

					String[] rowArray = new String[11];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRevDate());
					rowArray[3] = Common.get(dtl.getApplNo());				//核準文號
					rowArray[4] = Common.get(dtl.getApplNo());				//列管單位
					rowArray[5] = Common.get(dtl.getApplNo());				//許可證字號
					rowArray[6] = Common.get(dtl.getMedTestMedical());
					rowArray[7] = Common.get(dtl.getMedFactory());
					rowArray[8] = Common.get(dtl.getMedMainCategory());
					rowArray[9] = Common.get(dtl.getBadReactionResults());
					rowArray[10] = Common.get(dtl.getStatus());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}
	
//	protected String[][] getInsertCheckSQL(){	
//		String[][] checkSQLArray = new String[1][4];
//	 	checkSQLArray[0][0] = "select count(*) from Con1015Db where code = " + Common.sqlChar(getCode());
//		checkSQLArray[0][1] = ">";
//		checkSQLArray[0][2] = "0";
//		checkSQLArray[0][3] = "該流程角色代碼已存在，請重新輸入！";
//		return checkSQLArray;
//	}

	@Override
	public void doCreate() throws Exception {
//		Con1015Db obj = new Con1015Db();	    
//	    
//	    Con1014Db con1014db = (Con1014Db)View.getObject(" from Con1014Db where id = " + Common.getLong(getId()));
//	    obj.setCon1014Db(con1014db);			
//	    
//	    CommonUser user = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getCommonUser()));
//	    obj.setCommonUser(user);
//  
//		if(getCompetence()!=null && getCompetence().length>0){
//			StringBuffer s = new StringBuffer();
//			for(String idx : getCompetence()){
//				if(s.toString().length() > 0)	s.append(",");
//				s.append(idx);
//			}
//			if(s.toString().length() > 0){
//				obj.setCompetence(s.toString());
//			}
//		}
//
//	    obj.setCreator(getEditID());
//	    obj.setCreateDate(getEditDate());
//	    obj.setCreateTime(getEditTime());
//	    obj.setModifier(getEditID());
//	    obj.setModifyDate(getEditDate());
//	    obj.setModifyTime(getEditTime());
//		ServiceGetter.getInstance().getTcbwService().save(obj);
//		setId2(Common.get(obj.getId()));
		
	}
	
//	protected String[][] getUpdateCheckSQL(){	
//		String[][] checkSQLArray = new String[1][4];	 	
//		checkSQLArray[0][0] = "select count(*) from Con1014Db where code = " + Common.sqlChar(getCode()) + " and id != " + Common.getLong(getId()); 	
//	 	checkSQLArray[0][1] = ">";
//		checkSQLArray[0][2] = "0";
//		checkSQLArray[0][3] = "該流程角色代碼已存在，請重新輸入！";	
//		return checkSQLArray;		
//	}

	@Override
	public void doUpdate() throws Exception {
//		Con1015Db obj = (Con1015Db)View.getObject(" from Con1015Db where id = " + Common.getLong(getId2()));
//		if(obj != null){		
//			Con1007Db con1007db = new Con1007Db();
//		    con1007db.setId(Common.getLong(getCon1007_id()));
//		    
//		    Con1014Db con1014db = (Con1014Db)View.getObject(" from Con1014Db where id = " + Common.getLong(getId()));
//		    obj.setCon1014Db(con1014db);		
//
//		    CommonUser user = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getCommonUser()));
//		    obj.setCommonUser(user);
//
//			if(getCompetence()!=null && getCompetence().length>0){
//				StringBuffer s = new StringBuffer();
//				for(String idx : getCompetence()){
//					if(s.toString().length() > 0)	s.append(",");
//					s.append(idx);
//				}
//				if(s.toString().length() > 0){
//					obj.setCompetence(s.toString());
//				}
//			}
//
//		    obj.setModifier(getEditID());
//		    obj.setModifyDate(getEditDate());
//		    obj.setModifyTime(getEditTime());
//			ServiceGetter.getInstance().getTcbwService().update(obj);
//			setId(Common.get(obj.getId()));
//		}
	}

	@Override
	public void doDelete() throws Exception {
//		Con1015Db obj = (Con1015Db)View.getObject(" from Con1015Db where id = " + Common.getLong(getId2()));
//		if(obj != null){
//			ServiceGetter.getInstance().getTcbwService().delete(obj);
//			setId2("");
//		}else{
//			throw new Exception("查無資料，無法刪除，請重新操作 !");
//		}
	}

}
