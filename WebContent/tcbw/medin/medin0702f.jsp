<!--
程式目的：不良品案件審核
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj0702" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0702F">
	<jsp:setProperty name="obj0702" property="*"/>
</jsp:useBean>
<jsp:useBean id="objQry0701" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0701F">
	<jsp:setProperty name="objQry0701" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList0702" scope="page" class="java.util.ArrayList"/>
<%

String statusType = Common.get(request.getParameter("statusType"));
String caseType = Common.get(request.getParameter("caseType"));

String competence="Y";

if("1".equals(obj0702.getCaseType()))
	competence=TCBWCommon.getCompetence("MED01","01",User.getUserId(),"4");
else
	competence=TCBWCommon.getCompetence("MED02","01",User.getUserId(),"4");


if("pauseSave".equals(obj0702.getState()) || "upload".equals(obj0702.getState()))
{
	try
	{
		obj0702.doUpdateType0702();
		obj0702.setErrorMsg("暫存成功");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj0702.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("completed".equals(obj0702.getState()))
{
	try
	{
		obj0702.doCompleted();
		obj0702.doDeleteCon0003Db();
		obj0702.setErrorMsg("評估完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj0702.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("completedConfirmed".equals(obj0702.getState()))
{
	try
	{
		obj0702.doCompletedConfirmed();
		obj0702.doDeleteCon0003Db();
		obj0702.setErrorMsg("評估確認完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj0702.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("toClear".equals(obj0702.getState()))
{
	try
	{
		obj0702.setHttpRequest(pageContext.getRequest());	
		obj0702.doDeleteCon0003Db();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj0702.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj0702 = (com.kangdainfo.tcbw.view.medin.MEDIN0702F)obj0702.queryOne();
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
	
	var alertStr="";

	alertStr +=validateAddrTable('med1006DbRow','patientIssuesName');
	
	if(form1.state.value == "completed")
	{
		//alertStr += checkRadioButton(form1.bulletinQuality,"通報品質");
		//alertStr += checkRadioButton(form1.eventClass,"事件等級");
		//alertStr += checkRadioButton(form1.eventDetails,"需再取得事件詳細說明");
		<%=TCBWCommon.getCheckFiled("MED02","MEDIN0702F")%>
	}
	
	if(alertStr.length!=0){ alert(alertStr); return false;}
	
	beforeSubmit();
	form1.submit();
	return true;
}

function validateAddrTable(typeId,itName) 
{
	var rs=document.getElementsByName(typeId);
	
	if (rs!=null && rs.length>0) 
	{		
		var itemIdx = new Array(rs.length);
		for (var i=0; i<rs.length; i++) 
	    {
			var itemName=itName;
			var id = rs[i].value;
			var sb = new StringBuffer();
			itemIdx[i] = document.all.item(itemName+id).value;
		}

		for (var i=0; i<itemIdx.length; i++)
		{
			for (var j=0; j<itemIdx.length; j++) 
			{
				if (i!=j && itemIdx[i]==itemIdx[j]) 
					return "醫材問題代碼不能重覆!!\n";
			}
		}
		
	}
	return "";
}


function init() 
{

	//只有轉送評估頁籤可以修改
	if(isObj(document.getElementById('Tab1')))
	$('#Tab1').find('* :input').attr('class','field_RO');
	if(isObj(document.getElementById('Tab2')))
	$('#Tab2').find('* :input').attr('class','field_RO');
	if(isObj(document.getElementById('Tab3')))
	$('#Tab3').find('* :input').attr('class','field_RO');
	if(isObj(document.getElementById('Tab4')))
	$('#Tab4').find('* :input').attr('class','field_RO');
	if(isObj(document.getElementById('Tab5')))
	$('#Tab5').find('* :input').attr('class','field_RO');
	if(isObj(document.getElementById('Tab7')))
	$('#Tab7').find('* :input').attr('class','field_RO');
	if(isObj(document.getElementById('Tab10')))
	$('#Tab10').find('* :input').attr('class','field_RO');

	$('#notifier').attr('class','field_Q');//查看通報者資訊功能不鎖
	
	
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanConfirm,spanListPrint,spanListHidden','H');

	setFormItem("pauseSave,completed,completedConfirmed,doUpload,doUpload0703,doUpload0803,doUploadForReplayContent","R");
	
    var tbID=document.all.item("changeTabValue").value;

    if(tbID==null)
      changeTab(1);
    else
	  changeTab(tbID);

    
    if('<%=obj0702.getErrorMsg()%>'=='評估完成' || '<%=obj0702.getErrorMsg()%>'=='評估確認完成' )
	{	
	    form1.action = "medin0701f.jsp?statusType="+'<%=statusType%>'+"&caseType="+'<%=obj0702.getCaseType()%>';
	    form1.state.value = "queryAll";
	    form1.submit();
	    //重新整理左邊樹狀
	    window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	}

}
function beforeinit()
{
	<%=obj0702.getMed1006DbItemSet(obj0702.getId2())%>
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
			setFormItem("pauseSave,accepted,backPieces,dismissal,correction,addocuments,reassignment,doUpload,doUpload0703,doUpload0803,doUploadForReplayContent","R");	
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
		setFormItem("completed,completedConfirmed,doUpload,doUpload0703,doUpload0803,doUploadForReplayContent","R");
		setFormItem("update","O");	
		alert("此案件目前已有其他作業人員編輯中!");
		form1.state.value="init";
		return false;	
	}
}

function queryOne(id)
{
	form1.id2.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
	
}

function totalNcar()
{
	var x = getRemoteData('../../ajax/jsonMed1003Db.jsp',"");
	
	if(x!=null && x.length>0)
	{

		var json = eval('(' + x + ')'); 
		var str="";
		var sum=0;
		var checkStr="";

		for (var i=0; i<json.length; i++) 
		{
			//id,isYes,isYesType,isNo,isNoType
			
			str="ncarOptions"+i;

			var rads = document.getElementsByName(str);

			if(rads.length >0)
			{
			  checkStr+=json[i].id+":";

			  if(rads[0].checked)
			  {
				checkStr+="Y";
				
				if(json[i].isYesType=='0')
				  sum+=json[i].isYes;
				else
				  sum-=json[i].isYes;
			  }

			  if(rads[1].checked)
			  {
				if(json[i].isNoType=='0')
				  sum+=json[i].isNo;
				else
				  sum-=json[i].isNo;

				checkStr+="N";
			  }
			
			  checkStr+=";";
			}
			
		}

		form1.ncarOptions.value=checkStr;
		form1.ncarTotal.value=sum;

	}

	
}

function toClear()
{
   form1.state.value="toClear";
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
	  var q="?id="+form1.id.value+"&q_formCode=MED02&q_code=02";
	  returnWindow=window.open(getVirtualPath() + "tcbw/medin/medin0203f.jsp"+q,"",prop);
    }
}

function reassignment()
{
	form1.action = "medin0701f.jsp?statusType="+'<%=statusType%>'+"&caseType="+'<%=caseType%>';
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
	form1.action = "medin0702f.jsp"
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


</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj0702.getState()%>');beforeinit();init();showErrorMsg('<%=obj0702.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="checkField();" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", objQry0701); %>
	<jsp:include page="medin0701q.jsp" />
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
    <input type="hidden" name="id" value="<%=obj0702.getId()%>">
    <input type="hidden" name="id2" value="<%=obj0702.getId2()%>">
	<input type="hidden" name="state" value="<%=obj0702.getState()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="applNo">
	<input type="hidden" name="conveyedVendors" value="<%=obj0702.getConveyedVendors()%>">
	<input type="hidden" name="autoReUpdate" value="<%=obj0702.getAutoReUpdate()%>">
	<input type="hidden" name="changeTabValue" value="<%=obj0702.getChangeTabValue()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj0702.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="inOrOut" value="<%=User.getInORout()%>">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj0702.getIsCloseUserInfo()%>"> <!-- 列印時是否遮蔽個資 -->
	<input type="hidden" name="isAlreadyAutoSave">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    
    
    <%if("70".equals(obj0702.getStatusType())){%>
    <span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanCompleted">
	   <input class="toolbar_default" type="button" followPK="false" name="completed" value="評估完成" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    
    <%if("71".equals(obj0702.getStatusType())){%>
     <span id="spanCompletedConfirmed">
	   <input class="toolbar_default" type="button" followPK="false" name="completedConfirmed" value="評估確認" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    
    <span id="spanReassignment">
	   <input class="toolbar_default" type="button" followPK="false" name="reassignment" value="案件改派" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    
    <input class="toolbar_default" type="button" followPK="false" name="report" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
    
    
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>

	<input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doAddDoc" value="案件補件紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doMailList" value="郵件清單紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doFlow" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">	
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
	<td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">不良品評估初評</a></td>
	<%if("2".equals(obj0702.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0010Db where med0001Db.id = " + Common.sqlChar(obj0702.getId()))){ %>
	<td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">廠商回覆</a></td>
    <%} %>
    <%if("2".equals(obj0702.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0009Db where med0001Db.id = " + Common.sqlChar(obj0702.getId()))){ %>
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
      <jsp:include page="medin0101js.jsp" />
	  <jsp:include page="medin0101f.jsp" />
      <jsp:include page="medin0303f.jsp" />
      <jsp:include page="medin0703f.jsp" />
      <jsp:include page="medin2003f.jsp" />
      <%if("2".equals(obj0702.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0010Db where med0001Db.id = " + Common.sqlChar(obj0702.getId()))){ %>
      <jsp:include page="../medex/medex0303f.jsp" />
      <%} %>
      <%if("2".equals(obj0702.getCaseType()) && TCBWCommon.hasDataByTableName(" from Med0009Db where med0001Db.id = " + Common.sqlChar(obj0702.getId()))){ %>
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
	  case "doQuery":
		  form1.submit();
		  break;
	  case "update":
		  setFormItem("pauseSave,completed,completedConfirmed,doUpload,doUpload0703,doUpload0803,doUploadForReplayContent","O");
		  getUserUpdate("MED","MED01",form1.id.value);
		  //if(isObj(document.all.item("assignmentKind"))) from1.assignmentKind.checked=true;

		  break;
	  case "clear":
		  if(confirm("確定放棄編輯?"))
		  {
		    toClear();
		  }
		  break;
		  //暫存
	  case "pauseSave":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "completed":
		  if(confirm("確定評估完成?"))
		  {
		   form1.state.value = buttonName;
		   checkField();
		  }
		  break;
	  case "completedConfirmed":
		  if(confirm("確定評估確認完成?"))
		  {
			if(form1.revision.value!='01')
			{
				if(confirm("是否轉知廠商"))
		        {
					form1.conveyedVendors.value="Y";
		        }
				else
				{
					form1.conveyedVendors.value="N";
				}
			} 
			form1.state.value = buttonName;
			checkField();
		  }
		  break;
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
		   form1.action = "medin0701f.jsp?statusType="+'<%=statusType%>'+"&caseType="+'<%=obj0702.getCaseType()%>';
		   form1.state.value = "queryAll";
		   form1.submit();
		  }
		  break;
	  case "report":
	      toAsk();
	      break;
	  case "doOriginalVer":
			popMed4001('<%=obj0702.getId()%>');
			break;
	  case "doAddDoc":
			popCon0004('<%=obj0702.getApplNo()%>');
			break;
	  case "doMailList":
		   <%if("1".equals(obj0702.getCaseType())) {%>
			popCon0002('MED01','<%=obj0702.getId()%>');
			<%} else if("2".equals(obj0702.getCaseType())) { %>
			popCon0002('MED02','<%=obj0702.getId()%>');
			<%} else {%>
			popCon0002('MED','<%=obj0702.getId()%>');
			<%}%>
			break;
	  case "doFlow":
		  <%if("1".equals(obj0702.getCaseType())) {%>
			popCon2001('MED01','<%=obj0702.getId()%>');
			<%} else { %>
			popCon2001('MED02','<%=obj0702.getId()%>');
			<%}%>
			break;
	 
	}
}

</script>
</body>
</html>
