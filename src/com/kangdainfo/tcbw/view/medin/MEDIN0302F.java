package com.kangdainfo.tcbw.view.medin;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0003Db;
import com.kangdainfo.tcbw.model.bo.Med0006Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN0302F extends MEDIN0101F
{
	
	private String id2;//序號	NUMERIC(19,0)
	private String bulletinKind;//通報方式	
	private String remark;//備註	
	private String transDate;//傳送日期
	private String assignmentKind;//分派方式
	private String worker;//作業人員
    private String workerName;
    private String autoWorker;
    private String changeTabValue;
    
	private String isCloseUserInfo;		//是否遮蔽個資
	public String getIsCloseUserInfo() {return checkGet(isCloseUserInfo);}
	public void setIsCloseUserInfo(String isCloseUserInfo) {this.isCloseUserInfo = checkSet(isCloseUserInfo);}

	
	//由後端取得醫材問題代碼塞回前端
	public String getMed1006DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String[] med1006DbTypeCode = View.getLookupField("select medicalIssues from Med0008Db where med0001Db = "+Common.get(getId())).split(",");
		System.out.println("ID: " + getId());
		String[] med1006DbTypeName = new String[med1006DbTypeCode.length];	
		if(med1006DbTypeCode!=null && med1006DbTypeCode.length>0)
		{
			for(int i = 0; i < med1006DbTypeCode.length; i++) {
				med1006DbTypeName[i] = View.getOneCodeName(med1006DbTypeCode[i],2);
				
				sb.append("addMed1006Db('attMed1006DbView").append("'");
				sb.append(",'").append(med1006DbTypeCode[i]).append("'");
				sb.append(",'").append(med1006DbTypeName[i]).append("');\n");
			}
		}
		return sb.toString(); 
	}
	@Override
	public Object doQueryAll() throws Exception 
	{		

		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med0006Db  where med0001Db.id="+getId();

		//因為這幾支是共用page.jsp的模組，會造成衝突，故都將查出結果分頁設為最大
		int count = ServiceGetter.getInstance().getTcbwService().loadCount(hql);
		this.setPageSize(count);
		this.processCurrentPageAttribute(count);
		
		System.out.println("[TCBW]-[MEDIN0302F]-[QUERYALL] : " + hql);
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				java.util.Map<String, String> medetsMap = TCBWCommon.getCommonCodeMap("MEDNTS");
				for(Object dtlObj : objList) 
				{				
					Med0006Db dtl = (Med0006Db)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());
					setApplNo(Common.get(dtl.getMed0001Db().getApplNo()));
					rowArray[1] = Common.get(medetsMap.get(dtl.getBulletinKind()));
					rowArray[2] = Common.get(dtl.getRemark());
					rowArray[3] = Common.get(dtl.getTransDate());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public Object doQueryOne() throws Exception 
	{
		MEDIN0302F obj = this;
		 
		Med0006Db c =null;
		
		if(!"".equals(getId2()))
		  c = (Med0006Db)View.getObject(" from Med0006Db where id = " + Common.getLong(getId2()) + "order by id desc");
		else
		  c = (Med0006Db)View.getObject(" from Med0006Db where med0001Db.applNo="+Common.sqlChar(getApplNo()) + "order by id desc");
		
		System.out.println("[TCBW]-[MEDIN0302F]-[QueryOne] : " + c);
		
		if(c!=null)
		{
			obj.setId2(Common.get(c.getId()));
			obj.setAssignmentKind(c.getAssignmentKind());
			obj.setRemark(c.getRemark());
			obj.setBulletinKind(c.getBulletinKind());
			
			if(!"".equals(Common.get(c.getWorker())))
			{	
			  obj.setWorkerName(c.getWorker());
			}
			else
			{	
			  obj.setWorker("");
			  obj.setWorkerName("");
			}
		}
		
		return obj;
	}
	
	public void doUpdateType0302() throws Exception 
	{
		String hql=" from Med0006Db where ";
		       hql+=" id=(select max(id) from  Med0006Db ";
		       hql+=" where  med0001Db.id = " + Common.getLong(getId());
		       hql+=" ) order by id desc" ;
		
		Med0006Db obj = (Med0006Db)View.getObject(hql);
		
		if(obj!=null)
		{	
		  obj.setBulletinKind(getBulletinKind());
		  obj.setAssignmentKind(getAssignmentKind());
		  obj.setRemark(getRemark());
		  obj.setWorker(getWorkerName());
		  
		  if("".equals(Common.get(obj.getCreator())))
		  {
			  obj.setCreator(getEditID());
			  obj.setCreateDate(getEditDate());
			  obj.setCreateTime(getEditTime());
		  }	  
		  
		  obj.setModifier(getEditID());
		  obj.setModifyDate(getEditDate());
		  obj.setModifyTime(getEditTime());
	      ServiceGetter.getInstance().getTcbwService().update(obj);
		  setId2(Common.get(obj.getId()));
		}
	}
	
	//轉送評估
	public void doTransfer() throws Exception 
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateBydoTransferMedIN0302F(this);
	}
	
	//刪除LOCK
	public void doDeleteCon0003Db() throws Exception
	{
		String hql = " from Con0003Db where systemType='MED' ";
               hql +=" and stateus= "+ Common.sqlChar(getStatus());
               hql +=" and dbID=" + Common.sqlChar(getId());

        Con0003Db con0003Db = (Con0003Db)View.getObject(hql);	

        if (con0003Db != null) 
        { 
	      ServiceGetter.getInstance().getTcbwService().delete(con0003Db);
        }
	}
	

	@Override
	public void doDelete() throws Exception {
		
	}
	
	@Override
	public void doCreate() throws Exception 
	{
		
	}
	

	public String getId2() {
		return checkGet(id2);
	}

	public void setId2(String id2) {
		this.id2 = checkSet(id2);
	}

	public String getBulletinKind() {
		return checkGet(bulletinKind);
	}

	public void setBulletinKind(String bulletinKind) {
		this.bulletinKind = checkSet(bulletinKind);
	}

	public String getRemark() {
		return checkGet(remark);
	}

	public void setRemark(String remark) {
		this.remark = checkSet(remark);
	}

	public String getTransDate() {
		return checkGet(transDate);
	}

	public void setTransDate(String transDate) {
		this.transDate = checkSet(transDate);
	}

	public String getAssignmentKind() {
		return checkGet(assignmentKind);
	}

	public void setAssignmentKind(String assignmentKind) {
		this.assignmentKind = checkSet(assignmentKind);
	}

	public String getWorker() {
		return checkGet(worker);
	}

	public void setWorker(String worker) {
		this.worker = checkSet(worker);
	}

	public String getWorkerName() {
		return checkGet(workerName);
	}

	public void setWorkerName(String workerName) {
		this.workerName = checkSet(workerName);
	}

	public String getChangeTabValue() {
		return checkGet(changeTabValue);
	}

	public void setChangeTabValue(String changeTabValue) {
		this.changeTabValue = checkSet(changeTabValue);
	}

	public String getAutoWorker() {
		return checkGet(autoWorker);
	}

	public void setAutoWorker(String autoWorker) {
		this.autoWorker = checkSet(autoWorker);
	}

	
	

	

	


	

}
