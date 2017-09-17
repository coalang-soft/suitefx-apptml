package io.github.coalangsoft.intern.suitefx.apptml.languages.intern;

import java.util.List;

import io.github.coalangsoft.intern.suitefx.apptml.SuiteFXFeatures;
import io.github.coalangsoft.intern.suitefx.apptml.languages.WebEngineWrapper;
import io.github.coalangsoft.intern.suitefx.part.SimpleSuitePart;
import io.github.coalangsoft.jsearch.JSearchEngine;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebView;

public class AppTMLSuitePart extends SimpleSuitePart {

	private String url;
	private String name;
	private SuiteFXFeatures features;
	private WebEngine engine;

	public AppTMLSuitePart(SuiteFXFeatures features, String name, String url) {
		this.url = url;
		this.name = name;
		this.features = features;
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
			
			this.engine = engine;
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
	
	public List<Menu> createMenus(JSearchEngine<?> se){
		return features.makeMenus(WebEngineWrapper.wrap(engine));
	}

}
