<!--
程式目的：  審核中、處理中、補件中
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj0202" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0202F">
	<jsp:setProperty name="obj0202" property="*"/>
</jsp:useBean>
<jsp:useBean id="objQry0201" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0201F">
	<jsp:setProperty name="objQry0201" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

String statusType = Common.get(request.getParameter("statusType"));

String caseType = Common.get(request.getParameter("caseType"));
obj0202.setCaseType(caseType);

String competence="Y";

if("1".equals(obj0202.getCaseType()))
  competence=TCBWCommon.getCompetence("MED01","01",User.getUserId(),"4");
else
  competence=TCBWCommon.getCompetence("MED02","01",User.getUserId(),"4");


if("pauseSave".equals(obj0202.getState()))
{
	try
	{
		obj0202.setHttpRequest(pageContext.getRequest());	
		obj0202.doUpdateType();
		obj0202.setErrorMsg("暫存成功");
		obj0202.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj0202.setIsNeedBackQuery("N");
		obj0202.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("accepted".equals(obj0202.getState()))
{
	try
	{
		obj0202.setHttpRequest(pageContext.getRequest());
		obj0202.doUpdateType();
		obj0202.doAccepted();
		obj0202.doDeleteCon0003Db();
		obj0202.setErrorMsg("受理完成");
		obj0202.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj0202.setIsNeedBackQuery("N");
		obj0202.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("backPieces".equals(obj0202.getState()))
{
	try
	{
		obj0202.setHttpRequest(pageContext.getRequest());	
		obj0202.doBackPieces();
		obj0202.doDeleteCon0003Db();
		obj0202.setErrorMsg("退件完成");
		obj0202.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj0202.setIsNeedBackQuery("N");
		obj0202.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("dismissal".equals(obj0202.getState()))
{
	try
	{
		obj0202.setHttpRequest(pageContext.getRequest());	
		obj0202.doDismissal();
		obj0202.doDeleteCon0003Db();
		obj0202.setErrorMsg("撤案完成");
		obj0202.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj0202.setIsNeedBackQuery("N");
		obj0202.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("correction".equals(obj0202.getState()))
{
	try
	{
		obj0202.setHttpRequest(pageContext.getRequest());	
		obj0202.doUpdateType();
		obj0202.doCorrection();
		obj0202.doDeleteCon0003Db();
		obj0202.setErrorMsg("校正完成");
		obj0202.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj0202.setIsNeedBackQuery("N");
		obj0202.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("addocuments".equals(obj0202.getState()))
{
	try
	{
		obj0202.setHttpRequest(pageContext.getRequest());	
		obj0202.doAddocuments();
		obj0202.doDeleteCon0003Db();
		obj0202.setErrorMsg("通知補件完成");
		obj0202.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj0202.setIsNeedBackQuery("N");
		obj0202.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("toClear".equals(obj0202.getState()))
{
	try
	{
		obj0202.setHttpRequest(pageContext.getRequest());	
		obj0202.doDeleteCon0003Db();
		obj0202.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj0202.setIsNeedBackQuery("N");
		obj0202.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj0202 = (com.kangdainfo.tcbw.view.medin.MEDIN0202F)obj0202.queryOne();
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
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">

var insertDefault = new Array();

function checkField()
{
	var str="";
	
	if(form1.state.value == "accepted" || form1.state.value == "correction" )
	{
		str+=checkFieldCommon();
	}

	if(str!=0){ alert(str); return false; }
	
	beforeSubmit();
	form1.submit();
	return true;
}

function doBack()
{
	form1.action = "medin0201f.jsp?statusType="+'<%=statusType%>'+"&caseType="+'<%=caseType%>';
	form1.state.value = "queryAll";
	form1.submit();
}

function beforeInit()
{
	<%=obj0202.getMed0002DbItemSet()%>
	<%=obj0202.getMed0003DbItemSet()%>
	<%=obj0202.getMed0004DbItemSet()%>
	<%=obj0202.getMed0005DbItemSet()%>
}

function init() 
{

	//評估頁籤不能修改
	if(isObj(document.getElementById('Tab6')))
	$('#Tab6').find('* :input').attr('class','field_RO');
	if(isObj(document.getElementById('Tab7')))
	$('#Tab7').find('* :input').attr('class','field_RO');
	if(isObj(document.getElementById('Tab12')))
	$('#Tab12').find('* :input').attr('class','field_RO');
	if(isObj(document.getElementById('Tab1')))
	$('#Tab10').find('* :input').attr('class','field_RO');
	
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
	if('<%=obj0202.getErrorMsg()%>'=='受理完成' || '<%=obj0202.getErrorMsg()%>'=='退件完成' 
		|| '<%=obj0202.getErrorMsg()%>'=='撤案完成' || '<%=obj0202.getErrorMsg()%>'=='校正完成'
			|| '<%=obj0202.getErrorMsg()%>'=='通知補件完成' || '<%=obj0202.getErrorMsg()%>'=='強制結案完成' )
	{	
	    form1.action = "medin0201f.jsp?statusType="+'<%=statusType%>'+"&caseType="+'<%=obj0202.getCaseType()%>';
	    form1.state.value = "queryAll";
	    form1.submit();
	    //重新整理左邊樹狀
	    window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	}
}
    



function getEmail(mailID,isJS,isAdd,manyMailID)
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

	var url="";
	url+="?isAdd="+isAdd;
	url+="&applNo="+form1.applNo.value;
	url+="&isJS="+isJS;;
	url+="&mailID="+mailID;
	//url+="&systemType=MED";

	if(manyMailID!="N")
	  url+="&manyMailID="+manyMailID;

	url+="&id="+form1.id.value;
	url+="&applState="+'<%=statusType%>';

	if('<%=statusType%>'>='20')
	  url+="&kind="+'<%=caseType%>';
	
	returnWindow=window.open(getVirtualPath() + "tcbw/conbg/conbg0001f.jsp"+url,"",prop);
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

function reassignment()
{
	form1.action = "medin0201f.jsp?statusType="+'<%=statusType%>'+"&caseType="+'<%=obj0202.getCaseType()%>';
	form1.state.value = "queryAll";
	form1.submit();
}

function getUserUpdate(sysTemType,formCode,id)
{

	var hql = "select  id,workers  from Med0001Db where id='"+id+"'";
	
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
	
	var qUserUpdate = "&sysTemType="+sysTemType;
	    qUserUpdate +="&formCode="+formCode;
	    qUserUpdate +="&id="+id;
	    qUserUpdate +="&applNo="+form1.applNo.value;
	    qUserUpdate +="&stateus="+'<%=statusType%>';
	    qUserUpdate +="&notifier="+form1.notifierName.value;
	    
	var xUserUpdate = getRemoteData(urlUserUpdate,qUserUpdate);
	    
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

function popMed4001(id)
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popMed4001.jsp?med0001Id="+id,"",prop);
}

//列印報表
function report(isClose)
{
	form1.isCloseUserInfo.value=isClose;
	form1.target = "_blank";
	form1.action = "medin0202p.jsp";
	beforeSubmit();
	form1.submit();
	form1.target = "_self";
	form1.action = "medin0202f.jsp"
}

//彈出式視窗尋問是否遮蔽個資
function toAsk()
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;

	//點選不遮蔽個資時記錄相關LOG
	var q = "code=MED04";
	    q +="&methodName=print";
	    q +="&db=Med0001Db";
	    q +="&hql=select id,applNo,inOrOutcreator,notifierName from Med0001Db where id = " + form1.id.value;

	
	prop=prop+"width=800,height=100,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/conin/conin1001f.jsp?" + q,"",prop);
	
}



</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj0202.getState()%>');beforeInit();init();showErrorMsg('<%=obj0202.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="checkField();" autocomplete="off">



<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj0202); %>
	<jsp:include page="medin0101q.jsp" />
	<jsp:include page="medin0101js.jsp" />
	
	<% request.setAttribute("qBean", objQry0201); %>
	<jsp:include page="medin0201q.jsp" />
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj0202.getState()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="queryAllFlag" value="<%=obj0202.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="inOrOut" value="<%=User.getInORout()%>">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj0202.getIsCloseUserInfo()%>"> <!-- 列印時是否遮蔽個資 -->
	<input type="hidden" name="isAlreadyAutoSave">
	<input type="hidden" name="changeTabValue" value="<%=obj0202.getChangeTabValue()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span> 
    <%if("10".equals(obj0202.getStatusType())){%>
    <span id="spanAccepted">
	   <input class="toolbar_default" type="button" followPK="false" name="accepted" value="受　理" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if("10".equals(obj0202.getStatusType())){%>
    <span id="spanBackPieces">
	   <input class="toolbar_default" type="button" followPK="false" name="backPieces" value="退　件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if("10".equals(obj0202.getStatusType())){%>
    <span id="spanDismissal">
	   <input class="toolbar_default" type="button" followPK="false" name="dismissal" value="撤　案" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if("20".equals(obj0202.getStatusType())){%>
    <span id="spanAddocuments">
	   <input class="toolbar_default" type="button" followPK="false" name="addocuments" value="補　件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
     <%}%>
    <%if("20".equals(obj0202.getStatusType())||("40".equals(obj0202.getStatusType()))){%>
    <span id="spanCorrection">
	   <input class="toolbar_default" type="button" followPK="false" name="correction" value="校正完成" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    
    <span id="spanReport">
	   <input class="toolbar_default" type="button" followPK="false" name="report" value="案件列印" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanReassignment">
	   <input class="toolbar_default" type="button" followPK="false" name="reassignment" value="案件改派" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanDoGetMLMS">
		<input class="toolbar_default" type="button" followPK="false" name="doGetMLMS" value="檢視許可證資料" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
     <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	
	<input class="toolbar_default" type="button" followPK="false" name="enforceClose" value="強制結案" onClick="whatButtonFireEvent(this.name)">&nbsp;
	
	
	<input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doAddDoc" value="案件補件紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doMailList" value="郵件清單紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doFlow" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">	

</tr>

<tr>
<td nowrap>
  <% request.setAttribute("QueryBean",obj0202);%>
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
	<td nowrap ID="t9" CLASS="tab_border2"><a id="aTab9" href="#" onClick="changeTab(9);">相關附件</a></td>
	
	<%if("1".equals(obj0202.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0007Db where med0001Db.id = " + Common.sqlChar(obj0202.getId()) )){%>
	<td nowrap ID="t12" CLASS="tab_border2"><a id="aTab12" href="#" onClick="changeTab(12);">不良反應評估</a></td>
	<%} %>
	
	<%if("2".equals(obj0202.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0008Db where med0001Db.id = " + Common.sqlChar(obj0202.getId()))){ %>
	<td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">不良品評估初評</a></td>
	<%} %>
	
	<%if("2".equals(obj0202.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0010Db where med0001Db.id = " + Common.sqlChar(obj0202.getId()))){ %>
	<td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">廠商回覆</a></td>
    <%} %>
    
    <%if("2".equals(obj0202.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0009Db where med0001Db.id = " + Common.sqlChar(obj0202.getId()))){ %>
	<td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">不良品評估複評</a></td>
	<%} %>
	
	<td nowrap ID="t8" CLASS="tab_border2"><a id="aTab8" href="#" onClick="changeTab(8);">歷史通報</a></td>
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
	<jsp:include page="medin0101f.jsp" /> <!--、 -->
	<jsp:include page="medin2003f.jsp" />
	<%if("2".equals(obj0202.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0010Db where med0001Db.id = " + Common.sqlChar(obj0202.getId()))){ %>
	<jsp:include page="../medex/medex0303f.jsp" />
	<%} %>
	<%if("1".equals(obj0202.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0007Db where med0001Db.id = " + Common.sqlChar(obj0202.getId()) )){%>
	<jsp:include page="medin0403f.jsp" />
    <%} %>
    <%if("2".equals(obj0202.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0008Db where med0001Db.id = " + Common.sqlChar(obj0202.getId()))){ %>
    <jsp:include page="medin0703f.jsp" />
    <%} %>
    <%if("2".equals(obj0202.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0009Db where med0001Db.id = " + Common.sqlChar(obj0202.getId()))){ %>
    <jsp:include page="medin0803f.jsp" />
    <%} %>
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
		  getUserUpdate("MED","MED01",form1.id.value);
		  break;
	  case "doQuery":
		  form1.submit();
		  break;
	  case "clear":
		  if(confirm("確定放棄編輯?"))
		  {
		    toClear();
		  }
		  break;
	  //暫存
	  case "pauseSave":
		  checkField();
		  break;
	  //受理	  
	  case "accepted":
		  if(confirm("確定案件受理?"))
		  {
		    form1.state.value = buttonName;
		    checkField();
		  }
		  break;
	  //退件
	  case "backPieces":
		  if(confirm("確定退件?"))
		  {
		    setAllReadonly();
		    getEmail('MED010002','window.opener.backPieces','N','N');
		  }
		  break;
	  //撤案
	  case "dismissal":
		  if(confirm("確定撤案?"))
		  {
		    setAllReadonly();
		    getEmail('MED010003','window.opener.dismissal','N','N');
		  }
		  break;
	  //校正完成	  
	  case "correction":
		  if(confirm("確定校正完成?"))
		  {
		    setAllReadonly();
		    form1.state.value = buttonName;
		    checkField();
		  }
		  break;
	  //案件列印
	  case "report":
		  toAsk();
		  break;
	  //補件	  
	  case "addocuments":
		  if(confirm("確定補件?"))
		  {
		    setAllReadonly();
		    getEmail('MED010004','window.opener.addocuments','Y','MED010004,MED010010,MED010011');
		  }
		  break;
	  //案件改派
	  case "reassignment":
		  if(confirm("確定案件改派?"))
		  {
		    getReassignment();
		  }
		  break;
	  case "doGetMLMS":
		  if(""==form1.medPermit.value || ""==form1.medPermitNumber.value)
		  {
			  alert("無許可證資料");
		  } else {
			  toShowMLMS();
		  }
		  
		  break;
	  case "doDoQuit":
		  if(confirm("確定回到查詢頁面?"))
		  {
		   form1.action = "medin0201f.jsp?statusType="+'<%=statusType%>'+"&caseType="+'<%=caseType%>';
		   form1.state.value = "queryAll";
		   form1.submit();
		  }
		  break;
	  //匯出EXCEL
	  case "doReport":
		  form1.action = "medin2003p.jsp";
		  beforeSubmit();
		  form1.submit();
		  break;
	  case "doOriginalVer":
			popMed4001('<%=obj0202.getId()%>');
			break;
	  case "doAddDoc":
			popCon0004('<%=obj0202.getApplNo()%>');
			break;
	  case "doMailList":
		    <%if("10".equals(obj0202.getStatus()) || "11".equals(obj0202.getStatus())  ){%>
				popCon0002('MED','<%=obj0202.getId()%>');
			<%} else { %>
			   <%if("1".equals(obj0202.getCaseType())) {%>
				popCon0002('MED01','<%=obj0202.getId()%>');
				<%} else { %>
				popCon0002('MED02','<%=obj0202.getId()%>');
				<%}%>
			<%}%>
			break;
	  case "doFlow":
		    <%if("1".equals(obj0202.getCaseType())) {%>
		    popCon2001('MED01','<%=obj0202.getId()%>');
			<%} else { %>
			popCon2001('MED02','<%=obj0202.getId()%>');
			<%}%>
			break;
	  case "enforceClose":
		    if(getUserUpdate("MED","MED01",form1.id.value) != false)
			    {
			    	enforceClose(form1.id.value,'<%=obj0202.getApplNo()%>','<%=obj0202.getEventKind()[0]%>');
			    }
			
		    break;
	}
}

</script>
</body>
</html>
