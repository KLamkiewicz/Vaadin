package pl.krzysiek.ui22;
//package pl.krzysiek.ui;
//
//import javax.servlet.annotation.WebServlet;
//
//import org.atmosphere.cache.SessionBroadcasterCache;
//
//import pl.krzysiek.LoginLayout;
//import pl.krzysiek.MainUI;
//import pl.krzysiek.RegisterLayout;
//
//import com.vaadin.annotations.PreserveOnRefresh;
//import com.vaadin.annotations.VaadinServletConfiguration;
//import com.vaadin.navigator.Navigator;
//import com.vaadin.server.Page;
//import com.vaadin.server.VaadinRequest;
//import com.vaadin.server.VaadinService;
//import com.vaadin.server.VaadinServlet;
//import com.vaadin.server.VaadinSession;
//import com.vaadin.ui.UI;
//import com.vaadin.ui.VerticalLayout;
//
//@PreserveOnRefresh
//public class NavigatorUI extends UI {
//	private Navigator navigator;
//	private RegisterLayout registerLayout;
//	private LoginLayout loginLayout;
//	private Header header;
//	private VerticalLayout wrapperLayout;
//	private int t = 0;
//	public static boolean log = true;
//
//	@Override
//	protected void init(VaadinRequest request) {
//		if(log){
//			//VaadinSession.getCurrent().getLockInstance().lock();
//	    	VaadinSession.getCurrent().getSession().setAttribute("test", "dziala");
//			System.out.println(VaadinSession.getCurrent().getSession().getAttribute("test"));
//			getPage().setLocation("/testing");
//			//VaadinSession.getCurrent().getLockInstance().unlock();
//		}else{
//			System.out.println(VaadinSession.getCurrent().getSession().getAttribute("test"));
//			try{
//				VaadinSession.getCurrent().getLockInstance().lock();
//				VaadinSession.getCurrent().getSession().invalidate();
//			}finally{
//				VaadinSession.getCurrent().getLockInstance().unlock();
//			}
//			//System.out.println(VaadinSession.getCurrent().getSession().getAttribute("test"));
////		wrapperLayout = new VerticalLayout();
////		header = new Header();
////		registerLayout = new RegisterLayout();
////		loginLayout = new LoginLayout();
////
////		navigator = new Navigator(this, this);
////		navigator.addView("", registerLayout);
////		navigator.addView("login", loginLayout);
////		navigator.navigateTo("login");
////		
////		//UI.getCurrent().getSession().setAttribute("test", 444);
////		//System.out.println(UI.getCurrent().getSession().getAttribute("test"));
////		VaadinSession.getCurrent().getLockInstance().lock();
////		VaadinSession.getCurrent().setAttribute("dudu", "dodo");
////		VaadinSession.getCurrent().getLockInstance().unlock();
////
////		System.out.println(VaadinSession.getCurrent().getAttribute("dudu"));
////		t++;
//		System.out.println(t);
//		//getPage().setLocation("/testing");
//		}
//	}
//
//	@WebServlet(value = "/grr/*", asyncSupported = true)
//	@VaadinServletConfiguration(productionMode = false, ui = NavigatorUI.class)
//	public static class Servlet extends VaadinServlet {
//		
//	}
//
//}
