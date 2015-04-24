package com.kangdainfo.tcbw.view.dynamic;


import java.util.Iterator;
import java.util.List;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Variant0001Db;
import com.kangdainfo.tcbw.model.bo.Variant0002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DYNAMICLIST extends SuperBean
{
	String id;
	private String q_name;
	private String q_explain;
	private String q_kind;
	
	private String content;
	private String code;
	
	@Override
	public Object doQueryAll() throws Exception 
	{
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Variant0001Db where 1=1  ";
		
		if(null != getQ_kind() && !"".equals(getQ_kind()))
		{
		   hql += " and kind = " + Common.sqlChar(getQ_kind());
		}
		
		if(null != getQ_name() && !"".equals(getQ_name()))
		{
			hql += " and name like " + TCBWCommon.likeSqlChar(getQ_name());
		}
		
		if(null != getQ_explain() && !"".equals(getQ_explain()))
		{
			hql += " and explain like " + TCBWCommon.likeSqlChar(getQ_explain());
		}
		
		System.out.println(hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			List objList = ServiceGetter.getInstance().getCommonService().load(hql, this.getRecordStart(), this.getPageSize());
			
			if (objList != null && !objList.isEmpty()) 
			{
				Iterator it = objList.iterator();
				while (it.hasNext())
				{
					Variant0001Db o = (Variant0001Db) it.next();
					String rowArray[] = new String[5];	
					rowArray[0] = Common.get(o.getId());
					rowArray[1] = Common.get(o.getName());		
					rowArray[2] = Common.get(o.getExplain());	
					
					Variant0002Db c = (Variant0002Db) View.getObject("from Variant0002Db where variant0001Db.id="+Common.getLong(Common.get(o.getId())));
					
					if(c!=null)
					{
						rowArray[3] = Common.get(c.getContent());	
						rowArray[4] = Common.get(c.getCode());	
					}	
					
					arrList.add(rowArray);	
				}
			}
		}
		return arrList;
	}

	
	
	
	@Override
	public Object doQueryOne() throws Exception 
	{
		// TODO Auto-generated method stub
		
		DYNAMICLIST obj=this;
		
		Variant0002Db c = (Variant0002Db) View.getObject("from Variant0002Db where variant0001Db.id="+Common.getLong(getId()));
		System.out.println("cc=="+c);
		if(c!=null)
		{
			obj.setContent(c.getContent());	
			obj.setCode(c.getCode());	
		}	
		
		return obj;
	}

	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub		
	}
	
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	public String getQ_name() {return checkGet(q_name);}
	public void setQ_name(String qName) {q_name = checkSet(qName);}
	public String getQ_explain() {return checkGet(q_explain);}
	public void setQ_explain(String qExplain) {q_explain = checkSet(qExplain);}
	public String getQ_kind() {return checkGet(q_kind);}
	public void setQ_kind(String qKind) {q_kind = checkSet(qKind);}
	public String getContent() {return checkGet(content);}
	public void setContent(String content) {this.content = checkSet(content);}
	public String getCode() {return checkGet(code);}
	public void setCode(String code) {this.code = checkSet(code);}            
	
}
