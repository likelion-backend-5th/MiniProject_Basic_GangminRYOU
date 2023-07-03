package com.mutsa.mutsamarket.web.salesitem.controller;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mutsa.mutsamarket.api.file.dto.FileResponse;
import com.mutsa.mutsamarket.api.file.dto.request.ImageUpdateRequest;
import com.mutsa.mutsamarket.domain.salesitem.dto.SalesItemMapper;
import com.mutsa.mutsamarket.domain.salesitem.dto.request.SalesItemSave;
import com.mutsa.mutsamarket.domain.salesitem.dto.request.SalesItemUpdateRequest;
import com.mutsa.mutsamarket.domain.salesitem.dto.response.SalesItemResponse;
import com.mutsa.mutsamarket.domain.salesitem.dto.response.ResultMessageResponse;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;
import com.mutsa.mutsamarket.domain.salesitem.service.SalesItemService;

import lombok.RequiredArgsConstructor;

@RestController
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
	public Page<SalesItemResponse> readPages(@RequestParam Integer page, @RequestParam Integer limit) {
		PageRequest pageRequest = PageRequest.of(page, limit);
		Page<SalesItem> itempages = salesItemService.readAllWithPage(pageRequest);
		return itempages.map(i -> SalesItemResponse.builder()
			.title(i.getTitle())
			.minPriceWanted(i.getMinPriceWanted())
			.description(i.getDescription())
			.status(i.getStatus()).build());
	}

	@PutMapping("/{itemId}")
	public ResultMessageResponse updateItem(@PathVariable("itemId") Long id, @RequestBody SalesItemUpdateRequest updateRequest){
		salesItemService.updateOne(id, updateRequest.getTitle(), updateRequest.getDescription(),
			updateRequest.getMinPriceWanted(), updateRequest.getStatus(), updateRequest.getPassword());
		return new ResultMessageResponse("물품이 수정되었습니다.");
	}
	@PutMapping(value = "/{itemId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FileResponse updateItemImage(@PathVariable("itemId") Long id, @ModelAttribute ImageUpdateRequest updateRequest) {
		return salesItemService.updateImage(id, updateRequest.getWriter(), updateRequest.getPassword(),
			updateRequest.getImage());
	}

	@DeleteMapping("/{itemId}")
	public ResultMessageResponse deleteItem(@PathVariable("itemId") Long id, @RequestParam String writer, @RequestParam String password){
		salesItemService.deleteOne(id, password);
		return new ResultMessageResponse("물품을 삭제했습니다.");
	}
}
