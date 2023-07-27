package com.mutsa.mutsamarket.api.auth.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRegister {
	private String email;
	private String password;
	private String passwordConfirm;
	private String phoneNumber;
	private String address;

	@Builder
	public MemberRegister(String email, String password, String passwordConfirm, String phoneNumber, String address) {
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
}
