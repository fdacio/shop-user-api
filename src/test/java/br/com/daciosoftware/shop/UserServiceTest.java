package br.com.daciosoftware.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.daciosoftware.shop.user.dto.UserDTO;
import br.com.daciosoftware.shop.user.entity.User;
import br.com.daciosoftware.shop.user.repository.UserRepository;
import br.com.daciosoftware.shop.user.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	public static User getUser(Long id, String nome, String cpf) {
		User user = new User();
		user.setId(id);
		user.setNome(nome);
		user.setCpf(cpf);
		user.setEmail("usuario@exemplo.com");
		user.setEndereco("Rua Alberto Torres, 200");
		user.setTelefone("(85) 9 9971-8151");
		return user;
	}
	
	@Test
	public void testFindAllUser() {
		
		List<User> users = new ArrayList<>();
		
		users.add(getUser(1L, "Jose Ribamar Silva", "87459974496"));
		users.add(getUser(2L, "Zenilde Martins Braga Silva", "74598786152"));
		users.add(getUser(3L, "Francisco Dacio M B Silva", "80978380363"));
		
		Mockito.when(userRepository.findAll()).thenReturn(users);
		
		List<UserDTO> usersReturn = userService.findAll();
		
		Assertions.assertEquals(3, usersReturn.size());
	}
	
	@Test
	public void testFindByIdUser() {		
		
		User user = getUser(3L, "Francisco Dacio M B Silva", "80978380363");
		
		Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user));
		
		UserDTO userReturn = userService.findById(2L);
		
		Assertions.assertEquals("Francisco Dacio M B Silva", userReturn.getNome());;
	}
	
	@Test
	public void testFindUserCpf() {
		
		User user = getUser(3L, "Francisco Dacio M B Silva", "80978380363");
		
		Mockito.when(userRepository.findByCpf("80978380363")).thenReturn(Optional.of(user));
		
		UserDTO userReturn = userService.findByCpf("80978380363");
		
		Assertions.assertEquals("Francisco Dacio M B Silva", userReturn.getNome());
	}
}
