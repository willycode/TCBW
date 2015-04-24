package com.kangdainfo.tcbw.view.cosin;

import java.sql.ResultSet;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;

public class COSIN0404F extends SuperBean {
	
	private String permitKey;
	private String permitNo;
	
	public String getPermitKey() {		return checkGet(permitKey);	}
	public void setPermitKey(String permitKey) {		this.permitKey = checkSet(permitKey);	}
	public String getPermitNo() {		return checkGet(permitNo);	}
	public void setPermitNo(String permitNo) {		this.permitNo = checkSet(permitNo);	}
	
	private String liceid; 						// 許可證字號
	private String chname; 						// 中文品名
	private String enname; 						// 英文品名
	private String drugmcma; 					// 化粧品類別
	private String ingrma; 						// 主成分略述	
	private String appunno;  					// 申請商統一編號
	private String appname;  					// 申請商名稱
	private String appaddr;  					// 申請商地址
	private String appotel;  					// 申請商電話
	private String factname;  					// 製造廠名稱
	private String factaddr;  					// 製造廠地址
	private String factcidma;  					// 製造廠國別
	private String gidate;  					// 發證日期
	private String efdate;  					// 有效日期
	private String candate;  					// 註銷日期
	private String canmark;  					// 註銷狀態
	
	public String getLiceid() {		return checkGet(liceid);	}
	public void setLiceid(String liceid) {		this.liceid = checkSet(liceid);	}
	public String getChname() {		return checkGet(chname);	}
	public void setChname(String chname) {		this.chname = checkSet(chname);	}	
	public String getEnname() {		return checkGet(enname);	}
	public void setEnname(String enname) {		this.enname = checkSet(enname);	}
	public String getDrugmcma() {		return checkGet(drugmcma);	}
	public void setDrugmcma(String drugmcma) {		this.drugmcma = checkSet(drugmcma);	}	
	public String getIngrma() {		return checkGet(ingrma);	}
	public void setIngrma(String ingrma) {		this.ingrma = checkSet(ingrma);	}
	public String getAppunno() {		return checkGet(appunno);	}
	public void setAppunno(String appunno) {		this.appunno = checkSet(appunno);	}
	public String getAppname() {		return checkGet(appname);	}
	public void setAppname(String appname) {		this.appname = checkSet(appname);	}
	public String getAppaddr() {		return checkGet(appaddr);	}
	public void setAppaddr(String appaddr) {		this.appaddr = checkSet(appaddr);	}
	public String getAppotel() {		return checkGet(appotel);	}
	public void setAppotel(String appotel) {		this.appotel = checkSet(appotel);	}
	public String getFactname() {		return checkGet(factname);	}
	public void setFactname(String factname) {		this.factname = checkSet(factname);	}
	public String getFactaddr() {		return checkGet(factaddr);	}
	public void setFactaddr(String factaddr) {		this.factaddr = checkSet(factaddr);	}
	public String getFactcidma() {		return checkGet(factcidma);	}
	public void setFactcidma(String factcidma) {		this.factcidma = checkSet(factcidma);	}
	public String getGidate() {		return checkGet(gidate);	}
	public void setGidate(String gidate) {		this.gidate = checkSet(gidate);	}
	public String getEfdate() {		return checkGet(efdate);	}
	public void setEfdate(String efdate) {		this.efdate = checkSet(efdate);	}
	public String getCandate() {		return checkGet(candate);	}
	public void setCandate(String candate) {		this.candate = checkSet(candate);	}
	public String getCanmark() {		return checkGet(canmark);	}
	public void setCanmark(String canmark) {		this.canmark = checkSet(canmark);	}
	
	
	public String getVW_ForADR_BIGKND3() throws Exception {
		StringBuilder sb = new StringBuilder(1024); 
		String LICID = Common.get(getPermitKey()) + Common.get(getPermitNo());
		
		Database db = new Database("MLMS");
		try{
			java.util.List<Object> parameter = new java.util.ArrayList<Object>();
			
			String sql = " select LICID, DOES, PACKMA from VW_ForADR_BIGKND3 where LICID = ? ";
			parameter.add(LICID);
			
			ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
            while(rs.next()){
            	sb.append("addVW_ForADR_BIGKND3('").append(rs.getString("LICID")).append("'");
				sb.append(",'").append(rs.getString("DOES")).append("'");						// 劑型
				sb.append(",'").append(rs.getString("PACKMA")).append("');\n");					// 包裝
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
		COSIN0404F obj = this;
		Database db = new Database("MLMS");
		try{
			java.util.List<Object> parameter = new java.util.ArrayList<Object>();
			
			String sql = " select a.LICEKID, a.LICEKC, a.LICID1, a.CHNAME, a.ENNAME, a.DRUGMCMA, a.INGRMA, a.APPUNNO, a.APPNAME,a.APPADDR, a.APPOTEL, " +
						 " a.FACTNAME, a.FACTADDR, a.FACTCIDMA, a.GIDATE, a.EFDATE, a.CANDATE, a.CANMARK " +
						 " from VW_ForADR_TBMLIC a where 1 = 1 ";
			if(!"".equals(getPermitKey())){
				sql += " and a.LICEKID = ? ";
				parameter.add(getPermitKey());
			}
			if(!"".equals(getPermitNo())){
				sql += " and a.LICID1 = ? ";
				parameter.add(getPermitNo());
			}
			
			ResultSet rs = db.querySQLByPrepareStatement(sql, parameter);
			if(rs.next()){
				obj.setLiceid(Common.get(rs.getString("LICEKC") + rs.getString("LICID1")));
				obj.setChname(Common.get(rs.getString("CHNAME")));
				obj.setEnname(Common.get(rs.getString("ENNAME")));
				obj.setDrugmcma(Common.get(rs.getString("DRUGMCMA")));
				obj.setIngrma(Common.get(rs.getString("INGRMA")));
				obj.setAppunno(Common.get(rs.getString("APPUNNO")));
				obj.setAppname(Common.get(rs.getString("APPNAME")));
				obj.setAppaddr(Common.get(rs.getString("APPADDR")));
				obj.setAppotel(Common.get(rs.getString("APPOTEL")));
				obj.setFactname(Common.get(rs.getString("FACTNAME")));
				obj.setFactaddr(Common.get(rs.getString("FACTADDR")));
				obj.setFactcidma(Common.get(rs.getString("FACTCIDMA")));
				obj.setGidate(Common.get(rs.getString("GIDATE")));
				obj.setEfdate(Common.get(rs.getString("EFDATE")));
				obj.setCandate(Common.get(rs.getString("CANDATE")));
				obj.setCanmark(Common.get(rs.getString("CANMARK")));
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
