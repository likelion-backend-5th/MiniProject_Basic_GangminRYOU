package com.mutsa.mutsamarket.domain.salesitem.dto.response;

import lombok.Data;

@Data
public class ResultMessageResponse {
	private String message;

	public ResultMessageResponse(String message) {
		this.message = message;
	}
}
