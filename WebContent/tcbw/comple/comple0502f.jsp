<!--
程式目的：醫療器材不良事件通報登錄作業
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COMPLE0502" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.comple.COMPLE0502F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

String show=request.getParameter("show");

obj.setInOrOut(User.getInORout());


if("upload".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
		obj.setErrorMsg("上傳成功");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("update".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
		obj.setErrorMsg("修改完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}


obj = (com.kangdainfo.tcbw.view.comple.COMPLE0502F)obj.queryOne();

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
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">

var insertDefault = new Array();
var popWinName;

function checkField()
{
	var alertStr="";
	//validateAddrTable();
	
	if(form1.state.value=="reUpdate")
	{
		getReply();
		return false;
	}
	
	
	if(form1.state.value == "stayedUpload" || form1.state.value == "doSend" )
	{
		
	   <%=TCBWCommon.getCheckFiled("MED04","MED01001")%>

	   var a=form1.eventKind;
	   if (a!=null && a.length>0) 
	   {
			for (j=0; j<a.length; j++) 
			{
				if(a[0].checked)
				{	
				  <%=TCBWCommon.getCheckFiled("MED01")%>
				}
				if(a[1].checked)
				{
				  <%=TCBWCommon.getCheckFiled("MED02")%>
				}
			}
		}
	   
	}
	
	if(form1.state.value == "pauseSave")
	{
		form1.updateType.value = "1";
	}
	else if(form1.state.value == "stayedUpload")
	{
		form1.updateType.value = "2";
	}
	else if(form1.state.value=="doSend")
	{
		form1.updateType.value = "3";
	}
	else if(form1.state.value == "upload")
	{
		form1.updateType.value = "1";
	}

    
	//基本資料頁籤
	//檢查案例來源
	if(form1.state.value == "stayedUpload" || form1.state.value=="doSend")
	{
	   var caseSource = document.getElementsByName('caseSource');
	
	   if(isObj(caseSource))
	   {
		  for(var i = 0 ; i < caseSource.length ; i ++)
		  {
			var caseSourceCheckBox = caseSource[i];
			
			if(caseSourceCheckBox.checked)
			{
				if(caseSourceCheckBox.value == "out")
				{
					alertStr += checkEmpty(form1.caseSourceOutCountry,"案例來源(國家)");
					//form1.caseSourceInHospital.style.backgroundColor = "";
					//form1.caseSourceInDoctor.style.backgroundColor = "";
				}
			}
		   }
	     }

	    //檢查報告類別
		var reportKind = document.getElementsByName('reportKind');
		if(isObj(reportKind))
		{
			for(var i = 0 ; i < reportKind.length ; i ++)
			{
				var reportKindCheckBox = reportKind[i];
				if(reportKindCheckBox.checked)
				{
					var temp = "";
					if(reportKindCheckBox.value == "2")
					{
						 temp += checkEmpty(form1.trackingNum,"追蹤報告(次數)");
						 if(temp == "")
							 temp += checkNumber(form1.trackingNum,"追蹤報告(次數)");

					}
					else
					{
						form1.trackingNum.style.backgroundColor = "";
					}
					alertStr += temp;
				}
			}
		}
		
		//不良事件頁籤
		//檢查死亡欄位
		var badReactionResults = document.getElementsByName('badReactionResults');

		if(isObj(badReactionResults))
		{
			for(var i = 0 ; i < badReactionResults.length ; i ++)
			{
				var badReactionResultsCheckBox = badReactionResults[i];
				if(badReactionResultsCheckBox.checked)
				{
					if(badReactionResultsCheckBox.value == "01")
					{
						alertStr += checkEmpty(form1.badReactionResultsDeathDate,"死亡(日期)");
						alertStr += checkEmpty(form1.badReactionResultsDeathReason,"死亡(死亡原因)");
					}
					if(badReactionResultsCheckBox.value == "08")
						alertStr += checkEmpty(form1.badReactionResultsOther,"非嚴重不良事件說明欄位");
				}
				else
				{
					if(badReactionResultsCheckBox.value == "01")
					{
						form1.badReactionResultsDeathDate.style.backgroundColor = "";
						form1.badReactionResultsDeathReason.style.backgroundColor = "";
					}
					if(badReactionResultsCheckBox.value == "08")
						form1.badReactionResultsOther.style.backgroundColor = "";
				}
			}
		}
	     
	}

	if(alertStr.length!=0){ alert(alertStr); return false; }

	beforeSubmit();
	form1.submit();
	return true;
	
}

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
	
	returnWindow=window.open(getVirtualPath() + "tcbw/medex/medex0106f.jsp?applNo="+form1.applNo.value,"",prop);
}

function init() 
{
    
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden','H');

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
			<%if("00".equals(obj.getStatus()) 
					|| "01".equals(obj.getStatus()) 
					|| "02".equals(obj.getStatus()) 
					|| "03".equals(obj.getStatus())  
					|| "04".equals(obj.getStatus()) 
					|| "30".equals(obj.getStatus()) )
			{%>
			   setAllOpen();
			<%}%>
		}
		else
		{	
			setAllReadonly();
			setFormItem("pauseSave,stayedUpload,doSend","R");
		}
	
		if("<%=obj.getDoType()%>" == "insert")
		{
			//當新增時先將一些細項增加一筆新的，使用者不要就自行刪除。
			addMed0002Db();
			addMed0003Db();
			addMed0004Db();
			addMed0005Db();
		}
	}
	else if('<%=obj.getUpdateType()%>'=="1")
	{
		setAllOpen();
	}	
	else 
	{
		form1.action = "comple0501f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		//重新整理左邊樹狀
	    window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	}

	var len = document.all.item("notifierType").length;	
	   
	for(var i=0 ; i<len ; i++ )
	{			
		document.all.item("notifierType")[i].disabled = true;
	}
	chgBadReactionResults();
}



function open1()
{
	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit","O");
}


