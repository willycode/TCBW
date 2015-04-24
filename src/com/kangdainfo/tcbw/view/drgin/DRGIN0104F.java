package com.kangdainfo.tcbw.view.drgin;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Med5003Db;


public class DRGIN0104F extends SuperBean{
	
	private String q_permitKey;
	private String q_permitNo;
	
	public String getQ_permitKey() {return checkGet(q_permitKey);}
	public void setQ_permitKey(String qPermitKey) {q_permitKey = checkSet(qPermitKey);}
	public String getQ_permitNo() {	return checkGet(q_permitNo);}
	public void setQ_permitNo(String qPermitNo) {q_permitNo = checkSet(qPermitNo);}
	
	private String liceid; //許可證字號
	private String chname; //中文品名
	private String enname; //英文品名
	private String drugkc; //藥品類別
	private String ingrma; //主成分略述	
	private String appunno;  //申請商統一編號
	private String appname;  //申請商名稱
	private String appaddr;  //申請商地址
	private String appotel;  //申請商電話
	private String factname;  //製造廠名稱
	private String factaddr;  //製造廠地址
	private String factcidma;  //製造廠國別
	private String gidate;  //發證日期
	private String efdate;  //有效日期
	private String candate;  //註銷日期
	private String canmark;  //註銷狀態
	
	private String medClass;  //醫療器材等級
	private String docKndId;  //醫材主類別
	private String docKndMa;  //醫材主類別內容
	private String msKndId;  //醫材次類別
	private String msKndMa;  //醫材次類別內容
	
	public String getLiceid() {
		return checkGet(liceid);
	}
	public void setLiceid(String liceid) {
		this.liceid = checkSet(liceid);
	}
	public String getChname() {
		return checkGet(chname);
	}
	public void setChname(String chname) {
		this.chname = checkSet(chname);
	}
	public String getEnname() {
		return checkGet(enname);
	}
	public void setEnname(String enname) {
		this.enname = checkSet(enname);
	}
	public String getDrugkc() {
		return checkGet(drugkc);
	}
	public void setDrugkc(String drugkc) {
		this.drugkc = checkSet(drugkc);
	}
	public String getIngrma() {
		return checkGet(ingrma);
	}
	public void setIngrma(String ingrma) {
		this.ingrma = checkSet(ingrma);
	}

	public String getAppunno() {
		return checkGet(appunno);
	}
	public void setAppunno(String appunno) {
		this.appunno = checkSet(appunno);
	}
	public String getAppname() {
		return checkGet(appname);
	}
	public void setAppname(String appname) {
		this.appname = checkSet(appname);
	}
	public String getAppaddr() {
		return checkGet(appaddr);
	}
	public void setAppaddr(String appaddr) {
		this.appaddr = checkSet(appaddr);
	}
	public String getAppotel() {
		return checkGet(appotel);
	}
	public void setAppotel(String appotel) {
		this.appotel = checkSet(appotel);
	}
	public String getFactname() {
		return checkGet(factname);
	}
	public void setFactname(String factname) {
		this.factname = checkSet(factname);
	}
	public String getFactaddr() {
		return checkGet(factaddr);
	}
	public void setFactaddr(String factaddr) {
		this.factaddr = checkSet(factaddr);
	}
	public String getFactcidma() {
		return checkGet(factcidma);
	}
	public void setFactcidma(String factcidma) {
		this.factcidma = checkSet(factcidma);
	}
	public String getGidate() {
		return checkGet(gidate);
	}
	public void setGidate(String gidate) {
		this.gidate = checkSet(gidate);
	}
	public String getEfdate() {
		return checkGet(efdate);
	}
	public void setEfdate(String efdate) {
		this.efdate = checkSet(efdate);
	}
	public String getCandate() {
		return checkGet(candate);
	}
	public void setCandate(String candate) {
		this.candate = checkSet(candate);
	}
	public String getCanmark() {
		return checkGet(canmark);
	}
	public void setCanmark(String canmark) {
		this.canmark = checkSet(canmark);
	}
	
