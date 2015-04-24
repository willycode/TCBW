//*********************************************
//程式功能：判斷物件是否正確
//*********************************************
function isObj(obj){
	return (!((obj==null)||(obj==undefined)));
}
function isIE() {
	var nav = navigator.userAgent.toLowerCase();
	if ((nav.indexOf("msie") != -1)) return true;
	else return false;
}
function isObjReadOnly(obj) {
	if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){
    	return obj.readOnly;
	}else if((obj.type=="button")||(obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
		return obj.disabled;
   	}
	return true;
}
//js classes
 function StringBuffer() { 
   this.buffer = []; 
 }
 StringBuffer.prototype.append = function append(string) { 
   this.buffer.push(string);
   return this; 
 }; 
 StringBuffer.prototype.toString = function toString() { 
   return this.buffer.join(""); 
 };  
 
 String.prototype.trim = function() { return this.replace(/^\s+|\s+$/, ''); };
 String.prototype.startsWith = function(str) {return (this.match("^"+str)==str);};
 String.prototype.endsWith = function(str) {return (this.match(str+"$")==str);};
 //js classes 

/*模仿sql like功能*/
function like(buttonName,likestr){
	return (buttonName.indexOf(likestr)>=0);
}

function setObjectValue(objName,v) {
	if (isObj(document.all.item(objName))) document.all.item(objName).value = v;
}
function getObjectValue(objName) {
	if (isObj(document.all.item(objName))) return document.all.item(objName).value;
}

var returnWindow = null;
document.onreadystatechange=function(){
	if(document.readyState=="complete"){
		//禁止使用滑鼠右鍵
		//document.body.oncontextmenu=function(){ return false; }
		//本頁開啟(window.open)的所有視窗關閉
		document.body.onunload=function(){
			if (isObj(returnWindow)){ returnWindow.close(); }
		};
	}
};
function closeReturnWindow(){	
	try {	
		if (returnWindow!=null && isObj(returnWindow)){ returnWindow.close(); }
	} catch(e) {}
}

//*********************************************
//程式功能：取得虛擬路徑,return "../../"
//*********************************************
function getVirtualPath(){
	var pathName=window.location.pathname;		
	if (pathName.substring(0,1)=="/"){
		pathName=pathName.substring(1,pathName.length);
	}
	var pathArray=pathName.split("/");			
	var programLayer=pathArray.length - 2 ;//2	
	var virtualPath="";
	for(var i=0; i<programLayer; i++){
		virtualPath=virtualPath+"../";
	}
	return virtualPath;
}

//*********************************************
//函數功能：將檔名路徑改為javascript寫法
//參　　數：strPath:原始檔名路徑
//傳 回 值：
//*********************************************
function getDoublePath(strPath){
	var strReturn="";
	var i;
	var start=0;
	for(i=0; i<strPath.length; i++){
		if ("\\"==strPath.substring(i,i+1)){
			strReturn=strReturn + strPath.substring(start,i) +"\\";
			start=i;
		}
	}
	strReturn=strReturn + strPath.substring(start,strPath.length) ;
	return strReturn;
}

//*********************************************
//程式功能：將小寫轉為大寫
//*********************************************
function toUpper(obj){
	if (obj!=null && isObj(obj.value)) {
		obj.value = obj.value.toUpperCase();
	} else {
		var tmp=window.event.keyCode;
		if (tmp>=97 && tmp <=122){ tmp=tmp-32; }
		window.event.keyCode=tmp;		
	}
}

function randomUUID() {
	var s = [], itoh = '0123456789ABCDEF'; 
	// Make array of random hex digits. The UUID only has 32 digits in it, but we
	// allocate an extra items to make room for the '-'s we'll be inserting.
	for (var i = 0; i <36; i++) s[i] = Math.floor(Math.random()*0x10);
	
	// Conform to RFC-4122, section 4.4
	s[14] = 4;  // Set 4 high bits of time_high field to version
	s[19] = (s[19] & 0x3) | 0x8;  // Specify 2 high bits of clock sequence
	
	// Convert to hex chars
	for (var i = 0; i <36; i++) s[i] = itoh[s[i]];
	
	// Insert '-'s
	s[8] = s[13] = s[18] = s[23] = '-';	
	return s.join('');
}

//*********************************************
//程式功能：AJAX,擷取回應值
//*********************************************
function getRemoteData(uri,q,asyn)  {
	var x ;
	if(window.XMLHttpRequest){
	    x = new XMLHttpRequest();
	} else {
	   x = new ActiveXObject("Microsoft.XMLHTTP"); 
	}
	if (isObj(asyn) && asyn != null && asyn == true) {
		x.open('Post',uri,true); 		
	} else 	x.open('Post',uri,false); 
	x.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	x.setRequestHeader( "If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT" );
	x.send(encodeURI('q='+q));
	
	return x.responseText.trim();
}

function getRemoteXML(uri,q,asyn)  {
	var x ;
	if(window.XMLHttpRequest){
	    x = new XMLHttpRequest();
	} else {
	   x = new ActiveXObject("Microsoft.XMLHTTP"); 
	}
	if (isObj(asyn) && asyn != null && asyn == true) {
		x.open('Post',uri,true); 		
	} else 	x.open('Post',uri,false); 
	x.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	x.setRequestHeader( "If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT" );   
	x.send(encodeURI('q='+q));
	if (x.readyState == 4 && x.status == 200) return x.responseXML;
}
//*********************************************
 
//*********************************************
//函數功能：pop萬年曆輔助視窗,並回傳年月日(0920101)
//參　　數：dateField顯示日期的欄位名稱
//傳 回 值：無
//*********************************************
function popCalendar(dateField,js,sY,sM){
	var prop="";
	var windowTop=(document.body.clientHeight-400)/2+180;
	var windowLeft=(document.body.clientWidth-400)/2+250;
	prop=prop+"width=280px,height=220,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=no";	
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + 'home/calendar_v2.jsp?dateField=' + dateField + '&sY='+sY+'&sM='+sM,'popCalendar',prop);		
	//if (js!=null && js.length>0) eval(js);
}

//*********************************************
//函數功能：pop受理機關輔助視窗,並回shortCode與shortName
//參　　數：input為顯示shortCode的欄位,inputName為顯示shortName的欄位
//傳 回 值：無
//*********************************************
function popCommonDepartmentByShortCode(input,inputName){
	var prop="";
	var windowTop=(document.body.clientHeight-400)/2+180;
	var windowLeft=(document.body.clientWidth-400)/2+250;
	prop=prop+"resizable=1,width=550,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popCommonDepartmentByShortCode.jsp?popCode=" + input +"&popCodeName="+inputName,"",prop);
}

//*********************************************
//函數功能：查詢受理機關,shortCode與shortName
//參　　數：input為顯示shortCode的欄位,inputName為顯示shortName的欄位
//傳 回 值：無
//*********************************************
function getCommonDepartmentByShortCode(input,inputName,obj) {
	if (isObj(obj) && obj.value!="" && input!="") {
		toUpper(obj);
		var q = obj.value;	
		var x = getRemoteData(getVirtualPath() + "ajax/jsonCommonDepartmentByShortCode.jsp", q);
		if (x!=null && x.length>0) 
		{
			var json = eval('(' + x + ')'); 			
			document.all.item(input).value = json.shortCode; 
			document.all.item(inputName).value = json.shortName;		
		}
		else 
		{					
			document.all.item(input).value = ""; 
			document.all.item(inputName).value = "";
			alert("【錯誤】：輸入受理機關代碼錯誤，請查明後重新輸入!!");
		}
	} else {
		document.all.item(inputName).value="";
	}
}

