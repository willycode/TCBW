package com.kangdainfo.tcbw.view.vcos;


import net.htmlparser.jericho.Source;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Con1012Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class VCOS0202F extends SuperBean
{
	String id;
	
	
	@Override
	public Object doQueryOne() throws Exception {
		
		VCOS0202F obj = this;
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

		String hql = " from Con1012Db where  systemType='COS' ";
		    
		System.out.println("VCOS0202F====="+hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				java.util.Iterator it = objList.iterator();
				java.util.Map<String, String> sysMap = TCBWCommon.getCommonCodeMap("SYS");
				
				while (it.hasNext()) 
				{
					Con1012Db o = (Con1012Db) it.next();
					String rowArray[] = new String[4];
					rowArray[0] = Common.get(o.getId());											
					rowArray[1] = Common.get(sysMap.get(o.getSystemType()));
					rowArray[2] = Common.get(o.getCode());
					rowArray[3] = Common.get(o.getName());	
					arrList.add(rowArray);					
				}
			}
		}
		return arrList;
	}
	
	
	
	
	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		
	}
	
	

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	

	
	
}
