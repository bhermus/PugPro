package com.pugpro.customAnnotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy=TimeFormatValidator.class)
public @interface TimeFormat {
	String message() default "Invalid time (hh:mm).";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
