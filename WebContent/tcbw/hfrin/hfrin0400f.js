var idDRCount = 0;
var idFileCount = 0;
var win = null;
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

// 健康食品未達宣稱保健功效 - 簡表、一般表
function chUnHealthIsYes(val){
	if(val == "Y"){
		document.all.item("unHealthBrief").disabled = false;
	}else if(val == "N"){
		document.all.item("unHealthBrief").value = "";
		document.all.item("unHealthBrief").disabled = true;
	}else{
		document.all.item("unHealthBrief").disabled = true;
	}
}

// 是否有就醫 - 簡表
function chIsMedical(val){
	if(val == "Y"){
		document.all.item("medicalDate").disabled = false;
		document.all.item("hospital").disabled = false;
		document.all.item("btn_medicalDate").disabled = false;
	}else if(val == "N"){
		document.all.item("medicalDate").value = "";
		document.all.item("medicalDate").disabled = true;
		document.all.item("hospital").value = "";
		document.all.item("hospital").disabled = true;
		document.all.item("btn_medicalDate").disabled = true;
	}else{
		document.all.item("medicalDate").disabled = true;
		document.all.item("hospital").disabled = true;
		document.all.item("btn_medicalDate").disabled = true;
	}
}

// 同時服用西醫
function chIsWhileMedicine(val){
	if(val == "Y"){
		document.all.item("westDrugName").disabled = false;
	}else if(val == "N"){
		document.all.item("westDrugName").value = "";
		document.all.item("westDrugName").disabled = true;
	}else{
		document.all.item("westDrugName").disabled = true;
	}
}

// 同時服用中醫	
function chIsWhileCMedicine(val){
	if(val == "Y"){
		document.all.item("eastDrugName").disabled = false;
	}else if(val == "N"){
		document.all.item("eastDrugName").value = "";
		document.all.item("eastDrugName").disabled = true;
	}else{
		document.all.item("eastDrugName").disabled = true;
	}
}

// 同時食用其他錠、塑膠型食品	
function chIsWhileOther(val){
	if(val == "Y"){
		document.all.item("productName").disabled = false;
	}else if(val == "N"){
		document.all.item("productName").value = "";
		document.all.item("productName").disabled = true;
	}else{
		document.all.item("productName").disabled = true;
	}
}

// 曾食用同類健康食品之經驗 - 一般表
function chAgainEatingHealthFood(val){
	if(val == "Y"){
		document.all.item("healthFoodName").disabled = false;
		document.all.item("againOtherNonResponse").disabled = false;
	}else{
		document.all.item("healthFoodName").disabled = true;
		document.all.item("againOtherNonResponse").disabled = true;
		document.all.item("healthFoodName").value = "";
		document.all.item("againOtherNonResponse").value = "";
	}
}

// 藥物過敏史
function chDrugAllergies(val){
	if(val == "Y"){
		document.all.item("drugOther").disabled = false;
	}else{
		document.all.item("drugOther").value = "";
		document.all.item("drugOther").disabled = true;
	}
}

// 食物過敏史
function chFoodAllergies(val){
	if(val == "Y"){
		document.all.item("foodOther").disabled = false;
	}else{
		document.all.item("foodOther").value = "";
		document.all.item("foodOther").disabled = true;
	}
}

// 疾病史
function chDiseaseHistory(){
	var flag = false;
	var tmp = document.all.item("diseaseHistory");
	for(var i=0; i<tmp.length; i++){
		if(tmp[i].checked){
			if(tmp[i].value == "04"){
				flag = true;
			}
		}
		if(flag){
			break;
		}
	}
	if(flag){
		document.all.item("diseaseOther").disabled = false;
	}else{
		document.all.item("diseaseOther").value = "";
		document.all.item("diseaseOther").disabled = true;
	}
}

// 生活史
function chLifeHistory(){
	var flag = false;
	var tmp = document.all.item("lifeHistory");
	for(var i=0; i<tmp.length; i++){
		if(tmp[i].checked){
			if(tmp[i].value == "04"){
				flag = true;
			}
		}
		if(flag){
			break;
		}
	}
	if(flag){
		document.all.item("lifeOther").disabled = false;
	}else{
		document.all.item("lifeOther").value = "";
		document.all.item("lifeOther").disabled = true;
	}
}

