package com.kangdainfo.tcbw.view.conse;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.filestore.ReadWriteTextFile;
import com.kangdainfo.tcbw.model.bo.Con1009Db;
import com.kangdainfo.tcbw.model.bo.Con2002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.web.listener.MyServletContext;

/**
*<br>程式目的：醫事機構資料匯入作業
*<br>程式代號：CONSE0009_2f
*<br>程式日期：
*<br>程式作者：
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class CONSE0009_3F extends SuperBean{

//String id;
//String parentId;
//String parentIdName;
//String departmentCode;
//String department;
//String level;
//String zip;
//String address;
//String phone;
//String description;

//String q_parentId;
//String q_parentIdName;
//String q_departmentCode;
//String q_department;
//String q_level;
//String q_address;
//String q_phone;
	private String id;
	private String bnhi;
	private String medAgencyCode;
	private String medAgencyName;
	private String agencyAddress;
	private String areaTel;
	private String tel;
	private String engageKind;
    private String medAgencyKind;
    private String endDate;
	
	private String q_isQuery;
	private String q_id;
	private String q_bnhi;
	private String q_medAgencyCode;
	private String q_medAgencyName;	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getBnhi() {
		return checkGet(bnhi);
	}

	public void setBnhi(String bnhi) {
		this.bnhi = checkSet(bnhi);
	}

	public String getMedAgencyCode() {
		return checkGet(medAgencyCode);
	}

	public void setMedAgencyCode(String medAgencyCode) {
		this.medAgencyCode = checkSet(medAgencyCode);
	}

	public String getMedAgencyName() {
		return checkGet(medAgencyName);
	}

	public void setMedAgencyName(String medAgencyName) {
		this.medAgencyName = checkSet(medAgencyName);
	}

	public String getAgencyAddress() {
		return checkGet(agencyAddress);
	}

	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = checkSet(agencyAddress);
	}

	public String getAreaTel() {
		return checkGet(areaTel);
	}

	public void setAreaTel(String areaTel) {
		this.areaTel = checkSet(areaTel);
	}

	public String getTel() {
		return checkGet(tel);
	}

	public void setTel(String tel) {
		this.tel = checkSet(tel);
	}

	public String getEngageKind() {
		return checkGet(engageKind);
	}

	public void setEngageKind(String engageKind) {
		this.engageKind = checkSet(engageKind);
	}

	public String getMedAgencyKind() {
		return checkGet(medAgencyKind);
	}

	public void setMedAgencyKind(String medAgencyKind) {
		this.medAgencyKind = checkSet(medAgencyKind);
	}

	public String getEndDate() {
		return checkGet(endDate);
	}

	public void setEndDate(String endDate) {
		this.endDate = checkSet(endDate);
	}

	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}

	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String qId) {
		q_id = checkSet(qId);
	}
	
	public String getQ_bnhi() {
		return checkGet(q_bnhi);
	}

	public void setQ_bnhi(String qBnhi) {
		q_bnhi = checkSet(qBnhi);
	}

	public String getQ_medAgencyCode() {
		return checkGet(q_medAgencyCode);
	}

	public void setQ_medAgencyCode(String qMedAgencyCode) {
		q_medAgencyCode = checkSet(qMedAgencyCode);
	}

	public String getQ_medAgencyName() {
		return checkGet(q_medAgencyName);
	}

	public void setQ_medAgencyName(String qMedAgencyName) {
		q_medAgencyName = checkSet(qMedAgencyName);
	}
	
	

String importMsg;
String updateMsg;
String errMsg;
String srcFilePath;
String q_method;
String select;
String stateDownload;
String selectImport;


//public String getId(){ return checkGet(id); }
//public void setId(String s){ id=checkSet(s); }
//public String getParentId(){ return checkGet(parentId); }
//public void setParentId(String s){ parentId=checkSet(s); }
//public String getParentIdName(){ return checkGet(parentIdName); }
//public void setParentIdName(String s){ parentIdName=checkSet(s); }
//public String getDepartmentCode(){ return checkGet(departmentCode); }
//public void setDepartmentCode(String s){ departmentCode=checkSet(s); }
//public String getDepartment(){ return checkGet(department); }
//public void setDepartment(String s){ department=checkSet(s); }
//public String getLevel(){ return checkGet(level); }
//public void setLevel(String s){ level=checkSet(s); }
//public String getZip(){ return checkGet(zip); }
//public void setZip(String s){ zip=checkSet(s); }
//public String getAddress(){ return checkGet(address); }
//public void setAddress(String s){ address=checkSet(s); }
//public String getPhone(){ return checkGet(phone); }
//public void setPhone(String s){ phone=checkSet(s); }
//public String getDescription(){ return checkGet(description); }
//public void setDescription(String s){ description=checkSet(s); }

//public String getQ_parentId(){ return checkGet(q_parentId); }
//public void setQ_parentId(String s){ q_parentId=checkSet(s); }
//public String getQ_parentIdName(){ return checkGet(q_parentIdName); }
//public void setQ_parentIdName(String s){ q_parentIdName=checkSet(s); }
//public String getQ_departmentCode(){ return checkGet(q_departmentCode); }
//public void setQ_departmentCode(String s){ q_departmentCode=checkSet(s); }
//public String getQ_department(){ return checkGet(q_department); }
//public void setQ_department(String s){ q_department=checkSet(s); }
//public String getQ_level(){ return checkGet(q_level); }
//public void setQ_level(String s){ q_level=checkSet(s); }
//public String getQ_address(){ return checkGet(q_address); }
//public void setQ_address(String s){ q_address=checkSet(s); }
//public String getQ_phone(){ return checkGet(q_phone); }
//public void setQ_phone(String s){ q_phone=checkSet(s); }

public String getQ_method() {return checkGet(q_method);	}
public void setQ_method(String s) {this.q_method = checkSet(s);	}
public String getimportMsg() {	return Common.get(importMsg);	}
public void setimportMsg(String importMsg) {this.importMsg = Common.get(importMsg);	}
public String getupdateMsg(){ return Common.get(updateMsg);}
public void setupdateMsg(String updateMsg){this.updateMsg= Common.get(updateMsg);}
public String geterrMsg(){ return Common.get(errMsg);}
public void seterrMsg(String errMsg){this.errMsg= Common.get(errMsg);}
public String getSrcFilePath() {return checkGet(srcFilePath);}
public void setSrcFilePath(String s) {this.srcFilePath = checkSet(s);}
public String getselect() {return checkGet(select);	}
public void setselect(String s) {this.select = checkSet(s);	}
public String getstateDownload() {return checkGet(stateDownload);}
public void setstateDownload(String s) {this.select = checkSet(s);}
public String getSelectImport() {return checkGet(selectImport);}
public void setSelectImport(String s) {this.selectImport = checkSet(s);}
private String[] local;
private String[] input;

private ArrayList<String[]> localArrayList = new ArrayList<String[]>();
public ArrayList<String[]> getLocalArrayList() {return localArrayList;}
public void setLocalArrayList(ArrayList<String[]> localArrayList) { this.localArrayList = localArrayList;}

private ArrayList<String[]> inputArrayList = new ArrayList<String[]>();
public ArrayList<String[]> getInputArrayList() {return inputArrayList;}
public void setInputArrayList(ArrayList<String[]> inputArrayList) { this.inputArrayList = inputArrayList;}

public String[] getLocal() {return local;}
public void setLocal(String[] local) {this.local = local;}
public String[] getInput() {return input;}
public void setInput(String[] input) {this.input = input;}


@Override
public void doCreate() throws Exception {
	Con1009Db obj = new Con1009Db();
	obj.setBnhi(getBnhi());
	obj.setMedAgencyCode(getMedAgencyCode());
	obj.setMedAgencyName(getMedAgencyName());
	obj.setAgencyAddress(getAgencyAddress());
	obj.setAreaTel(getAreaTel());
	obj.setTel(getTel());
	obj.setEngageKind(getEngageKind());
	obj.setMedAgencyKind(getMedAgencyKind());
	obj.setEndDate(getEndDate());
    obj.setCreator(getEditID());
    obj.setCreateDate(getEditDate());
    obj.setCreateTime(getEditTime());
    obj.setModifier(getEditID());
    obj.setModifyDate(getEditDate());
    obj.setModifyTime(getEditTime());
	ServiceGetter.getInstance().getTcbwService().save(obj);
	setId(Common.get(obj.getId()));
}

@Override
public void doUpdate() throws Exception {	
	Con1009Db obj = (Con1009Db)View.getObject(" from Con1009Db where id = " + Common.getLong(getId()));
	if(obj != null){		
		obj.setBnhi(getBnhi());
		obj.setMedAgencyCode(getMedAgencyCode());
		obj.setMedAgencyName(getMedAgencyName());
		obj.setAgencyAddress(getAgencyAddress());
		obj.setAreaTel(getAreaTel());
		obj.setTel(getTel());
		obj.setEngageKind(getEngageKind());
		obj.setMedAgencyKind(getMedAgencyKind());
		obj.setEndDate(getEndDate());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().update(obj);
		setId(Common.get(obj.getId()));
	}
}

@Override
public void doDelete() throws Exception {
	Con1009Db obj = (Con1009Db)View.getObject(" from Con1009Db where id = " + Common.getLong(getId()));
	if(obj != null){
		ServiceGetter.getInstance().getTcbwService().delete(obj);
		setId("");
	}else{
		throw new Exception("查無資料，無法刪除，請重新操作 !");
	}
}
//@Override
//public void doCreate() throws Exception {
//	CommonDepartment obj = new CommonDepartment();
//	obj.setParentId(Common.getInteger(parentId)>0?Common.getInt(parentId):null);
//	obj.setDepartmentCode(departmentCode);
//	obj.setDepartment(department);
//	obj.setFullCode(departmentCode);
//	obj.setFullName(department);	
//	obj.setLevel(Common.getInt(level));
//	obj.setZip(zip);
//	obj.setAddress(address);
//	obj.setPhone(phone);
//	obj.setDescription(description);
//	obj.setEditId(getEditID());
//	obj.setEditDate(getEditDate());
//	obj.setEditTime(getEditTime());
//	ServiceGetter.getInstance().getCommonService().save(obj);
//	setId(obj.getId().toString());
//}
//
//
//@Override
//public void doUpdate() throws Exception {
//	CommonDepartment obj = (CommonDepartment) View.getObject(" from CommonDepartment where id=" + getId());
//	obj.setParentId(Common.getInteger(parentId)>0?Common.getInt(parentId):null);
//	obj.setDepartmentCode(departmentCode);
//	obj.setDepartment(department);
//	obj.setFullCode(departmentCode);
//	obj.setFullName(department);
//	obj.setLevel(Common.getInt(level));
//	obj.setZip(zip);
//	obj.setAddress(address);
//	obj.setPhone(phone);
//	obj.setDescription(description);
//	obj.setEditId(getEditID());
//	obj.setEditDate(getEditDate());
//	obj.setEditTime(getEditTime());
//	ServiceGetter.getInstance().getCommonService().save(obj);
//	setId(obj.getId().toString());
//}
//
//
//@Override
//public void doDelete() throws Exception {
//	ServiceGetter.getInstance().getCommonService().delete(CommonDepartment.class, Common.getInt(id));
//}


/**
 * <br>
 * <br>目的：依主鍵查詢單一資料
 * <br>參數：無
 * <br>傳回：傳回本物件
*/

