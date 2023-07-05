package com.mutsa.mutsamarket.domain.negotiation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.negotiation.entity.Negotiation;
import com.mutsa.mutsamarket.domain.negotiation.entity.NegotiationStatus;

public interface NegotiationRepository extends JpaRepository<Negotiation, Long> {

	Page<Negotiation> findAllByWriter(String writer, Pageable pageable);

	default Negotiation findByIdOrThrow(Long id){
		return findById(id)
			.orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
	}
}
