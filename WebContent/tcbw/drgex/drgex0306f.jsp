<!--
程式目的：藥品療效不等-廠商回覆
程式代號：drgex0306f
程式日期：1021125
程式作者：yuwen
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp"/>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0306F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
obj.setQ_inORout(User.getInORout());
if("init".equals(obj.getState())){
	obj.setQueryAllFlag("true");
}else if("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
	if("1".equals(obj.getActionType())){
		obj.setErrorMsg("廠商回覆完成");
		obj.setQueryAllFlag("false");
	}else{
		obj.setErrorMsg("暫存完成");
		obj.setState("queryOne");
		obj.setQueryAllFlag("true");
	}
}
if("true".equals(obj.getQueryAllFlag())){
	obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0306F)obj.queryOne();
	objList = (java.util.ArrayList) obj.doQueryAll();
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
<script type="text/javascript" src="./../../js/jquery.js"></script>
<script type="text/javascript">
var popWinName;
function checkField()
{
	var alertStr = "";
	if(alertStr.length!=0){ alert(alertStr); return false;}

	beforeSubmit();
	form1.submit();
	return true;
}

function init() 
{	
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden,spanUpdate,spanClear','H');
	if("out" != "<%=obj.getQ_inORout()%>"){
		setDisplayItem('spanMaintain1,spanConfirm','H');
	}else{
		whatButtonFireEvent("update");
		document.all.item("confirm").value = "暫　存";
	}
	
	if("updateSuccess" == "<%=obj.getState()%>"){
		form1.action = "drgex0306f.jsp";
		if("1" == form1.actionType.value){
			form1.action = "drgex0305f.jsp";
		}
		form1.state.value = "init";
		form1.submit();
	}
}

function toQuery(){
	form1.action = "drgex0305f.jsp";
	form1.state.value = "init";
	form1.submit();
}

function onClickButton(type){
	form1.actionType.value = type;
	form1.state.value = "update";
	setBeforePageUnload(false);
	beforeSubmit();
	form1.submit();
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
</div>
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="actionType" value="<%=obj.getActionType()%>">
	<input type="hidden" name="q_drg4001DbId" value="<%=obj.getQ_drg4001DbId()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
	<span id="spanMaintain1">
		<input class="toolbar_default" type="button" followPK="false" name="maintain1" value="回 覆 完 成" onClick="onClickButton(1)">&nbsp;
	</span>
	<span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="toQueryAll" value="返 回 查 詢" onClick="toQuery();">&nbsp;
	</span>
</tr>

<tr>
<td nowrap>
  <% request.setAttribute("QueryBean",obj);%>
  <jsp:include page="../../home/page_row.jsp" />
</td>
</tr>
</table>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    	<div id="formContainer" style="height:auto">
    		<table width="100%" align="center" class="table_form">
	    		<tr>
	    			<td nowrap class="td_form">製程管制與最終產品之結果檢討</td>
					<td nowrap class="td_form_white">
		               <textarea class="field" name="review1" cols="80" rows="5"><%=obj.getReview1()%></textarea>
					</td>
				</tr>
				<tr>
	    			<td nowrap class="td_form">原料(含來源)/包材或配方變更之檢討</td>
					<td nowrap class="td_form_white">
		               <textarea class="field" name="review2" cols="80" rows="5"><%=obj.getReview2()%></textarea>
					</td>
				</tr>
				<tr>
	    			<td nowrap class="td_form">製程/規格或分析方法變更之檢討</td>
					<td nowrap class="td_form_white">
		               <textarea class="field" name="review3" cols="80" rows="5"><%=obj.getReview3()%></textarea>
					</td>
				</tr>
				<tr>
	    			<td nowrap class="td_form">安定性監測計畫的結果與任何不良趨勢之檢討</td>
					<td nowrap class="td_form_white">
		               <textarea class="field" name="review4" cols="80" rows="5"><%=obj.getReview4()%></textarea>
					</td>
				</tr>
				<tr>
	    			<td nowrap class="td_form">一切顯著偏離或不相符(包括品質相關之客訴/回收…等)之<br>調查及實施CAPA後成果之檢討</td>
					<td nowrap class="td_form_white">
		               <textarea class="field" name="review5" cols="80" rows="5"><%=obj.getReview5()%></textarea>
					</td>
				</tr>
				<tr>
	    			<td nowrap class="td_form">任何其他先前產品製程/設備或環境改正行動之適當性檢討</td>
					<td nowrap class="td_form_white">
		               <textarea class="field" name="review6" cols="80" rows="5"><%=obj.getReview6()%></textarea>
					</td>
				</tr>
    		</table>
    	</div>
    </td>
</tr>
<!--List區============================================================-->
<tr><td nowrap class="bgList" colspan="2">
	<div id="listContainer">
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="listTHEAD">
		<tr>
			<th class="listTH">No.</th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">案件號碼</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">通報日期</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">藥品成分資訊</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">通報事件後果</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">不良反應等級</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">療效不等風險評估結果</a></th>
		</tr>
		</thead>
		<tbody id="listTBODY">
		<%
			boolean primaryArray[] = {true,false,false,false,false,false,false};
			boolean displayArray[] = {false,true,true,true,true,true,true};
			out.write(View.getQuerylist(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),true,null,null,"",false));
		%>
		</tbody>
	</table>
	</div>
</td></tr>
</table>
</form>
</body>
</html>
