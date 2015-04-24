<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
String isNeedShowNext = Common.get(request.getParameter("isNeedShowNext"));
com.kangdainfo.tcbw.view.comple.COMPLE0901F qBean = (com.kangdainfo.tcbw.view.comple.COMPLE0901F) request.getAttribute("qBean");
%>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	form1.eatersIdNum.value = "<%=qBean.getEatersIdNum()%>";
});

function getUser(){
	openCenterWindow(800, 500, '../hfrin/hfrin0411f.jsp?q_type=E');
}

function lockUserInfo(){
}

function clearUser(){
	document.all.item("isSameNotifier").value = "N";
	document.all.item("caseOwner").value = "";
	$("[id*=notifier]").each(function(){
		$(this).val("").removeClass("field_RO").addClass('field').attr("readonly", "");
	});
}

var yyymmdd = getToday();
var msgS = "，不得大於今日日期\n";
function checkHFR0001FDate(){
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
			</td>
			<td nowrap class="td_form">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white">
			    <%=View.getPopCalendar("field", "notifierRepDate", qBean.getNotifierRepDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報者資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">通報者姓名</td>
	  		<td nowrap class="td_form_white">
	  			<input type="hidden" name="caseOwner" value="<%=qBean.getCaseOwner()%>">
				<input class="field_RO" type="text" id="notifier1" name="notifierName" size="20" maxlength="20" value="<%=qBean.getNotifierName()%>">
				<input type="hidden" name="notifierType" value="<%=qBean.getNotifierType()%>">				
				<input class="field" type="button" name="doGetUser" value="通報者查詢" onClick="getUser();">
				<input class="field" type="button" name="doClearUser" value="清除通報者" onClick="clearUser();">				
			</td>
			<td nowrap class="td_form">通報者接獲通知日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "notifierRevDate", qBean.getNotifierRevDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">通報者電話</td>
		  	<td nowrap class="td_form_white">
				( <input class="field" type="text" id="notifier2" name="notifierTelArea" size="2" maxlength="2" value="<%=qBean.getNotifierTelArea()%>"> )
				- <input class="field" type="text" id="notifier3" name="notifierTel" size="10" maxlength="10" value="<%=qBean.getNotifierTel()%>">
				# <input class="field" type="text" id="notifier4" name="notifierTelExt" size="3" maxlength="3" value="<%=qBean.getNotifierTelExt()%>">
			</td>
			<td nowrap class="td_form">通報來源</td>
			<td nowrap class="td_form_white">
				<select class="field" name="informedSources" type="select">
					<%=View.getOptionCodeKind("HFRNFS", qBean.getInformedSources()) %>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">電子郵件信箱</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" id="notifier5" name="notifierEamil" size="50" maxlength="50" value="<%=qBean.getNotifierEamil()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">食用者資訊</td>
		</tr>
		<tr id="tr0001">
			<td nowrap class="td_form">食用者姓名</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" id="eater1" name="eatersName" size="20" maxlength="20" value="<%=qBean.getEatersName()%>">，
				同通報者
				<select class="field" name="isSameNotifier" type="select" onChange="updateEaters(this.value, 'Y');">
					<%=View.getYNOption(qBean.getIsSameNotifier()) %>
				</select>
			</td>
			<td nowrap class="td_form">身分證字號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="password" autocomplete="off" name="eatersIdNum" size="20" maxlength="10" value="">
			</td>
		</tr>
		<tr id="tr0002">
			<td nowrap class="td_form">身高</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="eatersHight" size="10" maxlength="3" value="<%=qBean.getEatersHight()%>">公分
			</td>
			<td nowrap class="td_form">體重</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="eatersWeight" size="10" maxlength="3" value="<%=qBean.getEatersWeight()%>">公斤
			</td>
		</tr>
		<tr id="tr0003">
			<td nowrap class="td_form">性別</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="radio" name="eatersSex" value="M" <%=qBean.getEatersSex().equals("M")?"checked":"" %>>男
				&nbsp;
				<input class="field" type="radio" name="eatersSex" value="F" <%=qBean.getEatersSex().equals("F")?"checked":"" %>>女
			</td>
			<td nowrap class="td_form">年齡</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="eatersAge" size="10" maxlength="3" value="<%=qBean.getEatersAge()%>">
			</td>
		</tr>
		<tr id="tr0004">
			<td nowrap class="td_form">連絡電話</td>
		  	<td nowrap class="td_form_white">
		  		( <input class="field" type="text" id="eater2" name="eatersTelArea" size="2" maxlength="2" value="<%=qBean.getEatersTelArea()%>"> )
				- <input class="field" type="text" id="eater3" name="eatersTel" size="10" maxlength="10" value="<%=qBean.getEatersTel()%>">
				# <input class="field" type="text" id="eater4" name="eatersTelExt" size="3" maxlength="3" value="<%=qBean.getEatersTelExt()%>">
			</td>
			<td nowrap class="td_form">食用者住址</td>
		  	<td nowrap class="td_form_white">
		  		<select class="field" name="notifierArea" class="field" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", qBean.getNotifierArea())%>
			    </select>
			    <select name="notifierZipCode" class="field">
				   <%=View.getOptionCon1002(qBean.getNotifierZipCode())%>
			    </select>
				<input class="field" type="text" name="address" size="50" maxlength="100" value="<%=qBean.getAddress()%>">
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
	            <input class="field" type="text" name="unHealthBrief" size="50" maxlength="50" value="<%=qBean.getUnHealthBrief()%>" >
	            <br>
	            <input class="field" type="radio" name="unHealthIsYes" value="N" <%=qBean.getUnHealthIsYes().equals("N")?"checked":"" %> onClick="chUnHealthIsYes(this.value);">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發生未預期反應日期</td>
			<td nowrap class="td_form_white" colspan="3">	
				<%=View.getPopCalendar("field", "occurDate", qBean.getOccurDate())%>
	  		</td>
		</tr>
		<tr>
			<td nowrap class="td_form" rowspan="6">簡述非預期反應</td>
	  		<td nowrap class="td_form_white" colspan="3">
	  			發生經過
	  			&nbsp;
	  			<input class="field" type="text" name="occurredAfter" size="100" maxlength="100" value="<%=qBean.getOccurredAfter()%>">
	  		</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="3">
	  			症狀
	  			&nbsp;
	  			<input class="field" type="text" name="symptom" size="100" maxlength="100" value="<%=qBean.getSymptom()%>">
	  		</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="3">
	  			嚴重度
	  			&nbsp;
	  			<input class="field" type="text" name="severity" size="100" maxlength="100" value="<%=qBean.getSeverity()%>">
	  		</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="3">
	  			症狀維持時間
	  			&nbsp;
	  			<input class="field" type="text" name="symptomDuration" size="100" maxlength="100" value="<%=qBean.getSymptomDuration()%>">
	  		</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="3">
	  			停止食用後反應
	  			&nbsp;
	  			<input class="field" type="text" name="stopEatingReaction" size="100" maxlength="100" value="<%=qBean.getStopEatingReaction()%>">
	  		</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="3">
	  			再次食用是否有相同反應
	  			&nbsp;
	  			<input class="field" type="radio" name="againEatingReaction" value="Y" <%=qBean.getAgainEatingReaction().equals("Y")?"checked":"" %> >是
	  			&nbsp;
	  			<input class="field" type="radio" name="againEatingReaction" value="N" <%=qBean.getAgainEatingReaction().equals("N")?"checked":"" %> >否
	  		</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4" style="font-size:14px;background-color:yellow">
				備註：簡述非預期反應(範例)<br>
				1.症狀：皮膚癢疹。2.嚴重度：全身或局部。3.發生經過：3/20開始食用，3/21臉部開始有癢疹，3/21手跟胸口...一直到4/1疹子才消失<br>
				4.症狀維持時間&nbsp;5.停止食用後反應
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否有就醫</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		<input class="field" type="radio" name="isMedical" value="Y" <%=qBean.getIsMedical().equals("Y")?"checked":"" %> onClick="chIsMedical(this.value);">是，
				就醫日期
				<%=View.getPopCalendar("field", "medicalDate", qBean.getMedicalDate())%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				醫療院所
				<input class="field" type="text" name="hospital" size="30" maxlength="40" value="<%=qBean.getHospital()%>">
				<br>
				<input class="field" type="radio" name="isMedical" value="N" <%=qBean.getIsMedical().equals("N")?"checked":"" %> onClick="chIsMedical(this.value);">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">同時服用西醫</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		<input class="field" type="radio" name="isWhileMedicine" value="Y" <%=qBean.getIsWhileMedicine().equals("Y")?"checked":"" %> onClick="chIsWhileMedicine(this.value);">是，
		  		西藥藥品名
				<input class="field" type="text" name="westDrugName" size="30" maxlength="40" value="<%=qBean.getWestDrugName()%>">
				<br>
				<input class="field" type="radio" name="isWhileMedicine" value="N" <%=qBean.getIsWhileMedicine().equals("N")?"checked":"" %> onClick="chIsWhileMedicine(this.value);">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">同時服用中醫</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		<input class="field" type="radio" name="isWhileCMedicine" value="Y" <%=qBean.getIsWhileCMedicine().equals("Y")?"checked":"" %> onClick="chIsWhileCMedicine(this.value);">是，
		  		中草藥品名
				<input class="field" type="text" name="eastDrugName" size="30" maxlength="40" value="<%=qBean.getEastDrugName()%>">
				<br>
				<input class="field" type="radio" name="isWhileCMedicine" value="N" <%=qBean.getIsWhileCMedicine().equals("N")?"checked":"" %> onClick="chIsWhileCMedicine(this.value);">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">同時食用其他錠、塑膠型食品</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		<input class="field" type="radio" name="isWhileOther" value="Y" <%=qBean.getIsWhileOther().equals("Y")?"checked":"" %> onClick="chIsWhileOther(this.value);">是，
		  		產品名稱
				<input class="field" type="text" name="productName" size="30" maxlength="40" value="<%=qBean.getProductName()%>">
				<br>
				<input class="field" type="radio" name="isWhileOther" value="N" <%=qBean.getIsWhileOther().equals("N")?"checked":"" %> onClick="chIsWhileOther(this.value);">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">其他需說明之事項</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="otherExplainMemo" rows="2" cols="60"><%=qBean.getOtherExplainMemo() %></textarea>
			</td>
		</tr>
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
	
	<%
	if("Y".equals(qBean.getIsShowPreAssessmentPage())){
	%>
	<table id="Tab6" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%" >
		<tr>
			<td nowrap class="td_form">病歷調閱發文日期</td>
			<td nowrap class="td_form_white">	
				<%=View.getPopCalendar("field", "medicalPostingDate", qBean.getMedicalPostingDate())%>
			</td>
			<td nowrap class="td_form">病歷調閱完成日期</td>
			<td nowrap class="td_form_white">	
				<%=View.getPopCalendar("field", "medicalCompleteDate", qBean.getMedicalCompleteDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件</td>
			<td nowrap class="td_form_white" colspan="3">	
				<jsp:include page="../hfrin/hfrin0410f.jsp">
					<jsp:param value="HFRFM" name="fileType"/>
				</jsp:include>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">檢驗報告發文日期</td>
			<td nowrap class="td_form_white">	
				<%=View.getPopCalendar("field", "inspectPostingDate", qBean.getInspectPostingDate())%>
			</td>
			<td nowrap class="td_form">檢驗報告完成日期</td>
			<td nowrap class="td_form_white">	
				<%=View.getPopCalendar("field", "inspectCompleteDate", qBean.getInspectCompleteDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件</td>
			<td nowrap class="td_form_white" colspan="3">	
				<jsp:include page="../hfrin/hfrin0410f.jsp">
					<jsp:param value="HFRFI" name="fileType"/>
				</jsp:include>
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
	<%
	}
	%>
	
	<table id="Tab3" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%" >
		<tr>
			<td nowrap class="td_form_white" colspan="3">健康食品資料</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white">
				<jsp:include page="../hfrin/hfrin0404f.jsp">
					<jsp:param value="<%=qBean.getIsNeedShowPermitKey()%>" name="isNeedShowPermitKey"/>
				</jsp:include>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="3">一般食品</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white">
				<jsp:include page="../hfrin/hfrin0405f.jsp">
					<jsp:param value="<%=qBean.getIsNeedShowDocNo()%>" name="isNeedShowDocNo"/>
				</jsp:include>
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
				<jsp:include page="../hfrin/hfrin0410f.jsp">
					<jsp:param value="food" name="fileType"/>
				</jsp:include>
			</td>
		</tr>
		</table>
	</td></tr>
	</table>

	<script type="text/javascript">
		function checkIsEdit(id, notifierName){			
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
	
	
	
	