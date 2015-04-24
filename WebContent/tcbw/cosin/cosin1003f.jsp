<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN1001F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String tabId = Common.get(request.getParameter("tabId"));
%>

<script type="text/javascript">
$(function(){
	chFirstResult("<%=obj.getFirstResult()%>");
});

function chFirstResult(val){
	if(val == "02"){
		$("#FR1").show();
		$("#FR2").hide();
		$("[name=leaveCaseReason]").val("");
	}else if(val == "03"){
		$("#FR2").show();
		$("#FR1").hide();
		$("[name=nonDefective]").val("");
	}else{
		$("#FR1").hide();
		$("[name=nonDefective]").val("");
		$("#FR2").hide();
		$("[name=leaveCaseReason]").val("");
	}
}

function queryCos0006DbOne(rowid, isNeedUpdateOther, classTR, id){
	$("tr[id*=listCos0006DbContainerRow]").each(function(){
		if($(this).attr("id") == ("listCos0006DbContainerRow" + rowid)){
			this.className = "activeRow";
			this.onmouseover = "activeRow";
			this.onmouseout = "activeRow";
		}else{
			this.className = classTR;
			this.onmouseover = function(){
				this.className = "listTRMouseover";
			};
			this.onmouseout = function(){
				this.className = classTR;
			}
		}
	});

	showCOS0006Db(id);
}

function showCOS0006Db(id){
	openCenterWindow(800, 300, 'cosin1005f.jsp?cos0006DbId=' + id);
}

function getCos0003Db(id){
	var isNeedCMS1 = false;					// 轉由地方衛生單位作後續處理
	var isNeedCMS2 = false;					// 寄發廠商詢問函		
	$.ajax({
		url: '../../ajax/jsonCos0003Db.jsp',
		type: 'POST',
		data: 'id=' + id,
		dataType: 'json',
		success: function(data){
			for(var i=0; i<data.length; i++){
				if(data[i].code == "0100"){
					isNeedCMS1 = true;
					form1.dMarked.checked = true;
				}
				if(data[i].code == "0200"){
					isNeedCMS1 = true;
					form1.dLawlessIng.checked = true;
				}
				if(data[i].code == "0300"){
					isNeedCMS2 = true;
					form1.dExteriorError.checked = true;
				}
				if(data[i].code == "0400"){
					isNeedCMS2 = true;
					form1.dPackageError.checked = true;
				}
				if(data[i].code == "0500"){
					isNeedCMS1 = true;
					form1.dExpired.checked = true;
				}
				if(data[i].code == "0600"){
					isNeedCMS2 = true;
					form1.dOthers.value = true;
				}
			}
			if(isNeedCMS1){
				form1.measure[0].checked = true;
			}
			if(isNeedCMS2){
				form1.measure[1].checked = true;
			}
		},
		error: function(){
			
		}
	});
}
</script>

<table id="<%=tabId.equals("")?"Tab6":tabId%>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">初步判定結果</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="firstResult" type="select" onChange="chFirstResult(this.value);">
			<%=View.getOptionCodeKind("CFR", obj.getFirstResult()) %>
		</select>
		<span id="FR1">
			，非不良品原因
			<input class="field" type="text" name="nonDefective" size="50" maxlength="90" value="<%=obj.getNonDefective()%>">
		</span>
		<span id="FR2">
			，留案備查理由
			<input class="field" type="text" name="leaveCaseReason" size="50" maxlength="90" value="<%=obj.getLeaveCaseReason()%>">
		</span>
		<input type="hidden" name="cos0006DbId" value="<%=obj.getCos0006DbId()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">調查摘要</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="checkbox" name="dMarked" value="Y" <%=obj.getdMarked().equals("Y")?"checked":"" %>>標示問題
		<br>
		
		<input class="field" type="checkbox" name="dLawlessIng" value="Y" <%=obj.getdLawlessIng().equals("Y")?"checked":"" %>>疑似含有不法或其他有效成分，
		說明
		<input class="field" type="text" name="dLawlessIngOther" size="30" maxlength="50" value="<%=obj.getdLawlessIngOther()%>">
		<br>
		
		<input class="field" type="checkbox" name="dExteriorError" value="Y" <%=obj.getdExteriorError().equals("Y")?"checked":"" %>>外觀異常，
		說明
		<input class="field" type="text" name="dExteriorErrorOther" size="30" maxlength="50" value="<%=obj.getdExteriorErrorOther()%>">
		<br>
		
		<input class="field" type="checkbox" name="dPackageError" value="Y" <%=obj.getdPackageError().equals("Y")?"checked":"" %>>包裝瑕疵，
		說明
		<input class="field" type="text" name="dPackageErrorOther" size="30" maxlength="50" value="<%=obj.getdPackageErrorOther()%>">
		<br>
		
		<input class="field" type="checkbox" name="dExpired" value="Y" <%=obj.getdExpired().equals("Y")?"checked":"" %>>過期，
		說明
		<input class="field" type="text" name="dExpiredOther" size="30" maxlength="50" value="<%=obj.getdExpiredOther()%>">
		<br>
		
		<input class="field" type="checkbox" name="dOthers" value="Y" <%=obj.getdOthers().equals("Y")?"checked":"" %>>其他，
		說明
		<input class="field" type="text" name="dOthersDesc" size="30" maxlength="50" value="<%=obj.getdOthersDesc()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">採取措施</td>
	<td nowrap class="td_form_white" colspan="3">
		<%=View.getCommonCheckBoxTextOption("field", "measure", "CMS", obj.getMeasure()) %>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">化粧品不良品通報件數</td>
	<td nowrap class="td_form_white">
		<input class="field_RO" type="text" name="num1" size="20" value="<%=obj.getNum1()%>">
	</td>
	<td nowrap class="td_form">化粧品不良反應通報件數</td>
	<td nowrap class="td_form_white">
		<input class="field_RO" type="text" name="num2" size="20" value="<%=obj.getNum2()%>">
	</td>
</tr>
<%
if("Y".equals(obj.getIsHasHistory())){
%>
	<tr>
		<td nowrap class="td_form_left" colspan="4" style="font-size:12pt;text-align:center;background-color:yellow">分類歷史流程資料</td>
	</tr>
	
	<tr><td nowrap class="bgList" colspan="4">
	<div id="COS0006DbListContainer" style="height:auto">
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="COS0006DbListTHEAD">
		<tr>
			<th class="listTH"><a class="text_link_w">NO.</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">初步判定結果</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">採取措施</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">分類日期</a></th>
		</tr>
		</thead>
		<tbody id="COS0006DbListTBODY">
		<%
		boolean primaryArray[] = {true, false, false, false};
		boolean displayArray[] = {false, true, true, true};
		out.write(View.getQuerylist(primaryArray, displayArray, null, obj.getCos0006DbList(), obj.getQueryAllFlag(), true, null, "", "", true, false, false, "Cos0006Db"));   
		%>
		</tbody>
	</table>
	</div>
	</td></tr>

<%
}
%>
</table>
