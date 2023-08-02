package com.mutsa.mutsamarket.domain.membernegotiation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutsa.mutsamarket.domain.member.entity.Member;
import com.mutsa.mutsamarket.domain.membernegotiation.entity.MemberNegotiation;
import com.mutsa.mutsamarket.domain.negotiation.entity.Negotiation;

public interface MemberNegotiationRepository extends JpaRepository<MemberNegotiation, Long> {
	public List<MemberNegotiation> findAllByMember(Member member);
	public List<MemberNegotiation> findAllByNegotiation(Negotiation negotiation);
}
