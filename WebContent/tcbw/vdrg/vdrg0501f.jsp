<!--
程式目的：國內外藥品品質警訊查詢作業
程式代號：vdrg0501f
程式日期：1030506
程式作者：yuwen
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="VDRG0501" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.vdrg.VDRG0501F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
if ("init".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.vdrg.VDRG0501F)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
function checkField(){
	var alertStr="";
	if(form1.state.value=="queryAll"){
		alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}
function queryOne(id){
    form1.id.value = id;
    form1.state.value = "queryOne";
    form1.action = "vdrg0100f.jsp";
	beforeSubmit();
	form1.submit();
}

function init(){
	setDisplayItem('spanInsert,spanDuplicate,spanUpdate,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden,spanQueryAll','H');
}

function doQueryAll(){
	form1.state.value="queryAll";
	beforeSubmit();
	form1.submit();
}

function popReportForm() {
	form1.target = "_blank";
	form1.action = "vdrg0501p.jsp";
	form1.submit();
	form1.target = "_self";
	form1.action = "vdrg0501f.jsp";		
}

function popWordReportForm() {
	form1.action="vdrg0501p2.jsp";
	form1.target="_blank";
	form1.submit();
	form1.target="_self";
	form1.action="vdrg0501q.jsp";
}
</script>
</head>

<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">

<!--Query區============================================================-->
<div id="queryContainer" style="width:400px;height:400px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
</div>
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td class="bgToolbar" style="text-align:left">
    <input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="formType" value="<%=obj.getFormType()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="button" followPK="false" name="doQuery" value="查   詢" onClick="doQueryAll();">&nbsp;
    </span>  
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
	<span id="spanListToExcel">
        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
    </span>
    <span id="spanPrint">
    	<input class="toolbar_default" type="button" followPK="false" name="btnPrint" value="列印風管組報表" onClick="popReportForm();">&nbsp;
    </span>
    <span id="spanPrint1">
		<input class="toolbar_default" type="button" followPK="false" name="btnPrint1" value="列印警訊通知" onClick="popWordReportForm()">&nbsp;
	</span>
</td></tr>

<!--Form區============================================================-->
<tr><td class="bg">
	<div id="formContainer" style="height:auto">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="vdrg0501q.jsp" />
	</div>
</td></tr>
<tr><td class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>
<!--List區============================================================-->
<tr><td class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">發佈單位</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">狀況</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">資料收集日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">商品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">事件簡述</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">回收型態</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true,false,false,false,false,false,false,false};
	boolean displayArray[] = {false,true,true,true,true,true,true,true};
	boolean linkArray[] = {false,true,false,false,false,false,false,false};
	String[] alignArray  =  {"center","center","center","center","center","left","left","left"};
	out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag(),true,linkArray,null,"",false,false,false,"",false,false,0,true));
	//out.write(View.getQueryDiscolorList(primaryArray, displayArray,null, objList, obj.getQueryAllFlag(), true, "", true,"vdrg0100f.jsp","&formType=0501","",1));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
