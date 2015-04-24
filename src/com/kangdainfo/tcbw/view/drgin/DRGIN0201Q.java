package com.kangdainfo.tcbw.view.drgin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class DRGIN0201Q extends SuperBean{
	
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
	private String q_通報單位;				//通報單位
	private String q_permitKey1;		//許可證字
	private String q_permitNo1;			//許可證號
	private String q_productName1;		//藥品品名
	private String q_scientificName1;	//學名
	private String q_applicationName1;	//申請商
	private String q_manufactorName1;	//製造廠
	private String q_通報事件後果;			//通報事件後果(schema未定)
	private String q_NTIDrugs;			//NTI Drugs(schema未定)
	private String q_療效不等評估結果;		//療效不等評估結果(schema未定)
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

	public String getQ_notifierSource() {
		return checkGet(q_notifierSource);
	}

	public void setQ_notifierSource(String q_notifierSource) {
		this.q_notifierSource = checkSet(q_notifierSource);
	}

	public String getQ_通報單位() {
		return checkGet(q_通報單位);
	}

	public void setQ_通報單位(String q_通報單位) {
		this.q_通報單位 = checkSet(q_通報單位);
	}

	public String getQ_permitKey1() {
		return checkGet(q_permitKey1);
	}

	public void setQ_permitKey1(String q_permitKey1) {
		this.q_permitKey1 = checkSet(q_permitKey1);
	}
	
	public String getQ_permitNo1() {
		return checkGet(q_permitNo1);
	}

	public void setQ_permitNo1(String q_permitNo1) {
		this.q_permitNo1 = checkSet(q_permitNo1);
	}

	public String getQ_productName1() {
		return checkGet(q_productName1);
	}

	public void setQ_productName1(String q_productName1) {
		this.q_productName1 = checkSet(q_productName1);
	}

	public String getQ_scientificName1() {
		return checkGet(q_scientificName1);
	}

	public void setQ_scientificName1(String q_scientificName1) {
		this.q_scientificName1 = checkSet(q_scientificName1);
	}
	
	
	public String getQ_applicationName1() {
		return checkGet(q_applicationName1);
	}

	public void setQ_applicationName1(String q_applicationName1) {
		this.q_applicationName1 = checkSet(q_applicationName1);
	}

	public String getQ_manufactorName1() {
		return checkGet(q_manufactorName1);
	}

	public void setQ_manufactorName1(String q_manufactorName1) {
		this.q_manufactorName1 = checkSet(q_manufactorName1);
	}

	public String getQ_status() {
		return checkGet(q_status);
	}

	public void setQ_status(String q_status) {
		this.q_status = checkSet(q_status);
	}

	public String getQ_通報事件後果() {
		return checkGet(q_通報事件後果);
	}

	public void setQ_通報事件後果(String q_通報事件後果) {
		this.q_通報事件後果 = checkSet(q_通報事件後果);
	}

	public String getQ_NTIDrugs() {
		return checkGet(q_NTIDrugs);
	}

	public void setQ_NTIDrugs(String q_NTIDrugs) {
		this.q_NTIDrugs = checkSet(q_NTIDrugs);
	}
	
	public String getQ_療效不等評估結果() {
		return checkGet(q_療效不等評估結果);
	}

	public void setQ_療效不等評估結果(String q_療效不等評估結果) {
		this.q_療效不等評估結果 = checkSet(q_療效不等評估結果);
	}
	

	@Override
	public Object doQueryOne() throws Exception {
		DRGIN0201Q obj = this;
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
		String hql = " from Drg4001Db as a where 1 = 1 ";

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
			
			if(!"".equals(getQ_notifierSource())) 
				hql += "and notifierSource = " + Common.sqlChar(getQ_notifierSource());
			
			if(!"".equals(getQ_通報單位())) 
				hql += "and 通報單位 = " + Common.sqlChar(getQ_通報單位());
			
			if(!"".equals(getQ_permitKey1())) 
				hql += "and permitKey1 = " + Common.sqlChar(getQ_permitKey1());
			
			if(!"".equals(getQ_permitNo1())) 
				hql += "and permitNo1 = " + Common.sqlChar(getQ_permitNo1());
			
			if(!"".equals(getQ_productName1())) 
				hql += "and productName1 like " + Common.sqlChar("%" + getQ_productName1() + "%");
			
			if(!"".equals(getQ_scientificName1())) 
				hql += "and scientificName1 like " + Common.sqlChar("%" + getQ_scientificName1() + "%");
			
			if(!"".equals(getQ_applicationName1())) 
				hql += "and applicationName1 like " + Common.sqlChar("%" + getQ_applicationName1() + "%");
			
			if(!"".equals(getQ_manufactorName1())) 
				hql += "and manufactorName1 like " + Common.sqlChar("%" + getQ_manufactorName1() + "%");
			
			if(!"".equals(getQ_status())) 
				hql += "and status = " + Common.sqlChar(getQ_status());
			

//			if(!"".equals(getQ_通報事件後果())) 
//				hql += "and a.applNo in (select drgLev from Drg0004Db as b where b.drgLev = " +  Common.sqlChar(getQ_通報事件後果()) + ")";
//			if(!"".equals(getQ_NTIDrugs())) 
//				hql += "and a.applNo in (select defect from Drg0006Db as c where c.defect = " +  Common.sqlChar(getQ_NTIDrugs()) + ")";
//			if(!"".equals(getQ_療效不等評估結果())) 
//				hql += "and a.applNo in (select defect from Drg0006Db as c where c.defect = " +  Common.sqlChar(getQ_療效不等評估結果()) + ")";
			
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
					Drg4001Db dtl = (Drg4001Db)dtlObj;

					String[] rowArray = new String[11];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRevDate());
//					rowArray[3] = Common.get(dtl.getPermitNo1());
//					rowArray[4] = Common.get(dtl.getScientificName1());
//					rowArray[5] = Common.get(dtl.getProductName1());
//					rowArray[6] = Common.get(dtl.getApplicationName1());
//					rowArray[7] = Common.get(dtl.getManufactorName1());
//					rowArray[8] = Common.get(dtl.getManufactorNo1());
					rowArray[9] = Common.get(dtl.getEffectChange());	//通報事件後果
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
