<!--
程式名稱：重大品質事件廠商主動通報 - 廠商回收作業
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SDRG0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0301F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%

if("pauseSave".equals(obj.getState()) || "doDelivery".equals(obj.getState()) 
		|| "doAccepted".equals(obj.getState()) ||  "backPieces".equals(obj.getState())){
	try	{
		obj.setHttpRequest(pageContext.getRequest());		
		obj.doUpdate0301();
		if("0".equals(obj.getUpdateType())){
			obj.setErrorMsg("修改完成");
		}else if ("4".equals(obj.getUpdateType()) || "5".equals(obj.getUpdateType()) || "6".equals(obj.getUpdateType())){
			obj.setErrorMsg("完成送出");
		}else if ("7".equals(obj.getUpdateType())){
			obj.setErrorMsg("審核完成");
		}else if ("8".equals(obj.getUpdateType())){
			obj.setErrorMsg("退件完成");
		}		
	}catch(Exception e){
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("doExtension".equals(obj.getState())){
	try	{		
		obj.doExtension0301();
		obj.setErrorMsg("展延完成");
	}catch(Exception e)	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("upload".equals(obj.getState())){
	try	{		
		obj.doUpdate0301();
	}catch(Exception e)	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.sdrg.SDRG0301F) obj.doQueryOne0301();

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
	else if(form1.state.value=="doDelivery"){
		if(form1.updateType.value=="4"){//回收完成
			alertStr += validateDrg8005Table();
			<%=TCBWCommon.getCheckFiled("DRG04","SDRG0302_1")%>;
			alertStr += checkRadioOtherEmpty(form1.apprecyclestorage,'90',form1.apprecyclestoragedesc,'回收品及庫存品處置方式其他說明');			
		}	
		else if(form1.updateType.value=="5"){//回覆完成
			<%=TCBWCommon.getCheckFiled("DRG04","SDRG0302_2")%>;
			alertStr += checkRadioOtherEmpty(form1.apprecyclereason,'90',form1.apprecyclersdesc,'回收原因其他說明');
			alertStr += checkRadioOtherEmpty(form1.appsurvey,'90',form1.appsurveyOther,'調查結果其他說明');
			alertStr += checkRadioOtherEmpty(form1.appprecaution,'90',form1.appprecautionOther,'預防措施其他說明');
		}
		else if(form1.updateType.value=="6"){//回收完成+回覆完成	
			alertStr += validateDrg8005Table();
			<%=TCBWCommon.getCheckFiled("DRG04","SDRG0302_1")%>;
			alertStr += checkRadioOtherEmpty(form1.apprecyclestorage,'90',form1.apprecyclestoragedesc,'回收品及庫存品處置方式其他說明');
			<%=TCBWCommon.getCheckFiled("DRG04","SDRG0302_2")%>;
			alertStr += checkRadioOtherEmpty(form1.apprecyclereason,'90',form1.apprecyclersdesc,'回收原因其他說明');
			alertStr += checkRadioOtherEmpty(form1.appsurvey,'90',form1.appsurveyOther,'調查結果其他說明');
			alertStr += checkRadioOtherEmpty(form1.appprecaution,'90',form1.appprecautionOther,'預防措施其他說明');
		}
		else{
			alertStr += "無法判斷送出類別!!";
		}
	}
	else if(form1.state.value=="doExtension"){
		alertStr += checkEmpty(form1.extendate,"申請展延日期");
		alertStr += checkEmpty(form1.extenreason,"展延理由");
		alertStr += checkEmpty(form1.extendeadline,"展延後回收期限");
	}
	else if(form1.state.value=="doAccepted"){
		alertStr += validateDrg8005Table();
		<%=TCBWCommon.getCheckFiled("DRG04","SDRG0302_1")%>;
		alertStr += checkRadioOtherEmpty(form1.apprecyclestorage,'90',form1.apprecyclestoragedesc,'回收品及庫存品處置方式其他說明');
		form1.updateType.value = "7"; //廠商回收-審核完成
	}
	else if(form1.state.value=="backPieces"){
		form1.updateType.value = "8"; //廠商回收-審核退件
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
		changeTab(3);
	}
	//許可證字號動態調整(tab1)
	permitData1("init");
	//訊息來源控制(tab2)
	checkPlnoInput();
	//檔案上傳維持在修改狀態
	if(form1.isUpload.value=="Y"){
		whatButtonFireEvent("update");
	}else{
		setFormItem("pauseSave,doDelivery,doAccepted,backPieces,doUpload1,doUpload2,doUpload3,doUpload4,btnAbroadQuery,btnAbroadClear,doUpload5,doUpload6,doUpload7,doUpload8","R");
	}
	form1.isUpload.value="";
	//隱藏不需要的預設按鈕
	setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	//修改資料完畢，導入查詢頁面
	if('<%=obj.getErrorMsg()%>'=='完成送出' || '<%=obj.getErrorMsg()%>'=='展延完成'
		|| '<%=obj.getErrorMsg()%>'=='審核完成' || '<%=obj.getErrorMsg()%>'=='退件完成')
	{			
		form1.action = "sdrg0301q.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
}
function beforeInit()
{
	<%=obj.getDrg8002DbItemSet()%>
	<%=obj.getDrg8005DbItemSet()%>
}

function doDeliverySubmit(){
	form1.state.value="doDelivery";
	//僅剩回收未送出
	if(form1.status.value =='20' && form1.status2.value!='40'){
		form1.updateType.value = "4";
		checkField();
	}
	//僅剩回覆未送出
	else if(form1.status.value !='20' && form1.status2.value =='40'){
		form1.updateType.value = "5";
		checkField();
	}
	else{
		var prop = '';
		var w = (screen.width-400)/2;
		var h = (screen.height-100)/2;
	    prop = prop + 'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes';
	    prop = prop + ',width=400';
	    prop = prop + ',height=100';
	    prop = prop + ',left=' + w;
	    prop = prop + ',top=' + h;

	    if(popWinName != null) popWinName.close();
	    popWinName = window.open('sdrg0303f.jsp','',prop);
	}
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

function printWordReport() {
	form1.reportType.value='03';
	form1.action="sdrg0101p.jsp";
	form1.target="_blank";
	form1.submit();
	form1.target="_self";
	form1.action="sdrg0301f.jsp";	
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

<!--回收期限展延區============================================================-->

<div id="queryContainer" style="width:400px;height:150px;display:none">
    <iframe id="queryContainerFrame"></iframe>
    <table class="queryTable"  border="1">
        <tr>
			<td nowrap class="td_form_left" colspan="4">展延資訊單</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >申請展延日期</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getPopCalendar("field_Q","extendate",obj.getExtendate())%>                
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >展延理由</td>
			<td nowrap class="td_form_white" colspan="3">
			   <input class="field_Q" type="text" name="extenreason" size="30" maxlength="50"  value="<%=obj.getExtenreason()%>" />               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >展延後回收期限</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getPopCalendar("field_Q","extendeadline",obj.getExtendeadline())%>                 
			</td>
		</tr>
		<tr>
			<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
				<input class="toolbar_default" followPK="false" type="button" name="doExtension" value="確　　定" onClick="whatButtonFireEvent(this.name)">
				<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
			</td>			
		</tr>
     </table>
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
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
    <%if("20".equals(Common.get(obj.getStatus())) || "40".equals(Common.get(obj.getStatus2()))){%>
    <span id="spanDoDelivery">
	   <input class="toolbar_default" type="button" followPK="false" name="doDelivery" value="回收/回覆完成" onClick="doDeliverySubmit();">&nbsp;
    </span>
    <%}%>
    <%if("20".equals(Common.get(obj.getStatus())) && "Y".equals(Common.get(obj.getShowExtension()))){%>     
    <span id="spanShowExtension">
	   <input class="toolbar_default" type="button" followPK="false" name="showExtension" value="展　延" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if("21".equals(Common.get(obj.getStatus())) && "2".equals(Common.get(obj.getCaseType()))){%>     
    <span id="spanDoAccepted">
	   <input class="toolbar_default" type="button" followPK="false" name="doAccepted" value="審核通過" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanBackPieces">
	   <input class="toolbar_default" type="button" followPK="false" name="backPieces" value="審核退件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <span id="spanPrintWord">
	   <input class="toolbar_default" type="button" followPK="false" name="printWord" value="產生回收報告書" onClick="printWordReport();">&nbsp;
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
	    <td nowrap ID="t3" CLASS="tab_border1"><a id="aTab3" href="#" onClick="changeTab(3);">廠商回收</a></td>
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
		  //廠商回收
		  if(form1.status.value =='20'){
			  $("#Tab3_1").find('.field').attr('disabled',false);
			  $("#Tab3_1").find('.field_btnAdd').attr('disabled',false);
			  $("#Tab3_1").find('.field_btnRemove').attr('disabled',false);
			  $("#Tab3_1").find('.field').attr('readOnly',false);			  
			  setFormItem("pauseSave,doDelivery,doAccepted,backPieces,doUpload5,doUpload6","O");
		  }
		  //廠商回覆
		  if(form1.status2.value =='40'){
			  $("#Tab3_2").find('.field').attr('disabled',false);
			  $("#Tab3_2").find('.field').attr('readOnly',false);
			  setFormItem("pauseSave,doDelivery,doAccepted,backPieces,doUpload7,doUpload8","O"); 
		  }
		  //廠商回收-審核
		  if(form1.status.value =='21' && '2'=='<%=obj.getCaseType()%>'){
			  $("#Tab3_1").find('.field').attr('disabled',false);
			  $("#Tab3_1").find('.field_btnAdd').attr('disabled',false);
			  $("#Tab3_1").find('.field_btnRemove').attr('disabled',false);
			  $("#Tab3_1").find('.field').attr('readOnly',false);
			  $("#Tab3_2").find('.field').attr('disabled',false);
			  $("#Tab3_2").find('.field').attr('readOnly',false);
			  setFormItem("pauseSave,doDelivery,doAccepted,backPieces,doUpload5,doUpload6,doUpload7,doUpload8","O");
		  }
		  setFormItem("showExtension","R");
	      break;
	  case "pauseSave":
	  case "doExtension":
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
	  case "showExtension":
		  queryShow('queryContainer');
		  break;
	  case "extensionCannel":
		  document.getElementById("extenreasonForm").style.display = 'none';
		  break;
	  case "doDoQuit":
		  form1.action = "sdrg0301q.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	}
}
</script>
</body>
</html>