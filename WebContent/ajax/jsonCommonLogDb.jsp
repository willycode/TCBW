<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="net.sf.json.*"%>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String code = Common.get(request.getParameter("code"));
String methodName= Common.get(request.getParameter("methodName"));
String hql=Common.get(request.getParameter("hql"));
String db=Common.get(request.getParameter("db"));


String applNo="",inOrOutcreator="",notifierName="",dbId="";

try 
{
	    java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if(list!=null && list.size()>0)
		{
			for (int i=0; i<list.size(); i++) 
			{
				Object[] o = (Object[]) list.get(i);
				dbId=Common.get(o[0]);
				applNo=Common.get(o[1]);
				inOrOutcreator=Common.get(o[2]);
				notifierName=Common.get(o[3]);
			}
		}
	    
		CommonLog commonLog = new CommonLog();
		
		commonLog.setCode(code);//通報表代碼
		commonLog.setOpenDate(Datetime.getYYYMMDD());//開啟日期
		commonLog.setOpenTime(Datetime.getHHMMSS());//開啟時間
		commonLog.setUserId(User.getUserId());//開啟人員
		commonLog.setIp(User.getUserIP());//開啟人員IP
		commonLog.setMethodName(methodName);//方法
		commonLog.setDb(db);//資料表
		commonLog.setDbId(dbId);//資料表IP
		commonLog.setApplNo(applNo);//
		commonLog.setOpenUserId(inOrOutcreator);//被開啟USerID
		commonLog.setOpenUserName(notifierName);

		ServiceGetter.getInstance().getTcbwService().save(commonLog);
	
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>
