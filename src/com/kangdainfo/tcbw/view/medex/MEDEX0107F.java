package com.kangdainfo.tcbw.view.medex;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;


public class MEDEX0107F extends SuperBean
{
	String id;
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	
	private String permitKey; //許可證字
	private String permitNo;  //許可證號
	private String chProduct; //中文品名
	private String enProduct; //英文品名
	private String expirationDate; //有效日期
	private String applicationName; //申請商名稱
	private String manufactorName; //製造廠名稱
	private String prodCountry; //製造廠國別
	
	private String rowCount;//for medex0104f.jsp儲存是哪個row選取的
                                  
	private String q_permitKey;
	private String q_permitNo;
	private String q_chProduct;
	private String q_enProduct;
	private String q_keyId;
	
	public String getPermitKey() {
		return checkGet(permitKey);
	}
	public void setPermitKey(String permitKey) {
		this.permitKey = checkSet(permitKey);
	}
	public String getPermitNo() {
		return checkGet(permitNo);
	}
	public void setPermitNo(String permitNo) {
		this.permitNo = checkSet(permitNo);
	}
	public String getChProduct() {
		return checkGet(chProduct);
	}
	public void setChProduct(String chProduct) {
		this.chProduct = checkSet(chProduct);
	}
	public String getEnProduct() {
		return get(enProduct);
	}
	public void setEnProduct(String enProduct) {
		this.enProduct = checkSet(enProduct);
	}
	public String getExpirationDate() {
		return checkGet(expirationDate);
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = checkSet(expirationDate);
	}
	public String getApplicationName() {
		return checkGet(applicationName);
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = checkSet(applicationName);
	}
	public String getManufactorName() {
		return checkGet(manufactorName);
	}
	public void setManufactorName(String manufactorName) {
		this.manufactorName = checkSet(manufactorName);
	}
	public String getProdCountry() {
		return checkGet(prodCountry);
	}
	public void setProdCountry(String prodCountry) {
		this.prodCountry = checkSet(prodCountry);
	}
	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}
	public void setQ_permitKey(String qPermitKey) {
		q_permitKey = checkSet(qPermitKey);
	}
	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}
	public void setQ_permitNo(String qPermitNo) {
		q_permitNo = checkSet(qPermitNo);
	}
	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}
	public void setQ_chProduct(String qChProduct) {
		q_chProduct = checkSet(qChProduct);
	}
	public String getQ_enProduct() {
		return checkGet(q_enProduct);
	}
	public void setQ_enProduct(String qEnProduct) {
		q_enProduct = checkSet(qEnProduct);
	}

	public String getRowCount() {
		return checkGet(rowCount);
	}
	public void setRowCount(String rowCount) {
		this.rowCount = checkSet(rowCount);
	}
	public String getQ_keyId() {return checkGet(q_keyId);}
	public void setQ_keyId(String s) {this.q_keyId = checkSet(s);}
	
	@Override
	public Object doQueryAll() throws Exception 
	{
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		Database db2 = new Database("MLMS");
		
		try
		{
			List<Object> parameter = new ArrayList<Object>();
			String hql = " select LICEKID,LICEKC,LICID1,CHNAME,ENNAME,EFDATE,APPNAME,FACTNAME,FACTCIDMA " +
		                 " from VW_ForADR_TBMLIC where 1=1 ";

			//要過濾掉LICEKID = 於common_code的MEDPKID.codeid中
			if(!"".equals(getLICEKID()))
			   hql += " and LICEKID in ("+getLICEKID()+")";
			
			if (!"".equals(getQ_permitKey())){	
				hql += " and LICEKID = ?";	
				parameter.add(getQ_permitKey());
			}
			if (!"".equals(getQ_permitNo())){	
				hql += " and LICID1 = ?";	
				parameter.add(getQ_permitNo());
			}
			if (!"".equals(getQ_chProduct())){	
				hql += " and CHNAME like ?";	
				parameter.add("%"+getQ_chProduct()+"%");
			}
			if (!"".equals(getQ_enProduct())){	
				hql += " and ENNAME like ?";	
				parameter.add("%"+getQ_enProduct()+"%");
			}
			
			System.out.println("[TCBW]-[MEDX0107F]-[QUERYALL] : " + hql);
			
		    ResultSet rs = db2.querySQLByPrepareStatement(hql, parameter);
			processCurrentPageAttribute(rs);
			if(getTotalRecord() > 0)
			{
				int count = getRecordStart();
				int end = getRecordEnd();
				do
				{
					if(count > end)
					{
						break;
					}
					String[] rowArray = new String[8];
					
					rowArray[0] = Common.get(rs.getString("LICEKID")+rs.getString("LICID1"));
					rowArray[1] = Common.get(rs.getString("LICEKC")+"-"+rs.getString("LICID1"));
					rowArray[2] = Common.get(rs.getString("CHNAME"));
					rowArray[3] = Common.get(rs.getString("ENNAME"));
					rowArray[4] = Common.get(rs.getString("EFDATE"));
					rowArray[5] = Common.get(rs.getString("APPNAME"));
					rowArray[6] = Common.get(rs.getString("FACTNAME"));
					rowArray[7] = Common.get(rs.getString("FACTCIDMA"));
					
					arrList.add(rowArray);
					
					count++;
				} 
				while (rs.next());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db2.closeAll();
		}
		return arrList;
	}

	public String getLICEKID()
	{
		String licekid="";
		
		String hql = " from CommonCode where (isStop is null or isStop = '' or isStop <> 'Y') and commonCodeKind.codeKindId='MEDPKID' ";

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
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub		
	}
}
