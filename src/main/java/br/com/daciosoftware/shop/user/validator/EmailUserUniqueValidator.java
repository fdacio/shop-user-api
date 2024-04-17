package br.com.daciosoftware.shop.user.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.daciosoftware.shop.exceptions.UserEmailExistsException;
import br.com.daciosoftware.shop.user.entity.User;
import br.com.daciosoftware.shop.user.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailUserUniqueValidator implements ConstraintValidator<EmailUserUnique, String> {

	@Autowired
	UserRepository userRepository;

	private Long id = null;
	
	@Override
	public void initialize(EmailUserUnique annotation) {
		if (annotation.id() != 0) {
			this.id = (Long)(annotation.id());
		}
	}

	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {

		boolean result = true;

		if (id == null) {

			Optional<User> user = userRepository.findByEmail(email);
			if (user.isPresent()) {
				throw new UserEmailExistsException();
			}

		} else {

			Optional<User> user = userRepository.findById(id);
			Optional<User> userOther = userRepository.findByEmail(email);
			System.err.println(id);
			System.err.println(email);
			System.err.println(user);
			System.err.println(userOther);
			if (userOther.isPresent()) {
				if (user.get().getId() != userOther.get().getId()) {
					throw new UserEmailExistsException();
				}
			}

		}

		return result;

	}

}
