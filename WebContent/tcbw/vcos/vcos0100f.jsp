<!--
程式目的：化妝品維護畫面
程式代號：vcos0100f
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>

<jsp:include page="../../home/secure.jsp"/>

<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.vcos.VCOS0100F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

if ("init".equals(obj.getState())) {
	obj.setQueryAllFlag("true");
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
	if("insertSuccess".equals(obj.getState())){
		obj.setQueryAllFlag("true");
		if("pauseSave".equals(obj.getActionType())){
			obj.setErrorMsg("暫存完成");
		}
	}
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
	if("updateSuccess".equals(obj.getState())){
		obj.setQueryAllFlag("true");
		if("pauseSave".equals(obj.getActionType())){
			obj.setErrorMsg("暫存完成");
		}else if("doSend".equals(obj.getActionType())){
			obj.setErrorMsg("送出完成");
		}else if("assesscomp".equals(obj.getActionType())){
			obj.setErrorMsg("評估完成");
		}
	}
}
else if("upload".equals(obj.getState())){
	try	{		
		obj.update();
		obj.queryOne();
	}catch(Exception e)	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
if ("true".equals(obj.getQueryAllFlag())){
	obj = (com.kangdainfo.tcbw.view.vcos.VCOS0100F)obj.queryOne();	
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
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
var insertDefault = new Array(
		new Array("applNo",""),
		new Array("status","00"),
		new Array("prodtype","化粧品")		
	)

function checkField()
{		//設定Button條件 
	var alertStr = "";		
	if(form1.state.value == "queryAll")
	{
		alertStr += checkQuery();		
	}
	else if(form1.state.value=="insert"||form1.state.value=="insertError"||
			form1.state.value=="update"||form1.state.value=="updateError")
	{
		
		if("vcos0101" == form1.formType.value)
		{
			<%=TCBWCommon.getCheckFiled("COS04","VCOS0101")%>
		}
		else if("vcos0301" == form1.formType.value)
		{
			<%=TCBWCommon.getCheckFiled("COS04","VCOS0301")%>
		}

		alertStr+=checkLen(form1.contextsummary,"資訊內容摘要", 500);
		alertStr+=checkLen(form1.lotNo,"產品批號", 300);
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	form1.submit();	
	return true;
}

function init() 
{
		document.all.item('publconsumerDate').className = 'field_RO';
		setFormItem("pauseSave,assesscomp,doSend","R");
		
		if("00" == form1.status.value)
		{
			setFormItem("doSend","O");
			setDisplayItem('spanAssesscomp,spanInsert,spanQueryAll,spanConfirm,spanDelete,spanListPrint,spanListHidden','H');			
		}
		else if("vcos0301"  == form1.formType.value)
		{
			setDisplayItem('spanDoSend,spanInsert,spanQueryAll,spanConfirm,spanDelete,spanListPrint,spanListHidden','H');
		}
		else if("vcos0401"  == form1.formType.value)
		{
			setDisplayItem('spanDoSend,spanUpdate,spanClear,spanInsert,spanQueryAll,spanConfirm,spanDelete,spanListPrint,spanListHidden','H');
			setDisplayItem("pauseSave,doSend,assesscomp","H")	;	
		}
		else 
		{
			setDisplayItem('spanInsert,spanAssesscomp,spanQueryAll,spanConfirm,spanDelete,spanListPrint,spanListHidden','H');		
		}

		if(null != form1.tabId.value && "" != form1.tabId.value)
		{
			changeTab(form1.tabId.value);
		}
		else
		{
			changeTab(1);
		}
		if(null == form1.status.value || "" == form1.status.value){
			whatButtonFireEvent("insert");
		}
		//修改資料完畢，導入查詢頁面
		if('<%=obj.getErrorMsg()%>'=='送出完成')
		{			
			form1.action = "vcos0101f.jsp";
			form1.state.value = "queryAll";
			form1.submit();
		} else if('<%=obj.getErrorMsg()%>'=='評估完成')
		{
			form1.action = "vcos0301f.jsp";
			form1.state.value = "queryAll";
			form1.submit();
		}
		//if("" == form1.formType.value)
		//{
		//	form1.formType.value = "vcos0101";
		//}
}

function queryOne(id)
{
	form1.id.value=id;
	form1.state.value="queryOne";
	beforeSubmit();
	form1.submit();
}

function changeTab(tabId) 
{		
	form1.tabId.value = tabId;

	document.getElementById("t1").className = "tab_border2";
	if(isObj(document.getElementById("t2")))
	{
		document.getElementById("t2").className = "tab_border2";
	}
	document.getElementById("aTab1").className = "";
	
	if(isObj(document.getElementById("aTab2")))
	{
		document.getElementById("aTab2").className = "";
	}
	
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	
	if(tabId == 2)
	{
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";			
	}
	else
	{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
	//if("1"==tabId)
	//{
	//	form1.formType.value = "vcos0101";
	//}
}	

function checkText()
{
	var conSequenceStr="";	
	for(var i=0;i<form1.ispublconsumer.length;i++)
	{
		if(form1.ispublconsumer[i].checked){
			if("Y" == form1.ispublconsumer[i].value){
				if(isObj(form1.publconsumerDate)){
					var publconsumerDates = form1.publconsumerDate;
					for (var j=0; j<publconsumerDates.length; j++) {
						publconsumerDates[j].className = 'field';
						publconsumerDates[j].disabled = false;
					}
				}
				document.all.item('publconsumerDate').disabled = false;
				document.all.item('publconsumerDate').className = 'field';	
			}
		if(form1.ispublconsumer[i].checked){
			if("N" == form1.ispublconsumer[i].value || ""== form1.ispublconsumer[i].value){
				if(isObj(form1.publconsumerDate)){
					var publconsumerDates = form1.publconsumerDate;
					for (var j=0; j<publconsumerDates.length; j++) {
						publconsumerDates[j].className = 'field_RO';
						publconsumerDates[j].disabled = true;
					}
				}
				document.all.item('publconsumerDate').disabled = true;
				document.all.item('publconsumerDate').className = 'field_RO';
				document.all.item('publconsumerDate').value = "";
				}
			}
		}
	}			
}

function upload(type)
{
	 var prop='';
	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();

     var url="../../home/popManyUploadSimple.jsp?";
         url+="fileKind=COS";
         url+="&systemType="+type;
         url+="&uploadId="+form1.id.value;
         url+="&dbName=Cos7001Db";         
	 returnWindow = window.open(url,'上傳檔案',prop);
}

function onClickButton()
{
	if(form1.formType.value == "") 
	{
		form1.formType.value = "vcos0101";
	}
	
	var action = form1.formType.value+"f.jsp?formType="+form1.formType.value;
	form1.action = action;
	form1.state.value = "queryAll";
	form1.submit();
	chk = false;	
}

function beforeInit() 
{	
	
}
</script>
</head>
<body topmargin="0" onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()">
	<table width="100%" cellspacing="0" cellpadding="0">
		<!--Toolbar區============================================================-->
		<tr><td nowrap class="bgToolbar" style="text-align:left">
	    		<input type="hidden" name="id" value="<%=obj.getId()%>">	
				<input type="hidden" name="state" value="<%=obj.getState()%>">			
				<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">			
				<input type="hidden" name="userID" value="<%=User.getUserId()%>">
				<input type="hidden" name="userName" value="<%=User.getUserName()%>">
				<input type="hidden" name="actionType" value="<%=obj.getActionType()%>">
				<input type="hidden" name="formType" value="<%=obj.getFormType()%>">
				<input type="hidden" class="field_Q" name="tabId" value ="<%=obj.getTabId()%>">
				<jsp:include page="../../home/toolbar.jsp" />		
				<span id="spanPauseSave">
					<input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
				</span>
				
				<span id="spanDoSend">
					<input class="toolbar_default" type="button" followPK="false" name="doSend" value="送　出" onClick="whatButtonFireEvent(this.name)">&nbsp;
				</span>
				<span id="spanAssesscomp">
						<input class="toolbar_default" type="button" followPK="false" name="assesscomp" value="評估完成" onClick="whatButtonFireEvent(this.name)">&nbsp;
				</span>
				
				<span id="spanReturn">
					<input class="toolbar_default"  type="button"  name="return" value="返　回" onclick="onClickButton()">&nbsp;
		    	</span>				
		</td></tr>
				<tr>
					<td nowrap>
					  <% request.setAttribute("QueryBean",obj);%>
					  <jsp:include page="../../home/page_row.jsp" />
					</td>
				</tr>
	</table>	
		<TABLE CELLPADDING="0" CELLSPACING="0" valign="top">
			<tr>
				<td nowrap ID="t1" CLASS="tab_border1" ><a id="aTab1" href="#" onClick="changeTab(1);">警訊登錄</a></td>
				<% if("vcos0301".equals(obj.getFormType()) || "vcos0401".equals(obj.getFormType())){ %>	
		    	<td nowrap ID="t2" CLASS="tab_border2" ><a id="aTab2" href="#" onClick="changeTab(2);">警訊評估</a></td>
		    	<% }%>
			</tr>
		</TABLE>
		<!--Form區=======================================================================================================-->
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
    		<td nowrap class="bg">	
    		<div id="formContainer" style="height:auto">
			<table id="Tab1" width="100%" align="center" class="table_form">
				<tr>
					<td nowrap class="td_form" width="15%">案件編號：</td>
					<td nowrap class="td_form_white" width="35%">
		                <input class="field_RO" type="text"  name="applNo" id="applNo"  maxlength="20" size="50" value="<%=obj.getApplNo()%>">
					</td>
					<td nowrap class="td_form" width="15%">案件狀態：</td>
					<td nowrap class="td_form_white" width="35%">
						<input class="field_RO" type="hidden"  name="status" id="status"  maxlength="20" size="50" value="<%=obj.getStatus()%>">
						<input class="field_RO" type="text"  name="statusCh" id="statusCh"  maxlength="20" size="50" value="<%=obj.getStatusCh()%>">
						
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">產品類別：</td>
					<td nowrap class="td_form_white" colspan="3">
		              	 <input type="text" class="field_RO" name="prodtype" id="prodtype" maxlength="20" size="10" value="<%=obj.getProdtype()%>">
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">品名：</td>
					<td nowrap class="td_form_white" colspan="3">
		              	 <textarea class="field" name="chProduct" cols="65" rows="2" ><%=obj.getChProduct()%></textarea>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">資料收集日期：</td>
					<td nowrap class="td_form_white" colspan="3">
		               <%=View.getPopCalendar("field","dataRevDate",obj.getDataRevDate())%>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">發佈單位：</td>
					<td nowrap class="td_form_white" >
						<%=View.getPopCode("field","publDept",obj.getPublDept(),obj.getPublDeptName(),"","CONPUBLDEPT","publDeptCodeId",obj.getPublDeptCodeId(),null,false,"","publCountry")%>
					</td>
					<td nowrap class="td_form">發佈單位國家：</td>
					<td nowrap class="td_form_white" >
		                <select class="field" name="publCountry" id="publCountry">
							<%=View.getOptionCodeKind("COUC", obj.getPublCountry())%>
						</select>
					</td>
				</tr>
				<tr>
				    <td nowrap class="td_form">產地：</td>
					<td nowrap class="td_form_white" colspan="3">					
		                <input type="text" class="field" name="manufactorCountry" id="manufactorCountry" maxlength="50" size="80" value="<%=obj.getManufactorCountry()%>" >
					</td>			
				</tr>
				<tr>
				    <td nowrap class="td_form">化粧品項目：</td>
					<td nowrap class="td_form_white" colspan="3">
		                <select class="field" name="ingredient" id="ingredient">
							<%=View.getOptionCodeKind("CCI", obj.getIngredient())%>
						</select>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">品牌/廠商：</td>
					<td nowrap class="td_form_white" colspan="3">
		                <input type="text" class="field" name="brand" id="brand" maxlength="50" size="80" value="<%=obj.getBrand()%>"  />
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">狀況：</td>
					<td nowrap class="td_form_white" >
		                <select class="field" name="situation" id="situation">
							<%=View.getOptionCodeKind("CONWARNING", obj.getSituation())%>
						</select>
					</td>
					<td nowrap class="td_form">發佈日期：</td>
					<td nowrap class="td_form_white" >
		                <%=View.getPopCalendar("field","publDate",obj.getPublDate())%> 
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">訊息主題：</td>
					<td nowrap class="td_form_white" colspan="3">
		                <select class="field" name="subjecttype" id="subjecttype">
							<%=View.getOptionCodeKind("COSSJTYPE", obj.getSubjecttype())%>
						</select>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">資訊內容摘要：</td>
					<td nowrap class="td_form_white" colspan="3">
		                <textarea class="field" name="contextsummary" cols="65" rows="5" ><%=obj.getContextsummary()%></textarea>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">產品批號：</td>
					<td nowrap class="td_form_white" colspan="3">
		                <textarea class="field" name="lotNo" cols="65" rows="5" ><%=obj.getLotNo()%></textarea>
					</td>
				</tr>
				<tr>
				<td nowrap class="td_form">資料來源：</td>
					<td nowrap class="td_form_white" colspan="3">
		                <input type="text" class="field" name="datasourWebSite" maxlength="500" size="80" value="<%=obj.getDatasourWebSite()%>" />
					</td>
				</tr>		
			</table>
			<table id="Tab2" width="100%" align="center" class="table_form">
			<tr>
						<td nowrap class="td_form" width="20%">評估日期：</td>
						<td nowrap class="td_form_white" colspan="3">
			               <%=View.getPopCalendar("field","estimateDate",obj.getEstimateDate())%>
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form" width="20%">產品是否進口：</td>
						<td nowrap class="td_form_white" colspan="3">
							<%=View.getRadioBoxTextOption("field","isImport","Y;是;N;否",obj.getIsImport())%>
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form" width="20%">是否發佈新聞稿：</td>
						<td nowrap class="td_form_white" colspan="3">
							<%=View.getRadioBoxTextOption("field","ispublnews","Y;是;N;否",obj.getIspublnews())%>
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form" width="20%">是否發佈消費者知識服務網：</td>
						<td nowrap class="td_form_white" colspan="3"  >
							<%=View.getRadioBoxTextOption("field","ispublconsumer","Y;是;N;否",obj.getIspublconsumer(),"checkText();" )%>
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form" width="20%">消費者知識服務網上架日期：</td>
						<td nowrap class="td_form_white" colspan="3" >
			               <%=View.getPopCalendar("field","publconsumerDate",obj.getPublconsumerDate())%>
						</td>
					</tr>
					<tr>
					   <td nowrap class="td_form">燈號：</td>
						<td nowrap class="td_form_white" >
			                <select class="field" name="lamp" id="lamp">
								<%=View.getOptionCodeKind("CONLAMP", obj.getLamp())%>
							</select>
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form">查詢單位：</td>
						<td nowrap class="td_form_white" colspan="3">
							<input type="text" class="field_RO" name="searchdept" id="searchdept" maxlength="20" size="50" value="<%=obj.getSearchdept()%>">
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form">備註：</td>
						<td nowrap class="td_form_white" colspan="3">
			              	 <input type="text" class="field" name="remark" id="remark" maxlength="20" size="50" value="<%=obj.getRemark()%>">
						</td>
					</tr>		
					<tr>
						<td nowrap class="td_form">後續處理：</td>
						<td nowrap class="td_form_white" colspan="3">
							<select class="field" name="aftereffect" id="aftereffect">
								<%=View.getOptionCodeKind("DRGEFFECT", obj.getAftereffect())%>
							</select>
						</td>
					</tr>					
					<tr>
		       			<td nowrap class="td_form">附件(監控附件)</td>
		       			<td nowrap class="td_form_white" colspan="3">  
		         			<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	             			<thead id="listTHEAD">           
	           			<tr>
		         			<th class="listTH" width="10%" >No.</th>
		          			<th class="listTH" width="30%">檔案名稱</th>
		          			<th class="listTH" width="50%">檔案說明</th>
		          			<th class="listTH" width="10%">
		          			<span id="spanDoUpload">
		             			<input class="toolbar_default" type="button" followPK="false" name="doUpload1" value="附件上傳" onClick="upload('COS040001')">
		          			</span>
		          			</th>
	           			</tr>
	           			</thead>
	           			<tbody>
			      			<%=obj.getAddFileVcos0100()%>
			   			</tbody>
               			</table>    
		       			</td>
		   			</tr>
				</table>		
			</div>	
			</td></tr>
		</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	form1.actionType.value = buttonName;
		
	switch (buttonName)	{
	case "clear":
		setFormItem("pauseSave,doSend,assess,assesscomp","R");
		break;
	case"insert":
		setFormItem("pauseSave","O");
		break;
	case"update":
		setFormItem("pauseSave,assess,assesscomp","O");
		setFormItem("doSend","R");
		if("vcos0301"==form1.formType.value){
			form1.searchdept.value="06";
			}
		break;
	case "pauseSave":		
		if(null==form1.id.value ||  ""== form1.id.value){
			form1.state.value = "insert";
			setBeforePageUnload(false);
			beforeSubmit();
			form1.submit();			
		}else{		
			form1.state.value = "update";
			setBeforePageUnload(false);
			beforeSubmit();
			form1.submit();
		}
		break;
	case "doSend":
		form1.state.value = "update";
		setBeforePageUnload(false);
		checkField();
		//beforeSubmit();
		//form1.submit();
		break;
	case "assesscomp":
		form1.state.value = "update";
		setBeforePageUnload(false);
		beforeSubmit();
		form1.submit();
		break;
	}
}
</script>
</html>