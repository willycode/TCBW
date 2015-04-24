package com.kangdainfo.tcbw.view.hfrin;

import java.sql.ResultSet;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;

public class HFRIN0405F extends SuperBean{
	
	private String permitKey;
	private String permitNo;
	
	public String getPermitKey() {		return checkGet(permitKey);	}
	public void setPermitKey(String permitKey) {		this.permitKey = checkSet(permitKey);	}
	public String getPermitNo() {		return checkGet(permitNo);	}
	public void setPermitNo(String permitNo) {		this.permitNo = checkSet(permitNo);	}
	
	private String DOCUT_NO;
	private String AD_LICENSE_TYPE_CONTENT;					// 許可證類別
	private String AD_LICENSE_NO;							// 許可證字號	
	private String CHINE_PRODUCT_NAME;						// 中文品名
	private String ENGLH_PRODUCT_NAME;						// 外文品名
	private String RATIFY_DATE;								// 發證日期
	private String VALID_DATE;								// 有效日期
	private String APPLY_MNFTY_NAME;						// 申請商
	private String MNFTY_NAME1;								// 製造商
	private String LICENSE_STATUS_CONTENT;					// 證況
	
	public String getDOCUT_NO() {		return checkGet(DOCUT_NO);	}
	public void setDOCUT_NO(String dOCUT_NO) {	DOCUT_NO = checkSet(dOCUT_NO);	}
	public String getAD_LICENSE_TYPE_CONTENT() {		return checkGet(AD_LICENSE_TYPE_CONTENT);	}
	public void setAD_LICENSE_TYPE_CONTENT(String aD_LICENSE_TYPE_CONTENT) {		AD_LICENSE_TYPE_CONTENT = checkSet(aD_LICENSE_TYPE_CONTENT);	}
	public String getAD_LICENSE_NO() {		return checkGet(AD_LICENSE_NO);	}
	public void setAD_LICENSE_NO(String aD_LICENSE_NO) {		AD_LICENSE_NO = checkSet(aD_LICENSE_NO);	}
	public String getCHINE_PRODUCT_NAME() {		return checkGet(CHINE_PRODUCT_NAME);	}
	public void setCHINE_PRODUCT_NAME(String cHINE_PRODUCT_NAME) {		CHINE_PRODUCT_NAME = checkSet(cHINE_PRODUCT_NAME);	}
	public String getENGLH_PRODUCT_NAME() {		return checkGet(ENGLH_PRODUCT_NAME);	}
	public void setENGLH_PRODUCT_NAME(String eNGLH_PRODUCT_NAME) {		ENGLH_PRODUCT_NAME = checkSet(eNGLH_PRODUCT_NAME);	}
	public String getRATIFY_DATE() {		return checkGet(RATIFY_DATE);	}
	public void setRATIFY_DATE(String rATIFY_DATE) {		RATIFY_DATE = checkSet(rATIFY_DATE);	}
	public String getVALID_DATE() {		return checkGet(VALID_DATE);	}
	public void setVALID_DATE(String vALID_DATE) {		VALID_DATE = checkSet(vALID_DATE);	}
	public String getAPPLY_MNFTY_NAME() {		return checkGet(APPLY_MNFTY_NAME);	}
	public void setAPPLY_MNFTY_NAME(String aPPLY_MNFTY_NAME) {		APPLY_MNFTY_NAME = checkSet(aPPLY_MNFTY_NAME);	}
	public String getMNFTY_NAME1() {		return checkGet(MNFTY_NAME1);	}
	public void setMNFTY_NAME1(String mNFTY_NAME1) {		MNFTY_NAME1 = checkSet(mNFTY_NAME1);	}
	public String getLICENSE_STATUS_CONTENT() {		return checkGet(LICENSE_STATUS_CONTENT);	}
	public void setLICENSE_STATUS_CONTENT(String lICENSE_STATUS_CONTENT) {		LICENSE_STATUS_CONTENT = checkSet(lICENSE_STATUS_CONTENT);	}
	
