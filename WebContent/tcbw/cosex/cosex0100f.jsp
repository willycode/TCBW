<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.cosex.COSEX0100F qBean = (com.kangdainfo.tcbw.view.cosex.COSEX0100F) request.getAttribute("qBean");
%>
	<script type="text/javascript">
		function nextPageCheck(tabId){
			var alertStr = "";
			if(tabId == "1"){
				<%=TCBWCommon.getCheckFiled("COS01", "COS01")%>
			}else if(tabId == "2"){
				<%=TCBWCommon.getCheckFiled("COS01", "COS02")%>
			}else if(tabId == "3"){
				<%=TCBWCommon.getCheckFiled("COS01", "COS03")%>
				
				if("O" == getRadioChecked(form1.isDamageYn)){
					alertStr += checkEmpty(form1.otherIsDamageYn, "其他不良品是否有損害使用者身體或健康之事實描述");
				}
			}else if(tabId == "4"){
				<%=TCBWCommon.getCheckFiled("COS01", "COS04")%>
			}
			if(alertStr.length != 0){
				alert(alertStr);
			}
		}
	</script>
	<table id="Tab1" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form">案件編號</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="applNo" size="11" maxlength="11" value="<%=qBean.getApplNo()%>">
				<input type="hidden" name="status" value="<%=qBean.getStatus()%>">
				<input type="hidden" name="notifierType" value="<%=qBean.getNotifierType()%>">
			</td>
			<td nowrap class="td_form">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="notifierRepDate" size="7" maxlength="7" value="<%=qBean.getNotifierRepDate()%>">
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
				<input class="field_RO" type="text" name="notifierName" size="20" maxlength="20" value="<%=qBean.getNotifierName()%>">
			</td>
			<td nowrap class="td_form">通報者電話</td>
		  	<td nowrap class="td_form_white" >
		  		( <input class="field_RO" type="text" name="notifierTelArea" size="2" maxlength="2" value="<%=qBean.getNotifierTelArea()%>"> )
				- <input class="field_RO" type="text" name="notifierTel" size="15" maxlength="10" value="<%=qBean.getNotifierTel()%>">
				# <input class="field_RO" type="text" name="notifierTelExt" size="3" maxlength="3" value="<%=qBean.getNotifierTelExt()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">電子郵件信箱</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field_RO" type="text" name="notifierEamil" size="40" maxlength="50" value="<%=qBean.getNotifierEamil()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">聯絡地址</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		<select class="field_RO" name="notifierArea" type="select" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", qBean.getNotifierArea())%>
			    </select>
			    <select class="field_RO" name="notifierZipCode" type="select">
				   <%=View.getOptionCon1002(qBean.getNotifierZipCode())%>
			    </select>
				<input class="field_RO" type="text" name="address" size="50" maxlength="100" value="<%=qBean.getAddress()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否同意將您個人聯絡方式提供產品負責業者</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxTextOption("field","isContactYn","N;否;Y;是",qBean.getIsContactYn())%>		
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
	
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
				<select class="field" name="permitKey" type="select" onChange="chkPermitProdData()">
					<%=View.getOptionCodeKind("CPT", qBean.getPermitKey()) %>
				</select>
				字第
				<input class="field" type="text" name="permitNo" size="20" maxlength="16" value="<%=qBean.getPermitNo()%>" onChange="chkPermitProdData()">
				號
				<%
				if(Common.getInt(qBean.getStatus()) >= 10 || "02".equals(qBean.getStatus())){
					// nothing ..
				}else{
				%>
				<input class="toolbar_default" type="button" name="chosePermit" value="許可證字號" onClick="getPermit();">
				<%
				}
				%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">登錄編號</td>
		  	<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="caseNo" size="16" maxlength="16" value="<%=qBean.getCaseNo()%>">
				<%
				if(Common.getInt(qBean.getStatus()) >= 10 || "02".equals(qBean.getStatus())){
					// nothing ..
				}else{
				%>
				<input class="toolbar_default" type="button" name="choseCaseNo" value="登錄編號" onClick="getCaseNo();">
				<%
				}
				%>
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
				<input class="field" type="text" name="manufactorName" size="30" maxlength="50" value="<%=qBean.getManufactorName()%>">
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
		  	<td nowrap class="td_form_white">
				<%=View.getYNRadioBoxTextOption("field", "isSimilarYn", qBean.getIsSimilarYn()) %>
			</td>
			<td nowrap class="td_form">是否已送交相關單位處理</td>
		  	<td nowrap class="td_form_white">
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
				<input class="field" type="radio" name="isDamageYn" value="O" <%=qBean.getIsDamageYn().equals("O")?"checked":"" %>>其他，請描述 :
				<input class="field" type="text" name="otherIsDamageYn" size="40" maxlength="50" value="<%=qBean.getOtherIsDamageYn()%>">
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
				<font color="blue">請盡可能使用精確的「標準醫學字彙」或診斷，英文或中文皆可接受。</font>
				<%--
					<input class="field" type="text" name="nonSeriousOther" size="80" maxlength="90" value="<%=qBean.getNonSeriousOther()%>">
				 --%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">不良反應描述</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="nonSeriousDis" rows="2" cols="50"><%=qBean.getNonSeriousDis() %></textarea>
				<br>
				<font color="blue">請依事件發生前後時序填寫，應包括使用化粧品前的相關病史；<br>使用化粧品及發生不良反應之時間；不良反應症狀、部位及嚴重程度；處置方式與處置後反應。</font>
			<%-- 
				<input class="field" type="text" name="nonSeriousDis" size="80" maxlength="90" value="<%=qBean.getNonSeriousDis()%>">
			--%>
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
		<tr>
			<td nowrap class="td_form">醫師診斷證明</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="diagnosisProof" size="60" maxlength="100" value="<%=qBean.getDiagnosisProof()%>">
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件(醫師診斷證明)</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="cosex0104f.jsp">
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
				<jsp:include page="cosex0104f.jsp">
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
				<jsp:include page="cosex0105f.jsp"/>	
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件(相關檢查及檢驗數據)</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="cosex0104f.jsp">
					<jsp:param value="COSID" name="fileType"/>
				</jsp:include>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form" colspan="4" style="text-align:center">併用的其他化粧品</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="cosex0106f.jsp"/>	
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
	
	<table id="Tab5" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form" colspan="3" style="text-align:center">相關附件</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="cosex0104f.jsp">
					<jsp:param value="C" name="fileType"/>
				</jsp:include>
		  	</td>
		</tr>
		</table>
	</td></tr>
	</table>
	<table id="toolbarTable" width="100%" align="center" class="table_form">
		<tr><td align="center">
			<input class="toolbar_default" type="button" followPK="false" name="nextPage" value="下 一 頁" onClick="nextPageCheck(form1.tabId.value);changeTab(parseInt(form1.tabId.value)+1);">
		</td></tr>
	</table>