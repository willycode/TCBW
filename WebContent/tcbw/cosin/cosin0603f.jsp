<!--
程式目的：不良品處理作業-廠商回覆頁籤
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
function queryCos0009DbOne(rowid, isNeedUpdateOther, classTR, id){
	$("tr[id*=listCos0009DbContainerRow]").each(function(){
		if($(this).attr("id") == ("listCos0009DbContainerRow" + rowid)){
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

	showCOS0009Db(id);
}

function showCOS0009Db(id){
	openCenterWindow(900, 500, getVirtualPath() + "tcbw/cosin/cosin0609f.jsp?cos0009DbId=" + id);
}
</script>

<table id="<%=tabId%>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">通知日期</td>
	<td nowrap class="td_form_white">
		<input class="field_RO" type="text" name="notifyDate9" size="7" maxlength="7" value="<%=obj.getNotifyDate9()%>">
		<input type="hidden" name="cos0009DbId" value="<%=obj.getCos0009DbId()%>">
	</td>
	<td nowrap class="td_form">回覆日期</td>
	<td nowrap class="td_form_white">
		<%=View.getPopCalendar("field", "replyDate9", obj.getReplyDate9())%>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">目前處理情形/調查報告</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field"  name="handling" cols="100" rows="5" ><%=obj.getHandling()%></textarea>(500字以內)
	</td>
</tr>
<tr>
	<td nowrap class="td_form">預防矯正措施</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field"  name="precaution9" cols="100" rows="5" ><%=obj.getPrecaution9()%></textarea>(500字以內)
	</td>
</tr>
<tr>
	<td nowrap class="td_form">類似客訴</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field"  name="similarComplaint" cols="100" rows="2" ><%=obj.getSimilarComplaint()%></textarea>(200字以內)
	</td>
</tr>
<tr>
	<td nowrap class="td_form">補充說明</td>
	<td nowrap class="td_form_white" colspan="3">
		<textarea class="field"  name="remark9" cols="100" rows="2" ><%=obj.getRemark9()%></textarea>(200字以內)
	</td>
</tr>
<tr>
	<td nowrap class="td_form">預計完成日期</td>
	<td nowrap class="td_form_white" colspan="3">
		<%=View.getPopCalendar("field", "predictDate", obj.getPredictDate())%>
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
if(obj.getCos0009DbList()!=null && obj.getCos0009DbList().size()>0){
%>
	<tr>
		<td nowrap class="td_form_left" colspan="4" style="font-size:12pt;text-align:center;background-color:yellow">不良品廠商回覆歷史資料</td>
	</tr>
	
	<tr><td nowrap class="bgList" colspan="4">
	<div id="COS0009DbListContainer" style="height:auto">
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="COS0009DbListTHEAD">
		<tr>
			<th class="listTH"><a class="text_link_w">NO.</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">通知日期</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">回覆日期</a></th>
		</tr>
		</thead>
		<tbody id="COS0009DbListTBODY">
		<%
		    boolean primaryArray[] = {true, false, false};
		    boolean displayArray[] = {false, true, true};
		    out.write(View.getQuerylist(primaryArray, displayArray, null, obj.getCos0009DbList(), obj.getQueryAllFlag(), true, null, "", "", true, false, false, "Cos0009Db"));
		%>
		</tbody>
	</table>
	</div>
	</td></tr>
<%
}
%>
</table>
