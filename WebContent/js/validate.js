var errorbg="#FFCEDB";
var readbg="";
var editbg="";

//*********************************************
//函數功能：檢查欄位是否空白
//參　　數：column物件本身;name中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkEmpty( column, name ){
	columnTrim( column );
	if( column.value.length == 0 ){
		column.style.backgroundColor=errorbg;
		return name + "不允許空白!\n";
	}else{
		column.style.backgroundColor="";
		return "";
	}
}
//*********************************************
//函數功能：檢查欄位必須是空白
//參　　數：column物件本身;name中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkMustEmpty( column, name ){
	columnTrim( column );
	if( column.value.length > 0 ){
		column.style.backgroundColor="#FFCEDB";
		return name + "必須空白!\n";
	}else{
		column.style.backgroundColor="";
		return "";
	}
}
//*********************************************
//函數功能：檢查資料是否為數字
//參　　數：column物件本身; name中文名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkNumber(column, name){
	  columnTrim(column);
	  if(column.value!=""){
		  if(isNaN(column.value)){
		  	 column.style.backgroundColor=errorbg;
			 return name+" 請輸入數值資料\n";
		  }
	  }
	  column.style.backgroundColor="";
	  return "" ;
}

function checkNumeric(column, name, isPositive, blankWhenErr){
	  columnTrim(column);
	  if(column.value!=""){
		  var v = deleteCommas(column.value);
		  if(isNaN(v)) {
			  column.style.backgroundColor=errorbg;
			  if (blankWhenErr!=null && blankWhenErr) {
				  column.value = "";
				  alert(name+" 請輸入數值資料\n");		  		 
			  } else {
				  return name+" 請輸入數值資料\n";
			  }			  
		  }		  
		  if(isPositive && getNumeric(column.value)<0){
		  	 column.style.backgroundColor=errorbg;
		  	 if (blankWhenErr!=null && blankWhenErr) {
		  		 column.value = "";
		  		 alert(name+" 請輸入>0的數值資料\n");		  		 
		  	 } else {
		  		return name+" 請輸入>0的數值資料\n";
		  	 }
		  }
		  if(!isPositive && getNumeric(column.value)>0){
			  column.style.backgroundColor=errorbg;
			  if (blankWhenErr!=null && blankWhenErr) {
				  column.value = "";
				  alert(name+" 請輸入<0的數值資料\n");		  		 
			  } else {
				  return name+" 請輸入<0的數值資料\n";
			  }
		  }		  
	  }
	  column.style.backgroundColor="";
	  return "";
}

//*********************************************
//函數功能：檢查資料是否為正整數
//參　　數：column物件本身; name中文名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkInt(column , name){
	  columnTrim( column );
	  if(column.value==""){
		column.value=0 ;
	  }
	  //var num = parseInt(column.value);
	  column.value = deleteCommas(column.value);
	  if(isNaN(column.value) || column.value.indexOf('.') >= 0){
	  	 column.style.backgroundColor=errorbg;
		 return name+" 請輸入整數\n";
	  }
	  if( column.value.indexOf("-") >= 0 ){
	  	column.style.backgroundColor=errorbg;
		return name+" 請輸入正整數\n";
	  }
	  column.style.backgroundColor="";
	  return "" ;
}
//*********************************************
//函數功能：檢查資料是否為浮點數
//參　　數：column物件本身; name中文名稱; I整數幾位; F小數點幾位
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkFloat( column , name, I , F ){
	columnTrim( column );
	var S = deleteCommas(column.value) ;
	var E = parseFloat(S) ;

	if(S==""){
		column.value = 0 ;
		return "";
	}
	for(var ii=0 ; ii<S.length ; ii++){
		E=S.substring(ii,S.length);
		if(isNaN(E)){
			column.style.backgroundColor=errorbg;
			return name + "請輸入數字!\n";
		}
	}
	if( S.indexOf('.')==-1){
		if(S.length > I){
			column.style.backgroundColor=errorbg;
			return name +"整數部分不可大於 "+I+" 位\n" ;
		}
	}else{
		if(F==0){
			column.style.backgroundColor=errorbg;
			return name +"請輸入整數\n" ;
		}
		if(S.indexOf('.') > I){
			column.style.backgroundColor=errorbg;
			return name +"整數部分不可大於 "+I+" 位\n" ;
		}
		if(S.substring(S.indexOf('.')+1,S.length).length > F ){
			column.style.backgroundColor=errorbg;
			return name +"小數部分不可大於 "+F+" 位\n" ;
		}
	}
	column.style.backgroundColor="";
	return "" ;
}

//*********************************************
//函數功能：檢核欄位數值是否大於某數值
//參　　數：HTMLText field 或數值
//傳 回 值：若超過則回覆錯誤訊息string
//*********************************************

