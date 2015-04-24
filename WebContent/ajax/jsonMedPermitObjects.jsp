<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="org.json.simple.*"%>
<%@ page import="java.sql.*"%>
<%
//如果沒有設定驗證filter，請自行加權限驗證，否則請勿使用本程式..

response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
String medPermit = Common.get(request.getParameter("medPermit"));
String medPermitNumber = Common.get(request.getParameter("medPermitNumber"));

try 
{

		JSONArray dsField = new JSONArray();
		
		Database db2 = new Database("MLMS");
		
		java.util.List<Object> parameter = new java.util.ArrayList<Object>();
		String hql=" select distinct a.CHNAME,a.ENNAME,a.APPUNNO,a.APPNAME,";
		       hql += " a.FACTNAME,b.DOCKNDID,b.DOCKNDMA,b.MSKNDID,b.MSKNDMA,a.FACTCIDMA ";
		       hql += " from VW_ForADR_TBMLIC a ,VW_ForADR_BIGKND2 b  ";
		       hql += " where a.LICEKID+a.LICID1=b.LICID ";
	           hql += " and a.LICEKID=? and a.LICID1=?";
	   	
	    parameter.add(medPermit);
	   	parameter.add(medPermitNumber);       
	    
		//ResultSet rs = db2.querySQLByScroll(hql);
		ResultSet rs = db2.querySQLByPrepareStatement(hql,parameter);
    	
		if (rs!=null) 
    	{
			String sql="select TOP 1 * from";
				   sql +="(";
				   sql +=" select a.applNo,a.medPermit,a.medPermitNumber,a.medMainCategoryCode,a.medMainCategory,a.medSecCategoryCode,a.medSecCategory,a.notifierRepDate";
				   sql +=" from MED0001_DB a union";
				   sql +=" select b.applNo,b.medPermit,b.medPermitNumber,b.medMainCategoryCode,b.medMainCategory,b.medSecCategoryCode,b.medSecCategory,b.notifierRepDate";
				   sql +=" from MED2001_DB b";
				   sql +=" ) as temp";
				   sql +=" where medPermit = " + Common.sqlChar(medPermit);
				   sql +=" and medPermitNumber = " + Common.sqlChar(medPermitNumber);
				   sql +=" and ISnull(applNo,'') <> ''";
				   sql +=" order by notifierRepDate desc";
				   System.out.println("Sql: " + sql);
		    java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(sql);
    		JSONObject item = new JSONObject();
    		
    		while (rs.next())
    		{ 			
    			item.put("obj0",Common.get(rs.getString("CHNAME")));
    			item.put("obj1",Common.get(rs.getString("ENNAME")));
    			item.put("obj2",Common.get(rs.getString("APPUNNO")));
    			item.put("obj3",Common.get(rs.getString("APPNAME")));
    			item.put("obj4",Common.get(rs.getString("FACTNAME")));
    			if(null!=list && list.size() > 0) {
    				Object instead[] = (Object[])list.get(0);
    				//System.out.println("TOP: " + instead[0]);
    				item.put("obj5",Common.get(instead[3]));	//醫材主類別代碼
        			item.put("obj6",Common.get(instead[4]));	//醫材主類別中文
        			item.put("obj8",Common.get(instead[5]));	//醫材次類別代碼
        			item.put("obj7",Common.get(instead[6]));	//醫材次類別中文
    				
    			} else {
    				item.put("obj5",Common.get(rs.getString("DOCKNDID")));
        			item.put("obj6",Common.get(rs.getString("DOCKNDMA")));
        			item.put("obj7",Common.get(rs.getString("MSKNDID")));
        			item.put("obj8",Common.get(rs.getString("MSKNDMA")));
    			}
    			
    			item.put("obj9",Common.get(rs.getString("FACTCIDMA")));			
    			dsField.add(item);	
    		}    		
    		if(item.isEmpty())
    		{
    			out.write("");
    		}
    		else
    		{
    			out.write(Common.get(item.toString()));
    		}
    	}

} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>