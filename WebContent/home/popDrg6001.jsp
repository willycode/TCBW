<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.Drg6001DbSearch">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%

java.util.ArrayList<String[]> objList = (java.util.ArrayList) obj.doQueryAll();

%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/json.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="../js/tablesoft.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript" src="../js/toolbar.js"></script>
<script type="text/javascript">
function queryOne(id) {
	popData(id);
}

function cancel(){
	window.close();
}

function popData(id){
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=800,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/drgex/drgex0302f.jsp?id="+id+"&formType=0309","",prop);
}

</script>
</head>
<body>
<form id="form1" name="form1" method="post" action="popDrg6001.jsp">
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td>
	<input type="hidden" name="q_id" value="<%=obj.getQ_id()%>">
</td></tr>
<tr><td>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="page.jsp" />
</td></tr>
<tr><td>
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="tbl">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" >NO</th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">版次</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">異動日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true,false,false,false,false};
		boolean displayArray[] = {false,true,true,true,true};
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