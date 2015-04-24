<!--
重大品質事件廠商主動通報-案件分析作業查詢頁
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SDRG0601" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0601Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<% 
String caseType = Common.get(request.getParameter("caseType"));
obj.setCaseType(caseType);

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
var popWinName;
function checkField(){
	var alertStr = "";	
	
	if(alertStr.length > 0){
		alert(alertStr);
		return false;
	}
	beforeSubmit();
	form1.state.value = "queryAll";
	return true;
}

function queryOne(id)
{	
    
    form1.id.value = id;    	
    form1.state.value = "queryOne";
    form1.action = "sdrg0601f.jsp";
    
	beforeSubmit();
	form1.submit();
}

function init() 
{
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
}
//主管衛生局查詢
function popHealthData(type){
	if(type==1){
		var prop="";
		var windowTop=120;
		var windowLeft=120;
		var v = "04";
		var p = "SDRGQuery";
		prop=prop+"width=1200px,height=600,";
		prop=prop+"top="+windowTop+",";
		prop=prop+"left="+windowLeft+",";
		prop=prop+"scrollbars=yes";
		if (popWinName!=null) popWinName.close();
		popWinName = window.open(getVirtualPath() +"/home/popUserJob.jsp?v="+v+"&p="+p,'popWinE',prop);
	}else{
		form1.q_healthbureau.value="";
		form1.q_healthbureauName.value="";
	}		
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
     <td nowrap class="bgToolbar" style="text-align:left">
     <jsp:include page="../../home/toolbar.jsp" />
     <input type="hidden" name="state" value="<%=obj.getState()%>">	
	 <input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	 <input type="hidden" name="userID" value="<%=User.getUserId()%>">
	 <input type="hidden" name="id" value="<%=obj.getId()%>">	 
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
<!--  form區============================================================-->
<tr>
<td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="sdrg0601q1.jsp" />
	</div>
</td>
	
<!--List區============================================================-->
<tr>
<td nowrap class="bgPagging"><br>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>
<tr>
<td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
		<tr>
          	<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">序號</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">案號</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">中文品名</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">英文品名</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">許可證字號</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">許可證持有商</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">發文日期</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">回收文號</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">訊息來源</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',9,false);" href="#">回收期限</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',10,false);" href="#">主管衛生局</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',11,false);" href="#">明細</a></th>
		</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true,true,true,true};
	    boolean linkArray[] = {false,true,false,false,false,false,false,false,false,false,false};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center","center"};
	    out.write(View.getQueryDiscolorList(primaryArray, displayArray,alignArray, objList, obj.getQueryAllFlag(), true, "", false,"sdrg0601f.jsp","","",1));
	%>
	</tbody>
</table>
</div>
</td>
</tr>
</table>
</form>
</body>
</html>