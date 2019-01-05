package com.pugpro.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pugpro.DAO.UserDAO;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (new UserDAO().getUserByEmail(value) != null) //if the method returns a non-null value the email is already in use
			return false;
		else
			return true;
	}

}