function checkFloatMax(column,maxNum,name){
	var value = isObj(column)?column.value:column;
	if(parseComma(value) >0 && parseFloat(parseComma(value)) > maxNum){
		if(isObj(column))
			column.style.backgroundColor=errorbg;
		return name +"不可大於 "+maxNum+" \n" ;
	}
	return "";
}
//*********************************************
//函數功能：檢查資料是否為日期(YYYMMDD)
//參　　數：obj物件本身; cName中文名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkDate(obj,cName){
	var data1;
	var sFlag = false;
	if (isObj(obj.value)) {
		sFlag = true;
		columnTrim( obj );
		data1 = obj.value;
	} else {
		data1 = obj;
	}
	if(data1 == "") {
		if (sFlag) obj.style.backgroundColor="";
    	return "";	      
	}
	//判斷是否為正整數
	var n1=Number(data1);
	var n2=Math.floor(n1);
	if (isNaN(n1)||(n1<0)||(n1!=n2)){
		if (sFlag) obj.style.backgroundColor=errorbg;
		return cName + "日期格式不符!請輸入正確的民國日期(YYYMMDD)\n";
	}
	//判斷是否為正確的日期格式
	if ((data1.length!=7)){
		if (sFlag) obj.style.backgroundColor=errorbg;
		return cName + "日期格式不符!請輸入正確的民國日期(YYYMMDD)\n";
	}else{
		var y=Number(data1.substring(0,3)) + 1911;
		var m=Number(data1.substring(3,5)) - 1;
		var d=Number(data1.substring(5,7));
		var d1=new Date(y,m,d);
		if((d1.getMonth()!=m)||(d1.getDate()!=d)){
			if (sFlag) obj.style.backgroundColor=errorbg;
			return cName + "日期格式不符!請輸入正確的民國日期(YYYMMDD)\n";
		}
	}
	if (sFlag) obj.style.backgroundColor="";
    return "";
}
//*********************************************
//函數功能：檢查資料年月是否正確(YYYMM)
//參　　數：column物件本身; name中文名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkYYYMM(column,name){
	columnTrim( column );
	var tDate = column.value;
	if(tDate == "") {
	    //column.style.backgroundColor=errorbg;
	    //return cName + "不允許空白!\n";
		column.style.backgroundColor="";
    	return "";	      
	}	
	if(isNaN(tDate)){
		column.style.backgroundColor=errorbg;
		return name + "日期格式不符!請輸入正確的民國日期(YYYMM)!\n";
	}
	if(column.value.length!=5){	
		column.style.backgroundColor=errorbg;	
		return name + "日期格式不符!請輸入正確的民國日期(YYYMM)!\n";
	}
	var year=Number(tDate.substring(0,3));
	var month=Number(tDate.substring(3,5));
        
    if(isNaN(year) || isNaN(month) ) {
       	column.style.backgroundColor=errorbg;
		return name + "日期格式不符!請輸入正確的民國日期(YYYMM)\n";
	} else if(year+1911 > 3000 || year+1911 < 1900) {
       	column.style.backgroundColor=errorbg;
		return name + "年份不符!\n";
	} else if(month > 12 || month < 1) {
    	column.style.backgroundColor=errorbg;
	    return name + "月份不符!\n";
    }
    column.style.backgroundColor="";
    return "";
}

