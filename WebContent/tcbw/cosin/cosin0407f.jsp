<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0404F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
request.setCharacterEncoding("UTF-8");
String permitKey = Common.get(request.getParameter("permitKey"));
String permitNo = Common.get(request.getParameter("permitNo"));
obj.setPermitKey(permitKey);
obj.setPermitNo(permitNo);

obj = (com.kangdainfo.tcbw.view.cosin.COSIN0404F)obj.queryOne();
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
function checkField(){
	var alertStr = "";
	if(alertStr.length!=0){ alert(alertStr); return; }	
	beforeSubmit();
	form1.submit();
}

function init(){
	<%=obj.getVW_ForADR_BIGKND3()%>
	setAllReadOnly();
}

var num1 = 1;
function addVW_ForADR_BIGKND3(id,v1,v2){
	var count1;
	count1 = num1++;		
	var newItem = "<tr id=\"VW_ForADR_BIGKND3" + count1 + "\">";
	newItem += "<td class='td_form_white'>";
	newItem += "<input type='hidden' name='LICID' value=\"" + (id!=null?id:'') + "\">";
	newItem += "<input id='DOES" + count1 + "' readonly class=\"field\" type=\"text\" name=\"DOES" + count1 + "\" size=\"10\"  maxlength=\"30\" value=\"" + (v1!=null?v1:'') + "\">&nbsp;";
	newItem += "</td>";

	newItem += "<td class='td_form_white'>";
	newItem += "<input id ='PACKMA" + count1 + "' readonly class=\"field\" type=\"text\" name=\"PACKMA" + count1 + "\" size=\"20\"  maxlength=\"30\" value=\"" + (v2!=null?v2:'') + "\">&nbsp;";
	newItem += "</td>";
	newItem += "</tr>";	

	$("#attVW_ForADR_BIGKND3").append(newItem);
}

$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
});
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" autocomplete="off">

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar">
	<input type="hidden" name="permitKey" value="<%=obj.getPermitKey()%>">
	<input type="hidden" name="permitNo" value="<%=obj.getPermitNo()%>">
	
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
    <jsp:include page="../../home/toolbar.jsp">
		<jsp:param value="N" name="btnCopy"/>
    </jsp:include>
</td></tr>
</table>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" align="center" >
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">藥證資訊</td>
	    </tr>
		<tr>
			<td nowrap class="td_form">許可證字號</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="liceid" size="20" value="<%=obj.getLiceid()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">中文品名</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="chname" size="150" value="<%=obj.getChname()%>">
			</td>					
		</tr>
		<tr>
			<td nowrap class="td_form">外文品名</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="enname" size="150" value="<%=obj.getEnname()%>">
			</td>					
		</tr>
		<tr>
		    <td nowrap class="td_form">藥品劑型/包裝</td>			
			<td nowrap class="td_form_white" colspan="3">
				<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
				<thead id="listTHEAD">       
	           	<tr>
					<th class="listTH">劑型</th>
					<th class="listTH">包裝</th>
				</tr>
				</thead>
				<tbody id="attVW_ForADR_BIGKND3">
			   
				</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">化粧品類別</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="drugmcma" size="20" value="<%=obj.getDrugmcma()%>">
			</td>
								
		</tr>
		<tr>
			<td nowrap class="td_form">主成分略述</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="ingrma" size="80" value="<%=obj.getIngrma()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">申請商統一編號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="appunno" size="10" value="<%=obj.getAppunno()%>">
			</td>
			<td nowrap class="td_form">申請商名稱</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="appname" size="50" value="<%=obj.getAppname()%>">
			</td>					
		</tr>
		<tr>
			<td nowrap class="td_form">申請商地址</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="appaddr" size="50" value="<%=obj.getAppaddr()%>">
			</td>
			<td nowrap class="td_form">申請商電話</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="appotel" size="15" value="<%=obj.getAppotel()%>">
			</td>						
		</tr>
		<tr>
			<td nowrap class="td_form">製造廠名稱</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="factname" size="50" value="<%=obj.getFactname()%>">
			</td>
			<td nowrap class="td_form">製造廠國別</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="factcidma" size="5" value="<%=obj.getFactcidma()%>">
			</td>						
		</tr>
		<tr>
			<td nowrap class="td_form">製造廠地址</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="factaddr" size="50" value="<%=obj.getFactaddr()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發證日期</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="gidate" size="20" value="<%=obj.getGidate()%>">
			</td>
			<td nowrap class="td_form">有效日期</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="efdate" size="20" value="<%=obj.getEfdate()%>">
			</td>						
		</tr>
		<tr>
			<td nowrap class="td_form">註銷日期</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="candate" size="20" value="<%=obj.getCandate()%>">
			</td>
			<td nowrap class="td_form">註銷狀態</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="canmark" size="20" value="<%=obj.getCanmark()%>">
			</td>						
		</tr>
	</table>
	</div>
</td></tr>
</table>
</form>
</body>
</html>
