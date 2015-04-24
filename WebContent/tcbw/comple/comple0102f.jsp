<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.comple.COMPLE0101F qBean = (com.kangdainfo.tcbw.view.comple.COMPLE0101F) request.getAttribute("qBean");
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
			<td nowrap class="td_form" width="15%">發現日期</td>
			<td nowrap class="td_form_white" width="35%">
                <%=View.getPopCalendar("field","occurDate",qBean.getOccurDate())%>
			</td>
			<td nowrap class="td_form" width="15%">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white"  width="35%">
                <%=View.getPopCalendar("field","notifierRepDate",qBean.getNotifierRepDate())%>(由通報中心填寫)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報者獲知日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field","notifierRevDate",qBean.getNotifierRevDate())%>
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
			<td nowrap class="td_form_left" colspan="4">通報者資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">姓名</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="hidden" name="notifierUserID" maxlength="50" size="15" value="<%=qBean.getNotifierUserID()%>">
				<input class="field_RO" type="text" name="notifierName" maxlength="10" size="15" value="<%=qBean.getNotifierName()%>" />
				<%if("in".equals(User.getInORout())) {%>
					<input class="field" type="button" name="btn_User" value="查詢通報者資料" title="通報者資訊輸入輔助視窗" onclick="popUserForm()"/>
					<input class="field" type="button" value="清除通報者" onClick="clearUser();">
				<% } %>
			</td>
			<td nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white">				
				<input class="field" type="hidden" name="notifierDeptID" size="10" maxlength="10" value="<%=qBean.getNotifierDeptID()%>" />
	  	        <input class="field_RO" type="text" name="notifierDept" size="30" maxlength="6"  value="<%=qBean.getNotifierDept()%>" />
			    <input type="button" name="btnQryNotifier" onClick="popNotifierDept(notifierType);" value="查詢" width="120px" class="field" >
			</td>			
		</tr>		
		<tr>
			<td nowrap class="td_form">電話</td>
			<td nowrap class="td_form_white">				
		        ( <input class="field" type="text" name="notifierTelArea" size="2" maxlength="2" value="<%=qBean.getNotifierTelArea()%>"> )
			    - <input class="field" type="text" name="notifierTel" size="20" maxlength="20" value="<%=qBean.getNotifierTel()%>">
			    # <input class="field" type="text" name="notifierTelExt" size="3" maxlength="3" value="<%=qBean.getNotifierTelExt()%>">
			</td>
			<td nowrap class="td_form">E-Mail</td>
			<td nowrap class="td_form_white" >
				<input class="field" type="text" name="notifierEmail" maxlength="30" size="20" value="<%=qBean.getNotifierEmail()%>"/>
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form">地址</td>
			<td nowrap class="td_form_white" colspan="3" >
				<select class="field" name="notifierCounty" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", qBean.getNotifierCounty())%>
			    </select>
			    <select name="notifierZipCode" class="field">
				   <%=View.getOptionCon1002(qBean.getNotifierZipCode())%>
			    </select>
			    <input class="field" type="text" name="notifierAddress" size="50" maxlength="80" value="<%=qBean.getNotifierAddress()%>">
			</td>
		</tr>
        <tr>
			<td nowrap class="td_form">職稱</td>
			<td nowrap class="td_form_white">				
				<select class="field" name="notifierTitle" type="select">
				      <%=View.getOptionCodeKind("TITLE", qBean.getNotifierTitle()) %>
			    </select>
			</td>
			<td nowrap class="td_form">屬性</td>
			<td nowrap class="td_form_white" id="clearDept">
				<%=View.getRadioBoxOption("field", "notifierType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", qBean.getNotifierType())%>			
			</td>
		</tr>
	</table>
	<!-- 不良事件 -->	
	<table id="Tab2" width="100%" align="center" class="table_form">
        <tr>
			<td nowrap class="td_form_left" colspan="4">不良藥品資訊</td>
		</tr>
		<tr> 
			<td nowrap class="td_form" >許可證字號</td>
			<td nowrap class="td_form_white" colspan="3">					
			<select class="field" name="permitKey" class="field" onChange="permitData1();">
				<%=View.getOptionCodeKind("DRGPKID", qBean.getPermitKey())%>
			</select>			 
			    <input class="field" type="text" name="permitNo" size="6" maxlength="6" value="<%=qBean.getPermitNo()%>" onChange="permitData1();"/>號
			    <input class="toolbar_default" type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" >
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">藥品商品名</td>
			<td nowrap class="td_form_white" colspan="3">
			      中文品名 <input class="field" type="text" name="chProduct" size="100" maxlength="100"  value="<%=qBean.getChProduct()%>"/><br>
			      英文品名 <input class="field" type="text" name="enProduct" size="100" maxlength="200"  value="<%=qBean.checkGet(qBean.getEnProduct())%>"/>
            </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">有效成分名稱</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="ingredient" size="50" maxlength="50" value="<%=qBean.getIngredient()%>"/></td>
			<td nowrap class="td_form">單位含量</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="content" size="20" maxlength="50" value="<%=qBean.getContent()%>"/></td>
		</tr>
		<tr>
			<td nowrap class="td_form">劑型</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "medModel", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGFLN' and isStop='N' order by codeId", qBean.getMedModel(),null,3)%>			
			    (請描述)：<input class="field" type="text" name="medModelOther" size="40" maxlength="50" value="<%=qBean.getMedModelOther()%>"/>
		    </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">包裝形式</td>
			<td nowrap colspan="3" class="td_form_white" >
                <%=View.getRadioBoxOption("field", "medPackage", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0102' and isStop='N' order by codeId", qBean.getMedPackage())%>			
			    (請描述)：<input class="field" type="text" name="medPackageOther" size="40" maxlength="50" value="<%=qBean.getMedPackageOther()%>"/>	
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">藥商</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="applicationName" size="40" maxlength="50" value="<%=qBean.getApplicationName()%>"/>
			  <input class="field" type="hidden" name="applicationID" size="10" maxlength="50" value="<%=qBean.getApplicationID()%>"/>
			</td>
			<td nowrap class="td_form">製造商</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="manufactorName" size="40" maxlength="50" value="<%=qBean.getManufactorName()%>"/>
			  <input class="field" type="hidden" name="manufactorCountry" size="40" maxlength="50" value="<%=qBean.getManufactorCountry()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造批號</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="manufactorNo" size="15" maxlength="11" value="<%=qBean.getManufactorNo()%>"/></td>
			<td nowrap class="td_form">製造日期</td>
			<td nowrap class="td_form_white" >
			  <%=View.getPopCalendar("field","manufactorDate",qBean.getManufactorDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">保存期限</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","expirationDate",qBean.getExpirationDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">儲存環境</td>
			<td nowrap colspan="3" class="td_form_white" >
                <%=View.getRadioBoxOption("field", "storage", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0103' and isStop='N' order by codeId", qBean.getStorage())%>			
			    (請描述)：<input class="field" type="text" name="storageOther" size="40" maxlength="50" value="<%=qBean.getStorageOther()%>"/>				
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">本次通報事件是否為單一個案</td>
			<td nowrap class="td_form_white" >
			<input class="field" type="radio" name="isSingleYn" value="Y" <%if("Y".equals(qBean.getIsSingleYn())) out.print("checked"); %>>是<br>
			<input class="field" type="radio" name="isSingleYn" value="N" <%if("N".equals(qBean.getIsSingleYn())) out.print("checked"); %>>否，			    			
				同批號產品共<input class="field" type="text" name="sameNum" size="5" maxlength="5" value="<%=qBean.getSameNum()%>"/>件&nbsp;&nbsp;
				不同批號產品共<input class="field" type="text" name="diffNum" size="5" maxlength="5" value="<%=qBean.getDiffNum()%>"/>件
			</td>
			<td nowrap class="td_form">是否一經拆封即發現本不良品缺陷</td>
			<td nowrap class="td_form_white" >
			    <%=View.getRadioBoxTextOption("field","isFindYn","N;否;Y;是;",qBean.getIsFindYn())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否為病人使用後發現不良品<br>，向醫療人員反應</td>
			<td nowrap class="td_form_white">
			    <%=View.getRadioBoxTextOption("field","isUsedYn","N;否;Y;是;",qBean.getIsUsedYn())%>
			</td>
			<td nowrap class="td_form">是否已對人體健康產生危害</td>
			<td nowrap class="td_form_white">
			    <%=View.getRadioBoxTextOption("field","isHarmYn","N;否;Y;是;",qBean.getIsHarmYn())%>，並請同時作藥品不良反應通報。
			</td>
		</tr>
		<tr>		
			<td nowrap class="td_form_left" colspan="4">不良品後續處理情形(請詳加填寫，通報中心將以此為依據)</td>
		</tr>
		<tr>
			<td nowrap class="td_form">已連絡廠商</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getRadioBoxTextOption("field","evenContactYn","N;否;Y;是;",qBean.getEvenContactYn())%>
			</td>
		</tr>
		<tr>
			<td class="td_form">不良品後續處理</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "dealWith", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0104' and isStop='N' order by codeId", qBean.getDealWith())%>			
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">提供聯絡資訊供廠商<br>後續調查評估</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getRadioBoxTextOption("field","isContactYn","N;否;Y;是;",qBean.getIsContactYn())%>
			    (僅提供機構名稱及住址)
			</td>
		</tr>
		<tr>
			<td nowrap colspan="4" class="td_form_left">不良品缺陷之描述</td>
		</tr>		
		<tr>
		    <td nowrap class="td_form_white" colspan="4">
		       <%=qBean.getCheckBoxOption2("field", "mainCode", "subCode", "otherDescribe", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' and isStop='N' order by codeId", qBean.getMainCode(),qBean.getSubCode(),qBean.getId())%>
	        </td>
	    </tr>
	    <tr>
			<td nowrap class="td_form">不良品缺陷描述說明</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="defectDesc" cols="100" rows="6"><%=qBean.getDefectDesc()%></textarea>
			</td>	
		</tr>
	    <tr>
			<td nowrap class="td_form">不良品原因初評</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getRadioBoxOption("field", "firstResult", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0106' order by codeId", qBean.getFirstResult())%>			
			    (請描述)：<input class="field" type="text" name="firstRemark" size="40" maxlength="50" value="<%=qBean.getFirstRemark()%>"/>	
			</td>
		</tr>
	</table>
	<!-- 其他附件 -->
	<table id="Tab10" width="100%" align="center" class="table_form">
	      <thead id="listTHEAD">
	      	  <tr>
	          <td>
	              <span id="spanDoUpload">
		          <input class="toolbar_default" type="button" followPK="false" name="doUpload" value="附件上傳" onClick="upload(1)">&nbsp;
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
			   <%=qBean.getAddFile("1")%>
			</tbody>
	</table>
	<!-- 案件分級 -->
	<table id="Tab3" width="100%" align="center" class="table_form">
	    <tr>
		    <td nowrap class="td_form_left" colspan="4">通報訊息	         
	             <input type="hidden" name="drg03id" value="<%=qBean.getDrg03id()%>">
	        </td>
	    </tr>
		<tr>
			<td nowrap class="td_form" >評估日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field","assessDate03",qBean.getAssessDate03())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >不良品風險評估結果</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				<%=View.getRadioBoxOption("field", "firstResult03", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRKL' order by codeShortName", qBean.getFirstResult03())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報時效</td>
			<td nowrap class="td_form_white" width="80%">
				間隔天數：<input type="text" name="intervalDays03" size="3" value="<%=qBean.getIntervalDays03()%>">天&nbsp;&nbsp;&nbsp;&nbsp;
				<%=View.getRadioBoxTextOption("field","notifierAging03","1;時效佳;2;時效待加強;",qBean.getNotifierAging03())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報品質</td>
			<td nowrap class="td_form_white" width="80%">
                 <%=View.getRadioBoxTextOption("field", "notifierQuality03", "1;Excellent;2;Good;3;Fair;",qBean.getNotifierQuality03())%>			
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >歷史通報資料摘要</td>
			<td nowrap class="td_form_white" width="80%">
				歷年本藥品之通報件數：<input class="field_RO" type="text" size="3"  value="<%=qBean.getHisData1()%>">件
				<br>歷年本藥品同此次瑕疵之通報件數：<input class="field_RO"  type="text" size="3" value="<%=qBean.getHisData2()%>">件
				<br>一年內本藥品之通報件數：<input class="field_RO" type="text" size="3" value="<%=qBean.getHisData3()%>">件
				<br>一年內本藥品同此次瑕疵之通報件數：<input class="field_RO" type="text" size="3" value="<%=qBean.getHisData4()%>">件
				<br>一年內本藥品同此次瑕疵之高風險通報件數：<input class="field_RO" type="text" size="3" value="<%=qBean.getHisData5()%>">件
				<br>一年內本藥品同此次瑕疵案件：<%=qBean.getHisData6()%>
				<br>一年內本藥品同此次瑕疵之高風險案件：<%=qBean.getHisData7()%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >備註</td>
			<td nowrap class="td_form_white" width="80%">
				<textarea class="field"  name="remark03" cols="120" rows="2" ><%=qBean.getRemark03()%></textarea>
			</td>
		</tr>
   </table>
   <!-- 分級確認 -->
   <table id="Tab4" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" >案號</td>
			<td nowrap class="td_form_white">
			    <input type="hidden" name="drg04id" value="<%=qBean.getDrg04id()%>">
                <input class="field_RO" type="text" name="applNo" size="10" value="<%=qBean.getApplNo()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >分級確認日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field","gradeDate04",qBean.getGradeDate04())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >不良品風險評估結果</td>
			<td nowrap class="td_form_white"  width="75%">
				 <%=View.getRadioBoxOption("field", "drgLev04", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRKL' order by codeShortName", qBean.getDrgLev04())%>
			</td>
		</tr>		
   </table>
