
package com.kangdainfo.tcbw.view.conex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONEX0301F extends SuperBean{

	private String q_isQuery;
	private String q_id;
	private String q_sysKind;	//系統別
	private String q_con1007_id;//表單別

	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}

	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String qId) {
		q_id = checkSet(qId);
	}
	
	public String getQ_con1007_id() {
		return checkGet(q_con1007_id);
	}

	public void setQ_con1007_id(String qCon1007Id) {
		q_con1007_id = checkSet(qCon1007Id);
	}
	
	public String getQ_sysKind() {
		return checkGet(q_sysKind);
	}

	public void setQ_sysKind(String qSysKind) {
		q_sysKind = checkSet(qSysKind);
	}

	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		return null;
	}

	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {

	}

	@Override
	public void doDelete() throws Exception {
		
	}

}
