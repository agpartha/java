package io.anand.springboot;

import com.sun.tools.javac.Main;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Service
public class InfoService {

	private String appString	= null;
	private String buildNumber	= null;

	private String getAppStringFromManifest() throws IOException {
		String appString;

		Enumeration<URL> resources = Thread.currentThread().getContextClassLoader()
				.getResources("META-INF/MANIFEST.MF");
		while (resources.hasMoreElements()) {
			URL manifestUrl = resources.nextElement();
			Manifest manifest = new Manifest(manifestUrl.openStream());
			Attributes mainAttributes = manifest.getMainAttributes();
			String startClass = mainAttributes.getValue("Start-Class");
			if (null != startClass) {
				appString = startClass;
				String buildString = mainAttributes.getValue("build");
				if (buildString != null)
					return appString + " - Version: " + buildString;
				else
					return appString;
			}
		}
		return "";
	}

	private  String getBuildNumberFromProps() throws IOException {
		String propertiesName = "/build.properties";

		InputStream propertiesStream = Main.class
				.getResourceAsStream(propertiesName);
		if (propertiesStream != null) {
			Properties pros = new Properties();
			pros.load(propertiesStream);

			return pros.getProperty("buildNumber");
		}
		return null;
	}

    public InfoService() {
		try {
			appString = getAppStringFromManifest();
			buildNumber = getBuildNumberFromProps();
		} catch (Exception e) {
				e.printStackTrace();
		}
    }
			
	public String getHealth () {
		return "Running: " + appString;
	}
	
	public String getVersion () {
		return appString;
	}

	public String getBuild () {
		return buildNumber;
	}
}
