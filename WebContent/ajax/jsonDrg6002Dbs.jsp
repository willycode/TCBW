<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="org.json.simple.*"%>
<%
//如果沒有設定驗證filter，請自行加權限驗證，否則請勿使用本程式..

response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
Long q = Common.getLong(request.getParameter("q"));
try {
	if (!"".equals(q) && q > 0) {
		JSONArray dsField = new JSONArray();
		java.util.List<Drg6002Db> list = ServiceGetter.getInstance().getCommonService().load(" from Drg6002Db where drg6001Db.id = " + q +" order by id ");
		if (list!=null && list.size()>0) {
			for(Drg6002Db drg62:list){
				JSONObject item=new JSONObject();
				item.put("id",Common.get(drg62.getId()));
				item.put("testDate",Common.get(drg62.getTestDate()));
				item.put("testItems",Common.get(drg62.getTestItems()));
				item.put("testNum",Common.get(drg62.getTestNum()));
				dsField.add(item);		
			}
			out.write(dsField.toString());
		}
	}
} catch (Exception e) {
	e.printStackTrace();
}
%>


