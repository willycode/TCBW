<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.common.view.sys.ap.SYSAP001F qBean = (com.kangdainfo.common.view.sys.ap.SYSAP001F)request.getAttribute("qBean");
%>
	<table class="queryTable"  border="1">
	<tr>
		<td nowrap class="queryTDLable">使用者帳號：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userId" size="20" maxlength="20" value="<%=qBean.getQ_userId()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">姓名：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userName" size="20" maxlength="50" value="<%=qBean.getQ_userName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">群組：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" type="select" name="q_groupId">
				<%=View.getOption("select id, groupName from CommonGroup order by groupName", qBean.getQ_groupId()) %>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">單位：</td>
		<td nowrap class="queryTDInput">
			<%=View.getPopOrgan("field_Q","q_deptId",qBean.getQ_deptId(),qBean.getQ_deptIdName(),"N","q_accountingYear","q_manageDept")%>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">職稱：</td>
		<td nowrap class="queryTDInput">
			<%=View.getPopCode("field_Q","q_userTitle",qBean.getQ_userTitle(),qBean.getQ_userTitleName(),"q_accountingYear","TIT","q_userTitleCodeId",qBean.getQ_userTitleCodeId())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">Email：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userEmail" size="20" maxlength="100" value="<%=qBean.getQ_userEmail()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">停用：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" type="select" name="q_isStop">
			<%=View.getYNOption(qBean.getQ_isStop())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">角色：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" type="select" name="q_roleId">
				<%=View.getRoleOption(qBean.getQ_roleId())%>			
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">

			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>