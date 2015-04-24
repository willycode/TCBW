package com.kangdainfo.tcbw.view.drgex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.model.bo.Drg4006Db;
import com.kangdainfo.tcbw.model.bo.Drg4010Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class DRGEX0305F extends SuperBean{

	private String id;
	
	private String q_applNo;
	private String q_notifierRepDateS;
	private String q_notifierRepDateE;
	private String q_drg4001DbId;
	private String q_inORout;		//使用者是外網或內網人員
	
	public String getId() {return checkGet(id);}
	public void setId(String s) {this.id = checkSet(s);}
	
	public String getQ_applNo() {return checkGet(q_applNo);}
	public void setQ_applNo(String s) {this.q_applNo = s;}
	public String getQ_notifierRepDateS() {return checkGet(q_notifierRepDateS);}
	public void setQ_notifierRepDateS(String s) {this.q_notifierRepDateS = checkSet(s);}
	public String getQ_notifierRepDateE() {return checkGet(q_notifierRepDateE);}
	public void setQ_notifierRepDateE(String s) {this.q_notifierRepDateE = checkSet(s);}
	public String getQ_drg4001DbId() {return checkGet(q_drg4001DbId);}
	public void setQ_drg4001DbId(String s) {this.q_drg4001DbId = checkSet(s);}
	public String getQ_inORout() {return checkGet(q_inORout);}
	public void setQ_inORout(String s) {this.q_inORout = checkSet(s);}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Drg4006Db where 1=1 ";

		if(!"in".equals(Common.get(getQ_inORout()))){
			hql += " and (isClose is null or isClose = '' or isClose = 'N') ";
			hql += " and manufactorID in ( select con1005Db.compegno from Con1006Db where commonUser.userId = "+Common.sqlChar(TCBWCommon.getCurrentUserId())+" )";
		}
		if(null != getQ_applNo() && !"".equals(getQ_applNo())){
			hql += " and id in (select drg4006Db.id from Drg4010Db where drg4001Db.applNo = " + Common.sqlChar(getQ_applNo())+")";
		}
		if(null != getQ_notifierRepDateS() && !"".equals(getQ_notifierRepDateS())){
			hql += " and id in (select drg4006Db.id from Drg4010Db where drg4001Db.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS())+")";
		}
		if(null != getQ_notifierRepDateE() && !"".equals(getQ_notifierRepDateE())){
			hql += " and id in (select drg4006Db.id from Drg4010Db where drg4001Db.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateE())+")";
		}
		if(null != getQ_drg4001DbId() && !"".equals(getQ_drg4001DbId())){
			hql += " and id in (select drg4006Db.id from Drg4010Db where drg4001Db.id = " + Common.sqlChar(getQ_drg4001DbId())+")";
		}
		hql += " and id in (select drg4006Db.id from Drg4010Db where drg4001Db.status ='40' )";
		System.out.println(hql);
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0)
			{
				for(Object dtlObj : objList)
				{
					Drg4006Db dtl = (Drg4006Db)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());
					if(null != dtl.getPermitKey2() && !"".equals(dtl.getPermitKey2()) && null != dtl.getPermitNo2() && !"".equals(dtl.getPermitNo2())){
						rowArray[1] =  Common.get(View.getCommonCodeName("DRGPKID", dtl.getPermitKey2()))+"第"+Common.get(dtl.getPermitNo2())+"號";
					}
					rowArray[2] = Common.get(dtl.getProductName2());
					rowArray[3] =  "<input type='button' class='toolbar_default' name='btn_Data' value='廠商回覆' onClick=\"listContainerRowClick('"+dtl.getId()+"');queryData('" + dtl.getId() + "');\"> ";
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
