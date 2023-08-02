package com.mutsa.mutsamarket.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutsa.mutsamarket.common.exception.EntityNotFoundException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	default Member findByIdOrThrow(Long id){
		return findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
	}


	Optional<Member> findByEmail(String email);

	default Member findByEmailOrThrow(String email){
		return findByEmail(email).orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
	}

	boolean existsByEmail(String email);
}
