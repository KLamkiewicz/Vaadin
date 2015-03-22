package pl.krzysiek.view;

import pl.krzysiek.dao.UserDAO;
import pl.krzysiek.data.DataContainer;

import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class RegisterView extends VerticalLayout {

	public RegisterView(){
		addComponent(new FormLayoutClass());
	}
	private class FormLayoutClass extends CustomComponent {
		private VerticalLayout v;
		private Button register;
		private TextField username;
		private PasswordField password;
		private PasswordField matchingPassword;
		
		public FormLayoutClass(){
			v = new VerticalLayout();
			setCompositionRoot(v);
			register = new Button("Register");
			username = new TextField("Username: ");
			username.addValidator(new UsernameValidator());
			password = new PasswordField("Password: ");
			matchingPassword = new PasswordField("Matching password: ");
			
			v.addComponent(username);
			v.addComponent(password);
			v.addComponent(matchingPassword);
			v.addComponent(register);

			register.addClickListener(new Button.ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					// TODO
				}
			});
		}
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
	
	private class PasswordValidator implements Validator{

		@Override
		public void validate(Object value) throws InvalidValueException {
			// TODO 
			
		}
		
	}
}
