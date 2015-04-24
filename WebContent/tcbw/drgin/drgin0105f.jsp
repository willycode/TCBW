<!--
程式目的：藥品不良品通報-案件分級查詢
程式代號：drgin0105
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGIN0105" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0105F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
int orderID = 0; // List 排序使用
String caseType = Common.get(request.getParameter("caseType"));
if(caseType != "") obj.setCaseType(caseType);

obj.setQueryAllFlag("true");

if("".equals(obj.getUserID()))
{
  obj.setUserID(User.getUserId());
}

if("true".equals(obj.getQueryAllFlag()))
{	
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
function checkField(){
	var alertStr = "";
	alertStr += checkDate(form1.q_occurDateS, "發生未預期反應日期-起");
	alertStr += checkDate(form1.q_occurDateE, "發生未預期反應日期-迄");
	alertStr += checkDateSE(form1.q_occurDateS, form1.q_occurDateE, "發生未預期反應日期");
	
	if(alertStr.length > 0){
		alert(alertStr);
		return false;
	}
	beforeSubmit();
	form1.state.value = "queryAll";
	return true;
}

function init() 
{
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
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
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
	<input type="hidden" name="drgLevType" value="<%=obj.getDrgLevType()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="doType" value="<%=obj.getDoType()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢">&nbsp;
    </span>
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
	
	<span id="spanListToExcel">
        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
    </span>
</td>
</tr>

<tr>
<td class="bg">
	<div id="formContainer" style="height:auto">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="drgin0105q.jsp" />
	</div>
</td>
</tr>

<!--List區============================================================-->
<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>
<tr>
<td class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID%>,false);" href="#">NO</a></th>
		<% if("2x".equals(obj.getCaseType())){%>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">燈號</a></th>
		<%} %>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">案件編號</a></th>		
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">英文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">不良品缺陷</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">風險評估</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">申請商</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">製造廠</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">案件狀態</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">主動補件</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">作業人員</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',<%=orderID=orderID+1%>,false);" href="#">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    if("2x".equals(obj.getCaseType())){	       
	    	boolean primaryArray[] = {true,false,false,false,false,false,false,false,false,false,false,false,false,false};	    
	    	boolean displayArray[] = {false,true,true,true,true,true,true,true,true,true,true,true,true,true};	    
	    	boolean linkArray[] = {false,false,true,false,false,false,false,false,false,false,false,false,false,false};	    
	    	String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center","center","center","center","center"};
	    	out.write(View.getQueryDiscolorList(primaryArray, displayArray,alignArray, objList, obj.getQueryAllFlag(), true, "", false,"drgin0106f.jsp","&caseType="+caseType,"",2));		    
	    }else{
	    	boolean primaryArray[] = {true,false,false,false,false,false,false,false,false,false,false,false,false};	    
	    	boolean displayArray[] = {false,true,true,true,true,true,true,true,true,true,true,true,true};	
	    	boolean linkArray[] = {false,true,false,false,false,false,false,false,false,false,false,false,false};
	    	String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center","center","center","center"};
	    	out.write(View.getQueryDiscolorList(primaryArray, displayArray,alignArray, objList, obj.getQueryAllFlag(), true, "", true,"drgin0106f.jsp","&caseType="+caseType,"",1));		    
	    }	
	    
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	case "queryAll":
		break;
	}
}
</script>
</html>