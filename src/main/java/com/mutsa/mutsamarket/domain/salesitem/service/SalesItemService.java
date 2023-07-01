package com.mutsa.mutsamarket.domain.salesitem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;
import com.mutsa.mutsamarket.domain.salesitem.repository.SalesItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesItemService {
	private final SalesItemRepository salesItemRepository;

	public SalesItem save(SalesItem salesItem){
		try{
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


}
