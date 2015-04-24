package com.kangdainfo.common.view.sys.ap;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.EmpDetail;
import com.kangdainfo.common.model.bo.EmpMaster;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;

/**
*<br>程式目的：教育訓練用二
*<br>程式代號：sysap701f_dtl
*<br>程式日期：0990203
*<br>程式作者：shark
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  SYSAP701F_DTL extends SYSAP701F {


String id;
String dtlId;
String relSSN;
String relName;
String relNameName;
String relRelative;
String relRelativeName;
String relRelativeCodeId;


public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }
public String getDtlId(){ return checkGet(dtlId); }
public void setDtlId(String s){ dtlId=checkSet(s); }
public String getRelSSN(){ return checkGet(relSSN); }
public void setRelSSN(String s){ relSSN=checkSet(s); }
public String getRelName(){ return checkGet(relName); }
public void setRelName(String s){ relName=checkSet(s); }
public String getRelNameName(){ return checkGet(relNameName); }
public void setRelNameName(String s){ relNameName=checkSet(s); }
public String getRelRelative(){ return checkGet(relRelative); }
public void setRelRelative(String s){ relRelative=checkSet(s); }
public String getRelRelativeName(){ return checkGet(relRelativeName); }
public void setRelRelativeName(String s){ relRelativeName=checkSet(s); }
public String getRelRelativeCodeId(){ return checkGet(relRelativeCodeId); }
public void setRelRelativeCodeId(String s){ relRelativeCodeId=checkSet(s); }



@Override
public void doCreate() throws Exception {	
	EmpDetail obj = new EmpDetail();
	
	EmpMaster master = new EmpMaster();
	master.setId(Common.getLong(getId()));
	obj.setEmpMaster(master);
	
	obj.setRelSsn(relSSN);
	obj.setRelName(relName);
	if (!"".equals(getRelRelative())) {
          CommonCode objCode = new CommonCode();
          objCode.setId(Common.getInt(relRelative));
          obj.setCommonCode(objCode);
	}
	obj.setUserId(getEditID());
	obj.setModDate(getEditDate());
	obj.setModTime(getEditTime());
	ServiceGetter.getInstance().getCommonService().save(obj);
	setDtlId(obj.getId().toString());
}


@Override
public void doUpdate() throws Exception {
	EmpDetail obj = (EmpDetail) View.getObject(" from EmpDetail where id=" + getDtlId());
	obj.setRelName(relName);
	if (!"".equals(getRelRelative())) {
          CommonCode objCode = new CommonCode();
          objCode.setId(Common.getInt(relRelative));
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
        ServiceGetter.getInstance().getCommonService().delete(EmpDetail.class, Common.getLong(id));
}


/**
 * <br>
 * <br>目的：依主鍵查詢單一資料
 * <br>參數：無
 * <br>傳回：傳回本物件
*/

public SYSAP701F_DTL  doQueryOne() throws Exception {
	SYSAP701F_DTL obj = this;
	EmpDetail c = (EmpDetail)ServiceGetter.getInstance().getCommonService().load(EmpDetail.class,Common.getLong(getId()));
	if (c!=null) {
        obj.setDtlId(c.getId().toString());
        obj.setRelSSN(c.getRelSsn());
        obj.setRelName(c.getRelName());
	if (c.getCommonCode()!=null) {
        obj.setRelRelative(c.getCommonCode().getId().toString());
        obj.setRelRelativeCodeId(c.getCommonCode().getCodeId());
        obj.setRelRelativeName(c.getCommonCode().getCodeName());
	} else {
        obj.setRelRelative("");
        obj.setRelRelativeCodeId("");
        obj.setRelRelativeName("");
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

	String hql = " from EmpDetail where empMaster.id=" + getId();
	if (getState().indexOf("query")<0) 
		ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
	
	java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by commonCode.codeId");
	if (objList != null && objList.size() > 0) {
		java.util.Iterator it = objList.iterator();
		while (it.hasNext()) {
			EmpDetail o = (EmpDetail) it.next();
			String rowArray[] = new String[4];
			rowArray[0] = Common.get(o.getId());
			rowArray[1] = Common.get(o.getRelSsn());
			rowArray[2] = Common.get(o.getRelName());
			rowArray[3] = Common.get(o.getCommonCode()!=null?o.getCommonCode().getCodeName():"");
			arrList.add(rowArray);					
		}
	}
	return arrList;
}

}


