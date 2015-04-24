<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.kangdainfo.ServiceGetter"%>
<%
System.out.println("======================= Start Med0502 Job ===========================");
ServiceGetter.getInstance().getScheduleService().getMed0502ScheduleDao().parseScheduleAndSaveLog();
System.out.println("======================= End Med0502 Job ===========================");
out.write("執行完畢");
%>