package com.shop.GoodsShop;

import com.shop.GoodsShop.Controller.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GoodsShopApplicationTests {
	@Autowired
	private MainController mainController;

	@Test
	void contextLoads() {
		assertThat(mainController).isNotNull();
	}

}
