<!--
程式目的：PSUR登錄作業
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="PMED0101Q" />
</jsp:include>
<jsp:useBean id="obj0101" scope="request" class="com.kangdainfo.tcbw.view.pmed.PMED0101F">
	<jsp:setProperty name="obj0101" property="*"/>
</jsp:useBean>
<jsp:useBean id="obj0101List" scope="page" class="java.util.ArrayList"/>
<%

if("insert".equals(obj0101.getDoType()) && "queryOne".equals(obj0101.getState())){
	// 由查詢頁面，到本頁時判斷，是否需新增一筆
	try {
		obj0101.doInsert();
	}catch(Exception e){
		e.printStackTrace();
	}
}
else if("pauseSave".equals(obj0101.getState()) || "doSend".equals(obj0101.getState())){
	try	{
		obj0101.setHttpRequest(pageContext.getRequest());	
		obj0101.doUpdateType();
		if("0".equals(obj0101.getUpdateType())){
			obj0101.setErrorMsg("修改完成");
		}else{
			obj0101.setErrorMsg("完成送出");
		}		
	}catch(Exception e){
		e.printStackTrace();
		obj0101.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("doCancelQuit".equals(obj0101.getState())){
	try	{
		obj0101.doDelete();
		if (obj0101.getErrorMsg().equals("")){
			obj0101.setErrorMsg("放棄資料完成");
		}
		obj0101.setId("");
	}catch(Exception e)	{
		e.printStackTrace();
		obj0101.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("upload".equals(obj0101.getState())){
	try	{		
		obj0101.doUpdateType();
		obj0101.queryOne();
	}catch(Exception e)	{
		e.printStackTrace();
		obj0101.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("generateList".equals(obj0101.getState())) {
	try {
		obj0101.doUpdateType();
		obj0101.doGenerateList();
		obj0101.setErrorMsg("產生繳交清單完成!");
	}catch(Exception e) {
		e.printStackTrace();
		obj0101.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj0101 = (com.kangdainfo.tcbw.view.pmed.PMED0101F) obj0101.queryOne();
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
function checkField()
{
	var id = form1.id.value;
	var alertStr = "";
	if(form1.state.value == "pauseSave"){		
		form1.updateType.value = "0";
	}else if(form1.state.value=="doSend"){
		alertStr += validateAddrTable('med9002DbTypeId','reporttype');
		<%=TCBWCommon.getCheckFiled("MED05","PMED0101")%>
		form1.updateType.value = "1";
	}else if(form1.state.value=="upload"){
		form1.updateType.value = "0";
	}else if(form1.state.value=="generateList") {
		alertStr += checkEmpty(form1.monitorSDate,"監控時間起日");
		alertStr += checkEmpty(form1.monitorEDate,"監控時間迄日");
		alertStr += checkEmpty(form1.reportIssuenum,"報告繳交期數");
		alertStr += checkEmpty(form1.intervalmonth,"各期間隔(月)");
	}
	if(alertStr.length!=0){ alert(alertStr); return }
	beforeSubmit();
	form1.submit();	
}

//送出時檢核報告繳交清單
function validateAddrTable(typeId,itName) 
{
	var rs=document.getElementsByName(typeId);
	
	if (rs!=null && rs.length>0) 
	{		
		var itemIdx = new Array(rs.length);
		for (var i=0; i<rs.length; i++) 
	    {
			var itemName=itName;
			var id = rs[i].value;
			var sb = new StringBuffer();
			itemName+=i+1;
			itemIdx[i] = document.all.item(itemName).value;
		}

		for (var i=0; i<itemIdx.length; i++)
		{
			if(itemIdx[i] != "01")
			{
				if(itemIdx[i] != "02")
				{
					return "報告繳交清單缺少定期報告與總結報告!!\n";
				}
				else
				{
					return "";
				}
			}
		}
		
	}
	return "報告繳交清單缺少定期報告與總結報告!!\n";
}

function beforeinit()
{
	<%=obj0101.getMed9002DbItemSet()%>
}

function init() 
{	
	var tabId = document.all.item("tabId").value;
	if(tabId == ""){
		changeTab(1);		
	}else{
		changeTab(tabId);
	}
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	if('<%=obj0101.getStatus()%>' >= "10")
		{
		setDisplayItem("spanUpdate,spanClear","S");
		setFormItem("pauseSave","R");
		setAllReadonly();
		}
	//修改模式(將按鈕解鎖)
	if('<%=obj0101.getDoType()%>'!="queryOne" && '<%=obj0101.getStatus()%>'=="00"){
		form1.state.value="init";
		form1.doType.value=""; //清除doType 避免重複新增明細		
		setAllOpen(); //打開所有欄位
			
	}
	//修改資料完畢，導入查詢頁面
	if('<%=obj0101.getErrorMsg()%>'=='放棄資料完成' || '<%=obj0101.getErrorMsg()%>'=='完成送出')
	{			
		form1.action = "pmed0101q.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
}

$(function()
		{
			var permitKey = form1.permitKey.value;

		    if(permitKey!="Z0" || permitKey!="Z5")
		    {
		    	document.all.item('chProduct').className="field_RO";		
		    	document.all.item('enProduct').className="field_RO";
		    	document.all.item('medapprovedate').className="field_RO";
		    	document.all.item('medEffectiveDate').className="field_RO";
		    	document.all.item('applicationName').className="field_RO";
		    	document.all.item('manufactorName').className="field_RO";
		    	document.all.item('manufactorCountry').className="field_RO";
		    	document.all.item('medclass').className="field_RO";
		    	document.all.item('medMainCategory').className="field_RO";
		    	document.all.item('medSecCategory').className="field_RO";
		    	document.all.item('medModel').className="field_RO";
		    	document.all.item('medeffect').className="field_RO";
				
				//document.getElementById('permitKey').className="field_RO";
				//document.getElementById('permitNo').className="field_RO";

				document.all.item('chProduct').readOnly = true;
				document.all.item('enProduct').readOnly = true;
				document.all.item('medapprovedate').readOnly = true;
				document.all.item('medEffectiveDate').readOnly = true;
				document.all.item('applicationName').readOnly = true;
				document.all.item('manufactorName').readOnly = true;
				document.all.item('manufactorCountry').readOnly = true;
				document.all.item('medclass').readOnly = true;
				document.all.item('medMainCategory').readOnly = true;
				document.all.item('medSecCategory').readOnly = true;
				document.all.item('medModel').readOnly = true;
				document.all.item('medeffect').readOnly = true;
				//document.getElementById('permitKey').readOnly = true;
				//document.getElementById('permitNo').readOnly = true;
		    }   

});

//檔案上傳
function upload(type)
{
	 var prop='';
	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();

   var url="../../home/popManyUploadSimple.jsp?";
       url+="fileKind=MED";
       url+="&systemType="+type;
       url+="&uploadId="+form1.id.value;
       url+="&dbName=Med9001Db";
       
	 returnWindow = window.open(url,'上傳檔案',prop);
}


</script>
</head>
<body  onLoad="whatButtonFireEvent('<%=obj0101.getState()%>');beforeinit();init();showErrorMsg('<%=obj0101.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<jsp:include page="pmed0101js.jsp" />
	
</div>

<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="id" value="<%=obj0101.getId()%>">
    <input type="hidden" name="state" value="<%=obj0101.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj0101.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	<input type="hidden" name="changeTabValue" value="<%=obj0101.getChangeTabValue()%>">
	<input type="hidden" name="status" value="<%=obj0101.getStatus() %>">
	<input type="hidden" name="tabId" value="<%=obj0101.getTabId()%>">
	<input type="hidden" name="updateType">
	

	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span> 
    

    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	
	<%if("00".equals(obj0101.getStatus())) {%>
	<span id="spanCancelQuit">
	    <input class="toolbar_default" type="button" followPK="false" name="doCancelQuit" value="放 棄 離 開" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<input class="toolbar_default" type="button" followPK="false" name="generateList" value="產生報告繳交清單" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<span id="spanDoSend">
	   <input class="toolbar_default" type="button" followPK="false" name="doSend" value="送　出" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    
    <%} %>
	
</td>
</tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">監視案件</a></td>
	    <td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">報告繳交清單</a></td>				
	</TR>
</TABLE>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
<jsp:include page="pmed0102f.jsp" />
<jsp:include page="pmed0103f.jsp" />

</td></tr>

</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName)
	{
		case "doSend":
		case "pauseSave":
			form1.state.value = buttonName;
			checkField();
			break;
	    case "queryAll":
		    break;
	    case "doCancelQuit":
		    if(confirm("確定放棄編輯?"))
		    {
			    form1.state.value = buttonName;
			    checkField();
		    }
		    break;
	    case "doDoQuit":
		    if(confirm("確定回到查詢頁面?"))
			{
				form1.action = "pmed0101q.jsp";
			    form1.state.value = "queryAll";
			    form1.submit();
		    }
		    break;
	    case "generateList":
		    form1.state.value = buttonName;
		    checkField();
		    break;
	    case "update":
	    	setAllReadonly();
	    	setFormItem("pauseSave","O");
	        $("#Tab2").find('.field').attr('readOnly',false);
	        $("#Tab2").find('.field').attr('disabled',false);
	        $("#Tab2").find('.field_btnAdd').attr('disabled',false);
            $("#Tab2").find('.field_btnRemove').attr('disabled',false);
		    break;    
	}
}
</script>
</html>
