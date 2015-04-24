<!--
程式目的：子系統管理者授權程式維護作業
程式代號：conin0301f.jsp
程式日期：102.10.07
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONIN0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conin.CONIN0302F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%

if (obj.getState().equals("submit")) obj.updateCommonSysDeptUser();

obj = (com.kangdainfo.tcbw.view.conin.CONIN0302F)obj.queryOne();
%>
<html>
<head>
<%@ include file="../../home/meta.jsp" %>
<script type="text/javascript">
$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
});

function checkURL(surl){
	columnTrim(form1.id);
	if(form1.id.value == ""){
		alert("請先執行查詢!");
	}else{
		form1.action = surl;
		beforeSubmit();
		form1.submit();
	}
}

function checkListIsSelect(buttonName){
	var alertStr="";	
	if(buttonName=="add"){
		alertStr += "[你必須先選擇左邊的機關方能進行新增]\n";
		for (var i = 0; i < document.form1.auth.length; i++) {
			if (document.form1.auth.options[i].selected==true) {				
				alertStr = "";
			}
	   	}
	} else {
		if(buttonName=="remove"){
			alertStr += "[你必須先選擇右邊已兼辦的機關方能移除]";
			for (var i = 0; i < document.form1.authed.length; i++) {
				if (document.form1.authed.options[i].selected==true) {				
					alertStr = "";
				}
		   	}
		}
	}
	//alertStr += checkEmpty(form1.subsystemCode,"子系統代碼");
	//alertStr += checkEmpty(form1.uid,"使用者帳號");
	if(alertStr.length!=0){ alert(alertStr); return false; }
	else {		
		if(buttonName=="add"){
			document.all("optype").value = "add";
		}else{
			document.all("optype").value = "remove";
		}
		form1.state.value = "submit";
		beforeSubmit();
		form1.submit();
	}
}
</script>
</head>	
<body topmargin="0" onLoad="whatButtonFireEvent('<%=obj.getState()%>');showErrorMsg('<%=obj.getJsMsg()%>',1);">
<form id="form1" name="form1" method="post" autocomplete="off">
<TABLE CELLPADDING=0 CELLSPACING=0 valign="top">
	<tr>		
		<td nowrap ID=t1 CLASS="tab_border2" width="100"><a href="#" onClick="return checkURL('conin0301f.jsp');">使用者資料</a></td>
		<td nowrap ID=t2 CLASS="tab_border1" width="100" HEIGHT="25">授權維護作業</td>
	</tr>
	<tr>
		<td nowrap class="tab_line1"></td>
		<td nowrap class="tab_line2"></td>	
	</tr>
</TABLE>
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap>
<div id="formContainer" style="height:auto;">
	<table class="table_form" width="80%" height="auto">
    <tr>
    	<td nowrap class="td_form" height="40px">系統別：</td>
    	<td nowrap class="td_form_white">
    		<%=View.getCheckBoxOption("field_RO", "subSystem", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId = 'SYS' order by codeId ", obj.getSubSystem())%>
        </td>
    </tr>
    <tr>
        <td nowrap class="td_form" height="40px">異動人員/日期：</td>
        <td nowrap class="td_form_white">
        	[
	    	<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">
	    	/
	    	<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">
	    	] 
        </td>
    </tr>
    </table>
</div>
</td></tr>

<tr><td nowrap class="bgToolbar" style="text-align:center;">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="q_userId" value="<%=obj.getQ_userId()%>">
    <input type="hidden" name="q_userName" value="<%=obj.getQ_userName()%>">
    <input type="hidden" name="optype" value="">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>

<tr><td nowrap>
<div id="listContainer" style="height:auto;">
	<table class="table_form" width="80%" cellspacing="0" cellpadding="0">
	<tr>
		<td nowrap class="td_form" style="text-align:left">授權身份</td>
		<td nowrap class="td_form" width="5%"></td>
		<td nowrap class="td_form" style="text-align:left">已授權身份</td>
	</tr>
	<tr>
		<td nowrap class="td_form_white" width="45%">
			<select class="field_Q" name="auth" size="20" style="width:50%" multiple="true" type="select">
				<%=obj.getStrAuth() %>
			</select>
		</td>
		<td width="100" align="center" valign="top" class="td_form_white">
		<table width="100%" border="0" align="center">
		         <tr>
		        	 <td align="center" valign="middle">
		        	 	<br>
						<input class="toolbar_default" type="button" name="add"    value="新  增  權  限 " onClick="return checkListIsSelect(this.name)">
		        	 	<br>
						<br>
						<br>
						<input class="toolbar_default" type="button" name="remove" value="移  除  權  限" onClick="return checkListIsSelect(this.name)">
		        	 	<br>
						<br>
						<br>
                     </td>
		         </tr>
		</table></td>
		<td nowrap class="td_form_white" width="45%">
			<select class="field_Q" name="authed" size="20" style="width:50%" multiple="true" type="select">
				<%=obj.getStrAuthed() %>
			</select>
		</td>
	</tr>
	</table>
</div>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch(buttonName){

	}
}
</script>  
</body>
</html>

