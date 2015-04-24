package com.kangdainfo.util.lang.runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class StreamPumper extends Thread {

	protected Logger logger = Logger.getLogger(this.getClass());
	
    private BufferedReader din;
    private int messageLevel;
    private boolean endOfStream = false;
    private int SLEEP_TIME = 5;

    public StreamPumper(InputStream is) {
        this.din = new BufferedReader(new InputStreamReader(is));
    }

    public void pumpStream() throws IOException {
        if (!endOfStream) {
            String line = din.readLine();

            if (line != null) {
                logger.info(line);
            } else {
                endOfStream = true;
            }
        }
    }

    public void run() {
        try {
            try {
                while (!endOfStream) {
                    pumpStream();
                    sleep(SLEEP_TIME);
                }
            } catch (InterruptedException ie) {
                //ignore
            }
            din.close();
        } catch (IOException ioe) {
            // ignore
        }
    }
    
}