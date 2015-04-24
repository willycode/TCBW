<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<% 
obj = (com.kangdainfo.tcbw.view.sdrg.SDRG0101F) obj.doQueryOne();
%>
<script type="text/javascript">
//回收清單資訊
var Drg8002DbCount = 0;
function addDrg8002Db(id,lotNo,reservesNum,reservesUnit,sellnum,sellunit)
{
	Drg8002DbCount += 1;		
	var tbl = document.getElementById("attDrg8002DbView");
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);	
	
	//回收批號
	var lotNoItem = row.insertCell(0);
	lotNoItem.innerHTML="<tr id=\"Drg8002DbfileType"+Drg8002DbCount+"\">";
	lotNoItem.innerHTML+="<td class='td_form_add'>";
	lotNoItem.innerHTML+="<input type='hidden' name='drg8002DbType' value='"+Drg8002DbCount+"'>";
	lotNoItem.innerHTML+="<input type='hidden' name='drg8002DbTypeId' value=\""+(id!=null?id:'')+"\">";	
	lotNoItem.innerHTML+="<input id ='lotNo"+Drg8002DbCount+"' class=\"field\" type=\"text\" name=\"lotNo\" size=\"15\"  maxlength=\"15\"   value=\""+(lotNo!=null?lotNo:'')+"\">";
	lotNoItem.innerHTML+="</td>";
    //庫存量
    var reservesNumItem = row.insertCell(1);
    reservesNumItem.innerHTML ="<td class='td_form_add'>";
    reservesNumItem.innerHTML +="<input id ='reservesNum"+Drg8002DbCount+"' class=\"field\" type=\"text\" name=\"reservesNum\" size=\"5\"  maxlength=\"5\"   value=\""+(reservesNum!=null?reservesNum:'')+"\" onchange=\"checkDetailNumValue(this,1,"+Drg8002DbCount+");\" >";
    reservesNumItem.innerHTML +="</td>";
    
    //庫存單位
    var reservesUnitItem = row.insertCell(2);
    reservesUnitItem.innerHTML ="<td class='td_form_add'>";
    reservesUnitItem.innerHTML +="<select id='reservesUnit"+Drg8002DbCount+"' class=\"field\" name= \"reservesUnit\" style=\"font-size:10pt\">"+"<%=View.getOptionCodeKind("DRGRECUNIT","")%>"+"</select>";
	reservesUnitItem.innerHTML +="</td>";
    //銷售數量
    var sellnumItem = row.insertCell(3);
    sellnumItem.innerHTML="<td class='td_form_add'>";
    sellnumItem.innerHTML+="<input id ='sellnum"+Drg8002DbCount+"' class=\"field\" type=\"text\" name=\"sellnum\" size=\"5\"  maxlength=\"5\" value=\""+(sellnum!=null?sellnum:'')+"\" onchange=\"checkDetailNumValue(this,2,"+Drg8002DbCount+");\" >";
    sellnumItem.innerHTML+="</td>";    
    //銷售數量單位
    var sellunitItem = row.insertCell(4);
    sellunitItem.innerHTML="<td class='td_form_add'>";
    sellunitItem.innerHTML+="<select name=sellunit id=sellunit"+Drg8002DbCount+" class=field style=\"font-size:10pt\">"+"<%=View.getOptionCodeKind("DRGRECUNIT","")%>"+"</select>";	
	sellunitItem.innerHTML+="</td>";
	//button
	var buttonItem = row.insertCell(5);
	buttonItem.innerHTML="<td class='td_form_add'>";
	buttonItem.innerHTML+="<input type=\"button\" class=\"field_btnAdd\" value=''  onClick=\"copyDrg8002Row("+Drg8002DbCount+")\" >";
	buttonItem.innerHTML+="<input type=\"button\" class=\"field_btnRemove\" value=''  onclick=\"removeRowFromTable('attDrg8002DbView',this.parentNode.parentNode.rowIndex-1);\" >";
	buttonItem.innerHTML+="</td>";
	buttonItem.innerHTML+="</tr>";
	
	document.getElementById('reservesUnit'+Drg8002DbCount).value = (reservesUnit!=null?reservesUnit:'');
	document.getElementById('sellunit'+Drg8002DbCount).value = (sellunit!=null?sellunit:'');
}

