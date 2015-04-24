<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Med0101 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getMed0101ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Med0101 Job ===========================");
out.write("執行完畢");
%>