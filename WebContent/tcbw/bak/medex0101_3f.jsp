<!--
程式目的：醫療器材不良事件通報登錄作業
程式代號：medex0101
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medex.MEDEX0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	//obj = (com.kangdainfo.tcbw.view.medex.MEDEX0101F)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
}

objList = (java.util.ArrayList) obj.queryAll();
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">

var insertDefault = new Array();

function init() {
	setDisplayItem("spanConfirm,spanDelete,spanListPrint,spanListHidden","H");		
}

function checkURL(surl){
	var alertStr = "";	
	//alertStr += checkEmpty(form1.applyNo,"主檔編號");
	if(alertStr.length != 0){
		alert("請先執行查詢, 若已查到資料則請選取其中一筆資料");
		return false;
	} else {
		form1.state.value="";
	}
	form1.action = surl;
	beforeSubmit();
	form1.submit();
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
<table width="100%" border="0" CELLPADDING="0" CELLSPACING="2" align="center">
<tr><td nowrap>
<jsp:include page="../../home/toolbar.jsp">
	<jsp:param value="N" name="btnCopy"/>
</jsp:include>
<span id="spanSave">
    <input class="toolbar_default" type="button" followPK="true"  name="btnSave" value="暫   存" onClick="">&nbsp;
</span>
<span id="spanMaintain">
    <input class="toolbar_default" type="button" followPK="true"  name="btnMaintain" value="送   出" onClick="">&nbsp;
</span>
<span id="spanReUpdate">
    <input class="toolbar_default" type="button" followPK="true"  name="btnReUpdate" value="補件回復" onClick="">&nbsp;
</span>
</td></tr>
</table>
</td></tr>

<tr><td nowrap>
  <% request.setAttribute("QueryBean",obj);%>
  <jsp:include page="../../home/page_row.jsp" />
</td></tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>		
		<td nowrap ID=t1 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101f.jsp');">基本資料</A></TD>		
		<td nowrap ID=t2 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_2f.jsp');">不良事件</A></TD>
		<td nowrap ID=t3 CLASS="tab_border1">不良反應</TD>
		<td nowrap ID=t4 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_4f.jsp');">不良品</A></TD>
		<td nowrap ID=t5 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_5f.jsp');">相關附件</A></TD>
		<td nowrap ID=t6 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_6f.jsp');">轉送評估</A></TD>
		<td nowrap ID=t7 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_7f.jsp');">不良反應評估</A></TD>
		<td nowrap ID=t8 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_8f.jsp');">不良品初評</A></TD>
	</TR>
	<TR>	
		<td nowrap ID=t9 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_9f.jsp');">不良品廠商回覆</A></TD>
		<td nowrap ID=t10 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_10f.jsp');">不良品複評</A></TD>
		<td nowrap ID=t11 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_11f.jsp');">歷史通報</A></TD>
		<td nowrap ID=t12 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_12f.jsp');">歷史版次</A></TD>
		<td nowrap ID=t13 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_13f.jsp');">案件補件</A></TD>
		<td nowrap ID=t14 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_14f.jsp');">郵件清單紀錄</A></TD>
		<td nowrap ID=t15 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_15f.jsp');">案件流程紀錄</A></TD>
	</TR>
</TABLE>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">	
<div>
	<table width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="20%">不良反應結果</td>
			<td nowrap class="td_form_white" colspan="3">
				<input type="checkbox" name="s1">A.死亡，日期︰
					<%=View.getPopCalendar("field","limitDate","")%>  &nbsp;&nbsp;死亡原因︰<input type="text" value=""  />&nbsp;&nbsp;
				<input type="checkbox" name="s1">B.危及生命&nbsp;&nbsp;
				<input type="checkbox" name="s1">C.造成永久性殘疾&nbsp;&nbsp;<br>
				<input type="checkbox" name="s1">D.胎兒先天性畸形&nbsp;&nbsp;
				<input type="checkbox" name="s1">E.導致病人住院或延長病人住院時間&nbsp;&nbsp;<br>
				<input type="checkbox" name="s1">F.需作處置以防永久性傷害&nbsp;&nbsp;
				<input type="checkbox" name="s1">G.非嚴重不良反應(請敘述)：<input type="text" value=""  />
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">器材操作者</td>
			<td nowrap class="td_form_white" colspan="3">
				<input type="radio" name="s1">醫療人員&nbsp;&nbsp;
				<input type="radio" name="s1">病人或其家屬&nbsp;&nbsp;
				<input type="radio" name="s1">其他
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">器材處置現況</td>
			<td nowrap class="td_form_white" colspan="3">
				<input type="radio" name="s1">已銷毀&nbsp;&nbsp;
				<input type="radio" name="s1">尚在調查中&nbsp;&nbsp;
				<input type="radio" name="s1">尚植於病患體內&nbsp;&nbsp;
				<input type="radio" name="s1">於<%=View.getPopCalendar("field","limitDate","")%>退還廠商(原廠)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">器材使用</td>
			<td nowrap class="td_form_white" colspan="3">
				<input type="radio" name="s1">初次使用&nbsp;&nbsp;
				<input type="radio" name="s1">拋棄式器材重覆使用&nbsp;&nbsp;
				<input type="radio" name="s1">可反覆使用式器材重覆使用&nbsp;&nbsp;
				<input type="radio" name="s1">重新維修/整修過&nbsp;&nbsp;
				<input type="radio" name="s1">其他<input type="text" value=""  />
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">曾使用同類醫材之經驗</td>
			<td nowrap class="td_form_white" colspan="3">
				<input type="radio" name="s1">是，醫材名稱<input type="text" value=""  /> 若發生不良反應請描述<input type="text" value=""  /> <br>
				<input type="radio" name="s1">否&nbsp;&nbsp;
				<input type="radio" name="s1">無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">停用後不良反應症狀是否減輕</td>
			<td nowrap class="td_form_white" colspan="3">
				<input type="radio" name="s1">是&nbsp;&nbsp;
				<input type="radio" name="s1">否&nbsp;&nbsp;
				<input type="radio" name="s1">無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">再使用是否出現同樣反應</td>
			<td nowrap class="td_form_white" colspan="3">
				<input type="radio" name="s1">是&nbsp;&nbsp;
				<input type="radio" name="s1">否&nbsp;&nbsp;
				<input type="radio" name="s1">無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">不良事件描述</td>
		</tr>
		<tr>
		    <td nowrap class="td_form" width="20%">不良事件描述資訊</td>			
			<td nowrap class="td_form_white"  colspan="3">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="15%">發生日期</th>
		         <th class="listTH" width="15%">部位</th>
		         <th class="listTH" width="30%">症狀</th>
		         <th class="listTH" width="10%">嚴重程度</th>
		         <th class="listTH" width="30%">處置</th>
	           </tr>
	           </thead>
	           <tr>
		         <th class="listTROdd">101/10/18</th>
		         <th class="listTROdd">手腕</th>
		         <th class="listTROdd">紅腫</th>
		         <th class="listTROdd">普通</th>
		         <th class="listTROdd">冰敷</th>
	           </tr>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">相關檢查及檢驗數據資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">相關檢查,檢驗數據及其他資料</td>
			<td nowrap class="td_form_white"  colspan="3">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH">檢驗日期</th>
		         <th class="listTH">檢驗項目</th>
		         <th class="listTH">檢驗數據</th>
	           </tr>
	           </thead>
	           <tr>
		         <th class="listTROdd">101/10/18</th>
		         <th class="listTROdd">xxxx</th>
		         <th class="listTROdd">xxxx</th>
	           </tr>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">併用醫療器材資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">併用醫療器材</td>		
			<td nowrap class="td_form_white"  colspan="3">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="10%">許可證字</th>
		         <th class="listTH" width="15%">許可證號</th>
		         <th class="listTH" width="20%">品名</th>
		         <th class="listTH" width="10%">許可證申請商</th>
		         <th class="listTH" width="10%">型號</th>
		         <th class="listTH" width="10%">醫材主類別</th>		         
		         <th class="listTH" width="10%">使用日期</th>
		         <th class="listTH" width="15%">使用原因</th>
	           </tr>
	           </thead>
	           <tr>
		         <th class="listTROdd">
		            <%=View.getPopCode("field","q_ovrRuleInsp","","","","CM","q_ovrRuleInspCodeId","",new int[]{2,2,2,3})%>
		         </th>
		         <th class="listTROdd">
		            <input type="text" value="DOH1232131212"  size="15" />
		         </th>
		         <th class="listTROdd">
		            <input class="field_RO"  type="text" value="xx醫療器材"  size="15" />
                 </th>
		         <th class="listTROdd">
		            <input class="field_RO"  type="text" value="xx製造商"  size="10" />
		         </th>
		         <th class="listTROdd">
		            <input class="field"  type="text" value=""  size="5" />
                 </th>
		         <th class="listTROdd">
		            <input class="field_RO"  type="text" value="主類別A"  size="10" />
		         </th>
		         <th class="listTROdd">
		            <%=View.getPopCalendar("field_Q","q_dateS","")%>
                 </th>
                 <th class="listTROdd">
                    <input class="field"  type="text" value=""  size="15" />
                 </th>
	           </tr>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">併用藥品資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">併用藥品</td>		
			<td nowrap class="td_form_white"  colspan="3">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="15%">學名/商品名</th>
		         <th class="listTH" width="10%">含量</th>
		         <th class="listTH" width="10%">劑型</th>
		         <th class="listTH" width="10%">給藥途徑</th>
		         <th class="listTH" width="10%">劑量</th>
		         <th class="listTH" width="10%">頻率</th>		         
		         <th class="listTH" width="25%">使用期間</th>
		         <th class="listTH" width="10%">使用原因</th>
	           </tr>
	           </thead>
	           <tr>
		         <th class="listTROdd">
		            <input class="field"  type="text" value="xx藥劑"  size="15" />
		         </th>
		         <th class="listTROdd">
		            <input type="text" value="xx"  size="15" />
		         </th>
		         <th class="listTROdd">
		            <input class="field"  type="text" value="xx"  size="15" />
                 </th>
		         <th class="listTROdd">
		            <input class="field"  type="text" value="xxx"  size="10" />
		         </th>
		         <th class="listTROdd">
		            <input class="field"  type="text" value=""  size="5" />
                 </th>
		         <th class="listTROdd">
		            <input class="field"  type="text" value=""  size="10" />
		         </th>
		         <th class="listTROdd">
		            <%=View.getPopCalendar("field","q_dateS","")%> ~ <%=View.getPopCalendar("field","q_dateS","")%>
                 </th>
                 <th class="listTROdd">
                    <input class="field"  type="text" value=""  size="15" />
                 </th>
	           </tr>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">其他相關資料</td>
			<td nowrap class="td_form_white" colspan="3">
			    <textarea name="textarea" cols="60" rows="2"></textarea>
			</td>
		</tr>
	</table>
</div>  
</td></tr>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName)	{
		case "insert":
			break;		
	}
}
</script>
</body>
</html>
