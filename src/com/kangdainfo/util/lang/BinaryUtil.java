package com.kangdainfo.util.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Hibernate;

public class BinaryUtil {

	public static Blob toBlob(byte bytes[]) {
		if(bytes==null) return null;
		return Hibernate.createBlob(bytes);
	}
	public static Blob toBlob(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		Blob blob = Hibernate.createBlob(in);
		in.close();		
		return blob;
	}
	public static Blob toBlob(InputStream inputStream) throws IOException {
		return Hibernate.createBlob(inputStream);
	}
	
	public static byte[] toByteArray(Blob blob) throws SQLException {
		if(blob==null) return null;
		return blob.getBytes((long) 0, (int) blob.length());
	}
	
	
	
}
