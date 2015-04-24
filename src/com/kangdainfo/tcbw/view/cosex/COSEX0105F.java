package com.kangdainfo.tcbw.view.cosex;

import java.sql.ResultSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;

public class COSEX0105F extends SuperBean{
	
	private String field1;
	private String field2;
	
	public String getField1() {		return checkGet(field1);	}
	public void setField1(String field1) {		this.field1 = checkSet(field1);	}
	public String getField2() {		return checkGet(field2);	}
	public void setField2(String field2) {		this.field2 = checkSet(field2);	}

	private String q_permitKey;	
	private String q_permitNo;
	private String q_name;
	
	public String getQ_permitKey() {		return checkGet(q_permitKey);	}
	public void setQ_permitKey(String q_permitKey) {		this.q_permitKey = checkSet(q_permitKey);	}
	public String getQ_permitNo() {		return checkGet(q_permitNo);	}
	public void setQ_permitNo(String q_permitNo) {		this.q_permitNo = checkSet(q_permitNo);	}
	public String getQ_name() {		return checkGet(q_name);	}
	public void setQ_name(String q_name) {		this.q_name = checkSet(q_name);	}

	public String getDefultPermitKeyOption(){
        StringBuffer sb = new StringBuffer();
        java.util.List<String> objList = ServiceGetter.getInstance().getTcbwService().load("select codeId from CommonCode where isStop <> 'Y' and commonCodeKind.codeKindId = 'CPT' ");
        if(objList!=null && objList.size()>0){
        	for(String rid : objList){
        		if(!"".equals(Common.get(rid))){
        			if(sb.toString().length() > 0){
        				sb.append(",");
        			}
        			sb.append(Common.get(rid));
        		}
        	}
        }
        return sb.toString();
    }   
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		
		String defaultKey = getDefultPermitKeyOption();
		Database db = new Database("MLMS");
		try{
			String sql = " select a.LICEKID, a.LICID1, a.LICEKC, a.CHNAME, a.ENNAME, a.EFDATE, a.APPNAME, a.FACTNAME, a.FACTCIDMA "+
			 			 " from VW_ForADR_TBMLIC a where 1 = 1 ";
			if(!"".equals(getQ_permitKey())){
				sql += " and a.LICEKID = ? ";
				parameter.add(getQ_permitKey());
			}
			if(!"".equals(getQ_permitNo())){
				sql += " and a.LICID1 = ? ";
				parameter.add(getQ_permitNo());
			}else{
				if(!"".equals(Common.get(defaultKey))){
					String[] tmp = Common.get(defaultKey).split(",");
					if(tmp!=null && tmp.length>0){
						StringBuffer sb = new StringBuffer();
						for(String rid : tmp){
							if(sb.toString().length() > 0){
								sb.append(",");
							}
							sb.append("?");
							parameter.add(rid);
						}
						if(sb.toString().length() > 0){
							sql += " and a.LICEKID in (" + sb.toString() + ")";
						}
					}
				}
			}
			if(!"".equals(getQ_name())){
				sql += " and a.CHNAME like ? ";
				parameter.add("%" + getQ_name() + "%");
			}
			
			sql += " order by a.LICEKID, a.LICID1 ";
			System.out.println("[TCBW]-[COSEX0105F]-[QUERYALL] : " + sql);
			
			ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
			processCurrentPageAttribute(rs);
			if(getTotalRecord() > 0){
				int count = getRecordStart();
				int end = getRecordEnd();
				do{
					if(count > end){
						break;
					}
					String rowArray[] = new String[9];
					rowArray[0] = Common.get(rs.getString("LICEKID"));							// 許可證字
					rowArray[1] = Common.get(rs.getString("LICID1"));							// 許可證號
					rowArray[2] = Common.get(rs.getString("LICEKC")) + rowArray[1];
					rowArray[3] = Common.get(rs.getString("CHNAME"));
					rowArray[4] = Common.get(rs.getString("ENNAME"));
					rowArray[5] = Common.get(rs.getString("EFDATE"));
					rowArray[6] = Common.get(rs.getString("APPNAME"));
					rowArray[7] = Common.get(rs.getString("FACTNAME"));
					rowArray[8] = Common.get(rs.getString("FACTCIDMA"));
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
