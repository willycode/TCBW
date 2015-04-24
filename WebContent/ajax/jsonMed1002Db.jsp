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
	Med1002Db[] dt=null;
	
	String hql=" from Med1002Db order by id ";
	
	
	java.util.List<Med1002Db> depts = ServiceGetter.getInstance().getCommonService().load(hql);
	
	if(depts!=null && depts.size()>0)
	{
		dt = new Med1002Db[depts.size()];
		int i=0;
		for (Med1002Db idx:depts) 
		{
			dt[i]=idx;
			i++;
		}
	}

	if (dt!=null && dt.length>0) {
		for (int i=0; i<dt.length; i++) {
			JSONObject item = new JSONObject();	
			//id,isYes,isYesType,isNo,isNoType,isUnknown,isUnknownType 
			item.put("id",dt[i].getId());
			item.put("isYes",dt[i].getIsYes());
			item.put("isYesType",dt[i].getIsYesType());
			item.put("isNo",dt[i].getIsNo());
			item.put("isNoType",dt[i].getIsNoType());
			item.put("isUnknown",dt[i].getIsUnknown());
			item.put("isUnknownType",dt[i].getIsUnknownType());
			dsField.add(item);			
		}
		out.write(dsField.toString());
	}		
} catch (Exception e) {
	e.printStackTrace();
}
%>
