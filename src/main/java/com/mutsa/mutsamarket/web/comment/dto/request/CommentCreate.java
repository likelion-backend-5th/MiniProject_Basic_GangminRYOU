package com.mutsa.mutsamarket.web.comment.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentCreate {
	@NotNull
	private Long salesItemId;
	@NotBlank
	private String writer;
	private String password;
	@NotBlank
	private String content;
	private String reply;

	@Builder
	public CommentCreate(Long salesItemId, String writer, String password, String content, String reply) {
		this.salesItemId = salesItemId;
		this.writer = writer;
		this.password = password;
		this.content = content;
		this.reply = reply;
	}
}
