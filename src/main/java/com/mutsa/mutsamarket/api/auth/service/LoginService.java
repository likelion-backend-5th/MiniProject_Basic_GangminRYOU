package com.mutsa.mutsamarket.api.auth.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mutsa.mutsamarket.api.auth.dto.MemberLogin;
import com.mutsa.mutsamarket.config.CustomUserDetails;
import com.mutsa.mutsamarket.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final AuthenticationManagerBuilder managerBuilder;
	private final JwtUtils jwtUtils;

	public String authenticate(MemberLogin login){
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			login.getEmail(), login.getPassword());
		Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
		CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
		return jwtUtils.createAccessToken(userDetails);
	}
}
