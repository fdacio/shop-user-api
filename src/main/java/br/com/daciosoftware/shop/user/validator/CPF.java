package br.com.daciosoftware.shop.user.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = { CpfValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface CPF {
	
	  String message() default "CPF inválido";

	  Class<?>[] groups() default {};

	  Class<? extends Payload>[] payload() default {};
}
