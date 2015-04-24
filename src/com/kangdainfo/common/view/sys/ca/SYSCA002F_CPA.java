package com.kangdainfo.common.view.sys.ca;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.web.listener.MyServletContext;

/**
*<br>程式目的：機關代碼維護作業_CPA版本
*<br>程式代號：sysca002f_cpa
*<br>程式日期：0980811
*<br>程式作者：shark
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  SYSCA002F_CPA extends SuperBean{


String id;
String parentId;
String parentIdName;
String departmentCode;
String department;
String level;
String zip;
String address;
String phone;
String description;

String q_parentId;
String q_parentIdName;
String q_departmentCode;
String q_department;
String q_level;
String q_address;
String q_phone;

public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }
public String getParentId(){ return checkGet(parentId); }
public void setParentId(String s){ parentId=checkSet(s); }
public String getParentIdName(){ return checkGet(parentIdName); }
public void setParentIdName(String s){ parentIdName=checkSet(s); }
public String getDepartmentCode(){ return checkGet(departmentCode); }
public void setDepartmentCode(String s){ departmentCode=checkSet(s); }
public String getDepartment(){ return checkGet(department); }
public void setDepartment(String s){ department=checkSet(s); }
public String getLevel(){ return checkGet(level); }
public void setLevel(String s){ level=checkSet(s); }
public String getZip(){ return checkGet(zip); }
public void setZip(String s){ zip=checkSet(s); }
public String getAddress(){ return checkGet(address); }
public void setAddress(String s){ address=checkSet(s); }
public String getPhone(){ return checkGet(phone); }
public void setPhone(String s){ phone=checkSet(s); }
public String getDescription(){ return checkGet(description); }
public void setDescription(String s){ description=checkSet(s); }

public String getQ_parentId(){ return checkGet(q_parentId); }
public void setQ_parentId(String s){ q_parentId=checkSet(s); }
public String getQ_parentIdName(){ return checkGet(q_parentIdName); }
public void setQ_parentIdName(String s){ q_parentIdName=checkSet(s); }
public String getQ_departmentCode(){ return checkGet(q_departmentCode); }
public void setQ_departmentCode(String s){ q_departmentCode=checkSet(s); }
public String getQ_department(){ return checkGet(q_department); }
public void setQ_department(String s){ q_department=checkSet(s); }
public String getQ_level(){ return checkGet(q_level); }
public void setQ_level(String s){ q_level=checkSet(s); }
public String getQ_address(){ return checkGet(q_address); }
public void setQ_address(String s){ q_address=checkSet(s); }
public String getQ_phone(){ return checkGet(q_phone); }
public void setQ_phone(String s){ q_phone=checkSet(s); }


String js, srcFilePath, q_method;

public String getQ_method() {return checkGet(q_method);	}
public void setQ_method(String s) {this.q_method = checkSet(s);	}
public String getJs() {	return Common.get(js);	}
public void setJs(String js) {this.js = Common.get(js);	}	
public String getSrcFilePath() {return checkGet(srcFilePath);}
public void setSrcFilePath(String s) {this.srcFilePath = checkSet(s);}

@Override
public void doCreate() throws Exception {
	CommonDepartment obj = new CommonDepartment();
	obj.setParentId(Common.getInteger(parentId)>0?Common.getInt(parentId):null);
	obj.setDepartmentCode(departmentCode);
	obj.setDepartment(department);
	obj.setFullCode(departmentCode);
	obj.setFullName(department);	
	obj.setLevel(Common.getInt(level));
	obj.setZip(zip);
	obj.setAddress(address);
	obj.setPhone(phone);
	obj.setDescription(description);
	obj.setEditId(getEditID());
	obj.setEditDate(getEditDate());
	obj.setEditTime(getEditTime());
	ServiceGetter.getInstance().getCommonService().save(obj);
	setId(obj.getId().toString());
}


@Override
public void doUpdate() throws Exception {
	CommonDepartment obj = (CommonDepartment) View.getObject(" from CommonDepartment where id=" + getId());
	obj.setParentId(Common.getInteger(parentId)>0?Common.getInt(parentId):null);
	obj.setDepartmentCode(departmentCode);
	obj.setDepartment(department);
	obj.setFullCode(departmentCode);
	obj.setFullName(department);
	obj.setLevel(Common.getInt(level));
	obj.setZip(zip);
	obj.setAddress(address);
	obj.setPhone(phone);
	obj.setDescription(description);
	obj.setEditId(getEditID());
	obj.setEditDate(getEditDate());
	obj.setEditTime(getEditTime());
	ServiceGetter.getInstance().getCommonService().save(obj);
	setId(obj.getId().toString());
}


@Override
public void doDelete() throws Exception {
	ServiceGetter.getInstance().getCommonService().delete(CommonDepartment.class, Common.getInt(id));
}


/**
 * <br>
 * <br>目的：依主鍵查詢單一資料
 * <br>參數：無
 * <br>傳回：傳回本物件
*/

