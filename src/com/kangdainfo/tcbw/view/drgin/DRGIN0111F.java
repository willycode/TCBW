package com.kangdainfo.tcbw.view.drgin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg0004Db;

public class DRGIN0111F extends SuperBean{
	
	
	private String applNo;
	private String postDate;  //發文日期
	private String postNo;	//發文文號	
	
	public String getApplNo() {return applNo;}
	public void setApplNo(String applNo) {this.applNo = applNo;}
	public String getPostDate() {return postDate;}
	public void setPostDate(String postDate) {this.postDate = postDate;}
	public String getPostNo() {return postNo;}
	public void setPostNo(String postNo) {this.postNo = postNo;}

	@Override
	public Object doQueryOne() throws Exception {
		DRGIN0111F obj = this;
		Drg0004Db c = (Drg0004Db) View.getObject(" from Drg0004Db where applNo="+Common.sqlChar(getApplNo()));
		
		if(c != null){
			obj.setPostDate(Common.get(c.getPostDate()));
			obj.setPostNo(Common.get(c.getPostNo()));
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		return null;
	}

	@Override
	public void doCreate() throws Exception{}

	@Override
	public void doUpdate() throws Exception
	{
		Drg0004Db obj = (Drg0004Db)View.getObject(" from Drg0004Db where applNo = " + Common.sqlChar(getApplNo()));
		if(obj!=null)
		{
			obj.setPostDate(Common.get(getPostDate()));
			obj.setPostNo(Common.get(getPostNo()));
			
			ServiceGetter.getInstance().getTcbwService().update(obj);
		}	
		
	}

	@Override
	public void doDelete() throws Exception{}

	
}


