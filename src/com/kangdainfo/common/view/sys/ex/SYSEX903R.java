package com.kangdainfo.common.view.sys.ex;

import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.persistence.PersistenceServiceGetter;

public class SYSEX903R extends SuperBean {
	String pagginPrefix;
	String isPrintPageNumber;
	public String getPagginPrefix() {return checkGet(pagginPrefix);	}
	public void setPagginPrefix(String s) {this.pagginPrefix = checkSet(s);	}	
	public String getIsPrintPageNumber() { return checkGet(isPrintPageNumber); }
	public void setIsPrintPageNumber(String s) { isPrintPageNumber = checkSet(s); }	

	public javax.swing.table.DefaultTableModel genModel(java.util.List<String[]> arrList) {
		String[] column = {"CODEKINDID","CODEKINDNAME","CODEID","CODENAME","CODEENGNAME"};
		DefaultTableModel model = new DefaultTableModel();
		if (arrList!=null && arrList.size()>0) {
			String[][] rs = new String[0][0];
			rs = (String[][])arrList.toArray(rs);
			model.setDataVector(rs, column);
		}		
		return model;
	}
	
	
	public javax.swing.table.DefaultTableModel getReportTableModel() throws Exception {
		java.util.List<String[]> arrList = new java.util.ArrayList<String[]>();
		Database db = new Database(PersistenceServiceGetter.getInstance().getJdbcTemplate().getDataSource().getConnection());
		try {
			String sql = "select a.CODEKINDID, a.CODEKINDNAME, b.CODEID, b.CODENAME, b.CODEENGNAME from common_codekind a, common_code b" +
					" where a.id=b.codeKindid " +
					" order by a.codeKindId, b.codeId";
			ResultSet rs = db.querySQL(sql, false);
			int i=0;
			while (rs.next()) {
				String[] r = new String[5];
				for (i=0; i<r.length; i++) {
					r[i] = Common.get(rs.getString(i+1));
				}
				arrList.add(r);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}		
		return genModel(arrList);
	}
	
	public javax.swing.table.DefaultTableModel getDefaultTableModel() throws Exception {
		java.util.List<String[]> arrList = new java.util.ArrayList<String[]>();
		java.util.List<CommonCode> list = ServiceGetter.getInstance().getCommonService().loadAll(CommonCode.class);
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				CommonCode obj = list.get(i);
				String[] r = new String[5];
				r[0] = Common.get(obj.getCommonCodeKind().getCodeKindId());
				r[1] = Common.get(obj.getCommonCodeKind().getCodeKindName());
				r[2] = Common.get(obj.getCodeId());
				r[3] = Common.get(obj.getCodeName());
				r[4] = Common.get(obj.getCodeEngName());
				arrList.add(r);
			}
		}		
		return genModel(arrList);
	}	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
