package io.github.coalangsoft.intern.suitefx.apptml;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.github.apptml.iface.LanguageEngine;
import io.github.apptml.platform.AppTMLFeatures;
import io.github.coalangsoft.intern.suitefx.part.SuitePart;
import io.github.coalangsoft.lib.data.Func;
import io.github.coalangsoft.lib.security.SecurityProfile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class SuiteFXFeatures extends AppTMLFeatures<SuitePart> {
	
	public String title = "Dummy 'app'";
	public String menutitle = "Dummy 'name'";
	public ArrayList<SuitePart> perspectives;
	public boolean noperspective = false;
	public boolean usesMenubar = true;
	public String styleBase;
	
	{
		perspectives = new ArrayList<>();
		
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
				String type = p.attr("type");
				String src = p.attr("abs:src");
				if(type.isEmpty()){
					String[] split = src.split("\\.");
					type = split[split.length - 1];
				}
				
				System.out.println(getPlatform());
				
				perspectives.add(getPlatform().styleLanguages.get(type.trim()).createUI(SuiteFXFeatures.this, p));
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
		add("menubar", new Func<Element, Void>(){
			@Override
			public Void call(Element p) {
				usesMenubar = Boolean.parseBoolean(p.attr("used"));
				return null;
			}
		});
		add("style", new Func<Element, Void>(){
			@Override
			public Void call(Element p) {
				styleBase = p.attr("base");
				return null;
			}
		});
	}

	public List<Menu> makeMenus(LanguageEngine e, List<Element> ms){
		ArrayList<Menu> menus = new ArrayList<>();
		for(int i = 0; i < ms.size(); i++){
			menus.add(createBaseMenu(e, ms.get(i)));
		}
		return menus;
	}

	private Menu createBaseMenu(LanguageEngine eng, Element p) {
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

	private MenuItem createMenu(LanguageEngine eng, Menu m, Element element) {
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
	
	private void setMenuAction(final LanguageEngine eng, MenuItem i, Element element) {
		final String action = element.attr("action");
		if(action != null){
			if(action.isEmpty()){
				return;
			}
			i.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					eng.invoke(action);
				}
			});
		}
	}
	
}
