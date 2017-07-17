package io.github.coalangsoft.intern.suitefx.apptml;

import io.github.apptml.platform.AppTMLDisplay;
import io.github.coalangsoft.intern.suitefx.SuiteView;
import io.github.coalangsoft.intern.suitefx.apptml.intern.AppTMLSuitePart;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

public class SuiteFXDisplay implements AppTMLDisplay<WebEngine> {
	
	private Stage stage;
	private WebEngine engine;
	private SuiteFXFeatures features;
	private SuiteView v;
	
	public SuiteFXDisplay(SuiteFXFeatures features, final String url) {
		this.features = features;
		
		//init ui
		new JFXPanel();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage = new Stage();
				setUI(url);
			}
		});
		while(stage == null);
	}
	
	@Override
	public WebEngine getJSEngine() {
		while(engine == null);
		return engine;
	}
	
	private void setUI(String url) {
		this.v = new SuiteView(features.title);
		if(!features.noperspective){
			v.add(new AppTMLSuitePart(features.menutitle, url));
		}
		for(int i = 0; i < features.perspectives.size(); i++){
			v.add(features.perspectives.get(i));
		}
		stage.setScene(new Scene(v));
	}

	@Override
	public void show() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.show();
			}
		});
	}

}
