<!--
程式目的：藥品不良品通報查詢作業
程式代號：drgin0102q
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0102Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
String isPop = Common.get(request.getParameter("isPop"));
String isQuery = Common.get(request.getParameter("isQuery"));//隱藏綜合查詢返回按鈕


if ("doCorrection".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doCorrectionDrg0112();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("重新校正完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if ("doAssess".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doAssessDrg0102Q();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("重新評估完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0102Q) obj.doQueryOneDrg0102();

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
<script type="text/javascript" src="drgin0102.js"></script>
<script type="text/javascript">
var popWinName;
function checkField()
{	
	var alertStr = "";
	if(form1.state.value == "doCorrection"){
    	alertStr += checkEmpty(form1.correctionReason,"重新校正理由");
    }
	else if(form1.state.value == "doAssess"){
		alertStr += checkEmpty(form1.reAssessReason,"重新評估理由");
    }
	
	if(alertStr.length!=0){ alert(alertStr); return }

	beforeSubmit();
	form1.submit();
	//return true;	
}

function init() 
{
	
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden,spanUpdate,spanClear,spanConfirm','H');
	setFormItem("doUpload,btnQryExpense,doUpload2","R");	
	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);
	}else{
		changeTab(1);
	}
	if('<%=obj.getErrorMsg()%>'=='重新校正完成' || '<%=obj.getErrorMsg()%>'=='重新評估完成')
	{			
		form1.action = "drgin0101q.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}

	if('<%=isQuery%>'=='Y')
	{	
		setDisplayItem('spanDoQuit,spanShowCorrection,spanShowAssess');
	}	
}

function queryOne(id, type){
	if(form1.tabId.value=="3") 
		form1.drg03id.value=id;
	else if(form1.tabId.value=="7") 
		form1.drg07id.value=id;
	if(form1.tabId.value=="3" || form1.tabId.value=="7"){
		form1.state.value="queryOne";
		beforeSubmit();
		form1.submit();
	}
	if(form1.tabId.value=="11" || form1.tabId.value=="12"){
		var isPop = "A";
		if(type == "10")
			isPop = "F";
		else if(type == "11")
			isPop = "C";
		else if(type == "9")
			isPop = "C3";
						
		var jscript = "";	
		var prop = "";
		var windowTop=(document.body.clientHeight-400)/2+100;
		var windowLeft=(document.body.clientWidth-400)/2+100;
		prop=prop+"width=1250,height=720,";
		prop=prop+"top="+windowTop+",";
		prop=prop+"left="+windowLeft+",";
		prop=prop+"scrollbars=yes,resizable=yes";
		closeReturnWindow();
		returnWindow=window.open(getVirtualPath() + "tcbw/drgin/drgin0102q.jsp?isPop="+isPop+"&id="+id,"",prop);
	}
}

function popDrg5001(id){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popDrg5001.jsp?drg0001Id="+id,"",prop);
}

function changeForm(a){
	v = a.value;
	if(a.value == "01"){
		$('#s1').show();
		$('#s2').hide();
	}else{
		$('#s1').show();
		$('#s2').show();
	}
}

function popUserJobList(){	
	var prop="";
	var windowTop=120;
	var windowLeft=120;
	var q = "";
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	window.open(getVirtualPath()+"home/popUserJob.jsp?q="+q+"&v="+v,'popWinE',prop);	
}

