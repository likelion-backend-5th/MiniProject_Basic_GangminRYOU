package com.mutsa.mutsamarket.domain.salesitem.dto.request;

import com.mutsa.mutsamarket.domain.salesitem.entity.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalesItemUpdate {
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotNull
	private Integer minPriceWanted;
	private Status status;
	@NotNull
	private String passwordToValidate;

	@Builder
	public SalesItemUpdate(String title, String description, Integer minPriceWanted, Status status,
		String passwordToValidate) {
		this.title = title;
		this.description = description;
		this.minPriceWanted = minPriceWanted;
		this.status = status;
		this.passwordToValidate = passwordToValidate;
	}
}
