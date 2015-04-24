package com.kangdainfo.util.lang.runtime;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Exec {

	protected Logger logger = Logger.getLogger(this.getClass());

	private static Exec singleton;
	
	public static Exec getInstance() {
		if(singleton==null) {
			singleton = new Exec();
		}
		return singleton;
	}
	private Map<String, List<Process>> processes;
	
	private Exec() {
		processes = new HashMap<String, List<Process>>();
	}

	private void addProcess(String processName, Process process) {
		List list = processes.get(processName);
		if(list==null) {
			list = new ArrayList<Process>();			
			processes.put(processName, list);
		}
		list.add(process);
	}
	public void destroyAll() {
		Object processNames[] = processes.keySet().toArray();
		for(int i=0; i < processNames.length; i++) {
			String processName = (String) processNames[i];
			destroy(processName);
		}
		processes.clear();
	}
	public void destroy(String processName) {
		List<Process> list = processes.get(processName);
		for(int j=0,max_j=list.size(); j < max_j; j++) {
			Process process_j = list.get(j);
			try {
				process_j.destroy();
				logger.info("destroyed " + processName + j);
			} catch (Exception e) {
				logger.error("Error destroying process [" + processName + "]",e);
			}
		}
		list.clear();
	}
	
	public int run(String processName, File dir, String command, boolean failOnError) throws Exception {
	
		int err = -1; // assume the worst
	
	    // test if os match
	    String myos = System.getProperty("os.name");
	    logger.info("Myos = " + myos);
	
	    try {
	
	        // exec command on system runtime
	        Process process = Runtime.getRuntime().exec(command,null,dir);
	        addProcess(processName,process);
	
	        // copy input and error to the output stream
	        StreamPumper inputPumper = new StreamPumper(process.getInputStream());
	        StreamPumper errorPumper = new StreamPumper(process.getErrorStream());
	
	        // starts pumping away the generated output/error
	        inputPumper.start();
	        errorPumper.start();
	
	        // Wait for everything to finish
/*	        
	        process.waitFor();
	        inputPumper.join();
	        errorPumper.join();
	        process.destroy();
*/	
	
	        // check its exit value
/*
	        err = proc.exitValue();
	        if (err != 0) {
	            if (failOnError) {
	                throw new Exception("Exec returned: " + err);
	            } else {
	                logger.info("Result: " + err);
	            }
	        }
*/        
	    } 
	    catch (IOException ioe) 
	    {
	        throw new Exception("Error exec: " + command, ioe);
	    } 
/*	    
	    catch (InterruptedException ex) {
	        //ignore
	    }
*/	
	    return err;
	}
	
	public static void main(String args[]) throws Exception {
		Exec exec = Exec.getInstance();
		exec.run("HelloWorld",null, "java com.kangdainfo.lang.runtime.HelloWorld", true);
	}
	
	
	
	
	  
}
