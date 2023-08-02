package com.mutsa.mutsamarket.api.auth.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutsa.mutsamarket.api.auth.dto.MemberLogin;
import com.mutsa.mutsamarket.api.auth.dto.MemberRegister;
import com.mutsa.mutsamarket.api.auth.dto.TokenResponse;
import com.mutsa.mutsamarket.api.auth.service.LoginService;

import com.mutsa.mutsamarket.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final MemberService memberService;

	private final LoginService loginService;

	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody MemberRegister memberRegister){
		Long savedId = memberService.save(memberRegister.getRoles(), memberRegister.toMember());
		return ResponseEntity.created(URI.create("/members/" + savedId)).build();
	}

	@PostMapping("/login")
	public TokenResponse login(@RequestBody MemberLogin loginRequest){
		var accessToken = loginService.authenticate(loginRequest);
		return new TokenResponse(accessToken);
	}

}
