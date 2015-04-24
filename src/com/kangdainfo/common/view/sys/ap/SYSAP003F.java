package com.kangdainfo.common.view.sys.ap;

import com.kangdainfo.ServiceGetter;

public class SYSAP003F extends SYSAP002F {
	String sysID;
	String[] auth;
	String[] authed;
	String empID;	
	String optype;
	String hideAssigned;	
	String authoritytype;	
	
	public String getSysID() { return checkGet(sysID); }
	public void setSysID(String s) { sysID = checkSet(s); }
	
	public String getHideAssigned() {return hideAssigned;}
	public void setHideAssigned(String s) {hideAssigned = s; }	
	public void setAuth(String[] s) {auth = s;}
	public String[] getAuth() {return auth;}
	
	public void setAuthed(String[] s) {authed = s;}
	public String[] getAuthed() {return authed;}	
	
	public void setEmpID(String s) { empID = checkSet(s); }
	public String getEmpID() { return checkGet(empID); }
	
	public String getOptype() {return optype;}
	public void setOptype(String s) {optype = s;}	
	public String getAuthoritytype() {return checkGet(authoritytype);}
	public void setAuthoritytype(String s) {authoritytype = checkSet(s);}		
	
	public void updateAuth() {		
		try {
			if (optype!=null && optype.equals("add")) {			
				ServiceGetter.getInstance().getCommonService().getCommonAuthDao().updateAuth(optype, getId(), auth, getAuthoritytype(), this.getEditID());
				this.setErrorMsg("新增/修改權限成功");
			}else if(optype!=null && optype.equals("remove")) {
				ServiceGetter.getInstance().getCommonService().getCommonAuthDao().updateAuth(optype, getId(), authed, getAuthoritytype(), this.getEditID());
				this.setErrorMsg("刪除權限成功");				
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorMsg("更新失敗..");
		}
	}	
	
}
