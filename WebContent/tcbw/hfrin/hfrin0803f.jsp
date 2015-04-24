<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0801F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String tabId = Common.get(request.getParameter("tabId"));
%>
<script type="text/javascript">
function queryHfr0007DbOne(rowid, isNeedUpdateOther, classTR, id){
	$("tr[id*=listHfr0007DbContainerRow]").each(function(){
		if($(this).attr("id") == ("listHfr0007DbContainerRow" + rowid)){
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
	showHFR0007Db(id);
}

function showHFR0007Db(id){
	openCenterWindow(900, 500, 'hfrin0804f.jsp?id=' + id);
}

function chThiSeverity(val){
	if(val == "5"){
		$(":text[name=thiExplain]").attr("readonly","");
	}else{
		$(":text[name=thiExplain]").val("").attr("readonly","readonly");
	}
}

$(function(){
	$(":checkbox[name=thiUnExpectedReason]").bind("click", function(){
		chThiUnExpectedReason();
	});
});

// 非預期反應原因
function chThiUnExpectedReason(){
	var flag = false;
	var tmp = document.all.item("thiUnExpectedReason");
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
		$(":text[name=unThiExplain]").attr("readonly","");
	}else{
		$(":text[name=unThiExplain]").val("").attr("readonly","readonly");
	}
}

</script>
<table id="<%=tabId.equals("")?"Tab4":tabId %>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">委員會會期</td>
	<td nowrap class="td_form_white">
		<input class="field" type="text" name="committeeMeeting" size="10" maxlength="10" value="<%=obj.getCommitteeMeeting() %>">
		<input type="hidden" name="hfr0007Id" value="<%=obj.getHfr0007Id()%>">
	</td>
	<td nowrap class="td_form">委員會召開日期</td>
	<td nowrap class="td_form_white">
		<%=View.getPopCalendar("field", "committeeDate", obj.getCommitteeDate())%>
 	</td>
</tr>
<tr>
	<td nowrap class="td_form">會議紀錄發文日期</td>
	<td nowrap class="td_form_white">
		<%=View.getPopCalendar("field", "recordDate", obj.getRecordDate())%>
	</td>
	<td nowrap class="td_form">案件回饋日期</td>
	<td nowrap class="td_form_white">
		<%=View.getPopCalendar("field", "caseBackDate", obj.getCaseBackDate())%>
 	</td>
</tr>
<tr>
	<td nowrap class="td_form">非預期反應分類</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="thiUnExpectedClassify" type="select">
			<%=View.getOptionCodeKind("HFRFUC", obj.getThiUnExpectedClassify()) %>
		</select>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">非預期反應原因</td>
	<td nowrap class="td_form_white" colspan="3">
		<%=View.getCommonCodeKindBoxOption("field", "thiUnExpectedReason", "HFRNRS", obj.getThiUnExpectedReason()) %>
		<br>
		，說明
		<input class="field" type="text" name="unThiExplain" size="50" maxlength="50" value="<%=obj.getUnThiExplain()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">證據性</td>
	<td nowrap class="td_form_white">
		<select class="field" name="thiEvidentiary" type="select">
			<%=View.getOptionCodeKind("HFRFEV", obj.getThiEvidentiary()) %>
		</select>
	</td>
	<td nowrap class="td_form">行政處置層級</td>
	<td nowrap class="td_form_white">
		<select class="field" name="thiAdministrativeLevel" type="select">
			<%=View.getOptionCodeKind("HFRDRSP", obj.getThiAdministrativeLevel()) %>
		</select>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">嚴重程度</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="thiSeverity" type="select" onChange="chThiSeverity(this.value)">
			<%=View.getOptionCodeKind("HFRSVR", obj.getThiSeverity()) %>
		</select>
		，說明
		<input class="field" type="text" name="thiExplain" size="50" maxlength="50" value="<%=obj.getThiExplain()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">複評結果</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="reEvaluateResult" type="select">
			<%=View.getTextOption("1;結案;2;重新評估;", obj.getReEvaluateResult()) %>
		</select>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">相關附件</td>
	<td nowrap class="td_form_white" colspan="3">
		<jsp:include page="hfrin0410f.jsp">
			<jsp:param value="HFRRE" name="fileType"/>
		</jsp:include>
	</td>
</tr>
<%
if(obj.getHfr0007DbList()!=null && obj.getHfr0007DbList().size()>0){
%>
<tr>
	<td nowrap colspan="4">&nbsp;</td>
</tr>
<tr><td nowrap class="bgList" colspan="4">
<div id="listContainerHfr0007Db" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">委員會會期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">委員會召開日期</a></th>
	</tr>
	</thead>
	<tbody id="listTBODYHfr0007Db">
	<%
	boolean primaryArray[] = {true, false, false};
    boolean displayArray[] = {false, true, true};
    out.write(View.getQuerylist(primaryArray, displayArray, null, obj.getHfr0007DbList(), obj.getQueryAllFlag(), true, null, "", "", true, false, false, "Hfr0007Db"));
	%>
	</tbody>
</table>
</div>
</td></tr>
<%
}
%>
</table>
