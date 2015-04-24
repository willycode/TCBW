<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.kangdainfo.persistence.PersistenceServiceGetter"%>
<%@include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ap.SYSAP003F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
if (UserRole.getRoleId()>2) {	
	
StringBuilder sbSystem = new StringBuilder();
String sysid = (String) session.getAttribute(WebConstants.SESSION_CURRENT_SYSTEM_ID);
SubSystem currentSubSystem = ServiceGetter.getInstance().getCommonService().getSubSystemById(Common.getInt(sysid));

int sid = Common.getInt(request.getParameter("subSystemId"));
String[] auth = request.getParameterValues("auth");

String href = "";
String nodeList = "";
long fixAdd = Common.getLong(request.getParameter("fixAdd"));
String btnSubmit = Common.get(request.getParameter("btnSubmit"));
String state = Common.get(request.getParameter("state"));

java.util.List list = ServiceGetter.getInstance().getCommonService().getSubSystems();
if (list!=null && list.size()>0) {
	sbSystem.append("<option value=''>全部</option>");	
	for (int i=0; i<list.size(); i++) {
		SubSystem sys = (SubSystem) list.get(i);
		sbSystem.append("<option value='").append(sys.getId()).append("'");	
		if (sys.getId()==sid) {
			sbSystem.append(" selected");
			if (state.equals("showMenu")) nodeList = ServiceGetter.getInstance().getCommonService().getCommonAuthDao().buildCheckBoxTree("d", "請選擇要匯出的選單", "auth", "d", "d.checkBoxClick", String.valueOf(sys.getId()), null, UserRole, true, false, false);
		}
		sbSystem.append(">").append(sys.getName()).append("</option>");
	}
}

if (!"".equals(btnSubmit)) {
	String hql = "from CommonDtree where 1=1 ";
	if (sid!=0) hql += " and sysid=" + sid;
	StringBuilder sb = new StringBuilder();
	if (auth!=null&& auth.length>0) {
		sb.append("0");
		for (int i=0; i<auth.length; i++) {
			sb.append(",").append(auth[i]);
		}
		hql += " and id in (" + sb.toString() + ")";
	}
	hql += " order by sysid, pid, sorted, id";
	sb.setLength(0);
	list = ServiceGetter.getInstance().getCommonService().load(hql);
	if (list!=null && list.size()>0) {
		for (int i=0; i<list.size(); i++) {
			CommonDtree dt = (CommonDtree) list.get(i);
			sb.append("INSERT INTO COMMON_DTREE (id,sysid,pid,name,url,title,target,icon,iconOpen,opened,btnRead,btnWrite,sorted) VALUES (");
			sb.append(dt.getId()+fixAdd).append(",");
			sb.append(dt.getSysid()).append(",");
			sb.append((dt.getPid()!=null && dt.getPid()>0)?dt.getPid()+fixAdd:(dt.getPid()!=null?dt.getPid():dt.getSysid())).append(",");
			sb.append(Common.sqlChar(dt.getName())).append(",");
			sb.append(Common.sqlChar(dt.getUrl())).append(",");		
			sb.append(Common.sqlChar(dt.getTitle())).append(",");
			sb.append(Common.sqlChar(dt.getTarget())).append(",");
			sb.append(Common.sqlChar(dt.getIcon())).append(",");
			sb.append(Common.sqlChar(dt.getIconOpen())).append(",");
			sb.append(Common.sqlChar(dt.getOpened())).append(",");
			sb.append(Common.sqlChar(dt.getBtnRead())).append(",");
			sb.append(Common.sqlChar(dt.getBtnWrite())).append(",");
			sb.append(dt.getSorted()!=null?dt.getSorted():1000).append(");\n");
		}
		java.io.File f = new java.io.File(System.getProperty("java.io.tmpdir")+java.io.File.separator+Datetime.getYYYMMDD()+"_menu.sql");
		Common.writeStringToFile(sb.toString(),f,"UTF-8");
		//new java.io.File(System.getProperty("java.io.tmpdir")+java.io.File.separator+Common.getVMID()+".xls");
		href = "<br>匯出成功：<a class='sLink2' href=\"" + request.getContextPath() +"/downloadFileSimple?fileName=" + f.getPath() + "\">下載" + f.getName() + "</a><br>";
	}	
}

%>
<html>
<head>
<title>功能選單Insert SQL匯出程式</title>
<link rel="stylesheet" href="../../js/default.css" type="text/css" />
<link rel="stylesheet" href="../../js/dtree.css" type="text/css" />
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/dtree.js"></script>
<script type="text/javascript">
function showMenu() {
	form1.state.value = "showMenu";
	form1.submit();
}
</script>
</head>
<body>
<%=href%>
<form id="form1" name="form1">
    <table class="table_form" width="100%">
    <tr>
        <td nowrap colspan="2" class="td_lable">功能選單匯出作業</td>
    </tr>
    <tr>
    	<td nowrap width="20%" class="td_lable">子系統代碼：</td>
    	<td nowrap>
		      <select name="subSystemId" id="subSystemId">
		    	<%=sbSystem.toString()%>
		    </select>  <input type="button" value="顯示選單供挑選" onclick="showMenu();">
    	</td>
    </tr>
    <tr>
    	<td nowrap class="td_lable">輸出ID固定加：</td>
    	<td nowrap>
			<input type="text" name="fixAdd" size="10" mexlength="20"> 	<input type="submit" name="btnSubmit" value="確定匯出">	
    	</td>
    </tr>    
    <tr>
    	<td nowrap width="20%" class="td_lable">功能選單：</td>
	    <td nowrap valign="top">
		<div id="d" class="dtree">
			<a href="javascript: d.openAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("open_all"))%></a> | <a href="javascript: d.closeAll();"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("close_all"))%></a><br /><br />

			<script type="text/javascript">
				var d = new dTree('d','../../images/');
				d.config.folderLinks=true;
				d.config.useCookies=false;
				d.config.useIcons = false;
				d.config.objForm =  document.form1;
				d.config.checkboxPrefix = "d";
				<%=nodeList%>
				document.write(d);
			</script>

		</div>
		</td>    	
    </tr>
	</table>
	<input type="hidden" name="state">
</form>
</body>
</html>
<%}%>