<!--
程式目的：不良品、不良反應廠商回覆頁籤
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0601F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String tabId = Common.get(request.getParameter("tabId"));
%>

<script type="text/javascript">
function queryCos0910DbOne(rowid, isNeedUpdateOther, classTR, id, anotherId, type, idType){
	$("tr[id*=listCos0910DbContainerRow]").each(function(){
		if($(this).attr("id") == ("listCos0910DbContainerRow" + rowid)){
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
	showCOS0910Db(id, anotherId, type, idType);
}

function showCOS0910Db(id, anotherId, type, idType){
	if(type == "0910"){
		if(idType == "09"){
			openCenterWindow(900, 500, 'cosin0613f.jsp?cos0009DbId=' + id + "&cos0010DbId=" + anotherId);
		}else{
			openCenterWindow(900, 500, 'cosin0613f.jsp?cos0009DbId=' + anotherId + "&cos0010DbId=" + id);
		}
	}else if(type == "09"){
		openCenterWindow(900, 500, 'cosin0609f.jsp?cos0009DbId=' + id);
	}else if(type == "10"){
		openCenterWindow(900, 300, 'cosin0610f.jsp?cos0010DbId=' + id);
	}else{
		alert("無法辨別歷史資料種類，請確認");
	}
//	openCenterWindow(900, 500, 'cosin0609f.jsp?cos0009DbId=' + id);
}

function checkVal(){
	if(form1.complaintNum0910.value != ""){
		var s = checkInt(form1.complaintNum0910, "件數");
		if(s.length > 0){
			alert(s);
			form1.complaintNum0910.value = "";
		}
	}
	form1.complaintNum0910.style.backgroundColor = "";
}

function chIsComplaintYn0910(val){
	if(val == "Y"){
		form1.complaintNum0910.disabled = false;
	}else{
		form1.complaintNum0910.value = "";
		form1.complaintNum0910.disabled = true;
	}
}
</script>

<table id="<%=tabId%>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">通知日期</td>
	<td nowrap class="td_form_white">
		<input class="field_RO" type="text" name="notifyDate0910" size="7" maxlength="7" value="<%=obj.getNotifyDate0910()%>">
		<input type="hidden" name="cos0910Id" value="<%=obj.getCos0910Id()%>">
	</td>
	<td nowrap class="td_form">回覆日期</td>
	<td nowrap class="td_form_white">
		<%=View.getPopCalendar("field", "replyDate0910", obj.getReplyDate0910())%>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">是否接獲該產品其他消費者客訴</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="radio" name="isComplaintYn0910" value="Y" <%=obj.getIsComplaintYn0910().equals("Y")?"checked":"" %>>是，件數
		<input class="field" type="text" name="complaintNum0910" size="5" maxlength="5" value="<%=obj.getComplaintNum0910()%>" onChange="checkVal(this.value);">
		<input class="field" type="radio" name="isComplaintYn0910" value="N" <%=obj.getIsComplaintYn0910().equals("N")?"checked":"" %>>否
	</td>
</tr>
<tr>
	<td nowrap class="td_form">類似客訴之後續處理情形</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field"  name="similarComplaint0910" cols="100" rows="2" ><%=obj.getSimilarComplaint0910()%></textarea>(200字以內)
	</td>
</tr>
<tr>
	<td nowrap class="td_form">預防矯正措施</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field"  name="precaution0910" cols="100" rows="5" ><%=obj.getPrecaution0910()%></textarea>(500字以內)
	</td>
</tr>
<tr>
	<td nowrap class="td_form">目前處理情形/調查報告</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field"  name="handling0910" cols="100" rows="5" ><%=obj.getHandling0910()%></textarea>(500字以內)
	</td>
</tr>
<tr>
	<td nowrap class="td_form">補充說明</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field"  name="remark0910" cols="100" rows="2" ><%=obj.getRemark0910()%></textarea>(200字以內)
	</td>
</tr>
<tr>
	<td nowrap class="td_form">預計完成日期</td>
	<td nowrap class="td_form_white" colspan="3">
		<%=View.getPopCalendar("field", "predictDate0910", obj.getPredictDate0910())%>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">相關附件</td>
	<td nowrap class="td_form_white" colspan="3">
		<jsp:include page="../cosex/cosex0104f.jsp">
			<jsp:param value="COSVR" name="fileType"/>
		</jsp:include>
	</td>
</tr>

<%
if(obj.getCos0910DbList()!=null && obj.getCos0910DbList().size()>0){
%>
	<tr>
		<td nowrap class="td_form_left" colspan="4" style="font-size:12pt;text-align:center;background-color:yellow">廠商回覆歷史資料</td>
	</tr>
	
	<tr><td nowrap class="bgList" colspan="4">
	<div id="COS0910DbListContainer" style="height:auto">
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="COS0910DbListTHEAD">
		<tr>
			<th class="listTH"><a class="text_link_w">NO.</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">通知日期</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">回覆日期</a></th>
		</tr>
		</thead>
		<tbody id="COS0910DbListTBODY">
		<%
		    boolean primaryArray[] = {true, false, false, true, true, true};
		    boolean displayArray[] = {false, true, true, false, false, false};
		    out.write(View.getQuerylist(primaryArray, displayArray, null, obj.getCos0910DbList(), obj.getQueryAllFlag(), true, null, "", "", true, false, false, "Cos0910Db"));
		%>
		</tbody>
	</table>
	</div>
	</td></tr>
<%
}
%>
</table>
