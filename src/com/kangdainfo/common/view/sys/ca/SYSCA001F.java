package com.kangdainfo.common.view.sys.ca;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonCodeKind;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.util.TCBWCommon;


/**
 * 共用代碼檔維護作業
 * 
 * TABLE = COMMON_CODE 
 * @author clive.chang 
 *
 */
public class SYSCA001F extends SuperBean {
	String id;
	String accountingYear;
	String codeKindID;
	String codeKindName;
	String codeID;
	String codeName;
	String codeShortName;
	String codeEngName;
	String codeCon1;
	String codeCon2;
	String codeCon3;
	String codeMaxLen;
	String memo;
	String systemType;
	String isStop;

	public String getSystemType() {
		return checkGet(systemType);
	}
	public void setSystemType(String systemType) {
		this.systemType = checkSet(systemType);
	}
	
	public String getIsStop() {
		return checkGet(isStop);
	}
	public void setIsStop(String isStop) {
		this.isStop = checkSet(isStop);
	}

	String q_accountingYear;
	String q_codeKindID;
	String q_id; //代碼類別的Primary key
	String oldCodeKindID;
	String q_codeId;
	String q_codeName;
	String q_codeShortName;
	String q_codeEngName;
	String q_codeCon1;
	String q_codeCon2;
	String q_codeCon3;	
	String q_preWord;
	String[] q_systemType;
	String q_isStop;
	
	public String[] getQ_systemType() {
		return q_systemType;
	}
	public void setQ_systemType(String[] qSystemType) {
		q_systemType = qSystemType;
	}
	public String getQ_isStop() {
		return checkGet(q_isStop);
	}
	public void setQ_isStop(String qIsStop) {
		q_isStop = checkSet(qIsStop);
	}
	public String getId(){ return checkGet(id); }
	public void setId(String s){ id=checkSet(s); }	
	public String getAccountingYear(){ return checkGet(accountingYear); }
	public void setAccountingYear(String s){ accountingYear=checkSet(s); }	
	public String getCodeKindID(){ return checkGet(codeKindID); }
	public void setCodeKindID(String s){ codeKindID=checkSet(s); }
	public String getOldCodeKindID(){ return checkGet(oldCodeKindID); }
	public void setOldCodeKindID(String s){ oldCodeKindID=checkSet(s); }	
	public String getCodeKindName(){ return checkGet(codeKindName); }
	public void setCodeKindName(String s){ codeKindName=checkSet(s); }		
	public String getCodeID(){ return checkGet(codeID); }
	public void setCodeID(String s){ codeID=checkSet(s); }
	public String getCodeName(){ return checkGet(codeName); }
	public void setCodeName(String s){ codeName=checkSet(s); }	
	public String getCodeShortName(){ return checkGet(codeShortName); }
	public void setCodeShortName(String s){ codeShortName=checkSet(s); }
	public String getCodeEngName(){ return checkGet(codeEngName); }	
	public void setCodeEngName(String s){ codeEngName=checkSet(s); }	
	public String getCodeCon1(){ return checkGet(codeCon1); }
	public void setCodeCon1(String s){ codeCon1=checkSet(s); }
	public String getCodeCon2(){ return checkGet(codeCon2); }
	public void setCodeCon2(String s){ codeCon2=checkSet(s); }
	public String getCodeCon3(){ return checkGet(codeCon3); }
	public void setCodeCon3(String s){ codeCon3=checkSet(s); }	
	public String getCodeMaxLen() {return checkGet(codeMaxLen);}
	public void setCodeMaxLen(String s) {this.codeMaxLen = checkSet(s);	}
	
	public String getMemo(){ return checkGet(memo); }
	public void setMemo(String s){ memo=checkSet(s); }

	public String getQ_accountingYear(){ return checkGet(q_accountingYear); }
	public void setQ_accountingYear(String s){ q_accountingYear=checkSet(s); }	
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

