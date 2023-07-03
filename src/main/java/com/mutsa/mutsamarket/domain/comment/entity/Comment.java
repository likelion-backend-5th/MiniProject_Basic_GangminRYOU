package com.mutsa.mutsamarket.domain.comment.entity;

import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id", nullable = false)
	private SalesItem salesItem;

	@Column(nullable = false)
	private String writer;

	private String password;

	@Column(nullable = false)
	private String content;

	private String reply;

	public void connectItem(SalesItem salesItem){
		this.salesItem = salesItem;
	}

	public void changeContent(String content){
		this.content = content;
	}

	public void changeReply(String reply){
		this.reply = reply;
	}

	@Builder
	public Comment(SalesItem salesItem, String writer, String password, String content, String reply) {
		this.salesItem = salesItem;
		this.writer = writer;
		this.password = password;
		this.content = content;
		this.reply = reply;
	}
}