//*********************************************
//函數功能：pop機關輔助視窗,並回傳機關代碼及名稱
//參　　數：popOrganID顯示機關代碼的欄位名稱; popOrganName顯示機關名稱的欄位名稱; isLimit是否顯示全部機關名稱
//傳 回 值：無
//*********************************************
function popOrgan(popOrganID,popOrganName,isLimit,accYear,objManageOrgan,objFullCode) {
	var manageOrgan = "";
	var accountingYear = "";
	var popFullCode = "";
	if (accYear!=null && isObj(document.all.item(accYear))) {
		accountingYear = document.all.item(accYear).value;		
	}	
	if (objManageOrgan!=null && isObj(document.all.item(objManageOrgan))) {
		manageOrgan = document.all.item(objManageOrgan).value;		
	}
	if (objFullCode!=null && isObj(document.all.item(objFullCode))) {
		popFullCode = objFullCode;	
	}		
	var prop="";
	var windowTop=(document.body.clientHeight-400)/2+180;
	var windowLeft=(document.body.clientWidth-400)/2+250;
	prop=prop+"resizable=1,width=550,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popOrganTree.jsp?accountingYear=" + accountingYear + "&manageOrgan=" + manageOrgan + "&popOrganID="+popOrganID+"&popOrganName="+popOrganName+"&isLimit="+isLimit+"&popFullCode="+popFullCode,"",prop);
}

function getOrgan(popID,popName,isLimit,accYear,popFullCode,objFullCode) {
	var accountingYear = "";
	var js = null;
	if (accYear!=null && isObj(document.all.item(accYear))) {
		accountingYear = document.all.item(accYear).value;		
	}	
	if (isObj(objFullCode) && objFullCode.value!="") {
		toUpper(objFullCode);
		var q = objFullCode.value + "&accYear=" + accountingYear + "&popFullCode=" + popFullCode + "&popID="+popID+"&popName="+popName+"&isLimit="+isLimit;
		var x = getRemoteData(getVirtualPath() + "ajax/jsonDeptNode.jsp", q);
		if (x!=null && x.length>0) {
			var json = eval('(' + x + ')'); 			
			document.all.item(popID).value = json.Id;
			document.all.item(popName).value = json.fullName;
			if (objFullCode!=null && isObj(document.all.item(objFullCode))) document.all.item(popFullCode).value = json.fullCode;			
		} else {					
			document.all.item(popID).value = "";
			document.all.item(popName).value = "";
			if (popFullCode!=null && isObj(document.all.item(popFullCode))) document.all.item(popFullCode).value = "";
			alert("【錯誤】：輸入機關代碼錯誤，請查明後重新輸入!!");
		}
	} else {
		setObjectValue(popID,"");
		setObjectValue(popName,"");
		if (popFullCode!=null && isObj(document.all.item(popFullCode))) setObjectValue(popFullCode,"");
	}	
	if (isLimit!=null && isLimit.length>0) {
		var arrPreWord = isLimit.split("&");
		if (arrPreWord.length>1) js = getURLParameter(isLimit, "js" );			
	}	
	if (js!=null && js.length>0) eval(js);	
}

//*********************************************
//函數功能：pop共用代碼輔助視窗
//參　　數：popId顯示代碼Id的欄位名稱; accYear為會計年度;
//	 	  popKind為代碼類別,popCode為codeId欄位名稱; popCodeName顯示代碼名稱的欄位名稱;
//*********************************************
function popCode(popId,preWord,popKind,popCode,popCodeName,js,changeField){
	
	var additionalField = "";
	
	if (isObj(document.all.item(preWord))) 
		additionalField = document.all.item(preWord).value;

	var jscript = "";	
	if (js!=null && js.length>0) jscript = js;	
	var prop="";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=550,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popCode.jsp?preWord=" + additionalField + "&popKind="+popKind+"&popId="+popId+"&popCode="+popCode+"&popCodeName="+popCodeName+"&js="+jscript+"&changeField="+changeField,"",prop);
}
function getCodeName(popId,preWord,codeKind,codeId,popCodeName,js) {
	if(codeId==null||codeId.value.length==0){
		setObjectValue(popId,"");
		document.all.item(popCodeName).value="";
		return;
	}
	var additionalField = "";
	if (isObj(document.all.item(preWord))) 
		additionalField = document.all.item(preWord).value;
	toUpper(codeId);
	var q = codeId.value + '&preWord='+ additionalField + '&codeKind=' + codeKind;	
	var x = getRemoteData(getVirtualPath() + 'ajax/jsonCode.jsp', q);
	if (x!=null && x.length>0) {
		var json = eval('(' + x + ')');
		document.all.item(popId).value = json.Id;
		document.all.item(popCodeName).value = json.codeName;
		var engName = getURLParameter( codeKind, 'engName' );
		if (engName.length>0 && isObj(document.all.item(engName))) document.all.item(engName).value = json.codeEngName; 
		var fds = getURLParameter( codeKind, 'conField' );		
		if (fds.length>0) {
			var arrFds = fds.split(',');
			for (var i=0; i<arrFds.length; i++) {
				if (i==0 && isObj(document.all.item(arrFds[i]))) {
					document.all.item(arrFds[i]).value=json.codeCon1;
				}
				if (i==1 && isObj(document.all.item(arrFds[i]))) {
					document.all.item(arrFds[i]).value=json.codeCon2;
				}	
				if (i==2 && isObj(document.all.item(arrFds[i]))) {
					document.all.item(arrFds[i]).value=json.codeCon3;
				}		
				if (i==3 && isObj(document.all.item(arrFds[i]))) {
					document.all.item(arrFds[i]).value=json.codeMemo;
				}						
			}
		}		
	} else {
		alert("【錯誤】：查無該代碼資料，請查明後重新輸入! 或用輔助視窗輸入!!");	
	    codeId.value = "";
		setObjectValue(popId,"");
	    setObjectValue(popCodeName,"");	
	}
	if (codeKind!=null && codeKind.length>0) {
		var arrPreWord = codeKind.split("&");
		if (arrPreWord.length>1) js = getURLParameter(codeKind, "js" ); //arrPreWord[1].substring(arrPreWord[1].indexOf("=")+1);			
	}	
	if (js!=null && js.length>0) eval(js);
}

//*********************************************
//函數功能：pop病人問題代碼輔助視窗
//參　　數：popId顯示代碼Id的欄位名稱
//	 	  popKind為代碼類別,popCode為codeId欄位名稱; popCodeName顯示代碼名稱的欄位名稱;
//*********************************************
function popMed1005Code(fieldId,fieldCode,fieldName){
	var filedId = fieldId
	var fieldCode = fieldCode;
	var fieldName = fieldName;
	var prop="";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1300,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popMed1005Code.jsp?fieldId="+fieldId+"&fieldCode="+fieldCode+"&fieldName="+fieldName,"",prop);
}

