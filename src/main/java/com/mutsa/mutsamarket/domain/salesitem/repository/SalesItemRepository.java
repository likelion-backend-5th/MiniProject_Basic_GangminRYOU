package com.mutsa.mutsamarket.domain.salesitem.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;

public interface SalesItemRepository extends JpaRepository<SalesItem, Long>, PagingAndSortingRepository<SalesItem, Long> {
	Page<SalesItem> findAll(Pageable pageable);

	Optional<SalesItem> findByWriter(String writer);
}
