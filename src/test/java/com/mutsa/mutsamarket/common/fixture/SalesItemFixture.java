package com.mutsa.mutsamarket.common.fixture;

import org.springframework.test.util.ReflectionTestUtils;

import com.mutsa.mutsamarket.domain.salesitem.dto.request.SalesItemSave;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;

public class SalesItemFixture {


	public static final Long TEST_ID = 1L;
	public static final SalesItem TEST_SALES_ITEM = createSalesItemWithId(TEST_ID);

	public static SalesItemSave TEST_SALES_ITEM_SAVE = SalesItemSave.builder()
		.title("제목1")
		.description("상품 설명1")
		.minPriceWanted(1000)
		.writer("작성자1")
		.build();


	private static SalesItem createSalesItem(){
		return SalesItem.builder()
			.title("상품명")
			.description("상품 설명")
			.minPriceWanted(100)
			.writer("작성자")
			.build();
	}


	public static SalesItem createSalesItemWithId(Long id){
		SalesItem salesItem = createSalesItem();
		ReflectionTestUtils.setField(salesItem, "id", id);
		return salesItem;
	}
}
