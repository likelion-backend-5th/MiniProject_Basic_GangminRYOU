package com.mutsa.mutsamarket.common.exception;

public class UpdateFailure extends BusinessException{

	public UpdateFailure(ErrorCode errorCode, String detail) {
		super(errorCode, detail);
	}

	public UpdateFailure(ErrorCode errorCode) {
		super(errorCode);
	}
}