//*********************************************
//函數功能：pop醫材問題代碼輔助視窗
//參　　數：popId顯示代碼Id的欄位名稱
//	 	  popKind為代碼類別,popCode為codeId欄位名稱; popCodeName顯示代碼名稱的欄位名稱;
//*********************************************
function popMed1006Code(fieldCode,fieldName){
	var fieldCode = fieldCode;
	var fieldName = fieldName;
	var prop="";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1300,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popMed1006Code.jsp?fieldCode="+fieldCode+"&fieldName="+fieldName,"",prop);
}

//*********************************************
//函數功能：去除逗號 
//eg. 100,999.99 --> 100999.99
//eg. 一千,兩佰 --> 一仟兩佰
//*********************************************
function deleteCommas( obj ) {
	return deleteChr(obj,',');
}
function deleteChr(obj, targetChr) {
  var str = "", rStr="";
  var flag = false;
	if (isObj(obj.value)) {
	    str = obj.value;
	    flag = true;
	} else str = obj;
			
	var ch;
	if (str!=null && str!="" && str.length>0) {
		for(var i=0;i<str.length;i++){
			ch = str.charAt(i);
			if(ch!=targetChr) rStr+=str.charAt(i);
		}
	}
  if (flag) obj.value = rStr;
  else return rStr;
}

//*********************************************
//函數功能：將數字欄位加千分號
//參　　數：HTMLText field 或數值
//傳 回 值：若為數值則回傳加千分號後的數值,若為HTMLText field則直接格式化該欄位
//*********************************************
function addCommas( obj ) {
    var s = "";
    var flag = false;
	if (obj!=null && isObj(obj)) {	
		if (isObj(obj.value)) {
		    s = obj.value;
		    flag = true;
		} else s = ""+obj;
	} else return "";
	
    if (s!=null && s.length>0) {
    	s = deleteCommas(s);
        var sValue = s;
        var r = "";
        var arr = s.split(".");
        if (arr!=null && arr.length>0) {
            sValue = arr[0];
            if (arr.length>1) r = "." + arr[1];
	        var sRegExp = new RegExp('(-?[0-9]+)([0-9]{3})'); 
	        while(sRegExp.test(sValue)) { 
		        sValue = sValue.replace(sRegExp, '$1,$2'); 
	        }
	        if (flag) obj.value = sValue + r;
	        else return sValue + r;
        }
    }
    return s;
}
function formatCCC(obj, ignoreError) {

    var s = "";
    var flag = false;
	if (obj!=null && isObj(obj)) {	
		if (isObj(obj.value)) {
		    s = obj.value;
		    flag = true;
		} else s = ""+obj;
	} else return "";
	
  if (s!=null && s.length>0) {    	
  	var r = deleteChr(s,'.');
  	r = deleteChr(r,'-');
  	if (r.length>10 && r.length<13) {
  		r = r.substring(0,4)+'.'+r.substring(4,6)+'.'+r.substring(6,8)+'.'+r.substring(8,10)+'.'+r.substring(10,11)+(r.length==12?('-'+r.substring(11, 12)):'');
  	} else if (r.length==10) {
  		r = r.substring(0,4)+'.'+r.substring(4,6)+'.'+r.substring(6,8)+'.'+r.substring(8,10);
  	} else if (r.length==8) {
  		r = r.substring(0,4)+'.'+r.substring(4,6)+'.'+r.substring(6,8);
  	} else if (r.length==6) {
  		r = r.substring(0,4)+'.'+r.substring(4,6);
  	} else if (r.length==2 || r.length==4) {
  		
  	} else {	
  		if (ignoreError!=null && ignoreError) {
  		} else {
  			alert('CCC碼長度不符！');
  			if (flag) obj.style.backgroundColor=errorbg;
  		}
  		return s;
  	}
    if (flag) obj.value = r;
    else return r;
  }
  return s;
}
function deleteCCCFormat( obj ) {
	if (obj!=null && isObj(obj)) {	
		if (isObj(obj.value)) {
			deleteChr(obj,'.');
			deleteChr(obj,'-');
		} else {
		  	var r = deleteChr(obj,'.');
		  	r = deleteChr(r,'-');			
			return r;
		}
	}
	return "";
}

//*********************************************
//函數功能：顯示後端處理錯誤訊息
//參　　數：錯誤訊息, 1.取消功能選單隱藏, 2.取消權限檢查, 3.預算分配作業<取消階段別不符檢查>
//傳 回 值：無
//*********************************************
function showErrorMsg(error, intType){
	//stripe();
	var msg=error;		
	if(msg !=null && msg.length!=0){
		var strMsg = "新增完成,修改完成,更新完成,刪除完成";
		var sFlag = false;
		try {	
			var arrMsg = strMsg.split(",");
			for (var i=0; i<arrMsg.length; i++) {
				if (arrMsg[i]==msg) sFlag = true;
			}
			if (sFlag) top.frames['title'].showBoxMsg(msg);
			else alert(msg);
		} catch(e) {		
		  alert(msg);	
		}	  
	} else {
		try {	
			if (isObj(top.frames['title'])) top.frames['title'].showBoxMsg(msg);
		} catch(e) {}
	}
	if (isObj(form1.permissionField)) doPermission(form1.permissionField.value); 
	if (isObj(document.forms[0])) {
		var arrObj = document.forms[0].elements;
		var arrLen = arrObj.length;	  	
	  	for(var i=0; i<arrLen; i++){  			
			var obj = arrObj[i];
		    if(obj.type=="text" && (like(obj.className,"field_N") || like(obj.className,"field_QN"))){
		        addCommas(obj);
	   	    }
		    if(obj.type=="text" && (like(obj.className,"field_C") || like(obj.className,"field_QC") || like(obj.className,"field_PC"))){
		        formatCCC(obj,true);
		    }
			if (like(obj.className,"RO")){	
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){									
	    	    	obj.readOnly = true;	
				}else if((obj.type=="button")||(obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.disabled = true;
			   	}
			}		   	    
	    }    
	    if (intType!=null && intType==2) {
	    } else {
	    	if (isObj(form1.verify) && form1.verify.value=="Y") setFormItem("update,btnDuplicate,delete,approve1,approve2,approve3,approve4,approve5,approve6","R");	    
	    }
		//document.all.item("update").className = "toolbar_image_update_disable";
		//document.all.item("delete").className = "toolbar_image_delete_disable";		
	}		
	try {
		listContainerLoadActiveRow();
		if (intType!=null && intType==1) {
			//do nothing..
		} else {
			if (isObj(top.fbody.menuleft)) top.fbody.menuleft.doMax();					
		}
	} catch(e){}
	return false;
} 

function lockUpdateDeleteButton() {
	if (isObj(document.all.item("update")) && isObj(document.all.item("delete"))) {
		setFormItem("update,delete","R");
	}
}

//*********************************************
//函數功能：權限按鈕設定
//*********************************************
function doPermission(s) {
	try {
		if (s==null || s=="1") setDisplayItem('spanInsert,spanDuplicate,spanUpdate,spanDelete,spanClear,spanConfirm,spanNextInsert,btnMaintain,btnMaintain1,btnMaintain2,btnMaintain3,spanApproveOne,spanApproveAll,spanApprove1,spanApprove2,spanApprove3,spanApprove4,spanApprove5,spanApprove6','H');
		//if (s!=null && s=="2") //setDisplayItem('approveOne,approveAll,approve1,approve2,approve3,approve4,approve5','H');
	} catch(e) {}
}

