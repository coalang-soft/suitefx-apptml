package io.github.coalangsoft.intern.suitefx.apptml.languages;

import org.jsoup.nodes.Element;

import io.github.apptml.iface.StyleLanguage;
import io.github.apptml.platform.AppTMLFeatures;
import io.github.coalangsoft.intern.suitefx.apptml.SuiteFXFeatures;
import io.github.coalangsoft.intern.suitefx.apptml.languages.intern.AppTMLSuitePart;
import io.github.coalangsoft.intern.suitefx.part.SuitePart;

public class SuiteFxHtmlPlatform implements StyleLanguage<SuitePart> {

	@Override
	public SuitePart createUI(AppTMLFeatures<SuitePart> features, Element p) {
		return new AppTMLSuitePart((SuiteFXFeatures) features, p.attr("name"), p.attr("abs:src"));
	}

}