//*********************************************
//函數功能：檢查資料年是否正確(YYY)
//參　　數：column物件本身; name中文名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkYYY(column,name){
	columnTrim( column );
	var tDate = column.value;
	if(tDate == "") {
		column.style.backgroundColor="";
    	return "";	      
	}
	if(isNaN(tDate)){
		column.style.backgroundColor=errorbg;
		return name + "日期格式不符!請輸入正確的民國日期(YYY)!\n";
	}
	if(column.value.length!=3){	
		column.style.backgroundColor=errorbg;	
		return name + "日期格式不符!請輸入正確的民國日期(YYY)!\n";
	}
	var year=Number(tDate);
        
    if(isNaN(year)) {
       	column.style.backgroundColor=errorbg;
		return name + "日期格式不符!請輸入正確的民國日期(YYY)\n";
	} else if(year<=0 || year+1911 > 3000 || year+1911 < 1900) {
       	column.style.backgroundColor=errorbg;
		return name + "年份不符!\n";
	}
    column.style.backgroundColor="";
    return "";
}
//*********************************************
//函數功能：檢查資料月是否正確(MM)
//參　　數：column物件本身; name中文名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkMM(column,name){
	columnTrim( column );
	var tDate = column.value;
	if(tDate == "") {
	    //column.style.backgroundColor=errorbg;
	    //return cName + "不允許空白!\n";
		column.style.backgroundColor="";
    	return "";	      
	}	
	if(isNaN(tDate)){
		column.style.backgroundColor=errorbg;
		return name + "日期格式不符!請輸入正確的民國日期(MM)!\n";
	}
	if(column.value.length!=2){	
		column.style.backgroundColor=errorbg;	
		return name + "日期格式不符!請輸入正確的民國日期(MM)!\n";
	}
	var month=Number(tDate.substring(0,2));        
    if(isNaN(month)) {
       	column.style.backgroundColor=errorbg;
		return name + "日期格式不符!請輸入正確的民國日期(MM)\n";
	} else if(month > 12 || month < 1) {
    	column.style.backgroundColor=errorbg;
	    return name + "月份不符!\n";
    }
    column.style.backgroundColor="";
    return "";
}
//*********************************************
//函數功能：檢查資料是否為24小時制的資料(0~23)
//參　　數：obj物件本身; cName中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkHour(obj,cName){
	var data1 = obj.value;
	if(data1 == "") {
		obj.style.backgroundColor="";
    	return "";	      
	}	
	if(isNaN(data1)){
		obj.style.backgroundColor=errorbg;
		return cName+"必需為(0~23)!\n";
	}	
	var n1=Number(data1);
	var n2=Math.floor(n1);
	if (isNaN(n1)||(n1<0)||(n1!=n2)||(n1>23)){
       	obj.style.backgroundColor=errorbg;
		return cName+"必需為(0~23)!\n";
	}
	if (data1.length==1) { obj.value = "0" + data1; }
	obj.style.backgroundColor="";
    return "";
}

//*********************************************
//函數功能：檢查資料是否為分鐘的資料(0~59)
//參　　數：obj物件本身;cName中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkMinute(obj,cName){
	var data1 = obj.value;
	if(data1 == "") {
		obj.style.backgroundColor="";
    	return "";	      
	}	
	if(isNaN(data1)){
		obj.style.backgroundColor=errorbg;
		return cName+"必需為(0~59)!\n";
	}	
	var n1=Number(data1);
	var n2=Math.floor(n1);
	if (isNaN(n1)||(n1<0)||(n1!=n2)||(n1>59)){
       	obj.style.backgroundColor=errorbg;
		return cName+"必需為(0~59)!\n";
	}
	if (data1.length==1) { obj.value = "0" + data1; }
	obj.style.backgroundColor="";
    return "";
}

//*********************************************
//函數功能：檢查Email格式是否正確
//參　　數：obj物件本身;cName中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkEmail(obj,cName){
	var data1=obj.value;
	var len = data1.length;
	var errflag=0;
	if (len==0) {return "";}   //如果空白不檢查
	for(var i=0;i<len;i++){
		var c=data1.charAt(i);
		if(!((c>="A"&&c<="Z")||(c>="a"&&c<="z")||(c>="0"&&c<="9")||(c=="-")||(c=="_")||(c==".")||(c=="@"))){
			errflag=1;
		}
	}
	if((data1.indexOf("@")==-1)||(data1.indexOf("@")==0)||(data1.indexOf("@")==(len-1))){
		errflag=1;
	}else if((data1.indexOf("@")!=-1)&&(data1.substring(data1.indexOf("@")+1,len).indexOf("@")!=-1)){
		errflag=1;
	}else if((data1.indexOf(".")==-1)||(data1.indexOf(".")==0)||(data1.lastIndexOf(".")==(len-1))){
		errflag=1;
	}      
	if (errflag==1){
       	obj.style.backgroundColor=errorbg;
		return cName+"請輸入正確Email格式!\n";	
	}	
	obj.style.backgroundColor="";
    return "";
}

//*********************************************
//函數功能：判斷檢查身分証號碼格式是否正確
//參　　數：obj物件本身;cName中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkPersonalID(obj,cName){
	columnTrim( obj );
	var data1=obj.value;
	var intIdLength=data1.length;
	var strIdFirst=data1.charAt(0).toUpperCase();
	var strIdSecond=data1.charAt(1);
	var strIdNum=data1.substr(1,9);
	
	if (data1.length==0) {
		//obj.style.backgroundColor=errorbg;
		//return cName + "不可以空白！\n";
        obj.style.backgroundColor="";
        return "";		
	}   
	
	var errflag=0;
	if (intIdLength != 10){ 
		errflag=1; 
	}else if (strIdFirst<'A' || strIdFirst>'Z'){ 
		errflag=1; 
	}else if (strIdSecond != '1' && strIdSecond != '2'){ 
		errflag=1; 
	}				
	for (var i=0;i<=8;i++){
		if (isNaN(strIdNum.substr(i,1))){ errflag=1; }
	}
	if (errflag==1){
		obj.style.backgroundColor=errorbg;
		return cName + "請輸入正確格式！!\n";
	}	
		     
	var intVerify1=("ABCDEFGHJKLMNPQRSTUVXYWZIO".indexOf(strIdFirst,0))+10;
	data1=""+intVerify1+strIdNum;
	var intVerify2=eval(data1.substr(0,1));	
	for (i=1;i<=9;i++){
		intVerify2=intVerify2+(eval(data1.substr(i,1))*(10-i));
	}
	intVerify2=intVerify2+eval(data1.substr(10,1));
	
	if (intVerify2%10==0){
		obj.style.backgroundColor="";
		return "";
	}else{
		obj.style.backgroundColor=errorbg;
		return cName + "請輸入正確格式！!\n";
	}
	obj.style.backgroundColor="";
	return "";
}

