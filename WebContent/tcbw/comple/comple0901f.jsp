<!--
程式目的：食品非預期反應通報補登作業
程式代號：comple0901f
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COMPLE0901" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.comple.COMPLE0901F">
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
else if("deleteHfr0006Db".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doDeleteHfr0006Db();
		obj.setErrorMsg("修改完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.comple.COMPLE0901F) obj.doQueryOne();
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
<script type="text/javascript" src="../hfrin/hfrin0400f.js"></script>
<script type="text/javascript">
var popWinName;
$(function()
{	
		$('#Tab5 :input').bind('click change',function(){
			if(form1.state.value=="update"){			
				form1.hfr05Update.value="Y";
			}
		});
		$('#Tab7 :input').bind('click change',function(){
			if(form1.state.value=="update"){			
				form1.hfr06Update.value="Y";
			}
		});
		$('#Tab8 :input').bind('click change',function(){
			if(form1.state.value=="update"){			
				form1.hfr07Update.value="Y";
			}
		});		
});

function checkField()
{
	var alertStr = "";
	if(form1.state.value=="stayedUpload" || form1.state.value=="doSend"){
		//alertStr += checkDate(form1.occurDate, "發生未預期反應日期");		
	}
	if(alertStr.length!=0){ alert(alertStr); return }

	beforeSubmit();
	form1.submit();	
}

function init() 
{
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden','H');
	setFormItem("createHfr0006Db,updateHfr0006Db,deleteHfr0006Db,doCommittee","R");
		
	<%if("1".equals(obj.getHfrType())){
		//簡表
		out.write(obj.getGFoodSBuilder());
		out.write(obj.getNFoodSBuilder());
		out.write(obj.getFoodFileSBuilder());
		out.write(obj.getHFRFOFileRowSBuilder());
		out.write(obj.getHFRFMFileRowSBuilder());
		out.write(obj.getHFRFIFileRowSBuilder());
		out.write(obj.getHFRREFileRowSBuilder());
	}else{
		//一般表
		out.write(obj.getDiscriptSBuilder());
		out.write(obj.getDoctorSBuilder());
		out.write(obj.getGFoodSBuilder());
		out.write(obj.getNFoodSBuilder());
		out.write(obj.getDrugSBuilder());
		out.write(obj.getODrugSBuilder());
		out.write(obj.getFoodFileSBuilder());
		out.write(obj.getHFRFOFileRowSBuilder());
		out.write(obj.getHFRFMFileRowSBuilder());
		out.write(obj.getHFRFIFileRowSBuilder());
		out.write(obj.getHFRREFileRowSBuilder());
	}%>
	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);
	}else{
		changeTab(1);
	}
}


