<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">


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
		
		var q = "&code=MED03";
		    q +="&methodName=open";
		    q +="&db=Med2001Db";
		    q +="&hql=select id,applNo,inOrOutcreator,notifierName from Med2001Db where id="+form1.id.value;

		    var xUserUpdate = getRemoteData(url,q);
	    
	  }
   });

});

function notifierOpen()
{
	document.all.item("notifier").disabled=true;

	 
    var q = document.all.item("id").value;

    var x = getRemoteData("../../ajax/jsonMed2001Db.jsp",q );
	
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

	  if(isObj(document.all.item("notifierEmail").value))
		document.all.item("notifierEmail").value =json1.notifierEamil;
	
	  if(isObj(document.all.item("notifierCounty").value))
		document.all.item("notifierCounty").value =json1.notifierCounty;
	  if(isObj(document.all.item("notifierZipCode").value))
		document.all.item("notifierZipCode").value =json1.notifierZipCode;
	  if(isObj(document.all.item("notifierAddress").value))
		document.all.item("notifierAddress").value =json1.notifierAddress;
	  if(isObj(document.all.item("notifierTitle").value))
			document.all.item("notifierTitle").value =json1.notifierTitle;

     }
  
}

//檢視許可證資料
function toShowMLMS(){
	var permitKey = form1.medPermit.value;
	var permitNo = form1.medPermitNumber.value;	
	var params = 'width=980,height=640,resizable=1,menubar=no,scrollbars=yes';
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("medin0204f.jsp?permitKey="+permitKey+"&permitNo="+permitNo,'popWinE',params);	
}


