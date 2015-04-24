<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.kangdainfo.common.model.bo.*" %>
<%@ page import="com.kangdainfo.tcbw.util.TCBWCommon" %>
<%@ page import="com.kangdainfo.common.util.*" %>
<%@ page import="com.kangdainfo.*" %>
<%@ page import="org.owasp.esapi.ESAPI" %>
<%
	String pId = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("userId")));
	
    String rand = (String)session.getAttribute("rand");
    String input = request.getParameter("rand");
	

    String msg = "";
	
	if(pId!=null && !"".equals(pId))
	{
		CommonUser c = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(pId));
		if(c != null)
		{
			if(rand.equals(input))
			{
				if(!"".equals(Common.get(c.getUserEmail())))
				{
					if (!"".equals(Common.get(c.getUserEmail())) && Validate.checkEmail(Common.get(c.getUserEmail()))) {
						String newPassword = Common.getRandomPassword(8);
						java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
						mailList.add(new javax.mail.internet.InternetAddress(Common.get(c.getUserEmail())));
						try
						{
							ServiceGetter.getInstance().getCommonService().sendEmail(c.getUserName()+"， 這是您要求的新密碼", newPassword, mailList, null, true);
						}
						catch(Exception e)
						{
							e.printStackTrace();
							msg = "4";
						}
						if("".equals(msg))
						{
							c.setUserPwd(Common.getDigestString(newPassword, "SHA-1"));
							c.setIsGetBackPW("Y");
							ServiceGetter.getInstance().getCommonService().update(c);
							msg = "5";
						}
					} 
					else 
					{
						msg = "3";
					}
				}
				else
				{
					msg = "2";
				}
			}
			else
			{
				msg = "6";
			}
			
		}
		else
		{
			msg = "1";
		}
	}
	else
	{
	//	msg = "0";
	}
%>
<html>
<head>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="js/default.css" type="text/css"/>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript">
function init()
{
	if("<%=msg%>" != "")
	{
		switch("<%=msg%>"){
		case "1":
			alert("查無該使用者，請重新輸入!");
			window.close();
			break;
		case "2":
			alert("無信箱網址，無法發送!");
			window.close();
			break;
		case "3":
			alert("EMAIL資料有誤，無法發送!");
			window.close();
			break;
		case "4":
			alert("發送EMAIL時，發生錯誤 ，請連絡管理者!");
			window.close();
			break;
		case "5":
			alert("已將新密碼發送至您的信箱!");
			window.close();
			break;
		case "6":
			alert("圖形驗證碼錯誤");
			break;
		}
	}
}
</script>
</head>
<body topmargin="0" onLoad="init();">
<form id="form1" name="form1" method="post" action="sendBackPw.jsp"  autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto;">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form">帳號：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="userId" size="10" maxlength="10" value="<%=pId%>">
		</td>	
	</tr>
	<tr>
		<td nowrap class="td_form">認證碼：</td>
		<td nowrap class="td_form_white">
			<input type="text" name="rand" maxlength="4" size="4">
			<img border="0" src="image.jsp" height="100%">
		</td>
	</tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
<span id="spanDoSend">
	<input class="toolbar_default" type="submit" followPK="false" name="send" value="送　出">&nbsp;
</span>	
</td></tr>

</table>
</form>
</body>
</html>
