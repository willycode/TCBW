<!--
程式目的：訊息傳送作業
程式代號：syswm003f
程式日期：0990316
程式作者：shark
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SYSWM003" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.wm.SYSWM003F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.common.view.sys.wm.SYSWM003F)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
	obj.setQueryAllFlag("true");
	obj.setQ_startDate(obj.getStartDate());
	obj.setQ_startDateE(obj.getStartDate());
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>

<html>
<head>
<%@ include file="../../home/meta.jsp" %>
<script type="text/javascript">
var insertDefault = new Array(
	new Array("startDate",getToday()),
	new Array("startTime",getNow()),
	new Array("msgSender","<%=User.getUserId()%>")
);							  

function checkField(){
	var alertStr="";
	if(form1.state.value=="queryAll"){
		alertStr += checkQuery();
		alertStr += checkDate(form1.q_startDate,"查詢視窗訊息發送日期起日");
		alertStr += checkDate(form1.q_startDateE,"查詢視窗訊息發送日期迄日");
		alertStr += checkDateSE(form1.q_startDate,form1.q_startDateE,"查詢視窗訊息發送日期");
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkDate(form1.startDate,"開始日期");
		alertStr += checkEmpty(form1.startDate,"開始日期");
		alertStr += checkEmpty(form1.startTime,"開始時間");
		alertStr += checkEmpty(form1.msgBody,"訊息內容");
		
		//alertStr += checkDate(form1.endDate,"結束日期");
		//alertStr += checkEmpty(form1.endDate,"結束日期");
		//alertStr += checkEmpty(form1.endTime,"結束時間");
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }

	var userTr = document.all.item("userTR");
	var deptTR = document.all.item("deptTR");
	var groupTR = document.all.item("groupTR");

	if(userTr.style.display!="none")
		form1.userIds.value = getSelectedList(document.all.item("userSelected"));
	else if(deptTR.style.display!="none")
		form1.deptIds.value = getSelectedList(document.all.item("deptSelected"));
	else if(groupTR.style.display!="none")
		form1.groupIds.value = getSelectedList(document.all.item("groupSelected"));
	else {
		form1.userIds.value = "";
		form1.deptIds.value = "";
		form1.groupIds.value = "";
	}
	
	beforeSubmit();
	return true;
}

function getSelectedList(obj){
	var result = "";
	for (var i=0; i<obj.options.length; i++){
		result += obj.options[i].value + ",";
	}
	return result.substring(0, result.length-1);
}

function setSelectedOption(obj, value){
	var list = new Array();
	list = value.split(",");
	for (var i=0; i<obj.options.length; i++){
		for(var j=0;j<list.length;j++){
			if(list[j] == obj.options[i].value){
				obj.options[i].selected = true;
			}
		}
	}
}

function queryOne(id){
	form1.id.value=id;
	form1.state.value="queryOne";
	beforeSubmit();
	form1.submit();
}

function init() {
	//displayDiv('N');
}

function displayDiv(moveOption){
	var obj = document.all.item("isOpen");
	setDisplayItem('spanDelete,userTR,deptTR,groupTR','H');

	if(obj.value == "1"){
		setSelectedOption(document.all.item("userSelect"),form1.userIds.value);
		if(moveOption=="Y"){
			moveOptions(document.all.item("userSelect"),document.all.item("userSelected"));
		}
		setDisplayItem('userTR','S');
	} else if(obj.value == "2"){
		setSelectedOption(document.all.item("deptSelect"),form1.deptIds.value);
		if(moveOption=="Y"){
			moveOptions(document.all.item("deptSelect"),document.all.item("deptSelected"));
		}
		setDisplayItem('deptTR','S');
	} else if(obj.value == "3"){
		setSelectedOption(document.all.item("groupSelect"),form1.groupIds.value);
		if(moveOption=="Y"){
			moveOptions(document.all.item("groupSelect"),document.all.item("groupSelected"));
		}
		setDisplayItem('groupTR','S');
	}
}

function moveOptions(from, to){
	ListToList(from,to,true,true);
}
</script>
</head>

<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');displayDiv('Y');showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">

