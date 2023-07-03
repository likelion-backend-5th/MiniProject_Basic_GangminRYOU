package com.mutsa.mutsamarket.web.comment.dto.request;

import lombok.Data;

@Data
public class CommentUpdate {
	private String writer;
	private String password;
	private String content;
}
