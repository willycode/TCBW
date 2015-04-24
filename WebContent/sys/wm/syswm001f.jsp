<%--
程式目的：最新消息維護
程式代號：syswm001f
程式日期：0960726
程式作者：clive.chang
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SYSWM001" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.wm.SYSWM001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.common.view.sys.wm.SYSWM001F)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
	if ("insertSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");		
		obj.setQ_id(obj.getId());
	}
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
	//if ("updateSuccess".equals(obj.getState())) obj.setQ_id(obj.getId());	
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
var insertDefault;	//二維陣列, 新增時, 設定預設值
insertDefault = new Array(
	new Array("masterId", "<%=obj.getMasterId().equals("")?"1":obj.getMasterId()%>"),
	new Array("startDate", getToday()),
	new Array("endDate", "<%=Datetime.getDateAdd("m",1,Datetime.getYYYMMDD())%>")
);

function checkField(){
	var alertStr="";
	if(form1.state.value=="queryAll"){
		alertStr += checkQuery();
		alertStr += checkDate(form1.q_startDate,"查詢上檔日期起");
		alertStr += checkDate(form1.q_startDateE,"查詢上檔日期迄");
		alertStr += checkDateSE(form1.q_startDate,form1.q_startDateE,"查詢上檔日期");
		alertStr += checkDate(form1.q_endDate,"查詢下檔日期起");
		alertStr += checkDate(form1.q_endDateE,"查詢下檔日期迄");
		alertStr += checkDateSE(form1.q_endDate,form1.q_endDateE,"查詢下檔日期");
		
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.masterId,"訊息類別");	
		alertStr += checkEmpty(form1.subject,"主旨");
		alertStr += checkEmpty(form1.content,"內容");		
		alertStr += checkLen(form1.content,"內容",1000);
		alertStr += checkDate(form1.startDate,"上檔日期");
		alertStr += checkDate(form1.endDate,"下檔日期");
		alertStr += checkDateSE(form1.startDate, form1.endDate, "上下檔日期");		
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
}
function queryOne(id){
	form1.id.value=id;
	form1.state.value="queryOne";
	beforeSubmit();
	form1.submit();
}

function init() {
	for (var i=1; i<11; i++) {
		if (parse(document.all.item("itemPicture"+i).value).length>0) setFormItem("btn_itemPicture"+i+"Download","O");
	}
}
</script>
</head>

<body topmargin="0" onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">

