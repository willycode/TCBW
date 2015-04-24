<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0601F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String tabId = Common.get(request.getParameter("tabId"));
%>

<script type="text/javascript">
function queryHfr0005DbOne(rowid, isNeedUpdateOther, classTR, id){
	$("tr[id*=listHfr0005DbContainerRow]").each(function(){
		if($(this).attr("id") == ("listHfr0005DbContainerRow" + rowid)){
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

	showHFR0005Db(id);
}

function showHFR0005Db(id){
	openCenterWindow(900, 500, 'hfrin0604f.jsp?id=' + id);
}

function chPreSeverity(val){
	if(val == "5"){
		$(":text[name=preExplain]").attr("readonly","");
	}else{
		$(":text[name=preExplain]").val("").attr("readonly","readonly");
	}
}

$(function(){
	$(":checkbox[name=unExpectedReason]").bind("click", function(){
		chUnExpectedReason();
	});
});

// 非預期反應原因
function chUnExpectedReason(){
	var flag = false;
	var tmp = document.all.item("unExpectedReason");
	for(var i=0; i<tmp.length; i++){
		if(tmp[i].checked){
			if(tmp[i].value == "7"){
				flag = true;
			}
		}
		if(flag){
			break;
		}
	}
	if(flag){
		$(":text[name=unPreExplain]").attr("readonly","");
	}else{
		$(":text[name=unPreExplain]").val("").attr("readonly","readonly");
	}
}
</script>
<table id="<%=tabId.equals("")?"Tab4":tabId %>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">初評完成日期</td>
	<td nowrap class="td_form_white">
		<%=View.getPopCalendar("field", "preCompleteDate", obj.getPreCompleteDate())%>
		<input type="hidden" name="hfr0005Id" value="<%=obj.getHfr0005Id()%>">
	</td>
	<td nowrap class="td_form">預評送交日期</td>
	<td nowrap class="td_form_white">
		<%=View.getPopCalendar("field", "assessmentSendDate", obj.getAssessmentSendDate())%>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">預評完成日期</td>
	<td nowrap class="td_form_white">
		<%=View.getPopCalendar("field", "assessmentCompleteDate", obj.getAssessmentCompleteDate())%>
	</td>
	<td nowrap class="td_form">案件分類</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="caseType" type="select">
			<%=View.getOptionCodeKind("HFRCKD", obj.getCaseType()) %>
		</select>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">非預期反應分類</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="unExpectedClassify" type="select">
			<%=View.getOptionCodeKind("HFRFUC", obj.getUnExpectedClassify()) %>
		</select>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">非預期反應原因</td>
	<td nowrap class="td_form_white" colspan="3">
		<%=View.getCommonCodeKindBoxOption("field", "unExpectedReason", "HFRNRS", obj.getUnExpectedReason()) %>
		<br>
		，說明
		<input class="field" type="text" name="unPreExplain" size="50" maxlength="50" value="<%=obj.getUnPreExplain()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">證據性</td>
	<td nowrap class="td_form_white">
		<select class="field" name="evidentiary" type="select">
			<%=View.getOptionCodeKind("HFRFEV", obj.getEvidentiary()) %>
		</select>
	</td>
	<td nowrap class="td_form">行政處置層級</td>
	<td nowrap class="td_form_white">
		<select class="field" name="administrativeLevel" type="select">
			<%=View.getOptionCodeKind("HFRDRSP", obj.getAdministrativeLevel()) %>
		</select>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">嚴重程度</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="preSeverity" type="select" onChange="chPreSeverity(this.value);">
			<%=View.getOptionCodeKind("HFRSVR", obj.getPreSeverity()) %>
		</select>
		，說明
		<input class="field" type="text" name="preExplain" size="50" maxlength="50" value="<%=obj.getPreExplain()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">承辦備註</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="preRemark" size="100" maxlength="100" value="<%=obj.getPreRemark()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">評估意見</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field" name="preOpinion" rows="3" cols="50"><%=obj.getPreOpinion() %></textarea>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">相關附件</td>
	<td nowrap class="td_form_white" colspan="3">
		<jsp:include page="hfrin0410f.jsp">
			<jsp:param value="HFRFO" name="fileType"/>
		</jsp:include>
	</td>
</tr>
<%
if(obj.getHfr0005DbList()!=null && obj.getHfr0005DbList().size()>0){
%>
<tr><td nowrap class="bgList" colspan="4">
<div id="listContainerHfr0005Db" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEADHfr0005Db">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">初評完成日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">預評送交日期</a></th>
	</tr>
	</thead>
	<tbody id="listTBODYHfr0005Db">
	<%
	boolean primaryArray[] = {true, false, false};
    boolean displayArray[] = {false, true, true};
	out.write(View.getQuerylist(primaryArray, displayArray, null, obj.getHfr0005DbList(), obj.getQueryAllFlag(), true, null, "", "", true, false, false, "Hfr0005Db"));
	%>
	</tbody>
</table>
</div>
</td></tr>
<%
}
%>

</table>
