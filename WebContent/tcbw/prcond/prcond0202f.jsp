<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>

<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.prcond.PROCOND0202F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
String q_projid = Common.get(request.getParameter("q_projid"));
String isPop = Common.get(request.getParameter("isPop"));
String statePop = Common.get(request.getParameter("statePop"));
String urlName = "PROCOND0201";
if(!"".equals(q_projid)) {
	obj.setId(q_projid);
}
if("PRCOND0301Q".equals(statePop)) {
	urlName = "PRCOND0301Q";
}

if ("queryAll".equals(obj.getState())) 
{
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
else if ("queryOne".equals(obj.getState())) 
{
	obj = (com.kangdainfo.tcbw.view.prcond.PROCOND0202F)obj.queryOne();	
}
else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) 
{
	obj.setHttpRequest(pageContext.getRequest());
	obj.insert();
	if ("insertSuccess".equals(obj.getState())) 
	{
		obj = (com.kangdainfo.tcbw.view.prcond.PROCOND0202F)obj.queryOne();
		obj.setQueryAllFlag("true");
	}
}
else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) 
{
	obj.setHttpRequest(pageContext.getRequest());
	obj.update();
	if ("updateSuccess".equals(obj.getState())) 
	{
		obj = (com.kangdainfo.tcbw.view.prcond.PROCOND0202F)obj.queryOne();
		obj.setQueryAllFlag("true");
    }
}
else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) 
{
	obj.delete();
	if("deleteError".equals(obj.getState()))
	{
		obj = (com.kangdainfo.tcbw.view.prcond.PROCOND0202F)obj.queryOne();
	}
}

if ("true".equals(obj.getQueryAllFlag()) || "PRCOND0301Q".equals(statePop))
{
	objList = (java.util.ArrayList) obj.queryAll();
	obj.setStateQueryOneSuccess();
}


%>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value='<%=urlName%>' />
</jsp:include>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/json.js"></script>

<script type="text/javascript">

var insertDefault = new Array
(
	new Array("isclose","N")
);

var popWinName = null;

function init() 
{
	if("Y" == '<%=isPop%>') {
		setDisplayItem("spanQueryAll,spanInsert,spanDelete,spanUpdate,spanListHidden,spanConfirm,spanClear","H");
		
	} else {
		setDisplayItem("spanQueryAll","H");
	}
	
}

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
		alertStr += checkEmpty(form1.docdate,"文件日期");
		alertStr += checkEmpty(form1.docsubject,"文件主旨");
		alertStr += checkEmpty(form1.docsummary,"文件摘要");
		alertStr += checkEmpty(form1.isclose,"是否結案");
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
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
		if(surl=="prcond0201f.jsp") 
		{
			form1.state.value="queryOne";
		}
		else 
		{
			form1.state.value="queryAll";
		}
	
		form1.action = surl;
		
		form1.action = surl;
		beforeSubmit();
		form1.submit();
	}
}

function queryOne(id)
{
	form1.t_id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

//檔案上傳
function upload(type)
{
	 var prop='';
	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();

   var url="../../home/popManyUploadSimple.jsp?";
       url+="fileKind=PROCOND";
       url+="&systemType="+type;
       url+="&uploadId="+form1.t_id.value;
       url+="&dbName=Con3002Db";
       
	 returnWindow = window.open(url,'上傳檔案',prop);
}

var win = null;
function openCenterWindow(wid, hei, url)
{
	var prop = '';
	var w = (screen.width-wid)/2;
	var h = (screen.height-hei)/2;
	prop = prop + 'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes';
	prop = prop + ',width=' + wid;
	prop = prop + ',height=' + hei;
	prop = prop + ',left=' + w;
	prop = prop + ',top=' + h;
	if(win != null){
		win.close();
	}
	win = window.open(url,'',prop);
}

function beforeInit()
{
	<%=obj.getCFileRowSBuilder()%>
}

</script>
</head>
<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">

<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">

<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:250px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<% request.setAttribute("qBean",obj); %>
	<jsp:include page="prcond0201q.jsp" />
</div>

<table CELLPADDING=0 CELLSPACING=0>
	<tr>
		<% if(!"PRCOND0301Q".equals(urlName)) { %>
		<td nowrap id="t1" class="tab_border2"  width="100" height="25"><a href="#" onClick="return checkURL('prcond0201f.jsp');">專案資料</a></td>
		<% }%>
		<td nowrap id="t2" class="tab_border1" width="100">專案文件</td>		
	</tr>
</table>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap width="25%" class="td_form"><font color="red">*</font>文件日期</td>
		<td class="td_form_white">
			<%=View.getPopCalendar("field","docdate",obj.getDocdate())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>文件主旨：</td>
		<td class="td_form_white">
			<input class="field" type="text" size="50" maxlength="50" name="docsubject" value="<%=obj.getDocsubject()%>" />
		</td>
	</tr> 
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>文件摘要：</td>
		<td nowrap class="td_form_white" >
			<textarea class="field" rows="3" cols="50" name="docsummary"><%=obj.getDocsummary()%></textarea>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>是否結案：</td>
		<td nowrap class="td_form_white" >
		    <select class="field" name="isclose">
				<%=View.getYNOption(obj.getIsclose())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">附件</td>
		<td nowrap class="td_form_white">  
	        <jsp:include page="prcond0203f.jsp">
				<jsp:param value="prcond" name="fileType"/>
			</jsp:include>
		 </td>
	</tr>
	</table>
	</div>
</td>
</tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="t_id" value="<%=obj.getT_id()%>">
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
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">文件日期</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">文件主旨</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">是否結案</a></th>
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
</td>
</tr>
</table>
</form>
</body>
</html>

