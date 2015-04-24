<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.kangdainfo.common.util.*" %>
<%
String sY = Common.checkGet(request.getParameter("sY"));
String sM = Common.checkGet(request.getParameter("sM"));
String jsFunction = Common.get(request.getParameter("js"));

String strJavaScript = "", initJS = "";
if (!"".equals(Common.get(jsFunction))) strJavaScript += "opener." + jsFunction + ";\n";
String dateField = Common.checkGet(request.getParameter("dateField"));

if (dateField==null || Validate.checkSpecialChar(dateField)) {	
	out.write("欄位變數不符規定！");
	return;
}
String yyyMMdd = Datetime.getYYYMMDD();
String yyy = yyyMMdd.substring(0,3);
String MM = yyyMMdd.substring(3,5);
String dd = yyyMMdd.substring(5);

if (sY!=null && Validate.checkInt(sY) && sY.length()<=3 && sM!=null && Validate.checkInt(sM) && sM.length()<=2) {
	initJS = "drawCld('" + sY + "','" + sM + "');";
}

%>
<html>
<head>
<title>線上月曆</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.bar{ background-color: #FFFFFF; }
.week_bg { 
	background-color: #95DB02; 
	background-color: #f5fffa;
	text-align:center;
	font-size:9pt;
	color:#000000
} 
.week_day { 
	background-color: #ffffff; 	
	align:left;
	font-size:9pt;
	font-family:Arial;
	font-weight:normal;
}
.today { 
	background-color: #FF8040; 
	align:left;
	font-size:9pt;
	font-family:Arial;
	font-weight:normal;
	color:#ffffff
}

.toolbar_default {
	color: #000000;
   	background-color: #f5fffa;
    border: 1px solid #000000;
   	padding: 3px 0px 0px 0px;    
    cursor: hand;
}

-->
</style>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">
var some = 0;
//if (isIE()==false) some=1900;

var init = false;

function montharr(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11)
{
	this[0] = m0;
	this[1] = m1;
	this[2] = m2;
	this[3] = m3;
	this[4] = m4;
	this[5] = m5;
	this[6] = m6;
	this[7] = m7;
	this[8] = m8;
	this[9] = m9;
	this[10] = m10;
	this[11] = m11;
}

function dateSelected(strDate) {
	var arrayOfStrings = strDate.split("/");
	var d = opener.document.all.item("<%=dateField%>");
	if (isObj(opener.document.all.item("<%=dateField%>"))) {
		opener.document.all.item("<%=dateField%>").value = getToday(new Date(arrayOfStrings[0], arrayOfStrings[1]-1, arrayOfStrings[2]));		
		<%=strJavaScript%>				
		window.close();
	}
}

function clsMonth(s) {
	var sYear = getNumeric(document.all.sYear.options[document.all.sYear.selectedIndex].value);
	var sMonth = getNumeric(document.all.sMonth.options[document.all.sMonth.selectedIndex].value);	
	if (s=='Pre') {
		if (sMonth==0) {		
			document.all.item("sYear").value = sYear - 1;
			document.all.item("sMonth").value = 11;		
		} else {
			document.all.item("sMonth").value = sMonth - 1;
		}	
	} else {
		if(sMonth==11) {
			document.all.item("sYear").value = sYear + 1;
			document.all.item("sMonth").value = 0;				
		}else{
			document.all.item("sMonth").value = sMonth + 1;
		}	
	}
	calendar('x','x');	
}

function clsYear(s) {
	var sYear = getNumeric(document.all.sYear.options[document.all.sYear.selectedIndex].value);
	if (s=='Pre') {
		document.all.item("sYear").value = sYear - 1;
	} else {
		document.all.item("sYear").value = sYear + 1;
	}
	calendar('x','x');	
}

function calendar(sY,sM)
{
	//var monthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";
	var tDate = new Date(<%=yyy%>+1911, <%=Common.getInt(MM)-1%>, <%=dd%>);
	var today = new Date(<%=yyy%>+1911, <%=Common.getInt(MM)-1%>, <%=dd%>);
	if (sY!=null && sM!=null)
	today = new Date(document.all.sYear.options[document.all.sYear.selectedIndex].value, document.all.sMonth.options[document.all.sMonth.selectedIndex].value, 1);	
	
	var thisDay;
	var strBody = "";
	var monthDays = new montharr(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	
	year = today.getFullYear();
	thisDay = tDate.getDate();
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) monthDays[1] = 29;
		nDays = monthDays[today.getMonth()];
		firstDay = today;
		firstDay.setDate(1); // works fine for most systems
		testMe = firstDay.getDate();
		if (testMe == 2) firstDay.setDate(0);
		startDay = firstDay.getDay();
	
		//var dayNames = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
		var monthNames = new Array("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");
		var now = new Date();
		
		if (init==false) {
			//lightyellow
			//0080FF
			strBody = '<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="2" align="CENTER" bgcolor="lightyellow"><TR><td nowrap valign="top">';
			strBody += '<table width="100%"><tr>';
			strBody += '<td nowrap><input type="button" class="toolbar_default" name="btnClose" value="關閉視窗" onClick=javascript:window.close()></td>';
			strBody += '<td nowrap align=center>';
			//strBody += '<a href="#" onClick="clsYear(\'Pre\');"><FONT style=font-size:12pt;Color:#ffffff>&lt;&lt;</font></a>';			
			strBody += '<a href="#" onClick="clsYear(\'Pre\');"><img src="../images/prev.gif" border="0"></a>';
			strBody += '<select name="sYear" onChange="calendar(document.all.sYear.options[document.all.sYear.selectedIndex].value, document.all.sMonth.options[document.all.sMonth.selectedIndex].value);">';
			for (var i=1912; i<2100; i++) {
				if (i==(today.getFullYear()+some)) {
					strBody += '<option value="' + i + '" selected>' + (i-1911) + '</option>'
				} else {
					strBody += '<option value="' + i + '">' + (i-1911) + '</option>'			
				}
			}
			strBody += '</select>';
			strBody += '<a href="#" onClick="clsYear(\'Next\');"><img src="../images/next.gif" border="0"></a>';						
			strBody += '</td>';
						
			strBody += '<td nowrap align=right>';			
			strBody += '<a href="#" onClick="clsMonth(\'Pre\');"><img src="../images/prev.gif" border="0"></a>';
			strBody += '<select name="sMonth" onChange="calendar(document.all.sYear.options[document.all.sYear.selectedIndex].value, document.all.sMonth.options[document.all.sMonth.selectedIndex].value);">';
			for (var i=0; i<monthNames.length; i++) {
				if ((i+1)==(today.getMonth()+1)) {
					strBody += '<option value="' + i + '" selected>' + monthNames[i] + '</option>'
				} else {
					strBody += '<option value="' + i + '">' + monthNames[i] + '</option>'
				}
			}
			strBody += '</select>';
			strBody += '<a href="#" onClick="clsMonth(\'Next\');"><img src="../images/next.gif" border="0"></a>';			
			
			strBody += '</td></tr></table>';
			strBody += '<div id="calID">';
		}		
		strBody += '<table width="100%" border="0" cellspacing="1" cellpadding="2" bgcolor="#CCCCCC"><tr>';
		strBody += '<td nowrap class="week_bg">日</td>';
		strBody += '<td nowrap class="week_bg">一</td>';
		strBody += '<td nowrap class="week_bg">二</td>';
		strBody += '<td nowrap class="week_bg">三</td>';
		strBody += '<td nowrap class="week_bg">四</td>';
		strBody += '<td nowrap class="week_bg">五</td>';
		strBody += '<td nowrap class="week_bg">六</td>';
		strBody += '</TR><TR>';
		column = 0;
		for (i=0; i<startDay; i++)
		{
			strBody += '\n<td nowrap><FONT style=font-size:10pt> </FONT></TD>';
			column++;
		}
		for (i=1; i<=nDays; i++)
		{
			if (tDate.getFullYear()+'/'+tDate.getMonth() + '/' + i == today.getFullYear() + '/' + today.getMonth() + '/' + thisDay) {
				//strBody += '</TD><td nowrap align="left" bgcolor="#FF8040"><FONT style=font-size:9pt;font-family:Arial;font-weight:normal;Color:#ffffff><B>'
				strBody += '</TD><td nowrap class="today">';
			}else{
				strBody += '</TD><td nowrap class="week_day">';
			}
			strBody += '<input type=checkbox value="' + (today.getFullYear()+some) + '/' + (today.getMonth()+1) + '/' + i + '" onClick="dateSelected(this.value)">' + i;
			if (i == thisDay) strBody += '</TD>'
			column++;
			if (column == 7)
			{
			strBody += '<TR>'; 
			column = 0;
			}
		}
		strBody += '</TABLE>';		
		
		if (isObj(document.all['calID'])) {
			var obj = document.all['calID'];
			var IH = obj.innerHTML;
			obj.innerHTML = strBody
		} else {
			strBody += '</div></td><tr></table>';
			document.writeln(strBody);		
			init = true;
		}
}
</script>
</head>
<body onload="<%=initJS%>">
<script type="text/javascript">
calendar();
</script>
</body>
</html>
