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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="PMED0201Q" />
</jsp:include>
<jsp:useBean id="obj0201" scope="request" class="com.kangdainfo.tcbw.view.pmed.PMED0201F">
	<jsp:setProperty name="obj0201" property="*"/>
</jsp:useBean>
<jsp:useBean id="obj0201List" scope="page" class="java.util.ArrayList"/>
<%
if("pauseSave".equals(obj0201.getState()) || "doHand".equals(obj0201.getState())){
	try	{
		obj0201.setHttpRequest(pageContext.getRequest());	
		obj0201.doUpdateType();
		if("0".equals(obj0201.getUpdateType())){
			obj0201.setErrorMsg("修改完成");
		}else{
			obj0201.setErrorMsg("完成送出");
		}		
	}catch(Exception e){
		e.printStackTrace();
		obj0201.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("upload".equals(obj0201.getState())){
	try	{		
		obj0201.doUpdateType();
		obj0201.queryOne();
	}catch(Exception e)	{
		e.printStackTrace();
		obj0201.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}else if("reupdateComplete".equals(obj0201.getState()))
{
	try
	{
		obj0201.setHttpRequest(pageContext.getRequest());	
		obj0201.doReupdate();
		//obj0301.doDeleteCon0003Db();
		obj0201.setErrorMsg("補件回覆完成");
		//obj0301.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		//obj0301.setIsNeedBackQuery("N");
		obj0201.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj0201 = (com.kangdainfo.tcbw.view.pmed.PMED0201F) obj0201.queryOne();
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

function checkField()
{
	var id2 = form1.id2.value;
	var alertStr = "";
	if(form1.state.value == "pauseSave"){		
		form1.updateType.value = "0";
	}else if(form1.state.value=="doHand"){
		<%=TCBWCommon.getCheckFiled("MED05","PMED0201")%>
		alertStr += checkDateSE(form1.handdate,form1.systemTime0202,"實際繳交日期");
		form1.updateType.value = "1";
	}else if(form1.state.value=="reupdateComplete"){
		alertStr += checkEmpty(form1.noticereupdatedate,"通知補件日期");
		alertStr += checkEmpty(form1.reupdatedate,"補件日期");
		form1.updateType.value = "2";
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
	var tabId = document.all.item("tabId").value;

	//按鈕隱藏設定：依handstatus(繳交狀態)判斷，01：待繳交；02：待評估；03：待補件；04：已評估
	if(tabId == ""){
		changeTab(3);
		if("03"==form1.handstatus.value && "03"==form1.caseType.value) {
			setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden,doHand","H");
		} else
		{
			setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden,reupdateComplete","H");
		}		
	}else if("01"==form1.handstatus.value) {
		changeTab(3);
		setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden,reupdateComplete","H");
	}else{
		changeTab(tabId);
		if("02"==form1.handstatus.value || "04"==form1.handstatus.value || ("03"==form1.caseType.value && "03"!=form1.handstatus.value)) {
			setDisplayItem("spanUpdate,spanClear,spanPauseSave,spanDoHand,spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden,reupdateComplete","H");
		} else if("03"==form1.handstatus.value && "03"==form1.caseType.value) {
			setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden,doHand","H");
		} else if("01"==form1.handstatus.value) {
			setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden,reupdateComplete","H");
			
		} else {
			setDisplayItem("spanUpdate,spanClear,spanPauseSave,spanDoHand,spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden,reupdateComplete","H");
		}
	}	
	setFormItem("pauseSave,doHand,reupdateComplete,doUpload2,doUpload3,doUpload4","R");
	//修改資料完畢，導入查詢頁面
	if('<%=obj0201.getErrorMsg()%>'=='完成送出')
	{			
		form1.action = "pmed0201q.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
	//補件回覆完成，導入查詢頁面
	if('<%=obj0201.getErrorMsg()%>'=='補件回覆完成')
	{			
		form1.action = "pmed0301q.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
	//檔案上傳維持在修改狀態
	if(form1.isUpload.value=="Y"){
		whatButtonFireEvent("update");
	}
	form1.isUpload.value="";
	
}
function queryOne(id2)
{
    form1.id2.value = id2;
    form1.doType.value = "update";
    form1.state.value = "queryOne";
    form1.action = "pmed0201f.jsp";
	beforeSubmit();
	form1.submit();
}

function checkBeforeReport() 
{
	var med9001ID = form1.med9001Id.value;
	var reporttype = form1.reporttype.value;
	var reportIssueno = form1.reportIssueno.value;
	var prehanddate = form1.prehanddate.value;
	
	var q = "&med9001Id="+med9001ID;
    q += "&reporttype="+reporttype;
    q += "&reportIssueno="+reportIssueno;
    q += "&prehanddate="+prehanddate;

    x = getRemoteData("../../ajax/jsonPmed0201f.jsp",q );
    if(x!=null && x.length>0)
  	{
    	var json = eval('(' + x + ')'); 
		var i = 0;
		for (i=0; i<json.length; i++) 
		{
			var no =  json[i].reportIssueno;			
		}
		
      	alert("第"+no+"期報告未繳交，請先完成第"+no+"期報告");
		return false;
  	}
    else
    {
        return true;
    } 	
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
       url+="fileKind=MED";
       url+="&systemType="+type;
       url+="&uploadId="+form1.id2.value;
       if(type == "MED050002")
       {
           url+="&dbName=Med9001Db";
       }
       else
       {
    	   url+="&dbName=Med9002Db";
       }
       
	 returnWindow = window.open(url,'上傳檔案',prop);
}
</script>
</head>
<body  onLoad="whatButtonFireEvent('<%=obj0201.getState()%>');init();showErrorMsg('<%=obj0201.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<jsp:include page="pmed0101js.jsp" />
	
</div>

<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="id" value="<%=obj0201.getId()%>">
    <input type="hidden" name="id2" value="<%=obj0201.getId2()%>">	
    <input type="hidden" name="state" value="<%=obj0201.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj0201.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	<input type="hidden" name="changeTabValue" value="<%=obj0201.getChangeTabValue()%>">
	<input type="hidden" name="tabId" value="<%=obj0201.getTabId()%>">
	<input type="hidden" name=med9001Id value="<%=obj0201.getMed9001Id()%>">
	<input type="hidden" name=caseType value="<%=obj0201.getCaseType() %>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="isUpload" value="<%=obj0201.getIsUpload()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span> 	
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanDoHand">
		<input class="toolbar_default" type="button" followPK="false" name="doHand" value="繳交完成" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanReupdateComplete">
	   <input class="toolbar_default" type="button" followPK="false" name="reupdateComplete" value="補件完成" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
	
</td>
</tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">監視案件</a></td>	
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">定期報告</a></td>
	    <%if(!"01".equals(obj0201.getHandstatus())) { %>
	    <td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">定期報告評估</a></td>								
	    <%} %>			
	</TR>
</TABLE>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
<jsp:include page="pmed0102f.jsp" />
<jsp:include page="pmed0202f.jsp" />

<%if(!"01".equals(obj0201.getHandstatus())) { %>
<jsp:include page="pmed0302f.jsp" />
<%} %>
</td>
</tr>

</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName)
	{
		case "doHand":				//報告繳交
		case "reupdateComplete":	//補件回覆
		case "pauseSave":
			form1.state.value = buttonName;
			checkField();
			break;
		case "queryAll":
			break;
	  	case "doDoQuit":
		  	if(confirm("確定回到查詢頁面?"))
		  	{
			  	if(form1.caseType.value != "")
				{
				  	form1.action = "pmed0201q.jsp";
				}
			  	else
				{
			  		form1.action = "pmed0201q.jsp?caseType=2";
				} 	
			   	form1.state.value = "queryAll";
			   	form1.submit();
		  	}
		  	break;
	    case "update":
	    	setAllReadonly();
	    	setFormItem("pauseSave,doHand,reupdateComplete,doUpload2,doUpload3,doUpload4","O");
	    	if(checkBeforeReport()!=false)
		    {
		        $("#Tab3").find('.field').attr('readOnly',false);
		        $("#Tab3").find('.field').attr('disabled',false);
		        $("#Tab3").find('.field_btnAdd').attr('disabled',false);
	            $("#Tab3").find('.field_btnRemove').attr('disabled',false);
		    }
	    	else
		    {
		    	form1.state.value = "queryOne";
		    	form1.submit();
		    }

		    break;
	  	  
	}
}
</script>
</html>
