package pl.krzysiek.ui;

import javax.servlet.annotation.WebServlet;

import pl.krzysiek.model.User;
import pl.krzysiek.view.LoginView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("mytheme")
public class LoginUI extends UI {
	private LoginView loginView;

	@Override
	protected void init(VaadinRequest request) {
		loginView = new LoginView();
		setContent(loginView);
	}

	@WebServlet(value = "/auth/login/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = LoginUI.class)
	public static class Servlet extends VaadinServlet {

	}

}
