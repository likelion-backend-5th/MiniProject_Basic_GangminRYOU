package com.mutsa.mutsamarket.web.salesitem.dto.request;

import com.mutsa.mutsamarket.domain.salesitem.entity.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalesItemUpdateRequest {
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotNull
	private Integer minPriceWanted;
	private Status status;
	@NotNull
	private String password;
}
