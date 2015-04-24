
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj0102" scope="request" class="com.kangdainfo.tcbw.view.pmed.PMED0101F">
	<jsp:setProperty name="obj0102" property="*"/>
</jsp:useBean>
<% 
obj0102 = (com.kangdainfo.tcbw.view.pmed.PMED0101F) obj0102.doQueryOne();
%>
<script type="text/javascript">
//許可證字號


function permitData1()
{
	var permitKey = form1.permitKey.value;
	var permitNo = form1.permitNo.value;


	var q = "&permitKey="+permitKey;
	    q += "&permitNo="+permitNo;

    if(permitKey=="Z0" || permitKey=="Z5")
    {
    	document.all.item('chProduct').className="field";		
    	document.all.item('enProduct').className="field";
    	document.all.item('medapprovedate').className="field";
    	document.all.item('medEffectiveDate').className="field";
    	document.all.item('applicationName').className="field";
    	document.all.item('manufactorName').className="field";
    	document.all.item('manufactorCountry').className="field";
    	document.all.item('medclass').className="field";
    	document.all.item('medMainCategory').className="field";
    	document.all.item('medSecCategory').className="field";
    	document.all.item('medModel').className="field";
    	document.all.item('medeffect').className="field";

    	document.all.item('chProduct').readOnly = false;
    	document.all.item('enProduct').readOnly = false;
    	document.all.item('medapprovedate').readOnly = false;
    	document.all.item('medEffectiveDate').readOnly = false;
    	document.all.item('applicationName').readOnly = false;
    	document.all.item('manufactorName').readOnly = false;
    	document.all.item('manufactorCountry').readOnly = false;
    	document.all.item('medclass').readOnly = false;
    	document.all.item('medMainCategory').readOnly = false;
    	document.all.item('medSecCategory').readOnly = false;
    	document.all.item('medModel').readOnly = false;
    	document.all.item('medeffect').readOnly = false;
    }   
    else
    {
    	document.all.item('chProduct').className="field_RO";		
    	document.all.item('enProduct').className="field_RO";
    	document.all.item('medapprovedate').className="field_RO";
    	document.all.item('medEffectiveDate').className="field_RO";
    	document.all.item('applicationName').className="field_RO";
    	document.all.item('manufactorName').className="field_RO";
    	document.all.item('manufactorCountry').className="field_RO";
    	document.all.item('medclass').className="field_RO";
    	document.all.item('medMainCategory').className="field_RO";
    	document.all.item('medSecCategory').className="field_RO";
		document.all.item('medModel').className="field_RO";
		document.all.item('medeffect').className="field_RO";

		document.all.item('chProduct').readOnly = true;
		document.all.item('enProduct').readOnly = true;
		document.all.item('medapprovedate').readOnly = true;
		document.all.item('medEffectiveDate').readOnly = true;
		document.all.item('applicationName').readOnly = true;
		document.all.item('manufactorName').readOnly = true;
		document.all.item('manufactorCountry').readOnly = true;
		document.all.item('medclass').readOnly = true;
		document.all.item('medMainCategory').readOnly = true;
		document.all.item('medSecCategory').readOnly = true;
		document.all.item('medModel').readOnly = true;
		document.all.item('medeffect').readOnly = true;
    }    
   
	form1.chProduct.value = "";  //中文品名
	form1.enProduct.value = "";  //英文品名
	form1.medapprovedate.value = "";  //許可證核准日期
	form1.medEffectiveDate.value = "";  //許可證有效日期
	form1.applicationName.value = "";  //許可證持有商
	form1.manufactorName.value = "";  //製造廠
	form1.manufactorCountry.value = "";  //製造廠國別
	form1.medclass.value = "";	//醫材級數
	form1.medMainCategory.value = "";	//醫材主類別代碼
	form1.medMainCategoryCodeName.value = "";	//醫材主類別
	form1.medSecCategory.value = "";	//醫材次類別代碼
	form1.medSecCategoryCodeName.value = "";	//醫材次類別
	form1.medModel.value = "";	//醫材型號
	form1.medeffect.value = "";	//醫材效能
	    
	if(permitKey!="" && permitNo!="")
	{
		if(permitKey!="Z0" && permitKey!="Z5")
		{
			x = getRemoteData("../../ajax/jsonPmedObjects.jsp",q );
		  	if(x!=null && x.length>0)
		  	{
				var json1 = JSON.parse(x);
				form1.chProduct.value = json1.obj0;  //中文品名
				form1.enProduct.value = json1.obj1;  //英文品名		
				form1.medapprovedate.value = json1.obj2;  //許可證核准日期
				form1.medEffectiveDate.value = json1.obj3;  //許可證有效日期
				form1.applicationName.value = json1.obj4;  //許可證持有商
				form1.manufactorName.value = json1.obj5;  //製造廠
				form1.manufactorCountry.value = json1.obj6;  //製造廠國別
				form1.medclass.value = json1.obj7;  //醫材級數
				form1.medMainCategory.value = json1.obj8;  //醫材主類別代碼
				form1.medMainCategoryCodeName.value = json1.obj9;  //醫材主類別
				form1.medSecCategory.value = json1.obj10;	//醫材次類別代碼
				form1.medSecCategoryCodeName.value = json1.obj11;	//醫材次類別
				form1.medModel.value = json1.obj12;		//醫材型號
				form1.medeffect.value = json1.obj13;//醫材效能
				form1.applicationID.value = json1.obj14
		  	}
		  	else
		  	{ 
			  	form1.permitKey.value= "";
			  	form1.permitNo.value= "";
			  	alert("查無此許可證號!");
		  	}
		}
	}
}

