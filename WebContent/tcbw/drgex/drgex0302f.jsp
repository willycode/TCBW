<!--
程式目的：藥品療效不等通報登錄作業
程式代號：drgex0302
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGEX0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0302F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
//判別內外部人員
if("in".equals(User.getInORout())){
	obj.setIsInOrOutPerson("I");	
}else{
	obj.setIsInOrOutPerson("O");
}

if("insert".equals(obj.getDoType()) && "queryOne".equals(obj.getState()))
{
	// 由查詢頁面，到本頁時判斷，是否需新增一筆
	try{
		obj.doInsert();
	}catch(Exception e){
		e.printStackTrace();
	}
}
if("pauseSave".equals(obj.getState()) || "stayedUpload".equals(obj.getState()) || "doSend".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
		if("doSend".equals(obj.getState())){
			if("22" == obj.getStatus()){
				obj.setErrorMsg("補件回覆完成");
			}else{
				obj.setErrorMsg("送件完成");
			}
		}else{
			obj.setErrorMsg("修改完成");
		}
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}else if("doCancelQuit".equals(obj.getState())){
	try
	{
		obj.doDelete();
		if (obj.getErrorMsg().equals("")){
			System.out.println("erMsg=="+obj.getErrorMsg());
			obj.setErrorMsg("放棄資料完成");
		}
		obj.setIsNeedBackQuery("Y");
		obj.setId("");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0302F)obj.queryOne();

//取得藥品類別參數
java.util.List<CommonCode> list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRG0307");

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
	var isSubmit = true;
	if(form1.state.value=="stayedUpload" || form1.state.value=="doSend"){
		<%=TCBWCommon.getCheckFiled("DRG02")%>
		for(var i=0;i<form1.conSequence.length;i++)
		{
			if(form1.conSequence[i].checked){
				if("1" == form1.conSequence[i].value){
					alertStr += checkRadioButton(form1.effectChangeDesc,"藥效改變狀況");
				}
				if("2" == form1.conSequence[i].value){
					alertStr += checkRadioButton(form1.badReactionLev,"不良反應等級");
					alertStr += checkEmpty(form1.badReactionDesc,"不良反應等級");
				}
			}
		}
		if("ZZ" == getRadioChecked(form1.dealWith)){
			alertStr += checkEmpty(form1.dealWithOther,"發生事件後之處置其他描述");
		}
		alertStr += validateDrg62Table();		
		alertStr += validateDrg63Table();	
		if(alertStr.length == 0){
			var alertConStr = validateCon01Table();	
			if(alertConStr.length > 0){
				if(!confirm(alertConStr)){
					isSubmit = false;
				}
			}
		}
	}
	if(alertStr.length!=0){ alert(alertStr); return }

	if(isSubmit){
		if(form1.state.value == "pauseSave"){
			form1.updateType.value = "1";
		}else if(form1.state.value == "stayedUpload"){
			form1.updateType.value = "2";
		}else if(form1.state.value=="doSend"){
			form1.updateType.value = "3";
		}
		beforeSubmit();
		form1.submit();
	}
	
}