function chengEventKind()
{
	//依不良事件類別，顯示不良反應  or 不良品  頁簽
	document.getElementById("t3").style.display = 'none';
	document.getElementById("aTab3").style.display = 'none';
	document.getElementById("t4").style.display = 'none';
	document.getElementById("aTab4").style.display = 'none';
    var a=form1.eventKind;
	if (a!=null && a.length>0) 
	{
		for (j=0; j<a.length; j++) 
		{
			if(a[0].checked)
			{	
			  document.getElementById("t3").style.display = '';
			  document.getElementById("aTab3").style.display = '';
			}
			if(a[1].checked)
			{
		      document.getElementById("t4").style.display = '';
			  document.getElementById("aTab4").style.display = '';
			}
		}
	}
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
	
	$("#spanShow").hide();
	
	setFormItem('autoReConfirm','R');
	
	if (isObj(document.all.item("btn_notifierUserID"))) 
	  form1.btn_notifierUserID.value="選擇通報者";

	$(".field[name*=eventKind]").bind("click", chengEventKind);
	
	if("<%=obj.getDoType()%>" == "update")
	{
		if('<%=obj.getStatus()%>'=="00")
		{
		  $(".field").bind("change",openStartAutoSave);
		  $(".field[name*=btn_aFileName]").bind("click", openStartAutoSave);
		  $(".field[name*=btn]").bind("click", openStartAutoSave);
		  $(".field_btnAdd").bind("click", openStartAutoSave);
		  $(".field_btnRemove").bind("click", openStartAutoSave);
		}
	}

	var medPermit = form1.medPermit.value;

    if(medPermit=="Z0" || medPermit=="Z5")
    {
 		document.all.item("medPermitFirmCode").disabled = false;
 		document.all.item("medPermitFirmCode").className = "field";
 		document.all.item("medCname").disabled = false;
    	document.all.item("medCname").className = "field";
    	document.all.item("medPermitFirm").disabled = false;
    	document.all.item("medPermitFirm").className = "field_Q";
    	document.all.item("medFactory").disabled = false;
    	document.all.item("medFactory").className = "field_Q";
    	document.all.item("medCountr").disabled = false;
    	document.all.item("medCountr").className = "field_Q";
    }   

});




function changeTab(tabId) 
{
	var alertStr="";
	
	var oldTabId=document.all.item("changeTabValue").value
	
    if(oldTabId==1)
    {
       <%=TCBWCommon.getCheckFiled("MED04","MED01001")%>
    }
    else if(oldTabId==2)
    {
       <%=TCBWCommon.getCheckFiled("MED04","MED01002")%>
    }    

	if(alertStr.length!=0)
    { 
		alertStr+="確定要繼續前往下一個頁籤?";
		if(!confirm(alertStr))
		{
			return false;
	    }
    }

	if (isObj(document.all.item("changeTabValue")))
	{
		document.all.item("changeTabValue").value=tabId;
	}
	
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";
	document.getElementById("t4").className = "tab_border2";
	document.getElementById("t5").className = "tab_border2";
	
	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab4").className = "";
	document.getElementById("aTab5").className = "";
		
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';	
	document.getElementById("Tab4").style.display = 'none';	
	document.getElementById("Tab5").style.display = 'none';	
	
	//依不良事件類別，顯示不良反應  or 不良品  頁簽
	document.getElementById("t3").style.display = 'none';
	document.getElementById("aTab3").style.display = 'none';
	document.getElementById("t4").style.display = 'none';
	document.getElementById("aTab4").style.display = 'none';

    var a=form1.eventKind;

	if (a!=null && a.length>0) 
	{
		for (j=0; j<a.length; j++) 
		{
			if(a[0].checked)
			{	
			  document.getElementById("t3").style.display = '';
			  document.getElementById("aTab3").style.display = '';
			}
			if(a[1].checked)
			{
		      document.getElementById("t4").style.display = '';
			  document.getElementById("aTab4").style.display = '';
			}
		}
	}


	
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
	else if(tabId == 4)
	{
		document.getElementById("t4").className = "tab_border1";
		document.getElementById("Tab4").style.display = '';
		document.getElementById("aTab4").className = "text_w";
	}
	else if(tabId == 5)
	{
		document.getElementById("t5").className = "tab_border1";
		document.getElementById("Tab5").style.display = '';
		document.getElementById("aTab5").className = "text_w";
	}
	else
	{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}

	
}





