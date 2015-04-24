<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ca.SYSCA001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	
if (!"".equals(obj.getPopKind())) {
	obj.setQ_codeKindID(obj.getPopKind());
}
if (obj.getQ_codeKindID().equals("")) {
	out.write("錯誤：代碼類別不能空白");
	return;	
}

if (!"".equals(obj.getPreWord())) obj.setQ_preWord(obj.getPreWord());
if (!"".equals(obj.getPreCon1())) obj.setQ_codeCon1(obj.getPreCon1());
if (!"".equals(obj.getPreCon2())) obj.setQ_codeCon2(obj.getPreCon2());
if (!"".equals(obj.getPreCon3())) obj.setQ_codeCon3(obj.getPreCon3());

String strJavaScript = "";
if (!"".equals(Common.get(obj.getJs()))) strJavaScript += "\nopener." + obj.getJs() + ";\n\n";

obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();
%>
<html>
<head>
<title>代碼輔助視窗</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">
function queryOne(id,codeKindName,codeID,codeName,codeEngName,codeCon1,codeCon2,codeCon3,memo,editID,editDate){
	if (isObj(opener.document.all.item("<%=obj.getPopId()%>"))) {		
		opener.document.all.item("<%=obj.getPopId()%>").value=id;		
	}
	if (isObj(opener.document.all.item("<%=obj.getPopCode()%>"))) {
		opener.document.all.item("<%=obj.getPopCode()%>").value=codeID;		
	}
	if (isObj(opener.document.all.item("<%=obj.getPopCodeName()%>"))) {
		opener.document.all.item("<%=obj.getPopCodeName()%>").value=codeName;		
	}	
	if (isObj(opener.document.all.item("<%=obj.getEngName()%>"))) {
		opener.document.all.item("<%=obj.getEngName()%>").value=codeEngName;		
	}		
	var fds = "<%=obj.getConField()%>";
	if (fds.length>0) {
		var arrFds = fds.split(',');
		for (var i=0; i<arrFds.length; i++) {
			if (i==0 && isObj(opener.document.all.item(arrFds[i]))) {
				opener.document.all.item(arrFds[i]).value=codeCon1;
			}
			if (i==1 && isObj(opener.document.all.item(arrFds[i]))) {
				opener.document.all.item(arrFds[i]).value=codeCon2;
			}	
			if (i==2 && isObj(opener.document.all.item(arrFds[i]))) {
				opener.document.all.item(arrFds[i]).value=codeCon3;
			}		
			if (i==3 && isObj(opener.document.all.item(arrFds[i]))) {
				opener.document.all.item(arrFds[i]).value=memo;
			}						
		}
	}
	<%=strJavaScript%>	
	window.close();
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" action="popCode.jsp">
<input type="hidden" name="popId" value="<%=obj.getPopId()%>">
<input type="hidden" name="popCode" value="<%=obj.getPopCode()%>">
<input type="hidden" name="popCodeName" value="<%=obj.getPopCodeName()%>">
<input type="hidden" name="q_codeKindID" value="<%=obj.getQ_codeKindID()%>">
<input type="hidden" name="js" value="<%=obj.getJs()%>">
<input type="hidden" name="engName" value="<%=obj.getEngName()%>">
<input type="hidden" name="conField" value="<%=obj.getConField()%>">
<input type="hidden" name="q_preWord" value="<%=obj.getQ_preWord()%>">
<input type="hidden" name="q_codeCon1" value="<%=obj.getQ_codeCon1()%>">
<input type="hidden" name="q_codeCon2" value="<%=obj.getQ_codeCon2()%>">
<input type="hidden" name="q_codeCon3" value="<%=obj.getQ_codeCon3()%>">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg" >
	<div id="formContainer" style="height:70px">
	<table class="table_form" width="100%" height="100%">	
	<tr>		
		<td nowrap class="td_form">&nbsp;<font color="red">*</font>代碼編號：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_codeId" size="10" maxlength="10" value="<%=obj.getQ_codeId().toUpperCase()%>">
		</td>		
	</tr>	
	<tr>		
		<td nowrap class="td_form">&nbsp;<font color="red">*</font>代碼名稱：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_codeName" size="30" maxlength="30" value="<%=obj.getQ_codeName()%>">
		</td>		
	</tr>	
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
</td></tr>

<tr><td nowrap>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bg" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">代碼種類</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">代碼編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">代碼名稱</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true,  true, true, true, true, true, true, true, true};
		boolean displayArray[] = {false, true,  true, true, false, false, false, false, false, false};
		out.write(View.getQuerylist(primaryArray,displayArray,objList,obj.getQueryAllFlag()));
		%>
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
</body>
</html>
