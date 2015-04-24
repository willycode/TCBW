<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.json.simple.*"%>
<%@ include file="../home/head.jsp" %>
<%@ page import="java.sql.*"%>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String dbType = Common.get(request.getParameter("d"));
String revision = Common.get(request.getParameter("r"));
String fileName = Common.get(request.getParameter("f"));
String applNo = Common.get(request.getParameter("a"));
String medType = Common.get(request.getParameter("m"));
try 
{
	String hql ="";
	if(dbType.equals("DRG5001")){
		hql = " select a."+fileName+" from Drg5001Db a where a.applNo= "+Common.sqlChar(applNo)+" and a.revision = "+Common.sqlChar(revision);
	}
	else if(dbType.equals("DRG6001")){
		hql = " select a."+fileName+" from Drg6001Db a where a.applNo= "+Common.sqlChar(applNo)+" and a.revision = "+Common.sqlChar(revision);
	}
	else if(dbType.equals("DRG6003")){
		hql = " select b."+fileName+" from Drg6003Db b,Drg6001Db a where a.id=b.drg6001Db.id "+
		      " and a.applNo= "+Common.sqlChar(applNo)+" and a.revision = "+Common.sqlChar(revision)+
		      " and b.medType="+Common.sqlChar(medType);
	}	
	String fieldValue = View.getLookupField(hql);
	if(true)		
	{
		JSONObject item = new JSONObject();
					
		item.put("obj", Common.get(fieldValue));	
		
		out.write(Common.get(item.toString()));
	}		
} catch (Exception e) {
	e.printStackTrace();
}
%>
