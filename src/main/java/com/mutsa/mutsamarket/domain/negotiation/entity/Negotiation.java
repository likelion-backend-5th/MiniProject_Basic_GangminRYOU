package com.mutsa.mutsamarket.domain.negotiation.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Negotiation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false)
	private SalesItem salesItem;

	@Column(nullable = false)
	private Integer suggestedPrice;

	@Enumerated(EnumType.STRING)
	private NegotiationStatus status;

	@Column(nullable = false)
	private String writer;

	@Column(nullable = false)
	private String password;

	@Builder
	public Negotiation(SalesItem salesItem, Integer suggestedPrice, NegotiationStatus status, String writer,
		String password) {
		this.salesItem = salesItem;
		this.suggestedPrice = suggestedPrice;
		this.status = status;
		this.writer = writer;
		this.password = password;
	}

	public void encodePassword(PasswordEncoder passwordEncoder){
		this.password = passwordEncoder.encode(password);
	}

	public void connectItem(SalesItem salesItem){
		this.salesItem = salesItem;
		salesItem.enrollNegotiataion(this);
	}

	public void changeSuggestedPrice(Integer suggestedPrice){
		this.suggestedPrice = suggestedPrice;
	}

	public void changeStatus(NegotiationStatus status){
		this.status = status;
	}

	@PrePersist
	public void initStatus(){
		this.status = NegotiationStatus.IN_SUGGESTION;
	}
}
