<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0112F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
objList = (java.util.ArrayList) obj.doQueryAllDrg0112();
%>

<table id="Tab5" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" >案號</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="applNo" size="10" value="<%=obj.getApplNo()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >批號</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="lotNo05" size="10" value="<%=obj.getLotNo05()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >產品製造</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field_RO", "beforeOrLater05", "1;前次CAPA執行前製造;2;前次CAPA執行後製造;", obj.getBeforeOrLater05())%>
			</td>
		</tr>
		<%if("04".equals(obj.getDrgLev())){%>
		<tr>
			<td nowrap class="td_form" >高頻率案件資訊</td>
			<td nowrap class="td_form_white" colspan="3">
				一年內本藥品同此次瑕疵案件之同批號的各案件編號：<input class="field_RO" type="text" size="100"  value="<%=obj.getHisApplNoY()%>"><br>
				一年內本藥品同此次瑕疵案件之不同批號的各案件編號：<input class="field_RO"  type="text" size="100" value="<%=obj.getHisApplNoN()%>">
			</td>		
		</tr>
		<%}%>
		<tr>
			<td nowrap class="td_form">製造日期</td>
			<td nowrap class="td_form_white">
                  <%=View.getPopCalendar("field_RO","capaDate05",obj.getCapaDate05())%>
			</td>
		</tr>	
