package com.kangdainfo.tcbw.view.hfrex;

import java.sql.ResultSet;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;

public class HFREX0106F extends SuperBean{
	
	private String field1;
	private String field2;
	private String field3;
	private String field4;
	private String field5;
	
	public String getField1() {		return checkGet(field1);	}
	public void setField1(String field1) {		this.field1 = checkSet(field1);	}
	public String getField2() {		return checkGet(field2);	}
	public void setField2(String field2) {		this.field2 = checkSet(field2);	}
	public String getField3() {		return checkGet(field3);	}
	public void setField3(String field3) {		this.field3 = checkSet(field3);	}
	public String getField4() {		return checkGet(field4);	}
	public void setField4(String field4) {		this.field4 = checkSet(field4);	}
	public String getField5() {		return checkGet(field5);	}
	public void setField5(String field5) {		this.field5 = checkSet(field5);	}

	private String q_permitKey;	
	private String q_permitNo;
	private String q_name;
	
	public String getQ_permitKey() {		return checkGet(q_permitKey);	}
	public void setQ_permitKey(String q_permitKey) {		this.q_permitKey = checkSet(q_permitKey);	}
	public String getQ_permitNo() {		return checkGet(q_permitNo);	}
	public void setQ_permitNo(String q_permitNo) {		this.q_permitNo = checkSet(q_permitNo);	}
	public String getQ_name() {		return checkGet(q_name);	}
	public void setQ_name(String q_name) {		this.q_name = checkSet(q_name);	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.List<String[]> arrList = new java.util.ArrayList<String[]>();
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		
		Database db = new Database("FLM");
		try{
			String sql = " select a.AD_LICENSE_NO, a.AD_LICENSE_TYPE_CONTENT, a.CHINE_PRODUCT_NAME, a.ENGLH_PRODUCT_NAME, " +
						 " a.APPLY_MNFTY_NAME, a.DOCUT_NO, b.MNFTY_NAME1 " +
			 			 " from QMS_view_HEAL_BAS a, QMS_view_HEAL_MNFTY b where a.DOCUT_NO = b.DOCUT_NO ";
			if(!"".equals(getQ_permitKey()) && !"".equals(getQ_permitNo())){
				sql += " and a.AD_LICENSE_NO = ? ";
				parameter.add(getQ_permitKey() + getQ_permitNo());
			}else if(!"".equals(getQ_permitKey()) && "".equals(getQ_permitNo())){
				sql += " and substring(a.AD_LICENSE_NO,1,1) = ? ";
				parameter.add(getQ_permitKey());
			}else if("".equals(getQ_permitKey()) && !"".equals(getQ_permitNo())){
				sql += " and a.AD_LICENSE_NO like ? ";
				parameter.add("%" + getQ_permitNo());
			}
			if(!"".equals(getQ_name())){
				sql += " and a.CHINE_PRODUCT_NAME like ? ";
				parameter.add("%" + getQ_name() + "%");
			}
			sql += " order by a.AD_LICENSE_NO ";
			System.out.println("[TCBW]-[HFREX0106F]-[QUERYALL] : " + sql);
			
		    ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
			processCurrentPageAttribute(rs);
			if(getTotalRecord() > 0){
				int count = getRecordStart();
				int end = getRecordEnd();
				do{
					if(count > end){
						break;
					}
					String rowArray[] = new String[8];
					
					String keyNo = Common.get(rs.getString("AD_LICENSE_NO"));
					rowArray[0] = keyNo.length()>0?keyNo.substring(0, 1):keyNo;
					rowArray[1] = keyNo.length()>0?keyNo.substring(1):keyNo;
					rowArray[2] = Common.get(rs.getString("AD_LICENSE_TYPE_CONTENT")) + rowArray[1];
					rowArray[3] = Common.get(rs.getString("CHINE_PRODUCT_NAME"));
					rowArray[4] = Common.get(rs.getString("ENGLH_PRODUCT_NAME"));
					rowArray[5] = Common.get(rs.getString("APPLY_MNFTY_NAME"));
					rowArray[6] = Common.get(rs.getString("MNFTY_NAME1"));
					rowArray[7] = "";
					
					java.util.List<Object> parameterDtl = new java.util.ArrayList<Object>();
					parameterDtl.add(Common.get(rs.getString("DOCUT_NO")));
					
					StringBuffer sb = new StringBuffer();
					String sqlDtl = "select FUNCN_COMPT_CONTENT from QMS_view_HEAL_Funcn_compt where DOCUT_NO = ? " ;
					ResultSet rsDtl = db.querySQLByPrepareStatement(sqlDtl, parameterDtl);
					while(rsDtl.next()){
						if(sb.toString().length() > 0){
							sb.append("、");
						}
						sb.append(Common.get(rsDtl.getString("FUNCN_COMPT_CONTENT")));
					}
					if(sb.toString().length() > 25){
						rowArray[7] = sb.toString().substring(0, 25);
					}else{
						rowArray[7] = sb.toString();
					}
					rsDtl.close();
					
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
