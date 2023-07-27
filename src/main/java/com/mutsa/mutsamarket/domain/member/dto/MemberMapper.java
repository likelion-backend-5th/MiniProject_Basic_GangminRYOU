package com.mutsa.mutsamarket.domain.member.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mutsa.mutsamarket.api.auth.dto.MemberRegister;
import com.mutsa.mutsamarket.domain.member.domain.Member;

@Mapper(componentModel = "spring")
public interface MemberMapper {
	MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);


	Member toEntity(MemberRegister dto);

}
