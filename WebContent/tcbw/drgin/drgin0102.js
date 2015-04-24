

function changeTab(tabId) 
{
	if (isObj(document.all.item("t1")))
	{	
	  document.getElementById("t1").className = "tab_border2";
	  document.getElementById("aTab1").className = "";
	  document.getElementById("Tab1").style.display = 'none';
	}
	if (isObj(document.all.item("t2")))
	{	
	  document.getElementById("t2").className = "tab_border2";
	  document.getElementById("aTab2").className = "";
	  document.getElementById("Tab2").style.display = 'none';
	}
	if (isObj(document.all.item("t3")))
	{	
	  document.getElementById("t3").className = "tab_border2";
	  document.getElementById("aTab3").className = "";
	  document.getElementById("Tab3").style.display = 'none';
	}
	if (isObj(document.all.item("t4")))
	{	
	  document.getElementById("t4").className = "tab_border2";
	  document.getElementById("aTab4").className = "";
	  document.getElementById("Tab4").style.display = 'none';
	}
	if (isObj(document.all.item("t5")))
	{	
	  document.getElementById("t5").className = "tab_border2";
	  document.getElementById("aTab5").className = "";
	  document.getElementById("Tab5").style.display = 'none';
	}
	if (isObj(document.all.item("t6")))
	{	
	  document.getElementById("t6").className = "tab_border2";
	  document.getElementById("aTab6").className = "";
	  document.getElementById("Tab6").style.display = 'none';
	}
	if (isObj(document.all.item("t7")))
	{	
	  document.getElementById("t7").className = "tab_border2";
	  document.getElementById("aTab7").className = "";
	  document.getElementById("Tab7").style.display = 'none';
	}
	if (isObj(document.all.item("t8")))
	{	
	  document.getElementById("t8").className = "tab_border2";
	  document.getElementById("aTab8").className = "";
	  document.getElementById("Tab8").style.display = 'none';
	}
	if (isObj(document.all.item("t9")))
	{	
	  document.getElementById("t9").className = "tab_border2";
	  document.getElementById("aTab9").className = "";
	  document.getElementById("Tab9").style.display = 'none';
	}
	if (isObj(document.all.item("t10")))
	{	
	  document.getElementById("t10").className = "tab_border2";
	  document.getElementById("aTab10").className = "";
	  document.getElementById("Tab10").style.display = 'none';
	}
	if (isObj(document.all.item("t11")))
	{	
	  document.getElementById("t11").className = "tab_border2";
	  document.getElementById("aTab11").className = "";
	  document.getElementById("Tab11").style.display = 'none';
	}
	if (isObj(document.all.item("t12")))
	{	
	  document.getElementById("t12").className = "tab_border2";
	  document.getElementById("aTab12").className = "";
	  document.getElementById("Tab12").style.display = 'none';
	}
	

	if (isObj(document.all.item("changeTabValue")))
	{
		document.all.item("changeTabValue").value=tabId;
	}

	if(tabId == "2")
	{
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";
		form1.tabId.value='2';
	}
	else if(tabId == "3"){
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";
		form1.tabId.value='3';
	}
	else if(tabId == "4"){
		document.getElementById("t4").className = "tab_border1";	
		document.getElementById("Tab4").style.display = '';
		document.getElementById("aTab4").className = "text_w";
		form1.tabId.value='4';
	}
	else if(tabId == "5"){
		document.getElementById("t5").className = "tab_border1";	
		document.getElementById("Tab5").style.display = '';
		document.getElementById("aTab5").className = "text_w";
		form1.tabId.value='5';
	}
	else if(tabId == "6"){
		document.getElementById("t6").className = "tab_border1";	
		document.getElementById("Tab6").style.display = '';
		document.getElementById("aTab6").className = "text_w";
		form1.tabId.value='6';
	}
	else if(tabId == "7"){
		document.getElementById("t7").className = "tab_border1";	
		document.getElementById("Tab7").style.display = '';
		document.getElementById("aTab7").className = "text_w";
		form1.tabId.value='7';
	}
	else if(tabId == "8"){
		document.getElementById("t8").className = "tab_border1";	
		document.getElementById("Tab8").style.display = '';
		document.getElementById("aTab8").className = "text_w";
		form1.tabId.value='8';
	}
	else if(tabId == "9"){
		document.getElementById("t9").className = "tab_border1";	
		document.getElementById("Tab9").style.display = '';
		document.getElementById("aTab9").className = "text_w";
		form1.tabId.value='9';
	}
	else if(tabId == "10"){
		document.getElementById("t10").className = "tab_border1";	
		document.getElementById("Tab10").style.display = '';
		document.getElementById("aTab10").className = "text_w";
		form1.tabId.value='10';
	}
	else if(tabId == "11"){
		document.getElementById("t11").className = "tab_border1";	
		document.getElementById("Tab11").style.display = '';
		document.getElementById("aTab11").className = "text_w";
		form1.tabId.value='11';
	}
	else if(tabId == "12"){
		document.getElementById("t12").className = "tab_border1";	
		document.getElementById("Tab12").style.display = '';
		document.getElementById("aTab12").className = "text_w";
		form1.tabId.value='12';
	}
	else
	{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";
		form1.tabId.value='1';
	}
	
}

