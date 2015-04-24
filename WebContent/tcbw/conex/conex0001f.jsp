<!--
程式目的：
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.kangdainfo.common.util.*" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conse.CONSE0011F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<% 
String id = View.getLookupField("select id from Con1016Db where systemType="+Common.sqlChar("CON")+" and formType="+Common.sqlChar("01"));
obj.setId(id);

obj = (com.kangdainfo.tcbw.view.conse.CONSE0011F) obj.queryOne();
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">

function init() 
{
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
}

function toCreate()
{
	form1.action = "../../preLogin.jsp";
	form1.submit();
}

function doBack(){
	form1.action = "../../index.jsp";
	form1.submit();
}
</script>
</head>
<body onLoad="init();">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
	<td class="bg" align="center">
	<div id="formContainer"  style="height:auto">
	<table class="table_form" align="center" style="height:auto " >
	   <tr>
           <td valign="middle" >
                <textarea name="Statement" cols="100" rows="30" style="text-align: center"><%=obj.getFormDesc()%></textarea>
           </td>
       </tr>
	</table>
	</div>
</tr>
<!--Toolbar區============================================================-->
<tr>
<td nowrap class="bgToolbar"  style="text-align: center">
	<span id="toCreate">
    	<input class="toolbar_default" type="button" followPK="false"  name="toCreate1" value="我   同    意"  onClick="toCreate();">&nbsp;
    </span>
    <span id="spanDoBack">
		<input class="toolbar_default" type="button" followPK="false" name="back" value="返回登入頁面" onClick="doBack();">&nbsp;
	</span>
</td>
</tr>
</table>
</form>
</body>
</html>
