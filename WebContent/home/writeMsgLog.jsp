<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="head.jsp" %>
<%
String[] ids = Common.get(request.getParameter("q")).split(",");
if (ids!=null && ids.length>0) {
	java.util.List<CommonMsgLog> saveList = new java.util.ArrayList<CommonMsgLog>();
	for (int i=0; i<ids.length; i++) {		
		if (Common.getLong(ids[i])>0) {
			CommonMsg o = new CommonMsg();
			o.setId(Common.getLong(ids[i]));
			
			CommonMsgLog obj = new CommonMsgLog();
			obj.setLogDate(Datetime.getYYYMMDD());
			obj.setLogTime(Datetime.getHHMMSS());
			obj.setCommonMsg(o);
			obj.setUserId(User.getUserId());
			obj.setIsSlide("Y");
			saveList.add(obj);					
		}
	}
	if (saveList!=null && saveList.size()>0) ServiceGetter.getInstance().getCommonService().save(saveList);
}
%>