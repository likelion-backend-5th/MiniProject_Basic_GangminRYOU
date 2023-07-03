package com.mutsa.mutsamarket.domain.salesitem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mutsa.mutsamarket.api.file.dto.FileResponse;
import com.mutsa.mutsamarket.api.file.service.FileService;
import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;
import com.mutsa.mutsamarket.domain.salesitem.entity.Status;
import com.mutsa.mutsamarket.domain.salesitem.repository.SalesItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SalesItemService {
	private final SalesItemRepository salesItemRepository;
	private final FileService fileService;
	private final PasswordEncoder passwordEncoder;
	public SalesItem save(SalesItem salesItem){
		try{
			salesItem.encodePassword(passwordEncoder);
			return salesItemRepository.save(salesItem);
		}catch (Exception e){
			throw new BusinessException(ErrorCode.DUPLICATED_ITEM_ERROR);
		}
	}

	public List<SalesItem> readAll(){
		return salesItemRepository.findAll();
	}

	public Page<SalesItem> readAllWithPage(Pageable pageable){
		return salesItemRepository.findAll(pageable);
	}

	public SalesItem readOne(Long id){
		return salesItemRepository.findById(id)
			.orElseThrow(()-> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
	}

	public SalesItem updateOne(Long id, String title, String description,
		Integer minPriceWanted, Status status, String rawPassword){
		SalesItem salesItem = salesItemRepository.findById(id)
			.orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
		if(!passwordEncoder.matches(rawPassword, salesItem.getPassword())){
			throw new BusinessException(ErrorCode.WRONG_PASSWORD_ERROR);
		}
		salesItem.update(title, description, minPriceWanted, status);
		return salesItem;
	}

	public FileResponse updateImage(Long id, String writer, String rawPassword, MultipartFile file){
		SalesItem salesItem = salesItemRepository.findById(id)
			.orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
		if(!passwordEncoder.matches(rawPassword, salesItem.getPassword())){
			throw new BusinessException(ErrorCode.WRONG_PASSWORD_ERROR);
		}
		FileResponse fileResponse = fileService.storeAsHash(file);
		salesItem.updateImage(fileResponse.getFileName());
		return fileResponse;
	}
}
