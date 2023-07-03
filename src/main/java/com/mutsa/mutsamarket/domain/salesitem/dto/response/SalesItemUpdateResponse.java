package com.mutsa.mutsamarket.domain.salesitem.dto.response;

import lombok.Data;

@Data
public class SalesItemUpdateResponse {
	private String message;

	public SalesItemUpdateResponse(String message) {
		this.message = message;
	}
}
