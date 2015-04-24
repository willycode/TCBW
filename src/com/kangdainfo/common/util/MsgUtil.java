package com.kangdainfo.common.util;

public class MsgUtil {	  
    static String msg = "";
    static public void setMsg(String s) { msg = s; }
    static public String getMsg(){return Common.get(msg); }
}