<!--Query區============================================================-->
<div id="queryContainer" style="width:800px;height:400px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable"  border="1">
	<tr>
		<td nowrap class="queryTDLable" width="180px">訊息發送日期：</td>
		<td nowrap class="queryTDInput">
			<%=View.getPopCalendar("field_Q","q_startDate",obj.getQ_startDate())%>~<%=View.getPopCalendar("field_Q","q_startDateE",obj.getQ_startDateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">訊息單一接收者：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userId" size="20" maxlength="20" value="<%=obj.getQ_userId()%>">
		</td>
	</tr>	
	<tr>
		<td nowrap class="queryTDLable">訊息接收單位：</td>
		<td nowrap class="queryTDInput">
			<%=View.getPopOrgan("field_Q","q_deptId",obj.getQ_deptId(),obj.getQ_deptIdName(),"N","","","q_deptIdCodeId",obj.getQ_deptIdCodeId())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">訊息接收身份別：</td>
		<td nowrap class="queryTDInput">
			<%=View.getCheckBoxOption("field_Q","q_groupIds","select id, groupName from CommonGroup order by groupName", obj.getQ_groupIds())%>
		</td>
	</tr>	
	<tr>
		<td nowrap class="queryTDLable">訊息發送者：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_msgSender" size="20" maxlength="20" value="<%=obj.getQ_msgSender()%>">
		</td>
	</tr>	
	<tr>
		<td nowrap class="queryTDLable">訊息內容：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_msgBody" size="20" maxlength="20" value="<%=obj.getQ_msgBody()%>">
		</td>
	</tr>

	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">

			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form">訊息發送日期：</td>
		<td nowrap class="td_form_white">
		  <%=View.getPopCalendar("field_RO","startDate",obj.getStartDate())%>
		  <input class="field_RO" type="text" name="startTime" size="6" maxlength="6" value="<%=obj.getStartTime()%>">		  </td>
		</tr>
	<tr>
	  <td nowrap class="td_form">訊息發送者：</td>
	  <td nowrap class="td_form_white"><input class="field" type="text" name="msgSender" size="50" maxlength="50" value="<%=obj.getMsgSender()%>"></td>
	  </tr>
	 
	<tr>
	  <td nowrap class="td_form">發送條件：</td>
	  <td nowrap class="td_form_white">
	  	<select name="isOpen" onchange="displayDiv('N')">
	  		<%=View.getTextOption("Y;發送給全部;1;選擇接收者;2;選擇接收單位;3;選擇接收身份別",obj.getIsOpen() ,3) %>
	    </select>
	  </td>
	</tr>
		<tr id="userTR">
		  <td nowrap class="td_form">選擇接收者：</td>
		  <td nowrap class="td_form_white">
		  	<table>
		  		<tr>
		  		<td nowrap>
				    　 <select id="userSelect" size="5" multiple="multiple">
				    	<%=View.getOption("select id,userId||'-'||userName from CommonUser","" ,false,3) %>
				    </select>
		  		</td>
		  		<td nowrap valign="middle" align="center">
				    <input type="button" value="選取-&gt;" onclick="moveOptions(document.all.item('userSelect'),document.all.item('userSelected'))"><br/>
				    <input type="button" value="&lt;-移除" onclick="moveOptions(document.all.item('userSelected'),document.all.item('userSelect'))">
		  		</td>
		  		<td nowrap>
				    　 <select id="userSelected" size="5" multiple="multiple">
				    </select>
			    </td>
		  		</tr>
		  	</table>
		  </td>
		</tr>
		<tr id="deptTR">
		  <td nowrap class="td_form">訊息接收單位：</td>
		  <td nowrap class="td_form_white">
		  	<table>
		  		<tr>
		  		<td nowrap>
				    <select id="deptSelect" size="5" multiple="multiple">
				    <%=View.getOption("select id,department from CommonDepartment order by fullCode","",false,3) %>
				    </select>
		  		</td>
		  		<td nowrap valign="middle" align="center">
				    <input type="button" value="選取-&gt;" onclick="moveOptions(document.all.item('deptSelect'),document.all.item('deptSelected'))"><br/>
				    <input type="button" value="&lt;-移除" onclick="moveOptions(document.all.item('deptSelected'),document.all.item('deptSelect'))">
		  		</td>
		  		<td nowrap>
				    <select id="deptSelected" size="5" multiple="multiple">
				    </select>
			    </td>
		  		</tr>
		  	</table>
		    </td>
		</tr>
		<tr id="groupTR">
		  <td nowrap class="td_form">訊息接收身份別：</td>
		  <td nowrap class="td_form_white">
		  	<table>
		  		<tr>
		  		<td nowrap>
				    <select id="groupSelect" size="5" multiple="multiple">
				    <%=View.getOption("select id, groupName from CommonGroup order by groupName","",false,3) %>
				    </select>
		  		</td>
		  		<td nowrap valign="middle" align="center">
				    <input type="button" value="選取-&gt;" onclick="moveOptions(document.all.item('groupSelect'),document.all.item('groupSelected'))"><br/>
				    <input type="button" value="&lt;-移除" onclick="moveOptions(document.all.item('groupSelected'),document.all.item('groupSelect'))">
		  		</td>
		  		<td nowrap>
				    <select id="groupSelected" size="5" multiple="multiple">
				    </select>
			    </td>
		  		</tr>
		  	</table>
		  </td>
		</tr>
	<tr>
	  <td nowrap class="td_form">訊息內容：</td>
	  <td nowrap class="td_form_white"><textarea class="field" name="msgBody" cols="50" rows="4"><%=obj.getMsgBody()%></textarea></td>
		</tr>
	<tr>
	  <td nowrap class="td_form">異動人員/日期：</td>
	  <td nowrap class="td_form_white"> [
	    <input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">
	    /
	    <input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">
	    ] </td>
		</tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr>
	<td nowrap class="bgToolbar" style="text-align:center">
		<input type="hidden" name="id" value="<%=obj.getId()%>">
		<input type="hidden" name="userIds" value="<%=obj.getUserIds()%>">
		<input type="hidden" name="deptIds" value="<%=obj.getDeptIds()%>">
		<input type="hidden" name="groupIds" value="<%=obj.getGroupIds()%>">
		<input type="hidden" name="state" value="<%=obj.getState()%>">
		<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
		<input type="hidden" name="userID" value="<%=User.getId()%>">
		<jsp:include page="../../home/toolbar.jsp" />
	</td>
</tr>

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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">發送者</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">訊息內容</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">發送日期</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true,false,false,false};
	boolean displayArray[] = {false,true,true,true};
	String[] alignArray = {"center","left","left","center"};
	out.write(View.getQuerylist(primaryArray,displayArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
