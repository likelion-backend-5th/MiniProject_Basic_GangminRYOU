package com.mutsa.mutsamarket.domain.comment.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.domain.comment.entity.Comment;
import com.mutsa.mutsamarket.domain.comment.repository.CommentRepository;
import com.mutsa.mutsamarket.domain.member.entity.Member;
import com.mutsa.mutsamarket.domain.member.repository.MemberRepository;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;
import com.mutsa.mutsamarket.domain.salesitem.repository.SalesItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
	private final CommentRepository commentRepository;
	private final SalesItemRepository salesItemRepository;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	public Comment save(Long itemId, String email, Comment comment){
		try {
			SalesItem targetItem = salesItemRepository.findByIdOrThrow(itemId);
			comment.connectItem(targetItem);
			Member member = memberRepository.findByEmailOrThrow(email);
			comment.associateMember(member);
			comment.encodePassword(passwordEncoder);
			return commentRepository.save(comment);
		}catch (DataIntegrityViolationException e){
			throw new BusinessException(ErrorCode.DUPLICATED_COMMENT_ERROR);
		}
	}

	@Transactional(readOnly = true)
	public Page<Comment> readAllByPage(Long itemId){
		PageRequest pageRequest = PageRequest.of(0, 25);
		SalesItem item = salesItemRepository.findByIdOrThrow(itemId);
		List<Comment> comments = item.getComments();
		return PageableExecutionUtils.getPage(comments, pageRequest, comments::size);
	}

	public void updateOne(Long commentId, String content, String rawPassword){
		Comment foundComment = commentRepository.findByIdOrThrow(commentId);
		/*중복이 계속됨.. 개선 필요*/
		if(!passwordEncoder.matches(rawPassword, foundComment.getPassword())){
			throw new BusinessException(ErrorCode.WRONG_PASSWORD_ERROR);
		}
		foundComment.changeContent(content);
	}

	public void deleteOne(Long commentId, String rawPassword){
		Comment foundComment = commentRepository.findByIdOrThrow(commentId);
		/*중복이 계속됨.. 개선 필요
		* */
		if(!passwordEncoder.matches(rawPassword, foundComment.getPassword())){
			throw new BusinessException(ErrorCode.WRONG_PASSWORD_ERROR);
		}
		commentRepository.deleteById(commentId);
	}

	public void updateReply(Long commentId, String reply, String rawPassword){
		Comment foundComment = commentRepository.findByIdOrThrow(commentId);
		/*fetchJoin사용하는게 낫다.*/
		SalesItem salesItem = foundComment.getSalesItem();
		if(!passwordEncoder.matches(rawPassword, salesItem.getPassword())){
			throw new BusinessException(ErrorCode.WRONG_PASSWORD_ERROR);
		}
		foundComment.changeReply(reply);
	}

}
