<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ap.SYSAP003F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
String sys_id = "-11"; //session.getAttribute(WebConstants.SESSION_CURRENT_SYSTEM_ID).toString();
if (obj.getState().equals("submit")) obj.updateAuth();
%>

<html>
<head>
<title>權限設定作業</title>
<link rel="stylesheet" href="../../js/default.css" type="text/css" />
<link rel="stylesheet" href="../../js/dtree.css" type="text/css" />
<style>
.bg {
  background-color: #FBFFFD;
  border: 1px solid #EDEDED;
  color: #000000;
  cursor: default;
  left: 0px;
  margin: 1px;
  padding: 2px 6px 0px 6px;
  top: 0px;
  width:175px;
  height:400px;
 }


.root{
  background-color: #FFFFFF;
}

.folder{
  background-color: #FFFFFF;
}

.program{
  background-color: #BBBBFF;
  font-size: 12px;
}

.auth1{
  background-color: #AAFFAA;
  font-size: 12px;

}

.auth2{
  background-color: #FFAAAA;
  font-size: 12px;

}

.showAll{
  background-color: #FFFFF;
  font-size: 12px;
}
</style>
<script language="javascript" src="../../js/dtree.js"></script>
<script language="javascript">


function checkListIsSelect(buttonName){
	var alertStr="";
	if(buttonName=="add" && !d.hasElementChecked()){
		alertStr += "[你必須先選擇左邊功能選單某一節點或多重節點]";
	}
	if(buttonName=="remove" && !s.hasElementChecked){
		alertStr += "[你必須先選擇右邊功能選單某一節點或多重節點]";
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }

	if(buttonName=="add"){
		document.all("optype").value = "add";
	}else{
		document.all("optype").value = "remove";
	}
	form1.state.value = "submit";
	//return false;
}

function checkURL(surl){
	form1.action=surl;
	form1.submit();
}

function init() {
	var msg = "<%=obj.getErrorMsg()%>";
	if (msg!="") alert(msg);
}
</script>
</head>

<body onLoad="init();">
<form id="form1" name="form1" method="post">
<input type="hidden" name="id" value="<%=obj.getId()%>">
<input type="hidden" name="groupName" value="<%=obj.getGroupName()%>">
<input type="hidden" name="groupDesc" value="<%=obj.getGroupDesc()%>">
<input type="hidden" name="q_groupDesc" value="<%=obj.getQ_groupDesc()%>">
<input type="hidden" name="q_groupName" value="<%=obj.getQ_groupName()%>">
<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
<input type="hidden" name="editID" value="<%=User.getUserName()%>">
<input type="hidden" name="state" value="queryOne">
<input type="hidden" name="optype">
<TABLE STYLE="width:100%;" CELLPADDING=0 CELLSPACING=0>
	<tr>
		<td nowrap ID=t1 CLASS="tab_border2" width="100"><a href="#" onClick="return checkURL('sysap002f.jsp');">身分別資料</a></td>
		<td nowrap ID=t2 CLASS="tab_border1" width="100" HEIGHT="25">身分別權限</td>
		<td nowrap>&nbsp;</td>
	</tr>
	<tr>
		<td nowrap class="tab_line2"></td>
		<td nowrap class="tab_line1"></td>
		<td nowrap class="tab_line2"></td>
	</tr>
</TABLE>
<!--end Tabl-->

<table border="1" width="100%"><tr><td nowrap>
    <table class="table_form" width="100%" height="25" border="0">
    <tr>
        <td nowrap class="td_form" width="100">身分別名稱：</td>
        <td nowrap class="td_form_white">
            <%=obj.getGroupName()%>
        </td>
        <td nowrap class="td_form" width="100">身分別描述：</td>
        <td nowrap class="td_form_white">
            <%=obj.getGroupDesc()%>
        </td>
    </tr>
    </table>
</td></tr>
<tr><td nowrap>
<table border="1" width="100%">
	<tr>
	    <td nowrap width="45%" valign="top">
		<div id="d" class="dtree">
			<a href="javascript: d.openAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("open_all"))%></a> | <a href="javascript: d.closeAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("close_all"))%></a><br /><br />

			<script type="text/javascript">
//				<!--
				var d = new dTree('d','../../images/');
				d.config.folderLinks=true;
				d.config.useCookies=false;
				d.config.useIcons = false;
				d.config.objForm =  document.form1;
				d.config.checkboxPrefix = "d";
				d.config.checkParent = true;
				<%=ServiceGetter.getInstance().getCommonService().getCommonAuthDao().buildCheckBoxTree("d", "請選擇要授權的項目", "auth", "d", "d.checkBoxClick", sys_id, "", UserRole, true, false, false)%>		
				
				document.write(d);
				//-->
			</script>

		</div>
		</td>

		<td nowrap align="center" valign="top">
		    <table border="0">
		         <tr>
		        	 <td nowrap align="center" valign="middle">
		        	 	<table width="100" border="0">
		        	 	<tr>
		        	 	  <td nowrap colspan="2" align="center" class="td_lable">權限種類</td>
		        	 	  </tr>
		        	 	<tr>
							<td nowrap><input type="radio" name="authoritytype" value="1" ></td>
							<td nowrap><span class="query"><span class="auth1">查詢權限</span></span></td>
						</tr>
		        	 	<tr>
							<td nowrap><input type="radio" name="authoritytype" value="2" checked></td>
							<td nowrap><span class="auth2">維護權限</span></td>
						</tr>
						</table>
						<br>
						<input class="toolbar_default" type="submit" name="add"    value="加入權限" onClick="return checkListIsSelect(this.name)">
		        	 	<br>
						<br>
						<br>
						<input class="toolbar_default" type="submit" name="remove" value="移除權限" onClick="return checkListIsSelect(this.name)">
		        	 	<br>
						<br>
						<br>
		        	 </td>

		         </tr>
		    </table>
		</td>

		<td nowrap width="45%" valign="top">
		<div id="s" class="dtree">
			<a href="javascript: s.openAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("open_all"))%></a> | <a href="javascript: s.closeAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("close_all"))%></a><br /><br />

			<script type="text/javascript" language="javascript">
				<!--
				var s = new dTree('s','../../images/');
				s.config.folderLinks=true;
				s.config.useCookies=false;
				s.config.useIcons = false;
				s.config.objForm =  document.form1;
				s.config.checkboxPrefix = "s";
				s.config.checkParent = false;
				<%=ServiceGetter.getInstance().getCommonService().getCommonAuthDao().buildCheckBoxTree("s", "已授權項目", "authed", "s", "s.checkBoxClick", sys_id, obj.getId(), UserRole, false, false, false)%>		
				document.write(s);
				//-->
			</script>

		</div>
		&nbsp;
		</td>
	</tr>

</table>
</td></tr></table>
</form>
</body>
</html>
