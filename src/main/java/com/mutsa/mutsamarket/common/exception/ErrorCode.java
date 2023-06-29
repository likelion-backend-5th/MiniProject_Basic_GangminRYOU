package com.mutsa.mutsamarket.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "I_001", "상품을 찾을 수 없습니다."),
	DUPLICATED_ITEM_ERROR(HttpStatus.BAD_REQUEST, "I_002", "이미 등록된 상품입니다."),


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
