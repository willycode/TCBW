package com.kangdainfo.common.view.sys.ex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.MasterTable;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;

/**
*<br>程式目的：教育訓練
*<br>程式代號：sysex991f
*<br>程式日期：1000525
*<br>程式作者：shark
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  SYSEX991F extends SuperBean{


String id;
String mdate;
String deptId;
String deptIdName;
String titleId;
String titleIdName;
String titleIdCodeId;

String q_mdate;
String q_deptId;
String q_deptIdName;
String q_titleId;
String q_titleIdName;
String q_titleIdCodeId;

public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }
public String getMdate(){ return checkGet(mdate); }
public void setMdate(String s){ mdate=checkSet(s); }
public String getDeptId(){ return checkGet(deptId); }
public void setDeptId(String s){ deptId=checkSet(s); }
public String getDeptIdName(){ return checkGet(deptIdName); }
public void setDeptIdName(String s){ deptIdName=checkSet(s); }
public String getTitleId(){ return checkGet(titleId); }
public void setTitleId(String s){ titleId=checkSet(s); }
public String getTitleIdName(){ return checkGet(titleIdName); }
public void setTitleIdName(String s){ titleIdName=checkSet(s); }
public String getTitleIdCodeId(){ return checkGet(titleIdCodeId); }
public void setTitleIdCodeId(String s){ titleIdCodeId=checkSet(s); }

public String getQ_mdate(){ return checkGet(q_mdate); }
public void setQ_mdate(String s){ q_mdate=checkSet(s); }
public String getQ_deptId(){ return checkGet(q_deptId); }
public void setQ_deptId(String s){ q_deptId=checkSet(s); }
public String getQ_deptIdName(){ return checkGet(q_deptIdName); }
public void setQ_deptIdName(String s){ q_deptIdName=checkSet(s); }
public String getQ_titleId(){ return checkGet(q_titleId); }
public void setQ_titleId(String s){ q_titleId=checkSet(s); }
public String getQ_titleIdName(){ return checkGet(q_titleIdName); }
public void setQ_titleIdName(String s){ q_titleIdName=checkSet(s); }
public String getQ_titleIdCodeId(){ return checkGet(q_titleIdCodeId); }
public void setQ_titleIdCodeId(String s){ q_titleIdCodeId=checkSet(s); }


@Override
public void doCreate() throws Exception {
	MasterTable obj = new MasterTable();	
	obj.setMdate(mdate);
		
	CommonDepartment objDept = new CommonDepartment();
	objDept.setId(Common.getInt(deptId));
	obj.setCommonDepartment(objDept);	
	
	if (!"".equals(getTitleId())) {
      CommonCode objCode = new CommonCode();
      objCode.setId(Common.getInt(titleId));
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
	MasterTable obj = (MasterTable) View.getObject(" from MasterTable where id=" + getId());
	obj.setMdate(mdate);

	CommonDepartment objDept = (CommonDepartment) View.getObject("from CommonDepartment where id=" + Common.getInt(deptId)); //new CommonDepartment();
	//objDept.setId(Common.getInt(deptId));
	obj.setCommonDepartment(objDept);

	if (!"".equals(getTitleId())) {
		CommonCode objCode = new CommonCode();
		objCode.setId(Common.getInt(titleId));
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
        ServiceGetter.getInstance().getCommonService().delete(MasterTable.class, Common.getLong(id));
}


/**
 * <br>
 * <br>目的：依主鍵查詢單一資料
 * <br>參數：無
 * <br>傳回：傳回本物件
*/

public SYSEX991F  doQueryOne() throws Exception {
	SYSEX991F obj = this;
	MasterTable c = (MasterTable)ServiceGetter.getInstance().getCommonService().load(MasterTable.class,Common.getLong(getId()));
	if (c!=null) {
        obj.setId(c.getId().toString());
        obj.setMdate(c.getMdate());
        obj.setDeptId(c.getCommonDepartment()!=null?c.getCommonDepartment().getId().toString():"");
        obj.setDeptIdName(c.getCommonDepartment()!=null?c.getCommonDepartment().getDepartment():"");
		if (c.getCommonCode()!=null) {
	        obj.setTitleId(c.getCommonCode().getId().toString());
	        obj.setTitleIdCodeId(c.getCommonCode().getCodeId());
	        obj.setTitleIdName(c.getCommonCode().getCodeName());
		} else {
	        obj.setTitleId("");
	        obj.setTitleIdCodeId("");
	        obj.setTitleIdName("");
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

	String hql = " from MasterTable where 1=1 ";
	if (!"".equals(getQ_mdate()))
		hql += " and mdate=" + Common.sqlChar(getQ_mdate());
	if (!"".equals(getQ_deptId()))
		hql += " and commonDepartment.id=" + Common.getInt(getQ_deptId());
	if (!"".equals(getQ_titleId()))
		hql += " and commonCode.codeId=" + Common.sqlChar(getQ_titleIdCodeId());
	this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
	if (getTotalRecord() > 0) {
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
		if (objList != null && objList.size() > 0) {
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) {
				MasterTable o = (MasterTable) it.next();
				String rowArray[] = new String[4];
				rowArray[0] = Common.get(o.getId());
				rowArray[1] = Common.get(o.getMdate());
				rowArray[2] = Common.get(o.getCommonDepartment().getDepartment());
				rowArray[3] = Common.get(o.getCommonCode()!=null?o.getCommonCode().getCodeName():"");
				arrList.add(rowArray);					
			}
		}
	}
	return arrList;
}

}


