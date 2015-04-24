<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>操作時間逾時警告</title>
<style>
.bodyStyle{
    font-family: 新細明體;
    font-size: 12;
    margin-left: 5px;
    margin-right: 5px;
    margin-top: 10px;
    background-color:#DDDDDD;
}
.imgStyle{
    position:absolute;
    z-index:2;
    left:10px;
	top:20px
}
</style>
<script type="text/javascript">
function sessionTimeout(){
	opener.top.top.location.href="<%=request.getContextPath()%>/index.jsp";
	window.close();
}
</script>
</head>
<body class="bodyStyle">
<center>
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font color="red">操作時間逾期，請您重新登入，謝謝！</font>
<p>&nbsp;
<input type="button"  name="btnCannel" style="font-size:12" value="確　　定" onclick="sessionTimeout();">
</center>
</body>
</html>
