<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ca.SYSCA002F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%	
obj.setAccountingYear("097");
String strJavaScript = "";
if (!"".equals(Common.get(obj.getJs()))) strJavaScript += "\nopener." + obj.getJs() + ";\n\n";

String treeHTML = "";

//權限控制
if(!"Y".equals(obj.getIsLimit())){
	int q = 0;
	CommonDepartment parent = null;
	if (UserRole.getRoleId()>2) {
		treeHTML = obj.buildCheckBoxTree("d","機關代碼",obj.getAccountingYear(),null,"selectNode",false,false);
	} else {
		parent = ServiceGetter.getInstance().getCommonService().getCommonDepartmentDao().getCommonDepartment(Dept.getFullCode());		
	}
	if (parent!=null) {
		treeHTML = obj.buildCheckBoxTree("d","機關代碼",obj.getAccountingYear(),parent.getId().toString(),"selectNode",false,false);
	}	
} else {
	treeHTML = obj.buildCheckBoxTree("d","機關代碼",obj.getAccountingYear(),null,"selectNode",false,false);
}
%>
<html>
<head>
<title>機關輔助視窗</title>

<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/dtree.css" type="text/css" />
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript" src="../js/dtree.js"></script>
<script type="text/javascript">
	function selectNode(objCheckBox,selectName,level,fullCode,fullName){
		if (isObj(opener.document.all.item("<%=obj.getPopOrganID()%>"))) {		
			opener.document.all.item("<%=obj.getPopOrganID()%>").value=objCheckBox.value;		
		}
		if ("<%=obj.getPopFullCode()%>"!="" && isObj(opener.document.all.item("<%=obj.getPopFullCode()%>"))) {
			opener.document.all.item("<%=obj.getPopFullCode()%>").value=fullCode;
		}				
		if (isObj(opener.document.all.item("<%=obj.getPopOrganName()%>"))) {
			opener.document.all.item("<%=obj.getPopOrganName()%>").value=selectName; //fullName or selectName;		
		}
		//2,3,4,4	
		<%=strJavaScript%>	
		window.close();
	}
	
	function selectEmptyNode() {
		if (isObj(opener.document.all.item("<%=obj.getPopOrganID()%>"))) {		
			opener.document.all.item("<%=obj.getPopOrganID()%>").value="";
		}
		if ("<%=obj.getPopFullCode()%>"!="" && isObj(opener.document.all.item("<%=obj.getPopFullCode()%>"))) {
			opener.document.all.item("<%=obj.getPopFullCode()%>").value="";
		}		
		if (isObj(opener.document.all.item("<%=obj.getPopOrganName()%>"))) {
			opener.document.all.item("<%=obj.getPopOrganName()%>").value="";
		}
		<%=strJavaScript%>	
		window.close();
	}
</script>
</head>
<body topmargin="3" leftmargin="3" rightmargin="3" bottommargin="3">
<form id="form1" name="form1" method="post">
<input type="hidden" name="popOrganID" value="<%=obj.getPopOrganID()%>">
<input type="hidden" name="popOrganName" value="<%=obj.getPopOrganName()%>">
<input type="hidden" name="isLimit" value="<%=obj.getIsLimit()%>">

<div id="dtreeContainer" class="dtree">
	<a href="javascript: d.openAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("open_all"))%></a> | <a href="javascript: d.closeAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("close_all"))%></a><%if (UserRole.getRoleId()>2){ %> | <a href="javascript: selectEmptyNode();">清空</a><%} %><br /><br />
	<div id="dtree">
	
	<script type="text/javascript">
		<!--
		var d = new dTree('d');
		d.config.folderLinks=true;
		d.config.useCookies=false;
		d.config.useIcons = false;
		d.config.objForm =  document.form1;
		d.config.checkBoxName = "auth";
		
		<%=treeHTML%>

		document.write(d);
		//-->
	</script>
	</div>
</div>
</form>
</body>
</html>
