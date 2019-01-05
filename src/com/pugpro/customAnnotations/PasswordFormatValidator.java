package com.pugpro.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordFormatValidator implements ConstraintValidator<PasswordFormat, String> {

	@Override
	public void initialize(PasswordFormat constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.length() < 8 || value.length() > 128) //the length of the password is handled by a different annotation, so return true to avoid multiple errors
			return true;
		if (value.matches(".*[A-Z]+.*") //must contain a capital letter
			&& value.matches(".*[0-9]+.*") //must contain a digit
			&& value.matches(".*[!@#$%^&*]+.*")) { //must contain a special character
			return true;
		}
		
		return false;
	}
	
}
