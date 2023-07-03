package com.mutsa.mutsamarket.web.salesitem.dto.request;

import com.mutsa.mutsamarket.domain.salesitem.entity.Status;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalesItemSave {

	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotNull
	private Integer minPriceWanted;
	private Status status;
	@NotBlank
	private String writer;
	private String password;

	@Builder
	public SalesItemSave(String title, String description, Integer minPriceWanted, Status status, String writer,
		String password) {
		this.title = title;
		this.description = description;
		this.minPriceWanted = minPriceWanted;
		this.status = status;
		this.writer = writer;
		this.password = password;
	}
}