function permitDataQ(){
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var permitKey = form1.permitKey.value;
	var permitNo = form1.permitNo.value;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open(getVirtualPath() +"tcbw/medex/medex0107f.jsp?medPermit="+permitKey+"&medPermitNumber="+permitNo,'popWinE',prop);	
}

function permitData2(id){
	var permitKey = id.substring(0,2);
	var permitNo = id.substring(2,8);
	form1.permitKey.value = permitKey;
	form1.permitNo.value = permitNo;
	permitData1();
}

//許可證字號連動欄位屬性
function changeManuClass(type){	
	if(type=="1"){  //解除鎖定
		document.all.item('chProduct').className="field";		
		document.all.item('enProduct').className="field";
		document.all.item('medapprovedate').className="field";
		document.all.item('medEffectiveDate').className="field";
		document.all.item('applicationName').className="field";
		document.all.item('manufactorName').className="field";
		document.all.item('manufactorCountry').className="field";
		document.all.item('medclass').className="field";
		document.all.item('medMainCategory').className="field";
		document.all.item('medSecCategory').className="field";
		document.all.item('medModel').className="field";
		document.all.item('medeffect').className="field";
		document.all.item('permitNo').className="field";

		document.all.item('chProduct').readOnly = false;
		document.all.item('enProduct').readOnly = false;
		document.all.item('medapprovedate').readOnly = false;
		document.all.item('medEffectiveDate').readOnly = false;
		document.all.item('applicationName').readOnly = false;
		document.all.item('manufactorName').readOnly = false;
		document.all.item('manufactorCountry').readOnly = false;
		document.all.item('medclass').readOnly = false;
		document.all.item('medMainCategory').readOnly = false;
		document.all.item('medSecCategory').readOnly = false;
		document.all.item('medModel').readOnly = false;
		document.all.item('medeffect').readOnly = false;
		document.all.item('permitNo').readOnly = false;
		document.all.item('btnQryExpense').style.display="";
	}else if(type=="2"){
		document.all.item('chProduct').className="field_RO";		
		document.all.item('enProduct').className="field_RO";
		document.all.item('medapprovedate').className="field_RO";
		document.all.item('medEffectiveDate').className="field_RO";
		document.all.item('applicationName').className="field_RO";
		document.all.item('manufactorName').className="field_RO";
		document.all.item('manufactorCountry').className="field_RO";
		document.all.item('medclass').className="field_RO";
		document.all.item('medMainCategory').className="field_RO";
		document.all.item('medSecCategory').className="field_RO";
		document.all.item('medModel').className="field_RO";
		document.all.item('medeffect').className="field_RO";
		document.all.item('permitNo').className="field";

		document.all.item('chProduct').readOnly = true;
		document.all.item('enProduct').readOnly = true;
		document.all.item('medapprovedate').readOnly = true;
		document.all.item('medEffectiveDate').readOnly = true;
		document.all.item('applicationName').readOnly = true;
		document.all.item('manufactorName').readOnly = true;
		document.all.item('manufactorCountry').readOnly = true;
		document.all.item('medclass').readOnly = true;
		document.all.item('medMainCategory').readOnly = true;
		document.all.item('medSecCategory').readOnly = true;
		document.all.item('medModel').readOnly = true;
		document.all.item('medeffect').readOnly = true;
		document.all.item('permitNo').readOnly = false;
		document.all.item('btnQryExpense').style.display="";
	}else{
		document.all.item('permitNo').readOnly = true; //查詢狀態
	}	
}
</script>
<!--Form區============================================================-->
	<table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" >案件編號</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="text" name="applNo" value="<%=obj0102.getApplNo()%>">
				<input type="hidden" name="userID" value="<%=User.getUserId()%>">
			    <input type="hidden" name="doType" value="<%=obj0102.getDoType()%>">
			    <input type="hidden" name="applicationID" value="<%=obj0102.getApplicationID()%>"> 
			</td>
			<td nowrap class="td_form" >案件狀態</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="hidden" name="status" value="<%=obj0102.getStatus()%>">
				<input class="field_RO" type="text" name="statusCh" value="<%=obj0102.getStatusCh()%>">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >公告文號</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" name="monitorNo" value="<%=obj0102.getMonitorNo()%>">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">許可證字號</td>
		    <td nowrap class="td_form_white" colspan="3">
		    <select name="permitKey" class="field" onChange="permitData1();">
				<%=View.getOptionCodeKind("MEDPKID", obj0102.getPermitKey())%>
			</select>
			           字第<input class="field" type="text" onChange="permitData1();" name="permitNo" size="10" maxlength="6" value="<%=obj0102.getPermitNo()%>">號
		        <input type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" class="field_Q" >
		    </td>
		</tr>
		<tr>
			<td nowrap class="td_form" >中文品名</td>
			<td nowrap class="td_form_white" >
				<input class="field_RO" type="text" name="chProduct" value="<%=obj0102.getChProduct()%>">
			</td>	
			<td nowrap class="td_form" >英文品名</td>
			<td nowrap class="td_form_white" >
				<input class="field_RO" type="text" name="enProduct" value="<%=obj0102.getEnProduct()%>">
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form" >許可證核准日期</td>
			<td nowrap class="td_form_white"  >
				<%=View.getPopCalendar("field_RO","medapprovedate",obj0102.getMedapprovedate())%>
			</td>
			<td nowrap class="td_form" >許可證有效日期</td>
			<td nowrap class="td_form_white"  >
				<%=View.getPopCalendar("field_RO","medEffectiveDate",obj0102.getMedEffectiveDate())%>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >許可證持有商</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="text" name="applicationName" value="<%=obj0102.getApplicationName()%>">
			</td>
			<td nowrap class="td_form" >製造廠</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="text" name="manufactorName" value="<%=obj0102.getManufactorName()%>">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >製造廠國別</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="text" name="manufactorCountry" value="<%=obj0102.getManufactorCountry()%>">
			</td>
			<td nowrap class="td_form" >醫材級數</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="text" name="medclass" value="<%=obj0102.getMedclass()%>">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">醫材主類別</td>
			<td nowrap class="td_form_white" >
			    <%=View.getPopCode("field","medMainCategoryCode","",Common.get(View.getOneCodeName("MEDMCT",Common.get(obj0102.getMedMainCategory()))),"","MEDMCT","medMainCategory",obj0102.getMedMainCategory())%>
			</td>
			<td nowrap class="td_form">醫材次類別</td>
			<td nowrap class="td_form_white" >
			     <%=View.getPopCode("field_Q","medSecCategoryCode","",Common.get(View.getOneCodeName("MEDSCT",Common.get(obj0102.getMedSecCategory()))),"medMainCategory","MEDSCT","medSecCategory",obj0102.getMedSecCategory())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >醫材型號</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="text" name="medModel" value="<%=obj0102.getMedModel()%>">
			</td>
			<td nowrap class="td_form" >醫材效能</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="text" name="medeffect" value="<%=obj0102.getMedeffect()%>">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >監控期間起迄</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getPopCalendar("field_","monitorSDate",obj0102.getMonitorSDate())%>~
				<%=View.getPopCalendar("field","monitorEDate",obj0102.getMonitorEDate())%>
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form" >報告繳交期數</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="text" name="reportIssuenum" value="<%=obj0102.getReportIssuenum()%>">
			</td>
			<td nowrap class="td_form" >各期間隔(月)</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="text" name="intervalmonth" value="<%=obj0102.getIntervalmonth()%>">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >監控備註</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" name="monitorremark" value="<%=obj0102.getMonitorremark()%>">
			</td>		
		</tr>
		<tr>
		       <td nowrap class="td_form">附件(監控附件)</td>
		       <td nowrap class="td_form_white" colspan="3">  
		         <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	             <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%">檔案名稱</th>
		          <th class="listTH" width="50%">檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		             <input class="toolbar_default" type="button" followPK="false" name="doUpload1" value="附件上傳" onClick="upload('MED050001')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj0102.getAddFileMed0402()%>
			   </tbody>
               </table>    
		       </td>
		   </tr>
	</table>

