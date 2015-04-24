<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="java.io.*"%>
<%@ page import="net.sf.json.*"%>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

ServletContext context = getServletContext();
String fileID = Common.isoToBig5(Common.checkSet(request.getParameter("fileID")));
String fileType = request.getParameter("fileType");
if(fileType == null || fileType.equals("")){
	fileType = "HFR";
}

String filestoreLocation = context.getInitParameter(fileType);
try{
	JSONObject item = new JSONObject();
	String[] arrFileName = fileID.split(":;:"); 
	if (arrFileName.length>1) {
		File dir = new File(filestoreLocation + File.separator + arrFileName[0]);
		Common.RemoveDirectory(dir);
		item.put("isS", "Y");	
	}else{
		item.put("isS", "N");
	}
	out.write(item.toString());
}catch(Exception e){
	e.printStackTrace();
}
%>