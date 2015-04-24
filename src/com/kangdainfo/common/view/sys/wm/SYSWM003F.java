package com.kangdainfo.common.view.sys.wm;

import java.util.List;

import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonMsg;
import com.kangdainfo.common.model.bo.CommonMsgGroup;
import com.kangdainfo.common.model.bo.CommonMsgLog;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.model.bo.CommonUserGroup;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;

/**
*<br>程式目的：訊息傳送作業
*<br>程式代號：syswm003f
*<br>程式日期：0990316
*<br>程式作者：shark
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  SYSWM003F extends SuperBean{


String id;
String startDate;
String startTime;
String endDate;
String endTime;
String deptId;
String deptIdCodeId;
String deptIdName;
String userId, userName;
String userIds;
String deptIds;
String groupIds;
String msgBody;
String msgSender;
String isOpen;

String q_startDate, q_startDateE;
String q_startTime;
String q_endDate;
String q_endTime;
String q_userId;
String q_deptId;
String q_deptIdCodeId;
String q_deptIdName;
String q_msgBody;
String q_msgSender;
String q_isOpen;
String[] q_groupIds;

public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }
public String getStartDate(){ return checkGet(startDate); }
public void setStartDate(String s){ startDate=checkSet(s); }
public String getStartTime(){ return checkGet(startTime); }
public void setStartTime(String s){ startTime=checkSet(s); }
public String getEndDate(){ return checkGet(endDate); }
public void setEndDate(String s){ endDate=checkSet(s); }
public String getEndTime(){ return checkGet(endTime); }
public void setEndTime(String s){ endTime=checkSet(s); }
public String getDeptId(){ return checkGet(deptId); }
public void setDeptId(String s){ deptId=checkSet(s); }
public String getDeptIdName(){ return checkGet(deptIdName); }
public void setDeptIdName(String s){ deptIdName=checkSet(s); }
public String getDeptIdCodeId(){ return checkGet(deptIdCodeId); }
public void setDeptIdCodeId(String s){ deptIdCodeId=checkSet(s); }
public String getUserId(){ return checkGet(userId); }
public void setUserId(String s){ userId=checkSet(s); }
public String getUserName(){ return checkGet(userName); }
public void setUserName(String s){ userName=checkSet(s); }
public String getGroupIds(){ return checkGet(groupIds); }
public void setGroupIds(String s){ groupIds=checkSet(s); }
public String getUserIds() {return checkGet(userIds);}
public void setUserIds(String userIds) {this.userIds = checkSet(userIds);}
public String getDeptIds() {return checkGet(deptIds);}
public void setDeptIds(String deptIds) {this.deptIds = checkSet(deptIds);}
public String getMsgBody(){ return checkGet(msgBody); }
public void setMsgBody(String s){ msgBody=checkSet(s); }
public String getMsgSender(){ return checkGet(msgSender); }
public void setMsgSender(String s){ msgSender=checkSet(s); }
public String getIsOpen() { return checkGet(isOpen);}
public void setIsOpen(String isOpen) {this.isOpen = checkSet(isOpen);}

public String getQ_startDate(){ return checkGet(q_startDate); }
public void setQ_startDate(String s){ q_startDate=checkSet(s); }
public String getQ_startDateE(){ return checkGet(q_startDateE); }
public void setQ_startDateE(String s){ q_startDateE=checkSet(s); }
public String getQ_startTime(){ return checkGet(q_startTime); }
public void setQ_startTime(String s){ q_startTime=checkSet(s); }
public String getQ_endDate(){ return checkGet(q_endDate); }
public void setQ_endDate(String s){ q_endDate=checkSet(s); }
public String getQ_endTime(){ return checkGet(q_endTime); }
public void setQ_endTime(String s){ q_endTime=checkSet(s); }
public String getQ_userId(){ return checkGet(q_userId); }
public void setQ_userId(String s){ q_userId=checkSet(s); }
public String getQ_deptId(){ return checkGet(q_deptId); }
public void setQ_deptId(String s){ q_deptId=checkSet(s); }
public String getQ_deptIdCodeId(){ return checkGet(q_deptIdCodeId); }
public void setQ_deptIdCodeId(String s){ q_deptIdCodeId=checkSet(s); }
public String getQ_deptIdName(){ return checkGet(q_deptIdName); }
public void setQ_deptIdName(String s){ q_deptIdName=checkSet(s); }
public String getQ_msgBody(){ return checkGet(q_msgBody); }
public void setQ_msgBody(String s){ q_msgBody=checkSet(s); }
public String getQ_msgSender(){ return checkGet(q_msgSender); }
public void setQ_msgSender(String s){ q_msgSender=checkSet(s); }
public String getQ_isOpen() {return checkGet(q_isOpen);}
public void setQ_isOpen(String open) {q_isOpen = checkSet(open);}
public String[] getQ_groupIds() {return q_groupIds;}
public void setQ_groupIds(String[] s) {q_groupIds = s;}

@Override
public void doCreate() throws Exception {	
	CommonMsg obj = new CommonMsg();
	obj.setStartDate(startDate);
	obj.setStartTime(startTime);
	obj.setEndDate(endDate);
	obj.setEndTime(endTime);
	obj.setIsOpen(isOpen);
	obj.setDeptCode(deptIdCodeId);
	obj.setUserId(userId);	
	obj.setMsgBody(msgBody);
	obj.setMsgSender(msgSender);
	obj.setEditId(getEditID());
	obj.setEditDate(getEditDate());
	obj.setEditTime(getEditTime());
		
	if (this.getIsOpen().equals("1") && this.getUserIds()!=null && this.getUserIds().length()>0) {	
		java.util.Set dtlSet = new ListOrderedSet();
		String[] userList = this.getUserIds().split(",");
		for (int i=0; i<userList.length; i++) {
			CommonMsgGroup msgGroup = new CommonMsgGroup();
			msgGroup.setCommonMsg(obj);
			msgGroup.setUserId(Common.getInt(userList[i]));
			dtlSet.add(msgGroup);
		}
		obj.setCommonMsgGroups(dtlSet);
	} else if (this.getIsOpen().equals("2") && this.getDeptIds()!=null && this.getDeptIds().length()>0) {	
		java.util.Set dtlSet = new ListOrderedSet();
		String[] deptList = this.getDeptIds().split(",");
		for (int i=0; i<deptList.length; i++) {
			CommonMsgGroup msgGroup = new CommonMsgGroup();
			msgGroup.setCommonMsg(obj);
			msgGroup.setDeptId(Common.getInt(deptList[i]));
			dtlSet.add(msgGroup);
		}
		obj.setCommonMsgGroups(dtlSet);
	}  else if (this.getIsOpen().equals("3") && this.getGroupIds()!=null && this.getGroupIds().length()>0) {	
		java.util.Set dtlSet = new ListOrderedSet();
		String[] groupList = this.getGroupIds().split(",");
		for (int i=0; i<groupList.length; i++) {
			CommonMsgGroup msgGroup = new CommonMsgGroup();
			msgGroup.setCommonMsg(obj);
			msgGroup.setGroupId(Common.getInt(groupList[i]));
			dtlSet.add(msgGroup);
		}
		obj.setCommonMsgGroups(dtlSet);
	} else {
		java.util.Set dtlSet = new ListOrderedSet();
		CommonMsgGroup msgGroup = new CommonMsgGroup();
		msgGroup.setCommonMsg(obj);
		dtlSet.add(msgGroup);
		obj.setCommonMsgGroups(dtlSet);
	}
	ServiceGetter.getInstance().getCommonService().save(obj);
	setId(obj.getId().toString());
	
		
		
}


@Override
public void doUpdate() throws Exception {
	CommonMsg obj = (CommonMsg) View.getObject(" from CommonMsg where id=" + getId());
	obj.setStartDate(startDate);
	obj.setStartTime(startTime);
	obj.setEndDate(endDate);
	obj.setEndTime(endTime);
	obj.setIsOpen(isOpen);
	obj.setDeptCode(deptIdCodeId);
	obj.setUserId(userId);
	obj.setMsgBody(msgBody);
	obj.setMsgSender(msgSender);
	obj.setEditId(getEditID());
	obj.setEditDate(getEditDate());
	obj.setEditTime(getEditTime());
    ServiceGetter.getInstance().getCommonService().update(obj);
    //先刪除資料，再重新寫入
	StringBuffer sb = new StringBuffer();
	sb.append("delete from CommonMsgGroup where commonMsg.id=").append(Common.getLong(getId()));
	ServiceGetter.getInstance().getCommonService().bulkUpdate(sb.toString());
	ServiceGetter.getInstance().getCommonService().bulkUpdate("delete from CommonMsgLog where commonMsg.id="+Common.getLong(getId()));
	
    //傳送訊這種東西, 無關緊要, 所以也就包成Transaction了
    java.util.Map<Integer, CommonMsgGroup> h = new java.util.HashMap<Integer, CommonMsgGroup>();
    if (obj.getCommonMsgGroups()!=null && obj.getCommonMsgGroups().size()>0) {
    	for (Object dtlObj : obj.getCommonMsgGroups()) {
    		CommonMsgGroup dtl = (CommonMsgGroup) dtlObj;
    		h.put(dtl.getGroupId(), dtl);
    	}
    }    
    StringBuilder dtlUserKey = new StringBuilder("-2,-1,0");
    StringBuilder dtlDeptKey = new StringBuilder("-2,-1,0");
    StringBuilder dtlGroupKey = new StringBuilder("-2,-1,0");
	String[] userList = this.getUserIds().split(",");
	String[] deptList = this.getDeptIds().split(",");
	String[] groupList = this.getGroupIds().split(",");
	if (this.getIsOpen().equals("1") && this.getUserIds()!=null && this.getUserIds().length()>0) {	
		for (int i=0; i<userList.length; i++) {
			dtlUserKey.append(",").append(userList[i]);
			if (h.get(Common.getInteger(userList[i]))==null) {
				CommonMsgGroup msgGroup = new CommonMsgGroup();
				msgGroup.setCommonMsg(obj);
				msgGroup.setUserId(Common.getInt(userList[i]));
				ServiceGetter.getInstance().getCommonService().save(msgGroup);				
			}
		}
	} else if (this.getIsOpen().equals("2") && this.getDeptIds()!=null && this.getDeptIds().length()>0) {	
		for (int i=0; i<deptList.length; i++) {
			dtlDeptKey.append(",").append(deptList[i]);
			if (h.get(Common.getInteger(deptList[i]))==null) {
				CommonMsgGroup msgGroup = new CommonMsgGroup();
				msgGroup.setCommonMsg(obj);
				msgGroup.setDeptId(Common.getInt(deptList[i]));
				ServiceGetter.getInstance().getCommonService().save(msgGroup);				
			}
		}
	} else if (this.getIsOpen().equals("3") && this.getGroupIds()!=null && this.getGroupIds().length()>0) {				
		for (int i=0; i<groupList.length; i++) {
			dtlGroupKey.append(",").append(groupList[i]);
			if (h.get(Common.getInteger(groupList[i]))==null) {
				CommonMsgGroup msgGroup = new CommonMsgGroup();
				msgGroup.setCommonMsg(obj);
				msgGroup.setGroupId(Common.getInt(groupList[i]));
				ServiceGetter.getInstance().getCommonService().save(msgGroup);				
			}
		}
	} else {				
		CommonMsgGroup msgGroup = new CommonMsgGroup();
		msgGroup.setCommonMsg(obj);
		ServiceGetter.getInstance().getCommonService().save(msgGroup);	
	}     
}


@Override
public void doDelete() throws Exception {
	ServiceGetter.getInstance().getCommonService().bulkUpdate("delete from CommonMsgLog where commonMsg.id=" + Common.getLong(id));
	ServiceGetter.getInstance().getCommonService().delete(CommonMsg.class, Common.getLong(id));
}


/**
 * <br>
 * <br>目的：依主鍵查詢單一資料
 * <br>參數：無
 * <br>傳回：傳回本物件
*/

