package com.kangdainfo.common.view.sys.ap;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonAuth;
import com.kangdainfo.common.model.bo.CommonGroup;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;

/**
*<br>程式目的：群組資料維護作業
*<br>程式代號：sysap002f
*<br>程式日期：0960511
*<br>程式作者：clive.chang
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/
public class  SYSAP002F extends SuperBean{


String id;
String groupName;
String groupDesc;

String q_groupName;
String q_groupDesc;

public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }
public String getGroupName(){ return checkGet(groupName); }
public void setGroupName(String s){ groupName=checkSet(s); }
public String getGroupDesc(){ return checkGet(groupDesc); }
public void setGroupDesc(String s){ groupDesc=checkSet(s); }

public String getQ_groupName(){ return checkGet(q_groupName); }
public void setQ_groupName(String s){ q_groupName=checkSet(s); }
public String getQ_groupDesc(){ return checkGet(q_groupDesc); }
public void setQ_groupDesc(String s){ q_groupDesc=checkSet(s); }


@Override
public void doCreate() throws Exception {
	CommonGroup obj = null;
	java.util.List list = ServiceGetter.getInstance().getCommonService().getCommonGroupDao().loadCommonGroupsByGroupName(groupName);
	if (list!=null && list.size()>0) obj = (CommonGroup) list.get(0);  	 
	if (obj!=null && obj.getGroupName().equals(groupName)) {
		throw new Exception("該筆資料己重複，請重新輸入！");
	} else {
		obj = new CommonGroup();
		//obj.setId(id);
		obj.setGroupName(groupName);
		obj.setGroupDesc(groupDesc);
		obj.setEditId(getEditID());
		obj.setEditDate(getEditDate());
		obj.setEditTime(getEditTime());
	    ServiceGetter.getInstance().getCommonService().getCommonGroupDao().saveCommonGroup(obj);
	    this.setId(obj.getId().toString());		
	}
}


@Override
public void doUpdate() throws Exception {
	CommonGroup obj = new CommonGroup();
	obj.setId(Integer.parseInt(getId()));
	obj.setGroupName(groupName);
	obj.setGroupDesc(groupDesc);
	obj.setEditId(getEditID());
	obj.setEditDate(getEditDate());
	obj.setEditTime(getEditTime());
    ServiceGetter.getInstance().getCommonService().getCommonGroupDao().updateCommonGroup(obj);
}


@Override
public void doDelete() throws Exception {
	java.util.List<CommonAuth> authList = ServiceGetter.getInstance().getCommonService().load("from CommonAuth where commonGroup.id="+getId());
	java.util.List<CommonUser> userList = ServiceGetter.getInstance().getCommonService().load("from CommonUserGroup where commonGroup.id="+getId());
	
	if(authList!=null&&authList.size()>0){
		throw new Exception("身分別名稱已被賦與身分別權限,不允許刪除!!");
	}else if(userList!=null && userList.size()>0){
		throw new Exception("身分別名稱已被賦與使用者,不允許刪除!!");
	}else{
		ServiceGetter.getInstance().getCommonService().getCommonGroupDao().deleteCommonGroup(Integer.parseInt(id));
	}
}
	


/**
 * <br>
 * <br>目的：依主鍵查詢單一資料
 * <br>參數：無
 * <br>傳回：傳回本物件
*/

public SYSAP002F  doQueryOne() throws Exception {
	SYSAP002F obj = this;
	CommonGroup c = (CommonGroup)ServiceGetter.getInstance().getCommonService().getCommonGroupDao().load(CommonGroup.class,Integer.parseInt(getId()));
	if (c!=null) {
        obj.setGroupName(c.getGroupName());
        obj.setGroupDesc(c.getGroupDesc());
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
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
    String sql= " select a.id, a.groupName, a.groupDesc, a.editId, a.editDate ";
    String hql=" from "+ CommonGroup.class.getCanonicalName() + " a where 1=1 "; 
	if (!"".equals(getQ_groupName()))
		hql+=" and a.groupName like " + Common.sqlChar(getQ_groupName()) ;
	if (!"".equals(getQ_groupDesc()))
		hql+=" and a.groupDesc like " + Common.sqlChar(getQ_groupDesc()) ;
	this.initRecourdCount(hql);
	if (getTotalRecord() > 0) {
		 java.util.List objList = ServiceGetter.getInstance().getCommonService().getCommonGroupDao().load(sql+hql, this.getRecordStart(),this.getPageSize());
		 if (objList!=null && objList.size()>0) {
		     for (int i=0; i<objList.size(); i++) {
		    	 Object[] o=(Object[]) objList.get(i);
		    	 String rowArray[]=new String[o.length];
		    	 rowArray[0]=Common.get(o[0]); 
		    	 rowArray[1]=Common.get(o[1]); 
		    	 rowArray[2]=Common.get(o[2]); 
		    	 rowArray[3]=Common.get(o[3]);
		    	 rowArray[4]=Common.get(o[4]);
		    	 arrList.add(rowArray);
		     }
	     }
	}
	return arrList;
}

}


