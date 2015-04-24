<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%@ include file="head.jsp" %>
<%
String sys_id = "-11"; //+session.getAttribute(WebConstants.SESSION_CURRENT_SYSTEM_ID);
%>
<html>
<head>
<title>Menu</title>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1"/> 
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css" type="text/css" />
<link rel="stylesheet" href="../js/dtree.css" type="text/css" />
<style type="text/css">		
	.treeNode{color:#0000FF;font-size:13;font-family:Arial,新細明體,細明體;}
	.buttonStyle{
		width:160;
		FONT-SIZE: 12px;
	    COLOR: #333333;
		BORDER-TOP-STYLE: groove;
		FONT-FAMILY: "Verdana";
		BORDER-RIGHT-STYLE: groove;
		BORDER-LEFT-STYLE: groove;
		BACKGROUND-COLOR: lightyellow;
		BORDER-BOTTOM-STYLE: groove
		border-top-width: 1px;
		border-right-width: 1px;
		border-bottom-width: 1px;
		border-left-width: 1px
		text-decoration: none;
		border-style: solid;
		border-color: #CCCCCC #666666 #666666 #CCCCCC; 
	}
	
	body {
		font-family: 新細明體, 細明體, Verdana, Geneva, Arial, Helvetica, sans-serif;
		font-size: 12px;
		margin-top: 0px;
		margin-left: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-image:url(../images/bg2_03.gif);
	}	
		
	#msgList div { width: 211px; height: 120px; background-color: #FFFFFF; color: #333; text-align: left; font-size: 10px; }	
</style>
<script type="text/javascript" src="../js/json.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.cycle.js"></script>
<script type="text/javascript" src="../js/dtree.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">
function BroadCastDbMsg() 
{
	
	getDisplayDbMsg();
	
	if (dt.config.objPath==null) 
	  dt.config.objPath = top.fbody.mainhead.document.getElementById('pathname');		
}

function openMsgWindow(id){

	var prop='';
	prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	prop=prop+'width=630,';
	prop=prop+'height=390';
	window.open('popMsg.jsp?q='+id,'msgWin',prop);
}

function writeMsgLog(){	
	if (isObj(document.getElementById('msgIdList'))) {
		var q = document.getElementById('msgIdList').value;
		if (q!=null && q.length>0) {
			getRemoteData('writeMsgLog.jsp',q);
		}
	}
	document.getElementById('msgList').innerHTML = "";
	document.getElementById('msgContainer').style.display='none';	
}

function getDisplayDbMsg() {
	var msgContainer = document.getElementById('msgContainer');
	if (msgContainer.style.display=='none') {	
		var x = getRemoteData('../ajax/jsonMsg.jsp');	
		if (x!=null && x.length!=0) {
			var json = JSON.parse(x);
		    var sb = new StringBuffer();	
		    var ids = new StringBuffer();
		    //var count = json.length;
			for (var i=0; i<json.length; i++) {			
			    sb.append('<div><table width="100%" border="0" cellspacing="0" cellpadding="0" background="../images/msg_03.png">');
			    sb.append('<tr>');
			    sb.append('<td nowrap width="30" align="right" valign="top"><img src="../images/msg_06.png" alt="" width="21" height="15" /></td>');
			    sb.append('<td nowrap><font color="silver">').append(i+1).append(' / ').append(json.length).append('</font><br><a href="#" onClick="openMsgWindow(').append(json[i].obj0).append(');"> ');			    
			    sb.append(json[i].obj2);
			    sb.append('</a></td>');
			    sb.append('</tr>');
			    sb.append('</table>');
			    sb.append("</div>");	
			    ids.append(i==0?'':',').append(json[i].obj0);	
			}	
			if (json.length<2) {
			    sb.append('<div><table width="100%" border="0" cellspacing="0" cellpadding="0" background="../images/msg_03.png">');
			    sb.append('<tr>');
			    sb.append('<td nowrap width="30" align="right" valign="top"><img src="../images/msg_06.png" alt="" width="21" height="15" /></td>');
			    sb.append('<td nowrap align="center"><a href="listMsgUnRead.jsp" target="_blank">Read More..</a>');			    
			    sb.append('</td>');
			    sb.append('</tr>');
			    sb.append('</table>');
			    sb.append("</div>");					
			}
			sb.append('<input type="hidden" id="msgIdList" value="').append(ids.toString()).append('">');
			var y = (document.pageYOffset)?(document.pageYOffset):(document.documentElement)?document.documentElement.scrollTop:document.body.scrollTop; 
			top.fbody.menuleft.doNormal();
			var s6 = document.getElementById('msgList');
			s6.innerHTML = sb.toString();
			window.scrollTo(0,y);
			msgContainer.style.display = '';
			$.fn.cycle.defaults.timeout = 6000;			
			$(s6).cycle({
			    fx:     'scrollUp',	    
			    timeout: 6000,
			    delay:  -2000,
			    pause: 1 			    
			});	
		}			
	
	}

}
function init() 
{
	BroadCastDbMsg();
	window.setInterval("BroadCastDbMsg()", 60000);	
}

function onReload()
{
	location.reload();
}

</script>
</head>

<body onLoad="init();" topmargin="0" leftmargin="0" >
<table width="211" align="center" border="0" cellspacing="0" bgcolor="#FFFFFF" cellpadding="0" id="msgContainer" style="display:none">
  <tr>
    <td nowrap><img id="msgContainerTitle" src="../images/msg_title.png" alt="" width="211" height="47" border="0" usemap="#Map"></td>
  </tr>
  <tr>
    <td nowrap height="120"><div id="msgList"></div></td>
  </tr>
</table>
<table width="50%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td nowrap>
    <img src="../images/bg2_03.gif" height="5" width="204">
    </td>
  </tr>
  <tr>
    <td nowrap>
        <input type="button" value="重新整理" onClick="onReload()"><br>
        <div class="dtree">
        <script type="text/javascript">
		<!--
		var dt = new dTree('dt');
		//,'',top.fbody.mainhead.document.getElementById('pathname')
		dt.config.target = 'mainframe';
		dt.config.folderLinks=false;
		dt.config.useCookies=true;
		dt.config.useIcons=false;
		dt.config.objPath = top.fbody.mainhead.document.getElementById('pathname');		
		<%
		out.write(ServiceGetter.getInstance().getCommonService().getCommonAuthDao().buildAuthorizeMenu("dt","功能選單",sys_id,UserRole));
		%>		
		document.write(dt);		
		//-->
	</script>
    </div>    
    </td>
  </tr>
  <tr>
    <td nowrap>&nbsp;</td>
  </tr>
</table>
<map name="Map">
  <area shape="rect" coords="183,-5,215,34" href="#" onClick="writeMsgLog();" alt="關閉">
</map>
</body>
</html>
