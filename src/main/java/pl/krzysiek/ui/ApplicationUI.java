package pl.krzysiek.ui;

import javax.servlet.annotation.WebServlet;

import pl.krzysiek.model.User;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@Theme("mytheme")
public class ApplicationUI extends UI{
	private Navigator navigator;
	private MainView mainView;
	private OtherView otherView;
	private VerticalLayout mainContent;
	private HorizontalLayout mainLayout;
	private MenuView menuView;
	
	@Override
	protected void init(VaadinRequest request) {
		
		mainLayout = new HorizontalLayout();
		mainContent = new VerticalLayout();
		menuView = new MenuView();
		
		mainView = new MainView();
		otherView = new OtherView();
		
		
		navigator = new Navigator(this, mainContent);
		navigator.addView("", mainView);
		navigator.addView("Other", otherView);
		
		mainLayout.addComponent(menuView);
		mainLayout.addComponent(mainContent);
		
		setContent(mainLayout);
	}

	
	private class MenuView extends VerticalLayout{
		public MenuView(){
			addComponent(new Label("dd"));
			Button b = new Button("Logout");
			b.addClickListener(new LogoutButton());
			Button c = new Button("Change");
			c.addClickListener(new ChangeButton());
			Button d = new Button("Main page");
			d.addClickListener(new MainPageButton());
			addComponent(b);
			addComponent(c);
			addComponent(d);
		}
		
	}
	
	private class MainView extends VerticalLayout implements View{
		public MainView(){
			Label lab = new Label("main view lab");
			lab.setId("labtest");
			addComponent(new Label("main view"));
			addComponent(lab);
		}

		@Override
		public void enter(ViewChangeEvent event) {
			
		}
	}
	
	private class OtherView extends VerticalLayout implements View{
		public OtherView(){
			Label lab = new Label("OTTER VIEW");
			lab.setId("labtrest");
			addComponent(new Label("mainzxzx view"));
			addComponent(lab);
		}

		@Override
		public void enter(ViewChangeEvent event) {
		}
	}
	
	
	private class LogoutButton implements Button.ClickListener{
		@Override
		public void buttonClick(ClickEvent event) {
			VaadinSession.getCurrent().getSession().setAttribute("user", null);
			Page.getCurrent().setLocation("/auth/login");	
		}
	}
	
	private class ChangeButton implements Button.ClickListener{
		@Override
		public void buttonClick(ClickEvent event) {
			 navigator.navigateTo("Other");
			
		}
	}
	
	private class MainPageButton implements Button.ClickListener{
		@Override
		public void buttonClick(ClickEvent event){
			navigator.navigateTo("");
		}
	}
	
	@WebServlet(value = {"/VAADIN/*", "/main/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ApplicationUI.class)
	public static class Servlet extends VaadinServlet {
		
	}
	
}
