package com.mutsa.mutsamarket.api.file.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;

@Data
public class ImageUpdateRequest {
	private MultipartFile image;
	private String writer;
	private String password;

	@Builder
	public ImageUpdateRequest(MultipartFile image, String writer, String password) {
		this.image = image;
		this.writer = writer;
		this.password = password;
	}
}
