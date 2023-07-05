package com.mutsa.mutsamarket.web.negotiation.controller;

import org.apache.coyote.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mutsa.mutsamarket.domain.negotiation.entity.Negotiation;
import com.mutsa.mutsamarket.domain.negotiation.service.NegotiationService;
import com.mutsa.mutsamarket.web.negotiation.dto.NegotiationMapper;
import com.mutsa.mutsamarket.web.negotiation.dto.request.NegotiationRequest;
import com.mutsa.mutsamarket.web.negotiation.dto.request.NegotiationStatusUpdate;
import com.mutsa.mutsamarket.web.negotiation.dto.response.NegotiationResponse;
import com.mutsa.mutsamarket.web.salesitem.dto.request.Auth;
import com.mutsa.mutsamarket.web.salesitem.dto.response.ResultMessageResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/proposals")
public class NegotiationController {
	private final NegotiationService negotiationService;
	private final NegotiationMapper negotiationMapper;
	@PostMapping
	public ResultMessageResponse createProposal(@PathVariable Long itemId, @RequestBody NegotiationRequest requst){
		negotiationService.save(itemId, negotiationMapper.toEntity(requst));
		return new ResultMessageResponse("구매 제안이 등록되었습니다.");
	}

	@GetMapping
	public Page<NegotiationResponse> readAllNegotiation(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer page, @RequestParam String writer, @RequestParam String password){
		PageRequest pageRequest = PageRequest.of(page - 1, 25);
		Page<Negotiation> negotiations = negotiationService.findAllForItem(itemId, writer, password, pageRequest);
		return negotiations.map(i -> new NegotiationResponse(i.getId(), i.getSuggestedPrice(), i.getStatus()));
	}

	@PutMapping("/{proposalId}")
	public ResultMessageResponse updateProposal(@PathVariable Long proposalId, @RequestBody NegotiationRequest request){
		negotiationService.updateOne(proposalId, request.getWriter(), request.getPassword(), request.getSuggestedPrice());
		return new ResultMessageResponse("제안이 수정되었습니다.");
	}

	@DeleteMapping("/{proposalId}")
	public ResultMessageResponse deleteProposal(@PathVariable Long proposalId, @RequestBody Auth auth){
		negotiationService.deleteOne(proposalId, auth.getWriter(), auth.getPassword());
		return new ResultMessageResponse("제안을 삭제했습니다.");
	}


	@PutMapping("/{proposalId}/accept")
	public ResultMessageResponse AcceptProposal(@PathVariable Long itemId, @PathVariable Long proposalId, @RequestBody NegotiationStatusUpdate request){
		negotiationService.acceptProposal(itemId, proposalId, request.getWriter(), request.getPassword(), request.getStatus());
		return new ResultMessageResponse("제안의 상태가 변경되었습니다.");
	}


	@PutMapping("/{proposalId}/confirm")
	public ResultMessageResponse confirmProposal(@PathVariable Long proposalId, @RequestBody NegotiationStatusUpdate request){
		negotiationService.confirmProposal(proposalId, request.getWriter(), request.getPassword(), request.getStatus());
		return new ResultMessageResponse("구매가 확정되었습니다.");
	}
}

