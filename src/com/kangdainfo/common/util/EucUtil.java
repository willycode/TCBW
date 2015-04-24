package com.kangdainfo.common.util;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class EucUtil {
	
	private EucUtil() {
		 // avoid instantiation...
	}	
	   
	public static java.io.File eucToUTF8(java.io.File euc4File, java.io.File outputFile) throws Exception {
		java.io.InputStream is = new java.io.FileInputStream(euc4File);         
		OutputStream fos = new FileOutputStream(outputFile);		
		int ch;		
		while((ch=is.read())!=-1){
			if (ch==142) {
				byte[] eucByte = new byte[3];								
				is.read(eucByte);				
				byte CNS11643[] = new byte[]{eucByte[1],eucByte[2]};				
				fos.write(new String(new String(CNS11643,"CNS11643").getBytes(),"UTF-8").getBytes());
			} else {
				fos.write((byte)ch);
			}
		}
		fos.close();
		is.close();		
        return outputFile;
	}
	public static String eucToUTF8(java.io.File euc4File) throws Exception {
		java.io.InputStream is = new java.io.FileInputStream(euc4File);         
		StringBuilder sb = new StringBuilder();		
		int ch;		
		while((ch=is.read())!=-1){
			if (ch==142) {
				byte[] eucByte = new byte[3];								
				is.read(eucByte);			
				byte CNS11643[] = new byte[]{eucByte[1],eucByte[2]};				
				sb.append(new String(new String(CNS11643,"CNS11643").getBytes(),"UTF-8"));
			} else {
				sb.append(new String(new byte[]{(byte)ch}));
			}
		}
		is.close();
        return sb.toString();
	}	
	
	public static String eucToUTF8(byte[] euc4ByteArray) throws Exception {
		java.io.InputStream is = new java.io.ByteArrayInputStream(euc4ByteArray);         
		StringBuilder sb = new StringBuilder();
		int ch;		
		while((ch=is.read())!=-1){
			if (ch==142) {
				byte[] eucByte = new byte[3];								
				is.read(eucByte);			
				byte CNS11643[] = new byte[]{eucByte[1],eucByte[2]};
				sb.append(new String(new String(CNS11643,"CNS11643").getBytes(),"UTF-8"));
			} else {
				sb.append(new String(new byte[]{(byte)ch}));
			}
		}
		return sb.toString();               
	}	
	
}
