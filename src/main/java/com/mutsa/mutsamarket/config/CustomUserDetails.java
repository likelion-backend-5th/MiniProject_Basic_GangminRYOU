package com.mutsa.mutsamarket.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mutsa.mutsamarket.domain.member.domain.Member;

import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

	private Long id;
	private String email;
	private String password;
	private String phoneNumber;
	private String address;

	@Builder
	private CustomUserDetails(Long id, String email, String password, String phoneNumber, String address) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public CustomUserDetails fromEntity(Member member){
		return CustomUserDetails.builder()
			.id(member.getId())
			.email(member.getEmail())
			.password(member.getPassword())
			.phoneNumber(member.getPhoneNumber())
			.address(member.getAddress())
			.build();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
