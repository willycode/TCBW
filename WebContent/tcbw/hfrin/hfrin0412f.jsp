<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0405F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
request.setCharacterEncoding("UTF-8");
String permitKey = Common.get(request.getParameter("permitKey"));
String permitNo = Common.get(request.getParameter("permitNo"));
obj.setPermitKey(permitKey);
obj.setPermitNo(permitNo);

obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0405F)obj.queryOne();
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
	if(alertStr.length!=0){ alert(alertStr); return }	
	beforeSubmit();
	form1.submit();
}

function init(){
	<%=obj.getQMS_view_HEAL_Funcn_compt()%>
	<%=obj.getQMS_view_HEAL_Funcn()%>
	
	setAllReadOnly();
}
/*
var num1 = 1;
function addQMS_view_HEAL_BAS_COMPT(id,v1){
	var count1;
	count1 = num1++;		
	var newItem = "<tr id=\"QMS_view_HEAL_BAS_COMPT" + count1 + "\">";
	newItem += "<td class='td_form_white'>";
	newItem += "<input type='hidden' name='DOC_NO' value=\"" + (id!=null?id:'') + "\">";
	newItem += "<input id='COMPT" + count1 + "' readonly class=\"field\" type=\"text\" name=\"COMPT" + count1 + "\" size=\"80\" value=\"" + (v1!=null?v1:'') + "\">&nbsp;";
	newItem += "</td>";
	newItem += "</tr>";	

	$("#attQMS_view_HEAL_BAS_COMPT").append(newItem);
}
*/
var num1 = 1;
function addQMS_view_HEAL_Funcn_compt(id,v1){
	var count1;
	count1 = num1++;		
	var newItem = "<tr id=\"QMS_view_HEAL_Funcn_compt" + count1 + "\">";
	newItem += "<td class='td_form_white'>";
	newItem += "<input type='hidden' name='DOC_NO' value=\"" + (id!=null?id:'') + "\">";
	newItem += "<input id='COMPT" + count1 + "' readonly class=\"field\" type=\"text\" name=\"COMPT" + count1 + "\" size=\"20\" value=\"" + (v1!=null?v1:'') + "\">&nbsp;";
	newItem += "</td>";
	newItem += "</tr>";	

	$("#attQMS_view_HEAL_Funcn_compt").append(newItem);
}


var num2 = 1;
function addQMS_view_HEAL_Funcn(id,v1,v2){
	var count2;
	count2 = num2++;
	
	var newItem = "<tr id=\"QMS_view_HEAL_Funcn" + count2 + "\">";
	newItem += "<td class='td_form_white'>";
	newItem += "<input type='hidden' name='FDOC_NO' value=\"" + (id!=null?id:'') + "\">";
	newItem += "<input id='FUNCN_NEED_TYPE_CONTENT" + count2 + "' readonly class=\"field\" type=\"text\" name=\"FUNCN_NEED_TYPE_CONTENT" + count2 + "\" size=\"50\" value=\"" + (v1!=null?v1:'') + "\">&nbsp;";
	newItem += "</td>"
	newItem += "<td class='td_form_white'>";
	newItem += "<input id='FUNCN_NEED_CONTENT" + count2 + "' readonly class=\"field\" type=\"text\" name=\"FUNCN_NEED_CONTENT" + count2 + "\" size=\"50\" value=\"" + (v2!=null?v2:'') + "\">&nbsp;";
	newItem += "</td>";
	newItem += "</tr>";	
	$("#attQMS_view_HEAL_Funcn").append(newItem);
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
			<td nowrap class="td_form" style="text-align:center" colspan="4">健康食品資訊</td>
	    </tr>
		<tr>
			<td nowrap class="td_form">許可證類別</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="AD_LICENSE_TYPE_CONTENT" size="20" value="<%=obj.getAD_LICENSE_TYPE_CONTENT()%>">
				<input type="hidden" name="DOCUT_NO" value="<%=obj.getDOCUT_NO()%>">
			</td>
			<td nowrap class="td_form">許可證字號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="AD_LICENSE_NO" size="20" value="<%=obj.getAD_LICENSE_NO()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">中文品名</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="CHINE_PRODUCT_NAME" size="80" value="<%=obj.getCHINE_PRODUCT_NAME()%>">
			</td>					
		</tr>
		<tr>
			<td nowrap class="td_form">外文品名</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="ENGLH_PRODUCT_NAME" size="80" value="<%=obj.getENGLH_PRODUCT_NAME()%>">
			</td>					
		</tr>
		<tr>
			<td nowrap class="td_form">發證日期</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="RATIFY_DATE" size="20" value="<%=obj.getRATIFY_DATE()%>">
			</td>
			<td nowrap class="td_form">有效日期</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="VALID_DATE" size="20" value="<%=obj.getVALID_DATE()%>">
			</td>						
		</tr>
		<tr>
			<td nowrap class="td_form">申請商</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="APPLY_MNFTY_NAME" size="50" value="<%=obj.getAPPLY_MNFTY_NAME()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造商</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="MNFTY_NAME1" size="50" value="<%=obj.getMNFTY_NAME1()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">證況</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="LICENSE_STATUS_CONTENT" size="50" value="<%=obj.getLICENSE_STATUS_CONTENT()%>">
			</td>
		</tr>
		<tr>		
			<td nowrap class="td_form_white" colspan="4">
				<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
				<thead id="COMPTlistTHEAD">       
	           	<tr>
					<th class="listTH">成分</th>
				</tr>
				</thead>
				<%-- 
				<tbody id="attQMS_view_HEAL_BAS_COMPT">
			   
				</tbody>
				--%>
				
				<tbody id="attQMS_view_HEAL_Funcn_compt">
			   
				</tbody>
				</table>
			</td>
		</tr>
		<tr>	
			<td nowrap class="td_form_white" colspan="4">
				<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
				<thead id="FuncnlistTHEAD">       
	           	<tr>
					<th class="listTH">保健功效</th>
					<th class="listTH">保健功效宣稱</th>
				</tr>
				</thead>
				<tbody id="attQMS_view_HEAL_Funcn">
			   
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
</html>
