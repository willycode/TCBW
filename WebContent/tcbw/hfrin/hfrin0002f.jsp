<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
String isNeedShowNext = Common.get(request.getParameter("isNeedShowNext"));
com.kangdainfo.tcbw.view.hfrin.HFRIN0002F qBean = (com.kangdainfo.tcbw.view.hfrin.HFRIN0002F) request.getAttribute("qBean");
%>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	$("#notifier9").hide();
	form1.eatersIdNum.value = "<%=qBean.getEatersIdNum()%>";
	form1.notifierIdNum.value = "<%=qBean.getNotifierIdNum()%>";
	
	if("<%=qBean.getIsHiddenPersonalInfo()%>" == "Y"){
		chUserInput();
	}
});

var popWinName = null;
function getUser(){
	openCenterWindow(800, 500, 'hfrin0411f.jsp?q_type=C');
}

function lockUserInfo(){
	$("[id*=notifier]").each(function(){
		if($(this).attr("type") == "text" || $(this).attr("type") == "password"){
			$(this).removeClass("field").addClass('field_RO').attr("readonly", "readonly");
		}else{
			if($(this).attr("name") == "btnQryExpense"){
				$(this).hide();
			}else{
				$(this).removeClass("field").addClass('field_RO').attr("disabled", "disabled");
			}
		}
	});
}

// 清除通報者
function clearUser(){
	document.all.item("isSameNotifier").value = "N";
	document.all.item("caseOwner").value = "";
	document.all.item("notifierDeptID").value = "";
	for(var i=0; i<form1.notifierType.length; i++){
		form1.notifierType[i].checked = false;
	}
	
	$("[id*=notifier]").each(function(){
		if($(this).attr("type") == "text" || $(this).attr("type") == "password"){
			$(this).val("");
			if($(this).attr("name") != "notifierDept"){
				$(this).removeClass("field_RO").addClass('field').attr("readonly", "");
			}
		}else{
			if($(this).attr("type") == "button" || $(this).attr("type") == "radio"){
				
			}else{
				$(this).val("");
			}
			
			if($(this).attr("name") == "btnQryExpense"){
				$(this).show();
			}else{
				$(this).removeClass("field_RO").addClass('field').attr("disabled", "");
			}
		}
	});
}


// 審核之後頁籤，轉換成PASSWORD
function chUserInput(){
	$("[id*=notifier]").each(function(){
		if($(this).attr("type") == "text" || $(this).attr("type") == "password"){
			var tmp = $('<input class="field_RO" type="password" id="' + $(this).attr("id") + '" name="' + $(this).attr("name") + '" size="' + $(this).attr("size") + '" value="' +  $(this).val() + '">'); 
			$(this).replaceWith(tmp);
		}
	});
	
	$("[id*=tr000] .field").each(function(){
		if($(this).attr("type") == "text" || $(this).attr("type") == "password"){
			var tmp = $('<input class="field_RO" type="password" id="' + $(this).attr("id") + '" name="' + $(this).attr("name") + '" size="' + $(this).attr("size") + '" value="' +  $(this).val() + '">'); 
			$(this).replaceWith(tmp);
		}else{
			$(this).removeClass("field").addClass('field_RO').attr("disabled", "disabled");
		}
	});
}

// 顯示個資
function openUserInfo(){
	setFormItem("doOpenUserInfo", "R");
	$("[id*=notifier]").each(function(){
		if($(this).attr("type") == "password"){
			var tmp = $('<input readonly class="field_RO" type="text" id="' + $(this).attr("id") + '" name="' + $(this).attr("name") + '" size="' + $(this).attr("size") + '" value="' +  $(this).val() + '">'); 
			$(this).replaceWith(tmp);
		}
	});
	
	$("[id*=tr000] .field_RO").each(function(){
		if($(this).attr("type") == "password"){
			var tmp = $('<input class="field_RO" type="text" id="' + $(this).attr("id") + '" name="' + $(this).attr("name") + '" size="' + $(this).attr("size") + '" value="' +  $(this).val() + '">'); 
			$(this).replaceWith(tmp);
		}
	});
	
	var url = '../../ajax/jsonCommonLogDb.jsp';
    var q = "&code=HFR01";
	    q +="&methodName=open";
	    q +="&db=Hfr0001Db";
	    q +="&hql=select id,applNo,caseOwner,notifierName from Hfr0001Db where id = " + form1.id.value;
	var xUserUpdate = getRemoteData(url,q);
}

