<!--
程式目的：藥品不良品通報資料補登作業
程式代號：comple0101f
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COMPLE0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.comple.COMPLE0101F">
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

obj = (com.kangdainfo.tcbw.view.comple.COMPLE0101F) obj.doQueryOne();
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
<script type="text/javascript" src="../drgin/drgin0102.js"></script>
<script type="text/javascript">
var popWinName;
$(function()
{	
	$('#Tab3 :input').bind('click change',function(){
		if(form1.state.value=="update"){
			form1.drg03Update.value="Y";
		}
	});
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
	detailSave();
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
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="toUpdate" value="<%=obj.getToUpdate()%>">
	<input type="hidden" name="drg03Update">
	<input type="hidden" name="drg04Update">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">	
	<input type="hidden" name="userID" value="<%=obj.getUserID()%>">
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
	    <td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良事件</a></td>
	    <td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">其他附件</a></td>	     
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">案件分級</a></td>	     
	    <td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">分級確認</a></td>	    
	</TR>	
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
<div id="formContainer" style="display:none;">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="comple0101_2q.jsp" />
</div>
<div id="formInput" style="height:auto">
<jsp:include page="comple0102f.jsp" />
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
		  form1.action = "comple0101q.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	}
}
</script>
</body>
</html>
