package com.kangdainfo.tcbw.util;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;

/**
*<br>程式目的：產品分類POP視窗
*<br>程式代號：NONE
*<br>程式日期：101/08/23
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  PermitDataSearch extends SuperBean{
	
	String q_prodName1,q_prodName2,q_prodName3,q_prodName4,q_prodName5;
	String q_ingrMa1,q_ingrMa2,q_ingrMa3,q_ingrMa4,q_ingrMa5; 
	String q_appName;
	String q_factName;
	String q_removeKey;
	String q_caseType;

	public String getQ_prodName1() {return checkGet(q_prodName1);}
	public void setQ_prodName1(String s) {this.q_prodName1 = checkSet(s);}
	public String getQ_prodName2() {return checkGet(q_prodName2);}
	public void setQ_prodName2(String s) {this.q_prodName2 = checkSet(s);}
	public String getQ_prodName3() {return checkGet(q_prodName3);}
	public void setQ_prodName3(String s) {this.q_prodName3 = checkSet(s);}
	public String getQ_prodName4() {return checkGet(q_prodName4);}
	public void setQ_prodName4(String s) {this.q_prodName4 = checkSet(s);}
	public String getQ_prodName5() {return checkGet(q_prodName5);}
	public void setQ_prodName5(String s) {this.q_prodName5 = checkSet(s);}
	public String getQ_ingrMa1() {return checkGet(q_ingrMa1);}
	public void setQ_ingrMa1(String s) {this.q_ingrMa1 = checkSet(s);}
	public String getQ_ingrMa2() {return checkGet(q_ingrMa2);}
	public void setQ_ingrMa2(String s) {this.q_ingrMa2 = checkSet(s);}
	public String getQ_ingrMa3() {return checkGet(q_ingrMa3);}
	public void setQ_ingrMa3(String s) {this.q_ingrMa3 = checkSet(s);}
	public String getQ_ingrMa4() {return checkGet(q_ingrMa4);}
	public void setQ_ingrMa4(String s) {this.q_ingrMa4 = checkSet(s);}
	public String getQ_ingrMa5() {return checkGet(q_ingrMa5);}
	public void setQ_ingrMa5(String s) {this.q_ingrMa5 = checkSet(s);}
	public String getQ_appName() {return checkGet(q_appName);}
	public void setQ_appName(String s) {this.q_appName = checkSet(s);}
	public String getQ_factName() {return checkGet(q_factName);}
	public void setQ_factName(String s) {this.q_factName = checkSet(s);}
	public String getQ_removeKey() {return checkGet(q_removeKey);}
	public void setQ_removeKey(String s) {this.q_removeKey = checkSet(s);}
	public String getQ_caseType() {return checkGet(q_caseType);}
	public void setQ_caseType(String s) {this.q_caseType = checkSet(s);}
	
	@Override
	public Object doQueryOne() throws Exception {
		PermitDataSearch obj = this;
		return obj;
	}
	@Override
	public Object doQueryAll() throws Exception {
		ArrayList<String[]> arrList = new ArrayList<String[]>();
		java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
		Database db = new Database("MLMS");
		String prodNameStr = "";
		String ingrMaStr = "";
		try {
			java.util.List<Object> parameter = new java.util.ArrayList<Object>();
			
			String hql = " select LICEKID,LICID1,CHNAME,ENNAME,CONVERT(varchar(12) , EFDATE, 112 ) EFDATE,APPUNNO,APPNAME,FACTNAME,FACTCIDMA " + 
						" from VW_ForADR_TBMLIC where 1=1 ";
			if(null != getQ_prodName1() && !"".equals(getQ_prodName1())){
				if(prodNameStr.length() > 0)	prodNameStr += " or " ;
				prodNameStr += " chName like ? ";
				parameter.add("%"+getQ_prodName1()+"%");
				
				prodNameStr += " or enName like ? ";
				parameter.add("%"+getQ_prodName1()+"%");
				
//				prodNameStr += " or ingrMa like ? ";
//				parameter.add("%"+getQ_prodName1()+"%");
			}
			if(null != getQ_prodName2() && !"".equals(getQ_prodName2())){
				if(prodNameStr.length() > 0)	prodNameStr += " or " ;
				prodNameStr += " chName like ? ";
				parameter.add("%"+getQ_prodName2()+"%");
				
				prodNameStr += " or enName like ? ";
				parameter.add("%"+getQ_prodName2()+"%");
				
//				prodNameStr += " or ingrMa like ? ";
//				parameter.add("%"+getQ_prodName2()+"%");
			}
			if(null != getQ_prodName3() && !"".equals(getQ_prodName3())){
				if(prodNameStr.length() > 0)	prodNameStr += " or " ;
				prodNameStr += " chName like ? ";
				parameter.add("%"+getQ_prodName3()+"%");
				
				prodNameStr += " or enName like ? ";
				parameter.add("%"+getQ_prodName3()+"%");
				
//				prodNameStr += " or ingrMa like ? ";
//				parameter.add("%"+getQ_prodName3()+"%");
			}
			if(null != getQ_prodName4() && !"".equals(getQ_prodName4())){
				if(prodNameStr.length() > 0)	prodNameStr += " or " ;
				prodNameStr += " chName like ? ";
				parameter.add("%"+getQ_prodName4()+"%");
				
				prodNameStr += " or enName like ? ";
				parameter.add("%"+getQ_prodName4()+"%");
				
//				prodNameStr += " or ingrMa like ? ";
//				parameter.add("%"+getQ_prodName4()+"%");
			}
			if(null != getQ_prodName5() && !"".equals(getQ_prodName5())){
				if(prodNameStr.length() > 0)	prodNameStr += " or " ;
				prodNameStr += " chName like ? ";
				parameter.add("%"+getQ_prodName5()+"%");
				
				prodNameStr += " or enName like ? ";
				parameter.add("%"+getQ_prodName5()+"%");
				
//				prodNameStr += " or ingrMa like ? ";
//				parameter.add("%"+getQ_prodName5()+"%");
			}
			
			if(null != getQ_ingrMa1() && !"".equals(getQ_ingrMa1())){
				if(ingrMaStr.length() > 0)	ingrMaStr += " and " ;
				ingrMaStr += " ingrMa like ? ";
				parameter.add("%"+getQ_ingrMa1()+"%");
				
//				ingrMaStr += " or chName like ? ";
//				parameter.add("%"+getQ_ingrMa1()+"%");
//				
//				ingrMaStr += " or enName like ? ";
//				parameter.add("%"+getQ_ingrMa1()+"%");
			}
			if(null != getQ_ingrMa2() && !"".equals(getQ_ingrMa2())){
				if(ingrMaStr.length() > 0)	ingrMaStr += " and " ;
				ingrMaStr += " ingrMa like ? ";
				parameter.add("%"+getQ_ingrMa2()+"%");
				
//				ingrMaStr += " or chName like ? ";
//				parameter.add("%"+getQ_ingrMa2()+"%");
//				
//				ingrMaStr += " or enName like ? ";
//				parameter.add("%"+getQ_ingrMa2()+"%");
			}
			if(null != getQ_ingrMa3() && !"".equals(getQ_ingrMa3())){
				if(ingrMaStr.length() > 0)	ingrMaStr += " and " ;
				ingrMaStr += " ingrMa like ? ";
				parameter.add("%"+getQ_ingrMa3()+"%");
				
//				ingrMaStr += " or chName like ? ";
//				parameter.add("%"+getQ_ingrMa3()+"%");
//				
//				ingrMaStr += " or enName like ? ";
//				parameter.add("%"+getQ_ingrMa3()+"%");
			}
			if(null != getQ_ingrMa4() && !"".equals(getQ_ingrMa4())){
				if(ingrMaStr.length() > 0)	ingrMaStr += " and " ;
				ingrMaStr += " ingrMa like ? ";
				parameter.add("%"+getQ_ingrMa4()+"%");
				
//				ingrMaStr += " or chName like ? ";
//				parameter.add("%"+getQ_ingrMa4()+"%");
//				
//				ingrMaStr += " or enName like ? ";
//				parameter.add("%"+getQ_ingrMa4()+"%");
			}
			if(null != getQ_ingrMa5() && !"".equals(getQ_ingrMa5())){
				if(ingrMaStr.length() > 0)	ingrMaStr += " and " ;
				ingrMaStr += " ingrMa like ? ";
				parameter.add("%"+getQ_ingrMa5()+"%");
				
//				ingrMaStr += " or chName like ? ";
//				parameter.add("%"+getQ_ingrMa5()+"%");
//				
//				ingrMaStr += " or enName like ? ";
//				parameter.add("%"+getQ_ingrMa5()+"%");
			}
			if(null != prodNameStr && !"".equals(prodNameStr)){
				hql += " and ((" + prodNameStr + ")";
			}
			if(null != ingrMaStr && !"".equals(ingrMaStr) && null != prodNameStr && !"".equals(prodNameStr)){
				hql += " or (" +ingrMaStr + ")";
			} else if(null != ingrMaStr && !"".equals(ingrMaStr)){
				hql += " and (" +ingrMaStr + ")";
			}
			if(null != prodNameStr && !"".equals(prodNameStr)){
				hql += " ) ";
			}
			if(null != getQ_appName() && !"".equals(getQ_appName())){
				hql += " and (appName like ? ";
				parameter.add("%"+getQ_appName()+"%");
			}
			if(null != getQ_factName() && !"".equals(getQ_factName())){
				if(null != getQ_appName() && !"".equals(getQ_appName())){
					hql += " or ";
				}else{
					hql += " and ";
				}
				hql += " factName like ? ";
				parameter.add("%"+getQ_factName()+"%");
			}
			if(null != getQ_appName() && !"".equals(getQ_appName())){
				hql += " ) ";
			}
			if(null != getQ_removeKey() && !"".equals(getQ_removeKey())){
				hql += " and licekId+licId1 not in (" +Common.get(getQ_removeKey())+ ")";
			}
			if(null != getQ_caseType() && !"".equals(getQ_caseType())){
				hql += " and licekId in ("+getLICEKID()+")";
				//parameter.add(getQ_caseType());
			}
			System.out.println("hql=="+hql);
			System.out.println("parameter=="+parameter.toString());
			ResultSet rs = db.querySQLByPrepareStatement(hql, parameter);
			while (rs.next()) {
				String rowArray[] = new String[9];
				rowArray[0] = Common.get(rs.getString("LICEKID"))+";"+Common.get(rs.getString("LICID1"));
				rowArray[1] = Common.get(pkidMap.get(Common.get(rs.getString("LICEKID"))))+"第"+Common.get(rs.getString("LICID1"))+"號";
				rowArray[2] = Common.get(rs.getString("CHNAME"));
				rowArray[3] = Common.get(rs.getString("ENNAME"));
				rowArray[4] = Common.get(rs.getString("EFDATE"));
				rowArray[5] = Common.get(rs.getString("APPUNNO"));
				rowArray[6] = Common.get(rs.getString("APPNAME"));
				rowArray[7] = Common.get(rs.getString("FACTNAME"));
				rowArray[8] = Common.get(rs.getString("FACTCIDMA"));
				arrList.add(rowArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}		
		return arrList;
	}
	
	
	public String getLICEKID()
	{
		String licekid="";
		
		String hql = " from CommonCode where (isStop is null or isStop = '' or isStop <> 'Y') and commonCodeKind.codeKindId="+Common.sqlChar(getQ_caseType());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql);
		
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				CommonCode o = (CommonCode) it.next();
				licekid+="'"+o.getCodeId()+"',";
			}
		}
		
		if(licekid.length()>0)
			licekid=licekid.substring(0, licekid.length()-1);
		
		
		return licekid;
		
		
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