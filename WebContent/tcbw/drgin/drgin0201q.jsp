<!--
程式目的：藥品療效不等通報查詢作業
程式代號：drgin0201q
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGIN0201" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0201Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
//String caseType = Common.get(request.getParameter("caseType"));
//obj.setCaseType(caseType);
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())){
	obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0201Q)obj.queryOne();
}
if ("true".equals(obj.getQueryAllFlag())){	
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
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
function checkField(){
	var alertStr = "";
	form1.state.value="queryAll";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
	}
	
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}
function init() {
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	//if(form1.caseType.value=="rep"){
		//setDisplayItem("toCreate1,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	//}
}

function toCreate(){
	form1.state.value="";
	form1.action = "drgex0101f.jsp";
	beforeSubmit();
	form1.submit();
	
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
    <input type="hidden" name="id" value="">	
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	

	<input class="toolbar_default" type="submit" followPK="false" name="drgin0101q_queryAll" value="查　詢" onSubmit="return checkField()">&nbsp;

	<jsp:include page="../../home/toolbar.jsp" />
	
</td></tr>
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件編號：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="10" maxlength="10" value="<%=obj.getQ_applNoS()%>">~		
			<input class="field_Q" type="text" name="q_applNoE" size="10" maxlength="10" value="<%=obj.getQ_applNoE()%>">
		</td>
		<td nowrap width="15%" nowrap class="td_form">發現日期：</td>
		<td nowrap nowrap class="td_form_white"> <!-- q_dateS ~ q_dateE -->
			<%=View.getPopCalendar("field_Q","q_occurDateS",obj.getQ_occurDateS())%>~
			<%=View.getPopCalendar("field_Q","q_occurDateE",obj.getQ_occurDateE())%>				
		</td>
	</tr>  
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRevDateS",obj.getQ_notifierRevDateS())%>~
			<%=View.getPopCalendar("field_Q","q_notifierRevDateE",obj.getQ_notifierRevDateE())%>				
		</td>
		<td nowrap width="15%" nowrap class="td_form">收案日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",obj.getQ_notifierRepDateS())%>~
			<%=View.getPopCalendar("field_Q","q_notifierRepDateE",obj.getQ_notifierRepDateE())%>			
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報來源：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_notifierSource" size="50" maxlength="100" value="<%=obj.getQ_notifierSource()%>">						
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報單位：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_通報單位" size="50" maxlength="100" value="<%=obj.getQ_通報單位()%>">						
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">許可證字：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_permitNo" size="25" maxlength="100" value="<%=obj.getQ_permitKey1()%>">許可證號：
			<input class="field_Q" type="text" name="q_permitNo" size="25" maxlength="100" value="<%=obj.getQ_permitNo1()%>">	
		</td>
		<td nowrap width="15%" nowrap class="td_form">藥品品名：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_productName1" size="50" maxlength="100" value="<%=obj.getQ_productName1()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">學名：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_scientificName1" size="50" maxlength="100" value="<%=obj.getQ_scientificName1()%>">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">申請商：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applicationName1" size="50" maxlength="100" value="<%=obj.getQ_applicationName1()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">製造廠：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_manufactorName1" size="50" maxlength="100" value="<%=obj.getQ_manufactorName1()%>">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報事件後果：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_通報事件後果" size="50" maxlength="100" value="<%=obj.getQ_通報事件後果()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">NTI Drugs：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_NTIDrugs" size="50" maxlength="100" value="<%=obj.getQ_NTIDrugs()%>">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">療效不等評估結果：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_療效不等評估結果" size="50" maxlength="100" value="<%=obj.getQ_療效不等評估結果()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件狀態：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_status" size="50" maxlength="100" value="<%=obj.getQ_status()%>">		
		</td>
	</tr>
	</table>
	</div>	
</td></tr>
<!--List區============================================================-->
<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<tr><td nowrap>
<br>
<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
		<td nowrap ID=t1 CLASS="tab_border1">藥品療效不等通報登錄作業</TD>		
	</TR>
</TABLE>
<tr><td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">通報日期</a></th>		
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">學名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">商品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">申請商</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">製造廠</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">製造批號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',9,false);" href="#">通報事件後果</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',10,false);" href="#">案件狀態</a></th>
	</tr>
	<!--  
	<tr class="listTROdd">
			<td nowrap class="listTROdd">1</td>
			<td nowrap class="listTROdd"></td>
			<td nowrap class="listTROdd"></td>
			<td nowrap class="listTROdd"></td>
			<td nowrap class="listTROdd"></td>
			<td nowrap class="listTROdd"></td>
			<td nowrap class="listTROdd"></td>
			<td nowrap class="listTROdd"></td>
    </tr>
    -->
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center","center"};
		out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag()));
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
