<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
String isClose = Common.get(request.getParameter("isCloseNunBussiness"));
String isSpecial = Common.get(request.getParameter("isSpecial"));
com.kangdainfo.tcbw.view.cosin.COSIN0400F qBean = (com.kangdainfo.tcbw.view.cosin.COSIN0400F) request.getAttribute("qBean");
%>
<script type="text/javascript">
$(function(){
	if("<%=qBean.getCosType()%>" == "1" || "<%=qBean.getCosType()%>" == "3"){
		chIsDamageYn("<%=qBean.getIsDamageYn()%>");
	}else{
		chNonSeriousAR("<%=qBean.getNonSeriousAR()%>");
	}
});

// FOR 轉檔修正用
<%
if("Y".equals(Common.get(isSpecial))){
%>
	var popWinName = null;
	$(function(){
		$("#Tab1 .field_RO[name!=status][name!=applNo][name!=status1][name!=notifierDept]").each(function(){
			$(this).removeClass("field_RO").addClass("field");
		});
		$("[name=btnQryExpense]").hide();
	});
	
	function getNotifierInfo(){
		openCenterWindow(800, 500, '../hfrin/hfrin0411f.jsp');
	}
	
	function lockUserInfo(){
		$("[id^=notifier]").each(function(){
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
		changeNotifierType();
	}
	
	function changeNotifierType(){
		var notifierType = "";
		for(var i=0; i<document.all.item("notifierType").length; i++){
			if(document.all.item("notifierType")[i].checked){
				notifierType = document.all.item("notifierType")[i].value;
			}
		}
		if("" != notifierType){
			if("01" == notifierType){
				checkedRadio(form1.notifierSource,"06");
			}else if("02" == notifierType){
				checkedRadio(form1.notifierSource,"05");
			}else if("03" == notifierType){
				checkedRadio(form1.notifierSource,"02");
			}else if("04" == notifierType){
				checkedRadio(form1.notifierSource,"03");
			}
		}
	}
	
	//清除通報者
	function clearNotifierInfo(){
		document.all.item("caseOwner").value = "";
		document.all.item("notifierDeptID").value = "";
		
		$("#Tab1 [id^=notifier]").each(function(){
			if($(this).attr("type") == "text"){
				$(this).val("").removeClass("field_RO").addClass('field').attr("readonly", "");
			}else if($(this).attr("type") == "select-one"){
				$(this).val("").removeClass("field_RO").addClass('field').attr("disabled", "");
			}else if($(this).attr("type") == "radio"){
				$(this).attr("checked", "").removeClass("field_RO").addClass('field').attr("disabled", "");
			}
			
			if($(this).attr("name") == "btnQryExpense"){
				$(this).show();
			}
		});
	}
	
	// 許可證字號取得
	function getPermit(){
		openCenterWindow(900, 500, getVirtualPath() + "tcbw/cosex/cosex0110f.jsp?field1=permitKey&field2=permitNo");
	}
	
	// 登錄編號
	function getCaseNo(){
		openCenterWindow(900, 500, getVirtualPath() + "tcbw/cosex/cosex0111f.jsp?field1=caseNo");
	}
<%
}
%>

function relationApplno(id){
	var prop = "";
	var windowTop = 120; 
	var windowLeft = 120;
	prop = prop+"width=1200px,height=600,";
	prop = prop+"top="+windowTop+",";
	prop = prop+"left="+windowLeft+",";
	prop = prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow = window.open(getVirtualPath() + "tcbw/cosin/cosin0102q.jsp?id=" + id + "&isPop=A","",prop);	
}
</script>
<%
if(!"Y".equals(isClose)){
%>	
	<table id="Tab1" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form">案件編號</td>
		<td nowrap class="td_form_white">
			<input class="field_RO" type="text" name="applNo" size="11" maxlength="11" value="<%=qBean.getApplNo()%>">
		</td>
		<td nowrap class="td_form">案件狀態</td>
		<td nowrap class="td_form_white">
			<input class="field_RO" type="text" name="status" size="15" maxlength="15" value="<%=qBean.getStatusCh()%>">
		</td>
	</tr>
		<tr>
		<td nowrap class="td_form">關聯案件編號</td>
		<td nowrap class="td_form_white">
			<a href="javascript:relationApplno('<%=qBean.getRelationId()%>');" id="testA"><%=qBean.getApplno1() %></a>
		</td>
		<td nowrap class="td_form">關聯案件狀態</td>
		<td nowrap class="td_form_white">
			<input class="field_RO" type="text" name="status1" size="15" maxlength="15" value="<%=qBean.getStatus1()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報中心接獲通報日期</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_RO" type="text" name="notifierRepDate" size="7" maxlength="7" value="<%=qBean.getNotifierRepDate()%>">
			<input type="hidden" name="status" value="<%=qBean.getStatus()%>">
			<input type="hidden" name="chargeMan" value="<%=qBean.getChargeMan()%>">
			<input type="hidden" name="cosType" value="<%=qBean.getCosType()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">發生日期</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field", "occurDate", qBean.getOccurDate())%>
		</td>
		<td nowrap class="td_form">通報日期</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field", "notifierRevDate", qBean.getNotifierRevDate())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報來源</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getCommonRadioBoxOption("field", "notifierSource", "CIS", qBean.getNotifierSource(), "codeId", 4)%>
			<input class="field" type="text" name="notifierSourceOther" size="15" maxlength="20" value="<%=qBean.getNotifierSourceOther()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報者姓名</td>
	  	<td nowrap class="td_form_white">
	  		<input type="hidden" name="caseOwner" value="<%=qBean.getCaseOwner()%>">	<!-- 暫時沒有用途，只讓clearNotifierInfo使用 -->	
	  		<input class="field_RO" type="text" id="notifier1" name="notifierName" size="20" maxlength="20" value="<%=qBean.getNotifierName()%>">
	  		<input class="toolbar_default" type="button" name="doOpenUserInfo" value="不遮蔽個資" onClick="openUserInfo();">
	  	
		  	<%
			if("Y".equals(Common.get(isSpecial))){
			%>
		  		<input class="field" type="button" name="getUserInfo" value="通報者查詢" onClick="getNotifierInfo();">
				<input class="field" type="button" name="clearUserInfo" value="清除通報者" onClick="clearNotifierInfo();">
			<%
			}
			%>
		</td>
		<td nowrap class="td_form">通報者電話</td>
		<td nowrap class="td_form_white" >
			( <input class="field_RO" type="text" id="notifier2" name="notifierTelArea" size="2" maxlength="2" value="<%=qBean.getNotifierTelArea()%>"> )
			- <input class="field_RO" type="text" id="notifier3" name="notifierTel" size="15" maxlength="10" value="<%=qBean.getNotifierTel()%>">
			# <input class="field_RO" type="text" id="notifier4" name="notifierTelExt" size="3" maxlength="3" value="<%=qBean.getNotifierTelExt()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報者職稱</td>
		<td nowrap class="td_form_white">
			<select class="field_RO" id="notifier5" name="notifierTitle" type="select">
				<%=View.getOptionCodeKind("TITLE", qBean.getNotifierTitle()) %>
			</select>
		</td>
		<td nowrap class="td_form">通報者屬性</td>
		<td nowrap class="td_form_white" >
			<%=View.getCommonCodeRadioBoxOption("field_RO", "notifierType", "CONNFT1", qBean.getNotifierType(), "changeNotifierType()", "01,02,03,04", "notifier6") %>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white">
				<input type="hidden" name="notifierDeptID" value="<%=qBean.getNotifierDeptID()%>" />
	  	        <input class="field_RO" type="text" id="notifier7" name="notifierDept" size="30" maxlength="30" value="<%=qBean.getNotifierDept()%>" />
	  	        <%
				if("Y".equals(Common.get(isSpecial))){
				%>
			    	<input class="field" type="button" id="notifier8" name="btnQryExpense" onClick="popNotifierDept(notifierType,'Y');" value="查詢" width="120px" >
			    <%
				}
				%>
			</td>
		<td nowrap class="td_form">電子郵件信箱</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_RO" type="text" id="notifier8" name="notifierEamil" size="40" maxlength="50" value="<%=qBean.getNotifierEamil()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">聯絡地址</td>
		<td nowrap class="td_form_white" colspan="3">
			<select class="field_RO" id="notifier9" name="notifierArea" type="select" onChange="popZipCode(this,notifierZipCode);">
				<%=View.getOptionCodeKind("CTY", qBean.getNotifierArea())%>
			</select>
			<select class="field_RO" id="notifier10" name="notifierZipCode" type="select">
				<%=View.getOptionCon1002(qBean.getNotifierZipCode())%>
			</select>
			<input class="field_RO" type="text" id="notifier11" name="address" size="50" maxlength="100" value="<%=qBean.getAddress()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">是否同意將您個人聯絡方式<br>提供產品負責業者</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getRadioBoxTextOption("field", "isContactYn", "N;否;Y;是", qBean.getIsContactYn()) %>
		</td>
	</tr>
	</table>
	</td></tr>
	</table>
<%
}
%>	
	<table id="Tab2" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
	<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form">中文品名</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="chProduct" size="40" maxlength="100" value="<%=qBean.getChProduct()%>">
			</td>
			<td nowrap class="td_form">外文品名</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="enProduct" size="40" maxlength="100" value="<%=qBean.getEnProduct()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">不良事件類別</td>
		  	<td nowrap class="td_form_white">
		  		<%=View.getCommonCodeKindBoxOption("field_RO", "showCosType", "CCT", qBean.getShowCosType()) %>
		  	</td>
			<td nowrap class="td_form">許可證字號</td>
		  	<td nowrap class="td_form_white">
		  	
		  	<%
			if("Y".equals(Common.get(isSpecial))){
			%>
		  		<select class="field" name="permitKey" type="select">
					<%=View.getOptionCodeKind("CPT", qBean.getPermitKey()) %>
				</select>
		  		字第
				<input class="field" type="text" name="permitNo" size="20" maxlength="16" value="<%=qBean.getPermitNo()%>" onChange="chkPermitProdData()">
				號
		  		<input class="field" type="button" name="chosePermit" value="許可證字號" onClick="getPermit();">
			<%
			}else{
			%>	
				<select class="field_RO" name="permitKey" type="select">
					<%=View.getOptionCodeKind("CPT", qBean.getPermitKey()) %>
				</select>
				字第
				<input class="field_RO" type="text" name="permitNo" size="20" maxlength="16" value="<%=qBean.getPermitNo()%>">
				號
			<%
			}
			%>
				<input class="toolbar_default" type="button" name="showPermit" value="檢視藥證資料" onClick="showPermitKey();">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">登錄編號</td>
		  	<td nowrap class="td_form_white">
		  		<input class="field_RO" type="text" name="caseNo" size="16" maxlength="16" value="<%=qBean.getCaseNo()%>">
		  		
		  		<%
				if("Y".equals(Common.get(isSpecial))){
				%>
		  			<input class="field" type="button" name="choseCaseNo" value="登錄編號" onClick="getCaseNo();">
		  		<%
				}
		  		%>	
		  		
				<input class="toolbar_default" type="button" name="showCaseNo" value="檢視登錄資料" onClick="swhoCaseNoInfo();">
			</td>
			<td nowrap class="td_form">化粧品項目</td>
		  	<td nowrap class="td_form_white">
				<%=View.getCommonRadioBoxOption("field", "ingredient", "CCI", qBean.getIngredient(), "codeId", 3)%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">販賣通路</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field", "traffickWay", "CSP", qBean.getTraffickWay(), "codeId", 7)%>
				<input class="field" type="text" name="traffickWayOther" size="30" maxlength="50" value="<%=qBean.getTraffickWayOther()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">商家名稱</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="businessName" size="30" maxlength="50" value="<%=qBean.getBusinessName()%>">
			</td>
			<td nowrap class="td_form">商家地址</td>
		  	<td nowrap class="td_form_white">
				<select class="field" name="tradePlace" type="select" onChange="popZipCode(this,tradePlaceZipCode);">
					<%=View.getOptionCodeKind("CTY", qBean.getTradePlace()) %>
				</select>
				<select class="field" name="tradePlaceZipCode" type="select">
				   <%=View.getOptionCon1002(qBean.getTradePlaceZipCode())%>
			    </select>
			    <input class="field" type="text" name="tradePlaceAddr" size="40" maxlength="40" value="<%=qBean.getTradePlaceAddr()%>">
			    <br>
			    <font color="blue">請填寫購買產品之地址。<br>若無法填寫完整地址，請描述商家附近重要地標，如XX捷運站旁。</font>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">國內製造廠/國外產品進口(代理)商</td>
		  	<td nowrap class="td_form_white">
		  		<input type="hidden" name="manufactorID" value="<%=qBean.getManufactorID()%>">
				<input class="field_RO" type="text" id="manufactor1" name="manufactorName" size="30" maxlength="50" value="<%=qBean.getManufactorName()%>">
				<input class="field" type="button" name="getManufactor" value="廠商查詢" onClick="getManufactorInfo();">
				<input class="field" type="button" name="clearManufactor" value="清除廠商" onClick="clearManufactorInfo();">
			</td>
			<td nowrap class="td_form">廠商聯絡電話</td>
		  	<td nowrap class="td_form_white">
		  		( <input class="field" type="text" name="manufactorTelArea" size="2" maxlength="2" value="<%=qBean.getManufactorTelArea()%>"> )
				- <input class="field" type="text" name="manufactorTel" size="10" maxlength="10" value="<%=qBean.getManufactorTel()%>">
				# <input class="field" type="text" name="manufactorTelExt" size="3" maxlength="3" value="<%=qBean.getManufactorTelExt()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">廠商地址</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		<select class="field" name="manufactorArea" type="select" onChange="popZipCode(this,manufactorZipCode);">
			       <%=View.getOptionCodeKind("CTY", qBean.getManufactorArea())%>
			    </select>
			    <select class="field" name="manufactorZipCode" type="select">
				   <%=View.getOptionCon1002(qBean.getManufactorZipCode())%>
			    </select>
				<input class="field" type="text" name="manufactorAddr" size="40" maxlength="40" value="<%=qBean.getManufactorAddr()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造批號或製造日期</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="manufactorNo" size="10" maxlength="10" value="<%=qBean.getManufactorNo()%>">
			</td>
			<td nowrap class="td_form">保存期限</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="expirationDate" size="7" maxlength="7" value="<%=qBean.getExpirationDate()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">購買日期</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="tradeDate" size="7" maxlength="7" value="<%=qBean.getTradeDate()%>">
			</td>
			<td nowrap class="td_form">是否可提供樣品</td>
		  	<td nowrap class="td_form_white">
				<%=View.getYNRadioBoxTextOption("field", "isSampleYn", qBean.getIsSampleYn()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否已與廠商接觸過</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getYNRadioBoxTextOption("field", "evenContactYn", qBean.getEvenContactYn()) %>
			</td>
		</tr>
		<tr id="dealWith">
			<td nowrap class="td_form">後續處理</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxTextOption("field", "dealWith", "N;無處理;O;單一換貨;A;整批換貨", qBean.getDealWith()) %>
			</td>
		</tr>
		<tr id="isRecurrenceYn">
			<td nowrap class="td_form">處理後是否再度發生</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getYNRadioBoxTextOption("field", "isRecurrenceYn", qBean.getIsRecurrenceYn()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">同產品是否有類似案例</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getYNRadioBoxTextOption("field", "isSimilarYn", qBean.getIsSimilarYn()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否已送交相關單位處理</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		<input class="field" type="radio" name="isOtherDeptYn" value="Y" <%=qBean.getIsOtherDeptYn().equals("Y")?"checked":"" %>>是，
		  		相關單位名稱
		  		<input class="field" type="text" name="otherDpetName" size="40" maxlength="40" value="<%=qBean.getOtherDpetName()%>">
		  		<input class="field" type="radio" name="isOtherDeptYn" value="N" <%=qBean.getIsOtherDeptYn().equals("N")?"checked":"" %>>否
		  	</td>
		</tr>
		</table>
	</td></tr>
	</table>
	
	<table id="Tab3" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form">不良缺陷描述：</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=qBean.getCOS0003DbCheckBoxOption("field", "subCode", "otherDescribe", qBean.getSubCode(), qBean.getId(), true) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">不良品是否有損害使用者身體或健康之事實</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="isDamageYn" value="Y" <%=qBean.getIsDamageYn().equals("Y")?"checked":"" %>>是，
				請描述
				<input class="field" type="text" name="otherInformation" size="40" maxlength="50" value="<%=qBean.getOtherInformation()%>">
				<input class="field" type="radio" name="isDamageYn" value="N" <%=qBean.getIsDamageYn().equals("N")?"checked":"" %>>否
				<input class="field" type="radio" name="isDamageYn" value="O" <%=qBean.getIsDamageYn().equals("O")?"checked":"" %>>其他
				<input class="field" type="hidden" name="otherIsDamageYn">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">說明</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="otherExplain" rows="2" cols="50"><%=qBean.getOtherExplain() %></textarea>
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
	
	<table id="Tab4" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form">不良反應症狀</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="nonSeriousOther" rows="2" cols="50"><%=qBean.getNonSeriousOther() %></textarea>
				
				<br>
				<select class="field" name="nonSeriousAR" type="select" onChange="chNonSeriousAR(this.value)">
			       <%=View.getOptionCodeKind("COSAR", qBean.getNonSeriousAR())%>
			    </select>
			    <span id="AR1">
			    	<input class="field" type="text" name="nonSeriousAROther" size="40" maxlength="50" value="<%=qBean.getNonSeriousAROther()%>">
			    </span>
			    <br>
				<font color="blue">請盡可能使用精確的「標準醫學字彙」或診斷，英文或中文皆可接受。</font>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">不良反應描述</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="nonSeriousDis" rows="2" cols="50"><%=qBean.getNonSeriousDis() %></textarea>
				<br>
				<font color="blue">請依事件發生前後時序填寫，應包括使用化粧品前的相關病史；<br>使用化粧品及發生不良反應之時間；不良反應症狀、部位及嚴重程度；處置方式與處置後反應。</font>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">不良反應結果</td>
			<td nowrap class="td_form_white" colspan="3">
		  		<%=qBean.getCACRadioBoxOption("field", "adverseCondition", qBean.getAdverseCondition(), "", 5)%>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">可疑化粧品使用起迄時間</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field", "useDateS", qBean.getUseDateS())%>
				~
				<%=View.getPopCalendar("field", "useDateE", qBean.getUseDateE())%>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">使用方法</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" rows="3" cols="80" name="useMethod"><%=qBean.getUseMethod()%></textarea>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">使用頻率</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="useRate" size="70" maxlength="100" value="<%=qBean.getUseRate()%>">
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">停用後不良反應是否減輕</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field", "isMitigateYn", "Y;是;N;否;U;無法得知", qBean.getIsMitigateYn()) %>
		  	</td>
		  	<td nowrap class="td_form">再使用是否出現同樣反應</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field", "isRecurYn", "Y;是;N;否;U;無法得知", qBean.getIsRecurYn()) %>
		  	</td>
		</tr>
		<%
		if(!"Y".equals(isClose)){
		%>	
		<tr>
			<td nowrap class="td_form">醫師診斷證明</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="diagnosisProof" size="60" maxlength="100" value="<%=qBean.getDiagnosisProof()%>">
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件(醫師診斷證明)</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="../cosex/cosex0104f.jsp">
					<jsp:param value="COSDP" name="fileType"/>
				</jsp:include>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">就醫紀錄(病例報告)</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="diagnosisReport" size="60" maxlength="100" value="<%=qBean.getDiagnosisReport()%>">
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件(就醫紀錄)</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="../cosex/cosex0104f.jsp">
					<jsp:param value="COSSD" name="fileType"/>
				</jsp:include>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">其他相關資料</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="diagnosisOther" size="60" maxlength="100" value="<%=qBean.getDiagnosisOther()%>">
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關檢查及檢驗數據</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="cosin0403f.jsp"/>	
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件(相關檢查及檢驗數據)</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="../cosex/cosex0104f.jsp">
					<jsp:param value="COSID" name="fileType"/>
				</jsp:include>
		  	</td>
		</tr>
		<%
		}
		%>
		<tr>
			<td nowrap class="td_form" colspan="4" style="text-align:center">併用的其他化妝品</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="cosin0404f.jsp"/>	
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
<%
if(!"Y".equals(isClose)){
%>		
	<table id="Tab5" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form" colspan="3" style="text-align:center">相關附件</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="../cosex/cosex0104f.jsp">
					<jsp:param value="C" name="fileType"/>
				</jsp:include>
		  	</td>
		</tr>
		</table>
	</td></tr>
	</table>
<%
}
%>	
	<script type="text/javascript">
		function checkIsEdit(id, notifierName){
			var formCode = "COS01";
			if("<%=qBean.getCosType()%>" == "1"){
				formCode = "COS02";
			}else if("<%=qBean.getCosType()%>" == "2"){
				formCode = "COS03";
			}
			var s = "&sysTemType=COS&formCode=" + formCode + "&id=" + id + "&notifier=" + form1.notifierName.value;
			var xUserUpdate = getRemoteData("../../ajax/jsonCon0003Db.jsp", s);
			
			if (xUserUpdate!=null && xUserUpdate.length>0){
				var jsonUserUpdate = eval ('(' + xUserUpdate + ')');
				
				alert("此案件目前已有其他作業人員編輯中!");
				return false;	
			}
			return true;
		}
		
		function deleteEdit(id){
			var formCode = "COS01";
			if("<%=qBean.getCosType()%>" == "1"){
				formCode = "COS02";
			}else if("<%=qBean.getCosType()%>" == "2"){
				formCode = "COS03";
			}
			var s = "&sysTemType=COS&formCode=" + formCode + "&id=" + id;
			var x = getRemoteData("../../ajax/jsonDeleteCon0003Db.jsp", s);
			
			if(x!=null && x.length>0){
				var jsonUserUpdate = eval ('(' + x + ')');
				if(jsonUserUpdate.r == "Y"){
				
				}else{
				
				}	
			}
		}
		
		function showPermitKey(){
			if(form1.permitKey.value=="" && form1.permitNo.value==""){
				alert("許可證字號為空值，無法查詢");
				return;
			}
			openCenterWindow(900, 600, getVirtualPath() + "tcbw/cosin/cosin0407f.jsp?permitKey=" + form1.permitKey.value + "&permitNo=" + form1.permitNo.value);
		}
		
		function swhoCaseNoInfo(){
			if(form1.caseNo.value == ""){
				alert("登錄編號為空值，無法查詢");
				return;
			}
			openCenterWindow(800, 300, getVirtualPath() + "tcbw/cosin/cosin0408f.jsp?q_caseNo=" + form1.caseNo.value);
		}
		
		// 取得廠商
		function getManufactorInfo(){
			openCenterWindow(800, 500, getVirtualPath() + "tcbw/cosin/cosin0304f.jsp");
		}

		function lockManufactorInfo(){
			$("#manufactor1").removeClass("field").addClass('field_RO').attr("readonly", "readonly");
		}

		function clearManufactorInfo(){
			document.all.item("manufactorID").value = "";
			$("#manufactor1").val("").removeClass("field_RO").addClass('field').attr("readonly", "");
			
			if($("#manufactor1").attr("disabled")){
				$("#manufactor1").attr("disabled", "");
			}
		}
		
		// 是否已與廠商接觸過
		function chEvenContactYn(val){
			if(val == "Y"){
				$("tr[id=dealWith]").show();
			}else{
				for(var i=0; i<form1.dealWith.length; i++){
					form1.dealWith[i].checked = false;
				}
				$("tr[id=dealWith]").hide();
				for(var i=0; i<form1.isRecurrenceYn.length; i++){
					form1.isRecurrenceYn[i].checked = false;
				}
				$("tr[id=isRecurrenceYn]").hide();
			}
		}

		// 處理後是否再度發生
		function chDealWith(val){
			if(val=="O" || val=="A"){
				$("tr[id=isRecurrenceYn]").show();
			}else{
				for(var i=0; i<form1.isRecurrenceYn.length; i++){
					form1.isRecurrenceYn[i].checked = false;
				}
				$("tr[id=isRecurrenceYn]").hide();
			}
		}
		
		// 不良品時，變動此欄位時，在此只需LOCK欄位
		function chIsDamageYn(val){		
			if(val=="Y" || val=="O"){
				if(val == "Y"){
					form1.otherInformation.disabled = false;
					form1.otherIsDamageYn.value = "";
					form1.otherIsDamageYn.disabled = true;
				}
				if(val == "O"){
					form1.otherIsDamageYn.disabled = false;
					form1.otherInformation.value = "";
					form1.otherInformation.disabled = true;
				}
			}else{
				form1.otherIsDamageYn.value = "";
				form1.otherIsDamageYn.disabled = true;
				form1.otherInformation.value = "";
				form1.otherInformation.disabled = true;
			}
		}
		
		// 審核之後頁籤，轉換成PASSWORD
		function chUserInput(){
			$("[id*=notifier]").each(function(){
				if($(this).attr("type") == "text" || $(this).attr("type") == "password"){
					var tmp = $('<input class="field_RO" type="password" id="' + $(this).attr("id") + '" name="' + $(this).attr("name") + '" size="' + $(this).attr("size") + '" value="' +  $(this).val() + '">'); 
					$(this).replaceWith(tmp.css("background-color", editbg));
				}
			});
		}
		
		// 顯示個資
		function openUserInfo(){
			setFormItem("doOpenUserInfo", "R");
			$("[id*=notifier]").each(function(){
				if($(this).attr("type") == "password"){
					var tmp = $('<input readonly class="field_RO" type="text" id="' + $(this).attr("id") + '" name="' + $(this).attr("name") + '" size="' + $(this).attr("size") + '" value="' +  $(this).val() + '">'); 
					$(this).replaceWith(tmp.css("background-color", editbg));
				}
			});
			
			var url = '../../ajax/jsonCommonLogDb.jsp';
		    var q = "&code=HFR01";
			    q +="&methodName=open";
			    q +="&db=Hfr0001Db";
			    q +="&hql=select id,applNo,caseOwner,notifierName from Cos0001Db where id = " + form1.id.value;
			var xUserUpdate = getRemoteData(url,q);
		}
		
		// 不良反應狀況描述-代碼
		function chNonSeriousAR(val){
			if(val == "90"){
				$("#AR1").show();
			}else{
				$("#AR1").hide();
				$("[name=nonSeriousAROther]").val("");
			}
		}
	</script>