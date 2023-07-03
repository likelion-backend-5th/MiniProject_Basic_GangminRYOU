package com.mutsa.mutsamarket.common.fixture;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.util.ReflectionTestUtils;

import com.mutsa.mutsamarket.web.salesitem.dto.request.SalesItemSave;
import com.mutsa.mutsamarket.domain.salesitem.entity.SalesItem;

public class SalesItemFixture {


	public static final Long TEST_ID = 1L;
	public static final SalesItem TEST_SALES_ITEM = createSalesItemWithId(TEST_ID);

	public static final SalesItemSave TEST_SALES_ITEM_SAVE = SalesItemSave.builder()
		.title("제목1")
		.description("상품 설명1")
		.minPriceWanted(1000)
		.writer("작성자1")
		.password("1234")
		.build();
	public static final List<SalesItem> TEST_SALES_ITEM_LIST = createSalesItemList();

	private static SalesItem createSalesItem(){
		return SalesItem.builder()
			.title("상품명")
			.description("상품 설명")
			.minPriceWanted(100)
			.writer("작성자")
			.password("1234")
			.build();
	}

	private static List<SalesItem> createSalesItemList(){
		List<SalesItem> salesItemList = new ArrayList<>();
		salesItemList.add(SalesItemFixture.createSalesItemWithId(1L));
		salesItemList.add(SalesItemFixture.createSalesItemWithId(2L));
		salesItemList.add(SalesItemFixture.createSalesItemWithId(3L));
		salesItemList.add(SalesItemFixture.createSalesItemWithId(4L));
		salesItemList.add(SalesItemFixture.createSalesItemWithId(5L));
		return salesItemList;
	}


	public static SalesItem createSalesItemWithId(Long id){
		SalesItem salesItem = createSalesItem();
		ReflectionTestUtils.setField(salesItem, "id", id);
		return salesItem;
	}
}
