package com.kangdainfo.tcbw.view.drgin;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Drg0005Db;
import com.kangdainfo.tcbw.model.bo.Drg0006Db;
import com.kangdainfo.tcbw.model.bo.Drg0007Db;
import com.kangdainfo.tcbw.model.bo.Drg0008Db;

import com.kangdainfo.tcbw.util.TCBWCommon;

public class DRGIN0112F extends DRGIN0109F{
	
	
	public void doUpdateDrg0112() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().updateByDrgIN0112F(this);
	}
	
	public void doPostNoDrg0112() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().doPostNoByDrgIN0112F(this);
	}
	
	public void doAssessDrg0112() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().doAssessByDrgIN0112F(this);
	}
	
	public void doBackDrg0112() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().doBackByDrgIN0112F(this);
	}
	
	public void doCorrectionDrg0112() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().doCorrectionByDrgIN0112F(this);
	}
	
	public void doReAssessDrg0112() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().doReAssessByDrgIN0112F(this);
	}

	public Object doQueryAllDrg0112() throws Exception {
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Drg0007Db  where applNo ="+Common.sqlChar(getApplNo());
		
		System.out.println("[TCBW]-[DRGIN0112F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id desc ", this.getRecordStart(), this.getPageSize());
			
			if(objList!=null && objList.size()>0)
			{
				for(Object dtlObj : objList)
				{
					Drg0007Db dtl = (Drg0007Db)dtlObj;
 
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getAssessDate());
					rowArray[2] = Common.get(dtl.getCheckResult());
					rowArray[3] = Common.get(dtl.getSurvey());
					rowArray[4] = Common.get(dtl.getPrecaution());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		this.setState("queryAllSuccess");
		return arrList;
	}

	//檢查是否有分派權限
	public  String competenceDrg0112() throws Exception
	{
		
	  String hql="  from Con1015Db ";
             hql+=" where con1014Db.code = "+ Common.sqlChar("04");   //評估
             hql+=" and   con1014Db.con1007Db.formCode="+Common.sqlChar("DRG01");
             hql+=" and   competence like "+TCBWCommon.likeSqlChar("4");
             hql+=" and   commonUser.userId="+Common.sqlChar(getUserID());
    
        Con1015Db  c =(Con1015Db)View.getObject(hql);
             
        if(c!=null)
        {
        	return "Y";
        }	
        else
        {
        	return null;
        }	
	}

}
