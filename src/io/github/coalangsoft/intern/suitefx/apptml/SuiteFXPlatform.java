package io.github.coalangsoft.intern.suitefx.apptml;

import io.github.apptml.platform.AppTMLPlatform;
import javafx.scene.web.WebEngine;

public class SuiteFXPlatform extends AppTMLPlatform<WebEngine>{

	public SuiteFXPlatform() {
		super("suitefx", new SuiteFXLauncher(), new SuiteFXFeatures());
		// TODO Auto-generated constructor stub
	}

}
