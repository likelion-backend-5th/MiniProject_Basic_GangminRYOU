package com.mutsa.mutsamarket.web.salesitem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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



}