//介接藥證資料
function permitData1(){
	var permitKey = form1.permitKey.value;
	var permitNo = form1.permitNo.value;
	var q = "&permitKey="+permitKey;
        q += "&permitNo="+permitNo;
	x = getRemoteData("../../ajax/jsonDrgObjects.jsp", q );
	if(x!=null && x.length>0){
		var json1 = JSON.parse(x);
		form1.chProduct.value = json1.obj0;  //中文品名
		form1.enProduct.value = json1.obj1;  //英文品名
		form1.applicationName.value = json1.obj2;  //申請商
		form1.manufactorName.value = json1.obj3;  //製造廠
		form1.applicationID.value = json1.obj4;  //製造廠
		form1.manufactorCountry.value = json1.obj5;  //製造廠國別
		var ingredient = json1.obj6;
		if(ingredient.length > 50) ingredient = ingredient.substr(0,50);
		form1.ingredient.value = ingredient;  //有效成分名稱
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
	popWinName = window.open("../drgex/drgex0104f.jsp?permitKey="+permitKey+"&permitNo="+permitNo,'popWinE',prop);		
}

function permitData2(id){
	var permitKey = id.substring(0,2);
	var permitNo = id.substring(2,8);
	form1.permitKey.value = permitKey;
	form1.permitNo.value = permitNo;
	permitData1();
}


function toShowMLMS()
{
	var permitKey = form1.permitKey.value;
	var permitNo = form1.permitNo.value;	
	var params = 'width=980,height=640,resizable=1,menubar=no,scrollbars=yes';
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("drgin0104f.jsp?permitKey="+permitKey+"&permitNo="+permitNo,'popWinE',params);	
}

function showNotifierData()
{
	if(document.all.item("notifier").checked)
	{
		document.all.item("notifier").disabled=true;		
		notifierOpen();		
		//document.all.item("notifierPass1").style.display="none";
		//document.all.item("notifierPass2").style.display="none";
		//document.all.item("notifierPass3").style.display="none";
		//document.all.item("notifierPass4").style.display="none";
		
		//document.all.item("notifier1").style.display="block";
		//document.all.item("notifier2").style.display="block";
		//document.all.item("notifier3").style.display="block";
		//document.all.item("notifier4").style.display="block";
		
		//記錄log  
		var url = '../../ajax/jsonCommonLogDb.jsp';				
		var q = "&code=DRG01";
			q +="&methodName=open";
			q +="&db=Drg0001Db";
			q +="&hql=select id,applNo,notifierUserID,notifierName from Drg0001Db where id="+form1.id.value;
		var xUserUpdate = getRemoteData(url,q);		
	}
}

function notifierOpen()
{
	var q = document.all.item("id").value;

    var x = getRemoteData("../../ajax/jsonDrg0001Db.jsp",q );
	
    if(x!=null && x.length>0)
    {
	  var json1 = JSON.parse(x);

	  if(isObj(document.all.item("notifierName").value))
		document.all.item("notifierName").value =json1.notifierName;

	  if(isObj(document.all.item("notifierTelArea").value))
		document.all.item("notifierTelArea").value =json1.notifierTelArea;
	  if(isObj(document.all.item("notifierTel").value))
		document.all.item("notifierTel").value =json1.notifierTel;
	  if(isObj(document.all.item("notifierTelExt").value))
		document.all.item("notifierTelExt").value =json1.notifierTelExt;

	  if(isObj(document.all.item("notifierEmail").value))
		document.all.item("notifierEmail").value =json1.notifierEmail;
	
	  //if(isObj(document.all.item("notifierCounty").value))
		//document.all.item("notifierCounty").value =json1.notifierCounty;
	  //if(isObj(document.all.item("notifierZipCode").value))
		//document.all.item("notifierZipCode").value =json1.notifierZipCode;
	  if(isObj(document.all.item("notifierAddress").value))
		document.all.item("notifierAddress").value =json1.notifierAddress;
	  
	  if(isObj(document.all.item("notifierDept").value))
			document.all.item("notifierDept").value =json1.notifierDept;
	  if(isObj(document.all.item("notifierDeptID").value))
			document.all.item("notifierDeptID").value =json1.notifierDeptID;

     }
}

function popDrg5001(id){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popDrg5001.jsp?drg0001Id="+id,"",prop);
}

function upload(type)
{
	var systemType ="";
	if(type==1){
		systemType = "DRG010001";
	}else{
		systemType = "DRG010002";
	}
	var prop='';
	prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	prop=prop+'width=850,';
	prop=prop+'height=600';
	closeReturnWindow();

    var url="../../home/popManyUploadSimple.jsp?";
        url+="fileKind=DRG";
        url+="&systemType="+systemType;
        url+="&uploadId="+form1.id.value;
        url+="&dbName=Drg0001Db";
         
	returnWindow = window.open(url,'上傳檔案',prop);
}

//子代碼: 變更主代碼  & 清除其他
function updateMainCode(subCode){
	var v = subCode.value.substr(0,2);
	if(subCode.checked==true){ //將mainCode打勾
		var len = document.getElementsByName("mainCode").length;
		for(var i=0 ; i<len ; i++ ){		
			if(document.getElementsByName("mainCode")[i].value== v ){
				document.getElementsByName("mainCode")[i].checked=true;
				break;
			}		
		}
	}else{ 
		//清除mainCode打勾
    	var clear = new Boolean(true);
    	var len = document.getElementsByName("subCode").length;
    	for(var j=0 ; j<len ; j++ ){
    		if(document.getElementsByName("subCode")[j].value.substr(0,2) == v ){
    			if(document.getElementsByName("subCode")[j].checked==true){
    				clear = false;		    			
    				break;
    			}
    		}
    	}
    	if(clear==true){
    		var len2 = document.getElementsByName("mainCode").length;
			for(var k=0 ; k<len2 ; k++ ){		
				if(document.getElementsByName("mainCode")[k].value== v ){
					document.getElementsByName("mainCode")[k].checked=false;
					break;
				}		
			}
    	}
    	//若為ZZ選項，清除其他說明
    	if("ZZ"==subCode.value.substr(2)){
    		document.getElementById("otherDescribe"+v).value="";
    	}
	}
}

//請描述: 變更主代碼
function updateMainCode2(obj){
	if(obj.value !=""){
		var len = document.getElementsByName("mainCode").length;
		var v = obj.id.replace("otherDescribe","");
		for(var i=0 ; i<len ; i++ ){		
			if(document.getElementsByName("mainCode")[i].value== v ){
				document.getElementsByName("mainCode")[i].checked=true;
				break;
			}		
		}
	}else{
		var len2 = document.getElementsByName("mainCode").length;
		var v = obj.id.replace("otherDescribe","");
		for(var k=0 ; k<len2 ; k++ ){	
			if(document.getElementsByName("mainCode")[k].value== v ){
				document.getElementsByName("mainCode")[k].checked=false;
				break;
			}		
		}
	}
}

//其他 : 變更子代碼及主代碼
function updateSubCodeZZ(obj){
	if(obj.value !=""){
		var len = document.getElementsByName("subCode").length;		
		var v = obj.id.replace("otherDescribe","")+"ZZ";
    	for(var j=0 ; j<len ; j++ ){
    		if(document.getElementsByName("subCode")[j].value == v ){
    			document.getElementsByName("subCode")[j].checked=true;
    			updateMainCode(document.getElementsByName("subCode")[j]);
				break;
    		}
    	}
	}else{
		var len = document.getElementsByName("subCode").length;
		var v = obj.id.replace("otherDescribe","")+"ZZ";
    	for(var j=0 ; j<len ; j++ ){
    		if(document.getElementsByName("subCode")[j].value == v ){
    			document.getElementsByName("subCode")[j].checked=false;
    			updateMainCode(document.getElementsByName("subCode")[j]);
				break;
    		}
    	}
	}
}

function downLoadFile(fileType, fileRoute){
	if (null != fileRoute && "" != fileRoute) {
	    var prop='';
	    prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	    prop=prop+'width=450,';
	    prop=prop+'height=160';
	    closeReturnWindow();
	    returnWindow = window.open('../../downloadFileSimple?fileType='+fileType+'&fileID='+encodeURI(encodeURI(fileRoute)),'上傳檔案',prop);
	} else {
		alert("欄位不存在,請檢查!");
		return ;
	}
}

function checkParentMainCodeHis(input){	
	if(isObj(input)){
		var id = input.id;
		var parent = $('#'+id.substring(0,5));		
		if(isObj(parent)){
			if(input.checked){
				parent.attr('checked',true);
			}else{
				var children = $('[id^='+id.substring(0,5)+']');
				var isAllUnCheck = true;
				for(var i = 0;i<children.length;i++){
					if(children[i].checked&&children[i].id!=id.substring(0,5))isAllUnCheck=false;
				}
				if(isAllUnCheck)parent.attr('checked',false);
			}
		}
	}
}
function checkChildrenSubCodeHis(input){
	if(isObj(input)){		
		var id = input.id;
		var children = $('[id^='+id.substring(0,5)+']');
		for(var i = 0;i<children.length;i++){
			children[i].checked=input.checked;
		}
	}	
}