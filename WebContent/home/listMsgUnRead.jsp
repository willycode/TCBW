<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.wm.SYSWM003F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
java.util.ArrayList<String[]> arrList = obj.getUnReadMsg(User,false);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Notice</title>
<link rel="stylesheet" href="../js/default.css" type="text/css">
<link rel="stylesheet" href="../js/style.css" type="text/css">
<style>
.sLink2:link {  font-family: "細明體", "新細明體";  color: #C90026; text-decoration: none }
.sLink2:visited {  font-family: "細明體", "新細明體"; color: #C90026; text-decoration: none }
.sLink2:active {  font-family: "細明體", "新細明體";  color: #000099}
.sLink2:hover {  font-family: "細明體", "新細明體";  text-decoration: none; color: #FF7E00; }
</style>

<script type="text/javascript">
function popBoard(id){
	window.open("popMsg.jsp?q="+id,"","top=100,left=210,width=600,height=420,scrollbars=yes,resizable=yes");	
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post">

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td nowrap><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                    <td nowrap><img src="../images/home4_r1_c1.jpg" alt="q" width="67" height="46" /></td>
                    <td nowrap width="100%" background="../images/notice3_01.gif" class="home_title"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                    <td nowrap class="home_title">※訊息通知</td>
                    <td nowrap align="right"> </td>
                  </tr>
                  </table></td>
                    <td nowrap class="home_title"><img src="../images/notice3_03.gif" alt="q" width="17" height="46" /></td>
                  </tr>
                </table></td>
                </tr>
                  <tr>
                    <td nowrap><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <%
          
java.util.Iterator it= arrList.iterator();
if (it!=null) { 
	while(it.hasNext()){
		String[] rowArray=new String[4];
		rowArray=(String[])it.next();
%>                    
         <tr>
           <td nowrap align="right" background="../images/notice_line_01.gif" ><img src="../images/notice_line_01.gif" width="3" height="3" /></td>
           <td nowrap width="1%" class="sLink2"><img src="../images/home_icon01.gif" alt="訊息通告" /></td>
           <td nowrap width="100%" bgcolor="#F7F7F7" class="sLink2"><a class="sLink2" href="#" onClick="popBoard('<%=rowArray[0]%>')"><%=rowArray[2] + " " + Common.get(rowArray[3])%></a></td>
           <td nowrap background="../images/notice_line_05.gif" class="sLink2"><img src="../images/notice_line_05.gif" width="3" height="3" /></td>
         </tr>
<%
	}
}
%>  					

                      <tr>
                        <td nowrap colspan="4"><img src="../images/home_r3_c1.jpg" alt="q" width="100%"/></td>
                    </tr>
                </table></td>
                </tr>
                  <tr>
                    <td nowrap><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                    <td nowrap width="1%"><img src="../images/notice_buttom_01.gif" alt="q" width="15" height="16" /></td>
                    <td nowrap width="100%" background="../images/notice_buttom_02.gif"><img src="../images/notice_buttom_02.gif" alt="q" width="1" height="16" /></td>
                    <td nowrap><img src="../images/notice_buttom_04.gif" alt="q" /></td>
                  </tr>
                </table></td>
                </tr>
  </table>
</form>         
</body>
</html>        
