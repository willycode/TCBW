package com.kangdainfo.tcbw.view.drgin;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Drg1002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;



public class DRGIN0106F extends DRGIN0102F{
	
	public void doUpdateDrg0106() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().updateByDrgIN0106F(this);
	}
	
	public void backPiecesDrg0106() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().backPiecesByDrgIN0106F(this);
	}
	
	public void backForMailDrg0106() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().backForMailByDrgIN0106F(this);
	}
	
	public void doAccessDrg0106() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().doAccessByDrgIN0106F(this);
	}
	
	public void doPostDocDrg0106() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().doPostDocByDrgIN0106F(this);
	}

	public Object doQueryAllDrg0106() throws Exception {
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Drg0003Db  where applNo ="+Common.sqlChar(getApplNo());
		
		System.out.println("[TCBW]-[DRGIN0106F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id desc ", this.getRecordStart(), this.getPageSize());
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> rklMap = TCBWCommon.getCommonCodeMap("DRGRKL"); //風險評估結果
				for(Object dtlObj : objList)
				{
					Drg0003Db dtl = (Drg0003Db)dtlObj;
					
					//通報時效: 1.時效佳            2.時效待加強
					String notifierAging = "1".equals(Common.get(dtl.getNotifierAging()))?"時效佳":"2".equals(Common.get(dtl.getNotifierAging()))?"時效待加強":"";
					//通報品質: 1.Excellent 2.Good 3.Fair
					String notifierQuality = "1".equals(Common.get(dtl.getNotifierQuality()))?"Excellent":
						                     "2".equals(Common.get(dtl.getNotifierQuality()))?"Good":
							                 "3".equals(Common.get(dtl.getNotifierQuality()))?"Fair":"";
					
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getAssessDate());
					rowArray[2] = Common.get(dtl.getAssessMan());
					rowArray[3] = Common.get(dtl.getFirstResult()!=null?rklMap.get(dtl.getFirstResult()):"");
					rowArray[4] = Common.get(notifierAging);
					rowArray[5] = Common.get(notifierQuality);
					rowArray[6] = Common.get(dtl.getRemark());
					arrList.add(rowArray);
				}
				if(rklMap != null) rklMap.clear();
				objList.clear();
			}
		}
		this.setState("queryAllSuccess");
		return arrList;
	}


	//檢查是否有分派權限
	public  String competenceDrg0106() throws Exception
	{
		
	  String hql="  from Con1015Db ";
             hql+=" where con1014Db.code = "+ Common.sqlChar("02");   //分級
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
