<!--
程式名稱：重大品質事件廠商主動通報 - 回收確認作業
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SDRG0401" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0401F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%

if("pauseSave".equals(obj.getState()) || "doConfirm".equals(obj.getState()) 
		|| "doAccepted".equals(obj.getState()) ||  "backPieces".equals(obj.getState())){
	try	{
		obj.setHttpRequest(pageContext.getRequest());		
		obj.doUpdate0401();
		if("0".equals(obj.getUpdateType())){
			obj.setErrorMsg("修改完成");
		}else if ("9".equals(obj.getUpdateType()) || "A".equals(obj.getUpdateType())){
			obj.setErrorMsg("確認送出完成");
		}else if ("10".equals(obj.getUpdateType())){
			obj.setErrorMsg("審核完成");
		}else if ("11".equals(obj.getUpdateType())){
			obj.setErrorMsg("退件完成");
		}		
	}catch(Exception e){
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("upload".equals(obj.getState())){
	try	{		
		obj.doUpdate0401();
	}catch(Exception e)	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.sdrg.SDRG0401F) obj.doQueryOne0401();

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
	}
	else if(form1.state.value=="doConfirm"){
		if('<%=obj.getCaseType()%>'=='1'){
			form1.updateType.value = "A"; //回收確認-確認完成(廠商)
			<%=TCBWCommon.getCheckFiled("DRG04","SDRG0402_1")%>;
			alertStr += checkRadioOtherEmpty(form1.checkcyclestorage,'90',form1.checkcyclestorageOther,'回收品及庫存品處置方式其他說明');
		}else if ('<%=obj.getCaseType()%>'=='2'){
			form1.updateType.value = "9"; //回收確認-確認完成(衛生局)
			<%=TCBWCommon.getCheckFiled("DRG04","SDRG0402_1")%>;
			alertStr += checkRadioOtherEmpty(form1.checkcyclestorage,'90',form1.checkcyclestorageOther,'回收品及庫存品處置方式其他說明');
			<%=TCBWCommon.getCheckFiled("DRG04","SDRG0402_2")%>;
			alertStr += checkRadioOtherEmpty(form1.ischeckmatchnum,'N',form1.checknonmatchreason,'數量不符之理由');
		}else{
			alertStr += "無法判斷送出類別!!";
		}
	}
	else if(form1.state.value=="doAccepted"){
		<%=TCBWCommon.getCheckFiled("DRG04","SDRG0402_1")%>;
		alertStr += checkRadioOtherEmpty(form1.checkcyclestorage,'90',form1.checkcyclestorageOther,'回收品及庫存品處置方式其他說明');
		<%=TCBWCommon.getCheckFiled("DRG04","SDRG0402_2")%>;
		alertStr += checkRadioOtherEmpty(form1.ischeckmatchnum,'N',form1.checknonmatchreason,'數量不符之理由');
		form1.updateType.value = "10"; //回收確認-審核完成
	}
	else if(form1.state.value=="backPieces"){
		form1.updateType.value = "11"; //回收確認-審核退件
	}
	else if(form1.state.value=="upload"){
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
		changeTab(4);
	}
	//許可證字號動態調整(tab1)
	permitData1("init");
	//訊息來源控制(tab2)
	checkPlnoInput();
	//檔案上傳維持在修改狀態
	if(form1.isUpload.value=="Y"){
		whatButtonFireEvent("update");
	}else{	
		setFormItem("pauseSave,doConfirm,doAccepted,backPieces,doUpload1,doUpload2,doUpload3,doUpload4,btnAbroadQuery,btnAbroadClear,doUpload5,doUpload6,doUpload7,doUpload8,doUpload9,doUpload10","R");
	}
	form1.isUpload.value="";
	
	
	//修改資料完畢，導入查詢頁面
	if('<%=obj.getErrorMsg()%>'=='確認送出完成' || '<%=obj.getErrorMsg()%>'=='審核完成' || '<%=obj.getErrorMsg()%>'=='退件完成')
	{			
		form1.action = "sdrg0401q.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
	
	//隱藏不需要的預設按鈕
	if(form1.checkUpdateYn.value=="Y" && '1'=='<%=obj.getCaseType()%>'){
		setDisplayItem("spanUpdate,spanClear,pauseSave,doConfirm,spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	}else{
		setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");	
	}
}
function beforeInit()
{
	<%=obj.getDrg8002DbItemSet()%>
	<%=obj.getDrg8005DbItemSet()%>
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

function getCheckHealthbureauUpdate()
{
    var alertStr="";
	if('<%=obj.checkHealthbureauUpdate()%>'!='Y')
    {
		alertStr+="此案件非貴單位負責案件!";
    }
	return alertStr;
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

</script>
</head>
<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bgToolbar"> 
    <jsp:include page="../../home/toolbar.jsp" />  
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
	<input type="hidden" name="isUpload" value="<%=obj.getIsUpload()%>">
	<input type="hidden" name="updateType">
	
    <span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%if(!"31".equals(Common.get(obj.getStatus()))){%>
    <span id="spanDoConfirm">
	   <input class="toolbar_default" type="button" followPK="false" name="doConfirm" value="確認完成" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>    
    <%if("31".equals(Common.get(obj.getStatus()))){%>     
    <span id="spanDoAccepted">
	   <input class="toolbar_default" type="button" followPK="false" name="doAccepted" value="審核通過" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanBackPieces">
	   <input class="toolbar_default" type="button" followPK="false" name="backPieces" value="審核退件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
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
	    <td nowrap ID="t3" CLASS="tab_border1"><a id="aTab3" href="#" onClick="changeTab(3);">廠商回收</a></td>
	    <td nowrap ID="t4" CLASS="tab_border1"><a id="aTab4" href="#" onClick="changeTab(4);">回收確認</a></td>
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
        <jsp:include page="sdrg0102f.jsp" />
        <jsp:include page="sdrg0202f.jsp" />
        <jsp:include page="sdrg0302f.jsp" />
        <jsp:include page="sdrg0402f.jsp" />
    </div>
    </td>
</tr>

</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  var alertStr="";
		  alertStr += getCheckHealthbureauUpdate();
		  if(alertStr.length!=0){
			  alert(alertStr); return 
		  }
		  //不開放登錄及審核頁籤(全部先鎖定)
		  setAllReadonly();		  
		  //回收確認
		  if(form1.status.value =='30' || form1.status.value =='31'){
			  $("#Tab4_1").find('.field').attr('disabled',false);
			  $("#Tab4_1").find('.field').attr('readOnly',false);
			  $("#Tab4_2").find('.field').attr('disabled',false);
			  $("#Tab4_2").find('.field').attr('readOnly',false);
			  setFormItem("pauseSave,doConfirm,doAccepted,backPieces,doUpload9,doUpload10","O");
		  }		  
	      break;
	  case "pauseSave":
	  case "doConfirm":		  
	  case "doAccepted":
	  case "backPieces":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "clear":			
		  form1.state.value = "queryOne";			
		  beforeSubmit();
		  form1.submit();	
		  break;
	  case "doDoQuit":
		  form1.action = "sdrg0401q.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	}
}
</script>
</body>
</html>