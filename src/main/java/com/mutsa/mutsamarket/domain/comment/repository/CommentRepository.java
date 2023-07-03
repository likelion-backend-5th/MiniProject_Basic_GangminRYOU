package com.mutsa.mutsamarket.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutsa.mutsamarket.common.exception.EntityNotFoundException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	default Comment findByIdOrThrow(Long id){
		return findById(id)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.COMMENT_NOT_FOUND));
	}
}
