<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.json.simple.*"%>
<%@ include file="../home/head.jsp" %>
<%@ page import="com.kangdainfo.tcbw.model.bo.Con1014Db" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String q_con1007_id = Common.get(request.getParameter("q_con1007_id"));

JSONArray dsField = new JSONArray();
try 
{
	Con1014Db[] dt=null;
	
	String hql=" from Con1014Db where 1 = 1 ";
	
	if(!"".equals(q_con1007_id))
		hql+=" and con1007_id like "+Common.sqlChar("%" + q_con1007_id + "%");
	
	java.util.List<Con1014Db> depts = ServiceGetter.getInstance().getCommonService().load(hql);
	
	if(depts!=null && depts.size()>0)
	{
		dt = new Con1014Db[depts.size()];
		int i=0;
		for (Con1014Db idx:depts) {
			dt[i]=idx;
			i++;
		}
	}

	if (dt!=null && dt.length>0) {
		for (int i=0; i<dt.length; i++) {
			JSONObject item = new JSONObject();				
			item.put("name",dt[i].getName());
			item.put("id",dt[i].getId());
			dsField.add(item);			
		}
		out.write(dsField.toString());
	}		
} catch (Exception e) {
	e.printStackTrace();
}
%>
