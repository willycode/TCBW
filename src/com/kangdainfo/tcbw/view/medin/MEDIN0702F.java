package com.kangdainfo.tcbw.view.medin;



import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0003Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0008Db;
import com.kangdainfo.tcbw.model.bo.Med1003Db;

import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN0702F extends MEDIN0101F
{
	
	private String id2;//序號	NUMERIC(19,0)
	private String bulletinQuality;//通報品質
    private String evaluationDate;//評估日期
    private String eventClass;//事件等級
    private String eventDetails;//需再取得事件詳細說明
	private String med1006DbTypeCode[];
	private String med1006DbTypeId[];
    private String medicalIssues;//醫材問題代碼
//    private String medicalIssues1;//
    
    private String assessProposal;//評估建議
    private String ncarOptions;//Ncar通報篩選
    private String ncarResult;//Ncar通報篩選結果
    private String ncarTotal;//Ncar總分
	
	private String changeTabValue;//
	private String conveyedVendors;//是否轉知廠商
	private String patientIssues;
	
	private String statDateS;//統計區間起
	private String statDateE;//統計區間迄
	private String statEventKind1;//統計不良反應通報件數
	private String statEventKind2;//統計不良品通報件數
	
	private String isCloseUserInfo;		//是否遮蔽個資
	public String getIsCloseUserInfo() {return checkGet(isCloseUserInfo);}
	public void setIsCloseUserInfo(String isCloseUserInfo) {this.isCloseUserInfo = checkSet(isCloseUserInfo);}
	
	//由後端取得醫材問題代碼塞回前端
	public String getMed1006DbItemSet(String id2) throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024);
		String hql = "select medicalIssues from Med0008Db where 1=1";
		if(!"".equals(id2) && id2.length() > 0) {
			hql += " and id = " + Common.getLong(id2);
		} else {
			hql += " and id = (select max(id) from Med0008Db where med0001Db.id = " + getId() + ")";
		}
		String[] med1006DbTypeCode = View.getLookupField(hql).split(",");
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
		
		String hql = " from Med0008Db  where med0001Db.id="+getId();

		//因為這幾支是共用page.jsp的模組，會造成衝突，故都將查出結果分頁設為最大
		int count = ServiceGetter.getInstance().getTcbwService().loadCount(hql);
		this.setPageSize(count);
		this.processCurrentPageAttribute(count);
		
		System.out.println("[TCBW]-[MEDIN0702F]-[QUERYALL] : " + hql);
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				java.util.Map<String, String> ntqlMap = TCBWCommon.getCommonCodeMap("MEDNTQL");
				java.util.Map<String, String> eventClassMap = TCBWCommon.getCommonCodeMap("MEDEVC");

				for(Object dtlObj : objList) 
				{				
					Med0008Db dtl = (Med0008Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());
					setApplNo(Common.get(dtl.getMed0001Db().getApplNo()));
					rowArray[1] = Common.get(dtl.getEvaluationDate());
					rowArray[2] = Common.get(ntqlMap.get(dtl.getBulletinQuality()));
					rowArray[3] = Common.get(eventClassMap.get(dtl.getEventClass()));
					rowArray[4] = Common.get("1".equals(dtl.getNcarResult())?"通報NCAR":"不通報NCAR");
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
		MEDIN0702F obj = this;
		
		//Med0008Db c = (Med0008Db)View.getObject(" from Med0008Db where id = " + Common.getLong(getId2()));
		
		Med0008Db c =null;
		if(getId2() != null && !"".equals(getId2()))
		  c = (Med0008Db)View.getObject(" from Med0008Db where id = " + Common.getLong(getId2()));
//		else if(getApplNo() != null && !"".equals(getApplNo()))
//		  c = (Med0008Db)View.getObject(" from Med0008Db where med0001Db.applNo="+Common.sqlChar(getApplNo()));
		else
		  c = (Med0008Db)View.getObject(" from Med0008Db where med0001Db.id="+Common.sqlChar(getId())+"order by id desc");
		
		System.out.println("[TCBW]-[MEDIN0702F]-[QueryOne] : " + c);
		
		if(c!=null)
		{
			obj.setId2(Common.get(c.getId()));
			obj.setBulletinQuality(c.getBulletinQuality());//通報品質
			obj.setEvaluationDate(c.getEvaluationDate());//評估日期
			obj.setEventClass(c.getEventClass());//事件等級
			obj.setEventDetails(c.getEventDetails());//需再取得事件詳細說明
			obj.setMedicalIssues(c.getMedicalIssues());//醫材問題代碼
			obj.setAssessProposal(c.getAssessProposal());//評估建議
			obj.setNcarOptions(c.getNcarOptions());//Ncar通報篩選
			obj.setNcarResult(c.getNcarResult());//Ncar通報篩選結果
			obj.setNcarTotal(c.getNcarTotal());//Ncar總分
		}
		//顯示統計區間
		if(getId() != null && !"".equals(getId())){
			Med0001Db med0001 = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.sqlChar(getId()));
			if(med0001 != null){
				obj.setStatDateE(med0001.getNotifierRevDate());
				if(med0001.getNotifierRevDate() != null && !"".equals(med0001.getNotifierRevDate())){
					int diffYear = Common.getInt(View.getLookupField("select field1 from Sys0001Db where id = 4"));
					obj.setStatDateS(Datetime.getDateAdd("y", -diffYear, med0001.getNotifierRevDate()));
				}
				obj.setStatEventKind1(Common.get(ServiceGetter.getInstance().getTcbwService().getMedin2Dao().getStatEventKind1(
						med0001.getMedPermit(), med0001.getMedPermitNumber(), med0001.getMedCname(), obj.getStatDateS(), obj.getStatDateE())));
				obj.setStatEventKind2(Common.get(ServiceGetter.getInstance().getTcbwService().getMedin2Dao().getStatEventKind2(
						med0001.getMedPermit(), med0001.getMedPermitNumber(), med0001.getMedCname(), obj.getStatDateS(), obj.getStatDateE())));
			}
		}
		return obj;
	}
	
	public void doUpdateType0702() throws Exception 
	{
		String hql=" from Med0008Db where ";
		hql+=" id=(select max(id) from  Med0008Db ";
		hql+=" where  med0001Db.id = " + Common.getLong(getId());
		hql+=" ) order by id desc" ;
		
		System.out.println("ID:" + getId());
		Med0008Db obj = (Med0008Db)View.getObject(hql);
		
		if(obj!=null)
		{	
		   if("".equals(Common.get(obj.getCreator())))
		   {
			  obj.setCreator(getEditID());
			  obj.setCreateDate(getEditDate());
			  obj.setCreateTime(getEditTime());
		   }	  
		  

		  obj.setBulletinQuality(getBulletinQuality());//通報品質
	      obj.setEvaluationDate(getEvaluationDate());//評估日期
		  obj.setEventClass(getEventClass());//事件等級
		  obj.setEventDetails(getEventDetails());//需再取得事件詳細說明
		
		  
		  String med1006DbTypeCode = "";
		  if(getMed1006DbTypeId() != null) {
			  for(int i = 0; i < getMed1006DbTypeId().length; i++) {
				  med1006DbTypeCode+= getMed1006DbTypeCode()[i];
				  if(i != getMed1006DbTypeId().length-1) {
					  med1006DbTypeCode+=",";
				  }
			  }
		  }
		  obj.setMedicalIssues(med1006DbTypeCode);//醫材問題代碼
		  
//		  obj.setMedicalIssues(getMedicalIssues());//醫材問題代碼
		  
		  obj.setAssessProposal(getAssessProposal());//評估建議
		  obj.setNcarOptions(getNcarOptions());//Ncar通報篩選
		  obj.setNcarResult(getNcarResult());//Ncar通報篩選結果
		  obj.setNcarTotal(getNcarTotal());//Ncar總分

		  obj.setModifier(getEditID());
		  obj.setModifyDate(getEditDate());
		  obj.setModifyTime(getEditTime());
		  
	      ServiceGetter.getInstance().getTcbwService().update(obj);
		  setId2(Common.get(obj.getId()));
		}
	}
	
	//案件評估中(2:不良品)
	public void doCompleted() throws Exception 
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateBydoCompletedMedIN0702F(this);
	}
	
	//案件確認(2:不良品)
	public void doCompletedConfirmed() throws Exception 
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateBydoCompletedConfirmedMedIN0702F(this);
	}
	
	


	public String getMed1003Db(String id2) throws Exception
	{
		String html="",value="",value1="";
		
		java.util.Map<String,String> h = new java.util.HashMap<String,String>();
		
		String[] name=null,name1=null;
		
//		String med0008Db=" from Med0008Db where ";
//		       med0008Db+=" id=(select max(id) from  Med0008Db ";
//		       med0008Db+=" where  med0001Db.id = " + Common.getLong(getId());
//		       med0008Db+=" ) order by id desc" ;
				
		String med0008Db=" from Med0008Db where ";
			   med0008Db+=" id = " + Common.getLong(id2);
		       med0008Db+=" ) order by id desc" ;		
		Med0008Db c = (Med0008Db)View.getObject(med0008Db);
		
		if(c!=null)
		{
			value=c.getNcarOptions();
			
			if(c.getNcarOptions()!=null && c.getNcarOptions().length()>0)
			{  
			  name=value.split(";");
			
			  for(int x=0;x<name.length;x++)
			  {
		
				value1=name[x];
				if(value1.length()>0)
				{
				  name1=value1.split(":");
				  String map1="",map2="";
				
				  int z=1;
				  for(int y=0;y<name1.length;y++)
				  {
					if(z%2==1)
					  map1=name1[y];
					
					if(z%2==0)
					  map2=name1[y];
					
					z++;
				  }
				  h.put(map1,map2);
			    }
			  }
			}
		}

		String hql="from Med1003Db where isStop='N' ";
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id");
		
		if (objList != null && objList.size() > 0) 
		{
			int i=0;
			int q=1;
			
			for(Object dtlObj : objList) 
			{				
				String checkedY="",checkedN="";
				
				Med1003Db dtl = (Med1003Db)dtlObj;
			
				html+="<tr>";
				html+="<td>";
				
				if(!"1".equals(dtl.getNum()) && !"3".equals(dtl.getNum()))
				{
				   html+=dtl.getMatter();
				}
				else
				{
				   html+="<font color='red'>"+dtl.getMatter()+"</font>";
				}
				
				html+="</td>";
				
				if("Y".equals(h.get(String.valueOf(q))))
				{
					checkedY="checked";
				}
				else if("N".equals(h.get(String.valueOf(q))))
				{
					checkedN="checked";
				}

				
				if(!"3".equals(dtl.getNum()))
				{
                  html+="<td align='center'>";
                  html+="<input type='radio' onClick='totalNcar()' name='ncarOptions"+i+"' "+checkedY+" value='Y'>";
				  html+="</td>";
				
                  html+="<td align='center'>";
                  html+="<input type='radio' onClick='totalNcar()' name='ncarOptions"+i+"' "+checkedN+" value='N'>";
				  html+="</td>";
				  
				  i++;
				  q++;
				}
				else
				{
				  html+="<td align='center'>";
				  html+="</td>";
					
	              html+="<td align='center'>";           
			      html+="</td>";
				}
				
				html+="</tr>";
				
			}
			
			objList.clear();
		}
		
		
		return html;
		
	}
	
	
	
	public void doDeleteCon0003Db() throws Exception
	{
		
		String hql = "from Con0003Db where systemType='MED' ";
        hql +=" and formCode='MED01' ";
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

	public String getBulletinQuality() {
		return checkGet(bulletinQuality);
	}

	public void setBulletinQuality(String bulletinQuality) {
		this.bulletinQuality = checkSet(bulletinQuality);
	}

	public String getEvaluationDate() {
		return checkGet(evaluationDate);
	}

	public void setEvaluationDate(String evaluationDate) {
		this.evaluationDate = checkSet(evaluationDate);
	}

	public String getEventClass() {
		return checkGet(eventClass);
	}

	public void setEventClass(String eventClass) {
		this.eventClass = checkSet(eventClass);
	}

	public String getEventDetails() {
		return checkGet(eventDetails);
	}

	public void setEventDetails(String eventDetails) {
		this.eventDetails = checkSet(eventDetails);
	}

	public String[] getMed1006DbTypeCode() {
		return med1006DbTypeCode;
	}

	public void setMed1006DbTypeCode(String[] med1006DbTypeCode) {
		this.med1006DbTypeCode = med1006DbTypeCode;
	}
	
	public String[] getMed1006DbTypeId() {
		return med1006DbTypeId;
	}

	public void setMed1006DbTypeId(String[] med1006DbTypeId) {
		this.med1006DbTypeId = med1006DbTypeId;
	}
	
	public String getMedicalIssues() {
		return checkGet(medicalIssues);
	}

	public void setMedicalIssues(String medicalIssues) {
		this.medicalIssues = checkSet(medicalIssues);
	}

	public String getAssessProposal() {
		return checkGet(assessProposal);
	}

	public void setAssessProposal(String assessProposal) {
		this.assessProposal = checkSet(assessProposal);
	}

	public String getNcarOptions() {
		return checkGet(ncarOptions);
	}

	public void setNcarOptions(String ncarOptions) {
		this.ncarOptions = checkSet(ncarOptions);
	}

	public String getNcarResult() {
		return checkGet(ncarResult);
	}

	public void setNcarResult(String ncarResult) {
		this.ncarResult = checkSet(ncarResult);
	}

	public String getNcarTotal() {
		return checkGet(ncarTotal);
	}

	public void setNcarTotal(String ncarTotal) {
		this.ncarTotal = checkSet(ncarTotal);
	}

	public String getChangeTabValue() {
		return checkGet(changeTabValue);
	}

	public void setChangeTabValue(String changeTabValue) {
		this.changeTabValue = checkSet(changeTabValue);
	}

	public String getConveyedVendors() {
		return checkGet(conveyedVendors);
	}

	public void setConveyedVendors(String conveyedVendors) {
		this.conveyedVendors = checkSet(conveyedVendors);
	}
	
//	public String getMedicalIssues1() {
//		return checkGet(medicalIssues1);
//	}
//
//	public void setMedicalIssues1(String medicalIssues1) {
//		this.medicalIssues1 = checkSet(medicalIssues1);
//	}

	public String getPatientIssues() {
		return checkGet(patientIssues);
	}

	public void setPatientIssues(String patientIssues) {
		this.patientIssues = checkSet(patientIssues);
	}

	public String getStatDateS() {
		return checkGet(statDateS);
	}


	public void setStatDateS(String statDateS) {
		this.statDateS = checkSet(statDateS);
	}


	public String getStatDateE() {
		return checkGet(statDateE);
	}


	public void setStatDateE(String statDateE) {
		this.statDateE = checkSet(statDateE);
	}


	public String getStatEventKind1() {
		return checkGet(statEventKind1);
	}


	public void setStatEventKind1(String statEventKind1) {
		this.statEventKind1 = checkSet(statEventKind1);
	}


	public String getStatEventKind2() {
		return checkGet(statEventKind2);
	}


	public void setStatEventKind2(String statEventKind2) {
		this.statEventKind2 = checkSet(statEventKind2);
	}
	
	



	

}
