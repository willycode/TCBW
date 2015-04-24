<!--
程式目的：通報欄位必填設定作業
程式代號：conse0008_1f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONSE0008" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conse.CONSE0008_1F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
	objList = (java.util.ArrayList) obj.queryAll();
}else{
	objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
function checkField(){
	var alertStr = "";
	var errorflag = true;
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){
		obj = arrObj[i];	
		if (like(obj.id,"id")){
			if(obj.type=="checkbox"){
				if (obj.checked){
					errorflag=false;
					break;				
				}
			}
		}	
	}
	if (errorflag){
		alertStr = "請至少選取一個項目!";
	}

	if(alertStr.length!=0){ alert(alertStr); return false; }
	else {
		if(confirm("確定要修改嗎？"))
		{
			if (checkSpecialChar(form1)) {
				form1.state.value = "update";
				beforeSubmit();
				return true;
			}
		}
		return false;		
	}
}

function queryOne(a){
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

function init() {
}
</script>
</head>
<body onLoad="init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->

<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w">是否必填</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">欄位名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">日期格式</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">數字格式</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">長度限制</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false};
	boolean displayArray[] = {false,false, true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center", "center"};
	out.write(View.getCheckboxQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag(),"id"));
	%>
	</tbody>
</table>
</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="editID" value="<%=User.getEditId()%>">
	<span id="spanConfirm">
	<input class="toolbar_default" type="submit" followPK="false" name="confirm" value="確　定" >&nbsp;
	</span>
</td></tr>

</table>
</form>
</body>
</html>