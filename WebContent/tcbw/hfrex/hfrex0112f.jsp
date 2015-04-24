<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrex.HFREX0106F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String field1 = Common.get(request.getParameter("field1"));
String field2 = Common.get(request.getParameter("field2"));
String field3 = Common.get(request.getParameter("field3"));
String field4 = Common.get(request.getParameter("field4"));
String field5 = Common.get(request.getParameter("field5"));
obj.setField1(field1);
obj.setField2(field2);
obj.setField3(field3);
obj.setField4(field4);
obj.setField5(field5);

obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.doQueryAll();
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
function checkField(){
	var alertStr = "";
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function queryOne(pKey, pNo, cName, eName, ingredient){
	if (isObj(opener.document.all.item("<%=obj.getField1()%>"))) {		
		opener.document.all.item("<%=obj.getField1()%>").value = pKey;		
	}
	if (isObj(opener.document.all.item("<%=obj.getField2()%>"))) {
		opener.document.all.item("<%=obj.getField2()%>").value = pNo;		
	}
	if (isObj(opener.document.all.item("<%=obj.getField3()%>"))) {		
		opener.document.all.item("<%=obj.getField3()%>").value = cName;		
	}
	if (isObj(opener.document.all.item("<%=obj.getField4()%>"))) {		
		opener.document.all.item("<%=obj.getField4()%>").value = eName;		
	}
	if (isObj(opener.document.all.item("<%=obj.getField5()%>"))) {		
		opener.document.all.item("<%=obj.getField5()%>").value = ingredient;		
	}
	window.close();
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">	
	<tr>		
		<td nowrap class="td_form">許可證字：</td>
		<td nowrap class="td_form_white">
			<select class="field" name="q_permitKey" type="select">
				<%=View.getOptionCodeKind("HFRPKID", obj.getQ_permitKey()) %>
			</select>
		</td>		
	</tr>	
	<tr>		
		<td nowrap class="td_form">許可證號：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_permitNo" size="6" maxlength="6" value="<%=obj.getQ_permitNo()%>">
		</td>		
	</tr>	
	<tr>		
		<td nowrap class="td_form">品名：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_name" size="30" maxlength="50" value="<%=obj.getQ_name()%>">
		</td>		
	</tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<input type="hidden" name="field1" value="<%=obj.getField1()%>">
	<input type="hidden" name="field2" value="<%=obj.getField2()%>">
	<input type="hidden" name="field3" value="<%=obj.getField3()%>">
	<input type="hidden" name="field4" value="<%=obj.getField4()%>">
	<input type="hidden" name="field5" value="<%=obj.getField5()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">

	<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
</td></tr>

<tr><td class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>
</table>

<table width="100%" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
<!--List區============================================================-->
<tr><td class="bgList">
<div id="listContainer" style="height:auto;width:100%">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">外文品名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">申請商名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">製造商名稱</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, true, false, true, true, false, false, true};
	boolean displayArray[] = {false, false, true, true, true, true, true, false};
	out.write(View.getQuerylist(primaryArray, displayArray, null, objList, obj.getQueryAllFlag(), true, null, "", "", true, false, true, "", false));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
