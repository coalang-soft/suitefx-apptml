package io.github.coalangsoft.intern.suitefx.apptml;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import io.github.apptml.base.platform.AppTMLDisplay;
import io.github.apptml.base.platform.AppTMLFeatures;
import io.github.apptml.base.platform.AppTMLLauncher;
import io.github.coalangsoft.intern.suitefx.part.SuitePart;

public class SuiteFXLauncher implements AppTMLLauncher<SuitePart> {
	
	@Override
	public AppTMLDisplay display(AppTMLFeatures<SuitePart> features, String url, Document doc) {
		return new SuiteFXDisplay((SuiteFXFeatures) features, url, doc);
	}

	@Override
	public void onMainTag(AppTMLFeatures<SuitePart> f, Element mainTag) {
		throw new RuntimeException("NIy");
	}

}
