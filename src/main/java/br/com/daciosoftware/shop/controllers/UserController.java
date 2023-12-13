package br.com.daciosoftware.shop.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.daciosoftware.shop.dtos.UserDTO;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	private List<UserDTO> usuarios = new ArrayList<UserDTO>();

	@PostConstruct
	public void initiateList() {
		UserDTO userDTO = new UserDTO();
		userDTO.setNome("Pedro Artur");
		userDTO.setCpf("54655203021");
		userDTO.setEndereco("Avenida Raizalm, 859");
		userDTO.setEmail("ariana.hall@geradornv.com.br");
		userDTO.setTelefone("(63) 99567-5448");
		userDTO.setDataCadastro(LocalDateTime.now());

		UserDTO userDTO2 = new UserDTO();
		userDTO2.setNome("Elimar Alfradique Richa");
		userDTO2.setCpf("55711466135");
		userDTO2.setEndereco("Rua Di Cavalcante, 877");
		userDTO2.setEmail("elimar.richa@geradornv.com.br");
		userDTO2.setTelefone("(67) 98003-6757");
		userDTO2.setDataCadastro(LocalDateTime.now());

		UserDTO userDTO3 = new UserDTO();
		userDTO3.setNome("Vivaldo Baesso da Paixão");
		userDTO3.setCpf("26336805263");
		userDTO3.setEndereco("Avenida Francisco Soares, 500");
		userDTO3.setEmail("vivaldo.paixao@geradornv.com.br");
		userDTO3.setTelefone("vivaldo.paixao@geradornv.com.br");
		userDTO3.setDataCadastro(LocalDateTime.now());

		UserDTO userDTO4 = new UserDTO();
		userDTO4.setNome("Ryan Richa Coimbra");
		userDTO4.setCpf("64822263371");
		userDTO4.setEndereco("Rua CP 18, 100");
		userDTO4.setEmail("ryan.coimbra@geradornv.com.br");
		userDTO4.setTelefone("(99) 97282-9528");
		userDTO4.setDataCadastro(LocalDateTime.now());

		usuarios.add(userDTO);
		usuarios.add(userDTO2);
		usuarios.add(userDTO3);
		usuarios.add(userDTO4);

	}

	@GetMapping
	public List<UserDTO> getUsers() {
		return usuarios;
	}
	
	@GetMapping("/{cpf}")
	public UserDTO getUsersFiltroCpf(@PathVariable String cpf) {
		return usuarios
				.stream()
				.filter(userDTO -> userDTO.getCpf().equals(cpf))
				.findFirst()
				.orElseThrow( () -> new RuntimeException("Usuário não encontrado")); 
	}
	
	@GetMapping("/nome/{nome}")
	public UserDTO getUsersFiltroNome(@PathVariable String nome) {
		return usuarios
				.stream()
				.filter(userDTO -> userDTO.getNome().contains(nome))
				.findFirst()
				.orElseThrow( () -> new RuntimeException("Usuário não encontrado")); 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO insert(@RequestBody @Valid UserDTO userDTO) {
		userDTO.setDataCadastro(LocalDateTime.now());
		usuarios.add(userDTO);
		return userDTO;
	}
	
	
	
	
}
