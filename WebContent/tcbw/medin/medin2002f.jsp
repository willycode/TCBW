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
<jsp:useBean id="obj0101" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0101F">
	<jsp:setProperty name="obj0101" property="*"/>
</jsp:useBean>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0202F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="obj0402" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0402F">
	<jsp:setProperty name="obj0402" property="*"/>
</jsp:useBean>
<jsp:useBean id="obj0702" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0702F">
	<jsp:setProperty name="obj0702" property="*"/>
</jsp:useBean>
<jsp:useBean id="obj0802" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0802F">
	<jsp:setProperty name="obj0802" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList0802" scope="page" class="java.util.ArrayList"/>
<%
String isPop = Common.get(request.getParameter("isPop"));
String statusType = Common.get(request.getParameter("statusType"));
String caseType = Common.get(request.getParameter("caseType"));
String queryType = Common.get(request.getParameter("queryType"));
obj0802 = (com.kangdainfo.tcbw.view.medin.MEDIN0802F)obj0802.queryOne();
obj0101 = (com.kangdainfo.tcbw.view.medin.MEDIN0101F)obj0101.queryOne();
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
	//validateAddrTable();
	
	var alertStr="";

	if(alertStr.length!=0){ alert(alertStr); return false;}

	beforeSubmit();
	form1.submit();
	return true;
}
function init() 
{
	
	setDisplayItem('update,clear,spanInsert,spanQueryAll,spanDelete,spanConfirm,spanListPrint,spanListHidden','H');

	setFormItem("pauseSave,completed,doUpload,doUpload0403,doUpload0703,doUpload0803,doUploadForReplayContent","R");
	
    var tbID=document.all.item("changeTabValue").value;

    if(tbID==null)
      changeTab(1);
    else
	  changeTab(tbID);

    if (isObj(document.all.item("eventKind")))
	{	
       var a=form1.eventKind;
	   if (a!=null && a.length>0) 
	   {
		  for (j=0; j<a.length; j++) 
		  {
			 if(a[0].checked)//為不良反應時
			 {		 
			  document.getElementById("t4").style.display = 'none';
			  document.getElementById("aTab4").style.display = 'none';
			  document.getElementById("t6").style.display = 'none';
			  document.getElementById("aTab6").style.display = 'none';
			  document.getElementById("t7").style.display = 'none';
			  document.getElementById("aTab7").style.display = 'none';
			 }
			 if(a[1].checked)//為不良品時
			 {
		      document.getElementById("t3").style.display = 'none';
			  document.getElementById("aTab3").style.display = 'none';
			  document.getElementById("t12").style.display = 'none';
			  document.getElementById("aTab12").style.display = 'none';
			 }
		   }
	    }
	}

}
function popMed4001(id){
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
function queryOne1(id)
{
	var isPop = "Y";
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1250,height=720,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/medin/medin2002f.jsp?isPop="+isPop+"&id="+id,"",prop);
}
function queryOne(id)
{
	form1.id2.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}
function doBack(process)
{
	if("0901" == process) {
		alert("案件再評估完成!");
	} else if("0902" == process) {
		alert("案件再校正完成!");
	}
	
	form1.action = "medin2001q.jsp";
	form1.state.value = "queryAll";
	form1.submit();
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
	form1.action = "medin2002f.jsp"
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

function beforeinit()
{
	<%=obj.getMed0002DbItemSet()%>
	<%=obj.getMed0003DbItemSet()%>
	<%=obj.getMed0004DbItemSet()%>
	<%=obj.getMed0005DbItemSet()%>
	<%=obj0402.getMed1005DbItemSet(obj0402.getId2())%>
	<%=obj0402.getMed1006DbItemSet(obj0402.getId2())%>
	<%=obj0702.getMed1006DbItemSet(obj0702.getId2())%>
	<%=obj0802.getMed1007DbItemSet(obj0802.getId2())%>
	
	
}



</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj0802.getState()%>');beforeinit();init();showErrorMsg('<%=obj0802.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="checkField();" autocomplete="off">
<!--Query區============================================================-->


<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bgToolbar">
    <input type="hidden" name="id" value="<%=obj0802.getId()%>">
    <input type="hidden" name="id2" value="<%=obj0802.getId2()%>">
	<input type="hidden" name="state" value="<%=obj0802.getState()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="revision" value="<%=obj0802.getRevision()%>">
	<input type="hidden" name="conveyedVendors" value="<%=obj0802.getConveyedVendors()%>">
	<input type="hidden" name="autoReUpdate" value="<%=obj0802.getAutoReUpdate()%>">
	<input type="hidden" name="changeTabValue" value="<%=obj0802.getChangeTabValue()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj0802.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="inOrOut" value="<%=User.getInORout()%>">
	<input type="hidden" name="status" value="<%=obj0802.getStatus()%>">
	<input type="hidden" name="caseSource" value="<%=obj0802.getCaseSource()%>">
	<input type="hidden" name="isAlreadyAutoSave">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj.getIsCloseUserInfo()%>"> <!-- 列印時是否遮蔽個資 -->
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <%if("".equals(isPop) || isPop == null){%>
    <input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doAddDoc" value="案件補件紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doMailList" value="郵件清單紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doFlow" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">	
	<input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>
	<%if("90".equals(obj0802.getStatus()) && "in".equals(obj0802.getCaseSource())) { %>
	    <input class="toolbar_default" type="button" followPK="false" name="reAssessment" value="案件再評估" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<%} %>
	<%if(Common.getInt(obj0802.getStatus()) >= 70) { %>
	<input class="toolbar_default" type="button" followPK="false" name="reCalibration" value="重新校正" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<%} %>
</tr>

</table>

<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
	<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	<td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良事件</a></td>

	<td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">不良反應</a></td>

	<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">不良品</a></td>

	<td nowrap ID="t9" CLASS="tab_border2"><a id="aTab9" href="#" onClick="changeTab(9);">相關附件</a></td>
	<td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">轉送評估</a></td>

	<td nowrap ID="t12" CLASS="tab_border2"><a id="aTab12" href="#" onClick="changeTab(12);">不良反應評估</a></td>

	<td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">不良品評估初評</a></td>
	
	<%if("2".equals(obj0802.getEventKind()[0]) && TCBWCommon.hasDataByTableName(" from Med0010Db where med0001Db.id = " + Common.sqlChar(obj0802.getId()))){ %>
	<td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">廠商回覆</a></td>
    <%} %>
    
	<td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">不良品評估複評</a></td>

	<td nowrap ID="t8" CLASS="tab_border2"><a id="aTab8" href="#" onClick="changeTab(8);">歷史通報</a></td>
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
      <jsp:include page="medin0101js.jsp" />
	  <jsp:include page="medin0101f.jsp" />
      <jsp:include page="medin0303f.jsp" />
      <%if("2".equals(obj.getEventKind()[0]) && TCBWCommon.hasDataByTableName(" from Med0010Db where med0001Db.id = " + Common.sqlChar(obj.getId()))){ %>
      <jsp:include page="../medex/medex0303f.jsp" />
      <%} %>
      <jsp:include page="medin0403f.jsp" />
      <jsp:include page="medin0703f.jsp" />
      <jsp:include page="medin0803f.jsp" />
      <jsp:include page="medin2003f.jsp" />
    </div>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
      var result="";result1="",result2="";

	  //if (isObj(document.all.item("eventKind")))
	  //{	
	  //     var a=form1.eventKind;
		   //if (a!=null && a.length>0) 
		   //{
			  //for (j=0; j<a.length; j++) 
			  //{
				 //if(a[0].checked)
				 //{	
					 //result1="1";
					 //result="1";
				 //}
				 //if(a[1].checked)
				 //{
					 //result2="2";
					 //result="2";
				 //}
			   //}
		    //}

		    //if(result1=="1" && result2=="2")
		    //{
		    //	result="3";
		    //}
		    //else
		    //{
		    //	result=result;
			//}
	   //}

	switch (buttonName)	
	{
	  case "doDoQuit":
		 if(confirm("確定回到查詢頁面?"))
		 {
		   <%if("1".equals(queryType)) {%>
		   
		   form1.action = "medin0301q.jsp";
		   form1.state.value = "queryAll";
		   form1.submit();
		   <%} else {%>
		   form1.action = "medin2001q.jsp";
		   form1.state.value = "queryAll";
		   form1.submit();
		   <%}%>
		   
		 }
		 break;
	  case "doOriginalVer":
			popMed4001('<%=obj0802.getId()%>');
			break;
	  case "doAddDoc":
			popCon0004('<%=obj0802.getApplNo()%>');
			break;
	  case "doMailList":
		  <% if("1".equals(obj0101.getEventKindTemp())) { %>
		  		popCon0002('MED01','<%=obj0101.getId()%>');
		  <%} else if("2".equals(obj0101.getEventKindTemp())) {%>
		  		popCon0002('MED02','<%=obj0101.getId()%>');
		  <%} else {%>
		  		popCon0002('MED','<%=obj0101.getId()%>');
		  <%}%>
			break;
	  case "doFlow":
		    <%if("1".equals(obj0802.getEventKind()[0])) {%>
			popCon2001('MED01','<%=obj0802.getId()%>');
			<%} else { %>
			popCon2001('MED02','<%=obj0802.getId()%>');
			<%}%>
			break;
	  case "doQuery":
		  	form1.submit();
		  	break;
	  case "doReportView":
		  toAsk();
		  break;
	  case "reAssessment":
		  if("2"=='<%=obj0802.getEventKind()[0]%>' && "" == getRadioChecked1("eventClass")) {
			alert("該案件尚未進行評估，故無事件等級，請改使用重新校正功能");
		  } else {
			  reAssessment(form1.id.value,'<%=obj0802.getApplNo()%>','<%=obj0802.getEventKind()[0]%>');
		  }
  	  	  break;
	  case "reCalibration":
		  reCalibration(form1.id.value,'<%=obj0802.getApplNo()%>','<%=obj0802.getEventKind()[0]%>');
		  break;
	}
}

</script>
</body>
</html>
