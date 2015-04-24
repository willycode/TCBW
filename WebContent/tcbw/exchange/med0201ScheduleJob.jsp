<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Med0201 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getMed0201ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Med0201 Job ===========================");
out.write("執行完畢");
%>