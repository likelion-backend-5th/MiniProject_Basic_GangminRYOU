package com.mutsa.mutsamarket.domain.comment.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.comment.entity.Comment;
import com.mutsa.mutsamarket.domain.comment.repository.CommentRepository;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;
import com.mutsa.mutsamarket.domain.salesitem.repository.SalesItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
	private final CommentRepository commentRepository;
	private final SalesItemRepository salesItemRepository;
	public Comment save(Long itemId, Comment comment){
		try {
			SalesItem targetItem = salesItemRepository.findByIdOrThrow(itemId);
			comment.connectItem(targetItem);
			targetItem.addComment(comment);
			return commentRepository.save(comment);
		}catch (DataIntegrityViolationException e){
			throw new BusinessException(ErrorCode.DUPLICATED_COMMENT_ERROR);
		}
	}






}
