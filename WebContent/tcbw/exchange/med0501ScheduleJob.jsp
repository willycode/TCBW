<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Med0501 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getMed0501ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Med0501 Job ===========================");
out.write("執行完畢");
%>