<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Cos0301 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getCos0301ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Cos0301 Job ===========================");
out.write("執行完畢");
%>