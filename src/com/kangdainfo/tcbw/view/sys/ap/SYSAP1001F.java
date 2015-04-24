package com.kangdainfo.tcbw.view.sys.ap;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Sys0001Db;

public class SYSAP1001F extends SuperBean{

	private String id;
	private String field1;
	private String field2;
	private String field3;
	private String field4;
	private String field5;
	private String field6;
	private String field7;
	private String field8;
	private String field9;
	private String field10;
	private String field11;
	private String field12;
	
	public String getId() {	return checkGet(id);	}
	public void setId(String id) {this.id = checkSet(id);	}
	public String getField1(){ return checkGet(field1); }
	public void setField1(String s){ field1=checkSet(s); }
	public String getField2(){ return checkGet(field2); }
	public void setField2(String s){ field2=checkSet(s); }
	public String getField3(){ return checkGet(field3); }
	public void setField3(String s){ field3=checkSet(s); }
	public String getField4(){ return checkGet(field4); }
	public void setField4(String s){ field4=checkSet(s); }
	public String getField5(){ return checkGet(field5); }
	public void setField5(String s){ field5=checkSet(s); }
	public String getField6(){ return checkGet(field6); }
	public void setField6(String s){ field6=checkSet(s); }
	public String getField7(){ return checkGet(field7); }
	public void setField7(String s){ field7=checkSet(s); }
	public String getField8(){ return checkGet(field8); }
	public void setField8(String s){ field8=checkSet(s); }
	public String getField9(){ return checkGet(field9); }
	public void setField9(String s){ field9=checkSet(s); }
	public String getField10(){ return checkGet(field10); }
	public void setField10(String s){ field10=checkSet(s); }
	public String getField11(){ return checkGet(field11); }
	public void setField11(String s){ field11=checkSet(s); }
	public String getField12(){ return checkGet(field12); }
	public void setField12(String s){ field12=checkSet(s); }
	
	private String q_id;
	public String getQ_id() {return checkGet(q_id);	}
	public void setQ_id(String s) {q_id = checkSet(s);	}
	
	
	@Override
	public Object doQueryOne() throws Exception {
		SYSAP1001F obj = this;
		Sys0001Db c = (Sys0001Db) View.getObject(" from Sys0001Db where id = " + Common.sqlChar(getId()));
		if (c != null) {
	        obj.setId(c.getId());
	        obj.setField1(c.getField1());
	        obj.setField2(c.getField2());
	        obj.setField3(c.getField3());
	        obj.setField4(c.getField4());
	        obj.setField5(c.getField5());
	        obj.setField6(c.getField6());
	        obj.setField7(c.getField7());
	        obj.setField8(c.getField8());
	        obj.setField9(c.getField9());
	        obj.setField10(c.getField10());
	        obj.setField11(c.getField11());
	        obj.setField12(c.getField12());
	        obj.setEditID(c.getUserId());
	        obj.setEditDate(c.getModDate());
	        obj.setEditTime(c.getModTime());
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doCreate() throws Exception {
		Sys0001Db obj = new Sys0001Db();
		obj.setId(getId());
		obj.setField1(getField1());
		obj.setField2(getField2());
		obj.setField3(getField3());
		obj.setField4(getField4());
		obj.setField5(getField5());
		obj.setField6(getField6());
		obj.setField7(getField7());
		obj.setField8(getField8());
		obj.setField9(getField9());
		obj.setField10(getField10());
		obj.setField11(getField11());
		obj.setField12(getField12());
		CommonUser u = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if (u != null){
			obj.setUserId(u.getUserId());
		}else{
			obj.setUserId("SYS");
		}
		obj.setModDate(getEditDate());
		obj.setModTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
	}

	@Override
	public void doUpdate() throws Exception {
		Sys0001Db obj = (Sys0001Db) View.getObject(" from Sys0001Db where id = " + Common.sqlChar(getId()));
		if(obj != null){
			obj.setField1(getField1());
			obj.setField2(getField2());
			obj.setField3(getField3());
			obj.setField4(getField4());
			obj.setField5(getField5());
			obj.setField6(getField6());
			obj.setField7(getField7());
			obj.setField8(getField8());
			obj.setField9(getField9());
			obj.setField10(getField10());
			obj.setField11(getField11());
			obj.setField12(getField12());
			
			CommonUser u = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
			if (u != null){
				obj.setUserId(u.getUserId());
			}else{
				obj.setUserId("SYS");
			}
			obj.setModDate(getEditDate());
			obj.setModTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
		}else{
			doCreate();
		}
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
