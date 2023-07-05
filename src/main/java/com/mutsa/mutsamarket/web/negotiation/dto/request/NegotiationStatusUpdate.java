package com.mutsa.mutsamarket.web.negotiation.dto.request;

import com.mutsa.mutsamarket.domain.negotiation.entity.NegotiationStatus;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NegotiationStatusUpdate {
	private String writer;
	private String password;
	private NegotiationStatus status;

	@Builder
	public NegotiationStatusUpdate(String writer, String password, NegotiationStatus status) {
		this.writer = writer;
		this.password = password;
		this.status = status;
	}
}
