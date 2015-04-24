<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Cos0202 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getCos0202ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Cos0202 Job ===========================");
out.write("執行完畢");
%>