	/* 成分
	public String getQMS_view_HEAL_BAS_COMPT() throws Exception {
		StringBuilder sb = new StringBuilder(1024); 
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		
		Database db = new Database("FLM");
		try{
			String sql = " select DOCUT_NO, COMPT from QMS_view_HEAL_BAS_COMPT where DOCUT_NO = ? ";
			parameter.add(getDOCUT_NO());
			
			ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
            while(rs.next()){
            	sb.append("addQMS_view_HEAL_BAS_COMPT('").append(rs.getString("DOCUT_NO")).append("'");
				sb.append(",'").append(rs.getString("COMPT")).append("');\n");					
            }	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.closeAll();
		}
		return get(sb.toString());
	}*/
	
	// 成分
	public String getQMS_view_HEAL_Funcn_compt() throws Exception {
		StringBuilder sb = new StringBuilder(1024); 
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		
		Database db = new Database("FLM");
		try{
			String sql = " select DOCUT_NO, FUNCN_COMPT_CONTENT from QMS_view_HEAL_Funcn_compt where DOCUT_NO = ? ";
			parameter.add(getDOCUT_NO());
			
			ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
            while(rs.next()){
            	sb.append("addQMS_view_HEAL_Funcn_compt('").append(rs.getString("DOCUT_NO")).append("'");
				sb.append(",'").append(rs.getString("FUNCN_COMPT_CONTENT")).append("');\n");					
            }	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.closeAll();
		}
		return get(sb.toString());
	}
	
	// 保健功效
	public String getQMS_view_HEAL_Funcn() throws Exception {
		StringBuilder sb = new StringBuilder(1024); 
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		
		Database db = new Database("FLM");
		try{
			String sql = " select DOCUT_NO, FUNCN_NEED_TYPE_CONTENT, FUNCN_NEED_CONTENT from QMS_view_HEAL_Funcn where DOCUT_NO = ? ";
			parameter.add(getDOCUT_NO());
			
			ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
            while(rs.next()){
            	sb.append("addQMS_view_HEAL_Funcn('").append(rs.getString("DOCUT_NO")).append("'");
				sb.append(",'").append(rs.getString("FUNCN_NEED_TYPE_CONTENT")).append("'");						
				sb.append(",'").append(rs.getString("FUNCN_NEED_CONTENT")).append("');\n");					
            }	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.closeAll();
		}
		return get(sb.toString()); 
	}
	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		HFRIN0405F obj = this;
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		
		Database db = new Database("FLM");
		try{
			String sql = " select a.DOCUT_NO, a.AD_LICENSE_TYPE_CONTENT, a.AD_LICENSE_NO, a.CHINE_PRODUCT_NAME, a.ENGLH_PRODUCT_NAME, " +
						 " a.RATIFY_DATE, a.VALID_DATE, a.APPLY_MNFTY_NAME, b.MNFTY_NAME1, a.LICENSE_STATUS_CONTENT " +
						 " from QMS_view_HEAL_BAS a, QMS_view_HEAL_MNFTY b where a.DOCUT_NO = b.DOCUT_NO ";
			sql += " and a.AD_LICENSE_NO = ? ";
			parameter.add(getPermitKey() + getPermitNo());
			
			ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
			if(rs.next()){
				obj.setDOCUT_NO(Common.get(rs.getString("DOCUT_NO")));
				obj.setAD_LICENSE_TYPE_CONTENT(Common.get(rs.getString("AD_LICENSE_TYPE_CONTENT")));
				
				String tmp = obj.getAD_LICENSE_TYPE_CONTENT() +   
							 ( Common.get(rs.getString("AD_LICENSE_NO")).length()>0?Common.get(rs.getString("AD_LICENSE_NO")).substring(1):Common.get(rs.getString("AD_LICENSE_NO")) );
				obj.setAD_LICENSE_NO(tmp);
				obj.setCHINE_PRODUCT_NAME(Common.get(rs.getString("CHINE_PRODUCT_NAME")));
				obj.setENGLH_PRODUCT_NAME(Common.get(rs.getString("ENGLH_PRODUCT_NAME")));
				obj.setRATIFY_DATE(Common.get(rs.getString("RATIFY_DATE")));
				obj.setVALID_DATE(Common.get(rs.getString("VALID_DATE")));
				obj.setAPPLY_MNFTY_NAME(Common.get(rs.getString("APPLY_MNFTY_NAME")));
				obj.setMNFTY_NAME1(Common.get(rs.getString("MNFTY_NAME1")));
				obj.setLICENSE_STATUS_CONTENT(Common.get(rs.getString("LICENSE_STATUS_CONTENT")));
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
