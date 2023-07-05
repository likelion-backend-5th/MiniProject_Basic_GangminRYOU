package com.mutsa.mutsamarket.domain.negotiation.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutsa.mutsamarket.common.exception.BusinessException;
import com.mutsa.mutsamarket.common.exception.ErrorCode;
import com.mutsa.mutsamarket.common.exception.UpdateFailure;
import com.mutsa.mutsamarket.domain.negotiation.entity.Negotiation;
import com.mutsa.mutsamarket.domain.negotiation.entity.NegotiationStatus;
import com.mutsa.mutsamarket.domain.negotiation.repository.NegotiationRepository;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;
import com.mutsa.mutsamarket.domain.salesitem.entity.Status;
import com.mutsa.mutsamarket.domain.salesitem.repository.SalesItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NegotiationService {
	private final NegotiationRepository negotiationRepository;
	private final SalesItemRepository salesItemRepository;
	private final PasswordEncoder passwordEncoder;


	public Negotiation save(Long itemId, Negotiation negotiation){
		try{
			SalesItem targetItem = salesItemRepository.findByIdOrThrow(itemId);
			negotiation.encodePassword(passwordEncoder);
			negotiation.connectItem(targetItem);
			return negotiationRepository.save(negotiation);
		}catch (Exception e){
			throw new BusinessException(ErrorCode.DUPLICATED_NEGOTIATION_ERROR, e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public Page<Negotiation> findAllForItem(Long itemId, String writer, String rawPassword, Pageable pageable){
		try {
			SalesItem foundItem = salesItemRepository.findByIdOrThrow(itemId);
			authenticate(foundItem, writer, rawPassword);
			List<Negotiation> negotiations = foundItem.getNegotiations();
			return PageableExecutionUtils.getPage(negotiations, pageable, negotiations::size);
		}catch (Exception e){
			throw new BusinessException(ErrorCode.ITEM_NOT_FOUND, e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public Page<Negotiation> findAllEnrolledProposals(String writer, Pageable pageable){
		try{
			return negotiationRepository.findAllByWriter(writer, pageable);
		}catch (Exception e){
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}


	public void updateOne(Long negotiationId, String writer, String password, Integer suggestedPrice){
		try {
			Negotiation foundNegotiation = negotiationRepository.findByIdOrThrow(negotiationId);
			authenticate(foundNegotiation, writer, password);
			foundNegotiation.changeSuggestedPrice(suggestedPrice);
		}catch (Exception e){
			throw new UpdateFailure(ErrorCode.UPDATE_FAILURE);
		}
	}

	public void deleteOne(Long negotiationId, String writer, String rawPassword){
		try{
			Negotiation foundNegotiation = negotiationRepository.findByIdOrThrow(negotiationId);
			authenticate(foundNegotiation, writer, rawPassword);
			negotiationRepository.deleteById(negotiationId);
		}catch (Exception e){
			throw new UpdateFailure(ErrorCode.DELETE_FAILURE);
		}
	}


	public void acceptProposal(Long itemId, Long negotiationId, String writer, String rawPassword, NegotiationStatus status){
		try {
			SalesItem foundItem = salesItemRepository.findByIdOrThrow(itemId);
			authenticate(foundItem, writer, rawPassword);
			Negotiation negotiation = negotiationRepository.findByIdOrThrow(negotiationId);
			negotiation.changeStatus(status);
		}catch (Exception e){
			throw new UpdateFailure(ErrorCode.UPDATE_FAILURE);
		}
	}


	public void confirmProposal(Long negotiationId, String writer, String password, NegotiationStatus status){
		try{
			Negotiation negotiation = negotiationRepository.findByIdOrThrow(negotiationId);
			authenticate(negotiation, writer, password);
			if(negotiation.getStatus().equals(NegotiationStatus.ACCEPTED)){
				negotiation.changeStatus(status);
				SalesItem salesItem = negotiation.getSalesItem();
				salesItem.getNegotiations().stream().forEach(i -> {
					if(!i.getId().equals(negotiationId)) {
						i.changeStatus(NegotiationStatus.REJECTED);
					}
				});
			}
		}catch (Exception e){
			throw new UpdateFailure(ErrorCode.UPDATE_FAILURE);
		}
	}



	private void authenticate(SalesItem item, String writer, String rawPassword) {
		if (!item.getWriter().equals(writer))
			throw new BusinessException(ErrorCode.NO_AUTHORITY_ERROR);
		if (!passwordEncoder.matches(rawPassword, item.getPassword()))
			throw new BusinessException(ErrorCode.AUTHENTICATION_FAILURE);
	}

	private void authenticate(Negotiation negotiation, String writer, String rawPassword) {
		if (!negotiation.getWriter().equals(writer))
			throw new BusinessException(ErrorCode.NO_AUTHORITY_ERROR);
		if (!passwordEncoder.matches(rawPassword, negotiation.getPassword()))
			throw new BusinessException(ErrorCode.AUTHENTICATION_FAILURE);
	}

}
