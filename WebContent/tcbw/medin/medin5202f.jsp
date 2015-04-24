<!--
程式目的：醫療器材臨床試驗不良事件通報登錄作業
程式代號：medex5102f
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN5202F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objQry5201" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN5201F">
	<jsp:setProperty name="objQry5201" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

String statusType = Common.get(request.getParameter("statusType"));



if("pauseSave".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("修改完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("accepted".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doAccepted();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("受理完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("backPieces".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doBackPieces();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("退件完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("dismissal".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doDismissal();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("撤案完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("correction".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdateType();
		obj.doCorrection();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("校正完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("addocuments".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doAddocuments();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("通知補件完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("toClear".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doDeleteCon0003Db();
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.medin.MEDIN5202F)obj.queryOne();
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
var popWinName;
function checkField()
{

	var alertStr="";
	
	if(alertStr.length!=0){ alert(alertStr); return false;}


	
	if(form1.state.value=="addocuments")
	{
		getEmail('MED030003','window.opener.addocuments','N');
		return false;
	}

	beforeSubmit();
	form1.submit();
	return true;
}

function beforeInit()
{
	<%=obj.getMed2002DbItemSet()%>
	<%=obj.getMed2003DbItemSet()%>
	<%=obj.getMed2004DbItemSet()%>
	<%=obj.getMed2005DbItemSet()%>
}

function init() 
{
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanConfirm,spanListPrint,spanListHidden','H');

    setFormItem("pauseSave,accepted,backPieces,dismissal,correction,addocuments,doUpload","R");

    var tbID=document.all.item("changeTabValue").value;

    if(tbID=="")
    {    
      changeTab(1);
    }
    else
    {
	  changeTab(tbID);
    }
	
	if('<%=obj.getErrorMsg()%>'=='受理完成' || '<%=obj.getErrorMsg()%>'=='退件完成' 
		|| '<%=obj.getErrorMsg()%>'=='撤案完成' || '<%=obj.getErrorMsg()%>'=='校正完成'
			|| '<%=obj.getErrorMsg()%>'=='通知補件完成' )
	{	
	    form1.action = "medin5201f.jsp?statusType="+<%=statusType%>;
	    form1.state.value = "queryAll";
	    form1.submit();
	    //重新整理左邊樹狀
	    window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	}
}

function getEmail(mailID,isJS,isAdd)
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1200,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	
	var url="isAdd="+isAdd;
	    url+="&applNo="+form1.applNo.value;
	    url+="&isJS="+isJS;
	    url+="&Kind=3";
	    url+="&mailID="+mailID;
	    url+="&id="+form1.id.value;
	    //url+="&manyMailID=MED030002,MED030003";
	    
	returnWindow=window.open(getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?"+url,"",prop);
}


function backPieces()
{
   form1.state.value="backPieces";
   beforeSubmit();
   form1.submit();
   return true;
}

function dismissal()
{
   form1.state.value="dismissal";
   beforeSubmit();
   form1.submit();
   return true;
}

function addocuments()
{
   form1.state.value="addocuments";
   beforeSubmit();
   form1.submit();
   return true;
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
	form1.action = "medin5201f.jsp?statusType="+'<%=statusType%>'+"&caseType="+'<%=obj.getCaseType()%>';
	form1.state.value = "queryAll";
	form1.submit();
}


function getReassignment()
{
    if('<%=obj.competence()%>'!='Y')
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
	  var q="?id="+form1.id.value+"&q_formCode=MED03&q_code=01";
	  returnWindow=window.open(getVirtualPath() + "tcbw/medin/medin5203f.jsp"+q,"",prop);
    }
}



function getUserUpdate(sysTemType,formCode,id)
{

	var hql = "select  id,workers  from Med2001Db where id='"+id+"'";
	
	var x = getRemoteData('../../ajax/jsonObjects.jsp',hql);

	if(x!=null&&x.length>0)
	{
		var json = JSON.parse(x);
		
		if(json.obj1!="" && json.obj1!="<%=User.getUserId()%>")
		{
			alert("此案件已分派給其他作業人員!");
			setFormItem("pauseSave,accepted,backPieces,dismissal,correction,addocuments,reassignment,doUpload","R");	
			setFormItem("update","O");	
			form1.state.value="init";
			return false;
		}	
		
	}
	
	
	var urlUserUpdate = '../../ajax/jsonCon0003Db.jsp';
	
	var qUserUpdate = "?sysTemType="+sysTemType+"&formCode="+formCode+"&id="+id;
	    qUserUpdate +="&notifier="+form1.notifierName.value;

	var xUserUpdate = getRemoteData(urlUserUpdate+qUserUpdate,"");
	
	if (xUserUpdate!=null && xUserUpdate.length>0) 
	{
		var jsonUserUpdate = eval ('(' + xUserUpdate + ')');
		setFormItem("pauseSave,accepted,backPieces,dismissal,correction,addocuments,reassignment,doUpload","R");	
		setFormItem("update","O");	
		alert("此案件目前已有其他作業人員編輯中!");
		form1.state.value="init";
		return false;	
	}
		
	
}

function upload()
{
	 var prop='';
	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();

     var url="../../home/popManyUploadSimple.jsp?";
         url+="fileKind=MED";
         url+="&systemType=MED030001";
         url+="&uploadId="+form1.id.value;
         url+="&dbName=Med2001Db";
         
	 returnWindow = window.open(url,'上傳檔案',prop);
}

//列印報表
function report(isClose)
{
	form1.isCloseUserInfo.value=isClose;
	form1.target = "_blank";
	form1.action = "medin5202p.jsp";
	beforeSubmit();
	form1.submit();
	form1.target = "_self";
	form1.action = "medin5202f.jsp"
}

//彈出式視窗尋問是否遮蔽個資
function toAsk()
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;

	//點選不遮蔽個資時記錄相關LOG
	var q = "code=MED03";
	q +="&methodName=print";
	q +="&db=Med2001Db";
	q +="&hql=select id,applNo,inOrOutcreator,notifierName from Med2001Db where id = " + form1.id.value;
	
	prop=prop+"width=800,height=100,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/conin/conin1001f.jsp?" + q,"",prop);
	
}
//案件原始版本
function popMed5001(id){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popMed5001.jsp?med2001Id="+id,"",prop);
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
	<jsp:include page="medin5101js.jsp" />
	<jsp:include page="medin5101q.jsp" />
	<% request.setAttribute("qBean", objQry5201); %>
	<jsp:include page="medin5201q.jsp" />
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="inOrOut" value="<%=User.getInORout()%>">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj.getIsCloseUserInfo()%>"> <!-- 列印時是否遮蔽個資 -->
	<input type="hidden" name="isAlreadyAutoSave">
	<input type="hidden" name="changeTabValue" value="<%=obj.getChangeTabValue()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span> 
    <%if("10".equals(obj.getStatusType()) || "20".equals(obj.getStatusType())){%>
    <span id="spanAccepted">
	   <input class="toolbar_default" type="button" followPK="false" name="accepted" value="受　理" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if("10".equals(obj.getStatusType())){%>
    <span id="spanBackPieces">
	   <input class="toolbar_default" type="button" followPK="false" name="backPieces" value="退　件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if("10".equals(obj.getStatusType())){%>
    <span id="spanDismissal">
	   <input class="toolbar_default" type="button" followPK="false" name="dismissal" value="撤　案" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if("30".equals(obj.getStatusType())){%>
    <span id="spanAddocuments">
	   <input class="toolbar_default" type="button" followPK="false" name="addocuments" value="補　件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
     <%}%>
    <%if("30".equals(obj.getStatusType())||("40".equals(obj.getStatusType()) )){%>
    <span id="spanCorrection">
	   <input class="toolbar_default" type="button" followPK="false" name="correction" value="校正完成" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <span id="spanReassignment">
	   <input class="toolbar_default" type="button" followPK="false" name="reassignment" value="案件改派" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    
    <input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
    <span id="spanDoGetMLMS">
		<input class="toolbar_default" type="button" followPK="false" name="doGetMLMS" value="檢視許可證資料" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	
	<input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doAddDoc" value="案件補件紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doMailList" value="郵件清單紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doFlow" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">	
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
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">其他附件</a></td>			
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
	<jsp:include page="medin5101f.jsp" />
    </div>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)	
	{
	  case "update":
		  setFormItem("pauseSave,accepted,backPieces,dismissal,correction,addocuments,doUpload","O");
		  getUserUpdate("MED","MED03",form1.id.value);
		  break;
	  case "clear":
		  toClear();
		  break;
	  case "pauseSave":
		  checkField();
		  break;
	  case "accepted":
		  if(confirm("確定案件受理?"))
		  {
		   form1.state.value = buttonName;
		   checkField();
		  }
		  break;
	  case "backPieces":
		  if(confirm("確定退件?"))
		  {
		    getEmail('MED030002','window.opener.backPieces');
		  }
		  break;
	  case "dismissal":
		  if(confirm("確定撤案?"))
		  {
		    getEmail('MED030002','window.opener.dismissal');
		  }
		  break;
	  case "correction":
		  if(confirm("確定校正完成?"))
		  {
			setAllReadonly();
		    form1.state.value = buttonName;
		    checkField();
		  }
		  break;
	  //檢視許可證資料
	  case "doGetMLMS":
		  toShowMLMS();
		  break;
	  //補件 - 寄發補件通知
	  case "addocuments":
		  if(confirm("確定補件?"))
		  {
		    setAllReadonly();
		    getEmail('MED030003','window.opener.addocuments','Y');
		  }

		  break;
	  case "reassignment":
		  if(confirm("確定案件改派?"))
		  {
		    getReassignment();
		  }
		  break;
	  case "doDoQuit":
		  if(confirm("確定回到查詢頁面?"))
		  {
		    form1.action = "medin5201f.jsp?statusType="+'<%=statusType%>';
		    form1.state.value = "queryAll";
		    form1.submit();
		  }
		  break;
	  case "doReportView":
		  	toAsk();
			break;
	  //案件原始版本
	  case "doOriginalVer":
			popMed5001('<%=obj.getId()%>');
			break;
	  //案件補件記錄
	  case "doAddDoc":
			popCon0004('<%=obj.getApplNo()%>');
			break;
	  //郵件清單記錄
	  case "doMailList":
			popCon0002('MED03','<%=obj.getId()%>');
			break;
	  //案件流程記錄			
	  case "doFlow":
			popCon2001('MED03','<%=obj.getId()%>');
			break;
	}
}

</script>
</body>
</html>