function init() 
{	
	setDisplayItem('spanInsert,spanUpdate,spanClear,spanQueryAll,spanDelete,spanConfirm,spanListPrint,spanListHidden,spanPauseSave,spanStayedUpload,spanDoSend,spanReUpdate,spanCancelQuit','H');
	if("0309" == form1.formType.value){
		setDisplayItem('spanPauseSave,spanDoUpload,spanStayedUpload,spanDoSend,spanReUpdate,spanCancelQuit,spanDoQuit,report','H');
		<%=TCBWCommon.getRevisionField("DRG02","","DRG6001",Common.get(Common.getInt(obj.getRevision())-1),obj.getApplNo())%>
		<%=TCBWCommon.getRevisionField("DRG02","drg63Row","DRG6003",Common.get(Common.getInt(obj.getRevision())-1),obj.getApplNo())%>
		//進行遮蔽處理
		document.all.item("notifierName").value='';//姓名
		document.all.item("notifierDept").value='';//服務機構
		document.all.item("notifierTelArea").value='';//電話
		document.all.item("notifierTel").value='';//電話
		document.all.item("notifierTelExt").value='';//電話
		document.all.item("notifierEmail").value='';//E-Mail
		document.all.item("notifierAddress").value='';//地址	
	}else{
		setFormItem("pauseSave,stayedUpload,doSend,doUpload","R");
		if("00" == "<%=obj.getStatus()%>"){
			setDisplayItem('spanCancelQuit','S');
		}
		if("00" == "<%=obj.getStatus()%>" || "01" == "<%=obj.getStatus()%>" || "02" == "<%=obj.getStatus()%>"){
			setDisplayItem('spanUpdate,spanClear,spanPauseSave,spanStayedUpload,spanDoSend','S');
		}
		if("21" == "<%=obj.getStatus()%>" ){
			setDisplayItem('spanUpdate,spanClear,spanDoSend,spanCancelQuit','S');
			document.all.item("doSend").value = "確認回覆";
		}else if("22" == "<%=obj.getStatus()%>" ){
			setDisplayItem('spanReUpdate','S');
		}
		
		if("<%=obj.getIsNeedBackQuery()%>" == "N" || "<%=obj.getIsNeedBackQuery()%>" == ""){
			if("<%=obj.getErrorMsg()%>" != ""){
				alert("<%=obj.getErrorMsg()%>");
			}
			if("<%=obj.getDoType()%>" == "insert"){
				openStartAutoSave();
				whatButtonFireEvent("update");
			}
		}else if('<%=obj.getUpdateType()%>'!="1"){		
			form1.action = "drgex0301f.jsp";
			form1.state.value = "queryAll";
			form1.submit();
			window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
		}
	}
	
	if("I" == "<%=obj.getIsInOrOutPerson()%>"){
		setDisplayItem('spanStayedUpload','H');
	}

	chgConSequence();
	
	<%
		if(null == obj.getDrg63JSBuilder() || "".equals(obj.getDrg63JSBuilder())){
			for(CommonCode code : list){
				out.write("addDrg63Row"+code.getCodeId()+"('drg63Table"+code.getCodeId()+"');");
		 	}
		}
	%>

	<%--=TCBWCommon.getIsComplete("DRG02")--%>
	<%--out.write(TCBWCommon.getIsMlmsField("DRG02","drg43Row"));--%>
}

function beforeInit() {
	<%=obj.getDrg62JSBuilder()%>
	<%=obj.getDrg63JSBuilder()%>
	<%=obj.getConJSBuilder()%>
}

function openStartAutoSave()
{
	if(form1.isAlreadyAutoSave.value != "Y")
	{
		form1.isAlreadyAutoSave.value = "Y";
		setTimeout("startSave()", 30000);
	}
}

function startSave()
{
	setTimeout("startSave()", 30000);
	
	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit","R");

	if("<%=obj.getDoType()%>" == "insert")
	{
		setFormItem("doCancelQuit","R");
	}
	
	if("<%=obj.getDoType()%>" == "update")
	{
		setFormItem("doOpenAutoSave","R");
	}
	$("#spanShow").empty().append($("<font color='red'>自動儲存中</font>")).fadeIn("slow");
	form1.action = "drgex0303f.jsp?isSave=Y";
	form1.target = "saveContainerFrame";	
	beforeSubmit();
	form1.submit();
	form1.action = "drgex0302f.jsp";
	form1.target = "_self";
}


function unLockAutoSaveButton()
{
	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit","O");
	if("<%=obj.getDoType()%>" == "insert")
	{
		setFormItem("doCancelQuit","O");
	}
	$("#spanShow").empty().append($("<font color='red'>自動儲存完成</font>")).fadeOut(4000);
	//重新代入檢驗數據資料
	getDrg62Table();
	//重新代入使用藥品資料
	//getDrg63Table();
	//init();	
}

