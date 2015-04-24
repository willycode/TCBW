<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.kangdainfo.common.util.*" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conex.CONEX0001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
request.setCharacterEncoding("UTF-8");
String q = Common.get(request.getParameter("q"));
String v = Common.get(request.getParameter("v"));

if(v!="") obj.setType(v);

if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
else{
    if(q!=""&&"1".equals(obj.getIsFirst())) obj.setQ_engageKind(q);
    objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="js/default.css" type="text/css"/>
<script type="text/javascript" src="js/json.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript" src="js/toolbar.js"></script>
<script type="text/javascript">
function checkField(type){
	form1.state.value = type;
	var alertStr = "";
    alertStr += checkQuery();
	if(alertStr.length!=0){ alert(alertStr); return false; }
	form1.submit();
	return true;
}

function queryOne(id, userJobName, userJobType, address, city, zip)
{
	if (isObj(opener.document.all.item("userJob")))
		opener.document.all.item("userJob").value=id;
	if (isObj(opener.document.all.item("userJobName")))
		opener.document.all.item("userJobName").value=userJobName;
	<%if("03".equals(obj.getType())){%>
	if (isObj(opener.document.all.item("userJobType")))
		opener.document.all.item("userJobType").value=userJobType;
    <%}%>

	if (isObj(opener.document.all.item("notifierDeptID")))
		opener.document.all.item("notifierDeptID").value=id;
	if (isObj(opener.document.all.item("notifierDept")))
		opener.document.all.item("notifierDept").value=userJobName;

	if (isObj(opener.document.all.item("userCounty")))
		opener.document.all.item("userCounty").value=city;
	if (isObj(opener.document.all.item("userZipCode")))
		opener.document.all.item("userZipCode").value=zip;
	if (isObj(opener.document.all.item("userAddr")))
		opener.document.all.item("userAddr").value=address;

    //藥品不良品、化粧品
	if (isObj(opener.document.all.item("q_notifierDept")))
		opener.document.all.item("q_notifierDept").value=userJobName;
	//藥品不良品、化粧品
	if (isObj(opener.document.all.item("q_notifierDeptHis")))
		opener.document.all.item("q_notifierDeptHis").value=userJobName;

	if (isObj(opener.document.all.item("notifierDept")))
		opener.document.all.item("notifierDept").value=id;
	if (isObj(opener.document.all.item("notifierDeptName")))
		opener.document.all.item("notifierDeptName").value=userJobName;
	
	window.close();
}

function resetTableColor(){
	var tbl = document.all.item("tbl");
	var lastRow = tbl.rows.length;
	for(var i=0;i<lastRow;i++){
		var trClass = "listTROdd";
		if(i%2==0){
			trClass = "listTREven";
		}
		tbl.rows[i].setAttribute("className",trClass);
	}
}

function init(){
	
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
</script>
</head>
<body onload="init();">
<form id="form1" name="form1" method="post" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
    <%if("02".equals(obj.getType())){%>
	<tr>
		<td nowrap class="td_form">廠商統編：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_compegno" size="12" maxlength="12" value="<%=obj.getQ_compegno()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">廠商名稱：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_name" size="50" maxlength="50" value="<%=obj.getQ_name()%>">		
		</td>
	</tr>
	<%}%>    
    <%if("03".equals(obj.getType())){%>
	<tr>
		<td nowrap class="td_form" width="35%">特約類別：</td>
		<td nowrap class="td_form_white" width="65%">
			<select class="field" name="q_engageKind">
				<%=View.getOptionCodeKind("MEDENG", obj.getQ_engageKind())%>
			</select>				
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">醫事機構代碼：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_medAgencyCode" size="12" maxlength="12" value="<%=obj.getQ_medAgencyCode()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">醫事機構名稱：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_medAgencyName" size="50" maxlength="50" value="<%=obj.getQ_medAgencyName()%>">		
		</td>
	</tr>
	<%}%>
	<%if("04".equals(obj.getType())){%>
	<tr>
		<td nowrap class="td_form">衛生單位名稱：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_unionName" size="50" maxlength="50" value="<%=obj.getQ_unionName()%>">		
		</td>
	</tr>
	<%}%>
	<tr>
		<td nowrap class="td_form">縣市別：</td>
		<td nowrap class="td_form_white">
		    <select name="q_county" class="field" onChange="setValue(this,q_zipCode);">
			     <%=View.getOptionCodeKind("CTY", obj.getQ_county())%>
			</select>	
			<select name="q_zipCode" class="field">
				<%=View.getOptionCon1002(obj.getQ_zipCode())%>
			</select>
		</td>
	</tr>
</table>	

<table width="100%" cellspacing="0" cellpadding="0">
<tr>
<td class="bg" align="left">
<span id="spanConfirm">
<input type="hidden" name="state" value="<%=obj.getState()%>">
<input type="hidden" name="type" value="<%=obj.getType()%>">
<input type="hidden" name="id" value="<%=obj.getId()%>">
<input type="hidden" name="isFirst" value="<%=obj.getIsFirst()%>">
<input type="hidden" id="listContainerActiveRowId">
	<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="查　　詢" onclick="form1.isFirst.value='1';">
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
</span>
</td>
</tr>
<tr><td>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="home/page.jsp" />
</td></tr>
<tr><td class="bg">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="tbl">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<%if("02".equals(obj.getType())){%>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">廠商名稱</a></th>
		<%}%>
		<%if("03".equals(obj.getType())){%>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">醫事機構名稱</a></th>	
		<%}%>
		<%if("04".equals(obj.getType())){%>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">衛生單位名稱</a></th>
		<%}%>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">縣市別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">地址</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%	
		boolean primaryArray[] = {true, true, true, false, true, true, true};	
		boolean displayArray[] = {false, true, false, true, true, false, false};	
		String[] alignArray = {"center", "center","center","center","center","center","center"};	
		out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag(),true,null,null,"",true,false));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>