package com.mutsa.mutsamarket.web.salesitem.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mutsa.mutsamarket.domain.salesitem.dto.SalesItemMapper;
import com.mutsa.mutsamarket.domain.salesitem.dto.request.SalesItemSave;
import com.mutsa.mutsamarket.domain.salesitem.dto.response.SalesItemResponse;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;
import com.mutsa.mutsamarket.domain.salesitem.service.SalesItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class SalesItemController {
	private final SalesItemService salesItemService;
	private final SalesItemMapper salesItemMapper;
	@PostMapping
	public ResponseEntity<String> register(@RequestBody SalesItemSave salesItemSave){
		SalesItem salesItem = salesItemMapper.toEntity(salesItemSave);
		salesItemService.save(salesItem);
		return ResponseEntity.ok().body("등록이 완료되었습니다.");
	}


	@GetMapping("/{id}")
	public SalesItemResponse readOne(@PathVariable Long id){
		return salesItemMapper.toDto(salesItemService.readOne(id));
	}

	@GetMapping
	public Page<SalesItemResponse> readPages(@RequestParam Pageable pageable){
		Page<SalesItem> itempages = salesItemService.readAllWithPage(pageable);
		return itempages.map(i -> SalesItemResponse.builder()
			.title(i.getTitle())
			.minPriceWanted(i.getMinPriceWanted())
			.description(i.getDescription())
			.status(i.getStatus()).build());
	}

	@GetMapping
	public List<SalesItemResponse> readAll(){
		return salesItemService.readAll().stream().map(salesItemMapper::toDto).toList();
	}





}
