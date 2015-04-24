<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="net.sf.json.*"%>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");


String sysTemType = Common.get(request.getParameter("sysTemType"));


String formCode = Common.get(request.getParameter("formCode"));

System.out.println("formCode=="+formCode);


String id = Common.get(request.getParameter("id"));
String notifier = Common.get(request.getParameter("notifier"));
String applNo = Common.get(request.getParameter("applNo"));
String stateus = Common.get(request.getParameter("stateus"));

try 
{
	String hql = "from Con0003Db where systemType = " + Common.sqlChar(sysTemType);
	       hql += " and formCode = " + Common.sqlChar(formCode);
	       hql += " and dbID = " + Common.sqlChar(id);
	
	if(!"".equals(stateus))
		hql += " and stateus = " + Common.sqlChar(stateus);
	       
	
	Con0003Db dt = (Con0003Db) View.getObject(hql);
	
	if (dt != null) 
	{
		JSONObject item = new JSONObject();	
		item.put("id", dt.getId());
		
		if(!User.getUserId().equals(dt.getUserID()))
		{	
			out.write(item.toString());
		}
	}
	else
	{
		Con0003Db con0003Db = new Con0003Db();
		con0003Db.setApplNo(applNo);
		con0003Db.setStateus(stateus);
		con0003Db.setDbID(Common.getLong(id));
		con0003Db.setFormCode(formCode);
		con0003Db.setSystemType(sysTemType);
		con0003Db.setUserID(User.getUserId());
		con0003Db.setUserName(User.getUserName());
		con0003Db.setIp(User.getUserIP());
		con0003Db.setLockDate(Datetime.getYYYMMDD());
		con0003Db.setLockTime(Datetime.getHHMMSS());
		con0003Db.setNotifier(notifier);
		con0003Db.setCreateDate(Datetime.getYYYMMDD());
		con0003Db.setCreateTime(Datetime.getHHMMSS());
		con0003Db.setCreator(User.getUserId());
		ServiceGetter.getInstance().getTcbwService().save(con0003Db);
	}	
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>
