package com.mutsa.mutsamarket.web.salesitem.controller;

import static com.mutsa.mutsamarket.common.fixture.SalesItemFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mutsa.mutsamarket.common.fixture.SalesItemFixture;
import com.mutsa.mutsamarket.domain.salesitem.dto.SalesItemMapper;
import com.mutsa.mutsamarket.domain.salesitem.dto.request.SalesItemSave;
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

		ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post("/items")
			.contentType(MediaType.APPLICATION_JSON)
			.content(JsonUtil.toJson(TEST_SALES_ITEM_SAVE)));

		//then
		action.andExpect(MockMvcResultMatchers.status().isOk());
	}




}