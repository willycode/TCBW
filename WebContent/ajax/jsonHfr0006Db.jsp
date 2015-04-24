<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="net.sf.json.util.*"%>
<%@ page import="com.kangdainfo.tcbw.model.bo.Hfr0006Db" %>
<%@ page import="com.kangdainfo.tcbw.model.bo.Hfr1001Db" %>
<%
String q = Common.get(request.getParameter("id"));
if (!"".equals(q)) {
	Hfr0006Db obj = (Hfr0006Db) View.getObject("from Hfr0006Db where id = " + Common.sqlChar(q));
	if (obj != null) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"hfr0001Db"});
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);		
		JSONObject json = JSONObject.fromObject(obj, jsonConfig);
		
		Hfr1002Db tmp = (Hfr1002Db)View.getObject(" from Hfr1002Db where id = " + Common.getLong(obj.getEvaluateCommittee()));
		if(tmp != null){
			if(tmp.getHfr1001Db() != null){
				json.put("cId", Common.get(tmp.getId()));
				json.put("cName", Common.get(tmp.getHfr1001Db().getName()));
			}
		}else{
			json.put("cId", "");
			json.put("cName", "");
		}
		out.write(json.toString());		
	}
}
%>