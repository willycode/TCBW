<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
String hql = "from CommonCodeKind order by codeKindId";
java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
if (list!=null && list.size()>0) {
	for (int i=0; i<list.size(); i++) {
		CommonCodeKind master = (CommonCodeKind) list.get(i);
		out.write(i+ "."+ master.getCodeKindId() + " - " + master.getCodeKindName() + "<br>");
		if (master.getCommonCodes()!=null && master.getCommonCodes().size()>0) {
			for (Object dtlObj : master.getCommonCodes()) {
				CommonCode dtl = (CommonCode) dtlObj;
				out.write(dtl.getCodeId() + "." + dtl.getCodeName()+"<br>");
			}
		}
		out.write("<br>");
	}
}
out.write("<hr>");
hql = "select id, codeKindId, codeKindName from CommonCodeKind order by codeKindId";
list = ServiceGetter.getInstance().getCommonService().load(hql);
if (list!=null && list.size()>0) {
	for (int i=0; i<list.size(); i++) {
		Object[] arrObj = (Object[]) list.get(i);
		out.write(Common.getInt(arrObj[0])+ "."+ Common.get(arrObj[1]) + " - " + Common.get(arrObj[2]) + "<br>");
	}
}

out.write("<hr>");
hql = "select count(*) from CommonCodeKind where size(commonCodes)>0";
list = ServiceGetter.getInstance().getCommonService().load(hql);
if (list!=null && list.size()>0) {
	out.write(Common.get(list.get(0)));
}
out.write("<hr>");
hql = "from CommonCode where commonCodeKind.codeKindId='MSG' order by codeId";
list = ServiceGetter.getInstance().getCommonService().load(hql);
if (list!=null && list.size()>0) {
	for (Object dtlObj : list) {
		CommonCode dtl = (CommonCode) dtlObj;
		out.write(dtl.getCodeId() + "." + dtl.getCodeName()+"<br>");
	}
}


//=================== insert, update ====================
if (ServiceGetter.getInstance().getCommonService().loadCount(CommonCodeKind.class, "codeKindId", "TTT")<=0) {
	CommonCodeKind master = new CommonCodeKind();
	master.setCodeKindId("TTT");
	master.setCodeKindName("教育訓練TTT");
	master.setCommonCodes(new java.util.HashSet<CommonCode>());
	for (int i=0; i<10; i++) {
		CommonCode dtl = new CommonCode();
		dtl.setCommonCodeKind(master);
		dtl.setCodeId(Common.formatFrontZero(String.valueOf(i+1), 2));
		dtl.setCodeName("明細" + dtl.getCodeId());
		master.getCommonCodes().add(dtl);
	}
	ServiceGetter.getInstance().getCommonService().getCommonCodeKindDao().saveCommonCodeKind(master);	
}










%>