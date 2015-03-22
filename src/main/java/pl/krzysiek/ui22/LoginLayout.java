package pl.krzysiek.ui22;
//package pl.krzysiek;
//
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import pl.krzysiek.ui.NavigatorUI;
//
//import com.vaadin.navigator.View;
//import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.Label;
//import com.vaadin.ui.VerticalLayout;
//import com.vaadin.ui.Button.ClickEvent;
//
//public class LoginLayout extends VerticalLayout implements View {
//	private Button loginButton;
//	private MainUI main;
//	
//	public LoginLayout(MainUI m){
//		this.main = m;
//		loginButton = new Button("Login 22");
//		this.setMargin(true);
//		this.addComponent(loginButton);
//		
//		loginButton.addClickListener(new Button.ClickListener() {
//			@Override
//			public void buttonClick(ClickEvent event) {
//				NavigatorUI.log = false;
//				main.getPage().setLocation("/");
//				//main.isLogged(true);
//			}
//		});
//		
//	}
//	
//	public LoginLayout() {
//		this.addComponent(new Label("LOGIN"));
//	}
//
//	@Override
//	public void enter(ViewChangeEvent event) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	
//	
//}
