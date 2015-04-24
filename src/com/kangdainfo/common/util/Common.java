/*
*<br>程式目的：常用函數
*<br>程式代號：Common
*<br>撰寫日期：93/12/01
*<br>程式作者：KangDa Info
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*<br>
*/

package com.kangdainfo.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.ibm.icu.util.StringTokenizer;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.util.io.file.LargeFileReader;


public class Common {
	
	private Common() {
		 // avoid instantiation...
	}
	
  	/**
  	 * <br>
   	 * <br>目的：撰寫JavaBean get方法時所需套用的函數
   	 * <br>參數：資料字串
     * <br>傳回：檢查資料為null,是則傳回空字串
  	*/
	static public String get(String s){
		if(s==null)return "";
		else return checkSet(s.trim());		
	}
	static public String get(Object s){
		if(s==null) return "";
		else return checkSet(s.toString().trim());		
	}	
	static public String get(int s){
		return get(""+s);
	}
	static public Double get(Double d){
		if(d!=null) return d;
		else return 0d;		
	}
	
  	/**
  	 * <br>
  	 * <br>目的：撰寫JavaBean set方法時所需套用的函數
  	 * <br>參數：資料字串
  	 * <br>傳回：將資料前後空白去除
  	*/
	static public String set(String s){
		if(s==null){
			return "";
		}else{
			return s.trim();
		}
	}
	
	static public String checkSet(String s){
		if(s==null){
			return "";
		} else {
			s = s.replaceAll("(?i)<SCRIPT>","");
			s = s.replaceAll("(?i)ALERT","");
			s = s.replaceAll("&amp;","&");
			s = s.replaceAll("&lt;","<");
			s = s.replaceAll("&gt;",">");
			s = s.replaceAll("&quot;","\"");
			s = s.replaceAll("&#39;","'");
			return s.trim();
		}
	}	
	
	static public String checkGet(String s){
		if(s==null){
			return "";
		}else{
			s = s.trim();
			s = s.replaceAll("&","&amp;");
			s = s.replaceAll("<","&lt;");
			s = s.replaceAll(">","&gt;");
			s = s.replaceAll("\"","&quot;");
			s = s.replaceAll("'","&#39;");
			//s = org.apache.commons.lang.StringEscapeUtils.escapeHtml(s);
			return s;
		}
	}	
	
    /**
     * <br>
     * <br>目的：組合sql語法時字串需加單引號的函數 
     * <br>參數：資料字串 
     * <br>傳回：加上單引號傳回資料字串 
     */
    static public String sqlChar(String s) {
    	return sqlChar(s, false);
    }
    /**
     * 組合sql語法時字串需加單引號的函數 
     * @param s
     * @param isUpper = 是否要轉成大寫
     * @return
     */
    static public String sqlChar(String s, boolean isUpper) {
        if (s == null || s .equals("")) { return "''"; }
        return "'" + StringEscapeUtils.escapeSql(isUpper?s.trim().toUpperCase():s.trim()) + "'";
    }    
    
    /**
	 * LIKE SQL CHAR
	 */
	public static String likeSqlChar(String s){
		return likeSqlChar(s,false);
	}
	public static String likeSqlChar(String s, boolean isUpper){
		return Common.sqlChar("%"+s+"%",isUpper);
	}    
	public static String likeSqlCharEnd(String s, boolean isUpper){
		return Common.sqlChar(s+"%",isUpper);
	} 
    
    /**
     * 針對要比對的欄位做轉換
     * e.g. upper(trim(brand)) : sqlUpperAndEscapeChar("brand");
     * @param fieldName
     * @return
     */
    static public String sqlFieldUpperAndEscapeChar(String fieldName) {
    	return sqlFieldUpperAndEscapeChar(fieldName, null);
    }
    static public String sqlFieldUpperAndEscapeChar(String fieldName, String coalesceChar) {
    	//upper(coalesce(replace(trim(fieldName),'''',''),'*'))
    	if (coalesceChar!=null) {
    		return " upper(coalesce(trim("+fieldName+"),'" + coalesceChar + "'))";
    	} else {
    		return " upper(trim("+fieldName+"))";
    	}
    }    
    
    /**
     * 這個函式主要用在報表
     * <br>例如：某個欄位x可以顯示的寬度是20個字，但需要縮排5個字，其使用範例如下：
     * <br>alignString(x,20-5,"五個縮排使用的字元(不管半型或全型都算一個字)")
     * @param s
     * @param size
     * @param padStr
     * @return
     */
    static public String alignString(String s, int size, String padStr) {
    	s = get(s);
    	int len = s.length();
    	if (len>size) {    		
    		int loopLen = len/size;
    		if ((len % size) > 0 ) loopLen = loopLen + 1;
    		StringBuilder sb = new StringBuilder();    		
    		for (int i=0; i<loopLen; i++) {
    			sb.append(padStr).append(StringUtils.substring(s, i*size, (i+1)*size>len?len:(i+1)*size)).append("\n");
    		}
    		return sb.toString();
    	} else return get(padStr) + s;
    }        
    
    /**
     * 很多時候我們需要自動產生資料的key值,而用vmid是個不錯的選擇..
     * @return modifyVMID
     * @see java.rmi.dgc.VMID()
     */
    public static String getVMID() {
		return new java.rmi.dgc.VMID().toString().replaceAll(":","_");
    }
    public static String getTimeBasedUniqueId() {
    	return com.kangdainfo.util.uid.TimeBasedUniqueIdGenerator.getInstance().next().getTextValue();
    }    

