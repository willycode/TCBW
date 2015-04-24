<%@ include file="../../home/head.jsp" %>
<%if(User!=null){ %>
<%if("in".equals(Common.get(User.getInORout()))){ %>
<jsp:include page="conex0202f.jsp" />
<%}else if("Y".equals(Common.get(User.getIsGetNewID()))){ %>
<jsp:include page="conex0203f.jsp?v=out&p=Y" />
<%}else{ %>
<jsp:include page="conex0203f.jsp?v=out" />
<%}} %>