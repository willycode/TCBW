<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ap.SYSAP003F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
String sys_id = Common.checkGet(""+session.getAttribute(WebConstants.SESSION_CURRENT_SYSTEM_ID));
int userId = Common.getInt(request.getParameter("userId"));
CommonUser commonUser = ServiceGetter.getInstance().getCommonService().getCommonUserDao().loadCommonUser(userId);
if (commonUser!=null) {
	CommonUserRole userRole = new CommonUserRole();
	userRole.setId(commonUser.getId());
	userRole.setRoleId(commonUser.getRoleId());
	userRole.setCommonGroup(commonUser.getCommonGroup());	


%>

<html>
<head>
<title>使用者被授使用的功能選單</title>
<link rel="stylesheet" href="../../js/default.css" type="text/css" />
<link rel="stylesheet" href="../../js/dtree.css" type="text/css" />
<style>
.bg {
  background-color: #FBFFFD;
  border: 1px solid #EDEDED;
  color: #000000;
  cursor: default;
  left: 0px;
  margin: 1px;
  padding: 2px 6px 0px 6px;
  top: 0px;
  width:175px;
  height:400px;
 }


.root{
  background-color: #FFFFFF;
}

.folder{
  background-color: #FFFFFF;
}

.program{
  background-color: #BBBBFF;
  font-size: 12px;
}

.auth1{
  background-color: #AAFFAA;
  font-size: 12px;

}

.auth2{
  background-color: #FFAAAA;
  font-size: 12px;

}

.showAll{
  background-color: #FFFFF;
  font-size: 12px;
}
</style>
<script type="text/javascript" src="../../js/dtree.js"></script>
<script type="text/javascript">

</script>
</head>

<body>
<form id="form1" name="form1" method="post">
<table border="1" width="100%">
	<tr>
	    <td nowrap width="45%" valign="top">
		<div id="d" class="dtree">
			<a href="javascript: d.openAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("open_all"))%></a> | <a href="javascript: d.closeAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("close_all"))%></a><br /><br />

			<script type="text/javascript">
				<!--
				var d = new dTree('d','../../images/');				
				d.config.folderLinks=true;
				d.config.useCookies=false;
				d.config.useIcons = false;
				<%=ServiceGetter.getInstance().getCommonService().getCommonAuthDao().buildAuthorizeMenu("d","功能選單", sys_id, userRole, "")%>		
				
				document.write(d);
				//-->
			</script>

		</div>
		</td>
	</tr>

</table>
</form>
</body>
</html>
<%
} else {
	out.write("錯誤，使用者資訊不足！");	
}
%>
