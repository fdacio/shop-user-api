package br.com.daciosoftware.shop.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.daciosoftware.shop.exceptions.exceptions.CategoryNotFoundException;
import br.com.daciosoftware.shop.modelos.dto.product.CategoryDTO;
import br.com.daciosoftware.shop.modelos.dto.user.UserDTO;
import reactor.core.publisher.Mono;

@Service
public class CategoryService {

	@Value("${product.api.url}")
	private String productApiURL;

	public List<CategoryDTO> findCategorys(UserDTO userDTO) {

		List<CategoryDTO> categorysDTO = new ArrayList<>();

		if (userDTO.getInteresses() != null) {

			WebClient webClient = WebClient.builder().baseUrl(productApiURL).build();

			for (CategoryDTO c : userDTO.getInteresses()) {
				try {
					Long categoryId = c.getId();
					Mono<CategoryDTO> category = webClient
							.get()
							.uri("/category/" + categoryId)
							.retrieve()
							.bodyToMono(CategoryDTO.class);
					categorysDTO.add(category.block());
				} catch (Exception e) {
					e.printStackTrace();
					throw new CategoryNotFoundException();
				}
			}
		}

		return categorysDTO;
	}
}
