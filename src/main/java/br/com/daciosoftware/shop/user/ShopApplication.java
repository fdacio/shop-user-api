package br.com.daciosoftware.shop.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "br.com.daciosoftware.shop.user.repository", "br.com.daciosoftware.shop.product.repository" })
@ComponentScan(basePackages = { "br.com.daciosoftware.shop.user*", "br.com.daciosoftware.shop.exceptions.*" })
@EntityScan(basePackages = { "br.com.daciosoftware.shop.user.entity", "br.com.daciosoftware.shop.product.entity"  })

public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}