public SYSWM003F  doQueryOne() throws Exception {
	SYSWM003F obj = this;
	CommonMsg c = (CommonMsg)ServiceGetter.getInstance().getCommonService().load(CommonMsg.class,Common.getLong(getId()));
	if (c!=null) {
        obj.setId(c.getId().toString());
        obj.setIsOpen(c.getIsOpen());
        obj.setStartDate(c.getStartDate());
        obj.setStartTime(c.getStartTime());
        obj.setEndDate(c.getEndDate());
        obj.setEndTime(c.getEndTime());
        obj.setDeptIdCodeId(c.getDeptCode());
        obj.setDeptIdName(View.getCommonDeptName(c.getDeptCode()));
        obj.setUserId(c.getUserId());
        if (!"".equals(obj.getUserId())) obj.setUserName(View.getLookupField("select userName from CommonUser where userId=" + Common.sqlChar(c.getUserId())));
        else obj.setUserName("");
        obj.setMsgBody(c.getMsgBody());
        obj.setMsgSender(c.getMsgSender());
    	obj.setEditID(c.getEditId());
    	obj.setEditDate(c.getEditDate());
    	obj.setEditTime(c.getEditTime());
        if (c.getCommonMsgGroups()!=null && c.getCommonMsgGroups().size()>0) {
        	String userList = "";
        	String deptList = "";
        	String groupList = "";
        	for (Object dtlObj : c.getCommonMsgGroups()) {
        		CommonMsgGroup dtl = (CommonMsgGroup) dtlObj;
        		if(dtl.getUserId() > 0)
        			userList += String.valueOf(dtl.getUserId())+",";
        		if(dtl.getDeptId() > 0)
        			deptList += String.valueOf(dtl.getDeptId())+",";
        		if(dtl.getGroupId() > 0)
        			groupList += String.valueOf(dtl.getGroupId())+",";
        	}
        	if(userList.length()>0)
        		this.setUserIds(userList.substring(0, userList.length()-1));
        	if(deptList.length()>0)
        		this.setDeptIds(deptList.substring(0, deptList.length()-1));
        	if(groupList.length()>0)
        		this.setGroupIds(groupList.substring(0, groupList.length()-1));
        } else {
        	obj.setGroupIds(null);
        }
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

	String hql = " select distinct a.id,a.msgSender,a.msgBody,a.startDate,a.startTime from CommonMsg a,CommonMsgGroup b where a.id=b.commonMsg.id ";
	if(!"".equals(getQ_userId())){
		List<Integer> tmpList = ServiceGetter.getInstance().getCommonService().load("select id from CommonUser where userId like"+Common.sqlChar(getQ_userId()));
		StringBuffer sb = new StringBuffer();
		for(Integer ob:tmpList){
			sb.append(",").append(Common.get(ob));
		}
		hql += "and b.userId in(-1"+sb.toString()+")";
	}
	if (!"".equals(getQ_startDate()))
		hql += " and a.startDate>=" + Common.sqlChar(getQ_startDate());
	if (!"".equals(getQ_startDateE()))
		hql += " and a.startDate<=" + Common.sqlChar(getQ_startDateE());	
	if (!"".equals(getQ_startTime()))
		hql += " and a.startTime=" + Common.sqlChar(getQ_startTime());
	if (!"".equals(getQ_endDate()))
		hql += " and a.endDate=" + Common.sqlChar(getQ_endDate());
	if (!"".equals(getQ_endTime()))
		hql += " and a.endTime=" + Common.sqlChar(getQ_endTime());
	if (!"".equals(getQ_deptIdCodeId()))
		hql += " and b.deptId=" + getQ_deptId();
	if (!"".equals(getQ_isOpen()))
		hql += " and a.isOpen=" + Common.sqlChar(getQ_isOpen());	
	if (!"".equals(getQ_msgBody()))
		hql += " and a.msgBody like " + Common.sqlChar(getQ_msgBody());
	if (!"".equals(getQ_msgSender()))
		hql += " and a.msgSender like " + Common.sqlChar(getQ_msgSender());
	if (getQ_groupIds()!=null && getQ_groupIds().length>0) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<getQ_groupIds().length;i++){
			sb.append(",").append(getQ_groupIds()[i]);
		}
		hql += " and b.groupId in(-1"+sb.toString()+")";
	}
