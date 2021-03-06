<!--
程式目的：廠商資料設定作業
程式代號：conse0007f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONSE0007" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conse.CONSE0007F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.conse.CONSE0007F)obj.queryOne();	
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
		obj = (com.kangdainfo.tcbw.view.conse.CONSE0007F)obj.queryOne();
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
		alertStr += checkEmpty(form1.name, "公司名稱");
		alertStr += checkEmpty(form1.county, "公司地址縣市");
		alertStr += checkEmpty(form1.zipcode, "公司地址區域");
		alertStr += checkEmpty(form1.address, "公司地址");
		alertStr += checkEmpty(form1.compegno, "統一編號");
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

function setValue(place,zipcode)
{
	var obj1 = zipcode;
	var oldDeptValue = zipcode.value;
	obj1.options.length=0;
	obj1.options.add(new Option("請選擇",""));	

	var x = getRemoteData(getVirtualPath() + "/ajax/jsonZipCode.jsp?city="+place.value,"");

	if (x!=null && x.length>0) 
	{
		var json = eval('(' + x + ')'); 
		var i = 0;
		for (i=0; i<json.length; i++) 
		{
			if(json[i].zipcode==null)
				continue;
			var astId =  json[i].zipcode;			
			var oOption = new Option(json[i].zipname,astId);
			obj1.options.add(oOption);
	    	if(astId == oldDeptValue) oOption.selected=true;			
		}
		obj1.disabled = false;
	}
}

function checkURL(surl){
	columnTrim(form1.id);
	if(form1.id.value==""){
		alert("請先執行查詢或新增!");
	}else if( (form1.state.value=="insert")||(form1.state.value=="update")){
		alert("新增或修改狀態無法更換頁標籤，請先點選取消!");
	}else{
		form1.action=surl;
		form1.state.value = "queryAll";
		beforeSubmit();
		form1.submit();
	}
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:800px;height:150px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" border="1">
	<tr>
		<td nowrap class="queryTDLable">廠商名稱：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_name" size="40" maxlength="50" value="<%=obj.getQ_name()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">公司地址：</td>
		<td nowrap class="queryTDInput">
		    <select name="q_county" class="field_Q" onChange="setValue(this,q_zipcode);">
			     <%=View.getOptionCodeKind("CTY", obj.getQ_county())%>
			</select>	
			<select name="q_zipcode" class="field_Q">
				<%=View.getOptionCon1002(obj.getQ_zipcode())%>
			</select>
			<input class="field_Q" type="text" name="q_address" size="50" maxlength="50" value="<%=obj.getQ_address()%>">		
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
		<td nowrap id="t1" class="tab_border1"  width="100" height="25">廠商基本資料</td>
		<td nowrap id="t1" class="tab_border2" width="100"><a href="#" onClick="return checkURL('conse0007_1f.jsp');">廠商人員</a></td>		
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
		<td nowrap class="td_form" width="35%"><font color="red">*</font>廠商名稱：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="name" size="50" maxlength="50" value="<%=obj.getName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%">統一編號：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="compegno" size="20" maxlength="10" value="<%=obj.getCompegno()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>公司地址：</td>
		<td nowrap class="td_form_white" width="65%">
		    <select name="county" class="field" onChange="setValue(this,zipcode);">
			     <%=View.getOptionCodeKind("CTY", obj.getCounty())%>
			</select>	
			<select name="zipcode" class="field">
				<%=View.getOptionCon1002(obj.getZipcode())%>
			</select>
			<input class="field" type="text" name="address" size="50" maxlength="50" value="<%=obj.getAddress()%>">				
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form">公司電話：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="text" name="telarea" size="2" maxlength="2" value="<%=obj.getTelarea()%>">
	  		- <input class="field" type="text" name="tel" size="10" maxlength="10" value="<%=obj.getTel()%>">
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form">公司傳真：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="text" name="faxarea" size="2" maxlength="2" value="<%=obj.getFaxarea()%>">
	  		- <input class="field" type="text" name="fax" size="10" maxlength="10" value="<%=obj.getFax()%>">
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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">公司名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">統一編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">公司地址</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">公司電話</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">公司傳真</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center", "center"};
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