package com.mutsa.mutsamarket.web.salesitem.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Auth {
	private String writer;
	private String password;

	@Builder
	public Auth(String writer, String password) {
		this.writer = writer;
		this.password = password;
	}
}