var yyymmdd = getToday();
var msgS = "，不得大於今日日期\n";
function checkHFR0002FDate(){
	var alertStr = "";
	$(":text[name$=Date][class=field]").each(function(){		
		var name = $(this).parent().prev().text();
		var m = checkDate(this, name);
		if(m.length == 0){
			if($(this).val() > yyymmdd){
				alertStr += name + msgS;
				this.style.backgroundColor = errorbg;
			}else{
				this.style.backgroundColor = "";
			}
		}else{
			alertStr += m;
		}
	});
	return alertStr;
}

</script>
	<table id="Tab1" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form_left" colspan="4">案件資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">案件編號</td>
	  		<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="applNo" size="15" maxlength="11" value="<%=qBean.getApplNo()%>">
				<input type="hidden" name="hfr40001Id" value="<%=qBean.getHfr40001Id()%>">
			</td>
			<td nowrap class="td_form">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="notifierRepDate" size="15" maxlength="11" value="<%=qBean.getNotifierRepDate()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發生日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "occurDate", qBean.getOccurDate())%>
			</td>
			<td nowrap class="td_form">通報者獲知日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "notifierRevDate", qBean.getNotifierRevDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報者資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">姓名</td>
	  		<td nowrap class="td_form_white">
				<input type="hidden" name="caseOwner" value="<%=qBean.getCaseOwner()%>">
				<input class="field_RO" type="text" id="notifier1" name="notifierName" size="20" maxlength="20" value="<%=qBean.getNotifierName()%>">
				<%
				if("Y".equals(qBean.getIsShowUserButton())){
				%>
					<input class="field" type="button" name="doGetUser" width="120px" value="通報者查詢" onClick="getUser();">
					<input class="field" type="button" name="doClearUser" width="120px" value="清除通報者" onClick="clearUser();">
				<%
				}
				%>
				
				<%
				if("Y".equals(qBean.getIsShowOpenPersonalInfo())){
				%>
					<input class="toolbar_default" type="button" name="doOpenUserInfo" value="不遮蔽個資" onClick="openUserInfo();">
				<%
				}
				%>
				
			</td>
			<td nowrap class="td_form">身分證字號</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="password" autocomplete="off" id="notifier2" name="notifierIdNum" size="20" maxlength="10">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">電話</td>
		  	<td nowrap class="td_form_white">
		  		( <input class="field_RO" type="text" id="notifier3" name="notifierTelArea" size="2" maxlength="2" value="<%=qBean.getNotifierTelArea()%>"> )
				- <input class="field_RO" type="text" id="notifier4" name="notifierTel" size="10" maxlength="10" value="<%=qBean.getNotifierTel()%>">
				# <input class="field_RO" type="text" id="notifier5" name="notifierTelExt" size="3" maxlength="3" value="<%=qBean.getNotifierTelExt()%>">
			</td>
			<td nowrap class="td_form">電子郵件信箱</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" id="notifier6" name="notifierEamil" size="30" maxlength="50" value="<%=qBean.getNotifierEamil()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">屬性</td>
			<td nowrap class="td_form_white" >
				<%=View.getCommonCodeRadioBoxOption("field_RO", "notifierType", "CONNFT1", qBean.getNotifierType(), null, "02,03,04", "notifier7") %>
			</td>
			<td nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white">
				<input type="hidden" name="notifierDeptID" value="<%=qBean.getNotifierDeptID()%>" />
	  	        <input class="field_RO" type="text" id="notifier8" name="notifierDept" size="30" maxlength="30" value="<%=qBean.getNotifierDept()%>" />
			    <input class="field" type="button" id="notifier9" name="btnQryExpense" onClick="popNotifierDept(notifierType);" value="查詢" width="120px" >
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">職稱</td>
			<td nowrap class="td_form_white">
				<select class="field_RO" id="notifier10" name="notifierTitle" type="select">
					<%=View.getOptionCodeKind("TITLE", qBean.getNotifierTitle()) %>
				</select>
			</td>
			<td nowrap class="td_form">通報來源</td>
			<td nowrap class="td_form_white">
				<select class="field" name="informedSources" type="select">
					<%=View.getOptionCodeKind("HFRNFS", qBean.getInformedSources()) %>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">住址</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<select class="field_RO" id="notifier11" name="notifierArea" type="select" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", qBean.getNotifierArea())%>
			    </select>
			    <select class="field_RO" id="notifier12" name="notifierZipCode" type="select">
				   <%=View.getOptionCon1002(qBean.getNotifierZipCode())%>
			    </select>
				<input class="field_RO" type="text" id="notifier13" name="address" size="50" maxlength="100" value="<%=qBean.getAddress()%>">
			</td>
		</tr>
		
		<tr>
			<td nowrap class="td_form_left" colspan="4">食用者資訊</td>
		</tr>
		<tr id="tr0001">
			<td nowrap class="td_form">識別代號</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="eatersId" size="20" maxlength="20" value="<%=qBean.getEatersId()%>">
			</td>
			<td nowrap class="td_form">與通報者為同一人</td>
		  	<td nowrap class="td_form_white">
				<select class="field" name="isSameNotifier" type="select" onChange="updateEaters(this.value, 'Y');">
					<%=View.getYNOption(qBean.getIsSameNotifier()) %>
				</select>
			</td>
		</tr>
		<tr id="tr0002">
			<td nowrap class="td_form">姓名</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" id="eater1" name="eatersName" size="20" maxlength="20" value="<%=qBean.getEatersName()%>">
			</td>
			<td nowrap class="td_form">身分證字號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="password" autocomplete="off" id="eater2" name="eatersIdNum" size="20" maxlength="10">
			</td>
		</tr>
		<tr id="tr0003">
			<td nowrap class="td_form">性別</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="radio" name="eatersSex" value="M" <%=qBean.getEatersSex().equals("M")?"checked":"" %>>男
				&nbsp;
				<input class="field" type="radio" name="eatersSex" value="F" <%=qBean.getEatersSex().equals("F")?"checked":"" %>>女
			</td>
			<td nowrap class="td_form">出生年份</td>
		  	<td nowrap class="td_form_white">
		  		民國<input class="field" type="text" name="eatersBirthYear" size="10" maxlength="3" value="<%=qBean.getEatersBirthYear()%>">年
			</td>
		</tr>
		<tr id="tr0004">
			<td nowrap class="td_form">身高</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="eatersHight" size="10" maxlength="3" value="<%=qBean.getEatersHight()%>">公分
			</td>
			<td nowrap class="td_form">體重</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="eatersWeight" size="10" maxlength="3" value="<%=qBean.getEatersWeight()%>">公斤
			</td>
		</tr>
		<tr id="tr0005">
			<td nowrap class="td_form">連絡電話</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		( <input class="field" type="text" id="eater3" name="eatersTelArea" size="2" maxlength="2" value="<%=qBean.getEatersTelArea()%>"> )
				- <input class="field" type="text" id="eater4" name="eatersTel" size="10" maxlength="10" value="<%=qBean.getEatersTel()%>">
				# <input class="field" type="text" id="eater5" name="eatersTelExt" size="3" maxlength="3" value="<%=qBean.getEatersTelExt()%>">
			</td>
			
		</tr>
		<tr>
			<td nowrap class="td_form">藥物過敏史</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="drugAllergies" value="Y" <%=qBean.getDrugAllergies().equals("Y")?"checked":"" %>>是，
				說明
				<input class="field" type="text" name="drugOther" size="40" maxlength="50" value="<%=qBean.getDrugOther()%>">
				&nbsp;
				<input class="field" type="radio" name="drugAllergies" value="N" <%=qBean.getDrugAllergies().equals("N")?"checked":"" %>>否
				&nbsp;
				<input class="field" type="radio" name="drugAllergies" value="U" <%=qBean.getDrugAllergies().equals("U")?"checked":"" %>>無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">食物過敏史</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="foodAllergies" value="Y" <%=qBean.getFoodAllergies().equals("Y")?"checked":"" %>>是，
				說明
				<input class="field" type="text" name="foodOther" size="40" maxlength="50" value="<%=qBean.getFoodOther()%>">
				&nbsp;
				<input class="field" type="radio" name="foodAllergies" value="N" <%=qBean.getFoodAllergies().equals("N")?"checked":"" %>>否
				&nbsp;
				<input class="field" type="radio" name="foodAllergies" value="U" <%=qBean.getFoodAllergies().equals("U")?"checked":"" %>>無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">疾病史</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonCodeKindBoxOption("field", "diseaseHistory", "HFRDSH", qBean.getDiseaseHistory()) %>
				，說明
				<input class="field" type="text" name="diseaseOther" size="40" maxlength="50" value="<%=qBean.getDiseaseOther()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">生活史</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonCodeKindBoxOption("field", "lifeHistory", "HFRLFH", qBean.getLifeHistory()) %>
				，說明
				<input class="field" type="text" name="lifeOther" size="40" maxlength="50" value="<%=qBean.getLifeOther()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否告知案件評估結果</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="isReactionResult" value="Y" <%=qBean.getIsReactionResult().equals("Y")?"checked":"" %>>是
				&nbsp;
				<input class="field" type="radio" name="isReactionResult" value="N" <%=qBean.getIsReactionResult().equals("N")?"checked":"" %>>否
			</td>
		</tr>
		<%
		if("Y".equals(isNeedShowNext)){
		%>
		<tr>
			<td colspan="4" style="text-align:center">
				<span id="spanDoQuit">
					<input class="toolbar_default" type="button" followPK="false" name="doNextTab2" value="下 一 頁" onClick="changeTab(2);">&nbsp;
				</span>
			</td>
		</tr>
		<%
		}
		%>
		</table>
	</td></tr>
	</table>
	
	<table id="Tab2" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%" >
		<tr>
			<td nowrap class="td_form">健康食品未達宣稱保健功效</td>
		  	<td nowrap class="td_form_white" colspan="3">
	            <input class="field" type="radio" name="unHealthIsYes" value="Y" <%=qBean.getUnHealthIsYes().equals("Y")?"checked":"" %> onClick="chUnHealthIsYes(this.value);">是，
				簡述
	            <input class="field" type="text" name="unHealthBrief" size="50" maxlength="80" value="<%=qBean.getUnHealthBrief()%>">
	            <br>
	            <input class="field" type="radio" name="unHealthIsYes" value="N" <%=qBean.getUnHealthIsYes().equals("N")?"checked":"" %> onClick="chUnHealthIsYes(this.value);">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">非預期反應結果</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=qBean.getHFRURCRRadioOption("field", "unReactionResults", qBean.getUnReactionResults(), qBean.getDeathDate(), qBean.getDeathResult(), qBean.getEndangerLife(), qBean.getOtherNonResponse()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">通報事件描述</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="../hfrex/hfrex0107f.jsp"/>	
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">醫師診斷及相關檢查及檢驗數據</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="../hfrex/hfrex0108f.jsp"/>	
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">其它相關資料</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=qBean.getFORCheckBoxOption("field", "otherrElevantInformation", qBean.getOtherrElevantInformation(), qBean.getAllergicDescription(), qBean.getOthersDescribeHistory()) %>
			</td>
		</tr>
		
		<%
		if("Y".equals(qBean.getIsShowPreAssessmentPage())){
		%>
		<tr id="hfr0005Db1">
			<td nowrap class="td_form">病歷調閱發文日期</td>
			<td nowrap class="td_form_white">	
				<%=View.getPopCalendar("field", "medicalPostingDate", qBean.getMedicalPostingDate())%>
			</td>
			<td nowrap class="td_form">病歷調閱完成日期</td>
			<td nowrap class="td_form_white">	
				<%=View.getPopCalendar("field", "medicalCompleteDate", qBean.getMedicalCompleteDate())%>
			</td>
		</tr>
		<tr id="hfr0005Db2">
			<td nowrap class="td_form">相關附件</td>
			<td nowrap class="td_form_white" colspan="3">	
				<jsp:include page="hfrin0410f.jsp">
					<jsp:param value="HFRFM" name="fileType"/>
				</jsp:include>
			</td>
		</tr>
		<tr id="hfr0005Db3">
			<td nowrap class="td_form">檢驗報告發文日期</td>
			<td nowrap class="td_form_white">	
				<%=View.getPopCalendar("field", "inspectPostingDate", qBean.getInspectPostingDate())%>
			</td>
			<td nowrap class="td_form">檢驗報告完成日期</td>
			<td nowrap class="td_form_white">	
				<%=View.getPopCalendar("field", "inspectCompleteDate", qBean.getInspectCompleteDate())%>
			</td>
		</tr>
		<tr id="hfr0005Db4">
			<td nowrap class="td_form">相關附件</td>
			<td nowrap class="td_form_white" colspan="3">	
				<jsp:include page="hfrin0410f.jsp">
					<jsp:param value="HFRFI" name="fileType"/>
				</jsp:include>
			</td>
		</tr>
		<%
		}
		%>
		
		<%
		if("Y".equals(isNeedShowNext)){
		%>
		<tr>
			<td colspan="4" style="text-align:center">
				<span id="spanDoQuit">
					<input class="toolbar_default" type="button" followPK="false" name="doNextTab3" value="下 一 頁" onClick="changeTab(3);">&nbsp;
				</span>
			</td>
		</tr>
		<%
		}
		%>
		
		</table>
	</td></tr>
	</table>
	
	<table id="Tab3" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%" >
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">健康食品</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrin0404f.jsp">
					<jsp:param value="<%=qBean.getIsNeedShowPermitKey()%>" name="isNeedShowPermitKey"/>
				</jsp:include>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">非衛生署核准為健康食品之錠、膠劑型食品</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrin0405f.jsp">
					<jsp:param value="<%=qBean.getIsNeedShowDocNo()%>" name="isNeedShowDocNo"/>
				</jsp:include>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">併用藥品(西藥、中藥)</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrin0409f.jsp">
					<jsp:param value="dr" name="drugType"/>
				</jsp:include>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">併用其他錠、膠劑型食品</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrin0409f.jsp">
					<jsp:param value="odr" name="drugType"/>
				</jsp:include>	
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">曾食用同類健康食品之經驗</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="againEatingHealthFood" value="Y" <%=qBean.getAgainEatingHealthFood().equals("Y")?"checked":"" %> onClick="chAgainEatingHealthFood(this.value);">是，
				商品名 : <input class="field" type="text" name="healthFoodName" size="30" maxlength="540" value="<%=qBean.getHealthFoodName()%>">
				，曾經發生非預期反應 : <input class="field" type="text" name="againOtherNonResponse" size="30" maxlength="50" value="<%=qBean.getAgainOtherNonResponse()%>">
				&nbsp;
				<input class="field" type="radio" name="againEatingHealthFood" value="N" <%=qBean.getAgainEatingHealthFood().equals("N")?"checked":"" %> onClick="chAgainEatingHealthFood(this.value);">否
				&nbsp;
				<input class="field" type="radio" name="againEatingHealthFood" value="U" <%=qBean.getAgainEatingHealthFood().equals("U")?"checked":"" %> onClick="chAgainEatingHealthFood(this.value);">無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">停止食用健康食品後，此反應是否減輕</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="stopEatingReaction" value="Y" <%=qBean.getStopEatingReaction().equals("Y")?"checked":"" %>>是
				&nbsp;
				<input class="field" type="radio" name="stopEatingReaction" value="N" <%=qBean.getStopEatingReaction().equals("N")?"checked":"" %>>否
				&nbsp;
				<input class="field" type="radio" name="stopEatingReaction" value="U" <%=qBean.getStopEatingReaction().equals("U")?"checked":"" %>>無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">再次食用是否出現相同反應</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="againEatingReaction" value="Y" <%=qBean.getAgainEatingReaction().equals("Y")?"checked":"" %>>是
				&nbsp;
				<input class="field" type="radio" name="againEatingReaction" value="N" <%=qBean.getAgainEatingReaction().equals("N")?"checked":"" %>>否
				&nbsp;
				<input class="field" type="radio" name="againEatingReaction" value="U" <%=qBean.getAgainEatingReaction().equals("U")?"checked":"" %>>無法得知
			</td>
		</tr>
		<%
		if("Y".equals(isNeedShowNext)){
		%>
		<tr>
			<td colspan="4" style="text-align:center">
				<span id="spanDoQuit">
					<input class="toolbar_default" type="button" followPK="false" name="doNextTab4" value="下 一 頁" onClick="changeTab(4);">&nbsp;
				</span>
			</td>
		</tr>
		<%
		}
		%>
		</table>
	</td></tr>
	</table>
	
	<table id="Tab4" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%" >
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">相關附件</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrin0410f.jsp">
					<jsp:param value="food" name="fileType"/>
				</jsp:include>
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
	
	<script type="text/javascript">
		function checkIsEdit(id, notifierName){
			var s = "&sysTemType=HFR&formCode=HFR01&id=" + id + "&notifier=" + form1.notifierName.value;
			var xUserUpdate = getRemoteData("../../ajax/jsonCon0003Db.jsp", s);
			
			if (xUserUpdate!=null && xUserUpdate.length>0){
				var jsonUserUpdate = eval ('(' + xUserUpdate + ')');
				
				alert("此案件目前已有其他作業人員編輯中!");
				return false;	
			}
			return true;
		}
		
		function deleteEdit(id){
			var s = "&sysTemType=HFR&formCode=HFR01&id=" + id;
			var x = getRemoteData("../../ajax/jsonDeleteCon0003Db.jsp", s);
			
			if(x!=null && x.length>0){
				var jsonUserUpdate = eval ('(' + x + ')');
				if(jsonUserUpdate.r == "Y"){
				//	return true;
				}else{
				//	return false;
				}	
			}
			// return true;
		}
	</script>
	
	
	