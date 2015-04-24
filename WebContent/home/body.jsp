<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String sIsGetBackPw = "N";
	Object isGetBackPw = session.getAttribute("isGetBackPw");
	if(isGetBackPw != null){
		sIsGetBackPw = isGetBackPw.toString();
	}
	String sIsInOrOut = "O";
	Object isInOrOut = session.getAttribute("isInOrOut");
	if(isInOrOut != null){
		sIsInOrOut = isInOrOut.toString();
	}
	String sIsGetNewID = "N";
	Object isGetNewID = session.getAttribute("isGetNewID");
	if(isGetNewID != null){
		sIsGetNewID = isGetNewID.toString();
	}
%>
<html>
<head>

<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
</head>
	<frameset id="bodyFrame" name="bodyFrame" cols="26,229,*" border="0" framespacing="0" rows="*" frameborder="NO">
		<frame id="menuleft" name="menuleft" scrolling="NO" border="0" framespacing="0" src="upperfunction.jsp"> 	    
	    <frame id="memu" name="menu" border="0" framespacing="0" src="dTreeMenu.jsp">
	    <frameset rows="30,*" border="0" framespacing="0" rows="*" frameborder="NO"> 
	    	<frame id="mainhead" name="mainhead" src="mainhead.jsp" scrolling="NO">
	    	<%
	    	if (("Y".equals(sIsGetBackPw) || "Y".equals(sIsGetNewID)) && "O".equals(sIsInOrOut)) {
	    	%>
	    		<frame id="mainframe" name="mainframe" src="../tcbw/conex/conex0201f.jsp">
	    	<%
	    	} else if("Y".equals(sIsGetBackPw) && "I".equals(sIsInOrOut)) {
	    	%>	
	    		<frame id="mainframe" name="mainframe" src="../tcbw/conin/conin0101f.jsp" >  
	    	<%
	    	} else {
	    	%>
				<frame id="mainframe" name="mainframe" src="welcome.jsp">
			<%
	    	}
			%>   
		</frameset><noframes></noframes>	   
	</frameset>	
<body>
</body>	
</html>

