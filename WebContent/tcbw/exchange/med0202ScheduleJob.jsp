<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Med0202 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getMed0202ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Med0202 Job ===========================");
out.write("執行完畢");
%>