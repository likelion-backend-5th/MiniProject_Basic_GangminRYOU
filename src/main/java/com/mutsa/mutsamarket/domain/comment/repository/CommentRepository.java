package com.mutsa.mutsamarket.domain.comment.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mutsa.mutsamarket.common.exception.EntityNotFoundException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	Page<Comment> findAllBySalesItem(Long itemId, Pageable pageable);

	default Comment findByIdOrThrow(Long id){
		return findById(id)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.COMMENT_NOT_FOUND));
	}
}
