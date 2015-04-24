<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.kangdainfo.common.model.bo.*" %>
<%@ page import="com.kangdainfo.tcbw.util.TCBWCommon" %>
<%@ page import="com.kangdainfo.common.util.*" %>
<%@ page import="com.kangdainfo.*" %>
<%
	String msg = "";
	String userId = request.getParameter("v");
	if(userId!=null && !"".equals(userId)){
		CommonUser u = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(TCBWCommon.getDecodeString(userId)));
		if(u != null){
			if("Y".equals(Common.get(u.getIsStop()))){
				u.setIsStop("N");
				ServiceGetter.getInstance().getTcbwService().update(u);
				
				msg = "帳號啟用完成，請至<a href=\"index.jsp\">登入頁面</a>登入";
			}else{
				msg = "帳號已啟用過，請至<a href=\"index.jsp\">登入頁面</a>登入";
			}
		}else{
			msg = "查無該帳號資料，無法完成啟用";
		}
	}else{
		msg = "啟用帳號無法解析，無法完成啟用";
	}
	
	out.write(msg);
%>

