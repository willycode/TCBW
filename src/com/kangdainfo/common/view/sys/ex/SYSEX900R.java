package com.kangdainfo.common.view.sys.ex;

import javax.swing.table.DefaultTableModel;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.report.SQLReportEnvironment;
import com.kangdainfo.persistence.PersistenceServiceGetter;

public class SYSEX900R extends SuperBean{
	
	String pagginPrefix;
	String isPrintPageNumber;
	public String getPagginPrefix() {return checkGet(pagginPrefix);	}
	public void setPagginPrefix(String s) {this.pagginPrefix = checkSet(s);	}	
	public String getIsPrintPageNumber() { return checkGet(isPrintPageNumber); }
	public void setIsPrintPageNumber(String s) { isPrintPageNumber = checkSet(s); }	

	String q_codeKindID;
	String q_id;
	String q_codeId;
	String q_codeName;
	String q_codeShortName;
	String q_codeEngName;
	String q_codeCon1;
	String q_codeCon2;
	String q_codeCon3;	
	String q_preWord;
	public String getQ_id(){ return checkGet(q_id); }
	public void setQ_id(String s){ q_id=checkSet(s); }	
	public String getQ_codeKindID(){ return checkGet(q_codeKindID); }
	public void setQ_codeKindID(String s){ q_codeKindID=checkSet(s); }
	public String getQ_codeId(){ return checkGet(q_codeId); }
	public void setQ_codeId(String s){ q_codeId=checkSet(s); }
	public String getQ_codeName(){ return checkGet(q_codeName); }
	public void setQ_codeName(String s){ q_codeName=checkSet(s); }	
	public String getQ_codeEngName(){ return checkGet(q_codeEngName); }
	public void setQ_codeEngName(String s){ q_codeEngName=checkSet(s); }	
	public String getQ_codeShortName() {return checkGet(q_codeShortName);	}
	public void setQ_codeShortName(String s) {q_codeShortName = checkSet(s);}
	public String getQ_codeCon1() {return checkGet(q_codeCon1);}
	public void setQ_codeCon1(String s) {q_codeCon1 = checkSet(s);	}
	public String getQ_codeCon2() {	return checkGet(q_codeCon2);	}
	public void setQ_codeCon2(String s) {q_codeCon2 = checkSet(s);	}
	public String getQ_codeCon3() {	return checkGet(q_codeCon3);	}
	public void setQ_codeCon3(String s) { q_codeCon3 = checkSet(s);	}
	public String getQ_preWord() {return checkGet(q_preWord);	}
	public void setQ_preWord(String s) {this.q_preWord = checkSet(s);	}
	
	
	public void setSQLReportEnvironment(SQLReportEnvironment env, java.util.HashMap<String, Object> parms) throws Exception {
		if (env!=null) {
			StringBuilder sb = new StringBuilder(500).append("SELECT a.CODEKINDID, a.CODEKINDNAME, b.CODEID, b.CODENAME, b.CODEENGNAME FROM COMMON_CODEKIND a, COMMON_CODE b where a.id=b.codeKindId");
			if (!"".equals(getQ_id()))
				sb.append(" and a.id=").append(getQ_id());					
			if (!"".equals(getQ_codeKindID()))
				sb.append(" and a.codeKindId=").append(Common.sqlChar(getQ_codeKindID()));		
			if (!"".equals(getQ_codeId()))
				sb.append(" and b.codeId like ").append(Common.sqlChar(getQ_codeId()));			
			if (!"".equals(getQ_codeName()))
				sb.append(" and b.codeName like ").append(Common.sqlChar("%"+getQ_codeName()+"%"));		
			if (!"".equals(getQ_codeEngName()))
				sb.append(" and b.codeEngName like ").append(Common.sqlChar("%"+getQ_codeEngName()+"%"));		
			if (!"".equals(getQ_codeShortName()))
				sb.append(" and b.codeShortName like ").append(Common.sqlChar("%"+getQ_codeShortName()+"%"));
			if (!"".equals(getQ_preWord()))
				sb.append(" and b.codeId like ").append(Common.sqlChar(getQ_preWord()+"%"));					
			if (!"".equals(getQ_codeCon1()))
				sb.append(" and b.codeCon1 like ").append(Common.sqlChar(getQ_codeCon1()+"%"));
			if (!"".equals(getQ_codeCon2()))
				sb.append(" and b.codeCon2 like ").append(Common.sqlChar(getQ_codeCon2()+"%"));
			if (!"".equals(getQ_codeCon3()))
				sb.append(" and b.codeCon3 like ").append(Common.sqlChar(getQ_codeCon3()+"%"));			
			sb.append(" ORDER BY a.CODEKINDID, b.CODEID ");
			env.setConnection(PersistenceServiceGetter.getInstance().getDataSource().getConnection());
			env.setUseDataSource(true);
			env.setDataSourceSQL(sb.toString());			
		}
	}
	
	
	public DefaultTableModel getDefaultTableModel() throws Exception {
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[] {"CODEKINDID","CODEKINDNAME","CODEID","CODENAME","CODEENGNAME"};		
    	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
    	
		StringBuilder sb = new StringBuilder(300).append("from CommonCode where 1=1 ");			
		if (!"".equals(getQ_id()))
			sb.append(" and commonCodeKind.id=").append(getQ_id());					
		if (!"".equals(getQ_codeKindID()))
			sb.append(" and commonCodeKind.codeKindId=").append(Common.sqlChar(getQ_codeKindID()));		
		if (!"".equals(getQ_codeId()))
			sb.append(" and codeId like ").append(Common.sqlChar(getQ_codeId()));			
		if (!"".equals(getQ_codeName()))
			sb.append(" and codeName like ").append(Common.sqlChar("%"+getQ_codeName()+"%"));		
		if (!"".equals(getQ_codeEngName()))
			sb.append(" and codeEngName like ").append(Common.sqlChar("%"+getQ_codeEngName()+"%"));		
		if (!"".equals(getQ_codeShortName()))
			sb.append(" and codeShortName like ").append(Common.sqlChar("%"+getQ_codeShortName()+"%"));
		if (!"".equals(getQ_preWord()))
			sb.append(" and codeId like ").append(Common.sqlChar(getQ_preWord()+"%"));					
		if (!"".equals(getQ_codeCon1()))
			sb.append(" and codeCon1 like ").append(Common.sqlChar(getQ_codeCon1()+"%"));
		if (!"".equals(getQ_codeCon2()))
			sb.append(" and codeCon2 like ").append(Common.sqlChar(getQ_codeCon2()+"%"));
		if (!"".equals(getQ_codeCon3()))
			sb.append(" and codeCon3 like ").append(Common.sqlChar(getQ_codeCon3()+"%"));			
		sb.append(" order by commonCodeKind.codeKindId, codeId");
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(sb.toString());
		
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String rowArray[]=new String[cols.length];
				rowArray[0]=Common.get(obj.getCommonCodeKind().getCodeKindId()); 
				rowArray[1]=Common.get(obj.getCommonCodeKind().getCodeKindName()); 
				rowArray[2]=Common.get(obj.getCodeId()); 
				rowArray[3]=Common.get(obj.getCodeName()); 
				rowArray[4]=Common.get(obj.getCodeEngName());
				arrList.add(rowArray);
			}
		}		
		if (arrList!=null && arrList.size()>0) {
			String[][] rs = new String[0][0];
			rs = (String[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}			
		return model;    	
	}	
	
	
	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		
	}

	@Override
	public Object doQueryAll() throws Exception {
		return null;
	}

	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public void doUpdate() throws Exception {
		
	}
}