@Override
public Object doQueryOne() throws Exception {
	CONSE0009_3F obj = this;
	Con1009Db c = (Con1009Db)View.getObject("from Con1009Db where id = " + Common.getLong(getId()));
	if(c != null){
		obj.setId(Common.get(c.getId()));
		obj.setBnhi(c.getBnhi());
		obj.setMedAgencyCode(c.getMedAgencyCode());
		obj.setMedAgencyName(c.getMedAgencyName());
		obj.setAgencyAddress(c.getAgencyAddress());
		obj.setAreaTel(c.getAreaTel());
		obj.setTel(c.getTel());
		obj.setEngageKind(c.getEngageKind());
		obj.setMedAgencyKind(c.getMedAgencyKind());
		obj.setEndDate(c.getEndDate());
		obj.setEditID(c.getModifier());
		obj.setEditDate(c.getModifyDate());		
	}
	return obj;
}


//public CONSE0009_3F doQueryOne() throws Exception {
//	CONSE0009_3F obj = this;
//	CommonDepartment c = (CommonDepartment)ServiceGetter.getInstance().getCommonService().load(CommonDepartment.class,Integer.parseInt(getId()));
//	if (c!=null) {
//        obj.setId(c.getId().toString());        
//        CommonDepartment parent = null;
//        if (c.getParentId()!=null && c.getParentId()>0) {
//        	parent = (CommonDepartment) View.getObject("from CommonDepartment where id=" + c.getParentId());
//        }
//    	if (parent!=null) {
//    		obj.setParentId(parent.getId().toString());
//    		obj.setParentIdName(parent.getFullName());
//    	} else {
//    		obj.setParentId("");
//    		obj.setParentIdName("");    		
//    	}
//        obj.setParentId(c.getParentId()!=null?c.getParentId().toString():"");
//        obj.setDepartmentCode(c.getDepartmentCode());
//        obj.setDepartment(c.getDepartment());
//        obj.setLevel(Common.get(c.getLevel()));
//        obj.setZip(c.getZip());
//        obj.setAddress(c.getAddress());
//        obj.setPhone(c.getPhone());
//        obj.setDescription(c.getDescription());
//        obj.setEditID(c.getEditId());
//        obj.setEditDate(c.getEditDate());
//        obj.setEditTime(c.getEditTime());
//	} else throw new Exception("查無該筆資料！");
//	return obj;
//}


