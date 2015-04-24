package com.kangdainfo.tcbw.view.conin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1007Db;
import com.kangdainfo.tcbw.model.bo.Con1014Db;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONIN0702F extends CONIN0701F{

	private String id2;		//第二頁籤ID
//	private String sysKind;		//系統別
//	private String con1007_id;	//通報表單名稱
//	private String name;		//流程角色名稱
//	private String userId;	//群組人員ID
//	private String competence;	//權限
	private String con1014_id;
	private String usernameid;
	private String commonUser;
	private String[] competence;

	public String getId2() {
		return checkGet(id2);
	}

	public void setId2(String id2) {
		this.id2 = checkSet(id2);
	}
	
	public String getCommonUser() {
		return checkGet(commonUser);
	}

	public void setCommonUser(String commonUser) {
		this.commonUser = checkSet(commonUser);
	}

	public String getUsernameid() {
		return checkGet(usernameid);
	}

	public void setUsernameid(String usernameid) {
		this.usernameid = checkSet(usernameid);
	}
	
	public String[] getCompetence() {
		return competence;
	}
	
	public void setCompetence(String[] competence) {
		this.competence = competence;	
	}

//	public String getSysKind() {
//		return checkGet(sysKind);
//	}
//
//	public void setSysKind(String sysKind) {
//		this.sysKind = checkSet(sysKind);
//	}
//
	public String getCon1014_id() {
		return checkGet(con1014_id);
	}

	public void setCon1014_id(String con1014_id) {
		this.con1014_id = checkSet(con1014_id);
	}
//	
//	public String getName() {
//		return checkGet(name);
//	}
//
//	public void setName(String name) {
//		this.name = checkSet(name);
//	}
	
//	public String getUserId() {
//		return checkGet(userId);
//	}
//
//	public void setUserId(String userId) {
//		this.userId = checkSet(userId);
//	}
	public boolean isUpdateYn() throws Exception {
		CONIN0702F obj = this;
		boolean isUpdateYn = true;
		CommonUser c = (CommonUser) View.getObject(" from CommonUser where userId="+Common.sqlChar(getUserID()));
		if(c!=null && !"".equals(c.getSubSystem())){            
            String[] subSystem = c.getSubSystem().split(",");
			for(String system: subSystem){
                if(system.equals(obj.getSysKind())){
                	isUpdateYn=false;
                	break;
                }
			}
		}
		return isUpdateYn;
	}
	@Override
	public Object doQueryOne() throws Exception {
		
		CONIN0702F obj = this;
		Con1015Db c = (Con1015Db)View.getObject("from Con1015Db where id = " + Common.getLong(getId2()));
		if(c != null){
			obj.setId(Common.get(c.getCon1014Db().getId()));
			obj.setId2(Common.get(c.getId()));
			obj.setSysKind(Common.get(c.getCon1014Db().getSysKind()));
			obj.setCon1007_id(Common.get(c.getCon1014Db().getCon1007Db().getId()));						
			obj.setName(Common.get(c.getCon1014Db().getName())); //流程角色名稱
			
			obj.setCommonUser(Common.get(c.getCommonUser().getId()));//塞入commonUserID
			
			obj.setUsernameid(Common.get(c.getCommonUser().getUserId()));	//聯絡人ID
			
			
			
			if(!"".equals(Common.get(c.getCompetence())))
	    		this.competence = c.getCompetence().split(",");
	    	else
	    		this.competence = null;
			
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Con1015Db where 1 = 1 ";
			   hql += " and con1014Db.id = " + getId();
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				
				//使用map先將資料撈出來
				java.util.Map<String, String> sysKindMap = TCBWCommon.getCommonCodeMap("SYS");
				
				for(Object dtlObj : objList) {				
					Con1015Db dtl = (Con1015Db)dtlObj;
					String[] rowArray = new String[6];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getCon1014Db().getSysKind());
					rowArray[2] = Common.get(dtl.getCon1014Db().getCon1007Db().getFormdName());
					rowArray[3] = Common.get(dtl.getCon1014Db().getName());
					rowArray[4] = Common.get(dtl.getCommonUser().getUserId());
					rowArray[5] = Common.get(dtl.getCompetence());
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

		Con1015Db obj = new Con1015Db();	    
	    
	    Con1014Db con1014db = (Con1014Db)View.getObject(" from Con1014Db where id = " + Common.getLong(getId()));
	    
	    obj.setCon1014Db(con1014db);			
	    
	    CommonUser user = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getCommonUser()));
	    obj.setCommonUser(user);
  
		obj.setId(Common.getLong(getId2()));
	    
		if(getCompetence()!=null && getCompetence().length>0){
			StringBuffer s = new StringBuffer();
			for(String idx : getCompetence()){
				if(s.toString().length() > 0)	s.append(",");
				s.append(idx);
			}
			if(s.toString().length() > 0){
				obj.setCompetence(s.toString());
			}
		}

	    obj.setCreator(getEditID());
	    obj.setCreateDate(getEditDate());
	    obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId2(Common.get(obj.getId()));
		
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
		Con1015Db obj = (Con1015Db)View.getObject(" from Con1015Db where id = " + Common.getLong(getId2()));
		if(obj != null){		
			Con1007Db con1007db = new Con1007Db();
		    con1007db.setId(Common.getLong(getCon1007_id()));
		    
		    Con1014Db con1014db = (Con1014Db)View.getObject(" from Con1014Db where id = " + Common.getLong(getId()));
		    obj.setCon1014Db(con1014db);	

		    CommonUser user = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getCommonUser()));
		    obj.setCommonUser(user);
		    		    
			if(getCompetence()!=null && getCompetence().length>0){
				StringBuffer s = new StringBuffer();
				for(String idx : getCompetence()){
					if(s.toString().length() > 0)	s.append(",");
					s.append(idx);
				}
				if(s.toString().length() > 0){
					obj.setCompetence(s.toString());
				}
			}

		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId2(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Con1015Db obj = (Con1015Db)View.getObject(" from Con1015Db where id = " + Common.getLong(getId2()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId2("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
