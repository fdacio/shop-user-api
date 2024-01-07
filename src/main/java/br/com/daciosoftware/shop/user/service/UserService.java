package br.com.daciosoftware.shop.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.daciosoftware.shop.exceptions.InvalidUserKeyException;
import br.com.daciosoftware.shop.exceptions.UserNotFoundException;
import br.com.daciosoftware.shop.modelos.dto.UserDTO;
import br.com.daciosoftware.shop.modelos.entity.User;
import br.com.daciosoftware.shop.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(UserDTO::convert).collect(Collectors.toList());
	}

	public UserDTO findById(Long userId) {
		return userRepository.findById(userId).map(UserDTO::convert).orElseThrow(UserNotFoundException::new);
	}

	public List<UserDTO> findByNome(String nome) {
		return userRepository.findByNomeContainingIgnoreCase(nome).stream().map(UserDTO::convert)
				.collect(Collectors.toList());
	}

	public UserDTO findByCpf(String cpf) {
		return userRepository.findByCpf(cpf).map(UserDTO::convert).orElseThrow(UserNotFoundException::new);
	}

	public UserDTO save(UserDTO userDTO) {
		userDTO.setDataCadastro(LocalDateTime.now());
		userDTO.setKey(UUID.randomUUID().toString());
		User user = userRepository.save(User.convert(userDTO));
		return UserDTO.convert(user);
	}

	public void delete(Long userId) {
		userRepository.delete(User.convert(findById(userId)));
	}

	public UserDTO editUser(Long userId, UserDTO userDTO) {
		User user = User.convert(findById(userId));
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
	}

	public Page<UserDTO> getAllPage(Pageable page) {
		return userRepository.findAll(page).map(UserDTO::convert);
	}

	public List<UserDTO> updateKeyAll() {
		List<User> usuarios = userRepository.findAll();
		return usuarios.stream().map(u -> {
			u.setKey(UUID.randomUUID().toString());
			u = userRepository.save(u);
			return UserDTO.convert(u);
		}).collect(Collectors.toList());
	}

	public UserDTO validUserKey(UserDTO userDTO, String key) {
		return userRepository.findByIdAndKey(userDTO.getId(), key).map(UserDTO::convert)
				.orElseThrow(InvalidUserKeyException::new);
	}
}