//*********************************************
//函數功能：營利事業統一編號檢查
//參　　數：column物件本身;name中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkCompID(column,name) { 
	columnTrim( column );
	var CompID = column.value;
	var wk1, wk2, wk3, wk4, wk5, wk6, wk7, wk8, wk9, wk10, wk_total, wk_check; 

	if (CompID.length==0) {
		//column.style.backgroundColor=errorbg;
		//return  name+"不可以空白！\n";
    	column.style.backgroundColor="";
    	return "";		
	}   
	
	if ( CompID.length != 8 ){
		column.style.backgroundColor=errorbg;
		return  name+"統一編號格式不符!\n";
	}

	for ( var i = 0 ; i < 8 ; i++ ) { 
		nNum = CompID.substr(i,1); 
		if ( nNum < '0' || nNum > '9' ) { 
			column.style.backgroundColor=errorbg;
			return  name+"統一編號格式不符!\n"; 
		} 
	} 

	wk1 = parseInt(CompID.substr(0,1)) + parseInt(CompID.substr(2,1)) + parseInt(CompID.substr(4,1)) + parseInt(CompID.substr(7,1)); 
	wk2 = Math.floor(parseInt(CompID.substr(1, 1)) * 2 / 10); 
	wk3 = Math.floor(parseInt(CompID.substr(3, 1)) * 2 / 10); 
	wk4 = Math.floor(parseInt(CompID.substr(5, 1)) * 2 / 10); 
	wk5 = Math.floor(parseInt(CompID.substr(6, 1)) * 4 / 10); 
	wk6 = parseInt(CompID.substr(1, 1)) * 2 % 10; 
	wk7 = parseInt(CompID.substr(3, 1)) * 2 % 10; 
	wk8 = parseInt(CompID.substr(5, 1)) * 2 % 10; 
	wk9 = parseInt(CompID.substr(6, 1)) * 4 % 10; 
	wk10 = Math.round(wk5 + wk9 / 10); 
	wk_total = wk1 + wk2 + wk3 + wk4 + wk5 + wk6 + wk7 + wk8 + wk9; 
	wk_check = wk_total % 10; 

	if (wk_check == 0) { 
		return ""; 
	} else { 
		if (CompID.substr(6,1) == '7') { 
		wk_total = wk1 + wk2 + wk3 + wk4 + wk6 + wk7 + wk8 + wk10; 
		wk_check = wk_total % 10; 
			if (wk_check == 0) { 
				return ""; 
			} else { 
				column.style.backgroundColor=errorbg;
				return  name+"統一編號格式不符!\n";
			} 
		} 
	} 
    column.style.backgroundColor="";
    return "";
}

//*********************************************
//函數功能：郵局局帳號之檢查
//參　　數：column物件本身;cName欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//規則:
//A = [ ((第1碼X2)+(第2碼X3)+(第3碼X4)+(第4碼X5)+(第5碼X6)+(第6碼X7))/11 ]的餘數
//B = 11 - A ,if B = 11 then 檢查碼 = 1 else if B = 10 then 檢查碼 = 0 else 檢查碼 = B
//*********************************************
function checkBankID(column,cName) { 
	columnTrim( column );
	var BankID = column.value;
	var BankSum=0;
	if (BankID.length==0) {
		//column.style.backgroundColor=errorbg;
		//return  cName +"不可以空白！\n";
	    column.style.backgroundColor="";
    	return "";		
	}   	
	if (BankID.length!=7) {
		column.style.backgroundColor=errorbg;
		return cName + "應為7碼！\n";
	}
    for(var i=0;i<6;i++){
	     if(BankID.charAt(i).match(/[0-9]/)==null) {
	     	column.style.backgroundColor=errorbg;
	       return cName +"格式錯誤！\n";
	     }else {
	        BankSum+=parseInt(BankID.charAt(i),10)*(i+2);
	     }
    }
    if((11-BankSum%11)%10 != BankID.substr(6,1)){
	     column.style.backgroundColor=errorbg;
	     return cName +"格式錯誤！\n";
    }
	return "";
}