    /**
     * <br>
     * <br>目的：組合sql語法時字串需加單引號的函數 
     * <br>參數：資料字串 
     * <br>傳回：加上單引號傳回資料字串 
     */
    static public String sqlInt(String s) {
        if (s == null) { return "'0'"; }
        return "'" + s.trim() + "'";
    }
    
  	/**
  	 * <br> 
  	 * <br>目的：將字串BIG5轉為ISO1
  	 * <br>參數：資料字串
  	 * <br>傳回：傳回ISO1字串
  	*/
    public static String big5ToIso(String s){
     	if (s==null){ s=""; }
    	try {
    		s=new String(s.getBytes("big5"),"ISO-8859-1");
    	} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   
	    return s; 
    }    
    public static String big5ToUTF8(String s){
     	if (s==null){ s=""; }
    	try {
    		s=new String(s.getBytes("big5"),"utf-8");
    	} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   
	    return s; 
    }            
    public static String utf8ToIso(String s){
     	if (s==null){ s=""; }
    	try {
    		s=new String(s.getBytes("utf-8"),"ISO-8859-1");
    	} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   
	    return s; 
    }    
    
  	/**
  	 * <br> 
  	 * <br>目的：將字串ISO1轉為BIG5
  	 * <br>參數：資料字串
  	 * <br>傳回：傳回BIG5字串
  	*/
    public static String isoToBig5(String s) {
     	if (s==null){ s=""; }
    	try {
			s=new String(s.getBytes("ISO-8859-1"),"big5");
    	} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   
	    return s;        
    }
    
    public static String isoToUTF8(String s) {
     	if (s==null){ s=""; }
    	try {
			s=new String(s.getBytes("ISO-8859-1"),"utf-8");
    	} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   
	    return s;        
    }    
    
    /**
     * Turn a bytearray into a printable form, representing
     * each byte in hex.
     *
     * @param data the bytearray to stringize
     * @return a hex-encoded printable representation of <code>data</code>
     */
    public static String toHexString(byte[] data) {
        StringBuffer sb = new StringBuffer(data.length * 2);
        for (int i = 0; i < data.length; ++i)
        {
            sb.append(Integer.toHexString((data[i] >> 4) & 15));
            sb.append(Integer.toHexString(data[i] & 15));
        }
        return sb.toString();
    }
    
    /**
     * Encode a string as UTF-8.
     *
     * @param str the string to encode
     * @return the UTF-8 representation of <code>str</code>
     */
    public static byte[] encodeUTF8(String str) {
        // It turns out that under 1.4.2, at least, calling getBytes() is
        // faster than rolling our own converter (it uses direct buffers and, I suspect,
        // a native helper -- and the cost of the encoding lookup is mitigated by a
        // ThreadLocal that caches the last lookup). So we just do things the simple way here.
        try
        {
            return str.getBytes("UTF-8");
        }
        catch (java.io.UnsupportedEncodingException e)
        {
            // Javadoc says that UTF-8 *must* be supported by all JVMs, so we don't try to be clever here.
            throw new RuntimeException("Unexpected exception: UTF-8 charset not supported: " + e);
        }
    }    
    
    
	/**
	*<br>目的：將字串前面補零傳回字串
	*<br>參數：(1)資料字串 (2)所需長度
	*<br>傳回：傳回字串
	*<br>範例：formatFrontZero("123",5)，傳回"00123"
	*/
	static public String formatFrontZero(String s,int len){
		return formatFrontString(s,len,'0');
	}
	
	/**
	*<br>目的：將字串後面補零傳回字串
	*<br>參數：(1)資料字串 (2)所需長度
	*<br>傳回：傳回字串
	*<br>範例：formatRearZero("123",5)，傳回"12300"
	*/
	static public String formatRearZero(String s,int len){
		return formatRearString(s,len,'0');
	}
	
	/**
	*<br>目的：將字串前面補自己想要加入的字元傳回字串
	*<br>參數：(1)資料字串 (2)所需長度 (3)欲加入的字元
	*<br>傳回：傳回字串
	*<br>範例：formatRearZero("123",5, "A")，傳回"AA123"
	*/
	static public String formatFrontString(String s,int len, char ch){
		StringBuilder sb = new StringBuilder().append("");
		int sLen=get(s).length();
		for(int i=0; i<(len-sLen) ; i++){
			sb.append(ch);
		}
		sb.append(get(s));
		return sb.toString();
	}	

	/**
	*<br>目的：將字串後面補自己想要加入的字元傳回字串
	*<br>參數：(1)資料字串 (2)所需長度 (3)欲加入的字元
	*<br>傳回：傳回字串
	*<br>範例：formatRearZero("123",5, "A")，傳回"123AA"
	*/	
	static public String formatRearString(String s,int len, char ch){
		StringBuilder sb = new StringBuilder().append("");
		int sLen=get(s).length();
		for(int i=0; i<(len-sLen) ; i++){
			sb.append(ch);
		}
		return get(s) + sb.toString();		
	}	
	
	/**
	 * 將HHMMSS時間轉成HH:MM:SS
	 * @param HHMMSS
	 * @return HH:MM:SS
	 */
	static public String formatHHMMSS(String s) {
		if (s!=null && s.length()==6) {
			return s.substring(0,2)+":"+s.substring(2,4)+":"+s.substring(4);
		}else if (s!=null && s.length()==4) {
			return s.substring(0,2)+":"+s.substring(2,4);
		}
		return get(s);
	}
	