//回收清單複製新增
function copyDrg8002Row(rid) {
	var arrFd = new Array(5);
	var arrDrg8002FieldName = new Array("lotNo","reservesNum","reservesUnit","sellnum","sellunit");
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrDrg8002FieldName[i] + rid))){
			arrFd[i] = document.all.item(arrDrg8002FieldName[i] + rid).value;
		}
	}
	addDrg8002Db(rid,arrFd[0],arrFd[1],arrFd[2],arrFd[3],arrFd[4]);	
}

//回收清單檢核
function validateDrg8002Table() {
	var Drg8002Array = document.getElementsByName("drg8002DbType");
	var arrDrg8002FieldName = new Array("lotNo","reservesNum","reservesUnit","sellnum","sellunit");
	var arrDrg8002FieldZhName = new Array("回收批號","庫存量","庫存單位","銷售數量","銷售數量單位");
	if(Drg8002Array!=null && Drg8002Array.length>0){		
		var sb = new StringBuffer();
		var reservesUnitYn = new Boolean(false);
		var sellunitYn = new Boolean(false);
		for (var i=0; i<Drg8002Array.length; i++)		
		{
			var id = Drg8002Array[i].value;
			
			//檢核單位需一致
			if(i==0){				
				var reservesUnit = document.all.item(arrDrg8002FieldName[2]+id).value;
				var sellunit = document.all.item(arrDrg8002FieldName[4]+id).value;
			}
			if(reservesUnit != document.all.item(arrDrg8002FieldName[2]+id).value) reservesUnitYn=true;
			if(sellunit != document.all.item(arrDrg8002FieldName[4]+id).value) sellunitYn=true;
			
			//檢核必填欄位
			for (var j=0; j<arrDrg8002FieldZhName.length; j++)			
			{			
				var fieldName = document.all.item(arrDrg8002FieldName[j]+id);		
				//var fieldName = document.getElementsByName(arrDrg8002FieldName[j]+id);			
				if (isObj(fieldName) && arrDrg8002FieldZhName[j]!='')			
				{				
					sb.append(checkEmpty(fieldName,arrDrg8002FieldZhName[j]));			
				}		
			}			
		}
		if(reservesUnitYn==true) sb.append("庫存單位須一致！\n");
		if(sellunitYn==true) sb.append("銷售數量單位須一致！\n");
		if (sb.toString().length>0) return sb.toString();
	}else {
		return "回收清單輸入至少一筆！\n";
	}	
	return "";
}

//許可證字號
function permitData1(type){
	var permitKey = form1.permitKey.value;
	var permitNo = form1.permitNo.value;
	if("Z0"==permitKey || "Z5"==permitKey){	
		changeManuClass(1);
		if(type!="init"){
			form1.permitNo.value="";     //許可證號
			form1.chProduct.value = "";  //中文品名
			form1.enProduct.value = "";  //英文品名		
			form1.appName.value = "";  //許可證持有商
			form1.manufactorName.value = "";  //製造廠
			form1.appID.value = "";  //許可證持有商統編
			form1.manufactorCountry.value = "";  //製造廠國別
			form1.ingredient.value = "";  //主成分
			form1.appAddr.value = "";  //許可證持有商地址
			form1.manufactorAddr.value = "";  //製造廠地址
		}		
	}else{
		changeManuClass(2);		
		var q = "&permitKey="+permitKey;
            q += "&permitNo="+permitNo;
	    x = getRemoteData("../../ajax/jsonDrgObjects.jsp", q );
	    if(x!=null && x.length>0){
			var json1 = JSON.parse(x);
			form1.chProduct.value = json1.obj0;  //中文品名
			form1.enProduct.value = json1.obj1;  //英文品名		
			form1.appName.value = json1.obj2;  //許可證持有商
			form1.manufactorName.value = json1.obj3;  //製造廠
			form1.appID.value = json1.obj4;  //許可證持有商統編
			form1.manufactorCountry.value = json1.obj5;  //製造廠國別
			var ingredient = json1.obj6;
			if(ingredient.length > 50) ingredient = ingredient.substr(0,50);
			form1.ingredient.value = ingredient;  //主成分
			form1.appAddr.value = json1.obj7;  //許可證持有商地址
			form1.manufactorAddr.value = json1.obj8;  //製造廠地址
		}else{
			if(type!="init"){
				form1.chProduct.value = "";  //中文品名
				form1.enProduct.value = "";  //英文品名		
				form1.appName.value = "";  //許可證持有商
				form1.manufactorName.value = "";  //製造廠
				form1.appID.value = "";  //許可證持有商統編
				form1.manufactorCountry.value = "";  //製造廠國別
				form1.ingredient.value = "";  //主成分
				form1.appAddr.value = "";  //許可證持有商地址
				form1.manufactorAddr.value = "";  //製造廠地址
			}
		}	
	}
	if(type=="init") changeManuClass(3);
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
	popWinName = window.open(getVirtualPath() +"tcbw/drgex/drgex0104f.jsp?permitKey="+permitKey+"&permitNo="+permitNo,'popWinE',prop);		
}

