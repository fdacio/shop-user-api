package br.com.daciosoftware.shop.user.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.daciosoftware.shop.exceptions.exceptions.InvalidUserKeyException;
import br.com.daciosoftware.shop.exceptions.exceptions.UserCpfExistsException;
import br.com.daciosoftware.shop.exceptions.exceptions.UserEmailExistsException;
import br.com.daciosoftware.shop.exceptions.exceptions.UserNotFoundException;
import br.com.daciosoftware.shop.modelos.dto.user.UserDTO;
import br.com.daciosoftware.shop.modelos.entity.product.Category;
import br.com.daciosoftware.shop.modelos.entity.user.User;
import br.com.daciosoftware.shop.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryService categoryService;

	public List<UserDTO> findAll() {
		return userRepository.findAll()
				.stream()
				.sorted(Comparator.comparing(User::getId))
				.map(UserDTO::convert)
				.collect(Collectors.toList());	
	}

	public UserDTO findById(Long userId) {
		return userRepository.findById(userId)
				.map(UserDTO::convert)
				.orElseThrow(UserNotFoundException::new);
	}

	public List<UserDTO> findByNome(String nome) {
		return userRepository.findByNomeContainingIgnoreCase(nome)
				.stream()
				.map(UserDTO::convert)
				.collect(Collectors.toList());
	}

	public UserDTO findByCpf(String cpf) {
		return userRepository.findByCpf(cpf)
				.map(UserDTO::convert)
				.orElseThrow(UserNotFoundException::new);
	}
	
	public UserDTO findByEmail(String email) {
		return userRepository.findByEmail(email)
				.map(UserDTO::convert)
				.orElseThrow(UserNotFoundException::new);
	}
	
	private void validCpfUnique(String cpf) {
		Optional<UserDTO> userDTO = userRepository.findByCpf(cpf).map(UserDTO::convert);
		if (userDTO.isPresent()) {
			throw new UserCpfExistsException();
		}
	}
	
	private void validEmailUnique(String email, Long id) {
		Optional<UserDTO> userDTO = userRepository.findByEmail(email).map(UserDTO::convert);
		if (userDTO.isPresent()) {
			if (id == null) {
				throw new UserEmailExistsException();
			} else if (id != userDTO.get().getId()) {
				throw new UserEmailExistsException();
			}
		}
	}

	public UserDTO save(UserDTO userDTO) {
		userDTO.setDataCadastro(LocalDateTime.now());
		userDTO.setKey(UUID.randomUUID().toString());
		userDTO.setInteresses(categoryService.findCategorys(userDTO));
		validCpfUnique(userDTO.getCpf());
		validEmailUnique(userDTO.getEmail(), null);
		return UserDTO.convert(userRepository.save(User.convert(userDTO)));
	}

	public void delete(Long userId) {
		userRepository.delete(User.convert(findById(userId)));
	}

	public UserDTO update(Long userId, UserDTO userDTO) {
		
		User user = User.convert(findById(userId));
		
		if (userDTO.getEndereco() != null) { 
			boolean isEnderecoAlterado = !(user.getEndereco().equals(userDTO.getEndereco())); 
			if (isEnderecoAlterado) {
				user.setEndereco(userDTO.getEndereco());
			}
		}
		
		if (userDTO.getEmail() != null) { 
			boolean isEmailAlterado = !(user.getEmail().equals(userDTO.getEmail()));
			if (isEmailAlterado) {
				validEmailUnique(userDTO.getEmail(), userId);
				user.setEmail(userDTO.getEmail());
			}
		}
		
		if (userDTO.getTelefone() != null) {
			boolean isTelefoneAlterado = !(user.getTelefone().equals(userDTO.getTelefone()));
			if (isTelefoneAlterado) {
				user.setTelefone(userDTO.getTelefone());
			}
		}
		
		if ((userDTO.getInteresses() != null)) {
			userDTO.setInteresses(categoryService.findCategorys(userDTO));
			user.setInteresses(userDTO.getInteresses().stream().map(Category::convert).collect(Collectors.toList()));
		}
		
		return UserDTO.convert(userRepository.save(user));
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
