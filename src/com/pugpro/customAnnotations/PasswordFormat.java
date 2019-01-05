package com.pugpro.customAnnotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target( {ElementType.FIELD, ElementType.METHOD })
@Constraint(validatedBy=PasswordFormatValidator.class)
public @interface PasswordFormat {
	String message() default "Password must contain a capital letter, number, and special character.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}


