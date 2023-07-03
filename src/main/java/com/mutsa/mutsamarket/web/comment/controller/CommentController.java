package com.mutsa.mutsamarket.web.comment.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutsa.mutsamarket.domain.comment.service.CommentService;
import com.mutsa.mutsamarket.web.comment.dto.CommentMapper;
import com.mutsa.mutsamarket.web.comment.dto.request.CommentCreate;
import com.mutsa.mutsamarket.web.salesitem.dto.response.ResultMessageResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("items/{itemId}/comments")
public class CommentController {
	private final CommentService commentService;
	private final CommentMapper commentMapper;

	@PostMapping
	public ResultMessageResponse writeComment(@PathVariable Long itemId, @RequestBody CommentCreate comment){
		commentService.save(itemId ,commentMapper.toEntity(comment));
		return new ResultMessageResponse("댓글이 등록되었습니다.");
	}


}
