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
		
		String hql=" select distinct a.CHNAME,a.ENNAME,a.GIDATE,a.EFDATE,a.APPNAME,a.APPUNNO,a.FACTNAME,a.FACTCIDMA,b.MEDCLASS,b.DOCKNDID,b.DOCKNDMA,b.MSKNDID,b.MSKNDMA,b.WAYSPEC,b.EFECT";
	           hql += " from VW_ForADR_TBMLIC a left join VW_ForADR_BIGKND2 b on a.LICEKID + a.LICID1 = b.LICID where 1=1 ";
               hql += " and a.LICEKID=? and a.LICID1=?";
	    parameter.add(permitKey);
	    parameter.add(permitNo);
	    ResultSet rs = db2.querySQLByPrepareStatement(hql,parameter);
    	if (rs!=null) {
    		JSONObject item = new JSONObject();
    		while (rs.next()){
    			//針對許可證核准日期與許可證有效日期先做格式處理：YYYMMDD
    			String gidate = rs.getString("GIDATE");
    			gidate = gidate.substring(0,10);
    			gidate = gidate.replace("-","");
    			gidate = Datetime.getRocDateFromYYYYMMDD(gidate);
    			
    			String efdate = rs.getString("EFDATE");
    			efdate = efdate.substring(0,10);
    			efdate = efdate.replace("-","");
    			efdate = Datetime.getRocDateFromYYYYMMDD(efdate);
    			
    			item.put("obj0",Common.get(rs.getString("CHNAME")));	//中文名稱
    			item.put("obj1",Common.get(rs.getString("ENNAME")));	//英文名稱
    			
    			item.put("obj2",gidate);	//許可證核准日期
    			
    			item.put("obj3",efdate);	//許可證有效日期
    			
    			item.put("obj4",Common.get(rs.getString("APPNAME"))+Common.get(rs.getString("APPUNNO")));	//許可證持有商
    			item.put("obj5",Common.get(rs.getString("FACTNAME")));	//製造廠
    			item.put("obj6",Common.get(rs.getString("FACTCIDMA")));	//製造廠國別
    			item.put("obj7",Common.get(rs.getString("MEDCLASS")));	//醫材級數
    			item.put("obj8",Common.get(rs.getString("DOCKNDID")));	//醫材主類別代碼
    			item.put("obj9",Common.get(rs.getString("DOCKNDMA")));	//醫材主類別
    			item.put("obj10",Common.get(rs.getString("MSKNDID")));	//醫材次類別代碼
    			item.put("obj11",Common.get(rs.getString("MSKNDMA")));	//醫材次類別
    			item.put("obj12",Common.get(rs.getString("WAYSPEC")));	//醫材型號
    			item.put("obj13",Common.get(rs.getString("EFECT")));	//醫材效能
    			item.put("obj14",Common.get(rs.getString("APPUNNO")));	//製造廠統編

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