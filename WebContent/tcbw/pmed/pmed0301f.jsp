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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="PMED0301Q" />
</jsp:include>
<jsp:useBean id="obj0301" scope="request" class="com.kangdainfo.tcbw.view.pmed.PMED0301F">
	<jsp:setProperty name="obj0301" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if("pauseSave".equals(obj0301.getState()) || "assessComplete".equals(obj0301.getState())){
	try	{
		obj0301.setHttpRequest(pageContext.getRequest());	
		obj0301.doUpdateType();
		if("0".equals(obj0301.getUpdateType())){
			obj0301.setErrorMsg("修改完成");
		}else{
			obj0301.setErrorMsg("評估完成");
		}		
	}catch(Exception e){
		e.printStackTrace();
		obj0301.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("upload".equals(obj0301.getState())){
	try	{		
		obj0301.doUpdateType();
		obj0301.queryOne();
	}catch(Exception e)	{
		e.printStackTrace();
		obj0301.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}else if("addocuments".equals(obj0301.getState()))
{
	try
	{
		obj0301.setHttpRequest(pageContext.getRequest());	
		obj0301.doAddocuments();
		//obj0301.doDeleteCon0003Db();
		obj0301.setErrorMsg("通知補件完成");
		//obj0301.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		//obj0301.setIsNeedBackQuery("N");
		obj0301.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj0301 = (com.kangdainfo.tcbw.view.pmed.PMED0301F) obj0301.queryOne();
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
	}else if(form1.state.value=="assessComplete"){
		<%=TCBWCommon.getCheckFiled("MED05","PMED0301")%>
		alertStr += checkDateSE(form1.assessdate,form1.systemTime0202,"評估日期");
		form1.updateType.value = "1";
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
	if(tabId == ""){
		if("02" == form1.handstatus1.value) {
			setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
		} else {
			setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden,assessComplete,addocuments","H");
		}
		changeTab(4);
	}else if("01" == form1.handstatus1.value) {
		changeTab(3);
		setDisplayItem("spanUpdate,spanClear,spanPauseSave,spanDoHand,spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden,spanAddocuments,spanDoAssessComplete","H");
	}else{
		changeTab(tabId);
		if("02" == form1.handstatus1.value) {
			setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
		} else {
			setDisplayItem("spanUpdate,spanClear,spanPauseSave,spanDoHand,spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden,spanAddocuments,spanDoAssessComplete","H");
		}
	}
	
	setFormItem("pauseSave,assessComplete,addocuments,doUpload5","R");

	//修改資料完畢，導入查詢頁面
	if('<%=obj0301.getErrorMsg()%>'=='評估完成' || '<%=obj0301.getErrorMsg()%>'=='通知補件完成')
	{			
		form1.action = "pmed0301q.jsp?caseType=02";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
	//檔案上傳維持在修改狀態
	if(form1.isUpload.value=="Y"){
		whatButtonFireEvent("update");
	}else{
		//setDisplayItem("spanUpdate,spanClear,spanPauseSave,spanDoHand,spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden,spanAddocuments,spanDoAssessComplete","H");
	}
	form1.isUpload.value="";
}

function queryOne(id2,handstatus)
{
    form1.id2.value = id2;
    form1.doType.value = "update";
    form1.state.value = "queryOne";
    //if("03" == handstatus)
    //{
    	//form1.action = "pmed0201f.jsp";
    //}
    //else
    //{
    //	form1.action = "pmed0301f.jsp";
    //}
    
	beforeSubmit();
	form1.submit();
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
       url+="&dbName=MED9002DB";
       
	 returnWindow = window.open(url,'上傳檔案',prop);
}

//寄發郵件
function getEmail(mailID,isJS,isAdd)
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1200,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	
	var url="isAdd="+isAdd;
	    url+="&applNo="+form1.applNo.value;
	    url+="&isJS="+isJS;
	    //url+="&Kind=3";
	    url+="&mailID="+mailID;
	    url+="&id="+<%=obj0301.getId2()%>;
	    //url+="&manyMailID=MED030002,MED030003";
	    url+="&applState="+'<%=obj0301.getStatus()%>';
	    
	returnWindow=window.open(getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?"+url,"",prop);
}

//補件
function addocuments()
{
   form1.state.value="addocuments";
   beforeSubmit();
   form1.submit();
   return true;
}
//檢視監視期間內同產品不良反應案件
function toShowMed01()
{
	if(null != form1.permitKey.value && "" != form1.permitNo.value){
		var permitKey = form1.permitKey.value;
		var permitNo = form1.permitNo.value;	
		var monitorsdateR = form1.monitorsdateR.value;
		var monitoredateR = form1.monitoredateR.value;	 
		var params = 'width=980,height=640,resizable=1,menubar=no,scrollbars=yes';
		closeReturnWindow();
		window.open(getVirtualPath() + "tcbw/medin/medin2001q.jsp?isPop=Y&q_medPermit="+permitKey+"&q_medPermitNumber="+permitNo+"&q_notifierRepDateS="+monitorsdateR+"&q_notifierRepDateE="+monitoredateR+"&q_eventKind=1"+"&q_caseSource=in",'popWinE',params);	
		whatButtonFireEvent("queryCannel");
		
	}else{
		alert("請選擇一筆藥證資料!!");
	}
}
//檢視監視期間內同產品不良品案件
function toShowMed02()
{
	if(null != form1.permitKey.value && "" != form1.permitNo.value)
	{
		var permitKey = form1.permitKey.value;
		var permitNo = form1.permitNo.value;
		var monitorsdateR = form1.monitorsdateR.value;
		var monitoredateR = form1.monitoredateR.value;	
		var params = 'width=980,height=640,resizable=1,menubar=no,scrollbars=yes';
		closeReturnWindow();
		window.open(getVirtualPath() + "tcbw/medin/medin2001q.jsp?isPop=Y&q_medPermit="+permitKey+"&q_medPermitNumber="+permitNo+"&q_notifierRepDateS="+monitorsdateR+"&q_notifierRepDateE="+monitoredateR+"&q_eventKind=2"+"&q_caseSource=in",'popWinE',params);	
		whatButtonFireEvent("queryCannel");
		
	}
	else
	{
		alert("請選擇一筆藥證資料!!");
	}
}

</script>
</head>
<body  onLoad="whatButtonFireEvent('<%=obj0301.getState()%>');init();showErrorMsg('<%=obj0301.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:300px;height:100px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<jsp:include page="pmed0101js.jsp" />
	
</div>

<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
    <input type="hidden" name="id" value="<%=obj0301.getId()%>">	
    <input type="hidden" name="id2" value="<%=obj0301.getId2()%>">
    <input type="hidden" name="state" value="<%=obj0301.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj0301.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	<input type="hidden" name="changeTabValue" value="<%=obj0301.getChangeTabValue()%>">
	<input type="hidden" name="tabId" value="<%=obj0301.getTabId()%>">
	<input type="hidden" name="isNotHand" value="<%=obj0301.getIsNotHand()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="isUpload" value="<%=obj0301.getIsUpload()%>">
	<input class="field_RO" type="hidden" name="handstatus1" value="<%=obj0301.getHandstatus()%>">
	
	

	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span> 
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanDoAssessComplete">
		<input class="toolbar_default" type="button" followPK="false" name="assessComplete" value="評估完成" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanAddocuments">
	   <input class="toolbar_default" type="button" followPK="false" name="addocuments" value="補　件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanMaintain4">
		<input class="toolbar_default" type="button" followPK="false" name="maintain4" value="檢視國內不良反應通報資料" onClick="toShowMed01();">&nbsp;
	</span>
	<span id="spanMaintain5">
		<input class="toolbar_default" type="button" followPK="false" name="maintain5" value="檢視國內不良品通報資料" onClick="toShowMed02();">&nbsp;
	</span>

</td>
</tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">監視案件</a></td>
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">定期報告</a></td>
	    <%if(!"01".equals(obj0301.getHandstatus())) { %>
	    <td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">定期報告評估</a></td>								
	    <%} %>		
   </TR>
</TABLE>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
<jsp:include page="pmed0102f.jsp" />
<jsp:include page="pmed0202f.jsp" />
<%if(!"01".equals(obj0301.getHandstatus())) { %>
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
	   case "queryAll":
		    break;
	   case "assessComplete":
	   case "reupdateComplete":
	   case "pauseSave":
			form1.state.value = buttonName;
			checkField();
			break;
	   case "update":
	    	setAllReadonly();
	    	if("03" == form1.handstatus.value)
		   	{
	    		setFormItem("pauseSave,reupdateComplete,doUpload5","O");
			    $("#Tab3").find('.field').attr('readOnly',false);
			    $("#Tab3").find('.field').attr('disabled',false);
			    $("#Tab3").find('.field_btnAdd').attr('disabled',false);
		        $("#Tab3").find('.field_btnRemove').attr('disabled',false);
		    }
	    	else
		    {
	    		setFormItem("pauseSave,assessComplete,addocuments,doUpload5","O");
			    $("#Tab4").find('.field').attr('readOnly',false);
			    $("#Tab4").find('.field').attr('disabled',false);
			    $("#Tab4").find('.field_btnAdd').attr('disabled',false);
		        $("#Tab4").find('.field_btnRemove').attr('disabled',false);
		    }
	    	
		    break;
	   case "addocuments":
		   if(confirm("確定補件?"))
			  {
			    setAllReadonly();
			    if("01" == form1.reporttype1.value)
				{
				    getEmail('MED050004','window.opener.addocuments','Y');
				}
			    else
				{
			    	getEmail('MED050005','window.opener.addocuments','Y');
				}
			  }
		   break;
	  	case "doDoQuit":
		  	if(confirm("確定回到查詢頁面?"))
		  	{
			  	if(form1.caseType.value == "03")
				{
				  	form1.action = "pmed0301q.jsp?caseType=03";
				}
			  	else
				{
			  		form1.action = "pmed0301q.jsp?caseType=02";
				} 	
			   	form1.state.value = "queryAll";
			   	form1.submit();
		  	}
		  	break;
		   
	}
}
</script>
</html>
