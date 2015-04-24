<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.tcbw.view.pivot.PIVOT0901R">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

if ("queryData".equals(obj.getState())) {	
	if ("false".equals(obj.getQueryAllFlag())){ obj.setQueryAllFlag("true"); }
}	
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryOne();	
}
%>     
<html>
<head>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">

function init()
{
	
}
</script>
</head>
<body onLoad="init();">
<form id="form1" name="form1" method="post" autocomplete="off" >
<!--List區============================================================-->
<br>
<table width="100%" class="bg" cellspacing="0" cellpadding="0" style="height:auto">
<tr><td class="bgList">
<div id="listContainer" style="display:block;height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0" style="height:auto">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">通報日期 </a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">食品字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">食品品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">健康食品未達宣稱之保健功效</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">非預期反應結果</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">案件狀態</a></th>		
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%	    
	    boolean primaryArray[] = {false,false,false,false,false,false,false};
	    boolean displayArray[] = {true,true,true,true,true,true,true};
	    String[] alignArray = {"center","center","center","center","center","center","center"};
	    out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag(),false));        
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
<input type="hidden" name="state" value="<%=obj.getState()%>"/>
</form>
</body>
</html>