//不良事件描述資訊
var numberMed0002Db = 1;
function addMed0002Db(id,bulletinDate,position,symptom,severity,dealWith)
{
	var Med0002DbCount;
	Med0002DbCount = numberMed0002Db++;		
	var newItem ="<tr id=\"med0002DbfileType"+Med0002DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med0002DbType'>";
	newItem +="<input type='hidden' name='med0002DbTypeId' value=\""+(id!=null?id:'')+"\">";
	//newItem +='<input type=text class=field_Q name=bulletinDate  id=bulletinDate' + Med0002DbCount + ' size=7 value=\'' + (bulletinDate!=null?bulletinDate:'') + '\'><input class="field_Q" type="button" name="btn_bulletinDate' + Med0002DbCount + '" onclick="popCalendar(\'bulletinDate' + Med0002DbCount + '\')" value="..." title="萬年曆輔助視窗">';
	newItem +='<input type=text class=field_Q name=bulletinDate  id=bulletinDate' + Med0002DbCount + ' size=7 value=\'' + (bulletinDate!=null?bulletinDate:'') + '\'><button class="field_Q" type="button" name="btn_bulletinDate' + Med0002DbCount + '" onclick="popCalendar(\'bulletinDate' + Med0002DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='position"+Med0002DbCount+"' class=\"field_Q\" type=\"text\" name=\"position"+"\" size=\"20\"  maxlength=\"30\"   value=\""+(position!=null?position:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='symptom"+Med0002DbCount+"' class=\"field_Q\" type=\"text\" name=\"symptom"+"\" size=\"20\"  maxlength=\"30\" value=\""+(symptom!=null?symptom:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='severity"+Med0002DbCount+"' class=\"field_Q\" type=\"text\" name=\"severity"+"\" size=\"20\"  maxlength=\"30\" value=\""+(severity!=null?severity:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='dealWith"+Med0002DbCount+"' class=\"field_Q\" type=\"text\" name=\"dealWith"+"\" size=\"20\"  maxlength=\"30\" value=\""+(dealWith!=null?dealWith:'')+"\">&nbsp;";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed0002Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med0002DbfileType"+Med0002DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed0002DbView").append(newItem);
}
//相關檢查及檢驗數據資訊
var numberMed0003Db = 1;
function addMed0003Db(id,testDate,testItems,testNum)
{
	var Med0003DbCount;
	Med0003DbCount = numberMed0003Db++;	
		
	var newItem ="<tr id=\"med0003DbfileType"+Med0003DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med0003DbType'>";
	newItem +="<input type='hidden' name='med0003DbTypeId' value=\""+(id!=null?id:'')+"\">";
	//newItem +='<input type=text class=field_Q name=testDate  id=testDate' + Med0003DbCount + ' size=7 value=\'' + (testDate!=null?testDate:'') + '\'><input class="field_Q" type="button" name="btn_testDate' + Med0003DbCount + '" onclick="popCalendar(\'testDate' + Med0003DbCount + '\')" value="..." title="萬年曆輔助視窗">';
	newItem +='<input type=text class=field_Q name=testDate  id=testDate' + Med0003DbCount + ' size=7 value=\'' + (testDate!=null?testDate:'') + '\'><button class="field_Q" type="button" name="btn_testDate' + Med0003DbCount + '" onclick="popCalendar(\'testDate' + Med0003DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='testItems"+Med0003DbCount+"' class=\"field_Q\" type=\"text\" name=\"testItems"+"\" size=\"40\"  value=\""+(testItems!=null?testItems:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='testNum"+Med0003DbCount+"' class=\"field_Q\" type=\"text\" name=\"testNum"+"\" size=\"40\"  value=\""+(testNum!=null?testNum:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed0003Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med0003DbfileType"+Med0003DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed0003DbView").append(newItem);
}

//併用醫療器材資訊
var numberMed0004Db = 1;
function addMed0004Db(id,permit,permitNumber,ccname,permitFirm,model
		,mainCategory,useDate,useReason,mainCategoryCode)
{
	var Med0004DbCount;
	Med0004DbCount = numberMed0004Db++;		
	var newItem ="<tr id=\"med0004DbfileType"+Med0004DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med0004DbType'>";
	newItem +="<input type='hidden' name='med0004DbTypeId' value=\""+(id!=null?id:'')+"\">";

	newItem +="<select class=\"field_Q\" type=\"select\"  id ='permit"+Med0004DbCount+"' onChange=\"manyPermitData1('permit"+Med0004DbCount+"','permitNumber"+Med0004DbCount+"','"+Med0004DbCount+"');\"  name=\"permit"+"\" >"+"<%=View.getOptionCodeKind("MEDPKID","").replaceAll("\n","").replace("'","\\'")%>";
	newItem +="<input id ='permitNumber"+Med0004DbCount+"' onBlur=\"manyPermitData1('permit"+Med0004DbCount+"','permitNumber"+Med0004DbCount+"','"+Med0004DbCount+"');\" class=\"field_Q\" type=\"text\" name=\"permitNumber"+"\" size=\"6\"  maxlength=\"6\"   value=\""+(permitNumber!=null?permitNumber:'')+"\">&nbsp;";
	newItem +="<input type=\"button\" name=\"btnQryExpense\" onClick=\"manyPermitDataQ('permit"+Med0004DbCount+"','permitNumber"+Med0004DbCount+"','"+Med0004DbCount+"');\" value=\"查詢\" width=\"60px\" class=\"field\" >";
	newItem +="</td>";


	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='ccname"+Med0004DbCount+"' class=\"field_Q\" type=\"text\" name=\"ccname"+"\" size=\"15\"  maxlength=\"30\" value=\""+(ccname!=null?ccname:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='permitFirm"+Med0004DbCount+"' class=\"field_Q\" type=\"text\" name=\"permitFirm"+"\" size=\"15\"  maxlength=\"30\" value=\""+(permitFirm!=null?permitFirm:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='model"+Med0004DbCount+"' class=\"field_Q\" type=\"text\" name=\"model"+"\" size=\"15\"  maxlength=\"20\" value=\""+(model!=null?model:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";

	newItem +="<input class=\"field\" type=\"hidden\" id='medMainCategoryCode"+Med0004DbCount+"' name=\"medMainCategoryCode\" >";
	newItem +="<input class=\"field\" type=\"text\" id ='mainCategoryCode"+Med0004DbCount+"' style=\"ime-mode: disabled;\" name=\"mainCategoryCode\" size=\"5\" maxlength=\"10\"  onchange=\"getCodeName('medMainCategoryCode"+Med0004DbCount+"','','MEDMCT',this,'mainCategory"+Med0004DbCount+"');\">";
	newItem +="<input class=\"field_RO\" type=\"text\" id='mainCategory"+Med0004DbCount+"' name=\"mainCategory\" size=\"20\" maxlength=\"50\">";
	newItem +="<input class=\"field\" type=\"button\" id='btn_medMainCategoryCode"+Med0004DbCount+"' value=\"...\" title=\"代碼輸入輔助視窗\" name=\"btn_medMainCategoryCode\" onclick=\"popCode('medMainCategoryCode"+Med0004DbCount+"','','MEDMCT','mainCategoryCode"+Med0004DbCount+"','mainCategory"+Med0004DbCount+"');\">";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	//newItem +='<input type=text class=field_Q name=useDate  id=useDate' + Med0004DbCount + ' size=7 value=\'' + (useDate!=null?useDate:'') + '\'><input class="field_Q" type="button" name="btn_useDate' + Med0004DbCount + '" onclick="popCalendar(\'useDate' + Med0004DbCount + '\')" value="..." title="萬年曆輔助視窗">';
	newItem +='<input type=text class=field_Q name=useDate  id=useDate' + Med0004DbCount + ' size=7 value=\'' + (useDate!=null?useDate:'') + '\'><button class="field_Q" type="button" name="btn_useDate' + Med0004DbCount + '" onclick="popCalendar(\'useDate' + Med0004DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='useReason"+Med0004DbCount+"' class=\"field_Q\" type=\"text\" name=\"useReason"+"\" size=\"15\"  maxlength=\"50\" value=\""+(useReason!=null?useReason:'')+"\">&nbsp;";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed0004Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('Med0004DbfileType"+Med0004DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed0004DbView").append(newItem);
	$("#permit"+Med0004DbCount).val(permit);
	$("#mainCategoryCode"+Med0004DbCount).val(mainCategoryCode);
	$("#mainCategory"+Med0004DbCount).val(mainCategory);
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

function manyPermitData2(id,a,b,c,d,e)
{
	var medPermit = id.substring(0,2);
	var medPermitNumber = id.substring(2,8);
	document.all.item(c).value = medPermit;
	document.all.item(d).value = medPermitNumber;
	manyPermitData1(c,d,e);
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

		if(isObj(document.all.item("ccname"+count)))
		  document.all.item("ccname"+count).value = json1.obj0;  //中文品名 CHNAME

		if(isObj(document.all.item("permitFirm"+count)))
		  document.all.item("permitFirm"+count).value= json1.obj3;  //申請商  APPNAME

		if(isObj(document.all.item("mainCategory"+count)))
		  document.all.item("mainCategory"+count).value = json1.obj6;  //主類別  DOCKNDID  DOCKNDMA

		  
		if(isObj(document.all.item("mainCategoryCode"+count)))
		  document.all.item("mainCategoryCode"+count).value = json1.obj5;  //
		
	  }
	  else
	  { 

		  document.all.item(medPermit1).value = "";
		  document.all.item(medPermitNumber1).value = "";
		  
		  if(isObj(document.all.item("ccname"+count)))
			 document.all.item("ccname"+count).value = "";  //中文品名 CHNAME

		  if(isObj(document.all.item("permitFirm"+count)))
			 document.all.item("permitFirm"+count).value= "";  //申請商  APPNAME

		  if(isObj(document.all.item("mainCategory"+count)))
			 document.all.item("mainCategory"+count).value = "";  //主類別  DOCKNDID  DOCKNDMA

		  if(isObj(document.all.item("mainCategoryCode"+count)))
			 document.all.item("mainCategoryCode"+count).value = "";  //
			 
		  alert("查無此許可證號!");
	  }
	}
}



//併用藥品資訊
var numberMed0005Db = 1;
function addMed0005Db(id,cName,content,formulation,drgApproach,dose,frequency,sDate,eDate,medCauses)
{
	var Med0005DbCount;
	Med0005DbCount = numberMed0005Db++;		
	var newItem ="<tr id=\"Med0005DbfileType"+Med0005DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='Med0005DbType'>";
	newItem +="<input type='hidden' name='Med0005DbTypeId' value=\""+(id!=null?id:'')+"\">";
	newItem +="<input type=text class=field_Q name=cName2  id=cName2" + Med0005DbCount + " size=\"15\" maxlength=\"30\"  value=\"" + (cName!=null?cName:'') + "\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='content"+Med0005DbCount+"' class=\"field_Q\" type=\"text\" name=\"content"+"\" size=\"15\"  maxlength=\"20\"   value=\""+(content!=null?content:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='formulation"+Med0005DbCount+"' class='field_Q' name='formulation'>"+"<%=View.getOptionCodeKind("DRGFLN","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='drgApproach"+Med0005DbCount+"' class='field_Q' name='drgApproach'>"+"<%=View.getOptionCodeKind("DRG0304","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='dose"+Med0005DbCount+"' class='field_Q' name='dose'>"+"<%=View.getOptionCodeKind("DRG0305","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='frequency"+Med0005DbCount+"' class='field_Q' name='frequency'>"+"<%=View.getOptionCodeKind("DRG0306","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	//newItem +='<input type=text class=field_Q name=sDate  id=sDate' + Med0005DbCount + ' size=7 value=\'' + (sDate!=null?sDate:'') + '\'><input class="field_Q" type="button" name="btn_sDate' + Med0005DbCount + '" onclick="popCalendar(\'sDate' + Med0005DbCount + '\')" value="..." title="萬年曆輔助視窗">';
    //newItem +="~" + '<input type=text class=field_Q name=eDate  id=eDate' + Med0005DbCount + ' size=7 value=\'' + (eDate!=null?eDate:'') + '\'><input class="field_Q" type="button" name="btn_eDate' + Med0005DbCount + '" onclick="popCalendar(\'eDate' + Med0005DbCount + '\')" value="..." title="萬年曆輔助視窗">';
    newItem +='<input type=text class=field_Q name=sDate  id=sDate' + Med0005DbCount + ' size=7 value=\'' + (sDate!=null?sDate:'') + '\'><button class="field_Q" type="button" name="btn_sDate' + Med0005DbCount + '" onclick="popCalendar(\'sDate' + Med0005DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
    newItem +="~" + '<input type=text class=field_Q name=eDate  id=eDate' + Med0005DbCount + ' size=7 value=\'' + (eDate!=null?eDate:'') + '\'><button class="field_Q" type="button" name="btn_eDate' + Med0005DbCount + '" onclick="popCalendar(\'eDate' + Med0005DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='medCauses"+Med0005DbCount+"' class=\"field_Q\" type=\"text\" name=\"medCauses"+"\" size=\"15\"  maxlength=\"50\" value=\""+(medCauses!=null?medCauses:'')+"\">&nbsp;";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed0005Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('Med0005DbfileType"+Med0005DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed0005DbView").append(newItem);
	$("#formulation"+Med0005DbCount).val(formulation);
	$("#drgApproach"+Med0005DbCount).val(drgApproach);
	$("#dose"+Med0005DbCount).val(dose);
	$("#frequency"+Med0005DbCount).val(frequency);
}


function rmObj(obj)
{
	$("#"+obj).remove();
}

function beforeInit()
{
	<%=obj.getMed0002DbItemSet()%>
	<%=obj.getMed0003DbItemSet()%>
	<%=obj.getMed0004DbItemSet()%>
	<%=obj.getMed0005DbItemSet()%>
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
	popWinName = window.open("../medex/medex0107f.jsp?medPermit="+medPermit+"&medPermitNumber="+medPermitNumber+"&rowCount="+rowCount,'popWinE',prop);		
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

    if(medPermit=="Z0" || medPermit=="Z5")
    {
 		document.all.item("medPermitFirmCode").disabled = false;
 		document.all.item("medPermitFirmCode").className = "field";
 		document.all.item("medCname").disabled = false;
    	document.all.item("medCname").className = "field";
    	document.all.item("medPermitFirm").disabled = false;
    	document.all.item("medPermitFirm").className = "field_Q";
    	document.all.item("medFactory").disabled = false;
    	document.all.item("medFactory").className = "field_Q";
    	document.all.item("medCountr").disabled = false;
    	document.all.item("medCountr").className = "field_Q";
    }   
    else
    {
    	document.all.item("medCname").className = "field_X";
    	document.all.item("medCname").disabled = true;
    	document.all.item("medPermitFirmCode").className = "field_X";
    	document.all.item("medPermitFirmCode").disabled = true;
    	document.all.item("medPermitFirm").className = "field_X";
    	document.all.item("medPermitFirm").disabled = true;
    	document.all.item("medFactory").className = "field_X";
    	document.all.item("medFactory").disabled = true;
    	document.all.item("medCountr").className = "field_X";
    	document.all.item("medCountr").disabled = true;
    }    
   
	form1.medCname.value = "";  //中文品名 CHNAME
	form1.medPermitFirmCode.value = "";  //申請商  APPUNNO
	form1.medPermitFirm.value = "";  //申請商  APPNAME
	form1.medFactory.value = "";  //製造廠 FACTNAME
	form1.medMainCategoryCodeName.value = "";  //主類別  DOCKNDID  DOCKNDMA
	form1.medMainCategory.value = "";  //主類別  DOCKNDID  DOCKNDMA
	form1.medSecCategoryCodeName.value = "";  //次類別  MSKNDID  MSKNDMA
	form1.medSecCategory.value = "";  //次類別  MSKNDID  MSKNDMA
	form1.medCountr.value = "";  //製造廠國別 FACTCIDMA
	    
	if(medPermit!="" && medPermitNumber!="")
	{
	  x = getRemoteData("../../ajax/jsonMedPermitObjects.jsp",q );
		
	  if(x!=null && x.length>0)
	  {
		var json1 = JSON.parse(x);
		form1.medCname.value = json1.obj0;  //中文品名 CHNAME
		form1.medPermitFirmCode.value = json1.obj2;  //申請商  APPUNNO
		form1.medPermitFirm.value = json1.obj3;  //申請商  APPNAME
		form1.medFactory.value = json1.obj4;  //製造廠 FACTNAME
		form1.medMainCategory.value = json1.obj5;  //主類別  DOCKNDID  DOCKNDMA
		form1.medMainCategoryCodeName.value = json1.obj6;  //主類別  DOCKNDID  DOCKNDMA
        form1.medSecCategoryCodeName.value = json1.obj7;  //次類別  MSKNDID  MSKNDMA
        form1.medSecCategory.value = json1.obj8;  //次類別  MSKNDID  MSKNDMA
        form1.medCountr.value = json1.obj9;  //製造廠國別 FACTCIDMA
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
	form1.notifierName.value="";
	form1.notifierUserID.value="";
	form1.notifierAreaCode.value="";
	form1.notifierTel.value="";
	form1.notifierTelExt.value="";
	form1.notifierDeptID.value="";
	form1.notifierDept.value="";
	form1.notifierEamil.value="";
	form1.notifierCounty.value="";
	form1.notifierZipCode.value="";
	form1.notifierAddress.value="";
	form1.notifierStaffHospital.value="";
	form1.notifierStaffTitleOther.value="";
	form1.notifierFirmDept.value="";

	
	var len = document.all.item("notifierStaffTitle").length;	

	for(var i=0;i<len;i++ )
	{		
		document.all.item("notifierStaffTitle")[i].checked=false;
	}

	var len1 = document.all.item("notifierType").length;	

	for(var i=0;i<len1;i++ )
	{		
		document.all.item("notifierType")[i].checked=false;
	}

	form1.notifierStaffTitleOther.value="";
	form1.notifierFirmDept.value="";

	document.all.item("notifierName").className = "field";
	document.all.item("notifierName").readOnly = false;

	document.all.item("notifierUserID").className = "field";
	document.all.item("notifierUserID").readOnly = false;
	
	document.all.item("notifierAreaCode").className = "field";
	document.all.item("notifierAreaCode").readOnly = false;

	document.all.item("notifierTel").className = "field";
	document.all.item("notifierTel").readOnly = false;

	document.all.item("notifierTelExt").className = "field";
	document.all.item("notifierTelExt").readOnly = false;

	document.all.item("notifierDeptID").className = "field";
	document.all.item("notifierDeptID").readOnly = false;
	
	document.all.item("notifierDept").className = "field";
	document.all.item("notifierDept").readOnly = false;

	document.all.item("notifierEamil").className = "field";
	document.all.item("notifierEamil").readOnly = false;
	
	document.all.item("notifierCounty").className = "field";
	document.all.item("notifierCounty").readOnly = false;

	document.all.item("notifierZipCode").className = "field";
	document.all.item("notifierZipCode").readOnly = false;

	document.all.item("notifierAddress").className = "field";
	document.all.item("notifierAddress").readOnly = false;

	document.all.item("notifierStaffHospital").className = "field";
	document.all.item("notifierStaffHospital").readOnly = false;

	document.all.item("notifierStaffTitleOther").className = "field";
	document.all.item("notifierStaffTitleOther").readOnly = false;

	document.all.item("notifierFirmDept").className = "field";
	document.all.item("notifierFirmDept").readOnly = false;

	var len = document.all.item("notifierStaffTitle").length;	

	for(var i=0;i<len;i++ )
	{			
		document.getElementsByName("notifierStaffTitle")[i].className="field";		
		document.getElementsByName("notifierStaffTitle")[i].disabled = false;	
	}

	var len1 = document.all.item("notifierType").length;	

	for(var i=0;i<len1;i++ )
	{		
		document.getElementsByName("notifierType")[i].className="field";		
		document.getElementsByName("notifierType")[i].disabled = false;	
	}
	
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
         url+="&systemType=MED010001";
         url+="&uploadId="+form1.id.value;
         url+="&dbName=Med0001DB";
         
	 returnWindow = window.open(url,'上傳檔案',prop);
}

function chgBadReactionResults()
{
	var isClear = true;
	
	var badReactionResults = form1.badReactionResults;
	for(var i=0;i<badReactionResults.length;i++)
	{
		if(badReactionResults[i].checked && "01" == badReactionResults[i].value)
		{
			document.all.item("badReactionResultsDeathDate").disabled = false;
	    	document.all.item("badReactionResultsDeathDate").className = "field";
	    	document.all.item("btn_badReactionResultsDeathDate").disabled = false;
	    	document.all.item("btn_badReactionResultsDeathDate").className = "field";
	    	document.all.item("badReactionResultsDeathReason").className = "field";
	    	document.all.item("badReactionResultsDeathReason").disabled = false;
	    	isClear = false;
		}
	}
	if(isClear)
	{
		document.all.item("badReactionResultsDeathDate").disabled = true;
		document.all.item("btn_badReactionResultsDeathDate").disabled = true;
		document.all.item("badReactionResultsDeathReason").disabled = true;
		document.all.item("badReactionResultsDeathDate").value = "";
		document.all.item("badReactionResultsDeathReason").value = "";
	}
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');beforeInit();init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<iframe name="saveContainerFrame" width="0" height="0" frameborder="0">
</iframe>

<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="comple0501q.jsp" />
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
    <input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="med0001ID" value="<%=obj.getMed0001ID()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="inOrOut" value="<%=User.getInORout()%>">
	<input type="hidden" name="isAlreadyAutoSave">
	<input type="hidden" name="changeTabValue" value="<%=obj.getChangeTabValue()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    
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
	<td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">不良反應</a></td>
	<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">不良品</a></td>
	<td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">相關附件</a></td>
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
			<td nowrap class="td_form" width="15%">案件編號</td>
			<td nowrap class="td_form_white" width="35%">
                <input class="field_RO" type="text" name="applNo" value="<%=obj.getApplNo()%>">
			</td>
			<td nowrap class="td_form" width="15%">案件狀態</td>
			<td nowrap class="td_form_white"  width="35%">
			    <input class="field_RO" type="text" name="statusCh" value="<%=obj.getStatusCh()%>"> 
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">關聯案件編號</td>
			<td nowrap class="td_form_white" width="35%">
                <input class="field_RO" type="text" name="applNo1" value="<%=obj.getApplNo1()%>">
			</td>
			<td nowrap class="td_form" width="15%">關聯案件狀態</td>
			<td nowrap class="td_form_white"  width="35%">
			    <input class="field_RO" type="text" name="statusCh1" value="<%=obj.getStatusCh1()%>"> 
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">發生日期</td>
			<td nowrap class="td_form_white" width="35%">
                <%=View.getPopCalendar("field","occurDate",obj.getOccurDate())%>
			</td>
			<td nowrap class="td_form" width="15%">通報中心<br>接獲通報日期</td>
			<td nowrap class="td_form_white"  width="35%">
                <%=View.getPopCalendar("field_RO","notifierRevDate",obj.getNotifierRevDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">通報者獲知日期</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getPopCalendar("field","notifierRepDate",obj.getNotifierRepDate())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">原始藥物不良事件<br>獲知來源</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="radio" name="drugEventsSources" value="1" <%if("1".equals(obj.getDrugEventsSources())) out.print("checked"); %>>由醫療人員轉知(
				<%=View.getCommonRadioBoxOption("field","medicalStaff","medTitle",obj.getMedicalStaff(),"")%>
					<input class="field" type="text" name="medicalStaffOther" size="50" maxlength="30"  value="<%=obj.getMedicalStaffOther()%>"  />)
					<br>
				<input class="field" type="radio" name="drugEventsSources" value="2" <%if("2".equals(obj.getDrugEventsSources())) out.print("checked"); %>>由衛生單位得知(
					<%=View.getRadioBoxTextOption("field","healthUnits","1;衛生局(所);2;其他",obj.getHealthUnits())%>
					<input class="field" type="text" name="healthUnitsOther" size="50" maxlength="30" value="<%=obj.getHealthUnitsOther()%>"/>
					)
					<br>
				<input class="field" type="radio" name="drugEventsSources" value="3" <%if("3".equals(obj.getDrugEventsSources())) out.print("checked"); %>>廠商<br>
				<input class="field" type="radio" name="drugEventsSources" value="4" <%if("4".equals(obj.getDrugEventsSources())) out.print("checked"); %>>由民眾主動告知
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">案例來源</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getRadioBoxTextOption("field","caseSource","in;國內，或;out;國外",obj.getCaseSource())%>，
				<%--<input class="field" type="text"  name="caseSourceOutCountry" size="35" maxlength="20" value="<%=obj.getCaseSourceOutCountry()%>"  /> --%>
				<select class="field" name="caseSourceOutCountry" id="caseSourceOutCountry">
					<%=View.getOption("select A.codeId , A.codeId+' '+A.codeName from CommonCode A where A.commonCodeKind.codeKindId = 'COUC'",obj.getCaseSourceOutCountry()) %>
				</select>
				(國家)
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form">報告類別</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field","reportKind","1;初始報告;2;追蹤報告",obj.getReportKind())%>，
				第<input class="field" type="text" name="trackingNum" size="5" maxlength="2" value="<%=obj.getTrackingNum()%>"  />次<br>
			</td>		
			<td nowrap class="td_form">矯正措施</td>
			<td nowrap class="td_form_white" >
			    <%=View.getCommonRadioBoxOption("field","correctiveAction","medYNO",obj.getCorrectiveAction(),"")%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">附件</td>
			<td nowrap class="td_form_white" >
				<%=View.getRadioBoxTextOption("field","attachment","N;無;Y;有",obj.getAttachment())%>
				，共<input class="field" type="text" name="attachmentYnum" size="5" maxlength="2" value="<%=obj.getAttachmentYnum()%>" />件
			</td>
			<td nowrap class="td_form">產品經公告列入<br>藥物安全監視</td>
			<td nowrap class="td_form_white">
			    <%=View.getCommonRadioBoxOption("field","drugSafetyMonitoring","medYNO",obj.getDrugSafetyMonitoring(),"")%>
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
			    <input type="button" class="field" name="btnNotifierDept" onClick="popNotifierDept(notifierType);" value="查詢" width="120px"  >
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
				<input class="field_RO" type="radio" name="notifierType" value="03" <%if("03".equals(obj.getNotifierType())) out.print("checked"); %>>醫療人員，
				            醫院名稱︰<input class="field_RO" type="text" name="notifierStaffHospital" size="30" maxlength="30"  value="<%=obj.getNotifierStaffHospital()%>"  />
					<br>&nbsp;&nbsp;&nbsp;&nbsp;(職稱：
					<%=View.getCommonRadioBoxOption("field_RO","notifierStaffTitle","medTitle",obj.getNotifierStaffTitle(),"")%>
					<input class="field_RO" type="text" name="notifierStaffTitleOther" value="<%=obj.getNotifierStaffTitleOther()%>" />)
					<br>
				<input class="field_RO" type="radio" name="notifierType" value="02" <%if("02".equals(obj.getNotifierType())) out.print("checked"); %>>廠商，服務機構︰
				<input class="field_RO" type="text"  name="notifierFirmDept" size="30" maxlength="30"  value="<%=obj.getNotifierFirmDept()%>"  /><br>
				<input class="field_RO" type="radio" name="notifierType" value="01" <%if("01".equals(obj.getNotifierType())) out.print("checked"); %>>民眾<br>
			    <input class="field_RO" type="radio" name="notifierType" value="04" <%if("04".equals(obj.getNotifierType())) out.print("checked"); %>>衛生單位
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">您是否願意提供廠商<br>您的聯絡資訊<br>以助廠商分析不良事件</td>
			<td nowrap class="td_form_white" colspan="3" >
				<%=View.getRadioBoxTextOption("field","isContactYn","Y;願意;N;不願意",obj.getIsContactYn())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">病人相關資料</td>
		</tr>
		<tr>
			<td nowrap class="td_form">病人識別代碼</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" size="15" maxlength="10" name="badReactionPatientCode" value="<%=obj.getBadReactionPatientCode()%>" />
			</td>
			<td nowrap class="td_form">性別</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field","badReactionSex","M;男;F;女",obj.getBadReactionSex())%>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">出生日期</td>
			<td nowrap class="td_form_white" colspan="3" >
				<%=View.getPopCalendar("field","badReactionBirthday",obj.getBadReactionBirthday())%>
				&nbsp;&nbsp;(或約<input type="text" size="3" maxlength="3" name="badReactionAge" value="<%=obj.getBadReactionAge()%>"  />歲)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">身高</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" size="3" maxlength="3" name="badReactionHeight" value="<%=obj.getBadReactionHeight()%>" />公分
			</td>
			<td nowrap class="td_form">體重</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" size="3" maxlength="3" name="badReactionWeight" value="<%=obj.getBadReactionWeight()%>"/>公斤
			</td>				
		</tr>
	</table>
	
	<table id="Tab2" width="100%" align="center" class="table_form">
	    <tr>
			<td nowrap class="td_form_left" colspan="4">不良事件類別</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">不良事件類別</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCheckBoxTextOption("field","eventKind","1;不良反應;2;不良品",obj.getEventKind())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">不良事件資訊</td>
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
			<td nowrap class="td_form" width="15%">許可證申請商</td>
			<td nowrap class="td_form_white" width="35%">
			    <input class="field_X" type="text" disabled name="medPermitFirmCode" size="10" value="<%=obj.getMedPermitFirmCode()%>">
				<input class="field_X" type="text" disabled name="medPermitFirm" size="20" value="<%=obj.getMedPermitFirm()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">中文品名</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field_X" type="text" disabled name="medCname" size="40"  value="<%=obj.getMedCname()%>">
			</td>
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
			<td nowrap class="td_form">製造廠</td>
			<td nowrap class="td_form_white">
				<input class="field_X" type="text" disabled name="medFactory" size="30"  value="<%=obj.getMedFactory()%>">
			</td>
			<td nowrap class="td_form">製造廠國別</td>
			<td nowrap class="td_form_white">
				<input class="field_X" type="text" disabled name="medCountr" size="30"  value="<%=obj.getMedCountr()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">型號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medModel" size="10" maxlength="10" value="<%=obj.getMedModel()%>">
			</td>
			<td nowrap class="td_form">序號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medNo" size="10" maxlength="10" value="<%=obj.getMedNo()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">批號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medLotNum"  size="10" maxlength="10" value="<%=obj.getMedLotNum()%>">
			</td>
			<td nowrap class="td_form">軟體版本</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medSoftwareVersion"  size="10" maxlength="10" value="<%=obj.getMedSoftwareVersion()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造日期</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medManufactureDate" size="7" maxlength="7" value="<%=obj.getMedManufactureDate()%>"/>
			</td>
			<td nowrap class="td_form">有效日期/保存期限</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medEffectiveDate" size="7" maxlength="7" value="<%=obj.getMedEffectiveDate()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">採購日期</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="medPurchaseDate" size="7" maxlength="7" value="<%=obj.getMedPurchaseDate()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">使用日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field","medUseDate",obj.getMedUseDate())%>
			</td>
			<td nowrap class="td_form">使用原因</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="medUseReason" size="30" maxlength="30" value="<%=obj.getMedUseReason()%>">
			</td>
		</tr>
	</table>
	
	<table id="Tab3" width="100%" align="center" class="table_form">
	   <tr>
			<td nowrap class="td_form" width="15%">不良反應結果</td>
			<td nowrap class="td_form_white" colspan="3" width="85%">
				<%=obj.getCheckBoxOptionAD("field","badReactionResults","select codeId,codeName from CommonCode where commonCodeKind.codeKindId = 'MEDAD1'",obj.getBadReactionResults(),obj.getBadReactionResultsDeathDate(),obj.getBadReactionResultsDeathReason(),obj.getBadReactionResultsOther())  %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">醫療器材操作者</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getCommonRadioBoxOption("field","medOperator","medOperator",obj.getMedOperator(),"")%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">器材處置現況</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="medDisposalStatus" value="1" <%if("1".equals(obj.getMedDisposalStatus())) out.print("checked"); %>>已銷毀&nbsp;&nbsp;
				<input class="field" type="radio" name="medDisposalStatus" value="2" <%if("2".equals(obj.getMedDisposalStatus())) out.print("checked"); %>>尚在調查中&nbsp;&nbsp;
				<input class="field" type="radio" name="medDisposalStatus" value="3" <%if("3".equals(obj.getMedDisposalStatus())) out.print("checked"); %>>尚植於病患體內&nbsp;&nbsp;
				<input class="field" type="radio" name="medDisposalStatus" value="4" <%if("4".equals(obj.getMedDisposalStatus())) out.print("checked"); %>>於<%=View.getPopCalendar("field","medDisposalStatusDate",obj.getMedDisposalStatusDate())%>退還廠商(原廠)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">器材使用</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field","medUse","medUse",obj.getMedUse(),"")%>
				<input type="text" size="20" maxlength="30" name="medUseOther" value="<%=obj.getMedUseOther()%>"  />
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">曾使用同類醫材<br>之經驗</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="onceUseMed" value="Y" <%if("Y".equals(obj.getOnceUseMed())) out.print("checked"); %>>是，醫材名稱
				<input class="field" type="text" name="onceUseMedName" size="20" maxlength="20" value="<%=obj.getOnceUseMedName()%>"  /> 
				若發生不良反應請描述<input class="field" type="text" size="20" maxlength="30" name="onceUseMedOther"  value="<%=obj.getOnceUseMedOther()%>"/> <br>
				<input class="field" type="radio" name="onceUseMed" value="N" <%if("N".equals(obj.getOnceUseMed())) out.print("checked"); %>>否&nbsp;&nbsp;
				<input class="field" type="radio" name="onceUseMed" value="O" <%if("O".equals(obj.getOnceUseMed())) out.print("checked"); %>>無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">停用後不良反應症狀<br>是否減輕</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getCommonRadioBoxOption("field","stopMedMitigate","medYNO",obj.getStopMedMitigate(),"")%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">再使用是否出現<br>同樣反應</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field","sameReaction","medYNO",obj.getSameReaction(),"")%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">不良事件描述資訊</td>
		</tr>
		<tr>		
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="15%">發生日期</th>
		         <th class="listTH" width="15%">部位</th>
		         <th class="listTH" width="30%">症狀</th>
		         <th class="listTH" width="10%">嚴重程度</th>
		         <th class="listTH" width="30%">處置</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed0002Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed0002DbView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">相關檢查及檢驗數據資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="15%">檢驗日期</th>
		         <th class="listTH" width="45%">檢驗項目</th>
		         <th class="listTH" width="40%">檢驗數據</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed0003Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed0003DbView">
			   
			   </tbody>
               </table>
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
		         <th class="listTH" width="25%">許可證字號</th>
		         <th class="listTH" width="20%">品名</th>
		         <th class="listTH" width="10%">許可證申請商</th>
		         <th class="listTH" width="10%">型號</th>
		         <th class="listTH" width="10%">醫材主類別</th>		         
		         <th class="listTH" width="10%">使用日期</th>
		         <th class="listTH" width="15%">使用原因</th>
		         <th width="5%" class="listTH">
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed0004Db()">
		         </th>
	           </tr>
	           </thead>

	           <tbody id="attMed0004DbView">
			   
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
		         <input class="field_Q" name="btnAdd" value="+" type="button" onClick="addMed0005Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed0005DbView">
			   
			   </tbody>
               </table>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">其他相關資料</td>
			<td nowrap class="td_form_white" colspan="3">
			    <textarea name="otherRelatedData" cols="60" rows="2" maxlength="255"><%=obj.getOtherRelatedData()%></textarea>
			</td>
		</tr>
	</table>
	
	<table id="Tab4" width="100%" align="center" class="table_form">
	   <tr>
			<td nowrap class="td_form" width="15%">產品問題分類</td>
			<td nowrap class="td_form_white" colspan="3" width="85%">
               (1)器材操作：<%=View.getCommonCheckBoxTextOption("field","productProblemKind1","medOperating",obj.getProductProblemKind1())%><br>
               (2)環境設施：<%=View.getCommonCheckBoxTextOption("field","productProblemKind2","medFacility",obj.getProductProblemKind2())%><br>
               (3)人因：<%=View.getCommonCheckBoxTextOption("field","productProblemKind3","medPeoFactor",obj.getProductProblemKind3())%><br>
               (4)物理特性：<%=View.getCommonCheckBoxTextOption("field","productProblemKind4","medPhysical",obj.getProductProblemKind4())%><br>
               (5)其他(請敘述)：<input type="text" size="40" maxlength="30" name="productProblemKindOther" value="<%=obj.getProductProblemKindOther() %>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">不良品缺陷描述</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="defProductDescription" cols="60" rows="2"><%=obj.getDefProductDescription()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">其他資料</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="defProductOtherDescription" cols="60" rows="2"><%=obj.getDefProductOtherDescription()%></textarea>
			</td>
		</tr>		
	</table>
	<table id="Tab5" width="100%" align="center" class="table_form">
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
</td>
</tr>
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
	 
	  case "doDoQuit":
		  if(confirm("確定回到查詢頁面?"))
		  {
		    form1.action = "comple0501f.jsp";
			form1.state.value = "queryAll";
			form1.submit();
		  }
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
	    

      //原始藥物不良事件獲知來源
	  var len = document.all.item("drugEventsSources").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("drugEventsSources")[i].disabled = true;	
	  }
	  var len = document.all.item("medicalStaff").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("medicalStaff")[i].disabled = true;	
	  }
	  var len = document.all.item("healthUnits").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		   document.all.item("healthUnits")[i].disabled = true;	
	  }
	  if(isObj(document.all.item("medicalStaffOther")))
		  setFormItem("medicalStaffOther","RO");
	  if(isObj(document.all.item("healthUnitsOther")))
		  setFormItem("healthUnitsOther","RO");
	  
	  
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
		
	  //矯正措施
	  var len = document.all.item("correctiveAction").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("correctiveAction")[i].disabled = true;	
	  }
	  //附件
	  var len = document.all.item("attachment").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("attachment")[i].disabled = true;	
	  }
	  if(isObj(document.all.item("attachmentYnum")))
		  setFormItem("attachmentYnum","RO"); 
	  
	  //產品經公告列入藥物安全監視
	  var len = document.all.item("drugSafetyMonitoring").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("drugSafetyMonitoring")[i].disabled = true;	
	  }

	  //服務機構
	  if(isObj(document.all.item("notifierDeptID")))
		  setFormItem("notifierDeptID","RO"); 
	  if(isObj(document.all.item("notifierDept")))
		  setFormItem("notifierDept","RO"); 
	  if(isObj(document.all.item("btnNotifierDept")))
		  setFormItem("btnNotifierDept","RO"); 
	  
      //屬性
	  var len = document.all.item("notifierType").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("notifierType")[i].disabled = true;	
	  }
	  if(isObj(document.all.item("notifierStaffHospital")))
		  setFormItem("notifierStaffHospital","RO");  
	  
	  var len = document.all.item("notifierStaffTitle").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("notifierStaffTitle")[i].disabled = true;	
	  }
	  if(isObj(document.all.item("notifierStaffTitleOther")))
		  setFormItem("notifierStaffTitleOther","RO");  
	  if(isObj(document.all.item("notifierFirmDept")))
		  setFormItem("notifierFirmDept","RO");  

	  
	  var len = document.all.item("isContactYn").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("isContactYn")[i].disabled = true;	
	  }

	  //不良事件類別
	  var len = document.all.item("eventKind").length;	
	  for(var i=0 ; i<len ; i++ )
	  {			
		  document.all.item("eventKind")[i].disabled = true;	
	  }
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
