package com.mutsa.mutsamarket.common.fixture;

import com.mutsa.mutsamarket.domain.comment.entity.Comment;

public class CommentFixture {

	public static Comment TEST_COMMENT = createComment();


	private static Comment createComment(){
		return Comment.builder()
			.writer("작성자")
			.reply("답글")
			.password("비밀번호")
			.content("댓글 내용")
			.build();
	}

}
