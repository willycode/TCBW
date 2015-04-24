<!--
程式目的：使用者維護作業(外部)
程式代號：conex0202f.jsp
程式日期：102.10.17
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONEX0201" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conex.CONEX0202F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String v = Common.get(request.getParameter("v"));
String p = Common.get(request.getParameter("p"));
if("out".equals(v)) obj.setCreateYN("N");
	
if("".equals(obj.getId()))
{
  obj.setId(Common.get(User.getId()));
}
if("insert".equals(obj.getState()))
{
	obj.doCreate();
}
else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
	if(User.getUserId().equals(obj.getUserId())){
		obj.setReLogin("reLogin");
	}
}
if(!"updateError".equals(obj.getState()) && !"toCreate".equals(obj.getState())){
	obj = (com.kangdainfo.tcbw.view.conex.CONEX0202F)obj.queryOne();
}
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
var popWinName;
var insertDefault = new Array(
		new Array("creator", "<%=User.getUserId()%>"),
		new Array("applyDate", "<%=Datetime.getYYYMMDD()%>")
	);	
function checkField(){
	var alertStr = "";
	alertStr += checkEmpty(form1.userId, "帳號");
	alertStr += checkEmpty(form1.userPwd, "密碼");
	alertStr += checkEmpty(form1.confirmUserPwd, "確認密碼");
	alertStr += checkEmpty(form1.userName, "中文姓名");
	alertStr += checkEmpty(form1.userTel, "連絡電話");
	alertStr += checkEmpty(form1.userTelArea, "電話區碼");
	//alertStr += checkEmpty(form1.userMobile, "行動電話");
	alertStr += checkEmpty(form1.userEmail, "電子信箱");
	alertStr += checkEmpty(form1.userAddr, "地址");
	alertStr += checkEmail(form1.userEmail, "電子信箱");
	alertStr += checkDate(form1.userBirthday, "出生日期");
	alertStr += checkEmpty(form1.userCounty, "縣市別");
	alertStr += checkEmpty(form1.userZipCode, "鄉鎮區別");

	alertStr += checkMustMixAlphaInt(form1.userPwd, "密碼",8);
	
	if(form1.userPwd.value != form1.confirmUserPwd.value){
		alertStr += "登入密碼與確認密碼不符 \n";
	}
	
	if("" != form1.personalId.value){
		if(!checkIdNum(form1.personalId.value)){
			form1.personalId.style.backgroundColor="#FFCEDB";
			alertStr +="身分證字號有誤!\n";			
		}
	}

	if("02"==form1.department.value){
		alertStr += checkEmpty(form1.jobTitle, "職稱");
		alertStr += checkEmpty(form1.userJobName, "服務機構");		
	}
	if("03"==form1.department.value){
		alertStr += checkEmpty(form1.jobTitle, "職稱");
		alertStr += checkEmpty(form1.userJobType, "服務機構類別");
		alertStr += checkEmpty(form1.userJobName, "服務機構");
	}
	if("04"==form1.department.value){
		alertStr += checkEmpty(form1.jobTitle, "職稱");
		alertStr += checkEmpty(form1.userJobName, "服務機構");
	}
	if(alertStr.length!=0){ 
		alert(alertStr); 
		return false; 
	}
	
	beforeSubmit();
	return true;
}

function checkURL(surl){
	form1.state.value="queryAll";	
	form1.action = surl;
	beforeSubmit();
	form1.submit();
}