function changeTab(tabId) 
{
	document.all.item("nextPage").value = "下 一 頁";
	form1.tabId.value = tabId;
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";
	document.getElementById("t4").className = "tab_border2";

	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab4").className = "";
	
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';
	document.getElementById("Tab4").style.display = 'none';

	if(tabId == 2){
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";		
	}else if (tabId == 3){
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";	
	}else if (tabId == 4){
		document.getElementById("t4").className = "tab_border1";	
		document.getElementById("Tab4").style.display = '';
		document.getElementById("aTab4").className = "text_w";	
		document.all.item("nextPage").value = "回第一頁";
	}else{
		form1.tabId.value = 1;
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}

function popUserForm() {
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=420,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("../../home/popUser.jsp?","",prop);
}

function chgNotifierType(notifierType){
	if(null != notifierType && "" != notifierType){
		if("01" == notifierType){
			checkedRadio(form1.notifierSource,"01");
		}else if("02" == notifierType){
			checkedRadio(form1.notifierSource,"04");
		}else if("03" == notifierType){
			checkedRadio(form1.notifierSource,"05");
		}else if("04" == notifierType){
			checkedRadio(form1.notifierSource,"06");
		}
	}
}

$(function()
{
	$("#spanShow").hide();
	changeTab(1);
	if("<%=obj.getDoType()%>" == "update")
	{
		$(".field").bind("change", openStartAutoSave);
		$(".field_btnAdd").bind("click", openStartAutoSave);
		$(".field_btnRemove").bind("click", openStartAutoSave);
	}

	
});

function upload()
{
	 var prop='';
	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();
	 startSave();	//開啟附件時，先暫存畫面上資料。
     var url="../../home/popManyUploadSimple.jsp?";
         url+="fileKind=DRG";
         url+="&systemType=DRG020001";
         url+="&uploadId="+form1.id.value;
         url+="&dbName=Drg6001Db";
         
	 returnWindow = window.open(url,'上傳檔案',prop);
}
function doReUpdate()
{	
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1200,height=520,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/drgex/drgex0307f.jsp?applNo="+form1.applNo.value,"",prop);

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
				document.all.item('badReactionDesc').className = 'field';
				document.all.item('badReactionDesc').disabled = false;
				document.all.item('badReactionDesc').removeAttribute('readonly',0);
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

</script>
</head>
<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<iframe name="saveContainerFrame" width="0" height="0" frameborder="0">
</iframe>

<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="drgex0301q.jsp" />
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="isAlreadyAutoSave">
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
	<input type="hidden" name="doType" value="<%=obj.getDoType()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="formType" value="<%=obj.getFormType()%>">
	<input type="hidden" name="tabId" value ="<%=obj.getTabId()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
	<span id="spanPauseSave">
		<input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span> 
	<span id="spanStayedUpload">
		<input class="toolbar_default" type="button" followPK="false" name="stayedUpload" value="待 上 傳" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanDoSend">
		<input class="toolbar_default" type="button" followPK="false" name="doSend" value="送　出" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanReUpdate">
       	<input class="toolbar_default" type="button" followPK="false"  name="btnReUpdate" value="補 件 回 覆" onClick="doReUpdate()">&nbsp;
    </span>
    <span id="spanReport">
	   		<input class="toolbar_default" type="button" followPK="false" name="report" value="案件列印" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
	<span id="spanCancelQuit">
	   <input class="toolbar_default" type="button" followPK="false" name="doCancelQuit" value="放 棄 離 開" onClick="whatButtonFireEvent(this.name)">&nbsp;
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
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	    <td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">療效不等反應</a></td>
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">使用藥品</a></td>	
	    <td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">其他附件</a></td>	
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
	<table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報訊息</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">登錄編號</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="applNo" value="<%=obj.getApplNo()%>">(由通報中心填寫)
			</td>
			<td nowrap class="td_form" width="15%">版次</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="revision" value="<%=obj.getRevision()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發現日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field","occurDate",obj.getOccurDate())%>
			</td>
			<td nowrap class="td_form">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field_RO","notifierRevDate",obj.getNotifierRevDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報者獲知日期</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getPopCalendar("field","notifierRepDate",obj.getNotifierRepDate())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >通報來源</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getRadioBoxOption("field", "notifierSource", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGNFS' order by codeId", obj.getNotifierSource())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報者資訊</td>
		</tr>	
		<tr>
			<td nowrap class="td_form">姓名</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="hidden" name="notifierUserID" maxlength="50" size="15" value="<%=obj.getNotifierUserID()%>">
				<input class="field_RO" type="text" name="notifierName" maxlength="10" size="15" value="<%=obj.getNotifierName()%>" />
				<% if("I".equals(obj.getIsInOrOutPerson())){%>
					<input class="field" type="button" name="btn_User" value="查詢通報者資料" title="通報者資訊輸入輔助視窗" onclick="popUserForm()"/>
					<input class="field" type="button" value="清除通報者" onClick="clearUser();">
				<% } %>
			</td>
			<td nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white">
			    <input class="field" type="hidden" name="notifierDeptID" size="10" maxlength="10" value="<%=obj.getNotifierDeptID()%>" />
	  	        <input class="field_RO" type="text" name="notifierDept" size="30" maxlength="50"  value="<%=obj.getNotifierDept()%>" />
			    <input type="button" name="btnQryDept" onClick="popNotifierDept(notifierType);" value="查詢" width="120px" class="field" >			    
			</td>			
		</tr>		
		<tr>
			<td nowrap class="td_form">電話</td>
			<td nowrap class="td_form_white">
				 ( <input class="field_RO" type="text" name="notifierTelArea" size="2" maxlength="2" value="<%=obj.getNotifierTelArea()%>"> )
			    - <input class="field_RO" type="text" name="notifierTel" size="10" maxlength="10" value="<%=obj.getNotifierTel()%>">
			    # <input class="field_RO" type="text" name="notifierTelExt" size="3" maxlength="3" value="<%=obj.getNotifierTelExt()%>">
			</td>
			<td nowrap class="td_form">E-Mail</td>
			<td nowrap class="td_form_white" >
				<input class="field_RO" type="text" name="notifierEmail" maxlength="30" size="20" value="<%=obj.getNotifierEmail()%>"/>
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">手機</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="notifierPhone" maxlength="10" size="10" value="<%=obj.getNotifierPhone()%>"/>
			</td>
			<td nowrap class="td_form">傳真</td>
			<td nowrap class="td_form_white" >
				( <input class="field_RO" type="text" name="notifierFaxArea" size="2" maxlength="2" value="<%=obj.getNotifierFaxArea()%>"> )
			    - <input class="field_RO" type="text" name="notifierFax" size="10" maxlength="10" value="<%=obj.getNotifierFax()%>">
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form">地址</td>
			<td nowrap class="td_form_white" colspan="3" >
				<select class="field_RO" name="notifierCounty" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", obj.getNotifierCounty())%>
			    </select>
			    <select class="field_RO" name="notifierZipCode">
				   <%=View.getOptionCon1002(obj.getNotifierZipCode())%>
			    </select>
				<input class="field_RO" type="text" name="notifierAddress" maxlength="100" size="100" value="<%=obj.getNotifierAddress()%>"/>
			</td>
		</tr>
        <tr>
			<td nowrap class="td_form">職稱</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field_RO", "notifierTitle", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='TITLE' order by codeId", obj.getNotifierTitle())%>			
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">屬性</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field_RO", "notifierType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", obj.getNotifierType(),"chgNotifierType(this.value);")%>			
			</td>
		</tr>	
	    <tr>
			<td nowrap class="td_form_left" colspan="4">病人相關資料</td>
		</tr>
		<tr>
			<td nowrap class="td_form">識別代碼</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientId"  size="10" maxlength="10" value="<%=obj.getPatientId()%>"/>
			</td>
			<td nowrap class="td_form">性別</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field","patientSex","M;男;F;女",obj.getPatientSex())%>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">出生日期</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","patientBirth",obj.getPatientBirth())%>
				年齡：<input class="field" type="text" name="patientAge"  size="5" maxlength="3" value="<%=obj.getPatientAge()%>"/>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">身高</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientHeight" size="5" maxlength="3" value="<%=obj.getPatientHeight()%>"/>公分
			</td>
			<td nowrap class="td_form">體重</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientWeight" size="5" maxlength="3" value="<%=obj.getPatientWeight()%>"/>公斤
			</td>				
		</tr>
	</table>	
	<table id="Tab2" width="100%" align="center" class="table_form">
		<tr>		
			<td nowrap class="td_form" >通報事件之後果</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="checkbox" name="conSequence" value="1" <%if(!"".equals(Common.get(obj.getConSequence()))){for(String con:obj.getConSequence())if("1".equals(con))out.print("checked");}else{out.print("");}%> onclick="chgConSequence();">藥效改變 &nbsp;&nbsp;--
					<%=View.getRadioBoxTextOption("field","effectChangeDesc","1;增強;2;減弱",obj.getEffectChangeDesc())%><br>
				<input class="field" type="checkbox" name="conSequence" value="2" <%if(!"".equals(Common.get(obj.getConSequence()))){for(String con:obj.getConSequence())if("2".equals(con))out.print("checked");}else{out.print("");}%> onclick="chgConSequence();">不良反應發生、強度增強或頻率增加<br>
				    &nbsp;&nbsp;&nbsp;&nbsp;不良反應等級：<%=View.getRadioBoxOption("field", "badReactionLev", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0308' order by codeId", obj.getBadReactionLev())%><br>
				    &nbsp;&nbsp;&nbsp;&nbsp;不良反應狀況：<input class="field" type="text" name="badReactionDesc" size="50" maxlength="50" value="<%=obj.getBadReactionDesc()%>"/>	
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報事件之描述</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >事件前</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="beforeDesc" cols="95" rows="3" class="field"><%=obj.getBeforeDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >藥品轉換</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="changeDesc" cols="95" rows="3" class="field"><%=obj.getChangeDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >發生事件</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="occurDesc" cols="95" rows="3" class="field"><%=obj.getOccurDesc()%></textarea>
			</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >事件後</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="afterDesc" cols="95" rows="3" class="field"><%=obj.getAfterDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他相關檢查及檢驗數據資訊(請附日期)</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關檢查,檢驗數據<br>及其他資料</td>
			<td nowrap class="td_form_white" colspan="3">
               <jsp:include page="drgex0362Layout.jsp" />
		    </td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他相關資料(例如：診斷、過敏、懷孕、吸菸、喝酒、習慣、其他疾病、肝/腎功能不全...等)</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >請描述</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="otherDesc" cols="95" rows="3" class="field"><%=obj.getOtherDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發生事件後之處置</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field", "dealWith", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0301' order by codeId",obj.getDealWith())%>
				(請描述)：<input class="field" type="text" name="dealWithOther" size="30" maxlength="50" value="<%=obj.getDealWithOther()%>"/>			
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">病人恢復原藥或轉換同成分藥品<br>其症狀是否改善</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxTextOption("field","isImproveYn","Y;是;N;否;0;未知",obj.getIsImproveYn())%>		
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">提供聯絡資訊供廠商後續調查評估</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxTextOption("field","isContactYn","Y;是;N;否;",obj.getIsContactYn())%>		
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">醫師對換藥的態度</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "dressingAttitude", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0302' order by codeId",obj.getDressingAttitude())%>		
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">病人服藥順從性</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "obedienceLev", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0303' order by codeId",obj.getObedienceLev())%>		
			</td>				
		</tr>
	</table>
	<table id="Tab3" class="table_form" width="100%" border="0" cellpadding="0" cellspacing="0">
		<%
			for(CommonCode code : list){
		%>
			<tr><td class="bg">
				<jsp:include page="drgex0363Layout.jsp">
					 <jsp:param name="type" value="<%=code.getCodeId()%>" />
				</jsp:include>
			</td></tr>
		<% }%>
	</table>
	<table id="Tab4" width="100%" align="center" class="table_form">
		<tr><td class="bgToolbar">
			<span id="spanDoUpload">
				<input class="toolbar_default" type="button" followPK="false" name="doUpload" value="附件上傳" onClick="upload()">&nbsp;
			</span>
		</td>
		<tr><td class="bg">
			<jsp:include page="conex01Layout.jsp"/>
		</td></tr>
	</table>
	<table id="toolbarTable" width="100%" align="center" class="table_form">
		<tr><td align="center">
			<input class="toolbar_default" type="button" followPK="false" name="nextPage" value="下 一 頁" onClick="changeTab(parseInt(form1.tabId.value)+1);">
		</td></tr>
	</table>
</div>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  if("21" == "<%=obj.getStatus()%>" || "22" == "<%=obj.getStatus()%>"){
			  setFormItem("doSend,doUpload","O");
		  }else{
			  setFormItem("pauseSave,stayedUpload,doSend,doUpload,doCancelQuit","O");
		  }
		  
		  break;
	  case "pauseSave":
	  case "stayedUpload":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doSend":
		  checkField();
		  break;
	  case "doCancelQuit":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doDoQuit":
		  form1.action = "drgex0301f.jsp?caseType="+form1.caseType.value;
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	  case "report":
		  form1.action = "drgex0302p.jsp" ;
		  form1.target = "_blank";
		  beforeSubmit();
		  form1.submit();
		  form1.action = "drgex0302f.jsp" ;
		  form1.target = "_self";
		  break;	  
	}
}
</script>
</body>
</html>
