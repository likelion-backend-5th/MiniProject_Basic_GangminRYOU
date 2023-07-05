package com.mutsa.mutsamarket.common.exception;

public class BusinessException extends RuntimeException{

	public BusinessException(ErrorCode errorCode, String detail){
		super(errorCode.getMessage() + detail==null? "" : "(" + detail + ")");
		this.errorCode =errorCode;
		this.detail = detail;
	}

	public BusinessException(ErrorCode errorCode){
		this(errorCode, errorCode.getMessage());
	}

	private final ErrorCode errorCode;
	private final String detail;
}
