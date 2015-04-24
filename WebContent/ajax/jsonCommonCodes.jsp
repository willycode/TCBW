<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="org.json.simple.*"%>
<%@ page import="java.util.*"%>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
String codeKindId = Common.get(request.getParameter("codeKindId"));
String codeId = Common.get(request.getParameter("codeId"));
try {
	JSONArray items=new JSONArray(); //List
	if(null != codeKindId && !"".equals(codeKindId)){
		String hql = " from CommonCode where 1=1 ";
		if(null != codeKindId && !"".equals(codeKindId))
			hql += " and commonCodeKind.codeKindId =" + Common.sqlChar(codeKindId);
		if(null != codeId && !"".equals(codeId))
			hql += " and codeId like " + Common.likeSqlChar(codeId);
		List<CommonCode> codeList = ServiceGetter.getInstance().getCommonService().load(hql);
		
		if (null != codeList && !codeList.isEmpty()) {
			for(CommonCode code : codeList){
				JSONObject item=new JSONObject();	//object
				item.put("codeId",code.getCodeId());
				item.put("codeName",code.getCodeName());		
				items.add(item);
			}
			out.write(items.toString());
		}
	}
} catch (Exception e) {
}
%>
