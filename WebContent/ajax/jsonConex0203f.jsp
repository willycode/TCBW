<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.json.simple.*"%>
<%@ include file="../home/head.jsp" %>
<%@ page import="com.kangdainfo.tcbw.model.bo.Con1002Db" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String zipname = Common.get(request.getParameter("zipname"));
JSONArray dsField = new JSONArray();
try 
{
	Con1002Db[] dt=null;
	
	String hql=" from Con1002Db where 1 = 1 ";
	
	 if(!"".equals(zipname))
		hql+=" and zipCode = "+Common.sqlChar(zipname);
	
	java.util.List<Con1002Db> depts = ServiceGetter.getInstance().getCommonService().load(hql);
	System.out.println("????: " + depts.toString());
	if(depts!=null && depts.size()>0)
	{
		dt = new Con1002Db[depts.size()];
		int i=0;
		for (Con1002Db idx:depts) {
			dt[i]=idx;
			i++;
		}
	}

	if (dt!=null && dt.length>0) {
		for (int i=0; i<dt.length; i++) {
			JSONObject item = new JSONObject();				
			item.put("zipCode",dt[i].getZipcode());
			item.put("id",dt[i].getId());
			dsField.add(item);
			
		}
		out.write(dsField.toString());
	}		
} catch (Exception e) {
	e.printStackTrace();
}
%>