</table>
<table id="Tab6" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">藥品不良品基本資料</td>
		</tr>
		<tr>
			<td nowrap class="td_form">藥品名</td>
			<td nowrap class="td_form_white" colspan="3">
			      中文品名 <input class="field_RO" type="text" name="chProduct" size="100" maxlength="100"  value="<%=obj.getChProduct()%>"/><br>
			      英文品名 <input class="field_RO" type="text" name="enProduct" size="100" maxlength="200"  value="<%=obj.checkGet(obj.getEnProduct())%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">許可證字號</td>
			<td nowrap class="td_form_white">
			<select class="field_RO" name="permitKey" >
				<%=View.getOptionCodeKind("DRGPKID", obj.getPermitKey())%>
			</select>			 
			    <input class="field_RO" type="text" name="permitNo" size="6" maxlength="6" value="<%=obj.getPermitNo()%>" />號			    
			</td>					
			<td nowrap class="td_form">批號</td>
			<td nowrap colspan="3" class="td_form_white">
                <input class="field_RO" type="text" name="manufactorNo" size="15" maxlength="11" value="<%=obj.getManufactorNo()%>"/>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">主成分</td>
			<td nowrap class="td_form_white"  colspan="3">
                <input class="field_RO" type="text" name="ingredient" size="50" maxlength="50" value="<%=obj.getIngredient()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">劑型</td>
			<td nowrap class="td_form_white"  colspan="3">
                <%=View.getRadioBoxOption("field_RO", "medModel", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGFLN' order by codeId", obj.getMedModel())%>			
			    (請描述)：<input class="field_RO" type="text" name="medModelOther" size="40" maxlength="50" value="<%=obj.getMedModelOther()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">申請商/藥商</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="applicationName" size="40" maxlength="50" value="<%=obj.getApplicationName()%>"/>
			</td>
			<td nowrap class="td_form">製造廠</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="manufactorName" size="40" maxlength="50" value="<%=obj.getManufactorName()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">廠商案件分析</td>
		</tr>
		<tr>
			<td nowrap class="td_form">學名藥/原廠藥</td>
			<td nowrap class="td_form_white" colspan="3">
			  <%=View.getRadioBoxOption("field_RO", "medicineType06", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0101' order by codeId", obj.getMedicineType06())%>		
		    </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">國產/輸入</td>
			<td nowrap class="td_form_white" colspan="3">
               <%=View.getRadioBoxTextOption("field_RO", "produceType06", "1;國產;2;進口;", obj.getProduceType06())%>		
			 </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">批號範圍</td>
			<td nowrap class="td_form_white" colspan="3">
               <%=View.getRadioBoxOption("field_RO", "lotType06", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0107' order by codeId", obj.getLotType06())%>		
		    </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">廠商不良品缺陷</td>
			<td nowrap class="td_form_white" colspan="3">
		       <%=View.getRadioBoxOption("field_RO", "defect06", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0108' order by codeId", obj.getDefect06())%>
		       (請描述)：<input class="field_RO" type="text" name="defectOther06" size="40" maxlength="50" value="<%=obj.getDefectOther06()%>"/>		
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">廠商調查結果</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field_RO", "survey06", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0109' order by codeId", obj.getSurvey06())%>
		       (請描述)：<input class="field_RO" type="text" name="surveyOther06" size="40" maxlength="50" value="<%=obj.getSurveyOther06()%>"/>		
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">廠商預防措施</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field_RO", "precaution06", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0110' order by codeId", obj.getPrecaution06())%>
		       (請描述)：<input class="field_RO" type="text" name="precautionOther06" size="40" maxlength="50" value="<%=obj.getPrecautionOther06()%>"/>		
			</td>		
		</tr>
</table>
<table id="Tab7" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">藥品不良品基本資料
			   <input type="hidden" name="drg07id" value="<%=obj.getDrg07id()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">通報編號</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="applNo" size="10" value="<%=obj.getApplNo()%>">
			</td>
			<td nowrap class="td_form" >許可證字號</td>
			<td nowrap class="td_form_white">
			    <select class="field" name="permitKey" class="field_RO">
				   <%=View.getOptionCodeKind("DRGPKID", obj.getPermitKey())%>
			    </select>			 
			    <input class="field_RO" type="text" name="permitNo" size="6" maxlength="6" value="<%=obj.getPermitNo()%>" />號			    
			</td>		
		</tr>
		<%if("04".equals(obj.getDrgLev())){%>
		<tr>
			<td nowrap class="td_form" >高頻率案件資訊</td>
			<td nowrap class="td_form_white" colspan="3">
				一年內本藥品同此次瑕疵案件之同批號的各案件編號：<input class="field_RO" type="text" size="100"  value="<%=obj.getHisApplNoY()%>"><br>
				一年內本藥品同此次瑕疵案件之不同批號的各案件編號：<input class="field_RO"  type="text" size="100" value="<%=obj.getHisApplNoN()%>">
			</td>		
		</tr>
		<%}%>
		<tr>
			<td nowrap class="td_form" >藥品名</td>
			<td nowrap class="td_form_white" colspan="3">
			      中文品名 <input class="field_RO" type="text" name="chProduct" size="100" maxlength="100"  value="<%=obj.getChProduct()%>"/><br>
			      英文品名 <input class="field_RO" type="text" name="enProduct" size="100" maxlength="200"  value="<%=obj.checkGet(obj.getEnProduct())%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >主成分</td>
			<td nowrap class="td_form_white" colspan="3">
                <input class="field_RO" type="text" name="ingredient" size="50" maxlength="50" value="<%=obj.getIngredient()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >劑型</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field_RO", "medModel", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGFLN' order by codeId", obj.getMedModel())%>			
			    (請描述)：<input class="field_RO" type="text" name="medModelOther" size="40" maxlength="50" value="<%=obj.getMedModelOther()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >申請商/藥商</td>
			<td nowrap class="td_form_white" >
                <input class="field_RO" type="text" name="applicationName" size="40" maxlength="50" value="<%=obj.getApplicationName()%>"/>
			</td>
			<td nowrap class="td_form" >製造廠</td>
			<td nowrap class="td_form_white" >
                <input class="field_RO" type="text" name="manufactorName" size="40" maxlength="50" value="<%=obj.getManufactorName()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >批號</td>
			<td nowrap class="td_form_white" colspan="3">
                <input class="field_RO" type="text" name="manufactorNo" size="15" maxlength="11" value="<%=obj.getManufactorNo()%>"/>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >不良品缺陷之描述</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=obj.getCheckBoxOption2("field_RO", "mainCode", "subCode", "otherDescribe", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId", obj.getMainCode(),obj.getSubCode(),obj.getId())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >歷年類似不良品通報情形</td>
			<td nowrap class="td_form_white" colspan="3">
				歷年本藥品之通報件數：<input class="field_RO" type="text" size="3"  value="<%=obj.getHisData1()%>">件
				<br>歷年本藥品同此次瑕疵之通報件數：<input class="field_RO"  type="text" size="3" value="<%=obj.getHisData2()%>">件
				<br>一年內本藥品之通報件數：<input class="field_RO" type="text" size="3" value="<%=obj.getHisData3()%>">件
				<br>一年內本藥品同此次瑕疵之通報件數：<input class="field_RO" type="text" size="3" value="<%=obj.getHisData4()%>">件
				<br>一年內本藥品同此次瑕疵之高風險通報件數：<input class="field_RO" type="text" size="3" value="<%=obj.getHisData5()%>">件
				<br>一年內本藥品同此次瑕疵案件：<%=obj.getHisData6()%>
				<br>一年內本藥品同此次瑕疵之高風險案件：<%=obj.getHisData7()%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >風險等級</td>
			<td nowrap class="td_form_white">
                <%=View.getRadioBoxOption("field_RO", "drgLev07", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRKL' order by codeShortName", obj.getDrgLev())%>
			</td>
			<td nowrap class="td_form" >本案原由</td>
			<td nowrap class="td_form_white">
               <input type="radio" name="caseReason07" checked>通報系統&nbsp;
			   <input type="radio" name="caseReason07">廠商主動通報&nbsp;
			   <input type="radio" name="caseReason07">警訊監控&nbsp;
			   <input type="radio" name="caseReason07">品質監測
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">清查結果</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field_RO" name="checkResult07" cols="80" rows="6"><%=obj.getCheckResult07()%></textarea>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">調查報告</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field_RO" name="survey07" cols="80" rows="6"><%=obj.getSurvey07()%></textarea>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">預防矯正措施及改善時程</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field_RO" name="precaution07" cols="80" rows="6"><%=obj.getPrecaution07()%></textarea>
			</td>		
		</tr>
<!-- List 區   -->	
<tr><td nowrap class="bgPagging" colspan="4">		
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>


<tr>
<td nowrap class="bgList" colspan="4">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">評估日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">清查結果</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">調查結果</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">預防矯正措施及改善時程</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center"};
	    out.write(View.getQuerylist(primaryArray,displayArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>

<table id="Tab8" width="100%" align="center" class="table_form">     	
		<tr>
			<td nowrap class="td_form" width="10%">是否發文</td>
			<td nowrap class="td_form_white">
                <%=View.getRadioBoxTextOption("field","isPostYn08","N;否;Y;是;",obj.getIsPostYn08())%>
			</td>
			<td nowrap class="td_form" width="10%">不發文理由</td>
			<td nowrap class="td_form_white">
                <input class="field" type="text" name="reason08" size="40" maxlength="50" value="<%=obj.getReason08()%>"/>	
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="10%">FDA發文字號</td>
			<td nowrap class="td_form_white">
                <input class="field" type="text" name="fdaPostNo08" size="10" maxlength="20" value="<%=obj.getFdaPostNo08()%>"/>
			</td>
			<td nowrap class="td_form" width="10%">廠商交付CAPA日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field","payDate08",obj.getPayDate08())%>
			</td>
		</tr>    
    <%if(Common.getInt(obj.getStatus()) >= 41 || Common.getInt(obj.getStatus()) < 40){ %>		
		<tr>
			<td nowrap class="td_form" width="10%">展延日期</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getPopCalendar("field","delayDate08",obj.getDelayDate08())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="10%">補件日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field_RO","addDocDate08",obj.getAddDocDate08())%>
			</td>
			<td nowrap class="td_form" width="10%">再評估日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field_RO","reAssessDate08",obj.getReAssessDate08())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="10%">CAPA執行完成日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field","capaDownDate08",obj.getCapaDownDate08())%>
			</td>
			<td nowrap class="td_form" width="10%">再評估人員</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="reAssessMan08" size="10" maxlength="20" value="<%=obj.getReAssessMan08()%>"/>
			</td>
		</tr>
		<tr>
		    <td nowrap class="td_form" width="10%">風險等級</td>
			<td nowrap class="td_form_white" colspan="3">
               <input type="radio" name="drgLev08" value="B1" <%if("B1".equals(obj.getDrgLev08())) out.print("checked"); %>>高通報(同批號n≧3)&nbsp;
			   <input type="radio" name="drgLev08" value="B2" <%if("B2".equals(obj.getDrgLev08())) out.print("checked"); %>>高通報(不同批號n≧5)&nbsp;
			   <input type="radio" name="drgLev08" value="A" <%if("A".equals(obj.getDrgLev08())) out.print("checked"); %>>高風險
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">本案原由</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="drgReason08" cols="100"  rows="6"><%=obj.getDrgReason08()%></textarea>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">廠商回覆說明</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" style="text-align:center;" colspan="2">廠商輸入內容</td>
			<td nowrap class="td_form_white" style="text-align:center;" colspan="2">TFDA輸入內容</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="10%">清查結果</td>
			<td nowrap class="td_form_white">
				<textarea class="field_RO" name="checkResult07" cols="65" rows="9"><%=obj.getCheckResult07()%></textarea>
			</td>
			<td nowrap class="td_form" width="10%">清查結果</td>
			<td nowrap class="td_form_white">
				<textarea class="field" name="checkResult08" cols="65" rows="9"><%=obj.getCheckResult08()%></textarea>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form" width="10%">調查報告</td>
			<td nowrap class="td_form_white">
				<textarea class="field_RO" name="survey07" cols="65" rows="9"><%=obj.getSurvey07()%></textarea>
			</td>
			<td nowrap class="td_form" width="10%">調查報告</td>
			<td nowrap class="td_form_white">
				<textarea class="field" name="survey08" cols="65" rows="9"><%=obj.getSurvey08()%></textarea>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form" width="10%">預防矯正措施<br>及改善時程</td>
			<td nowrap class="td_form_white">
				<textarea class="field_RO" name="precaution07" cols="65" rows="9"><%=obj.getPrecaution07()%></textarea>
			</td>
			<td nowrap class="td_form" width="10%">預防矯正措施<br>及改善時程</td>
			<td nowrap class="td_form_white">
				<textarea class="field" name="precaution08" cols="65" rows="9"><%=obj.getPrecaution08()%></textarea>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" width="10%">擬辦事項</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getCommonCodeKindBoxOption("field", "dealWith08", "DRG0111", obj.getDealWith08())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" width="10%">評估結果</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="assessResult08" cols="100" rows="6"><%=obj.getAssessResult08()%></textarea>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form" width="10%">相關附件</td>
			<td nowrap class="td_form_white" colspan="3">
			<table width="100%">
              <thead id="listTHEAD">	          
	          <tr>
	          <td>
	              <span id="spanDoUpload">
		          <input class="toolbar_default" type="button" followPK="false" name="doUpload2" value="附件上傳" onClick="upload(2)">&nbsp;
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
			    <%=obj.getAddFile("2")%>
			</tbody>
			</table>
			</td>
		</tr>
	<%}%>
</table>