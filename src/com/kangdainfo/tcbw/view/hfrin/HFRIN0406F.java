package com.kangdainfo.tcbw.view.hfrin;

import java.sql.ResultSet;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;

public class HFRIN0406F extends SuperBean{
	
	private String permitNo;
	
	public String getPermitNo() {		return checkGet(permitNo);	}
	public void setPermitNo(String permitNo) {		this.permitNo = checkSet(permitNo);	}
	
	private String DOCUT_NO;
	private String CHINE_PRODUCT_NAME;
	private String ENGLH_PRODUCT_NAME;
	private String RATIFY_DATE;
	private String VALID_DATE;
	private String APPLY_MNFTY_NAME;
	private String MNFTY_NAME;
	private String LICENSE_STATUS_CONTENT;
	
	public String getDOCUT_NO() {		return checkGet(DOCUT_NO);	}
	public void setDOCUT_NO(String dOCUT_NO) {		DOCUT_NO = checkSet(dOCUT_NO);	}
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
	public String getMNFTY_NAME() {		return checkGet(MNFTY_NAME);	}
	public void setMNFTY_NAME(String mNFTY_NAME) {		MNFTY_NAME = checkSet(mNFTY_NAME);	}
	public String getLICENSE_STATUS_CONTENT() {		return checkGet(LICENSE_STATUS_CONTENT);	}
	public void setLICENSE_STATUS_CONTENT(String lICENSE_STATUS_CONTENT) {		LICENSE_STATUS_CONTENT = checkSet(lICENSE_STATUS_CONTENT);	}
	
	
	// 成分
	public String getQMS_view_AR_BAS_COMPT() throws Exception {
		StringBuilder sb = new StringBuilder(1024); 
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		
		Database db = new Database("FLM");
		try{
			String sql = " select DOCUT_NO, COMPT from QMS_view_AR_BAS_COMPT where DOCUT_NO = ? ";
			parameter.add(getDOCUT_NO());
			
			ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
            while(rs.next()){
            	sb.append("addQMS_view_AR_BAS_COMPT('").append(rs.getString("DOCUT_NO")).append("'");
				sb.append(",'").append(rs.getString("COMPT")).append("');\n");					
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
		HFRIN0406F obj = this;
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		
		Database db = new Database("FLM");
		try{
			String sql = " select a.DOCUT_NO, a.CHINE_PRODUCT_NAME, a.ENGLH_PRODUCT_NAME, a.RATIFY_DATE, a.VALID_DATE, " +
						 " a.APPLY_MNFTY_NAME, a.MNFTY_NAME, a.LICENSE_STATUS_CONTENT " +
						 " from QMS_view_AR_BAS a where a.DOCUT_NO = ? ";
			parameter.add(getPermitNo());
			
			ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
			if(rs.next()){
				obj.setDOCUT_NO(Common.get(rs.getString("DOCUT_NO")));
				obj.setCHINE_PRODUCT_NAME(Common.get(rs.getString("CHINE_PRODUCT_NAME")));
				obj.setENGLH_PRODUCT_NAME(Common.get(rs.getString("ENGLH_PRODUCT_NAME")));
				obj.setRATIFY_DATE(Common.get(rs.getString("RATIFY_DATE")));
				obj.setVALID_DATE(Common.get(rs.getString("VALID_DATE")));
				obj.setAPPLY_MNFTY_NAME(Common.get(rs.getString("APPLY_MNFTY_NAME")));
				obj.setMNFTY_NAME(Common.get(rs.getString("MNFTY_NAME")));
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
