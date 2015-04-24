<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0301F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<% 
obj = (com.kangdainfo.tcbw.view.sdrg.SDRG0301F) obj.doQueryOne0301();
%>  
<script type="text/javascript">
//實際回收清單資訊
var Drg8005DbCount = 0;
function addDrg8005Db(id,applotNo,appreservesNum,appreservesUnit,appsellnum,appsellunit,appprerecyclenum,appprerecycleunit,appktotalNum)
{
	Drg8005DbCount += 1;		
	var tbl = document.getElementById("attDrg8005DbView");
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);	
	//回收批號
	var applotNoItem = row.insertCell(0);
	applotNoItem.innerHTML="<tr id=\"drg8005DbfileType"+Drg8005DbCount+"\">";
	applotNoItem.innerHTML+="<td class='td_form_add'>";
	applotNoItem.innerHTML+="<input type='hidden' name='drg8005DbType' value='"+Drg8005DbCount+"'>";
	applotNoItem.innerHTML+="<input type='hidden' name='drg8005DbTypeId' value=\""+(id!=null?id:'')+"\">";	
	applotNoItem.innerHTML+="<input id ='applotNo"+Drg8005DbCount+"' class=\"field\" type=\"text\" name=\"applotNo"+"\" size=\"15\"  maxlength=\"15\"   value=\""+(applotNo!=null?applotNo:'')+"\">&nbsp;";
	applotNoItem.innerHTML+="</td>";  
	//庫存量
	var appreservesNumItem = row.insertCell(1);
	appreservesNumItem.innerHTML="<td class='td_form_add'>";
	appreservesNumItem.innerHTML+="<input id ='appreservesNum"+Drg8005DbCount+"' class=\"field\" type=\"text\" name=\"appreservesNum"+"\" size=\"5\"  maxlength=\"5\"   value=\""+(appreservesNum!=null?appreservesNum:'')+"\" onchange=\"checkDetailNumValue(this,3,"+Drg8005DbCount+");\" \">&nbsp;";
	appreservesNumItem.innerHTML+="</td>";
    //庫存單位
    var appreservesUnitItem = row.insertCell(2);
    appreservesUnitItem.innerHTML="<td class='td_form_add'>";
    appreservesUnitItem.innerHTML+="<select id='appreservesUnit"+Drg8005DbCount+"' class=\"field\" name= \"appreservesUnit"+"\" style=\"font-size:10pt\">"+"<%=View.getOptionCodeKind("DRGRECUNIT","")%>"+'</select>';
    appreservesUnitItem.innerHTML+="</td>";  
    //銷售數量
    var appsellnumItem = row.insertCell(3);
    appsellnumItem.innerHTML ="<td class='td_form_add'>";
    appsellnumItem.innerHTML+="<input id ='appsellnum"+Drg8005DbCount+"' class=\"field\" type=\"text\" name=\"appsellnum"+"\" size=\"5\"  maxlength=\"5\" value=\""+(appsellnum!=null?appsellnum:'')+"\" onchange=\"checkDetailNumValue(this,4,"+Drg8005DbCount+");\" \">&nbsp;";
    appsellnumItem.innerHTML+="</td>";  
    //銷售數量單位
    var appsellunitItem = row.insertCell(4);
    appsellunitItem.innerHTML="<td class='td_form_add'>";
    appsellunitItem.innerHTML+='<select name=appsellunit id=appsellunit'+Drg8005DbCount+' class=field style="font-size:10pt">'+"<%=View.getOptionCodeKind("DRGRECUNIT","")%>"+'</select>';	
    appsellunitItem.innerHTML+="</td>";  
    //回收數量
    var appprerecyclenumItem = row.insertCell(5);
    appprerecyclenumItem.innerHTML="<td class='td_form_add'>";
    appprerecyclenumItem.innerHTML+="<input id ='appprerecyclenum"+Drg8005DbCount+"' class=\"field\" type=\"text\" name=\"appprerecyclenum"+"\" size=\"5\"  maxlength=\"5\" value=\""+(appprerecyclenum!=null?appprerecyclenum:'')+"\" onchange=\"checkDetailNumValue(this,5,"+Drg8005DbCount+");\" \">&nbsp;";
    appprerecyclenumItem.innerHTML+="</td>";  
    //回收數量單位
    var appprerecycleunitItem = row.insertCell(6);
    appprerecycleunitItem.innerHTML="<td class='td_form_add'>";
    appprerecycleunitItem.innerHTML+='<select name=appprerecycleunit id=appprerecycleunit'+Drg8005DbCount+' class=field style="font-size:10pt">'+"<%=View.getOptionCodeKind("DRGRECUNIT","")%>"+'</select>';	
    appprerecycleunitItem.innerHTML+="</td>";
	//廠商回收-總計
	var appktotalNumItem = row.insertCell(7);
	appktotalNumItem.innerHTML="<td class='td_form_add'>";
	appktotalNumItem.innerHTML+="<input id ='appktotalNum"+Drg8005DbCount+"' class=\"field_RO\" type=\"text\" name=\"appktotalNum"+"\" size=\"5\"  maxlength=\"5\" value=\""+(appktotalNum!=null?appktotalNum:'')+"\" readOnly=true >&nbsp;";
	appktotalNumItem.innerHTML+="</td>";
	//button
	var buttonItem = row.insertCell(8);
	buttonItem.innerHTML="<td class='td_form_add'>";
	buttonItem.innerHTML+="<input type=\"button\" class=\"field_btnAdd\" value=''  onClick=\"copyDrg8005Row("+Drg8005DbCount+")\" >&nbsp;";
	buttonItem.innerHTML+="<input type=\"button\" class=\"field_btnRemove\" value=''  onclick=\"removeRowFromTable('attDrg8005DbView',this.parentNode.parentNode.rowIndex-1);\" >&nbsp;";
	buttonItem.innerHTML+="</td>";
	buttonItem.innerHTML+="</tr>";

	document.all.item('appreservesUnit'+Drg8005DbCount).value = (appreservesUnit!=null?appreservesUnit:'');
	document.all.item('appsellunit'+Drg8005DbCount).value = (appsellunit!=null?appsellunit:'');
	document.all.item('appprerecycleunit'+Drg8005DbCount).value = (appprerecycleunit!=null?appprerecycleunit:'');
}

