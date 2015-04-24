<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.json.simple.*"%>
<%@ include file="../home/head.jsp" %>
<%@ page import="com.kangdainfo.tcbw.model.bo.Con1007Db" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String sysKind = Common.get(request.getParameter("sysKind"));

JSONArray dsField = new JSONArray();
try 
{
	Con1007Db[] dt=null;
	
	String hql=" from Con1007Db where 1 = 1 ";
	if("COS".equals(sysKind)) {
		hql += "and formCode in ('COS01') ";
	} else if("MED".equals(sysKind)) {
		hql += "and formCode in ('MED03','MED04')";
	} else if("DRG".equals(sysKind)) {
		hql += "and formCode in ('DRG01','DRG02')";
	} else if("HFR".equals(sysKind)) {
		hql += "and formCode in ('HFR01')";
	} else if(!"".equals(sysKind)) {
		hql+=" and formCode like "+Common.sqlChar("%" + sysKind + "%");
	}
	System.out.println("HQL: " + hql);
	
	java.util.List<Con1007Db> depts = ServiceGetter.getInstance().getCommonService().load(hql);
	
	if(depts!=null && depts.size()>0)
	{
		dt = new Con1007Db[depts.size()];
		int i=0;
		for (Con1007Db idx:depts) {
			dt[i]=idx;
			i++;
		}
	}

	if (dt!=null && dt.length>0) {
		for (int i=0; i<dt.length; i++) {
			JSONObject item = new JSONObject();				
			item.put("formdName",dt[i].getFormdName());
			item.put("id",dt[i].getId());
			dsField.add(item);			
		}
		out.write(dsField.toString());
	}		
} catch (Exception e) {
	e.printStackTrace();
}
%>
