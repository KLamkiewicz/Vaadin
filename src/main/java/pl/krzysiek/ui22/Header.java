package pl.krzysiek.ui22;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class Header extends VerticalLayout implements View{

	@Override
	public void enter(ViewChangeEvent event) {
		this.addComponent(new Label("Test header"));
	}


}