package pl.krzysiek.view;

import pl.krzysiek.dao.UserDAO;
import pl.krzysiek.data.DataContainer;

import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class RegisterView extends VerticalLayout {

	private class FormLayoutClass extends CustomComponent {

	}

	private class UsernameValidator implements Validator {

		@Override
		public void validate(Object value) throws InvalidValueException {
			String v = (String) value;
			if (v.length() < 3)
				throw new InvalidValueException("Username too short");
			if (v.length() > 15)
				throw new InvalidValueException("Username too long");
			if(new UserDAO().getUser(v)!=null){
				throw new InvalidValueException("User already exists");
			}
		}

	}
}
