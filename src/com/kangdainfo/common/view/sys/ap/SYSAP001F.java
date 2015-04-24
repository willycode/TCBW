package com.kangdainfo.common.view.sys.ap;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonCodeKind;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonGroup;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.model.bo.CommonUserGroup;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.persistence.hibernate3.query.params.QueryParam;
import com.kangdainfo.persistence.hibernate3.query.params.QueryParams;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class  SYSAP001F extends SuperBean{

	private String id;
	private String userId;
	private String userSSN;
	private String userPWD;
	private String userName;
	private String groupId;
	private String deptId;
	private String deptIdFullCode;
	private String deptIdName;
	private String jobTitle;
	private String userTel;
	private String userMobile;
	private String userFax;
	private String userEmail;
//	private String takeJobDate;
	private String isStop;
	private String roleId;
	private String memo;
	private String applyDate;
	private String personalId;
	
	private String q_userId;
	private String q_userName;
//	private String q_groupId;
	private String q_deptId;
	private String q_deptIdFullCode;
	private String q_deptIdName;
	private String q_userEmail;
	private String q_isStop;
	private String q_roleId;
	private String q_applyDate;
	
	public String getId(){ return checkGet(id); }
	public void setId(String s){ id=checkSet(s); }
	public String getUserId(){ return checkGet(userId); }
	public void setUserId(String s){ userId=checkSet(s); }
	public String getUserSSN(){ return checkGet(userSSN); }
	public void setUserSSN(String s){ userSSN=checkSet(s); }
	public String getUserPWD(){ return get(userPWD); }
	public void setUserPWD(String s){ userPWD=get(s); }
	public String getUserName(){ return checkGet(userName); }
	public void setUserName(String s){ userName=checkSet(s); }
	public String getGroupId(){ return checkGet(groupId); }
	public void setGroupId(String s){ groupId=checkSet(s); }
	public String getDeptId(){ return checkGet(deptId); }
	public void setDeptId(String s){ deptId=checkSet(s); }
	public String getDeptIdFullCode(){ return checkGet(deptIdFullCode); }
	public void setDeptIdFullCode(String s){ deptIdFullCode=checkSet(s); }
	public String getDeptIdName(){ return checkGet(deptIdName); }
	public void setDeptIdName(String s){ deptIdName=checkSet(s); }
	public String getJobTitle(){ return checkGet(jobTitle); }
	public void setJobTitle(String s){ jobTitle=checkSet(s); }
	public String getUserTel(){ return checkGet(userTel); }
	public void setUserTel(String s){ userTel=checkSet(s); }
	public String getUserMobile(){ return checkGet(userMobile); }
	public void setUserMobile(String s){ userMobile=checkSet(s); }
	public String getUserFax(){ return checkGet(userFax); }
	public void setUserFax(String s){ userFax=checkSet(s); }
	public String getUserEmail(){ return checkGet(userEmail); }
	public void setUserEmail(String s){ userEmail=checkSet(s); }
//	public String getTakeJobDate(){ return checkGet(takeJobDate); }
//	public void setTakeJobDate(String s){ takeJobDate=checkSet(s); }
	public String getIsStop(){ return checkGet(isStop); }
	public void setIsStop(String s){ isStop=checkSet(s); }
	public String getRoleId(){ return checkGet(roleId); }
	public void setRoleId(String s){ roleId=checkSet(s); }
	public String getMemo(){ return checkGet(memo); }
	public void setMemo(String s){ memo=checkSet(s); }
	public String getApplyDate() {		return checkGet(applyDate);	}
	public void setApplyDate(String applyDate) {		this.applyDate = checkSet(applyDate);	}
	public String getPersonalId() {		return checkGet(personalId);	}
	public void setPersonalId(String personalId) {		this.personalId = checkSet(personalId);	}
	
	public String getQ_userId(){ return checkGet(q_userId); }
	public void setQ_userId(String s){ q_userId=checkSet(s); }
	public String getQ_userName(){ return checkGet(q_userName); }
	public void setQ_userName(String s){ q_userName=checkSet(s); }
//	public String getQ_groupId(){ return checkGet(q_groupId); }
//	public void setQ_groupId(String s){ q_groupId=checkSet(s); }
	public String getQ_deptId(){ return checkGet(q_deptId); }
	public void setQ_deptId(String s){ q_deptId=checkSet(s); }
	public String getQ_deptIdFullCode(){ return checkGet(q_deptIdFullCode); }
	public void setQ_deptIdFullCode(String s){ q_deptIdFullCode=checkSet(s); }
	public String getQ_deptIdName(){ return checkGet(q_deptIdName); }
	public void setQ_deptIdName(String s){ q_deptIdName=checkSet(s); }
	public String getQ_userEmail(){ return checkGet(q_userEmail); }
	public void setQ_userEmail(String s){ q_userEmail=checkSet(s); }
	public String getQ_isStop(){ return checkGet(q_isStop); }
	public void setQ_isStop(String s){ q_isStop=checkSet(s); }
	public String getQ_roleId(){ return checkGet(q_roleId); }
	public void setQ_roleId(String s){ q_roleId=checkSet(s); }
	public String getQ_applyDate() {		return checkGet(q_applyDate);	}
	public void setQ_applyDate(String q_applyDate) {		this.q_applyDate = checkSet(q_applyDate);	}

	String isMgr, q_isMgr;
	public String getIsMgr() {return checkGet(isMgr);}
	public void setIsMgr(String s) {this.isMgr = checkSet(s);}
	public String getQ_isMgr() {return checkGet(q_isMgr);}
	public void setQ_isMgr(String s) {q_isMgr = checkSet(s);}
	
	String[] groupIds, q_groupIds;
	public String[] getGroupIds() {return groupIds;}
	public void setGroupIds(String[] groupIds) {this.groupIds = groupIds;}
	public String[] getQ_groupIds() {return q_groupIds;}
	public void setQ_groupIds(String[] ids) {q_groupIds = ids;}
	
	private String[] subSystem;
	public String[] getSubSystem() {		return subSystem;	}
	public void setSubSystem(String[] subSystem) {		this.subSystem = subSystem;	}
	
	/*
	String isDeptManager;
	String q_isDeptManager;
	public String getIsDeptManager() {return checkGet(isDeptManager);}
	public void setIsDeptManager(String s) {this.q_isDeptManager = checkSet(s);}
	public String getQ_isDeptManager() {return checkGet(isDeptManager);}
	public void setQ_isDeptManager(String s) {this.q_isDeptManager = checkSet(s);}
	*/
	
	
	@Override
	public void doCreate() throws Exception {
		CommonUser obj = (CommonUser) View.getObject(" from CommonUser where userId = " + Common.sqlChar(getUserId()));
		if (obj != null) {
			throw new Exception("新增失敗，帳號重覆");
		} else {
			obj = new CommonUser();
			obj.setUserId(getUserId());
		//	obj.setUserSsn(userSSN);
			if (!"".equals(getUserPWD())){
				obj.setUserPwd(Common.getDigestString(getUserPWD(), "SHA-1"));
			}
			obj.setUserName(getUserName());
			if(!"".equals(getPersonalId())){
				obj.setPersonalId(TCBWCommon.getEncodeString(getPersonalId()));
			}
			obj.setJobTitle(getJobTitle());
			obj.setUserTel(getUserTel());
			obj.setUserMobile(getUserMobile());
			obj.setUserFax(getUserFax());
			obj.setUserEmail(getUserEmail());
			obj.setIsStop(getIsStop());
//			obj.setTakeJobDate(takeJobDate);
//			obj.setIsDeptManager(isDeptManager);
			obj.setApplyDate(getApplyDate());
			obj.setMemo(getMemo());
			obj.setEditId(getEditID());
			obj.setEditDate(getEditDate());
			obj.setEditTime(getEditTime());
			
			// 所屬子系統
			if(getSubSystem()!=null && getSubSystem().length>0){
				StringBuffer s = new StringBuffer();
				for(String idx : getSubSystem()){
					if(s.toString().length() > 0)	s.append(",");
					s.append(idx);
				}
				if(s.toString().length() > 0){
					obj.setSubSystem(s.toString());
				}
			}
			
			if ("Y".equals(getIsMgr()) || Common.get(getRoleId()).equals("3")){
				obj.setRoleId(3);
			} else {
				obj.setRoleId(1);	
			}
	
			// 所屬單位
			CommonDepartment dept = new CommonDepartment();
			dept.setId(Common.getInt(getDeptId()));
			obj.setCommonDepartment(dept);
	
			if (!"".equals(getJobTitle())) {
				CommonCode code = new CommonCode();
				code.setId(Common.getInt(getJobTitle()));
				obj.setCommonCode(code);			
			}		
			
//			if (!"".equals(getGroupId())) {
//				CommonGroup group = new CommonGroup();
//				group.setId(Common.getInt(groupId));	
//				obj.setCommonGroup(group);				
//			}
	
			// 所屬群組 - 身分別
			int [] gids = null;
			if (getGroupIds()!=null && getGroupIds().length>0) {
				gids = new int[this.getGroupIds().length];
				for (int i=0; i<this.getGroupIds().length; i++) {
					gids[i] = Common.getInt(getGroupIds()[i]);
				}
			}		
			ServiceGetter.getInstance().getCommonService().getCommonUserDao().saveCommonUser(obj, gids);
		}
	}
	
	@Override
	public void doUpdate() throws Exception {
		CommonUser obj = (CommonUser) View.getObject("from CommonUser where id=" + Common.getInt(getId()));	
		if (obj != null) {
//			obj.setUserId(userId);
//			obj.setUserSsn(userSSN);
			if (!"".equals(getUserPWD())){
				obj.setUserPwd(Common.getDigestString(getUserPWD(),"SHA-1"));
			}
//			obj.setUserName(userName);
			if(!"".equals(getPersonalId())){
				obj.setPersonalId(TCBWCommon.getEncodeString(getPersonalId()));
			}
			obj.setJobTitle(getJobTitle());
			obj.setUserTel(getUserTel());
			obj.setUserMobile(getUserMobile());
			obj.setUserFax(getUserFax());
			obj.setUserEmail(getUserEmail());
			obj.setIsStop(getIsStop());
//			obj.setTakeJobDate(takeJobDate);
//			obj.setIsDeptManager(isDeptManager);
//			obj.setApplyDate(getApplyDate());
			obj.setMemo(getMemo());
			obj.setEditId(getEditID());
			obj.setEditDate(getEditDate());
			obj.setEditTime(getEditTime());
			
			// 所屬子系統
			if(getSubSystem()!=null && getSubSystem().length>0){
				StringBuffer s = new StringBuffer();
				for(String idx : getSubSystem()){
					if(s.toString().length() > 0)	s.append(",");
					s.append(idx);
				}
				if(s.toString().length() > 0){
					obj.setSubSystem(s.toString());
				}
			}
			
			if ("Y".equals(getIsMgr()) || Common.get(getRoleId()).equals("3")){
				obj.setRoleId(3);
			} else {
				obj.setRoleId(1);
			}
			
			// 所屬單位
			CommonDepartment dept = new CommonDepartment();
			dept.setId(Common.getInt(getDeptId()));
			obj.setCommonDepartment(dept);
		
//			if (!"".equals(getJobTitle())) {
//				CommonCode code = new CommonCode();
//				code.setId(Common.getInt(getJobTitle()));
//				obj.setCommonCode(code);			
//			} else {
//				obj.setCommonCode(null);
//			}
		
			
//			if (!"".equals(getGroupId())) {
//				CommonGroup group = new CommonGroup();
//				group.setId(Common.getInt(groupId));	
//				obj.setCommonGroup(group);				
//			} else obj.setCommonGroup(null);
			
			int [] gids = null;
			if (this.getGroupIds()!=null && this.getGroupIds().length>0) {
				gids = new int[this.getGroupIds().length];
				for (int i=0; i<this.getGroupIds().length; i++) {
					gids[i] = Common.getInt(getGroupIds()[i]);
				}
			}		
			ServiceGetter.getInstance().getCommonService().getCommonUserDao().updateCommonUser(obj, gids);
		} else {
			throw new Exception("查無資料，無法更新");
		}
		
	}
	
	@Override
	public void doDelete() throws Exception {
		ServiceGetter.getInstance().getCommonService().getCommonUserDao().deleteCommonUser(Common.getInt(getId()));	
	}
	
	
	/**
	 * <br>
	 * <br>目的：依主鍵查詢單一資料
	 * <br>參數：無
	 * <br>傳回：傳回本物件
	*/
	
	public SYSAP001F  doQueryOne() throws Exception {
		SYSAP001F obj = this;
		CommonUser c = (CommonUser)ServiceGetter.getInstance().getCommonService().getCommonUserDao().load(CommonUser.class, Integer.parseInt(getId()));
		if (c != null) {
	        obj.setId(Common.get(c.getId()));
	        obj.setUserId(c.getUserId());
//			obj.setUserSSN(c.getUserSsn());
//			obj.setUserPWD(c.getUserPwd());
	        obj.setUserPWD("");
	        obj.setUserName(c.getUserName());
	        if(!"".equals(Common.get(c.getPersonalId()))){
	        	obj.setPersonalId(TCBWCommon.getDecodeString(c.getPersonalId()));
	        }else{
	        	obj.setPersonalId("");
	        }
	        obj.setJobTitle(c.getJobTitle());
	        obj.setUserTel(c.getUserTel());
	        obj.setUserMobile(c.getUserMobile());
	        obj.setUserFax(c.getUserFax());
	        obj.setUserEmail(c.getUserEmail());
	        obj.setIsStop(c.getIsStop());
	        obj.setApplyDate(c.getApplyDate());
	        obj.setMemo(c.getMemo());
	        obj.setEditID(c.getEditId());
	        obj.setEditDate(c.getEditDate());
	        obj.setEditTime(c.getEditTime());
	        
	        // 所屬子系統
	        if(!"".equals(Common.get(c.getSubSystem()))){
	        	String[] ridArray = c.getSubSystem().split(",");
	        	obj.setSubSystem(ridArray);
	        }else{
	        	obj.setSubSystem(null);
	        }
	        
	        if (c.getRoleId()!=null && c.getRoleId()>2){
	        	obj.setIsMgr("Y");
	        } else {
	        	obj.setIsMgr("N");
	        }
	        
	        // 所屬單位
	        obj.setDeptId(c.getCommonDepartment()!=null?c.getCommonDepartment().getId().toString():"");
	        obj.setDeptIdFullCode(c.getCommonDepartment()!=null?c.getCommonDepartment().getFullCode():"");
	        obj.setDeptIdName(c.getCommonDepartment()!=null?c.getCommonDepartment().getFullName():"");

	        
//			obj.setRoleId(Common.get(c.getRoleId()));
//	        obj.setGroupId(c.getCommonGroup()!=null?c.getCommonGroup().getId().toString():"");
	        
			String hql = " from CommonUserGroup where commonUser.id = " + Common.getInt(c.getId());
			java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);		
			if (list!=null && list.size()>0) {
				this.groupIds = new String[list.size()];
				for (int i=0; i<list.size(); i++) {
					CommonUserGroup g = (CommonUserGroup) list.get(i);
					groupIds[i] = g.getCommonGroup().getId().toString();
				}
			} else {
				obj.setGroupIds(null);
			}
		} else {
			throw new Exception("查無該筆資料！");
		}
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

		StringBuffer sb = new StringBuffer();
		sb.append(" from CommonUser where 1=1 ");
		if (!"".equals(getQ_userId()))
			sb.append(" and userId like ").append(Common.sqlChar(getQ_userId()));
		if (!"".equals(getQ_userName()))
			sb.append(" and userName like ").append(Common.sqlChar(getQ_userName()));
		if (getQ_groupIds()!=null && getQ_groupIds().length>0) {		
			QueryParams g = new QueryParams();
			g.add(new QueryParam("and", "commonGroup.id", "in", getQ_groupIds()));
			java.util.List list = ServiceGetter.getInstance().getCommonService().loadObjectsByParams(CommonUserGroup.class, g);	
			if (list!=null && list.size()>0) {		
				StringBuffer sb2 = new StringBuffer().append("-1");
				for (int i=0; i<list.size(); i++) {
					CommonUserGroup group = (CommonUserGroup) list.get(i);
					sb2.append(",").append(group.getCommonUser().getId());
				}				
				sb.append(" and id in(").append(sb2.toString()).append(")");
			} else {
				sb.append(" and id = -1 ");
			}
		}
		if (!"".equals(getQ_deptId()))
			sb.append(" and commonDepartment.id = ").append(Common.sqlChar(getQ_deptId()));
		if (!"".equals(getQ_userEmail()))
			sb.append(" and userEmail like ").append(Common.sqlChar(getQ_userEmail()));
		if (!"".equals(getQ_isStop()))
			sb.append(" and isStop = ").append(Common.sqlChar(getQ_isStop()));
		if (!"".equals(getQ_roleId()))
			sb.append(" and roleId = ").append(Common.getInteger(getQ_roleId()));
//		if (!"".equals(getQ_isDeptManager())) {
//			if ("Y".equals(getQ_isDeptManager())) sb.append(" and isDeptManager=").append(Common.sqlChar(getQ_isDeptManager()));
//			else sb.append(" and (isDeptManager is null or isDeptManager!='Y') ");
//		}
		if ("Y".equals(getQ_isMgr()))
			sb.append(" and roleId = 3");
		if ("N".equals(getQ_isMgr()))
			sb.append(" and roleId != 3");		
		if (!"".equals(getQ_applyDate())) 
			sb.append(" and applyDate = ").append(Common.sqlChar(getQ_applyDate()));

		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().getCommonUserDao().loadCount(sb.toString()));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getCommonUserDao().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().getCommonUserDao().load(sb.toString(), this.getRecordStart(), this.getPageSize(), "userId", true);
			if (objList != null && objList.size() > 0) {
				java.util.Iterator it = objList.iterator();
				java.util.Map<String, String> titleMap = TCBWCommon.getCommonCodeMap("TITLE");
				while (it.hasNext()) {
					CommonUser o = (CommonUser) it.next();					
					
					String rowArray[] = new String[7];
					rowArray[0] = Common.get(o.getId());
					rowArray[1] = Common.get(o.getUserId());
					rowArray[2] = Common.get(o.getUserName());
					rowArray[3] = Common.get(o.getJobTitle()!=null?titleMap.get(o.getJobTitle()):"");
					rowArray[4] = Common.get(o.getCommonDepartment()!=null?o.getCommonDepartment().getDepartment():"");
					rowArray[5] = Common.get(o.getUserTel());
					rowArray[6] = (o.getRoleId()!=null && o.getRoleId()>2)?"Y":"N";
					arrList.add(rowArray);					
				}
				if(titleMap!=null) titleMap.clear();
			}
		}
		return arrList;
	}

	public void doUpdatePeraonal() throws Exception {		
		CommonUser obj = (CommonUser)ServiceGetter.getInstance().getCommonService().getCommonUserDao().load(CommonUser.class,Integer.parseInt(getId()));
		if (obj!=null) {
			obj.setUserSsn(userSSN);
			if (!"".equals(getUserPWD()) && getUserPWD().length()>0) obj.setUserPwd(Common.getDigestString(userPWD,"SHA-1"));
			obj.setUserName(userName);
			
			/**
			if (!"".equals(getDeptId())) {
				CommonDepartment dept = new CommonDepartment();
				dept.setId(Common.getInt(deptId));
				obj.setCommonDepartment(dept);					
			}
			**/
			if (!"".equals(getJobTitle())) {
				CommonCode code = new CommonCode();
				code.setId(Common.getInt(getJobTitle()));
				obj.setCommonCode(code);				
			}
			obj.setUserTel(userTel);
			obj.setUserMobile(userMobile);
			obj.setUserFax(userFax);
			obj.setUserEmail(userEmail);
//			obj.setTakeJobDate(takeJobDate);

			obj.setMemo(memo);
			obj.setEditId(getEditID());
			obj.setEditDate(getEditDate());
			obj.setEditTime(getEditTime());
			ServiceGetter.getInstance().getCommonService().getCommonUserDao().updateCommonUser(obj);
			this.setStateUpdateSuccess();
			this.setErrorMsg("修改完成");
		} else {
			throw new Exception("查無資料");
		}	
	}	
	
	public void importUsersData() throws Exception{
		Database db = new Database(PersistenceServiceGetter.getInstance().getJdbcTemplate().getDataSource().getConnection());
		try {
			int maxTitleCode = Common.getInt(View.getObject("select max(codeId) from CommonCode where commonCodeKind.codeKindId='TIT' "));
			java.util.Map<String, CommonCode> codeMap = new java.util.HashMap<String, CommonCode>();
			java.util.Map<String, CommonDepartment> deptMap = new java.util.HashMap<String, CommonDepartment>();
			
			CommonCodeKind tMaster = null;
			java.util.List list = ServiceGetter.getInstance().getCommonService().load("from CommonCode where commonCodeKind.codeKindId='TIT' ");
			if (list!=null && list.size()>0) {
				for (Object o : list) {
					CommonCode obj = (CommonCode) o;
					codeMap.put(obj.getCodeName(), obj);
					
					tMaster = obj.getCommonCodeKind();
				}
			}
			
			list = ServiceGetter.getInstance().getCommonService().load("from CommonDepartment ");
			if (list!=null && list.size()>0) {
				for (Object o : list) {
					CommonDepartment obj = (CommonDepartment) o;
					deptMap.put(obj.getDepartment(), obj);
				}
			}
			
			CommonGroup commonGroup = (CommonGroup) View.getObject("from CommonGroup order by id desc");
			
			java.util.ArrayList<CommonUser> saveList = new java.util.ArrayList<CommonUser>();
			java.util.ArrayList<CommonCode> codeList = new java.util.ArrayList<CommonCode>();
			java.sql.ResultSet rs = db.querySQL("select deptName, userId, userName, titleName, userEmail from KCCC_USERS",null);
			while (rs.next()) {
				String dName = Common.get(rs.getObject("deptName"));
				String tName = Common.get(rs.getObject("titleName"));
				
				CommonCode t = codeMap.get(tName);
				if (t==null) {
					t = new CommonCode();
					t.setCommonCodeKind(tMaster);
					t.setCodeId(Common.formatFrontZero(String.valueOf(++maxTitleCode), 2));
					t.setCodeName(tName);
					codeList.add(t);
					
					codeMap.put(tName, t);
				}
				
				CommonDepartment d = deptMap.get(dName);
				if (d==null) {
					throw new Exception("查無機關：" + dName);
				}
				
				
				CommonUser u = new CommonUser();
				u.setUserId(Common.get(rs.getObject("userId")));
				u.setUserName(Common.get(rs.getObject("userName")));
				u.setCommonCode(t);
				u.setCommonDepartment(d);
				u.setUserPwd(Common.getDigestString("666666","SHA-1"));
				u.setUserEmail(Common.get(rs.getObject("userEmail")));
				u.setRoleId(1);
				u.setCommonGroup(commonGroup);
				u.setIsStop("N");
				u.setIsDeptManager("N");
				u.setIsAdministrator("N");
				
				saveList.add(u);
								
			}
			
			ServiceGetter.getInstance().getCommonService().save(codeList);
			ServiceGetter.getInstance().getCommonService().save(saveList);
			
			this.setErrorMsg("資料匯入完成");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
		
	}
	
	
	public java.util.ArrayList<String[]> doQueryOnlineUser() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		java.util.Map<String, CommonUser> h = ServiceGetter.getInstance().getMyServletContext().getOnlistUserMap();
		if (h!=null && h.size()>0) {
			for (String key : h.keySet()) {
				//使用者帳號、登入IP 位址、登入時間、登入狀態(登入成功、失敗)等等
				CommonUser o = (CommonUser) h.get(key);
				String rowArray[] = new String[6];
				rowArray[0] = Common.get(o.getSessionId());
				rowArray[1] = Common.get(o.getUserId());
				rowArray[2] = Common.get(o.getUserName());
				rowArray[3] = Common.get(o.getUserIP());					
				rowArray[4] = Common.formatHHMMSS(o.getLoginTime());
				rowArray[5] = "成功";		
				arrList.add(rowArray);
			}
		}
		return arrList;
	}
	
	
	
	
}


