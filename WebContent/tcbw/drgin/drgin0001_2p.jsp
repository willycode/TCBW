<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
	try{	
		
		TableModelReportEnvironment env = new TableModelReportEnvironment();		
		javax.swing.table.DefaultTableModel model2 = obj.getTableModel2();	
		
		if(null == model2){			
			out.write("查無資料!");			
			return;		
		}	
		    
		java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();		
		env.setTableModel(model2);
		if("1".equals(obj.getPrintType())){		
			env.setJasperFile(this.getServletContext().getRealPath("/report/drg/DRGIN0001_2r.jasper"));
		}else if("2".equals(obj.getPrintType())){		
			env.setJasperFile(this.getServletContext().getRealPath("/report/drg/DRGIN0001_3r.jasper"));
		}else{
			env.setJasperFile(this.getServletContext().getRealPath("/report/drg/DRGIN0001_4r.jasper"));
		}
		env.setFormat(ReportEnvironment.VAL_FORMAT_XLS);	    
		TableModelReportGenerator generator = new TableModelReportGenerator(env);		
		out.clear();		
		generator.reportToHttpResponse(request, response, parms);		
		out = pageContext.pushBody();		

	}catch(Exception e){
		System.out.println(e.getMessage());	
		e.printStackTrace();
	}
%>

