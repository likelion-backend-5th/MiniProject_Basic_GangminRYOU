package com.mutsa.mutsamarket.domain.salesitem.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;

public interface SalesItemRepository extends JpaRepository<SalesItem, Long>, PagingAndSortingRepository<SalesItem, Long> {
	Page<SalesItem> findAll(Pageable pageable);

	default SalesItem findByIdOrThrow(Long id){
		return findById(id)
			.orElseThrow( ()-> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
	}
}