	/**
	 * 將YYYMMDD日期轉化成比較易讀的格式
	 * 
	 * @param YYYMMDD
	 * @param format = 1, 2, 3, 4
	 * @return YYY.MM.DD, YYY年MM月DD日, 民國YYY年MM月DD日, YYY/MM/DD, YYY-MM-DD
	 */
	static public String formatYYYMMDD(String s, int format) {
		if (s!=null && s.length()==7) {
			try {
				switch (format) {
				case 1: return s.substring(0,3)+"."+s.substring(3,5)+"."+s.substring(5);
				case 2: return s.substring(0,3)+"年"+s.substring(3,5)+"月"+s.substring(5)+"日";
				case 3: return "民國"+s.substring(0,3)+"年"+s.substring(3,5)+"月"+s.substring(5)+"日";
				case 4: return s.substring(0,3)+"/"+s.substring(3,5)+"/"+s.substring(5);
				case 5: return s.substring(0,3)+"-"+s.substring(3,5)+"-"+s.substring(5);
				}
				return s.substring(0,3)+"."+s.substring(3,5)+"."+s.substring(5);				
			}catch(Exception e) {
				return s;
			}
		} else if (s!=null && s.length()==5) {
			try {
				switch (format) {
				case 1: return s.substring(0,3)+"."+s.substring(3,5);
				case 2: return s.substring(0,3)+"年"+s.substring(3,5)+"月";
				case 3: return "民國"+s.substring(0,3)+"年"+s.substring(3,5);
				case 4: return s.substring(0,3)+"/"+s.substring(3,5);
				case 5: return s.substring(0,3)+"-"+s.substring(3,5);
				}
				return s.substring(0,3)+"."+s.substring(3,5);				
			}catch(Exception e) {
				return s;
			}			
		}
		return get(s);
	}
	public static String formatDateTime(String yyyMMddHHMMSS, int format) {
		if (yyyMMddHHMMSS!=null && yyyMMddHHMMSS.length()>7) {
			return formatYYYMMDD(yyyMMddHHMMSS.substring(0,7),format) + " " + formatHHMMSS(yyyMMddHHMMSS.substring(7));
		} else {
			return formatYYYMMDD(yyyMMddHHMMSS,format);
		}
	}	
	
	/**
	 * 將YYYMMDD日期轉化成YYY.MM.DD
	 * 
	 * @param YYYMMDD
	 * @return YYY.MM.DD
	 */	
	static public String formatYYYMMDD(String s) {
		return formatYYYMMDD(s,99);
	}

  
  	/**
  	 * <br> 
  	 * <br>目的：將路徑檔名改為jasper可以解讀的格式, 例:"d:\test\test.jasper", 傳回"d:\\test\\test.jasper"
  	 * <br>參數：資料字串
  	 * <br>傳回：傳回轉換後字串
  	*/
    public static String getJasperPath(String s) {
    	StringBuffer sReturn = new StringBuffer();
    	int start=0;
    	for(int i=0; i<s.length(); i++){
    		if ("\\".equals(s.substring(i,i+1))){
    			sReturn.append(s.substring(start,i) +"\\");
    			start=i;
    		}
    	}
    	sReturn.append(s.substring(start,s.length()));
    	return sReturn.toString();     
    }    
    
    /**
     * 主要用於前端的網頁畫面顯示<br>
     * LG-T的架構，所有前端網頁的bean其getter和setter已經把<,>的符號轉成& l t ;和& g t ;<br>
     * 若要在前端顯示HTML語法內容則需要再將它還原，其用法如下：<br>
     * FormatStr(targetField,"Y")
     * 
     * @param sString : 要顯示的文字內容
     * @param sHTML　：　是否為HTML格式
     * @return
     */
    static public String FormatStr(String sString, String sHTML) {
      	String sStr = "";
      	if (sHTML.equalsIgnoreCase("Y") || sHTML.equalsIgnoreCase("true") || sHTML.equalsIgnoreCase("1")) {
      		//sStr = StringEscapeUtils.escapeJavaScript(sString).replaceAll("<%", "&lt;%");
      		return StringEscapeUtils.unescapeHtml(sString).replaceAll("<%", "&lt;%");
    	} else {
    		sStr = StringEscapeUtils.escapeHtml(sString);
    		//sStr = Common.checkGet(sString);
    		//sStr = sString.replaceAll("<", "&lt;");
    		//sStr = sStr.replaceAll(">", "&gt;");
    		sStr = sStr.replaceAll("\r\n", "<br>");
    		sStr = sStr.replaceAll("\n", "<br>");
    	}
    	return sStr;
    }
    
    static public String escapeJavaScript(String s) {
    	if (s!=null && !"".equals(s)) {
        	String[] arr = new String[]{"'","\"","\b","\n","\t","\f","\r","<>","<",">"};
        	for (int i=0; i<arr.length; i++) {
        		s = s.replaceAll(arr[i], "");
        	}
        	return StringEscapeUtils.escapeJavaScript(s);    		
    	}
    	return "";
    }      
    
    static public String escapeReturnChar(String s) {
    	return escapeReturnChar(s, false);
    }       
    static public String escapeReturnChar(String s, boolean enterOnly) {
    	if (s!=null && !"".equals(s)) {
    		String[] arr = null;
    		if (enterOnly) arr = new String[]{"\n","\r"};
    		else arr = new String[]{"'","\"","\b","\n","\t","\f","\r","<>","<",">"," "};    		   	
        	for (int i=0; i<arr.length; i++) {
        		s = s.replaceAll(arr[i], "");
        	}
        	return s;
    	}
    	return "";
    }     
    static public String escapeBlank(String s) {
    	if (s!=null && !"".equals(s)) {
        	String[] arr = new String[]{" ","　"};
        	for (int i=0; i<arr.length; i++) {
        		s = s.replaceAll(arr[i], "");
        	}
        	return s;    		
    	}
    	return "";
    }    
        