function changeTab(tabId) {
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";
	document.getElementById("t4").className = "tab_border2";
	document.getElementById("t5").className = "tab_border2";
	document.getElementById("t7").className = "tab_border2";
	document.getElementById("t8").className = "tab_border2";
	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab4").className = "";
	document.getElementById("aTab5").className = "";
	document.getElementById("aTab7").className = "";
	document.getElementById("aTab8").className = "";
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';
	document.getElementById("Tab4").style.display = 'none';
	document.getElementById("Tab5").style.display = 'none';
	<%
	if("Y".equals(obj.getIsShowPreAssessmentPage()) && "1".equals(obj.getHfrType())){
	%>
	document.getElementById("Tab6").style.display = 'none';
	<%}%>
	document.getElementById("Tab7").style.display = 'none';
	document.getElementById("Tab8").style.display = 'none';
	
	if(tabId == 2){
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";
		<%
		if("Y".equals(obj.getIsShowPreAssessmentPage()) && "1".equals(obj.getHfrType())){
		%>
		document.getElementById("Tab6").style.display = '';
		<%}%>
		form1.tabId.value="2";
	}else if(tabId == 3){
		document.getElementById("t3").className = "tab_border1";
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";
		form1.tabId.value="3";
	}else if(tabId == 4){
		document.getElementById("t4").className = "tab_border1";
		document.getElementById("Tab4").style.display = '';
		document.getElementById("aTab4").className = "text_w";
		form1.tabId.value="4";
	}else if(tabId == 5){
		document.getElementById("t5").className = "tab_border1";
		document.getElementById("Tab5").style.display = '';
		document.getElementById("aTab5").className = "text_w";
		form1.tabId.value="5";
	}else if(tabId == 7){
		document.getElementById("t7").className = "tab_border1";
		document.getElementById("Tab7").style.display = '';
		document.getElementById("aTab7").className = "text_w";
		form1.tabId.value="7";
	}else if(tabId == 8){
		document.getElementById("t8").className = "tab_border1";
		document.getElementById("Tab8").style.display = '';
		document.getElementById("aTab8").className = "text_w";
		form1.tabId.value="8";
	}else{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";
		form1.tabId.value="1";
	}
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="text" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">	
	<input type="hidden" name="userID" value="<%=obj.getUserID()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
	<input type="text" name="hfr05Update" >
	<input type="text" name="hfr06Update" >
	<input type="text" name="hfr07Update" >
	<span id="spanPageUpdate">
		<input class="toolbar_default" type="button" followPK="false" name="pageUpdate" value="修　改" onClick="whatButtonFireEvent(this.name)">
	</span>   
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>    
    <span id="spanDoCreateHfr0006Db">
    	<input class="toolbar_default" type="button" followPK="false" name="createHfr0006Db" value="新 增 預 評 資 料" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanDoUpdateHfr0006Db">
    	<input class="toolbar_default" type="button" followPK="false" name="updateHfr0006Db" value="修 改 預 評 資 料" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span> 
    <span id="spanDoDeleteHfr0006Db">
    	<input class="toolbar_default" type="button" followPK="false" name="deleteHfr0006Db" value="刪 除 預 評 資 料" onClick="whatButtonFireEvent(this.name)">&nbsp;
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
<tr>
	<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	<td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">調查紀錄</a></td>		
	<td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">可疑食品列表</a></td>
	<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">相關附件</a></td>
	<td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">初步評估</a></td>
	<td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">委員評估意見單</a></td>
	<td nowrap ID="t8" CLASS="tab_border2"><a id="aTab8" href="#" onClick="changeTab(8);">評估委員會決議表</a></td>
</tr>	
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
<div id="formContainer" style="display:none;">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="comple0901_2q.jsp" />
</div>
<div id="formInput" style="height:auto">
        <% request.setAttribute("qBean", obj); %>
		<% if("1".equals(obj.getHfrType())){%>
		<jsp:include page="comple0902_1f.jsp" />
		<% }else{%>
		<jsp:include page="comple0902_2f.jsp" />
		<% }%>		
		<jsp:include page="../hfrin/hfrin0603f.jsp">
			<jsp:param value="Tab5" name="tabId"/>
		</jsp:include>		
		<jsp:include page="../hfrin/hfrin0703f.jsp">
			<jsp:param value="Tab7" name="tabId"/>
		</jsp:include>		
		<jsp:include page="../hfrin/hfrin0803f.jsp">
			<jsp:param value="Tab8" name="tabId"/>
		</jsp:include>
</div>	
</td></tr>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "pageUpdate":		  
		  if (isObj(form1.editID)) form1.editID.value = "<%=User.getUserId()%>";
		  if (isObj(form1.editDate)) form1.editDate.value = getToday();			
		  setUnpkOpen();
		  btnFollowPK();
		  btnCC();
		  if(form1.hfr0006Id.value == ""){
				setFormItem("createHfr0006Db","O");
				setFormItem("updateHfr0006Db,deleteHfr0006Db","R");
		  }else{
				setFormItem("createHfr0006Db","R");
				setFormItem("updateHfr0006Db,deleteHfr0006Db","O");
		  }
		  setFormItem("pageUpdate","R");
		  closeHfr0006DbFiled();
		  //setFormItem("createHfr0006Db","O");	  		 			
		  form1.state.value = "update";			
		  break;
	  case "createHfr0006Db":
		  clearHfr0006Db();
		  setFormItem("doCommittee","O");
		  setFormItem("createHfr0006Db,updateHfr0006Db","R");
		  $("#Tab7 [class*=field]").each(function(){				
			  if($(this).attr("type") == "text" || $(this).attr("type") == "textarea"){					
				  $(this).attr("readonly", false);				
			  }else{					
				  $(this).attr("disabled", false);				
			  }				
			  if($(this).attr("class") == "field_RO"){					
				  $(this).attr("disabled", true);				
			  }			
		  });
		  form1.state.value = "update";		  
		  break;
	  case "updateHfr0006Db":
		  if(buttonName == "updateHfr0006Db"){			
			  setFormItem("updateHfr0006Db","R");			
			  setFormItem("createHfr0006Db,doCommittee","O");			  
		  }else{				
			  setFormItem("updateHfr0006Db","R");			
		  }			
		  $("#Tab7 [class*=field]").each(function(){				
			  if($(this).attr("type") == "text" || $(this).attr("type") == "textarea"){					
				  $(this).attr("readonly", false);				
			  }else{					
				  $(this).attr("disabled", false);				
			  }				
			  if($(this).attr("class") == "field_RO"){					
				  $(this).attr("disabled", true);				
			  }			
		  });
		  form1.state.value = "update";
		  break;
	  case "deleteHfr0006Db":
		  form1.state.value = "deleteHfr0006Db";
		  form1.submit();
		  break;
	  case "doDoQuit":
		  form1.action = "comple0901q.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	}
}
</script>
</body>
</html>
