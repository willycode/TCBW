<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="org.json.simple.*"%>
<%@ page import="java.sql.*"%>
<%

response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String med9001Id = Common.get(request.getParameter("med9001Id"));
String reporttype = Common.get(request.getParameter("reporttype"));
String reportIssueno = Common.get(request.getParameter("reportIssueno"));
String prehanddate = Common.get(request.getParameter("prehanddate"));

JSONArray dsField = new JSONArray();
try 
{
	Med9002Db[] dt=null;
	
	String hql = " from Med9002Db where 1=1 ";
    hql += " and med9001Db.id=" + Common.getLong(med9001Id);
    hql += " and prehanddate<" + Common.sqlChar(prehanddate);
    hql += " and reportIssueno<>" + Common.getLong(reportIssueno);
    hql += " and isnull(handdate,'')=''";
	System.out.println("HQL: " + hql);
	
	java.util.List<Med9002Db> depts = ServiceGetter.getInstance().getCommonService().load(hql);
	
	System.out.println("LEN: " + depts.size());
	if(depts!=null && depts.size()>0)
	{
		dt = new Med9002Db[depts.size()];
		int i=0;
		for (Med9002Db idx:depts) {
			dt[i]=idx;
			i++;
		}
	}
	if (dt!=null && dt.length>0) {
		for (int i=0; i<dt.length; i++) {
			JSONObject item = new JSONObject();				
			item.put("reportIssueno",dt[i].getReportIssueno());
			dsField.add(item);			
		}
		out.write(dsField.toString());
	}		
} catch (Exception e) {
	e.printStackTrace();
}
%>