//	if (getQ_groupIds()!=null && getQ_groupIds().length>0) {		
//		QueryParams g = new QueryParams();
//		g.add(new QueryParam("and","groupId","in",getQ_groupIds()));
//		java.util.List list = ServiceGetter.getInstance().getCommonService().loadObjectsByParams(CommonMsgGroup.class, g);	
//		if (list!=null && list.size()>0) {
//			StringBuilder sb = new StringBuilder().append("-1");
//			for (int i=0; i<list.size(); i++) {
//				CommonMsgGroup group = (CommonMsgGroup) list.get(i);
//				sb.append(",").append(group.getCommonMsg().getId());
//			}
//			hql += " and a.id in (" + sb.toString() + ") "; 
//		} else {
//			hql += " and a.id=-1 ";			
//		}
//	}
	System.out.println("HQL===>"+hql);
	java.util.List<Object[]> tmpList = ServiceGetter.getInstance().getCommonService().load(hql);
	this.processCurrentPageAttribute(tmpList.size());
	if (getTotalRecord() > 0) {
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by a.id desc", this.getRecordStart(), this.getPageSize());
		if (objList != null && objList.size() > 0) {
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) {
				Object[] o = (Object[]) it.next();
				String rowArray[] = new String[7];
				rowArray[0] = Common.get(o[0]);				
				rowArray[1] = Common.get(o[1]);
				rowArray[2] = o[2]!=null?Common.checkGet(Common.get(o[2])):"";
				rowArray[3] = Common.get(o[3]);
				rowArray[4] = Common.formatHHMMSS(Common.get(o[4]));								
				arrList.add(rowArray);					
			}
		}
	}
	//getUnReadMsg(ServiceGetter.getInstance().getAuthenticationService().getCurrentUser(),true);
	return arrList;
}