function permitData2(id){
	var permitKey = id.substring(0,2);
	var permitNo = id.substring(2,8);
	form1.permitKey.value = permitKey;
	form1.permitNo.value = permitNo;
	permitData1();
}

//地址區號
function setValue(place,zipcode)
{
	var obj1 = zipcode;
	var oldDeptValue = zipcode.value;
	obj1.options.length=0;
	obj1.options.add(new Option("請選擇",""));	
  var q = "&city="+place.value;
	var x = getRemoteData(getVirtualPath() + "/ajax/jsonZipCode.jsp",q);	
	if (x!=null && x.length>0)	{
		var json = eval('(' + x + ')'); 
		var i = 0;
		for (i=0; i<json.length; i++) 
		{
			if(json[i].zipcode==null)
				continue;
			var astId =  json[i].zipcode;			
			var oOption = new Option(json[i].zipname,astId);
			obj1.options.add(oOption);
	    	if(astId == oldDeptValue) oOption.selected=true;			
		}
		obj1.disabled = false;
	}
}

//許可證字號連動欄位屬性
function changeManuClass(type){	
	if(type=="1"){  //解除鎖定
		document.getElementById('chProduct').className="field";		
		document.getElementById('enProduct').className="field";
		document.getElementById('ingredient').className="field";
		document.getElementById('manufactorName').className="field";
		document.getElementById('manufactorCountry').className="field";
		document.getElementById('manufactorAddr').className="field";
		document.getElementById('permitNo').className="field_RO";
		document.getElementById('chProduct').readOnly = false;
		document.getElementById('enProduct').readOnly = false;
		document.getElementById('ingredient').readOnly = false;
		document.getElementById('manufactorName').readOnly = false;
		document.getElementById('manufactorCountry').readOnly = false;
		document.getElementById('manufactorAddr').readOnly = false;
		document.getElementById('permitNo').readOnly = true;
		document.getElementById('appButton').style.display="";
	}else if(type=="2"){
		document.getElementById('chProduct').className="field_RO";
		document.getElementById('enProduct').className="field_RO";
		document.getElementById('ingredient').className="field_RO";
		document.getElementById('manufactorName').className="field_RO";
		document.getElementById('manufactorCountry').className="field_RO";
		document.getElementById('manufactorAddr').className="field_RO";
		document.getElementById('permitNo').className="field";
		document.getElementById('chProduct').readOnly = true;
		document.getElementById('enProduct').readOnly = true;
		document.getElementById('ingredient').readOnly = true;
		document.getElementById('manufactorName').readOnly = true;
		document.getElementById('manufactorCountry').readOnly = true;
		document.getElementById('manufactorAddr').readOnly = true;
		document.getElementById('permitNo').readOnly = false;
		document.getElementById('appButton').style.display="none";
	}else{
		document.getElementById('permitNo').readOnly = true; //查詢狀態
	}	
}

//許可證持有商選擇
function popAppData(){	
	var prop="";
	var windowTop=120;
	var windowLeft=120;
	var v = "02";
	var p = "SDRG0101";
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open(getVirtualPath() +"/home/popUserJob.jsp?v="+v+"&p="+p,'popWinE',prop);
}

//回收清單資訊-數量檢核
function checkDetailNumValue(obj,intType,rid) {
	if (intType==1) {
		checkNumeric(obj,'庫存量',true,true);
	} else if(intType==2){
		checkNumeric(obj,'銷售數量',true,true);
	} 
}

