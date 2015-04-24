<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="com.kangdainfo.common.util.*" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conex.CONEX0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
	String r = "";
	String rand = (String)session.getAttribute("rand");
	String input = request.getParameter("rand");
 

	if("doCreate".equals(obj.getState()))
	{
		try
		{
			if(rand.equals(input))
			{
				r = obj.doSaveCreate();			
			}
			else
			{
				r = "圖形驗證碼錯誤!";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			r = "發生未預期錯誤，請重新操作 !";
		}
	}
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="js/default.css" type="text/css"/>
<script type="text/javascript" src="js/json.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/tablesoft.js"></script>
<script type="text/javascript">
var popWinName;

function checkField()
{
	var alertStr = "";
	alertStr += checkEmpty(form1.commonDepartment, "註冊身份");
	alertStr += checkEmpty(form1.userId, "帳號");
	alertStr += checkEmpty(form1.userName, "中文姓名");
	alertStr += checkEmpty(form1.userPwd, "登入密碼");
	alertStr += checkEmpty(form1.confirmUserPwd, "確認密碼");
	alertStr += checkEmpty(form1.userTelArea, "電話區碼");
	alertStr += checkEmpty(form1.userTel, "連絡電話");
	//alertStr += checkEmpty(form1.userMobile, "行動電話");
	alertStr += checkEmpty(form1.userEmail, "電子信箱");
	alertStr += checkEmail(form1.userEmail, "電子信箱");
	alertStr += checkEmpty(form1.userCounty, "縣市別");
	alertStr += checkEmpty(form1.userZipCode, "鄉鎮區別");
	alertStr += checkEmpty(form1.userAddr, "地址");
	alertStr += checkDate(form1.userBirthday, "出生日期");
	
	alertStr += checkMustMixAlphaInt(form1.userPwd, "密碼",8);
	
	if("" != form1.personalId.value){
		if(!checkIdNum(form1.personalId)){
			form1.personalId.style.backgroundColor="#FFCEDB";
			alertStr += "身分證字號有誤!\n";			
		}
	}

	if("03"==form1.commonDepartment.value)
	{
		alertStr += checkEmpty(form1.jobTitle, "職稱");
		alertStr += checkEmpty(form1.userJobType, "服務機構類別");
		alertStr += checkEmpty(form1.userJobName, "服務機構");		
	}
	if("04"==form1.commonDepartment.value)
	{
		alertStr += checkEmpty(form1.jobTitle, "職稱");
		alertStr += checkEmpty(form1.userJobName, "服務機構");
		//alertStr += checkEmpty(form1.medicineSN, "醫事人員證書號碼");
	}
	if(alertStr.length > 0)
	{
		alert(alertStr);
		return false;
	}
	
	if(form1.userPwd.value != form1.confirmUserPwd.value)
	{
		alertStr += "登入密碼與確認密碼不符 \n";
	}

	if(alertStr.length > 0)
	{
		alert(alertStr);
		return false;
	}
	beforeSubmit();
	form1.state.value = "doCreate";
	return true;
}

function doBack(){
	form1.action = "index.jsp";
	form1.submit();
}


function doClear(){
	$("#formContainer table tr[id]").css("display", "none");
	var jt1 = document.all.item("userJobType");
	jt1.options.length = 0;
	showImage("c","");	
}

function checkUser(obj){
	if(obj.value != ""){		
		if(checkIdNum(obj.value)){
			obj.style.backgroundColor="#FFCEDB";
			alert("基於資訊安全考量，帳號不可使用身分證字號\n請進行更換登入帳號， 謝謝!");
		}else{
			$.ajax({
				url: "noFilterAjax/jsonU.jsp",
				type: "POST",
				dataType: "json",
				data: "u=" + obj.value,
				success: function(Jdata) {
					if(Jdata.obj0 == "Y"){
						obj.style.backgroundColor="#FFCEDB";
						showImage("1","N");
					}else{
						obj.style.backgroundColor="";
						showImage("1","Y");
					}
				},
				error: function(){
					
				}
			});	
		}
	}
}

$(function(){
	
	$("#formContainer table tr:first select").bind("change", function(){
		form1.userJob.value = "";
		form1.userJobName.value = "";
		form1.medicineSN.value = "";

		var jt1 = document.all.item("userJobType");
		jt1.options.length = 0;
		
		if($(this).val() == "01"){
			$("#formContainer table tr[id]").css("display", "none");			
		}else if($(this).val() == "03"){
			$("#formContainer table tr[id]:not([id=1])").css("display", "");
			$("#formContainer table tr[id=1]").css("display", "none");			
	
			// 服務機構類別 
			$.ajax({
				url: "noFilterAjax/jsonMedeng.jsp",
				type: "POST",
				dataType: "json",
				success: function(Jdata) {
				    var jt = document.all.item("userJobType");
					jt.options.length = 0;
					
					var jtOption = document.createElement("Option");
					jtOption.innerText = "請選擇";
					jtOption.value = "";
					
					jt.appendChild(jtOption);
					
					for(var i=0; i<Jdata.length; i++){
						var obj = Jdata[i];
						var jtDtlOption = document.createElement("Option");
						jtDtlOption.innerText = obj.name;
						jtDtlOption.value = obj.id;
						jt.appendChild(jtDtlOption); 
					}
				},
				error: function(){
					alert("無法取得服務機構類別資料 !");
				}
			});			

		}else{
			$("#formContainer table tr[id]:not([id=2])").css("display", "");
			$("#formContainer table tr[id=2]").css("display", "none");
		}
	});
});

function init(){
	 document.all.item("userJobName").disabled=true;
	
	$("#formContainer table tr[id]").each(function(index){
		$(this).css("display", "none");
	});
	
	if("<%=r%>" != "")
	{

		alert("<%=r%>");
		if("<%=r%>" != "圖形驗證碼錯誤!")
		{
			form1.action="index.jsp";
			form1.submit();
		}
		else
		{
			return false;
		}
		
		
	}
}

function setValue(place,zipcode)
{
	var obj1 = zipcode;
	var oldDeptValue = zipcode.value;
	obj1.options.length=0;
	obj1.options.add(new Option("請選擇",""));	
	
	$.ajax({
		url: 'noFilterAjax/jsonZipCode.jsp',
		type: 'POST',
		data: 'city='+place.value,
		dataType: 'json',
		success: function(json){
			for(var i=0; i<json.length; i++){
				if(json[i].zipcode==null)
					continue;
				var astId =  json[i].zipcode;			
				var oOption = new Option(json[i].zipname,astId);
				obj1.options.add(oOption);
		    	if(astId == oldDeptValue) oOption.selected=true;
				
			}
			obj1.disabled = false;
		},
		error: function(){
		}
	});
}



function popUserJobList(){	
	var prop="";
	var windowTop=120;
	var windowLeft=120;
	var q = form1.userJobType.value;
	var v = form1.commonDepartment.value;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("popUserJob.jsp?q="+q+"&v="+v,'popWinE',prop);	
}

function popCalendar1(dateField){
	var prop="";
	var windowTop=(document.body.clientHeight-400)/2+180;
	var windowLeft=(document.body.clientWidth-400)/2+250;
	prop=prop+"width=280px,height=220,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=no";	
	closeReturnWindow();
	returnWindow=window.open("calendar_v2.jsp?dateField=" + dateField ,'popCalendar',prop);
}

function chkEmail(obj){
	if(obj.value != "" && obj.value != null){
		if(checkEmail(obj,"") != ""){
			showImage("2","N2");		
		}else{
			//新增時檢核唯一性
			if(form1.state.value=="init"){
				$.ajax({
					url: getVirtualPath() +"noFilterAjax/jsonU.jsp",
					type: "POST",
					dataType: "json",
					data: "v=" + obj.value,
					success: function(Jdata) {
						if(Jdata.obj0 == "Y"){
							showImage("2","N1");
						}else{
							obj.style.backgroundColor="";
							showImage("2","Y");
						}
					},
	                error: function(){                	
					}
				});
			}
		}
	}	
}

function showImage(form,type){
	//帳號
	if(form=="1"){
		if(type=="Y"){
			document.getElementById("imgType1A").style.display="";
			document.getElementById("imgType1E").style.display="none";
		}else if(type=="N"){
			document.getElementById("imgType1A").style.display="none";
			document.getElementById("imgType1E").style.display="";
		}
	}
	//信箱
	else if(form=="2"){
		if(type=="Y"){
			document.getElementById("imgType2A").style.display="";
			document.getElementById("imgType2E1").style.display="none";
			document.getElementById("imgType2E2").style.display="none";
		}else if(type=="N1"){
			document.getElementById("imgType2A").style.display="none";
			document.getElementById("imgType2E1").style.display="";
			document.getElementById("imgType2E2").style.display="none";
		}else if(type=="N2"){
			document.getElementById("imgType2A").style.display="none";
			document.getElementById("imgType2E1").style.display="none";
			document.getElementById("imgType2E2").style.display="";
		}
	}
	//clear
    else if(form=="c"){
    	document.getElementById("imgType1A").style.display="none";
		document.getElementById("imgType1E").style.display="none";
		document.getElementById("imgType2A").style.display="none";
		document.getElementById("imgType2E1").style.display="none";
		document.getElementById("imgType2E2").style.display="none";
    }
}
</script>
</head>
<body topmargin="0" onLoad="init();">
<form id="form1" name="form1" method="post" autocomplete="off" onSubmit="return checkField();">
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form" width="20%"><font color="red">*</font>註冊身份：</td>
		<td nowrap class="td_form_white" colspan="3" width="80%">
			<select class="field" name="commonDepartment" type="select">
				<%=View.getTextOption("01;民眾;03;醫事機構;04;衛生單位;", "", 0) %>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="20%"><font color="red">*</font>帳號：</td>
		<td nowrap class="td_form_white" width="30%">
			<input class="field" type="text" name="userId" size="20" maxlength="20" onChange="checkUser(this);" value="<%=obj.getUserId()%>">		
			<label id="imgType1A" style="align:center;display:none"><img src="images/tick.png" width="15" height="15"  style="align:bottom;"></label>
            <label id="imgType1E" style="align:center;display:none"><img src="images/error.gif" width="15" height="15"  style="align:bottom;"><font color="red">此帳號已使用</font></label>
		</td>
		<td nowrap class="td_form" width="15%"><font color="red">*</font>中文姓名：</td>
		<td nowrap class="td_form_white" width="35%">
			<input class="field" type="text" name="userName" size="20" maxlength="50" value=<%=obj.getUserName()%>>	
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>登入密碼：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="password" autocomplete="off" name="userPwd" size="20" maxlength="50">
	  	</td>
	  	<td nowrap class="td_form"><font color="red">*</font>確認密碼：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="password" autocomplete="off" name="confirmUserPwd" size="20" maxlength="50">
	  	</td>
	</tr>
	<tr>
		<td nowrap class="td_form">身分證號：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="password" autocomplete="off" name="personalId" size="15" maxlength="10" value="<%=obj.getPersonalId()%>" onchange="toUpper();">
		</td>
		<td nowrap class="td_form">性別：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="radio" name="sex" value="M" <%if("M".equals(obj.getUserSex())) out.write("checked");%>>男
			&nbsp;
			<input class="field" type="radio" name="sex" value="F" <%if("F".equals(obj.getUserSex())) out.write("checked");%>>女
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">出生日期：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="userBirthday"  size="7" value="<%=obj.getUserBirthday()%>">
			<input class="field" type="button"  onclick="popCalendar1('userBirthday');" value="..."  title="萬年曆輔助視窗\">
			(民國XXX年XX月XX日)	
		</td>
		<td nowrap class="td_form">最高學歷：</td>
		<td nowrap class="td_form_white">	
			<select class="field" name="education" type="select">
				<%=View.getOptionCodeKind("EDUCATION", "") %>
			</select>
		</td>
	</tr>
	
	<span id="spanDoUpload">
            <input class="toolbar_default" type="button" followPK="false" name="doUpload" value="附件上傳" onClick="upload('medin0703')"> 
    </span>
	
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>連絡電話：</td>
		<td nowrap class="td_form_white">
		    ( <input class="field" type="text" name="userTelArea" size="2" maxlength="2" value="<%=obj.getUserTelArea()%>"> )
			- <input class="field" type="text" name="userTel" size="20" maxlength="20" value="<%=obj.getUserTel()%>">
			# <input class="field" type="text" name="userTelExt" size="3" maxlength="3" value="<%=obj.getUserTelExt()%>">
		</td>
		<td nowrap class="td_form">行動電話：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="text" name="userMobile" size="20" maxlength="20" value="<%=obj.getUserMobile()%>">
	  	</td>
	</tr>
	<tr>
		<td nowrap class="td_form">傳真：</td>
	  	<td nowrap class="td_form_white">
	  	    ( <input class="field" type="text" name="userFaxArea" size="2" maxlength="2" value="<%=obj.getUserFaxArea()%>"> )
	  		- <input class="field" type="text" name="userFax" size="20" maxlength="20" value="<%=obj.getUserFax()%>">
	  	</td>
	  	<td nowrap class="td_form"><font color="red">*</font>電子信箱：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="userEmail" size="30" maxlength="100" value="<%=obj.getUserEmail()%>" onchange="chkEmail(this);">
			<label id="imgType2A" style="align:center;display:none"><img src="images/tick.png" width="15" height="15"  style="align:bottom;"></label>
            <label id="imgType2E1" style="align:center;display:none"><img src="images/error.gif" width="15" height="15"  style="align:bottom;"><font color="red">此信箱已使用</font></label>
            <label id="imgType2E2" style="align:center;display:none"><img src="images/error.gif" width="15" height="15"  style="align:bottom;"><font color="red">請輸入正確信箱格式</font></label>	
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>職稱：</td>
	  	<td nowrap class="td_form_white" colspan="3">
	  		<select class="field" name="jobTitle" type="select">
	  		    <%=View.getOptionCodeKind("TITLE", "")%>
	  		</select>
        </td>
    </tr>
    <tr id ="3">
    	<td nowrap class="td_form"><font color="red">*</font>服務機構：</td>
	  	<td nowrap class="td_form_white" colspan="3">	  	    
	  	    <input class="field" type="hidden" name="userJob" size="10" maxlength="10" value="<%=obj.getUserJob()%>" />
	  	    <input class="field_RO" type="text" name="userJobName" size="30" maxlength="50" value="" />
			<input type="button" name="btnQryExpense" onClick="popUserJobList();" value="查詢" width="120px" class="field" >
	  	</td>
    </tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>地址：</td>
	  	<td nowrap class="td_form_white" colspan="3">	  		
	  		<select name="userCounty" class="field" onChange="setValue(this,userZipCode);">
			     <%=View.getOptionCodeKind("CTY",obj.getUserCounty())%>
			</select>
			<select name="userZipCode" class="field_Q">
				<%=View.getOptionCon1002(null)%>
			</select>
			<input class="field" type="text" name="userAddr" size="50" maxlength="50" value="<%=obj.getUserAddr()%>">
	  	</td>
	</tr>
	<tr id="2">
		<td nowrap class="td_form">服務機構類別：</td>
		<td nowrap class="td_form_white" colspan="3">
			<select class="field" name="userJobType" type="select"></select>
		</td>
	</tr>
	<tr id="3">
		<td nowrap class="td_form">醫事人員證書號碼：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="medicineSN" size="20" maxlength="20" value="<%=obj.getMedicineSN()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">認證碼：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input type="text" name="rand" maxlength="4" size="4">
			<img border="0" src="image.jsp" height="100%">
		</td>
	</tr>
	
	
	
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="URL" value="<%=request.getRequestURL()%>">
	<span id="spanDoSend">
		<input class="toolbar_default" type="submit" followPK="false" name="send" value="確　定">&nbsp;
	</span>	
	<span id="spanDoClear">
		<input class="toolbar_default" type="reset" followPK="false" name="clear" value="清　除" onClick="doClear();">&nbsp;
	</span>
	<span id="spanDoBack">
		<input class="toolbar_default" type="button" followPK="false" name="back" value="返回登入頁面" onClick="doBack();">&nbsp;
	</span>
</td></tr>

</table>
</form>
</body>
</html>
