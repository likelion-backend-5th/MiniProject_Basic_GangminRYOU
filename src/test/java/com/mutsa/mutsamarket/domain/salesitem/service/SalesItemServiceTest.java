package com.mutsa.mutsamarket.domain.salesitem.service;

import static com.mutsa.mutsamarket.common.fixture.FileFixture.*;
import static com.mutsa.mutsamarket.common.fixture.SalesItemFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.mutsa.mutsamarket.api.file.dto.FileResponse;
import com.mutsa.mutsamarket.api.file.service.FileService;
import com.mutsa.mutsamarket.common.fixture.FileFixture;
import com.mutsa.mutsamarket.common.fixture.SalesItemFixture;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;
import com.mutsa.mutsamarket.domain.salesitem.entity.Status;
import com.mutsa.mutsamarket.domain.salesitem.repository.SalesItemRepository;

@ExtendWith(MockitoExtension.class)
class SalesItemServiceTest {

	@Mock
	SalesItemRepository salesItemRepository;
	@Mock
	PasswordEncoder passwordEncoder;

	@Mock
	FileService fileService;

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

	@Test
	@DisplayName("상품 수정이 가능하다.")
	void updateOneTest() throws Exception {
		//given
		when(salesItemRepository.findById(anyLong())).thenReturn(Optional.of(TEST_SALES_ITEM));
		when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
		//when
		SalesItem salesItem = salesItemService.updateOne(1L, "제목42", "설명42", 42000, Status.ON_SALE, "42424");
		//then
		assertThat(TEST_SALES_ITEM.getTitle()).isEqualTo(salesItem.getTitle());
	}

	@Test
	@DisplayName("상품 이미지 수정이 가능하다.")
	void updateImageTest() throws Exception {
		//given
		when(salesItemRepository.findById(anyLong())).thenReturn(Optional.of(TEST_SALES_ITEM));
		when(fileService.storeAsHash(any(MultipartFile.class))).thenReturn(TEST_FILE_RESPONSE);
		when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
		//when
		FileResponse fileResponse = salesItemService.updateImage(
			1L,
			TEST_SALES_ITEM.getWriter(),
			TEST_SALES_ITEM.getPassword(),
			new MockMultipartFile("이미지 파일", "something.jpeg".getBytes())
		);
		//then
		assertThat(TEST_FILE_RESPONSE.getFileName()).isEqualTo(fileResponse.getFileName());
	}

}