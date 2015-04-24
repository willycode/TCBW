<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0109F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<table id="Tab4" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" >案號</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="applNo" size="10" value="<%=obj.getApplNo()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >分級確認日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field_RO","gradeDate",obj.getGradeDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >不良品風險評估結果</td>
			<td nowrap class="td_form_white"  width="75%">
				 <%=View.getRadioBoxOption("field", "drgLev", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRKL' order by codeShortName", obj.getDrgLev())%>
			</td>
		</tr>		
</table>
