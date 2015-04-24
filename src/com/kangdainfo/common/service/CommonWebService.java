package com.kangdainfo.common.service;

/**
 * 這只是Web Service的Sample，上線時以下的function需移除或將安全性加強
 * @author shark
 *
 */
public interface CommonWebService {
	
	public String getRevData(String s1, String s2, String s3);
	
	/**
	 * 備援單機版取得資料 = List型態
	 * @param s1 = 帳號
	 * @param s2 = 密碼
	 * @param s3 = HQL
	 * @param s4 = Excludes
	 * @return
	 */	
	public String getWSRevData(String s1, String s2, String s3, String s4);
	
	public String getRevDataObject(String s1, String s2, String s3);
	
	/**
	 * 版取得資料 = Single Object型態
	 * @param s1 = 帳號
	 * @param s2 = 密碼
	 * @param s3 = HQL
	 * @param s4 = Excludes
	 * @return
	 */	
	public String getWSRevDataObject(String s1, String s2, String s3, String s4);
	
	/**
	 * 以路徑取得檔案內容
	 * @param s1 = 帳號
	 * @param s2 = 密碼
	 * @param s3 = eg. xxx:;:File1.txt
	 * @return
	 */
	public String getCommonFileContent(String s1, String s2, String s3);
	
}
