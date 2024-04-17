package br.com.daciosoftware.shop.user.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.daciosoftware.shop.exceptions.UserCpfExistsException;
import br.com.daciosoftware.shop.user.entity.User;
import br.com.daciosoftware.shop.user.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFUserUniqueValidator implements ConstraintValidator<CPFUserUnique, String> {

	@Autowired
	UserRepository userRepository;
	
	private Long id = null;
	
	@Override
	public void initialize(CPFUserUnique annotation) {
		if (annotation.id() != 0) {
			this.id = (Long)(annotation.id());
		}
	}

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {

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