//*********************************************
//函數功能：由身分証號碼的判斷男女
//參　　數：IDcolumn身分証號碼;Sexcolumn性別欄位
//傳 回 值：
//*********************************************
function ChangeSex(IDcolumn,Sexcolumn){
	if( IDcolumn.value.length >= 2 ){
		Sexcolumn.value=IDcolumn.value.substr(1,1);
	}  
}
//*********************************************
//函數功能：取得下拉選項的預設值
//參　　數：elem:欄位物件;val:預設值
//傳 回 值：
//*********************************************
function getSelectedValue(elem,val) {
	var i=0;
	if (val !=""){
		for (i=0;i< elem.length;i++){
			if (elem.options[i].value==val)	{
				elem.options[i].selected=true;
				return;
		    }
		}
	}
}
//*********************************************
//程式功能：取得今天日期（YYYMMDD）
//*********************************************
function getToday(s){
	if (s==null) {
		var x = getRemoteData(getVirtualPath() + "ajax/jsonDatetime.jsp", "");
		if (x!=null && x.length>0) {
			var json = eval('(' + x + ')'); 
			return json.YYYMMDD;
		}
	}
	var myDate = new Date();		
	if (s!=null) myDate = s;
	var shif = 0;
	//var intYear = myDate.getFullYear();
	//if (intYear<300) shif = 1900;
	var myYear = (myDate.getFullYear()-1911+shif).toString();	
	var myMonth = (myDate.getMonth()+1).toString();
	var myDay = myDate.getDate().toString();
	myToday = lpad(myYear,3,'0') + lpad(myMonth,2,'0') + lpad(myDay,2,'0');
	return myToday;	
}
//*********************************************
//程式功能：取得現在時間（HHMMSS）
//*********************************************
function getNow(){
	var x = getRemoteData(getVirtualPath() + "ajax/jsonDatetime.jsp", "");
	if (x!=null && x.length>0) {
		var json = eval('(' + x + ')'); 
		return json.HHMMSS;
	}
	/**
	myTime = new Date();
	var myHour = myTime.getHours().toString();
	var myMinute = myTime.getMinutes().toString();
	var mySecond = myTime.getSeconds().toString();

	if (myHour.length<=1){ myHour="0"+myHour; }
	if (myMinute.length<=1){ myMinute="0"+myMinute; }
	if (mySecond.length<=1){ mySecond="0"+mySecond; }	
	myNow = myHour + myMinute + mySecond;
	return myNow;
	**/
}

function getNumeric(s) {
	if (s==null || s=="" || isNaN(deleteCommas(s))) return 0;
	else return parseFloat(deleteCommas(s));
}

//*********************************************
//函數功能：比較兩個日期
//參　　數：objDateS,objDateE為起訖日期物件本身, strType可以是d(Day),m(Month),y(Year)
//傳 回 值：假如正確是傳回一字串值;假如錯誤則傳回空字串
//*********************************************
function getDateDiff(sType, objDateS, objDateE ){
	var sdate, edate;
	var dates, datee;
	var one_day=1000*60*60*24;
	var strType;

	if (isObj(sType.value)) strType = parse(sType.value);
	strType = parse(sType);
		
	if (isObj(objDateS.value)) sdate = parse(objDateS.value);
	else sdate = parse(objDateS);
	
	if (isObj(objDateE.value)) edate = parse(objDateE.value);
	else edate = parse(objDateE);	
	
	if( sdate.length==7 && edate.length==7 && strType.length>0){
		dates = new Date(parseInt(sdate.substring(0,3),10)+1911, sdate.substring(3,5)-1, sdate.substring(5,7));
		datee = new Date(parseInt(edate.substring(0,3),10)+1911, edate.substring(3,5)-1, edate.substring(5,7));
		if (strType=="d") {
			return (Math.ceil((datee-dates)/one_day));
		} else if (strType=="y") {
			return (parseInt(edate.substring(0,3),10)-parseInt(sdate.substring(0,3),10));
		} else if (strType=="m") {
			var sMonth = dates.getMonth() + (dates.getFullYear() * 12);
			var eMonth = datee.getMonth() + (datee.getFullYear() * 12);		
			return (eMonth-sMonth);			
		}		
	} else {
		return "";
	}
}

//*********************************************
//函數功能：某一日期加上一定期間的日或月或年
//參　　數：strType可以是d(Day),m(Month),y(Year); sNum數值; objDate為日期物件或日期
//傳 回 值：傳回加上特定期間之後的日期
//*********************************************
function getDateAdd(sType, sNum, objDate){
	var sdate = "";
	var rdate = new Date();
	var myYear, myMonth, myDay;
	var intNumber;
	var sNumber;
	var intYear, intMon, intDay;
	var strType;

	if (isObj(sType.value)) strType = parse(sType.value);
	strType = parse(sType);
		
	if (isObj(sNum.value)) sNumber = parse(sNum.value);
	else sNumber = sNum;
	
	if (objDate!=null && isObj(objDate.value)) sdate = parse(objDate.value);
	else sdate = parse(objDate);
		
	if (sNumber=="") sNumber = 0;	
	if (isNaN(sNumber)) intNumber = 0;
	else intNumber = parseInt(sNumber,10);

	if(sdate.length==7 && strType.length>0) {
		intYear = parseInt(sdate.substring(0,3),10)+1911;
		intMon = parseInt(sdate.substring(3,5),10)-1;
		intDay = parseInt(sdate.substring(5,7),10);	
		if (strType=="d") {
			rdate = new Date(intYear, intMon, intDay+intNumber);
		} else if (strType=="m") {
			rdate = new Date(intYear, intMon+intNumber, intDay);
		} else if (strType=="y") {
			rdate = new Date(intYear+intNumber, intMon, intDay);
		}
		//the bullshit js trancate 19xx to xx
		if (rdate.getYear()<2000) myYear = (rdate.getYear()-11).toString();
		else myYear = (rdate.getYear()-1911).toString();				
		//myYear = (rdate.getYear()-1911).toString();
		myMonth = (rdate.getMonth()+1).toString();
		myDay = rdate.getDate().toString();
			
		if (myYear.length<=2){ myYear="0"+myYear; }
		if (myMonth.length<=1){ myMonth="0"+myMonth; }
		if (myDay.length<=1){ myDay="0"+myDay; }	
		myToday = myYear + myMonth + myDay;
		return myToday;
		
	} else {
		return "";
	}		
}

function openUploadWindow(popFileID, popFileName, popFileType){
	if (isObj(eval("form1."+popFileID))) {
	    var prop='';
	    prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	    prop=prop+'width=450,';
	    prop=prop+'height=160';
	    closeReturnWindow();
	    returnWindow = window.open(getVirtualPath()+'home/popUploadSimple.jsp?popFileID=' + popFileID + '&popFileName='+popFileName + '&popFileType='+popFileType,'上傳檔案',prop);
	} else {
		alert("欄位不存在,請檢查!");
		return ;
	}
}

