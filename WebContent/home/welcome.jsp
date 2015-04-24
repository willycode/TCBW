<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.wm.SYSWM001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
obj.setQ_masterId("1");
obj.setQ_startDateE(Datetime.getYYYMMDD());
obj.setQ_endDate(Datetime.getYYYMMDD());
java.util.ArrayList arrList = (java.util.ArrayList)obj.queryAll();
%>

<html>
<head>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<title>Notice</title>
<link rel="stylesheet" href="../js/default.css" type="text/css">
<link rel="stylesheet" href="../js/style.css" type="text/css">
<style>
body {
	margin-top: 0px;
	margin-left: 5px;
	margin-right: 0px;
	margin-bottom: 0px;
}
  
.showTD {
	padding: 2px 5px 2px 2px;
	border-left: 1px solid silver;
	border-bottom: 1px solid silver;
	border-right: 1px solid silver;
	text-align: left;
	height:24px;
}

.th{
	font-weight:normal;
	z-index: 20;
	padding: 4px 2px 2px 2px;
	color: white;
	text-align:center;
	height:23;	
	border-left: 1px solid white;
	border-bottom: 1px solid white;
	position:relative;
}

.sLink2:link {  font-family: "細明體", "新細明體";  color: #C90026; text-decoration: none }
.sLink2:visited {  font-family: "細明體", "新細明體"; color: #C90026; text-decoration: none }
.sLink2:active {  font-family: "細明體", "新細明體";  color: #000099}
.sLink2:hover {  font-family: "細明體", "新細明體";  text-decoration: none; color: #FF7E00; }
</style>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">
function popBoard(newsID){
	window.open("popBoard.jsp?newsID="+newsID,"","top=100,left=210,width=600,height=420,scrollbars=yes,resizable=yes");	
}

function nodeclicked(){
	try {
		top.fbody.mainhead.document.getElementById("pathname").innerHTML = "<%=application.getServletContextName()%> > > 訊息公告 ";
	} catch(e) {}
}

/*列表檔案*/
function listToHTMLReport(listHeadName,listBodyName){
	var sb = new StringBuffer();
	sb.append("<table border='0' cellpadding='0' cellspacing='0' bgcolor='#000000' width='100%'><tr><td nowrap><table width='100%' border='0' cellpadding='1' cellspacing='1'>");
	//寫入Thead資料
	var listHead = document.getElementById(listHeadName);	
	for(i=0; i<listHead.rows.length; i++){
		sb.append("<tr bgcolor='#CCCCCC' align='center'>");
		for(j=0; j<listHead.rows[i].cells.length; j++){			
			if (isObj(listHead.rows[i].cells[j].childNodes[0].childNodes[0])){
				sb.append("<td nowrap>").append(listHead.rows[i].cells[j].childNodes[0].childNodes[0].nodeValue).append("</td>");
			}else{
				sb.append("<td nowrap>&nbsp;</td>");
			}
		}
		sb.append("</tr>");
	}
		
	//寫入Tbody資料	
	var listBody = document.getElementById(listBodyName);	
	for(i=0; i<listBody.rows.length; i++){
		sb.append("<tr bgcolor='#FFFFFF'>");	
		for(j=0; j<listBody.rows[i].cells.length; j++){
			if (isObj(listBody.rows[i].cells[j].childNodes[0])){
				sb.append("<td nowrap>").append(listBody.rows[i].cells[j].childNodes[0].nodeValue).append("</td>");
			}else{
				sb.append("<td nowrap>&nbsp;</td>");
			}
		}
		sb.append("</tr>");
	}	
	sb.append("</table></td></tr></table>");
	var ie = window.open();
	ie.document.write(sb.toString());
}
</script>
</head>
<body onLoad="nodeclicked();">
<form id="form1" name="form1" method="post">
<input type="hidden" name="id" value="">
<table width="100%" border="0" cellpadding="0" cellspacing="0" id="table24">
<tr><td nowrap>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="page.jsp" />
</td></tr>      
      <tr>
        <td nowrap valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" id="table24">
                <tr>
                  <td nowrap valign="top"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td nowrap><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td nowrap><img src="../images/home4_r1_c1.jpg" alt="系統公告" width="67" height="46" /></td>
                            <td nowrap width="100%" background="../images/notice3_01.gif" class="home_title">※系統公告</td>
                            <td nowrap class="home_title"><img src="../images/notice3_03.gif" width="18" height="46" /></td>
                          </tr>
                      </table></td>
                    </tr>
                    <tr>
                      <td nowrap><table width="100%" border="0" cellspacing="0" cellpadding="0">
<%      
StringBuilder sb = new StringBuilder().append("");                          
java.util.Iterator it= arrList.iterator();
if (it!=null) {
	while(it.hasNext()){
		String[] rowArray=new String[6];
		rowArray=(String[])it.next();	
		if (Common.get(rowArray[5]).equals("Y")) {
			if (ServiceGetter.getInstance().getCommonService().loadCount("from CommonNewsLog where commonNews.id="+Common.getInt(rowArray[1])+" and userId="+Common.sqlChar(User.getUserId()))<=0) {
				if (sb.length()>0) {
					sb.append(",").append(rowArray[1]);		
				} else {
					sb.append("popupFullScreen('popNews.jsp?ids=").append(rowArray[1]);	
				}				
			}			
		}		
%>
                          <tr>
                            <td nowrap align="right" background="../images/notice_line_01.gif" ><img src="../images/notice_line_01.gif" width="3" height="3" /></td>
                            <td nowrap width="1%" class="sLink2"><img src="../images/home_icon01.gif" alt="系統公告" /></td>
                            <td nowrap width="100%" bgcolor="#F7F7F7" class="sLink2"><a class="sLink2" href="#" onClick="popBoard('<%=rowArray[1]%>')"><%=rowArray[2] + " " + Common.formatYYYMMDD(rowArray[3],4)%></a></td>
                            <td nowrap background="../images/notice_line_05.gif" class="sLink2"><img src="../images/notice_line_05.gif" width="3" height="3" /></td>
                          </tr>
                          <%
	}
	if (sb.length()>0) {
		sb.append("');");
	}
}	
%>
                          <tr>
                            <td nowrap colspan="4"><img src="../images/home_r3_c1.jpg" alt="系統公告" width="100%"/></td>
                          </tr>
                      </table></td>
                    </tr>
                    <tr>
                      <td nowrap><table width="100%" border="0" cellpadding="0" cellspacing="0">
                          <tr>
                            <td nowrap width="1%"><img src="../images/notice_buttom_01.gif" width="15" height="16" /></td>
                            <td nowrap width="100%" background="../images/notice_buttom_02.gif"><img src="../images/notice_buttom_02.gif" width="1" height="16" /></td>
                            <td nowrap><img src="../images/notice_buttom_04.gif" /></td>
                          </tr>
                      </table></td>
                    </tr>
                  </table></td>
                </tr>
                </table>
        <br></td>
    </tr>
 
</table>
 </form>
 <script type="text/javascript">
 <%=sb.toString()%>
 </script>
</body>
</html>