/**
 * 訊息廣擴用的
 * @param user
 * @param isSlide
 * @return
 */
public java.util.ArrayList<String[]> getUnReadMsg(CommonUser user, boolean isSlide) {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	
	if (user!=null) {
		java.util.Map<Long,String> h = new java.util.HashMap<Long,String>(); 
		String hql = "from CommonMsgLog where commonMsg.startDate=" + Common.sqlChar(Datetime.getYYYMMDD()) + " and userId=" + Common.sqlChar(user.getUserId());	
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				CommonMsgLog obj = (CommonMsgLog) list.get(i);			
				h.put(obj.getCommonMsg().getId(), "Y");
			}
		}
			
		StringBuilder ids = null;
		hql = "from CommonUserGroup where commonUser.id=" + user.getId();
		list = ServiceGetter.getInstance().getCommonService().load(hql);		
		if (list!=null && list.size()>0) {
			ids = new StringBuilder();
			for (int i=0; i<list.size(); i++) {
				CommonUserGroup group = (CommonUserGroup) list.get(i);
				ids.append(",").append(group.getCommonGroup().getId());
			}		 		
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("from CommonMsgGroup where commonMsg.startDate=" + Common.sqlChar(Datetime.getYYYMMDD()) + " and (commonMsg.isOpen='Y' "); 
		sb.append(" or userId=").append(user.getId());
		sb.append(" or deptId=").append(user.getCommonDepartment().getId());	
		if (ids!=null) sb.append(" or groupId in (-1").append(ids).append(") ");	
		sb.append(") order by commonMsg.id");
		String msgId = "";	//20100622 Carter for 避免多個身分別會多次收到同一封訊息
		list = ServiceGetter.getInstance().getCommonService().load(sb.toString());
		if (list!=null && list.size()>0) {
			java.util.List<CommonMsgLog> saveList = new java.util.ArrayList<CommonMsgLog>();
			for (int i=0; i<list.size(); i++) {
				CommonMsgGroup o = (CommonMsgGroup) list.get(i);
				
				if (isSlide && h.get(o.getCommonMsg().getId())!=null) continue;
								
				if (isSlide && h.get(o.getCommonMsg().getId())==null) {
					CommonMsgLog obj = new CommonMsgLog();
					obj.setLogDate(Datetime.getYYYMMDD());
					obj.setLogTime(Datetime.getHHMMSS());
					obj.setCommonMsg(o.getCommonMsg());
					obj.setUserId(user.getUserId());
					obj.setIsSlide("Y");
					saveList.add(obj);
				}
				if(!msgId.equals(Common.get(o.getCommonMsg().getId()))){ //20100622 Carter for 避免多個身分別會多次收到同一封訊息
				String rowArray[] = new String[4];
				rowArray[0] = Common.get(o.getCommonMsg().getId());				
				rowArray[1] = Common.get(o.getCommonMsg().getMsgSender());
				rowArray[2] = Common.FormatStr(Common.get(o.getCommonMsg().getMsgBody()),"N");
				rowArray[3] = Common.formatYYYMMDD(o.getCommonMsg().getStartDate(),4) + " " + Common.formatHHMMSS(o.getCommonMsg().getStartTime());	
				arrList.add(rowArray);
				} //20100622 Carter for 避免多個身分別會多次收到同一封訊息
				msgId = Common.get(o.getCommonMsg().getId()); //20100622 Carter for 避免多個身分別會多次收到同一封訊息
			}
			//if (saveList!=null && saveList.size()>0) ServiceGetter.getInstance().getCommonService().save(saveList);
		}				
	}
	return arrList;
}


}


