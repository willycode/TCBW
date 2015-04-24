package com.kangdainfo.common.util.filestore;

import java.io.IOException;
import java.util.Properties;


public class ContentTypeConfiguration
{
 
    static Properties contentType = new Properties();
    
    static{
        try {
            contentType.load(ContentTypeConfiguration.class.getResourceAsStream("contenttype.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    static public String getContentType(String filename){
        int index = filename.lastIndexOf('.');
        if(index==-1) return null;
        String extend = filename.substring(index+1,filename.length()).toLowerCase();
        String ct = contentType.getProperty(extend);
        return ct;
        
    }

}