public SYSCA002F_CPA  doQueryOne() throws Exception {
	SYSCA002F_CPA obj = this;
	CommonDepartment c = (CommonDepartment)ServiceGetter.getInstance().getCommonService().load(CommonDepartment.class,Integer.parseInt(getId()));
	if (c!=null) {
        obj.setId(c.getId().toString());        
        CommonDepartment parent = null;
        if (c.getParentId()!=null && c.getParentId()>0) {
        	parent = (CommonDepartment) View.getObject("from CommonDepartment where id=" + c.getParentId());
        }
    	if (parent!=null) {
    		obj.setParentId(parent.getId().toString());
    		obj.setParentIdName(parent.getFullName());
    	} else {
    		obj.setParentId("");
    		obj.setParentIdName("");    		
    	}
        obj.setParentId(c.getParentId()!=null?c.getParentId().toString():"");
        obj.setDepartmentCode(c.getDepartmentCode());
        obj.setDepartment(c.getDepartment());
        obj.setLevel(Common.get(c.getLevel()));
        obj.setZip(c.getZip());
        obj.setAddress(c.getAddress());
        obj.setPhone(c.getPhone());
        obj.setDescription(c.getDescription());
        obj.setEditID(c.getEditId());
        obj.setEditDate(c.getEditDate());
        obj.setEditTime(c.getEditTime());
	} else throw new Exception("查無該筆資料！");
	return obj;
}


/**
 * <br>
 * <br>目的：依查詢欄位查詢多筆資料
 * <br>參數：無
 * <br>傳回：傳回ArrayList
*/

	public java.util.ArrayList<String[]> doQueryAll() throws Exception {
		
		String hql = " from CommonDepartment where 1=1 ";
		if (!"".equals(getQ_parentId())) {
			hql += " and parentId=" + Common.getInt(getQ_parentId());
		}
		if (!"".equals(getQ_departmentCode()))
			hql += " and departmentCode like " + Common.sqlChar(getQ_departmentCode()+"%");
		if (!"".equals(getQ_department()))
			hql += " and department like " + Common.sqlChar("%"+getQ_department()+"%");
		if (!"".equals(getQ_level()))
			hql += " and level=" + Common.getInt(getQ_level());
		if (!"".equals(getQ_address()))
			hql += " and address like " + Common.sqlChar("%"+getQ_address()+"%");
		if (!"".equals(getQ_phone()))
			hql += " and phone=" + Common.sqlChar("%"+getQ_phone()+"%");		
		return doQueryAll(hql,false);
		/**
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by fullCode", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Iterator it = objList.iterator();
				while (it.hasNext()) {
					CommonDepartment o = (CommonDepartment) it.next();
					String rowArray[] = new String[7];
					rowArray[0] = Common.get(o.getId());					
					rowArray[1] = Common.get(o.getDepartmentCode());
					rowArray[2] = Common.get(o.getDepartment());
					rowArray[3] = Common.get(o.getLevel());
					rowArray[4] = o.getParentId()!=null?View.getLookupField("select fullCode||'.'||fullName from CommonDepartment where id=" + o.getParentId()):"";					
					rowArray[5] = Common.get(o.getZip())+Common.get(o.getAddress());
					rowArray[6] = Common.get(o.getPhone());
					arrList.add(rowArray);					
				}
			}
		}
		return arrList;
		**/
	}

	public java.util.ArrayList<String[]> doQueryAll(String hql, boolean addBlank) throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by fullCode", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Iterator it = objList.iterator();
				while (it.hasNext()) {
					CommonDepartment o = (CommonDepartment) it.next();
					String rowArray[] = new String[7];
					rowArray[0] = Common.get(o.getId());					
					rowArray[1] = Common.get(o.getDepartmentCode());
					rowArray[2] = Common.get(o.getDepartment());
					rowArray[3] = Common.get(o.getLevel());
					rowArray[4] = o.getParentId()!=null?View.getLookupField("select fullCode||'.'||fullName from CommonDepartment where id=" + o.getParentId()):"";					
					rowArray[5] = Common.get(o.getZip())+Common.get(o.getAddress());
					rowArray[6] = Common.get(o.getPhone());
					arrList.add(rowArray);					
				}
			}
		}
		if (addBlank) {
			String rowArray[] = new String[7];
			for (int i=0; i<rowArray.length; i++) rowArray[i] = "";
			arrList.add(rowArray);
		}
		return arrList;
	}
	

	public void doImportProcess() {
		try {
			StringBuilder msg = new StringBuilder();
			if (!"".equals(getSrcFilePath())) {
				System.out.println("GGGXX");
				String[] arrFileName=getSrcFilePath().split(":;:");
				if (arrFileName.length>1) {
					java.io.File parentFile = new java.io.File(MyServletContext.getInstance().getServletContext().getInitParameter("filestoreLocation")+java.io.File.separator+arrFileName[0]);
					java.io.File srcFile = new java.io.File(parentFile,arrFileName[1]);					
					int counter = ServiceGetter.getInstance().getCommonService().getCommonDepartmentDao().importCommonDepartmentFromCPA(srcFile);					
					msg.append("更新筆數：共　").append(counter).append("　筆<br>");;
					Common.RemoveDirectory(parentFile);
				}
			}
			setState("doTransferSuccess");			
			this.setJs("<br><br>資料匯入成功，其結果如下：<br>" + msg.toString());					
		} catch (Exception e) {
			e.printStackTrace();
			setState("doTransferFail");	
			this.setJs("<br><br>資料匯入失則，錯誤訊息如下：<br>" + " : " + e.getMessage());	
		}
	}	
	
}


