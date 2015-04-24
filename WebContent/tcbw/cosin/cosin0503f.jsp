<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0503F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.doQueryAll();
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
function checkField(){
	var alertStr = "";
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function setValue(place,zipcode){
	var obj1 = zipcode;
	var oldDeptValue = zipcode.value;
	obj1.options.length=0;
	obj1.options.add(new Option("請選擇",""));	

	var x = getRemoteData(getVirtualPath() + "/ajax/jsonZipCode.jsp?city="+place.value,"");
	if(x!=null && x.length>0){
		var json = eval('(' + x + ')'); 
		var i = 0;
		for(i=0; i<json.length; i++){
			if(json[i].zipcode == null)
				continue;
			var astId =  json[i].zipcode;			
			var oOption = new Option(json[i].zipname,astId);
			obj1.options.add(oOption);
	    	if(astId == oldDeptValue) oOption.selected=true;			
		}
		obj1.disabled = false;
	}
}

function queryOne(id){
	if(isObj(opener.document.all.item("<%=obj.getField1()%>"))){		
		opener.document.all.item("<%=obj.getField1()%>").value = id;		
	}
	opener.openDeptMail();
	window.close();
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">

<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">	
	<tr>
		<td nowrap class="queryTDLable">單位名稱：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_unionName" size="40" maxlength="50" value="<%=obj.getQ_unionName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">地址：</td>
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
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="field1" value="<%=obj.getField1()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">

	<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
</td></tr>

<tr><td class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>
</table>

<table width="100%" cellspacing="0" cellpadding="0">
<!--List區============================================================-->
<tr><td class="bgList">
<div id="listContainer" style="hieght:auto;">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">單位名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">地址</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">電話</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">傳真</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center"};
	out.write(View.getQuerylist(primaryArray, displayArray, alignArray, objList, obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
