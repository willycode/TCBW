package com.kangdainfo.common.view.sys.ap;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.EmpMaster;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;

/**
*<br>程式目的：教育訓練用一
*<br>程式代號：sysap701f
*<br>程式日期：0990203
*<br>程式作者：shark
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  SYSAP701F extends SuperBean{


String id;
String empName;
String empAddr;
String deptId;
String deptIdName;
String empDOB;
String empTitle;
String empTitleName;
String empTitleCodeId;

String q_empName;
String q_empAddr;
String q_deptId;
String q_deptIdName;

public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }
public String getEmpName(){ return checkGet(empName); }
public void setEmpName(String s){ empName=checkSet(s); }
public String getEmpAddr(){ return checkGet(empAddr); }
public void setEmpAddr(String s){ empAddr=checkSet(s); }
public String getDeptId(){ return checkGet(deptId); }
public void setDeptId(String s){ deptId=checkSet(s); }
public String getDeptIdName(){ return checkGet(deptIdName); }
public void setDeptIdName(String s){ deptIdName=checkSet(s); }
public String getEmpDOB(){ return checkGet(empDOB); }
public void setEmpDOB(String s){ empDOB=checkSet(s); }
public String getEmpTitle(){ return checkGet(empTitle); }
public void setEmpTitle(String s){ empTitle=checkSet(s); }
public String getEmpTitleName(){ return checkGet(empTitleName); }
public void setEmpTitleName(String s){ empTitleName=checkSet(s); }
public String getEmpTitleCodeId(){ return checkGet(empTitleCodeId); }
public void setEmpTitleCodeId(String s){ empTitleCodeId=checkSet(s); }

public String getQ_empName(){ return checkGet(q_empName); }
public void setQ_empName(String s){ q_empName=checkSet(s); }
public String getQ_empAddr(){ return checkGet(q_empAddr); }
public void setQ_empAddr(String s){ q_empAddr=checkSet(s); }
public String getQ_deptId(){ return checkGet(q_deptId); }
public void setQ_deptId(String s){ q_deptId=checkSet(s); }
public String getQ_deptIdName(){ return checkGet(q_deptIdName); }
public void setQ_deptIdName(String s){ q_deptIdName=checkSet(s); }


@Override
public void doCreate() throws Exception {
	EmpMaster obj = new EmpMaster();
	obj.setEmpName(empName);
	obj.setEmpAddr(empAddr);

	CommonDepartment objDept = (CommonDepartment) View.getObject("from CommonDepartment where id=" + deptId);
	obj.setCommonDepartment(objDept);
	
	obj.setEmpDob(empDOB);
	if (!"".equals(getEmpTitle())) {
          CommonCode objCode = new CommonCode();
          objCode.setId(Common.getInt(empTitle));
          obj.setCommonCode(objCode);
	}
	obj.setUserId(getEditID());
	obj.setModDate(getEditDate());
	obj.setModTime(getEditTime());
	ServiceGetter.getInstance().getCommonService().save(obj);
	setId(obj.getId().toString());
}


@Override
public void doUpdate() throws Exception {
	EmpMaster obj = (EmpMaster) View.getObject(" from EmpMaster where id=" + getId());
	obj.setEmpName(empName);
	obj.setEmpAddr(empAddr);
	obj.setEmpDob(empDOB);
	if (!"".equals(getEmpTitle())) {
          CommonCode objCode = new CommonCode();
          objCode.setId(Common.getInt(empTitle));
          obj.setCommonCode(objCode);
	} else {
          obj.setCommonCode(null);
	}
	obj.setUserId(getEditID());
	obj.setModDate(getEditDate());
	obj.setModTime(getEditTime());
        ServiceGetter.getInstance().getCommonService().update(obj);
}


@Override
public void doDelete() throws Exception {
        ServiceGetter.getInstance().getCommonService().delete(EmpMaster.class, Common.getLong(id));
}


/**
 * <br>
 * <br>目的：依主鍵查詢單一資料
 * <br>參數：無
 * <br>傳回：傳回本物件
*/

public SYSAP701F  doQueryOne() throws Exception {
	SYSAP701F obj = this;
	EmpMaster c = (EmpMaster)ServiceGetter.getInstance().getCommonService().load(EmpMaster.class,Common.getLong(getId()));
	if (c!=null) {
        obj.setId(c.getId().toString());
        obj.setEmpName(c.getEmpName());
        obj.setEmpAddr(c.getEmpAddr());
        
        obj.setDeptId(c.getCommonDepartment().getId().toString());
        obj.setDeptIdName(c.getCommonDepartment().getFullName());
        
        obj.setEmpDOB(c.getEmpDob());
		if (c.getCommonCode()!=null) {
	        obj.setEmpTitle(c.getCommonCode().getId().toString());
	        obj.setEmpTitleCodeId(c.getCommonCode().getCodeId());
	        obj.setEmpTitleName(c.getCommonCode().getCodeName());
		} else {
	        obj.setEmpTitle("");
	        obj.setEmpTitleCodeId("");
	        obj.setEmpTitleName("");
		}
        obj.setEditID(c.getUserId());
        obj.setEditDate(c.getModDate());
        obj.setEditTime(c.getModTime());
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
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

	String hql = " from EmpMaster where 1=1 ";
	if (!"".equals(getQ_empName()))
		hql += " and empName=" + Common.sqlChar(getQ_empName());
	if (!"".equals(getQ_empAddr()))
		hql += " and empAddr=" + Common.sqlChar(getQ_empAddr());
	if (!"".equals(getQ_deptId()))
		hql += " and commonDepartment.id=" + Common.getInt(getQ_deptId());
	this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
	if (getTotalRecord() > 0) {
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
		if (objList != null && objList.size() > 0) {
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) {
				EmpMaster o = (EmpMaster) it.next();
				String rowArray[] = new String[4];
				rowArray[0] = Common.get(o.getId());
				rowArray[1] = Common.get(o.getEmpName());
				rowArray[2] = Common.get(o.getEmpAddr());
				rowArray[3] = Common.get(o.getCommonDepartment().getFullName());
				arrList.add(rowArray);					
			}
		}
	}
	return arrList;
}

}


