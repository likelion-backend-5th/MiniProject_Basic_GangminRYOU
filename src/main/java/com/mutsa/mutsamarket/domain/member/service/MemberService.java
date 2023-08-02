package com.mutsa.mutsamarket.domain.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.EntityNotFoundException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.member.entity.Member;
import com.mutsa.mutsamarket.domain.member.repository.MemberRepository;
import com.mutsa.mutsamarket.domain.role.entity.Role;
import com.mutsa.mutsamarket.domain.role.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Long save(List<String> roles, Member member){
		if(!member.validateEmail()){
			throw new BusinessException(ErrorCode.NOT_VALID_EMAIL);
		}
		member.encode(passwordEncoder);
		List<Role> foundRoles = roles.stream().map(role -> {
			Optional<Role> foundRole = roleRepository.findAllByRoleName(role);
			if (foundRole.isEmpty()) {
				throw new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND);
			}
			return foundRole.get();
		}).toList();
		member.setRoles(foundRoles);
		return memberRepository.save(member).getId();
	}

	public Member readOne(Long id){
		return memberRepository.findByIdOrThrow(id);
	}

	public Member readOneByEmail(String email){
		return memberRepository.findByEmailOrThrow(email);
	}
}
