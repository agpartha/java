package io.anand.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);

    @Autowired
    HealthService healthService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Started {}", healthService.getHealth());
    }

}
