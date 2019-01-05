package com.pugpro.customAnnotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailFormatValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailFormat {
	String message() default "Invalid email format.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}