/**
 * <br>
 * <br>目的：依查詢欄位查詢多筆資料
 * <br>參數：無
 * <br>傳回：傳回ArrayList
*/
@Override
public Object doQueryAll() throws Exception {
	if (!"".equals(getQ_isQuery())){
		setQ_id("");
	}
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	String hql = " from Con1009Db where 1 = 1 ";
	if(!"".equals(getQ_id())){
		hql += " and id = " + Common.getLong(getQ_id());
	}else{
		if(!"".equals(getQ_bnhi()))
			hql += " and bnhi = " + Common.sqlChar(getQ_bnhi());
		if(!"".equals(getQ_medAgencyCode()))
			hql += " and medAgencyCode = " + Common.sqlChar(getQ_medAgencyCode());
		if(!"".equals(getQ_medAgencyName()))
			hql += " and medAgencyName like " + Common.sqlChar("%" + getQ_medAgencyName() + "%");
	}
	
	this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
	if (getTotalRecord() > 0){
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
		if (objList != null && objList.size() > 0) {
			//使用map先將資料撈出來
			java.util.Map<String, String> bnhiMap = TCBWCommon.getCommonCodeMap("MEDDIV");
			java.util.Map<String, String> engkindMap = TCBWCommon.getCommonCodeMap("MEDENG");
			java.util.Map<String, String> medkindMap = TCBWCommon.getCommonCodeMap("MEDKIND");

			for(Object dtlObj : objList) {	
				Con1009Db dtl = (Con1009Db)dtlObj;
				String[] rowArray = new String[7];
				rowArray[0] = Common.get(dtl.getId());											
				rowArray[1] = Common.get(bnhiMap.get(dtl.getBnhi()));
				rowArray[2] = Common.get(dtl.getMedAgencyCode());
				rowArray[3] = Common.get(dtl.getMedAgencyName());
				rowArray[4] = Common.get(dtl.getAreaTel()) + Common.get(dtl.getTel());		
				rowArray[5] = Common.get(engkindMap.get(dtl.getEngageKind()));	
				rowArray[6] = Common.get(medkindMap.get(dtl.getMedAgencyKind()));		
				arrList.add(rowArray);
			}
			objList.clear();
		}
	
	}
	return arrList;
}


