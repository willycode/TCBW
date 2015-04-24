<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="org.json.simple.*"%>
<%@ page import="java.sql.*"%>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
String permitKey = Common.get(request.getParameter("k"));
String permitNo = Common.get(request.getParameter("n"));
Database db = new Database("MLMS");
try 
{
	JSONObject item = new JSONObject();
	java.util.List<Object> parameter = new java.util.ArrayList<Object>();
	
	String hql=" select distinct a.LICEKID,a.LICID1,a.APPUNNO,a.APPNAME";
           hql += " from VW_ForADR_TBMLIC a where 1=1 ";
           hql += " and a.LICEKID=? and a.LICID1=?";
    parameter.add(permitKey);
    parameter.add(permitNo);
    ResultSet rs = db.querySQLByPrepareStatement(hql,parameter);
	if (rs!=null) 
	{
		while (rs.next())
	    { 			
			item.put("licekId",Common.get(rs.getString("LICEKID")));
			item.put("licId1",Common.get(rs.getString("LICID1")));
			item.put("appunNo",Common.get(rs.getString("APPUNNO")));
			item.put("applName",Common.get(rs.getString("APPNAME")));
		}
	}
	out.write(Common.get(item.toString()));
} catch (Exception e) {
	e.printStackTrace();
} finally{
	db.closeAll();	
}
%>
