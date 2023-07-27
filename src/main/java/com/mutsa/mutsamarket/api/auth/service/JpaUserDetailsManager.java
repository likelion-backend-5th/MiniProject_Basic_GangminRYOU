package com.mutsa.mutsamarket.api.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.config.CustomUserDetails;
import com.mutsa.mutsamarket.domain.member.domain.Member;
import com.mutsa.mutsamarket.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsManager implements UserDetailsManager {

	private final MemberRepository memberRepository;


	@Override
	public void createUser(UserDetails user) {
		if(userExists(user.getUsername()))
			throw new BusinessException(ErrorCode.DUPLICATED_EMAIL_ERROR);
		var member = Member.builder()
			.email(user.getUsername())
			.password(user.getPassword())
			.build();
		memberRepository.save(member);
	}

	@Override
	public void updateUser(UserDetails user) {

	}

	@Override
	public void deleteUser(String username) {

	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {

	}

	@Override
	public boolean userExists(String username) {
		return memberRepository.existsByEmail(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var foundUser = memberRepository.findByEmailOrThrow(username);
		return CustomUserDetails.builder().email(username).password(foundUser.getPassword()).build();
	}
}
