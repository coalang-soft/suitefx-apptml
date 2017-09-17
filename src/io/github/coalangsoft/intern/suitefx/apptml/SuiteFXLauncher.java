package io.github.coalangsoft.intern.suitefx.apptml;

import org.jsoup.nodes.Element;

import io.github.apptml.platform.AppTMLDisplay;
import io.github.apptml.platform.AppTMLFeatures;
import io.github.apptml.platform.AppTMLLauncher;
import io.github.coalangsoft.intern.suitefx.part.SuitePart;

public class SuiteFXLauncher implements AppTMLLauncher<SuitePart> {
	
	@Override
	public AppTMLDisplay display(AppTMLFeatures<SuitePart> features, String url) {
		return new SuiteFXDisplay((SuiteFXFeatures) features, url);
	}

	@Override
	public void onMainTag(AppTMLFeatures<SuitePart> f, Element mainTag) {
		throw new RuntimeException("NIy");
	}

}
