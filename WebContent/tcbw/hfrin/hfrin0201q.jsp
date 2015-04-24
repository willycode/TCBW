<!--
程式目的：食品通報案件綜合查詢作業
程式代號：hfrin0201q
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFRIN0201" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0201Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
if ("true".equals(obj.getQueryAllFlag())){	
	objList = (java.util.ArrayList) obj.doQueryAll();
	obj.setStateQueryOneSuccess();
}
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
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
var isData = false;
function checkField(){
	var alertStr = "";
	form1.state.value = "queryAll";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
		if(form1.q_eatersAgeS.value != "")
			alertStr += checkInt(form1.q_eatersAgeS, "食用者年齡(起)");
		if(form1.q_eatersAgeE.value != "")
			alertStr += checkInt(form1.q_eatersAgeE, "食用者年齡(訖)");
	}
	
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function init() {
	<%if(objList != null && objList.size() > 0){%>
	isData = true;
	<%}%>
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
}

function queryOne(id, hType){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1250,height=720,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/hfrin/hfrin0102q.jsp?isPop=A&id="+id+"&hType="+hType,"",prop);
}

function goReport(){
	var isCheck = $('input:checkbox:checked[name="reportField"]').val();
	if(isData){
		if(isCheck  == undefined){
			alert("至少選取一個查詢結果欄位!");
		}else{
			form1.action = "hfrin0201p.jsp";
			form1.target = "_blank";
			beforeSubmit();
			form1.submit();			
			form1.action = "hfrin0201q.jsp";
			form1.target = "_self";
		}
	}else{
		alert("請先執行查詢!");
	}
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
    <input type="hidden" name="id" value="">	
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<input class="toolbar_default" type="submit" followPK="false" name="hfrin0201q_queryAll" value="查　詢" onSubmit="return checkField()">&nbsp;
	<span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
	<input class="toolbar_default" type="button" followPK="false" name="doReport" value="匯出EXCEL" onClick="goReport();">
</td></tr>
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">
	<tr>
		<td nowrap class="td_form_left" colspan="4">查詢條件：</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRevDateS",obj.getQ_notifierRevDateS())%>
			~<%=View.getPopCalendar("field_Q","q_notifierRevDateE",obj.getQ_notifierRevDateE())%>				
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報來源：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field_Q", "q_informedSources", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='HFRNFS' order by codeId", obj.getQ_informedSources())%>						
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報者屬性：</td>
		<td nowrap nowrap class="td_form_white" >
			<select class="field_Q" name="q_notifierType">
				<%=View.getOptionCommonDepartment(obj.getQ_notifierType(), "02;03;04;") %>
			</select>		
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報者職稱：</td>
		<td nowrap nowrap class="td_form_white" >
			<select class="field_Q" name="q_notifierTitle" type="select">
				<%=View.getOptionCodeKind("TITLE", obj.getQ_notifierTitle()) %>
			</select>					
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">食用者性別：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="radio" name="q_eatersSex" value="M" <%="M".equals(obj.getQ_eatersSex())?"checked":"" %>>男
			&nbsp;
			<input class="field_Q" type="radio" name="q_eatersSex" value="F" <%="F".equals(obj.getQ_eatersSex())?"checked":"" %>>女
		</td>
		<td nowrap width="15%" nowrap class="td_form">食用者年齡：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_eatersAgeS" size="2" maxlength="3" value="<%=obj.getQ_eatersAgeS()%>">
			~<input class="field_Q" type="text" name="q_eatersAgeE" size="2" maxlength="3" value="<%=obj.getQ_eatersAgeE()%>">
		</td>
	</tr>
		<tr>
		<td nowrap width="15%" nowrap class="td_form">食品類別：</td>
		<td nowrap nowrap class="td_form_white">
		<%=View.getCheckBoxTextOption("field_Q","q_type","G;健康食品;N1;核備食品;N2;一般食品;",obj.getQ_type())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">食品名稱：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct" size="50" maxlength="100" value="<%=obj.getQ_chProduct()%>">
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">食品字號：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_permitKey">
				<%=View.getOptionCodeKind("HFRPKID", obj.getQ_permitKey()) %>
			</select>
			<input class="field_Q" type="text" name="q_permitNo" size="10" maxlength="10" value="<%=obj.getQ_permitNo()%>">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">購買來源：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_buySource" >
				<%=View.getOptionCodeKind("HFRBYS", obj.getQ_buySource()) %>
			</select>		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">非預期反應分類：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_unExpectedClassify">
				<%=View.getOptionCodeKind("HFRFUC", obj.getQ_unExpectedClassify()) %>
			</select>		
		</td>
		<td nowrap width="15%" nowrap class="td_form">非預期反應原因：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_unExpectedReason">
				<%=View.getOptionCodeKind("HFRNRS", obj.getQ_unExpectedReason()) %>
			</select>	
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">證據性：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_evidentiary" >
				<%=View.getOptionCodeKind("HFRFEV", obj.getQ_evidentiary()) %>
			</select>		
		</td>
		<td nowrap width="15%" nowrap class="td_form">嚴重程度：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_thiSeverity" >
				<%=View.getOptionCodeKind("HFRSVR", obj.getQ_thiSeverity()) %>
			</select>			
		</td>
	</tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">行政處置層級：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_administrativeLevel">
				<%=View.getOptionCodeKind("HFRDRSP", obj.getQ_administrativeLevel()) %>
			</select>	
		</td>
		<td nowrap width="15%" nowrap class="td_form">案件狀態：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_status" type="select">
				<%=View.getOptionCodeKind("FCS", obj.getQ_status()) %>
			</select>		
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form_left" colspan="4">選取查詢結果欄位：</td>
	</tr>
	<tr>
		<td nowrap nowrap class="td_form_white" colspan="4">
			<table width="100%" id="reportTable">
				<tr>
					<td width="5%" class="td_lable"><input type="checkbox" class="field_Q" onclick="selectAllTable(this)"></td>
					<td width="19%" class="td_lable"><input type="checkbox" class="field_Q" id="1" onclick="selectColumn(this)"></td>
					<td width="19%" class="td_lable"><input type="checkbox" class="field_Q" id="2" onclick="selectColumn(this)"></td>
					<td width="19%" class="td_lable"><input type="checkbox" class="field_Q" id="3" onclick="selectColumn(this)"></td>
					<td width="19%" class="td_lable"><input type="checkbox" class="field_Q" id="4" onclick="selectColumn(this)"></td>
					<td width="19%" class="td_lable"><input type="checkbox" class="field_Q" id="5" onclick="selectColumn(this)"></td>
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="1" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="applNo">案件編號</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="notifierRevDate">通報日期</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="informedSources">通報來源</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="notifierType">通報者屬性</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="notifierTitle">通報者職稱</td>
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="2" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="address">通報案件縣市別</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="eatersSex">食用者性別</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="eatersAge">食用者年齡</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="eatersAgeLevel">食用者年齡級距</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="unHealthIsYes">健康食品未達宣稱之保健功效</td>					
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="3" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="unReactionResults">非預期反應結果</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="unReactionDetails">非預期反應描述</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="permit">食品字號</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="Product">食品品名</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="buySource">購買來源</td>					
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="4" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="ingredient">食品成分</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="useDrugs">併用藥品品名</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="useFoods">併用食品品名</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="againEatingHealthFood">曾食用同類健康食品之經驗</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="stopEatingReaction">停止食用健康食品後反應是否減輕</td>					
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="5" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="againEatingReaction">再次食用是否出現同樣反應</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="unExpectedClassify">非預期反應分類</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="unExpectedReason">非預期反應原因</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="evidentiary">證據性</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="recentlySeverity">嚴重程度</td>					
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="6" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="administrativeLevel">行政處置層級</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>					
				</tr>
			</table>
		</td>
	</tr>
	</table>
	</div>	
</td></tr>
<!--List區============================================================-->
<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<tr><td nowrap>
<br>
<tr><td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">食品字號</a></th>		
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">食品品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">案件狀態</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,true,false,false,false,false,false};
	    boolean displayArray[] = {false,false,true,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center","center","center"};
		out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	case "queryAll":
		break;
	}
}
</script>
</html>
