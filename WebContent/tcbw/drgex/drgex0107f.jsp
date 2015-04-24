<!--
程式目的：藥品不良品通報-廠商回覆-CAPA分析表
程式代號：drgin0107
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
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0107F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
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
else if ("doAnalyOver".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doAnalyOver();
		obj.setErrorMsg("分析完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if ("doCheckNo".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doCheckNo();
		obj.setErrorMsg("批號確認完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0107F)obj.queryOne();
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
	alertStr += checkRadioButton(form1.medicineType06,"學名藥/原廠藥");
	alertStr += checkRadioButton(form1.produceType06,"國產/輸入");
	alertStr += checkRadioButton(form1.lotType06,"批號範圍  ");
	alertStr += checkRadioButton(form1.defect06,"廠商不良品缺陷");
	alertStr += checkRadioButton(form1.survey06,"廠商調查結果");
	alertStr += checkRadioButton(form1.precaution06,"廠商預防措施");
	if("90" == getRadioChecked(form1.defect06)){
		alertStr += checkEmpty(form1.defectOther06,"廠商不良品缺陷其他描述" );
	}

	if("90" == getRadioChecked(form1.survey06)){
		alertStr += checkEmpty(form1.surveyOther06,"廠商調查結果其他描述" );
	}

	if("90" == getRadioChecked(form1.precaution06)){
		alertStr += checkEmpty(form1.precautionOther06,"廠商預防措施其他描述" );
	}
	if(alertStr.length!=0){ alert(alertStr); return }

	beforeSubmit();
	form1.submit();
	return true;
}


function init() 
{	
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden','H');
	setFormItem("doAnalyOver","R");
	document.all.item("confirm").value = "暫　存";
	document.all.item("update").value = "案 件 分 析";	
    
    <%if(obj.checkNo()){%>
        alert("請先進行批號確認");
    <%}%>

    <%if(obj.countDrg07()>1){%>
        setFormItem("update","R");
    <%}%>

	if('<%=obj.getErrorMsg()%>'=='批號確認完成')
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
		alertStr += checkEmpty(form1.applNo,"主檔編號");
		if(alertStr.length != 0){
			alert("請先執行查詢, 若已查到資料則請選取其中一筆資料");
			return false;
		} else {
			form1.state.value="queryAll";
		}
		form1.action = surl;
		beforeSubmit();
		form1.submit();
	}
}

function getCheckCAPA(isJS)
{    
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-100)/2+100;
	var windowLeft=300;
	prop=prop+"width=1200,height=300,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/drgex/drgex0108f.jsp?applNo="+form1.applNo.value+"&isJS="+isJS,"",prop);
}

function doCheckNo()
{
   form1.state.value="doCheckNo";
   form1.submit();
   return true;
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
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <%if(obj.checkNo()){%>
    <span id="spanDoCheckNo">
		<input class="toolbar_default" type="button" followPK="false" name="doCheckNo" value="批 號 確 認" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>
    <span id="spanDoAnalyOver">
		<input class="toolbar_default" type="button" followPK="false" name="doAnalyOver" value="分 析 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
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
		<TD ID=t1 CLASS="tab_border1">CAPA分析表</TD>
		<TD ID=t2 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0109f.jsp');">CAPA評估表</A></TD>
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
      <table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">藥品不良品基本資料</td>
		</tr>		
		<tr>
			<td nowrap class="td_form">藥品名</td>
			<td nowrap class="td_form_white" colspan="3">
			      中文品名 <input class="field_RO" type="text" name="chProduct" size="100" maxlength="100"  value="<%=obj.getChProduct()%>"/><br>
			      英文品名 <input class="field_RO" type="text" name="enProduct" size="100" maxlength="200"  value="<%=obj.checkGet(obj.getEnProduct())%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">許可證字號</td>
			<td nowrap class="td_form_white">
			<select class="field_RO" name="permitKey" class="field" >
				<%=View.getOptionCodeKind("DRGPKID", obj.getPermitKey())%>
			</select>			 
			    <input class="field_RO" type="text" name="permitNo" size="6" maxlength="6" value="<%=obj.getPermitNo()%>" />號			    
			</td>					
			<td nowrap class="td_form">批號</td>
			<td nowrap colspan="3" class="td_form_white">
                <input class="field_RO" type="text" name="manufactorNo" size="15" maxlength="11" value="<%=obj.getManufactorNo()%>"/>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">主成分</td>
			<td nowrap class="td_form_white"  colspan="3">
                <input class="field_RO" type="text" name="ingredient" size="50" maxlength="50" value="<%=obj.getIngredient()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">劑型</td>			
			<td nowrap class="td_form_white"  colspan="3">
                <%=View.getRadioBoxOption("field_RO", "medModel", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGFLN' order by codeId", obj.getMedModel())%>			
			    (請描述)：<input class="field_RO" type="text" name="medModelOther" size="40" maxlength="50" value="<%=obj.getMedModelOther()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">申請商/藥商</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="applicationName" size="40" maxlength="50" value="<%=obj.getApplicationName()%>"/>
			</td>
			<td nowrap class="td_form">製造廠</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="manufactorName" size="40" maxlength="50" value="<%=obj.getManufactorName()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">廠商案件分析</td>
		</tr>		
		<tr>
			<td nowrap class="td_form">學名藥/原廠藥</td>
			<td nowrap class="td_form_white" colspan="3">
			  <%=View.getRadioBoxOption("field", "medicineType06", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0101' order by codeId", obj.getMedicineType06())%>		
		      <input class="field_RO" type="hidden" name="analyDate06" size="7" value="<%=obj.getAnalyDate06()%>"/>
		    </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">國產/輸入</td>
			<td nowrap class="td_form_white" colspan="3">
               <%=View.getRadioBoxTextOption("field", "produceType06", "1;國產;2;進口;", obj.getProduceType06())%>		
			 </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">批號範圍</td>
			<td nowrap class="td_form_white" colspan="3">
               <%=View.getRadioBoxOption("field", "lotType06", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0107' order by codeId", obj.getLotType06())%>		
		    </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">廠商不良品缺陷</td>
			<td nowrap class="td_form_white" colspan="3">
		       <%=View.getRadioBoxOption("field", "defect06", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0108' order by codeId", obj.getDefect06())%>
		       (請描述)：<input class="field" type="text" name="defectOther06" size="40" maxlength="50" value="<%=obj.getDefectOther06()%>"/>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">廠商調查結果</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field", "survey06", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0109' order by codeId", obj.getSurvey06())%>
		       (請描述)：<input class="field" type="text" name="surveyOther06" size="40" maxlength="50" value="<%=obj.getSurveyOther06()%>"/>		
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">廠商預防措施</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field", "precaution06", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0110' order by codeId", obj.getPrecaution06())%>
		       (請描述)：<input class="field" type="text" name="precautionOther06" size="40" maxlength="50" value="<%=obj.getPrecautionOther06()%>"/>		
			</td>		
		</tr>
</table>
</div>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  setFormItem("doAnalyOver","O");
		  break;
	  case "doCheckNo":
		  getCheckCAPA('window.opener.doCheckNo');
		  break;
	  case "doAnalyOver":
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
