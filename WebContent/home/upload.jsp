<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="com.kangdainfo.common.util.filestore.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>
<%@ page import="com.oreilly.servlet.*" %>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@ include file="../home/head.jsp" %>
<html>
<head>
    <title>File_Chinese.jsp</title>
</head>
<body>
<%  
    try
    {
       //宣告上傳檔案名稱
       String FileName = null;
       // 宣告上傳檔案型態
       String ContentType = null;
       // 宣告敘述上傳檔案內容敘述
       String Description = null;

       // 宣告上傳檔案檔名所使用的編碼，預設值為 ISO-8859-1，
       // 若改為Big5或MS950則支援中文檔名  
       String enCoding = "UTF-8";

       int maxPostSize = 1024*1024*5; //5 M
       if (!"".equals(Common.get(application.getInitParameter("filestoreLimit"))))
       {
	      maxPostSize = Integer.parseInt(Common.get(application.getInitParameter("filestoreLimit")));
       }

       //?fileKind=MED&systemType=MED010001&uploadId=44"
       String fileKind = request.getParameter("fileKind");
       String systemType = request.getParameter("systemType");
       
       //建立資料夾
       //先執行資安檢測
       systemType = Common.isValidChildFilePath(systemType);
       File tempDirectory = new File(application.getInitParameter(fileKind));
       tempDirectory = new File(tempDirectory,systemType);                
       tempDirectory.mkdirs();
       
       //建立亂碼資料夾
       String uploadCaseID = new java.rmi.dgc.VMID().toString();
       uploadCaseID = uploadCaseID.replaceAll(":","_");
       
       tempDirectory = new File(tempDirectory,uploadCaseID);                
       tempDirectory.mkdirs();  

	  //檔案上傳路徑
	  String saveDirectory = application.getInitParameter(fileKind)+"/"+systemType+"/"+uploadCaseID;
	
	  MultipartRequest multi= new MultipartRequest(request, saveDirectory, maxPostSize, enCoding); 
	
	  String uploadId =request.getParameter("uploadId");
	  String dbName =request.getParameter("dbName");
	  
	  //取得所有上傳之檔案輸入型態名稱及敘述
	  Enumeration filesname = multi.getFileNames();
	  Enumeration filesdc = multi.getParameterNames(); 
	  
	  java.util.List<Con0001Db> saveCon0001DbList = new java.util.ArrayList<Con0001Db>();
	
	  while (filesname.hasMoreElements())
	  {
	
		String name = (String)filesname.nextElement();
	    String dc = (String)filesdc.nextElement();
	     
	    FileName = multi.getFilesystemName(name);  
	     
	    ContentType = multi.getContentType(name);
	     
	    Description = multi.getParameter(name);
		  
	    if (FileName != null) 
	    { 
			Con0001Db con0001 = new Con0001Db();
			con0001.setFileKind(fileKind);
			con0001.setSystemType(systemType);
			con0001.setUpLoadId(Long.valueOf(uploadId));
			con0001.setFileRoute(systemType+":;:"+uploadCaseID);
			con0001.setFileName(FileName);
			con0001.setFileExplan(Description);//檔案說明
			con0001.setDbName(dbName);
			con0001.setCreator(User.getUserId());
			con0001.setIsInsert("Y");
			con0001.setCreateDate(Datetime.getYYYMMDD());
			con0001.setCreateTime(Datetime.getHHMMSS());
			saveCon0001DbList.add(con0001);
	    }     
	 }
	
	 if(saveCon0001DbList.size()>0)
	    ServiceGetter.getInstance().getTcbwService().saveBatch(saveCon0001DbList);
    }
    catch(Exception e)
	{
    	e.printStackTrace();
	}
%> 


</body>
<script type="text/javascript">
	var url="popManyUploadSimple.jsp?doClose=Y";
	window.location=url;

</script>
</html>
