<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.io.*"%>
<%@ include file="head.jsp" %>
<%
long id = Common.getLong(request.getParameter("id"));

if (id > 0) {		
	ServiceGetter.getInstance().getTcbwService().getConinDao().deleteCon0001Db(id);	
}
%>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">    
    if (isObj(opener.document.all.item("state")))
	    opener.document.all.item("state").value="upload";
    window.opener.checkField();
    window.close();
</script>