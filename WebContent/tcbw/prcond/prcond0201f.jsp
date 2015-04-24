<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="PROCOND0201" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.prcond.PROCOND0201F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
if ("queryAll".equals(obj.getState())) 
{
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
else if ("queryOne".equals(obj.getState())) 
{
	obj = (com.kangdainfo.tcbw.view.prcond.PROCOND0201F)obj.queryOne();	
}

if ("true".equals(obj.getQueryAllFlag()))
{
	objList = (java.util.ArrayList) obj.queryAll();
}

%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript">


function checkField()
{
	var alertStr = "";
	
	if(form1.state.value == "queryAll")
	{
		alertStr += checkQuery();
	}
	else if(form1.state.value=="insert"||form1.state.value=="insertError"||
			form1.state.value=="update"||form1.state.value=="updateError")
	{

	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function queryOne(id)
{
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}


function init() 
{
	setDisplayItem("spanInsert,spanUpdate,spanDelete,spanConfirm","H");
	document.all.item("drg").style.display="none";
	changKind();
	q_changKind();
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

function q_changKind()
{
	
  var kind=form1.q_projType.value;

  if(kind=="drg")
  {
	  document.all.item("q_med").style.display="none";
	  document.all.item("q_drg").style.display="";
	  document.all.item("q_permitKeyMed").value="";
	  document.all.item("q_permitNoMed").value="";
  }
  else if(kind=="med")
  {
	  document.all.item("q_drg").style.display="none";
	  document.all.item("q_med").style.display="";
	  
	  document.all.item("q_permitKeyDrg").value="";
	  document.all.item("q_permitNoDrg").value="";
  }	  	  

}

function changKind()
{
  var kind=form1.projType.value;

  if(kind=="drg")
  {
	  document.all.item("med").style.display="none";
	  document.all.item("drg").style.display="";
  }
  else if(kind=="med")
  {
	  document.all.item("drg").style.display="none";
	  document.all.item("med").style.display="";
  }	  	  
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->

<div id="queryContainer" style="width:700px;height:250px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<% request.setAttribute("qBean",obj); %>
	<jsp:include page="prcond0201q.jsp" />
</div>

<table CELLPADDING=0 CELLSPACING=0>
	<tr>
		<td nowrap id="t1" class="tab_border1" width="100" height="25">專案資料</td>
		<td nowrap id="t1" class="tab_border2" width="100"><a href="#" onClick="return checkURL('prcond0202f.jsp');">專案文件</a></td>		
	</tr>
</table>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap width="25%" class="td_form">專案類別</td>
		<td class="td_form_white" width="85%">
			<select class="field_RO" name="projType" onChange="changKind();">
			    <%=View.getTextOption("drg;藥品;med;醫療器材" ,obj.getProjType(),0)%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">專案名稱</td>
		<td class="td_form_white">
			<input class="field_RO" type="text" size="50" name="projName" value="<%=obj.getProjName()%>" />
		</td>
	</tr> 
	
	<tr id="drg">
		<td nowrap class="td_form">許可證字：</td>
		<td nowrap class="td_form_white">
		    <select name="permitKeyDrg" class="field_RO">
				<%=View.getOptionCodeKind("DRGPKID",obj.getPermitKeyDrg())%>
			</select>
			字第<input class="field_RO" type="text" name="permitNoDrg" size="6" maxlength="6" value="<%=obj.getPermitNoDrg()%>">號
		</td>
	</tr>

	<tr id="med">
		<td nowrap class="td_form">許可證字：</td>
		<td nowrap class="td_form_white">
		    <select name="permitKeyMed" class="field_RO" onChange="getChProduct();">
				<%=View.getOptionCodeKind("MEDPKID",obj.getPermitKeyMed())%>
			</select>
			字第<input class="field_RO" type="text" onChange="getChProduct();" name="permitNoMed" size="6" maxlength="6" value="<%=obj.getPermitNoMed()%>">號
		</td>
	</tr>
	
	<tr>
		<td nowrap class="td_form">中文品名</td>
		<td nowrap class="td_form_white">
			<input class="field_RO" type="text" size="50" name="chProduct" value="<%=obj.getChProduct()%>" />
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">許可證持有商</td>
		<td nowrap class="td_form_white" >
			<input class="field_RO" type="text" size="10" name="applicationId" value="<%=obj.getApplicationId()%>" />
		    <input class="field_RO" type="text" size="40" name="applicationName" value="<%=obj.getApplicationName()%>" />
		</td>
	</tr>
	
	<tr>
		<td nowrap class="td_form">醫材次類別：</td>
		<td nowrap class="td_form_white">
		   <%=View.getPopCode("field_RO","medSecCategoryCode","",Common.get(View.getOneCodeName("MEDSCT",Common.get(obj.getMedSecCategory()))),"","MEDSCT","medSecCategory",obj.getMedSecCategory())%>
		</td>
	</tr>
	
	</table>
	</div>
</td>
</tr>

<!--Toolbar區============================================================-->
<tr>
<td nowrap class="bgToolbar" style="text-align:center">
    <input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td>
</tr>

<!--List區============================================================-->

<tr>
<td nowrap class="bgList">
<div id="listContainer">
<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">序號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">專案類別</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">專案名稱</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">許可證持有商</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">醫材次類別</a></th>
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
</td>
</tr>
</table>
</form>
</body>
</html>