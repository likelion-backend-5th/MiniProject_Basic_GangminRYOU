package com.mutsa.mutsamarket.domain.comment.service;

import static com.mutsa.mutsamarket.common.fixture.CommentFixture.*;
import static com.mutsa.mutsamarket.common.fixture.SalesItemFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mutsa.mutsamarket.common.fixture.CommentFixture;
import com.mutsa.mutsamarket.common.fixture.SalesItemFixture;
import com.mutsa.mutsamarket.domain.comment.entity.Comment;
import com.mutsa.mutsamarket.domain.comment.repository.CommentRepository;
import com.mutsa.mutsamarket.domain.salesitem.repository.SalesItemRepository;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

	@Mock
	SalesItemRepository salesItemRepository;

	@Mock
	CommentRepository commentRepository;

	@InjectMocks
	CommentService commentService;

	@Test
	@DisplayName("상품에 댓글을 등록할 수 있다.")
	void writeCommentTest() throws Exception {
		//given

		when(salesItemRepository.findByIdOrThrow(anyLong())).thenReturn(TEST_SALES_ITEM);
		when(commentRepository.save(any(Comment.class))).thenReturn(TEST_COMMENT);
		//when
		commentService.save(1L, TEST_COMMENT);
		//then
		verify(commentRepository, times(1)).save(TEST_COMMENT);
	}

}