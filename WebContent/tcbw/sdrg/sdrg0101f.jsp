<!--
程式名稱：重大品質事件廠商主動通報登錄作業
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SDRG0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
String q_id = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("v")));
if(!"".equals(Common.get(q_id))) obj.setId(q_id);

if("insert".equals(obj.getDoType()) && "queryOne".equals(obj.getState())){
	// 由查詢頁面，到本頁時判斷，是否需新增一筆
	try {
		obj.doInsert();
	}catch(Exception e){
		e.printStackTrace();
	}
}
else if("pauseSave".equals(obj.getState()) || "doSend".equals(obj.getState())){
	try	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
		if("0".equals(obj.getUpdateType())){
			obj.setErrorMsg("修改完成");
		}else{
			obj.setErrorMsg("完成送出");
		}		
	}catch(Exception e){
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("doCancelQuit".equals(obj.getState())){
	try	{
		obj.doDelete();
		if (obj.getErrorMsg().equals("")){
			obj.setErrorMsg("放棄資料完成");
		}
		obj.setId("");
	}catch(Exception e)	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("upload".equals(obj.getState())){
	try	{		
		obj.doUpdateType();
		obj.queryOne();
	}catch(Exception e)	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.sdrg.SDRG0101F) obj.queryOne();

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
		form1.updateType.value = "0";
	}else if(form1.state.value=="doSend"){
		alertStr += validateDrg8002Table();			
		alertStr += checkRadioOtherEmpty(form1.isabroad,'Y',form1.abroadCountry,'輸出國別');
		//其他輸出國別檢核
		var myArray = form1.abroadCountry.value.split(",");    
	    for (var i = 0; i < myArray.length; i++) 
	    {
	        if("ZZ"==myArray[i]) alertStr +=checkEmpty(form1.abroadCountryOther,"輸出國別勾選其他，其他輸出國別不得為空");	    	
	    }		
		<%=TCBWCommon.getCheckFiled("DRG04","SDRG0102")%>;
		form1.updateType.value = "1";
	}else if(form1.state.value=="upload"){
		form1.updateType.value = "0";
	}
	if(alertStr.length!=0){ alert(alertStr); return }
	beforeSubmit();
	form1.submit();	
}

function init() 
{	
	//將按鈕先鎖住
	setFormItem("doUpload1,doUpload2,doUpload3,doUpload4,btnAbroadQuery,btnAbroadClear","R");	
	//隱藏不需要的預設按鈕
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");	
	//修改模式(將按鈕解鎖)
	if('<%=obj.getDoType()%>'!="queryOne" && '<%=obj.getStatus()%>'=="00"){
		setFormItem("doUpload1,doUpload2,doUpload3,doUpload4,btnAbroadQuery,btnAbroadClear","O");
		form1.state.value="init";
		//當新增時先將回收清單增加一筆新的，使用者不要就自行刪除。
		if('<%=obj.getDoType()%>'=="insert"){			
			addDrg8002Db();
		}
		form1.doType.value=""; //清除doType 避免重複新增明細		
		setAllOpen(); //打開所有欄位		
	}
	//修改資料完畢，導入查詢頁面
	if('<%=obj.getErrorMsg()%>'=='放棄資料完成' || '<%=obj.getErrorMsg()%>'=='完成送出')
	{			
		form1.action = "sdrg0101q.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
	//許可證字號動態調整(tab1)
	permitData1("init");
	var dRows = document.getElementById("attDrg8002DbView");	
	if(dRows!=null && dRows.length==0){
		addDrg8002Db();
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

function printWordReport(type) {	
	if(type != null && type != ''){
		form1.reportType.value=type;
		form1.action="sdrg0101p.jsp";
		form1.target="_blank";
		form1.submit();
		form1.target="_self";
		form1.action="sdrg0101f.jsp";
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
	<input type="hidden" name="reportType" value="<%=obj.getReportType()%>">
	<input type="hidden" name="updateType">
	<%if("00".equals(Common.get(obj.getStatus()))){ %>
    <span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>  
    <span id="spanDoSend">
	   <input class="toolbar_default" type="button" followPK="false" name="doSend" value="送　出" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanPrintWord">
	   <input class="toolbar_default" type="button" followPK="false" name="printWord1" value="產生回收通知函" onClick="printWordReport('01');">&nbsp;
	   <input class="toolbar_default" type="button" followPK="false" name="printWord2" value="產生回收計畫書" onClick="printWordReport('02');">&nbsp;
    </span>
    <%}%>
    <%if("".equals(Common.get(obj.getApplNo()))){ %>
    <span id="spanCancelQuit">
	   <input class="toolbar_default" type="button" followPK="false" name="doCancelQuit" value="放 棄 離 開" onClick="whatButtonFireEvent(this.name)">&nbsp;
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
	    <td nowrap nowrap CLASS="tab_border1" ID=t1>案件登錄</TD>
	</TR>
</TABLE>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
        <jsp:include page="sdrg0102f.jsp" />
    </div>
    </td>
</tr>

</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "pauseSave":
	  case "doSend":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doCancelQuit":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doDoQuit":
		  form1.action = "sdrg0101q.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;	  
	}
}
</script>
</body>
</html>
