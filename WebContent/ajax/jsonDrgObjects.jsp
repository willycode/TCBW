<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="org.json.simple.*"%>
<%@ page import="java.sql.*"%>
<%
//如果沒有設定驗證filter，請自行加權限驗證，否則請勿使用本程式..

response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
String permitKey = Common.get(request.getParameter("permitKey"));
String permitNo = Common.get(request.getParameter("permitNo"));

try {
	
		JSONArray dsField = new JSONArray();
		Database db2 = new Database("MLMS");
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		
		String hql=" select distinct a.CHNAME,a.ENNAME,a.APPNAME,a.FACTNAME,a.APPUNNO,a.FACTCIDMA,a.INGRMA,a.APPADDR,a.FACTADDR ";
	           hql += " from VW_ForADR_TBMLIC a where 1=1 ";
               hql += " and a.LICEKID=? and a.LICID1=?";
	    parameter.add(permitKey);
	    parameter.add(permitNo);
	    ResultSet rs = db2.querySQLByPrepareStatement(hql,parameter);
    	if (rs!=null) {
    		JSONObject item = new JSONObject();
    		while (rs.next()){ 			
    			item.put("obj0",Common.get(rs.getString("CHNAME")));    //中文品名
    			item.put("obj1",Common.get(rs.getString("ENNAME")).replace("'","`"));    //英文品名
    			item.put("obj2",Common.get(rs.getString("APPNAME")));   //申請商名稱
    			item.put("obj3",Common.get(rs.getString("FACTNAME")));  //製造廠
    			item.put("obj4",Common.get(rs.getString("APPUNNO")));   //申請商統編
    			item.put("obj5",Common.get(rs.getString("FACTCIDMA"))); //製造廠國別
    			item.put("obj6",Common.get(rs.getString("INGRMA")));    //主成分    			
    			item.put("obj7",Common.get(rs.getString("APPADDR")));   //申請商地址
    			item.put("obj8",Common.get(rs.getString("FACTADDR")));  //製造廠地址
    			
    			dsField.add(item);	
    		}    		
    		if(item.isEmpty()){
    			out.write("");
    		}else{
    			out.write(Common.get(item.toString()));
    		}
    	}
	
} catch (Exception e) {
	e.printStackTrace();
}
%>