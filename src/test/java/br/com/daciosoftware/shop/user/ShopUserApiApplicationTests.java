package br.com.daciosoftware.shop.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.daciosoftware.shop.user.controller.UserController;

@SpringBootTest
class ShopUserApiApplicationTests {
	
	@Autowired
	private UserController userController;
	
	@Test
	void contextLoads() {
		Assertions.assertThat(userController).isNotNull();
	}

}
