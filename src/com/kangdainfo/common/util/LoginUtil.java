package com.kangdainfo.common.util;

import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class LoginUtil {
	
    static Logger logger = Logger.getLogger(LoginUtil.class);	
    
    static Hashtable retryingTable = new Hashtable();
    static Timer retryingRemoveTimer = new Timer();
    
    public static Hashtable<String, Integer> getRetryingTable() {return retryingTable;	}    
    public static void removeUserRetrying(String key){
    	retryingTable.remove(key);
    } 
    
    static public boolean isUserRetrying(String account,String ip,int maxcount){
        Integer count = (Integer)retryingTable.get(account+ip);        
        if(count!=null){
            if(count.intValue()>=maxcount){
                logger.info("user "+account+" is retrying login at "+ip);                
                return true;
            }else{
                return false;
            }
        }        
        return false;
    }
    
    static public void markUserRetrying(String account,String ip,int maxcount,int timeout){
        Integer count = (Integer)retryingTable.get(account+ip);
        if(count==null){
            count = new Integer(1);
            retryingRemoveTimer.schedule(new RetryingRemoveTask(account,ip),timeout*60000);            
        }else{
            count = new Integer(count.intValue()+1);
        }
        logger.debug("mark retrying "+account+","+ip+","+count.intValue());
        retryingTable.put(account+ip,count);        
    }
    
    static class RetryingRemoveTask extends TimerTask{
        
        String account;
        String ip;
        
        RetryingRemoveTask(String account,String ip){
            this.account = account;
            this.ip = ip;
        }
        
        public void run() {
            retryingTable.remove(account+ip);
            logger.debug("remove retrying "+account+","+ip);     
        }        
    }

}
