<!--
程式目的：不良品評估頁籤
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0801F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String tabId = Common.get(request.getParameter("tabId"));
%>

<script type="text/javascript">
function queryCos0007DbOne(rowid, isNeedUpdateOther, classTR, id){
	$("tr[id*=listCos0007DbContainerRow]").each(function(){
		if($(this).attr("id") == ("listCos0007DbContainerRow" + rowid)){
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

	showCOS0007Db(id);
}

function showCOS0007Db(id){
	openCenterWindow(800, 300, getVirtualPath() + "tcbw/cosin/cosin0804f.jsp?cos0007DbId=" + id);
}
</script>
<table id="<%=tabId%>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">評估結果</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="assessResult" type="select">
			<%=View.getTextOption("1;不良品;2;非不良品", obj.getAssessResult()) %>
		</select>
		<input type="hidden" name="cos0007DbId" value="<%=obj.getCos0007DbId()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">評估備註</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="assessRemark" size="80" maxlength="100" value="<%=obj.getAssessRemark()%>">
	</td>
</tr>

<%
if(obj.getCos0007DbList()!=null && obj.getCos0007DbList().size()>0){
%>
	<tr>
		<td nowrap class="td_form_left" colspan="4" style="font-size:12pt;text-align:center;background-color:yellow">評估歷史資料</td>
	</tr>
	
	<tr><td nowrap class="bgList" colspan="4">
	<div id="COS0007DbListContainer" style="height:auto">
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="COS0007DbListTHEAD">
		<tr>
			<th class="listTH"><a class="text_link_w">NO.</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">評估日期</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">評估結果</a></th>
		</tr>
		</thead>
		<tbody id="COS0007DbListTBODY">
		<%
		boolean primaryArray[] = {true, false, false};
		boolean displayArray[] = {false, true, true};
		out.write(View.getQuerylist(primaryArray, displayArray, null, obj.getCos0007DbList(), obj.getQueryAllFlag(), true, null, "", "", true, false, false, "Cos0007Db"));
		%>
		</tbody>
	</table>
	</div>
	</td></tr>



<%
}
%>
</table>