//不良事件描述資訊
var numberMed2002Db = 1;
function addMed2002Db(id,bulletinDate,position,symptom,severity,dealWith)
{
	var Med2002DbCount;
	Med2002DbCount = numberMed2002Db++;		
	var newItem ="<tr id=\"med2002DbfileType"+Med2002DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med2002DbType'>";
	newItem +="<input type='hidden' name='med2002DbTypeId' value=\""+(id!=null?id:'')+"\">";
	//newItem +='<input type=text class=field_Q name=bulletinDate  id=bulletinDate' + Med2002DbCount + ' size=7 value=\'' + (bulletinDate!=null?bulletinDate:'') + '\'><input class="field_Q" type="button" name="btn_bulletinDate' + Med2002DbCount + '" onclick="popCalendar(\'bulletinDate' + Med2002DbCount + '\')" value="..." title="萬年曆輔助視窗">';
	newItem +='<input type=text class=field_Q name=bulletinDate  id=bulletinDate' + Med2002DbCount + ' size=7 value=\'' + (bulletinDate!=null?bulletinDate:'') + '\'><button class="field_Q" type="button" name="btn_bulletinDate' + Med2002DbCount + '" onclick="popCalendar(\'bulletinDate' + Med2002DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='position"+Med2002DbCount+"' class=\"field_Q\" type=\"text\" name=\"position"+"\" size=\"20\"  maxlength=\"30\"   value=\""+(position!=null?position:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='symptom"+Med2002DbCount+"' class=\"field_Q\" type=\"text\" name=\"symptom"+"\" size=\"20\"  maxlength=\"30\" value=\""+(symptom!=null?symptom:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='severity"+Med2002DbCount+"' class=\"field_Q\" type=\"text\" name=\"severity"+"\" size=\"20\"  maxlength=\"30\" value=\""+(severity!=null?severity:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='dealWith"+Med2002DbCount+"' class=\"field_Q\" type=\"text\" name=\"dealWith"+"\" size=\"20\"  maxlength=\"30\" value=\""+(dealWith!=null?dealWith:'')+"\">&nbsp;";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed2002Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med2002DbfileType"+Med2002DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed2002DbView").append(newItem);
}



var numberMed2003Db = 1;
function addMed2003Db(id,testDate,testItems,testNum)
{
	var Med2003DbCount;
	Med2003DbCount = numberMed2003Db++;	
		
	var newItem ="<tr id=\"med2003DbfileType"+Med2003DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med2003DbType'>";
	newItem +="<input type='hidden' name='med2003DbTypeId' value=\""+(id!=null?id:'')+"\">";
	//newItem +='<input type=text class=field_Q name=testDate  id=testDate' + Med2003DbCount + ' size=7 value=\'' + (testDate!=null?testDate:'') + '\'><input class="field_Q" type="button" name="btn_testDate' + Med2003DbCount + '" onclick="popCalendar(\'testDate' + Med2003DbCount + '\')" value="..." title="萬年曆輔助視窗">';
	newItem +='<input type=text class=field_Q name=testDate  id=testDate' + Med2003DbCount + ' size=7 value=\'' + (testDate!=null?testDate:'') + '\'><button class="field_Q" type="button" name="btn_testDate' + Med2003DbCount + '" onclick="popCalendar(\'testDate' + Med2003DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='testItems"+Med2003DbCount+"' class=\"field_Q\" type=\"text\" name=\"testItems"+"\" size=\"40\"  value=\""+(testItems!=null?testItems:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='testNum"+Med2003DbCount+"' class=\"field_Q\" type=\"text\" name=\"testNum"+"\" size=\"40\"  value=\""+(testNum!=null?testNum:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed2003Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med2003DbfileType"+Med2003DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed2003DbView").append(newItem);
}

//併用醫療器材資訊
var numberMed2004Db = 1;
function addMed2004Db(id,permit,permitNumber,cName,permitFirm,model,
		mainCategory,useDate,useReason,mainCategoryCode)
{
	var Med2004DbCount;
	Med2004DbCount = numberMed2004Db++;		
	var newItem ="<tr id=\"med2004DbfileType"+Med2004DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med2004DbType'>";
	newItem +="<input type='hidden' name='med2004DbTypeId' value=\""+(id!=null?id:'')+"\">";

	newItem +="<select class=\"field\" type=\"select\"  id ='permit"+Med2004DbCount+"' onChange=\"manyPermitData1('permit"+Med2004DbCount+"','permitNumber"+Med2004DbCount+"','"+Med2004DbCount+"');\"  name=\"permit"+"\" >"+"<%=View.getOptionCodeKind("MEDPKID","").replaceAll("\n","").replace("'","\\'")%>";
	newItem +="<input id ='permitNumber"+Med2004DbCount+"' onBlur=\"manyPermitData1('permit"+Med2004DbCount+"','permitNumber"+Med2004DbCount+"','"+Med2004DbCount+"');\" class=\"field_Q\" type=\"text\" name=\"permitNumber"+"\" size=\"6\"  maxlength=\"6\"   value=\""+(permitNumber!=null?permitNumber:'')+"\">&nbsp;";
	newItem +="<input type=\"button\" name=\"btnQryExpense\" onClick=\"manyPermitDataQ('permit"+Med2004DbCount+"','permitNumber"+Med2004DbCount+"','"+Med2004DbCount+"');\" value=\"查詢\" width=\"60px\" class=\"field\" >";
	newItem +="</td>";


	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='ccname"+Med2004DbCount+"' class=\"field_Q\" type=\"text\" name=\"ccname"+"\" size=\"15\"  maxlength=\"30\" value=\""+(cName!=null?cName:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='permitFirm"+Med2004DbCount+"' class=\"field_Q\" type=\"text\" name=\"permitFirm"+"\" size=\"15\"  maxlength=\"30\" value=\""+(permitFirm!=null?permitFirm:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='model"+Med2004DbCount+"' class=\"field_Q\" type=\"text\" name=\"model"+"\" size=\"15\"  maxlength=\"20\" value=\""+(model!=null?model:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field\" type=\"hidden\" id='medMainCategoryCode"+Med2004DbCount+"' name=\"medMainCategoryCode\" >";
	newItem +="<input class=\"field\" type=\"text\" id ='mainCategoryCode"+Med2004DbCount+"' style=\"ime-mode: disabled;\" name=\"mainCategoryCode\" size=\"5\" maxlength=\"10\"  onchange=\"getCodeName('medMainCategoryCode"+Med2004DbCount+"','','MEDMCT',this,'mainCategory"+Med2004DbCount+"');\">";
	newItem +="<input class=\"field_RO\" type=\"text\" id='mainCategory"+Med2004DbCount+"' name=\"mainCategory\" size=\"20\" maxlength=\"50\">";
	newItem +="<input class=\"field\" type=\"button\" id='btn_medMainCategoryCode"+Med2004DbCount+"' value=\"...\" title=\"代碼輸入輔助視窗\" name=\"btn_medMainCategoryCode\" onclick=\"popCode('medMainCategoryCode"+Med2004DbCount+"','','MEDMCT','mainCategoryCode"+Med2004DbCount+"','mainCategory"+Med2004DbCount+"');\">";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	//newItem +='<input type=text class=field_Q name=useDate  id=useDate' + Med2004DbCount + ' size=7 value=\'' + (useDate!=null?useDate:'') + '\'><input class="field_Q" type="button" name="btn_useDate' + Med2004DbCount + '" onclick="popCalendar(\'useDate' + Med2004DbCount + '\')" value="..." title="萬年曆輔助視窗">';
	newItem +='<input type=text class=field_Q name=useDate  id=useDate' + Med2004DbCount + ' size=7 value=\'' + (useDate!=null?useDate:'') + '\'><button class="field_Q" type="button" name="btn_useDate' + Med2004DbCount + '" onclick="popCalendar(\'useDate' + Med2004DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='useReason"+Med2004DbCount+"' class=\"field_Q\" type=\"text\" name=\"useReason"+"\" size=\"15\"  maxlength=\"50\" value=\""+(useReason!=null?useReason:'')+"\">&nbsp;";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed2004Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med2004DbfileType"+Med2004DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed2004DbView").append(newItem);
	$("#permit"+Med2004DbCount).val(permit);
	$("#mainCategoryCode"+Med2004DbCount).val(mainCategoryCode);
	$("#mainCategory"+Med2004DbCount).val(mainCategory);
}

//併用藥品資訊
var numberMed2005Db = 1;
function addMed2005Db(id,cName,content,formulation,drgApproach,dose,frequency,sDate,eDate,medCauses)
{
	var Med2005DbCount;
	Med2005DbCount = numberMed2005Db++;		
	var newItem ="<tr id=\"med2005DbfileType"+Med2005DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med2005DbType'>";
	newItem +="<input type='hidden' name='med2005DbTypeId' value=\""+(id!=null?id:'')+"\">";
	newItem +="<input type=text class=field_Q name=cName2  id=cName2" + Med2005DbCount + " size=\"15\" maxlength=\"30\"  value=\"" + (cName!=null?cName:'') + "\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='content"+Med2005DbCount+"' class=\"field_Q\" type=\"text\" name=\"content"+"\" size=\"15\"  maxlength=\"20\"   value=\""+(content!=null?content:'')+"\">&nbsp;";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='formulation"+Med2005DbCount+"' class='field_Q' name='formulation'>"+"<%=View.getOptionCodeKind("DRGFLN","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='drgApproach"+Med2005DbCount+"' class='field_Q' name='drgApproach'>"+"<%=View.getOptionCodeKind("DRG0304","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='dose"+Med2005DbCount+"' class='field_Q' name='dose'>"+"<%=View.getOptionCodeKind("DRG0305","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<select id='frequency"+Med2005DbCount+"' class='field_Q' name='frequency'>"+"<%=View.getOptionCodeKind("DRG0306","").replaceAll("\n","")%>"+"</select>";
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	//newItem +='<input type=text class=field_Q name=sDate  id=sDate' + Med2005DbCount + ' size=7 value=\'' + (sDate!=null?sDate:'') + '\'><input class="field_Q" type="button" name="btn_sDate' + Med2005DbCount + '" onclick="popCalendar(\'sDate' + Med2005DbCount + '\')" value="..." title="萬年曆輔助視窗">';
	newItem +='<input type=text class=field_Q name=sDate  id=sDate' + Med2005DbCount + ' size=7 value=\'' + (sDate!=null?sDate:'') + '\'><button class="field_Q" type="button" name="btn_sDate' + Med2005DbCount + '" onclick="popCalendar(\'sDate' + Med2005DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	//newItem +="~" + '<input type=text class=field_Q name=eDate  id=eDate' + Med2005DbCount + ' size=7 value=\'' + (eDate!=null?eDate:'') + '\'><input class="field_Q" type="button" name="btn_eDate' + Med2005DbCount + '" onclick="popCalendar(\'eDate' + Med2005DbCount + '\')" value="..." title="萬年曆輔助視窗">';
	newItem +="~" + '<input type=text class=field_Q name=eDate  id=eDate' + Med2005DbCount + ' size=7 value=\'' + (eDate!=null?eDate:'') + '\'><button class="field_Q" type="button" name="btn_eDate' + Med2005DbCount + '" onclick="popCalendar(\'eDate' + Med2005DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	newItem +="</td>";

	newItem +="<td class='td_form_add'>";
	newItem +="<input id ='medCauses"+Med2005DbCount+"' class=\"field_Q\" type=\"text\" name=\"medCauses"+"\" size=\"15\"  maxlength=\"50\" value=\""+(medCauses!=null?medCauses:'')+"\">&nbsp;";
	newItem +="</td>";
	
	newItem +="<td class='td_form_add'>";
	newItem +="<input class=\"field_Q\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed2005Db()\" >&nbsp;";
	newItem +="<input class=\"field_Q\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med2005DbfileType"+Med2005DbCount+"')\" >&nbsp;";
	newItem +="</td>";

	newItem +="</tr>";
	

	$("#attMed2005DbView").append(newItem);
	$("#formulation"+Med2005DbCount).val(formulation);
	$("#drgApproach"+Med2005DbCount).val(drgApproach);
	$("#dose"+Med2005DbCount).val(dose);
	$("#frequency"+Med2005DbCount).val(frequency);
}



function rmObj(obj)
{
	$("#"+obj).remove();
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
</script>