$(function(){
	if("<%=isPop%>" == "A"){
		$("#t11").hide();
		$("#Tab11").hide();
		$("#t12").hide();
		$("#Tab12").hide();
	}else if("<%=isPop%>" == "C" || "<%=isPop%>" == "C3"){
		$("#t1").hide();
		$("#t2").hide();
		$("#t3").hide();
		$("#t4").hide();
		$("#t8").hide();
		$("#t9").hide();
		$("#t10").hide();
		$("#t11").hide();
		$("#t12").hide();
		$("#Tab1").hide();
		$("#Tab2").hide();
		$("#Tab3").hide();
		$("#Tab4").hide();
		$("#Tab8").hide();
		$("#Tab9").hide();
		$("#Tab11").hide();
		$("#Tab12").hide();
		if("<%=isPop%>" == "C3")
			changeTab(7);
		else
			changeTab(5);
	}else if("<%=isPop%>" == "F"){
		$("#t1").hide();
		$("#t2").hide();
		$("#t3").hide();
		$("#t4").hide();
		$("#t5").hide();
		$("#t6").hide();
		$("#t7").hide();
		$("#t8").hide();
		$("#t9").hide();
		$("#t11").hide();
		$("#t12").hide();
		$("#t1").hide();
		$("#Tab2").hide();
		$("#Tab3").hide();
		$("#Tab4").hide();
		$("#Tab5").hide();
		$("#Tab6").hide();
		$("#Tab7").hide();
		$("#Tab8").hide();
		$("#Tab9").hide();
		$("#Tab11").hide();
		$("#Tab12").hide();
		changeTab(10);
	}	
});
//列印不良品通報表
function report(isClose)
{
	form1.isCloseUserInfo.value=isClose;
	form1.action = "drgin0102p.jsp" ;
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "drgin0102q.jsp";
	form1.target = "_self";
}
//列印CAPA評估表
function capareport()
{
	form1.action = "drgin0114p.jsp" ;
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "drgin0102q.jsp";
	form1.target = "_self";
}
//彈出式視窗尋問是否遮蔽個資
function toAsk()
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;

	//點選不遮蔽個資時記錄相關LOG
	var q = "code=DRG01";
	q +="&methodName=print";
	q +="&db=Drg0001Db";
	q +="&hql=select id,applNo,notifierUserID,notifierName from Drg0001Db where id = " + form1.id.value;
	
	//彈出式視窗大小資訊

	
	prop=prop+"width=800,height=100,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/conin/conin1001f.jsp?" + q,"",prop);
	
}

