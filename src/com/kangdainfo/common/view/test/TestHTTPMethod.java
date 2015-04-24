package com.kangdainfo.common.view.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import com.kangdainfo.common.util.Common;

public class TestHTTPMethod {

	public static void main(String[] args) {
		String urlString = "http://localhost:8080/pojo";
		try {
			String[] httpMethods = {"GET","HEAD","POST","PUT","DELETE","TRACE","OPTIONS"};			
			java.net.URL url = new java.net.URL(urlString);						
			for (String httpMethod : httpMethods) {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod(httpMethod);
				conn.setRequestProperty("Hello", "Kitty");

				java.util.Map<String, java.util.List<String>> rMap = conn.getRequestProperties();
				System.out.println("Request Properties:");
				if (rMap!=null && rMap.size()>0) {
					for (String idx : rMap.keySet()) {						
						System.out.println(idx + " = " + Common.arrayToString(rMap.get(idx).toArray(), ","));
					}
					System.out.println();
				}
				
				int reponseCode = conn.getResponseCode();
				
				System.out.println("Http Method Name : " + httpMethod);
				System.out.println("Status Code :" + reponseCode);				

				
				java.util.Map<String, java.util.List<String>> h = conn.getHeaderFields();
				System.out.println("Header Fields:");
				if (h!=null && h.size()>0) {
					for (String idx : h.keySet()) {						
						System.out.println(idx + " = " + Common.arrayToString(h.get(idx).toArray(), ","));
					}
					System.out.println();
				}			
				
				if (reponseCode<400) {
					StringBuilder sb = new StringBuilder(); 
		    		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
		    		String line = null; 
		    		while ((line = reader.readLine()) != null) sb.append(line).append("\n"); 
		    		reader.close();
					System.out.println("Body :");
					System.out.println(sb.toString());					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
