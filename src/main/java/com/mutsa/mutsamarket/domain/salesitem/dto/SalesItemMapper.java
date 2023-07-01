package com.mutsa.mutsamarket.domain.salesitem.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.mutsa.mutsamarket.domain.salesitem.dto.request.SalesItemSave;
import com.mutsa.mutsamarket.domain.salesitem.dto.response.SalesItemResponse;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;

@Mapper(componentModel = "spring")
public interface SalesItemMapper {

	SalesItemMapper INSTANCE = Mappers.getMapper(SalesItemMapper.class);

	@Mapping(target = "imageUrl", ignore = true)
	SalesItem toEntity(SalesItemSave salesItemSave);

	SalesItemResponse toDto(SalesItem salesItem);
}