//
//	public java.util.ArrayList<String[]> doQueryAll() throws Exception {
//		
//		String hql = " from CommonDepartment where 1=1 ";
//		if (!"".equals(getQ_parentId())) {
//			hql += " and parentId=" + Common.getInt(getQ_parentId());
//		}
//		if (!"".equals(getQ_departmentCode()))
//			hql += " and departmentCode like " + Common.sqlChar(getQ_departmentCode()+"%");
//		if (!"".equals(getQ_department()))
//			hql += " and department like " + Common.sqlChar("%"+getQ_department()+"%");
//		if (!"".equals(getQ_level()))
//			hql += " and level=" + Common.getInt(getQ_level());
//		if (!"".equals(getQ_address()))
//			hql += " and address like " + Common.sqlChar("%"+getQ_address()+"%");
//		if (!"".equals(getQ_phone()))
//			hql += " and phone=" + Common.sqlChar("%"+getQ_phone()+"%");		
//		return doQueryAll(hql,false);
//		/**
//		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
//		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
//		if (getTotalRecord() > 0) {
//			if (getState().indexOf("query")<0) 
//				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
//			
//			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by fullCode", this.getRecordStart(), this.getPageSize());
//			if (objList != null && objList.size() > 0) {
//				java.util.Iterator it = objList.iterator();
//				while (it.hasNext()) {
//					CommonDepartment o = (CommonDepartment) it.next();
//					String rowArray[] = new String[7];
//					rowArray[0] = Common.get(o.getId());					
//					rowArray[1] = Common.get(o.getDepartmentCode());
//					rowArray[2] = Common.get(o.getDepartment());
//					rowArray[3] = Common.get(o.getLevel());
//					rowArray[4] = o.getParentId()!=null?View.getLookupField("select fullCode||'.'||fullName from CommonDepartment where id=" + o.getParentId()):"";					
//					rowArray[5] = Common.get(o.getZip())+Common.get(o.getAddress());
//					rowArray[6] = Common.get(o.getPhone());
//					arrList.add(rowArray);					
//				}
//			}
//		}
//		return arrList;
//		**/
//	}
//
//	public java.util.ArrayList<String[]> doQueryAll(String hql, boolean addBlank) throws Exception {
//		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
//		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
//		if (getTotalRecord() > 0) {
//			if (getState().indexOf("query")<0) 
//				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();			
//			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by fullCode", this.getRecordStart(), this.getPageSize());
//			if (objList != null && objList.size() > 0) {
//				java.util.Iterator it = objList.iterator();
//				while (it.hasNext()) {
//					CommonDepartment o = (CommonDepartment) it.next();
//					String rowArray[] = new String[7];
//					rowArray[0] = Common.get(o.getId());					
//					rowArray[1] = Common.get(o.getDepartmentCode());
//					rowArray[2] = Common.get(o.getDepartment());
//					rowArray[3] = Common.get(o.getLevel());
//					rowArray[4] = o.getParentId()!=null?View.getLookupField("select fullCode||'.'||fullName from CommonDepartment where id=" + o.getParentId()):"";					
//					rowArray[5] = Common.get(o.getZip())+Common.get(o.getAddress());
//					rowArray[6] = Common.get(o.getPhone());
//					arrList.add(rowArray);					
//				}
//			}
//		}
//		if (addBlank) {
//			String rowArray[] = new String[7];
//			for (int i=0; i<rowArray.length; i++) rowArray[i] = "";
//			arrList.add(rowArray);
//		}
//		return arrList;
//	}
	

	//資料匯入

	public void doImportProcess() {
		CommonUser commonUser = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if(commonUser == null){
			commonUser = new CommonUser();
			System.out.println("[TCBW]-[CONSE0009_2F]-[新增]-[無法辨別登入的使用者]");
		}

		try {
			StringBuilder importMsg = new StringBuilder();
			StringBuilder updateMsg = new StringBuilder();
			StringBuilder errMsg = new StringBuilder();
			if (!"".equals(getSrcFilePath())) {
				
				String[] arrFileName = getSrcFilePath().split(":;:");
				if (arrFileName.length>1) {
					
					java.io.File parentFile = new java.io.File(MyServletContext.getInstance().getServletContext().getInitParameter("filestoreLocation")+java.io.File.separator+arrFileName[0]);
					java.io.File srcFile = new java.io.File(parentFile,arrFileName[1]);
					File f = new File(srcFile.getPath());
					ReadWriteTextFile rtf = new ReadWriteTextFile();
					ArrayList<String> objList = rtf.getFileContents(f);
					java.util.List<Con1009Db> saveList = new java.util.ArrayList<Con1009Db>();
					int counter = 0;
					int updatecounter = 0;
					int checkcounter = 0;					
					String selectOne = "1";
					String selectTwo = "2";
					if(this.getselect().equals(selectTwo)){
						this.setstateDownload("doDownload");						
					}
					java.util.ArrayList<String[]> localArrList = new java.util.ArrayList<String[]>();
					java.util.ArrayList<String[]> inputArrList = new java.util.ArrayList<String[]>();
					for(int i = 1; i < objList.size(); i++){
						String[] rs = new String[] {objList.get(i)};
						
						String mustSplitString = Common.arrayToString(rs,",").replaceAll("\"", "");
						String[] AfterSplit = mustSplitString.split(",",-1);
						//必填欄位資料檢核(分局別、醫事機構代碼、醫事機構名稱)
						if (!"".equals(AfterSplit[0].toString()) &&	!"".equals(AfterSplit[1].toString()) &&	!"".equals(AfterSplit[2].toString())) {
							
							String[] oldData = new String[9];
							
							
							//匯入資料不存在於系統中，將資料連同異動人員與異動日期寫入系統
							Con1009Db old = (Con1009Db)View.getObject(" from Con1009Db where medAgencyCode = " + Common.sqlChar(AfterSplit[1].toString()));
							Con1009Db obj = (Con1009Db) View.getObject("from Con1009Db where medAgencyCode=" + Common.sqlChar(AfterSplit[1].toString()));
							if(old!=null) {
								oldData[0] = old.getBnhi();
								oldData[1] = old.getMedAgencyCode();
								oldData[2] = old.getMedAgencyName();
								oldData[3] = old.getAgencyAddress();
								oldData[4] = old.getAreaTel();
								oldData[5] = old.getTel();
								oldData[6] = old.getEngageKind();
								oldData[7] = old.getMedAgencyKind();
								oldData[8] = old.getEndDate();
							}
							
							//選擇"差異資料覆蓋匯入"							
							if(this.getselect().equals(selectOne)){
								String[] newData = new String[9];
								if(obj!=null){
									//資料連同異動人員與異動日期寫入系統
									obj.setBnhi(Common.get(AfterSplit[0]));		  //分局別		
									obj.setMedAgencyCode(Common.get(AfterSplit[1]));//醫事機構代碼
									obj.setMedAgencyName(Common.get(AfterSplit[2]));//醫事機構名稱
									obj.setAgencyAddress(Common.get(AfterSplit[3]));//機構地址
									obj.setAreaTel(Common.get(AfterSplit[4]));	  //電話區域號碼
									obj.setTel(Common.get(AfterSplit[5]));		  //電話號碼
									obj.setEngageKind(Common.get(AfterSplit[6]));	  //特約類別
									obj.setMedAgencyKind(Common.get(AfterSplit[8]) + Common.get(AfterSplit[7]));//醫事機構種類
									if(AfterSplit.length == 10){
										obj.setEndDate(Datetime.getRocDateFromYYYYMMDD((AfterSplit[9])));  //終止合約或歇業日期
									}
									
									
									obj.setModifier(Common.get(commonUser.getUserId()));
									obj.setModifyDate(Common.get(Datetime.getYYYMMDD()));
									obj.setModifyTime(Common.get(Datetime.getHHMMSS()));
									ServiceGetter.getInstance().getTcbwService().update(obj);
									updatecounter++;
								}
								newData[0] = obj.getBnhi();
								newData[1] = obj.getMedAgencyCode();
								newData[2] = obj.getMedAgencyName();
								newData[3] = obj.getAgencyAddress();
								newData[4] = obj.getAreaTel();
								newData[5] = obj.getTel();
								newData[6] = obj.getEngageKind();
								newData[7] = obj.getMedAgencyKind();
								newData[8] = obj.getEndDate();
								
								//con2002db，資料有異動將舊資料寫入歷程
								Con2002Db con2002db = new Con2002Db();
								
								Con1009Db con1009db = new Con1009Db();
								con1009db.setId(obj.getId());
								con2002db.setCon1009Db(con1009db.getId());//取消與con1009DB的關聯，直接到con1009DB抓ID
								
								con2002db.setBnhi(oldData[0]);
								con2002db.setMedAgencyCode(oldData[1]);
								con2002db.setMedAgencyName(oldData[2]);
								con2002db.setAgencyAddress(oldData[3]);
								con2002db.setAreaTel(oldData[4]);
								con2002db.setTel(oldData[5]);
								con2002db.setEngageKind(oldData[6]);
								con2002db.setMedAgencyKind(oldData[7]);
								con2002db.setEndDate(oldData[8]);
								con2002db.setCreator(obj.getCreator());
								con2002db.setCreateDate(obj.getCreateDate());
								con2002db.setCreateTime(obj.getCreateTime());
								con2002db.setModifier(obj.getModifier());
								con2002db.setModifyDate(obj.getModifyDate());
								con2002db.setModifyTime(obj.getModifyDate());
								
								if(!Arrays.equals(oldData,newData)) {
									ServiceGetter.getInstance().getTcbwService().save(con2002db);
								}
								
							}
							
							if(obj!=null) {		//資料庫已存在該筆資料時將其取出並與匯入的資料做比對
								String[] objArray = new String[10];
								objArray[0] = obj.getBnhi();
								objArray[1] = obj.getMedAgencyCode();
								objArray[2] = obj.getMedAgencyName();
								objArray[3] = obj.getAgencyAddress();
								objArray[4] = obj.getAreaTel();
								objArray[5] = obj.getTel();
								objArray[6] = obj.getEngageKind();
								if(!"".equals(obj.getMedAgencyKind())) {
									objArray[7] = obj.getMedAgencyKind().substring(1,3);
									objArray[8] = obj.getMedAgencyKind().substring(0,1);
								} else {
									objArray[7] = obj.getMedAgencyKind();
									objArray[8] = obj.getMedAgencyKind();
								}
								objArray[9] = obj.getEndDate();
								
	
								if(Arrays.equals(AfterSplit,objArray) == false) { //比對資料，不相同時塞成list
									String local[] = new String[objArray.length];
									String input[] = new String[AfterSplit.length];
									for(int o = 0; o < objArray.length; o++) {
										local[o] = objArray[o];
										input[o] = AfterSplit[o];
									}
									this.setLocal(local);
									this.setInput(input);
									localArrList.add(local);
									inputArrList.add(input);
								}
								this.setLocalArrayList(localArrList); //給前端-資料庫的差異資料
								this.setInputArrayList(inputArrList); //給前端-匯入的差異資料
							}
							if(obj==null){	
								
							    for (int w = 0; w < 1; w++){
							    	Con1009Db Con1009Db = new Con1009Db();
							    	if("".equals(AfterSplit[0].toString())){
										errMsg.append("[分局別]不允許空白").append("<br>");
										checkcounter++;
									} else if("".equals(AfterSplit[1].toString())){
										errMsg.append("[醫事機構代碼]不允許空白").append("<br>");
										checkcounter++;
									} else if("".equals(AfterSplit[2].toString())){
										errMsg.append("[醫事機構名稱]不允許空白").append("<br>");
										checkcounter++;
									} else if((!"".equals(AfterSplit[4]) && AfterSplit[4].length() > 3) || !AfterSplit[4].startsWith("0")) {
										System.out.println("電話區碼長度：" + AfterSplit[4]);
										errMsg.append(AfterSplit[1]+ "," + AfterSplit[2] + "," + "[電話區碼長度錯誤]或[電話區碼格式不正確]").append("<br>");
										checkcounter++;
									} else {

							    	Con1009Db.setBnhi(Common.get(AfterSplit[0]));		  //分局別		
									Con1009Db.setMedAgencyCode(Common.get(AfterSplit[1]));//醫事機構代碼
									Con1009Db.setMedAgencyName(Common.get(AfterSplit[2]));//醫事機構名稱
									Con1009Db.setAgencyAddress(Common.get(AfterSplit[3]));//機構地址
									Con1009Db.setAreaTel(Common.get(AfterSplit[4]));	  //電話區域號碼
									Con1009Db.setTel(Common.get(AfterSplit[5]));		  //電話號碼
									Con1009Db.setEngageKind(Common.get(AfterSplit[6]));	  //特約類別
									Con1009Db.setMedAgencyKind(Common.get(AfterSplit[8]) + Common.get(AfterSplit[7]));//醫事機構種類
									if(AfterSplit.length == 10){
										Con1009Db.setEndDate(Datetime.getRocDateFromYYYYMMDD((AfterSplit[9])));  //終止合約或歇業日期
									}

									Con1009Db.setModifier(Common.get(commonUser.getUserId()));
									Con1009Db.setModifyDate(Common.get(Datetime.getYYYMMDD()));
									Con1009Db.setModifyTime(Common.get(Datetime.getHHMMSS()));
									saveList.add(Con1009Db);
									counter++;
									System.out.println("匯入的資料： " + Con1009Db.toString());
							    }
							    
							}
							}
							
								
						}
					}
					
					if(counter >= 0){
						importMsg.append("新增筆數：共　").append(counter).append("　筆<br>");
						this.setimportMsg("<br><br>資料匯入成功，其結果如下：<br>" + importMsg.toString());
					}
					
					if(updatecounter != 0){
						updateMsg.append("更新筆數：共　").append(updatecounter).append("　筆<br>");
						this.setupdateMsg("<br><br>資料更新成功，其結果如下：<br>" + updateMsg.toString());
					}
					
					if(checkcounter != 0){
						this.seterrMsg("<br><br>部份資料欄空白，其結果如下：<br>" + errMsg.toString());
					}
					
					if(saveList.size() > 0){
						ServiceGetter.getInstance().getTcbwService().saveBatch(saveList);
						saveList.clear();
					}
				}
			}
			setState("doTransferSuccess");
		} catch (Exception e) {
			e.printStackTrace();
			setState("doTransferFail");
			this.seterrMsg("<br><br>資料匯入失敗，錯誤訊息如下：<br>" + " : " + e.getMessage());	
		}
	}
	
} 