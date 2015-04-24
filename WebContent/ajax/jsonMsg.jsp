<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="net.sf.json.*"%>
<%
//如果沒有設定驗證filter，請自行加權限驗證，否則請勿使用本程式..

response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
try {	
	com.kangdainfo.common.view.sys.wm.SYSWM003F obj = new com.kangdainfo.common.view.sys.wm.SYSWM003F();
	JSONArray dsField = new JSONArray();
	java.util.ArrayList<String[]> list = obj.getUnReadMsg(User, true);		
	if (list!=null && list.size()>0) {
		int j=0;
		for (int i=0; i<list.size(); i++) {
			String[] x = list.get(i);
			JSONObject item=new JSONObject();
			for (j=0; j<x.length; j++) item.put("obj"+j,x[j]);
			dsField.add(item);
		}
		out.write(dsField.toString());
	}			
} catch (Exception e) {
	e.printStackTrace();
}
%>


