<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.comple.COMPLE0201F qBean = (com.kangdainfo.tcbw.view.comple.COMPLE0201F) request.getAttribute("qBean");
//取得藥品類別參數
java.util.List<CommonCode> list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRG0307");
%>		
		<!-- 基本資料 -->
		<table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報訊息</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">登錄編號</td>
			<td nowrap class="td_form_white" colspan="3">
                <input class="field_RO" type="text" name="applNo" value="<%=qBean.getApplNo()%>">(由通報中心填寫)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發現日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field","occurDate",qBean.getOccurDate())%>
			</td>
			<td nowrap class="td_form">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field","notifierRevDate",qBean.getNotifierRevDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報者獲知日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field","notifierRepDate",qBean.getNotifierRepDate())%>
			</td>
			<td nowrap class="td_form" >收案日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field","enrolledDate",qBean.getEnrolledDate())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >通報來源</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getRadioBoxOption("field", "notifierSource", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGNFS' order by codeId", qBean.getNotifierSource())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">
				通報者資訊
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">姓名</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="hidden" name="notifierUserID" maxlength="50" size="15" value="<%=qBean.getNotifierUserID()%>">
				<input class="field_RO" type="text" name="notifierName" maxlength="10" size="15" value="<%=qBean.getNotifierName()%>" />				
				<input class="field" type="button" name="btn_User" value="查詢通報者資料" title="通報者資訊輸入輔助視窗" onclick="popUserForm()"/>
				<input class="field" type="button" value="清除通報者" onClick="clearUser();">
			</td>
			<td nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white">
			    <input class="field" type="hidden" name="notifierDeptID" size="10" maxlength="10" value="<%=qBean.getNotifierDeptID()%>" />
	  	        <input class="field_RO" type="text" name="notifierDept" size="30" maxlength="50"  value="<%=qBean.getNotifierDept()%>" />
			    <input type="button" name="btnQryDept" onClick="popNotifierDept(notifierType);" value="查詢" width="120px" class="field" >			    
			</td>			
		</tr>		
		<tr>
			<td nowrap class="td_form">電話</td>
			<td nowrap class="td_form_white">
				 ( <input class="field" type="text" name="notifierTelArea" size="2" maxlength="2" value="<%=qBean.getNotifierTelArea()%>"> )
			    - <input class="field" type="text" name="notifierTel" size="10" maxlength="10" value="<%=qBean.getNotifierTel()%>">
			    # <input class="field" type="text" name="notifierTelExt" size="3" maxlength="3" value="<%=qBean.getNotifierTelExt()%>">
			</td>
			<td nowrap class="td_form">E-Mail</td>
			<td nowrap class="td_form_white" >
				<input class="field" type="text" name="notifierEmail" maxlength="30" size="20" value="<%=qBean.getNotifierEmail()%>"/>
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">手機</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="notifierPhone" maxlength="10" size="10" value="<%=qBean.getNotifierPhone()%>"/>
			</td>
			<td nowrap class="td_form">傳真</td>
			<td nowrap class="td_form_white" >
				( <input class="field" type="text" name="notifierFaxArea" size="2" maxlength="2" value="<%=qBean.getNotifierFaxArea()%>"> )
			    - <input class="field" type="text" name="notifierFax" size="10" maxlength="10" value="<%=qBean.getNotifierFax()%>">
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form">地址</td>
			<td nowrap class="td_form_white" colspan="3" >
				<select class="field_RO" name="notifierCounty" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", qBean.getNotifierCounty())%>
			    </select>
			    <select class="field_RO" name="notifierZipCode">
				   <%=View.getOptionCon1002(qBean.getNotifierZipCode())%>
			    </select>
				<input class="field_RO" type="text" name="notifierAddress" maxlength="100" size="100" value="<%=qBean.getNotifierAddress()%>"/>
			</td>
		</tr>
        <tr>
			<td nowrap class="td_form">職稱</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field", "notifierTitle", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='TITLE' order by codeId", qBean.getNotifierTitle())%>			
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">屬性</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field", "notifierType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", qBean.getNotifierType(),"chgNotifierType(this.value);")%>			
			</td>
		</tr>	
	    <tr>
			<td nowrap class="td_form_left" colspan="4">病人相關資料</td>
		</tr>
		<tr>
			<td nowrap class="td_form">識別代碼</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientId"  size="10" maxlength="10" value="<%=qBean.getPatientId()%>"/>
			</td>
			<td nowrap class="td_form">性別</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field","patientSex","M;男;F;女",qBean.getPatientSex())%>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">出生日期</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","patientBirth",qBean.getPatientBirth())%>
				年齡：<input class="field" type="text" name="patientAge"  size="5" maxlength="3" value="<%=qBean.getPatientAge()%>"/>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">身高</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientHeight" size="5" maxlength="3" value="<%=qBean.getPatientHeight()%>"/>公分
			</td>
			<td nowrap class="td_form">體重</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientWeight" size="5" maxlength="3" value="<%=qBean.getPatientWeight()%>"/>公斤
			</td>				
		</tr>
	</table>
	<!--  療效不等反應    -->
	<table id="Tab2" width="100%" align="center" class="table_form">
		<tr>		
			<td nowrap class="td_form" >通報事件之後果</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="checkbox" name="conSequence" value="1" <%if(!"".equals(Common.get(qBean.getConSequence()))){for(String con:qBean.getConSequence())if("1".equals(con))out.print("checked");}else{out.print("");}%> onclick="chgConSequence();">藥效改變&nbsp;&nbsp;--
				    <%=View.getRadioBoxTextOption("field","effectChangeDesc","1;增強;2;減弱",qBean.getEffectChangeDesc())%><br>
				<input class="field" type="checkbox" name="conSequence" value="2" <%if(!"".equals(Common.get(qBean.getConSequence()))){for(String con:qBean.getConSequence())if("2".equals(con))out.print("checked");}else{out.print("");}%> onclick="chgConSequence();">不良反應發生、強度增強或頻率增加<br>
				    &nbsp;&nbsp;&nbsp;&nbsp;不良反應等級：<%=View.getRadioBoxOption("field", "badReactionLev", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0308 ' order by codeId", qBean.getBadReactionLev())%><br>
				    &nbsp;&nbsp;&nbsp;&nbsp;不良反應狀況：<input class="field" type="text" name="badReactionDesc" size="50" maxlength="50" value="<%=qBean.getBadReactionDesc()%>"/>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報事件之描述</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >事件前</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="beforeDesc" cols="95" rows="3" class="field"><%=qBean.getBeforeDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >藥品轉換</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="changeDesc" cols="95" rows="3" class="field"><%=qBean.getChangeDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >發生事件</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="occurDesc" cols="95" rows="3" class="field"><%=qBean.getOccurDesc()%></textarea>
			</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >事件後</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="afterDesc" cols="95" rows="3" class="field"><%=qBean.getAfterDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他相關檢查及檢驗數據資訊(請附日期)</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關檢查,檢驗數據<br>及其他資料</td>
			<td nowrap class="td_form_white" colspan="3">
               <jsp:include page="comple0201_42Layout.jsp"/>
		    </td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他相關資料(例如：診斷、過敏、懷孕、吸菸、喝酒、習慣、其他疾病、肝/腎功能不全...等)</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >請描述</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="otherDesc" cols="95" rows="3" class="field"><%=qBean.getOtherDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發生事件後之處置</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field", "dealWith", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0301' order by codeId",qBean.getDealWith())%>
				(請描述)：<input class="field" type="text" name="dealWithOther" size="30" maxlength="50" value="<%=qBean.getDealWithOther()%>"/>			
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">病人恢復原藥或轉換同成分藥品<br>其症狀是否改善</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxTextOption("field","isImproveYn","Y;是;N;否;0;未知",qBean.getIsImproveYn())%>		
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">提供聯絡資訊供廠商後續調查評估</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxTextOption("field","isContactYn","Y;是;N;否",qBean.getIsContactYn())%>		
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">醫師對換藥的態度</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "dressingAttitude", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0302' order by codeId",qBean.getDressingAttitude())%>		
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">病人服藥順從性</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "obedienceLev", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0303' order by codeId",qBean.getObedienceLev())%>		
			</td>				
		</tr>
	</table>
	
	<!--  使用藥品    -->
	<table id="Tab3" class="table_form" width="100%" border="0" cellpadding="0" cellspacing="0">
		<%for(CommonCode code : list){%>
			<tr><td class="bg">
				<jsp:include page="comple0201_43Layout.jsp">
					 <jsp:param name="type" value="<%=code.getCodeId()%>" />
				</jsp:include>
			</td></tr>
		<% }%>
	</table>
	
	<!--  其他附件    -->
	<table id="Tab10" width="100%" align="center" class="table_form">		
			<tr><td class="bgToolbar">
				<span id="spanDoUpload">
		          <input class="toolbar_default" type="button" followPK="false" name="doUpload" value="附件上傳" onClick="upload()">&nbsp;
	              </span>
			</td></tr>
		<tr><td class="bg">
			<jsp:include page="conin01Layout.jsp"/>
		</td></tr>
	</table>
	
	<!--  案件初評表    -->
	<table id="Tab4" class="table_form" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr> 
			<td nowrap class="td_form">藥品成分資訊</td>
			<td nowrap class="td_form_white">
			    <input type="hidden" name="drg04id" value="<%=qBean.getDrg04id()%>">
				<input class="field" type="checkbox" name="medNti" value="Y" <%=qBean.getMedNti().equals("Y")?"checked":""%> >NTI Drugs<br>
				藥理治療分類(ATC code)：<input class="field" type="text" name="medAtcCode" size="20" maxlength="50" value="<%=qBean.getMedAtcCode()%>">
			</td>
		</tr>
		<tr>		
			<td nowrap class="td_form" >通報事件之後果</td>
			<td nowrap class="td_form_white">
				<input class="field" type="checkbox" name="conSequence4" value="1" <%if(!"".equals(Common.get(qBean.getConSequence4()))){for(String con:qBean.getConSequence4())if("1".equals(con))out.print("checked");}else{out.print("");}%> onclick="chgConSequence4();showAssess();">藥效改變--
				    <%=View.getRadioBoxTextOption("field","effectChangeDesc4","1;增強;2;減弱",qBean.getEffectChangeDesc4())%><br>
				<input class="field" type="checkbox" name="conSequence4" value="2" <%if(!"".equals(Common.get(qBean.getConSequence4()))){for(String con:qBean.getConSequence4())if("2".equals(con))out.print("checked");}else{out.print("");}%> onClick="chgConSequence4();showAssess();">不良反應發生、強度增強或頻率增加<br>
				不良反應等級：<%=View.getRadioBoxOption("field", "badReactionLev4", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0308 ' order by codeId", qBean.getBadReactionLev4())%><br>
				不良反應狀況：<input class="field" type="text" name="badReactionDesc4" size="50" maxlength="50" value="<%=qBean.getBadReactionDesc4()%>"/><br>
				MedDRA coding：<input class="field" type="text" name="badReactionDra" size="10" maxlength="10" value="<%=qBean.getBadReactionDra()%>"/>	
			</td>	
		</tr>
		<tr>		
			<td nowrap class="td_form">相關性評估</td>
			<td nowrap class="td_form_white">
				<span id="assessShow1" style="display:none">
					轉換藥品與藥效不等發生有合理的時序性?<%=View.getRadioBoxTextOption("field","assessEC1","Y;是;N;否;0;未知",qBean.getAssessEC1())%><br>
					藥效不等與病人本身疾病、生理狀態或併用藥物有關?<%=View.getRadioBoxTextOption("field","assessEC2","Y;是;N;否;0;未知",qBean.getAssessEC2())%><br>
					新藥調整劑量或換回舊藥後維持先前藥效?<%=View.getRadioBoxTextOption("field","assessEC3","Y;是;N;否;0;未知",qBean.getAssessEC3())%><br>
				</span>
				<span id="assessShow2" style="display:none">
					轉換藥品與ADR發生有合理的時序性?<%=View.getRadioBoxTextOption("field","assessBR1","Y;是;N;否;0;未知",qBean.getAssessBR1())%><br>
					ADR與病人本身疾病、生理狀態或併用藥物有關?<%=View.getRadioBoxTextOption("field","assessBR2","Y;是;N;否;0;未知",qBean.getAssessBR2())%><br>
					停藥後ADR減輕或消失?<%=View.getRadioBoxTextOption("field","assessBR3","Y;是;N;否;0;未知",qBean.getAssessBR3())%><br>
				</span>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >療效不等評估結果</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxOption("field", "assessResult", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG2RKL' order by codeId", qBean.getAssessResult())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報時效</td>
			<td nowrap class="td_form_white">
				間隔天數：<input class="field" type="text" name="intervalDays" size="3" value="<%=qBean.getIntervalDays()%>">天&nbsp;&nbsp;&nbsp;&nbsp;
				<%=View.getRadioBoxTextOption("field","notifierAging","1;時效佳;2;時效待加強;",qBean.getNotifierAging())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報品質</td>
			<td nowrap class="td_form_white">
                 <%=View.getRadioBoxTextOption("field", "notifierQuality", "1;Excellent;2;Good;3;Fair;",qBean.getNotifierQuality())%>			
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">提報諮議會</td>
			<td nowrap class="td_form_white">
				<input class="field" type="checkbox" name="isCouncilYn" value="Y" <%=qBean.getIsCouncilYn().equals("Y")?"checked":""%> >
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >歷史通報資料摘要(不含本案)</td>
			<td nowrap class="td_form_white">
				歷年本藥品之通報件數：<%=qBean.getCaseHCount()%>件
				<br>歷年本藥品Possible以上之通報件數：<%=qBean.getCaseHPCount()%>件
				<br>一年內本藥品之通報件數：<%=qBean.getCaseHYCount()%>件
				<br>一年內本藥品Possible以上之通報件數：<%=qBean.getCaseHPYCount()%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >備註</td>
			<td nowrap class="td_form_white">
				<textarea class="field"  name="remark" cols="120" rows="2" ><%=qBean.getRemark()%></textarea>
			</td>
		</tr>
	</table>
