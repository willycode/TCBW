<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="net.sf.json.util.*"%>
<%
String q = Common.get(request.getParameter("q"));
String q1 = Common.get(request.getParameter("q1"));

JSONObject json = new JSONObject();
if(!"".equals(q) && !"".equals(q1)){
	if("1".equals(q1)){
		Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where (isClose is null or isClose <> 'Y') " + 
														" and applNo = " + Common.sqlChar(q) + " order by id desc ");
		if(cos0009Db != null){
			if(!"".equals(Common.get(cos0009Db.getNotifyDate()))){
				if(Common.getInt(Datetime.getYYYMMDD()) - Common.getInt(cos0009Db.getNotifyDate()) > 3){
					json.put("obj0", "Y");
				}else{
					json.put("obj0", "N");
					json.put("obj1", Common.get(cos0009Db.getNotifyDate()));
				}
			}else{
				json.put("obj0", "Y");
			}
		}else{
			json.put("obj0", "Y");
		}
	}else{
		Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where (isClose is null or isClose <> 'Y') " +
														" and applNo = " + Common.sqlChar(q) + " order by id desc ");
		if(cos0010Db != null){
			if(!"".equals(Common.get(cos0010Db.getNotifyDate()))){
				if(Common.getInt(Datetime.getYYYMMDD()) - Common.getInt(cos0010Db.getNotifyDate()) > 3){
					json.put("obj0", "Y");
				}else{
					json.put("obj0", "N");
					json.put("obj1", Common.get(cos0010Db.getNotifyDate()));
				}
			}else{
				json.put("obj0", "Y");
			}
		}else{
			json.put("obj0", "Y");
		}
	}
	out.write(json.toString());
}
%>