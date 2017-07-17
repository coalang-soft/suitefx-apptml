package io.github.coalangsoft.intern.suitefx.apptml;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.github.apptml.platform.AppTMLFeatures;
import io.github.coalangsoft.intern.suitefx.apptml.intern.AppTMLSuitePart;
import io.github.coalangsoft.lib.data.Func;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class SuiteFXFeatures extends AppTMLFeatures {
	
	private final ArrayList<Element> htmlMenus;
	public String title = "Dummy 'app'";
	public String menutitle = "Dummy 'name'";
	public ArrayList<AppTMLSuitePart> perspectives;
	public boolean noperspective = false;
	
	{
		htmlMenus = new ArrayList<>();
		perspectives = new ArrayList<>();
		
		add("basemenu", new Func<Element, Void>(){

			@Override
			public Void call(Element p) {
				htmlMenus.add(p);
				return null;
			}
			
		});
		add("app", new Func<Element, Void>(){

			@Override
			public Void call(Element p) {
				title = p.attr("name");
				return null;
			}
			
		});
		add("name", new Func<Element, Void>(){

			@Override
			public Void call(Element p) {
				menutitle = p.attr("name");
				return null;
			}
			
		});
		add("perspective", new Func<Element, Void>(){

			@Override
			public Void call(Element p) {
				switch(p.attr("type")){
				case "html":
					perspectives.add(new AppTMLSuitePart(p.attr("name"), p.attr("src")));
					break;
				default: throw new RuntimeException("Unknown perspective type: " + p.attr("type"));
				}
				return null;
			}
			
		});
		add("noperspective", new Func<Element, Void>(){

			@Override
			public Void call(Element p) {
				noperspective = true;
				return null;
			}
			
		});
	}

	public List<Menu> makeMenus(WebEngine e){
		ArrayList<Menu> menus = new ArrayList<>();
		for(int i = 0; i < htmlMenus.size(); i++){
			menus.add(createBaseMenu(e, htmlMenus.get(i)));
		}
		return menus;
	}
	
	private Menu createBaseMenu(WebEngine eng, Element p) {
		Menu m = new Menu(p.attr("text"));
		
		Elements submenus = p.children();
		for(int i = 0; i < submenus.size(); i++){
			Element e = submenus.get(i);
			if(e.tag().getName().equals("apptml-menu")){
				m.getItems().add(createMenu(eng, m, submenus.get(i)));
			}
		}
		
		setMenuAction(eng, m, p);
		return m;
	}

	private MenuItem createMenu(WebEngine eng, Menu m, Element element) {
		Elements sub = element.children();
		if(sub.size() == 0){
			MenuItem i = new MenuItem(element.attr("text"));
			setMenuAction(eng, i, element);
			return i;
		}
		
		Menu men = new Menu(element.attr("text"));
		for(int i = 0; i < sub.size(); i++){
			Element e = sub.get(i);
			if(e.tag().getName().equals("apptml-menu")){
				men.getItems().add(createMenu(eng, men, sub.get(i)));
			}
		}
		setMenuAction(eng, men, element);
		return men;
	}

	private void setMenuAction(final WebEngine eng, MenuItem i, Element element) {
		final String action = element.attr("action");
		if(action != null){
			i.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					eng.executeScript(action);
				}
			});
		}
	}
	
}