//*********************************************
//函數功能：檢查可以輸入的中文字長度
//參　　數：column物件本身;name中文欄位名稱;maxlen最大長度
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkLen(column, name, maxlen){
   var strlen = 0 ;
   columnTrim(column);
   strlen = GetLength (column.value) ;
   if (strlen > maxlen) {
      column.select() ;      
      column.focus() ;
      return name + "長度超過限制,請刪減字數.\n(限"+maxlen+"個中文字)";
   }
   column.style.backgroundColor="";
   return "" ;
}

//*********************************************
//函數功能：檢查可以輸入的列數
//參　　數：column物件本身;name中文欄位名稱;maxRow最大列數
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkTextAreaRows(column, name, maxRow){
	var flag=true;
	
	if(isObj(column) && (column.type=="textarea")){
	   var re=new RegExp("\n","g");
	   var result = column.value.match(re);
	   if(result==null) {
	      flag= false;
	   } else {
	   	  //var rows=(column.rows) ? column.rows :  5 ;
	   	  //if (isObj(maxRow) && maxRow>0) rows = maxRow;	      
	      if(result.length < column.rows-1){
	         flag=false;
	      }
	   }
	}
	if (flag) {	
	    column.select() ;      
	    column.focus() ;		
		column.style.backgroundColor=errorbg;
		return name + "的列數超過限制,請刪減列數.(限"+maxRow+"列)\n";
	}
	column.style.backgroundColor="";
	return "";
} 

//*********************************************
//函數功能：取得中文字長度
//參　　數：s資料字串
//傳 回 值：傳回中文字長度
//*********************************************
function GetLength (s){
   var strlen = s.length ;
   var c = '' ;
   var i ;
   var j = 0 ;
 
   if (strlen > 0) {
      for (i = 0, j = 0; i < strlen; i++) {
         c = escape(s.charAt(i)) ;
         if (c.charAt(0) == '%') {
            if (c.length == 3) j++ ;
            else               j += 1 ;
         }
         else j++ ;
      }
      return j ;
   }
   else return 0 ;
}


//*********************************************
//函數功能：欄位去頭尾空白
//參　　數：column物件本身;
//傳 回 值：去頭尾空白後之字串
//*********************************************
function columnTrim( column ){
	var S = column.value ;
	if(S.length>0){
		while(S.substring(0,1)==" "){
			S = S.substring(1,S.length);
		}
		while(S.substring(S.length-1,S.length)==" "){
			S = S.substring(0,(S.length-1));
		}
	}
	column.value = S ;
}
//*********************************************
//函數功能：字串去頭尾空白~ ( 字串 ) 
//參　　數：column物件本身;
//傳 回 值：去頭尾空白後之字串
//*********************************************
function AllTrim(column){
	var S = column.value;
	if(S.length>0){
		while(S.substring(0,1)==" "){
			S = S.substring(1,S.length);
		}
		while(S.substring(S.length-1,S.length)==" "){
			S = S.substring(0,(S.length-1));
		}
	}
	return S;
}

//再一個去空白
function parse(str) {
	var rStr="";	
	var ch;
	if (str!=null && str!="" && str.length>0) {
		for(var i=0;i<str.length;i++) {
			ch = str.charAt(i);
			if(ch!=" ")
			rStr+=str.charAt(i);
		}
	}
	return rStr;
}

//去除逗號 eg. 100,999.99 --> 100999.99
function parseComma(str) {
	var rStr="";
	var ch;
	if (str!=null && str!="" && str.length>0) {
		for(var i=0;i<str.length;i++){
			ch = str.charAt(i);
			if(ch!=",") rStr+=str.charAt(i);
		}
	}
	return rStr;
}


//*********************************************
//函數功能：檢查日期訖是否大於起
//參　　數：objDateS,objDateE為起訖日期物件本身;objCName中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkDateSE( objDateS, objDateE, objCName ){
	var sdate, edate;
	var dates, datee;
	try {
		if (isObj(objDateS.value)) sdate = parse(objDateS.value);
		else sdate = parse(objDateS);	
		if (isObj(objDateE.value)) edate = parse(objDateE.value);
		else edate = parse(objDateE);
		if(sdate.length==7 && edate.length==7){
			dates = new Date(parseInt(sdate.substring(0,3),10)+1911, sdate.substring(3,5)-1, sdate.substring(5,7));
			datee = new Date(parseInt(edate.substring(0,3),10)+1911, edate.substring(3,5)-1, edate.substring(5,7));
			if (dates > datee) {
				objDateS.style.backgroundColor=errorbg;
				objDateE.style.backgroundColor=errorbg;
				return objCName + "不合邏輯!\n";
			} else {
				return "";
			}
		} else {
			return "";
		}
	} catch(e) {return "";}
}

