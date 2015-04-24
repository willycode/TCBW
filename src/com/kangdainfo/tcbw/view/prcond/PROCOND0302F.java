package com.kangdainfo.tcbw.view.prcond;

import java.io.File;
import java.util.Map;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.BeanUtil;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con3001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


/**
 *<br>程式目的：
 *<br>程式代號：
 *<br>程式日期：
 *<br>程式作者：
 *<br>--------------------------------------------------------
 *<br>修改作者　　修改日期　　　修改目的
 *<br>--------------------------------------------------------
 */
public class  PROCOND0302F extends SuperBean
{

	private String projName;
	private String applicationId;
	private String applicationName;
	private String type;
	
	private String js;
	private String popId;
	private String popCodeName;
	private String popCode;
	
	private String q_projName;//專案名稱
	private String q_permitKey;//許可證字
	private String q_permitNo;//許可證號
	private String q_chProduct;//商品名稱中文
	private String q_isclose;//是否結案
	private String q_medSecCategory;//醫材次類別
	
	@Override
	public void doCreate() throws Exception 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void doDelete() throws Exception 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public Object doQueryAll() throws Exception 
	{
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		
		String hql=" from Con3001Db  ";
		
		       hql += " where projType = " + Common.sqlChar( getType());
		       hql += " and maintainman like " + TCBWCommon.likeSqlChar(getUserID()+",");
		       
		if(!"".equals(getQ_projName()))
			hql += " and projName like " + TCBWCommon.likeSqlChar(getQ_projName());
		
		if(!"".equals(getQ_permitKey()))
			hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
		
		if(!"".equals(getQ_permitNo()))
			hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());

		if(!"".equals(getQ_chProduct()))
			hql += " and chProduct like " + TCBWCommon.likeSqlChar(getQ_chProduct());
		
		if(!"".equals(getQ_isclose()))
			hql += " and isclose = " + Common.sqlChar(getQ_isclose());

		if(!"".equals(getQ_medSecCategory()))
			hql += " and medSecCategory = " + Common.sqlChar(getQ_medSecCategory());
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().getCommonCodeDao().loadCount(hql));
		
		System.out.println("PROCOND0302F==="+hql);

		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getHibernateTemplate().clear();			
		
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id ");
			
			try 
			{
				if (objList!=null && objList.size()>0) 
				{
					
					//使用map先將資料撈出來
					java.util.Map<String, String> DrgpKidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
					
					java.util.Map<String, String> MedpKidMap = TCBWCommon.getCommonCodeMap("MEDPKID");
					
					java.util.Map<String, String> MedSecCategoryMap = TCBWCommon.getCommonCodeMap("MEDSCT");
					
					
					for (int i=0; i<objList.size(); i++) 
					{
						
						Con3001Db dtl = (Con3001Db) objList.get(i);		
						
						String rowArray[]=new String[7];	
						
						rowArray[0]=Common.get(dtl.getId());
						rowArray[1]=Common.get(dtl.getProjName());
						rowArray[2] = Common.get("drg".equals(dtl.getProjType())?"藥品":"醫療器材");
						
						if("drg".equals(dtl.getProjType()))
						{
						  String permit="";
							
						  if(!"".equals(Common.get(DrgpKidMap.get(dtl.getPermitKey()))))
							  permit+=Common.get(DrgpKidMap.get(dtl.getPermitKey()))+"字";
						  
						  if(!"".equals(Common.get(dtl.getPermitNo())))
							  permit+=Common.get(dtl.getPermitNo())+"號";
						  
						  rowArray[3] = permit;	
						}
						else
						{	
						  String permit="";
							
						  if(!"".equals(Common.get(MedpKidMap.get(dtl.getPermitKey()))))
							permit+=Common.get(MedpKidMap.get(dtl.getPermitKey()))+"字";
							  
						  if(!"".equals(Common.get(dtl.getPermitNo())))
							permit+=Common.get(dtl.getPermitNo())+"號";
						  
						  rowArray[3] = Common.get(permit);	
						}
						
						rowArray[4] = Common.get(dtl.getApplicationName());
						rowArray[5] = Common.get(MedSecCategoryMap.get(dtl.getMedSecCategory()));
						
						arrList.add(rowArray);					
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return arrList;
	}


	@Override
	public Object doQueryOne() throws Exception 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void doUpdate() throws Exception 
	{
		// TODO Auto-generated method stub
		
	}





	public String getType() {
		return checkGet(type);
	}


	public void setType(String type) {
		this.type = checkSet(type);
	}


	public String getJs() {
		return checkGet(js);
	}


	public void setJs(String js) {
		this.js = checkSet(js);
	}


	public String getPopId() {
		return checkGet(popId);
	}


	public void setPopId(String popId) {
		this.popId = checkSet(popId);
	}


	public String getPopCodeName() {
		return checkGet(popCodeName);
	}


	public void setPopCodeName(String popCodeName) {
		this.popCodeName = checkSet(popCodeName);
	}


	public String getPopCode() {
		return checkGet(popCode);
	}


	public void setPopCode(String popCode) {
		this.popCode = checkSet(popCode);
	}


	public String getProjName() {
		return checkGet(projName);
	}


	public void setProjName(String projName) {
		this.projName = checkSet(projName);
	}


	public String getQ_projName() {
		return checkGet(q_projName);
	}


	public void setQ_projName(String qProjName) {
		q_projName = checkSet(qProjName);
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


	public String getQ_isclose() {
		return checkGet(q_isclose);
	}


	public void setQ_isclose(String qIsclose) {
		q_isclose = checkSet(qIsclose);
	}


	public String getQ_medSecCategory() {
		return checkGet(q_medSecCategory);
	}


	public void setQ_medSecCategory(String qMedSecCategory) {
		q_medSecCategory = checkSet(qMedSecCategory);
	}


	public String getApplicationId() {
		return checkGet(applicationId);
	}


	public void setApplicationId(String applicationId) {
		this.applicationId = checkSet(applicationId);
	}


	public String getApplicationName() {
		return checkGet(applicationName);
	}


	public void setApplicationName(String applicationName) {
		this.applicationName = checkSet(applicationName);
	}


	

	

	




	
	

      
}
