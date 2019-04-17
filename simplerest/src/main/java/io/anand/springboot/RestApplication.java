package io.anand.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

@SpringBootApplication
public class RestApplication {

	private static String getAppStringFromManifest() throws IOException {
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
	
	public static void main (String [] args) {
		try {
			System.out.println("Starting: " + getAppStringFromManifest());
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpringApplication.run(RestApplication.class, args);
	}

}