function popReportForm()
{
	var params = 'width=800,height=600,resizable=1,menubar=no,scrollbars=yes';
	if (popWinName!=null)  popWinName.close();	
	popWinName = window.open("sdrg0102f_abroadCountry.jsp?"+"&abroadCountry="+form1.abroadCountry.value,'popWinName', params);		
}

function clearSelect(obj)
{
	document.all.item(obj).value="";
	document.all.item(obj+"Name").value="";
}
</script>
    <table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="20%">案件編號</td>
			<td nowrap class="td_form_white" width="30%">
			   <input class="field_RO" type="text" name="applNo" size="20" maxlength="20" value="<%=obj.getApplNo()%>"> 
			   <input type="hidden" name="userID" value="<%=User.getUserId()%>">
			   <input type="hidden" name="id" value="<%=obj.getId()%>"> 
			   <input type="hidden" name="doType" value="<%=obj.getDoType()%>">
			   <input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">     
			</td>
			<td nowrap class="td_form" width="10%">案件狀態：</td>
			<td nowrap class="td_form_white">
			   <select class="field_RO" name="status" >
				   <%=View.getOptionCodeKind("DRGEVSTATUS", obj.getStatus()) %>
			   </select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >中文品名</td>
			<td nowrap class="td_form_white" colspan="3">               
			    <input class="field_RO" type="text" id="chProduct" name="chProduct" size="50" maxlength="100" value="<%=obj.getChProduct()%>"> 		
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">英文品名</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field_RO" type="text" id="enProduct" name="enProduct" size="50" maxlength="100" value="<%=obj.getEnProduct()%>">
			</td>
		</tr>
		<tr> 
			<td nowrap class="td_form" >許可證字號</td>
			<td nowrap class="td_form_white" colspan="3">					
			<select name="permitKey" class="field" onChange="permitData1();">
				<%=View.getOptionCodeKind("DRGPKID", obj.getPermitKey())%>
			</select>			 
			    <input class="field" type="text" id="permitNo" name="permitNo" size="6" maxlength="6" value="<%=obj.getPermitNo()%>" onChange="permitData1();"/>號
			    <input type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" class="field" >
			</td>
		</tr>		
		<tr>
			<td nowrap class="td_form">主成分</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field_RO" type="text" id="ingredient" name="ingredient" size="50" maxlength="50" value="<%=obj.getIngredient()%>">             
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">劑型</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonCodeRadioBoxOption("field", "medModel", "DRGFLN", obj.getMedModel(), null, "","" ) %>              
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">許可證持有商</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="appName" size="30" maxlength="50" value="<%=obj.getAppName()%>"> 
				<input class="field_RO" type="hidden" name="appID" size="10" maxlength="20" value="<%=obj.getAppID()%>">
				<input class="field" type="button" id="appButton" name="btnAppData" onClick="popAppData();" value="查詢" width="120px" style="display:none">				              
			</td>
			<td nowrap class="td_form">許可證持有商地址</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="appAddr" size="50" maxlength="100" value="<%=obj.getAppAddr()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造廠</td>
			<td nowrap class="td_form_white" >               
				<input class="field_RO" type="text" id="manufactorName" name="manufactorName" size="30" maxlength="50" value="<%=obj.getManufactorName()%>"> 
			</td>
			<td nowrap class="td_form">製造廠地址</td>
			<td nowrap class="td_form_white" >
				<input class="field_RO" type="text" id="manufactorAddr" name="manufactorAddr" size="50" maxlength="100" value="<%=obj.getManufactorAddr()%>">              
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造廠國別</td>
			<td nowrap class="td_form_white" colspan="3">
			    <input class="field_RO" type="text" id="manufactorCountry" name="manufactorCountry" size="30" maxlength="50" value="<%=obj.getManufactorCountry()%>">				           
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">回收清單</td>
		</tr>		
		<tr>		
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="20%">回收批號</th>
		         <th class="listTH" width="20%">庫存量</th>
		         <th class="listTH" width="15%">庫存量單位</th>
		         <th class="listTH" width="15%">銷售數量</th>
		         <th class="listTH" width="15%">銷售數量單位</th>
		         <th width="5%" class="listTH">		         
		           <input type="button" class="field_btnAdd" onclick="addDrg8002Db();" />
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attDrg8002DbView">
			   
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
		               <input class="toolbar_default" type="button" followPK="false" name="doUpload1" value="附件上傳" onClick="upload('DRG040001')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj.getAddFileDrg0401()%>
			   </tbody>
	           </table>
		   </td>
		</tr>
		<tr>
			<td nowrap class="td_form">回收原因</td>
			<td nowrap class="td_form_white" colspan="3">			    
				<input class="field" type="text" name="orirecyclereason" size="100" maxlength="200" value="<%=obj.getOrirecyclereason()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">可能產生之危險</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="danger" size="100" maxlength="200" value="<%=obj.getDanger()%>">                
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">預計完成回收日期</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","prerecycledate",obj.getPrerecycledate())%>               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否輸出國外</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxTextOption("field","isabroad","N;否;Y;是;",obj.getIsabroad())%>              
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">輸出國別</td>
			<td nowrap class="td_form_white">
			    <textarea class="field_RO" name="abroadCountryName" cols="35" rows="2"><%=obj.getAbroadCountryName()%></textarea>
                <input class="field" type="hidden" name="abroadCountry" value="<%=obj.getAbroadCountry()%>">	
                <input class="toolbar_default" type="button" name="btnAbroadQuery" value="選擇輸出國別" style="width:80px" onClick="popReportForm();">&nbsp;
                <input class="toolbar_default" type="button" name="btnAbroadClear" value="清除輸出國別" style="width:80px" onClick="clearSelect('abroadCountry');">				              
			</td>
			<td nowrap class="td_form">其他輸出國別</td>
			<td nowrap class="td_form_white">
                <input class="field" type="text" name="abroadCountryOther" size="50" maxlength="50" value="<%=obj.getAbroadCountryOther()%>">			              
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">通知藥物供應者方式</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="drugsupplier" size="50" maxlength="100" value="<%=obj.getDrugsupplier()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">擬採取之相關動作</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="precaution" size="50" maxlength="100" value="<%=obj.getPrecaution()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">回收批號產品最終儲存地點</td>
			<td nowrap class="td_form_white" colspan="3">
			<select name="lotnumStockcity" class="field" onChange="setValue(this,lotnumStockarea);">
			     <%=View.getOptionCodeKind("CTY", obj.getLotnumStockcity())%>
			</select>
			<select name="lotnumStockarea" class="field">
				<%=View.getOptionCon1002(obj.getLotnumStockarea())%>
			</select>
				<input class="field" type="text" name="lotnumStock" size="50" maxlength="100" value="<%=obj.getLotnumStock()%>">             
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">聯絡窗口</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="contactman" size="30" maxlength="50" value="<%=obj.getContactman()%>">              
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">聯絡電話</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="contacttel" size="20" maxlength="10" value="<%=obj.getContacttel()%>">               
			</td>
		</tr>
		<tr>
		   <td nowrap class="td_form">附件(運銷記錄)</td>
		   <td nowrap class="td_form_white" colspan="3">  
		       <table width="100%" align="center" class="table_form">
	           <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%">檔案名稱</th>
		          <th class="listTH" width="50%">檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		             <input class="toolbar_default" type="button" followPK="false" name="doUpload2" value="附件上傳" onClick="upload('DRG040002')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj.getAddFileDrg0402()%>
			   </tbody>
	           </table>   
		   </td>
		</tr>
		<tr>
		   <td nowrap class="td_form">附件(回收通知函)</td>
		   <td nowrap class="td_form_white" colspan="3">  
		       <table width="100%" align="center" class="table_form">
	           <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%">檔案名稱</th>
		          <th class="listTH" width="50%">檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		               <input class="toolbar_default" type="button" followPK="false" name="doUpload3" value="附件上傳" onClick="upload('DRG040003')">
	              </span>		         
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj.getAddFileDrg0403()%>
			   </tbody>
	           </table>    
		   </td>
		</tr>
		<tr>
		   <td nowrap class="td_form">附件(回收計劃書)</td>
		   <td nowrap class="td_form_white" colspan="3">  
		       <table width="100%" align="center" class="table_form">
	           <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%">檔案名稱</th>
		          <th class="listTH" width="50%">檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		             <input class="toolbar_default" type="button" followPK="false" name="doUpload4" value="附件上傳" onClick="upload('DRG040004')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj.getAddFileDrg0404()%>
			   </tbody>
	           </table>  
		   </td>
		</tr>
	</table>	


