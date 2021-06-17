package br.com.zupacademy.templateproposta.validacao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidaEnumValidator implements ConstraintValidator<ValidaEnum, CharSequence>{

	private List<String> valores;
	
	@Override
	public void initialize(ValidaEnum aValidar) {
		valores = Stream.of(aValidar.enumClass().getEnumConstants())
						.map(Enum::name)
						.collect(Collectors.toList());
						
	}
	
	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		 if (value == null) {
	            return true;
	        }

	        return valores.contains(value.toString());
	}

}
