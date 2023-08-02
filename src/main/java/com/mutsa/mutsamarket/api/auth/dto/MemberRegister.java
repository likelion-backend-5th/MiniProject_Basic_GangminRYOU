package com.mutsa.mutsamarket.api.auth.dto;

import java.util.List;

import com.mutsa.mutsamarket.domain.member.entity.Member;

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
	private List<String> roles;

	@Builder
	public MemberRegister(String email, String password, String passwordConfirm, String phoneNumber, String address,
		List<String> roles) {
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.roles = roles;
	}

	public Member toMember(){
		return Member.builder()
			.email(this.email)
			.password(this.password)
			.address(this.address)
			.phoneNumber(this.phoneNumber)
			.build();
	}
}
