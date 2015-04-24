<!--
程式目的：藥品不良品通報-廠商回覆-CAPA評估表
程式代號：drgin0109
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGEX0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0109F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
		obj.setErrorMsg("修改完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if ("doReplyOver".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doReplyOver();
		obj.setErrorMsg("評估回覆完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

objList = (java.util.ArrayList) obj.doQueryAll();
obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0109F)obj.queryOne();
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
<script type="text/javascript" src="./../../js/jquery.js"></script>
<script type="text/javascript">
var popWinName;
function checkField()
{
	var alertStr = "";

	alertStr += checkEmpty(form1.checkResult07,"清查結果");
	alertStr += checkEmpty(form1.survey07,"調查報告");
	alertStr += checkEmpty(form1.precaution07,"預防矯正措施及改善時程");
    alertStr += checkLen(form1.checkResult07,"清查結果",500);
    alertStr += checkLen(form1.survey07,"調查報告",500);
    alertStr += checkLen(form1.precaution07,"預防矯正措施及改善時程",500);
	
	if(alertStr.length!=0){ alert(alertStr); return }

	beforeSubmit();
	form1.submit();
	return true;
}


function init() 
{	
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden','H');
	setFormItem("doReplyOver","R");
	document.all.item("confirm").value = "暫　存";
	document.all.item("update").value = "評 估 回 覆";
	
    if('<%=obj.getErrorMsg()%>'=='評估回覆完成')
	{		
		form1.action = "drgex0101f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}	
}

function checkURL(surl){
	var alertStr = "";
	if (form1.state.value=="update" || form1.state.value=="updateError") {
		alert("修改狀態無法更換頁標籤，請先點選取消!");
	}else{		
		if(alertStr.length != 0){
			alert("請先執行查詢, 若已查到資料則請選取其中一筆資料");
			return false;
		} else {
			form1.state.value="queryOne";
		}
		form1.action = surl;
		beforeSubmit();
		form1.submit();
	}
}

function queryOne(id){
	form1.drg07id.value=id;
	form1.state.value="queryOne";
	beforeSubmit();
	form1.submit();
}

function checkAnalyDate(){
	if(form1.analyDate.value ==""){ 
		alert("請先進行CAPA分析表填寫!");
		form1.state.value="queryOne";
		beforeSubmit();
		form1.submit();
	}
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
</div>
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=obj.getUserID()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
    <input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
    <input type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
    <input type="hidden" name="analyDate" value="<%=obj.getAnalyDate()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <span id="spanDoReplyOver">
		<input class="toolbar_default" type="button" followPK="false" name="doReplyOver" value="回 覆 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanShow"></span>
    </td>
</tr>

<tr>
<td nowrap>
  <% request.setAttribute("QueryBean",obj);%>
  <jsp:include page="../../home/page_row.jsp" />
</td>
</tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>		
		<TD ID=t1 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0107f.jsp');">CAPA分析表</A></TD>
		<TD ID=t2 CLASS="tab_border1">CAPA評估表</TD>
	</TR>
	<tr>
		<td class="tab_line1"></td>
		<td class="tab_line2"></td>	
	</tr>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
     <table id="Tab7" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">藥品不良品基本資料
			   <input type="hidden" name="drg07id" value="<%=obj.getDrg07id()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">通報編號</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="applNo" size="10" value="<%=obj.getApplNo()%>">
			</td>
			<td nowrap class="td_form" >許可證字號</td>
			<td nowrap class="td_form_white">
			    <select class="field" name="permitKey" class="field_RO">
				   <%=View.getOptionCodeKind("DRGPKID", obj.getPermitKey())%>
			    </select>			 
			    <input class="field_RO" type="text" name="permitNo" size="6" maxlength="6" value="<%=obj.getPermitNo()%>" />號			    
			</td>		
		</tr>
		<%if("04".equals(obj.getDrgLev())){%>
		<tr>
			<td nowrap class="td_form" >高頻率案件資訊</td>
			<td nowrap class="td_form_white" colspan="3">
				一年內本藥品同此次瑕疵案件之同批號的各案件編號：<input class="field_RO" type="text" size="100"  value="<%=obj.getHisApplNoY()%>"><br>
				一年內本藥品同此次瑕疵案件之不同批號的各案件編號：<input class="field_RO"  type="text" size="100" value="<%=obj.getHisApplNoN()%>">
			</td>		
		</tr>
		<%}%>
		<tr>
			<td nowrap class="td_form" >藥品名</td>
			<td nowrap class="td_form_white" colspan="3">
			      中文品名 <input class="field_RO" type="text" name="chProduct" size="100" maxlength="100"  value="<%=obj.getChProduct()%>"/><br>
			      英文品名 <input class="field_RO" type="text" name="enProduct" size="100" maxlength="200"  value="<%=obj.checkGet(obj.getEnProduct())%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >主成分</td>
			<td nowrap class="td_form_white" colspan="3">
                <input class="field_RO" type="text" name="ingredient" size="50" maxlength="50" value="<%=obj.getIngredient()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >劑型</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field_RO", "medModel", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGFLN' order by codeId", obj.getMedModel())%>			
			    (請描述)：<input class="field_RO" type="text" name="medModelOther" size="40" maxlength="50" value="<%=obj.getMedModelOther()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >申請商/藥商</td>
			<td nowrap class="td_form_white" >
                <input class="field_RO" type="text" name="applicationName" size="40" maxlength="50" value="<%=obj.getApplicationName()%>"/>
			</td>
			<td nowrap class="td_form" >製造廠</td>
			<td nowrap class="td_form_white" >
                <input class="field_RO" type="text" name="manufactorName" size="40" maxlength="50" value="<%=obj.getManufactorName()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >批號</td>
			<td nowrap class="td_form_white" colspan="3">
                <input class="field_RO" type="text" name="manufactorNo" size="15" maxlength="11" value="<%=obj.getManufactorNo()%>"/>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >不良品缺陷之描述</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=obj.getCheckBoxOption2("field_RO", "mainCode", "subCode", "otherDescribe", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId", obj.getMainCode(),obj.getSubCode(),obj.getId())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >歷年類似不良品通報情形</td>
			<td nowrap class="td_form_white" colspan="3">
				歷年本藥品之通報件數：<input class="field_RO" type="text" size="3"  value="<%=obj.getHisData1()%>">件
				<br>歷年本藥品同此次瑕疵之通報件數：<input class="field_RO"  type="text" size="3" value="<%=obj.getHisData2()%>">件
				<br>一年內本藥品之通報件數：<input class="field_RO" type="text" size="3" value="<%=obj.getHisData3()%>">件
				<br>一年內本藥品同此次瑕疵之通報件數：<input class="field_RO" type="text" size="3" value="<%=obj.getHisData4()%>">件
				<br>一年內本藥品同此次瑕疵之高風險通報件數：<input class="field_RO" type="text" size="3" value="<%=obj.getHisData5()%>">件
				<br>一年內本藥品同此次瑕疵案件：<%=obj.getHisData6()%>
				<br>一年內本藥品同此次瑕疵之高風險案件：<%=obj.getHisData7()%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >風險等級</td>
			<td nowrap class="td_form_white">
                <%=View.getRadioBoxOption("field_RO", "drgLev07", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRKL' order by codeShortName", obj.getDrgLev())%>
			</td>
			<td nowrap class="td_form" >本案原由</td>
			<td nowrap class="td_form_white">
               <input class="field_RO" type="radio" name="caseReason07" checked>通報系統&nbsp;
			   <input class="field_RO" type="radio" name="caseReason07">廠商主動通報&nbsp;
			   <input class="field_RO" type="radio" name="caseReason07">警訊監控&nbsp;
			   <input class="field_RO" type="radio" name="caseReason07">品質監測
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">清查結果</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="checkResult07" cols="100" rows="6"><%=obj.getCheckResult07()%></textarea>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">調查報告</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="survey07" cols="100" rows="6"><%=obj.getSurvey07()%></textarea>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">預防矯正措施及改善時程</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="precaution07" cols="100" rows="6"><%=obj.getPrecaution07()%></textarea>
			</td>		
		</tr>
<!-- List 區   -->	
<tr><td nowrap class="bgPagging" colspan="4">		
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>


<tr>
<td nowrap class="bgList" colspan="4">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">評估日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">清查結果</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">調查結果</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">預防矯正措施及改善時程</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center"};
	    out.write(View.getQuerylist(primaryArray,displayArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</div>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  checkAnalyDate();
		  setFormItem("doReplyOver","O");
		  break;
	  case "doReplyOver":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doDoQuit":
		  form1.action = "drgex0101f.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	}
}
</script>
</body>
</html>
