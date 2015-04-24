<!--
程式目的：食品非預期反應通報登入作業(外部)
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFRIN0401" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0401F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if(User.getCommonDepartment() == null){
	out.write("無法辨別登入者屬性 !");
	return;
}

obj.setQueryAllFlag("true");
if("init".equals(obj.getState())){	obj.setListContainerActiveRowId("");	}
if("".equals(obj.getUserID())){	obj.setUserID(User.getUserId());	}

if("true".equals(obj.getQueryAllFlag())){	
	objList = (java.util.ArrayList) obj.doQueryAll();
	obj.setStateQueryOneSuccess();
}
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
var win = null;
function checkField(){
	var alertStr = "";
	alertStr += checkDate(form1.q_notifierRepDateS, "通報日期-起");
	alertStr += checkDate(form1.q_notifierRepDateE, "通報日期-迄");
	alertStr += checkDateSE(form1.q_notifierRepDateS, form1.q_notifierRepDateE, "通報日期");
	
	if(alertStr.length > 0){
		alert(alertStr);
		return false;
	}
	
	form1.listContainerActiveRowId.value = "";					// 復原預設值
	form1.currentPage.value = "1";
	//form1.pageSize.value = "10";
	
	form1.state.value = "queryAll";
	beforeSubmit();
	return true;
}

function toCreate(){
	var prop = '';
	var w = (screen.width-400)/2;
	var h = (screen.height-100)/2;
    prop = prop + 'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes';
    prop = prop + ',width=400';
    prop = prop + ',height=100';
    prop = prop + ',left=' + w;
    prop = prop + ',top=' + h;

    if(win != null) win.close();
    win = window.open('hfrin0402f.jsp','',prop);
}

function goInsert(){
	if(form1.hfrType.value != ""){
		form1.state.value = "queryOne";
		form1.doType.value = "insert";
		if(form1.hfrType.value == "1"){
			form1.action = "hfrin0403f.jsp";
		}else{
			form1.action = "hfrin0406f.jsp";
		}
		beforeSubmit();
		form1.submit();
	}else{
		alert("請先選取通報表種類");
	}
}

function updateData(id, hfrType){
	form1.id.value = id;
	form1.state.value = "queryOne";
	form1.doType.value = "update";
	form1.hfrType.value = hfrType;
	if(hfrType == "2"){
		form1.action = "hfrin0406f.jsp";
	}else{
		form1.action = "hfrin0403f.jsp";
	}
	beforeSubmit();
	form1.submit();
}
function queryOne(rowId,hfrType){
	updateData(rowId,hfrType);
}
/*
function queryOne(rowid, isNeedUpdateOther, classTR, id, hfrType){
	if(isNeedUpdateOther == "Y"){
		document.all.item("id").value = id;
	}
	document.all.item("listContainerActiveRowId").value = rowid;
	$("tr[id*=listContainerRow]").each(function(){
		if($(this).attr("id") == ("listContainerRow" + rowid)){
			this.className = "activeRow";
			this.onmouseover = "activeRow";
			this.onmouseout = "activeRow";
		}else{
			if(isNeedUpdateOther == "Y"){
				this.className = classTR;
				this.onmouseover = function(){
					this.className = "listTRMouseover";
				};
				this.onmouseout = function(){
					this.className = classTR;
				}
			}
		}
	});
	if(isNeedUpdateOther == "Y"){
		updateData(id, hfrType);
	}
}
*/
function init() {
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	//queryOne("<%=obj.getListContainerActiveRowId()%>", "N");
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">

<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoCreate">
    	<input class="toolbar_default" type="button" followPK="false" name="doCreate" value="新   增" onClick="toCreate();">&nbsp;
    </span>
    <span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢">&nbsp;
    </span> 
    <span id="spanListToExcel">
        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
    </span> 
</td></tr>

<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="hfrin0401q.jsp" />
	</div>
</td></tr>



<!--List區============================================================-->
<tr><td nowrap class="bgPagging">
	<% request.setAttribute("QueryBean",obj);%>
	<jsp:include page="../../home/page.jsp" />
</td></tr>
 
<tr><td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">發生非預期反應日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">食品字號</a></th>
		<th class="listTH"><a class="text_link_w" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" href="#">案件狀態</a></th>
		<th class="listTH"><a class="text_link_w" href="#">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true, false, false, false, false, false, true};
	    boolean displayArray[] = {false, true, true, true, true, true, false};
	    boolean linkArray[] = {false,true,false,false,false,false,false};
	    //out.write(View.getQuerylist(primaryArray, displayArray, null, objList, obj.getQueryAllFlag(), true, null, "", "", true, false, false));
	    out.write(View.getQuerylistPlusDetailButton(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),true,linkArray,null,"",false, false));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
