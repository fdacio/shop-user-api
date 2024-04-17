package br.com.daciosoftware.shop.user.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.daciosoftware.shop.exceptions.UserCpfExistsException;
import br.com.daciosoftware.shop.user.dto.UserDTO;
import br.com.daciosoftware.shop.user.entity.User;
import br.com.daciosoftware.shop.user.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFUserUniqueValidator implements ConstraintValidator<CPFUserUnique, UserDTO> {

	@Autowired
	UserRepository userRepository;

	@Override
	public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {

		String cpf = userDTO.getCpf();
		Long id = userDTO.getId();
		
		boolean result = true;
		
		if (id == null) {
			
			Optional<User> user = userRepository.findByCpf(cpf);
			if (user.isPresent()) {
				throw new UserCpfExistsException();
			}

		} else {
			
			Optional<User> user = userRepository.findById(id);
			Optional<User> userOther = userRepository.findByCpf(cpf);
			
			if (user.get().getId() != userOther.get().getId()) {
				throw new UserCpfExistsException();
			}
			
		}
		
		return result;

	}

}
