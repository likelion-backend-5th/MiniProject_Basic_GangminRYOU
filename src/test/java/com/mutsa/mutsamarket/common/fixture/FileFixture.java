package com.mutsa.mutsamarket.common.fixture;

import java.time.LocalDateTime;

import com.mutsa.mutsamarket.api.file.dto.FileResponse;

public class FileFixture {

	public static final FileResponse TEST_FILE_RESPONSE = createFileResponse();


	private static FileResponse createFileResponse(){
		return FileResponse.builder()
			.fileName("이미지 파일1")
			.fileSize(42)
			.contentType("jpeg")
			.uploadTimeStamp(LocalDateTime.now())
			.build();
	}
}
