package pl.krzysiek.view;

import pl.krzysiek.dao.UserDAO;
import pl.krzysiek.model.Message;
import pl.krzysiek.model.User;
import pl.krzysiek.util.Broadcaster;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class Chat extends VerticalLayout implements View {
	private TextField input;
	public Panel chatContentPanel;
	private VerticalLayout chatContent;
	private VerticalLayout chatControl;
	public VerticalLayout vPanel;
	private User user;

	public Chat() {
		user = (User) VaadinSession.getCurrent().getSession().getAttribute("user");
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

	public class SendMessageButton implements Button.ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			if (input.getValue() != "") {
				Message message = new Message();
				message.setUsername(user.getUsername());
				message.setMessage(input.getValue());

				new UserDAO().addMessageCount(user.getUsername());
				Broadcaster.broadcastMessage(message);
				input.setValue("");
			} else {
				new Notification("Message cannot be empty!").show(Page
						.getCurrent());

			}
		}
	}
}
