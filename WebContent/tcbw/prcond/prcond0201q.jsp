<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
    com.kangdainfo.tcbw.view.prcond.PROCOND0201F qBean = ( com.kangdainfo.tcbw.view.prcond.PROCOND0201F)request.getAttribute("qBean");
%>
    <div class="queryTitle">查詢視窗</div>
	<table class="queryTable"  border="1">
	<tr>
		<td nowrap width="15%" class="queryTDLable">專案類別：</td>
		<td class="queryTDInput">
			<select class="field_Q" name="q_projType" onChange="q_changKind()">
			 <%=View.getTextOption("drg;藥品;med;醫療器材",qBean.getQ_projType(),0)%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" class="queryTDLable">專案名稱：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" size="30" name="q_projName" value="<%=qBean.getQ_projName()%>" />
		</td>
	</tr> 
	
	<tr id="q_med">
		<td nowrap class="queryTDLable">許可證字號：</td>
		<td nowrap class="queryTDInput" >
			<select name="q_permitKeyMed" class="field_Q">
			  <%=View.getOptionCodeKind("MEDPKID",qBean.getQ_permitKeyMed())%>
			</select>
			字第<input class="field_Q" type="text" name="q_permitNoMed" size="10"  value="<%=qBean.getQ_permitNoMed()%>">號
		</td>
	</tr>
	
	<tr id="q_drg">
		<td nowrap class="queryTDLable">許可證字號：</td>
		<td nowrap class="queryTDInput" >
			<select name="q_permitKeyDrg" class="field_Q">
			  <%=View.getOptionCodeKind("DRGPKID",qBean.getQ_permitKeyDrg())%>
			</select>
			字第<input class="field_Q" type="text" name="q_permitNoDrg" size="10"  value="<%=qBean.getQ_permitNoDrg()%>">號
		</td>
	</tr>
	
	<tr>
		<td nowrap class="queryTDLable">中文品名：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" size="30" name="q_chProduct" value="<%=qBean.getQ_chProduct()%>" />
		</td>
	</tr>
			
	<tr>
		<td nowrap class="queryTDLable">文件日期：</td>
		<td nowrap class="queryTDInput">
			<%=View.getPopCalendar("field_Q","q_docdateS",qBean.getQ_docdateS())%>~
			<%=View.getPopCalendar("field_Q","q_docdateE",qBean.getQ_docdateE())%>
		</td>
	</tr>
			
	<tr>
		<td nowrap class="queryTDLable">是否結案：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" name="q_isclose">
			  <%=View.getYNOption(qBean.getQ_isclose())%>
			</select>
		</td>
	</tr>	
	<tr>
		<td class="queryTDInput" colspan="2" style="text-align:center;">

			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>