	public String getVW_ForADR_BIGKND1() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		String LICID = Common.get(getQ_permitKey())+Common.get(getQ_permitNo());
		
		//String hql = " select LICID,DOES,PACKMA from VW_ForADR_BIGKND1 where LICID="+Common.get(LICID);
		List<Object> parameter = new ArrayList<Object>();
		String hql = " select LICID,DOES,PACKMA from VW_ForADR_BIGKND1 where LICID= ?";
		parameter.add(Common.get(LICID));
		
		Database db2 = new Database("MLMS");
		//ResultSet rs1 = db2.querySQLByScroll(hql);
		ResultSet rs1 = db2.querySQLByPrepareStatement(hql,parameter);
		try {
			if(rs1!=null){
				while (rs1.next()){
					sb.append("addVW_ForADR_BIGKND1('").append(rs1.getString("LICID")).append("'");
					sb.append(",'").append(rs1.getString("DOES")).append("'");//劑型
					sb.append(",'").append(rs1.getString("PACKMA")).append("');\n");//包裝
				}
			}
		}catch (Exception e) {			
			e.printStackTrace();
		}finally{
			db2.closeAll();
		}
		return get(sb.toString()); 
	}
	
	public String getVW_ForADR_MAINCING() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		String LICID = Common.get(getQ_permitKey())+Common.get(getQ_permitNo());
		
		//String hql = " select LICID,INGRMA,INGRDT,UNITMA from VW_ForADR_MAINCING where LICID="+Common.get(LICID);
		List<Object> parameter = new ArrayList<Object>();
		String hql = " select LICID,INGRMA,INGRDT,UNITMA from VW_ForADR_MAINCING where LICID=?";
		parameter.add(Common.get(LICID));
		Database db2 = new Database("MLMS");
		//ResultSet rs2 = db2.querySQLByScroll(hql);
		ResultSet rs2 = db2.querySQLByPrepareStatement(hql,parameter);
		try {
			if(rs2!=null){
				while (rs2.next()){
					sb.append("addVW_ForADR_MAINCING('").append(rs2.getString("LICID")).append("'");
					sb.append(",'").append(rs2.getString("INGRMA")).append("'");   //成分名稱
					sb.append(",'").append(Common.getNumeric(rs2.getString("INGRDT"))).append("'");   //含量
					sb.append(",'").append(rs2.getString("UNITMA")).append("');\n");//含量單位
				}
			}
		}catch (Exception e) {			
			e.printStackTrace();
		}finally{
			db2.closeAll();
		}
		return get(sb.toString()); 
	}
	
	@Override
	public Object doQueryOne() throws Exception {		
		DRGIN0104F obj = this;	
		List<Object> parameter = new ArrayList<Object>();
		//跟醫材共用，多查醫材的欄位
		String hql = " select a.LICEKID,a.LICEKC,a.LICID1,a.CHNAME,a.ENNAME,a.DRUGKC,a.INGRMA,a.APPUNNO,a.APPNAME,a.APPADDR,a.APPOTEL, " +
		             " a.FACTNAME,a.FACTADDR,a.FACTCIDMA,a.GIDATE,a.EFDATE,a.CANDATE,a.CANMARK,B.*"+
	                 " from VW_ForADR_TBMLIC a left join VW_ForADR_BIGKND2 B on a.LICEKID+a.LICID1 = B.LICID where 1=1 ";

		if (!"".equals(getQ_permitKey())){
			hql += " and a.LICEKID = ?";
			parameter.add(getQ_permitKey());
		}

		if (!"".equals(getQ_permitNo())){
			hql += " and a.LICID1 = ?";
			parameter.add(getQ_permitNo());
		}

		Database db2 = new Database("MLMS");
		//ResultSet rsdata = db2.querySQLByScroll(hql);
		ResultSet rsdata = db2.querySQLByPrepareStatement(hql,parameter);
		try {
			//a.LICEKID-a.LICEKC許可證字號、a.CHNAME中文品名、a.ENNAME英文品名、、a.DRUGKC藥品類別、a.INGRMA主成分略述		
			//a.APPUNNO申請商統一編號、a.APPNAME申請商名稱、a.APPADDR申請商地址、a.APPOTEL申請商電話、a.FACTNAME製造廠名稱、a.FACTADDR製造廠地址、a.FACTCIDMA製造廠國別、
			//a.GIDATE發證日期、a.EFDATE有效日期、a.CANDATE註銷日期、a.CANMARK註銷狀態
			
			//b.DOES劑型、b.PACKMA包裝
			//成分明細(c.INGRMA成分名稱、c.INGRDT含量、c.IUNITMA含量單位)、
			if(rsdata!=null){
				while (rsdata.next()){	
					obj.setLiceid(Common.get(rsdata.getString("LICEKC")+"-"+rsdata.getString("LICID1")));
					obj.setChname(Common.get(rsdata.getString("CHNAME")));
					obj.setEnname(Common.get(rsdata.getString("ENNAME")));
					obj.setDrugkc(Common.get(rsdata.getString("DRUGKC")));
					obj.setIngrma(Common.get(rsdata.getString("INGRMA")));
					obj.setAppunno(Common.get(rsdata.getString("APPUNNO")));
					obj.setAppname(Common.get(rsdata.getString("APPNAME")));
					obj.setAppaddr(Common.get(rsdata.getString("APPADDR")));
					obj.setAppotel(Common.get(rsdata.getString("APPOTEL")));
					obj.setFactname(Common.get(rsdata.getString("FACTNAME")));
					obj.setFactaddr(Common.get(rsdata.getString("FACTADDR")));
					obj.setFactcidma(Common.get(rsdata.getString("FACTCIDMA")));
					obj.setGidate(Common.get(rsdata.getString("GIDATE")));
					obj.setEfdate(Common.get(rsdata.getString("EFDATE")));
					obj.setCandate(Common.get(rsdata.getString("CANDATE")));
					obj.setCanmark(Common.get(rsdata.getString("CANMARK")));
					
					obj.setMedClass(Common.get(rsdata.getString("MEDCLASS")));
					obj.setDocKndId(Common.get(rsdata.getString("DOCKNDID")));
					obj.setDocKndMa(Common.get(rsdata.getString("DOCKNDMA")));
					obj.setMsKndId(Common.get(rsdata.getString("MSKNDID")));
					obj.setMsKndMa(Common.get(rsdata.getString("MSKNDMA")));
				}
			}
		}catch (Exception e) {			
			e.printStackTrace();
		}finally{
			db2.closeAll();
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		return null;
	}

	
	@Override
	public void doCreate() throws Exception {		
	}

	@Override
	public void doUpdate() throws Exception {		
	}

	@Override
	public void doDelete() throws Exception {		
	}
	public String getMedClass() {
		return checkGet(medClass);
	}
	public void setMedClass(String medClass) {
		this.medClass = checkSet(medClass);
	}
	public String getDocKndId() {
		return checkGet(docKndId);
	}
	public void setDocKndId(String docKndId) {
		this.docKndId = checkSet(docKndId);
	}
	public String getDocKndMa() {
		return checkGet(docKndMa);
	}
	public void setDocKndMa(String docKndMa) {
		this.docKndMa = checkSet(docKndMa);
	}
	public String getMsKndId() {
		return checkGet(msKndId);
	}
	public void setMsKndId(String msKndId) {
		this.msKndId = checkSet(msKndId);
	}
	public String getMsKndMa() {
		return checkGet(msKndMa);
	}
	public void setMsKndMa(String msKndMa) {
		this.msKndMa = checkSet(msKndMa);
	}

	
}
