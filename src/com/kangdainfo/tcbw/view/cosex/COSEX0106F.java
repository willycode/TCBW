package com.kangdainfo.tcbw.view.cosex;

import java.sql.ResultSet;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;

public class COSEX0106F extends SuperBean{
	
	private String field1;
	
	public String getField1() {		return checkGet(field1);	}
	public void setField1(String field1) {		this.field1 = checkSet(field1);	}
	
	private String q_caseNo;
	private String q_name;
	
	public String getQ_caseNo() {		return checkGet(q_caseNo);	}
	public void setQ_caseNo(String q_caseNo) {		this.q_caseNo = checkSet(q_caseNo);	}
	public String getQ_name() {		return checkGet(q_name);	}
	public void setQ_name(String q_name) {		this.q_name = checkSet(q_name);	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		
		Database db = new Database("COS");
		try{
			String sql = " select a.CASENO, a.PRODCNAME, a.PRODENAME, a.ENDDATE, a.COMPNAME, a.LICENSENO "+
			 			 " from COM4001_VIEW a where 1 = 1 ";
			if(!"".equals(getQ_caseNo())){
				sql += " and a.CASENO = ? ";
				parameter.add(getQ_caseNo());
			}
			if(!"".equals(getQ_name())){
				sql += " and a.PRODCNAME like ? ";
				parameter.add("%" + getQ_name() + "%");
			}
			sql += " order by a.CASENO ";
			System.out.println("[TCBW]-[COSEX0106F]-[QUERYALL] : " + sql);
			
			ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
			processCurrentPageAttribute(rs);
			if(getTotalRecord() > 0){
				int count = getRecordStart();
				int end = getRecordEnd();
				do{
					if(count > end){
						break;
					}
					String rowArray[] = new String[6];
					rowArray[0] = Common.get(rs.getString("CASENO"));							
					rowArray[1] = Common.get(rs.getString("PRODCNAME"));							
					rowArray[2] = Common.get(rs.getString("PRODENAME"));
					rowArray[3] = Common.get(rs.getString("ENDDATE"));
					rowArray[4] = Common.get(rs.getString("COMPNAME"));
					rowArray[5] = Common.get(rs.getString("LICENSENO"));
					arrList.add(rowArray);
					
					count++;
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
		return arrList;
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
