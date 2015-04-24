package com.kangdainfo.tcbw.view.prcond;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con3001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class PROCOND0101F extends SuperBean{

	private String id;
	private String q_id;
	private String projType;//專案類別
	private String projName;//專案名稱
	private String permitKeyMed;//許可證字
	private String permitNoMed;//許可證號
	private String permitKeyDrg;//許可證字
	private String permitNoDrg;//許可證號
	private String chProduct;//商品名稱中文
	private String applicationId;//許可證持有商ID
	private String applicationName;//許可證持有商
	private String medSecCategory;//醫材次類別
	private String medSecCategoryCodeName;//醫材次類別
	private String isclose;//是否結案
	private String maintainman;//維護人員
	private String maintainmanName;
	
	
	private String q_projType;//專案類別
	private String q_projName;//專案名稱
	
	private String q_permitKeyMed;//許可證字
	private String q_permitNoMed;//許可證號
	private String q_permitKeyDrg;//許可證字
	private String q_permitNoDrg;//許可證號
	
	private String q_chProduct;//商品名稱中文
	private String q_isclose;//是否結案
	
	private String q_maintainman;//維護人員
	

	@Override
	public Object doQueryOne() throws Exception 
	{
		PROCOND0101F obj = this;
		
		Con3001Db c = (Con3001Db)View.getObject("from Con3001Db where id = " + Common.getLong(getId()));		
		
		if(c != null)
		{
			obj.setProjType(c.getProjType());//專案類別
			obj.setProjName(c.getProjName());//專案名稱
			
			obj.setChProduct(c.getChProduct());//商品名稱中文
			
			if("med".equals(c.getProjType()))
			{
				obj.setMedSecCategory(c.getMedSecCategory());//醫材次類別
				obj.setApplicationId("");//許可證持有商ID
				obj.setApplicationName("");//許可證持有商
				obj.setPermitKeyMed(c.getPermitKey());//許可證字
				obj.setPermitNoMed(c.getPermitNo());//許可證號
			}	
			else
			{
				obj.setMedSecCategory("");//醫材次類別
				obj.setApplicationId(c.getApplicationId());//許可證持有商ID
				obj.setApplicationName(c.getApplicationName());//許可證持有商
				obj.setPermitKeyDrg(c.getPermitKey());//許可證字
				obj.setPermitNoDrg(c.getPermitNo());//許可證號
			}	

			obj.setIsclose(c.getIsclose());//是否結案
		
			String userName="";
			
	        if(!"".equals(Common.get(c.getMaintainman())))
	        {
	        	String[] tmp=c.getMaintainman().split(",");
	        	
	        	for(int j=0;j<tmp.length;j++)
	        	{
	        		String name=View.getLookupField("select userName from CommonUser where userId="+Common.sqlChar(tmp[j]) );
	        		userName+=name+"\n";
	        	}		
	        }

			
			obj.setMaintainman(c.getMaintainman());
			
			if(userName.length()>0)
				obj.setMaintainmanName(userName);
			else
				obj.setMaintainmanName("");
			
		
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());	
			obj.setEditTime(c.getModifyTime());
			
		}
		
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Con3001Db where 1 = 1 ";
		
	
		   if(!"All".equals(getQ_projType()))
			   hql += " and projType = " + Common.sqlChar( getQ_projType());
	
		   
		   if(!"".equals(getQ_projName()))
			   hql += " and projName like " + TCBWCommon.likeSqlChar(getQ_projName());
		
		   if(!"".equals(getQ_permitKeyMed()))
			   hql += " and permitKey = " + Common.sqlChar(getQ_permitKeyMed());
		
		   if(!"".equals(getQ_permitNoMed()))
			   hql += " and permitNo = " + Common.sqlChar(getQ_permitNoMed());
		
		   if(!"".equals(getQ_permitKeyDrg()))
			   hql += " and permitKey = " + Common.sqlChar(getQ_permitKeyDrg());
		
		   if(!"".equals(getQ_permitNoDrg()))
			   hql += " and permitNo = " + Common.sqlChar(getQ_permitNoDrg());
		
		   if(!"".equals(getQ_chProduct()))
			   hql += " and chProduct like " + TCBWCommon.likeSqlChar(getQ_chProduct());
	
		   if(!"".equals(getQ_isclose()))
			   hql += " and isclose= " + Common.sqlChar(getQ_isclose());
		   
		   if(!"".equals(getQ_maintainman()))
		   {	   
			   String man=View.getLookupField("select userId from CommonUser where userName="+Common.sqlChar(getQ_maintainman()));
			   if(!"".equals(Common.get(man)))
			     hql += " and maintainman like " + TCBWCommon.likeSqlChar(man+",");
			   else
				 hql += " and 1=2 ";
		   }
		   
		System.out.println("hql==="+hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				//使用map先將資料撈出來
				java.util.Map<String, String> DrgpKidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
				
				java.util.Map<String, String> MedpKidMap = TCBWCommon.getCommonCodeMap("MEDPKID");
				
				java.util.Map<String, String> MedSecCategoryMap = TCBWCommon.getCommonCodeMap("MEDSCT");
				
				
				for(Object dtlObj : objList) 
				{				
					Con3001Db dtl = (Con3001Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get("drg".equals(dtl.getProjType())?"藥品":"醫療器材");
					rowArray[2] = Common.get(dtl.getProjName());	
					
					if("drg".equals(dtl.getProjType()))
					{
					  String permit="";
						
					  if(!"".equals(Common.get(DrgpKidMap.get(dtl.getPermitKey()))))
						  permit+=Common.get(DrgpKidMap.get(dtl.getPermitKey()))+"字";
					  
					  if(!"".equals(Common.get(dtl.getPermitNo())))
						  permit+="第"+Common.get(dtl.getPermitNo())+"號";
					  
					  rowArray[3] = permit;	
					}
					else
					{	
					  String permit="";
						
					  if(!"".equals(Common.get(MedpKidMap.get(dtl.getPermitKey()))))
						permit+=Common.get(MedpKidMap.get(dtl.getPermitKey()))+"字";
						  
					  if(!"".equals(Common.get(dtl.getPermitNo())))
						permit+="第"+Common.get(dtl.getPermitNo())+"號";
					  
					  rowArray[3] = permit;	
					}
					
					rowArray[4] = Common.get(dtl.getApplicationName());
					
					rowArray[5] = Common.get(MedSecCategoryMap.get(dtl.getMedSecCategory()));
					
					rowArray[6] = Common.get("Y".equals(dtl.getIsclose())?"是":"否");
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}
	
	protected String[][] getInsertCheckSQL()
	{	
		String[][] checkSQLArray = new String[1][4];
	 	checkSQLArray[0][0] = "select count(*) from Con3001Db where projType=" + Common.sqlChar(getProjType())+" and projName="+Common.sqlChar(getProjName());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該專案已存在，請重新輸入！";
		return checkSQLArray;
	}

	@Override
	public void doCreate() throws Exception 
	{
		Con3001Db obj = new Con3001Db();
	 
		obj.setProjType(projType);//專案類別
		obj.setProjName(projName);//專案名稱
		
		obj.setChProduct(chProduct);//商品名稱中文
		
		if("med".equals(getProjType()))
		{
			obj.setMedSecCategory( medSecCategory);//醫材次類別
			obj.setApplicationId("");//許可證持有商ID
			obj.setApplicationName("");//許可證持有商
			obj.setPermitKey(permitKeyMed);//許可證字
			obj.setPermitNo(permitNoMed);//許可證號
		}	
		else
		{
			obj.setMedSecCategory("");//醫材次類別
			obj.setApplicationId(applicationId);//許可證持有商ID
			obj.setApplicationName(applicationName);//許可證持有商
			obj.setPermitKey(permitKeyDrg);//許可證字
			obj.setPermitNo(permitNoDrg);//許可證號
		}	

		obj.setIsclose(isclose);//是否結案
	
		obj.setMaintainman(maintainman);//維護人員
		
        obj.setCreateDate(getEditDate());
        obj.setCreateTime(getEditTime());
        obj.setCreator(getEditID());
        obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
	    
	    ServiceGetter.getInstance().getTcbwService().save(obj);
		
		
		setId(Common.get(obj.getId()));
	}
	
	protected String[][] getUpdateCheckSQL()
	{	
		String[][] checkSQLArray = new String[1][4];	 	
	    checkSQLArray[0][0] = "select count(*) from Con3001Db where projType=" + Common.sqlChar(getProjType())+" and projName="+Common.sqlChar(getProjName()) + " and id != " + Common.getLong(getId()); 	
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該專案已存在，請重新輸入！";	
		return checkSQLArray;		
	}

	@Override
	public void doUpdate() throws Exception 
	{
		Con3001Db obj = (Con3001Db)View.getObject(" from Con3001Db where id = " + Common.getLong(getId()));
		
		if(obj != null)
		{		
		 
			obj.setProjType(projType);//專案類別
			obj.setProjName(projName);//專案名稱
			
			
			obj.setChProduct(chProduct);//商品名稱中文
			
			if("med".equals(getProjType()))
			{
				obj.setMedSecCategory( medSecCategory);//醫材次類別
				obj.setApplicationId("");//許可證持有商ID
				obj.setApplicationName("");//許可證持有商
				obj.setPermitKey(permitKeyMed);//許可證字
				obj.setPermitNo(permitNoMed);//許可證號
			}	
			else
			{
				obj.setMedSecCategory("");//醫材次類別
				obj.setApplicationId(applicationId);//許可證持有商ID
				obj.setApplicationName(applicationName);//許可證持有商
				obj.setPermitKey(permitKeyDrg);//許可證字
				obj.setPermitNo(permitNoDrg);//許可證號
			}	

			obj.setIsclose(isclose);//是否結案
			obj.setMaintainman(maintainman);//維護人員
			
			obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
		    
	        
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		//Con1007Db obj = (Con1007Db)View.getObject(" from Con1007Db where id = " + Common.getLong(getId()));
		//System.out.println("ID: " + getId());
		//if(obj != null){
		//	ServiceGetter.getInstance().getTcbwService().delete(obj);
		//	setId("");
		//}else{
		//	throw new Exception("查無資料，無法刪除，請重新操作 !");
		//}
	}

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getProjType() {
		return checkGet(projType);
	}

	public void setProjType(String projType) {
		this.projType = checkSet(projType);
	}

	public String getProjName() {
		return checkGet(projName);
	}

	public void setProjName(String projName) {
		this.projName = checkSet(projName);
	}

	

	public String getPermitKeyMed() {
		return checkGet(permitKeyMed);
	}

	public void setPermitKeyMed(String permitKeyMed) {
		this.permitKeyMed = checkSet(permitKeyMed);
	}

	public String getPermitNoMed() {
		return checkGet(permitNoMed);
	}

	public void setPermitNoMed(String permitNoMed) {
		this.permitNoMed = checkSet(permitNoMed);
	}

	public String getPermitKeyDrg() {
		return checkGet(permitKeyDrg);
	}

	public void setPermitKeyDrg(String permitKeyDrg) {
		this.permitKeyDrg = checkSet(permitKeyDrg);
	}

	public String getPermitNoDrg() {
		return checkGet(permitNoDrg);
	}

	public void setPermitNoDrg(String permitNoDrg) {
		this.permitNoDrg = checkSet(permitNoDrg);
	}

	public String getChProduct() {
		return checkGet(chProduct);
	}

	public void setChProduct(String chProduct) {
		this.chProduct = checkSet(chProduct);
	}

	public String getApplicationName() {
		return checkGet(applicationName);
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = checkSet(applicationName);
	}

	public String getMedSecCategory() {
		return checkGet(medSecCategory);
	}

	public void setMedSecCategory(String medSecCategory) {
		this.medSecCategory = checkSet(medSecCategory);
	}

	public String getIsclose() {
		return checkGet(isclose);
	}

	public void setIsclose(String isclose) {
		this.isclose = checkSet(isclose);
	}

	public String getMaintainman() {
		return checkGet(maintainman);
	}

	public void setMaintainman(String maintainman) {
		this.maintainman = checkSet(maintainman);
	}

	public String getQ_projType() {
		return checkGet(q_projType);
	}

	public void setQ_projType(String qProjType) {
		q_projType = checkSet(qProjType);
	}

	public String getQ_projName() {
		return checkGet(q_projName);
	}

	public void setQ_projName(String qProjName) {
		q_projName = checkSet(qProjName);
	}

	

	public String getQ_permitKeyMed() {
		return checkGet(q_permitKeyMed);
	}

	public void setQ_permitKeyMed(String qPermitKeyMed) {
		q_permitKeyMed = checkSet(qPermitKeyMed);
	}

	public String getQ_permitNoMed() {
		return checkGet(q_permitNoMed);
	}

	public void setQ_permitNoMed(String qPermitNoMed) {
		q_permitNoMed = checkSet(qPermitNoMed);
	}

	public String getQ_permitKeyDrg() {
		return checkGet(q_permitKeyDrg);
	}

	public void setQ_permitKeyDrg(String qPermitKeyDrg) {
		q_permitKeyDrg = checkSet(qPermitKeyDrg);
	}

	public String getQ_permitNoDrg() {
		return checkGet(q_permitNoDrg);
	}

	public void setQ_permitNoDrg(String qPermitNoDrg) {
		q_permitNoDrg = checkSet(qPermitNoDrg);
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

	public String getApplicationId() {
		return checkGet(applicationId);
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = checkSet(applicationId);
	}

	public String getMaintainmanName() {
		return checkGet(maintainmanName);
	}

	public void setMaintainmanName(String maintainmanName) {
		this.maintainmanName = checkSet(maintainmanName);
	}

	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String qId) {
		q_id = checkSet(qId);
	}

	public String getQ_maintainman() {
		return checkGet(q_maintainman);
	}

	public void setQ_maintainman(String qMaintainman) {
		q_maintainman = checkSet(qMaintainman);
	}

	public String getMedSecCategoryCodeName() {
		return checkGet(medSecCategoryCodeName);
	}

	public void setMedSecCategoryCodeName(String medSecCategoryCodeName) {
		this.medSecCategoryCodeName = checkSet(medSecCategoryCodeName);
	}
	
	

}
