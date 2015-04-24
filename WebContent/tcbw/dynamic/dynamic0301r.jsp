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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DYNAMIC0301R" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.dynamic.DYNAMIC0301R">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%


if ("queryOne".equals(obj.getState()))
{
   obj = (com.kangdainfo.tcbw.view.dynamic.DYNAMIC0301R)obj.queryOne();
}
else if("variantSave".equals(obj.getState()))
{
	try
	{
		obj.doSaveOrUpdate();
		obj.setErrorMsg("存檔成功!");
		obj = (com.kangdainfo.tcbw.view.dynamic.DYNAMIC0301R)obj.queryOne();
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
	/*
	if(document.all.item("state").value=="init"){
		var column=document.all.item("q_status");
	    for(var j=1;j<column.length;j++)	        
	    {	     
	    	column[j].checked= true;        
	    }
	}
	*/
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
	popWinName = window.open("../dynamic/dynamicList.jsp?kind=drg03",'popWinE',prop);		
}

function checkVariant(){  
	var q = form1.variantName.value + "&v=drg03";	
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
		<td nowrap class="td_form" width="15%">案件編號：</td>
		<td nowrap class="td_form_white" width="35%">
			<input class="field_Q" type="text" name="q_applNoS" value="<%=obj.getQ_applNoS()%>"/> ~ 
			<input class="field_Q" type="text" name="q_applNoE" value="<%=obj.getQ_applNoE()%>"/>
	    </td>
		<td nowrap class="td_form" width="15%">案件狀態：</td>
		<td nowrap class="td_form_white" width="35%">
			<%=View.getCheckBoxOption("field_Q", "q_status", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGQTSTATUS' order by codeId", obj.getQ_status(),5)%>					
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">資料收集日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_dataRevDateS",obj.getQ_dataRevDateS())%>~
		    <%=View.getPopCalendar("field_Q","q_dataRevDateE",obj.getQ_dataRevDateE())%>		
		</td>
		<td nowrap class="td_form">發佈單位：</td>
		<td nowrap class="td_form_white">
			<select name="q_publDept" class="field_Q" >
			   <%=View.getOptionCodeKind("CONPUBLDEPT", obj.getQ_publDept())%>
		    </select>
		</td>				
	</tr>
			
	<tr>
		<td nowrap class="td_form">狀況：</td>
		<td nowrap class="td_form_white">
			<%=View.getCheckBoxOption("field_Q", "q_situation", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONWARNING' order by codeId", obj.getQ_situation(),5)%>								
		</td>
		<td nowrap class="td_form">發佈日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_publDateS",obj.getQ_publDateS())%>~
		    <%=View.getPopCalendar("field_Q","q_publDateE",obj.getQ_publDateE())%>		
		</td>				
	</tr>			
	<tr>
		<td nowrap class="td_form">商品名：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_chProduct" size="50" value="<%=obj.getQ_chProduct()%>"/>
		</td>						
	</tr>	
	<tr>		
		<td nowrap class="td_form">學名：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_scientificName" size="50" value="<%=obj.getQ_scientificName()%>"/>
		</td>				
	</tr>			
	<tr>
		<td nowrap class="td_form">廠商：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_druggist" size="50" value="<%=obj.getQ_druggist()%>"/>
		</td>			
	</tr>	
	<tr>
		<td nowrap class="td_form">製造廠：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_manufactorName" size="50" value="<%=obj.getQ_manufactorName()%>"/>
		</td>				
	</tr>		
	<tr>
		<td nowrap class="td_form">國內許可證：</td>
		<td nowrap class="td_form_white" colspan="3">
		    <select name="q_permitKey" class="field_Q" >
			   <%=View.getOptionCodeKind("DRGPKID", obj.getQ_permitKey())%>
		    </select>
		    <input class="field_Q" type="text" name="q_permitNo" size="6" value="<%=obj.getQ_permitNo()%>" />號			
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">國內許可證持有商：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_applicationName" size="50" value="<%=obj.getQ_applicationName()%>"/>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">產品批號：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_lotNo" size="50" value="<%=obj.getQ_lotNo()%>"/>
		</td>
	</tr>	
	<tr>
		<td nowrap class="td_form">品質異常型態：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getCheckBoxOption("field_Q", "q_qualitywarningtype", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGWNTP' order by codeId", obj.getQ_qualitywarningtype(),5)%>					
		</td>
	</tr>		
	<tr>
		<td nowrap class="td_form">回收型態：</td>
		<td nowrap class="td_form_white">
			<%=View.getCheckBoxOption("field_Q", "q_recycleType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRECTP' order by codeId", obj.getQ_recycleType(),5)%>					
		</td>
		<td nowrap class="td_form">是否草擬紅綠燈初稿：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","q_iswarning","1;是;2;否",obj.getQ_iswarning())%>
		</td>
	</tr>
			
	<tr>
		<td nowrap class="td_form">產品是否進口：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","q_isImport","1;是;2;否",obj.getQ_isImport())%>
		</td>
		<td nowrap class="td_form">產品未進口原因：</td>
		<td nowrap class="td_form_white">
			<%=View.getCommonRadioBoxOption("field_Q","q_nonImportReason","DRGNIMREASON",obj.getQ_nonImportReason(),"")%>		
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">燈號：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getCheckBoxOption("field_Q", "q_lamp", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONLAMP' order by codeId", obj.getQ_lamp())%>					
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">後續處理：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getCommonRadioBoxOption("field_Q","q_aftereffect","DRGEFFECT",obj.getQ_aftereffect(),"2")%>		
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

        document.all.item("applNo7001").checked= true;
			
		form1.action = "dynamic0301p.jsp" ;
		form1.target = "_blank";
		form1.submit();
		form1.action = "dynamic0301r.jsp";
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