    /**
  	 * <br> 
  	 * <br>目的：將user所鍵入之password經MD5加密
  	 * <br>參數：已加密字串
  	 * <br>傳回：傳回轉換後字串
  	*/
    public static String getMD5PassWord(String srcText){
    	return getDigestString(srcText,"MD5");
    }    
    /**
     * 目的：將字串加密,若algorithm為null時,為SHA-1
     * @param srcText
     * @param algorithm : MD5, SHA-1, SHA-256, SHA-384 and SHA-512
     * @return
     */
    public static String getDigestString(String srcText, String algorithm){
    	try{
    		MessageDigest md = MessageDigest.getInstance(algorithm!=null?algorithm:"SHA-1");
    		md.update(srcText.getBytes());
    		MessageDigest theClone = (MessageDigest)md.clone();
    		byte[] digest = theClone.digest();
    		final StringBuffer sb = new StringBuffer();
    		for (int i = 0; i < digest.length; ++i){
    			final byte b = digest[i]; 
    			final int value = (b & 0x7F) + (b < 0 ? 128 : 0);
    			sb.append(value < 16 ? "0" : "");
    			sb.append(Integer.toHexString(value));
    		}
    		return sb.toString();
    	}catch(Exception ex){
    		ex.printStackTrace();
    		return "";
    	}
    }
    
    /**
     * 亂機產生數字+英文字每的字串
     * 
     * @param len = 要回傳的字串長度
     * @return
     */
    public static String getRandomPassword(int len) {
    	char[] pw = new char[len];
    	int c  = 'A';
    	int r1 = 0;
    	for (int i=0; i < len; i++) {
    		r1 = (int)(Math.random() * 3);
    		switch(r1) {
    		case 0: c = '0' +  (int)(Math.random() * 10); break;
    		case 1: c = 'a' +  (int)(Math.random() * 26); break;
    		case 2: c = 'A' +  (int)(Math.random() * 26); break;
    		}
    		pw[i] = (char)c;
    	}
    	return new String(pw);
    }
	public static String getRandomNum(int r1, int len) {
		char[] pw = new char[len];
		int c  = 'A';
		if (!(r1==0 || r1<3)) r1 = 0;
		for (int i=0; i < len; i++) {
			switch(r1) {
			case 0: c = '0' +  (int)(Math.random() * 10); break;
			case 1: c = 'a' +  (int)(Math.random() * 26); break;
			case 2: c = 'A' +  (int)(Math.random() * 26); break;
			}
			pw[i] = (char)c;
		}
		return new String(pw);
	}    
    
    /**
     * 將陣列轉成普通文字
     * 例如:String[]{"1","2"} 變成 1,2
     *  
     * @param strArray
     * @return
     */
    public static String arrayToString(Object[] strArray) {
    	return arrayToString(strArray, ",");
    }
    
    /**
     * 將陣列轉成普通文字
     * 例如:String[] x = new String[]{"1","2"}
     * arrayToString(x,",") 會得到 1,2
     * 
     * @param a
     * @param separator
     * @return
     */    
    public static String arrayToString(Object[] a, String separator) {
        StringBuilder sb = new StringBuilder().append("");
        if (a.length > 0) {
            sb.append(a[0]);
            for (int i=1; i<a.length; i++) {
                sb.append(separator);
                sb.append(a[i]);
            }
        }
        return sb.toString();
    }     

