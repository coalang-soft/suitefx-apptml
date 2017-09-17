package io.github.coalangsoft.intern.suitefx.apptml;

import io.github.apptml.languages.coalang.CoaLangPlatform;
import io.github.apptml.languages.java.JavaPlatform;
import io.github.apptml.languages.javascript.JavaScriptPlatform;
import io.github.apptml.languages.python.PythonPlatform;
import io.github.apptml.platform.AppTMLPlatform;
import io.github.coalangsoft.intern.suitefx.apptml.languages.SuiteFxFxmlPlatform;
import io.github.coalangsoft.intern.suitefx.apptml.languages.SuiteFxHtmlPlatform;
import io.github.coalangsoft.intern.suitefx.part.SuitePart;

public class SuiteFXPlatform extends AppTMLPlatform<SuitePart>{

	public SuiteFXPlatform() {
		super("suitefx", new SuiteFXLauncher(), new SuiteFXFeatures());
		styleLanguages.put("html", new SuiteFxHtmlPlatform());
		styleLanguages.put("fxml", new SuiteFxFxmlPlatform());
		scriptLanguages.put("js", new JavaScriptPlatform());
		scriptLanguages.put("cl0", new CoaLangPlatform());
		scriptLanguages.put("cc0", new CoaLangPlatform());
		scriptLanguages.put("py", new PythonPlatform());
		scriptLanguages.put("java", new JavaPlatform());
		// TODO Auto-generated constructor stub
	}

}
