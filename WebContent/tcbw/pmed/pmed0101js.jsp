<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">
function changeTab(tabId) 
{
	form1.tabId.value=tabId;
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

//報告繳交清單資訊
var numberMed9002Db = 1;
function addMed9002Db(id,handStatus,reportIssueno,prehanddate,reporttype)
{
	
	var Med9002DbCount;
	Med9002DbCount = numberMed9002Db++;		
	
	var newItem ="<tr id=\"med9002DbfileType"+Med9002DbCount+"\">";

	newItem +="<td class='td_form_add'>";
	newItem +="<input type='hidden' name='med9002DbType'>";
	newItem +="<input type='hidden' name='med9002DbTypeId' value=\""+(id!=null?id:'')+"\">";
	newItem +="<input type='hidden' class=field name='handstatus' value=\""+(handStatus!=null?handStatus:'')+"\">";
	newItem +='<input type=text class=field_RO name=reportIssueno  id=reportIssueno' + Med9002DbCount + ' size=7 value=\'' + (reportIssueno!=null?reportIssueno:'') + '\'>';
	newItem +="</td>";

	if(handStatus == "01")//繳交狀態為待繳交的報告才能修改
	{
		newItem +="<td class='td_form_add'>";
		newItem +='<input type=text class=field name=prehanddate  id=prehanddate' + Med9002DbCount + ' size=7 value=\'' + (prehanddate!=null?prehanddate:'') + '\'><button class="field" type="button" name="btn_prehanddate' + Med9002DbCount + '" onclick="popCalendar(\'prehanddate' + Med9002DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
		newItem +="</td>";

		newItem +="<td class='td_form_add'>";
		newItem +="<select id='reporttype"+Med9002DbCount+"' class='field' name='reporttype'>"+"<option value=''>請選擇</option><option value='01' >定期</option><option value='02' >總結</option>"+"</select>";
		newItem +="</td>";

		newItem +="<td class='td_form_add'>";
		newItem +="<input class=\"field_btnAdd\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed9002Db()\" >&nbsp;";
		newItem +="<input class=\"field_btnRemove\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med9002DbfileType"+Med9002DbCount+"')\" >&nbsp;";
		newItem +="</td>";
	} 
	else if(handStatus == "02" || handStatus == "03" || handStatus == "04")
	{
		newItem +="<td class='td_form_add'>";
		newItem +='<input type=text class=field_RO name=prehanddate  id=prehanddate' + Med9002DbCount + ' size=7 value=\'' + (prehanddate!=null?prehanddate:'') + '\'><button class="field_RO" type="button" name="btn_prehanddate' + Med9002DbCount + '" onclick="popCalendar(\'prehanddate' + Med9002DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
		newItem +="</td>";

		newItem +="<td class='td_form_add'>";
		newItem +="<select id='reporttype"+Med9002DbCount+"' class='field_RO' name='reporttype'>"+"<option value=''>請選擇</option><option value='01' >定期</option><option value='02' >總結</option>"+"</select>";
		newItem +="</td>";

		newItem +="<td class='td_form_add'>";
		newItem +="<input class=\"field_RO\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed9002Db()\" >&nbsp;";
		newItem +="<input class=\"field_RO\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med9002DbfileType"+Med9002DbCount+"')\" >&nbsp;";
		newItem +="</td>";
	}
	else
	{
		newItem +="<td class='td_form_add'>";
		newItem +='<input type=text class=field name=prehanddate  id=prehanddate' + Med9002DbCount + ' size=7 value=\'' + (prehanddate!=null?prehanddate:'') + '\'><button class="field" type="button" name="btn_prehanddate' + Med9002DbCount + '" onclick="popCalendar(\'prehanddate' + Med9002DbCount + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
		newItem +="</td>";

		newItem +="<td class='td_form_add'>";
		newItem +="<select id='reporttype"+Med9002DbCount+"' class='field' name='reporttype'>"+"<option value=''>請選擇</option><option value='01' >定期</option><option value='02' >總結</option>"+"</select>";
		newItem +="</td>";

		newItem +="<td class='td_form_add'>";
		newItem +="<input class=\"field_btnAdd\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addMed9002Db()\" >&nbsp;";
		newItem +="<input class=\"field_btnRemove\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('med9002DbfileType"+Med9002DbCount+"')\" >&nbsp;";
		newItem +="</td>";
	}
	


	$("#attMed9002DbView").append(newItem);
	$("#reporttype"+Med9002DbCount).val(reporttype);

	
}


function rmObj(obj)
{
	$("#"+obj).remove();
}



</script>