function init(){	
	setDisplayItem("spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	if(form1.createYN.value=="N")  setDisplayItem("spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	var a = form1.department;
	changeForm(a,"");
	if('02'==<%=User.getCommonDepartment().getDepartmentCode()%>){
		//先全檔再開啟密碼欄位
		$(".field").attr('className','field_RO');
		document.all.item("confirmUserPwd").className='field';
		document.all.item("userPwd").className='field';
	}
	form1.userPwd.value = "<%=obj.getUserPwd()%>";
	form1.confirmUserPwd.value = "<%=obj.getUserPwd()%>";
	form1.personalId.value = "<%=obj.getPersonalId()%>";
	if("<%=obj.getMailMsg()%>" != "" && form1.state.value=="insertSuccess")
		alert('<%=obj.getMailMsg()%>');
	if('<%=User.getRoleId()%>' > 1)
		s4.style.display="block";
	s5.style.display="block";

	if("toCreate" == form1.state.value){
		whatButtonFireEvent("insert");
	}
	//針對匯入資料允許帳號修改
	if('Y'=='<%=p%>'){		
		document.getElementById("userId").style.backgroundColor="#ffff00";
		document.getElementById("userId").className = "field";
		document.getElementById("userId").readOnly = false;
	}
	if("reLogin"=="<%=obj.getReLogin()%>"){
		turnReLogin();
	}
}

function turnReLogin(){
	form1.state.value = "reLogin";
	form1.action = "../../auth.jsp";
	form1.target = "_top";
	form1.submit();
}

function changeForm(a,b){
	if(b=="C"){
		form1.medicineSN.value="";
		form1.userJob.value="";
		form1.userJobName.value="";
		form1.userJobType.value="";
	}
	if(a.value == "01"){		
		s1.style.display="none";
		s2.style.display="none";
		s3.style.display="none";
	}else if(a.value == "02"){		
		s1.style.display="none";
		s2.style.display="block";
		s3.style.display="none";
	}else if(a.value == "03"){		
		s1.style.display="block";
		s2.style.display="block";
		s3.style.display="block";
	}else{		
		s1.style.display="none";
		s2.style.display="block";
		s3.style.display="block";
	}
}


function popUserJobList(){	
	var prop="";
	var windowTop=120;
	var windowLeft=120;
	var q = form1.userJobType.value;
	var v = form1.department.value;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open(getVirtualPath()+"home/popUserJob.jsp?q="+q+"&v="+v,'popWinE',prop);	
}

function setValue(place,zipcode)
{
	var obj1 = zipcode;
	var oldDeptValue = zipcode.value;
	obj1.options.length=0;
	obj1.options.add(new Option("請選擇",""));
	var x = getRemoteData(getVirtualPath() + "/ajax/jsonZipCode.jsp?city="+place.value,"");

	if (x!=null && x.length>0) 
	{
		var json = eval('(' + x + ')'); 
		var i = 0;
		for (i=0; i<json.length; i++) 
		{
			if(json[i].zipcode==null)
				continue;
			var astId =  json[i].zipcode;			
			var oOption = new Option(json[i].zipname,astId);
			obj1.options.add(oOption);
	    	if(astId == oldDeptValue) oOption.selected=true;			
		}
		obj1.disabled = false;
	}
}
//自動顯示郵遞區號
function setValue1(zipname,zipcode)
{

	var obj1 = zipcode;
	var oldDeptValue = zipcode.value;	
	
	var x = getRemoteData(getVirtualPath() + "/ajax/jsonConex0203f.jsp?zipname="+zipname.value,"");
	
	if (x!=null && x.length>0) 
	{
		
		var json = eval('(' + x + ')'); 
		var i = 0;
		for (i=0; i<json.length; i++) 
		{
			if(json[i].zipCode==null)
				continue;
			obj1.value = json[i].zipCode;			
		}
		
	}
}

function chUserId(obj){
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
			}else{				
				showImage("2","Y");
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
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
 <TABLE CELLPADDING="0" CELLSPACING="0" valign="top">
	<tr>
		<%
		if("in".equals(Common.get(User.getInORout()))){
		%>
		<td ID="t2" CLASS="tab_border2"><a href="#" onClick="return checkURL('conex0202f.jsp');">註冊資料查詢</a></td>
		<%
		}
		%>
		<td ID="t1" CLASS="tab_border1">註冊維護資料</td>		
	</tr>
</TABLE>

<!--Query區============================================================-->
<div id="queryContainer" style="width:600px;height:200px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="conex0202q.jsp" />
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form" width="20%"><font color="red">*</font>註冊身份：</td>
		<td nowrap class="td_form_white" colspan="3">
			<select class="field" name="department" type="select" onchange="changeForm(this,'C');">
				<%=View.getTextOption("01;民眾;02;廠商;03;醫事機構;04;衛生單位;", obj.getDepartment(), 0) %>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="20%"><font color="red">*</font>帳號：</td>
		<td nowrap class="td_form_white" width="30%">  
			<input class="field_P" type="text" id="userId" name="userId" size="20" maxlength="20" value="<%=obj.getUserId()%>" onchange="chUserId(this);">			
            <label id="imgType1A" style="align:center;display:none"><img src="../../images/tick.png" width="15" height="15"  style="align:bottom;"></label>
            <label id="imgType1E" style="align:center;display:none"><img src="../../images/error.gif" width="15" height="15"  style="align:bottom;"><font color="red">此帳號已使用</font></label>         
		</td>		
		<td nowrap class="td_form" width="20%"><font color="red">*</font>中文姓名：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="userName" size="20" maxlength="50" value="<%=obj.getUserName()%>">	
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>登入密碼：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="password" autocomplete="off" name="userPwd" size="20" maxlength="50" value="">
	  	</td>
	  	<td nowrap class="td_form"><font color="red">*</font>確認密碼：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="password" autocomplete="off" name="confirmUserPwd" size="20" maxlength="50" value="">
	  	</td>
	</tr>
	<tr>
		<td nowrap class="td_form">身分證號：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="password" autocomplete="off" id="personalId" name="personalId" size="15" maxlength="10" value="" onchange="toUpper();">
		</td>
		<td nowrap class="td_form">性別：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="radio" name="userSex" value="M" <%=obj.getUserSex().equals("M")?"checked":""%>>男
			&nbsp;
			<input class="field" type="radio" name="userSex" value="F" <%=obj.getUserSex().equals("F")?"checked":""%>>女
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">出生日期：</td>
		<td nowrap class="td_form_white">			
			<%=View.getPopCalendar("field", "userBirthday", obj.getUserBirthday()) %>
		</td>
		<td nowrap class="td_form">最高學歷：</td>
		<td nowrap class="td_form_white">	
			<select class="field" name="education" type="select">
				<%=View.getOptionCodeKind("EDUCATION", obj.getEducation()) %>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">職稱：</td>
		<td nowrap class="td_form_white" colspan="3">			
			<select class="field" name="jobTitle" type="select">
				<%=View.getOptionCodeKind("TITLE", obj.getJobTitle()) %>
			</select>
		</td>
	</tr>
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
			<input class="field" type="text" name="userEmail" size="20" maxlength="100" value="<%=obj.getUserEmail()%>" onchange="chkEmail(this);">
			<label id="imgType2A" style="align:center;display:none"><img src="../../images/tick.png" width="15" height="15"  style="align:bottom;"></label>
            <label id="imgType2E1" style="align:center;display:none"><img src="../../images/error.gif" width="15" height="15"  style="align:bottom;"><font color="red">此信箱已使用</font></label>
            <label id="imgType2E2" style="align:center;display:none"><img src="../../images/error.gif" width="15" height="15"  style="align:bottom;"><font color="red">請輸入正確信箱格式</font></label>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>地址：</td>
	  	<td nowrap class="td_form_white" colspan="3">	  		
	  		<select name="userCounty" class="field" onchange="setValue(this,userZipCode);">
			     <%=View.getOptionCodeKind("CTY", obj.getUserCounty())%>
			</select>
			<select name="userZipCode" class="field" onchange="setValue1(this,zipCode);">
				<%=View.getOptionCon1002(obj.getUserZipCode())%>
			</select>
			<input class="field" type="text" name="userAddr" size="50" maxlength="50" value="<%=obj.getUserAddr()%>">
			郵遞區號：<input class="field" type="text" name="zipCode" size="10" maxlength="10" value="<%=obj.getUserZipCode()%>">
	  	</td>
	</tr>
	</table>	
	<div id="s1" style="display:none">
		<table class="table_form" width="100%" height="100%">
			<tr>
				<td nowrap class="td_form" width="20%">服務機構類別：</td>
				<td nowrap class="td_form_white" colspan="3" width="80%">
					<select class="field" name="userJobType" type="select">
						<%=View.getOptionCodeKind("MEDENG", obj.getUserJobType()) %>
					</select>
				</td>
			</tr>
		</table>
	</div>	
    <div id="s2" style="display:none">
		<table class="table_form" width="100%" height="100%">
			<tr>
				<td nowrap class="td_form" width="20%">服務機構：</td>
			  	<td nowrap class="td_form_white" colspan="3" width="80%">			  	
			  		<input class="field" type="hidden" name="userJob" size="10" maxlength="10" value="<%=obj.getUserJob()%>" />
	  	            <input class="field_RO" type="text" name="userJobName" size="30" maxlength="50"  value="<%=obj.getUserJobName()%>" />
			        <input type="button" name="btnQryExpense" onClick="popUserJobList();" value="查詢" width="120px" class="field" >
			  	</td>
			</tr>
		</table>
		<div id="s3" style="display:none">
			<table class="table_form" width="100%" height="100%">
				<tr>
					<td nowrap class="td_form" width="20%">醫事人員證書號碼：</td>
				    <td nowrap class="td_form_white" colspan="3" width="80%">
					<input class="field" type="text" name="medicineSN" size="20" maxlength="20" value="<%=obj.getMedicineSN()%>">
				    </td>
				</tr>
			</table>
		</div> 
	</div>
	<div id="s4" style="display:none">
	<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form" width="20%">停用：</td>
			<td nowrap class="td_form_white" colspan="3" width="80%">
		  		<select class="field" name="isStop">
		    		<%=View.getYNOption(obj.getIsStop())%>
		    	</select>
			</td>
		</tr>
	</table>
	</div>
	<div id="s5" style="display:none">
	<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form" width="20%">申請資訊：</td>
			<td nowrap class="td_form_white"  width="30%">
				[
				<input class="field_RO" type="text" name="creator" size="10" value="<%=obj.getCreator()%>">
		    	/
		    	<input class="field_RO" type="text" name="applyDate" size="7" value="<%=obj.getApplyDate()%>">
		    	] 			
			</td>
		  	<td nowrap class="td_form" width="20%">異動資訊：</td>
		  	<td nowrap class="td_form_white" width="30%"> [
		    	<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">
		    	/
		    	<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">
		    	] 
			</td>
		</tr>
	</table>
	</div>
	</div>
</td></tr>


<!--Toolbar區============================================================-->
<tr><td class="bgToolbar" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="createYN" value="<%=obj.getCreateYN()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="oldUserJob" value="<%=obj.getOldUserJob()%>">
	<input type="hidden" name="reLogin" value="<%=obj.getReLogin()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userIp" value="<%=User.getUserIP()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>

</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  document.all.item("department").disabled=true;
		  break;
	}
}
</script>
</body>
</html>
