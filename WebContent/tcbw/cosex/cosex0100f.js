var win = null;
var idFileCount = 0;
function openCenterWindow(wid, hei, url){
	var prop = '';
	var w = (screen.width-wid)/2;
	var h = (screen.height-hei)/2;
	prop = prop + 'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes';
	prop = prop + ',width=' + wid;
	prop = prop + ',height=' + hei;
	prop = prop + ',left=' + w;
	prop = prop + ',top=' + h;
	if(win != null){
		win.close();
	}
	win = window.open(url,'',prop);
}

// FOR COS4001_DB、COS0001_DB畫面的變動
function chNotifierSource(val){
	if(val == "07"){
		form1.notifierSourceOther.disabled = false;
	}else{
		form1.notifierSourceOther.value = "";
		form1.notifierSourceOther.disabled = true;
	}
}

// 販賣通路
function chTraffickWay(val){								
	if(val == "10"){
		form1.traffickWayOther.disabled = false;
	}else{
		form1.traffickWayOther.value = "";
		form1.traffickWayOther.disabled = true;
	}
}

// 是否已送交相關單位處理
function chIsOtherDeptYn(val){								
	if(val == "Y"){
		form1.otherDpetName.disabled = false;
	}else{
		form1.otherDpetName.value = "";
		form1.otherDpetName.disabled = true;
	}
}

// 不良反應狀況
function chAdverseCondition(val){							
	if(val == "06"){
		form1.nonSeriousOther.disabled = false;
	}else{
		form1.nonSeriousOther.value = "";
		form1.nonSeriousOther.disabled = true;
	}
}


function chkPermitProdData(){
	var permitKey = getObjectValue("permitKey");
	var permitNo = getObjectValue("permitNo");
	if(null != permitKey && "" != permitKey && null != permitNo && "" != permitNo){
		var x = getRemoteData("../../ajax/jsonDrgObjects.jsp", "&permitKey="+permitKey+"&permitNo="+permitNo );
		if(x == null || x.length == 0){
			alert("輸入的許可證號不存在!!");
			setObjectValue("permitKey","");
			setObjectValue("permitNo","");
		}
	}
	
}

function popCos4001(id){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popCos4001.jsp?cos0001Id="+id,"",prop);
}

function report()
{
	form1.action = "cosin0402p.jsp" ;
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "" ;
	form1.target = "";
}
function chgSubCode(){		
	var subCodes = form1.subCode;
	for(var i=0; i<subCodes.length; i++){
		if("99" == subCodes[i].value.substring(4,2)){
			if (subCodes[i].checked) {
				document.getElementById('otherDescribe'+subCodes[i].value).disabled = false;
			}else{
				document.getElementById('otherDescribe'+subCodes[i].value).disabled = true;
			}
		}
		
	}
}


