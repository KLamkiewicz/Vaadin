package pl.krzysiek.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class UserRepositoryView extends VerticalLayout implements View{

	public UserRepositoryView() {
		Label lab = new Label("USER VIEW");
		addComponent(new Label("HERE IS "));
		addComponent(lab);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println(event.getParameters());
		
	}

}
