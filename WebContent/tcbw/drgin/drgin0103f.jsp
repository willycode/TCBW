<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0102F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
$( document ).ready(function() 
{
	if($("#notifier").attr("checked"))
	{
		notifierOpen();
	}
});
</script>
		<table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報訊息</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">登錄編號</td>
			<td nowrap class="td_form_white" colspan="3">
                <input class="field_RO" type="text" name="applNo" value="<%=obj.getApplNo()%>">(由通報中心填寫)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">發現日期</td>
			<td nowrap class="td_form_white" width="35%">
                <%=View.getPopCalendar("field_RO","occurDate",obj.getOccurDate())%>
			</td>
			<td nowrap class="td_form" width="15%">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white"  width="35%">
                <%=View.getPopCalendar("field_RO","notifierRepDate",obj.getNotifierRepDate())%>(由通報中心填寫)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報者獲知日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field_RO","notifierRevDate",obj.getNotifierRevDate())%>
			</td>
			<td nowrap class="td_form" >收案日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field_RO","enrolledDate",obj.getEnrolledDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報來源</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getRadioBoxOption("field_RO", "notifierSource", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGNFS' order by codeId", obj.getNotifierSource())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報者資訊&nbsp;&nbsp;&nbsp;&nbsp;
			    <input class="field_Q" id="notifier" type="checkBox" name="notifier" value="Y" <%if("Y".equals(obj.getNotifier())) out.write("checked"); %> onclick="showNotifierData();"/>顯示個人資料
			    <input class="field" type="hidden" name="notifierDeptID" size="10" maxlength="10"  />
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">姓名</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="notifierName" maxlength="10" size="15" />
			</td>
			<td nowrap class="td_form" width="15%">服務機構</td>
			<td nowrap class="td_form_white">
	  	        <input class="field_RO" type="text" name="notifierDept" size="30" maxlength="6"   />			    
			</td>			
		</tr>		
		<tr>
			<td nowrap class="td_form">電話</td>
			<td nowrap class="td_form_white">
				( <input class="field_RO" type="text" name="notifierTelArea" size="2" maxlength="2" > )
			    - <input class="field_RO" type="text" name="notifierTel" size="20" maxlength="20" >
			    # <input class="field_RO" type="text" name="notifierTelExt" size="3" maxlength="3" >
			</td>
			<td nowrap class="td_form">E-Mail</td>
			<td nowrap class="td_form_white" >
				<input class="field_RO" type="text" name="notifierEmail" maxlength="30" size="20" />
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form">地址</td>			    
			<td nowrap class="td_form_white" colspan="3" >
			    <select class="field_RO" name="notifierCounty" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", obj.getNotifierCounty())%>
			    </select>
			    <select name="notifierZipCode" class="field_RO">
				   <%=View.getOptionCon1002(obj.getNotifierZipCode())%>
			    </select>
			    <input class="field_RO" type="text" name="notifierAddress" size="50" maxlength="80" >
			</td>
		</tr>
        <tr>
			<td nowrap class="td_form">職稱</td>
			<td nowrap class="td_form_white">
				<select class="field_RO" name="notifierTitle">
				      <%=View.getOptionCodeKind("TITLE", obj.getNotifierTitle()) %>
			    </select>
			</td>
			<td nowrap class="td_form">屬性</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxOption("field_RO", "notifierType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", obj.getNotifierType())%>			
			</td>
		</tr>		
	</table>	
	<table id="Tab2" width="100%" align="center" class="table_form">
        <tr>
			<td nowrap class="td_form_left" colspan="4">不良藥品資訊</td>
		</tr>
		<tr> 
			<td nowrap class="td_form" >許可證字號</td>
			<td nowrap class="td_form_white" colspan="3">					
			<select class="field" name="permitKey" class="field" onChange="permitData1();">
				<%=View.getOptionCodeKind("DRGPKID", obj.getPermitKey())%>
			</select>			 
			    <input class="field" type="text" name="permitNo" size="6" maxlength="6" value="<%=obj.getPermitNo()%>" onChange="permitData1();"/>號
			    <input class="toolbar_default" type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" >
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">藥品商品名</td>
			<td nowrap class="td_form_white" colspan="3">
			      中文品名 <input class="field" type="text" name="chProduct" size="100" maxlength="100"  value="<%=obj.getChProduct()%>"/><br>
			      英文品名 <input class="field" type="text" name="enProduct" size="100" maxlength="200"  value="<%=obj.getEnProduct()%>"/>
            </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">有效成分名稱</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="ingredient" size="50" maxlength="50" value="<%=obj.getIngredient()%>"/></td>
			<td nowrap class="td_form">單位含量</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="content" size="20" maxlength="50" value="<%=obj.getContent()%>"/></td>
		</tr>
		<tr>
			<td nowrap class="td_form">劑型</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "medModel", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGFLN' and isStop='N' order by codeId", obj.getMedModel(),null,3)%>			
			    (請描述)：<input class="field" type="text" name="medModelOther" size="40" maxlength="50" value="<%=obj.getMedModelOther()%>"/>
		    </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">包裝形式</td>
			<td nowrap colspan="3" class="td_form_white" >
                <%=View.getRadioBoxOption("field", "medPackage", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0102' and isStop='N' order by codeId", obj.getMedPackage())%>			
			    (請描述)：<input class="field" type="text" name="medPackageOther" size="40" maxlength="50" value="<%=obj.getMedPackageOther()%>"/>	
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">藥商</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="applicationName" size="40" maxlength="50" value="<%=obj.getApplicationName()%>"/>
			  <input class="field" type="hidden" name="applicationID" size="10" maxlength="50" value="<%=obj.getApplicationID()%>"/>
			</td>
			<td nowrap class="td_form">製造商</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="manufactorName" size="40" maxlength="50" value="<%=obj.getManufactorName()%>"/>
			  <input class="field" type="hidden" name="manufactorCountry" size="40" maxlength="50" value="<%=obj.getManufactorCountry()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造批號</td>
			<td nowrap class="td_form_white" >
			  <input class="field" type="text" name="manufactorNo" size="15" maxlength="11" value="<%=obj.getManufactorNo()%>"/></td>
			<td nowrap class="td_form">製造日期</td>
			<td nowrap class="td_form_white" >
			   <input class="field" type="text" name="manufactorDate" size="15"  maxlength="20" value="<%=obj.getManufactorDate()%>"/>			  
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">保存期限</td>
			<td nowrap class="td_form_white" colspan="3">
			    <input class="field" type="text" name="expirationDate" size="15" maxlength="20" value="<%=obj.getExpirationDate()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">儲存環境</td>
			<td nowrap colspan="3" class="td_form_white" >
                <%=View.getRadioBoxOption("field", "storage", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0103' and isStop='N' order by codeId", obj.getStorage())%>			
			    (請描述)：<input class="field" type="text" name="storageOther" size="40" maxlength="50" value="<%=obj.getStorageOther()%>"/>				
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">本次通報事件是否為單一個案</td>
			<td nowrap class="td_form_white" >
			<input class="field" type="radio" name="isSingleYn" value="Y" <%if("Y".equals(obj.getIsSingleYn())) out.print("checked"); %>>是<br>
			<input class="field" type="radio" name="isSingleYn" value="N" <%if("N".equals(obj.getIsSingleYn())) out.print("checked"); %>>否，			    			
				同批號產品共<input class="field" type="text" name="sameNum" size="5" maxlength="5" value="<%=obj.getSameNum()%>"/>件&nbsp;&nbsp;
				不同批號產品共<input class="field" type="text" name="diffNum" size="5" maxlength="5" value="<%=obj.getDiffNum()%>"/>件
			</td>
			<td nowrap class="td_form">是否一經拆封即發現本不良品缺陷</td>
			<td nowrap class="td_form_white" >
			    <%=View.getRadioBoxTextOption("field","isFindYn","N;否;Y;是;",obj.getIsFindYn())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否為病人使用後發現不良品<br>，向醫療人員反應</td>
			<td nowrap class="td_form_white">
			    <%=View.getRadioBoxTextOption("field","isUsedYn","N;否;Y;是;",obj.getIsUsedYn())%>
			</td>
			<td nowrap class="td_form">是否已對人體健康產生危害</td>
			<td nowrap class="td_form_white">
			    <%=View.getRadioBoxTextOption("field","isHarmYn","N;否;Y;是;",obj.getIsHarmYn())%>，並請同時作藥品不良反應通報。
			</td>
		</tr>
		<tr>		
			<td nowrap class="td_form_left" colspan="4">不良品後續處理情形(請詳加填寫，通報中心將以此為依據)</td>
		</tr>
		<tr>
			<td nowrap class="td_form">已連絡廠商</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getRadioBoxTextOption("field","evenContactYn","N;否;Y;是;",obj.getEvenContactYn())%>
			</td>
		</tr>
		<tr>
			<td class="td_form">不良品後續處理</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "dealWith", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0104' and isStop='N' order by codeId", obj.getDealWith())%>			
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">提供聯絡資訊供廠商<br>後續調查評估</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getRadioBoxTextOption("field","isContactYn","N;否;Y;是;",obj.getIsContactYn())%>
			    (僅提供機構名稱及住址)
			</td>
		</tr>
		<tr>
			<td nowrap colspan="4" class="td_form_left">不良品缺陷之描述</td>
		</tr>		
		<tr>
		    <td nowrap class="td_form_white" colspan="4">
		       <%=obj.getCheckBoxOption2("field", "mainCode", "subCode", "otherDescribe", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' and isStop='N' order by codeId", obj.getMainCode(),obj.getSubCode(),obj.getId())%>
	        </td>
	    </tr>
	    <tr>
			<td nowrap class="td_form">不良品缺陷描述說明</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="defectDesc" cols="100" rows="6"><%=obj.getDefectDesc()%></textarea>
			</td>	
		</tr>
	    <tr>
			<td nowrap class="td_form">不良品原因初評</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getRadioBoxOption("field", "firstResult", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0106' order by codeId", obj.getFirstResult())%>			
			    (請描述)：<input class="field" type="text" name="firstRemark" size="40" maxlength="50" value="<%=obj.getFirstRemark()%>"/>	
			</td>
		</tr>
	</table>
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
			   <%=obj.getAddFile("1")%>
			</tbody>
	</table>

