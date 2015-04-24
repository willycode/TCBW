<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Drg0102 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getDrg0102ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Drg0102 Job ===========================");
out.write("執行完畢");
%>