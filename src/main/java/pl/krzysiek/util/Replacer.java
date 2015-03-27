package pl.krzysiek.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.krzysiek.model.Message;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

public class Replacer {

	private enum Faces{
		SMILEY(":)", ":\\)","images/smiley.jpg"),
		SMILEY2(":D", ":D","images/smiley2.jpg"),
		SMILEY3(";)", ";\\)", "images/smiley3.jpg"),
		SMILEY4(":(", ":\\(", "images/smiley4.jpg"),
		SMILEY5(";(", ";\\(", "images/smiley5.jpg");
		
		private final String face;
		private final String regex;
		private final String location;
		
		Faces(String face, String regex, String location){
			this.face = face;
			this.regex = regex;
			this.location = location;
		}
	}
		
	

	public static HorizontalLayout replace(Message message){
		final HorizontalLayout hl = new HorizontalLayout();
		hl.addComponent(new Label(message.getUsername() + ": "));
		StringBuilder regex = new StringBuilder();
		Faces[] faces = Faces.values();
		Map<String, String> facesList = new HashMap<String, String>();
		for(int i=0; i<faces.length; i++){
			regex.append("(");
			if(i<faces.length-1){
				regex.append(faces[i].regex+")|");
			}else{
				regex.append(faces[i].regex+")");
			}
			facesList.put(faces[i].face, faces[i].location);			
		}
	
		Pattern stuff = Pattern.compile(regex.toString() + "|[\\w]+|[\\pP]" );
		Matcher matcher = stuff.matcher(message.getMessage());
		List<String> matchList = new ArrayList<String>();
		while (matcher.find()) {
			if(facesList.containsKey(matcher.group(0))){
				hl.addComponent(new Image(null, new ThemeResource(facesList.get(matcher.group(0)))));
			}else{
				hl.addComponent(new Label(matcher.group(0)));
			}
		    matchList.add(matcher.group(0));
		}

		return hl;	
	}
	
}
