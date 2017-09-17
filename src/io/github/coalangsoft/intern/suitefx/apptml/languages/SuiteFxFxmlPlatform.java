package io.github.coalangsoft.intern.suitefx.apptml.languages;

import org.jsoup.nodes.Element;

import io.github.apptml.iface.StyleLanguage;
import io.github.apptml.platform.AppTMLFeatures;
import io.github.coalangsoft.intern.suitefx.apptml.languages.intern.FXMLSuitePart;
import io.github.coalangsoft.intern.suitefx.part.SuitePart;

public class SuiteFxFxmlPlatform implements StyleLanguage<SuitePart> {

	@Override
	public SuitePart createUI(AppTMLFeatures<SuitePart> features, Element p) {
		return new FXMLSuitePart(features.getPlatform(), p.attr("name"), p.attr("abs:src"), p.attr("abs:code"));
	}

}
