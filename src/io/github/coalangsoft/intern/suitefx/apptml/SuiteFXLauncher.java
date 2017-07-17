package io.github.coalangsoft.intern.suitefx.apptml;

import org.jsoup.nodes.Element;

import io.github.apptml.platform.AppTMLDisplay;
import io.github.apptml.platform.AppTMLFeatures;
import io.github.apptml.platform.AppTMLLauncher;
import javafx.scene.web.WebEngine;

public class SuiteFXLauncher implements AppTMLLauncher<WebEngine> {
	
	@Override
	public AppTMLDisplay<WebEngine> display(AppTMLFeatures features, String url) {
		return new SuiteFXDisplay((SuiteFXFeatures) features, url);
	}

	@Override
	public void onMainTag(Element mainTag) {
		throw new RuntimeException("NIy");
	}

}
