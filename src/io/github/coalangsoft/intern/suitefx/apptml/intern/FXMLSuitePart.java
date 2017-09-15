package io.github.coalangsoft.intern.suitefx.apptml.intern;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import io.github.coalangsoft.intern.suitefx.part.SimpleSuitePart;
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
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import jdk.nashorn.api.scripting.URLReader;
import netscape.javascript.JSException;

public class FXMLSuitePart extends SimpleSuitePart{

	private String title;
	private String url;
	private String code;
	private ScriptEngine engine;

	public FXMLSuitePart(String title, String fxmlUrl, String codepath) {
		this.title = title;
		this.url = fxmlUrl;
		this.code = codepath;
	}

	@Override
	public Node createView() {
		try {
			Node view = FXMLLoader.load(new URL(url));
			if(code != null){
				String[] split = code.split("\\.");
				engine = new ScriptEngineManager().getEngineByExtension(split[split.length - 1]);
				
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
				
				engine.eval(new URLReader(new URL(code)));
			}
			return view;
		} catch (IOException | ScriptException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String getName(){
		return title;
	}
	
	public void restoreState(Node n, PartState s){
		try{
			if(engine.get("restoreState") != null){
				((ScriptObjectMirror) engine.get("restoreState")).call(new Object(), n,s);
			}
		}catch(JSException e){e.printStackTrace();}
	}
	
	public void storeState(Node n, PartState s){
		if(engine.get("storeState") != null){
			((ScriptObjectMirror) engine.get("storeState")).call(new Object(), n,s);
		}
	}

}