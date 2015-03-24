package pl.krzysiek.ui;

import javax.servlet.annotation.WebServlet;

import pl.krzysiek.model.User;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class ApplicationUI extends UI{

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout v = new VerticalLayout();
		v.addComponent(new Label("TEST MAIN"));
		Button b = new Button("Logout");
		b.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				VaadinSession.getCurrent().getSession().setAttribute("user", null);
				Page.getCurrent().setLocation("/auth/login");
			}
		});
		v.addComponent(b);
		setContent(v);
	}

	
	@WebServlet(value = {"/VAADIN/*", "/main/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ApplicationUI.class)
	public static class Servlet extends VaadinServlet {
		
	}
	
}
