package pl.krzysiek.ui22;
//package pl.krzysiek;
//
//import javax.servlet.annotation.WebServlet;
//
//import com.vaadin.annotations.Theme;
//import com.vaadin.annotations.VaadinServletConfiguration;
//import com.vaadin.navigator.Navigator;
//import com.vaadin.navigator.View;
//import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
//import com.vaadin.server.VaadinRequest;
//import com.vaadin.server.VaadinService;
//import com.vaadin.server.VaadinServlet;
//import com.vaadin.server.VaadinSession;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.Button.ClickEvent;
//import com.vaadin.ui.Component;
//import com.vaadin.ui.Label;
//import com.vaadin.ui.UI;
//import com.vaadin.ui.VerticalLayout;
//
//@Theme("mytheme")
//@SuppressWarnings("serial")
//public class MainUI extends UI implements View{
//	
//	private View loginL;
//	private boolean logged = false;
//	final VerticalLayout mainLayout = new VerticalLayout();
//	
//    @Override
//    protected void init(VaadinRequest request) {
//
//		
//    	loginL = (View) new LoginLayout(this);
//    	isLogged(logged);
//    	System.out.println(VaadinSession.getCurrent().getSession().getAttribute("test") + " main ui");
//    }
//    
//    public void loggedInLayout(){
//    	mainLayout.setMargin(true);
//    	setContent(mainLayout);
//    	Label l = new Label("label");
//    	mainLayout.addComponent(l);
//    	Button button = new Button("log me");
//    }
//
//
//    public boolean isLogged(boolean l){
//    	if(!l){
//    		setContent((Component) loginL);
//    	}else{
//    		loggedInLayout();
//    	}
//    	return false;
//    }
//   
//    @WebServlet(value = "/testing/*", asyncSupported = true)
//    @VaadinServletConfiguration(productionMode = false, ui = MainUI.class)
//    public static class Servlet extends VaadinServlet {
//    }
//
//	@Override
//	public void enter(ViewChangeEvent event) {
//		// TODO Auto-generated method stub
//		
//	}
//    
//}
