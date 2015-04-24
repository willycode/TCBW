<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.Drg5001DbSearch">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String drg0001Id = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("drg0001Id")));
if(drg0001Id!=null) obj.setDrg0001Id(drg0001Id);

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

function queryOne(id){
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
	returnWindow=window.open(getVirtualPath() + "tcbw/drgex/drgex0102f.jsp?v="+id,"",prop);
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" action="popDrg5001.jsp" onSubmit="return checkField()">

<!--預設欄位名稱  -->
<input type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
<input type="hidden" name="drg0001Id" value="<%=obj.getDrg0001Id()%>">

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
		boolean primaryArray[] = {true, false, false, false, false};
		boolean displayArray[] = {false, true, true, true, true};
		String[] alignArray = {"center","center","center","center","center"};
		boolean[] linkArray = {false, false, false, false, true};
		out.write(View.getQuerylist(primaryArray, displayArray,alignArray, objList, obj.getQueryAllFlag(),true,linkArray,null));
		%>
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
</body>
</html>
