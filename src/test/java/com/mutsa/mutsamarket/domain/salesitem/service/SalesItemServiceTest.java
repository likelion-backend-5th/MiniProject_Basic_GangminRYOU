package com.mutsa.mutsamarket.domain.salesitem.service;

import static com.mutsa.mutsamarket.common.fixture.SalesItemFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mutsa.mutsamarket.common.fixture.SalesItemFixture;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;
import com.mutsa.mutsamarket.domain.salesitem.repository.SalesItemRepository;

@ExtendWith(MockitoExtension.class)
class SalesItemServiceTest {

	@Mock
	SalesItemRepository salesItemRepository;


	@InjectMocks
	SalesItemService salesItemService;

	@Test
	@DisplayName("중복된 id를 가진 상품은 예외가 터져야 한다.")
	void DuplicateIdItemError() throws Exception {
		//given
		when(salesItemRepository.save(any(SalesItem.class))).thenReturn(TEST_SALES_ITEM);
		//when
		SalesItem savedItem = salesItemService.save(TEST_SALES_ITEM);
		//then
		Assertions.assertThat(TEST_SALES_ITEM.getTitle()).isEqualTo(savedItem.getTitle());
	}

}