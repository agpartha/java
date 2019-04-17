package io.anand.springboot;

import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
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


    private static String getBuildNumberFromProps() throws IOException {
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

    public static void main (String [] args) {
        try {
            System.out.println("Starting: " + getAppStringFromManifest());
            System.out.println("Starting: " + getBuildNumberFromProps());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpringApplication.run(RestApplication.class, args);
    }

}
