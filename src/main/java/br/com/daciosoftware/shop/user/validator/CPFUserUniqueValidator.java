package br.com.daciosoftware.shop.user.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.daciosoftware.shop.user.entity.User;
import br.com.daciosoftware.shop.user.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFUserUniqueValidator implements ConstraintValidator<CPFUserUnique, String> {

	@Autowired
	UserRepository userRepository;

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {

		Optional<User> user = userRepository.findByCpf(cpf);
		if (user.isPresent()) {
			return false;
		}

		return true;

	}

}
