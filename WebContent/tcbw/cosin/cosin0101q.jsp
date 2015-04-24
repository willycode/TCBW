<!--
程式目的：化粧品不良事件通報查詢作業
程式代號：cosin0101q
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0101Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
//String caseType = Common.get(request.getParameter("caseType"));
//obj.setCaseType(caseType);
if ("init".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())){
	obj = (com.kangdainfo.tcbw.view.cosin.COSIN0101Q)obj.queryOne();
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
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
var v = "";
function checkField(){
	var alertStr = "";
	form1.state.value = "queryAll";
	if(form1.state.value == "queryAll"){
	// 不檢核 103.07.24
	}	
	
	if(alertStr.length != 0){ 
		alert(alertStr); return false; 
	}
	
	beforeSubmit();
	return true;
}

function init() {
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	setFormItem("btnQryExpense", "O");
}

function changeForm(a){
	v = a;
	if(a == "01"){
		$('#s1').show();
		$('#s2').hide();
	}else{
		$('#s1').show();
		$('#s2').show();
	}
}

function popUserJobList(){	
	var prop = "";
	var windowTop = 120;
	var windowLeft = 120;
	var q = "";
	prop = prop+"width=1200px,height=600,";
	prop = prop+"top="+windowTop+",";
	prop = prop+"left="+windowLeft+",";
	prop = prop+"scrollbars=yes";
	window.open(getVirtualPath()+"home/popUserJob.jsp?q="+q+"&v="+v,'popWinE',prop);	
}

function queryOne(id){
    form1.id.value = id;
    form1.state.value = "queryOne";
    form1.action = "cosin0102q.jsp";
	beforeSubmit();
	form1.submit();
}

$(function(){
	$("#s1,#s2").hide();
	
	$(":radio[name=q_notifierType]").bind("change", function(){
		changeForm($(this).val());
	});
});
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
	
	<input class="toolbar_default" type="submit" followPK="false" name="drgin0101q_queryAll" value="查　詢">&nbsp;
	<span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
	<span id="spanListToExcel">
        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
    </span>
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>
<!--Form區============================================================-->
<tr><td class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">
	<tr>
		<td nowrap width="15%" class="td_form">案件編號：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="12" maxlength="12" value="<%=obj.getQ_applNoS()%>">
			~		
			<input class="field_Q" type="text" name="q_applNoE" size="12" maxlength="12" value="<%=obj.getQ_applNoE()%>">
		</td>
		<td nowrap width="15%" class="td_form">發生日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q", "q_occurDateS", obj.getQ_occurDateS())%>
			~
			<%=View.getPopCalendar("field_Q", "q_occurDateE", obj.getQ_occurDateE())%>				
		</td>
	</tr>  
	<tr>
		<td nowrap class="td_form">通報日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q", "q_notifierRevDateS", obj.getQ_notifierRevDateS())%>
			~
			<%=View.getPopCalendar("field_Q", "q_notifierRevDateE", obj.getQ_notifierRevDateE())%>				
		</td>
		<td nowrap class="td_form">收案日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q", "q_notifierRepDateS", obj.getQ_notifierRepDateS())%>
			~
			<%=View.getPopCalendar("field_Q", "q_notifierRepDateE", obj.getQ_notifierRepDateE())%>			
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報來源：</td>
		<td nowrap class="td_form_white">
			<%=View.getCommonRadioBoxOption("field_Q", "q_notifierSource", "CIS", obj.getQ_notifierSource(), "codeId", 4)%>
		</td>
		<td nowrap class="td_form">通報單位：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field_Q", "q_notifierType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", "")%>
			<br>
			<input class="field_Q" type="text" id="s1" name="q_notifierDept" size="30" maxlength="30" value=""/>
	  	    <input type="button" id="s2" name="btnQryExpense" onClick="popUserJobList();" value="查詢" width="120px" class="field"/>						
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">許可證字：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_permitKey" type="select">
				<%=View.getOptionCodeKind("CPT", obj.getQ_permitKey()) %>
			</select>
			 字第
			<input class="field_Q" type="text" name="q_permitNo" size="15" maxlength="15" value="<%=obj.getQ_permitNo()%>">				
		</td>
		<td nowrap class="td_form">不良事件類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getCommonCodeKindBoxOption("field_Q", "q_cosType", "CCT", obj.getQ_cosType()) %>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">化粧品品名：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct" size="50" maxlength="100" value="<%=obj.getQ_chProduct()%>">		
		</td>
		<td nowrap class="td_form">製造廠/進口代理商：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_manufactorName" size="50" maxlength="100" value="<%=obj.getQ_manufactorName()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件狀態：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_status" type="select">
				<%=View.getOptionCodeKind("CCS", obj.getQ_status()) %>
			</select>	
		</td>
		<td nowrap class="td_form">留案備查：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="checkbox" name="q_preResult" value="Y" <%=obj.getQ_preResult().equals("Y")?"checked":"" %>>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">是否為歷史移轉資料：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
		    <%=View.getRadioBoxTextOption("field_Q","q_isTrans","Y;是;N;否;",obj.getQ_isTrans())%>		
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">不良品缺陷：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=obj.getCOS0003DbCheckBoxOption("field_Q", "q_mainCode", obj.getQ_mainCode()) %>
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
<tr><td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">通報日期</a></th>		
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">不良事件類別</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">外文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">製造廠/進口代理商</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">不良反應狀況</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',9,false);" href="#">不良品缺陷</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',10,false);" href="#">案件狀態</a></th>
		<th class="listTH"><a class="text_link_w" >明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true,true,true,true};
	    boolean linkArray[] = {false,true,false,false,false,false,false,false,false,false,false};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center","center"};
	    out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag(),true,linkArray,null,"",false,false,false,"",false,false,0,true));
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
