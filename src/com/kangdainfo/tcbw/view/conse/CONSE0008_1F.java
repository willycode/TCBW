package com.kangdainfo.tcbw.view.conse;

import java.util.ArrayList;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1002Db;
import com.kangdainfo.tcbw.model.bo.Con1007Db;
import com.kangdainfo.tcbw.model.bo.Con1008Db;

public class CONSE0008_1F extends SuperBean{

	private String[] id;	

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Con1008Db ";
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id");
			
			if (objList != null && objList.size() > 0) 
			{
				for(Object dtlObj : objList) 
				{	
					Con1008Db dtl = (Con1008Db)dtlObj;
					String[] rowArray = new String[6];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getIsRequired());
					rowArray[2] = Common.get(dtl.getChName());
					rowArray[3] = Common.get(dtl.getIsDate()).equals("Y")?"是":Common.get(dtl.getIsDate()).equals("N")?"否":"";
					rowArray[4] = Common.get(dtl.getIsNum()).equals("Y")?"是":Common.get(dtl.getIsNum()).equals("N")?"否":"";
					rowArray[5] = Common.get(dtl.getIsLength());
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
	public void doUpdate() throws Exception 
	{
		String updateId = "";
		for(String s: getId())
		{
			updateId += s + ",";
		}
		//Set Y
		java.util.ArrayList<Con1008Db> com1008List = (ArrayList<Con1008Db>)ServiceGetter.getInstance().getTcbwService().load(" from Con1008Db where isRequired != 'S' and id in (" + updateId.substring(0, updateId.length()-1) +")");
		java.util.List<Con1008Db> updateList = new java.util.ArrayList<Con1008Db>();
		for(Con1008Db obj : com1008List){
			obj.setIsRequired("Y");
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
		    updateList.add(obj);
		}
		ServiceGetter.getInstance().getTcbwService().updateBatch(updateList);
		
		//Set N
		com1008List.clear();
		updateList.clear();
		com1008List = (ArrayList<Con1008Db>)ServiceGetter.getInstance().getTcbwService().load(" from Con1008Db where isRequired != 'S' and id not in (" + updateId.substring(0, updateId.length()-1) +")");
		for(Con1008Db obj : com1008List){
			obj.setIsRequired("N");
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
		    updateList.add(obj);
		}
		ServiceGetter.getInstance().getTcbwService().updateBatch(updateList);
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
	}

}
