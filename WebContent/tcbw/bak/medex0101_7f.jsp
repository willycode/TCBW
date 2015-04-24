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
		<td nowrap ID=t3 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_3f.jsp');">不良反應</A></TD>		
		<td nowrap ID=t4 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_4f.jsp');">不良品</A></TD>		
		<td nowrap ID=t5 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_5f.jsp');">相關附件</A></TD>		
		<td nowrap ID=t6 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_6f.jsp');">轉送評估</A></TD>
		<td nowrap ID=t7 CLASS="tab_border1">不良反應評估</TD>
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
			<td nowrap class="td_form" width="20%">通報品質</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input type="radio" name="s3">Excellent&nbsp;&nbsp;
				<input type="radio" name="s3">Good&nbsp;&nbsp;
				<input type="radio" name="s3">Fair
			</td>
		</tr>		
		<tr>
			<td nowrap class="td_form_left" colspan="4">醫療器材背景資料摘要</td>
		</tr>	
		<tr>
			<td nowrap class="td_form" width="20%">仿單</td>
			<td nowrap class="td_form_white"  width="30%">
				<input type="checkbox" value="">是&nbsp;&nbsp;
				<input type="checkbox" value="">否&nbsp;&nbsp;
				<input type="checkbox" value="">未取得仿單&nbsp;&nbsp;
			</td>
			<td nowrap class="td_form" width="20%">文獻紀錄</td>
			<td nowrap class="td_form_white"  width="30%">
				<input type="checkbox" value="">PubMed，<input type="text" value="" size="2"/>件&nbsp;&nbsp;
				<input type="checkbox" value="">查無紀錄&nbsp;&nbsp;
			</td>			
		</tr>		
		<tr>
			<td nowrap class="td_form" width="20%">警訊查詢</td>
			<td nowrap class="td_form_white"  width="30%">
				<input type="checkbox" value="">FDA，<input type="text" value="" size="2" />件&nbsp;&nbsp;
				<input type="checkbox" value="">MHRA，<input type="text" value="" size="2" />件&nbsp;&nbsp;<br>
				<input type="checkbox" value="">ECRI，<input type="text" value="" size="2" />件&nbsp;&nbsp;
				<input type="checkbox" value="">查無紀錄&nbsp;&nbsp;
			</td>
			<td nowrap class="td_form" width="20%">不良事件通報</td>
			<td nowrap class="td_form_white"  width="30%">
			          統計區間 <%=View.getPopCalendar("field_RO","q_dateS","")%>~<%=View.getPopCalendar("field_RO","q_dateE","")%><br>
				國內醫材不良反應通報資料庫，<input class="field_RO" type="text" value="" size="2" />件<br>
				國內不良品通報資料庫，<input class="field_RO" type="text" value="" size="2" />件
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form" width="20%">醫材問題代碼</td>
			<td nowrap class="td_form_white"  width="30%">
				<%=View.getPopCode("field_Q","q_ovrRuleInsp","","","","CM","q_ovrRuleInspCodeId","",new int[]{2,2,10,20})%>
			</td>
			<td nowrap class="td_form" width="20%">病人問題代碼</td>
			<td nowrap class="td_form_white"  width="30%">
				<%=View.getPopCode("field_Q","q_ovrRuleInsp","","","","CM","q_ovrRuleInspCodeId","",new int[]{2,2,10,20})%>
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">可能性：評估不良反應，請回答下列問題，並勾選適當的答案</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
				<table width="70%" class="table_form" >
					<tr>
						<td nowrap class="listTH">項目</td>
						<td nowrap class="listTH">是</td>
						<td nowrap class="listTH">否</td>
						<td nowrap class="listTH">不知</td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >1.以前是否有關於此種不良事件確定的研究報告？</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s1" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s1" value="0"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s1" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >2.此不良事件是否發生於此醫療器材(技術)使用後？</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s2" value="2"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s2" value="-1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s2" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >3.當停止使用此醫療器材(技術)後，不良事件是否減輕？</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s3" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s3" value="0"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s3" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >4.停用後一段時間再使用，同樣的不良事件是否再度發生？</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s4" value="2"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s4" value="-1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s4" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >5.是否有其他原因可以引起同樣的不良事件？</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s5" value="-1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s5" value="2"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s5" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >6.對此病患而言，此醫療器材(技術)使用與不良事件是否成正向關係？</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s6" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s6" value="0"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s6" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >7.病患過去對同樣或類似醫療器材(技術)使用是否也會產生同樣的不良事件？</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s7" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s7" value="0"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s7" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >8.此項不良事件是否有客觀證據證明是此醫療器材(技術)引起的？</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s8" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s8" value="0"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="s8" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >總分:</td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >確定為下列何者:
							<input type="checkbox" value="">≦2分：存疑&nbsp;&nbsp;
							<input type="checkbox" value="">3-5分：可能相關&nbsp;&nbsp;
							<input type="checkbox" value="">6-8分：極有可能相關&nbsp;&nbsp;
							<input type="checkbox" value="">≧9：確定相關&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">事件嚴重程度</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
				<input type="checkbox" value="">輕度 (無需治療、停止使用器材或技術即可)&nbsp;&nbsp;
				<input type="checkbox" value="">中度 (需治療、導致住院或延長住院時間至少一天以上)&nbsp;&nbsp;<br>
				<input type="checkbox" value="">重度 (導致死亡、危及生命、需加護病房治療或需七天以上才能復原、造成永久性殘疾或先天性畸形)&nbsp;&nbsp;
				<input type="checkbox" value="">無法評估&nbsp;&nbsp;
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">初評意見</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
				<input type="checkbox" value="">先行結案，並持續受理通報。需注意觀察後續類似通報情形&nbsp;&nbsp;<br>
				<input type="checkbox" value="">應聯繫原始通報者，取得事件詳細說明。如：<input type="text" value="" /><br>
				<input type="checkbox" value="">應聯繫該醫材許可證持有商，取得事件詳細說明。如：<input type="text" value="" />&nbsp;&nbsp;
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他意見</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
				<textarea name="textarea" cols="60" rows="4"></textarea>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">NCAR通報篩選</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<table width="70%" class="table_form" >
					<tr>
						<td nowrap class="listTH">項目</td>
						<td nowrap class="listTH">是</td>
						<td nowrap class="listTH">否</td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >
						<font color="red">
						A.事件是否為嚴重危害?<br>(導致死亡、危及生命、需加護病房治療或需七天以上才能復原、造成永久性殘疾或先天性畸形)
						</font></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="a1" value="Y"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="a1" value="N"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >B.是否為非預期事件?</font></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="b1" value="Y"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="b1" value="N"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white"  colspan="3"><font color="red">C.如以上二題均選答均為「是」，請續答下列選項。</font></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white">1.事件危害範圍是否涉及易受傷害族群（兒童或年長者）?</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c1" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c1" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >2.是否可由事件中獲得改善建議措施?</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c2" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c2" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >3.事件危害是否大於使用醫材之利益?</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c3" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c3" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >4.醫材是否缺少有效的安全數據?</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c4" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c4" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >5.是否已發生多例相關問題事件?</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c5" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c5" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >6.政府單位是否已發布相關警訊新聞稿?</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c6" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c6" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >7.製造廠已發布/完成相關矯正措施?</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c7" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c7" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" >8.是否為全球性銷售產品?</td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c8" value="1"></td>
						<td nowrap class="td_form_white" style="text-align:center;"><input type="radio" name="c8" value="0"></td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >小計:</td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >
							NCAR通報結果：
							<input type="radio" value="" name="d1">通報NCAR&nbsp;&nbsp;
							<input type="radio" name="d1" value="">不通報NCAR
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >
							說明：<br>
							(1)	A、B選項需同時成立，方可進行本表C項問答。<br>
							(2)	合計分數≧5分，建議進行NCAR通報。<br>
							(3)	合計分數< 5分，建議無須進行NCAR通報。
						</td>
					</tr>
				</table>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">結案通知</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
				<input type="radio" value="" name="e1">通知廠商本案資料留廠備查&nbsp;&nbsp;
				<input type="radio" name="e1" value="">通知廠商來文至TFDA說明事件矯正措施
			</td>		
		</tr>
	</table>
</div>  
</td></tr>
<tr><td nowrap>
<br>
</td></tr>
<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD"> 
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">評估日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">通報品質</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">醫材與不良反應關聯性</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">NCAR通報狀況</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true,false,false,false,false};
	boolean displayArray[] = {false,true,true,true,true};
	out.write(View.getQuerylist(primaryArray,displayArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td>
</tr>
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
