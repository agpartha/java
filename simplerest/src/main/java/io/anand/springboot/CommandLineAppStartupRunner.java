package io.anand.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Autowired
    InfoService infoService;

    @Autowired
    private Environment environment;

    private void getActiveProfiles() {
        for (final String profileName : environment.getActiveProfiles()) {
            logger.info("Currently active profile - " + profileName);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("\n----Begin logging SimpleRestApplication----");
        logger.info(
                "Application version: {} . \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t To kill this application, press Ctrl + C.",
                infoService.getVersion());
        logger.info("----System Properties from VM Arguments----");
        logger.info("server.port: " + System.getProperty("server.port"));
        logger.info("----Program Arguments----");
        for (String arg : args) {
            logger.info(arg);
        }

        if (environment != null) {
            getActiveProfiles();
            logger.info("----Environment Properties----");
            logger.info("server.port: " + environment.getProperty("server.port"));
            logger.info("simplerest.environment: " + environment.getProperty("simplerest.environment"));
            logger.info("spring.datasource.url: " + environment.getProperty("spring.datasource.url"));
            logger.info("spring.datasource.username: " + environment.getProperty("spring.datasource.username"));
            logger.info("spring.datasource.password: " + environment.getProperty("spring.datasource.password"));
            logger.info("spring.jpa.database-platform: " + environment.getProperty("spring.jpa.database-platform"));
            logger.info("spring.jpa.hibernate.ddl-auto: " + environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        }

        logger.info("----End logging SimpleRestApplication----");
    }
}
