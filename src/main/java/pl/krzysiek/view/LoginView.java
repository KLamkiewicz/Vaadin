package pl.krzysiek.view;

import pl.krzysiek.data.DataContainer;
import pl.krzysiek.model.User;
import pl.krzysiek.service.LoginService;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class LoginView extends VerticalLayout {
	private Button login;

	public LoginView() {
		this.addComponent(new FormLayoutClass());
	}

	private class FormLayoutClass extends CustomComponent{
		private Button loginButton;
		private TextField username;
		private PasswordField password;
		private VerticalLayout v;
		private Notification n;
		private String invalid = "Invalid data";

		public FormLayoutClass(){
			n = new Notification(invalid);
			n.setPosition(Position.TOP_CENTER);
			v = new VerticalLayout();
			setCompositionRoot(v);
			
			loginButton = new Button("Log me in!");
	
			username = new TextField("Username: ");
			username.setRequired(true);
			username.addValidator(new UsernameValidator());
			
			password = new PasswordField("Password: ");
			password.setRequired(true);
			password.addValidator(new PasswordValidator());
			
			v.addComponent(username);
			v.addComponent(password);
			v.addComponent(loginButton);

			loginButton.addClickListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					if(username.isValid() && password.isValid()){
						if(LoginService.validCredentials(username.getValue(), password.getValue())){
							VaadinSession.getCurrent().getSession().setAttribute("user", new User(username.getValue()));
							UI.getCurrent().getPage().setLocation("/");
						}else{
							n.setCaption("Username does not match the password");
							n.show(Page.getCurrent());
						}
					}else{
						n.setCaption(invalid);
						n.show(Page.getCurrent());
					}
				}
			});
		}
	}
	
	private class UsernameValidator implements Validator{
		@Override
		public void validate(Object value) throws InvalidValueException {
			String v = (String) value;
			if(v.length()<3)
				throw new InvalidValueException("Username too short");
			if(v.length()>15)
				throw new InvalidValueException("Username too long");
		}
	}
	
	private class PasswordValidator implements Validator{
		@Override
		public void validate(Object value) throws InvalidValueException {
			String v = (String) value;
			if(v.length()<3)
				throw new InvalidValueException("Password too short");
			if(v.length()>15)
				throw new InvalidValueException("Password too long");
		}
	}

}