<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Med0503 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getMed0503ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Med0503 Job ===========================");
out.write("執行完畢");
%>