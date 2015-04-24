<!--
程式目的：醫事機構資料設定作業
程式代號：conse0009f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONSE0009" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conse.CONSE0009F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.conse.CONSE0009F)obj.queryOne();	
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
		obj = (com.kangdainfo.tcbw.view.conse.CONSE0009F)obj.queryOne();
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
	var isRadioShow = "";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.bnhi, "分局別");
		alertStr += checkEmpty(form1.medAgencyCode, "醫事機構代碼");
		alertStr += checkEmpty(form1.medAgencyName, "醫事機構名稱");
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
	returnWindow=window.open(getVirtualPath() + "tcbw/conse/conse0009_3f.jsp?","",prop);

}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:800px;height:150px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<% request.setAttribute("qBean",obj); %>
	<jsp:include page="conse0009q.jsp" />
	</table>
</div>

<table cellpadding="0" cellspacing="0" valign="top">
	<tr>
		<td nowrap id="t1" class="tab_border1"  width="100" height="25">醫事機構資料</td>
		<td nowrap id="t1" class="tab_border2" width="100"><a href="#" onClick="return checkURL('conse0009_1f.jsp');">醫事機構人員</a></td>
		<td nowrap id="t1" class="tab_border2" width="100"><a href="#" onClick="return checkURL('conse0009_2f.jsp');">醫事機構資料歷程</a></td>	
	</tr>
	<tr>
		<td nowrap class="tab_line1"></td>
		<td nowrap class="tab_line2"></td>
	</tr>
</table>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="99%" height="100%">
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>分局別：</td>
		<td nowrap class="td_form_white" width="65%">
			<select class="field" name="bnhi">
				<%=View.getOptionCodeKind("MEDDIV", obj.getBnhi())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>醫事機構代碼：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="medAgencyCode" size="12" maxlength="12" value="<%=obj.getMedAgencyCode()%>">				
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>醫事機構名稱：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="medAgencyName" size="50" maxlength="50" value="<%=obj.getMedAgencyName()%>">				
		</td>
	</tr>	
	<tr>
		<td nowrap class="td_form" width="35%">機構地址：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="agencyAddress" size="50" maxlength="100" value="<%=obj.getAgencyAddress()%>">				
		</td>
	</tr>	
	<tr>
	  	<td nowrap class="td_form">電話號碼：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="text" name="areaTel" size="2" maxlength="3" value="<%=obj.getAreaTel()%>">
	  		- <input class="field" type="text" name="tel" size="10" maxlength="10" value="<%=obj.getTel()%>">
	  	</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%">特約類別：</td>
		<td nowrap class="td_form_white" width="65%">
			<select class="field" name="engageKind">
				<%=View.getOptionCodeKind("MEDENG", obj.getEngageKind())%>
			</select>				
		</td>
	</tr>	
	<tr>
		<td nowrap class="td_form" width="35%">醫事機構種類：</td>
		<td nowrap class="td_form_white" width="65%">
			<select class="field" name="medAgencyKind">
				<%=View.getOptionCodeKind("MEDKIND", obj.getMedAgencyKind())%>
			</select>				
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form">終止合約或歇業日期：</td>
	  	<td nowrap class="td_form_white">
	  		<%=View.getPopCalendar("field", "endDate", obj.getEndDate())%>
	  	</td>
	</tr>	
	<tr>
		<td nowrap class="td_form">異動人員/日期：</td>
		<td nowrap class="td_form_white" colspan="3">
		  	[<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">/
		  	<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">]
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
	<span id="spanDoReUpdate">
       <input class="toolbar_default" type="button" followPK="false"  name="doImport" value="匯入資料" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
</td></tr>

<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer">
<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">分局別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">醫事機構代碼</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">醫事機構名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">電話號碼</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">特約類別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">醫事機構種類</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center", "center", "center"};
	out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)	
	{
	  case "doImport":
		  getReply();
		  break;
	}
}
</script>
</body>
</html>