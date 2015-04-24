<!--
程式目的：
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0601F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

String statusType = Common.get(request.getParameter("statusType"));

String caseType = Common.get(request.getParameter("caseType"));
obj.setCaseType(caseType);

String competence="Y";

if("pauseSave".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
		obj.setErrorMsg("暫存成功");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.medin.MEDIN0601F)obj.queryOne();
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="./../../js/jquery.js"></script>
<script type="text/javascript">

var insertDefault = new Array();

function checkField()
{
	//validateAddrTable();
	beforeSubmit();
	form1.submit();
	return true;
}

function beforeInit()
{
	<%=obj.getMed0002DbItemSet()%>
	<%=obj.getMed0003DbItemSet()%>
	<%=obj.getMed0004DbItemSet()%>
	<%=obj.getMed0005DbItemSet()%>
}

function init() 
{
	setDisplayItem('update,clear,spanInsert,spanQueryAll,spanDelete,spanConfirm,spanListPrint,spanListHidden','H');
	if(tbID=="")
    {    
      changeTab(1);
    }
    else
    {
	  changeTab(tbID);
    }
}



function toClear()
{
   form1.state.value="toClear";
   beforeSubmit();
   form1.submit();
   return true;
}

function reassignment()
{
	form1.action = "medin0601f.jsp?statusType="+'<%=statusType%>'+"&caseType="+'<%=obj.getCaseType()%>';
	form1.state.value = "queryAll";
	form1.submit();
}

function getReassignment()
{
    if('<%=competence%>'!='Y')
    {
       alert("您無改派權限!");
    }
    else
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
	  var q="?id="+form1.id.value+"&q_formCode=MED02&q_code=01";
	  returnWindow=window.open(getVirtualPath() + "tcbw/medin/medin0203f.jsp"+q,"",prop);
    }
}



</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');beforeInit();init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="checkField();" autocomplete="off">

<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="medin0101q.jsp" />
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="medin0601q.jsp" />
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="inOrOut" value="<%=User.getInORout()%>">
	<input type="hidden" name="isAlreadyAutoSave">
	<input type="hidden" name="changeTabValue" value="<%=obj.getChangeTabValue()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>

    <span id="spanReassignment">
	   <input class="toolbar_default" type="button" followPK="false" name="reassignment" value="案件改派" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    
     <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>

</tr>

<tr>
<td nowrap>
  <% request.setAttribute("QueryBean",obj);%>
  <jsp:include page="../../home/page_row.jsp" />
</td>
</tr>
</table>


<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
	<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	<td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良事件</a></td>		
	<td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">不良反應</a></td>
	<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">不良品</a></td>
	<td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">轉送評估</a></td>
	<td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">不良品評估初評</a></td>
	<td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">廠商回覆</a></td>
	<td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">不良品評估複評</a></td>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
    <jsp:include page="medin0101js.jsp" />
	<jsp:include page="medin0101f.jsp" />
	<jsp:include page="medin0303f.jsp" />
	<jsp:include page="medin0703f.jsp" />
	<jsp:include page="../medex/medex0303f.jsp" />
    <jsp:include page="medin0803f.jsp" />
    </div>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)	
	{
	  //案件改派
	  case "reassignment":
		  if(confirm("確定案件改派?"))
		  {
		    getReassignment();
		  }
		  break;
	  case "doDoQuit":
		  if(confirm("確定回到查詢頁面?"))
		  {
		   form1.action = "medin0601f.jsp?statusType="+'<%=statusType%>'+"&caseType="+'<%=obj.getCaseType()%>';
		   form1.state.value = "queryAll";
		   form1.submit();
		  }
		  break;
	}
}

</script>
</body>
</html>
