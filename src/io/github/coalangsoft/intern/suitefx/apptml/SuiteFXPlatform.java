package io.github.coalangsoft.intern.suitefx.apptml;

import io.github.apptml.base.platform.AppTMLPlatform;
import io.github.coalangsoft.intern.suitefx.apptml.languages.SuiteFxFxmlPlatform;
import io.github.coalangsoft.intern.suitefx.apptml.languages.SuiteFxHtmlPlatform;
import io.github.coalangsoft.intern.suitefx.part.SuitePart;

public class SuiteFXPlatform extends AppTMLPlatform<SuitePart>{

	public SuiteFXPlatform() {
		super("suitefx", new SuiteFXLauncher(), new SuiteFXFeatures());
		styleLanguages.put("html", new SuiteFxHtmlPlatform());
		styleLanguages.put("fxml", new SuiteFxFxmlPlatform());
		// TODO Auto-generated constructor stub
	}

}
