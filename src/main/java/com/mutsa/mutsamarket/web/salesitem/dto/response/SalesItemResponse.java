package com.mutsa.mutsamarket.web.salesitem.dto.response;

import com.mutsa.mutsamarket.domain.salesitem.entity.Status;


import lombok.Builder;
import lombok.Data;

@Data
public class SalesItemResponse {
	private String title;
	private String description;
	private Integer minPriceWanted;
	private Status status;

	@Builder
	public SalesItemResponse(String title, String description, Integer minPriceWanted, Status status) {
		this.title = title;
		this.description = description;
		this.minPriceWanted = minPriceWanted;
		this.status = status;
	}
}
