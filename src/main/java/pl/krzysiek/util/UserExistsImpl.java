package pl.krzysiek.util;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.krzysiek.dao.UserDAO;

public class UserExistsImpl implements ConstraintValidator<UserExists, Object> {

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext arg1) {
		if(new UserDAO().getUser((String)object)!=null){
			return false;
		}
		return true;
	}

	@Override
	public void initialize(UserExists arg0) {
		
	}

}
