<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<%
String css = ""; //" style=\"background:blue;color:white;\"";
String css1 = "";
int sid = -11;
if (User!=null && session.getAttribute("isLog")==null) {
	session.setAttribute("isLog","Y");
	ServiceGetter.getInstance().getCommonService().saveCommonVisitLog(User, request.getRemoteAddr(), "1", "Y", null);
}
com.kangdainfo.common.model.bo.SubSystem subsys = ServiceGetter.getInstance().getCommonService().getSubSystemById(sid);
%>
<html>
<head>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css" type="text/css">
<style>
body {
	margin-top: 0px;
	margin-left: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	color: #FFFFFF;
}
	
div.titleBar {
	font-family: "新細明體"; 
	font-size: 12px;
	background-color: #EDEDED;
	/**
	border-top:1px solid #000000;
	border-bottom:1px solid #000000;
	**/
	padding: 4px 8px 2px 8px;
	text-align: left;
	background-image:url(../images/bg_funbar.gif)
}

a.titleBarButton {
	font-family: "新細明體";
	font-size: 12px;
	background-color: transparent;
	color: #FFFFFF;
	padding: 2px 6px 0px 6px;
	text-decoration: none;
}

a.titleBarButton:hover {
	font-family: transparent; 
	font-size: transparent;
	background-color: transparent;
	color: #000099;
	padding: 2px 6px 0px 6px;
	text-decoration: none;
}

a.titleBarButton:active {
	font-family: transparent; 
	font-size: transparent;
	background-color: transparent;
	color: #FFFFFF;
	padding: 2px 6px 0px 6px;
	text-decoration: none;
}

.titleBarActiveButton {
	font-family: transparent; 
	font-size: transparent;
	background-color: transparent;
	border: 1px outset #EDEDED;
	color: #000000;
	padding: 2px 6px 0px 6px;
	text-decoration: none;
}

div#topbar{
	position:absolute;
	border: 1px solid black;
	padding: 2px;
	background-color: lightyellow;
	width: 550px;
	height: 70px;
	visibility: hidden;
	z-index: 100;
	overflow:auto;
}
#Layer1 {
	position:absolute;
	width:71px;
	height:34px;
	z-index:101;
	left: 846px;
	top: 13px;
}
</style>
<script type="text/javascript" src="../js/popup.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.cycle.js"></script>
<script type="text/javascript" src="../js/json.js"></script>
<script type="text/javascript">
var boxMsgFlag = false;
var broadcastMsg = "";

function titleBarButtonClick(thisobj){	
	/**
	var children = document.getElementById('titleBar').getElementsByTagName('*');
	if (children!=null && children.length>0) {
		for(var i=0; i<children.length; i++){
			children[i].className = "titleBarButton";
		}
	}
	thisobj.className="titleBarActiveButton";
	**/
}

function openMsgWindow(id){
	var prop='';
	prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	prop=prop+'width=750,';
	prop=prop+'height=390';
	window.open('popMsg.jsp?q='+id,'msgWin',prop);
}

function openPersonalWindow(){
	var actItem = "b1";
	var prop='';
	prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	prop=prop+'width=750,';
	prop=prop+'height=390';
	window.open('../sys/ap/sysap001f_s.jsp?actItem='+actItem,'個人基本資料維護',prop);
}

function showBoxMsg(msg,b) {
	if(msg !=null && msg.length!=0){    		
		//new popUp(760, 1, 200, 70, "boxMsg", msg, "white", "black", "10pt 細明體", "即時訊息", "#C8E8D9", "black", "#EFF9FF", "gray", "#EFF9FF", true, true, true, true, true);
		if (boxMsgFlag) {			
			//fadeboxout("boxMsg");
			changecontent("boxMsg",msg);			
			fadeboxin("boxMsg");
		} else {
			//790 , 1 , 200 , 70
			if (b!=null && b==true) new popUp(500 , 1 , 350 , 70 , "boxMsg" , msg, "white" , "black" , "10pt 新細明體" , "即時訊息" , "#C8E8D9" , "black" , "#EFF9FF", "#EFF9FF" , "black" , true , true , true , true , false , false , '../images/min.gif' , '../images/max.gif' , '../images/close.gif' , '../images/resize.gif');
			else new popUp(790 , 1 , 200 , 70 , "boxMsg" , msg, "white" , "black" , "10pt 新細明體" , "即時訊息" , "#C8E8D9" , "black" , "#EFF9FF", "#EFF9FF" , "black" , true , true , true , true , false , false , '../images/min.gif' , '../images/max.gif' , '../images/close.gif' , '../images/resize.gif');
			boxMsgFlag = true;
		}
		//3秒後自動消失 , 避免暫存的訊息的誤解
		if (b!=null && b==true) {			
		} else {
			window.setTimeout("fadeboxout('boxMsg');", 3000);
		}		
	} else {
		if (boxMsgFlag) { 
			//hidebox("boxMsg");
			fadeboxout("boxMsg");
		}
	}
}

