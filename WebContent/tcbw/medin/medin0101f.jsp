<!--
程式目的：
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
obj = (com.kangdainfo.tcbw.view.medin.MEDIN0101F)obj.queryOne();

String thirdParty="N";

if("1".equals(obj.getCaseType()))
  thirdParty=TCBWCommon.getThirdParty("MED01","",User.getUserId(),"3");
else if("2".equals(obj.getCaseType()))
  thirdParty=TCBWCommon.getThirdParty("MED02","",User.getUserId(),"3");

%>

<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
</script>
	<table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報訊息</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">案件編號</td>
			<td nowrap class="td_form_white" width="35%">
                <input class="field_RO" type="text" name="applNo" value="<%=obj.getApplNo()%>">
			</td>
			<td nowrap class="td_form" width="15%">案件狀態</td>
			<td nowrap class="td_form_white"  width="35%">
			    <input class="field_RO" type="text" name="statusCh" value="<%=obj.getStatusCh()%>"> 
			    <input class="field_RO" type="hidden" name="status" value="<%=obj.getStatus()%>"> 
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">關聯案件編號</td>
			<td nowrap class="td_form_white" width="35%">
                <input class="field_RO" type="text" name="applNo1" value="<%=obj.getApplNo1()%>">
			</td>
			<td nowrap class="td_form" width="15%">關聯案件狀態</td>
			<td nowrap class="td_form_white"  width="35%">
			    <input class="field_RO" type="text" name="statusCh1" value="<%=obj.getStatusCh1()%>"> 
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">發生日期</td>
			<td nowrap class="td_form_white" width="35%">
                <%=View.getPopCalendar("field","occurDate",obj.getOccurDate())%>
			</td>
			<td nowrap class="td_form" width="15%">通報中心<br>接獲通報日期</td>
			<td nowrap class="td_form_white"  width="35%">
                <%=View.getPopCalendar("field","notifierRevDate",obj.getNotifierRevDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">通報者獲知日期</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getPopCalendar("field_RO","notifierRepDate",obj.getNotifierRepDate())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">原始藥物不良事件<br>獲知來源</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="radio" name="drugEventsSources" value="1" <%if("1".equals(obj.getDrugEventsSources())) out.print("checked"); %>>由醫療人員轉知(
				<%=View.getCommonRadioBoxOption("field","medicalStaff","medTitle",obj.getMedicalStaff(),"")%>
					<input class="field" type="text" name="medicalStaffOther" size="50" maxlength="30"  value="<%=obj.getMedicalStaffOther()%>"  />)
					<br>
				<input class="field" type="radio" name="drugEventsSources" value="2" <%if("2".equals(obj.getDrugEventsSources())) out.print("checked"); %>>由衛生單位得知(
					<%=View.getRadioBoxTextOption("field","healthUnits","1;衛生局(所);2;其他",obj.getHealthUnits())%>
					<input class="field" type="text" name="healthUnitsOther" size="50" maxlength="30" value="<%=obj.getHealthUnitsOther()%>"/>
					)
					<br>
				<input class="field" type="radio" name="drugEventsSources" value="3" <%if("3".equals(obj.getDrugEventsSources())) out.print("checked"); %>>廠商<br>
				<input class="field" type="radio" name="drugEventsSources" value="4" <%if("4".equals(obj.getDrugEventsSources())) out.print("checked"); %>>由民眾主動告知
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">案例來源</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getRadioBoxTextOption("field","caseSource","in;國內，或;out;國外",obj.getCaseSource())%>，
				<input class="field" type="text"  name="caseSourceOutCountry" size="35" maxlength="20" value="<%=obj.getCaseSourceOutCountry()%>"  />(國家)
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">報告類別</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field","reportKind","1;初始報告;2;追蹤報告",obj.getReportKind())%>，
				第<input class="field" type="text" name="trackingNum" size="5" maxlength="2" value="<%=obj.getTrackingNum()%>"  />次<br>
			</td>		
			<td nowrap class="td_form">矯正措施</td>
			<td nowrap class="td_form_white">
			    <%=View.getCommonRadioBoxOption("field","correctiveAction","medYNO",obj.getCorrectiveAction(),"")%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">附件</td>
			<td nowrap class="td_form_white" >
				<%=View.getRadioBoxTextOption("field","attachment","N;無;Y;有",obj.getAttachment())%>
				，共<input class="field" type="text" name="attachmentYnum" size="5" maxlength="2" value="<%=obj.getAttachmentYnum()%>" />件
			</td>
			<td nowrap class="td_form">產品經公告列入<br>藥物安全監視</td>
			<td nowrap class="td_form_white">
			    <%=View.getCommonRadioBoxOption("field","drugSafetyMonitoring","medYNO",obj.getDrugSafetyMonitoring(),"")%>
			</td>
		</tr>
		
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報者資訊
			<input class="field_Q" id="notifier" type="checkBox" name="notifier" value="Y" <%if("Y".equals(obj.getNotifier())) out.write("checked"); %> />
			</td>
		</tr>	
		<tr>
			<td nowrap class="td_form">姓名</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text"   name="notifierName"   size="20" maxlength="10" />
			</td>
			<td nowrap class="td_form">電話</td>
			<td nowrap class="td_form_white">
			    (<input class="field_RO" type="text"  name="notifierAreaCode" size="3"  maxlength="3"/>)-
				-<input class="field_RO" type="text"  name="notifierTel" size="15" maxlength="10"/>
				#<input class="field_RO" type="text"  name="notifierTelExt" size="3" maxlength="3">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white" colspan="3">				
				<input class="field_RO" type="text" name="notifierDeptID" size="10" maxlength="10"  />
	  	        <input class="field_RO" type="text" name="notifierDept" size="30" maxlength="6"   />
			</td>				
		</tr>		
		<tr>
			<td nowrap class="td_form">電子郵件</td>
			<td nowrap class="td_form_white" colspan="3" >
				<input class="field_RO" type="text"  name="notifierEamil"  size=50" maxlength="30" />
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form">地址</td>
			<td nowrap class="td_form_white" colspan="3" >
				<select class="field_RO" name="notifierCounty" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY","")%>
			    </select>
			    <select name="notifierZipCode" class="field_RO">
				   <%=View.getOptionCon1002("")%>
			    </select>
				<input class="field_RO" type="text"  name="notifierAddress" size="80" maxlength="50" />
			</td>
		</tr>
        <tr>
			<td nowrap class="td_form">屬性</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field_RO" type="radio" name="notifierType" value="03" <%if("03".equals(obj.getNotifierType())) out.print("checked"); %>>醫療人員，
				            醫院名稱︰<input class="field_RO" type="text" name="notifierStaffHospital" size="30" maxlength="30"  value="<%=obj.getNotifierStaffHospital()%>"  />
					<br>&nbsp;&nbsp;&nbsp;&nbsp;(職稱：
					<%=View.getCommonRadioBoxOption("field_RO","notifierStaffTitle","medTitle",obj.getNotifierStaffTitle(),"")%>
					<input class="field_RO" type="text" name="notifierStaffTitleOther" value="<%=obj.getNotifierStaffTitleOther()%>" />)
					<br>
				<input class="field_RO" type="radio" name="notifierType" value="02" <%if("02".equals(obj.getNotifierType())) out.print("checked"); %>>廠商<br>
				<input class="field_RO" type="radio" name="notifierType" value="01" <%if("01".equals(obj.getNotifierType())) out.print("checked"); %>>民眾<br>
			    <input class="field_RO" type="radio" name="notifierType" value="04" <%if("04".equals(obj.getNotifierType())) out.print("checked"); %>>衛生單位
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">您是否願意提供廠商<br>您的聯絡資訊<br>以助廠商分析不良事件</td>
			<td nowrap class="td_form_white" colspan="3" >
				<%=View.getRadioBoxTextOption("field","isContactYn","Y;願意;N;不願意",obj.getIsContactYn())%>
			</td>
		</tr>
		
		<tr>
			<td nowrap class="td_form_left" colspan="4">病人相關資料</td>
		</tr>
		<tr>
			<td nowrap class="td_form">病人識別代碼</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" size="15" maxlength="10" name="badReactionPatientCode" value="<%=obj.getBadReactionPatientCode()%>" />
			</td>
			<td nowrap class="td_form">性別</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field","badReactionSex","M;男;F;女",obj.getBadReactionSex())%>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">出生日期</td>
			<td nowrap class="td_form_white" colspan="3" >
				<%=View.getPopCalendar("field","badReactionBirthday",obj.getBadReactionBirthday())%>
				&nbsp;&nbsp;(或約<input type="text" size="3" maxlength="3" name="badReactionAge" value="<%=obj.getBadReactionAge()%>"  />歲)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">身高</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" size="3" maxlength="3" name="badReactionHeight" value="<%=obj.getBadReactionHeight()%>" />公分
			</td>
			<td nowrap class="td_form">體重</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" size="3" maxlength="3" name="badReactionWeight" value="<%=obj.getBadReactionWeight()%>"/>公斤
			</td>				
		</tr>
	</table>
	
	<table id="Tab2" width="100%" align="center" class="table_form">
	    <tr>
			<td nowrap class="td_form_left" colspan="4">不良事件類別</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">不良事件類別</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCheckBoxTextOption("field_RO","eventKind","1;不良反應;2;不良品",obj.getEventKind())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">不良事件資訊</td>
		</tr>
		<tr>
		    <td nowrap class="td_form">許可證字號</td>
		    <td nowrap class="td_form_white">
		    <select name="medPermit" class="field" onChange="permitData1('medPermit','medPermitNumber');">
				<%=View.getOptionCodeKind("MEDPKID", obj.getMedPermit())%>
			</select>
				第<input class="field" type="text" onChange="permitData1('medPermit','medPermitNumber');" name="medPermitNumber" size="10" maxlength="6" value="<%=obj.getMedPermitNumber()%>">號
		        <input type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" class="field" >
		    </td>
			<td nowrap class="td_form" width="15%">許可證申請商</td>
			<td nowrap class="td_form_white" width="35%">
			    <input class="field_X" type="text" disabled name="medPermitFirmCode" size="10" value="<%=obj.getMedPermitFirmCode()%>">
				<input class="field_X" type="text" disabled name="medPermitFirm" size="20" value="<%=obj.getMedPermitFirm()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">中文品名</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field_X" type="text" disabled name="medCname" size="40"  value="<%=obj.getMedCname()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">醫材主類別</td>
			<td nowrap class="td_form_white">
			    <%=View.getPopCode("field","medMainCategoryCode","",Common.get(View.getOneCodeName("MEDMCT",Common.get(obj.getMedMainCategory()))),"","MEDMCT","medMainCategory",obj.getMedMainCategory())%>
			</td>
			<td nowrap class="td_form">醫材次類別</td>
			<td nowrap class="td_form_white">
			    <%=View.getPopCode("field","medSecCategoryCode","",Common.get(View.getOneCodeName("MEDSCT",Common.get(obj.getMedSecCategory()))),"medMainCategory","MEDSCT","medSecCategory",obj.getMedSecCategory())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造廠</td>
			<td nowrap class="td_form_white">
				<input class="field_X" type="text" disabled name="medFactory" size="30"  value="<%=obj.getMedFactory()%>">
			</td>
			<td nowrap class="td_form">製造廠國別</td>
			<td nowrap class="td_form_white">
				<input class="field_X" type="text" disabled name="medCountr" size="30"  value="<%=obj.getMedCountr()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">型號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medModel" size="10" maxlength="10" value="<%=obj.getMedModel()%>">
			</td>
			<td nowrap class="td_form">序號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medNo" size="10" maxlength="10" value="<%=obj.getMedNo()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">批號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medLotNum"  size="10" maxlength="10" value="<%=obj.getMedLotNum()%>">
			</td>
			<td nowrap class="td_form">軟體版本</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medSoftwareVersion"  size="10" maxlength="10" value="<%=obj.getMedSoftwareVersion()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field","medManufactureDate",obj.getMedManufactureDate())%>
			</td>
			<td nowrap class="td_form">有效日期/保存期限</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field","medEffectiveDate",obj.getMedEffectiveDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">採購日期</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","medPurchaseDate",obj.getMedPurchaseDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">使用日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field","medUseDate",obj.getMedUseDate())%>
			</td>
			<td nowrap class="td_form">使用原因</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medUseReason" size="30" maxlength="30" value="<%=obj.getMedUseReason()%>">
			</td>
		</tr>
	</table>
	
	<table id="Tab3" width="100%" align="center" class="table_form">
	   <tr>
			<td nowrap class="td_form" width="15%">不良反應結果</td>
			<td nowrap class="td_form_white" colspan="3" width="85%">
				<%=obj.getCheckBoxOptionAD("field","badReactionResults","select codeId,codeName from CommonCode where commonCodeKind.codeKindId = 'MEDAD1'",obj.getBadReactionResults(),obj.getBadReactionResultsDeathDate(),obj.getBadReactionResultsDeathReason(),obj.getBadReactionResultsOther())  %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">醫療器材操作者</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getCommonRadioBoxOption("field","medOperator","medOperator",obj.getMedOperator(),"")%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">器材處置現況</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="medDisposalStatus" value="1" <%if("1".equals(obj.getMedDisposalStatus())) out.print("checked"); %>>已銷毀&nbsp;&nbsp;
				<input class="field" type="radio" name="medDisposalStatus" value="2" <%if("2".equals(obj.getMedDisposalStatus())) out.print("checked"); %>>尚在調查中&nbsp;&nbsp;
				<input class="field" type="radio" name="medDisposalStatus" value="3" <%if("3".equals(obj.getMedDisposalStatus())) out.print("checked"); %>>尚植於病患體內&nbsp;&nbsp;
				<input class="field" type="radio" name="medDisposalStatus" value="4" <%if("4".equals(obj.getMedDisposalStatus())) out.print("checked"); %>>於<%=View.getPopCalendar("field","medDisposalStatusDate",obj.getMedDisposalStatusDate())%>退還廠商(原廠)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">器材使用</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field","medUse","medUse",obj.getMedUse(),"")%>
				<input type="text" size="20" maxlength="30" name="medUseOther" value="<%=obj.getMedUseOther()%>"  />
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">曾使用同類醫材<br>之經驗</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="onceUseMed" value="Y" <%if("Y".equals(obj.getOnceUseMed())) out.print("checked"); %>>是，醫材名稱
				<input class="field" type="text" name="onceUseMedName" size="20" maxlength="20" value="<%=obj.getOnceUseMedName()%>"  /> 
				若發生不良反應請描述<input class="field" type="text" size="20" maxlength="30" name="onceUseMedOther"  value="<%=obj.getOnceUseMedOther()%>"/> <br>
				<input class="field" type="radio" name="onceUseMed" value="N" <%if("N".equals(obj.getOnceUseMed())) out.print("checked"); %>>否&nbsp;&nbsp;
				<input class="field" type="radio" name="onceUseMed" value="O" <%if("O".equals(obj.getOnceUseMed())) out.print("checked"); %>>無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">停用後不良反應症狀<br>是否減輕</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getCommonRadioBoxOption("field","stopMedMitigate","medYNO",obj.getStopMedMitigate(),"")%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">再使用是否出現<br>同樣反應</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field","sameReaction","medYNO",obj.getSameReaction(),"")%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">不良事件描述資訊</td>
		</tr>
		<tr>		
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="15%">發生日期</th>
		         <th class="listTH" width="15%">部位</th>
		         <th class="listTH" width="30%">症狀</th>
		         <th class="listTH" width="10%">嚴重程度</th>
		         <th class="listTH" width="30%">處置</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed0002Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed0002DbView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">相關檢查及檢驗數據資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="15%">檢驗日期</th>
		         <th class="listTH" width="45%">檢驗項目</th>
		         <th class="listTH" width="40%">檢驗數據</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed0003Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed0003DbView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">併用醫療器材資訊</td>
		</tr>
		<tr>		
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="25%">許可證字</th>
		         <th class="listTH" width="20%">品名</th>
		         <th class="listTH" width="10%">許可證申請商</th>
		         <th class="listTH" width="10%">型號</th>
		         <th class="listTH" width="10%">醫材主類別</th>		         
		         <th class="listTH" width="10%">使用日期</th>
		         <th class="listTH" width="15%">使用原因</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed0004Db()">
		         </th>
	           </tr>
	           </thead>

	           <tbody id="attMed0004DbView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">併用藥品資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
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
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed0005Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed0005DbView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">其他相關資料</td>
			<td nowrap class="td_form_white" colspan="3">
			    <textarea name="otherRelatedData" cols="60" rows="2"><%=obj.getOtherRelatedData()%></textarea>
			</td>
		</tr>
	</table>
	
	<table id="Tab4" width="100%" align="center" class="table_form">
	   <tr>
			<td nowrap class="td_form" width="15%">產品問題分類</td>
			<td nowrap class="td_form_white" colspan="3" width="85%">
               (1)器材操作：<%=View.getCommonCheckBoxTextOption("field","productProblemKind1","medOperating",obj.getProductProblemKind1())%><br>
               (2)環境設施：<%=View.getCommonCheckBoxTextOption("field","productProblemKind2","medFacility",obj.getProductProblemKind2())%><br>
               (3)人因：<%=View.getCommonCheckBoxTextOption("field","productProblemKind3","medPeoFactor",obj.getProductProblemKind3())%><br>
               (4)物理特性：<%=View.getCommonCheckBoxTextOption("field","productProblemKind4","medPhysical",obj.getProductProblemKind4())%><br>
               (5)其他(請敘述)：<input type="text" size="40" maxlength="30" name="productProblemKindOther" value="<%=obj.getProductProblemKindOther() %>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">不良品缺陷描述</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="defProductDescription" cols="60" rows="2"><%=obj.getDefProductDescription()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">其他資料</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="defProductOtherDescription" cols="60" rows="2"><%=obj.getDefProductOtherDescription()%></textarea>
			    <input type="hidden" name="revision" value="<%=obj.getRevision()%>">
			</td>
			
		</tr>		
	</table>
	
    <table id="Tab9" width="100%" align="center" class="table_form">
	      <thead id="listTHEAD">   
	        <tr>
	        <td>
	            <span id="spanDoUpload">
		        <input class="toolbar_default" type="button" followPK="false" name="doUpload" value="附件上傳" onClick="upload()">&nbsp;
	            </span>
	        </td>
	        </tr>        
	        <tr>
		        <th class="listTH" width="10%" >No.</th>
		        <th class="listTH" width="30%">檔案名稱</th>
		        <th class="listTH" width="50%">檔案說明</th>
		        <th class="listTH" width="10%">刪除</th>
	        </tr>
	        </thead>
	        <tbody>
			   <%=obj.getAddFile()%>
			</tbody>
	</table>
	
	