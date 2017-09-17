package io.github.coalangsoft.intern.suitefx.apptml.languages;

import io.github.apptml.iface.LanguageEngine;
import javafx.scene.web.WebEngine;

public class WebEngineWrapper {
	
	public static LanguageEngine wrap(WebEngine e){
		return new LanguageEngine() {
			@Override
			public Object get(String obj) {
				return e.executeScript(obj);
			}

			@Override
			public Object evalUrl(String script) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object invoke(String func, Object... args) {
				if(args.length > 0){
					throw new RuntimeException("Only zero parameters possible!");
				}
				return e.executeScript(func + "()");
			}

			@Override
			public void put(String name, Object value) {
				throw new RuntimeException("Trying to put " + value + " as " + name);
			}
		};
	}
	
}
