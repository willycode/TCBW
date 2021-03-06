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
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DYNAMIC0201R" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.dynamic.DYNAMIC0201R">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%


if ("queryOne".equals(obj.getState()))
{
   obj = (com.kangdainfo.tcbw.view.dynamic.DYNAMIC0201R)obj.queryOne();
}
else if("variantSave".equals(obj.getState()))
{
	try
	{
		obj.doSaveOrUpdate();
		obj.setErrorMsg("存檔成功!");
		obj = (com.kangdainfo.tcbw.view.dynamic.DYNAMIC0201R)obj.queryOne();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) 
{
	obj.delete();
	obj.setErrorMsg("刪除成功!");
}

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

function checkField()
{
	var alertStr = "";
	
	if(form1.state.value == "variantSave" )
	{
		alertStr += checkEmpty(form1.variantName,"常用變式名稱");
		form1.htmlValue.value = getTable1Value();
	}

	if(alertStr.length!=0){ alert(alertStr); return false; }

	if(form1.state.value == "variantSave" && checkVariant()){
		if(confirm("變式名稱已存在，是否要更新變式?")){
		}else{
			return false;
		}
	}
	//beforeSubmit();
	form1.submit();
	return true;
}

function init() 
{
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanListPrint,spanListHidden","H");
	document.all.item("delete").disabled = false;
	if("刪除成功!"=='<%=obj.getErrorMsg()%>'){
		setAllClearQ();
		document.all.item("state").value="init";
	}
	if(document.all.item("state").value=="init"){
		var column=document.all.item("status");
	    for(var j=10;j<column.length;j++)	        
	    {	     
	    	column[j].checked= true;        
	    }
	} 
}

function queryOne(id)
{
	form1.id.value = id;
 	form1.state.value = "queryOne";
	form1.submit();
}

var popWinName;
function getDynamicList()
{
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("../dynamic/dynamicList.jsp?kind=drg02",'popWinE',prop);		
}

function checkVariant(){  
	var q = form1.variantName.value + "&v=drg02";	
	var x = getRemoteData('../../ajax/jsonCheckVariant.jsp',q);
	if(x!=null&&x.length>0)
	{
		var json = JSON.parse(x);	
		if(json.obj0 == "Y"){
			return true;
		}	
	}
	return false;
}

function getTable1Value(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;
	var returnStr ="";
	for(var i=0; i< arrLen; i++){
		var obj = arrObj[i];
		if(""==obj.name) break; // 只需要取到table1內容
		if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="select-one")){							
			if("" != obj.value ) returnStr += (obj.type+":"+obj.name+":"+obj.value+";");
		}else if((obj.type=="checkbox")||(obj.type=="radio")){		
			if(true == obj.checked ){				
				returnStr += (obj.type+":"+obj.name+":"+obj.value+";");
			}
		}			 	  	
	}
	return returnStr;
}
</script>
</head>
<body  onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
    <input type="hidden" name="id" value="">	
    <input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	<span id="spanPrint">
    	<input class="toolbar_default" id="print" type="button" followPK="false" name="print" value="列     印">&nbsp;
    </span>
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
	<span id="spanVariantList">
    	<input class="toolbar_default"  type="button" followPK="false" name="variantList" value="載入常用變式"  onClick="getDynamicList();">&nbsp;
    </span>
	<span id="spanVariantSave">
	   <input class="toolbar_default" type="button" followPK="false" name="variantSave" value="常用變式存檔" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span> 
     <jsp:include page="../../home/toolbar.jsp" />
</td>
</tr>
<!--Form區============================================================-->
<tr>

