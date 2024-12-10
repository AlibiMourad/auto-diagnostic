package com.hospital.auto.diagnostic;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AutoDiagnosticApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(AutoDiagnosticApplicationTests.class);

    @Test
    void contextLoads(ApplicationContext context) {
        logger.info("Testing if the Spring context loads correctly...");
        
        // Ensure that the Spring context is not null
        assertThat(context).isNotNull();
        
        // Optionally, you can check if a specific bean is present
        assertThat(context.containsBean("autoDiagnosticApplication")).isTrue();
        
        logger.info("Spring context loaded successfully.");
    }
}
