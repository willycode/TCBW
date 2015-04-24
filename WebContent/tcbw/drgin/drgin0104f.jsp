<!--
程式目的：藥品不良品通報-檢視藥證系統
程式代號：drgin0104
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0104F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
request.setCharacterEncoding("UTF-8");
String permitNo = Common.get(request.getParameter("permitNo"));
String permitKey = Common.get(request.getParameter("permitKey"));

if(permitKey!="") obj.setQ_permitKey(permitKey);
if(permitNo!="") obj.setQ_permitNo(permitNo);

obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0104F)obj.queryOne();
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

function checkField()
{
	var alertStr = "";
	if(alertStr.length!=0){ alert(alertStr); return }	

	beforeSubmit();
	form1.submit();
}


function init() 
{
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
	<%=obj.getVW_ForADR_BIGKND1()%>
	<%=obj.getVW_ForADR_MAINCING()%>
	setAllReadonly();
}

//劑型/包裝
var num1 = 1;
function addVW_ForADR_BIGKND1(id,v1,v2)
{
	var count1;
	count1 = num1++;		
	var newItem ="<tr id=\"VW_ForADR_BIGKND1"+count1+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='LICID' value=\""+(id!=null?id:'')+"\">";
	newItem +="<input id ='DOES"+count1+"' class=\"field\" type=\"text\" name=\"DOES"+"\" size=\"10\"  maxlength=\"30\"   value=\""+(v1!=null?v1:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='PACKMA"+count1+"' class=\"field\" type=\"text\" name=\"PACKMA"+"\" size=\"20\"  maxlength=\"30\" value=\""+(v2!=null?v2:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";	

	$("#attVW_ForADR_BIGKND1View").append(newItem);
}

//劑型/包裝
var num2 = 1;
function addVW_ForADR_MAINCING(id,v1,v2,v3)
{
	var count2;
	count2 = num2++;		
	var newItem ="<tr id=\"VW_ForADR_MAINCING"+count2+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='LICID' value=\""+(id!=null?id:'')+"\">";
	newItem +="<input id ='INGRMA"+count2+"' class=\"field\" type=\"text\" name=\"INGRMA"+"\" size=\"50\"  maxlength=\"30\"   value=\""+(v1!=null?v1:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='INGRDT"+count2+"' class=\"field\" type=\"text\" name=\"INGRDT"+"\" size=\"10\"  maxlength=\"30\" value=\""+(v2!=null?v2:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='UNITMA"+count2+"' class=\"field\" type=\"text\" name=\"UNITMA"+"\" size=\"10\"  maxlength=\"30\" value=\""+(v3!=null?v3:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";	

	$("#attVW_ForADR_MAINCINGView").append(newItem);
}


</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="isAlreadyAutoSave">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    </td>
</tr>
</table>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">  
	<table class="table_form" width="100%" align="center" >
		<tr>
			<td nowrap class="td_form_left" colspan="4">藥證資訊</td>
	    </tr>
		<tr>
			<td nowrap class="td_form">申請商統一編號</td>
			<td nowrap class="td_form_white" colspan="3">
               <input type="text" name="appunno" size="10" value="<%=obj.getAppunno()%>" />
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">申請商名稱</td>
			<td nowrap class="td_form_white">
               <input type="text" name="appname" size="50" value="<%=obj.getAppname()%>" />
			</td>
			<td nowrap class="td_form">製造廠名稱</td>
			<td nowrap class="td_form_white">
               <input type="text" name="factname" size="50" value="<%=obj.getFactname()%>" />
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">申請商地址</td>
			<td nowrap class="td_form_white">
               <input type="text" name="appaddr" size="50" value="<%=obj.getAppaddr()%>" />
			</td>
			<td nowrap class="td_form">製造廠地址</td>
			<td nowrap class="td_form_white">
               <input type="text" name="factaddr" size="50" value="<%=obj.getFactaddr()%>" />
			</td>					
		</tr>
		<tr>
			<td nowrap class="td_form">申請商電話</td>
			<td nowrap class="td_form_white">
               <input type="text" name="appotel" size="15" value="<%=obj.getAppotel()%>" />
			</td>	
			<td nowrap class="td_form">製造廠國別</td>
			<td nowrap class="td_form_white">
               <input type="text" name="factcidma" size="5" value="<%=obj.getFactcidma()%>" />
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">許可證字號</td>
			<td nowrap class="td_form_white" colspan="3">
               <input type="text" name="liceid" size="15" value="<%=obj.getLiceid()%>" />
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">中文品名</td>
			<td nowrap class="td_form_white" colspan="3">
               <input type="text" name="chname" size="150" value="<%=obj.getChname()%>" />
			</td>					
		</tr>
		<tr>
			<td nowrap class="td_form">英文品名</td>
			<td nowrap class="td_form_white" colspan="3">
               <input type="text" name="enname" size="150" value="<%=obj.getEnname()%>" />
			</td>					
		</tr>
		<tr>
		    <td nowrap class="td_form">藥品劑型/包裝</td>			
			<td nowrap class="td_form_white"  colspan="3">
               <table class="table_form" width="50%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="20%">劑型</th>
		         <th class="listTH" width="30%">包裝</th>
	           </tr>
	           </thead>
	           <tbody id="attVW_ForADR_BIGKND1View">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">藥品類別</td>
			<td nowrap class="td_form_white" width="35%">
               <input type="text" name="drugkc" size="20" value="<%=obj.getDrugkc()%>" />
			</td>
			<td nowrap class="td_form" width="15%">主成分略述</td>
			<td nowrap class="td_form_white" width="35%">
               <input type="text" name="ingrma" size="50" value="<%=obj.getIngrma()%>" />
			</td>					
		</tr>
		<tr>
		    <td nowrap class="td_form">成分明細</td>			
			<td nowrap class="td_form_white"  colspan="3">
               <table class="table_form" width="80%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="60%">成分名稱</th>
		         <th class="listTH" width="10%">含量</th>
		         <th class="listTH" width="10%">含量單位</th>
	           </tr>
	           </thead>
	           <tbody id="attVW_ForADR_MAINCINGView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發證日期</td>
			<td nowrap class="td_form_white">
               <input type="text" name="gidate" size="20" value="<%=obj.getGidate()%>" />
			</td>
			<td nowrap class="td_form">有效日期</td>
			<td nowrap class="td_form_white">
               <input type="text" name="efdate" size="20" value="<%=obj.getEfdate()%>" />
			</td>						
		</tr>
		<tr>
			<td nowrap class="td_form">註銷日期</td>
			<td nowrap class="td_form_white">
               <input type="text" name="candate" size="20" value="<%=obj.getCandate()%>" />
			</td>
			<td nowrap class="td_form">註銷狀態</td>
			<td nowrap class="td_form_white">
               <input type="text" name="canmark" size="20" value="<%=obj.getCanmark()%>" />
			</td>						
		</tr>
	</table>
	</td>
</tr>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	
	}
}
</script>
</body>
</html>
