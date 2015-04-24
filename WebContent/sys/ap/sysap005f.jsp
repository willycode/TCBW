<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
if (UserRole.getRoleId()>2) {
	String lockUserList = "";
	java.util.Hashtable<String, Integer> u = LoginUtil.getRetryingTable();
	String msg = "";
	
	String btnSubmit = Common.get(request.getParameter("btnSubmit"));	
	if (!"".equals(btnSubmit)) {
		String[] unlockUserList = request.getParameterValues("lockUser");
		if (unlockUserList!=null && unlockUserList.length>0) {
			for (int i=0; i<unlockUserList.length; i++) {
				LoginUtil.removeUserRetrying(unlockUserList[i]);				
			}
			msg = "alert('已成功移除被鎖定之使用者！');";
		}
	}
	
	StringBuilder sb = new StringBuilder().append("");
	for (String key : u.keySet()) {
		Integer count = u.get(key);
		if (count.intValue()>2) {
			sb.append("<option value='").append(key).append("'>" ).append( key ).append( "</option>\n");
		}
	}
	lockUserList = sb.toString();

%>

<html>
<head>
<title>移除鎖定之使用者</title>
<%@ include file="../../home/meta.jsp" %>
<script type="text/javascript">
var insertDefault;  //二維陣列, 新增時, 設定預設值

function init() {
	<%=msg%>
}

</script>
<style type="text/css">
<!--
.maintain {  background-color: #FFAAAA;
  font-size: 12px;
}
.query {  background-color: #AAFFAA;
  font-size: 12px;
}
-->
</style>
</head>
<body topmargin="0" onload="init();">
<br>
<form id="form1" name="form1" method="post">
<table align="center" cellpadding="0" cellspacing="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">

    <table class="table_form" width="100%" height="100%">
    <tr>
    <td nowrap class="td_lable" width="700">請選擇要解除鎖定之使用者</td>
    </tr>
    <tr>
        <td nowrap align="center" class="td_form_white" >
			<select class="field_Q" name="lockUser" size="10" multiple="true" id="lockUser">
				<%=lockUserList%>				
            </select>
        </td>
    </tr>
    <tr>
        <td nowrap class="queryTDInput" colspan="3" style="text-align:center;">
        	<input class="toolbar_default" followPK="false" type="submit" name="btnSubmit" value="確　　定" >
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
