<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.prcond.PROCOND0302F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

obj.setUserID(User.getUserId());
String type=request.getParameter("type");

obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();
%>
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
function checkField()
{	
	return true;
}

function queryOne(id,name)
{
	opener.setObjectValue("q_projid",id);
	opener.setObjectValue("q_projName",name);
	window.close();
}

function popCon1005(type)
{
	var jscript = "";		
	var prop="";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=650,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	if(type=='1')
	{	
	   returnWindow=window.open(getVirtualPath() + "home/popCon1005.jsp","",prop);
	}
	else if(type=='2')
	{
		form1.applicationId.value="";
		form1.applicationName.value="";
	}
}

</script>
</head>
<body>
<form id="form1" name="form1" method="post" onSubmit="return checkField()" >
<table width="100%" cellspacing="0" cellpadding="0">

<tr>
<td class="bg" >
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">	
	<tr>
	<td nowrap width="15%" class="queryTDLable">專案名稱：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" size="30" name="q_projName" value="<%=obj.getQ_projName()%>" />
		</td>
	</tr> 
	<%if("med".equals(type)) {%>
	<tr>
		<td nowrap class="queryTDLable">許可證字號：</td>
		<td nowrap class="queryTDInput" >
			<select name="q_permitKey" class="field_Q">
			  <%=View.getOptionCodeKind("MEDPKID",obj.getQ_permitKey())%>
			</select>
			字第<input class="field_Q" type="text" name="q_permitNo" size="10"  value="<%=obj.getQ_permitNo()%>">號
		</td>
	</tr>
	<% } else if("drg".equals(type)){ %>
	<tr>
		<td nowrap class="queryTDLable">許可證字號：</td>
		<td nowrap class="queryTDInput" >
			<select name="q_permitKey" class="field_Q">
			  <%=View.getOptionCodeKind("DRGPKID",obj.getQ_permitKey())%>
			</select>
			字第<input class="field_Q" type="text" name="q_permitNo" size="10"  value="<%=obj.getQ_permitNo()%>">號
		</td>
	</tr>
	<% }%>
	<tr>
		<td nowrap class="queryTDLable">中文品名：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" size="30" name="q_chProduct" value="<%=obj.getQ_chProduct()%>" />
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">許可證持有商：</td>
		<td nowrap class="queryTDInput">
		    <input class="field_RO" type="text" size="10" name="applicationId" value="<%=obj.getApplicationId()%>" />
		    <input class="field_RO" type="text" size="40" maxlength="50" name="applicationName" value="<%=obj.getApplicationName()%>" />
		    <input name="btn_application" class="field" type="button" value="廠商資料" onclick="popCon1005('1');">
		    <input name="btn_applicationClear" class="field" type="button" value="清除廠商資料" onclick="popCon1005('2');">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">醫材次類別：</td>
		<td nowrap class="queryTDInput">
		   <%=View.getPopCode("field_Q","q_medSecCategoryCode","",Common.get(View.getOneCodeName("MEDSCT",Common.get(obj.getQ_medSecCategory()))),"","MEDSCT","q_medSecCategory",obj.getQ_medSecCategory())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">是否結案：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" name="q_isclose">
			  <%=View.getYNOption(obj.getQ_isclose())%>
			</select>
		</td>
	</tr>	
	</table>
	</div>
</td>
</tr>

<!--Toolbar區============================================================-->

<tr><td class="bg" style="text-align:center">
	<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
</td></tr>
<!--List區============================================================-->
<tr>
<td nowrap class="bg" >
<input type="hidden" name="type" value="<%=obj.getType()%>">
<input type="hidden" name="userID" value="<%=User.getUserId()%>">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">專案類別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">專案名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">許可證持有商</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">醫材次類別</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
		boolean primaryArray[] = {true, true,false,false,false,false,false};
		boolean displayArray[] = {false, true, true, true, true, true, true};
		out.write(View.getQuerylist(primaryArray, displayArray,null, objList, obj.getQueryAllFlag(),true,null,null));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
</body>
</html>
