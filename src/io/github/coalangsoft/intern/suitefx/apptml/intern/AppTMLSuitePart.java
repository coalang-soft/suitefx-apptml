package io.github.coalangsoft.intern.suitefx.apptml.intern;

import io.github.coalangsoft.intern.suitefx.part.SimpleSuitePart;
import io.github.coalangsoft.intern.suitefx.state.PartState;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;

public class AppTMLSuitePart extends SimpleSuitePart {

	private String url;
	private String name;

	public AppTMLSuitePart(String name, String url) {
		this.url = url;
		this.name = name;
	}

	@Override
	public Node createView() {
		System.out.println("??");
		
		try{
			WebView v = new WebView();
			WebEngine engine;
			engine = v.getEngine();
			engine.setJavaScriptEnabled(true);
			engine.executeScript("apptml = true;");
			engine.setOnError(new EventHandler<WebErrorEvent>() {
				
				@Override
				public void handle(WebErrorEvent event) {
					event.getException().printStackTrace(System.out);
				}
				
			});
			engine.load(url);
			System.out.println(v);
			return v;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String getName(){
		return name;
	}

}
