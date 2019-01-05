package com.pugpro.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pugpro.DAO.UserDAO;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (new UserDAO().getUserByUsername(value) != null) //if the method returns a non-null value the username is already in use
			return false;
		else
			return true;
	}

}
