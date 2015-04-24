<!--
程式目的：藥品不良品通報登錄作業
程式代號：drgex0101
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGEX0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0101F">
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
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">
	<tr><td nowrap>通報區</td></tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">案件類別：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">			
		</td>
		<td nowrap width="15%" nowrap class="td_form">案件編號：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">		
			~<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">		
		</td>
	</tr>  	
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_dateS","")%>~<%=View.getPopCalendar("field_Q","q_dateE","")%>				
		</td>
		<td nowrap width="15%" nowrap class="td_form">收案日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_dateS","")%>~<%=View.getPopCalendar("field_Q","q_dateE","")%>				
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">發生日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_dateS","")%>~<%=View.getPopCalendar("field_Q","q_dateE","")%>				
		</td>
		<td nowrap width="15%" nowrap class="td_form">服務機構：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="50" maxlength="100" value="">
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件狀態：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_mean" size="50" maxlength="100" value="">						
		</td>
	</tr>
    <tr><td nowrap>藥品區</td></tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">許可證字號：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">			
		</td>
		<td nowrap width="15%" nowrap class="td_form">藥品品名：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">			
		</td>
	</tr>  	
	<tr>
		<td nowrap width="15%" nowrap class="td_form">有效成分/學名：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">申請商：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">					
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">製造廠：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="50" maxlength="100" value="">
		</td>
		<td nowrap width="15%" nowrap class="td_form">製造批號：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="50" maxlength="100" value="">
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件狀態：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_mean" size="50" maxlength="100" value="">						
		</td>
	</tr>
    <tr><td nowrap>不良品區</td></tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">不良品風險評估結果：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">			
		</td>
		<td nowrap width="15%" nowrap class="td_form">不良品原因：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">			
		</td>
	</tr>  	
	<tr>
		<td nowrap width="15%" nowrap class="td_form">不良品缺陷：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">		
		</td>
	</tr>
	<tr><td nowrap>療效不等區</td></tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">NTI Drugs：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">			
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報事件後果：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">			
		</td>
	</tr>  	
	<tr>
		<td nowrap width="15%" nowrap class="td_form">療效不等評估結果：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">		
		</td>
	</tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">選取查詢結果欄位：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
<input type="checkbox" >案件編號
<input type="checkbox" >通報日期
<input type="checkbox" >通報來源
<input type="checkbox" >通報者屬性
<input type="checkbox" >通報者職稱<br>
<input type="checkbox" >不良事件類別
<input type="checkbox" >許可證字號
<input type="checkbox" >登錄編號
<input type="checkbox" >化粧品品名<br>
<input type="checkbox" >化粧品項目
<input type="checkbox" >販賣通路
<input type="checkbox" >購買地點
<input type="checkbox" >不良品缺陷描述(小類)<br>
<input type="checkbox" >不良品初步判定結果
<input type="checkbox" >不良反應狀況
<input type="checkbox" >停用後不良反應是否減輕
<input type="checkbox" >再使用是否出現同樣反應<br>
<input type="checkbox" >併用化粧品品名
<input type="checkbox" >時序性
<input type="checkbox" >後續處置
<input type="checkbox" >不良反應與可疑化粧品相關性
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

<tr>
<td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">案件編號</a></th>		
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">藥品品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">申請商</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">製造廠</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">不良品缺陷/通報事件後果</a></th>
	    <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">製造批號</a></th>
	    <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">案件狀態</a></th>
	</tr>
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
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {false,false,false,false,false,false,false};
	    boolean displayArray[] = {true,true,true,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center","center","center"};
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
