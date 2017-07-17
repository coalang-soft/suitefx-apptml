package io.github.coalangsoft.intern.suitefx.apptml.intern;

import io.github.coalangsoft.intern.suitefx.SimpleSuitePart;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebView;

public class AppTMLSuitePart extends SimpleSuitePart<Node> {

	private String url;
	private String name;

	public AppTMLSuitePart(String name, String url) {
		this.url = url;
		this.name = name;
	}

	@Override
	public Node createView() {
		WebView v = new WebView();
		WebEngine engine = v.getEngine();
		engine.setJavaScriptEnabled(true);
		engine.executeScript("apptml = true;");
		engine.setOnError(new EventHandler<WebErrorEvent>() {
			
			@Override
			public void handle(WebErrorEvent event) {
				event.getException().printStackTrace(System.out);
			}
			
		});
		engine.load(url);
		return v;
	}
	
	public String getName(){
		return name;
	}

}
