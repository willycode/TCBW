package com.kangdainfo.tcbw.view.hfrex;

import java.sql.ResultSet;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;

public class HFREX0107F extends SuperBean{
	
	private String field1;
	private String field2;
	private String field3;
	
	public String getField1() {		return checkGet(field1);	}
	public void setField1(String field1) {		this.field1 = checkSet(field1);	}
	public String getField2() {		return checkGet(field2);	}
	public void setField2(String field2) {		this.field2 = checkSet(field2);	}
	public String getField3() {		return checkGet(field3);	}
	public void setField3(String field3) {		this.field3 = checkSet(field3);	}
	
	private String q_name;
	
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
			String sql = " select a.DOCUT_NO, a.CHINE_PRODUCT_NAME, a.ENGLH_PRODUCT_NAME, a.APPLY_MNFTY_NAME, a.MNFTY_NAME " +
			 			 " from QMS_view_AR_BAS a where 1 = 1 ";
			
			if(!"".equals(getQ_name())){
				sql += " and a.CHINE_PRODUCT_NAME like ? ";
				parameter.add("%" + getQ_name() + "%");
			}
			sql += " order by a.DOCUT_NO ";
			System.out.println("[TCBW]-[HFREX0107F]-[QUERYALL] : " + sql);
			
		    ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
			processCurrentPageAttribute(rs);
			if(getTotalRecord() > 0){
				int count = getRecordStart();
				int end = getRecordEnd();
				do{
					if(count > end){
						break;
					}
					String rowArray[] = new String[5];
					rowArray[0] = Common.get(rs.getString("DOCUT_NO"));
					rowArray[1] = Common.get(rs.getString("CHINE_PRODUCT_NAME"));
					rowArray[2] = Common.get(rs.getString("ENGLH_PRODUCT_NAME"));
					rowArray[3] = Common.get(rs.getString("APPLY_MNFTY_NAME"));
					rowArray[4] = Common.get(rs.getString("MNFTY_NAME"));
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
	
	public String getQuerylist(boolean primaryArray[], boolean displayArray[], String[] arrAlign,
			java.util.ArrayList objList, String queryAllFlag, boolean withListNo, String noDataMsg, boolean checkHtml ) {
    	int i, counter=0;
    	boolean trFlag = false, even = false;
    	String pk = "";
    	
    	StringBuilder sb = new StringBuilder();
    	if (objList!=null && objList.size()>0) {
			String rowArray[]=new String[primaryArray.length];
			java.util.Iterator it=objList.iterator();
			
			String defaultKey = "null";
			for(i=0;i<primaryArray.length;i++){
				if (primaryArray[i]) defaultKey = "";
			}
			
			int j=0;
			while(it.hasNext()) {
				j++;
				rowArray= (String[])it.next();
				counter++;				
				String classTR="listTROdd", classTD = "listTDOdd";				
				if (even) {
					classTR = "listTREven";
					classTD = "listTDEven";
				}				
			
				pk = "";
				for(i=0;i<primaryArray.length;i++){			
					if (primaryArray[i]) pk+=Common.escapeReturnChar(rowArray[i]);
				}				
				StringBuilder v = new StringBuilder().append(defaultKey);
				for(i=0;i<primaryArray.length;i++){
					if (primaryArray[i]) {
						if (trFlag) {
							v.append(",'").append(Common.escapeReturnChar(rowArray[i])).append("'");
						} else {
							v.append("'").append(Common.escapeReturnChar(rowArray[i])).append("'");
							trFlag = true;
						}
					}
				}
				
				sb.append(" <tr id=\"").append("listContainerRow").append(pk).append("\"");
				sb.append(" class='").append(classTR).append("' onmouseover=\"this.className='listTRMouseover'\" onmouseout=\"this.className='").append(classTR).append("'\" onClick=\"listContainerRowClick('").append(pk).append("');queryOne(");
				sb.append(v);
				sb.append(");\" >\n");
				
				//顯示TD
				if (withListNo) sb.append(" <td nowrap class='").append(classTD).append("' >").append(counter).append(".</td>\n");			
				
				for(i=0;i<displayArray.length;i++){
					if (displayArray[i]) {
						if (arrAlign!=null && arrAlign.length>0) {
							sb.append(" <td class='").append(classTD).append("' style=\"text-align:").append(arrAlign[i]).append("\">"); //.append(Common.get(rowArray[i])).append("</td>\n");
						} else {
							sb.append(" <td class='").append(classTD).append("' >");
						}
						sb.append(checkHtml?Common.checkGet(rowArray[i]):Common.get(rowArray[i]));
						sb.append("</td>\n");
					}
				}
				sb.append("</tr>\n");
				trFlag = false;
				even = !even;
			}
    	} else {
    		if ("true".equals(queryAllFlag)) sb.append(" <tr class='highLight' ><td nowrap class='listTD' colspan='100'>").append("".equals(Common.get(noDataMsg))?"查無資料，請您重新輸入查詢條件！":noDataMsg).append("</td></tr>");
    	}
		return sb.toString();
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
