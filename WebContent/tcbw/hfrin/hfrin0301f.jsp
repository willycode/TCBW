<!--
程式目的：委員會期資料維護作業
程式代號：hfrin0301f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFRIN0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0301F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0301F)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
	if ("insertSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");
		obj.setQ_id(obj.getId());
	}
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
	if ("updateSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");
	}
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
	if("deleteError".equals(obj.getState())){
		obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0301F)obj.queryOne();
	}
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
function checkField(){
	var alertStr = "";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
		if(form1.q_meetingSeason.value != "")
			alertStr += checkEmpty(form1.q_meetingYear, "年度");
		else{
			form1.q_meetingYear.style.backgroundColor = "";
		}
		alertStr += checkDate(form1.q_meetingDateS, "召開起始日期");
		alertStr += checkDate(form1.q_meetingDateE, "召開結束日期");
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.meetingYear, "年度");
		alertStr += checkEmpty(form1.meetingSeason, "期別");
		alertStr += checkEmpty(form1.meetingDate, "召開日期");
		if(form1.meetingDate.value != "")
			alertStr += checkDate(form1.meetingDate, "召開日期");
		alertStr += checkEmpty(form1.meetingStimeH, "召開起始時間(時)");
		alertStr += checkEmpty(form1.meetingStimeM, "召開起始時間(分)");
		alertStr += checkEmpty(form1.meetingEtimeH, "召開結束時間(時)");
		alertStr += checkEmpty(form1.meetingEtimeM, "召開結束時間(分)");
		/*
		if((form1.meetingStimeH.value > form1.meetingEtimeH.value) || 
				((form1.meetingStimeH.value == form1.meetingEtimeH.value) && (form1.meetingStimeM.value > form1.meetingEtimeM.value))){
			alertStr += "請輸入正確召開時間";
			form1.meetingStimeH.style.backgroundColor = errorbg;
			form1.meetingStimeE.style.backgroundColor = errorbg;
		}else{
			form1.meetingStimeH.style.backgroundColor = "";
			form1.meetingEtimeH.style.backgroundColor = "";
		}
		*/
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function queryOne(id){
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

function init() {

}

function checkURL(surl)
{
	var alertStr = "";
	if (form1.state.value=="insert" || form1.state.value=="insertError" || 
			form1.state.value=="update" || form1.state.value=="updateError") 
	{
		alert("新增或修改狀態無法更換頁標籤，請先點選取消!");
	}
	else
	{
		if(form1.id.value==null || form1.id.value == "")
		{
			alertStr += ("請先執行查詢或新增!");
		}
		
		if(alertStr.length!=0)
		{
			alert("請先執行查詢, 若已查到資料則請選取其中一筆資料");
			return false;
		} 
		else 
		{
			form1.state.value="queryAll";
		}
		
		form1.action = surl;
		beforeSubmit();
		form1.submit();
	}
}

function popHfrin0304() {
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=220,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/hfrin/hfrin0304f.jsp","",prop);
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:500px;height:150px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" border="1">
	<tr>
		<td nowrap class="queryTDLable">評估委員會會期：</td>
		<td nowrap class="queryTDInput">
			年度<input class="field_Q" type="text" name="q_meetingYear" size="3" maxlength="3" value="<%=obj.getQ_meetingYear()%>">
			- 期別<input class="field_Q" type="text" name="q_meetingSeason" size="2" maxlength="2" value="<%=obj.getQ_meetingSeason()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">召開日期：</td>
		<td nowrap class="queryTDInput">
			<%=View.getPopCalendar("field_Q", "q_meetingDateS", obj.getQ_meetingDateS())%>
			~ <%=View.getPopCalendar("field_Q", "q_meetingDateE", obj.getQ_meetingDateE())%>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">召開時間：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" name=q_meetingStime type="select">
				<%=View.getHourOption(obj.getQ_meetingStime())%>
			</select> ~
			<select class="field_Q" name=q_meetingEtime type="select">
				<%=View.getHourOption(obj.getQ_meetingEtime())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
			<input type="hidden" name="q_id" value="<%=obj.getQ_id()%>">
			<input type="hidden" name="q_isQuery">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" onClick="form1.q_isQuery.value='Y'">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>

<table cellpadding="0" cellspacing="0" valign="top">
	<tr>
		<td nowrap id="t1" class="tab_border1"  width="100" height="25">委員會期資料維護作業</td>
		<td nowrap id="t1" class="tab_border2" width="100"><a href="#" onClick="return checkURL('hfrin0302f.jsp');">評估委員維護作業</a></td>
		<td nowrap id="t1" class="tab_border2" width="100"><a href="#" onClick="return checkURL('hfrin0303f.jsp');">評估案件維護作業</a></td>			
	</tr>
	<tr>
		<td nowrap class="tab_line1"></td>
		<td nowrap class="tab_line2"></td>	
	</tr>
</table>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer">
	<table class="table_form" width="99%" height="100%">
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>評估委員會會期：</td>
		<td nowrap class="td_form_white" colspan="3">
			年度<input class="field" type="text" name="meetingYear" size="3" maxlength="3" value="<%=obj.getMeetingYear()%>">
			- 期別<input class="field" type="text" name="meetingSeason" size="2" maxlength="2" value="<%=obj.getMeetingSeason()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>召開日期：</td>
		<td nowrap class="td_form_white"  colspan="3">
			<%=View.getPopCalendar("field", "meetingDate", obj.getMeetingDate())%>
		</td>	
	</tr>	
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>召開時間：</td>
		<td nowrap class="td_form_white" colspan="3">
			<select class="field" name=meetingStimeH type="select">
				<%=View.getHourOption(obj.getMeetingStimeH())%>
			</select>:
			<select class="field" name=meetingStimeM type="select">
				<%=View.getMinuteOption(obj.getMeetingStimeM())%>
			</select> ~
			<select class="field" name=meetingEtimeH type="select">
				<%=View.getHourOption(obj.getMeetingEtimeH())%>
			</select>:
			<select class="field" name=meetingEtimeM type="select">
				<%=View.getMinuteOption(obj.getMeetingEtimeM())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">上傳會議記錄：</td>
		<td nowrap class="td_form_white"  colspan="3">
			<%=View.getPopUpload("field","meetingFile",obj.getMeetingFile(),false,"HFRIN0301F")%>
		</td>	
	</tr>		
	<tr>
		<td nowrap class="td_form">異動資訊：</td>
		<td nowrap class="td_form_white" colspan="3">
			[<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">
			/<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">] 
		</td>		
	</tr>	
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="Excel匯入" onClick="popHfrin0304();">&nbsp;
</td></tr>

<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">評估委員會會期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">召開日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">召開時間</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false};
	boolean displayArray[] = {false, true, true, true};
	String[] alignArray = {"center", "center", "center", "center"};
	out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>