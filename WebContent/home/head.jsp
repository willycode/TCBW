<%@ page import="com.kangdainfo.*" %>
<%@ page import="com.kangdainfo.web.util.*" %>
<%@ page import="com.kangdainfo.common.util.*" %>
<%@ page import="com.kangdainfo.common.util.report.*" %>
<%@ page import="com.kangdainfo.common.model.bo.*" %>
<%@ page import="com.kangdainfo.tcbw.model.bo.*" %>
<%@ page import="org.owasp.esapi.ESAPI" %>
<%@ page import="com.kangdainfo.persistence.PersistenceServiceGetter" %>
<%@ page import="com.kangdainfo.tcbw.util.TCBWCommon" %>
<jsp:useBean id="User" scope="session" class="com.kangdainfo.common.model.bo.CommonUser"/>
<jsp:useBean id="Dept" scope="session" class="com.kangdainfo.common.model.bo.CommonDepartment"/>
<jsp:useBean id="UserRole" scope="session" class="com.kangdainfo.common.model.bo.CommonUserRole"/>
<%
if (User==null || Dept==null || UserRole==null || UserRole.getRoleId()==null || UserRole.getRoleId()<1){
	out.println("<script language=\"javascript\">");
	out.println("var prop='';");
	out.println("prop=prop+'width=300px,height=120,scrollbars=no'");
	out.println("window.open('" + request.getContextPath() + "/home/sessionTimeout.jsp','sessionTimeout',prop);");
	out.println("top.top.location.href='" + request.getContextPath() + "/index.jsp';");
	out.println("</script>");
	return;
}
%>
