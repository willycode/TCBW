<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.Med4001DbSearch">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String med0001Id = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("med0001Id")));
if(med0001Id != null) obj.setMed0001Id(med0001Id);

obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();
%>
<html>
<head>
<title>案件原始版本</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">
function checkField(){	
	return true;
}

function queryOne(id){}

function updateData(id)
{
	popDrg5001(id);
}

function popDrg5001(id){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1050,height=620,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	//還不確定是哪個
	returnWindow=window.open(getVirtualPath() + "tcbw/medex/medex0104f.jsp?show=Y&id="+id,"",prop);
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" action="popMed4001.jsp" onSubmit="return checkField()">

<!--預設欄位名稱  -->
<input type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
<input type="hidden" name="med0001Id" value="<%=obj.getMed0001Id()%>">

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->

<!--Toolbar區============================================================-->

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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">版次</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">異動日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true, false, false, false};
		boolean displayArray[] = {false, true, true, true};
		String[] alignArray = {"center","center","center","center"};
		out.write(obj.getQuerylist(primaryArray, displayArray,alignArray, objList, obj.getQueryAllFlag(), true, "", true));
		%>
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
</body>
</html>
