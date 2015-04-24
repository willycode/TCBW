<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">

function checkFieldCommon()
{
	var alertStr="";
	
	<%=TCBWCommon.getCheckFiled("MED04")%>
    
	var eventKind=form1.eventKind;

	if (eventKind!=null && eventKind.length>0) 
	{
		for (j=0; j<eventKind.length; j++) 
		{
			if(eventKind[0].checked)
			{	
				 <%=TCBWCommon.getCheckFiled("MED01")%>
			}
			if(eventKind[1].checked)
			{
				 <%=TCBWCommon.getCheckFiled("MED02")%>
			}
		}
    }
	
	//基本資料頁籤
	//檢查案例來源
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
	if(eventKind[0].checked)
	{
		
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
					{
						alertStr += checkEmpty(form1.badReactionResultsOther,"非嚴重不良事件說明欄位");
					}
				}
				else
				{
					if(badReactionResultsCheckBox.value == "01")
					{
						form1.badReactionResultsDeathDate.style.backgroundColor = "";
						form1.badReactionResultsDeathReason.style.backgroundColor = "";
					}
					if(badReactionResultsCheckBox.value == "08")
					{
						form1.badReactionResultsOther.style.backgroundColor = "";
					}
				}
			}
		}
	}

	if(alertStr.length!=0)
	{ 
	  return alertStr; 
	}
	return ""; 
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
	  //依不良事件類別，顯示不良反應  or 不良品  頁簽
	  document.getElementById("t3").style.display = 'none';
	  document.getElementById("aTab3").style.display = 'none';
	}
	
	if (isObj(document.all.item("t4")))
	{
	  document.getElementById("t4").className = "tab_border2";
	  document.getElementById("aTab4").className = "";
	  document.getElementById("Tab4").style.display = 'none';
	  document.getElementById("t4").style.display = 'none';
	  document.getElementById("aTab4").style.display = 'none';
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
	
	if (isObj(document.all.item("eventKind")))
	{	
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


	if (isObj(document.all.item("changeTabValue")))
	{
		document.all.item("changeTabValue").value=tabId;
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
	else if(tabId == 6)
	{
		document.getElementById("t6").className = "tab_border1";
		document.getElementById("Tab6").style.display = '';
		document.getElementById("aTab6").className = "text_w";
	}
	else if(tabId == 7)
	{
		document.getElementById("t7").className = "tab_border1";
		document.getElementById("Tab7").style.display = '';
		document.getElementById("aTab7").className = "text_w";
	}
	else if(tabId == 8)
	{
		document.getElementById("t8").className = "tab_border1";
		document.getElementById("Tab8").style.display = '';
		document.getElementById("aTab8").className = "text_w";
	}
	else if(tabId == 9)
	{
		document.getElementById("t9").className = "tab_border1";
		document.getElementById("Tab9").style.display = '';
		document.getElementById("aTab9").className = "text_w";
	}
	else if(tabId == 10)
	{
		document.getElementById("t10").className = "tab_border1";
		document.getElementById("Tab10").style.display = '';
		document.getElementById("aTab10").className = "text_w";
	}
	else if(tabId == 11)
	{
		document.getElementById("t11").className = "tab_border1";
		document.getElementById("Tab11").style.display = '';
		document.getElementById("aTab11").className = "text_w";
	}
	else if(tabId == 12)
	{
		document.getElementById("t12").className = "tab_border1";
		document.getElementById("Tab12").style.display = '';
		document.getElementById("aTab12").className = "text_w";
	}
	else
	{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}

function rmObj(obj)
{
	$("#"+obj).remove();
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
function addMed0004Db(id,permit,permitNumber,ccname,permitFirm,model,
		mainCategory,useDate,useReason,mainCategoryCode)
{
	var Med0004DbCount;
	Med0004DbCount = numberMed0004Db++;		
	var newItem ="<tr id=\"med0004DbfileType"+Med0004DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med0004DbType'>";
	newItem +="<input type='hidden' name='med0004DbTypeId' value=\""+(id!=null?id:'')+"\">";

	newItem +="<select class=\"field\" type=\"select\"  id ='permit"+Med0004DbCount+"' onChange=\"manyPermitData1('permit"+Med0004DbCount+"','permitNumber"+Med0004DbCount+"','"+Med0004DbCount+"');\"  name=\"permit"+"\" >"+"<%=View.getOptionCodeKind("MEDPKID","").replaceAll("\n","").replace("'","\\'")%>";
	newItem +="<input id ='permitNumber"+Med0004DbCount+"' onBlur=\"manyPermitData1('permit"+Med0004DbCount+"','permitNumber"+Med0004DbCount+"','"+Med0004DbCount+"');\" class=\"field_Q\" type=\"text\" name=\"permitNumber"+"\" size=\"6\"  maxlength=\"6\"   value=\""+(permitNumber!=null?permitNumber:'')+"\">&nbsp;";
	newItem +="<input type=\"button\" name=\"btnQryExpense\" onClick=\"manyPermitDataQ('permit"+Med0004DbCount+"','permitNumber"+Med0004DbCount+"','"+Med0004DbCount+"');\" value=\"查詢\" width=\"60px\" class=\"field\" >";
	newItem +="</td>";

	
	//document.all.item("permit"+Med0004DbCount).value=permit;
	
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
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med0004DbfileType"+Med0004DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed0004DbView").append(newItem);
	$("#permit"+Med0004DbCount).val(permit);
	$("#mainCategoryCode"+Med0004DbCount).val(mainCategoryCode);
	$("#mainCategory"+Med0004DbCount).val(mainCategory);
}

//併用藥品資訊
var numberMed0005Db = 1;
function addMed0005Db(id,cName,content,formulation,drgApproach,dose,frequency,sDate,eDate,medCauses)
{
	var Med0005DbCount;
	Med0005DbCount = numberMed0005Db++;		
	var newItem ="<tr id=\"med0005DbfileType"+Med0005DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med0005DbType'>";
	newItem +="<input type='hidden' name='med0005DbTypeId' value=\""+(id!=null?id:'')+"\">";
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
	newItem +="~" +'<input type=text class=field_Q name=eDate  id=eDate' + Med0005DbCount + ' size=7 value=\'' + (eDate!=null?eDate:'') + '\'><button class="field_Q" type="button" name="btn_eDate' + Med0005DbCount + '" onclick="popCalendar(\'eDate' + Med0005DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='medCauses"+Med0005DbCount+"' class=\"field_Q\" type=\"text\" name=\"medCauses"+"\" size=\"15\"  maxlength=\"50\" value=\""+(medCauses!=null?medCauses:'')+"\">&nbsp;";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed0005Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med0005DbfileType"+Med0005DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed0005DbView").append(newItem);
	$("#formulation"+Med0005DbCount).val(formulation);
	$("#drgApproach"+Med0005DbCount).val(drgApproach);
	$("#dose"+Med0005DbCount).val(dose);
	$("#frequency"+Med0005DbCount).val(frequency);
}

//病人問題代碼
var numberMed1005Db = 0;
function addMed1005Db(id,medicalIssuesCode,medicalIssuesName)
{
	var Med1005DbCount;
	Med1005DbCount = numberMed1005Db++;	
	
	var newItem ="<tr id=\"med1005DbfileType"+Med1005DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med1005DbType'>";
	newItem +="<input type='hidden' name='med1005DbRow' value=\""+Med1005DbCount+"\">";
	newItem +="<input type='hidden' name='med1005DbTypeId' value=\""+(id!=null?id:'')+"\">";
	newItem +="<input type='hidden' name='med1005DbTypeCode' id='med1005DbTypeCode" + Med1005DbCount + "' value=\""+(medicalIssuesCode!=null?medicalIssuesCode:'')+"\">";
	newItem +="<input type='text' class='field_P' name='medicalIssuesName" + Med1005DbCount + "'  id='medicalIssuesName" + Med1005DbCount + "' size='50' maxlength='50' value=\"" + (medicalIssuesName!=null?medicalIssuesName:'') + "\"><button class='field' type='button' name='btn_medicalIssues" + Med1005DbCount + "' onclick=popMed1005Code('med1005DbTypeId','med1005DbTypeCode" + Med1005DbCount + "','medicalIssuesName" + Med1005DbCount + "') title='代碼輔助視窗'>...</button>";
	newItem +="</td>";
	

	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed1005Db()\" >&nbsp;";
	newItem +="<input class=\"field\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med1005DbfileType"+Med1005DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed1005DbView").append(newItem);
}

//醫材問題代碼-不良反應初評頁籤
var numberMed1006_0403Db = 0;
function addMed1006_0403Db(id,patientIssuesCode,patientIssuesName)
{
	var Med1006_0403DbCount;
	Med1006_0403DbCount = numberMed1006_0403Db++;	
	
	var newItem ="<tr id=\"med1006_0403DbfileType"+Med1006_0403DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med1006_0403DbType'>";
	newItem +="<input type='hidden' name='med1006_0403DbRow' value=\""+Med1006_0403DbCount+"\">";
	newItem +="<input type='hidden' name='med1006_0403DbTypeId' value=\""+(id!=null?id:'')+"\">";
	newItem +="<input type='hidden' name='med1006_0403DbTypeCode' id='med1006_0403DbTypeCode" + Med1006_0403DbCount + "' value=\""+(patientIssuesCode!=null?patientIssuesCode:'')+"\">";
	newItem +="<input type='text' class='field_P' name='patientIssuesName_0403" + Med1006_0403DbCount + "'  id='patientIssuesName_0403" + Med1006_0403DbCount + "' size='50' maxlength='50' value=\"" + (patientIssuesName!=null?patientIssuesName:'') + "\"><button class='field' type='button' name='btn_patientIssues_0403" + Med1006_0403DbCount + "' onclick=popMed1006Code('med1006_0403DbTypeCode" + Med1006_0403DbCount + "','patientIssuesName_0403" + Med1006_0403DbCount + "')  title='代碼輔助視窗'>...</button>";
	newItem +="</td>";
	

	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field\" name=\"btnAdd_0403\" value=\"+\" type=\"button\" onClick=\"addMed1006_0403Db('"+ id + "')\" >&nbsp;";
	newItem +="<input class=\"field\" name=\"btnDel_0403\" value=\"-\" type=\"button\" onClick=\"rmObj('med1006_0403DbfileType"+Med1006_0403DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	$("#attMed1006_0403DbView").append(newItem);
	
}


//醫材問題代碼-不良品初評頁籤
var numberMed1006Db = 0;
function addMed1006Db(id,patientIssuesCode,patientIssuesName)
{
	var Med1006DbCount;
	Med1006DbCount = numberMed1006Db++;	
	
	var newItem ="<tr id=\"med1006DbfileType"+Med1006DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med1006DbType'>";
	newItem +="<input type='hidden' name='med1006DbRow' value=\""+Med1006DbCount+"\">";
	newItem +="<input type='hidden' name='med1006DbTypeId' value=\""+(id!=null?id:'')+"\">";
	newItem +="<input type='hidden' name='med1006DbTypeCode' id='med1006DbTypeCode" + Med1006DbCount + "' value=\""+(patientIssuesCode!=null?patientIssuesCode:'')+"\">";
	newItem +="<input type='text' class='field_P' name='patientIssuesName" + Med1006DbCount + "'  id='patientIssuesName" + Med1006DbCount + "' size='50' maxlength='50' value=\"" + (patientIssuesName!=null?patientIssuesName:'') + "\"><button class='field' type='button' name='btn_patientIssues" + Med1006DbCount + "' onclick=popMed1006Code('med1006DbTypeCode" + Med1006DbCount + "','patientIssuesName" + Med1006DbCount + "')  title='代碼輔助視窗'>...</button>";
	newItem +="</td>";
	

	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed1006Db('"+ id + "')\" >&nbsp;";
	newItem +="<input class=\"field\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med1006DbfileType"+Med1006DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	$("#attMed1006DbView").append(newItem);
	
}

//醫材問題代碼-不良品複評頁籤
var numberMed1007Db = 0;
function addMed1007Db(id,patientIssuesCode,patientIssuesName)
{
	var Med1007DbCount;
	Med1007DbCount = numberMed1007Db++;	
	
	var newItem ="<tr id=\"med1007DbfileType"+Med1007DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med1007DbType'>";
	newItem +="<input type='hidden' name='med1007DbRow' value=\""+Med1007DbCount+"\">";
	newItem +="<input type='hidden' name='med1007DbTypeId' value=\""+(id!=null?id:'')+"\">";
	newItem +="<input type='hidden' name='med1007DbTypeCode' id='med1007DbTypeCode" + Med1007DbCount + "' value=\""+(patientIssuesCode!=null?patientIssuesCode:'')+"\">";
	newItem +="<input type='text' class='field_P' name='patientIssuesNameRe' id='patientIssuesNameRe" + Med1007DbCount + "' size='50' maxlength='50' value=\"" + (patientIssuesName!=null?patientIssuesName:'') + "\"><button class='field' type='button' name='btn_patientIssuesRe" + Med1007DbCount + "' onclick=popMed1006Code('med1007DbTypeCode" + Med1007DbCount + "','patientIssuesNameRe" + Med1007DbCount + "') title='代碼輔助視窗'>...</button>";
	newItem +="</td>";
	

	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field\" name=\"btnAddRe\" value=\"+\" type=\"button\" onClick=\"addMed1007Db()\" >&nbsp;";
	newItem +="<input class=\"field\" name=\"btnDelRe\" value=\"-\" type=\"button\" onClick=\"rmObj('med1007DbfileType"+Med1007DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	$("#attMed1007DbView").append(newItem);
	
}

var popWinName;
function permitDataQ()
{
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var medPermit = form1.medPermit.value;
	var medPermitNumber = form1.medPermitNumber.value;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("../medex/medex0107f.jsp?medPermit="+medPermit+"&medPermitNumber="+medPermitNumber,'popWinE',prop);		
}


function permitData2(id,a)
{
	var medPermit = id.substring(0,2);
	var medPermitNumber = id.substring(2,8);
	form1.medPermit.value = medPermit;
	form1.medPermitNumber.value = medPermitNumber;
	permitData1('medPermit','medPermitNumber');
}

function permitData1(medPermit1,medPermitNumber1)
{
	var medPermit = document.all.item(medPermit1).value;
	var medPermitNumber = document.all.item(medPermitNumber1).value;

	var q = "&medPermit="+medPermit;
	    q += "&medPermitNumber="+medPermitNumber;


	if(medPermit=="Z0" || medPermit=="Z5")
	{
		 document.all.item("medPermitFirmCode").disabled = false;
		 document.all.item("medPermitFirmCode").className = "field";
		 document.all.item("medCname").disabled = false;
		 document.all.item("medCname").className = "field";
		 document.all.item("medPermitFirm").disabled = false;
		 document.all.item("medPermitFirm").className = "field";
		 document.all.item("medFactory").disabled = false;
		 document.all.item("medFactory").className = "field";
		 document.all.item("medCountr").disabled = false;
		 document.all.item("medCountr").className = "field";
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

	    
	if(medPermit!="" && medPermitNumber!="")
	{
	  x = getRemoteData("../../ajax/jsonMedPermitObjects.jsp",q );
		
	  if(x!=null && x.length>0)
	  {
		var json1 = JSON.parse(x);

		if(isObj(document.all.item("medCname")))
		  form1.medCname.value = json1.obj0;  //中文品名 CHNAME

		if(isObj(document.all.item("medPermitFirmCode")))
		  form1.medPermitFirmCode.value = json1.obj2;  //申請商  APPUNNO

		if(isObj(document.all.item("medPermitFirm")))
		  form1.medPermitFirm.value = json1.obj3;  //申請商  APPNAME

		if(isObj(document.all.item("medFactory")))
		  form1.medFactory.value = json1.obj4;  //製造廠 FACTNAME

		if(isObj(document.all.item("medMainCategory")))
		  form1.medMainCategory.value = json1.obj5;  //主類別  DOCKNDID  DOCKNDMA

		if(isObj(document.all.item("medMainCategoryCodeName")))
          form1.medMainCategoryCodeName.value = json1.obj6;  //主類別  DOCKNDID  DOCKNDMA

        if(isObj(document.all.item("medSecCategoryCode")))
          form1.medSecCategoryCode.value = json1.obj7;  //次類別  MSKNDID  MSKNDMA
          
        if(isObj(document.all.item("medSecCategoryCodeName")))
          form1.medSecCategoryCodeName.value = json1.obj8;  //次類別  MSKNDID  MSKNDMA

        if(isObj(document.all.item("medCountr")))
          form1.medCountr.value = json1.obj9;  //製造廠國別 FACTCIDMA
        
	  }
	  else
	  { 
		  document.all.item(medPermit1).value = "";
		  document.all.item(medPermitNumber1).value = "";

		  
		    if(isObj(document.all.item("medCname")))
			  form1.medCname.value = "";  //中文品名 CHNAME
		    if(isObj(document.all.item("medPermitFirmCode")))
			  form1.medPermitFirmCode.value = "";  //申請商  APPUNNO
			if(isObj(document.all.item("medPermitFirm")))
			  form1.medPermitFirm.value = "";  //申請商  APPNAME
			if(isObj(document.all.item("medFactory")))
			  form1.medFactory.value = "";  //製造廠 FACTNAME
			if(isObj(document.all.item("medMainCategory")))
			  form1.medMainCategory.value = "";  //主類別  DOCKNDID  DOCKNDMA
			if(isObj(document.all.item("medMainCategoryCodeName")))
	          form1.medMainCategoryCodeName.value = "";  //主類別  DOCKNDID  DOCKNDMA
	        if(isObj(document.all.item("medSecCategoryCode")))
	          form1.medSecCategoryCode.value = "";  //次類別  MSKNDID  MSKNDMA
	        if(isObj(document.all.item("medSecCategoryCodeName")))
	          form1.medSecCategoryCodeName.value = "";  //次類別  MSKNDID  MSKNDMA
	        if(isObj(document.all.item("medCountr")))
	          form1.medCountr.value = "";  //製造廠國別 FACTCIDMA

	        alert("查無此許可證號!");
	  }
	}
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
	      document.all.item("mainCategoryCode"+count).value = json1.obj5;  //主類別  DOCKNDID  DOCKNDMA
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

		  if(isObj(document.all.item("medMainCategoryCode"+count)))
			 document.all.item("medMainCategoryCode"+count).value = "";  //主類別  DOCKNDID  DOCKNDMA
			 
		  alert("查無此許可證號!");
	  }
	}
}




$(function()
{
	if($("#notifier").attr("checked"))
	{
		notifierOpen();
	}
	
	$("#notifier").click(function() 
	{
		if($("#notifier").attr("checked"))
		{
			notifierOpen();
			
			//記錄log  
			var url = '../../ajax/jsonCommonLogDb.jsp';
				
			var q = "&code=MED04";
				q +="&methodName=open";
				q +="&db=Med0001Db";
				q +="&hql=select id,applNo,inOrOutcreator,notifierName from Med0001Db where id="+form1.id.value;

			var xUserUpdate = getRemoteData(url,q);
			    
		}
	});
	
	
	$("#btn_medMainCategoryCode").click(function() 
	{
		document.all.item("medSecCategoryCode").value="";
		document.all.item("medSecCategoryCodeName").value="";
		document.all.item("medSecCategory").value="";
	});
	
	$("#medMainCategoryCode").change(function()
	{ 
		document.all.item("medSecCategoryCode").value="";
		document.all.item("medSecCategoryCodeName").value="";
		document.all.item("medSecCategory").value="";
    });
	
	$(".field[name*=medMainCategory]").bind("blur", a);
	
	function a()
	{
		document.all.item("medSecCategoryCode").value="";
		document.all.item("medSecCategoryCodeName").value="";
		document.all.item("medSecCategory").value="";
	}

	var medPermit = form1.medPermit.value;
	var medPermitNumber = form1.medPermitNumber.value;

    if(medPermit=="Z0" || medPermit=="Z5")
    {
 		document.all.item("medPermitFirmCode").disabled = false;
 		document.all.item("medPermitFirmCode").className = "field";
 		document.all.item("medCname").disabled = false;
    	document.all.item("medCname").className = "field";
    	document.all.item("medPermitFirm").disabled = false;
    	document.all.item("medPermitFirm").className = "field";
    	document.all.item("medFactory").disabled = false;
    	document.all.item("medFactory").className = "field";
    	document.all.item("medCountr").disabled = false;
    	document.all.item("medCountr").className = "field";
    }   

    var len = document.all.item("notifierType").length;	
	   
	for(var i=0 ; i<len ; i++ )
	{			
		document.all.item("notifierType")[i].disabled = true;
	}
	
	
});

function notifierOpen()
{
	document.all.item("notifier").disabled=true;
	$("[name*=notifier]").each(function()
	{
		if($(this).attr("type") == "password")
		{
			var tmp = $('<input class="field_RO" type="text" id="' + $(this).attr("id") + '" name="' + $(this).attr("name") + '" size="' + $(this).attr("size") + '" value="' +  $(this).val() + '">'); 
			$(this).replaceWith(tmp);
		}
	});
	    
    var q = document.all.item("id").value;

    var x = getRemoteData("../../ajax/jsonMed0001Db.jsp",q );
	
    if(x!=null && x.length>0)
    {
	  var json1 = JSON.parse(x);

	  if(isObj(document.all.item("notifierName").value))
		document.all.item("notifierName").value =json1.notifierName;

	  if(isObj(document.all.item("notifierAreaCode").value))
		document.all.item("notifierAreaCode").value =json1.notifierAreaCode;
	  if(isObj(document.all.item("notifierTel").value))
		document.all.item("notifierTel").value =json1.notifierTel;
	  if(isObj(document.all.item("notifierTelExt").value))
		document.all.item("notifierTelExt").value =json1.notifierTelExt;

	  if(isObj(document.all.item("notifierEamil").value))
		document.all.item("notifierEamil").value =json1.notifierEamil;
	
	  if(isObj(document.all.item("notifierCounty").value))
		document.all.item("notifierCounty").value =json1.notifierCounty;
	  if(isObj(document.all.item("notifierZipCode").value))
		document.all.item("notifierZipCode").value =json1.notifierZipCode;
	  if(isObj(document.all.item("notifierAddress").value))
		document.all.item("notifierAddress").value =json1.notifierAddress;
	  if(isObj(document.all.item("notifierDeptID").value))
			document.all.item("notifierDeptID").value =json1.notifierDeptID;
	  if(isObj(document.all.item("notifierDept").value))
			document.all.item("notifierDept").value =json1.notifierDept;

     }
  
}

function upload(fileType)
{
	 var prop='';
	 var fileType = fileType;

	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();
	 var url="../../home/popManyUploadSimple.jsp?";
	 if(fileType == "medin0403") {
	     url+="fileKind=MED";
	     url+="&systemType=MED010003"; //醫材不良反應評估頁籤附件代碼
	     url+="&uploadId="+form1.id.value;
	     url+="&dbName=Med0007DB";
	} else if(fileType == "medin0703") {
		url+="fileKind=MED";
        url+="&systemType=MED010004"; //醫材不良品初評頁籤附件代碼
        url+="&uploadId="+form1.id.value;
        url+="&dbName=Med0008DB";
	} else if(fileType == "medin0803") {
		url+="fileKind=MED";
        url+="&systemType=MED010005"; //醫材不良品複評頁籤附件代碼
        url+="&uploadId="+form1.id.value;
        url+="&dbName=Med0009DB";
	} else {
		url+="fileKind=MED";
	    url+="&systemType=MED010001";
	    url+="&uploadId="+form1.id.value;
	    url+="&dbName=Med4001DB";
	}
	 returnWindow = window.open(url,'上傳檔案',prop);
}

function toShowMLMS(){
	var permitKey = form1.medPermit.value;
	var permitNo = form1.medPermitNumber.value;	
	var params = 'width=980,height=640,resizable=1,menubar=no,scrollbars=yes';
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("medin0204f.jsp?permitKey="+permitKey+"&permitNo="+permitNo,'popWinE',params);	
}

//案件再評估
function reAssessment(id,applNo,eventKind) {
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	var id = id;
	var applNo = applNo;
	var eventKind = eventKind;

	//帶案件id、案號、案件類別等資訊
	var q = "id=" + id;
		q +="&applNo=" + applNo;
	    q +="&eventKind=" + eventKind;
		
	prop=prop+"width=800,height=300,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/medin/medin0901f.jsp?" + q,"",prop);
	
}
//重新校正
function reCalibration(id,applNo,eventKind) {
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	var id = id;
	var applNo = applNo;
	var eventKind = eventKind;

	//帶案件id、案號、案件類別等資訊
	var q = "id=" + id;
		q +="&applNo=" + applNo;
	    q +="&eventKind=" + eventKind;
		
	prop=prop+"width=800,height=300,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/medin/medin0902f.jsp?" + q,"",prop);
	
}

//強制結案
function enforceClose(id,applNo,eventKind) {
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	var id = id;
	var applNo = applNo;
	var eventKind = eventKind;

	//帶案件id、案號、案件類別等資訊
	var q = "id=" + id;
		q +="&applNo=" + applNo;
	    q +="&eventKind=" + eventKind;
		
	prop=prop+"width=800,height=300,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/medin/medin0903f.jsp?" + q,"",prop);
	
}



</script>

