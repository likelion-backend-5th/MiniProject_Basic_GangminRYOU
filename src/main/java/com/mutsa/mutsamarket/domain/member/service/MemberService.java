package com.mutsa.mutsamarket.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.member.domain.Member;
import com.mutsa.mutsamarket.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public Long save(Member member){
		if(!member.validateEmail()){
			throw new BusinessException(ErrorCode.NOT_VALID_EMAIL);
		}
		member.encode(passwordEncoder);
		return memberRepository.save(member).getId();
	}

	public Member readOne(Long id){
		return memberRepository.findByIdOrThrow(id);
	}

	public Member readOneByEmail(String email){
		return memberRepository.findByEmailOrThrow(email);
	}
}
