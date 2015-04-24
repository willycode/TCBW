<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
int progID = Common.getInt(request.getParameter("progID"));
java.util.Map<Integer, CommonGroup> h = new java.util.HashMap<Integer, CommonGroup>();
String hql = "from " + CommonGroup.class.getCanonicalName();
java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
if (list!=null && list.size()>0) {	
	for (int i=0; i<list.size(); i++){
		CommonGroup obj = (CommonGroup) list.get(i);		
		h.put(obj.getId(), obj);
	}	
}

StringBuilder sb = new StringBuilder(500).append("");
hql = "select commonGroup.id, auth from " + CommonAuth.class.getCanonicalName() + " where commonDtree.id=" + progID + " group by commonGroup.id, auth order by auth";
list = ServiceGetter.getInstance().getCommonService().load(hql);
if (list!=null && list.size()>0) {	
	int j=0;
	sb.append("<table class=\"table_form\" width=\"100%\" border='0' cellspacing=\"2\" cellpadding=\"2\"><tr><td nowrap class=\"listTH\">身分別-使用者</td><td nowrap class=\"listTH\">權限</td></tr>");
	for (int i=0; i<list.size(); i++){
		Object[] o = (Object[]) list.get(i);
		CommonGroup obj = h.get(o[0]);
		if (obj!=null) {
			int auth = Common.getInt(o[1]);
			sb.append("<tr class='listTRMouseover'><td nowrap align='left'>").append(obj.getGroupName());
			
			hql = "select commonUser.userId, commonUser.userName from " + CommonUserGroup.class.getCanonicalName() + " where commonGroup.id=" + obj.getId() + " group by commonUser.userId, commonUser.userName order by commonUser.userId, commonUser.userName";
			java.util.List uList = ServiceGetter.getInstance().getCommonService().load(hql);
			if (uList!=null && uList.size()>0) {
				for (j=0; j<uList.size(); j++) {
					Object[] u = (Object[]) uList.get(j);
					sb.append("<li>").append(u[0]).append("-").append(u[1]).append("</li>");
				}
			}
			sb.append("</td>");
			sb.append("<td nowrap class=\"listTR\" valign='top'>").append(auth==2?"維護":"查詢").append("</td></tr>");
		}
	}
	sb.append("</table>");
}


%>
<html>
<head>
<title>功能選單查身份別</title>
<link rel="stylesheet" href="../../js/default.css" type="text/css" />
</head>

<body>
<%=sb.toString()%>
</body>
</html>
