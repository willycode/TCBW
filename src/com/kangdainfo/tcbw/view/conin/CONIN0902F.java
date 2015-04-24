package com.kangdainfo.tcbw.view.conin;

import com.kangdainfo.ServiceGetter;

public class CONIN0902F extends CONIN0201F{
	
	private String sysID;
	private String empID;	
	private String optype;
	private String hideAssigned;	
	private String authoritytype;	
	
	public String getSysID() { return checkGet(sysID); }
	public void setSysID(String s) { sysID = checkSet(s); }
	public void setEmpID(String s) { empID = checkSet(s); }
	public String getEmpID() { return checkGet(empID); }
	public String getOptype() {return optype;}
	public void setOptype(String s) {optype = s;}
	public String getHideAssigned() {return hideAssigned;}
	public void setHideAssigned(String s) {hideAssigned = s; }
	public String getAuthoritytype() {return checkGet(authoritytype);}
	public void setAuthoritytype(String s) {authoritytype = checkSet(s);}
	
	String[] auth;
	String[] authed;
	
	public void setAuth(String[] s) {auth = s;}
	public String[] getAuth() {return auth;}
	public void setAuthed(String[] s) {authed = s;}
	public String[] getAuthed() {return authed;}	
	
	public void updateAuth() {
		try {
			if ("add".equals(getOptype())) {			
				ServiceGetter.getInstance().getCommonService().getCommonUserDao().updateUserGroup(getOptype(), getId(), auth, getAuthoritytype(), getEditID());
				setErrorMsg("新增/修改權限成功");
			} else if("remove".equals(getOptype())) {
				ServiceGetter.getInstance().getCommonService().getCommonUserDao().updateUserGroup(getOptype(), getId(), authed, getAuthoritytype(), getEditID());
				this.setErrorMsg("刪除權限成功");				
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorMsg("更新失敗，出現非預期錯誤");
		}
	}	
	
}