<!--Query區============================================================-->
<div id="queryContainer" style="width:400px;height:250px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable"  border="1">
	<tr>
		<td nowrap class="queryTDLable">訊息類別：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" type="select" name="q_masterId">
				<%=View.getOption("select id, codeName from CommonCode where commonCodeKind.codeKindId='MSG' order by codeId", obj.getQ_masterId()) %>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">主旨：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_subject" size="25" maxlength="50" value="<%=obj.getQ_subject()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">內容：</td>
		<td nowrap class="queryTDInput">
			<input type="text" class="field_Q" name="q_content" size="25" value="<%=obj.getQ_content()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">上檔日期：</td>
		<td nowrap class="queryTDInput">
			<%=View.getPopCalendar("field_Q","q_startDate",obj.getQ_startDate())%>~
			<%=View.getPopCalendar("field_Q","q_startDateE",obj.getQ_startDateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">下檔日期：</td>
		<td nowrap class="queryTDInput">
			<%=View.getPopCalendar("field_Q","q_endDate",obj.getQ_endDate())%>~
			<%=View.getPopCalendar("field_Q","q_endDateE",obj.getQ_endDateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">強制讀取：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="checkbox" name="q_isOpen" value="Y" <%=obj.getQ_isOpen().equals("Y")?"checked":""%> >
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
			<input type="hidden" name="q_id" value="<%=obj.getQ_id()%>" class="field_Q">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定">
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
	  <td nowrap class="td_form"><font color="red">*</font>主旨：</td>
	  <td nowrap colspan="3" class="td_form_white">
			<input class="field" type="text" name="subject" size="70" maxlength="250" value="<%=obj.getSubject()%>">		</td>
	</tr>
	<tr>
	  <td nowrap class="td_form"><font color="red">*</font>訊息類別：</td>
	  <td nowrap class="td_form_white">
			<select class="field" type="select" name="masterId">
				<%=View.getOption("select id, codeName from CommonCode where commonCodeKind.codeKindId='MSG' order by codeId", obj.getMasterId()) %>
			</select>
	  </td>
	  <td nowrap class="td_form">強制讀取：</td>
	  <td nowrap class="td_form_white">
			<input class="field" type="checkbox" name="isOpen" value="Y" <%=obj.getIsOpen().equals("Y")?"checked":""%> >		</td>
	</tr>
	<tr>
	  <td nowrap class="td_form">上檔日期：</td>
	  <td nowrap class="td_form_white">
			<%=View.getPopCalendar("field","startDate",obj.getStartDate())%>		</td>
	  <td nowrap class="td_form">下檔日期：</td>
	  <td nowrap class="td_form_white">
			<%=View.getPopCalendar("field","endDate",obj.getEndDate())%>		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>內容：</td>
		<td nowrap colspan="3" class="td_form_white">
			<textarea class="field" name="content" cols="60" rows="6"><%=obj.getContent()%></textarea>		</td>
		</tr>
	<tr>
      <td nowrap class="td_form">附件1：</td>
      <td nowrap colspan="3" class="td_form_white"><%=View.getPopUpload("field","itemPicture1",obj.getItemPicture1())%></td>
	</tr>
	<tr>
      <td nowrap class="td_form">附件2：</td>
      <td nowrap colspan="3" class="td_form_white"><%=View.getPopUpload("field","itemPicture2",obj.getItemPicture2())%></td>
	</tr>
	<tr>
      <td nowrap class="td_form">附件3：</td>
      <td nowrap colspan="3" class="td_form_white"><%=View.getPopUpload("field","itemPicture3",obj.getItemPicture3())%></td>
	</tr>
	<tr>
      <td nowrap class="td_form">附件4：</td>
      <td nowrap colspan="3" class="td_form_white"><%=View.getPopUpload("field","itemPicture4",obj.getItemPicture4())%></td>
	</tr>
	<tr>
      <td nowrap class="td_form">附件5：</td>
      <td nowrap colspan="3" class="td_form_white"><%=View.getPopUpload("field","itemPicture5",obj.getItemPicture5())%></td>
	</tr>
	<tr>
      <td nowrap class="td_form">附件6：</td>
      <td nowrap colspan="3" class="td_form_white"><%=View.getPopUpload("field","itemPicture6",obj.getItemPicture6())%></td>
	</tr>
	<tr>
      <td nowrap class="td_form">附件7：</td>
      <td nowrap colspan="3" class="td_form_white"><%=View.getPopUpload("field","itemPicture7",obj.getItemPicture7())%></td>
	</tr>
	<tr>
      <td nowrap class="td_form">附件8：</td>
      <td nowrap colspan="3" class="td_form_white"><%=View.getPopUpload("field","itemPicture8",obj.getItemPicture8())%></td>
	</tr>
	<tr>
      <td nowrap class="td_form">附件9：</td>
      <td nowrap colspan="3" class="td_form_white"><%=View.getPopUpload("field","itemPicture9",obj.getItemPicture9())%></td>
	</tr>
	<tr>
      <td nowrap class="td_form">附件10：</td>
      <td nowrap colspan="3" class="td_form_white"><%=View.getPopUpload("field","itemPicture10",obj.getItemPicture10())%></td>
	</tr>
	<tr>
		<td nowrap class="td_form">異動人員/日期：</td>
		<td nowrap class="td_form_white">
			[<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">/
			<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">]		</td>
		<td nowrap class="td_form">&nbsp;</td>
		<td nowrap class="td_form_white">&nbsp;</td>
	</tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<input type="hidden" name="filestoreLocation" value="<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("filestoreLocation"))%>">
			<input type="hidden" name="id" value="<%=obj.getId()%>">

	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>

<tr><td nowrap>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>
<!--List區============================================================-->
<tr><td nowrap class="bg">
<div id="listContainer">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >序號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">訊息類別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">主旨</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">上檔日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">下檔日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">強制讀取</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {false,true,false,false,false,false};
	boolean displayArray[] = {true,false,true,true,true,true};
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
