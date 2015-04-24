<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<%@ page import="com.kangdainfo.common.util.filestore.ContentTypeConfiguration" %>
<%@ page import="net.htmlparser.jericho.Source" %>
<%@ page import="java.util.List" %>
<%@ page import="net.htmlparser.jericho.Element" %>
<%@ page import="net.htmlparser.jericho.HTMLElementName" %>
<%@ page import="net.htmlparser.jericho.Segment"%>
<%@ page import="java.io.*"%>
<%@ page import="jxl.write.*"%>
<%@ page import="jxl.*"%>

<%
String excelValue=request.getParameter("excelValue");

Source source = null;
source = new Source(excelValue);
List<Element> tbs = source.getAllElements(HTMLElementName.TABLE); 

//判斷是否有table
if (tbs!=null && tbs.size()>=1) {
	File outputFile = new File(System.getProperty("java.io.tmpdir")+File.separator+"list"+ ".xls");
	WritableWorkbook workbook = Workbook.createWorkbook(outputFile);	
	WritableSheet sheet = workbook.createSheet("Sheet1", 0);
   
	WritableFont Font10 = new WritableFont(WritableFont.ARIAL,12);
	WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
	detailCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
	detailCenter.setAlignment(jxl.format.Alignment.CENTRE);
//	detailCenter.setWrap(true);
	
//	CellView cellView = new CellView();
//	cellView.setSize(100);
//	cellView.setAutosize(true); // 自動調整欄寬
	
	Segment ctx = (Segment) tbs.get(1).getContent();  
	List<Element> trs = ctx.getAllElements(HTMLElementName.TR);
	int maxJ = 0;
	if (trs!=null && trs.size()>=1) {
		// 取出最大欄位長度
		for (int i=0;i<trs.size();i++) {
			Segment trContent = trs.get(i).getContent();  
			List<Element> tds = trContent.getAllElements(HTMLElementName.TD);
			if((tds.size()-1) >= maxJ){
				maxJ = (tds.size()-1); 
			}
		}
		int[] tmpMaxLength = new int[maxJ];
		for (int i=0;i<trs.size();i++) {
			Segment trContent = trs.get(i).getContent();  
			List<Element> tds = trContent.getAllElements(HTMLElementName.TD); 
			String[] row = new String[tds.size()];
			if (tds!=null && tds.size()>=1) {	
				for(int j=0; j<tds.size()-1; j++){
	    			Element td = tds.get(j);
       			    row[j] = td.getContent().toString().replaceAll("<[^>]+>","").replaceAll("&nbsp;","");
       			    sheet.addCell(new Label(j, i, Common.get(row[j]), detailCenter));
       			    
					if(Common.get(row[j]).length() >= tmpMaxLength[j]){
						tmpMaxLength[j] = Common.get(row[j]).length(); 
					}
				}
	        }
		}	
		
		for(int i=0; i<tmpMaxLength.length; i++)
		{
			sheet.setColumnView(i, tmpMaxLength[i]*3);
		}
	}
	workbook.write();			
	workbook.close();
	

	
	OutputStream os = null;
	FileInputStream is = null;	        
	try
	{
		String ct = ContentTypeConfiguration.getContentType(outputFile.getName());
		if (ct != null) 
		{
           response.setContentType(ct);
		}		        	
		response.setHeader("Cache-control","");
		response.setContentLength((int)outputFile.length());	        
		response.setHeader("Content-Disposition", "attachment; filename=\"" + outputFile.getName() + "\"");	
		
		byte b[] = new byte[(int)outputFile.length()];
		if (outputFile.isFile()) 
		{
			response.flushBuffer();
			is = new FileInputStream(outputFile);
			os = response.getOutputStream();
			int read = 0;
			while ((read = is.read(b)) != -1)
			{
				os.write(b,0,read);
			}
		}
	}
	catch(Exception x)
	{
		try
		{
			response.sendError(500,"Template產製錯誤！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	finally
	{
		if (os!=null) os.close();
		if (is!=null) is.close();
		if (outputFile!=null && outputFile.exists()) outputFile.delete();
		out.clear();  
		out = pageContext.pushBody();
	}
	
	
}



%>


