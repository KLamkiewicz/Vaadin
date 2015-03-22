package pl.krzysiek.ui;

import pl.krzysiek.model.User;
import pl.krzysiek.view.LoginView;
import pl.krzysiek.view.RegisterView;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

public class RegisterUI extends UI {
	private RegisterView registerView;

	@Override
	protected void init(VaadinRequest request) {
		User u = null;
		try {
			VaadinSession.getCurrent().getLockInstance().lock();
			u = (User) VaadinSession.getCurrent().getSession()
					.getAttribute("user");
			if (u!=null) {
				UI.getCurrent().getPage().setLocation("/");
			}
		} finally {
			VaadinSession.getCurrent().getLockInstance().unlock();
		}

		if (u==null) {
			registerView = new RegisterView();
			setContent(registerView);
		}
	}
}
