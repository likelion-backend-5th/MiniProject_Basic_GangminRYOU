package com.mutsa.mutsamarket.domain.negotiation.entity;

public enum NegotiationStatus {

	IN_SUGGESTION("제안"), ACCEPTED("수락"), REJECTED("거절"), CONFIRMED("확정");

	private String status;

	NegotiationStatus(String status) {
		this.status = status;
	}
}
