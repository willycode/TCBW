<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Med0203 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getMed0203ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Med0203 Job ===========================");
out.write("執行完畢");
%>