package io.github.coalangsoft.intern.suitefx.apptml;

import java.io.File;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cpa.subos.io.BufferIOBase;
import cpa.subos.io.IO;
import io.github.apptml.iface.LanguageEngine;
import io.github.apptml.platform.AppTMLDisplay;
import io.github.coalangsoft.intern.suitefx.Styles;
import io.github.coalangsoft.intern.suitefx.SuiteView;
import io.github.coalangsoft.intern.suitefx.apptml.languages.WebEngineWrapper;
import io.github.coalangsoft.intern.suitefx.apptml.languages.intern.AppTMLSuitePart;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

public class SuiteFXDisplay implements AppTMLDisplay {
	
	private Stage stage;
	private WebEngine engine;
	private SuiteFXFeatures features;
	private SuiteView v;
	
	public SuiteFXDisplay(SuiteFXFeatures features, final String url, Document doc) {
		this.features = features;
		
		//init ui
		new JFXPanel();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage = new Stage();
				setUI(url, doc);
				System.out.println(stage);
			}
		});
		System.out.println(stage);
		while(true){
			if(stage != null) break;
			stuff(stage);
		}
		System.out.println("...");
	}
	
	private void stuff(Stage s) {
		System.out.println(s);
	}

	@Override
	public LanguageEngine getJSEngine() {
		while(engine == null);
		return WebEngineWrapper.wrap(engine);
	}
	
	private void setUI(String url, Document doc) {		
		this.v = new SuiteView(features.title, features.usesMenubar);
		if(!features.noperspective){
			v.add(new AppTMLSuitePart(features, features.menutitle, url));
		}
		for(int i = 0; i < features.perspectives.size(); i++){
			v.add(features.perspectives.get(i));
		}
		
		Scene s = new Scene(v);
		if(features.styleBase != null){
			switch(features.styleBase){
			case "dark": Styles.dark(s); break;
			case "light": Styles.light(s); break;
			}
		}
		
		//apply styles
		try{
			BufferIOBase temp = IO.buffer();
			
			Document d = features.document(url);
			
			Elements es = d.getElementsByTag("style");
			System.out.println(es.size());
			for(int i = 0; i < es.size(); i++){
				temp.writeString(es.get(i).html(), "UTF-8");
			}
			
			s.getStylesheets().add(
					IO.file(File.createTempFile("apptmlcss", ".css")).downloadFrom(temp)
			.toUrl());
			
			//from <link> tags
			es = d.getElementsByTag("link");
			System.out.println(es.size());
			for(int i = 0; i < es.size(); i++){
				Element e = es.get(i);
				if("stylesheet".equals(e.attr("rel"))){
					s.getStylesheets().add(e.attr("href"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		stage.setScene(s);
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
