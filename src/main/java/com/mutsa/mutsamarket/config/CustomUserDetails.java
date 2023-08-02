package com.mutsa.mutsamarket.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mutsa.mutsamarket.domain.member.entity.Member;
import com.mutsa.mutsamarket.domain.member.entity.RoleType;
import com.mutsa.mutsamarket.domain.role.entity.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

	private Long id;
	private String email;
	private String password;
	private String phoneNumber;
	private String address;
	private List<GrantedAuthority> authorities = new ArrayList<>();

	@Builder
	public CustomUserDetails(Long id, String email, String password, String phoneNumber, String address,
		List<GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.authorities = authorities;
	}

	public CustomUserDetails fromEntity(Member member){
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : member.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role.getRoleName())));
			authorities.addAll(role.getAuthorities()
				.stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getName()))
				.toList());
		}
		return CustomUserDetails.builder()
			.id(member.getId())
			.email(member.getEmail())
			.password(member.getPassword())
			.phoneNumber(member.getPhoneNumber())
			.address(member.getAddress())
			.authorities(authorities)
			.build();
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
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
