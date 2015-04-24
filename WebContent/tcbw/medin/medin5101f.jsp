<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN5101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<% 
obj = (com.kangdainfo.tcbw.view.medin.MEDIN5101F) obj.queryOne();
%>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
function permitData1()
{
	var medPermit = form1.medPermit.value;
	var medPermitNumber = form1.medPermitNumber.value;

	var q = "&medPermit="+medPermit;
	    q += "&medPermitNumber="+medPermitNumber;

	//許可證字號選擇"專案"or"其他"時，將欄位打開供使用者輸入
    if(medPermit=="Z0" || medPermit=="Z5")
    {
 		document.all.item("medTestMedical").disabled = false;
    	document.all.item("medTestMedical").className = "field";
    	document.all.item("medFactory").disabled = false;
    	document.all.item("medFactory").className = "field_Q";
    	document.all.item("medMainCategory").disabled = false;
    	document.all.item("medMainCategory").className = "field_Q";
    	document.all.item("medMainCategoryCodeName").disabled = false;
    	document.all.item("medMainCategoryCodeName").className = "field_Q";
    }   
    else
    {
    	document.all.item("medTestMedical").className = "field_X";
    	document.all.item("medTestMedical").disabled = true;
    	document.all.item("medFactory").className = "field_X";
    	document.all.item("medFactory").disabled = true;
    	document.all.item("medMainCategory").className = "field_X";
    	document.all.item("medMainCategory").disabled = true;
    	document.all.item("medMainCategoryCodeName").disabled = true;
    	document.all.item("medMainCategoryCodeName").className = "field_X";
    }    

	form1.medTestMedical.value = "";  //中文品名 CHNAME
	form1.medFactory.value = "";  //製造廠 FACTNAME
	form1.medMainCategoryCodeName.value = "";  //主類別  DOCKNDID  DOCKNDMA
	form1.medMainCategory.value = "";  //主類別  DOCKNDID  DOCKNDMA
	form1.medSecCategoryCodeName.value = "";  //次類別  MSKNDID  MSKNDMA
	form1.medSecCategory.value = "";  //次類別  MSKNDID  MSKNDMA
	    
	if(medPermit!="" && medPermitNumber!="")
	{
	  x = getRemoteData("../../ajax/jsonMedPermitObjects.jsp",q );
		
	  if(x!=null && x.length>0)
	  {
		var json1 = JSON.parse(x);
		form1.medTestMedical.value = json1.obj0;  //中文品名 CHNAME
		form1.medFactory.value = json1.obj4;  //製造廠 FACTNAME
		form1.medMainCategory.value = json1.obj5;  //主類別  DOCKNDID  DOCKNDMA
		form1.medMainCategoryCodeName.value = json1.obj6;  //主類別  DOCKNDID  DOCKNDMA
        form1.medSecCategoryCodeName.value = json1.obj7;  //次類別  MSKNDID  MSKNDMA
        form1.medSecCategory.value = json1.obj8;  //次類別  MSKNDID  MSKNDMA
	  }
	  else
	  { 
		  form1.medPermit.value= "";
		  form1.medPermitNumber.value= "";
		  alert("查無此許可證號!");
	  }
	}
}
function permitData2(id)
{
	var medPermit = id.substring(0,2);
	var medPermitNumber = id.substring(2,8);
	form1.medPermit.value = medPermit;
	form1.medPermitNumber.value = medPermitNumber;
	permitData1();
}
function permitDataQ()
{
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var medPermit = form1.medPermit.value;
	var medPermitNumber = form1.medPermitNumber.value;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("../medex/medex0107f.jsp?medPermit="+medPermit+"&medPermitNumber="+medPermitNumber,'popWinE',prop);		
}
</script>
	<table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報訊息</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">發生日期</td>
			<td nowrap class="td_form_white" width="35%">
                <%=View.getPopCalendar("field","occurDate",obj.getOccurDate())%>
			</td>
			<td nowrap class="td_form" width="15%">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white"  width="35%">
                <%=View.getPopCalendar("field_RO","notifierRevDate",obj.getNotifierRevDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報者獲知日期</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getPopCalendar("field_RO","notifierRepDate",obj.getNotifierRepDate())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">案例來源</td>
			<td nowrap class="td_form_white"  colspan="3">				
				<input type="radio" name="caseSource" value="out" <%if("out".equals(obj.getCaseSource())) out.print("checked"); %> >國外，
				<input type="text" name="caseSourceOutCountry"  maxlength="20" size="15" value="<%=obj.getCaseSourceOutCountry()%>" />(國家)<br>
				<input type="radio" name="caseSource" value="in" <%if("in".equals(obj.getCaseSource())) out.print("checked"); %>>國內，
				試驗醫院<input type="text" name="caseSourceInHospital" maxlength="30" size="15"  value="<%=obj.getCaseSourceInHospital()%>"  />
				&nbsp;&nbsp;試驗醫師<input type="text" name="caseSourceInDoctor" maxlength="20" size="10" value="<%=obj.getCaseSourceInDoctor()%>"  />
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">報告類別</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxTextOption("field","reportKind","1;初始報告;2;追蹤報告",obj.getReportKind())%>
				，第<input type="text" name="trackingNum" maxlength="3" size="3" value="<%=obj.getTrackingNum()%>"  />次
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">試驗名稱</td>
			<td nowrap class="td_form_white" colspan="3">
                <input type="text" name="testName" maxlength="20" size="50" value="<%=obj.getTestName()%>" />
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">衛生福利主管機關<br>核准函文號</td>
			<td nowrap class="td_form_white" colspan="3">
                <input type="text" name="fdaNum" maxlength="20" size="40" value="<%=obj.getFdaNum()%>" />&nbsp;&nbsp;
                <%=View.getRadioBoxTextOption("field","fdaOptions","1;查驗登記用;2;學術研究用",obj.getFdaOptions())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">核准單位</td>
			<td nowrap class="td_form_white"  colspan="3">				
				<%=View.getRadioBoxTextOption("field","approvedUnits","1;醫事司;2;食品藥物管理署;3;其他",obj.getApprovedUnits())%>
				<input type="text" name="approvedUnitsOther" maxlength="30" size="20"  value="<%=obj.getApprovedUnitsOther()%>"  />
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">廠商試驗編號</td>
			<td nowrap class="td_form_white" colspan="3">
                 <input type="text" name="firmTestNo" maxlength="20" size="50" value="<%=obj.getFirmTestNo()%>"  />
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
				<input class="field_RO" type="text" name="notifierName"  size="20" maxlength="10" />
			</td>
			<td nowrap class="td_form">電話</td>
			<td nowrap class="td_form_white">
			    (<input class="field_RO" type="text" name="notifierAreaCode" size="3"  maxlength="3"/>)-
				-<input class="field_RO" type="text" name="notifierTel" size="15" maxlength="10"/>
				#<input class="field_RO" type="text" name="notifierTelExt" size="3" maxlength="3">
			</td>			
		</tr>		
		<tr>
			<td nowrap class="td_form">電子郵件</td>
			<td nowrap class="td_form_white" colspan="3" >
				<input class="field_RO" type="text" name="notifierEmail" size=50" maxlength="30"/>
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form">地址</td>
			<td nowrap class="td_form_white" colspan="3" >
				<select class="field_RO" name="notifierCounty" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", "")%>
			    </select>
			    <select name="notifierZipCode" class="field_RO">
				   <%=View.getOptionCon1002("")%>
			    </select>
				<input class="field_RO" type="text" autocomplete="off" name="notifierAddress" size="80" maxlength="50" />
			</td>
		</tr>
		
        <tr>
			<td nowrap class="td_form">屬性</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field_RO" type="radio" name="notifierType" value="03" <%if("03".equals(obj.getNotifierType())) out.print("checked"); %>>醫療人員
				<input class="field_RO" type="radio" name="notifierType" value="02" <%if("02".equals(obj.getNotifierType())) out.print("checked"); %>>廠商
				<input class="field_RO" type="radio" name="notifierType" value="01" <%if("01".equals(obj.getNotifierType())) out.print("checked"); %>>民眾
			    <input class="field_RO" type="radio" name="notifierType" value="04" <%if("04".equals(obj.getNotifierType())) out.print("checked"); %>>衛生單位，職稱：
			    <input class="field_RO" type="text" name="notifierTitle" maxlength="20" size="30" value="<%=obj.getNotifierTitle()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">病人相關資料</td>
		</tr>
		<tr>
			<td nowrap class="td_form">病人識別代碼</td>
			<td nowrap class="td_form_white">
				<input type="text" name="patientId" maxlength="10" size="20" value="<%=obj.getPatientId()%>" />
			</td>
			<td nowrap class="td_form">性別</td>
			<td nowrap class="td_form_white" >
				<%=View.getRadioBoxTextOption("field","patientSex","M;男;F;女",obj.getPatientSex())%>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">出生日期</td>
			<td nowrap class="td_form_white" colspan="3" >
				<%=View.getPopCalendar("field","patientBirth",obj.getPatientBirth())%>
				&nbsp;&nbsp;(或約<input type="text" name="patientAge" maxlength="3" size="3" value="<%=obj.getPatientAge()%>" />歲)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">身高</td>
			<td nowrap class="td_form_white">
				<input type="text" name="patientHeigth" maxlength="3" size="3" value="<%=obj.getPatientHeigth()%>" />公分
			</td>
			<td nowrap class="td_form">體重</td>
			<td nowrap class="td_form_white">
				<input type="text" name="patientWeight" maxlength="3" size="3" value="<%=obj.getPatientWeight()%>"/>公斤
			</td>				
		</tr>
	</table>
	
	<table id="Tab2" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="15%">不良反應結果</td>
			<td nowrap class="td_form_white" colspan="3">
			<%=obj.getCheckBoxOptionAD("field_Q","badReactionResults","select codeId,codeName from CommonCode where commonCodeKind.codeKindId = 'MEDAD1'",obj.getBadReactionResults(),obj.getBadReactionResultsDeathDate(),obj.getBadReactionResultsDeathReason(),obj.getBadReactionResultsOther())  %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">不良事件描述（請依事件發生前後順序填寫。應包括發生不良反應之部位、症狀、嚴重程度及處置）</td>
		</tr>
		<tr>	
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="15%">發生日期</th>
		         <th class="listTH" width="20%">部位</th>
		         <th class="listTH" width="20%">症狀</th>
		         <th class="listTH" width="20%">嚴重程度</th>
		         <th class="listTH" width="20%">處置</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed2002Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed2002DbView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否為非預期<br>之不良事件</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxTextOption("field","isAdverseEvents","Y;是;N;否",obj.getIsAdverseEvents())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">相關檢查及檢驗數據資訊（例如︰藥品血中濃度、肝/腎功能指數……等）</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th width="15%" class="listTH">檢驗日期</th>
		         <th width="50%" class="listTH">檢驗項目</th>
		         <th width="30%" class="listTH">檢驗數據</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed2003Db()">
		         </th>
	           </tr>
	           </thead>
               <tbody id="attMed2003DbView">
			   
			   </tbody>
	    
               </table>
		     </td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他相關資料（例如︰診斷、過敏、懷孕、吸菸、喝酒、習慣、其他疾病、肝/腎功能不全…等）</td>
		</tr>		
		<tr>
			<td nowrap class="td_form">其他相關資料</td>
			<td nowrap class="td_form_white" colspan="3">
			    <textarea name="textarea" cols="60" rows="2"><%=obj.getOtherDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">醫療器材資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">許可證字號</td>
		    <td nowrap class="td_form_white">
		    <select name="medPermit" class="field" onChange="permitData1();">
				<%=View.getOptionCodeKind("MEDPKID", obj.getMedPermit())%>
			</select>
			           字第<input class="field" type="text" onChange="permitData1();" name="medPermitNumber" size="10" maxlength="6" value="<%=obj.getMedPermitNumber()%>">號
		        <input type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" class="field" >
		    </td>
		    <td nowrap class="td_form" width="15%">試驗醫材名稱</td>
			<td nowrap class="td_form_white" width="35%">
			     <input class="field" type="text" name="medTestMedical" size="30" maxlength="30" value="<%=obj.getMedTestMedical()%>">
			</td>
		</tr>
		<tr>
			<!--  
			<td nowrap class="td_form" width="15%">器材種類</td>
			<td nowrap class="td_form_white" width="35%">
			    <%=View.getPopCode("field","medType","","","","CM","medType","",new int[]{2,2,10,20})%>
			</td>
			-->
		</tr>
		<tr>
			<td nowrap class="td_form">醫材主類別</td>
			<td nowrap class="td_form_white">
			    <%=View.getPopCode("field","medMainCategoryCode","",Common.get(View.getOneCodeName("MEDMCT",Common.get(obj.getMedMainCategory()))),"","MEDMCT","medMainCategory",obj.getMedMainCategory())%>
			</td>
			<td nowrap class="td_form">醫材次類別</td>
			<td nowrap class="td_form_white">
				 <%=View.getPopCode("field_Q","medSecCategoryCode","",Common.get(View.getOneCodeName("MEDSCT",Common.get(obj.getMedSecCategory()))),"medMainCategory","MEDSCT","medSecCategory",obj.getMedSecCategory())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >製造廠</td>
			<td nowrap class="td_form_white" >
			     <input class="field" type="text" name="medFactory" size="15" maxlength="10" value="<%=obj.getMedFactory()%>">
			</td>
			<td nowrap class="td_form" >供應商</td>
			<td nowrap class="td_form_white">
			     <input class="field" type="text" name="medSupplier" size="15" maxlength="10" value="<%=obj.getMedSupplier()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >型號</td>
			<td nowrap class="td_form_white" >
			     <input class="field" type="text" name="medModel" size="15" maxlength="10" value="<%=obj.getMedModel()%>">
			</td>
			<td nowrap class="td_form" >序號</td>
			<td nowrap class="td_form_white">
			     <input class="field" type="text" name="medNo" size="15" maxlength="10" value="<%=obj.getMedNo()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >批號</td>
			<td nowrap class="td_form_white" >
			     <input class="field" type="text" name="medLotNum" size="15" maxlength="10" value="<%=obj.getMedLotNum()%>">
			</td>
			<td nowrap class="td_form" >製造日期</td>
			<td nowrap class="td_form_white" >
			    <%=View.getPopCalendar("field","medManufactureDate",obj.getMedManufactureDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >醫療器材操作者</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxTextOption("field","medOperator","1;醫療人員;2;病人或其家屬;3;其他",obj.getMedOperator())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >使用日期</td>
			<td nowrap class="td_form_white" >
				<%=View.getPopCalendar("field","medUseDate",obj.getMedUseDate())%>
			</td>
			<td nowrap class="td_form" >使用原因</td>
			<td nowrap class="td_form_white" >
				<input class="field" type="text" name="medUseReason" size="20" maxlength="15" value="<%=obj.getMedUseReason()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >停用日期</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","medStopDate",obj.getMedStopDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >是否可提供<br>器材作評估</td>
			<td nowrap class="td_form_white" colspan="3">
				<input type="radio" name="medUseIsYn" value="Y" <%if("Y".equals(obj.getMedUseIsYn()))out.print("checked");%>>是，取得來源
				<input type="text" size="20" maxlength="30" name="medYesSoruce" value="<%=obj.getMedYesSoruce()%>"  />&nbsp;&nbsp;
				<input type="radio" name="medUseIsYn" value="N" <%if("N".equals(obj.getMedUseIsYn()))out.print("checked");%>>否&nbsp;&nbsp;
				<input type="radio" name="medUseIsYn" value="O" <%if("O".equals(obj.getMedUseIsYn()))out.print("checked");%>>已於<%=View.getPopCalendar("field","medNoReturnDate",obj.getMedNoReturnDate())%>退還給廠商
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
		         <th class="listTH" width="15%">品名</th>
		         <th class="listTH" width="10%">許可證申請商</th>
		         <th class="listTH" width="10%">型號</th>
		         <th class="listTH" width="10%">醫材主類別</th>		         
		         <th class="listTH" width="10%">使用日期</th>
		         <th class="listTH" width="15%">使用原因</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed2004Db()">
		         </th>
	           </tr>
	           </thead>
               <tbody id="attMed2004DbView">
			   
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
		         <th class="listTH" width="20%">使用期間</th>
		         <th class="listTH" width="10%">使用原因</th>
		         <th width="5%" class="listTH">
		            <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed2005Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed2005DbView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">曾使用同類<br>醫材之經驗</td>
			<td nowrap class="td_form_white" colspan="3">
				<input type="radio" name="medOnceUseMed" value="Y" <%if("Y".equals(obj.getMedOnceUseMed()))out.print("checked");%>>是，
				醫材名稱<input type="text" name="medOnceUseMedName" maxlength="30" size="20" value="<%=obj.getMedOnceUseMedName()%>"  /> 
				若發生不良反應請描述<input type="text" name="medOnceUseBadReaction" maxlength="30" size="20" value="<%=obj.getMedOnceUseBadReaction()%>"  /> <br>
				<input type="radio" name="medOnceUseMed" value="N" <%if("N".equals(obj.getMedOnceUseMed()))out.print("checked");%>>否&nbsp;&nbsp;
				<input type="radio" name="medOnceUseMed" value="O" <%if("O".equals(obj.getMedOnceUseMed()))out.print("checked");%>>無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">停用後不良反應症狀<br>是否減輕</td>
			<td nowrap class="td_form_white">	
				<%=View.getCommonRadioBoxOption("field","medStopMedMitigate","MEDYN",obj.getMedStopMedMitigate(),"")%>		
			</td>
			<td nowrap class="td_form" >再使用是否出現<br>同樣反應</td>
			<td nowrap class="td_form_white" >
				<%=View.getCommonRadioBoxOption("field","onceSameReaction","MEDYN",obj.getOnceSameReaction(),"")%>		
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否同時使用</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field","sameTimeUse","MEDSAME",obj.getSameTimeUse(),"")%>		
				<input type="text" name="sameTimeUseOther" maxlength="30" size="30" value="<%=obj.getSameTimeUseOther()%>" />
				*若有同時使用，請填入併用藥品內◦
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">因果關係資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >試驗醫師評估醫材<br>與SAE之因果關係</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field","medSea","MEDSEA",obj.getMedSea(),"",3)%>		
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >驗醫師評估手續程序<br>與SAE之因果關係</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field","procedureSea","MEDSEA",obj.getProcedureSea(),"",3)%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">相關通報資訊</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >本案是否立即通知<br>試驗委託者</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getYNRadioBoxTextOption("field","noticeSponsor",obj.getNoticeSponsor()) %>
				&nbsp;&nbsp;&nbsp;&nbsp;且提供詳細書面報告？&nbsp;&nbsp;&nbsp;&nbsp;
				<%=View.getYNRadioBoxTextOption("field","noticeSponsorWritten",obj.getNoticeSponsorWritten()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >本案是否立即<br>通知人體試驗委員會</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getYNRadioBoxTextOption("field","noticeIRB",obj.getNoticeIRB()) %>
				&nbsp;&nbsp;&nbsp;&nbsp;且提供詳細書面報告？&nbsp;&nbsp;&nbsp;&nbsp;
				<%=View.getYNRadioBoxTextOption("field","noticeIRBWritten",obj.getNoticeIRBWritten()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">本案是否立即通知<br>試驗核准單位</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getYNRadioBoxTextOption("field","noticeApprovedUnits",obj.getNoticeApprovedUnits()) %>
				&nbsp;&nbsp;&nbsp;&nbsp;且提供詳細書面報告？&nbsp;&nbsp;&nbsp;&nbsp;
				<%=View.getYNRadioBoxTextOption("field","noticeApprovedUnitsWritten",obj.getNoticeApprovedUnitsWritten()) %>
			</td>
		</tr>
	
	</table>
    <table id="Tab3" width="100%" align="center" class="table_form">
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
