package com.kangdainfo.tcbw.view.drgin;


import javax.swing.table.DefaultTableModel;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Drg0004Db;
import com.kangdainfo.tcbw.model.bo.Drg0005Db;
import com.kangdainfo.tcbw.model.bo.Drg0006Db;
import com.kangdainfo.tcbw.model.bo.Drg0007Db;
import com.kangdainfo.tcbw.model.bo.Drg0008Db;
import com.kangdainfo.tcbw.model.bo.Drg0009Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;

import com.kangdainfo.tcbw.util.TCBWCommon;

public class DRGIN0114F extends DRGIN0112F{	
	
	public void doUpdateDrg0114() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().updateByDrgIN0114F(this);
	}	

	public void doAnalyDrg0114() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().doAnalyByDrgIN0114F(this);
	}
	

	//檢查是否有分派權限
	public  String competenceDrg0114() throws Exception
	{
		
	  String hql="  from Con1015Db ";
             hql+=" where con1014Db.code = "+ Common.sqlChar("05");   //案件分析
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
