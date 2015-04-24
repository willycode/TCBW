<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ap.TreeEntry">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
	obj.setSysid("-11");	
	String openNodeID = Common.checkGet(request.getParameter("openNodeID"));
	String popId = Common.checkGet(request.getParameter("popId"));
	String popName = Common.checkGet(request.getParameter("popName"));
	
	String jsFunction = Common.checkGet(request.getParameter("js"));
	
	String strJavaScript = "";
	if (!"".equals(Common.get(jsFunction))) strJavaScript += "\nopener." + jsFunction + ";\n\n";
%>
<html>
<head>
	<title>Node Select</title>
	<link rel="stylesheet" href="../js/dtree.css" type="text/css" />
	<script type="text/javascript" language="javascript" src="../js/dtree.js"></script>
	<script type="text/javascript" language="javascript">
	function selectNode(obj,selectName){
		if (isObj(opener.document.all.item("<%=popId%>"))) {		
			opener.document.all.item("<%=popId%>").value=obj.value;		
		}
		if (isObj(opener.document.all.item("<%=popName%>"))) {
			opener.document.all.item("<%=popName%>").value=selectName;		
		}
		<%=strJavaScript%>	
		window.close();
	}
	</script>
</head>	
<body id="body" onload="init();">
<form name="form1" id="form1">
<div class="dtree">
	<p><a href="javascript: d.openAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("open_all"))%></a> | <a href="javascript: d.closeAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("close_all"))%></a></p>

	<script type="text/javascript" language="javascript">
		<!--
		var d = new dTree('d');
		d.config.folderLinks=true;
		d.config.useCookies=false;
		d.config.useIcons = false;
		d.config.objForm =  document.form1;
		d.config.checkBoxName = "auth";
		
		<%
		out.write(obj.buildCheckBoxTree("功能選單","selectNode",false,true));
		%>

		document.write(d);
		
		
		function init() {
			var treeNode = "<%=openNodeID%>";	
			if ("<%=openNodeID%>".length>0) {			
				//d.openTo(treeNode,true);
				d.openTo(treeNode);
			}
		}		
		//-->
	</script>

</div>

</form>
</body>
</html>
