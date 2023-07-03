package com.mutsa.mutsamarket.web.comment.dto.request;

import lombok.Data;

@Data
public class ReplyRequest {
	   private String writer;
	   private String password;
	   private String reply;
}
