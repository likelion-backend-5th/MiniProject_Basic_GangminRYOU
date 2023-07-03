package com.mutsa.mutsamarket.api.file.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.apache.el.parser.ArithmeticNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mutsa.mutsamarket.api.file.dto.FileResponse;
import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;

@Service
public class FileService {


	private final Path root;

	/*생성자에서 파일을 저장할 폴더 생성*/
	public FileService(@Value("${file.upload-dir}") String uploadDir){
		root = Paths.get(uploadDir);
		try {
			Files.createDirectories(root);
		} catch (IOException e){
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR, "디렉토리 생성 실패");
		}
	}



	public FileResponse storeAsHash(MultipartFile file){
		try{
			validateFileSize(file.getSize());
			String originalFilename = file.getOriginalFilename();
			validateFileName(originalFilename);
			String fileExtension = getFileExtension(originalFilename);
			String hashedFileName = hashFile(file.getBytes());
			hashedFileName += fileExtension;
			Path path = saveFile(file, hashedFileName);
			return buildFileResposne(path, file.getSize(), file.getContentType());
		}catch (Exception e){
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	private FileResponse buildFileResposne(Path filePath, long fileSize, String contentType){
		return FileResponse.builder()
			.fileName(filePath.getFileName().toString())
			.fileSize(fileSize)
			.contentType(contentType)
			.uploadTimeStamp(LocalDateTime.now())
			.build();
	}


	public Path saveFile(MultipartFile file, String fileName){
		/*파일경로를 설정한다.*/
		Path targetLocation = root.resolve(fileName);
		try{
			/**/
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException e){
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		/*프론트로 파일 저장경로도 같이 전달한다.*/
		return targetLocation;
	}

	private String getFileExtension(String originalFileName){
		int dotIndex = originalFileName.lastIndexOf(".");
		return originalFileName.substring(dotIndex);
	}

	private String validateFileName(String originalFileName){
		if(originalFileName.isEmpty() || originalFileName == null){
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return StringUtils.cleanPath(originalFileName);
	}
	private void validateFileSize(long fileSize){
		if(fileSize == 0){
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	private String hashFile(byte[] fileByte){
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = digest.digest(fileByte);
			return bytesToHex(hashedBytes);
		}catch (NoSuchAlgorithmException e){
			throw new BusinessException(ErrorCode.HASHING_ERROR);
		}
	}

	private String bytesToHex(byte[] bytes){
		StringBuilder sb = new StringBuilder(2 * bytes.length);
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}
