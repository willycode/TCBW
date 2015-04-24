<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="net.sf.json.*"%>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
String q = Common.get(request.getParameter("q"));
String codeKind = Common.get(request.getParameter("codeKind"));
String preWord = Common.get(request.getParameter("preWord"));
String preCon1 = Common.get(request.getParameter("preCon1"));
String preCon2 = Common.get(request.getParameter("preCon2"));
String preCon3 = Common.get(request.getParameter("preCon3"));

try {
	String hql = "from " + CommonCode.class.getCanonicalName() + " where commonCodeKind.codeKindId=" + Common.sqlChar(codeKind) +
		" and codeId=" + Common.sqlChar(q);
	if (!"".equals(preWord)) hql += " and codeId like " + Common.sqlChar(preWord+"%");
	if (!"".equals(preCon1)) hql += " and codeCon1 like " + Common.sqlChar(preCon1+"%");
	if (!"".equals(preCon2)) hql += " and codeCon2 like " + Common.sqlChar(preCon2+"%");
	if (!"".equals(preCon3)) hql += " and codeCon3 like " + Common.sqlChar(preCon3+"%");	
	CommonCode dt = (CommonCode) View.getObject(hql);
	if (dt!=null) {
		JSONObject item=new JSONObject();			
		item.put("accountingYear","");
		item.put("Id",dt.getId());
		item.put("codeId",dt.getCodeId());
		item.put("codeName",dt.getCodeName());		
		item.put("codeEngName",Common.get(dt.getCodeEngName()));
		item.put("codeCon1",Common.get(dt.getCodeCon1()));
		item.put("codeCon2",Common.get(dt.getCodeCon2()));
		item.put("codeCon3",Common.get(dt.getCodeCon3()));
		item.put("codeMemo",Common.get(dt.getCodeMemo()));
		item.put("parentId",dt.getCommonCodeKind().getId());
		item.put("codeKindId",dt.getCommonCodeKind().getCodeKindId());
		item.put("codeKindName",dt.getCommonCodeKind().getCodeKindName());
		out.write(item.toString());
	}
} catch (Exception e) {
	//e.printStackTrace();
}
%>
