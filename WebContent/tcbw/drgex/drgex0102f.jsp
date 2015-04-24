<!--
程式目的：藥品不良品通報登錄作業
程式代號：drgex0102
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
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0102F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
String q_id = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("v")));

if(!"".equals(Common.get(q_id))){
	obj.setId(q_id);
	obj.getOldRevisionData();
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
if("pauseSave".equals(obj.getState()) || "stayedUpload".equals(obj.getState()) 
		|| "doSend".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
		obj.setErrorMsg("修改完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}else if("doCancelQuit".equals(obj.getState()))
{
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
else if("autoUpdateSend".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.autoUpdateSend();;
	    obj.setErrorMsg("主動補件完成");		
		obj.setIsNeedBackQuery("Y");
		obj.setId("");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}else if("upload".equals(obj.getState()))
{
	try
	{		
		obj.doUpdateType();
		obj.queryOne();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0102F)obj.queryOne();
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
	
	if(form1.state.value=="stayedUpload" || form1.state.value=="doSend" || form1.state.value=="autoUpdateSend"){
		alertStr += checkDate(form1.occurDate, "發生未預期反應日期");	
		alertStr += checkEmpty(form1.permitKey,"許可證字");
		alertStr += checkEmpty(form1.permitNo,"許可證號");
		alertStr += checkButton(form1.subCode, "不良品缺陷");
		<%=TCBWCommon.getCheckFiled("DRG01")%>		
		if(checkCon0001Count(id,'Drg5001Db')){
			var contactLen =form1.isContactYn.length;
			for (var i=0; i< contactLen ; i++)
			{			   
				//提供聯絡資訊供廠商後續調查評估為N，需檢核是否需要上傳附件
				if (form1.isContactYn[i].checked && form1.isContactYn[i].value=="N")			   
				{
					if(checkSubCodeFileYn()){
						alertStr += "選取之不良品缺陷描述，需要提供附件或可提供聯絡資訊供廠商後續調查評估，兩者擇一";
					}
				}
			}			
			if (confirm("請確認是否有相關附件需要提供！\n若需上傳附件，請點擊取消，並補上傳附件！\n若不需上傳附件，請點擊確定鍵繼續！")) {
				   
			} else {
				form1.state.value="queryOneSuccess";
				return false;
			}
		}
	}	
	
	
	if(alertStr.length!=0){ alert(alertStr); return }
	
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

//子代碼: 變更主代碼  & 清除其他
function updateMainCode(subCode){
	var v = subCode.value.substr(0,2);
	if(subCode.checked==true){ //將mainCode打勾
		var len = document.getElementsByName("mainCode").length;
		for(var i=0 ; i<len ; i++ ){		
			if(document.getElementsByName("mainCode")[i].value== v ){
				document.getElementsByName("mainCode")[i].checked=true;
				break;
			}		
		}
	}else{ 
		//清除mainCode打勾
    	var clear = new Boolean(true);
    	var len = document.getElementsByName("subCode").length;
    	for(var j=0 ; j<len ; j++ ){
    		if(document.getElementsByName("subCode")[j].value.substr(0,2) == v ){
    			if(document.getElementsByName("subCode")[j].checked==true){
    				clear = false;		    			
    				break;
    			}
    		}
    	}
    	if(clear==true){
    		var len2 = document.getElementsByName("mainCode").length;
			for(var k=0 ; k<len2 ; k++ ){		
				if(document.getElementsByName("mainCode")[k].value== v ){
					document.getElementsByName("mainCode")[k].checked=false;
					break;
				}		
			}
    	}
    	//若為ZZ選項，清除其他說明
    	if("ZZ"==subCode.value.substr(2)){
    		document.getElementById("otherDescribe"+v).value="";
    	}
	}
}

//請描述: 變更主代碼
function updateMainCode2(obj){
	if(obj.value !=""){
		var len = document.getElementsByName("mainCode").length;
		var v = obj.id.replace("otherDescribe","");
		for(var i=0 ; i<len ; i++ ){		
			if(document.getElementsByName("mainCode")[i].value== v ){
				document.getElementsByName("mainCode")[i].checked=true;
				break;
			}		
		}
	}else{
		var len2 = document.getElementsByName("mainCode").length;
		var v = obj.id.replace("otherDescribe","");
		for(var k=0 ; k<len2 ; k++ ){	
			if(document.getElementsByName("mainCode")[k].value== v ){
				document.getElementsByName("mainCode")[k].checked=false;
				break;
			}		
		}
	}
}

//其他 : 變更子代碼及主代碼
function updateSubCodeZZ(obj){
	if(obj.value !=""){
		var len = document.getElementsByName("subCode").length;		
		var v = obj.id.replace("otherDescribe","")+"ZZ";
    	for(var j=0 ; j<len ; j++ ){
    		if(document.getElementsByName("subCode")[j].value == v ){
    			document.getElementsByName("subCode")[j].checked=true;
    			updateMainCode(document.getElementsByName("subCode")[j]);
				break;
    		}
    	}
	}else{
		var len = document.getElementsByName("subCode").length;
		var v = obj.id.replace("otherDescribe","")+"ZZ";
    	for(var j=0 ; j<len ; j++ ){
    		if(document.getElementsByName("subCode")[j].value == v ){
    			document.getElementsByName("subCode")[j].checked=false;
    			updateMainCode(document.getElementsByName("subCode")[j]);
				break;
    		}
    	}
	}
}

function init() 
{	
	if("<%=obj.getIsNeedBackQuery()%>" == "N" || "<%=obj.getIsNeedBackQuery()%>" == ""){
		if("<%=obj.getErrorMsg()%>" != ""){
			alert("<%=obj.getErrorMsg()%>");
		}
		if("<%=obj.getIsEnabledUpdate()%>" == "Y"){
			form1.state.value="init";
			setAllOpen();
			var len = document.getElementsByName("notifierType").length;
		}else{			
			setAllReadonly();
			setFormItem("pauseSave,stayedUpload,doSend,doUpload","R");
		}
	
		if("<%=obj.getDoType()%>" == "insert"){
			form1.state.value="init";
			openStartAutoSave();
		}
	}else if('<%=obj.getUpdateType()%>'=="1"){
		setAllOpen();
	}else{		
		form1.action = "drgex0101f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		//重新整理左邊樹狀圖
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
	
	if("<%=obj.getAutoUpdate()%>"=="N"){
		document.all.item("doUpload").disabled=false;
        document.all.item("autoUpdateSend").disabled=false;
        document.all.item("autoReUpdate").disabled=true;  
	}else{
	    <%if("10,11,20,21,24,30".indexOf(Common.get(obj.getStatus())) != -1){ %>
           document.all.item("autoUpdateSend").disabled=true;
        <%}%>
	}
	
	<%if(!"".equals(Common.get(q_id))){%>    
	setAllReadonly();
	setDisplayItem("spanPauseSave,spanStayedUpload,spanDoSend,spanCancelQuit,spanReUpdate,spanAutoReUpdate,spanAutoUpdateSend,spanDoUpload,spanDoQuit,doReportView","H");	
	<%=TCBWCommon.getRevisionField("DRG01","","DRG5001",Common.get(Common.getInt(obj.getRevision())-1),obj.getApplNo())%>	
	//進行遮蔽處理	
	document.all.item("notifierName").value='';//姓名
	document.all.item("notifierDept").value='';//服務機構
	document.all.item("notifierTelArea").value='';//電話
	document.all.item("notifierTel").value='';//電話
	document.all.item("notifierTelExt").value='';//電話
	document.all.item("notifierEmail").value='';//E-Mail
	document.all.item("notifierAddress").value='';//地址	
    <%}%>     
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
	
	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit,doUpload","R");

	if("<%=obj.getDoType()%>" == "insert")
	{
		setFormItem("doCancelQuit","R");
	}
	
	if("<%=obj.getDoType()%>" == "update")
	{
		setFormItem("doOpenAutoSave","R");
	}
	$("#spanShow").empty().append($("<font color='red'>自動儲存中</font>")).fadeIn("slow");
	
	form1.action = "drgex0103f.jsp?isSave=Y";
	form1.target = "saveContainerFrame";	
	beforeSubmit();
	form1.submit();
	form1.action = "drgex0102f.jsp";
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
    /*
	var len = document.getElementsByName("notifierType").length;	
	for(var i=0 ; i<len ; i++ ){		
		document.getElementsByName("notifierType")[i].disabled = true;	
	}
	*/
	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit,doUpload","O");
}

function changeTab(tabId) 
{
	document.all.item("nextPage").value = "下 一 頁";
	form1.tabId.value = tabId;
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";

	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";

	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';


	if(tabId == 2)
	{
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";
	}
	else if(tabId == 3)
	{
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";
		document.all.item("nextPage").value = "回第一頁";
	}
	else
	{
		form1.tabId.value = 1;
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";
		form1.tabId.value='1';
	}
}

$(function()
{
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');

	$("#spanShow").hide();

	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);
	}else{
		changeTab(1);
	}
	
	if("<%=obj.getDoType()%>" == "update" && "<%=obj.getAutoUpdate()%>"=="Y")
	{
		$(".field").bind("change", openStartAutoSave);
		$(".field_btnAdd").bind("click", openStartAutoSave);
		$(".field_btnRemove").bind("click", openStartAutoSave);
	}
	
	$("#clearDept").bind("change", clearDept);
});

function permitData1(){
	var permitKey = form1.permitKey.value;
	var permitNo = form1.permitNo.value;
	var q = "&permitKey="+permitKey;
        q += "&permitNo="+permitNo;
	x = getRemoteData("../../ajax/jsonDrgObjects.jsp", q );
	if(x!=null && x.length>0){
		var json1 = JSON.parse(x);
		form1.chProduct.value = json1.obj0;  //中文品名
		form1.enProduct.value = json1.obj1;  //英文品名
		form1.applicationName.value = json1.obj2;  //申請商
		form1.manufactorName.value = json1.obj3;  //製造廠
		form1.applicationID.value = json1.obj4;  //製造廠
		form1.manufactorCountry.value = json1.obj5;  //製造廠國別
		var ingredient = json1.obj6;
		if(ingredient.length > 50) ingredient = ingredient.substr(0,50);
		form1.ingredient.value = ingredient;  //有效成分名稱
	}
}

function permitDataQ(){
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var permitKey = form1.permitKey.value;
	var permitNo = form1.permitNo.value;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("drgex0104f.jsp?permitKey="+permitKey+"&permitNo="+permitNo,'popWinE',prop);		
}

function permitData2(id){
	var permitKey = id.substring(0,2);
	var permitNo = id.substring(2,8);
	form1.permitKey.value = permitKey;
	form1.permitNo.value = permitNo;
	permitData1();
}

function getReply()
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
	returnWindow=window.open(getVirtualPath() + "tcbw/drgex/drgex0106f.jsp?isAdd=Y&applNo="+form1.applNo.value,"",prop);

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
         url+="&systemType=DRG010001";
         url+="&uploadId="+form1.id.value;
         url+="&dbName=Drg5001Db";
         
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

//檢查有無相關附件
function checkCon0001Count(upLoadId,dbName){	
	var q = "&upLoadId="+upLoadId;
        q += "&dbName="+dbName;
	x = getRemoteData("../../ajax/jsonCon0001Count.jsp", q );
	if(x!=null && x.length>0){
        return false;
	}else{
		return true;
	}
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

function checkSubCodeFileYn(){
	var subCode = "";
	for(var i=0;i<form1.subCode.length;i++){
		if(form1.subCode[i].checked){
			if(subCode.length > 0 )  subCode += ",";
			subCode += form1.subCode[i].value;
		}
	}
	x = getRemoteData("../../ajax/jsonDrg1001CheckFile.jsp", subCode);
	if(x!=null && x.length>0){
        return true;
	}else{
		return false;
	}
}

function alertAbnormalColor(subCode0101){
	if(subCode0101.checked==true) 
		alert("若為雙/三腔軟袋於操作前發現顏色異常，請詳加描述隔膜及外袋之完整性。");
}

function clearDept(){
	form1.notifierDept.value="";
	form1.notifierDeptID.value="";
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
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<iframe name="saveContainerFrame" width="0" height="0" frameborder="0">
</iframe>

<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="drgex0101q.jsp" />
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="status" value="<%=obj.getStatus()%>">
	<input type="hidden" name="drg0001Id" value="<%=obj.getDrg0001Id()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="isAlreadyAutoSave">
	<input type="hidden" name="autoUpdate" value="<%=obj.getAutoUpdate()%>">
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
	<input type="hidden" name="oldSubCode" value="<%=obj.getOldSubCode()%>">
	<input type="hidden" name="revisionId" value="<%=obj.getRevisionId()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <%if("00,01,02,22".indexOf(Common.get(obj.getStatus())) != -1){ %>
    <span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if("00".indexOf(Common.get(obj.getStatus())) != -1){ %>
    <span id="spanStayedUpload">
	   <input class="toolbar_default" type="button" followPK="false" name="stayedUpload" value="待 上 傳" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if("00,01,02,22".indexOf(Common.get(obj.getStatus())) != -1){ %>
    <span id="spanDoSend">
	   <input class="toolbar_default" type="button" followPK="false" name="doSend" value="送　出" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if("00,01".indexOf(Common.get(obj.getStatus())) != -1){ %>
    <span id="spanCancelQuit">
	   <input class="toolbar_default" type="button" followPK="false" name="doCancelQuit" value="放 棄 離 開" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
    <%}%>
    <%if("21,23,30".indexOf(Common.get(obj.getStatus())) != -1){ %>
    <span id="spanReUpdate">
       <input class="toolbar_default" type="button" followPK="false"  name="doReUpdate" value="補件回復" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if("10,11,20,21,24,30".indexOf(Common.get(obj.getStatus())) != -1){ %>
    <span id="spanAutoReUpdate">
       <input class="toolbar_default" type="button" followPK="false"  name="autoReUpdate" value="主動補件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanAutoUpdateSend">
       <input class="toolbar_default" type="button" followPK="false"  name="autoUpdateSend" value="送出補件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>    
    <%}%>
    <input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
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
	    <td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良藥品</a></td>
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">其他附件</a></td>	
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
			<td nowrap class="td_form" width="15%">發現日期</td>
			<td nowrap class="td_form_white" width="35%">
                <%=View.getPopCalendar("field","occurDate",obj.getOccurDate())%>
			</td>
			<td nowrap class="td_form" width="15%">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white"  width="35%">
                <%=View.getPopCalendar("field_RO","notifierRepDate",obj.getNotifierRepDate())%>(由通報中心填寫)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報者獲知日期</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getPopCalendar("field","notifierRevDate",obj.getNotifierRevDate())%>
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
				<%if("in".equals(User.getInORout())) {%>
					<input class="field" type="button" name="btn_User" value="查詢通報者資料" title="通報者資訊輸入輔助視窗" onclick="popUserForm()"/>
					<input class="field" type="button" value="清除通報者" onClick="clearUser();">
				<% } %>
			</td>
			<td nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white">				
				<input class="field" type="hidden" name="notifierDeptID" size="10" maxlength="10" value="<%=obj.getNotifierDeptID()%>" />
	  	        <input class="field_RO" type="text" name="notifierDept" size="30" maxlength="6"  value="<%=obj.getNotifierDept()%>" />
			    <input type="button" name="btnQryExpense" onClick="popNotifierDept(notifierType);" value="查詢" width="120px" class="field" >
			</td>			
		</tr>		
		<tr>
			<td nowrap class="td_form">電話</td>
			<td nowrap class="td_form_white">				
		        ( <input class="field_RO" type="text" name="notifierTelArea" size="2" maxlength="2" value="<%=obj.getNotifierTelArea()%>"> )
			    - <input class="field_RO" type="text" name="notifierTel" size="20" maxlength="20" value="<%=obj.getNotifierTel()%>">
			    # <input class="field_RO" type="text" name="notifierTelExt" size="3" maxlength="3" value="<%=obj.getNotifierTelExt()%>">
			</td>
			<td nowrap class="td_form">E-Mail</td>
			<td nowrap class="td_form_white" >
				<input class="field_RO" type="text" name="notifierEmail" maxlength="30" size="20" value="<%=obj.getNotifierEmail()%>"/>
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form">地址</td>
			<td nowrap class="td_form_white" colspan="3" >
				<select class="field_RO" name="notifierCounty" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", obj.getNotifierCounty())%>
			    </select>
			    <select name="notifierZipCode" class="field_RO">
				   <%=View.getOptionCon1002(obj.getNotifierZipCode())%>
			    </select>
			    <input class="field_RO" type="text" name="notifierAddress" size="50" maxlength="80" value="<%=obj.getNotifierAddress()%>">
			</td>
		</tr>
        <tr>
			<td nowrap class="td_form">職稱</td>
			<td nowrap class="td_form_white">				
				<select class="field_RO" name="notifierTitle" type="select">
				      <%=View.getOptionCodeKind("TITLE", obj.getNotifierTitle()) %>
			    </select>
			</td>
			<td nowrap class="td_form">屬性</td>
			<td nowrap class="td_form_white" id="clearDept">
				<%=View.getRadioBoxOption("field_RO", "notifierType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", obj.getNotifierType())%>			
			</td>
		</tr>
	</table>	
	<table id="Tab2" width="100%" align="center" class="table_form">
        <tr>
			<td nowrap class="td_form_left" colspan="4">不良藥品資訊</td>
		</tr>
		<tr> 
			<td nowrap class="td_form" >許可證字號</td>
			<td nowrap class="td_form_white" colspan="3">					
			<select name="permitKey" class="field" onChange="permitData1();">
				<%=View.getOptionCodeKind("DRGPKID", obj.getPermitKey())%>
			</select>			 
			    <input class="field" type="text" name="permitNo" size="6" maxlength="6" value="<%=obj.getPermitNo()%>" onChange="permitData1();"/>號
			    <input type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" class="field" >
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">藥品商品名</td>
			<td nowrap class="td_form_white" colspan="3">
			      中文品名 <input class="field" type="text" name="chProduct" size="100"  maxlength="50"  value="<%=obj.getChProduct()%>"/><br>
			      英文品名 <input class="field" type="text" name="enProduct" size="100"  maxlength="200"  value="<%=obj.getEnProduct()%>"/>
            </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">有效成分名稱</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="ingredient" size="50" maxlength="25" value="<%=obj.getIngredient()%>"/></td>
			<td nowrap class="td_form">單位含量</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="content" size="20" maxlength="20" value="<%=obj.getContent()%>"/></td>
		</tr>
		<tr>
			<td nowrap class="td_form">劑型</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "medModel", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGFLN' order by codeId", obj.getMedModel(),null,3)%>			
			    (請描述)：<input class="field" type="text" name="medModelOther" size="40" maxlength="25" value="<%=obj.getMedModelOther()%>"/>
		    </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">包裝形式</td>
			<td nowrap colspan="3" class="td_form_white" >
                <%=View.getRadioBoxOption("field", "medPackage", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0102' order by codeId", obj.getMedPackage())%>			
			    (請描述)：<input class="field" type="text" name="medPackageOther" size="40" maxlength="25" value="<%=obj.getMedPackageOther()%>"/>	
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">藥商</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="applicationName" size="40" maxlength="25" value="<%=obj.getApplicationName()%>"/>
			  <input class="field" type="hidden" name="applicationID" size="40" maxlength="25" value="<%=obj.getApplicationID()%>"/>
			</td>
			<td nowrap class="td_form">製造商</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="manufactorName" size="40" maxlength="25" value="<%=obj.getManufactorName()%>"/>
			  <input class="field" type="hidden" name="manufactorCountry" size="40" maxlength="25" value="<%=obj.getManufactorCountry()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造批號</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="manufactorNo" size="15"   maxlength="11" value="<%=obj.getManufactorNo()%>"/></td>
			<td nowrap class="td_form">製造日期</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="manufactorDate" size="15"  maxlength="20" value="<%=obj.getManufactorDate()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">保存期限</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="expirationDate" size="15" maxlength="20" value="<%=obj.getExpirationDate()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">儲存環境</td>
			<td nowrap colspan="3" class="td_form_white" >
                <%=View.getRadioBoxOption("field", "storage", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0103' order by codeId", obj.getStorage())%>			
			    (請描述)：<input class="field" type="text" name="storageOther" size="40" maxlength="25" value="<%=obj.getStorageOther()%>"/>				
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">本次通報事件是否為單一個案</td>
			<td nowrap class="td_form_white" >
			<input class="field" type="radio" name="isSingleYn" value="Y" <%if("Y".equals(obj.getIsSingleYn())) out.print("checked"); %>>是<br>
			<input class="field" type="radio" name="isSingleYn" value="N" <%if("N".equals(obj.getIsSingleYn())) out.print("checked"); %>>否，			    			
				同批號產品共<input class="field" type="text" name="sameNum" size="5" maxlength="5" value="<%=obj.getSameNum()%>"/>件&nbsp;&nbsp;
				不同批號產品共<input class="field" type="text" name="diffNum" size="5" maxlength="5" value="<%=obj.getDiffNum()%>"/>件
			</td>
			<td nowrap class="td_form">是否一經拆封即發現本不良品缺陷</td>
			<td nowrap class="td_form_white" >
			    <%=View.getRadioBoxTextOption("field","isFindYn","N;否;Y;是;",obj.getIsFindYn())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否為病人使用後發現不良品<br>，向醫療人員反應</td>
			<td nowrap class="td_form_white">
			    <%=View.getRadioBoxTextOption("field","isUsedYn","N;否;Y;是;",obj.getIsUsedYn())%>
			</td>
			<td nowrap class="td_form">是否已對人體健康產生危害</td>
			<td nowrap class="td_form_white">
			    <%=View.getRadioBoxTextOption("field","isHarmYn","N;否;Y;是;",obj.getIsHarmYn())%>，並請同時作藥品不良反應通報。
			</td>
		</tr>
		<tr>		
			<td nowrap class="td_form_left" colspan="4">不良品後續處理情形(請詳加填寫，通報中心將以此為依據)</td>
		</tr>
		<tr>
			<td nowrap class="td_form">已連絡廠商</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getRadioBoxTextOption("field","evenContactYn","N;否;Y;是;",obj.getEvenContactYn())%>
			</td>
		</tr>
		<tr>
			<td class="td_form">不良品後續處理</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "dealWith", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0104' order by codeId", obj.getDealWith())%>			
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">提供聯絡資訊供廠商<br>後續調查評估</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getRadioBoxTextOption("field","isContactYn","N;否;Y;是;",obj.getIsContactYn())%>
			    (僅提供機構名稱及住址)
			</td>
		</tr>
		<tr>
			<td nowrap colspan="4" class="td_form_left">不良品缺陷之描述</td>
		</tr>		
		<tr>
		    <td nowrap class="td_form_white" colspan="4">
		       <%=obj.getCheckBoxOption2("field", "mainCode", "subCode", "otherDescribe", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId", obj.getMainCode(),obj.getSubCode(),obj.getId(),obj.getRevisionId(),obj.getOldSubCode())%>
	        </td>
	    </tr>
	    <tr>
			<td nowrap class="td_form">不良品缺陷描述說明</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="defectDesc" cols="100" rows="6"><%=obj.getDefectDesc()%></textarea>
			</td>	
		</tr>
	    <tr>
			<td nowrap class="td_form">不良品原因初評</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getRadioBoxOption("field", "firstResult", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0106' order by codeId", obj.getFirstResult())%>			
			    (請描述)：<input class="field" type="text" name="firstRemark" size="40" maxlength="50" value="<%=obj.getFirstRemark()%>"/>	
			</td>
		</tr>
	</table>	
	<table id="Tab3" width="100%" align="center" class="table_form">
	      <thead id="listTHEAD">	          
	          <tr>
	          <td>
	              <span id="spanDoUpload">
		          <input class="toolbar_default" type="button" followPK="false" name="doUpload" value="附件上傳" onClick="upload()">&nbsp;
	              </span>
	          </td>
	          </tr>
	          <tr>
		         <th class="listTH" width="10%" >No.</th>
		         <th class="listTH" width="30%">檔案名稱</th>
		         <th class="listTH" width="50%">檔案說明</th>
		         <th class="listTH" width="10%">刪除</th>
	           </tr>
	        </thead>
	        <tbody>
			   <%=obj.getAddFile()%>
			</tbody>
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
	  case "pauseSave":
	  case "stayedUpload":
	  case "doSend":
	  case "doCancelQuit":
		  form1.state.value = buttonName;
		  checkField();
		  break;		  
	  case "doReUpdate":
		  getReply();
		  break;
	  case "doDoQuit":
		  form1.action = "drgex0101f.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	  case "autoReUpdate":
		  if(confirm("確定主動補件?"))
		  {
              form1.autoUpdate.value="N"; //關閉自動儲存
              form1.state.value="update";
              document.all.item("doUpload").disabled=false;
              document.all.item("autoUpdateSend").disabled=false;
              document.all.item("autoReUpdate").disabled=true;              
			  setAllOpen();
		  }
		  break;
	  case "autoUpdateSend":
		  form1.state.value = buttonName;
		  checkField();
		  break;
		//案件列印  
	  case "doReportView":
		  form1.action = "drgex0102p.jsp" ;
		  form1.target = "_blank";
		  beforeSubmit();
		  form1.submit();
		  form1.action = "drgex0102f.jsp";
		  form1.target = "_self";		  
		  break;
	}
}
</script>
</body>
</html>