function BroadCastMsg() {
	var x = getRemoteData('msgBroadCast.jsp');	
	if (x!=null && x.length!=0) {
		if (x!=broadcastMsg) {
			showBoxMsg("<font color='red'>"+x+"</font>",true);
			broadcastMsg = x;
		}
	}
	//window.setTimeout("BroadCastMsg()", 60000);
	// onload="BroadCastMsg();" 
	//try {
	//	getDisplayMsg();	
	//} catch (e) {}	
}

function clearDisplayMsg() {
	//top.fbody.menu.document.getElementById('msgContainer').style.display = 'none';	
	//top.fbody.menu.document.getElementById('msgList').innerHTML = "";	
}
function getDisplayMsg() {
	var menuFrame = top.fbody.menu;
	if (menuFrame!=null && isObj(menuFrame.document.getElementById('msgContainer')) && menuFrame.document.getElementById('msgContainer').style.display=='none') {	
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
			    sb.append('<td nowrap align="center"><a href="listMsgUnRead.jsp" target="mainframe">Read More..</a>');			    
			    sb.append('</td>');
			    sb.append('</tr>');
			    sb.append('</table>');
			    sb.append("</div>");					
			}
			sb.append('<input type="hidden" id="msgIdList" value="').append(ids.toString()).append('">');
			var y = (menuFrame.document.pageYOffset)?(menuFrame.document.pageYOffset):(document.documentElement)?menuFrame.document.documentElement.scrollTop:menuFrame.document.body.scrollTop; 
			top.fbody.menuleft.doNormal();
			var s6 = menuFrame.document.getElementById('msgList');
			s6.innerHTML = sb.toString();
			menuFrame.scrollTo(0,y);
			menuFrame.document.getElementById('msgContainer').style.display = '';
			//menuFrame.document.getElementById('msgContainerTitle').focus();		
			//end:	clearDisplayMsg
			//$.fn.cycle.defaults.autostop = 1;
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

//window.setInterval("BroadCastMsg()", 30000);

function init() {
	BroadCastMsg();
	window.setInterval("BroadCastMsg()", 60000);	
}
</script>

</head>
<body onLoad="init();">
<form>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>   
    <td nowrap align="right" background="../images/banner_1.png"><table id="Table_01" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td nowrap rowspan="2"><img src="../images/v1_04.png" width="157" height="60" alt=""></td>
        <td nowrap align="center" background="../images/v1_05.png"><font color="#000000" size="2">使用者：<%=User.getUserName()+"-"+Common.get(Dept.getDepartment())%>&nbsp;&nbsp; <a class="text_w" href="logout.jsp" target="_top"><img src="../images/out.gif" alt="登出" border="0" align="absmiddle"></a>&nbsp;</font></td>
        <td nowrap><img src="../images/spacer.gif" width="1" height="21" alt=""></td>
      </tr>
      <tr>
        <td nowrap><img src="../images/v1_06.png" width="338" height="39" alt=""></td>
        <td nowrap><img src="../images/spacer.gif" width="1" height="39" alt=""></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td nowrap colspan="2" background="../images/v1_07.png">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td nowrap><table border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td nowrap><img src="../images/v1_07.png" alt=""/></td>
            <td nowrap>&nbsp;</td>
            <td nowrap><a id="b1" class="titleBarButton" target="fbody" href="body.jsp" onClick="titleBarButtonClick(this);"<%=css%>>系統公告</a></td>
            <td nowrap>&nbsp;</td>
            <td nowrap><a id="b2" class="titleBarButton" target="mainframe" href="../home/listBoard.jsp?q=2" onClick="titleBarButtonClick(this);">文件下載區</a></td>
            <td nowrap>&nbsp;</td>
            <td nowrap><a id="b3" class="titleBarButton" href="#" onClick="openPersonalWindow();titleBarButtonClick(this);">個人基本資料維護</a></td>
            <td nowrap>&nbsp;</td>
          </tr>
        </table></td>
        <td nowrap align="right"><font class="text_w" size="2">程式版本：1.0　上次登入：<%=Common.formatYYYMMDD(User.getLastLoginDate(),4) + " " + Common.formatHHMMSS(User.getLastLoginTime()) + " IP：" + User.getLastLoginIP() + (Common.get(User.getLastLoginDate()).equals("")?"":" 登入成功")%></font>&nbsp;</td>
      </tr>
    </table></td>
    </tr>
</table>
 <div id="topbar"></div>
</form>
</body>
</html>