//*********************************************
//函數功能：檢查日期訖是否大於起
//參　　數：objDateS,objDateE為起訖日期物件本身或value;type為條件，errorMsg為錯誤訊息
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkDateSE2( objDateS, objDateE, type, errorMsg){
	var sdate, edate;
	var dates, datee;
	try {
		if (isObj(objDateS.value)) sdate = parse(objDateS.value);
		else sdate = parse(objDateS);	
		if (isObj(objDateE.value)) edate = parse(objDateE.value);
		else edate = parse(objDateE);	
		if(sdate.length==7 && edate.length==7){
			dates = new Date(parseInt(sdate.substring(0,3),10)+1911, sdate.substring(3,5)-1, sdate.substring(5,7));
			datee = new Date(parseInt(edate.substring(0,3),10)+1911, edate.substring(3,5)-1, edate.substring(5,7));
			if(type=="ge"){
				if (dates > datee) {
					if (isObj(objDateS.value)) objDateS.style.backgroundColor=errorbg;
					if (isObj(objDateE.value)) objDateE.style.backgroundColor=errorbg;
					return errorMsg+"\n";
				} else {
					return "";
				}
			} else if(type=="le"){
				if (dates < datee) {
					if (isObj(objDateS.value)) objDateS.style.backgroundColor=errorbg;
					if (isObj(objDateE.value)) objDateE.style.backgroundColor=errorbg;
					return errorMsg+"\n";
				} else {
					return "";
				}
			} else if(type=="gt"){
				if (dates >= datee) {
					if (isObj(objDateS.value)) objDateS.style.backgroundColor=errorbg;
					if (isObj(objDateE.value)) objDateE.style.backgroundColor=errorbg;
					return errorMsg+"\n";
				} else {
					return "";
				}
			} else if(type=="lt"){
				if (dates <= datee) {
					if (isObj(objDateS.value)) objDateS.style.backgroundColor=errorbg;
					if (isObj(objDateE.value)) objDateE.style.backgroundColor=errorbg;
					return errorMsg+"\n";
				} else {
					return "";
				}
			} else {
				return "";
			}
		} else {
			return "";
		}
	} catch(e) {return "";}
}

//*********************************************
//函數功能：檢查年度訖是否大於起
//參　　數：objYearS,objYearE為起訖日期物件本身;objCName中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkYearSE( objYearS, objYearE, objCName ){
	var sYear, eYear;
	var Years, Yeare;	
	try {
		if (isObj(objYearS.value)) sYear = parse(objYearS.value);
		else sYear = parse(objYearS);
		if (isObj(objYearE.value)) eYear = parse(objYearE.value);
		else eYear = parse(objYearE);
		if(sYear.length==3 && eYear.length==3){
			sYear +="0101";
			eYear +="1231";
			Years = new Date(parseInt(sYear.substring(0,3),10)+1911, sYear.substring(3,5)-1, sYear.substring(5,7));
			Yeare = new Date(parseInt(eYear.substring(0,3),10)+1911, eYear.substring(3,5)-1, eYear.substring(5,7));
			if (Years > Yeare) {
				objYearS.style.backgroundColor=errorbg;
				objYearE.style.backgroundColor=errorbg;
				return objCName + "不合邏輯!\n";
			} else {
				return "";
			}
		} else {
			return "";
		}
	} catch(e) {return "";}
}

//*********************************************
//函數功能：設定欄位為唯讀或取消唯讀
//參　　數：arrField為一欄位名稱陣列，strOption可以是R(readonly)或O(open),預設為R
//傳 回 值：無
//*********************************************
function setFormField(arrField,strOption) {
	var obj = null;
	if (isObj(arrField)) {
		if (strOption=="O") {
		 	for(var i=0; i<arrField.length; i++){  			
		 		if (isObj(document.all.item(arrField[i]))) {		 		  	
					obj = document.all.item(arrField[i]);
					if (like(obj.className,"RO")){			
						obj.style.backgroundColor=editbg;			
			        	obj.readOnly = true;			
					}else{		
						if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){					
							obj.style.backgroundColor=editbg;			
				        	obj.readOnly = false;			
						}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
							obj.style.backgroundColor=editbg;
							obj.disabled = false;
			    	  	}else if (obj.type=="button"){
				    	  	obj.disabled = false;
			    	  	}
			   	  	}
				}
			}
		} else {
		 	for(var i=0; i<arrField.length; i++){			
		 		if (isObj(document.all.item(arrField[i]))) {		 	
					obj = document.all.item(arrField[i]);
					if (like(obj.className,"RO")){			
						obj.style.backgroundColor=readbg;			
			        	obj.readOnly = true;			
					}else{		
						if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){					
							obj.style.backgroundColor=readbg;			
				        	obj.readOnly = true;			
						}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
							obj.style.backgroundColor=readbg;
							obj.disabled = true;
			    	  	}else if (obj.type=="button"){
				    	  	obj.disabled = true;
			    	  	}
			   	  	}
				}
			}
		}
	}
}

