<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0701F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String tabId = Common.get(request.getParameter("tabId"));
%>
<script type="text/javascript">
function queryHfr0006DbOne(rowid, isNeedUpdateOther, classTR, id, isShow){
	$("tr[id*=listHfr0006DbContainerRow]").each(function(){
		if($(this).attr("id") == ("listHfr0006DbContainerRow" + rowid)){
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
	if(isNeedUpdateOther == "Y"){
		if(isShow == "Y"){
			openCenterWindow(900, 500, 'hfrin0705f.jsp?id=' + id);
			if($(":button[name=pageUpdate]").attr("disabled")){
				setFormItem("createHfr0006Db", "O");
				setFormItem("updateHfr0006Db,doCommittee", "R");
				clearHfr0006Db();
				closeHfr0006DbFiled();
			}
		}else{
			if($(":button[name=pageUpdate]").attr("disabled")){
				clearTabTabl();
				$.ajax({
					url: getVirtualPath() +'ajax/jsonHfr0006Db.jsp',
					type: 'POST',
					data: 'id=' + id,
					dataType: 'json',
					success: function(data){
						form1.hfr0006Id.value = data.id;
						form1.evaluateDate.value = data.evaluateDate;
						form1.secUnExpectedClassify.value = data.unExpectedClassify;
						if(data.unExpectedReason != ""){
							var s = data.unExpectedReason.split(","); 
							for(var i=0; i<s.length; i++){
								var flag = true;
								var tmp = document.all.item("secUnExpectedReason");
								for(var j=0; j<tmp.length; j++){
									if(tmp[j].value == s[i]){
										document.all.item("secUnExpectedReason")[j].checked = true;
										flag = false;
									}
									if(!flag){
										break;
									}
								}
							}
						}
						form1.secEvidentiary.value = data.evidentiary;
						form1.secSeverity.value = data.secSeverity;
						form1.secAdministrativeLevel.value = data.administrativeLevel;
						form1.assessments.value = data.assessments;
						form1.evaluateResult.value = data.evaluateResult;
						form1.secExplain.value = data.secExplain;
						form1.unSecExplain.value = data.unSecExplain;
						form1.evaluateCommittee.value = data.cId;
						form1.evaluateCommitteeName.value = data.cName;
						getCon0001Db(id);
						setFormItem("createHfr0006Db,updateHfr0006Db,deleteHfr0006Db", "O");
						setFormItem("doCommittee", "R");
					},
					error: function(){
						setFormItem("createHfr0006Db", "O");
						setFormItem("updateHfr0006Db,deleteHfr0006Db", "R");
						setFormItem("doCommittee", "R");
						clearHfr0006Db();
						closeHfr0006DbFiled();
					}
				});
			}else{
				// nothing ...
			}
		}
	}else{
		// nothing ...
	}
}

function getCon0001Db(id){
	$.ajax({
		url:  getVirtualPath() +'ajax/jsonCon0001Db.jsp',
		type: 'POST',
		data: 'id=' + id + "&fileType=HFRAS&dbName=HFR0006DB",
		dataType: 'json',
		success: function(data){
			for(var i=0; i<data.length; i++){
				addFileHFRAS('tabFileHFRAS', data[i].fileName, data[i].fileRoute, data[i].fileExplan, data[i].id);
			}
			closeHfr0006DbFiled();
		},
		error: function(){
			
		}
	});
}

function clearHfr0006Db(){
	clearTabTabl();
	$("#<%=tabId.equals("")?"Tab4":tabId %> [class*=field]").each(function(){
		if($(this).attr("type") != "button" && $(this).attr("type") != "checkbox"){
			$(this).val("");
		}
	});
}

function closeHfr0006DbFiled(){
	$("#<%=tabId.equals("")?"Tab4":tabId %> [class*=field]").each(function(){
		if($(this).attr("type") == "text" || $(this).attr("type") == "textarea"){
			$(this).attr("readonly", true);
		}else{
			$(this).attr("disabled", true);
		}
		if($(this).attr("class") == "field_RO"){
			$(this).attr("disabled", true);
		}
	});
}

function clearTabTabl(){
	$("#listTFOOTHFRAS").remove();
	
	var tbl = document.getElementById('tabFileHFRAS');
	var lastRow = tbl.rows.length;
	if(lastRow > 1){
		for(var i=1; i<lastRow; i++){		
			tbl.deleteRow(lastRow-i);
		}
	}
	
	var t = $("<tfoot id='listTFOOTHFRAS'><tr><td>&nbsp;</td></tr></tfoot>");
	$(tbl).append(t);
}

function getCommitteeInfo(){
	openCenterWindow(800, 300, 'hfrin0704f.jsp?fieldId=evaluateCommittee&fieldName=evaluateCommitteeName');
}

function chSecSeverity(val){
	if(val == "5"){
		$(":text[name=secExplain]").attr("readonly","");
	}else{
		$(":text[name=secExplain]").val("").attr("readonly","readonly");
	}
}

$(function(){
	$(":checkbox[name=secUnExpectedReason]").bind("click", function(){
		chSecUnExpectedReason();
	});
});

// 非預期反應原因
function chSecUnExpectedReason(){
	var flag = false;
	var tmp = document.all.item("secUnExpectedReason");
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
		$(":text[name=unSecExplain]").attr("readonly","");
	}else{
		$(":text[name=unSecExplain]").val("").attr("readonly","readonly");
	}
}
</script>
<table id="<%=tabId.equals("")?"Tab4":tabId %>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">評估日期</td>
	<td nowrap class="td_form_white">
		<input class="field_RO" type="text" name="evaluateDate" size="7" value="<%=obj.getEvaluateDate()%>">
		<input class="field" type="hidden" name="hfr0006Id" value="<%=obj.getHfr0006Id()%>">
	</td>
	<td nowrap class="td_form">評估委員</td>
	<td nowrap class="td_form_white">
		<input class="field_RO" type="text" name="evaluateCommitteeName" size="15" maxlength="20" value="<%=obj.getEvaluateCommitteeName() %>">
		<input type="hidden" name="evaluateCommittee" value="<%=obj.getEvaluateCommittee() %>">
		&nbsp;
		<input class="toolbar_default" type="button" name="doCommittee" value="評 估 委 員" onClick="getCommitteeInfo();">&nbsp;
 	</td>
</tr>
<tr>
	<td nowrap class="td_form">非預期反應分類</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="secUnExpectedClassify" type="select">
			<%=View.getOptionCodeKind("HFRFUC", obj.getSecUnExpectedClassify()) %>
		</select>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">非預期反應原因</td>
	<td nowrap class="td_form_white" colspan="3">
		<%=View.getCommonCodeKindBoxOption("field", "secUnExpectedReason", "HFRNRS", obj.getSecUnExpectedReason()) %>
		<br>
		，說明
		<input class="field" type="text" name="unSecExplain" size="50" maxlength="50" value="<%=obj.getUnSecExplain()%>">
	</td>
</tr>


<tr>
	<td nowrap class="td_form">證據性</td>
	<td nowrap class="td_form_white">
		<select class="field" name="secEvidentiary" type="select">
			<%=View.getOptionCodeKind("HFRFEV", obj.getSecEvidentiary()) %>
		</select>
	</td>
	<td nowrap class="td_form">行政處置層級</td>
	<td nowrap class="td_form_white">
		<select class="field" name="secAdministrativeLevel" type="select">
			<%=View.getOptionCodeKind("HFRDRSP", obj.getSecAdministrativeLevel()) %>
		</select>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">嚴重程度</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="secSeverity" type="select" onChange="chSecSeverity(this.value);">
			<%=View.getOptionCodeKind("HFRSVR", obj.getSecSeverity()) %>
		</select>
		，說明
		<input class="field" type="text" name="secExplain" size="50" maxlength="50" value="<%=obj.getSecExplain()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">評估意見</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field" name="assessments" rows="2" cols="80"><%=obj.getAssessments() %></textarea>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">評估結論</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field" name="evaluateResult" rows="2" cols="80"><%=obj.getEvaluateResult() %></textarea>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">相關附件</td>
	<td nowrap class="td_form_white" colspan="3">
		<jsp:include page="hfrin0410f.jsp">
			<jsp:param value="HFRAS" name="fileType"/>
		</jsp:include>
	</td>
</tr>
<%
if(obj.getHfr0006DbList()!=null && obj.getHfr0006DbList().size()>0){
%>
<tr><td nowrap class="bgList" colspan="4">
<div id="listContainerHfr0006Db" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEADHfr0006Db">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">評估日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">評估委員</a></th>
	</tr>
	</thead>
	<tbody id="listTBODYHfr0006Db">
	<%
	boolean primaryArray[] = {true, false, false, true};
    boolean displayArray[] = {false, true, true, false};
	out.write(View.getQuerylist(primaryArray, displayArray, null, obj.getHfr0006DbList(), obj.getQueryAllFlag(), true, null, "", "", true, false, false, "Hfr0006Db"));
	%>
	</tbody>
</table>
</div>
</td></tr>
<%
}
%>

</table>
