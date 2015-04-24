<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="com.kangdainfo.*" %>
<%@ page import="com.kangdainfo.common.util.*" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

JSONArray dsField = new JSONArray();
try{
	java.util.Map<String, String> code = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("MEDENG", null);
	for(java.util.Map.Entry<String, String> dtl : code.entrySet()){
		JSONObject item = new JSONObject();	
		item.put("id", dtl.getKey());
		item.put("name", dtl.getValue());
		dsField.add(item);
	}
	out.write(Common.get(dsField.toString()));
}catch(Exception e){
	e.printStackTrace();
}
%>
