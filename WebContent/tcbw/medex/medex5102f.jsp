<!--
程式目的：醫療器材臨床試驗不良事件通報登錄作業
程式代號：medex5102f
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medex.MEDEX5102F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

String q_status=ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("q_status")));

if("insert".equals(obj.getDoType()) && "queryOne".equals(obj.getState()))
{	
	// 由查詢頁面，到本頁時判斷，是否需新增一筆
	try
    {
		obj.doInsert();
	}
    catch(Exception e)
    {
		e.printStackTrace();
	}
}
if("pauseSave".equals(obj.getState()) || "stayedUpload".equals(obj.getState()) 
		|| "doSend".equals(obj.getState()) || "upload".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
		
		if("pauseSave".equals(obj.getState()))
		  obj.setErrorMsg("暫存成功");
		else if("stayedUpload".equals(obj.getState()))
		  obj.setErrorMsg("待上傳完成");
		else if("doSend".equals(obj.getState()))
		  obj.setErrorMsg("案件已送出");
		
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}else if("doCancelQuit".equals(obj.getState()))
{
	try
	{
		obj.doDelete();
		obj.setErrorMsg("放棄資料完成");
		obj.setIsNeedBackQuery("Y");
		obj.setId("");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("autoReConfirm".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doAutoReUpdate();
		obj.setErrorMsg("主動補件完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
obj = (com.kangdainfo.tcbw.view.medex.MEDEX5102F)obj.queryOne();
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="./../../js/jquery.js"></script>
<script type="text/javascript">

var insertDefault = new Array();


var popWinName;
function checkField()
{
	var alertStr="";


	if(form1.state.value == "stayedUpload" || form1.state.value == "doSend" )
	{
	   <%=TCBWCommon.getCheckFiled("MED03")%>
	}

	
	if(form1.state.value == "pauseSave" || form1.state.value == "upload")
	{
		form1.updateType.value = "1";
		beforeSubmit();
		form1.submit();
		return true;
	}
	else if(form1.state.value == "stayedUpload")
	{
		form1.updateType.value = "2";
	}
	else if(form1.state.value=="doSend")
	{
		form1.updateType.value = "3";
	}
	

	//基本資料頁籤
	//檢查案例來源
	if(form1.state.value == "stayedUpload" || form1.state.value=="doSend")
	{
	  var caseSource = document.getElementsByName('caseSource');
	  if(isObj(caseSource)){
		for(var i = 0 ; i < caseSource.length ; i ++){
			var caseSourceCheckBox = caseSource[i];
			if(caseSourceCheckBox.checked){
				if(caseSourceCheckBox.value == "in"){
					alertStr += checkEmpty(form1.caseSourceInHospital,"案例來源(試驗醫院)");
					alertStr += checkEmpty(form1.caseSourceInDoctor,"案例來源(試驗醫師)");
					form1.caseSourceOutCountry.style.backgroundColor = "";
				}else if(caseSourceCheckBox.value = "out"){
					alertStr += checkEmpty(form1.caseSourceOutCountry,"案例來源(國家)");
					form1.caseSourceInHospital.style.backgroundColor = "";
					form1.caseSourceInDoctor.style.backgroundColor = "";
				}
			}
		}
	  }
	  //檢查報告類別
	  var reportKind = document.getElementsByName('reportKind');
	  if(isObj(reportKind)){
		for(var i = 0 ; i < reportKind.length ; i ++){
			var reportKindCheckBox = reportKind[i];
			if(reportKindCheckBox.checked){
				var temp = "";
				if(reportKindCheckBox.value == "2"){
					 temp += checkEmpty(form1.trackingNum,"追蹤報告(次數)");
					 if(temp == "")
						 temp += checkNumber(form1.trackingNum,"追蹤報告(次數)");
				}else
					form1.trackingNum.style.backgroundColor = "";
				alertStr += temp;
			}
		}
	  }
	  //不良事件頁籤
	  //檢查死亡欄位
	  var badReactionResults = document.getElementsByName('badReactionResults');
	  if(isObj(badReactionResults)){
		for(var i = 0 ; i < badReactionResults.length ; i ++){
			var badReactionResultsCheckBox = badReactionResults[i];
			if(badReactionResultsCheckBox.checked){
				if(badReactionResultsCheckBox.value == "01"){
					alertStr += checkEmpty(form1.badReactionResultsDeathDate,"死亡(日期)");
					alertStr += checkEmpty(form1.badReactionResultsDeathReason,"死亡(死亡原因)");
				}
				if(badReactionResultsCheckBox.value == "08")
					alertStr += checkEmpty(form1.badReactionResultsOther,"非嚴重不良事件說明欄位");
			}else{
				if(badReactionResultsCheckBox.value == "01"){
					form1.badReactionResultsDeathDate.style.backgroundColor = "";
					form1.badReactionResultsDeathReason.style.backgroundColor = "";
				}
				if(badReactionResultsCheckBox.value == "08")
					form1.badReactionResultsOther.style.backgroundColor = "";
			}
		}
	  }
	  //是否可提供器材作評估	
	  var medUseIsYn = document.getElementsByName('medUseIsYn');
	  if(isObj(medUseIsYn)){
		for(var i = 0 ; i < medUseIsYn.length ; i ++){
			var medUseIsYnCheckBox = medUseIsYn[i];
			if(medUseIsYnCheckBox.checked){
				if(medUseIsYnCheckBox.value == "Y")
					alertStr += checkEmpty(form1.medYesSoruce,"是否可提供器材作評估(是，取得來源)");
				else
					form1.medYesSoruce.style.backgroundColor = "";
			}
		}
	  }
	  //曾使用同類醫材之經驗
	  var medOnceUseMed = document.getElementsByName('medOnceUseMed');
	  if(isObj(medOnceUseMed)){
		for(var i = 0 ; i < medOnceUseMed.length ; i ++){
			var medOnceUseMedCheckBox = medOnceUseMed[i];
			if(medOnceUseMedCheckBox.checked){
				if(medOnceUseMedCheckBox.value == "Y")
					alertStr += checkEmpty(form1.medOnceUseMedName,"曾使用同類醫材之經驗(醫材名稱)");
				else
					form1.medYesSoruce.style.backgroundColor = "";
			}
		}
	  }  
	  //是否同時使用
	  var sameTimeUse = document.getElementsByName('sameTimeUse');
	  if(isObj(sameTimeUse)){
		for(var i = 0 ; i < sameTimeUse.length ; i ++){
			var sameTimeUseCheckBox = sameTimeUse[i];
			if(sameTimeUseCheckBox.checked){
				if(sameTimeUseCheckBox.value == "4")
					alertStr += checkEmpty(form1.sameTimeUseOther,"是否同時使用(說明欄位)");
				else
					form1.medYesSoruce.style.backgroundColor = "";
			}
		}
	   }
	}
	
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	form1.submit();
	return true;
}


function init() 
{
	
	var tbID=document.all.item("changeTabValue").value;

    if(tbID=="")
    {    
      changeTab(1);
    }
    else
    {
	  changeTab(tbID);
    }
    
	if("<%=obj.getIsNeedBackQuery()%>" == "N" || "<%=obj.getIsNeedBackQuery()%>" == "")
	{
		if("<%=obj.getErrorMsg()%>" != "")
		{
			alert("<%=obj.getErrorMsg()%>");
		}
		if("<%=obj.getIsEnabledUpdate()%>" == "Y")
		{
			form1.state.value="update";
			setAllOpen();
		}
		else
		{	
			 setAllReadonly();
			 setFormItem("pauseSave,stayedUpload,doSend","R");
		}
	
		if("<%=obj.getDoType()%>" == "insert")
		{
			openStartAutoSave();
			//當新增時先將一些細項增加一筆新的，使用者不要就自行刪除。
			addMed5002Db();
			addMed5003Db();
			addMed5004Db();
			addMed5005Db();
		}
	}
	else if('<%=obj.getUpdateType()%>'=="1")
	{
		setAllOpen();
	}	
	else 
	{
		form1.action = "medex5101f.jsp?q_status="+'<%=q_status%>';
		form1.state.value = "queryAll";
		form1.submit();
	}
	
	<%=TCBWCommon.getIsComplete("MED03")%>
	window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	
	
}

function openStartAutoSave()
{
	if(form1.isAlreadyAutoSave.value != "Y")
	{
		form1.isAlreadyAutoSave.value = "Y";
		setTimeout("startSave()", 60000);
	}
}

function startSave()
{　
	setTimeout("startSave()", 60000);
	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit","R");

	if("<%=obj.getDoType()%>" == "insert")
	{
		setFormItem("doCancelQuit","R");
	}
	
	if("<%=obj.getDoType()%>" == "update")
	{
		setFormItem("doOpenAutoSave","R");
	}
	
	$("#spanShow").empty().append($("<font color='red'>自動儲存中</font>")).fadeIn("slow");
	
	form1.action = "medex5103f.jsp?isSave=Y";
	form1.target = "saveContainerFrame";
	beforeSubmit();
	form1.submit();
	form1.action = "medex5102f.jsp";
	form1.target = "_self";
}


function unLockAutoSaveButton()
{
	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit","O");
	if("<%=obj.getDoType()%>" == "insert")
	{
		setFormItem("doCancelQuit","O");
	}
	$("#spanShow").empty().append($("<font color='red'>自動儲存完成</font>")).fadeOut(5000);

	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit","R");

	setTimeout("open1()",5000);
}


function open1()
{
	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit","O");
}


function changeTab(tabId) 
{
	if (isObj(document.all.item("changeTabValue")))
	{
		document.all.item("changeTabValue").value=tabId;
	}
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";

	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";

	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';

	if(tabId == 2)
	{
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";		
	}
	else if(tabId == 3)
	{
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";		
	}
	else
	{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}


function checkURL(surl)
{
	var alertStr = "";	
	//alertStr += checkEmpty(form1.applyNo,"主檔編號");
	if(alertStr.length != 0){
		alert("請先執行查詢, 若已查到資料則請選取其中一筆資料");
		return false;
	} else {
		form1.state.value="";
	}
	form1.action = surl;
	beforeSubmit();
	form1.submit();
}



$(function()
{
	<%if("out".equals(User.getInORout())) {%>
	   document.all.item("notifierName").className = 'field_RO';	
	   document.all.item("notifierAreaCode").className = 'field_RO';
	   document.all.item("notifierTel").className = 'field_RO';
	   document.all.item("notifierEamil").className = 'field_RO';
	   document.all.item("notifierAddress").className = 'field_RO';
	   document.all.item("notifierTelExt").className = 'field_RO';
	<%}%>
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');

	$("#spanShow").hide();

	//setFormItem('autoReConfirm','R');

	if (isObj(document.all.item("btn_notifierUserID"))) 
	    form1.btn_notifierUserID.value="選擇通報者";

	if("<%=obj.getDoType()%>" == "update")
	{
		$(".field").bind("change",openStartAutoSave);
		$(".field[name*=btn_aFileName]").bind("click", openStartAutoSave);
		$(".field[name*=btn]").bind("click", openStartAutoSave);
		$(".field_btnAdd").bind("click", openStartAutoSave);
		$(".field_btnRemove").bind("click", openStartAutoSave);
	}

	var medPermit = form1.medPermit.value;

    if(medPermit=="Z0" || medPermit=="Z5")
    {
 		document.all.item("medTestMedical").disabled = false;
    	document.all.item("medTestMedical").className = "field";
    	document.all.item("medMainCategory").disabled = false;
    	document.all.item("medMainCategory").className = "field";
    	document.all.item("medFactory").disabled = false;
    	document.all.item("medFactory").className = "field";
    	document.all.item("medMainCategoryCodeName").disabled = false;
    	document.all.item("medMainCategoryCodeName").className = "field";
    } 
  
});




function getReply()
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1200,height=520,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/medex/medex5104f.jsp?isAdd=Y&applNo="+form1.applNo.value,"",prop);

}

function beforeInit()
{
	<%=obj.getMed5002DbItemSet()%>
	<%=obj.getMed5003DbItemSet()%>
	<%=obj.getMed5004DbItemSet()%>
	<%=obj.getMed5005DbItemSet()%>
}

//不良事件描述資訊
var numberMed5002Db = 1;
function addMed5002Db(id,bulletinDate,position,symptom,severity,dealWith)
{
	var Med5002DbCount;
	Med5002DbCount = numberMed5002Db++;		
	var newItem ="<tr id=\"med5002DbfileType"+Med5002DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med5002DbType'>";
	newItem +="<input type='hidden' name='med5002DbTypeId' value=\""+(id!=null?id:'')+"\">";
	//newItem +='<input type=text class=field_Q name=bulletinDate  id=bulletinDate' + Med5002DbCount + ' size=7 value=\'' + (bulletinDate!=null?bulletinDate:'') + '\'><input class="field_Q" type="button" name="btn_bulletinDate' + Med5002DbCount + '" onclick="popCalendar(\'bulletinDate' + Med5002DbCount + '\')" value="..." title="萬年曆輔助視窗">';
    newItem +='<input type=text class=field_Q name=bulletinDate  id=bulletinDate' + Med5002DbCount + ' size=7 value=\'' + (bulletinDate!=null?bulletinDate:'') + '\'><button class="field_Q" type="button" name="btn_bulletinDate' + Med5002DbCount + '" onclick="popCalendar(\'bulletinDate' + Med5002DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='position"+Med5002DbCount+"' class=\"field_Q\" type=\"text\" name=\"position"+"\" size=\"20\"  maxlength=\"30\"   value=\""+(position!=null?position:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='symptom"+Med5002DbCount+"' class=\"field_Q\" type=\"text\" name=\"symptom"+"\" size=\"20\"  maxlength=\"30\" value=\""+(symptom!=null?symptom:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='severity"+Med5002DbCount+"' class=\"field_Q\" type=\"text\" name=\"severity"+"\" size=\"20\"  maxlength=\"30\" value=\""+(severity!=null?severity:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='dealWith"+Med5002DbCount+"' class=\"field_Q\" type=\"text\" name=\"dealWith"+"\" size=\"20\"  maxlength=\"30\" value=\""+(dealWith!=null?dealWith:'')+"\">&nbsp;";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed5002Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med5002DbfileType"+Med5002DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed5002DbView").append(newItem);
}



var numberMed5003Db = 1;
function addMed5003Db(id,testDate,testItems,testNum)
{
	var Med5003DbCount;
	Med5003DbCount = numberMed5003Db++;	
		
	var newItem ="<tr id=\"med5003DbfileType"+Med5003DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med5003DbType'>";
	newItem +="<input type='hidden' name='med5003DbTypeId' value=\""+(id!=null?id:'')+"\">";
	//newItem +='<input type=text class=field_Q name=testDate  id=testDate' + Med5003DbCount + ' size=7 value=\'' + (testDate!=null?testDate:'') + '\'><input class="field_Q" type="button" name="btn_testDate' + Med5003DbCount + '" onclick="popCalendar(\'testDate' + Med5003DbCount + '\')" value="..." title="萬年曆輔助視窗">';
    newItem +='<input type=text class=field_Q name=testDate  id=testDate' + Med5003DbCount + ' size=7 value=\'' + (testDate!=null?testDate:'') + '\'><button class="field_Q" type="button" name="btn_testDate' + Med5003DbCount + '" onclick="popCalendar(\'testDate' + Med5003DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='testItems"+Med5003DbCount+"' class=\"field_Q\" type=\"text\" name=\"testItems"+"\" size=\"40\"  value=\""+(testItems!=null?testItems:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='testNum"+Med5003DbCount+"' class=\"field_Q\" type=\"text\" name=\"testNum"+"\" size=\"40\"  value=\""+(testNum!=null?testNum:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed5003Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med5003DbfileType"+Med5003DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed5003DbView").append(newItem);
}

//併用醫療器材資訊
var numberMed5004Db = 1;
function addMed5004Db(id,permit,permitNumber,cName,permitFirm,model,
		mainCategory,useDate,useReason,mainCategoryCode)
{
	var Med5004DbCount;
	Med5004DbCount = numberMed5004Db++;		
	var newItem ="<tr id=\"med5004DbfileType"+Med5004DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med5004DbType'>";
	newItem +="<input type='hidden' name='med5004DbTypeId' value=\""+(id!=null?id:'')+"\">";

	newItem +="<select class=\"field\" type=\"select\"  id ='permit"+Med5004DbCount+"' onChange=\"manyPermitData1('permit"+Med5004DbCount+"','permitNumber"+Med5004DbCount+"','"+Med5004DbCount+"');\"  name=\"permit"+"\" >"+"<%=View.getOptionCodeKind("MEDPKID","").replaceAll("\n","").replace("'","\\'")%>";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='permitNumber"+Med5004DbCount+"' onBlur=\"manyPermitData1('permit"+Med5004DbCount+"','permitNumber"+Med5004DbCount+"','"+Med5004DbCount+"');\" class=\"field_Q\" type=\"text\" name=\"permitNumber"+"\" size=\"6\"  maxlength=\"6\"   value=\""+(permitNumber!=null?permitNumber:'')+"\">&nbsp;";
	newItem +="<input type=\"button\" name=\"btnQryExpense\" onClick=\"manyPermitDataQ('permit"+Med5004DbCount+"','permitNumber"+Med5004DbCount+"','"+Med5004DbCount+"');\" value=\"查詢\" width=\"60px\" class=\"field\" >";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='cName"+Med5004DbCount+"' class=\"field_Q\" type=\"text\" name=\"cName"+"\" size=\"15\"  maxlength=\"30\" value=\""+(cName!=null?cName:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='permitFirm"+Med5004DbCount+"' class=\"field_Q\" type=\"text\" name=\"permitFirm"+"\" size=\"15\"  maxlength=\"30\" value=\""+(permitFirm!=null?permitFirm:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='model"+Med5004DbCount+"' class=\"field_Q\" type=\"text\" name=\"model"+"\" size=\"15\"  maxlength=\"20\" value=\""+(model!=null?model:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field\" type=\"hidden\" id='medMainCategoryCode"+Med5004DbCount+"' name=\"medMainCategoryCode\" >";
	newItem +="<input class=\"field\" type=\"text\" id ='mainCategoryCode"+Med5004DbCount+"' style=\"ime-mode: disabled;\" name=\"mainCategoryCode\" size=\"5\" maxlength=\"10\"  onchange=\"getCodeName('medMainCategoryCode"+Med5004DbCount+"','','MEDMCT',this,'mainCategory"+Med5004DbCount+"');\">";
	newItem +="<input class=\"field_RO\" type=\"text\" id='mainCategory"+Med5004DbCount+"' name=\"mainCategory\" size=\"20\" maxlength=\"50\">";
	newItem +="<input class=\"field\" type=\"button\" id='btn_medMainCategoryCode"+Med5004DbCount+"' value=\"...\" title=\"代碼輸入輔助視窗\" name=\"btn_medMainCategoryCode\" onclick=\"popCode('medMainCategoryCode"+Med5004DbCount+"','','MEDMCT','mainCategoryCode"+Med5004DbCount+"','mainCategory"+Med5004DbCount+"');\">";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	//newItem +='<input type=text class=field_Q name=useDate  id=useDate' + Med5004DbCount + ' size=7 value=\'' + (useDate!=null?useDate:'') + '\'><input class="field_Q" type="button" name="btn_useDate' + Med5004DbCount + '" onclick="popCalendar(\'useDate' + Med5004DbCount + '\')" value="..." title="萬年曆輔助視窗">';
	newItem +='<input type=text class=field_Q name=useDate  id=useDate' + Med5004DbCount + ' size=7 value=\'' + (useDate!=null?useDate:'') + '\'><button class="field_Q" type="button" name="btn_useDate' + Med5004DbCount + '" onclick="popCalendar(\'useDate' + Med5004DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='useReason"+Med5004DbCount+"' class=\"field_Q\" type=\"text\" name=\"useReason"+"\" size=\"15\"  maxlength=\"50\" value=\""+(useReason!=null?useReason:'')+"\">&nbsp;";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed5004Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med5004DbfileType"+Med5004DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed5004DbView").append(newItem);
	$("#permit"+Med5004DbCount).val(permit);
	$("#mainCategoryCode"+Med5004DbCount).val(mainCategoryCode);
	$("#mainCategory"+Med5004DbCount).val(mainCategory);
}


//併用藥品資訊
var numberMed5005Db = 1;
function addMed5005Db(id,cName,content,formulation,drgApproach,dose,frequency,sDate,eDate,medCauses)
{
	var Med5005DbCount;
	Med5005DbCount = numberMed5005Db++;		
	var newItem ="<tr id=\"med5005DbfileType"+Med5005DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med5005DbType'>";
	newItem +="<input type='hidden' name='med5005DbTypeId' value=\""+(id!=null?id:'')+"\">";
	newItem +="<input type=text class=field_Q name=cName2  id=cName2" + Med5005DbCount + " size=\"15\" maxlength=\"30\"  value=\"" + (cName!=null?cName:'') + "\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='content"+Med5005DbCount+"' class=\"field_Q\" type=\"text\" name=\"content"+"\" size=\"15\"  maxlength=\"20\"   value=\""+(content!=null?content:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='formulation"+Med5005DbCount+"' class='field_Q' name='formulation'>"+"<%=View.getOptionCodeKind("DRGFLN","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='drgApproach"+Med5005DbCount+"' class='field_Q' name='drgApproach'>"+"<%=View.getOptionCodeKind("DRG0304","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='dose"+Med5005DbCount+"' class='field_Q' name='dose'>"+"<%=View.getOptionCodeKind("DRG0305","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='frequency"+Med5005DbCount+"' class='field_Q' name='frequency'>"+"<%=View.getOptionCodeKind("DRG0306","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	//newItem +='<input type=text class=field_Q name=sDate  id=sDate' + Med5005DbCount + ' size=7 value=\'' + (sDate!=null?sDate:'') + '\'><input class="field_Q" type="button" name="btn_sDate' + Med5005DbCount + '" onclick="popCalendar(\'sDate' + Med5005DbCount + '\')" value="..." title="萬年曆輔助視窗">';
	newItem +='<input type=text class=field_Q name=sDate  id=sDate' + Med5005DbCount + ' size=7 value=\'' + (sDate!=null?sDate:'') + '\'><button class="field_Q" type="button" name="btn_sDate' + Med5005DbCount + '" onclick="popCalendar(\'sDate' + Med5005DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	//newItem +="~" + '<input type=text class=field_Q name=eDate  id=eDate' + Med5005DbCount + ' size=7 value=\'' + (eDate!=null?eDate:'') + '\'><input class="field_Q" type="button" name="btn_eDate' + Med5005DbCount + '" onclick="popCalendar(\'eDate' + Med5005DbCount + '\')" value="..." title="萬年曆輔助視窗">';
	newItem +="~" +'<input type=text class=field_Q name=eDate  id=eDate' + Med5005DbCount + ' size=7 value=\'' + (eDate!=null?eDate:'') + '\'><button class="field_Q" type="button" name="btn_eDate' + Med5005DbCount + '" onclick="popCalendar(\'eDate' + Med5005DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='medCauses"+Med5005DbCount+"' class=\"field_Q\" type=\"text\" name=\"medCauses"+"\" size=\"15\"  maxlength=\"50\" value=\""+(medCauses!=null?medCauses:'')+"\">&nbsp;";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed5005Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med5005DbfileType"+Med5005DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed5005DbView").append(newItem);
	$("#formulation"+Med5005DbCount).val(formulation);
	$("#drgApproach"+Med5005DbCount).val(drgApproach);
	$("#dose"+Med5005DbCount).val(dose);
	$("#frequency"+Med5005DbCount).val(frequency);
}

function manyPermitDataQ(medPermit1,medPermitNumber1,count)
{
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var medPermit = document.all.item(medPermit1).value;
	var medPermitNumber = document.all.item(medPermitNumber1).value;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("../medex/medex0108f.jsp?medPermit="+medPermit+"&medPermitNumber="+medPermitNumber+"&b="+medPermit1+"&c="+medPermitNumber1+"&d="+count,'popWinE',prop);		
}

function manyPermitData1(medPermit1,medPermitNumber1,count)
{

	var medPermit = document.all.item(medPermit1).value;
	var medPermitNumber = document.all.item(medPermitNumber1).value;

	var q = "&medPermit="+medPermit;
	    q += "&medPermitNumber="+medPermitNumber;

	if(medPermit!="" && medPermitNumber!="")
	{
	  x = getRemoteData("../../ajax/jsonMedPermitObjects.jsp",q );
		
	  if(x!=null && x.length>0)
	  {
		var json1 = JSON.parse(x);

		if(isObj(document.all.item("cName"+count)))
		  document.all.item("cName"+count).value = json1.obj0;  //中文品名 CHNAME

		if(isObj(document.all.item("permitFirm"+count)))
		  document.all.item("permitFirm"+count).value= json1.obj3;  //申請商  APPNAME

		if(isObj(document.all.item("mainCategory"+count)))
		  document.all.item("mainCategory"+count).value = json1.obj6;  //主類別  DOCKNDID  DOCKNDMA
		
		if(isObj(document.all.item("mainCategoryCode"+count)))
	      document.all.item("mainCategoryCode"+count).value = json1.obj5;  //主類別  DOCKNDID  DOCKNDMA
	  }
	  else
	  { 

		  document.all.item(medPermit1).value = "";
		  document.all.item(medPermitNumber1).value = "";
		  
		  if(isObj(document.all.item("cName"+count)))
			 document.all.item("cName"+count).value = "";  //中文品名 CHNAME

		  if(isObj(document.all.item("permitFirm"+count)))
			 document.all.item("permitFirm"+count).value= "";  //申請商  APPNAME

		  if(isObj(document.all.item("mainCategory"+count)))
			 document.all.item("mainCategory"+count).value = "";  //主類別  DOCKNDID  DOCKNDMA

		  if(isObj(document.all.item("medMainCategoryCode"+count)))
			 document.all.item("medMainCategoryCode"+count).value = "";  //主類別  DOCKNDID  DOCKNDMA
			 
		  alert("查無此許可證號!");
	  }
	}
}

function manyPermitData2(id,a,b,c,d,e)
{
	var medPermit = id.substring(0,2);
	var medPermitNumber = id.substring(2,8);
	document.all.item(c).value = medPermit;
	document.all.item(d).value = medPermitNumber;
	manyPermitData1(c,d,e);
}


function rmObj(obj)
{
	$("#"+obj).remove();
}

function validateAddrTable() 
{
	//計算資料筆數
	var rs=document.getElementsByName("fileType");

	//檢核欄位名稱
	var itemFieldName = new Array("testDate");
    var itemFieldZhName = new Array("課程名稱");	
    
	var sb = new StringBuffer();
	
	if (rs!=null && rs.length>0) 
	{		
		var o=1;	
		for(var i=0; i<rs.length; i++)
		{	
		  for (var j=0; j<itemFieldZhName.length; j++) 
		  {
			var fieldName=itemFieldName[j]+o;
			var fieldZhName=itemFieldZhName[j]+o;
			
			var fd	= document.getElementById(fieldName);

			if (isObj(fd) && itemFieldZhName[j]!='') 
			{
				sb.append(checkEmpty(fd,fieldZhName));					
			}
		  }	
		  o++;	
		}
		if (sb.toString().length>0) return sb.toString();
	}
	return "";
}

function permitData2(id)
{
	var medPermit = id.substring(0,2);
	var medPermitNumber = id.substring(2,8);
	form1.medPermit.value = medPermit;
	form1.medPermitNumber.value = medPermitNumber;
	permitData1();
}

function permitData1()
{
	var medPermit = form1.medPermit.value;
	var medPermitNumber = form1.medPermitNumber.value;

	var q = "&medPermit="+medPermit;
	    q += "&medPermitNumber="+medPermitNumber;

	//許可證字號選擇"專案"or"其他"時，將欄位打開供使用者輸入
    if(medPermit=="Z0" || medPermit=="Z5")
    {
 		document.all.item("medTestMedical").disabled = false;
    	document.all.item("medTestMedical").className = "field";
    	document.all.item("medFactory").disabled = false;
    	document.all.item("medFactory").className = "field_Q";
    	document.all.item("medMainCategory").disabled = false;
    	document.all.item("medMainCategory").className = "field_Q";
    	document.all.item("medMainCategoryCodeName").disabled = false;
    	document.all.item("medMainCategoryCodeName").className = "field_Q";
    }   
    else
    {
    	document.all.item("medTestMedical").className = "field_X";
    	document.all.item("medTestMedical").disabled = true;
    	document.all.item("medFactory").className = "field_X";
    	document.all.item("medFactory").disabled = true;
    	document.all.item("medMainCategory").className = "field_X";
    	document.all.item("medMainCategory").disabled = true;
    	document.all.item("medMainCategoryCodeName").disabled = true;
    	document.all.item("medMainCategoryCodeName").className = "field_X";
    }    

	form1.medTestMedical.value = "";  //中文品名 CHNAME
	form1.medFactory.value = "";  //製造廠 FACTNAME
	form1.medMainCategoryCodeName.value = "";  //主類別  DOCKNDID  DOCKNDMA
	form1.medMainCategory.value = "";  //主類別  DOCKNDID  DOCKNDMA
	form1.medSecCategoryCodeName.value = "";  //次類別  MSKNDID  MSKNDMA
	form1.medSecCategory.value = "";  //次類別  MSKNDID  MSKNDMA
	    
	if(medPermit!="" && medPermitNumber!="")
	{
	  x = getRemoteData("../../ajax/jsonMedPermitObjects.jsp",q );
		
	  if(x!=null && x.length>0)
	  {
		var json1 = JSON.parse(x);
		form1.medTestMedical.value = json1.obj0;  //中文品名 CHNAME
		form1.medFactory.value = json1.obj4;  //製造廠 FACTNAME
		form1.medMainCategory.value = json1.obj5;  //主類別  DOCKNDID  DOCKNDMA
		form1.medMainCategoryCodeName.value = json1.obj6;  //主類別  DOCKNDID  DOCKNDMA
        form1.medSecCategoryCodeName.value = json1.obj7;  //次類別  MSKNDID  MSKNDMA
        form1.medSecCategory.value = json1.obj8;  //次類別  MSKNDID  MSKNDMA
	  }
	  else
	  { 
		  form1.medPermit.value= "";
		  form1.medPermitNumber.value= "";
		  alert("查無此許可證號!");
	  }
	}
}
function clearUser()
{
   form1.notifierUserID.value="";
   form1.notifierAreaCode.value="";
   form1.notifierTel.value="";
   form1.notifierEamil.value="";
   form1.notifierName.value="";
}

function upload()
{
	 var prop='';
	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();

     var url="../../home/popManyUploadSimple.jsp?";
         url+="fileKind=MED";
         url+="&systemType=MED030001";
         url+="&uploadId="+form1.id.value;
         url+="&dbName=Med5001Db";
         
	 returnWindow = window.open(url,'上傳檔案',prop);
}
function permitDataQ(rowCount)
{
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var medPermit = "";
	var medPermitNumber = "";
	medPermit = form1.medPermit.value;
	medPermitNumber = form1.medPermitNumber.value;
	if(rowCount != null && rowCount != "" && !isNaN(rowCount)){
		medPermit = $("#permit"+rowCount).val();
		medPermitNumber = $("#permitNumber"+rowCount).val();
	}
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("medex0107f.jsp?medPermit="+medPermit+"&medPermitNumber="+medPermitNumber+"&rowCount="+rowCount,'popWinE',prop);		
}
function permitDataOption(id,q_keyId,rowCount){
	var medPermit = id.substring(0,2);
	var medPermitNumber = id.substring(2,8);
	$("#permit"+rowCount).val(medPermit);
	$("#permitNumber"+rowCount).val(medPermitNumber);
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');beforeInit();init();showErrorMsg('<%=obj.getErrorMsg()%>');">

<form id="form1" name="form1" method="post" onSubmit="checkField();" autocomplete="off">

<iframe name="saveContainerFrame" width="0" height="0" frameborder="0">
</iframe>

<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="medex5101q.jsp" />
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="inOrOut" value="<%=User.getInORout()%>">
	<input type="hidden" name="isAlreadyAutoSave">
	<input type="hidden" name="changeTabValue" value="<%=obj.getChangeTabValue()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <%if(!"20".equals(obj.getStatus()) && !"30".equals(obj.getStatus()) && !"40".equals(obj.getStatus())){%>
    <span id="spanPauseSave">
	   <input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span> 
    <%}%>
    <%if("00".equals(obj.getStatus())){%>
    <span id="spanStayedUpload">
	   <input class="toolbar_default" type="button" followPK="false" name="stayedUpload" value="待 上 傳" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%} %>
    <%if("00".equals(obj.getStatus()) || "01".equals(obj.getStatus()) || "02".equals(obj.getStatus()) )  {%>
    <span id="spanDoSend">
	   <input class="toolbar_default" type="button" followPK="false" name="doSend" value="送　出" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%} %>
    
    <%if("20".equals(obj.getStatus()) || "30".equals(obj.getStatus()) || "90".equals(obj.getStatus()) ){%>
    <span id="spanAutoReUpdate">
       <input class="toolbar_default" type="button" followPK="false"  name="autoReUpdate" value="主動補件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    
    <span id="spanAutoReConfirm">
       <input class="toolbar_default" type="button" followPK="false"  name="autoReConfirm" value="確定主動補件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    
    <%}%>
    <%if("40".equals(obj.getStatus())){%>
    <span id="spanDoReUpdate">
       <input class="toolbar_default" type="button" followPK="false"  name="doReUpdate" value="補件回復" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%} %>
    <input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
    <%
	if("insert".equals(obj.getDoType())||("00".equals(obj.getStatus())&&"update".equals(obj.getDoType()))){
	%>
		<span id="spanCancelQuit">
	    	<input class="toolbar_default" type="button" followPK="false" name="doCancelQuit" value="放 棄 離 開" onClick="whatButtonFireEvent(this.name)">&nbsp;
	    </span>
    <%
	}
    %>
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>

	<span id="spanShow"></span>
    </td>
</tr>

<tr>
<td nowrap>
  <% request.setAttribute("QueryBean",obj);%>
  <jsp:include page="../../home/page_row.jsp" />
</td>
</tr>
</table>


<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	    <td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良事件</a></td>	
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">其他附件</a></td>			
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
	<table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報訊息</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">發生日期</td>
			<td nowrap class="td_form_white" width="35%">
                <%=View.getPopCalendar("field","occurDate",obj.getOccurDate())%>
			</td>
			<td nowrap class="td_form" width="15%">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white"  width="35%">
                <%=View.getPopCalendar("field_RO","notifierRepDate",obj.getNotifierRepDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報者獲知日期</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getPopCalendar("field","notifierRevDate",obj.getNotifierRevDate())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">案例來源</td>
			<td nowrap class="td_form_white"  colspan="3">				
				<input class="field" type="radio" name="caseSource" value="out" <%if("out".equals(obj.getCaseSource())) out.print("checked"); %> >國外，
				<%-- <input class="field" type="text" name="caseSourceOutCountry"  maxlength="20" size="15" value="<%=obj.getCaseSourceOutCountry()%>" />(國家)<br>--%>
				<select class="field" name="caseSourceOutCountry" id="caseSourceOutCountry">
					<%=View.getOption("select A.codeId , A.codeId+' '+A.codeName from CommonCode A where A.commonCodeKind.codeKindId = 'COUC'",obj.getCaseSourceOutCountry()) %>
				</select>(國家)<br/>
				<input class="field" type="radio" name="caseSource" value="in" <%if("in".equals(obj.getCaseSource())) out.print("checked"); %>>國內，
				試驗醫院<input class="field" type="text" name="caseSourceInHospital" maxlength="30" size="15"  value="<%=obj.getCaseSourceInHospital()%>"  />
				&nbsp;&nbsp;試驗醫師<input class="field" type="text" name="caseSourceInDoctor" maxlength="20" size="10" value="<%=obj.getCaseSourceInDoctor()%>"  />
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">報告類別</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxTextOption("field","reportKind","1;初始報告;2;追蹤報告",obj.getReportKind())%>
				，第<input class="field" type="text" name="trackingNum" maxlength="3" size="3" value="<%=obj.getTrackingNum()%>"  />次
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">試驗名稱</td>
			<td nowrap class="td_form_white" colspan="3">
                <input type="text" class="field" name="testName" maxlength="20" size="50" value="<%=obj.getTestName()%>" />
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">衛生福利主管機關<br>核准函文號</td>
			<td nowrap class="td_form_white" colspan="3">
                <input class="field" type="text" name="fdaNum" maxlength="20" size="40" value="<%=obj.getFdaNum()%>" />&nbsp;&nbsp;
                <%=View.getRadioBoxTextOption("field","fdaOptions","1;查驗登記用;2;學術研究用",obj.getFdaOptions())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">核准單位</td>
			<td nowrap class="td_form_white"  colspan="3">				
				<%=View.getRadioBoxTextOption("field","approvedUnits","1;醫事司;2;食品藥物管理署;3;其他",obj.getApprovedUnits())%>
				<input class="field" type="text" name="approvedUnitsOther" maxlength="30" size="20"  value="<%=obj.getApprovedUnitsOther()%>"  />
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">廠商試驗編號</td>
			<td nowrap class="td_form_white" colspan="3">
                 <input class="field" type="text" name="firmTestNo" maxlength="20" size="50" value="<%=obj.getFirmTestNo()%>"  />
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報者資訊</td>
		</tr>	
	<tr>
			<td nowrap class="td_form">姓名</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="notifierName" value="<%=obj.getNotifierName()%>"  size="20" maxlength="10" />
			    <%if("in".equals(User.getInORout())) {%>
			    <%=View.getPopCommonUser("field" ,"notifierUserID", "commonUser", obj.getNotifierUserID(), obj.getCommonUser())%>
			    <input class="field" type="button" value="清除通報者" onClick="clearUser();">
			    <%}%>
			</td>
			<td nowrap class="td_form">電話</td>
			<td nowrap class="td_form_white">
			    (<input class="field_RO" type="text" name="notifierAreaCode" value="<%=obj.getNotifierAreaCode()%>"  size="3"  maxlength="3"/>)-
				-<input class="field_RO" type="text" name="notifierTel" value="<%=obj.getNotifierTel()%>"  size="15" maxlength="10"/>
			    #<input class="field_RO" type="text" name="notifierTelExt" size="3" maxlength="3" value="<%=obj.getNotifierTelExt()%>">
			</td>			
		</tr>	
		<tr>
			<td nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white" colspan="3">				
				<input class="field_RO" type="text" name="notifierDeptID" size="10" maxlength="10" value="<%=obj.getNotifierDeptID()%>" />
	  	        <input class="field_RO" type="text" name="notifierDept" size="30" maxlength="6"  value="<%=obj.getNotifierDept()%>" />
			    <input type="button" name="btnQryExpense" onClick="popNotifierDept(notifierType);" value="查詢" width="120px" class="field" >
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form">電子郵件</td>
			<td nowrap class="td_form_white" colspan="3" >
				<input class="field_RO" type="text" name="notifierEamil" value="<%=obj.getNotifierEamil()%>" size=50" maxlength="30"/>
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
				<input class="field_RO" type="text" name="notifierAddress" value="<%=obj.getNotifierAddress()%>" size="80" maxlength="50" />
			</td>
		</tr>
        <tr>
			<td nowrap class="td_form">屬性</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field_RO" type="radio" name="notifierType" value="03" <%if("03".equals(obj.getNotifierType())) out.print("checked"); %>>醫療人員
				<input class="field_RO" type="radio" name="notifierType" value="02" <%if("02".equals(obj.getNotifierType())) out.print("checked"); %>>廠商
				<input class="field_RO" type="radio" name="notifierType" value="01" <%if("01".equals(obj.getNotifierType())) out.print("checked"); %>>民眾
			    <input class="field_RO" type="radio" name="notifierType" value="04" <%if("04".equals(obj.getNotifierType())) out.print("checked"); %>>衛生單位，職稱：
			    <input class="field_RO" type="text" name="notifierTitle" maxlength="20" size="30" value="<%=obj.getNotifierTitle()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">病人相關資料</td>
		</tr>
		<tr>
			<td nowrap class="td_form">病人識別代碼</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientId" maxlength="10" size="20" value="<%=obj.getPatientId()%>" />
			</td>
			<td nowrap class="td_form">性別</td>
			<td nowrap class="td_form_white" >
				<%=View.getRadioBoxTextOption("field","patientSex","M;男;F;女",obj.getPatientSex())%>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">出生日期</td>
			<td nowrap class="td_form_white" colspan="3" >
				<%=View.getPopCalendar("field","patientBirth",obj.getPatientBirth())%>
				&nbsp;&nbsp;(或約<input type="text" name="patientAge" maxlength="3" size="3" value="<%=obj.getPatientAge()%>" />歲)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">身高</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientHeigth" maxlength="3" size="3" value="<%=obj.getPatientHeigth()%>" />公分
			</td>
			<td nowrap class="td_form">體重</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientWeight" maxlength="3" size="3" value="<%=obj.getPatientWeight()%>"/>公斤
			</td>				
		</tr>
	</table>
	
	<table id="Tab2" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="15%">不良反應結果</td>
			<td nowrap class="td_form_white" colspan="3">
			<%=obj.getCheckBoxOptionAD("field_Q","badReactionResults","select codeId,codeName from CommonCode where commonCodeKind.codeKindId = 'MEDAD1'",obj.getBadReactionResults(),obj.getBadReactionResultsDeathDate(),obj.getBadReactionResultsDeathReason(),obj.getBadReactionResultsOther())  %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">不良事件描述（請依事件發生前後順序填寫。應包括發生不良反應之部位、症狀、嚴重程度及處置）</td>
		</tr>
		<tr>	
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="15%">發生日期</th>
		         <th class="listTH" width="20%">部位</th>
		         <th class="listTH" width="20%">症狀</th>
		         <th class="listTH" width="20%">嚴重程度</th>
		         <th class="listTH" width="20%">處置</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed5002Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed5002DbView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否為非預期<br>之不良事件</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxTextOption("field","isAdverseEvents","Y;是;N;否",obj.getIsAdverseEvents())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">相關檢查及檢驗數據資訊（例如︰藥品血中濃度、肝/腎功能指數……等）</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th width="15%" class="listTH">檢驗日期</th>
		         <th width="50%" class="listTH">檢驗項目</th>
		         <th width="30%" class="listTH">檢驗數據</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed5003Db()">
		         </th>
	           </tr>
	           </thead>
               <tbody id="attMed5003DbView">
			   
			   </tbody>
	    
               </table>
		     </td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他相關資料（例如︰診斷、過敏、懷孕、吸菸、喝酒、習慣、其他疾病、肝/腎功能不全…等）</td>
		</tr>		
		<tr>
			<td nowrap class="td_form_white" colspan="4">
			    <textarea name="otherDesc" cols="60" rows="2"><%=obj.getOtherDesc() %></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">醫療器材資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">許可證字號</td>
		    <td nowrap class="td_form_white">
		    <select name="medPermit" class="field" onChange="permitData1();">
				<%=View.getOptionCodeKind("MEDPKID", obj.getMedPermit())%>
			</select>
			           字第<input class="field" type="text" onChange="permitData1();" name="medPermitNumber" size="10" maxlength="6" value="<%=obj.getMedPermitNumber()%>">號
		        <input type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" class="field" >
		    </td>
		    <td nowrap class="td_form" width="15%">試驗醫材名稱</td>
			<td nowrap class="td_form_white" width="35%">
			     <input class="field_X" type="text" disabled name="medTestMedical" size="30" maxlength="30" value="<%=obj.getMedTestMedical()%>">
			</td>
		</tr>
		<tr>
			<!--  
			<td nowrap class="td_form" width="15%">器材種類</td>
			<td nowrap class="td_form_white" width="35%">
			    <%=View.getPopCode("field","medType","","","","CM","medType","",new int[]{2,2,10,20})%>
			</td>
			-->
		</tr>
		<tr>
			<td nowrap class="td_form">醫材主類別</td>
			<td nowrap class="td_form_white">
			    <%=View.getPopCode("field","medMainCategoryCode","",Common.get(View.getOneCodeName("MEDMCT",Common.get(obj.getMedMainCategory()))),"","MEDMCT","medMainCategory",obj.getMedMainCategory())%>
			</td>
			<td nowrap class="td_form">醫材次類別</td>
			<td nowrap class="td_form_white">
			     <%=View.getPopCode("field","medSecCategoryCode","",Common.get(View.getOneCodeName("MEDSCT",Common.get(obj.getMedSecCategory()))),"medMainCategory","MEDSCT","medSecCategory",obj.getMedSecCategory())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >製造廠</td>
			<td nowrap class="td_form_white" >
			     <input class="field_X" type="text" disabled name="medFactory" size="15" maxlength="10" value="<%=obj.getMedFactory()%>">
			</td>
			<td nowrap class="td_form" >供應商</td>
			<td nowrap class="td_form_white">
			     <input class="field" type="text" name="medSupplier" size="15" maxlength="10" value="<%=obj.getMedSupplier()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >型號</td>
			<td nowrap class="td_form_white" >
			     <input class="field" type="text" name="medModel" size="15" maxlength="10" value="<%=obj.getMedModel()%>">
			</td>
			<td nowrap class="td_form" >序號</td>
			<td nowrap class="td_form_white">
			     <input class="field" type="text" name="medNo" size="15" maxlength="10" value="<%=obj.getMedNo()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >批號</td>
			<td nowrap class="td_form_white" >
			     <input class="field" type="text" name="medLotNum" size="15" maxlength="10" value="<%=obj.getMedLotNum()%>">
			</td>
			<td nowrap class="td_form" >製造日期</td>
			<td nowrap class="td_form_white" >
			    <%=View.getPopCalendar("field","medManufactureDate",obj.getMedManufactureDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >醫療器材操作者</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxTextOption("field","medOperator","1;醫療人員;2;病人或其家屬;3;其他",obj.getMedOperator())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >使用日期</td>
			<td nowrap class="td_form_white" >
				<%=View.getPopCalendar("field","medUseDate",obj.getMedUseDate())%>
			</td>
			<td nowrap class="td_form" >使用原因</td>
			<td nowrap class="td_form_white" >
				<input class="field" type="text" name="medUseReason" size="20" maxlength="15" value="<%=obj.getMedUseReason()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >停用日期</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","medStopDate",obj.getMedStopDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >是否可提供<br>器材作評估</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="medUseIsYn" value="Y" <%if("Y".equals(obj.getMedUseIsYn()))out.print("checked");%>>是，取得來源
				<input class="field" type="text" size="20" maxlength="30" name="medYesSoruce" value="<%=obj.getMedYesSoruce()%>"  />&nbsp;&nbsp;
				<input class="field" type="radio" name="medUseIsYn" value="N" <%if("N".equals(obj.getMedUseIsYn()))out.print("checked");%>>否&nbsp;&nbsp;
				<input class="field" type="radio" name="medUseIsYn" value="O" <%if("O".equals(obj.getMedUseIsYn()))out.print("checked");%>>已於<%=View.getPopCalendar("field","medNoReturnDate",obj.getMedNoReturnDate())%>退還給廠商
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">併用醫療器材資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="10%">許可證字</th>
		         <th class="listTH" width="15%">許可證號</th>
		         <th class="listTH" width="20%">品名</th>
		         <th class="listTH" width="10%">許可證申請商</th>
		         <th class="listTH" width="10%">型號</th>
		         <th class="listTH" width="10%">醫材主類別</th>		         
		         <th class="listTH" width="10%">使用日期</th>
		         <th class="listTH" width="15%">使用原因</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed5004Db()">
		         </th>
	           </tr>
	           </thead>
	           
	           <tbody id="attMed5004DbView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">併用藥品資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="15%">學名/商品名</th>
		         <th class="listTH" width="10%">含量</th>
		         <th class="listTH" width="10%">劑型</th>
		         <th class="listTH" width="10%">給藥途徑</th>
		         <th class="listTH" width="10%">劑量</th>
		         <th class="listTH" width="10%">頻率</th>		         
		         <th class="listTH" width="25%">使用期間</th>
		         <th class="listTH" width="10%">使用原因</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed5005Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed5005DbView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">曾使用同類<br>醫材之經驗</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="medOnceUseMed" value="Y" <%if("Y".equals(obj.getMedOnceUseMed()))out.print("checked");%>>是，
				醫材名稱<input class="field" type="text" name="medOnceUseMedName" maxlength="30" size="20" value="<%=obj.getMedOnceUseMedName()%>"  /> 
				若發生不良反應請描述<input type="text" name="medOnceUseBadReaction" maxlength="30" size="20" value="<%=obj.getMedOnceUseBadReaction()%>"  /> <br>
				<input class="field" type="radio" name="medOnceUseMed" value="N" <%if("N".equals(obj.getMedOnceUseMed()))out.print("checked");%>>否&nbsp;&nbsp;
				<input class="field" type="radio" name="medOnceUseMed" value="O" <%if("O".equals(obj.getMedOnceUseMed()))out.print("checked");%>>無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">停用後不良反應症狀<br>是否減輕</td>
			<td nowrap class="td_form_white">	
				<%=View.getCommonRadioBoxOption("field","medStopMedMitigate","MEDYN",obj.getMedStopMedMitigate(),"")%>		
			</td>
			<td nowrap class="td_form" >再使用是否出現<br>同樣反應</td>
			<td nowrap class="td_form_white" >
				<%=View.getCommonRadioBoxOption("field","onceSameReaction","MEDYN",obj.getOnceSameReaction(),"")%>		
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否同時使用</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field","sameTimeUse","MEDSAME",obj.getSameTimeUse(),"")%>		
				<input type="text" name="sameTimeUseOther" maxlength="30" size="30" value="<%=obj.getSameTimeUseOther()%>" />
				*若有同時使用，請填入併用藥品內◦
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">因果關係資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >試驗醫師評估醫材<br>與SAE之因果關係</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field","medSea","MEDSEA",obj.getMedSea(),"",3)%>		
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >驗醫師評估手續程序<br>與SAE之因果關係</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field","procedureSea","MEDSEA",obj.getProcedureSea(),"",3)%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">相關通報資訊</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >本案是否立即通知<br>試驗委託者</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getYNRadioBoxTextOption("field","noticeSponsor",obj.getNoticeSponsor()) %>
				&nbsp;&nbsp;&nbsp;&nbsp;且提供詳細書面報告？&nbsp;&nbsp;&nbsp;&nbsp;
				<%=View.getYNRadioBoxTextOption("field","noticeSponsorWritten",obj.getNoticeSponsorWritten()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >本案是否立即<br>通知人體試驗委員會</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getYNRadioBoxTextOption("field","noticeIRB",obj.getNoticeIRB()) %>
				&nbsp;&nbsp;&nbsp;&nbsp;且提供詳細書面報告？&nbsp;&nbsp;&nbsp;&nbsp;
				<%=View.getYNRadioBoxTextOption("field","noticeIRBWritten",obj.getNoticeIRBWritten()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">本案是否立即通知<br>試驗核准單位</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getYNRadioBoxTextOption("field","noticeApprovedUnits",obj.getNoticeApprovedUnits()) %>
				&nbsp;&nbsp;&nbsp;&nbsp;且提供詳細書面報告？&nbsp;&nbsp;&nbsp;&nbsp;
				<%=View.getYNRadioBoxTextOption("field","noticeApprovedUnitsWritten",obj.getNoticeApprovedUnitsWritten()) %>
			</td>
		</tr>
	
	</table>
	
	<table id="Tab3" width="100%" align="center" class="table_form">
	    <thead id="listTHEAD">  
	    <tr>
	        <td>
	            <span id="spanDoUpload">
		        <input class="toolbar_default" type="button" followPK="false" name="doUpload" value="附件上傳" onClick="upload()">&nbsp;
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
			<%=obj.getAddFile()%>
		</tbody>
	</table>
	
	
</div>
</table>
</form>




<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)	
	{
	  case "pauseSave":
		  checkField();
		  break;
	  case "stayedUpload":
		  checkField();
		  break;
	  case "doSend":
		  checkField();
		  break;
	  case "doReUpdate":
		  getReply();
		  break;
	  case "doCancelQuit":
		  if(confirm("確定放棄並刪除通報資料?"))
		  {
			  form1.action = "medex5102f.jsp";
			  form1.state.value = buttonName;
			  form1.submit();
		  }
		  break;
	  case "doDoQuit":
		  if(confirm("確定回到查詢頁面?"))
		  {
			  form1.action = "medex5101f.jsp?q_status="+'<%=q_status%>';
			  form1.state.value = "queryAll";
			  form1.submit();
		  }
		  break;
	  case "autoReUpdate":
		  setFormItem("autoReUpdate","R");
		  setFormItem("autoReConfirm","O"); 
		  setAllOpen();
		  closeField();
		  break;
	  case "autoReConfirm":
		  form1.state.value = "autoReConfirm";
		  checkField();
		  break;
	  case "doReportView":
		  	form1.target = "_blank";
			form1.action = "medex5102p.jsp";
			beforeSubmit();
			form1.submit();
			form1.target = "_self";
			form1.action = "medex5102f.jsp"
			break;
	}
}

function closeField()
{
	  //發生日期
	  if(isObj(document.all.item("occurDate")))
		setFormItem("occurDate","RO");
	  if(isObj(document.all.item("btn_occurDate")))
		setFormItem("btn_occurDate","RO");

	  //通報者獲知日期
	  if(isObj(document.all.item("notifierRepDate")))
		setFormItem("notifierRepDate","RO");
	  if(isObj(document.all.item("btn_notifierRepDate")))
		setFormItem("btn_notifierRepDate","RO");
	  
	  //案例來源
	  var len = document.all.item("caseSource").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("caseSource")[i].disabled = true;	
	  }
	  if(isObj(document.all.item("caseSourceOutCountry")))
		  setFormItem("caseSourceOutCountry","RO"); 

	  //報告類別
	  var len = document.all.item("reportKind").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("reportKind")[i].disabled = true;	
	  }
	  if(isObj(document.all.item("trackingNum")))
		  setFormItem("trackingNum","RO"); 
		
	  //試驗名稱
	  if(isObj(document.all.item("testName")))
		  setFormItem("testName","RO");
	  //衛生福利主管機關核准函文號
	  var len = document.all.item("fdaOptions").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("fdaOptions")[i].disabled = true;	
	  }
	  if(isObj(document.all.item("fdaNum")))
		  setFormItem("fdaNum","RO"); 
	  
	  //核准單位
	  var len = document.all.item("approvedUnits").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("approvedUnits")[i].disabled = true;	
	  }
	  if(isObj(document.all.item("approvedUnitsOther")))
		  setFormItem("approvedUnitsOther","RO"); 
	  
	  //服務機構
	  if(isObj(document.all.item("notifierDeptID")))
		  setFormItem("notifierDeptID","RO"); 
	  if(isObj(document.all.item("notifierDept")))
		  setFormItem("notifierDept","RO"); 
	  if(isObj(document.all.item("btnNotifierDept")))
		  setFormItem("btnNotifierDept","RO"); 

	  //廠商試驗編號
	  if(isObj(document.all.item("firmTestNo")))
		  setFormItem("firmTestNo","RO");
	  
      //屬性
	  var len = document.all.item("notifierType").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("notifierType")[i].disabled = true;	
	  }
	  if(isObj(document.all.item("notifierTitle")))
		  setFormItem("notifierTitle","RO");  

      //許可證字號
	  if(isObj(document.all.item("medPermit")))
		 setFormItem("medPermit","RO"); 
	  if(isObj(document.all.item("medPermitNumber")))
	     setFormItem("medPermitNumber","RO");
	  if(isObj(document.all.item("btnQryExpense")))
	     setFormItem("btnQryExpense","RO"); 
	  
}
</script>
</body>
</html>
