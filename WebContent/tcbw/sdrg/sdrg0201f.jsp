<!--
程式名稱：重大品質事件廠商主動通報 - 審核作業
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SDRG0201" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0201F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%

if("pauseSave".equals(obj.getState()) || "doAccepted".equals(obj.getState()) || "backPieces".equals(obj.getState())){
	try	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
		obj.doUpdate0201();
		if("0".equals(obj.getUpdateType())){
			obj.setErrorMsg("修改完成");
		}else if ("2".equals(obj.getUpdateType())){
			obj.setErrorMsg("審核完成");
		}else if ("3".equals(obj.getUpdateType())){
			obj.setErrorMsg("退件完成");
		}		
	}catch(Exception e){
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("upload".equals(obj.getState())){
	try	{		
		obj.doUpdateType();
		obj.doUpdate0201();		
	}catch(Exception e)	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.sdrg.SDRG0201F) obj.doQueryOne0201();

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
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
var popWinName;

function checkField()
{
	var id = form1.id.value;
	var alertStr = "";	
	
	if(form1.state.value == "pauseSave"){
		form1.updateType.value = "0"; //修改
	}else if(form1.state.value=="doAccepted"){
		alertStr += validateDrg8002Table();			
		alertStr += checkRadioOtherEmpty(form1.isabroad,'Y',form1.abroadCountry,'輸出國別');
		<%=TCBWCommon.getCheckFiled("DRG04","SDRG0102")%>;
		<%=TCBWCommon.getCheckFiled("DRG04","SDRG0202")%>;		
		alertStr += checkRadioOtherEmpty(form1.msgsource,'90',form1.msgsourcedesc,'訊息來源其他說明');
		form1.updateType.value = "2"; //審核完成
	}else if(form1.state.value=="backPieces"){		
		form1.updateType.value = "3"; //審核退件
	}else if(form1.state.value=="upload"){
		form1.updateType.value = "0";
		form1.isUpload.value="Y";
	}
	if(alertStr.length!=0){ alert(alertStr); return }
	beforeSubmit();
	form1.submit();	
}

function init() 
{	
	//頁籤切換
	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);		
	}else{
		changeTab(2);
	}
	//許可證字號動態調整(tab1)
	permitData1("init");
	//訊息來源控制(tab2)
	checkPlnoInput();
	//檔案上傳維持在修改狀態
	if(form1.isUpload.value=="Y"){
		whatButtonFireEvent("update");
	}else{
		setFormItem("pauseSave,doAccepted,backPieces,doUpload1,doUpload2,doUpload3,doUpload4,btnAbroadQuery,btnAbroadClear","R");
	}	
	form1.isUpload.value="";	
	//隱藏不需要的預設按鈕
	setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	//修改資料完畢，導入查詢頁面
	if('<%=obj.getErrorMsg()%>'=='審核完成' || '<%=obj.getErrorMsg()%>'=='退件完成')
	{			
		form1.action = "sdrg0201q.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
}
function beforeInit()
{
	<%=obj.getDrg8002DbItemSet()%>
}

//檔案上傳
function upload(type)
{
	 var prop='';
	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();

   var url="../../home/popManyUploadSimple.jsp?";
       url+="fileKind=DRG";
       url+="&systemType="+type;
       url+="&uploadId="+form1.id.value;
       url+="&dbName=Drg8001Db";
       
	 returnWindow = window.open(url,'上傳檔案',prop);
}

