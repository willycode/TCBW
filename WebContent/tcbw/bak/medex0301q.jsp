<!--
程式目的：醫療器材臨床試驗不良事件通報登錄作業
程式代號：medex0301
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="MEDEX0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medex.MEDEX0301F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String caseType = Common.get(request.getParameter("caseType"));
obj.setCaseType(caseType);

if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())){

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

function init() {
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	if(form1.caseType.value=="rep"){
		setDisplayItem("toCreate1,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	}
}

function toCreate(){
	form1.state.value="";
	form1.action = "medex0301f.jsp";
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
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="toCreate">
    	<input class="toolbar_default" type="button" followPK="false"  name="toCreate1" value="新   增"  onClick="toCreate();">&nbsp;
    </span> 
</td></tr>
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">
	<tr>
		<td nowrap class="td_form" width="15%">案件編號：</td>
		<td nowrap class="td_form_white" width="35%">
			<input class="field_Q" type="text" name="q_meanS" size="10" maxlength="10" value="">
			- 
			<input class="field_Q" type="text" name="q_meanE" size="10" maxlength="10" value="">
		</td>
		<td nowrap class="td_form" width="15%">通報日期起訖：</td>
		<td nowrap class="td_form_white" width="35%">
			<%=View.getPopCalendar("field_Q","q_dateS","")%>~<%=View.getPopCalendar("field_Q","q_dateE","")%>
		</td>
	</tr>  
	<tr>
		<td nowrap class="td_form" width="15%">發生日期起訖：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_dateS","")%>~<%=View.getPopCalendar("field_Q","q_dateE","")%>
		</td>
		<td nowrap class="td_form" width="15%">收案日期起訖：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_dateS","")%>~<%=View.getPopCalendar("field_Q","q_dateE","")%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">通報來源：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_meanS" size="10" maxlength="10" value="">
		</td>
		<td nowrap class="td_form" width="15%">通報單位：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_meanS" size="10" maxlength="10" value="">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">案例來源：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_meanS" size="10" maxlength="10" value="">
		</td>
		<td nowrap class="td_form" width="15%">核准文號：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">
		</td>
	</tr>	
	<tr>
		<td nowrap class="td_form" width="15%">許可證字號：</td>
		<td nowrap class="td_form_white">
			字<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">
			號<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">
		</td>
		<td nowrap class="td_form" width="15%">列管單位：</td>
		<td nowrap class="td_form_white" >
			<input class="field_Q" type="text" name="q_meanS" size="10" maxlength="10" value="">
		</td>
	</tr>  
	<tr>
		<td nowrap class="td_form" width="15%">醫材品名：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_mean" size="60" maxlength="100" value="">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">製造廠：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_mean" size="60" maxlength="100" value="">
		</td>
    </tr>
	<tr>
		<td nowrap class="td_form" width="15%">醫材主類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field_Q","q_ovrRuleInsp","","","","CM","q_ovrRuleInspCodeId","",new int[]{2,2,10,20})%>
		</td>
		<td nowrap class="td_form" width="15%">醫材次類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field_Q","q_ovrRuleInsp","","","","CM","q_ovrRuleInspCodeId","",new int[]{2,2,10,20})%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">不良反應結果：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="60" maxlength="100" value="">
		</td>
		<td nowrap class="td_form" width="15%">案件狀態：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field_Q","q_ovrRuleInsp","","","","CM","q_ovrRuleInspCodeId","",new int[]{2,2,10,20})%>
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
		<td nowrap ID=t1 CLASS="tab_border1">醫材臨床試驗不良事件通報</TD>		
	</TR>
</TABLE>
<tr><td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">通報來源</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">通報單位</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">核准文號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">列管單位</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',9,false);" href="#">醫材品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',10,false);" href="#">製造廠</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',11,false);" href="#">醫材主類別</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',12,false);" href="#">不良反應結果</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',13,false);" href="#">案件狀態</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',14,false);" href="#">作業人員</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',15,false);" href="#">明細</a></th>
	</tr>
	<tr class="listTROdd">
			<td nowrap class="listTROdd">1</td>
			<td nowrap class="listTROdd">12334</td>
			<td nowrap class="listTROdd">102/01/02</td>
			<td nowrap class="listTROdd">民眾通報</td>
			<td nowrap class="listTROdd">一般民眾</td>
			<td nowrap class="listTROdd">TEST1232133</td>
			<td nowrap class="listTROdd">衛生局</td>
			<td nowrap class="listTROdd">DOH12334566323</td>
			<td nowrap class="listTROdd">Mentholatum</td>
			<td nowrap class="listTROdd">曼秀雷敦</td>
			<td nowrap class="listTROdd">xxx</td>
			<td nowrap class="listTROdd">嚴重</td>
			<td nowrap class="listTROdd">待補件</td>
			<td nowrap class="listTROdd">承辦人</td>
			<td nowrap class="listTROdd"><a href="medex0301f.jsp">明細</a></td>
    </tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	    boolean displayArray[] = {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center","center","center","center"};
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