<td nowrap class="bg">
<div id="formContainer" style="height:auto">
<table id="table1" class="table_form" width="100%">
	<tr>
		<td nowrap class="td_form" >常用變式名稱：</td>
		<td class="td_form_white" colspan="3">
		<input class="field_Q" type="text" name="variantName" size="50"  maxlength="50" value="<%=obj.getVariantName()%>"/>
		<input name="htmlValue" type="hidden" >
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">常用變式說明：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="variantExplain" size="80" maxlength="255" value="<%=obj.getVariantExplain()%>"/>
		</td>
	</tr> 
	<tr>
		<td nowrap class="td_form" >案件編號：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="applNoS" value="<%=obj.getApplNoS()%>"/> ~ 
			<input class="field_Q" type="text" name="applNoE" value="<%=obj.getApplNoE()%>"/>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">案件狀態：</td>
		<td nowrap class="td_form_white" colspan="3">
		    <%=View.getCheckBoxOption("field_Q", "status", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0310'  order by codeId", obj.getStatus(),5)%>					
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">收案日期：</td>
		<td nowrap class="td_form_white" width="35%">
			<%=View.getPopCalendar("field_Q","enrolledDateS",obj.getEnrolledDateS())%>~
			<%=View.getPopCalendar("field_Q","enrolledDateE",obj.getEnrolledDateE())%>	
		</td>
		<td nowrap class="td_form" width="15%">通報中心接獲通報日期：</td>
		<td nowrap class="td_form_white" width="35%">
			<%=View.getPopCalendar("field_Q","notifierRepDateS",obj.getNotifierRepDateS())%>~
			<%=View.getPopCalendar("field_Q","notifierRepDateE",obj.getNotifierRepDateE())%>		
		</td>
	</tr>		
	<tr>
		<td nowrap class="td_form">發現日期：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getPopCalendar("field_Q","occurDateS",obj.getOccurDateS())%>~
			<%=View.getPopCalendar("field_Q","occurDateE",obj.getOccurDateE())%>		
	    </td>
	</tr>
	<tr>
		<td nowrap class="td_form">服務機構：</td>
		<td nowrap class="td_form_white" colspan="3">
	  	    <input class="field_Q" type="text" name="notifierDept1" size="30"  value="<%=obj.getNotifierDept1()%>" />
	  	    <input class="field_Q" type="text" name="notifierDept2" size="30"  value="<%=obj.getNotifierDept2()%>" />
	  	    <input class="field_Q" type="text" name="notifierDept3" size="30"  value="<%=obj.getNotifierDept3()%>" />
		</td>
	</tr>
			
	<tr>
		<td nowrap class="td_form">通報者屬性：</td>
		<td nowrap class="td_form_white">
			<%=View.getCheckBoxOption("field_Q", "notifierType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", obj.getNotifierType())%>					
		</td>
		<td nowrap class="td_form">通報事件後果：</td>
		<td nowrap class="td_form_white">
			<%=View.getCheckBoxTextOption("field_Q","conSequenceDrg4001","1;藥效改變;2;不良反應發生",obj.getConSequenceDrg4001())%>				
		</td>
	</tr>
			
	<tr>
		<td nowrap class="td_form">許可證字號：</td>
		<td nowrap class="td_form_white">
			<select name="permitKey" class="field_Q" >
				 <%=View.getOptionCodeKind("DRGPKID", obj.getPermitKey())%>
			</select>
			<input class="field_Q" type="text" name="permitNo" size="6" value="<%=obj.getPermitNo()%>" />號
		</td>
		<td nowrap class="td_form">學名：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="scientificName" size="40"  value="<%=obj.getScientificName()%>"/>
		</td>
	</tr>
			
	<tr>
		<td nowrap class="td_form">商品名：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="productName" size="40"  value="<%=obj.getProductName()%>"/>
		</td>
		<td nowrap class="td_form">申請商：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="applicationName" size="40"  value="<%=obj.getApplicationName()%>"/>
		</td>
	</tr>
			
	<tr>
		<td nowrap class="td_form">製造廠：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="manufactorName" size="40"  value="<%=obj.getManufactorName()%>"/>
		</td>
		<td nowrap class="td_form">製造廠國別：</td>
		<td nowrap class="td_form_white">
			
		</td>
	</tr>			
	<tr>
		<td nowrap class="td_form">批號：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="manufactorNo" size="40"  value="<%=obj.getManufactorNo()%>"/>
		</td>				
	</tr>
	<tr>
		<td nowrap class="td_form">劑型：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getRadioBoxOption("field_Q", "medModel", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGFLN' order by codeId", obj.getMedModel(),null)%>			
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">藥品成分資訊：</td>
		<td nowrap class="td_form_white" colspan="3">
		<%=View.getRadioBoxTextOption("field_Q","medNti","1;是;2;否",obj.getMedNti())%>NTI Drugs  
		<br>藥理治療分類(ATC code)：	 
		<input class="field_Q" type="text" name="medAtcCode" size="20" value="<%=obj.getMedAtcCode()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報事件後果：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getCheckBoxTextOption("field_Q","conSequenceDrg4004","1;藥效改變;2;不良反應發生、強度增強或頻率增加",obj.getConSequenceDrg4004())%>
		</td>
	</tr>
	<tr>		
		<td nowrap class="td_form">療效不等評估結果：</td>
		<td nowrap class="td_form_white" colspan="3">	
			 <%=View.getCheckBoxOption("field_Q", "assessResult", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG2RKL' order by codeId", obj.getAssessResult(),6)%>					    		    
		</td>
	</tr>
	<tr>				
		<td nowrap class="td_form">間隔天數：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="intervalDaysS" size="3" maxlength="3" value="<%=obj.getIntervalDaysS()%>"/>天 ~
			<input class="field_Q" type="text" name="intervalDaysE" size="3" maxlength="3" value="<%=obj.getIntervalDaysE()%>"/>天
		</td>
		<td nowrap class="td_form">提報諮議會</td>
		<td nowrap class="td_form_white">
		<%=View.getRadioBoxTextOption("field_Q","isCouncilYn","1;是;2;否",obj.getIsCouncilYn())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">發信日期：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getPopCalendar("field_Q","notifyDateS",obj.getNotifyDateS())%> ~  
			<%=View.getPopCalendar("field_Q","notifyDateE",obj.getNotifyDateE())%> 
		</td>
	</tr>
	</table>
	<table class="table_form" width="100%">
	<tr>
		<td nowrap class="td_form_left" colspan="4">匯出欄位</td>
	</tr>
	    <%=obj.exportField(obj.getCodeValue())%>
	</table>
	
	</div>	
</td>
</tr>

</table>
</form>
</body>
<script type="text/javascript">

localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName)
	{
	   case "variantSave":
		    form1.state.value = "variantSave";
		    checkField();
		    break;
	}
}

$(document).ready(function()
{        
	$("#print").click(function()
	{
		var alertStr = "";
		if(alertStr.length!=0){ alert(alertStr); return false; }

        document.all.item("applNo4001").checked= true;
			
		form1.action = "dynamic0201p.jsp" ;
		form1.target = "_blank";
		form1.submit();
		form1.action = "dynamic0201r.jsp";
		form1.target = "_self";
	});
});


$('input[checkbox-group]').click(function(){
	if( $(this).is("[main]") ){
		var groupId = $(this).attr('checkbox-group');
		$('input[checkbox-group='+groupId+']').attr('checked', $(this).is(':checked') );
	}
});

</script>
</html>
