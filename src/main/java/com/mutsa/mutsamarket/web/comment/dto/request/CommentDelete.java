package com.mutsa.mutsamarket.web.comment.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDelete {
	private String writer;
	private String password;

	@Builder
	public CommentDelete(String writer, String password) {
		this.writer = writer;
		this.password = password;
	}
}
