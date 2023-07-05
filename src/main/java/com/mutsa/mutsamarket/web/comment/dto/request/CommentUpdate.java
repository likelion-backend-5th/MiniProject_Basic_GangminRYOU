package com.mutsa.mutsamarket.web.comment.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentUpdate {
	private String writer;
	private String password;
	private String content;

	@Builder
	public CommentUpdate(String writer, String password, String content) {
		this.writer = writer;
		this.password = password;
		this.content = content;
	}
}
