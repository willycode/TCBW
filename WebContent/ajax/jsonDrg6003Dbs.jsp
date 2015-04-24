<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="org.json.simple.*"%>
<%
//如果沒有設定驗證filter，請自行加權限驗證，否則請勿使用本程式..

response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
Long q = Common.getLong(request.getParameter("q"));
try {
	if (!"".equals(q) && q > 0) {
		JSONArray dsField = new JSONArray();
		java.util.List<Drg6003Db> list = ServiceGetter.getInstance().getCommonService().load(" from Drg6003Db where drg6001Db.id = " + q +" order by id ");
		if (list!=null && list.size()>0) {
			for(Drg6003Db drg63:list){
				JSONObject item=new JSONObject();
				item.put("id",Common.get(drg63.getId()));
				item.put("medType",Common.get(drg63.getMedType()));
				item.put("permitKey",Common.get(drg63.getPermitKey()));
				item.put("permitNo",Common.get(drg63.getPermitNo()));
				item.put("scientificName",Common.get(drg63.getScientificName()));
				item.put("productName",Common.get(drg63.getProductName()));
				item.put("applicationName",Common.get(drg63.getApplicationName()));
				item.put("manufactorID",Common.get(drg63.getManufactorID()));
				item.put("manufactorName",Common.get(drg63.getManufactorName()));
				item.put("manufactorNo",Common.get(drg63.getManufactorNo()));
				item.put("content",Common.get(drg63.getContent()));
				item.put("medModel",Common.get(drg63.getMedModel()));
				item.put("medPath",Common.get(drg63.getMedPath()));
				item.put("dosage",Common.get(drg63.getDosage()));
				item.put("frequency",Common.get(drg63.getFrequency()));
				item.put("startDare",Common.get(drg63.getStartDare()));
				item.put("endDate",Common.get(drg63.getEndDate()));
				item.put("indication",Common.get(drg63.getIndication()));
				item.put("medModelOther",Common.get(drg63.getMedModelOther()));
				item.put("medPathOther",Common.get(drg63.getMedPathOther()));
				item.put("frequencyOther",Common.get(drg63.getFrequencyOther()));
				dsField.add(item);		
			}
			out.write(dsField.toString());
		}
	}
} catch (Exception e) {
	e.printStackTrace();
}
%>


