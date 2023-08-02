package com.mutsa.mutsamarket.web.comment.controller;


import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutsa.mutsamarket.config.CustomUserDetails;
import com.mutsa.mutsamarket.domain.comment.entity.Comment;
import com.mutsa.mutsamarket.domain.comment.service.CommentService;
import com.mutsa.mutsamarket.web.comment.dto.CommentMapper;
import com.mutsa.mutsamarket.web.comment.dto.request.CommentCreate;
import com.mutsa.mutsamarket.web.comment.dto.request.CommentDelete;
import com.mutsa.mutsamarket.web.comment.dto.request.CommentUpdate;
import com.mutsa.mutsamarket.web.comment.dto.request.ReplyRequest;
import com.mutsa.mutsamarket.web.comment.dto.response.CommentResponse;
import com.mutsa.mutsamarket.web.salesitem.dto.response.ResultMessageResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("items/{itemId}/comments")
public class CommentController {
	private final CommentService commentService;
	private final CommentMapper commentMapper;

	@PostMapping
	public ResultMessageResponse writeComment(
		@PathVariable Long itemId,
		@RequestBody CommentCreate comment,
		@AuthenticationPrincipal CustomUserDetails userDetails){
		commentService.save(itemId, userDetails.getEmail(), commentMapper.toEntity(comment));
		return new ResultMessageResponse("댓글이 등록되었습니다.");
	}

	@GetMapping
	public Page<CommentResponse> readByPage(@PathVariable Long itemId){
		Page<Comment> comments = commentService.readAllByPage(itemId);
		return comments.map(i -> new CommentResponse(i.getId(), i.getContent(), i.getReply()));
	}

	@PutMapping("/{commentId}/reply")
	public ResultMessageResponse addReply(@PathVariable Long commentId, @RequestBody ReplyRequest replyRequest){
		commentService.updateReply(commentId, replyRequest.getReply(), replyRequest.getPassword());
		return new ResultMessageResponse("댓글에 답변이 추가 되었습니다.");
	}

	@PutMapping("/{commentId}")
	public ResultMessageResponse updateComment(@PathVariable Long commentId, @RequestBody CommentUpdate updateRequest){
		commentService.updateOne(commentId, updateRequest.getContent(), updateRequest.getPassword());
		return new ResultMessageResponse("댓글이 수정되었습니다.");
	}

	@DeleteMapping("/{commentId}")
	public ResultMessageResponse deleteComment(@PathVariable Long commentId, @RequestBody CommentDelete deleteRequest){
		commentService.deleteOne(commentId, deleteRequest.getPassword());
		return new ResultMessageResponse("댓글을 삭제했습니다.");
	}

}
