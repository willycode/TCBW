package com.kangdainfo.util.io.file;

import java.util.*;

public class EnhancedStringTokenizer implements Enumeration {
    public static final int NO_RETURN_DELIMS = 0;
    public static final int RETURN_DELIMS = 1;
    public static final int NO_CONSECUTIVE_DELIMS = 2;
    protected int Mode = NO_CONSECUTIVE_DELIMS;
    
    void setMode(int mode) {
        Mode = mode;
    }
    
    public int getMode() {
        return Mode;
    }
    
    // default delimiter in the StringTokenizer
    protected String Delimiter = " \t\n\r\f";
    protected void setDelimiter(String delim){
        Delimiter = delim;
    }
    
    public String getDelimiter() {
        return Delimiter;
    }
    
    protected String Remainder = "";
    protected void setRemainder(String str) {
        Remainder = str;
    }
    
    public String getRemainder() {
        return Remainder;
    }
    
    
    protected java.util.StringTokenizer st = null;
    /**
     * Same as the StringTokenizer constructor.  No added functionality.
     */
    public EnhancedStringTokenizer(String str) {
        setMode(this.NO_RETURN_DELIMS);
        st = new StringTokenizer(str);
    }
    
    
    /**
     * Same as the StringTokenizer constructor.  No added functionality.
     */
    public EnhancedStringTokenizer(String str, String delim){
        setMode(this.NO_RETURN_DELIMS);
        setDelimiter(delim);
        st = new StringTokenizer(str, delim);
    }
    
    
    /**
     * Same as the StringTokenizer constructor.  No added functionality.
     */
    public EnhancedStringTokenizer
        (String str, String delim, boolean returnDelims) {
        if (returnDelims) {
            setMode(RETURN_DELIMS);
        }
        else {
            setMode(NO_RETURN_DELIMS);
        }
        setDelimiter(delim);
        st = new StringTokenizer(str, delim, returnDelims);
    }
    
    
    /**
     * Using this constructor allows use of the NO_CONSECUTIVE_DELIMS mode
     * of operation.
     */
    public EnhancedStringTokenizer(String str, String delim, int mode) {
        setMode(mode);
        setDelimiter(delim);
        switch (getMode()) {
            case NO_RETURN_DELIMS :
                st = new StringTokenizer(str, delim, false);
                break;
            case RETURN_DELIMS :
                st = new StringTokenizer(str, delim, true);
                break;
            case NO_CONSECUTIVE_DELIMS :
            default :
                init(str);
                break;
        }
    }
    
    void init(String str) {
        setRemainder(str);
    }
    
    public int countTokens() {
        switch (getMode()) {
            case NO_CONSECUTIVE_DELIMS :
                {
                    String oldRem = getRemainder();
                    int count = 0;
                    try {
                        String temp;
                        while (true) {
                            temp = nextToken();
                            count++;
                        }
                    }
                    catch (NoSuchElementException nsee) {
                        setRemainder(oldRem);
                        return count;
                    }
                }
            default :
                return st.countTokens();
        }
    }
    
    public boolean hasMoreElements() {
        switch (getMode()) {
            case NO_CONSECUTIVE_DELIMS :
                int intIndex = 0;
                intIndex = 
                    getRemainder().indexOf(getDelimiter(), intIndex);
                if (intIndex != -1) {
                    return true;
                }
                else {
                    if (getRemainder().length() > 0) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            default :
                return st.hasMoreElements();
        }
    }
    
    public Object nextElement() throws NoSuchElementException {
        switch (getMode()) {
            case NO_CONSECUTIVE_DELIMS :
                int intIndex = 0;
                intIndex = 
                    getRemainder().indexOf(getDelimiter(), intIndex);
                if (intIndex != -1) {
                    String retValue = 
                         getRemainder().substring(0, intIndex);
                    setRemainder(getRemainder().substring(intIndex + 1));
                    return retValue;
                }
                else {
                    if (getRemainder().length() > 0) {
                        String retValue = getRemainder();
                        setRemainder("");
                        return retValue;
                    }
                    else {
                        throw new NoSuchElementException();
                    }
                }
            default :
                return st.nextElement();
        }
    }
    
    public boolean hasMoreTokens() {
        switch (getMode()) {
            case NO_CONSECUTIVE_DELIMS :
                int intIndex = 0;
                intIndex = 
                    getRemainder().indexOf(getDelimiter(), intIndex);
                if (intIndex != -1) {
                    return true;
                }
                else {
                    if (getRemainder().length() > 0) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            default :
                return st.hasMoreElements();
        }
    }
    
    public String nextToken() throws NoSuchElementException {
        switch (getMode()) {
            case NO_CONSECUTIVE_DELIMS :
                int intIndex = 0;
                intIndex = 
                    getRemainder().indexOf(getDelimiter(), intIndex);
                if (intIndex != -1) {
                    String retValue = 
                         getRemainder().substring(0, intIndex);
                    setRemainder(getRemainder().substring(intIndex + 1));
                    return retValue;
                }
                else {
                    if (getRemainder().length() > 0) {
                        String retValue = getRemainder();
                        setRemainder("");
                        return retValue;
                    }
                    else {
                        throw new NoSuchElementException();
                    }
                }
            default :
                return st.nextToken();
        }
    }
    
    public String nextToken(String delim) throws NoSuchElementException {
        switch (getMode()) { 
            case NO_CONSECUTIVE_DELIMS : 
                setDelimiter(delim);
                return nextToken();
            default :
                return st.nextToken(delim);
        }
    }
}