//實際回收清單資訊複製新增
function copyDrg8005Row(rid) {
	var arrFd = new Array(8);
	var arrDrg8005FieldName = new Array("applotNo","appreservesNum","appreservesUnit","appsellnum","appsellunit","appprerecyclenum","appprerecycleunit","appktotalNum");
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrDrg8005FieldName[i] + rid))){
			arrFd[i] = document.all.item(arrDrg8005FieldName[i] + rid).value;
		}
	}
	addDrg8005Db(rid,arrFd[0],arrFd[1],arrFd[2],arrFd[3],arrFd[4],arrFd[5],arrFd[6],arrFd[7]);	
}

//回收清單資訊-數量檢核/加總
function checkDetailNumValue(obj,intType,rid) {
	if (intType==1) {
		checkNumeric(obj,'庫存量',true,true);
	} else if(intType==2){
		checkNumeric(obj,'銷售數量',true,true);
	} else if(intType==3){
		var str = checkNumeric(obj,'庫存量',true,false);
		if(str.length>0){
			alert(str);
			obj.value = "";
		}else{
			sumAppkTotalNum(rid);
		}
	} else if(intType==4){
		checkNumeric(obj,'銷售數量',true,true);
	} else if(intType==5){
		var str = checkNumeric(obj,'回收數量',true,false);
		if(str.length>0){
			alert(str);
			obj.value = "";
		}else{
			sumAppkTotalNum(rid);
		}
	} 
}

//總數量 = 庫存量+回收數量
function sumAppkTotalNum(rid){
	var appreservesNum = document.all.item("appreservesNum"+rid).value;           //庫存量
	var appprerecyclenum = document.all.item("appprerecyclenum"+rid).value;       //回收數量
	var total = getNumeric(appreservesNum)+getNumeric(appprerecyclenum);          //總數量
	document.all.item("appktotalNum"+rid).value = total;	
}