function openDownloadWindow(fileID, fileType, fileName){
    var prop='';
    prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=yes,scrollbars=yes,resizable=yes,';
    prop=prop+'width=400,';
    prop=prop+'height=400';
	if (fileID!=null && fileID.length>0) {
		var arrFileName = fileID.split(":;:");
		if (arrFileName.length>1) {
		    var url = getVirtualPath() + "downloadFileSimple?fileID=" + fileID + "&fileType=" + fileType;    
		    window.open(url,'popDownload',prop);
		} else {
			alert("無法下載該檔案，因為檔案資訊不完整，若問題持續，請洽系統管理!");
		}		
	} else if (fileName!=null && fileName.length>0) {
	    var url = getVirtualPath() + "downloadFileSimple?fileName=" + fileName;    
		window.open(url,'popDownload',prop);		
	} else {
		alert("目前沒有任何檔案可供下載!");	
	}
}

function addLoadEventListener(func) {
  	var oldonload = document.onload;
	document.onload = function() {
		oldonload();			
		func();
	};
}

function setAutocompleteOff(strField, autoFlag) {	
	if (strField.trim().length>0) {
		var arrField = strField.split(",");
		if (arrField.length>0) {
		 	for(var i=0; i<arrField.length; i++){
		 		if (isObj(document.all.item(arrField[i]))) {		 		  	
					obj = document.all.item(arrField[i]);
					if (autoFlag=="on") obj.setAttribute('AutoComplete','on');
					else obj.setAttribute('AutoComplete','off');				
				}
			}
		}
	}
}

/*submit之前將select打開*/
function beforeSubmit(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;	  	
  	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")) {		
			obj.disabled = false;
		} else if(obj.type=="text") {
			if ((like(obj.className,"field_N") || like(obj.className,"field_QN"))) deleteCommas(obj);
			if ((like(obj.className,"field_C") || like(obj.className,"field_QC"))) deleteCCCFormat(obj);
			if ((like(obj.className,"field_Q") || like(obj.className,"field_X"))) obj.disabled = false;
		}
	}
}

//---- parse form field to JSONString ----------------
function genJSONString(objForm){
	var m;
	if (objForm!=null && isObj(objForm)) {
		m = objForm;
	} else if (isObj(document.forms[0])) {
		m = document.forms[0];
	} else return false;
	
	if (isObj(m.qryString) && isObj(m.fldString)) {
		var alertStr="";
		var q = new Object();
		var f = new Object();
		/**
		if (objForm!=null && isObj(objForm)) {
			m = objForm;
		} else if (isObj(document.forms[0])) {
			m = document.forms[0];
		} else {
			alertStr+="對不起,找不到目標表單物件, JSON Object處理失敗！\n";
		}
		**/
		if (!isObj(m.qryString)) alertStr += "找不到承接JSON Object的查詢欄位qryString！\n";
		if (!isObj(m.fldString)) alertStr += "找不到承接JSON Object的全部表單欄位fldString！\n";
		if(alertStr.length!=0){ alert(alertStr); return false; }
		else {
			for(var i=0; i<m.elements.length; i++){
				obj = m.elements[i];
				if((obj.type=="text")||(obj.type=="hidden")||(obj.type=="textarea")||(obj.type=="password")||obj.type=="select-one"||obj.type=="select"){	
					f[obj.name]=obj.value;
					if ((obj.className=="field_PQ")||(obj.className=="field_Q")||(obj.className=="field_QRO")) q[obj.name]=obj.value;
				}else if(obj.type=="select-multiple"){
					if ((f[obj.name]!=null) && isObj(f[obj.name])) {}
					else {
						f[obj.name]=multiSelectToArray(obj);
					if ((obj.className=="field_PQ")||(obj.className=="field_Q")||(obj.className=="field_QRO")) q[obj.name]=obj.value;
					}
				}else if((obj.type=="checkbox")||(obj.type=="radio")){
					if (f[obj.name]!=null && isObj(f[obj.name])) {}
					else {
						f[obj.name]=checkboxToArray(m,obj.name);
						if ((obj.className=="field_PQ")||(obj.className=="field_Q")||(obj.className=="field_QRO")) q[obj.name]=obj.value;
					}			
			    }
			    /**
				  若欄位型態為file或是button，則其值將會被忽略
				else if ((obj.type=='button')||(obj.type=='submit')||(obj.type=='reset')){
			    	
			    }else{
			    	alert(obj.type);
			    }	
			    **/		    				
			}	
			m.qryString.value = q.toJSONString();
			m.fldString.value = f.toJSONString();
			return true;
		}	
	}
}

function checkboxToArray(ml, cbName) {
	var len = ml.elements.length;
	var arrObj = new Array();
	for(var i = 0 ; i < len ; i++) {
		var obj = ml.elements[i];
	    if (obj.name == cbName && obj.checked) {
	    	if (obj.type=="radio") return obj.value;
	    	else arrObj.push(obj.value);
	    }
	}
	if (arrObj.length==1) return arrObj[0];	
	return arrObj;
}

//IE、chrome都通用的取得radio被選取的值方法
function getRadioChecked1(formName) {
	var obj = document.all.item(formName);
	
	for (var i=0; i<obj.length; i++) {
	      if (obj[i].checked) {
	    	  return obj[i].value;
	      }
	    }
	  return "";
}
//取得radio被選取的值
function getRadioChecked(obj) {
  //當只有一個選項的時候 可以得到value 也就不會等於undefined了
  if (obj.value != undefined) {
    if (obj.checked) {
      return obj.value;
    }
  } else { //當有兩個以上的選項時 要用迴圈取得checked的選項
    for (var i=0; i<obj.length; i++) {
      if (obj[i].checked) {
    	  return obj[i].value;
      }
    }
  }
  return "";
}

function checkedRadio(obj, val) {
	if (isObj(obj)){
		for (var i=0; i<obj.length; i++) {
			if (obj[i].value == val) {
				obj[i].checked = true;
			}
		}
	}
}

function multiSelectToArray(objOption) {
	var arrObj = new Array();
	try {
		if (objOption!=null && objOption.type=="select-multiple") {
			for(var i = 0 ; i < objOption.length ; i++) {
				if (objOption[i].selected) arrObj.push(objOption[i].value);				
			}
		}
	} catch(e) {}
	return arrObj;
}
//--------- parse form field to JSONString -------------------

function parseQueryOne(x) {
    if (x!=null && x.length>0) {
    	alert(x);
		var fd = eval('(' + x + ')'); 
        for (var i in fd){
          var field = document.getElementById(i);
          if(field !=null){
             if(typeof field.type!= 'undefined'){
                field.value=fd[i];
             } else{
                field.innerHTML=fd[i];
             }
          }
        }   
    }
}

function getURLParameter( url, name )
{
  var regexS = "[\\?&]"+name+"=([^&#]*)";
  var regex = new RegExp( regexS );
  var results = regex.exec( url );
  if( results == null )
    return "";
  else
    return results[1];
}

function setLength(obj, len){
	obj.setAttribute('maxLength',len);
}