//*********************************************
//函數功能：設定欄位為唯讀或取消唯讀
//參　　數：strField為欄位名稱集合(e.g. enterOrg,ownership,propertyNo)，strOption可以是R(readonly)或O(open),預設為R
//傳 回 值：無
//*********************************************
function setFormItem(strField, strOption) {	
	if (parse(strField).length>0) {
		var obj = null;
		var arrField = strField.split(",");
		if (arrField.length>0) {
		 	for(var i=0; i<arrField.length; i++){
		 		if (isObj(document.all.item(arrField[i]))) {		 		  	
					obj = document.all.item(arrField[i]);
					if (strOption=="O") {
						if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){					
							obj.style.backgroundColor=editbg;		
					    	obj.readOnly = false;			
						}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
							obj.style.backgroundColor=editbg;
							obj.disabled = false;
					  	}else if ((obj.type=="button")){
						  	obj.disabled = false;
			  			}
					 } else {
						if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){					
							obj.style.backgroundColor=readbg;			
					    	obj.readOnly = true;			
						}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
							obj.style.backgroundColor=readbg;
							obj.disabled = true;
					  	}else if (obj.type=="button"){
						  	obj.disabled = true;
					  	}
					 }	
				}
			}
		}
	}
}

//*********************************************
//函數功能：設定欄位為唯讀或取消唯讀
//參　　數：strField為欄位名稱集合，strOption可以是S(show)或H(hide),預設為H
//傳 回 值：無
//*********************************************
function setDisplayItem(strField, strOption) {	
	if (parse(strField).length>0) {
		var arrField = strField.split(",");
		if (arrField.length>0) {
		 	for(var i=0; i<arrField.length; i++){
		 		if (isObj(document.all.item(arrField[i]))) {		 		  	
					var obj = document.all.item(arrField[i]);
					if (strOption=="S") {
						obj.style.display = "";
					 } else {
						obj.style.display = "none";
					 }	
				}
			}
		}
	}
}

//檢查是否為英文字母或數字函數
function checkAlphaInt(column, cname, minLen){
	var sStr = "", rStr="";
	var i=0;
	if (isObj(column.value)) sStr = parse(column.value);
	else sStr = parse(column);
	
	for(i=0;i<sStr.length;i++){
		var ch=sStr.charAt(i);
	    if((ch < "A" || ch >"Z")&&(ch < "a"||ch > "z")&&(ch < "0" || ch >"9")) return cname + "請填寫英文字母或整數！\n";
	}
	if ((minLen>0) && (minLen>sStr.length)) {		
		return cname + "字串長度至少須為"+minLen+"碼英文字母或整數!\n";
	}
	return rStr;
}

function checkInOutSubjectType(obj,cname) {
	var fullCode = null;
	if (isObj(obj.value)) {
		fullCode = obj.value;
		if (isObj(obj.style)) obj.style.backgroundColor=errorbg;
	} else fullCode = obj;
	if (fullCode!=null && fullCode.length>=8) {
		var errFlag = false;
		var code0 = fullCode.substring(0,2);
		var code1 = fullCode.substring(2,4);
		var code2 = fullCode.substring(4,6);
		var code3 = fullCode.substring(6,8);
		if (code0=="00" || code1=="00" || code2=="00") errFlag = true;
		if (fullCode.length>8) {
			if (code3=="00") errorFlag = true;
			if (errFlag==true) return "科目層級必須選至工作計劃層級";			
		} else {
			if (errFlag==true) return "科目層級必須選至細目層級";
		}		
	} else {
		return "科目代碼格式符";
	}
	if (isObj(obj.style)) obj.style.backgroundColor="";
	return "";
}

//*********************************************
//函數功能：檢查特殊字元
//參　　數：allobj集合物件
//傳 回 值：假如正確是傳回true;假如錯誤則傳回false
//*********************************************
function checkSpecialChar(objForm){	
	//var check=new Array("'","\"","&","%","+","$","?","<>","=","<",">","\\","|","~","_");
	var errflag=0;
	var data1="";	
	if (objForm!=null && isObj(objForm)) {
		try {
			var arrObj = objForm.elements;
			var arrLen = arrObj.length;			
			for( var i=0; i<arrLen; i++){
				var obj = arrObj[i];
				if ((obj.type=="text")||(obj.type=="textarea")){
					data1 = obj.value;
					if (data1.indexOf("'")!=-1) { errflag=1;}
					if (data1.indexOf("\"")!=-1){ errflag=1;}
					if (errflag==1){
						alert("[ ' ][ \" ]為特殊字元，禁止輸入！");
						obj.select();
						return false;
					}
				}
			}
		} catch (e) {}
	}
	return true;		
}

