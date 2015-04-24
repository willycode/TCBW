package com.kangdainfo.tcbw.view.medex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med0010Db;
import com.kangdainfo.tcbw.model.bo.Sys0001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDEX0301F extends SuperBean{

	private String id;
	
	private String q_applNo;
	private String q_notifierRepDateS;
	private String q_notifierRepDateE;

	
	public String getId() {return checkGet(id);}
	public void setId(String s) {this.id = checkSet(s);}
	
	public String getQ_applNo() {return checkGet(q_applNo);}
	public void setQ_applNo(String s) {this.q_applNo = s;}
	public String getQ_notifierRepDateS() {return checkGet(q_notifierRepDateS);}
	public void setQ_notifierRepDateS(String s) {this.q_notifierRepDateS = checkSet(s);}
	public String getQ_notifierRepDateE() {return checkGet(q_notifierRepDateE);}
	public void setQ_notifierRepDateE(String s) {this.q_notifierRepDateE = checkSet(s);}

	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med0010Db where 1=1 ";
		hql += " and med0001Db.status = '51' and id in (select max(id) from Med0010Db)";

		if(null != getQ_applNo() && !"".equals(getQ_applNo()))
		{
			hql += " and med0001Db.applNo = " + Common.sqlChar(getQ_applNo());
		}
		
		if(null != getQ_notifierRepDateS() && !"".equals(getQ_notifierRepDateS())){
			hql += " and med0001Db.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		}
		
		if(null != getQ_notifierRepDateE() && !"".equals(getQ_notifierRepDateE())){
			hql += " and med0001Db.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		}
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		System.out.println("MEDEX0301F=="+hql);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> medMainCategoryMap = TCBWCommon.getCommonCodeMap("MEDMCT");
				java.util.Map<String, String> medPermitMap = TCBWCommon.getCommonCodeMap("MEDPKID");
				
				for(Object dtlObj : objList)
				{
					Med0010Db dtl = (Med0010Db)dtlObj;
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getMed0001Db().getId());
					
                    String light="";
					
	                Sys0001Db sys0001Db=(Sys0001Db)View.getObject("from Sys0001Db where id ='4' ");
	                    
				    if(sys0001Db!=null)
				    {	
				    	
				      if("A".equals(dtl.getMed0001Db().getEventClass()))	
				      {	  
					    String date = Datetime.getDateAdd("d",Integer.valueOf(sys0001Db.getField5()),dtl.getMed0001Db().getNotifierRevDate());
					    if(Common.getInt(Datetime.getYYYMMDD()) > Common.getInt(date)) 
					    	light="<img width='30px' src='../../images/ballRed.png'>";
				      }
				      else
				      {
				    	String date = Datetime.getDateAdd("m",Integer.valueOf(sys0001Db.getField6()),dtl.getMed0001Db().getNotifierRevDate());
						if(Common.getInt(Datetime.getYYYMMDD()) > Common.getInt(date)) 
							light="<img width='30px' src='../../images/ballRed.png'>";
				      }	  
				    
				    }	
				    
						
					rowArray[1] = light;
					
					
					rowArray[2] = Common.get(dtl.getMed0001Db().getApplNo());
					rowArray[3] = Common.get(dtl.getMed0001Db().getNotifierRevDate());
				
					if(!"".equals(Common.get(dtl.getMed0001Db().getMedPermit())))
					  rowArray[4] = Common.get(medPermitMap.get(dtl.getMed0001Db().getMedPermit()))+"字第"+ Common.get(dtl.getMed0001Db().getMedPermitNumber())+"號";	
					else
					  rowArray[4] ="";
					
					rowArray[5] = Common.get(dtl.getMed0001Db().getMedCname());
					rowArray[6] = Common.get(medMainCategoryMap.get(dtl.getMed0001Db().getMedMainCategory()));
					
					rowArray[7] =  "<input type='button' class='toolbar_default' name='btn_Data' value='廠商回覆' onClick=\"listContainerRowClick('"+dtl.getMed0001Db().getId()+"');queryData('" + dtl.getMed0001Db().getId() + "');\"> ";
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
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
