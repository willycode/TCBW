<!--
程式目的：藥品療效不等通報資料補登作業
程式代號：comple0201f
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COMPLE0201" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.comple.COMPLE0201F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<%
if("".equals(obj.getUserID()))
{
  obj.setUserID(User.getUserId());
}
if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdate();
		obj.setErrorMsg("修改完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("upload".equals(obj.getState()))
{
	try
	{		
		obj.doUpdate();
		obj.setToUpdate("Y"); //維持修改模式
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.comple.COMPLE0201F) obj.doQueryOne();
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
$(function()
{
	$('#Tab4 :input').bind('click change',function(){
		if(form1.state.value=="update"){		
			form1.drg04Update.value="Y";
		}
	});	
});

function checkField()
{
	var alertStr = "";
	if(form1.state.value=="stayedUpload" || form1.state.value=="doSend"){
		//alertStr += checkDate(form1.occurDate, "發生未預期反應日期");		
	}
	if(alertStr.length!=0){ alert(alertStr); return }

	beforeSubmit();
	form1.submit();	
}

function init() 
{
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden','H');
	//檔案上傳維持在修改狀態
	if(form1.toUpdate.value=="Y"){
		whatButtonFireEvent("update");
	}else{
		setFormItem("doUpload,btnQryExpense","R");
	}	
	form1.toUpdate.value="";	
	
	setFormItem("doUpload,btnQryExpense","R");
	//document.all.item("confirm").value = "暫　存";
	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);
	}else{
		changeTab(1);
	}
	
	showAssess();
	chgConSequence();
	chgConSequence4();
}

function showAssess(){
	document.getElementById("assessShow1").style.display="none";
	document.getElementById("assessShow2").style.display="none";
	for(var i=0;i<form1.conSequence4.length;i++)
	{
		if(form1.conSequence4[i].checked){
			if("1" == form1.conSequence4[i].value){
				document.getElementById("assessShow1").style.display="block";
			}
			if("2" == form1.conSequence4[i].value){
				document.getElementById("assessShow2").style.display="block";
			}
		}
	}
}

function upload()
{
	 var prop='';
	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();

     var url="../../home/popManyUploadSimple.jsp?";
         url+="fileKind=DRG";
         url+="&systemType=DRG020001";
         url+="&uploadId="+form1.id.value;
         url+="&dbName=Drg4001Db";
         
	 returnWindow = window.open(url,'上傳檔案',prop);
}

function clearUser()
{
   form1.notifierUserID.value="";
   form1.notifierName.value="";
   form1.notifierTelArea.value="";
   form1.notifierTel.value="";
   form1.notifierTelExt.value="";
   form1.notifierEmail.value="";
   form1.notifierCounty.value="";
   form1.notifierZipCode.value="";
   form1.notifierAddress.value="";
   form1.notifierTitle.value="";
   form1.notifierDept.value="";
   form1.notifierDeptID.value="";
   
   //將欄位解除鎖定，讓使用者能自行輸入
   
   document.all.item("notifierName").className = "field";
   document.all.item("notifierName").readOnly = false;
   document.all.item("notifierTelArea").className = "field";
   document.all.item("notifierTelArea").readOnly = false;
   document.all.item("notifierTel").className = "field";
   document.all.item("notifierTel").readOnly = false;
   document.all.item("notifierTelExt").className = "field";
   document.all.item("notifierTelExt").readOnly = false;
   document.all.item("notifierEmail").className = "field";
   document.all.item("notifierEmail").readOnly = false;
   document.all.item("notifierCounty").className = "field";
   document.all.item("notifierCounty").disabled = false;
   document.all.item("notifierZipCode").className = "field";
   document.all.item("notifierZipCode").disabled = false;
   document.all.item("notifierAddress").className = "field";
   document.all.item("notifierAddress").readOnly = false;
   document.all.item("notifierTitle").className = "field";
   document.all.item("notifierTitle").disabled = false;	

   var len = document.getElementsByName("notifierType").length;	
   for(var i=0 ; i<len ; i++ ){		
	   document.getElementsByName("notifierType")[i].className="field";		
	   document.getElementsByName("notifierType")[i].disabled = false;	
   }   
}

function popUserForm() {
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=800,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("../../home/popUser.jsp?","",prop);
}

function beforeInit() {
	<%=obj.getDrg42JSBuilder()%>
	<%=obj.getDrg43JSBuilder()%>
	<%=obj.getConJSBuilder()%>
}

