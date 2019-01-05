package com.pugpro.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeFormatValidator implements ConstraintValidator<TimeFormat, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		return value.matches("^\\d{2}:\\d{2}$"); //TODO DOESN'T CHECK FOR PROPER TIMES
	}

}
