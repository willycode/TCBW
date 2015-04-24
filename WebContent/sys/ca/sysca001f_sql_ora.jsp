<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
StringBuilder sb = new StringBuilder();
StringBuilder dt = new StringBuilder();
String hql = "from CommonCodeKind order by id";
java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
if (list!=null && list.size()>0) {
	for (int i=0; i<list.size(); i++) {
		CommonCodeKind obj = (CommonCodeKind) list.get(i);
		sb.append("insert into COMMON_CODEKIND(id,codeKindId,codeKindName) values(COMMON_CODEKIND_SEQ.NEXTVAL,'" ).append( obj.getCodeKindId() ).append( "','" ).append( obj.getCodeKindName()).append("');<br>");
		if (obj.getCommonCodes()!=null && obj.getCommonCodes().size()>0) {
			for (Object dtlObj : obj.getCommonCodes()) {
				CommonCode dtl = (CommonCode) dtlObj;
				dt.append("insert into COMMON_CODE(id,codeKindId,codeId,codeName,codeCon1,codeCon2,codeCon3,codeMemo) values(COMMON_CODE_SEQ.NEXTVAL,").append(i+1).append(",'").append(dtl.getCodeId()).append("','").append(dtl.getCodeName()).append("'");
				dt.append(",").append(Common.sqlChar(dtl.getCodeCon1()));
				dt.append(",").append(Common.sqlChar(dtl.getCodeCon2()));
				dt.append(",").append(Common.sqlChar(dtl.getCodeCon3()));
				dt.append(",").append(Common.sqlChar(dtl.getCodeMemo()));
				dt.append(");<br>");				
			}
		}
	}
}

out.write(sb.toString()+"<br><br>");
out.write(dt.toString()+"<br><br>");
%>
