<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medex.MEDEX0107F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
request.setCharacterEncoding("UTF-8");

String medPermit = Common.get(request.getParameter("medPermit"));
String medPermitNumber = Common.get(request.getParameter("medPermitNumber"));

String keyId = Common.get(request.getParameter("keyId"));


if ("queryAll".equals(obj.getState())) 
{
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
if ("true".equals(obj.getQueryAllFlag()))
{
	objList = (java.util.ArrayList) obj.queryAll();
}
else
{    
    //if(medPermit!="")
    //{ 
    //	obj.setQ_permitKey(medPermit);
    //}
    //else
    //{
    	//obj.setQ_permitKey("05"); //先預設一筆
    //}
    
    //if(medPermitNumber!="") obj.setQ_permitNo(medPermitNumber);
    
    if(keyId!="") obj.setQ_keyId(keyId);
    
    objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/cbToggle.js"></script>
<script type="text/javascript" src="../../js/toolbar.js"></script>
<script type="text/javascript">
function checkField(type)
{
	if(type == "confirm")
	{
		if (opener!=null) 
		{
			if (form1.id.value == "") 
			{
				alert("請先點選一筆資料!\n");
			}
			else
			{
				if(null != form1.q_keyId.value && "" != form1.q_keyId.value)
				{
					opener.setPermitData(form1.id.value, form1.q_keyId.value , form1.rowCount.value);
				}
				else
				{
					//如果是像list方式選擇此程式，則呼叫opener.permitDataOption
					if(form1.rowCount.value != "" && !isNaN(form1.rowCount.value))
						opener.permitDataOption(form1.id.value, form1.q_keyId.value , form1.rowCount.value);
					else
						opener.permitData2(form1.id.value, form1.q_keyId.value , form1.rowCount.value);
				}
				window.close();
			}
		}
	}
	else
	{
		form1.state.value = type;
		var alertStr = "";
		//alertStr += checkEmpty(form1.q_permitKey,"許可證字");
		//alertStr += checkQuery();
		if(alertStr.length!=0){ alert(alertStr); return false; }
		form1.submit();
		return true;
	}
}

function queryOne(id) 
{
	resetTableColor();
	form1.id.value = id;
	document.all.item("listContainerActiveRowId").value = id;
	document.all.item("listContainerRow"+id).className = "activeRow";
	document.all.item("listContainerRow"+id).onmouseover="activeRow";
	document.all.item("listContainerRow"+id).onmouseout="activeRow";
}

function resetTableColor()
{
	var tbl = document.all.item("tbl");
	var lastRow = tbl.rows.length;
	for(var i=0;i<lastRow;i++)
	{
		var trClass = "listTROdd";
		if(i%2==0)
		{
			trClass = "listTREven";
		}
		tbl.rows[i].setAttribute("className",trClass);
	}
}

function init(){
	
}

</script>
</head>
<body onload="init();">
<form id="form1" name="form1" method="post" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0" style="width:auto;height:auto;">
	<tr>
		<td nowrap class="queryTDLable">許可證字號：</td>
		<td nowrap class="queryTDInput">
			<select name="q_permitKey" class="field_Q">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='MEDPKID' order by codeId", obj.getQ_permitKey())%>
			</select>			 
			號：<input class="field_Q" type="text" name="q_permitNo" size="6" maxlength="6" value="<%=obj.getQ_permitNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">中文品名：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_chProduct" size="100" maxlength="100" value="<%=obj.getQ_chProduct()%>">
	    </td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">英文品名：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_enProduct" size="100" maxlength="100" value="<%=obj.getQ_enProduct()%>">
	    </td>
	</tr>	
</table>	

<table width="100%" cellspacing="0" cellpadding="0">
<tr>
<td class="bg" align="left">
<span id="spanConfirm">
<input type="hidden" name="state" value="<%=obj.getState()%>">
<input type="hidden" name="id" value="<%=obj.getId()%>">
<input type="hidden" id="listContainerActiveRowId">
<input type="hidden" name="rowCount" value="<%=obj.getRowCount()%>">
<input class="field_Q" type="hidden" name="q_keyId" value="<%=obj.getQ_keyId()%>">

<input class="toolbar_default" type="button" followPK="false" name="queryAll" value="查　詢" onClick="checkField(this.name);">&nbsp;
<input class="toolbar_default" type="button" followPK="false" name="confirm" value="確　定" onClick="checkField(this.name);">&nbsp;
</span>
</td>
</tr>
<tr><td>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>
<tr><td class="bg">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="tbl">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">英文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">有效日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">申請商名稱</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">製造廠名稱</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">製造廠國別</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true,false,false,false,false,false,false,false};
	boolean displayArray[] = {false,true,true,true,true,true,true,true};
	out.write(View.getQuerylist(primaryArray,displayArray,objList,"true"));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>