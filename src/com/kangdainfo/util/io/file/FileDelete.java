package com.kangdainfo.util.io.file;

import java.io.File;

public class FileDelete {

    public static boolean delete(File fileOrDirectory) {
    	int retries = 2;
    	long retryInterval = 20; 
    	for(int i=0; i < retries; i++) {
    		delete2(fileOrDirectory);
    		if(!fileOrDirectory.exists())	 {
    			return true;
    		}
    		 try {  Thread.sleep(retryInterval); } catch(InterruptedException ex) { }
    	}
    	throw new RuntimeException("Failed to delete " + fileOrDirectory.getAbsolutePath() + "!");
    }
    
    public static boolean delete2(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            String[] children = fileOrDirectory.list();
            for (int i=0; i<children.length; i++) {
                boolean success = delete2(new File(fileOrDirectory, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
    
        // The directory is now empty so delete it
        return fileOrDirectory.delete();
    }

    
}
