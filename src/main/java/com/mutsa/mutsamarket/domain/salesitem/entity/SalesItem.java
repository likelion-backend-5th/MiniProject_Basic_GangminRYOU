package com.mutsa.mutsamarket.domain.salesitem.entity;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.comment.entity.Comment;
import com.mutsa.mutsamarket.domain.negotiation.entity.Negotiation;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SalesItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Lob
	@Column(nullable = false)
	private String description;
	private String imageUrl;
	@Column(nullable = false)
	private Integer minPriceWanted;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(nullable = false)
	private String writer;
	private String password;

	@OneToMany(mappedBy = "salesItem", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "salesItem", cascade = CascadeType.ALL)
	private List<Negotiation> negotiations = new ArrayList<>();

	@Builder
	public SalesItem(String title, String description, String imageUrl, int minPriceWanted, Status status,
		String writer,
		String password) {
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.minPriceWanted = minPriceWanted;
		this.status = status;
		this.writer = writer;
		this.password = password;
	}

	public void addComment(Comment comment){
		this.comments.add(comment);
	}

	public void enrollNegotiataion(Negotiation negotiation){this.negotiations.add(negotiation);}
	public void update(String title, String description, Integer minPriceWanted, Status status){
		this.title = title;
		this.description = description;
		this.minPriceWanted = minPriceWanted;
		this.status =status;
	}

	public void encodePassword(PasswordEncoder passwordEncoder){
		try{
			this.password = passwordEncoder.encode(password);
		} catch (Exception e){
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	public void updateImage(String imageUrl){
		this.imageUrl = imageUrl;
	}

	@PostConstruct
	public void changeStatus(){
		this.status = Status.ON_SALE;
	}
}
