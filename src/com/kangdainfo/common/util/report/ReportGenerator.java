package com.kangdainfo.common.util.report;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFImageWriter;
import org.owasp.esapi.ESAPI;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.ZipUtil;
import com.kangdainfo.common.util.filestore.ContentTypeConfiguration;

import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public abstract class ReportGenerator
{

    public static final String IMAGE_SESSION_KEY = "ReportImageSession";
    public static final String PRINT_REQUEST_PARAMETER_KEY = "ReportPrintSession";    
    public static final String IMAGE_REQUEST_PARAMETER_KEY = "image_index";
    

    protected ReportEnvironment env;

    protected String format;

    protected String httpFileName;

    protected String jasperPrintSessionId;
    
    public String getJasperPrintSessionId() {
		return jasperPrintSessionId;
	}
	public void setJasperPrintSessionId(String jasperPrintSessionId) {
		this.jasperPrintSessionId = jasperPrintSessionId;
	}

	protected String htmlImagePattern;

    protected Long httpImageCacheTimeout;

    protected String jasperFile;
    protected java.util.List jasperFileList;
    protected java.util.List jasperParamList;

    public ReportGenerator(ReportEnvironment env) {
        this.env = env;
    }

    private Logger logger = Logger.getLogger(ReportGenerator.class);

    abstract protected JasperPrint getJasperPrint(java.util.Map parms) throws Exception;
    abstract protected java.util.List getJasperPrintList(java.util.Map parms) throws Exception;

    Hashtable exportParameters;

    private void loadEnvironment() {
        exportParameters = new Hashtable();        
        format = env.getFormat();
        httpFileName = env.getHttpFileName();
        htmlImagePattern = env.getHtmlImagePattern();
        httpImageCacheTimeout = env.getHttpImageCacheTimeout();
        jasperFile = env.getJasperFile();
        jasperFileList = env.getJasperFileList();
        jasperParamList = env.getJasperParamList();
        StringBuffer msg = new StringBuffer();
        msg.append("Environment[");
        msg.append("JasperFile:").append(jasperFile).append(",");
        msg.append("Format:").append(format).append(",");
        msg.append("httpFileName:").append(httpFileName).append(",");
        msg.append("httpImageCacheTimeout:").append(httpImageCacheTimeout).append(",");
        msg.append("htmlImagePattren:").append(htmlImagePattern).append(",");
        msg.append("]");
        logger.debug(msg.toString());
    }
    
    private String getResponseFileName(String filename) {
		String rptFile = ""; 
		if (jasperFileList!=null && jasperFileList.size()>0) {
			rptFile = (String) jasperFileList.get(0);
		} else {
			rptFile = jasperFile;
		}
		java.io.File f = new java.io.File(rptFile);
		int i = filename.lastIndexOf('.');
		int j = f.getName().indexOf('.');
		if (i>0 && i!=-1 && j>0 && j!=-1) {
			return f.getName().substring(0,j) + filename.substring(i).toLowerCase();				
		}
		return filename;
    }
    
    public java.io.File getDividePdf(File src) {
        try {
            // we create a reader for a certain document
            PdfReader reader = new PdfReader(src.getAbsolutePath());
            // we retrieve the total number of pages
            int n = reader.getNumberOfPages();                
			Rectangle pageSize = reader.getPageSize(1);
			Rectangle newSize = new Rectangle(pageSize.getWidth() / 2, pageSize.getHeight());    
			java.io.File newFile = new File(System.getProperty("java.io.tmpdir")+java.io.File.separator+Common.getVMID()+".pdf");
			//String newFileName = System.getProperty("java.io.tmpdir")+java.io.File.separator+Common.getVMID()+".pdf";    			
            Document d1 = new Document(newSize, 0, 0, 0, 0);
            PdfWriter out = PdfWriter.getInstance(d1, new FileOutputStream(newFile));
            d1.open();
            PdfContentByte sb = out.getDirectContent();
            int i = 0;   
            float offsetX, offsetY;
            PdfImportedPage page;
            while (i < n) {
            	d1.newPage();
            	i++;
				pageSize = reader.getPageSize(i);
				newSize = new Rectangle(pageSize.getWidth() / 2, pageSize.getHeight());                	
				d1.newPage();
				offsetX = 0;
				offsetY = 0;
				page = out.getImportedPage(reader, i);
				sb.addTemplate(page, 1, 0, 0, 1, offsetX, offsetY);
				d1.newPage();
				offsetX = -newSize.getWidth();
				offsetY = 0;
				page = out.getImportedPage(reader, i);
				sb.addTemplate(page, 1, 0, 0, 1, offsetX, offsetY);               
            }
            d1.close();                
            return newFile;
        }
        catch (Exception de) {
            de.printStackTrace();
        }
        return src;
    }
    
    private synchronized java.io.File writeImageToFolder(File srcPdf, String imageFormat) throws Exception{
        PDDocument document = PDDocument.load( srcPdf );	
        java.io.File tempDirectory = new java.io.File(System.getProperty("java.io.tmpdir"));
		String tempDirID = Common.getTimeBasedUniqueId();
		tempDirectory = new java.io.File(tempDirectory,tempDirID);			
		tempDirectory.mkdirs();	
        PDFImageWriter imageWriter = new PDFImageWriter();
        imageWriter.writeImage(document, "JPG", null, 1, Integer.MAX_VALUE, tempDirectory.getAbsolutePath()+File.separator+"IMG_", BufferedImage.TYPE_INT_RGB, 96);
        document.close();
        
        if (!imageFormat.equals(ReportEnvironment.VAL_FORMAT_JPG)) {
        	File[] jpgFiles = tempDirectory.listFiles();
        	if (jpgFiles!=null && jpgFiles.length>0) {
        		for (File f : jpgFiles) {
        			f.renameTo(new File(tempDirectory, f.getName().substring(0,f.getName().lastIndexOf('.'))+"."+imageFormat));
        		}
        	}
        }
        return tempDirectory;
    }
    
    public java.io.File transPDFToImage(File srcPdf, String imageFormat) {
    	java.io.File dir = null;
		try {
			dir = writeImageToFolder(srcPdf, imageFormat);
	    	if (dir!=null && dir.isDirectory()) {
	        	ZipUtil zip = new ZipUtil();
	        	java.io.File f = new File(Common.getTempDirectory(), Common.getTimeBasedUniqueId()+".zip");
	        	zip.zipFile(dir,f);    			        	
	        	Common.RemoveDirectory(dir);
	        	return f;
	    	}			
		} catch (Exception e) {			
			e.printStackTrace();
		}
    	return srcPdf;    	
    	/**
    	
        PDDocument document = null;
        try {
            document = PDDocument.load( srcPdf );			
            java.io.File tempDirectory = new java.io.File(System.getProperty("java.io.tmpdir"));
			String tempDirID = Common.getVMID();
			tempDirectory = new java.io.File(tempDirectory,tempDirID);			
			tempDirectory.mkdirs();	
			
            PDFImageWriter imageWriter = new PDFImageWriter();
            boolean success = imageWriter.writeImage(document, "jpg", null, 1, Integer.MAX_VALUE, tempDirectory.getAbsolutePath()+File.separator+"IMG_", BufferedImage.TYPE_INT_RGB, 96);
            if (success) {
            	ZipUtil zip = new ZipUtil();
            	java.io.File f = new File(System.getProperty("java.io.tmpdir")+File.separator+Common.getVMID()+".zip");
            	zip.zipFile(tempDirectory,f);
            	//Common.RemoveDirectory(tempDirectory);
            	return f;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if( document != null )
				try {
					document.close();
				} catch (IOException e) {
					e.printStackTrace();
				}            
        }
        return srcPdf;
        **/
    }
    
    
    public void reportToFile(File output, HashMap parms) throws Exception {
        loadEnvironment();
        logger.debug("Output File:" + output.getName());
        FileOutputStream fos = new FileOutputStream(output);
        try {
            if (ReportEnvironment.VAL_FORMAT_HTML.equals(format)) {

                if (htmlImagePattern != null) {
                    exportParameters.put(JRHtmlExporterParameter.IMAGES_DIR, new File(output.getParent(),
                            htmlImagePattern));
                    exportParameters.put(JRHtmlExporterParameter.IMAGES_URI, htmlImagePattern + File.separator);
                    exportParameters.put(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
                }
            }    
    		if (ReportEnvironment.VAL_FORMAT_XLS.equals(format)) parms.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);		
    		
            if (jasperFileList!=null && jasperFileList.size()>0) {
                java.util.List printer = getJasperPrintList(parms);
                doExport(fos, printer);            	
            } else {
                JasperPrint printer = getJasperPrint(parms);
                doExport(fos, printer);            	
            }
	        if (ReportEnvironment.VAL_FORMAT_JPG.equals(format)) {
	        	output = transPDFToImage(output, ReportEnvironment.VAL_FORMAT_JPG);
	        }
	        if (ReportEnvironment.VAL_FORMAT_TIF.equals(format)) {
	        	output = transPDFToImage(output, ReportEnvironment.VAL_FORMAT_TIF);
	        }	        
        } catch (Exception x) {
            logger.error(x.getMessage(), x);
            throw x;
        } finally {
            try {
                fos.close();
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public void reportToHttpResponse(HttpServletRequest request, HttpServletResponse response, HashMap parms) throws Exception {
    	loadEnvironment();
		try {
			if (ReportEnvironment.VAL_FORMAT_VIEWER.equals(format)) {
				Object printer = null;
	            if (jasperFileList!=null && jasperFileList.size()>0) {
	                printer = getJasperPrintList(parms);	                     	
	            } else {
	                printer = getJasperPrint(parms);
	            }				
	            if (printer!=null) {
	            	jasperPrintSessionId = Common.getDigestString(Common.getVMID(),"MD5");      
	            	request.getSession().setAttribute(jasperPrintSessionId, printer);
	            	String path = request.getContextPath();
	            	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/report/viewer/appletviewer.jsp?"+PRINT_REQUEST_PARAMETER_KEY+"="+jasperPrintSessionId;
	            	
	            	//response.sendRedirect(basePath);
	            	ESAPI.httpUtilities().sendRedirect(basePath);
	            }				
			} else {
			    HttpSession session = request.getSession();
			    ImageCacheHandler handler = (ImageCacheHandler) session.getAttribute(IMAGE_SESSION_KEY);
			    if (handler != null) {
			        handler.clearNow();
			        session.removeAttribute(IMAGE_SESSION_KEY);
			    }
			    String filename = httpFileName;
			    if (filename == null) filename = Common.getVMID();   
			    HashMap imageMap = null;		
			    if (ReportEnvironment.VAL_FORMAT_HTML.equals(format)) {
			        filename = filename + ".html";	
			        response.setContentType("text/html");		        
			        if (htmlImagePattern != null) {
			            imageMap = new HashMap();
			            exportParameters.put(JRHtmlExporterParameter.IMAGES_MAP, imageMap);
			            exportParameters.put(JRHtmlExporterParameter.IMAGES_URI, htmlImagePattern + "?"
			                    + IMAGE_REQUEST_PARAMETER_KEY + "=");
			            
		                if (httpImageCacheTimeout != null) {
		                    handler = new ImageCacheHandler(imageMap, httpImageCacheTimeout.longValue());
		                } else {
		                    handler = new ImageCacheHandler(imageMap);
		                }
		                session.setAttribute(IMAGE_SESSION_KEY, handler);		            
			        }		        
			    } else if (ReportEnvironment.VAL_FORMAT_PDF.equals(format)) {
			        filename = filename + ".pdf";		
			    } else if (ReportEnvironment.VAL_FORMAT_XLS.equals(format)) {
			    	parms.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
			        filename = filename + ".xls";
	            } else if (ReportEnvironment.VAL_FORMAT_RTF.equals(format)) {
	            	filename = filename + ".rtf";
	            	//ct = "application/rtf";	
			    } else if (ReportEnvironment.VAL_FORMAT_CSV.equals(format)) {
			        filename = filename + ".csv";
			    } else if (ReportEnvironment.VAL_FORMAT_JPG.equals(format)) {
	            	filename = filename + ".pdf";
			    } else if (ReportEnvironment.VAL_FORMAT_TIF.equals(format)) {
			    	filename = filename + ".pdf";
			    } else {
			        throw new IllegalArgumentException("Doesn't support format:" + format);
			    }	        
			    java.io.File outputFile = new java.io.File(System.getProperty("java.io.tmpdir")+java.io.File.separator+filename);			    
		        FileOutputStream fos = new FileOutputStream(outputFile);	        
		        try {
		            if (jasperFileList!=null && jasperFileList.size()>0) {
		                java.util.List printer = getJasperPrintList(parms);
		                doExport(fos, printer);
		            } else {
		                JasperPrint printer = getJasperPrint(parms);
		                doExport(fos, printer);            	
		            }
		        } catch (Exception x) {
		            logger.error(x.getMessage(), x);
		            throw x;
		        } finally {
		            try {
		                fos.close();
		            } catch (Throwable e) {
		                logger.error(e.getMessage(), e);
		            }
		        }	        
		        
		        if (ReportEnvironment.VAL_FORMAT_JPG.equals(format) || ReportEnvironment.VAL_FORMAT_TIF.equals(format)) {		        	
		        	outputFile = transPDFToImage(outputFile, format);
		        	filename = outputFile.getName();
		        }		        	  
		        int len = new Long(outputFile.length()).intValue();
		        
		        if (ReportEnvironment.VAL_FORMAT_HTML.equals(format)) {
		        	response.setHeader("Content-disposition", "inline; filename=" + filename);
		        } else {	        	
			        String ct = ContentTypeConfiguration.getContentType(outputFile.getName());
			        if(ct!=null){
			            response.setContentType(ct);
			        }		        	
		        	response.setHeader("Cache-control","");
				    response.setContentLength(len);	        
				    response.setHeader("Content-Disposition", "attachment; filename=\"" + getResponseFileName(filename) + "\"");	        	
		        }			    
			    FileInputStream is = null;	      
			    OutputStream os = null;
			    try{
			    	byte b[] = new byte[len];
			    	if (outputFile.isFile()) {
			    		response.flushBuffer();
			    	    is = new FileInputStream(outputFile);
			    	    os = response.getOutputStream();
			    	    int read = 0;
			    	    while ((read=is.read(b)) != -1) {
			    	        os.write(b,0,read);
			    	    }
			    	}
			    }catch(Exception x){
			    	logger.error(x.getMessage(), x);
			    	x.printStackTrace();
			    	//try{
			    	//	response.sendError(500,"報表產製錯誤！");
			    	//}catch(Exception e){
			    	//	e.printStackTrace();
			    	//}
			    }finally{
			    	if (os!=null) os.close();
			        if (is!=null) is.close();
			        if (outputFile!=null && outputFile.exists()) outputFile.delete();
			    }				
			}			
		} catch (Exception x) {
		    logger.error(x.getMessage(), x);
		    throw x;
		}
		
	}  
    

    private void doExport(OutputStream output, JasperPrint printer) throws Exception {
        JRExporter exporter = getJRExporterAndFillParameter();
        /**
        BufferedImage bufferedImage = null;
        if (ReportEnvironment.VAL_FORMAT_HTML.equals(format)) {
            exporter = new JRHtmlExporter();
        } else if (ReportEnvironment.VAL_FORMAT_PDF.equals(format)) {
            exporter = new JRPdfExporter();
            exportParameters.put(JRPdfExporterParameter.FORCE_LINEBREAK_POLICY, Boolean.TRUE); 
            exportParameters.put(JRPdfExporterParameter.FORCE_SVG_SHAPES, Boolean.TRUE);
        } else if (ReportEnvironment.VAL_FORMAT_XLS.equals(format)) {
            //exporter = new JRXlsExporter();
            exporter = new JExcelApiExporter();
            exportParameters.put(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
            exportParameters.put(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.FALSE);  
            exportParameters.put(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,Boolean.TRUE);
            exportParameters.put(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
        } else if (ReportEnvironment.VAL_FORMAT_RTF.equals(format)) {
        	exporter = new JRRtfExporter();
        } else if (ReportEnvironment.VAL_FORMAT_JPG.equals(format)) {
            exporter = new JRPdfExporter();
            exportParameters.put(JRPdfExporterParameter.FORCE_LINEBREAK_POLICY, Boolean.TRUE); 
            exportParameters.put(JRPdfExporterParameter.FORCE_SVG_SHAPES, Boolean.TRUE);   	
        }else {
            throw new IllegalArgumentException("Doesn't support format:" + format);
        }        
        **/
        exportParameters.put(JRExporterParameter.OUTPUT_STREAM, output);
        exportParameters.put(JRExporterParameter.JASPER_PRINT, printer);
        Enumeration enu = exportParameters.keys();
        JRExporterParameter parm;
        Object value;
        while (enu.hasMoreElements()) {
            parm = (JRExporterParameter) enu.nextElement();
            value = exportParameters.get(parm);
            exporter.setParameter(parm, value);
        }
        exporter.exportReport();
        /**
        if (ReportEnvironment.VAL_FORMAT_JPG.equals(format) && bufferedImage!=null) {        	
        	ImageIO.write(bufferedImage, "JPEG", output);
        }
        **/
    }    
    
    private JRExporter getJRExporterAndFillParameter() throws Exception {
    	JRExporter exporter = null;
        if (ReportEnvironment.VAL_FORMAT_HTML.equals(format)) {
            exporter = new JRHtmlExporter();
        } else if (ReportEnvironment.VAL_FORMAT_PDF.equals(format)) {
            exporter = new JRPdfExporter();
            exportParameters.put(JRPdfExporterParameter.FORCE_LINEBREAK_POLICY, Boolean.TRUE); 
            exportParameters.put(JRPdfExporterParameter.FORCE_SVG_SHAPES, Boolean.TRUE);
        } else if (ReportEnvironment.VAL_FORMAT_XLS.equals(format)) {
            //exporter = new JRXlsExporter();
            exporter = new JExcelApiExporter();          
            exportParameters.put(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
            exportParameters.put(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.FALSE);  
            exportParameters.put(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,Boolean.TRUE);
            exportParameters.put(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
            exportParameters.put(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
        } else if (ReportEnvironment.VAL_FORMAT_RTF.equals(format)) {
        	exporter = new JRRtfExporter();
        } else if (ReportEnvironment.VAL_FORMAT_CSV.equals(format)) {
        	exporter = new JRCsvExporter();        	
        } else if (ReportEnvironment.VAL_FORMAT_JPG.equals(format) || ReportEnvironment.VAL_FORMAT_TIF.equals(format)) {
            exporter = new JRPdfExporter();
            exportParameters.put(JRPdfExporterParameter.FORCE_LINEBREAK_POLICY, Boolean.TRUE); 
            exportParameters.put(JRPdfExporterParameter.FORCE_SVG_SHAPES, Boolean.TRUE);
            /** 報表很多頁時錯誤
        	exporter = new JRGraphics2DExporter();        	
        	bufferedImage = new BufferedImage(printer.getPageWidth()*2, printer.getPageHeight()*2, BufferedImage.TYPE_INT_RGB);        	  
        	Graphics2D g = (Graphics2D) bufferedImage.getGraphics();      
        	exportParameters.put(JRGraphics2DExporterParameter.GRAPHICS_2D, g);   
        	exportParameters.put(JRGraphics2DExporterParameter.ZOOM_RATIO, Float.valueOf(2));
        	**/
        }else {
            throw new IllegalArgumentException("Doesn't support format:" + format);
        }
        return exporter;
    }
    
    private void doExport(OutputStream output, java.util.List printer) throws Exception {
        JRExporter exporter = getJRExporterAndFillParameter();
        exportParameters.put(JRExporterParameter.JASPER_PRINT_LIST, printer);
        exportParameters.put(JRExporterParameter.OUTPUT_STREAM, output);        
        Enumeration enu = exportParameters.keys();
        JRExporterParameter parm;
        Object value;
        while (enu.hasMoreElements()) {
            parm = (JRExporterParameter) enu.nextElement();
            value = exportParameters.get(parm);
            exporter.setParameter(parm, value);
        }
        exporter.exportReport();      
    }    
    
}
