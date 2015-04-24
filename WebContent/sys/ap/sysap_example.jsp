<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../home/head.jsp" %>
<%
//Example 1:
String hql = "from CommonUser where commonDepartment.fullCod='01' ";
java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
if (list!=null && list.size()>0) {
	for (int i=0; i<list.size(); i++) {
		CommonUser o = (CommonUser) list.get(i);
		o.getUserName();
		out.write(o.getCommonDepartment().getDepartment());
		
		if (o.getCommonGroup().getCommonUsers()!=null && o.getCommonGroup().getCommonUsers().size()>0) {
			for (Object dtlObj : o.getCommonGroup().getCommonUsers()) {
				CommonUser dtl = (CommonUser) dtlObj;
				
			}
		}
	}
}


hql = "select id, userId, userName from CommonUser order by commonDepartment.fullCode, commonGroup.code ";
list = ServiceGetter.getInstance().getCommonService().load(hql);
if (list!=null && list.size()>0) {
	for (int i=0; i<list.size(); i++) {
		Object[] o = (Object[]) list.get(i);
		o[0] = "";
	}
}

hql = "select id, userId, userName from CommonUser order by commonDepartment.fullCode, commonGroup.code ";
Object[] arrObj = View.getObjects(hql);

hql = "select userName from commonUser where id=1";
String userName = View.getLookupField(hql);




%>