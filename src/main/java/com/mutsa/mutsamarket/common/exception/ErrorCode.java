package com.mutsa.mutsamarket.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "I_001", "상품을 찾을 수 없습니다."),
	AUTHENTICATION_FAILURE(HttpStatus.BAD_REQUEST, "A_001", "인증에 실패했습니다."),

	DUPLICATED_ITEM_ERROR(HttpStatus.BAD_REQUEST, "I_002", "이미 등록된 상품입니다."),
	WRONG_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "I_003", "비밀번호가 틀렸습니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S_002", "서버에 장애가 있습니다."),

	HASHING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S_001", "해싱에 실패하였습니다.");



	private final HttpStatus httpStatus;
	private final String errorCode;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}
}
