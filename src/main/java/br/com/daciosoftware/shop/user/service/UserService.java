package br.com.daciosoftware.shop.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.daciosoftware.shop.modelos.dto.UserDTO;
import br.com.daciosoftware.shop.modelos.entity.User;
import br.com.daciosoftware.shop.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> getAll() {
		List<User> usuarios = userRepository.findAll();
		return usuarios
				.stream()
				.map(UserDTO::convert)
				.collect(Collectors.toList());
	}
	
	public UserDTO findById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return UserDTO.convert(user.get());
		} else {
			throw new RuntimeException("Usuário não encontrado");
		}
		
	}
	
	public UserDTO save(UserDTO userDTO) {
		userDTO.setDataCadastro(LocalDateTime.now());
		User user = userRepository.save(User.convert(userDTO));
		return UserDTO.convert(user);
	}
	
	public UserDTO delete(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			userRepository.delete(user.get());
			return UserDTO.convert(user.get());
		} else {
			throw new RuntimeException("Usuário não encontrado");
		}
	}
	
	public UserDTO findByCpf(String cpf) {
		User user = userRepository.findByCpf(cpf);
		if (user != null) {
			return UserDTO.convert(user);
		} else {
			throw new RuntimeException("Usuário não encontrado");
		}
	}
	
	public List<UserDTO> findByNome(String nome) {
		List<User> usuarios = userRepository.findByNomeContaining(nome);
		return usuarios
				.stream()
				.map(UserDTO::convert)
				.collect(Collectors.toList());
	}
	
	public UserDTO editUser(Long userId, UserDTO userDTO) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			System.err.println(userDTO);
			User user = userOptional.get();
			if ((userDTO.getEmail() != null) && !(user.getEmail().equals(userDTO.getEmail()))) {
				user.setEmail(userDTO.getEmail());
			}
			if ((userDTO.getTelefone() != null) && !(user.getTelefone().equals(userDTO.getTelefone()))) {
				user.setTelefone(userDTO.getTelefone());
			}
			if ((userDTO.getEndereco() != null) && !(user.getEndereco().equals(userDTO.getEndereco()))) {
				user.setEndereco(userDTO.getEndereco());
			}
			user = userRepository.save(user);			
			return UserDTO.convert(user);
		} else {
			throw new RuntimeException("Usuário não encontrado");
		}
	}
	
	public Page<UserDTO> getAllPage(Pageable page) {
		Page<User> usuarios = userRepository.findAll(page);
		return usuarios.map(UserDTO::convert);
	}
}
