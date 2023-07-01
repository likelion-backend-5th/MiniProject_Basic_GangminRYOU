package com.mutsa.mutsamarket.domain.salesitem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;

public interface SalesItemRepository extends JpaRepository<SalesItem, Long> {
	Page<SalesItem> findAllPages(Pageable pageable);
}
