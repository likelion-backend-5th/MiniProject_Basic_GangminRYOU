package com.mutsa.mutsamarket.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "E_001", "상품을 찾을 수 없습니다."),
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "E_002", "댓글을 찾을 수 없습니다."),
	DUPLICATED_COMMENT_ERROR(HttpStatus.BAD_REQUEST, "E_004", "이미 작성된 댓글입니다."),
	DUPLICATED_NEGOTIATION_ERROR(HttpStatus.BAD_REQUEST, "E_005", "이미 등록된 제안입니다."),
	ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "E_006", "해당 건을 찾을 수 없습니다."),
	UPDATE_FAILURE(HttpStatus.NOT_MODIFIED, "E_007", "수정에 실패했습니다."),
	DELETE_FAILURE(HttpStatus.NOT_IMPLEMENTED, "E_008", "삭제에 실패했습니다."),
	AUTHENTICATION_FAILURE(HttpStatus.BAD_REQUEST, "A_001", "인증에 실패했습니다."),
	DUPLICATED_ITEM_ERROR(HttpStatus.BAD_REQUEST, "E_003", "이미 등록된 상품입니다."),
	WRONG_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "C_001", "비밀번호가 틀렸습니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S_002", "서버에 장애가 있습니다."),
	NO_AUTHORITY_ERROR(HttpStatus.FORBIDDEN, "A_002", "권한이 없습니다."),
	HASHING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S_001", "해싱에 실패하였습니다."),
	NOT_VALID_EMAIL(HttpStatus.BAD_REQUEST, "M_001", "이메일 형식이 유효하지 않습니다."),
	DUPLICATED_EMAIL_ERROR(HttpStatus.BAD_REQUEST, "M_002", "이미 존재하는 이메일 입니다."),
	INVALID_TOKEN_ERROR(HttpStatus.FORBIDDEN, "A_003", "유효하지 않은 토큰입니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
		this.httpStatus = httpStatus;
		this.code = errorCode;
		this.message = message;
	}
}
