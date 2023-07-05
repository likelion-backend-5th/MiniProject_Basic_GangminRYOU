package com.mutsa.mutsamarket.web.negotiation.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mutsa.mutsamarket.domain.negotiation.entity.Negotiation;
import com.mutsa.mutsamarket.web.negotiation.dto.request.NegotiationRequest;

@Mapper(componentModel = "spring")
public interface NegotiationMapper {
	NegotiationMapper INSTANCE = Mappers.getMapper(NegotiationMapper.class);

	Negotiation toEntity(NegotiationRequest negotiationRequest);
}