	String codeLabelCon1;
	String codeLabelCon2;
	String codeLabelCon3;
	String memoLabel;
	public String getCodeLabelCon1() {	return checkGet(codeLabelCon1);	}
	public void setCodeLabelCon1(String codeLabelCon1) {this.codeLabelCon1 = checkSet(codeLabelCon1);	}
	public String getCodeLabelCon2() {return checkGet(codeLabelCon2);	}
	public void setCodeLabelCon2(String codeLabelCon2) {this.codeLabelCon2 = checkSet(codeLabelCon2);	}
	public String getCodeLabelCon3() {return checkGet(codeLabelCon3);	}
	public void setCodeLabelCon3(String codeLabelCon3) {this.codeLabelCon3 = checkSet(codeLabelCon3);	}
	public String getMemoLabel() {return checkGet(memoLabel);	}
	public void setMemoLabel(String s) {this.memoLabel = checkSet(s);	}
	
	
	String issueDate, endDate;	
	public String getIssueDate() {return checkGet(issueDate);	}
	public void setIssueDate(String s) {this.issueDate = checkSet(s);	}
	public String getEndDate() {return checkGet(endDate);	}
	public void setEndDate(String s) {this.endDate = checkSet(s);	}
	
	
	String popId;
	String popCodeName;
	String popCode;
	String popKind;
	String js;
	String changeField;
	String conField;
	String engName;
	String preWord;
	String preCon1;
	String preCon2;
	String preCon3;	
	public String getPopId(){ return checkGet(popId); }
	public void setPopId(String s){ popId=checkSet(s); }
	public String getPopCodeName(){ return checkGet(popCodeName); }
	public void setPopCodeName(String s){ popCodeName=checkSet(s); }
	public String getPopCode(){ return checkGet(popCode); }
	public void setPopCode(String s){ popCode=checkSet(s); }
	public String getPopKind(){ return checkGet(popKind); }
	public void setPopKind(String s){ popKind=checkSet(s); }
	public String getJs(){ return checkGet(js); }
	public void setJs(String s){ js=checkSet(s); }
	public String getChangeField() {return checkGet(changeField);}
	public void setChangeField(String changeField) {this.changeField = checkSet(changeField);}
	public String getConField(){ return checkGet(conField); }
	public void setConField(String s){ conField=checkSet(s); }
	public String getEngName(){ return checkGet(engName); }
	public void setEngName(String s){ engName=checkSet(s); }
	public String getPreWord(){ return checkGet(preWord); }
	public void setPreWord(String s){ preWord=checkSet(s); }
	public String getPreCon1(){ return checkGet(preCon1); }
	public void setPreCon1(String s){ preCon1=checkSet(s); }
	public String getPreCon2(){ return checkGet(preCon2); }
	public void setPreCon2(String s){ preCon2=checkSet(s); }
	public String getPreCon3(){ return checkGet(preCon3); }
	public void setPreCon3(String s){ preCon3=checkSet(s); }
	
	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
	 	checkSQLArray[0][0]="select count(*) from CommonCode where commonCodeKind.id="+Common.getInt(getCodeKindID()) + " and codeId=" + Common.sqlChar(getCodeID());
		checkSQLArray[0][1]=">";
		checkSQLArray[0][2]="0";
		checkSQLArray[0][3]="該筆代碼已重複，請重新輸入！";
		return checkSQLArray;
	}	
	
	@Override
	public void doCreate() throws Exception {					
		CommonCodeKind parent = (CommonCodeKind) View.getObject(" from CommonCodeKind where id=" + Common.getInt(getCodeKindID()));
		if (parent!=null) {
			this.setCodeLabelCon1(parent.getCodeLabelCon1());
			this.setCodeLabelCon2(parent.getCodeLabelCon2());
			this.setCodeLabelCon3(parent.getCodeLabelCon3());	
			this.setMemoLabel(parent.getMemoLabel());
			this.setCodeMaxLen(String.valueOf(parent.getCodeMaxLen()));
			
			CommonCode obj = new CommonCode();		
			obj.setCommonCodeKind(parent);
			obj.setCodeId(getCodeID());
			obj.setCodeName(codeName);
			obj.setCodeShortName(codeShortName);
			obj.setCodeEngName(codeEngName);
			obj.setIssueDate(issueDate);
			obj.setEndDate(endDate);
			obj.setCodeCon1(codeCon1);
			obj.setCodeCon2(codeCon2);
			obj.setCodeCon3(codeCon3);
			obj.setCodeMemo(getMemo());
			obj.setEditId(getEditID());
			obj.setEditDate(Datetime.getYYYMMDD());
			obj.setEditTime(Datetime.getHHMMSS());
			
			obj.setIsCos("");
			obj.setIsDrg("");
			obj.setIsMed("");
			obj.setIsHfr("");

			
			obj.setIsStop(getIsStop());
			ServiceGetter.getInstance().getCommonService().getCommonCodeDao().saveCommonCode(obj);
			setId((obj.getId().toString()));			
		} else {
			throw new Exception("查無代碼類別物件，請查明!!");
		}
	}
	
	@Override
	public void doUpdate() throws Exception 
	{
		CommonCode obj = (CommonCode) View.getObject("from CommonCode where id=" + Common.getInt(getId()));
		obj.setCodeName(codeName);
		obj.setCodeShortName(codeShortName);
		obj.setCodeEngName(codeEngName);
		obj.setIssueDate(issueDate);
		obj.setEndDate(endDate);		
		obj.setCodeCon1(codeCon1);
		obj.setCodeCon2(codeCon2);
		obj.setCodeCon3(codeCon3);
		obj.setCodeMemo(getMemo());
		obj.setEditId(getEditID());
		obj.setEditDate(Datetime.getYYYMMDD());
		obj.setEditTime(Datetime.getHHMMSS());
		
		obj.setIsCos("");
		obj.setIsDrg("");
		obj.setIsMed("");
		obj.setIsHfr("");
	
		
		obj.setIsStop(getIsStop());
		ServiceGetter.getInstance().getCommonService().getCommonCodeDao().updateCommonCode(obj);
	}	

	protected String[][] getDeleteCheckSQL(){	
		CommonCode obj = (CommonCode) View.getObject("from CommonCode where id=" + Common.getInt(getId()));
		if (obj!=null && obj.getCommonCodeKind().getCodeKindId().equals("MK")) {
			String[][] checkSQLArray = new String[1][4];
		 	checkSQLArray[0][0]="select count(*) from Fee1101Db where currencyCode=" + Common.sqlChar(obj.getCodeId());
			checkSQLArray[0][1]=">";
			checkSQLArray[0][2]="0";
			checkSQLArray[0][3]="幣別代碼已被匯率檔使用，不允許刪除！";
			return checkSQLArray;			
		} else {
			return super.getDeleteCheckSQL();
		}
	}
	
	
	
	@Override
	public void doDelete() throws Exception {		
		ServiceGetter.getInstance().getCommonService().getCommonCodeDao().delete(CommonCode.class, Integer.parseInt(getId()));
	}


	@Override
	public SYSCA001F doQueryOne() throws Exception {	 
		SYSCA001F obj = this;		
		CommonCode c = (CommonCode)ServiceGetter.getInstance().getCommonService().getCommonCodeDao().load(CommonCode.class, Integer.parseInt(getId()));
		if (c!=null) {
			obj.setCodeKindID(c.getCommonCodeKind().getId().toString());
			obj.setCodeLabelCon1(c.getCommonCodeKind().getCodeLabelCon1());
			obj.setCodeLabelCon2(c.getCommonCodeKind().getCodeLabelCon2());
			obj.setCodeLabelCon3(c.getCommonCodeKind().getCodeLabelCon3());
			obj.setMemoLabel(c.getCommonCodeKind().getMemoLabel());
			obj.setCodeMaxLen(String.valueOf(c.getCommonCodeKind().getCodeMaxLen()));
			
			obj.setId(c.getId().toString());
			obj.setCodeID(c.getCodeId());
			obj.setCodeName(c.getCodeName());
			obj.setCodeShortName(c.getCodeShortName());
			obj.setCodeEngName(c.getCodeEngName());
			obj.setIssueDate(c.getIssueDate());
			obj.setEndDate(c.getEndDate());
			obj.setCodeCon1(c.getCodeCon1());
			obj.setCodeCon2(c.getCodeCon2());
			obj.setCodeCon3(c.getCodeCon3());
			obj.setMemo(c.getCodeMemo());
			obj.setEditID(c.getEditId());
			obj.setEditDate(c.getEditDate());
			obj.setEditTime(c.getEditTime());	
			obj.setSystemType(c.getCommonCodeKind().getSystemType());
			
			String selectedItem = "";
			selectedItem+=c.getCommonCodeKind().getSystemType();
			System.out.println("C: " + selectedItem);
//			if("Y".equals(c.getIsCos()))
//				selectedItem += "COS;";
//			if("Y".equals(c.getIsDrg()))
//				selectedItem += "DRG;";
//			if("Y".equals(c.getIsMed()))
//				selectedItem += "MED;";
//			if("Y".equals(c.getIsHfr()))
//				selectedItem += "HFR;";
	    	obj.setIsStop(c.getIsStop());
		} else throw new Exception("查無資料");		
		return obj;
	}
	
	
	@Override
	public java.util.ArrayList<String[]> doQueryAll() throws Exception 
	{		
		StringBuilder sb = new StringBuilder(300).append("from CommonCode where 1=1 ");
		
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		if (!"".equals(getQ_id()))
			sb.append(" and commonCodeKind.id=").append(getQ_id());					
		
		if (!"".equals(getQ_codeKindID()))
			sb.append(" and commonCodeKind.codeKindId=").append(Common.sqlChar(getQ_codeKindID()));		
		
		if (!"".equals(getQ_codeId()))
			sb.append(" and codeId like ").append(Common.sqlChar(getQ_codeId().toUpperCase()));			
		
		if (!"".equals(getQ_codeName()))
			sb.append(" and codeName like ").append(Common.sqlChar("%"+getQ_codeName()+"%"));		
		
		if (!"".equals(getQ_codeEngName()))
			sb.append(" and codeEngName like ").append(Common.sqlChar("%"+getQ_codeEngName()+"%"));		
		
		if (!"".equals(getQ_codeShortName()))
			sb.append(" and codeShortName like ").append(Common.sqlChar(getQ_codeShortName()));
		
		if (!"".equals(getQ_preWord()))
			sb.append(" and codeId like ").append(Common.sqlChar(getQ_preWord()+"%"));					
		if (!"".equals(getQ_codeCon1()))
			sb.append(" and codeCon1 like ").append(Common.sqlChar(getQ_codeCon1()+"%"));
		if (!"".equals(getQ_codeCon2()))
			sb.append(" and codeCon2 like ").append(Common.sqlChar(getQ_codeCon2()+"%"));
		if (!"".equals(getQ_codeCon3()))
			sb.append(" and codeCon3 like ").append(Common.sqlChar(getQ_codeCon3()+"%"));				
		if (!"".equals(getQ_isStop()))
			sb.append(" and isStop = ").append(Common.sqlChar(getQ_isStop()));
		
		
		if(getQ_systemType()!=null && getQ_systemType().length > 0){
			String h = "";
			for(String sys: getQ_systemType()){
				if(!"".equals(sys)) {
					h += Common.sqlChar(sys) + ",";
				}
			}
			if(h.length()>0) {
				h = h.substring(0,h.length() - 1);
				sb.append("and commonCodeKind.systemType in (" + h + ")");
			}
			
		}
		System.out.println(sb.toString());
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().getCommonCodeDao().loadCount(sb.toString()));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getHibernateTemplate().clear();			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().load(sb.toString(), this.getRecordStart(), this.getPageSize(), "codeId", true);		
			try {
				if (objList!=null && objList.size()>0) {
					java.util.Map<String, String> sysKindMap = TCBWCommon.getCommonCodeMap("SYS");						
					for (int i=0; i<objList.size(); i++) {
						CommonCode o = (CommonCode) objList.get(i);						
						if (i==0) {
							this.setCodeLabelCon1(o.getCommonCodeKind().getCodeLabelCon1());
							this.setCodeLabelCon2(o.getCommonCodeKind().getCodeLabelCon2());
							this.setCodeLabelCon3(o.getCommonCodeKind().getCodeLabelCon3());		
							this.setMemoLabel(o.getCommonCodeKind().getMemoLabel());
							this.setCodeMaxLen(String.valueOf(o.getCommonCodeKind().getCodeMaxLen()));
						}
						String[] rowArray=new String[14];
						rowArray[0]=o.getId().toString();
						rowArray[1]=o.getCommonCodeKind().getCodeKindName();
						rowArray[2]=o.getCodeId();
						rowArray[3]=Common.get(o.getCodeName());
						rowArray[4]=Common.get(o.getCodeEngName());
						rowArray[5]=Common.get(o.getCodeCon1());
						rowArray[6]=Common.get(o.getCodeCon2());
						rowArray[7]=Common.get(o.getCodeCon3());
						rowArray[8]=Common.get(o.getCodeMemo());
						rowArray[9]=Common.get(o.getCommonCodeKind().getSystemType()!=null?sysKindMap.get(o.getCommonCodeKind().getSystemType()):"");
						rowArray[10]=Common.get(o.getIsStop()).equals("Y")?"是":Common.get(o.getIsStop()).equals("N")?"否":"";
						rowArray[11]=Common.get(o.getEditId());
						rowArray[12]=Common.formatYYYMMDD(o.getEditDate(),4);
						rowArray[13]=Common.formatHHMMSS(o.getEditTime());
						arrList.add(rowArray);					
					}
					if(sysKindMap != null) sysKindMap.clear();
				}					
				
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return arrList;
	}
	



}
