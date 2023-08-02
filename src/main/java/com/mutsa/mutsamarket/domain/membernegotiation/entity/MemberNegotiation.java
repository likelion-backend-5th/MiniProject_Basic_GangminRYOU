package com.mutsa.mutsamarket.domain.membernegotiation.entity;

import com.mutsa.mutsamarket.common.BaseTimeEntity;
import com.mutsa.mutsamarket.domain.member.entity.Member;
import com.mutsa.mutsamarket.domain.negotiation.entity.Negotiation;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class MemberNegotiation extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "negotiation_id")
	private Negotiation negotiation;
}
