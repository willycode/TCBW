<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.json.simple.*"%>
<%@ include file="../home/head.jsp" %>
<%@ page import="com.kangdainfo.tcbw.model.bo.Con1007Db" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

JSONArray dsField = new JSONArray();
try 
{
	Med1003Db[] dt=null;
	
	String hql=" from Med1003Db order by id ";
	
	
	java.util.List<Med1003Db> depts = ServiceGetter.getInstance().getCommonService().load(hql);
	
	if(depts!=null && depts.size()>0)
	{
		dt = new Med1003Db[depts.size()];
		int i=0;
		for (Med1003Db idx:depts) 
		{
			dt[i]=idx;
			i++;
		}
	}

	if (dt!=null && dt.length>0) 
	{
		for (int i=0; i<dt.length; i++) 
		{
			JSONObject item = new JSONObject();	
			//id,isYes,isYesType,isNo,isNoType
			item.put("id",dt[i].getId());
			item.put("isYes",dt[i].getIsYes());
			item.put("isYesType",dt[i].getIsYesType());
			item.put("isNo",dt[i].getIsNo());
			item.put("isNoType",dt[i].getIsNoType());
			dsField.add(item);			
		}
		System.out.println(dsField.toString());
		out.write(dsField.toString());
	}		
} catch (Exception e) {
	e.printStackTrace();
}
%>
