package com.pugpro.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		return value.matches("^\\d{4}-\\d{2}-\\d{2}$"); //TODO DOESN'T CHECK FOR PROPER DATES
	}

}