    public static boolean RemoveDirectory(File dir) {    	
        if (dir.isDirectory()) {
        	// if directory is root directory then throw exception
        	if(dir.getParentFile()==null) throw new IllegalArgumentException("Cann't remove root directory");
        	
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = RemoveDirectory(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }    
        // The directory is now empty so delete it
        return dir.delete();
    }
    
    /**
     * 取得暫存檔目錄
     * @return
     */
    public static File getTempDirectory() {
    	return new File(System.getProperty("java.io.tmpdir"));
    }
	
	public static String ReportMoneyFormat(Object d) {
		if (d==null) return "";
		String s = MoneyFormat(getNumeric(d),0);
		if (s.equals("0")) return "";
		else return s;
	}
	
	public static String ReportMoneyFormat(Double d, boolean withDecimal) {
		if (d==null) return "";
		String s = MoneyFormat(d,withDecimal);
		if (s.equals("0")) return "";
		else return s;
	}
	
	public static String ReportMoneyFormat(Object d, int p) {
		if (d==null) return "";
		String s = get(MoneyFormat(""+d,p));
		if (s.equals("0")) return "";
		else return s;
	}

    public static String MoneyFormat(Object money){
    	return MoneyFormat(getNumeric(money),0);
    }
    
    public static String MoneyFormat(Double money, boolean withDecimal){
    	if (money!=null) {
    		if (withDecimal) {
            	String s = MoneyFormat(money,5);
            	if (s.substring(s.lastIndexOf('.')).equals(".00000")) {
            		return s.substring(0,s.lastIndexOf('.'));
            	} else {        
            		//若0.00005 Double.parseDouble  回來後會變為 0.5E-4
            		//return s.substring(0,s.lastIndexOf('.')) + ("." + Double.parseDouble(s.substring(s.lastIndexOf('.')))).replaceAll("0\\.", "");
            		double pointAfterValue = Double.parseDouble(s.substring(s.lastIndexOf('.')));            		
                    DecimalFormat df = new DecimalFormat("###,###0.0#####");            		            		
            		return s.substring(0,s.lastIndexOf('.')) + ("." + df.format(pointAfterValue)).replaceAll("0\\.", "");
            	}   			
    		} else {
    			return MoneyFormat(money,0);
    		}
    	} else return "";
    }
    
    /**
     * 格式化金額
     * @param money
     * @param d
     * @param withCommas -> 若為false,將不會有千分號
     * @return
     */
    public static String MoneyFormat(Object money, int d, boolean withCommas){
        if(money!=null){
        	String strFormat = withCommas?"###,###,###,###,###,":"";
            DecimalFormat df = new DecimalFormat(strFormat+"###");
            if (d>0) {
            	df = new DecimalFormat(strFormat+"##0."+formatRearZero("",d));
            	return df.format(getNumeric(money));
            } else {
            	return df.format(Math.round(getNumeric(money)));
            }            
        } else {
        	if (d>0) return "0."+formatFrontZero("",d);
        	return "0";
        }
    }    
    public static String MoneyFormat(Double money, int d){
    	return MoneyFormat(money,d,true);
    }    
    public static String MoneyFormat(String money,int d){
    	return MoneyFormat(getNumeric(money),d);
    }  
    
    /**
     * 將半形數子轉成全形數字
     * <br> type = 1 (１,２..)
     * <br> type = 2 (一,二..)
     * <br> type = 3 (壹,貳..)
     * @param type
     * @param s
     * @return
     */
    static public String changeNumberType(int type, String s){
    	char[] num = {'1','2','3','4','5','6','7','8','9','0',','};
    	char[] zh1 = {'１','２','３','４','５','６','７','８','９','０','，'};
    	char[] zh2 = {'一','二','三','四','五','六','七','八','九','０','，'};
    	char[] zh3 = {'壹','貳','參','肆','伍','陸','柒','捌','玖','零','，'};
    	s = get(s);
    	StringBuilder sb = new StringBuilder();
		for(int i=0;i<s.length();i++){
			char ch=s.charAt(i);
			for (int j=0; j<num.length; j++) {
				if (num[j]==ch) {
			    	switch (type) {    	
			    	case 1:ch=zh1[j]; break;
			    	case 2:ch=zh2[j]; break;
			    	case 3:ch=zh3[j]; break;
			    	}
				}
			}
			sb.append(ch);
		}
		return sb.toString();
    }
    
    /**
     * 數字金額轉國字金額:可以的話請直接使用numToZh(double val)
     * @param BigDecimal
     */
	public static String numToZh(BigDecimal bigdMoneyNumber)
	{
		return numToZh(bigdMoneyNumber.doubleValue());
	}
	
    /**
     * 數字金額轉國字金額:可以的話請直接使用numToZh(double val)
     * @param String
     */
	public static String numToZh(String value){
		return numToZh(Double.parseDouble(value));
	}

  /**
   * 數字金額轉國字金額
   * @param val double 金額
   * @return String
   */
	public static String numToZh(double val) {
		String HanDigiStr[] = new String[] {"零", "壹", "貳", "參", "肆", "伍", "陸", "柒", "捌", "玖"};
	    String HanDiviStr[] = new String[] {
	        "", "拾", "佰", "仟", "萬", "拾", "佰", "仟", "億",
	        "拾", "佰", "仟", "萬", "拾", "佰", "仟", "億",
	        "拾", "佰", "仟", "萬", "拾", "佰", "仟"};
	    String SignStr = "";
	    String TailStr = "";
	    long fraction, integer;
	    int jiao, fen;
	    String RMBStr = "", NumStr = "";
	    boolean lastzero = false;
	    boolean hasvalue = false; // 億、萬進位元前有數值標記
	    int len, n;
	    //數字金額判斷
	    if (val < 0) {
	      val = -val;
	      SignStr = "負";
	    }
	    if (val > 99999999999999.999 || val < -99999999999999.999) {
	      return "數值位數過大!";
	    }
	    // 四捨五入到分
	    long temp = Math.round(val * 100);
	    integer = temp / 100;
	    fraction = temp % 100;
	    jiao = (int) fraction / 10;
	    fen = (int) fraction % 10;
	    
	    if (jiao == 0 && fen == 0) {
	      TailStr = "整";
	    } else {
	    	TailStr = HanDigiStr[jiao];
	    	if (jiao != 0) {
	    		TailStr += "角";
	    	}
	    	if (integer == 0 && jiao == 0) { // 零元後不寫零幾分
	    		TailStr = "";
	    	}
	    	if (fen != 0) {
	    		TailStr += HanDigiStr[fen] + "分";
	    	}
	    }
	    
	    //判斷小數點前字串
	    NumStr = String.valueOf(integer);
	    len = NumStr.length();
	    if (len > 15) {
	      return "數值過大!";
	    }
	    for (int i = len - 1; i >= 0; i--) {
	      if (NumStr.charAt(len - i - 1) == ' ') {
	        continue;
	      }
	      n = NumStr.charAt(len - i - 1) - '0';
	      if (n < 0 || n > 9) {
	        return "輸入含非數位字元!";
	      }
	      if (n != 0) {
	        if (lastzero) {
	          RMBStr += HanDigiStr[0]; // 若干零後若跟非零值，只顯示一個零
	          // 除了億萬前的零不帶到後面
	          //if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) )    // 如十進位前有零也不發壹音用此行
	        }
	        //if (! (n == 1 && (i % 4) == 1 && i == len - 1)) { // 十進位處於第一位不發壹音
	        if (! (n == 0 && (i % 4) == 1 && i == len - 1)) { // 十進位處於第一位不發壹音
	          RMBStr += HanDigiStr[n];
	        }
	        RMBStr += HanDiviStr[i]; // 非零值後加進位，個位為空
	        hasvalue = true; // 置萬進位元前有值標記

	      }
	      else {
	        if ( (i % 8) == 0 || ( (i % 8) == 4 && hasvalue)) { // 億萬之間必須有非零值方顯示萬
	          RMBStr += HanDiviStr[i]; // “億”或“萬”
	        }
	      }
	      if (i % 8 == 0) {
	        hasvalue = false; // 萬進位元前有值標記逢億重定
	      }
	      lastzero = (n == 0) && (i % 4 != 0);
	    }
	    if (RMBStr.length() == 0) {
	      RMBStr = HanDigiStr[0]; // 輸入空字元或"0"，返回"零"
	    }
	    return SignStr + RMBStr + "元" + TailStr;
	}	

    public static double getNumeric(Object s) {    	
    	if (s!=null) return getNumeric(s.toString());
    	else return 0;
    }    
	public static double getNumeric(String s){
		if (get(s).equals("")) return 0;
		try {
			if (Double.isNaN(Double.parseDouble(s))) return 0;
			else return Double.parseDouble(s.replaceAll(",", ""));
		} catch (NumberFormatException e) {
			return 0;
		}		
	}
	
	public static String getNumericString(String s){
		if (get(s).equals("")) return "0";
		try {
			if (Double.isNaN(Double.parseDouble(s))) return "0";
			else return s;
		} catch (NumberFormatException e) {
			return "0";
		}		
	}	
	
	public static int getInt(Object s){
		return getInteger(get(s)).intValue();
	}		
	public static int getInt(String s){
		return getInteger(s).intValue();
	}	
	public static Integer getInteger(String s){
		Double d = getNumeric(s);
		return d.intValue();
	}
	public static long getLong(Object s){
		Double d = getNumeric(s);
		return d.longValue();
	}	
    
    public static String getRemoteData(String urlString) { 
    	StringBuffer document = new StringBuffer(); 
    	try {     		
    		java.net.URI uri = new java.net.URI(urlString);    		
    		java.net.URL url = new java.net.URL(uri.toASCIIString()); 
    		java.net.URLConnection conn = url.openConnection();
    		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
    		String line = null; 
    		while ((line = reader.readLine()) != null) document.append(line + "\n"); 
    		reader.close();
    	} catch (Exception e) { 
    		System.out.println("IOException when connecting to URL: " + urlString); 
    	}
    	return document.toString(); 
    }
    
    public static void saveRemoteData(String urlString, java.io.File saveFile) throws Exception {     	
		java.io.FileOutputStream fos = new java.io.FileOutputStream(saveFile);
		java.net.URI uri = new java.net.URI(urlString);    		
		java.net.URL url = new java.net.URL(uri.toASCIIString()); 
		java.net.URLConnection conn = url.openConnection();
		java.io.InputStream is = conn.getInputStream();
		byte[] b = new byte[1];
		while(is.read(b)!=-1) fos.write(b);		
		is.close();
    }
    
    /**
     * 將String寫入到某個檔案裡
     * 
     * @param text
     * @param outputFile
     * @param encoding
     * @throws IOException
     */
    public static void writeStringToFile(String text, File outputFile, String encoding) throws IOException  {
    	FileUtils.writeStringToFile(outputFile, text, encoding);
    }
    
	public static String getReportImageCachePath() { return ServiceGetter.getInstance().getMyServletContext().getServletContext().getContextPath()+"/reportImage"; }
	public static String getReportFilePath() {	return ServiceGetter.getInstance().getMyServletContext().getServletContext().getContextPath()+"/report"; }
	public static String getDownloadFilePath(){ return ServiceGetter.getInstance().getMyServletContext().getServletContext().getContextPath()+"/downloadFileSimple"; }
		
	public static String getDownloadFilePathLink(String fileID, String cssClass, Integer imgNo) {
		if (get(fileID).equals("")) return "";
		else {			
			String[] arrFileName = fileID.split(":;:");  
        	if (arrFileName.length>1) {
    			StringBuilder sb = new StringBuilder();
    			sb.append("<a href='").append(get(ServiceGetter.getInstance().getMyServletContext().getServletContext().getContextPath()));
    			sb.append("/downloadFileSimple?fileID=").append(fileID).append("' target='_blank' ");
    			if (cssClass!=null) sb.append(" class='").append(cssClass).append("'");
    			sb.append(">");
    			if (imgNo!=null) {
    				sb.append("<img src=\"../../images/");
    				switch (imgNo) {
    				case 1: sb.append("question.gif"); break;
    				case 2: sb.append("page.gif"); break;
    				case 3: sb.append("rar.gif"); break;
    				case 4: sb.append("sm-edit.gif"); break;
    				default: sb.append("page.gif");
    				}
    				sb.append("\" border=\"0\">");
    			} else sb.append(arrFileName[1]).append("</a>");
    			return sb.toString();
        	}  				
			return "";
		}		
	}
	
	public static final String MSG_DTREE_LEVEL_LIMIT_FOR_ADD = "您好！　該項目之下不能再新增下一層級之項目！\\n\\n若確實有該需求，請洽業務承辦人員及系統開發廠商！\\n";
	public static final String MSG_DTREE_LEVEL_LIMIT_FOR_EDIT = "<div align=center><br>此為根目錄，不提供維護功能！</div>";	
	public static final String MSG_NoPermission = "<div align=center><br><br><font color=red>對不起! 你沒有足夠的權限執行此功作!!<br><br>若有問題請洽詢系統管理者或相關承辦人員!!</font></div><br><br>";	
	public static final String MSG_ID_NOT_FOUND = "查無代碼資料，請先執行年度代碼承轉作業！";	

	public static String getFileExtension(java.io.File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		if (i > 0 &&  i < s.length() - 1) {
			ext = s.substring(i+1).toLowerCase();
		}
		return ext;
	}  	

	public static String appendXmlNode(String nodeName, Object s) {
		StringBuilder sb = new StringBuilder();
		if (s!=null && !"".equals(Common.get(s))) {
			sb.append("<").append(nodeName).append("><![CDATA[").append(s).append("]]></").append(nodeName).append(">");
		} else {
			sb.append("<").append(nodeName).append("/>");			
		}
		return sb.toString();
	}	
	
    /**
     * <br>目的:取得以-為分隔號的字串某節字串
     * <br>eg. s=課稅收入-房屋稅 --> getItemName(s, 0) = 課稅收入
     * @param s
     * @param pos
     * @return
     */
    public static String getSplitName(String s, int pos) {
    	return getSplitName(s,"-",pos);    	
    }    
    /**
     * 目的:取得以separator為分隔號的字串某節字串
     * <br>eg. s=課稅收入-房屋稅 --> getSplitName(s, "-", 0) = 課稅收入
     * @param s
     * @param separator
     * @param pos
     * @return
     */
    public static String getSplitName(String s, String separator, int pos) {
    	String[] arr = StringUtils.split(Common.get(s),separator);
    	if (arr!=null && arr.length>pos) return arr[pos];
    	else return Common.get(s);    	
    }	
    
    /**
     * 將file取成jsonArray
     * @param file
     * @return
     */
	public static JSONArray fileToJsonArray(File file){
		if(null != file && file.exists())
		{
			byte[] b = null;
			try {
				//將檔案取出成Bytes
				b = FileUtils.readFileToByteArray(file);
				//回傳成jsonArray
				return JSONArray.fromObject(b);
			} catch (IOException e) {
				e.printStackTrace();
			}						
		}	
		return null;
	}
	
	/**
	 * 將file取成jsonArrayString
	 * @param file
	 * @return
	 */
	public static String fileToJsonArrayString(File file){
		JSONArray jsonArray = fileToJsonArray(file);
		if(null != jsonArray)
		{
			return jsonArray.toString();			
		}	
		return null;
	}
	
	/**
	 * 取得jsonArray To File
	 * @param jsonArrayString
	 * @param fileFullName
	 * @return
	 */
	public static File getFileByJsonArray(String jsonArrayString,String fileFullName){
		if(!"".equals(Common.get(jsonArrayString))){
			JSONArray json = JSONArray.fromObject(jsonArrayString);
			byte[] b = new byte[json.size()];
			for(int i=0;i<json.size();i++){
				b[i] = Byte.parseByte(json.getString(i));
			}					
		    java.io.File outputFile = new java.io.File(fileFullName);				    
		    try {
				FileUtils.writeByteArrayToFile(outputFile,b);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return outputFile;	
		}
		return null;
	}	
	
	public static java.util.List<String[]> getFileContents(java.io.File f, String encoding, int[] lenIdx) throws Exception {
		return getFileContents(f, encoding, lenIdx, false);
	}			
	/**
	 * 將文字檔依長度集合轉為List<String[]>模式，舉例:<br>
	 * java.io.File f = new java.io.File("D:\\TBGV\\05_工作區\\03_系統分析\\介接說明\\電子閘門\\電子閘門檔案\\A0502.txt");<br>
	 * int[] idx = {14,3,14,4,1,1,2,5,14,8,8,8,20,20,150,11,2,2,3,13,12,3,18,3,8,5,2,12,11,8,70,100,2,390};<br>
	 * java.util.List<String[]> list = TBGVCommon.getFileContents(f, "MS950", idx, true);
	 * 
	 * @param f = 來源檔
	 * @param encoding = 檔案編碼
	 * @param lenIdx = 長度集合
	 * @param useByte = 長度截取是否為byteArray模式
	 * @return
	 * @throws Exception
	 */
	public static java.util.List<String[]> getFileContents(java.io.File f, String encoding, int[] lenIdx, boolean useByte) throws Exception {
		if (f!=null && lenIdx!=null) {
			boolean _encode = false;
			LargeFileReader rs = null;
			if (encoding!=null && encoding.length()>0) {
				rs = new LargeFileReader(f,encoding);
				_encode = true;
			} else {
				rs = new LargeFileReader(f, "MS950");
			}
			if (rs!=null) {
				int j=0, len=0, maxLen=0;				
				byte[] dataByte = null;
				int b = 0, t = 0;
				for (j=0; j<lenIdx.length; j++) {
					if (j==(lenIdx.length-1)) {
						maxLen += lenIdx[j]>0?1:lenIdx[j];
					} else {
						maxLen += lenIdx[j];
					}
				}					
				java.util.List<String[]> rList = new java.util.ArrayList<String[]>();
				for (String s: rs) {					
					len = 0;								
					if (useByte) {
						dataByte = _encode?s.getBytes(encoding):s.getBytes();
						if (dataByte!=null && dataByte.length>=maxLen) {
							String[] r = new String[lenIdx.length];
							for (j=0; j<lenIdx.length; j++) {		
								byte[] rowByte = new byte[lenIdx[j]];
								t = 0;
								for (b=len; b<len+lenIdx[j]; b++) {		
									if (dataByte.length>b) rowByte[t] = dataByte[b];
									t++;
								}
								r[j] = _encode?new String(rowByte,encoding):new String(rowByte);					
								len += lenIdx[j];							
							}
							rList.add(r);
						}						
					} else {
						if (s!=null && s.length()>=maxLen) {
							String[] r = new String[lenIdx.length];
							for (j=0; j<lenIdx.length; j++) {
								if (j==(lenIdx.length-1)) {
									r[j] = s.substring(len);
								} else {
									r[j] = s.substring(len,len+lenIdx[j]);
								}						
								len += lenIdx[j];							
							}
							rList.add(r);
						}						
					}
				}
				return rList;
			}			
		}
		return null;
	}			
	
	public static String setCccCode(String cccCode) {
		if (cccCode!=null && Common.get(cccCode).length()>0) {
			return cccCode.replace(".", "").replace("-", "");
		} else {
			return Common.get(cccCode);
		}
	}
	
	public static String formatCccCode(String cccCode) {
		if (cccCode!=null && Common.get(cccCode).length()>0) {			
			String r = setCccCode(cccCode);
			if (r.length()>10 && r.length()<13) {
		  		return r.substring(0,4)+'.'+r.substring(4,6)+'.'+r.substring(6,8)+'.'+r.substring(8,10)+'.'+r.substring(10,11)+(r.length()==12?('-'+r.substring(11, 12)):"");
		  	} else if (r.length()==10) {
		  		return r.substring(0,4)+'.'+r.substring(4,6)+'.'+r.substring(6,8)+'.'+r.substring(8,10);
		  	} else if (r.length()==8) {
		  		return r.substring(0,4)+'.'+r.substring(4,6)+'.'+r.substring(6,8);
		  	} else if (r.length()==6) {
		  		return r.substring(0,4)+'.'+r.substring(4,6);
		  	}
		}
		return Common.get(cccCode);
	}
	
	
	/**
	 * 傳入cccCode取得檢查碼
	 * @param cccCode
	 * @return
	 */
	public static String getCccCodeWithCheckBit(String cccCode) {
		cccCode = setCccCode(cccCode);
		char[] cccChr = cccCode.toCharArray();		
		int a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
		int b2, b3, b4, b5, b6;
		int i;
		String tempStr = "";
		if (cccChr.length>=10) {		  
		    a1 = Common.getInt(cccChr[0]);
		    a2 = Common.getInt(cccChr[1]);
		    a3 = Common.getInt(cccChr[2]);
		    a4 = Common.getInt(cccChr[3]);
		    a5 = Common.getInt(cccChr[4]);
		    a6 = Common.getInt(cccChr[5]);
		    a7 = Common.getInt(cccChr[6]);
		    a8 = Common.getInt(cccChr[7]);
		    a9 = Common.getInt(cccChr[8]);
		    a10 = Common.getInt(cccChr[9]);
		    tempStr = String.valueOf((a1 * 10000 + a3 * 1000 + a5 * 100 + a7 * 10 + a9) * 2);		    
		    tempStr = Common.formatFrontZero(tempStr, 6);
		    char[] tempChr = tempStr.toCharArray();
		    b2 = Common.getInt(tempChr[1]);
		    b3 = Common.getInt(tempChr[2]);
		    b4 = Common.getInt(tempChr[3]);
		    b5 = Common.getInt(tempChr[4]);
		    b6 = Common.getInt(tempChr[5]);
		    i = (a2 + a4 + a6 + a8 + a10 + b2 + b3 + b4 + b5 + b6) % 10;
		    
		    String r = "";		    
		    if ( i == 0 ) r = "0";
		    else r = String.valueOf(10 - i);
		    
		    if (cccChr.length>=12) return cccCode.substring(0,10)+r+cccChr[11];
		    else return cccCode.substring(0,10)+r;		   
		}
		return cccCode;
	}	
	
	public static String convertStreamToString(java.io.InputStream is) throws java.io.IOException {
		if (is != null) {
			java.io.Writer writer = new java.io.StringWriter();		
			char[] buffer = new char[1024];
			try {
			    java.io.Reader reader = new java.io.BufferedReader(new java.io.InputStreamReader(is, "UTF-8"));
			    int n;
			    while ((n = reader.read(buffer)) != -1) {
			        writer.write(buffer, 0, n);
			    }
			} finally {
			    is.close();
			}
			return writer.toString();
		} else {        
			return "";
		}
	}	
	
	public static String strforIn(String[] str)
	{
		String result=""; 
		
		result+="(";
		
		if(str.length>0)
		{
			for(int i=0;i<str.length;i++)
			{
				result+="'"+str[i]+"'";
				result+=",";
			}
			
			if(result.length()>1)
				result=result.substring(0,result.length()-1);

		}
		
		result+=")";
		
		
		return result;
		
	}
	/**
	 * 測試是否為單一檔案名稱，不應該包含\\,/,..，如果想要測試白名單也可以在這裡加入 
	 * @param filePath 檔案路徑
	 * @return 
	 */
	public static String isValidChildFilePath(String filePath){
		if(filePath != null && !"".equals(filePath)){
			if(filePath.indexOf("\\") >= 0 || filePath.indexOf("/") >= 0 || filePath.indexOf("\\\\") >= 0 && filePath.indexOf("..") >= 0)
				filePath = "";//空值 讓程式出現fileNotFoundException
		}
		return filePath;
	}
	/**
	 * @param filePath
	 * @return
	 */
	public static String isValidFilePath(String filePath){
		return filePath;
	}
	/**
	 * 給組hql語法時，組出合適IN這類語法的字串
	 * @param arr
	 * @return
	 */
	public static String getMultiCondition(String[] arr){
		String result = "";
		if(arr != null && arr.length > 0){
			for(int i=0;i<arr.length;i++){
				result += sqlChar(arr[i]);
				if(i!=arr.length-1)result += ",";
			}
		}
		return result;
	}

	/**
	 * 將路徑過濾掉，只傳回檔名
	 */
	public static String getFilename(String fullname) 
	{
		String filename = null;

		fullname = fullname.replace('\\', '/' );
		StringTokenizer token = new StringTokenizer(fullname, "/");
		while (token.hasMoreTokens()) {
			filename = token.nextToken();
		}
		return filename;
	}
	
}