function downLoadFile(fileType, fileRoute){
	if (null != fileRoute && "" != fileRoute) {
	    var prop='';
	    prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	    prop=prop+'width=450,';
	    prop=prop+'height=160';
	    closeReturnWindow();
	    returnWindow = window.open('../../downloadFileSimple?fileType='+fileType+'&fileID='+encodeURI(encodeURI(fileRoute)),'上傳檔案',prop);
	} else {
		alert("欄位不存在,請檢查!");
		return ;
	}
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<!--重新校正區============================================================-->
<div id="queryContainer" style="width:400px;height:50px;display:none">
    <iframe id="queryContainerFrame"></iframe>
    <table class="queryTable" id="doCorrection" style="width:400px;height:50px;display:none">
        <tr>
			<td nowrap class="td_form_left" colspan="2">重新校正</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >重新校正理由</td>
			<td nowrap class="td_form_white">
			   <input class="field_Q" type="text" name="correctionReason" size="50" maxlength="100" value="<%=obj.getCorrectionReason()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
				<input class="toolbar_default" followPK="false" type="button" name="doCorrection" value="確　　定" onClick="whatButtonFireEvent(this.name)">
				<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
			</td>			
		</tr>
     </table>

     <table class="queryTable" id="doAssess" style="width:400px;height:50px;display:none">
        <tr>
			<td nowrap class="td_form_left" colspan="2">重新評估</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >重新評估理由</td>
			<td nowrap class="td_form_white">
			   <input class="field_Q" type="text" name="reAssessReason" size="50" maxlength="100" value="<%=obj.getReAssessReason()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
				<input class="toolbar_default" followPK="false" type="button" name="doAssess" value="確　　定" onClick="whatButtonFireEvent(this.name)">
				<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
			</td>			
		</tr>
     </table>
</div>
<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="status" value="<%=obj.getStatus()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">	
	<input type="hidden" name="doType" value="<%=obj.getDoType()%>">
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj.getIsCloseUserInfo()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <%if("".equals(isPop)){%>
	<span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>	
	<%if("".equals(isPop) && "01,05".indexOf(Common.get(obj.getDrgLev())) != -1 && "90".equals(obj.getStatus())){%>
	<span id="spanShowCorrection">
	   <input class="toolbar_default" type="button" followPK="false" name="showCorrection" value="重 新 校 正" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>
	<%if("".equals(isPop) && "02,03,04".indexOf(Common.get(obj.getDrgLev())) != -1 && "90".equals(obj.getStatus())){%>
	<span id="spanShowAssess">
	   <input class="toolbar_default" type="button" followPK="false" name="showAssess" value="重 新 評 估" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>
	<%if("".equals(isPop)){%>
	<input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doAddDoc" value="案件補件紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doMailList" value="郵件清單紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doFlow" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">	
	<input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
	<input class="toolbar_default" type="button" followPK="false" name="doCAPAReportView" value="列印CAPA評估表" onClick="whatButtonFireEvent(this.name)">
	<%}%>
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
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	    <td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良藥品</a></td>
	    <td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">相關附件</a></td>
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">案件初評</a></td>
	    <td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">案件分級確認</a></td>
	    <td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">CAPA確認表</a></td>
	    <td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">CAPA分析表</a></td>
	    <td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">CAPA評估表</a></td>
	</tr>
	<tr>   
	    <td nowrap ID="t8" CLASS="tab_border2"><a id="aTab8" href="#" onClick="changeTab(8);">案件評估</a></td>
	    <td nowrap ID="t9" CLASS="tab_border2"><a id="aTab9" href="#" onClick="changeTab(9);">案件分析</a></td>
	    <td nowrap ID="t11" CLASS="tab_border2"><a id="aTab11" href="#" onClick="changeTab(11);">歷史通報</a></td>
	    <td nowrap ID="t12" CLASS="tab_border2"><a id="aTab12" href="#" onClick="changeTab(12);">歷史CAPA評估</a></td>
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td>
	<div id="formContainer" style="height:auto;display:none">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="drgin0102_2q.jsp" />
	</div>	
</td></tr>
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
       <jsp:include page="drgin0103f.jsp" />
       <jsp:include page="drgin0107f.jsp" />
       <jsp:include page="drgin0110f.jsp" />
       <jsp:include page="drgin0113f.jsp" />
       <jsp:include page="drgin0115f.jsp" />       
       <jsp:include page="drgin0119f.jsp" />
	</div>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "doDoQuit":
		  form1.action = "drgin0101q.jsp";
		  form1.state.value = "";
		  form1.queryAllFlag.value = "true";
		  form1.submit();
		  break;
	  case "showCorrection":
		  document.getElementById("doCorrection").style.display = 'block';
		  queryShow('queryContainer');
		  break;
	  case "doCorrection":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "showAssess":
		  document.getElementById("doAssess").style.display = 'block';
		  queryShow('queryContainer');
		  break;
	  case "doAssess":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doQHistory":
		  form1.submit();
		  break;
	  case "doReport":
		  form1.action = "drgin0103p.jsp";
		  form1.target = "_blank";
		  beforeSubmit();
		  form1.submit();
		  form1.action = "drgin0102q.jsp";
		  form1.target = "_self";
		  break;
	  case "doOriginalVer":
		  popDrg5001('<%=obj.getId()%>');
		  break;
	  case "doAddDoc":
		  popCon0004('<%=obj.getApplNo()%>');
		  break;
	  case "doMailList":
		  popCon0002('DRG','<%=obj.getId()%>');
		  break;
	  case "doFlow":
		  popCon2001('DRG1','<%=obj.getId()%>');
		  break;
	  //案件列印-詢問是否遮蔽個資	
	  case "doReportView":
		  toAsk();
		  break;
	  //CAPA評估表列印	  
	  case "doCAPAReportView":
		  capareport();
		  break;	  
		  
	}
}


</script>
</body>
</html>
