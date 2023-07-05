package com.mutsa.mutsamarket.web.negotiation.dto.response;

import com.mutsa.mutsamarket.domain.negotiation.entity.NegotiationStatus;

import lombok.Builder;
import lombok.Data;

@Data
public class NegotiationResponse {
	private Long id;
	private Integer suggestedPrice;
	private NegotiationStatus status;

	@Builder
	public NegotiationResponse(Long id, Integer suggestedPrice, NegotiationStatus status) {
		this.id = id;
		this.suggestedPrice = suggestedPrice;
		this.status = status;
	}
}