function getDeptCodeLenFromParent(level) {
	try {
		switch(level) {
			case "0": return 2;
			case "1": return 2;
			case "2": return 2;
			case "3": return 2;
			default : return 2;
		}
	} catch(e) {}
	return 2;
}
function getDeptFullCode(fullCode, parentLevel, s) {
	var code0 = fullCode.substring(0,2);
	var code1 = fullCode.substring(2,4);
	var code2 = fullCode.substring(4,6);
	var code3 = fullCode.substring(6,8);
	//var code4 = fullCode.substring(8,10);	
	switch (parentLevel) {
	case "0":
		return rpad(code0+lpad(s,2,'0'), 10, '0');
	case "1":
		return rpad(code0+code1+lpad(s,2,'0'), 10, '0');
	case "2":
		return rpad(code0+code1+code2+lpad(s,2,'0'), 10, '0');
	case "3":
		return rpad(code0+code1+code2+code3+lpad(s,2,'0'), 10, '0');
	}
	return rpad(lpad(s,2,'0'), 10, '0');	
}
function getDeptShortCode(fullCode, parentLevel, s) {
	var code0 = fullCode.substring(0,2);
	var code1 = fullCode.substring(2,4);
	var code2 = fullCode.substring(4,6);
	var code3 = fullCode.substring(6,8);
	//var code4 = fullCode.substring(8,10);	
	switch (parentLevel) {
	case "0":
		return code0+lpad(s,2,'0');
	case "1":
		return code0+code1+lpad(s,2,'0');
	case "2":
		return code0+code1+code2+lpad(s,2,'0');
	case "3":
		return code0+code1+code2+code3+lpad(s,2,'0');
	}
	return lpad(s,2,'0');	
}

function getDeptCodeLenFromParentBak(level) {
	try {
		switch(level) {
			case "0": return 3;
			case "1": return 4;
			case "2": return 4;
			default : return 2;
		}
	} catch(e) {}	
	return 2;
}

function stripe() {
	var i=0; j=0;
	var container = document.getElementById("formContainer");
	if (!isObj(container) || container==null) return false;		
	var tables = container.getElementsByTagName("table");
	var h=0; x=0;	
	var table = tables[x];
	if (!table) { return false; }			
	var tbodies = table.getElementsByTagName("tbody");
	if (!isObj(tbodies) || tbodies==null) tbodies = table;
	var even = true;
	if (isIE()) {
		for (h = 0; h < tbodies[0].childNodes.length; h++) {								
			for (i = 0; i < tbodies[0].childNodes[h].childNodes.length; i++) {					
				if(even) tbodies[0].childNodes[h].childNodes[i].className = "td_form_even";
				else tbodies[0].childNodes[h].childNodes[i].className = "td_form_odd";							
			}
			even = !even;
		}
	} else {
		for (h = 0; h < tbodies[0].childNodes.length; h++) {								
			for (i = 0; i < tbodies[0].childNodes[h].childNodes.length; i++) {					
				if(even) tbodies[0].childNodes[h].childNodes[i].className = "td_form_even";
				else tbodies[0].childNodes[h].childNodes[i].className = "td_form_odd";							
			}
			if (even) even=false;
			else even = true;
		}				
	}	
	return false;
}

function setCookie(c_name,value){
	c_name = validateCookie(escape(deleteChr(c_name)));
	value = validateCookie(escape(value));
	document.cookie=c_name+"="+value;
}

function validateCookie(cookieStr)
{
	//prevent Cookie Manipulation
	try{
		cookieStr = cookieStr.replace("%0d","");
		cookieStr = cookieStr.replace("%0D","");
		cookieStr = cookieStr.replace("\r","");
		cookieStr = cookieStr.replace("%0a","");
		cookieStr = cookieStr.replace("%0A","");
		cookieStr = cookieStr.replace("\n","");
	}catch(e){}
	return cookieStr;
}

function getCookie(c_name){
	c_name = escape(deleteChr(c_name));
	if (document.cookie.length>0) {
  		var c_start=document.cookie.indexOf(c_name + "=");
	  	if (c_start!=-1){
	    	c_start=c_start + c_name.length+1;
	    	var c_end=document.cookie.indexOf(";",c_start);
	    	if (c_end==-1) c_end=document.cookie.length;
	    	return unescape(document.cookie.substring(c_start,c_end));
	    }
  }
  return "";
}

function setDivPosition(divName, divCookieName){
	if(isObj(document.getElementById(divName))){
		if (divCookieName!=null) setCookie(divCookieName+"Pos",document.getElementById(divName).scrollTop);
		else setCookie(divName+"Pos",document.getElementById(divName).scrollTop);
	}
}
function getDivPosition(divName, divCookieName) {
	if (divCookieName!=null) {
		divCookieName = deleteChr(divCookieName);
		document.getElementById(divName).scrollTop = getNumeric(getCookie(divCookieName+"Pos"));
	} else document.getElementById(divName).scrollTop = getNumeric(getCookie(divName+"Pos"));
}
function listContainerRowClick(rowid) {	
	if (rowid!=null && isObj(document.all.item("listContainerActiveRowId"))) {	
		setCookie(document.URL+'_listContainerActiveRowId',rowid);		
		document.all.item("listContainerActiveRowId").value = rowid;
		setDivPosition('listContainer', deleteChr(document.URL));
	}
}
function listContainerLoadActiveRow() {
	if (isObj(document.all.item("listContainerActiveRowId"))) {				
		var rowid = getCookie(document.URL+'_listContainerActiveRowId');
		if (isObj(document.all.item("listContainerRow"+rowid))) {			
			if (isObj(document.all.item("state")) && !like(document.all.item("state").value,'insert') && !like(document.all.item("state").value,'delete')) {
				document.all.item("listContainerRow"+rowid).className = "activeRow";
			}
			document.all.item("listContainerRow"+rowid).onmouseover="activeRow";
			document.all.item("listContainerRow"+rowid).onmouseout="activeRow";
			getDivPosition('listContainer',deleteChr(document.URL));
		}	
	}	
}

function setTableStripe(tbody, color1, color2){
    var colors  = [color1, color2];
    var counter = 0;
    var tr      = tbody.firstChild;
    while(tr){
        tr.style.backgroundColor = colors[counter++ % 2];
        tr = tr.nextSibling;
    }
}

function lpad(s,len,ch) {
	var format="";	
	if (s==null) s = "";
	var sLen=s.length;
	for(var i=0; i<(len-sLen); i++)	format += ch;	
	format += s;
	return format;
}
function rpad(s,len,ch){
	var format="";
	if (s==null) s = "";
	var sLen=s.length;
	for(var i=0;i<(len-sLen); i++) format += ch;	
	format = s + format;
	return format;
}

function openDummyWindown(windowName,w,h)   {   
	var params = 'width=800,height=500,resizable=1,menubar=no,scrollbars=no';
	if (w!=null && h!=null) params = 'width=' + w + ',height=' + h + ',resizable=1,menubar=no,scrollbars=no';
	window.open('about:blank',windowName,params);  
}

function openPostWindow(windowName,w,h)   {   
	var params = 'width=800,height=500,resizable=1,menubar=no,scrollbars=no';
	if (w!=null && h!=null) params = 'width=' + w + ',height=' + h + ',resizable=yes,menubar=no,scrollbars=yes';
	window.open('about:blank',windowName,params);  
}

function removeRowFromTable(tblId, rid){
  var tbl = document.getElementById(tblId);
  var lastRow = tbl.rows.length;
  if (lastRow > 0) {
	  if (rid!=null) tbl.deleteRow(rid);
	  else tbl.deleteRow(lastRow - 1);
  }
}

