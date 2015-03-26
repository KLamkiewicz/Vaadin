package pl.krzysiek.ui;

import java.util.Collection;

import javax.servlet.annotation.WebServlet;

import pl.krzysiek.dao.UserDAO;
import pl.krzysiek.model.Message;
import pl.krzysiek.model.User;
import pl.krzysiek.util.Broadcaster;
import pl.krzysiek.view.UserRepositoryView;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

@Theme("mytheme")
@Push
@PreserveOnRefresh
public class ApplicationUI extends UI implements Broadcaster.BroadcastListener {

	private Navigator navigator;
	private MainView mainView;
	private OtherView otherView;
	private VerticalLayout mainContent;
	private HorizontalLayout mainLayout;
	private MenuView menuView;
	private Chat chatView;
	private TextField input = new TextField();
	private User user;

	@Override
	protected void init(VaadinRequest request) {
		user = ((User) VaadinSession.getCurrent().getSession()
				.getAttribute("user"));
		mainLayout = new HorizontalLayout();
		mainContent = new VerticalLayout();
		menuView = new MenuView();
		// chatView = new ChatView(this);

		mainView = new MainView();
		otherView = new OtherView();

		navigator = new Navigator(this, mainContent);
		navigator.addView("", mainView);
		mainLayout.addComponent(menuView);
		mainLayout.addComponent(mainContent);

		setContent(mainLayout);
		Broadcaster.register(this);
	}

	private class MenuView extends VerticalLayout {
		public MenuView() {
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
			addComponent(input);
		}

	}

	private class MainView extends VerticalLayout implements View {
		public MainView() {
			Label l = new Label(((User) VaadinSession.getCurrent().getSession()
					.getAttribute("user")).getUsername());
			Label lab = new Label("main view lab");
			lab.setId("labtest");
			addComponent(new Label("main view"));
			addComponent(lab);
			addComponent(l);
		}

		@Override
		public void enter(ViewChangeEvent event) {

		}
	}

	private class OtherView extends VerticalLayout implements View {
		public OtherView() {
			Label lab = new Label("OTTER VIEW");
			lab.setId("labtrest");
			addComponent(new Label("mainzxzx view"));
			addComponent(lab);
		}

		@Override
		public void enter(ViewChangeEvent event) {
		}
	}

	private class LogoutButton implements Button.ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			// // VaadinSession.getCurrent().getSession().setAttribute("user",
			// null);
			// // VaadinSession.getCurrent().close();
			// // Page.getCurrent().setLocation("/auth/login");
			//
			// for( final UI ui : VaadinSession.getCurrent().getUIs() ) {
			// ui.access(new Runnable() {
			// @Override
			// public void run() {
			// //ui.getPushConfiguration().setPushMode(PushMode.DISABLED);
			// ui.getPushConnection().disconnect();
			// }
			// });
			// }
			// VaadinSession.getCurrent().getSession().setAttribute("user",
			// null);
			// VaadinSession.getCurrent().getSession().invalidate();
		}
	}

	private class ChangeButton implements Button.ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {
			chatView = new Chat();
			chatView.setHeight("100%");
			navigator.addView("Chat", chatView);
			navigator.navigateTo("Chat");
		}
	}

	private class MainPageButton implements Button.ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			navigator.navigateTo("");
		}
	}

	@WebServlet(value = { "/VAADIN/*", "/main/*" }, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ApplicationUI.class)
	public static class Servlet extends VaadinServlet {

	}

	@Override
	public void detach() {
		Broadcaster.unregister(this);
		super.detach();
	}
	
	private class Chat extends VerticalLayout implements View {
		private TextField input;
		private Panel chatContentPanel;
		private VerticalLayout chatContent;
		private VerticalLayout chatControl;
		private VerticalLayout vPanel;
		
		public Chat() {
			chatContentPanel = new Panel();
			chatContent = new VerticalLayout();
			chatControl = new VerticalLayout();
			vPanel = new VerticalLayout();
			
			chatContentPanel.setContent(chatContent);
			chatContentPanel.setContent(vPanel);
			chatContentPanel.setHeight("500px");
			chatContentPanel.setWidth("800px");
			input = new TextField();
			chatControl.addComponent(input);
			Button sendButton = new Button("Send message");
			sendButton.setClickShortcut(KeyCode.ENTER);
			sendButton.addClickListener(new SendMessageButton());
			chatControl.addComponent(sendButton);
			
			addComponent(chatContentPanel);
			addComponent(chatContent);
			addComponent(chatControl);

		}

		@Override
		public void enter(ViewChangeEvent event) {
		}

		private class SendMessageButton implements Button.ClickListener {
			@Override
			public void buttonClick(ClickEvent event) {
				if (input.getValue() != "") {
					Message message = new Message();
					message.setUsername(user.getUsername());
					message.setMessage(input.getValue());
					
					new UserDAO().addMessageCount(user.getUsername());
					Broadcaster.broadcastMessage(message);
					input.setValue("");
				}else{
					new Notification("Message cannot be empty!").show(Page.getCurrent());
				}
			}
		}
	}

	@Override
	public void receiveBroadcast(final Message message) {
		// Must lock the session to execute logic safely
		access(new Runnable() {
			@Override
			public void run() {

				//Append message and assign listener
				handleMessage(message);
				
				//Scroll down on every message
                int messageCount = chatView.vPanel.getComponentCount();
                chatView.chatContentPanel.setScrollTop(100*messageCount);
			}
		});
		
	}

	public void handleMessage(final Message message){
		final VerticalLayout windowLayout = new VerticalLayout();
		final Window window = new Window();
		final HorizontalLayout h = new HorizontalLayout();
		final Label label = new Label(message.getUsername() + ": " +message.getMessage());
		h.addComponent(label);
		chatView.vPanel.addComponent(h);
		window.setWidth(300f, Unit.PIXELS);
		window.setContent(windowLayout);
		
		h.addLayoutClickListener(new LayoutClickListener() {
			@Override
			public void layoutClick(LayoutClickEvent event) {
				windowLayout.removeAllComponents();
				windowLayout.addComponent(new Label("Username: " + message.getUsername()));
				windowLayout.addComponent(new Label("Number of messages: " + (new UserDAO().getNumberOfMessages(message.getUsername()))));

				UI.getCurrent().addWindow(window);
				System.out.println("CLICKED");
				System.out.println(message.getUsername());
			}
		});
	}

}
