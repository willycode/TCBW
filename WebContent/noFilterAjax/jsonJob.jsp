<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="com.kangdainfo.*" %>
<%@ page import="com.kangdainfo.common.util.*" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
//response.addDateHeader("Expires", 1);

JSONArray dsField = new JSONArray();
try{	
	java.util.List objList = ServiceGetter.getInstance().getCommonService().load(" select codeId, codeName from CommonCode where commonCodeKind.codeKindId='TITLE' ");
	if(objList!=null && objList.size()>0){
		for(Object dtlObj : objList){
			Object[] dtl = (Object[])dtlObj;
			JSONObject item = new JSONObject();	
			item.put("id", Common.get(dtl[0]));
			item.put("name", Common.get(dtl[1]));
			dsField.add(item);
		}
		objList.clear();
	}
	out.write(Common.get(dsField.toString()));
}catch(Exception e){
	e.printStackTrace();
}
%>