function disableBackspaceEvent(e) {
	var code = null;
	if (!e) e = window.event;
	if (e.keyCode) code = e.keyCode;
	else if (e.which) code = e.which;
	if (code==8 && e.srcElement!=null && (like(e.srcElement.className,"RO")||isObjReadOnly(e.srcElement))) {
		return false;
	}
	return true;
}

//此函數查找相應的項是否存在
//repeatCheck是否進行重複性檢查
//若為"v"，按值進行重複值檢查
//若為"t"，按文字進行重複值檢查
//若為"vt"，按值和文字進行重複值檢查
//其它值，不進行重複性檢查，返回false
function OptionExists(list, optText, optValue, repeatCheck){
	var i = 0;
	var find = false;
	
	if (repeatCheck == "v"){
		//按值進行重複值檢查
		for (i=0; i<list.options.length; i++){
			if (list.options[i].value == optValue){
				find = true;
				break;
			}
		}
	} else if (repeatCheck == "t"){
		//按文字進行重複檢查
		for (i=0; i<list.options.length; i++){
			if (list.options[i].text == optText){
				find = true;
				break;
			}
		}
	} else if (repeatCheck == "vt"){
		//按值和文字進行重複檢查
		for (i=0; i<list.options.length; i++){
			if ((list.options[i].value == optValue) && (list.options[i].text == optText)){
				find = true;
				break;
			}
		}
	}
	
	return find;
}

//向列表中追加一個項
//list 是要追加的列表
//optText 和 optValue 分別表示項的文字和值
//repeatCheck 是否進行重複性檢查，參見 OptionExists
//添加成功返回 true，失敗返回 false
function AppendOption(list, optText, optValue, repeatCheck){
	if (!OptionExists(list, optText, optValue, repeatCheck)){
		list.options[list.options.length] = new Option(optText, optValue);
		return true;
	} else {
		return false;
	}
}

//將一個列表中的項導到另一個列表中
//repeatCheck是否進行重複性檢查，參見OptionExists
//deleteSource項導到目標後，是否刪除源列表中的項
//返回影響的項數量
function ListToList(sList, dList, repeatCheck, deleteSource)
{
	//所影響的行數
	var lines = 0;
	var i = 0;
	while (i<sList.options.length){
		if (sList.options[i].selected && AppendOption(dList, sList.options[i].text,sList.options[i].value, repeatCheck)){
			//添加成功
			lines++;
			if (deleteSource){
				//刪除源列表中的項
				sList.options[i] = null;
			} else {
				i++;
			}
		} else {
			i++;
		}
	}
	
	return lines;
}
//dialog for message
function openTBGVDialog() {
	var isLoaded = document.all.item("isJQueryJSLoaded");
	if(isObj(isLoaded) && "N" == isLoaded.value){
		isLoaded.value = "Y";
		//for MessagePopup
		jsinclude(getVirtualPath()+"js/jquery.js");
		cssinclude(getVirtualPath()+"js/jquery-ui-1.7.2.custom.css");
		jsinclude(getVirtualPath()+"js/jquery-ui-1.7.2.custom.min.js");
	}
	$('#msgList').dialog({
		autoOpen: false,
		width: 210,
		height: 100,
		resizable: false,
		title:"訊息通知",
		closeOnEscape: false,
		position: ['right','bottom'],
		beforeclose: function(event, ui) {
			writeMsgLog();
		}
	});
	$('#msgList').dialog('open');
}

//string:原始字符串
//substr:子字符串
//isIgnoreCase:忽略大小写
//
function contains(string,substr,isIgnoreCase) {
    if(isIgnoreCase) {
     string=string.toLowerCase();
     substr=substr.toLowerCase();
    }
     var startChar=substr.substring(0,1);
     var strLen=substr.length;
         for(var j=0;j<string.length-strLen+1;j++)
         {
             if(string.charAt(j)==startChar)
             {
                   if(string.substring(j,j+strLen)==substr)
                   {
                         return true;
                   }   
             }
         }
         return false;
}

function openMsgWindow(id){
	var prop='';
	prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	prop=prop+'width=630,';
	prop=prop+'height=390';
	window.open(getVirtualPath()+'home/popMsg.jsp?q='+id,'msgWin',prop);
}

function writeMsgLog(){	
	if (isObj(top.fbody.mainframe.document.all.item('msgIdList'))) {
		var q = top.fbody.mainframe.document.all.item('msgIdList').value;
		if (q!=null && q.length>0) {
			getRemoteData('writeMsgLog.jsp',q);
		}
	}
	top.fbody.mainframe.document.all.item('msgList').innerHTML = "";
	top.fbody.menu.document.all.item('msgContainer').style.display='none';	
}

function setOptionLabelField(obj,targetObj) {
	if (obj!=null && targetObj!=null) targetObj.value = obj.options[obj.selectedIndex].text;	
}

//動態載入CSS
function cssinclude(href) {
  var headID = document.getElementsByTagName("head")[0];
  var newCss = document.createElement('link');
  newCss.type = 'text/css';
  newCss.rel = "stylesheet";
  newCss.href = href;
  headID.appendChild(newCss);
  
}
//動態載入JS
function jsinclude(src) {
  var headID = document.getElementsByTagName("head")[0];
  
  var newJs = document.createElement('script');
  newJs.type = 'text/javascript';
  newJs.src= src;
  headID.appendChild(newJs);
}

//擋右鍵
//document.oncontextmenu=new Function("return false");

if (isIE()) document.onkeydown = disableBackspaceEvent;
else document.onkeypress = disableBackspaceEvent;
//將回上頁 的功能 Disabled，這是一個很差的處理方式，若有其它解決辦法，請用別的
window.history.forward(1);

//左補字串
function formatFrontString(s,len,ch){
	if(s!=null&&s.length>0){
		if(s.length<len){
		var str = "";
		for(var i=0;i<(len-s.length);i++){
			str += ch;
		}
		return str+s;
		}else{
			return s;
		}
	}
	return "";
}

/**
 * 某一字串僅取其英文或數字值
 * @param sStr = "一佰萬=1000000"
 * @return 1000000
 */
function getAlphaIntOnly(sStr){
	var s = "";
	for(var i=0;i<sStr.length;i++){
		var ch=sStr.charAt(i);
	    if(!((ch < "A" || ch >"Z")&&(ch < "a"||ch > "z")&&(ch < "0" || ch >"9"))) s += ch;	    
	}
	return s;
}

function popupFullScreen(url) {
	var params  = 'width='+screen.width;
	params += ', height='+screen.height;
	params += ', top=0, left=0';
	params += ', fullscreen=yes';
	
	var newwin=window.open(url,'popupFullScreenWin', params);
	if (window.focus) {newwin.focus();}
	return false;
}

//*********************************************
//函數功能：popHFR0001委員輔助視窗
//參　　數：委員名稱欄位、委員ID欄位、委員ID預設值;
//*********************************************
function popHFR0001(nameField, idField, idValue){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popHFR0001.jsp?nameField="+nameField+"&idField="+idField+"&idValue="+idValue,"",prop);
}

//*********************************************
//函數功能：popCommonUser輔助視窗
//參　　數：名稱欄位、ID欄位、ID預設值;
//*********************************************
function popCommonUser(nameField, idField, idValue){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popCommonUser.jsp?nameField="+nameField+"&idField="+idField+"&idValue="+idValue,"",prop);
}

