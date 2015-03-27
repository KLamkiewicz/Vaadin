package pl.krzysiek.view;

import java.util.HashMap;
import java.util.List;

import pl.krzysiek.dao.UserDAO;
import pl.krzysiek.model.User;

import com.vaadin.addon.charts.*;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class TopPostersView extends VerticalLayout implements View {

	public TopPostersView(){
		UserDAO udao = new UserDAO();
		
		Chart chart = new Chart();
		chart.setWidth("800px");
		chart.setHeight("700px");
		
		Configuration conf = chart.getConfiguration();
		conf.setTitle("Top users");
		conf.setSubTitle("Top 3 users by message count");
		
		List<User> top = udao.getTopPosters();

		if(top.size()>=3){
			ListSeries listSeries = new ListSeries("Messages");
			listSeries.setData(top.get(0).getMessages(), top.get(1).getMessages(), top.get(2).getMessages());
			conf.addSeries(listSeries);
			XAxis xaxis = new XAxis();
			xaxis.setCategories(top.get(0).getUsername(), top.get(1).getUsername(), top.get(2).getUsername());
			conf.addxAxis(xaxis);
			YAxis yaxis = new YAxis();
			yaxis.setTitle("Number of messages");
			conf.addyAxis(yaxis);
		}else{
			addComponent(new Label("Not enough data"));
		}
		
		addComponent(chart);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