function setErrBg(obj) {
	obj.style.backgroundColor=errorbg;	
}

function isAnyChecked(objForm,cbName) {
	var arrObj = objForm.elements;
	var arrLen = arrObj.length;
	for (var i=0; i<arrLen; i++) {
	    var e = arrObj[i];
	    if (e.name==cbName && e.checked) return true;	    
	}
	return false;
}

//檢查是否為英文字母
function checkAlpha(column, cname, ul){
	var sStr = "", rStr="";
	var i=0;
	if (isObj(column.value)) sStr = parse(column.value);
	else sStr = parse(column);
	
	for(i=0;i<sStr.length;i++){
		var ch=sStr.charAt(i);
		if(ul!=null&&"U"==ul){
			if(ch < "A" || ch >"Z"){
				return cname + "請填寫大寫英文字母！\n";
			}
		}else if(ul!=null&&"L"==ul){
			if(ch < "a"||ch > "z") {
				return cname + "請填寫小寫英文字母！\n";
			}
		}else{
			if((ch < "A" || ch >"Z")&&(ch < "a"||ch > "z")) return cname + "請填寫英文字母！\n";
		}
	}
	return rStr;
}

//*********************************************
//函數功能：檢查一組RadioButton欄位是否有選擇其一
//參　　數：column為RadioButton系列;name中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkRadioButton( column, name )
{
	var result = name + "請選擇其中一項!\n";

	for(var i=0;i<column.length;i++)
	{
		if(column[i].checked)
		{
			result="";
			//return "";
		}
	}
	
	if(result=="")
	{
		for(var i=0;i<column.length;i++)
		{
			column[i].style.backgroundColor="";	
		}
	}
	else
	{
		for(var i=0;i<column.length;i++)
		{
			column[i].style.backgroundColor=errorbg;	
		}
	}	
	
	
	return result;
}

//*********************************************
//函數功能：檢查一組RadioButton欄位是否有選擇其一
//參　　數：column為RadioButton系列;name中文欄位名稱
//傳 回 值：假如未選擇是傳回空字串;假如有選擇則傳回錯誤訊息
//*********************************************
function checkMustRadioButton( column, name ){
	var result = name + "不可選擇其中一項!\n";
	for(var i=0;i<column.length;i++){
		if(column[i].checked){
			return result;
		}
	}
	return "";
}

//*********************************************
//函數功能：檢查一組checkBox欄位是否有選擇其一
//參　　數：column為checkBox系列;name中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkButton(column, name ) 
{
	var result = name + "請選擇其中一項!\n";
	for(var i=0;i<column.length;i++)
	{
		if(column[i].checked)
		{
			result="";
			//return "";
		}
	}
	
	if(result=="")
	{
		for(var i=0;i<column.length;i++)
		{
			column[i].style.backgroundColor="";	
		}
	}
	else
	{
		for(var i=0;i<column.length;i++)
		{
			column[i].style.backgroundColor=errorbg;	
		}
	}	
	
	
	return result;
}

//*********************************************
//函數功能：檢查一組RadioBox選擇選項為"其他"時，需必填說明欄位
//參　　數：column1:Radio物件,code:選項其他的value值,column2:檢核必填欄位物件,name中文欄位名稱
//傳 回 值：假如正確是傳回空字串;假如錯誤則傳回錯誤訊息
//*********************************************
function checkRadioOtherEmpty(column1,code,column2,name){
	var result ="";
	var v1 ="";
	var len = column1.length;	
	for (var i=0; i<len; i++)
	{
	   if (column1[i].checked)
	   {
	      v1 = column1[i].value;
	   }	   
	   if(v1 == code){ 
			result += checkEmpty(column2, name);
			return result;
	   }
	}
	if(result=="") column2.style.backgroundColor="";
	return result;
}

//*********************************************
//函數功能：檢核身分證字號
//參　　數：身分證字號value
//傳 回 值：假如正確是傳回true；錯誤傳回false
//*********************************************
function checkIdNum( id ) {	  
	tab = "ABCDEFGHJKLMNPQRSTUVXYWZIO";  
	A1 = new Array (1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3 );	  
	A2 = new Array (0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5 );	  
	Mx = new Array (9,8,7,6,5,4,3,2,1,1);	  
	if ( id.length != 10 ) return false;
	var i = tab.indexOf(id.charAt(0));
	if (i == -1) return false;	  
	sum = A1[i] + A2[i]*9;
	for (var i=1; i<10; i++ ) {	    
		var v = parseInt( id.charAt(i) );
	    if ( isNaN(v) ) return false;
	    sum = sum + v * Mx[i];	  
	}	
	if ( sum % 10 != 0 ) return false;	  
	return true;
}
