package com.mutsa.mutsamarket.web.negotiation.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NegotiationRequest {
	private String writer;
	private String password;
	private Integer suggestedPrice;

	@Builder
	public NegotiationRequest(String writer, String password, Integer suggestedPrice) {
		this.writer = writer;
		this.password = password;
		this.suggestedPrice = suggestedPrice;
	}
}
