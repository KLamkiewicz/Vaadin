package pl.krzysiek.ui;

import java.util.Collection;

import javax.servlet.annotation.WebServlet;

import pl.krzysiek.dao.UserDAO;
import pl.krzysiek.model.Message;
import pl.krzysiek.model.User;
import pl.krzysiek.util.Broadcaster;
import pl.krzysiek.util.Replacer;
import pl.krzysiek.view.Chat;
import pl.krzysiek.view.TopPostersView;

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
import com.vaadin.server.ClassResource;
import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window;

@Theme("mytheme")
@Push
@PreserveOnRefresh
public class ApplicationUI extends UI implements Broadcaster.BroadcastListener {

	private Navigator navigator;
	private MainView mainView;
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
		chatView = new Chat();

		mainView = new MainView();
		navigator = new Navigator(this, mainContent);
		navigator.addView("", mainView); // MainView
		mainLayout.addComponent(menuView);
		mainLayout.addComponent(mainContent);
		
		
		menuView.setWidth("200px");
		//menuView.addStyleName("test");
		
		
		
		setContent(mainLayout);
		Broadcaster.register(this);
	}

	@WebServlet(value = { "/VAADIN/*", "/main/*" }, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ApplicationUI.class, widgetset = "pl.krzysiek.AppWidgetSet")
	public static class Servlet extends VaadinServlet {

	}

	private class MenuView extends VerticalLayout {
		public MenuView() {
			// Button logout = new Button("Logout");
			// logout.addClickListener(new LogoutButton());

			Button chatButton = new Button("Chat");
			chatButton.addClickListener(new ChangeButton());
			addComponent(chatButton);

			Button mainPageButton = new Button("Main page");
			mainPageButton.addClickListener(new MainPageButton());
			addComponent(mainPageButton);

			Button topUsers = new Button("Top");
			topUsers.addClickListener(new TopUsersButton());
			addComponent(topUsers);

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

	private class LogoutButton implements Button.ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			// // VaadinSession.getCurrent().getSession().setAttribute("user",
			// null);
			// // VaadinSession.getCurrent().close();
			// // Page.getCurrent().setLocation("/auth/login");;
			// VaadinSession.getCurrent().getSession().invalidate();
		}
	}

	//Menu buttons
	private class ChangeButton implements Button.ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {
			// chatView = new Chat();
			// chatView.setHeight("100%");
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

	public class TopUsersButton implements Button.ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {
			navigator.addView("Top", new TopPostersView());
			navigator.navigateTo("Top");
		}
	}

	public void handleMessage(final Message message) {
		final VerticalLayout windowLayout = new VerticalLayout();
		final Window window = new Window();
		final HorizontalLayout h = new HorizontalLayout();
		// Message
		// final Label label = new Label(message.getUsername() + ": "
		// +message.getMessage());
		h.addComponent(Replacer.replace(message));
		chatView.vPanel.addComponent(h);
		window.setWidth(300f, Unit.PIXELS);
		window.setContent(windowLayout);
		h.addLayoutClickListener(new LayoutClickListener() {
			@Override
			public void layoutClick(LayoutClickEvent event) {
				windowLayout.removeAllComponents();
				windowLayout.addComponent(new Label("Username: "
						+ message.getUsername()));
				windowLayout.addComponent(new Label("Number of messages: "
						+ (new UserDAO().getNumberOfMessages(message
								.getUsername()))));

				if (!UI.getCurrent().getWindows().isEmpty()) {
					for (Window w : UI.getCurrent().getWindows()) {
						w.close();
					}
				}
				UI.getCurrent().addWindow(window);
			}
		});

	}

	@Override
	public void receiveBroadcast(final Message message) {
		// Must lock the session to execute logic safely
		access(new Runnable() {
			@Override
			public void run() {
				// Append message and assign listener
				handleMessage(message);
				// Scroll down on every message
				int messageCount = chatView.vPanel.getComponentCount();
				chatView.chatContentPanel.setScrollTop(100 * messageCount);
			}
		});

	}

	@Override
	public void detach() {
		Broadcaster.unregister(this);
		super.detach();
	}

}
