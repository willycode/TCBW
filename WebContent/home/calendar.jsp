<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>萬年曆</title>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="private"/>
<style>
<!--
.left_day1{ COLOR: red; TEXT-DECORATION: none }
A.left_day1:hover{ COLOR: #99ffff; TEXT-DECORATION: none }
.left_day2{ COLOR: #000000; TEXT-DECORATION: none }
A.left_day2:hover{ COLOR: #99ffff; TEXT-DECORATION: none}
.dark_textcss{ FONT-SIZE: 12px; COLOR: #333333; LINE-HEIGHT: 180%; FONT-FAMILY: "Verdana"; TEXT-DECORATION: none }
.dark_textcss:hover{ FONT-SIZE: 12px; COLOR: red; LINE-HEIGHT: 180%; FONT-FAMILY: "Verdana"; TEXT-DECORATION: none;}
.formcss{ FONT-SIZE: 12px; COLOR: #333333; BORDER-TOP-STYLE: groove; FONT-FAMILY: "Verdana"; BORDER-RIGHT-STYLE: groove; WHITE-SPACE: pre; BORDER-LEFT-STYLE: groove; BORDER-BOTTOM-STYLE: groove;}
.week_bg{ BACKGROUND-COLOR:#95DB02;}
.week_day1{ FONT-SIZE: 12px; LINE-HEIGHT: 180%; FONT-FAMILY: "Verdana"; TEXT-DECORATION: none;}
.week_day2{ color: #006600; FONT-SIZE: 12px; LINE-HEIGHT: 180%; FONT-FAMILY: "Verdana"; TEXT-DECORATION: none;}
-->
</style>
<script language=javascript>
function drawCld(SY,SM) {
	var d,sObj,yDisplay
	var w = firstday(Number(SY) + 1911,SM)
	var m = lastday(Number(SY) + 1911,SM) 

	if(SY>1874 && SY<1909) yDisplay = '光緒' + (((SY-1874)==1)?'元':SY-1874)
	if(SY>1908 && SY<1912) yDisplay = '宣統' + (((SY-1908)==1)?'元':SY-1908)
	if(SY>1911) yDisplay = '民國' + (((SY-1911)==1)?'元':SY-1911)

	for(i=0;i<42;i++) {
		sObj=eval('SD'+ i)
		eval('GD'+ i +'.style.backgroundColor=""') //格子
		sObj.style.cursor = "hand"
		d = i-w+1;
		if(i<w || d>m) {
			sObj.innerHTML = ""
		}else{
			if ((i%7 == 0)||(i%7 == 6)){
				sObj.innerHTML = '<span class=left_day1 onclick="BackValue('+ d +');">' + d + '</span>'
			}else{
				sObj.innerHTML = '<span class=left_day2 onclick="BackValue('+ d +');">' + d + '</span>'
			}
		}
	}
	//讓今日為藍底
	if((parseInt(SYear)-1911==parseInt(SY)) && SMonth==SM){
		eval('GD'+ (SDay+w-1) +'.style.backgroundColor="#00FF00"')
	}
}

function changeCld() {
	var Y = CLD.SY.options[CLD.SY.selectedIndex].text
	var M = CLD.SM.options[CLD.SM.selectedIndex].text
	document.CLD.date_y.value=Y
	document.CLD.date_m.value=M
	drawCld(Y,M)
}

function pushBtm(K) {
	switch (K){
		case 'YU' :
			if(CLD.SY.selectedIndex>0) CLD.SY.selectedIndex--;
			break;
		case 'YD' :			
			if(CLD.SY.selectedIndex>0) CLD.SY.selectedIndex++;
			break;
		case 'MU' :
			if(CLD.SM.selectedIndex>0) {
				CLD.SM.selectedIndex--;
			}else{
				CLD.SM.selectedIndex=11;
				if(CLD.SY.selectedIndex>0) CLD.SY.selectedIndex--;
			}
			break;
		case 'MD' :
			if(CLD.SM.selectedIndex<11) {
				CLD.SM.selectedIndex++;
			}else {
				CLD.SM.selectedIndex=0;
				if(CLD.SY.selectedIndex<49) CLD.SY.selectedIndex++;
			}
			break;
		default :        
			CLD.SY.selectedIndex=SYear-1912;
			CLD.SM.selectedIndex=SMonth-1;
	}
	changeCld();
}

function BackValue(TheDay){
	var strYear=document.CLD.date_y.value;
	var strMonth=document.CLD.date_m.value;
	var strDay=new String(TheDay);	
	if (strYear.length==2){ 
		strYear="0"+strYear; 
	}else if (strYear.length==1){ 
		strYear="00"+strYear; 
	}
	if (strMonth.length<=1){ strMonth="0"+strMonth; }
	if (strDay.length<=1){ strDay="0"+strDay; }
	var strDate=strYear+strMonth+strDay;
	window.returnValue = strDate;
	window.close();
}

var time   = new Date()
var SYear  = time.getYear()
var SMonth = time.getMonth() + 1
var SDay   = time.getDate()

function initial() {
	if(navigator.appName == "Netscape" || parseInt(navigator.appVersion) < 4){
		alert("很抱歉!!\n你的流覽器無法執行此程式。\n此程式需在 IE5 以後的版本才能執行!!")
	}
	document.CLD.date_y.value=parseInt(SYear)-1911
	document.CLD.date_m.value=SMonth	
	pushBtm('')
}
</script>
<SCRIPT Language="VBScript">
<!--
function firstday(y,m)
	firstday=weekday(dateserial(y,m,1))-1
end function

function lastday(y,m)
	lastday=day(dateserial(y,m+1,0))
end function
'-->
</SCRIPT>
</head>
<body onload="initial()" bgcolor="lightyellow" >
<form name="CLD" >
<input type=hidden name="date_y" value="年">
<input type=hidden name="date_m" value="月">
<input type=hidden name=CY>
	<table width="150" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr> 
			<td nowrap height="10"></td>
		</tr>

		<tr> 
			<td nowrap height="2"></td>
		</tr>
		<tr> 
			<td nowrap height="30"> 
				<table border="0" cellspacing="0" cellpadding="0" width="150">
					<tr> 
						<td nowrap class="dark_textcss">民國</td>
						<td nowrap> 
							<select onChange="changeCld()" class="formcss" name="SY">
								<SCRIPT language="JavaScript">
								for(i=1912;i<2050;i++) document.write('<option value='+i+'>'+(i-1911))
								</SCRIPT>
							</select>
						</td>
						<td nowrap class="dark_textcss">年</td>
						<td nowrap> 
							<select onChange="changeCld()" class="formcss" name="SM">
								<SCRIPT language="JavaScript">
								for(i=1;i<13;i++) document.write('<option>'+i)
								</SCRIPT>
							</select>
						</td>
						<td nowrap class="dark_textcss">月</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr> 
			<td nowrap> 
				<table width="150" border="0" cellspacing="2" cellpadding="0" align="center">
					<tr class="week_bg" align="center"> 
						<td nowrap class="week_day2">日</td>
						<td nowrap class="week_day1">一</td>
						<td nowrap class="week_day1">二</td>
						<td nowrap class="week_day1">三</td>
						<td nowrap class="week_day1">四</td>
						<td nowrap class="week_day1">五</td>
						<td nowrap class="week_day2">六</td>
					</tr>
					<SCRIPT language="JavaScript">
						for(i=0;i<6;i++) {
							document.write('<tr align=center>')
							for(j=0;j<7;j++) {
								document.write('<td nowrap id="GD' + (i*7+j) +'"><font id="SD' + (i*7+j) +'"  size=2 face="新細明體"')
								document.write('> </font></td>')
							}
							document.write('</tr>')
						}
					</SCRIPT>
				</table>
			</td>
		</tr>
		<tr> 
			<td nowrap height="2"></td>
		</tr>
		<tr> 
			<td nowrap height="15"> 
				<div align="center">
					<a href="#" onClick="pushBtm('YU')"><img src="../images/year_back.gif" width="21" height="13" vspace="1" hspace="1" border="0"></a> 
					<a href="#" onClick="pushBtm('YD')"><img src="../images/year_next.gif" width="21" height="13" vspace="1" hspace="1" border="0"></a> 
					<a href="#" onClick="pushBtm('MU')"><img src="../images/month_back.gif" width="20" height="13" vspace="1" hspace="1" border="0"></a> 
					<a href="#" onClick="pushBtm('MD')"><img src="../images/month_next.gif" width="21" height="13" vspace="1" hspace="1" border="0"></a> 
					<a href="#" onClick="pushBtm('')"><img src="../images/today.gif" width="36" height="13" vspace="1" hspace="1" border="0"></a>
				</div>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
