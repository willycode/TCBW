<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.json.simple.*"%>
<%@ include file="../home/head.jsp" %>
<%@ page import="java.sql.*"%>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String permitKey = Common.get(request.getParameter("k"));
String permitNo = Common.get(request.getParameter("n"));
String fileName = Common.get(request.getParameter("f"));
try 
{
	Database db2 = new Database("MLMS");
	java.util.List<Object> parameter = new java.util.ArrayList<Object>();
	String hql = " select distinct a.ENNAME as enProduct, a.ENNAME as productName ,a.APPNAME as applicationName, "
		+ " a.FACTNAME as manufactorName, a.APPUNNO as medModel, a.INGRMA as scientificName, "
		+ " a.CHNAME as chProduct, a.INGRMA as ingredient "
		+ " from VW_ForADR_TBMLIC a where a.LICEKID= ? and a.LICID1= ?";

	parameter.add(permitKey);
	parameter.add(permitNo);
	ResultSet rs = db2.querySQLByPrepareStatement(hql,parameter);
	if (rs!=null) 
	{
		JSONObject item = new JSONObject();
		while (rs.next())
		{ 			
			item.put("obj", rs.getString(fileName));	
		}
		out.write(Common.get(item.toString()));
	}		
} catch (Exception e) {
	e.printStackTrace();
}
%>
