package br.com.daciosoftware.shop.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "br.com.daciosoftware.shop.user.repository" })
@ComponentScan(basePackages = { "br.com.daciosoftware.shop.user*", "br.com.daciosoftware.shop.modelos.service", "br.com.daciosoftware.shop.exceptions" })
@EntityScan(basePackages = { "br.com.daciosoftware.shop.modelos.entity" })

public class ShopUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopUserApplication.class, args);
	}

}
