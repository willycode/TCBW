<!--
程式目的：藥品通報案件綜合查詢作業
程式代號：drgin0001
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGIN0001" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){
		obj.setQueryAllFlag("true"); 
	}
}

if ("true".equals(obj.getQueryAllFlag())){	
	objList = (java.util.ArrayList) obj.doQueryAll();
	obj.setStateQueryOneSuccess();
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
function checkField(){
	var alertStr = "";
	alertStr += checkButton(form1.q_drgType, "案件類別");
	if(alertStr.length > 0){
		alert(alertStr);
		return false;
	}
	beforeSubmit();
	form1.state.value = "queryAll";
	return true;
}

function popReportForm(type) 
{
	var alertStr = "";
	alertStr += checkButton(form1.q_drgType, "案件類別");
	var drg01 = new Boolean(false);//不良品
	var drg02 = new Boolean(false);//療效不等
	var v1 ="";

	for (var i=0; i<form1.q_drgType.length; i++)
	{
	   if (form1.q_drgType[i].checked)
	   {
	      v1 = form1.q_drgType[i].value;
	  	
	      if(v1=="01")
		  {
        	  drg01 = true;
          }
          if(v1=="02")
          {
        	  drg02 = true;
              alertStr += checkRadioButton(form1.printType, "不良反應表單類別");
          }
	   }
	}
	
	if(alertStr.length > 0)
	{
		alert(alertStr);
		return false;
	}

	if(drg01==true && drg02==true)
	{
		form1.target = "_blank";
		form1.action = "drgin0001_1p.jsp";
		form1.submit();
		form1.target = "_self";
		form1.action = "drgin0001f.jsp";
		
	    setTimeout("all("+drg02+")",1000);
	}
	else if(drg01==true)
	{
		form1.target = "_blank";
		form1.action = "drgin0001_1p.jsp";
		form1.submit();
		form1.target = "_self";
		form1.action = "drgin0001f.jsp";
    }	
	else if(drg02==true)
	{
	    form1.target = "_blank";
		form1.action = "drgin0001_2p.jsp";
		form1.submit();
		form1.target = "_self";
		form1.action = "drgin0001f.jsp";
    }	

}

function all(drg02)
{
	if(drg02==true)
	{
	  form1.target = "saveContainerFrame1";
	  form1.action = "drgin0001_2p.jsp";
	  form1.submit();
	  form1.target = "_self";
	  form1.action = "drgin0001f.jsp";
	}
}


function init() {
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
}
function checkParentMainCode(input){
	if(isObj(input)){
		var id = input.id;
		var parent = $('#'+id.substring(0,2));
		if(isObj(parent)){
			if(input.checked){
				parent.attr('checked',true);
			}else{
				var children = $('[id^='+id.substring(0,2)+']');
				var isAllUnCheck = true;
				for(var i = 0;i<children.length;i++){
					if(children[i].checked&&children[i].id!=id.substring(0,2))isAllUnCheck=false;
				}
				if(isAllUnCheck)parent.attr('checked',false);
			}
		}
	}
}
function checkChildrenSubCode(input){
	if(isObj(input)){
		var id = input.id;
		var children = $('[id^='+id.substring(0,2)+']');
		for(var i = 0;i<children.length;i++){
			children[i].checked=input.checked;
		}
	}	
}
function queryOne(id, castType){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1250,height=720,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	if("01" == castType){
		returnWindow=window.open(getVirtualPath() + "tcbw/drgin/drgin0102q.jsp?isQuery=Y&id="+id,"",prop);
	}else if("02" == castType){
		returnWindow=window.open(getVirtualPath() + "tcbw/drgin/drgin0302f.jsp?isQuery=Y&id="+id+"&formType=0309","",prop);
	}
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<iframe name="saveContainerFrame1" width="0" height="0" frameborder="0">
</iframe>
<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">

	<input type="hidden" name="state" value="<%=obj.getState()%>">	
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">	
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢">&nbsp;
    </span>
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
    <span id="spanPrint">
    	<input class="toolbar_default" type="button" followPK="false" name="btnPrint" value="匯出EXCEL" style="width:80px" onClick="popReportForm();">&nbsp;
    </span>
</td>
</tr>

<tr>
    <td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">	
	<tr><td nowrap class="td_form_left" colspan="4">通報區</td>
	</tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">案件類別：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getCheckBoxTextOption("field_Q","q_drgType","01;藥品不良品;02;藥品療效不等;",obj.getQ_drgType())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">案件編號：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="11" maxlength="11" value="<%=obj.getQ_applNoS()%>">		
			~<input class="field_Q" type="text" name="q_applNoE" size="11" maxlength="11" value="<%=obj.getQ_applNoE()%>">		
		</td>
	</tr>  	
	<tr>
		<td nowrap width="15%" nowrap class="td_form">接獲通報日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",obj.getQ_notifierRepDateS())%>~<%=View.getPopCalendar("field_Q","q_notifierRepDateE",obj.getQ_notifierRepDateE())%>				
		</td>
		<td nowrap width="15%" nowrap class="td_form">收案日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_enrolledDateS",obj.getQ_enrolledDateS())%>~<%=View.getPopCalendar("field_Q","q_enrolledDateE",obj.getQ_enrolledDateE())%>				
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">發現日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_occurDateS",obj.getQ_occurDateS())%>~<%=View.getPopCalendar("field_Q","q_occurDateE",obj.getQ_occurDateE())%>				
		</td>
		<td nowrap width="15%" nowrap class="td_form">服務機構：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_notifierDept" size="50" maxlength="100" value="<%=obj.getQ_notifierDept()%>">
		</td>
	</tr>
    <tr><td nowrap class="td_form_left" colspan="4">藥品區</td></tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">許可證字號：</td>
		<td nowrap nowrap class="td_form_white">
		    <select name="q_permitKey" class="field_Q">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGPKID' order by codeId", obj.getQ_permitKey())%>
			</select>
			號：	<input class="field_Q" type="text" name="q_permitNo" size="6" maxlength="6" value="<%=obj.getQ_permitNo()%>">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">藥品品名：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_Product" size="50" maxlength="100" value="<%=obj.getQ_Product()%>">			
		</td>
	</tr>  	
	<tr>
		<td nowrap width="15%" nowrap class="td_form">有效成分/學名：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_ingredient" size="50" maxlength="100" value="<%=obj.getQ_ingredient()%>">	
		</td>
		<td nowrap width="15%" nowrap class="td_form">申請商：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applicationName" size="50" maxlength="100" value="<%=obj.getQ_applicationName()%>">	
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">製造廠：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_manufactorName" size="50" maxlength="100" value="<%=obj.getQ_manufactorName()%>">
		</td>
		<td nowrap width="15%" nowrap class="td_form">製造批號：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_manufactorNo" size="20" maxlength="20" value="<%=obj.getQ_manufactorNo()%>">
		</td>
	</tr>
    <tr><td nowrap class="td_form_left" colspan="4">不良品區</td></tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">不良品風險評估結果：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getCheckBoxOption("field_Q", "q_drgLev", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRKL' order by codeShortName", obj.getQ_drgLev())%>			
		</td>
		<td nowrap width="15%" nowrap class="td_form">不良品原因：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field_Q", "q_survey", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0109' order by codeId", obj.getQ_survey())%>		
		</td>
	</tr>  	
	<tr>
		<td nowrap width="15%" nowrap class="td_form">不良品缺陷：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<%=obj.getCheckBoxOption("field_Q", "q_mainCode", "q_subCode", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId", obj.getQ_mainCode(),obj.getQ_subCode())%>		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件狀態：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<select class="field_Q" name="q_status1">
    		   <%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGST1' order by codeId",obj.getQ_status1(), false, 1) %>
		    </select>					
		</td>
	</tr>
	<tr><td nowrap class="td_form_left" colspan="4">療效不等區</td></tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">藥品成份資訊：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="checkbox" name="q_medNti" value="Y" <%=obj.getQ_medNti().equals("Y")?"checked":""%> >NTI Drugs<br>
		    藥理治療分類(ATC code)：<input class="field_Q" type="text" name="q_medAtcCode" value="<%=obj.getQ_medAtcCode()%>" >
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報事件後果：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="checkbox" name="q_conSequence4" value="1" <%if(null != obj.getQ_conSequence4()){for(String conSequence : obj.getQ_conSequence4()){if("1".equals(conSequence)) out.print("checked"); else out.print("");}}%>>藥效改變
			<input class="field_Q" type="checkbox" name="q_conSequence4" value="2" <%if(null != obj.getQ_conSequence4()){for(String conSequence : obj.getQ_conSequence4()){if("2".equals(conSequence)) out.print("checked"); else out.print("");}}%>>不良反應發生、強度增強或頻率增加<br>		
		</td>
	</tr>  	
	<tr>
		<td nowrap width="15%" nowrap class="td_form">療效不等評估結果：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<%=View.getRadioBoxOption("field_Q", "q_assessResult", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG2RKL' order by codeId", obj.getQ_assessResult())%>		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件狀態：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<select class="field_Q" name="q_status2">
    		   <%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0310' order by codeId",obj.getQ_status2(), false, 1) %>
		    </select>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">表單類別：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
             <%=View.getRadioBoxTextOption("field_Q","printType","1;匯出格式(1);2;匯出格式(2);3;匯出格式(全);",obj.getPrintType())%><br>
		</td>
	</tr>
	</table>
	</div>	
</td></tr>


<!--List區============================================================-->
<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<tr><td nowrap><br></td></tr>

<tr>
<td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">案件類別</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">案件編號</a></th>		
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">藥品品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">申請商</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">製造廠</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">不良品缺陷/通報事件後果</a></th>
	    <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',9,false);" href="#">製造批號</a></th>
	    <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',10,false);" href="#">案件狀態</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,true,false,false,false,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,false,true,true,true,true,true,true,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center","center","center"};
		out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,null));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	case "queryAll":
		break;
	}
}
</script>
</html>