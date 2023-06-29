package com.mutsa.mutsamarket.domain.salesitem.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mutsa.mutsamarket.domain.salesitem.dto.request.SalesItemSave;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;

@Mapper(componentModel = "spring")
public interface SalesItemMapper {

	@Mapping(target = "imageUrl", ignore = true)
	SalesItem toEntity(SalesItemSave salesItemSave);
}
