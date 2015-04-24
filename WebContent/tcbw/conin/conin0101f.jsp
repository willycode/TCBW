<!--
程式目的：使用者維護作業
程式代號：conin0101f.jsp
程式日期：102.10.02
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONIN0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conin.CONIN0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
String autoQuery = request.getParameter("autoQuery");
System.out.println(autoQuery + " >>>>>> ");

if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.conin.CONIN0101F)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
	if (!"queryAll".equals(obj.getState()) || "false".equals(obj.getQueryAllFlag())) {
		obj.setQueryAllFlag("true");
	}
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>

<html>
<head>
<%@ include file="../../home/meta.jsp" %>
<script type="text/javascript">
var insertDefault = new Array(
	new Array("roleId", "1"),
	new Array("isMgr", "N"),
	new Array("isStop", "N"),
	//new Array("userPWD","666666"),
	new Array("creator", "<%=User.getUserId()%>"),
	new Array("applyDate", "<%=Datetime.getYYYMMDD()%>")
);

function checkField() {
	var alertStr = "";
	if(form1.state.value=="queryAll"){
		alertStr += checkQuery();
		alertStr += checkDate(form1.q_applyDate, "申請日期");
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.userId, "帳號");		
		alertStr += checkEmpty(form1.userName, "姓名");
		alertStr += checkEmpty(form1.deptCode, "單位");				
		//alertStr += checkEmpty(form1.isMgr, "超級使用者");			
		alertStr += checkEmpty(form1.isStop, "是否停用");	
		alertStr += checkEmail(form1.userEmail, "Email");
		alertStr += checkDate(form1.applyDate, "申請日期");
		alertStr += checkMustMixAlphaInt(form1.userPWD, "密碼",8);
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

var win = null;
function popAuthWin() {
	var alertStr = "";
	if (form1.state.value=="insert" || form1.state.value=="insertError" || form1.state.value=="update" || form1.state.value=="updateError") {
		alert("新增或修改狀態無查詢授權資料，請先點選取消!");
	}else{
		var s = "";
		alertStr += checkEmpty(form1.id, "帳號");
		alertStr += checkEmpty(form1.userId, "帳號");	
		if(alertStr.length!=0){
			alert(alertStr);
			return;
		} else {
			var prop="";
			var windowTop=180; //(document.body.clientHeight-400)/2+180;
			var windowLeft=250; //(document.body.clientWidth-400)/2+250;
			prop=prop+"resizable=yes,width=600,height=420,";
			prop=prop+"top="+windowTop+",";
			prop=prop+"left="+windowLeft+",";
			prop=prop+"scrollbars=yes";
			if(win != null){
				win.close();
			}
			win = window.open("../../sys/ap/sysap003r_user.jsp?userId=" + form1.id.value, "winExp", prop);			
		}
	}	

}

function queryOne(id)
{
	form1.id.value = id;
	form1.state.value = "queryOne";

	var url = '../../ajax/jsonCommonLogDb.jsp';
	
	var q = "&code=CONIN0101F";
        q +="&methodName=open";
        q +="&db=CommonUser";
        q +="&hql=select id,'',userId,userName from CommonUser where id="+form1.id.value;

    var xUserUpdate = getRemoteData(url,q);

	beforeSubmit();
	form1.submit();
}

function init()
{
	<%if(Common.getInt(User.getRoleId())==1 && "".equals(Common.get(User.getSubSystem()))){%>
	setDisplayItem("spanInsert,spanDelete,spanListPrint,spanListHidden","H");
	<%}%>
	form1.userPWD.value = "<%=obj.getUserPWD()%>";
	form1.personalId.value = "<%=obj.getPersonalId()%>";
	if("<%=obj.getMailMsg()%>" != "" && form1.state.value=="insertSuccess")
		alert('<%=obj.getMailMsg()%>');
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

</script>
</head>
<body topmargin="0" onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:300px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable"  border="1">
	<tr>
		<td nowrap class="queryTDLable">使用者帳號：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userId" size="20" maxlength="20" value="<%=obj.getQ_userId()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">姓名：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userName" size="20" maxlength="50" value="<%=obj.getQ_userName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">單位：</td>
		<td nowrap class="queryTDInput">			
			<select class="field_Q" name="q_deptCode" type="select" >
				<%=View.getTextOption("05;TFDA人員;06;藥害救濟基金會;07;評估單位;", obj.getQ_deptCode(), 1) %>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">Email：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userEmail" size="20" maxlength="100" value="<%=obj.getQ_userEmail()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">停用：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" type="select" name="q_isStop">
			<%=View.getYNOption(obj.getQ_isStop())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">申請日期：</td>
		<td nowrap class="queryTDInput">			
			<%=View.getPopCalendar("field_Q", "q_applyDate", obj.getQ_applyDate())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form" width="15%"><font color="red">*</font>帳號：</td>
		<td nowrap class="td_form_white" width="35%">
			<input class="field_P" type="text" name="userId" size="20" maxlength="20" value="<%=obj.getUserId()%>">
		</td>
		<td nowrap class="td_form" width="15%">電話：</td>
		<td nowrap class="td_form_white" width="35%">
			( <input class="field" type="text" name="userTelArea" size="2" maxlength="2" value="<%=obj.getUserTelArea()%>"> )
			- <input class="field" type="text" name="userTel" size="20" maxlength="20" value="<%=obj.getUserTel()%>">
			# <input class="field" type="text" name="userTelExt" size="3" maxlength="3" value="<%=obj.getUserTelExt()%>">
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>姓名：</td>
	  	<td nowrap class="td_form_white">
	    	<input class="field" type="text" name="userName" size="20" maxlength="50" value="<%=obj.getUserName()%>">		
	    </td>
	  	<td nowrap class="td_form">手機：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="text" name="userMobile" size="20" maxlength="20" value="<%=obj.getUserMobile()%>">
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>密碼：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="password" autocomplete="off" name="userPWD" size="20" maxlength="50" value="">
	  	</td>
	  	<td nowrap class="td_form">身分證：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="password" autocomplete="off" name="personalId" size="15" maxlength="10" value="">
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>單位：</td>
	  	<td nowrap class="td_form_white">
	    	<select class="field" name="deptCode" type="select">
				<%=View.getTextOption("05;TFDA人員;06;藥害救濟基金會;07;評估單位;", obj.getDeptCode(), 0) %>
			</select>
		</td>
		<td nowrap class="td_form">傳真：</td>
	  	<td nowrap class="td_form_white">
	  		( <input class="field" type="text" name="userFaxArea" size="2" maxlength="2" value="<%=obj.getUserFaxArea()%>"> )
	  		- <input class="field" type="text" name="userFax" size="20" maxlength="20" value="<%=obj.getUserFax()%>">
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form">地址：</td>
	  	<td nowrap class="td_form_white">
	  	    <select name="userCounty" class="field" onChange="setValue(this,userZipCode);">
			     <%=View.getOptionCodeKind("CTY", obj.getUserCounty())%>
			</select>
			<select name="userZipCode" class="field">
				<%=View.getOptionCon1002(obj.getUserZipCode())%>
			</select>
			<input class="field" type="text" name="userAddr" size="50" maxlength="50" value="<%=obj.getUserAddr()%>">
	  	</td>
	  	<td nowrap class="td_form">Email：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="text" name="userEmail" size="30" maxlength="100" value="<%=obj.getUserEmail()%>">
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form">職稱：</td>
	  	<td nowrap class="td_form_white">
	  		<select class="field" name="jobTitle" type="select">
				<%=View.getOptionCodeKind("TITLE", obj.getJobTitle()) %>
			</select>
	  	</td>
	  	<td nowrap class="td_form"><font color="red">*</font>停用：</td>
	  	<td nowrap class="td_form_white">
	  		<select class="field" name="isStop" type="select">
	    		<%=View.getYNOption(obj.getIsStop())%>
	    	</select>
	    </td>
	</tr>
	<tr>
		<td nowrap class="td_form">申請資訊：</td>
		<td nowrap class="td_form_white">
			[
			<input class="field_RO" type="text" name="creator" size="10" value="<%=obj.getCreator()%>">
	    	/
	    	<input class="field_RO" type="text" name="applyDate" size="7" value="<%=obj.getApplyDate()%>">
	    	] 			
		</td>
	  	<td nowrap class="td_form">異動資訊：</td>
	  	<td nowrap class="td_form_white"> [
	    	<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">
	    	/
	    	<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">
	    	] 
		</td>
	</tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userIP" value="<%=User.getUserIP()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>

<tr><td nowrap>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">帳號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">姓名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">職稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">單位</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">電話</a></th>
	</tr>
	</thead> 
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true,false,false,false,false,false};
	boolean displayArray[] = {false,true,true,true,true,true};
	out.write(View.getQuerylist(primaryArray,displayArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>