//頁籤更換
function changeTab(tabId) 
{
	if (isObj(document.all.item("t1")))
	{	
	  document.getElementById("t1").className = "tab_border2";
	  document.getElementById("aTab1").className = "";
	  document.getElementById("Tab1").style.display = 'none';
	}
	if (isObj(document.all.item("t2")))
	{	
	  document.getElementById("t2").className = "tab_border2";
	  document.getElementById("aTab2").className = "";
	  document.getElementById("Tab2").style.display = 'none';
	}
	if (isObj(document.all.item("t3")))
	{	
	  document.getElementById("t3").className = "tab_border2";
	  document.getElementById("aTab3").className = "";
	  document.getElementById("Tab3_1").style.display = 'none';
	  document.getElementById("Tab3_2").style.display = 'none';
	}
	if (isObj(document.all.item("t4")))
	{  
	  document.getElementById("t4").className = "tab_border2";
	  document.getElementById("aTab4").className = "";	  
	  document.getElementById("Tab4_1").style.display = 'none';
	  document.getElementById("Tab4_2").style.display = 'none';	    
	}
	if (isObj(document.all.item("t5")))
	{	
	  document.getElementById("t5").className = "tab_border2";
	  document.getElementById("aTab5").className = "";
	  document.getElementById("Tab5").style.display = 'none';
	}
	if (isObj(document.all.item("t6")))
	{	
	  document.getElementById("t6").className = "tab_border2";
	  document.getElementById("aTab6").className = "";
	  document.getElementById("Tab6").style.display = 'none';
	}
	

	if (isObj(document.all.item("changeTabValue")))
	{
		document.all.item("changeTabValue").value=tabId;
	}

	if(tabId == "2")
	{
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";
		form1.tabId.value='2';
	}
	else if(tabId == "3"){
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3_1").style.display = '';
		document.getElementById("Tab3_2").style.display = '';
		document.getElementById("aTab3").className = "text_w";
		form1.tabId.value='3';
	}
	else if(tabId == "4"){
		document.getElementById("t4").className = "tab_border1";
		if('<%=obj.getCaseType()%>'=='1'){
			document.getElementById("Tab4_1").style.display = '';
			document.getElementById("Tab4_2").style.display = 'none';
		}else{
		    document.getElementById("Tab4_1").style.display = '';
			document.getElementById("Tab4_2").style.display = '';
		}
		document.getElementById("aTab4").className = "text_w";
		form1.tabId.value='4';
	}
	else if(tabId == "5"){
		document.getElementById("t5").className = "tab_border1";	
		document.getElementById("Tab5").style.display = '';
		document.getElementById("aTab5").className = "text_w";
		form1.tabId.value='5';
	}
	else if(tabId == "6"){
		document.getElementById("t6").className = "tab_border1";	
		document.getElementById("Tab6").style.display = '';
		document.getElementById("aTab6").className = "text_w";
		form1.tabId.value='6';
	}
	else
	{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";
		form1.tabId.value='1';
	}
}

function printWordReport(type) {	
	if(type != null && type != ''){
		form1.reportType.value=type;
		form1.action="sdrg0101p.jsp";
		form1.target="_blank";
		form1.submit();
		form1.target="_self";
		form1.action="sdrg0201f.jsp";
	}else{
		alert("無法確認報表種類，請查明!!");
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
    <jsp:include page="../../home/toolbar.jsp" />  
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
	<input type="hidden" name="isUpload" value="<%=obj.getIsUpload()%>">
	<input type="hidden" name="reportType" value="<%=obj.getReportType()%>">
	<input type="hidden" name="updateType">	
	
    <span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>  
    <span id="spanDoAccepted">
	   <input class="toolbar_default" type="button" followPK="false" name="doAccepted" value="審核通過" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanBackPieces">
	   <input class="toolbar_default" type="button" followPK="false" name="backPieces" value="審核退件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanPrintWord">
	   <input class="toolbar_default" type="button" followPK="false" name="printWord1" value="產生回收通知函" onClick="printWordReport('01');">&nbsp;
	   <input class="toolbar_default" type="button" followPK="false" name="printWord2" value="產生回收計畫書" onClick="printWordReport('02');">&nbsp;
    </span>
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit"  value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
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

<!--  tab  -->
<TABLE>
	<TR>
		<td nowrap ID="t1" CLASS="tab_border2"><a id="aTab1" href="#" onClick="changeTab(1);">案件登錄</a></td>
	    <td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">案件審核</a></td>
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
        <jsp:include page="sdrg0102f.jsp" />
        <jsp:include page="sdrg0202f.jsp" />
    </div>
    </td>
</tr>

</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  setFormItem("pauseSave,doAccepted,backPieces,doUpload1,doUpload2,doUpload3,doUpload4,btnAbroadQuery,btnAbroadClear","O");
	      break;	  
	  case "pauseSave":
	  case "doAccepted":
	  case "backPieces":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doDoQuit":
		  form1.action = "sdrg0201q.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	}
}
</script>
</body>
</html>