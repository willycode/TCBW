package com.kangdainfo.util.lang.exception;


public class ErrorMsgException extends Exception implements ErrorMsgType {

	private ErrorMsg errorMsg;

    public ErrorMsgException() {
        super();
    }
    public ErrorMsgException(String message) {
        super(message);
    }
    public ErrorMsgException(String message, Throwable cause) {
        super(message, cause);
    }
    public ErrorMsgException(Throwable cause) {
        super(cause);
    }
    
    
    public ErrorMsgException(ErrorMsg errorMsg) {
        super(errorMsg.getKey());
        this.errorMsg = errorMsg;
    }
    public ErrorMsgException(ErrorMsg errorMsg, Throwable cause) {
    	super(errorMsg.getKey(),cause);
        this.errorMsg = errorMsg;
    }
    
    
	public ErrorMsg getErrorMsg() { return errorMsg; }
	public void setErrorMsg(ErrorMsg errorMsg) { this.errorMsg = errorMsg; }
	
	
	
    
}
