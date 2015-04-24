<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ include file="../home/head.jsp" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
//response.addDateHeader("Expires", 1);

String pid = Common.get(request.getParameter("pid"));
String fullCode = Common.get(request.getParameter("fullCode"));
String shortCode = Common.get(request.getParameter("shortCode"));

JSONArray dsField = new JSONArray();
try {
	Integer parentId = null;	
	if (!"".equals(pid)) parentId = Common.getInt(pid);		
	else if (!"".equals(fullCode)) {
		parentId = Common.getInt(View.getObject("select id from CommonDepartment where fullCode=" + Common.sqlChar(fullCode)));
	} else if (!"".equals(shortCode)) {
		parentId = Common.getInt(View.getObject("select id from CommonDepartment where shortCode=" + Common.sqlChar(shortCode)));
	}
	if (parentId!=null) {
		CommonDepartment[] dt = ServiceGetter.getInstance().getCommonService().getCommonDepartmentDao().getChildNodes(parentId,false);
		if (dt!=null && dt.length>0) {
			for (int i=0; i<dt.length; i++) {
				JSONObject item=new JSONObject();				
				item.put("parentId",dt[i].getParentId());		
				item.put("Id",dt[i].getId());
				item.put("department",dt[i].getDepartment());
				item.put("departmentCode",dt[i].getDepartmentCode());
				item.put("fullCode",dt[i].getFullCode());
				item.put("fullName",dt[i].getFullName());
				item.put("shortCode",dt[i].getShortCode());
				item.put("zip",dt[i].getZip());
				dsField.add(item);			
			}
			out.write(dsField.toString());
		}		
	}
	
} catch (Exception e) {
	e.printStackTrace();
}
%>
