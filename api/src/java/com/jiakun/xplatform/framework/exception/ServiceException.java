package com.jiakun.xplatform.framework.exception;

/**
 * 
 * @author xujiakun
 * 
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 5259805918456538208L;

	private String errorCode;

	public ServiceException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ServiceException(String errorCode, Throwable cause) {
		super(errorCode, cause);
		this.errorCode = errorCode;
	}

	public ServiceException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
