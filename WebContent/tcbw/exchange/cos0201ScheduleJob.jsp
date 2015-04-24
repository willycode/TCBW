<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Cos0201 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getCos0201ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Cos0201 Job ===========================");
out.write("執行完畢");
%>