//*********************************************
//函數功能：檢查密碼強度
//參　　數：column名稱欄位、cname欄位名稱、minLen長度限制;
//*********************************************
function checkMustMixAlphaInt(column, cname, minLen)
{
	var sStr = "", rStr="";
	var dStr = "";
	var i=0;
	if (isObj(column.value)) sStr = parse(column.value);
	else sStr = parse(column);
	
	for(i=0;i<sStr.length;i++)
	{
		var ch=sStr.charAt(i);
	    if(ch >= "0" && ch <="9") 
	    {
	    	dStr += ch;
	    } 
	    else 
	    {
	    	rStr += ch;
	    }
	}
	
	var containSpecial = RegExp(/[(\ )(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=)  (\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\')(\")(\,)(\.)(\/)(\<)(\>)(\?)(\)]+/);    
	
	
	if ((minLen>0) && (minLen>sStr.length)) 
	{		
		return cname + "長度至少須為"+minLen+"碼文數字混合的字串!\n";
	}
	
	if(containSpecial.test(sStr)==false)
	{		
		return cname + "須為特殊符號混合的字串!\n";
	}
	
	if (sStr!='' && (sStr.length==rStr.length) || (sStr.length==dStr.length)) 
	{
		return cname + "須為文數字混合的字串!\n";
	}
	
	
	
	return "";
}

//*********************************************
//函數功能：popCon0004案件補件紀錄
//參　　數：applNo
//*********************************************
function popCon0004(applNo){
	popCon0004(applNo, '');
}
function popCon0004(applNo, fileType){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popCon0004.jsp?applNo="+applNo+"&fileType="+fileType,"",prop);
}

//*********************************************
//函數功能：popCon0002郵件清單紀錄
//參　　數：sysType、formID
//*********************************************
function popCon0002(sysType, formID){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popCon0002.jsp?sysType="+sysType+"&formID="+formID,"",prop);
}

//*********************************************
//函數功能：popCon2001案件流程紀
//參　　數：systemType、formID
//*********************************************
function popCon2001(systemType, formID){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popCon2001.jsp?systemType="+systemType+"&formID="+formID,"",prop);
}


//*********************************************
//函數功能：popCon0002郵件清單紀錄-醫材專用
//參　　數：sysType、applNo
//*********************************************
function popCon0002Med(sysType, applNo){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popCon0002.jsp?sysType="+sysType+"&applNo="+applNo,"",prop);
}


//*********************************************
//函數功能：popCon2001案件流程紀-醫材專用
//參　　數：systemType、formID
//*********************************************
function popCon2001Med(systemType,applNo){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popCon2001.jsp?systemType="+systemType+"&applNo="+applNo,"",prop);
}


//*********************************************
//函數功能：popZipCode 鄉鎮區代碼
//參　　數：place、zipcode
//*********************************************
function popZipCode(place,zipcode)
{
	var obj1 = zipcode;
	var oldDeptValue = zipcode.value;
	obj1.options.length=0;
	obj1.options.add(new Option("請選擇",""));	

	var x = getRemoteData(getVirtualPath() + "/ajax/jsonZipCode.jsp?city="+place.value,"");

	if (x!=null && x.length>0) 
	{
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

//*********************************************
//函數功能：popNotifierDept 取得服務機構
//參　　數：notifierType 通報者屬性
//*********************************************
function popNotifierDept(notifierType, isNeedGetBack){
    var v = "";
	for(var i=0; i<notifierType.length; i++){
		if(notifierType[i].checked){
		   v = notifierType[i].value;
		   break;
		}
	}
	
	if(v == "01" || v == "3"){
		alert("民眾不需選擇服務機構");
	}else{
		var y = "";
		if(isNeedGetBack != null){
			y = "&y=" + isNeedGetBack; 
		}
		
		var prop = "";
		var windowTop = 120;
		var windowLeft = 120;	
		prop = prop + "width=1200px,height=600,";
		prop = prop + "top=" + windowTop + ",";
		prop = prop + "left=" + windowLeft + ",";
		prop = prop + "scrollbars=yes";
		if(popWinName !=null){
			popWinName.close();
		}
		popWinName = window.open(getVirtualPath()+"home/popUserJob.jsp?v=" + v + y,'popWinE',prop);
	}	
}
function validateCookie(cookieStr){
	//prevent Cookie Manipulation
	try{
		cookieStr = cookieStr.replace("%0d","");
		cookieStr = cookieStr.replace("%0D","");
		cookieStr = cookieStr.replace("\r","");
		cookieStr = cookieStr.replace("%0a","");
		cookieStr = cookieStr.replace("%0A","");
		cookieStr = cookieStr.replace("\n","");
	}catch(e){}
	return cookieStr;
}
//選取橫列
function selectRow(checkBox){
	var tr = $('#reportTable tbody tr').eq(checkBox.id);
	$('td',tr).each(function(){
		$(this).children('.field_Q').attr('checked',checkBox.checked);
	})
}
//選取縱列
function selectColumn(checkBox){
	$('#reportTable tbody tr').each(function(){
		$('td',this).eq(checkBox.id).children('.field_Q').attr('checked',checkBox.checked);
	})
}
//選取全部
function selectAllTable(checkBox){
	$('#reportTable tbody tr').each(function(){
		$('td',this).each(function(){
			$(this).children('.field_Q').attr('checked',checkBox.checked);
		})
	})
}

function getMLMSValue(permitKey, permitNo, fileName){
	var x = getRemoteData(getVirtualPath() + "ajax/jsonMLMSData.jsp?&k="+permitKey+"&n="+permitNo+"&f="+fileName, "");
	if (x!=null && x.length>0) {
		var json = eval('(' + x + ')'); 
		return json.obj;
	}
}
//給一些需要將兩個日期差異天數顯示的部分，會將差異日期放到id為prefix+id的div tag底下
function setDateDiff(prefix,id){
	var divBlock = document.getElementById(prefix+id);
	var dateS = document.all.item(prefix+'S'+id);
	var dateE = document.all.item(prefix+'E'+id);
	divBlock.innerHTML = '相差：'+getDateDiff('d',dateS,dateE)+'天';
}

/*query欄位都清空*/
function setAllClearQ(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (like(obj.className,"field_Q")){			
			if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){								
	        	obj.value="";
			}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")){
				obj.value="";
			}else if((obj.type=="checkbox")||(obj.type=="radio")){
				obj.checked  = false;
   		  	}
      	}
	}
}

/*檔案上傳刪除*/
function deleteFileSimple(deleteFileId){
	var state = document.all.item("state").value;
	if(state=='update' || state=='init'){
		if(confirm("附件刪除將無法復原，是否進行刪除 ?")){		
			window.open("../../home/deleteFileSimple.jsp?id="+deleteFileId, 'external', 'width=420,height=420,left=180,top=50');
		}
	}else{
		alert("於修改狀態時，才能進行附件資料刪除!!");
	}
}

function getRevisionValue(dbType, revision, fileName, applNo, medType){
	var x = getRemoteData(getVirtualPath() + "ajax/jsonRevisionData.jsp?d="+dbType+"&r="+revision+"&f="+fileName+"&a="+applNo+"&m="+medType, "");
	if (x!=null && x.length>0) {
		var json = eval('(' + x + ')'); 
		return json.obj;
	}
}