package br.com.daciosoftware.shop.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.daciosoftware.shop.modelos.dto.user.UserDTO;
import br.com.daciosoftware.shop.user.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<UserDTO> findAll() {
		return userService.findAll();
	}
	
	@GetMapping("/{id}")
	public UserDTO findById(@PathVariable Long id) {
		return userService.findById(id);
	}
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO save(@RequestBody @Valid UserDTO userDTO) {
		return userService.save(userDTO);
	}
	
	@GetMapping("/{cpf}/cpf")
	public UserDTO findByCpf(@PathVariable String cpf) {
		return userService.findByCpf(cpf);
	}
	
	@GetMapping("/{email}/email")
	public UserDTO findByEmail(@PathVariable String email) {
		return userService.findByEmail(email);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
	
	@GetMapping("/search")
	public List<UserDTO> findByNone(@RequestParam(name = "nome", required = true) String nome) {
		return userService.findByNome(nome);
	}
	
	@PatchMapping("/{id}")
	public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		return userService.update(id, userDTO);
	}
	
	@GetMapping("/pageable")
	public Page<UserDTO> getUserPage(Pageable page) {
		return userService.getAllPage(page);
	}	
	
	@PatchMapping("/update-key-all")
	public List<UserDTO> updateKeyAll() {
		return userService.updateKeyAll();
	}
	
	@PostMapping("/valid")
	public UserDTO validUser(@RequestBody UserDTO userDTO, @RequestHeader(required = true) String key) {
		return userService.validUserKey(userDTO, key);
	}
}
