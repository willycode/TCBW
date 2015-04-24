package com.kangdainfo.common.util;

import java.io.File;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author shark
 *
 */
public class EncodeDES {
	
	private String DES  = "DESede/ECB/PKCS5Padding";
	private Cipher   enc;
	protected SecretKey mKey;

	public SecretKey getMKey() {return mKey;}

	public EncodeDES() {
		try {
			mKey = KeyGenerator.getInstance("DESede").generateKey();
			enc = Cipher.getInstance(DES);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}	
	
	public EncodeDES(SecretKey mKey) {
		try {
			this.mKey = mKey;
			enc = Cipher.getInstance(DES);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}		
	
	public byte[] encode(byte[] srcPlainByte) {
		try {
			enc.init(Cipher.ENCRYPT_MODE,   mKey);
			return enc.doFinal(srcPlainByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] decode(byte[] srcEncodeByte) {
		try {
			enc.init(Cipher.DECRYPT_MODE,   mKey);
			return enc.doFinal(srcEncodeByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static java.io.File getEncodeFile(String secretKey, String src) throws Exception {		
		if (secretKey!=null && src!=null) {
			SecretKeySpec privateKey = new SecretKeySpec(secretKey.getBytes(), "TripleDES");			
			EncodeDES obj = new EncodeDES(privateKey);
			byte[] srcEncodeByte = obj.encode(src.getBytes("UTF-8"));			
			File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
	        java.io.File f = new File(tempDirectory,Common.getVMID()+".xml");
			org.apache.commons.io.FileUtils.writeByteArrayToFile(f,  srcEncodeByte);				
			return f;
		}		
        return null;        
	}
	
	public static String getEncodeStr(String secretKey,String src) throws Exception{
		if(secretKey!=null && src!=null){
			SecretKeySpec privateKey = new SecretKeySpec(secretKey.getBytes(), "TripleDES");			
			EncodeDES obj = new EncodeDES(privateKey);
			byte[] srcEncodeByte = obj.encode(src.getBytes("UTF-8"));			
			return new String(srcEncodeByte);
		}
		return null;
	}
	
	public static java.io.File getDecodeFile(String secretKey, java.io.File srcFile, java.io.File destFile) throws Exception {		
		if (secretKey!=null && srcFile!=null && srcFile.isFile() && destFile!=null) {
			SecretKeySpec privateKey = new SecretKeySpec(secretKey.getBytes(), "TripleDES");			
			EncodeDES obj = new EncodeDES(privateKey);
			byte[] xxByte = org.apache.commons.io.FileUtils.readFileToByteArray(srcFile);
			org.apache.commons.io.FileUtils.writeStringToFile(destFile, new String(obj.decode(xxByte),"UTF-8"), "UTF-8");
			return destFile;
		}		
        return null;        
	}	
	
	public static String getDecodeStr(String secretKey, java.io.File srcFile) throws Exception{
		if(secretKey!=null && srcFile!=null && srcFile.isFile()){
			SecretKeySpec privateKey = new SecretKeySpec(secretKey.getBytes(), "TripleDES");			
			EncodeDES obj = new EncodeDES(privateKey);
			byte[] xxByte = org.apache.commons.io.FileUtils.readFileToByteArray(srcFile);
			return new String(obj.decode(xxByte),"UTF-8");
		}
		return null; 
	}
	
	public static void main(String[] args) throws Exception {
		//DESede = DES = TripleDES
		SecretKeySpec privateKey = new SecretKeySpec("/kangdainfo*423021357fas".getBytes(), "TripleDES");		
		//如果要每次產都要不一樣的Key,則Constructor用以下這個
		//EncodeDES obj = new EncodeDES();	
		EncodeDES obj = new EncodeDES(privateKey);
		String srcText =  "康大資訊,123456,01011000101010100,支付傳輸檔案加密字串";
		byte[] srcEncodeByte = obj.encode(srcText.getBytes());
		
		org.apache.commons.io.FileUtils.writeByteArrayToFile(new java.io.File("d:/temp/encDES.txt"),  srcEncodeByte);
		byte[] xxByte = org.apache.commons.io.FileUtils.readFileToByteArray(new java.io.File("d:/temp/encDES.txt"));
		
		System.out.println("原始字串："+srcText);
		System.out.println("加密字串："+Common.toHexString(srcEncodeByte));
		System.out.println("解密字串："+new String(obj.decode(xxByte)));  
	}	
}
