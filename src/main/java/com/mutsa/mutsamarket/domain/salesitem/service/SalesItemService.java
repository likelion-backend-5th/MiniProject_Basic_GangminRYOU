package com.mutsa.mutsamarket.domain.salesitem.service;

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
}
