<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ include file="../home/head.jsp" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
//response.addDateHeader("Expires", 0);

String q = Common.get(request.getParameter("q"));
try {
	CommonDepartment dt = (CommonDepartment) View.getObject("from CommonDepartment where fullCode=" + Common.sqlChar(Common.formatRearZero(q,10)) + " or shortCode=" + Common.sqlChar(q));
	if (dt!=null) {
		JSONObject item=new JSONObject();
		item.put("parentId",dt.getParentId());		
		item.put("Id",dt.getId());
		item.put("department",dt.getDepartment());
		item.put("departmentCode",dt.getDepartmentCode());
		item.put("fullCode",dt.getFullCode());
		item.put("fullName",dt.getFullName());
		item.put("shortCode",Common.get(dt.getShortCode()));
		item.put("shortName",Common.get(dt.getShortName()));
		item.put("preFullCode",Common.get(dt.getPreFullCode()));
		item.put("address", Common.get(dt.getAddress()));
		item.put("phone", Common.get(dt.getPhone()));
		item.put("fax", Common.get(dt.getFax()));
		item.put("chiefName",Common.get(dt.getChiefName()));
		item.put("chiefTitle",Common.get(dt.getChiefTitle()));
		item.put("email",Common.get(dt.getEmail()));	
		out.write(item.toString());
	}
} catch (Exception e) {
	e.printStackTrace();
}
%>
