package br.com.zupacademy.templateproposta.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidaBase64 implements ConstraintValidator<Base64, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value == null) {
			return true;
		}
		try {
			byte[] decode = java.util.Base64.getDecoder().decode(value.getBytes());
			new String(decode);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