//實際回收清單檢核
function validateDrg8005Table() {
	var Drg8005Array = document.getElementsByName("drg8005DbType");
	var arrDrg8005FieldName = new Array("applotNo","appreservesNum","appreservesUnit","appsellnum","appsellunit","appprerecyclenum","appprerecycleunit");
	var arrDrg8005FieldZhName = new Array("回收批號","庫存量","庫存單位","銷售數量","銷售數量單位","回收數量","回收數量單位");
	
	if(Drg8005Array!=null && Drg8005Array.length>0){		
		var sb = new StringBuffer();
		var appreservesUnitYn = new Boolean(false);
		var appsellunitYn = new Boolean(false);
		var appprerecycleunitYn = new Boolean(false);
		var allUnitYn = new Boolean(false);
		for (var i=0; i<Drg8005Array.length; i++)		
		{
			var id = Drg8005Array[i].value;
			
			//檢核單位需一致
			if(i==0){				
				var appreservesUnit = document.all.item(arrDrg8005FieldName[2]+id).value;
				var appsellunit = document.all.item(arrDrg8005FieldName[4]+id).value;
				var appprerecycleunit = document.all.item(arrDrg8005FieldName[6]+id).value;
			}
			if(appreservesUnit != document.all.item(arrDrg8005FieldName[2]+id).value) appreservesUnitYn=true;
			if(appsellunit != document.all.item(arrDrg8005FieldName[4]+id).value) appsellunitYn=true;
			if(appprerecycleunit != document.all.item(arrDrg8005FieldName[6]+id).value) appprerecycleunitYn=true;
			
			//檢核必填欄位
			for (var j=0; j<arrDrg8005FieldZhName.length; j++)			
			{			
				var fieldName = document.all.item(arrDrg8005FieldName[j]+id);		
						
				if (isObj(fieldName) && arrDrg8005FieldZhName[j]!='')			
				{				
					sb.append(checkEmpty(fieldName,arrDrg8005FieldZhName[j]));			
				}
				if(j==2){
					var appreservesUnit1 = document.all.item(arrDrg8005FieldName[j]+id).value;
					var appsellunit1 = document.all.item(arrDrg8005FieldName[j+2]+id).value;
					var appprerecycleunit = document.all.item(arrDrg8005FieldName[j+4]+id).value;
					if(appreservesUnit1 != appsellunit1) allUnitYn=true;
					else if (appreservesUnit1 != appprerecycleunit) allUnitYn=true;
					else if (appsellunit1 != appprerecycleunit ) allUnitYn=true;
				}
			}			
		}
		if(appreservesUnitYn==true) sb.append("庫存單位須一致！\n");
		if(appsellunitYn==true) sb.append("銷售數量單位須一致！\n");
		if(appprerecycleunitYn==true) sb.append("回收數量單位須一致！\n");
		if(allUnitYn==true) sb.append("銷售/庫存/回收單位一致！\n");
		if (sb.toString().length>0) return sb.toString();
	}else {
		return "實際回收清單輸入至少一筆！\n";
	}	
	return "";
}
</script>
    <table id="Tab3_1" width="100%" align="center" class="table_form">                  
        <tr>
			<td nowrap class="td_form_left" colspan="4">廠商回收</td>
			<td><input type="hidden" name="status2" value="<%=obj.getStatus2()%>"></td>			
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">完成回收日期</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getPopCalendar("field","appRecDate",obj.getAppRecDate())%>            
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">回收作業人員</td>
			<td nowrap class="td_form_white" colspan="3">
			   <input class="field" type="text" name="appRecMan" size="30" maxlength="50" value="<%=obj.getAppRecMan()%>">              
			</td>
		</tr>
		<tr>		
			<td nowrap class="td_form">實際回收清單</td>		
			<td nowrap class="td_form_white" colspan="3">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="15%">回收批號</th>
		         <th class="listTH" width="10%">庫存量</th>
		         <th class="listTH" width="10%">庫存量單位</th>
		         <th class="listTH" width="10%">銷售數量</th>
		         <th class="listTH" width="10%">銷售數量單位</th>
		         <th class="listTH" width="15%">回收數量</th>
		         <th class="listTH" width="10%">回收數量單位</th>
		         <th class="listTH" width="15%">總計(庫存+回收)</th>
		         <th class="listTH" width="5%">		            
	                  <input type="button" class="field_btnAdd" onclick="addDrg8005Db();" />
	             </th>
	           </tr>
	           </thead>
	           <tbody id="attDrg8005DbView">			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
		   <td nowrap class="td_form">附件(回收清單)</td>
		   <td nowrap class="td_form_white" colspan="3">  
		       <table width="100%" align="center" class="table_form">
	           <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%" >檔案名稱</th>
		          <th class="listTH" width="50%" >檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		               <input class="toolbar_default" type="button" followPK="false" name="doUpload5" value="附件上傳" onClick="upload('DRG040005')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj.getAddFileDrg0405()%>
			   </tbody>
	           </table>
		   </td>
		</tr>
		<tr>
			<td nowrap class="td_form">回收品及庫存品<br>處置方式</td>
			<td nowrap class="td_form_white"  colspan="3">
			   <%=View.getCommonCodeRadioBoxOption("field", "apprecyclestorage", "DRGRECCS", obj.getApprecyclestorage(), null, "", "") %> 
			   <input class="field" type="text" name="apprecyclestoragedesc" size="50" maxlength="100" value="<%=obj.getApprecyclestoragedesc()%>">                           
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">預計處置日期</td>
			<td nowrap class="td_form_white"  colspan="3">
			   <%=View.getPopCalendar("field","appprepunishdate",obj.getAppprepunishdate())%>               
			</td>
		</tr>
		<tr>
		   <td nowrap class="td_form">附件(回收報告書)</td>
		   <td nowrap class="td_form_white" colspan="3">  
		       <table width="100%" align="center" class="table_form">
	           <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%" >檔案名稱</th>
		          <th class="listTH" width="50%" >檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		               <input class="toolbar_default" type="button" followPK="false" name="doUpload6" value="附件上傳" onClick="upload('DRG040006')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj.getAddFileDrg0406()%>
			   </tbody>
	           </table>
		   </td>
		</tr>
	 </table>
	 
	 <table id="Tab3_2" width="100%" align="center" class="table_form">    
		<tr>
			<td nowrap class="td_form_left" colspan="4">廠商回覆</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">回覆日期</td>
			<td nowrap class="td_form_white"  colspan="3">
			   <%=View.getPopCalendar("field","appreplydate",obj.getAppreplydate())%>             
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">學名藥/原廠藥</td>
			<td nowrap class="td_form_white">
			   <%=View.getCommonCodeRadioBoxOption("field", "appmedicineType", "DRG0101", obj.getAppmedicineType(), null, "", "") %>             
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">國產/輸入</td>
			<td nowrap class="td_form_white"  colspan="3">
			   <%=View.getCommonCodeRadioBoxOption("field", "appproduceType", "DRGSRTYPE", obj.getAppproduceType(), null, "", "") %>                 
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">批號範圍</td>
			<td nowrap class="td_form_white"  colspan="3">
			   <%=View.getCommonCodeRadioBoxOption("field", "applotType", "DRG0107", obj.getApplotType(), null, "", "") %>               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >回收原因</td>
			<td nowrap class="td_form_white"  colspan="3"> 
			   <%=View.getCommonCodeRadioBoxOption("field", "apprecyclereason", "DRGRECRS", obj.getApprecyclereason(), null, "", "") %> 
			   <input class="field" type="text" name="apprecyclersdesc" size="50" maxlength="100" value="<%=obj.getApprecyclersdesc()%>">                
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >調查結果</td>
			<td nowrap class="td_form_white"  colspan="3">  
			   <%=View.getCommonCodeKindBoxOption("field", "appsurvey", "DRG0109", obj.getAppsurvey()) %>  
			   <input class="field" type="text" name="appsurveyOther" size="50" maxlength="100" value="<%=obj.getAppsurveyOther()%>">            
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >預防措施</td>
			<td nowrap class="td_form_white"  colspan="3">  
			   <%=View.getCommonCodeKindBoxOption("field", "appprecaution", "DRG0110", obj.getAppprecaution()) %> 
			   <input class="field" type="text" name="appprecautionOther" size="50" maxlength="100" value="<%=obj.getAppprecautionOther()%>">            
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >清查結果</td>
			<td nowrap class="td_form_white"  colspan="3"> 
			   <textarea class="field" name="appsurveyresult" cols="65" rows="5"><%=obj.getAppsurveyresult()%></textarea>              
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >調查報告</td>
			<td nowrap class="td_form_white"  colspan="3">
			   <textarea class="field" name="appsurveyreport" cols="65" rows="5"><%=obj.getAppsurveyreport() %></textarea>              
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >預防矯正措施及<br>改善時程</td>
			<td nowrap class="td_form_white"  colspan="3"> 
			   <textarea class="field" name="appprecautiontime" cols="65" rows="5"><%=obj.getAppprecautiontime() %></textarea>              
			</td>
		</tr>
		<tr>
		   <td nowrap class="td_form">附件(調查報告)</td>
		   <td nowrap class="td_form_white" colspan="3">  
		       <table width="100%" align="center" class="table_form">
	           <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%" >檔案名稱</th>
		          <th class="listTH" width="50%" >檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		               <input class="toolbar_default" type="button" followPK="false" name="doUpload7" value="附件上傳" onClick="upload('DRG040007')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj.getAddFileDrg0407()%>
			   </tbody>
	           </table>
		   </td>
		</tr>		
		<tr>
			<td nowrap class="td_form">查廠日期</td>
			<td nowrap class="td_form_white"  colspan="3">
			  <%=View.getPopCalendar("field","appcheckmanudate",obj.getAppcheckmanudate())%>             
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >查廠查核結果</td>
			<td nowrap class="td_form_white"  colspan="3">  
			   <textarea class="field" name="appcheckmanuresult" cols="65" rows="5"><%=obj.getAppcheckmanuresult()%></textarea>             
			</td>
		</tr>
		<tr>
		   <td nowrap class="td_form">附件(查廠報告書)</td>
		   <td nowrap class="td_form_white" colspan="3">  
		       <table width="100%" align="center" class="table_form">
	           <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%" >檔案名稱</th>
		          <th class="listTH" width="50%" >檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		               <input class="toolbar_default" type="button" followPK="false" name="doUpload8" value="附件上傳" onClick="upload('DRG040008')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj.getAddFileDrg0408()%>
			   </tbody>
	           </table>
		   </td>
		</tr>	
	</table>