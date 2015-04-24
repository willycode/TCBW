package com.kangdainfo.tcbw.view.cosin;

import java.sql.ResultSet;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;

public class COSIN0405F extends SuperBean{
	
	private String q_caseNo;
	
	public String getQ_caseNo() {		return checkGet(q_caseNo);	}
	public void setQ_caseNo(String q_caseNo) {		this.q_caseNo = checkSet(q_caseNo);	}

	private String CASENO;
	private String FINISHDATE;
	private String ENDDATE;
	private String LICENSENO;
	private String PRODCNAME;
	private String PRODENAME;
	private String COMPNAME;
	private String COMPADDRESS;
	private String COMPTEL;
	
	public String getCASENO() {		return checkGet(CASENO);	}
	public void setCASENO(String cASENO) {		CASENO = checkSet(cASENO);	}
	public String getFINISHDATE() {		return checkGet(FINISHDATE);	}
	public void setFINISHDATE(String fINISHDATE) {		FINISHDATE = checkSet(fINISHDATE);	}
	public String getENDDATE() {		return checkGet(ENDDATE);	}
	public void setENDDATE(String eNDDATE) {		ENDDATE = checkSet(eNDDATE);	}
	public String getLICENSENO() {		return checkGet(LICENSENO);	}
	public void setLICENSENO(String lICENSENO) {		LICENSENO = checkSet(lICENSENO);	}
	public String getPRODCNAME() {		return checkGet(PRODCNAME);	}
	public void setPRODCNAME(String pRODCNAME) {		PRODCNAME = checkSet(pRODCNAME);	}
	public String getPRODENAME() {		return checkGet(PRODENAME);	}
	public void setPRODENAME(String pRODENAME) {		PRODENAME = checkSet(pRODENAME);	}
	public String getCOMPNAME() {		return checkGet(COMPNAME);	}
	public void setCOMPNAME(String cOMPNAME) {		COMPNAME = checkSet(cOMPNAME);	}
	public String getCOMPADDRESS() {		return checkGet(COMPADDRESS);	}
	public void setCOMPADDRESS(String cOMPADDRESS) {		COMPADDRESS = checkSet(cOMPADDRESS);	}
	public String getCOMPTEL() {		return checkGet(COMPTEL);	}
	public void setCOMPTEL(String cOMPTEL) {		COMPTEL = checkSet(cOMPTEL);	}

	@Override
	public Object doQueryOne() throws Exception {
		COSIN0405F obj = this;
		Database db = new Database("COS");
		try{
			java.util.List<Object> parameter = new java.util.ArrayList<Object>();
			
			String sql = " select a.CASENO, a.FINISHDATE, a.ENDDATE, a.LICENSENO, a.PRODCNAME, a.PRODENAME, a.COMPNAME, a.COMPADDRESS, a.COMPTEL " +
						 " from COM4001_VIEW a where 1 = 1 ";
			if(!"".equals(getQ_caseNo())){
				sql += " and a.CASENO = ? ";
				parameter.add(getQ_caseNo());
			}
			
			ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
			if(rs.next()){
				obj.setCASENO(Common.get(rs.getString("CASENO")));
				obj.setFINISHDATE(rs.getString("FINISHDATE"));
				obj.setENDDATE(rs.getString("ENDDATE"));
				obj.setLICENSENO(rs.getString("LICENSENO"));
				obj.setPRODCNAME(rs.getString("PRODCNAME"));
				obj.setPRODENAME(rs.getString("PRODENAME"));
				obj.setCOMPNAME(rs.getString("COMPNAME"));
				obj.setCOMPADDRESS(rs.getString("COMPADDRESS"));
				obj.setCOMPTEL(rs.getString("COMPTEL"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.closeAll();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
