import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.github.apptml.base.AppTML;
import io.github.coalangsoft.intern.suitefx.apptml.SuiteFXPlatform;
import io.github.coalangsoft.lib.security.SecurityProfile;

public class AppTMLTest {

	public static void main(String[] args) throws MalformedURLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Properties ps = new Properties();
		File f = new File("F:/Info/CCL/AppTML/demo.html");
		ps.load(new FileInputStream(new File("F:/Info/CCL/AppTML-Java/settings.properties")));
		
//		System.out.println(Class.forName("java.lang.System"));
		
		String url = f.toURI().toURL().toExternalForm();
		Document doc = Jsoup.parse(f, "UTF-8");
		new AppTML(ps).launch(new SuiteFXPlatform(), doc, url);
	}
	
}
