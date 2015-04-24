<!--
程式目的：不良反應處理作業-廠商回覆頁籤
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
function queryCos0010DbOne(rowid, isNeedUpdateOther, classTR, id){
	$("tr[id*=listCos0010DbContainerRow]").each(function(){
		if($(this).attr("id") == ("listCos0010DbContainerRow" + rowid)){
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

	showCOS0010Db(id);
}

function showCOS0010Db(id){
	openCenterWindow(900, 300, 'cosin0610f.jsp?cos0010DbId=' + id);
}

function chIsComplaintYn10(val){
	if(val == "Y"){
		form1.complaintNum.disabled = false;
	}else{
		form1.complaintNum.value = "";
		form1.complaintNum.disabled = true;
	}
}

function checkVal(){
	if(form1.complaintNum.value != ""){
		var s = checkInt(form1.complaintNum, "件數");
		if(s.length > 0){
			alert(s);
			form1.complaintNum.value = "";
		}
	}
	form1.complaintNum.style.backgroundColor = "";
}
</script>

<table id="<%=tabId%>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">通知日期</td>
	<td nowrap class="td_form_white">
		<input class="field_RO" type="text" name="notifyDate10" size="7" maxlength="7" value="<%=obj.getNotifyDate10()%>">
		<input type="hidden" name="cos0010DbId" value="<%=obj.getCos0010DbId()%>">
	</td>
	<td nowrap class="td_form">回覆日期</td>
	<td nowrap class="td_form_white">
		<%=View.getPopCalendar("field", "replyDate10", obj.getReplyDate10())%>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">是否接獲該產品其他消費者客訴</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="radio" name="isComplaintYn10" value="Y" <%=obj.getIsComplaintYn10().equals("Y")?"checked":"" %>>是，件數
		<input class="field" type="text" name="complaintNum" size="5" maxlength="5" value="<%=obj.getComplaintNum()%>" onChange="checkVal(this.value);">
		<input class="field" type="radio" name="isComplaintYn10" value="N" <%=obj.getIsComplaintYn10().equals("N")?"checked":"" %>>否
	</td>
</tr>
<tr>
	<td nowrap class="td_form">類似客訴案件之後續處理情形</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field"  name="dealWith10" cols="100" rows="2" ><%=obj.getDealWith10()%></textarea>(200字以內)
	</td>
</tr>
<tr>
	<td nowrap class="td_form">其他回覆</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field"  name="replyOther10" cols="100" rows="2" ><%=obj.getReplyOther10()%></textarea>(200字以內)
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
if(obj.getCos0010DbList()!=null && obj.getCos0010DbList().size()>0){
%>
	<tr>
		<td nowrap class="td_form_left" colspan="4" style="font-size:12pt;text-align:center;background-color:yellow">不良反應廠商回覆歷史資料</td>
	</tr>
	
	<tr><td nowrap class="bgList" colspan="4">
	<div id="COS0010DbListContainer" style="height:auto">
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="COS0010DbListTHEAD">
		<tr>
			<th class="listTH"><a class="text_link_w">NO.</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">通知日期</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">回覆日期</a></th>
		</tr>
		</thead>
		<tbody id="COS0010DbListTBODY">
		<%
		    boolean primaryArray[] = {true, false, false};
		    boolean displayArray[] = {false, true, true};
		    out.write(View.getQuerylist(primaryArray, displayArray, null, obj.getCos0010DbList(), obj.getQueryAllFlag(), true, null, "", "", true, false, false, "Cos0010Db"));
		%>
		</tbody>
	</table>
	</div>
	</td></tr>
<%
}
%>

</table>