function changeTab(tabId) 
{
	form1.tabId.value = tabId;
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";
	document.getElementById("t10").className = "tab_border2";	
	document.getElementById("t4").className = "tab_border2";
	
	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab10").className = "";	
	document.getElementById("aTab4").className = "";
	
	
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';
	document.getElementById("Tab4").style.display = 'none';
	document.getElementById("Tab10").style.display = 'none';
	
	if(tabId == 2){
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";		
	}
	else if (tabId == 3){
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";	
	}else if (tabId == 4){
		document.getElementById("t4").className = "tab_border1";	
		document.getElementById("Tab4").style.display = '';
		document.getElementById("aTab4").className = "text_w";	
	}else if (tabId == 10){
		document.getElementById("t10").className = "tab_border1";	
		document.getElementById("Tab10").style.display = '';
		document.getElementById("aTab10").className = "text_w";	
	}else{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}

function clearUser()
{
   form1.notifierUserID.value="";
   form1.notifierName.value="";
   form1.notifierPhone.value="";
   form1.notifierTelArea.value="";
   form1.notifierTel.value="";
   form1.notifierTelExt.value="";
   form1.notifierEmail.value="";
   form1.notifierCounty.value="";
   form1.notifierZipCode.value="";
   form1.notifierAddress.value="";
   form1.notifierDept.value="";
   form1.notifierDeptID.value="";
   form1.notifierFax.value="";
   form1.notifierFaxArea.value="";
   
   //將欄位解除鎖定，讓使用者能自行輸入
   
   document.all.item("notifierName").className = "field";
   document.all.item("notifierName").readOnly = false;
   document.all.item("notifierPhone").className = "field";
   document.all.item("notifierPhone").readOnly = false;
   document.all.item("notifierTelArea").className = "field";
   document.all.item("notifierTelArea").readOnly = false;
   document.all.item("notifierTel").className = "field";
   document.all.item("notifierTel").readOnly = false;
   document.all.item("notifierTelExt").className = "field";
   document.all.item("notifierTelExt").readOnly = false;
   document.all.item("notifierEmail").className = "field";
   document.all.item("notifierEmail").readOnly = false;
   document.all.item("notifierCounty").className = "field";
   document.all.item("notifierCounty").disabled = false;
   document.all.item("notifierZipCode").className = "field";
   document.all.item("notifierZipCode").disabled = false;
   document.all.item("notifierAddress").className = "field";
   document.all.item("notifierAddress").readOnly = false;
   document.all.item("notifierTitle").className = "field";
   document.all.item("notifierTitle").disabled = false;
   document.all.item("notifierFax").className = "field";
   document.all.item("notifierFax").disabled = false;	
   document.all.item("notifierFaxArea").className = "field";
   document.all.item("notifierFaxArea").disabled = false;	

   if(isObj(form1.notifierTitle)){
		var notifierTitles = form1.notifierTitle;
		for (var i=0; i<notifierTitles.length; i++) {
			notifierTitles[i].className = "field";
			notifierTitles[i].disabled = false;
		}
	}
	if(isObj(form1.notifierType)){
		var notifierTypes = form1.notifierType;
		for (var i=0; i<notifierTypes.length; i++) {
			notifierTypes[i].className = "field";
			notifierTypes[i].disabled = false;
		}
	}  
}

function chgConSequence(){
	var conSequenceStr="";	
	for(var i=0;i<form1.conSequence.length;i++)
	{
		if(form1.conSequence[i].checked){
			if("1" == form1.conSequence[i].value){
				if(isObj(form1.effectChangeDesc)){
					var effectChangeDescs = form1.effectChangeDesc;
					for (var j=0; j<effectChangeDescs.length; j++) {
						effectChangeDescs[j].className = 'field';
						effectChangeDescs[j].disabled = false;
					}
				}
				conSequenceStr += "1";
			}

			if("2" == form1.conSequence[i].value){
				if(isObj(form1.badReactionLev)){
					var badReactionLevs = form1.badReactionLev;
					for (var j=0; j<badReactionLevs.length; j++) {
						badReactionLevs[j].className = 'field';
						badReactionLevs[j].disabled = false;
					}
				}

				document.all.item('badReactionDesc').disabled = false;
				document.all.item('badReactionDesc').className = 'field';
				conSequenceStr += "2";
			}
		}
	}
	if("1" == conSequenceStr){
		document.all.item('badReactionDesc').disabled = true;
		document.all.item('badReactionDesc').className = 'field_RO';
		document.all.item('badReactionDesc').value = "";

		if(isObj(form1.badReactionLev)){
			var badReactionLevs = form1.badReactionLev;
			for (var i=0; i<badReactionLevs.length; i++) {
				badReactionLevs[i].className = 'field_RO';
				badReactionLevs[i].disabled = true;
				badReactionLevs[i].checked = false;
			}
		}
	}else if("2" == conSequenceStr){
		if(isObj(form1.effectChangeDesc)){
			var effectChangeDescs = form1.effectChangeDesc;
			for (var i=0; i<effectChangeDescs.length; i++) {
				effectChangeDescs[i].className = 'field_RO';
				effectChangeDescs[i].disabled = true;
				effectChangeDescs[i].checked = false;
			}
		}
	}else if(null == conSequenceStr || "" == conSequenceStr){
		document.all.item('badReactionDesc').disabled = true;
		document.all.item('badReactionDesc').className = 'field_RO';
		document.all.item('badReactionDesc').value = "";

		if(isObj(form1.badReactionLev)){
			var badReactionLevs = form1.badReactionLev;
			for (var i=0; i<badReactionLevs.length; i++) {
				badReactionLevs[i].className = 'field_RO';
				badReactionLevs[i].disabled = true;
				badReactionLevs[i].checked = false;
			}
		}

		if(isObj(form1.effectChangeDesc)){
			var effectChangeDescs = form1.effectChangeDesc;
			for (var i=0; i<effectChangeDescs.length; i++) {
				effectChangeDescs[i].className = 'field_RO';
				effectChangeDescs[i].disabled = true;
				effectChangeDescs[i].checked = false;
			}
		}
	}
}

function chgConSequence4(){
	var conSequence4Str="";	
	for(var i=0;i<form1.conSequence4.length;i++)
	{
		if(form1.conSequence4[i].checked){
			if("1" == form1.conSequence4[i].value){
				if(isObj(form1.effectChangeDesc4)){
					var effectChangeDesc4s = form1.effectChangeDesc4;
					for (var j=0; j<effectChangeDesc4s.length; j++) {
						effectChangeDesc4s[j].className = 'field';
						effectChangeDesc4s[j].disabled = false;
					}
				}
				conSequence4Str += "1";
			}

			if("2" == form1.conSequence4[i].value){
				if(isObj(form1.badReactionLev4)){
					var badReactionLev4s = form1.badReactionLev4;
					for (var j=0; j<badReactionLev4s.length; j++) {
						badReactionLev4s[j].className = 'field';
						badReactionLev4s[j].disabled = false;
					}
				}

				document.all.item('badReactionDesc4').disabled = false;
				document.all.item('badReactionDesc4').className = 'field';
				document.all.item('badReactionDesc4').removeAttribute('readonly',0);
				conSequence4Str += "2";
			}
		}
	}
	
	if("1" == conSequence4Str){
		document.all.item('badReactionDesc4').disabled = true;
		document.all.item('badReactionDesc4').className = 'field_RO';
		document.all.item('badReactionDesc4').value = "";
		if(isObj(form1.badReactionLev4)){
			var badReactionLev4s = form1.badReactionLev4;
			for (var j=0; j<badReactionLev4s.length; j++) {
				badReactionLev4s[j].className = 'field_RO';
				badReactionLev4s[j].disabled = true;
				badReactionLev4s[j].checked = false;
			}
		}
	}else if("2" == conSequence4Str){
		if(isObj(form1.effectChangeDesc4)){
			var effectChangeDesc4s = form1.effectChangeDesc4;
			for (var j=0; j<effectChangeDesc4s.length; j++) {
				effectChangeDesc4s[j].className = 'field_RO';
				effectChangeDesc4s[j].disabled = true;
				effectChangeDesc4s[j].checked = false;
			}
		}
	}else if(null == conSequence4Str || "" == conSequence4Str){
		document.all.item('badReactionDesc4').disabled = true;
		document.all.item('badReactionDesc4').className = 'field_RO';
		document.all.item('badReactionDesc4').value = "";
		if(isObj(form1.badReactionLev4)){
			var badReactionLev4s = form1.badReactionLev4;
			for (var j=0; j<badReactionLev4s.length; j++) {
				badReactionLev4s[j].className = 'field_RO';
				badReactionLev4s[j].disabled = true;
				badReactionLev4s[j].checked = false;
			}
		}

		if(isObj(form1.effectChangeDesc4)){
			var effectChangeDesc4s = form1.effectChangeDesc4;
			for (var j=0; j<effectChangeDesc4s.length; j++) {
				effectChangeDesc4s[j].className = 'field_RO';
				effectChangeDesc4s[j].disabled = true;
				effectChangeDesc4s[j].checked = false;
			}
		}
	}
}
</script>
</head>
<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="toUpdate" value="<%=obj.getToUpdate()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">	
	<input type="hidden" name="userID" value="<%=obj.getUserID()%>">
	<input type="hidden" name="drg04Update">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
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
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	    <td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">療效不等反應</a></td>
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">使用藥品</a></td>	
	    <td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">其他附件</a></td>         
	    <td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">案件初評表</a></td>	    
	</TR>	
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
<div id="formContainer" style="display:none;">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="comple0201_2q.jsp" />
</div>
<div id="formInput" style="height:auto">
<jsp:include page="comple0202f.jsp" />
</div>	
</td></tr>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  setFormItem("btnQryExpense,doUpload","O");
		  break;
	  case "doDoQuit":
		  form1.action = "comple0201q.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	}
}
</script>
</body>
</html>
