<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../home/head.jsp" %>
<%
if (UserRole.getRoleId()>2) {	
	String btnSubmit = Common.get(request.getParameter("btnSubmit"));
	
	String s = Common.checkGet(request.getParameter("msg"));
	if (!"".equals(btnSubmit) && !Validate.checkSpecialChar(s)) {
		MsgUtil.setMsg(s);
	}
%>

<html>
<head>
<%@ include file="../../home/meta.jsp" %>
<script type="text/javascript">
function checkField(){
    var alertStr=checkEmpty(form1.msg,"廣播訊息");
    if(alertStr.length!=0){ alert(alertStr); return false; }
    return true;
}
</script>
</head>
<body topmargin="0">
<br>
<form id="form1" name="form1" method="post">
<table align="center" cellpadding="0" cellspacing="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">

    <table class="table_form" width="100%" height="100%">
    <tr>
    <td nowrap class="td_lable">請輸入要廣播的訊息</td>
    </tr>
    <tr>
        <td nowrap align="center" class="td_form_white" >
            <textarea name="msg" cols="60" rows="4" class="field"><%=MsgUtil.getMsg()%></textarea></td>
    </tr>
    <tr>
        <td nowrap class="queryTDInput" colspan="3" style="text-align:center;">
            <input class="toolbar_default" type="submit" name="btnSubmit" value="送　　出" >
            　    </td>
    </tr>
    </table>

</td></tr>
</table>
</form>
</body>
</html>
<%
}
%>
