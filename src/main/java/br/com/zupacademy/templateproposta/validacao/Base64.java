package br.com.zupacademy.templateproposta.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { ValidaBase64.class })
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64 {
	
	String message() default "A string passada não é base64.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
