package com.mutsa.mutsamarket.domain.salesitem.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class SalesItemResponse {
	private String message;

	@Builder
	public SalesItemResponse(String message) {
		this.message = message;
	}
}
