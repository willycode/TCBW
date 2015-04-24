<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ca.SYSCA002F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
	obj.setAccountingYear("097");
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
	<link rel="stylesheet" href="../../js/dtree.css" type="text/css" />
	<script type="text/javascript" language="javascript" src="../../js/dtree.js"></script>
	<script type="text/javascript" language="javascript" src="../../js/ZhNumUtil.js"></script>
	<script type="text/javascript" language="javascript" src="../../js/function.js"></script>	
	<script type="text/javascript" language="javascript">
	function selectNode(obj,selectName,level,fullCode,fullName){
		if (isObj(level) && level=="3") {
			alert("您好！　" + selectName + " 之下不能再新增下一層級之機關！\n\n若確實有新增機關之需求，請洽業務承辦人員及系統開發廠商！\n");
			obj.checked = false;
			return false;
		}
		if (isObj(opener.document.all.item("<%=popId%>"))) {		
			opener.document.all.item("<%=popId%>").value=obj.value;					
		}
		if (isObj(opener.document.all.item("<%=popName%>"))) {
			opener.document.all.item("<%=popName%>").value=selectName;		
		}
		if (isObj(opener.document.all.item("parentLevel"))) {
			opener.document.all.item("parentLevel").value=level;		
		}		
		if (isObj(opener.document.all.item("parentFullCode"))) {
			opener.document.all.item("parentFullCode").value=fullCode;		
		}
		if (isObj(opener.document.all.item("parentFullName"))) {
			opener.document.all.item("parentFullName").value=fullName;
		}		
		if (isObj(opener.document.all.item("departmentCode")) && isObj(level)) {
			try {
				switch(level) {
					case "0":
						resetDepartCode(opener.document.all.item("departmentCode"),3);
						break;
					case "1":
						resetDepartCode(opener.document.all.item("departmentCode"),4);
						break;
					case "2":
						resetDepartCode(opener.document.all.item("departmentCode"),4);
						break;
					default :
						resetDepartCode(opener.document.all.item("departmentCode"),2);
						break;
				}
			} catch(e) {alert(e)}
		}		
		<%=strJavaScript%>	
		window.close();
	}
	
	function resetDepartCode(obj,len) {
		if (obj.value>len) {
			obj.value = obj.value.substring(obj.value.length,obj.value.length-len);			
		}
		setLength(obj,len);
	}
	</script>
</head>	
<body id="body" onload="init();">
<form name="form1" id="form1">
<div class="dtree">
	<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>

	<script type="text/javascript" language="javascript">
		<!--

		d = new dTree('d','../../images/');
		d.config.folderLinks=true;
		d.config.useCookies=false;
		d.config.useIcons = false;
		d.config.objForm =  document.form1;
		d.config.checkBoxName = "auth";
		
		<%
//		out.write(obj.buildCheckBoxTree("機關","selectNode",false,true));
		out.write(obj.buildCheckBoxTree("行政院衛生署食品藥物管理局","selectNode",false,true));
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

