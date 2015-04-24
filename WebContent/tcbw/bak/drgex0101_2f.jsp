<!--
程式目的：藥品不良品通報登錄作業
程式代號：drgex0101
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	//obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0101F)obj.queryOne();	
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

function show(a){
	var obj = document.getElementById(a.name+"1");
	if(a.checked){
		obj.style.display="block";
	}else{
		obj.style.display="none";
	}
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
		<td nowrap nowrap CLASS="tab_border2" ID=t1><A HREF="#" ONCLICK="return checkURL('drgex0101f.jsp');">基本資料</A></TD>
		<td nowrap nowrap CLASS="tab_border1" ID=t2>不良藥品資料</TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t3><A HREF="#" ONCLICK="return checkURL('drgex0101_3f.jsp');">其他相關附件</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t4><A HREF="#" ONCLICK="return checkURL('drgex0101_4f.jsp');">案件初評</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t5><A HREF="#" ONCLICK="return checkURL('drgex0101_5f.jsp');">案件分級確認</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t6><A HREF="#" ONCLICK="return checkURL('drgex0101_6f.jsp');">CAPA確認表</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t7><A HREF="#" ONCLICK="return checkURL('drgex0101_7f.jsp');">CAPA分析表</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t8><A HREF="#" ONCLICK="return checkURL('drgex0101_8f.jsp');">CAPA評估表</A></TD>
	</TR>
	<TR>	
		<td nowrap nowrap CLASS="tab_border2" ID=t9><A HREF="#" ONCLICK="return checkURL('drgex0101_9f.jsp');">案件評估</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t10><A HREF="#" ONCLICK="return checkURL('drgex0101_10f.jsp');">案件分析</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t11><A HREF="#" ONCLICK="return checkURL('drgex0101_11f.jsp');">歷史通報</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t12><A HREF="#" ONCLICK="return checkURL('drgex0101_12f.jsp');">歷史CAPA評估</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t13><A HREF="#" ONCLICK="return checkURL('drgex0101_13f.jsp');">歷史版次</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t14><A HREF="#" ONCLICK="return checkURL('drgex0101_14f.jsp');">案件補件</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t15><A HREF="#" ONCLICK="return checkURL('drgex0101_15f.jsp');">郵件清單紀錄</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t16><A HREF="#" ONCLICK="return checkURL('drgex0101_16f.jsp');">案件流程紀錄</A></TD>
	</TR>
</TABLE>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">	
<div>
	<table width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">不良藥品資訊</td>
		</tr>
		<tr>
			<td nowrap width="20%" nowrap class="td_form">藥品商品名</td>
			<td nowrap colspan="3" nowrap class="td_form_white" style="text-align:left;">
				中文品名
				  <input type="text" value="" size="40"/>
				<br>
			  外文品名<input type="text" value="" size="40"/>			</td>		
		</tr>
		<tr> 
			<td nowrap width="20%" nowrap class="td_form">許可證字號</td>
			<td nowrap colspan="3" nowrap class="td_form_white" style="text-align:left;" >
				<input type="text" value="" size="20" />
			  <input value="..." type="button"/>			</td>
		</tr>
		<tr>
			<td nowrap width="20%" nowrap class="td_form">有效成分名稱</td>
			<td nowrap width="30%" nowrap class="td_form_white" style="text-align:left;">
			  <input type="text" value="" size="40"/>			</td>
			<td nowrap width="20%" nowrap class="td_form">單位含量</td>
			<td nowrap width="30%" nowrap class="td_form_white" style="text-align:left;">
			  <input type="text" value="" size="5" />			</td>
		</tr>
		<tr>
			<td nowrap width="20%" nowrap class="td_form">劑型</td>
			<td nowrap colspan="3" nowrap class="td_form_white" style="text-align:left;">
				<input type="checkbox" name="c1">錠劑&nbsp;&nbsp;
				<input type="checkbox" name="c1">膠囊劑&nbsp;&nbsp;
				<input type="checkbox" name="c1">口服顆粒劑&nbsp;&nbsp;
				<input type="checkbox" name="c1">口服液劑&nbsp;&nbsp;
				<input type="checkbox" name="c1">注射液劑&nbsp;&nbsp;
				<input type="checkbox" name="c1">注射粉劑&nbsp;&nbsp;
				<input type="checkbox" name="c1">外用&nbsp;&nbsp;
				<input type="checkbox" name="c1">眼用&nbsp;&nbsp;
				<input type="checkbox" name="c1">耳用&nbsp;&nbsp;
				<br>
			  <input type="checkbox" name="c1">其他(請描述)：<input type="text" value="" />			</td>		
		</tr>
		<tr>
			<td nowrap width="20%" nowrap class="td_form">包裝形式</td>
			<td nowrap colspan="3" nowrap class="td_form_white" style="text-align:left;">
				<input type="checkbox" name="c2">片裝&nbsp;&nbsp;
				<input type="checkbox" name="c2">瓶裝&nbsp;&nbsp;
				<input type="checkbox" name="c2">盒裝&nbsp;&nbsp;
			  <input type="checkbox" name="c2">其他(請描述)：<input type="text" value="" />			</td>		
		</tr>
		<tr>
			<td nowrap width="20%" nowrap class="td_form">是否一經拆封即發現本不良品缺陷</td>
			<td nowrap width="25%" nowrap class="td_form_white" style="text-align:left;">
				<input type="radio" name="s1">否&nbsp;&nbsp;
			  <input type="radio" name="s1">是			</td>
			<td nowrap width="20%" nowrap class="td_form">本次通報事件是否為單一個案</td>
			<td nowrap width="25%" nowrap class="td_form_white" style="text-align:left;">
				<input type="radio" name="s2">否，共<input type="text" value="" size="3" />件&nbsp;&nbsp;
			  <input type="radio" name="s2">是			</td>
		</tr>
		<tr>
			<td nowrap width="20%" nowrap class="td_form">藥商</td>
			<td nowrap width="30%" nowrap class="td_form_white" style="text-align:left;">
			  <input type="text" value="" size="40" />			</td>
			<td nowrap width="20%" nowrap class="td_form">製造商</td>
			<td nowrap width="30%" nowrap class="td_form_white" style="text-align:left;">
			  <input type="text" value="" size="40" />			</td>
		</tr>
		<tr>
			<td nowrap width="20%" nowrap class="td_form">製造批號</td>
			<td nowrap colspan="3" nowrap class="td_form_white" style="text-align:left;" >
			  <input type="text" value="" size="20" />			</td>
		</tr>
		<tr>
			<td nowrap width="20%" nowrap class="td_form">保存期限</td>
			<td nowrap width="30%" nowrap class="td_form_white" style="text-align:left;">
				<%=View.getPopCalendar("field","limitDate","1020621")%> ~ 
			  <%=View.getPopCalendar("field","limitDate","1020621")%>			</td>
			<td nowrap width="20%" nowrap class="td_form">購買/收到/調劑日期</td>
			<td nowrap width="30%" nowrap class="td_form_white" style="text-align:left;">
				<%=View.getPopCalendar("field","limitDate","1020621")%>			</td>
		</tr>
		<tr>
			<td nowrap width="20%" nowrap class="td_form">儲存環境</td>
			<td nowrap colspan="3" nowrap class="td_form_white" style="text-align:left;">
				<input type="checkbox" name="c3">室溫&nbsp;&nbsp;
				<input type="checkbox" name="c3">避光陰涼處&nbsp;&nbsp;
				<input type="checkbox" name="c3">冷藏&nbsp;&nbsp;
			  <input type="checkbox" name="c3">其他儲存環境(請描述)︰<input type="text" value="" />			</td>		
		</tr>
		<tr>
			<td nowrap colspan="4" nowrap class="td_form_left">是否已對人體健康產生危害</td>
		</tr>
		<tr>
			<td nowrap colspan="4" nowrap class="td_form_white" style="text-align:left;">
				<input type="checkbox" name="c4">是，並請同時作藥物不良反應通報&nbsp;&nbsp;
			  <input type="checkbox" name="c4">否			</td>
		</tr>
		<tr>
			<td nowrap colspan="4" nowrap class="td_form_left">不良藥品已對人體健康產生危害之程度</td>
		</tr>
		<tr>
			<td nowrap colspan="4" nowrap class="td_form_white" style="text-align:left;">
				<input type="checkbox" name="c5">死亡，日期︰
				<%=View.getPopCalendar("field","limitDate","1020621")%>
				，死亡原因︰<input type="text" value="" />&nbsp;&nbsp;
				<input type="checkbox" name="c5">藥品確有損害使用者生命、身體或健康之事實
				<br>
				<input type="checkbox" name="c5">有損害使用者之虞者&nbsp;&nbsp;
			  <input type="checkbox" name="c5">其他(請描述)︰<input type="text" value="" />			</td>
		</tr>
		<tr>
			<td nowrap colspan="4" nowrap class="td_form_left">已經與廠商/製造商接觸過了嗎？□是 □否</td>
		</tr>
		<tr>
			<td nowrap width="20%" nowrap class="td_form">後續處理</td>
			<td nowrap colspan="3" nowrap class="td_form_white" style="text-align:left;" >
				<input type="radio" name="s3">無處理&nbsp;&nbsp;
				<input type="radio" name="s3">單一換貨&nbsp;&nbsp;
				<input type="radio" name="s3">整批換貨&nbsp;&nbsp;
			  <input type="radio" name="s3">更換廠商&nbsp;&nbsp;			</td>
		</tr>
		<tr>
			<td nowrap colspan="4" nowrap class="td_form_left">請詳加填寫（通報中心將以此為聯絡依據）</td>
		</tr>
		<tr>
			<td nowrap colspan="4" nowrap class="td_form_white" style="text-align:left;">
				是否可提供樣品以供廠商或TFDA檢驗之用？
				<input type="radio" name="s4">是&nbsp;&nbsp;<input type="radio" name="s4">否&nbsp;&nbsp;
				<br>
				是否願意提供聯絡資訊供廠商後續調查評估？
			  <input type="radio" name="s5">是&nbsp;&nbsp;<input type="radio" name="s5">否&nbsp;&nbsp;			</td>
		</tr>
		<tr>
			<td nowrap colspan="4" nowrap class="td_form_left">不良品缺陷之描述</td>
		</tr>
		<tr>
			<td nowrap colspan="4" nowrap class="td_form_white" style="text-align:left;">
				<input type="checkbox" name="a" onclick="show(this);" >外觀異常(非包材)<br>
				<div id="a1" style="display:none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" value="checkbox">顏色異常
					<input type="checkbox" value="checkbox">外漏外溢
					<input type="checkbox" value="checkbox">外觀不良
					<input type="checkbox" value="checkbox">結晶析出
					<input type="checkbox" value="checkbox">大小異常
					<input type="checkbox" value="checkbox">油水分離
					<input type="checkbox" value="checkbox">發霉
					<input type="checkbox" value="checkbox">碎裂/破損/缺損
					<input type="checkbox" value="checkbox">受潮(潮溼)
					<input type="checkbox" value="checkbox">結塊
					<input type="checkbox" value="checkbox">藥品黏在一起
					<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" value="checkbox">其他(請描述)：<input type="text" value="" />
				</div>
				<div style="border:1px solid #000000" ></div>
				<input type="checkbox" name="b" onclick="show(this);" >雜質/異物<br>
				<div id="b1" style="display:none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" value="checkbox">毛髮
					<input type="checkbox" value="checkbox">異物混入藥品內
					<input type="checkbox" value="checkbox">異物接觸藥品表面(未混入藥品內)
					<input type="checkbox" value="checkbox">其他(請描述)：<input type="text" value="" />
				</div>
				<div style="border:1px solid #000000" ></div>
				<input type="checkbox" name="j" onclick="show(this);" >藥品標示<br>
				<div id="j1" style="display:none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" value="checkbox">塗改有效期限
					<input type="checkbox" value="checkbox">標示錯誤
					<input type="checkbox" value="checkbox">標示不清
					<input type="checkbox" value="checkbox">無標示/無標籤
					<input type="checkbox" value="checkbox">缺批號或效期
					<input type="checkbox" value="checkbox">其他(請描述)：<input type="text" value="" />
				</div>
				<div style="border:1px solid #000000" ></div>
				<input type="checkbox" name="d" onclick="show(this);" >疑似偽禁藥<br>
				<div id="d1" style="display:none">
					<textarea name="textarea" cols="150" rows="2"></textarea>
				</div>
				<div style="border:1px solid #000000" ></div>
				<input type="checkbox" name="e" onclick="show(this);" >產品包裝<br>
				<div id="e1" style="display:none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" value="checkbox">空包
					<input type="checkbox" value="checkbox">外漏/外溢
					<input type="checkbox" value="checkbox">瓶口未密封
					<input type="checkbox" value="checkbox">外包裝異常
					<input type="checkbox" value="checkbox">產品包裝不全
					<input type="checkbox" value="checkbox">鬆脫
					<input type="checkbox" value="checkbox">容器破裂
					<input type="checkbox" value="checkbox">無法開啟/使用
					<input type="checkbox" value="checkbox">其他(請描述)：<input type="text" value="" />
				</div>
				<div style="border:1px solid #000000" ></div>
				<input type="checkbox" name="f" onclick="show(this);" >操作發生相關問題<br>
				<div id="f1" style="display:none">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" value="checkbox">無法依說明使用
					<input type="checkbox" value="checkbox">雙/三腔軟袋於操作前/中破損漏液
					<input type="checkbox" value="checkbox">針管與針頭接合處無法密合
					<input type="checkbox" value="checkbox">插針不密合
					<input type="checkbox" value="checkbox">其他(請描述)：<input type="text" value="" />
				</div>
				<div style="border:1px solid #000000" ></div>
				<input type="checkbox" name="g" onclick="show(this);" >過期<br>
				<div id="g1" style="display:none">
					<textarea name="textarea" cols="150" rows="2"></textarea>
				</div>
				<div style="border:1px solid #000000" ></div>
				<input type="checkbox" name="h" onclick="show(this);" >未達預期效果<br>
				<div id="h1" style="display:none">
					<textarea name="textarea" cols="150" rows="2"></textarea>
				</div>
				<div style="border:1px solid #000000" ></div>
				<input type="checkbox" name="i" onclick="show(this);" >其他<br>
				<div id="i1" style="display:none">
					<textarea name="textarea" cols="150" rows="2"></textarea>
			  </div>			</td>
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
