<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.kangdainfo.web.util.*" %>
<html>
<head>
<title><%=application.getServletContextName()%></title>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
</head>
<frameset rows="82,*,20" cols="*" border="0" framespacing="0"> 
	<frame id="title" name="title" border="0" framespacing="0" frameborder="0" noresize="yes" src="title.jsp"  scrolling="no">
	<frame id="fbody" name="fbody"  border="0" framespacing="0" frameborder="0" noresize="yes" src="body.jsp" scrolling="yes">
	<frame id="footer" name="footer" border="0" framespacing="0" frameborder="0" noresize="yes" src="footer.jsp"  scrolling="no">
</frameset><noframes></noframes>
</html>
