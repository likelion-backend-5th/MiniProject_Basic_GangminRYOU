package com.mutsa.mutsamarket.api.auth.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLogin {
	private String email;
	private String password;

	@Builder
	public MemberLogin(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
