<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.SendMailSearch">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	
String strJavaScript = "";
String isJS= ESAPI.encoder().decodeForHTML(ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("isJS"))));

if (!"".equals(Common.get(isJS)))
{
	strJavaScript += isJS;
	if(isJS.indexOf("(") < 0 ){
		strJavaScript += "();";
	}
	strJavaScript += "\n\n";
}

obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();
%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">
function checkField(){	
	return true;
}

function queryOne(id, applicationId){
	var parm = "isAdd="+form1.isAdd.value+"&applNo="+form1.applNo.value+"&isJS=window.opener.addSendId('"+applicationId+"')"+
			"&mailID="+form1.mailID.value+"&id="+id+ "&systemType="+form1.systemType.value+"&applicationId="+applicationId;
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1200,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	window.open(getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?"+parm,"",prop);
}

function addSendId(applicationId){
	if(null != form1.sendApplId.value && "" != form1.sendApplId.value){
		form1.sendApplId.value += ",";
	}
	form1.sendApplId.value += applicationId;
	beforeSubmit();
	form1.submit();
}

function init(){
	if("Y" == "<%=obj.getIsClose()%>"){
		<%=strJavaScript%>
		window.close();
	}
}
</script>
</head>
<body onLoad="init();">
<form id="form1" name="form1" method="post" action="popSendMail.jsp" onSubmit="return checkField()">
<!--預設欄位名稱  -->
<input class="field_Q" type="hidden" name="id" value="<%=obj.getId()%>">
<input class="field_Q" type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
<input class="field_Q" type="hidden" name="systemType" value="<%=obj.getSystemType()%>">
<input class="field_Q" type="hidden" name="mailID" value="<%=obj.getMailID()%>">
<input class="field_Q" type="hidden" name="isAdd" value="<%=obj.getIsAdd()%>">
<input class="field_Q" type="hidden" name="isJS" value="<%=obj.getIsJS()%>">
<input class="field_Q" type="hidden" name="sendApplId" value="<%=obj.getSendApplId()%>">

<table width="100%" cellspacing="0" cellpadding="0">
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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">廠商統編</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">廠商名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">已發送</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#"> </a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true, true, false, false};
		boolean displayArray[] = {false, true, true, true, true};
		out.write(View.getQuerylist(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),true ,null,null,"",false));
		%>
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
</body>
</html>
