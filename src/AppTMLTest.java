import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.github.apptml.AppTML;
import io.github.coalangsoft.intern.suitefx.apptml.SuiteFXPlatform;

public class AppTMLTest {

	public static void main(String[] args) throws MalformedURLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
//		Properties ps = new Properties();
		File f = new File("F:/Info/CCL/AppTML/demo.html");
//		ps.load(new FileInputStream(new File("settings.properties")));
		
		String url = f.toURI().toURL().toExternalForm();
		Document doc = Jsoup.parse(f, "UTF-8");
		new AppTML(new Properties()).launch(new SuiteFXPlatform(), doc, url);
	}
	
}
