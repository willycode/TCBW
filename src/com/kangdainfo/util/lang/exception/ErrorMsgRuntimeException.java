package com.kangdainfo.util.lang.exception;


public class ErrorMsgRuntimeException extends RuntimeException implements ErrorMsgType {

	private ErrorMsg errorMsg;

    public ErrorMsgRuntimeException() {
        super();
    }
    public ErrorMsgRuntimeException(String message) {
        super(message);
    }
    public ErrorMsgRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
    public ErrorMsgRuntimeException(Throwable cause) {
        super(cause);
    }
    
    
    public ErrorMsgRuntimeException(ErrorMsg errorMsg) {
        super(errorMsg.getKey());
        this.errorMsg = errorMsg;
    }
    public ErrorMsgRuntimeException(ErrorMsg errorMsg, Throwable cause) {
    	super(errorMsg.getKey(),cause);
        this.errorMsg = errorMsg;
    }

	public ErrorMsg getErrorMsg() { return errorMsg; }
	public void setErrorMsg(ErrorMsg errorMsg) { this.errorMsg = errorMsg; }
	
}
