<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="org.json.simple.*"%>
<%@ page import="java.sql.*"%>
<%
//如果沒有設定驗證filter，請自行加權限驗證，否則請勿使用本程式..

response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String q = Common.get(request.getParameter("q"));

try {
	java.util.Map<String, String> chkMap = new java.util.HashMap<String, String>();
	//chkMap:不良品缺陷代碼,是否需檢核上傳檔案
	java.util.List drg1001List = ServiceGetter.getInstance().getTcbwService().load("select dpdKind,isCheckFile from Drg1001Db ");
	if (drg1001List!=null && drg1001List.size()>0) 			
    {    	   
		for (int j=0; j<drg1001List.size(); j++)     	   
		{
			Object x[] = (Object[])drg1001List.get(j);		   
			chkMap.put(Common.get(x[0]),Common.get(x[1]));    	   
		}
    }
	boolean isCheckFile = false;	
	if(q != null && q.length()>0){				
		String[] subcode = q.split(",");		
		for(String rid : subcode){
			if("Y".equals(Common.get(chkMap.get(rid)))){
				isCheckFile = true;
				break;
			}
		}
	}	
	if (isCheckFile) {
		JSONObject item=new JSONObject();
		item.put("isCheck","Y");
		out.write(item.toString());
	}
} catch (Exception e) {
	e.printStackTrace();
}
%>