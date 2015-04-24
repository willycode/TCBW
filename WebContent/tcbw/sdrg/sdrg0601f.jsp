<!--
程式名稱：重大品質事件廠商主動通報 - 案件分析作業
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SDRG0601" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0601F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%

if("pauseSave".equals(obj.getState()) || "doAnalysis".equals(obj.getState())){
	try	{
		obj.setHttpRequest(pageContext.getRequest());		
		obj.doUpdate0601();
		if("0".equals(obj.getUpdateType())){
			obj.setErrorMsg("修改完成");
		}else if ("13".equals(obj.getUpdateType())){
			obj.setErrorMsg("分析完成");
		}		
	}catch(Exception e){
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.sdrg.SDRG0601F) obj.doQueryOne0601();

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
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
var popWinName;

function checkField()
{
	var id = form1.id.value;
	var alertStr = "";	
	if(form1.state.value == "pauseSave"){
		form1.updateType.value = "0"; //修改
	}
	else if(form1.state.value=="doAnalysis"){		
		form1.updateType.value = "13"; //案件分析完成
		<%=TCBWCommon.getCheckFiled("DRG04","SDRG0602")%>;
		alertStr += checkRadioOtherEmpty(form1.anarecyclereason,'90',form1.anarecyclersdesc,'回收原因其他說明');
		alertStr += checkRadioOtherEmpty(form1.anasurvey,'90',form1.anasurveyOther,'調查結果其他說明');
		alertStr += checkRadioOtherEmpty(form1.anaprecaution,'90',form1.anaprecautionOther,'預防措施其他說明');
	}	
	if(alertStr.length!=0){ alert(alertStr); return }
	beforeSubmit();
	form1.submit();	
}

function init() 
{
	//頁籤切換
	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);
	}else{
		changeTab(6);
	}
	//許可證字號動態調整(tab1)
	permitData1("init");
	//訊息來源控制(tab2)
	checkPlnoInput();	
	//將按鈕先鎖住
	setFormItem("pauseSave,doAnalysis,doUpload1,doUpload2,doUpload3,doUpload4,btnAbroadQuery,btnAbroadClear,doUpload5,doUpload6,doUpload7,doUpload8,doUpload9,doUpload10","R");
	//隱藏不需要的預設按鈕
	setDisplayItem("spanInsert,spanConfirm,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	//修改資料完畢，導入查詢頁面
	if('<%=obj.getErrorMsg()%>'=='分析完成')
	{			
		form1.action = "sdrg0601q.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
}
function beforeInit()
{
	<%=obj.getDrg8002DbItemSet()%>
	<%=obj.getDrg8005DbItemSet()%>
}

//頁籤更換
function changeTab(tabId) 
{
	if (isObj(document.all.item("t1")))
	{	
	  document.getElementById("t1").className = "tab_border2";
	  document.getElementById("aTab1").className = "";
	  document.getElementById("Tab1").style.display = 'none';
	}
	if (isObj(document.all.item("t2")))
	{	
	  document.getElementById("t2").className = "tab_border2";
	  document.getElementById("aTab2").className = "";
	  document.getElementById("Tab2").style.display = 'none';
	}
	if (isObj(document.all.item("t3")))
	{	
	  document.getElementById("t3").className = "tab_border2";
	  document.getElementById("aTab3").className = "";
	  document.getElementById("Tab3_1").style.display = 'none';
	  document.getElementById("Tab3_2").style.display = 'none';
	}
	if (isObj(document.all.item("t4")))
	{  
	  document.getElementById("t4").className = "tab_border2";
	  document.getElementById("aTab4").className = "";	  
	  document.getElementById("Tab4_1").style.display = 'none';
	  document.getElementById("Tab4_2").style.display = 'none';	    
	}
	if (isObj(document.all.item("t5")))
	{	
	  document.getElementById("t5").className = "tab_border2";
	  document.getElementById("aTab5").className = "";
	  document.getElementById("Tab5").style.display = 'none';
	}
	if (isObj(document.all.item("t6")))
	{	
	  document.getElementById("t6").className = "tab_border2";
	  document.getElementById("aTab6").className = "";
	  document.getElementById("Tab6").style.display = 'none';
	}
	

	if (isObj(document.all.item("changeTabValue")))
	{
		document.all.item("changeTabValue").value=tabId;
	}

	if(tabId == "2")
	{
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";
		form1.tabId.value='2';
	}
	else if(tabId == "3"){
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3_1").style.display = '';
		document.getElementById("Tab3_2").style.display = '';
		document.getElementById("aTab3").className = "text_w";
		form1.tabId.value='3';
	}
	else if(tabId == "4"){
		document.getElementById("t4").className = "tab_border1";
		if('<%=obj.getCaseType()%>'=='1'){
			document.getElementById("Tab4_1").style.display = '';
			document.getElementById("Tab4_2").style.display = 'none';
		}else{
		    document.getElementById("Tab4_1").style.display = '';
			document.getElementById("Tab4_2").style.display = '';
		}
		document.getElementById("aTab4").className = "text_w";
		form1.tabId.value='4';
	}
	else if(tabId == "5"){
		document.getElementById("t5").className = "tab_border1";	
		document.getElementById("Tab5").style.display = '';
		document.getElementById("aTab5").className = "text_w";
		form1.tabId.value='5';
	}
	else if(tabId == "6"){
		document.getElementById("t6").className = "tab_border1";	
		document.getElementById("Tab6").style.display = '';
		document.getElementById("aTab6").className = "text_w";
		form1.tabId.value='6';
	}
	else
	{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";
		form1.tabId.value='1';
	}
}
</script>
</head>
<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bgToolbar"> 
    <jsp:include page="../../home/toolbar.jsp" />  
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
	<input type="hidden" name="isUpload" value="<%=obj.getIsUpload()%>">
	<input type="hidden" name="updateType">
	
    <span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanDoAnalysis">
	   <input class="toolbar_default" type="button" followPK="false" name="doAnalysis" value="分析完成" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit"  value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanShow"></span>
    </td>
</tr>

<tr>
<td nowrap>
  <% request.setAttribute("QueryBean",obj);%>
  <jsp:include page="../../home/page_row.jsp" />
</td>
</tr>
</table>

<!--  tab  -->
<TABLE>
	<TR>
		<td nowrap ID="t1" CLASS="tab_border2"><a id="aTab1" href="#" onClick="changeTab(1);">案件登錄</a></td>
	    <td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">案件審核</a></td>
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">廠商回收</a></td>
	    <td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">回收確認</a></td>
	    <td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">案件評估</a></td>
	    <td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">案件分析</a></td>
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
        <jsp:include page="sdrg0102f.jsp" />
        <jsp:include page="sdrg0202f.jsp" />
        <jsp:include page="sdrg0302f.jsp" />
        <jsp:include page="sdrg0402f.jsp" />
        <jsp:include page="sdrg0502f.jsp" />
        <jsp:include page="sdrg0602f.jsp" />
    </div>
    </td>
</tr>

</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  //不開放登錄及審核頁籤(全部先鎖定)
		  setAllReadonly();	  
		  //案件分析
		  if(form1.status2.value =='60'){
			  $("#Tab6").find('.field').attr('disabled',false);
			  $("#Tab6").find('.field').attr('readOnly',false);
			  setFormItem("pauseSave,doAnalysis","O");
		  }		  
	      break;	 
	  case "pauseSave":
	  case "doAnalysis":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "clear":			
		  form1.state.value = "queryOne";			
		  beforeSubmit();
		  form1.submit();	
		  break;
	  case "doDoQuit":
		  form1.action = "sdrg0601q.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	}
}
</script>
</body>
</html>