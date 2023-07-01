package com.mutsa.mutsamarket.domain.salesitem.service;

import static com.mutsa.mutsamarket.common.fixture.SalesItemFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mutsa.mutsamarket.common.fixture.SalesItemFixture;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;
import com.mutsa.mutsamarket.domain.salesitem.repository.SalesItemRepository;

@ExtendWith(MockitoExtension.class)
class SalesItemServiceTest {

	@Mock
	SalesItemRepository salesItemRepository;


	@InjectMocks
	SalesItemService salesItemService;

	static List<SalesItem> list = new ArrayList<>();
	static PageRequest pageRequest;
	@BeforeEach
	void init(){
		list.add(SalesItemFixture.createSalesItemWithId(1L));
		list.add(SalesItemFixture.createSalesItemWithId(2L));
		list.add(SalesItemFixture.createSalesItemWithId(3L));
		list.add(SalesItemFixture.createSalesItemWithId(4L));
		list.add(SalesItemFixture.createSalesItemWithId(5L));
		pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "minPriceWanted"));
	}


	@Test
	@DisplayName("중복된 id를 가진 상품은 예외가 터져야 한다.")
	void DuplicateIdItemError() throws Exception {
		//given
		when(salesItemRepository.save(any(SalesItem.class))).thenReturn(TEST_SALES_ITEM);
		//when
		SalesItem savedItem = salesItemService.save(TEST_SALES_ITEM);
		//then
		assertThat(TEST_SALES_ITEM.getTitle()).isEqualTo(savedItem.getTitle());
	}

	@Test
	@DisplayName("전체조회가 가능하다.")
	void readAllTest() throws Exception {
		//given


		when(salesItemRepository.findAll()).thenReturn(list);
		//when
		List<SalesItem> salesItems = salesItemService.readAll();
		//then
		assertThat(salesItems.size()).isEqualTo(list.size());
	}

	@Test
	@DisplayName("페이지 단위 조회가 가능하다.")
	void readPages() throws Exception {
		//given
		List<SalesItem> pageList = list.subList(0, 2);
		Page<SalesItem> page = new PageImpl<>(pageList, pageRequest, list.size());

		when(salesItemRepository.findAll(any(Pageable.class))).thenReturn(page);

		//when
		Page<SalesItem> salesItemsPage = salesItemService.readAllWithPage(pageRequest);

		//then
		assertThat(salesItemsPage.getContent()).hasSameSizeAs(pageList);
		assertThat(salesItemsPage.getTotalElements()).isEqualTo(list.size());
		assertThat(salesItemsPage.getPageable()).isEqualTo(pageRequest);

		verify(salesItemRepository).findAll(pageRequest);
	}

}