package com.mutsa.mutsamarket.web.comment.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mutsa.mutsamarket.web.comment.dto.request.CommentCreate;
import com.mutsa.mutsamarket.domain.comment.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

	CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

	Comment toEntity(CommentCreate commentCreate);
}
