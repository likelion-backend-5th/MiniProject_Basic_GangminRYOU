package com.mutsa.mutsamarket.domain.salesitem.dto.response;

import com.mutsa.mutsamarket.api.file.dto.FileResponse;

import lombok.Data;

@Data
public class ItemImageUpdateResponse {
	private String message;
	private FileResponse fileResponse;
	public ItemImageUpdateResponse(String message, FileResponse fileResponse) {
		this.message = message;
		this.fileResponse = fileResponse;
	}
}
