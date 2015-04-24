<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Drg0101 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getDrg0101ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Drg0101 Job ===========================");
out.write("執行完畢");
%>