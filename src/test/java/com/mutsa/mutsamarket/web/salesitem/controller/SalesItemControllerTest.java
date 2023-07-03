package com.mutsa.mutsamarket.web.salesitem.controller;

import static com.mutsa.mutsamarket.common.fixture.SalesItemFixture.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mutsa.mutsamarket.web.salesitem.dto.SalesItemMapper;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;
import com.mutsa.mutsamarket.domain.salesitem.service.SalesItemService;
import com.mutsa.mutsamarket.utils.JsonUtil;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SalesItemControllerTest {

	@Mock
	SalesItemService salesItemService;
	@Mock
	SalesItemMapper salesItemMapper;

	@InjectMocks
	SalesItemController salesItemController;

	@Autowired
	MockMvc mockMvc;



	@BeforeEach
	void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup(salesItemController).build();
		when(salesItemMapper.toEntity(TEST_SALES_ITEM_SAVE)).thenReturn(TEST_SALES_ITEM);
		when(salesItemService.save(TEST_SALES_ITEM)).thenReturn(TEST_SALES_ITEM);
	}

	@Test
	@DisplayName("사용자는 상품을 등록할수 있어야 한다.")
	void testSave() throws Exception {
		//given & when

		ResultActions action = mockMvc.perform(post("/items")
			.contentType(MediaType.APPLICATION_JSON)
			.content(JsonUtil.toJson(TEST_SALES_ITEM_SAVE)));

		//then
		action.andExpect(status().isOk());
	}

	@Test
	@DisplayName("사용자는 상품을 페이지 단위로 조회할수 있어야한다.")
	void readOneTest() throws Exception {
		//given
		PageRequest pageRequest = PageRequest.of(0, 3);
		PageImpl<SalesItem> pageResult = new PageImpl<>(TEST_SALES_ITEM_LIST, pageRequest, 2);
		when(salesItemService.readAllWithPage(any(Pageable.class))).thenReturn(pageResult);
		//expected
		mockMvc.perform(get("/items?page=0&limit=3")
				.accept(MediaType.APPLICATION_JSON)
				.param("page", "0")
				.param("limit", "3"))
			.andExpect(status().isOk())
			.andDo(print());

	}
}