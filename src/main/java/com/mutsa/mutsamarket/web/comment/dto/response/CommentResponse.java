package com.mutsa.mutsamarket.web.comment.dto.response;

import lombok.Data;

@Data
public class CommentResponse {
	private Long id;
	private String content;
	private String reply;

	public CommentResponse(Long id, String content, String reply) {
		this.id = id;
		this.content = content;
		this.reply = reply;
	}
}
