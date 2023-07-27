package com.mutsa.mutsamarket.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mutsa.mutsamarket.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/members/new")
	public String registerForm(){
		return "";
	}

}
