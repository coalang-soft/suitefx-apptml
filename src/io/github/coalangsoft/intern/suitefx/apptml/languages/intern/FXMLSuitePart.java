package io.github.coalangsoft.intern.suitefx.apptml.languages.intern;

import java.io.IOException;
import java.net.URL;

import io.github.apptml.iface.LanguageEngine;
import io.github.apptml.platform.AppTMLPlatform;
import io.github.coalangsoft.intern.suitefx.part.SimpleSuitePart;
import io.github.coalangsoft.intern.suitefx.part.SuitePart;
import io.github.coalangsoft.intern.suitefx.state.PartState;
import io.github.coalangsoft.visit.Visitor;
import io.github.coalangsoft.visitfx.ParentChildrenVisitor;
import io.github.coalangsoft.visitfx.ScrollPaneContentVisitor;
import io.github.coalangsoft.visitfx.TabPaneContentVisitor;
import io.github.coalangsoft.visitfx.ToolbarContentVisitor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;

public class FXMLSuitePart extends SimpleSuitePart {

	private String title;
	private String url;
	private String code;
	private LanguageEngine engine;
	private AppTMLPlatform<SuitePart> platform;

	public FXMLSuitePart(AppTMLPlatform<SuitePart> platform, String title, String fxmlUrl, String codepath) {
		this.title = title;
		this.url = fxmlUrl;
		this.code = codepath;
		this.platform = platform;
	}

	@Override
	public Node createView() {
		try {
			Node view = FXMLLoader.load(new URL(url));
			if(code != null){
				String[] split = code.split("\\.");
				engine = platform.scriptLanguages.get(split[split.length - 1]).newEngine();
				
				Visitor v = new Visitor();
				v.addFunction(Node.class, (n) -> {
					Node no = (Node) n;
					if(no.getId() != null){
						engine.put(no.getId(), no);
					}
					return null;
				});
				v.addFunction(Parent.class, new ParentChildrenVisitor(v));
				v.addFunction(ScrollPane.class, new ScrollPaneContentVisitor(v));
				v.addFunction(TabPane.class, new TabPaneContentVisitor(v));
				v.addFunction(ToolBar.class, new ToolbarContentVisitor(v));
				v.handle(view);
				
				engine.evalUrl(code);
			}
			return view;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String getName(){
		return title;
	}
	
	public void restoreState(Node n, PartState s){
		try{
			engine.invoke("restoreState", n,s);
		}catch(RuntimeException e){e.printStackTrace();}
	}
	
	public void storeState(Node n, PartState s){
		try{
			engine.invoke("storeState", n,s);
		}catch(RuntimeException e){e.printStackTrace();}
	}

}
