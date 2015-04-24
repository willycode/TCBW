<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SYSCA002F" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ca.SYSCA002F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%	
	obj.setAccountingYear("097");
	String openNodeID = Common.checkGet(request.getParameter("openNodeID"));
	
	
%>
<html>
<head>
	<title>Department Management Studio</title>
	<link rel="stylesheet" href="../../js/dtree.css" type="text/css" />
	<script type="text/javascript" src="../../js/function.js"></script>		
	<script type="text/javascript" src="../../js/dtree.js"></script>
	<script type="text/javascript">
	var popWin;
	var popWinFlag=false;
	
	function openTreeNode(tid) {	
		var dt = top.frames['dTreeContent'].d;
		dt.openTo(tid, true);
	}	
	
	function reloadTree(tid,errMsg) {
		if (errMsg!=null && errMsg.length>0) showErrorMsg(errMsg);			
		window.location.href = "deptManage.jsp?openNodeID="+tid; //sScript"?openNodeID="+tid;
		try {
			popWin.close();		
		} catch(e) {}
	}
	
	function reloadForm() {
		window.parent.frames['dTreeContent'].location.href = "blank.htm";
	}	
	
	function getURLParameter( url, name )
	{
	  var regexS = "[\\?&]"+name+"=([^&#]*)";
	  var regex = new RegExp( regexS );
	  var results = regex.exec( url );
	  if( results == null )
	    return "";
	  else
	    return results[1];
	}
	
	function popFormFolders(url,event) { 	
		var s = getURLParameter(url,'exeKind');		
		var sid = getURLParameter(url,'sid');
		var fid = getURLParameter(url,'fid');
		var level = getURLParameter(url,"level");
		if ((fid=="-1") && (s=="delete" || s=="update")) {
			//alert("對不起，不允許從根節點執行此動作！");
			return false;
		}
		
		if ((level=="4") && (s=="insert")) {
			alert("<%=Common.MSG_DTREE_LEVEL_LIMIT_FOR_ADD%>");
			return false;
		}


		if (s=="delete") {
			popWin = window.open(url,'dTreeDelete','scrollbars=1, resizable=yes, status=yes, toolbar=no,menubar=no,width=550,height=390');			
		} else {
			form1.sid.value = sid;
			form1.fid.value = fid;
			form1.exeKind.value = s;		
			form1.submit();
		}
	}
	
	function disableLeftClick() {
		if (event.button==1) return false;
	}

	function listFormItem() {
		var ml = document.form1;
		var len = ml.elements.length;
		var n = 0, i = 0;

		for (i = 0; i < len; i++) {
		    var e = ml.elements[i];
		    document.write(e.name+'-'+e.value+'<br>');
		}
		return false;
	}

	</script>
</head>	
<body id="body" onload="init();">
<form name="form1" id="form1" action="deptForm.jsp" target="dTreeContent">
<input type="hidden" name="sid" value="">
<input type="hidden" name="fid" value="">
<input type="hidden" name="exeKind" value="">

<div class="dtree">
	<a href="javascript: d.openAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("open_all"))%></a> | <a href="javascript: d.closeAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("close_all"))%></a><br /><br />
	<div id="dtree">
	<script type="text/javascript">
		<!--
		var d = new dTree('d','../../images/');
		d.config.folderLinks=true;
		d.config.useCookies=false;
		d.config.target = 'dTreeContent';
		//d.config.useIcons = false;
		//d.config.objForm =  document.form1;
		//d.config.checkBoxName = "auth";

		d.add(-97,-1,'行政院衛生署食品藥物管理局','deptForm.jsp?sid=-97&fid=-1','','','','','','','');
		<%
		out.write(obj.buildManageTree());
		%>

		document.write(d);

		function init() {
			var treeNode = "<%=openNodeID%>";	
			if (treeNode.length>0 && getNumeric(treeNode)>0) {
				if (treeNode=="0" || treeNode=="-1") {} {
					d.openTo(treeNode);
				}				
			}
		}
		
		
		//-->		
	</script>
	</div>
</div>

</form>


<br /><br /><br /><br /><br />
<p></p><p></p><p></p><p></p><p></p>
	<div id="divContext" class="contextmenu" style="border: 1px solid gray; display: none; position: absolute; background-color: #ffffd2;">
		<a id="aInsertNav" href="">新增</a><br />
		<a id="aUpdateNav" href="">修改</a><br />
		<a id="aDeleteNav" href="" onclick="return confirm('確定要刪除該機關和其下的所有子機關?');CloseContext();">刪除</a><br />
	</div>
	
	<script type="text/javascript" src="../../js/contextmenu.js"></script>
	
</body>
</html>

