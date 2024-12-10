package com.hospital.auto.diagnostic.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HealthIndexProcessorTest {

    @Autowired
    private HealthIndexProcessor healthIndexProcessor;

    @Test
    public void testDiagnoseWithValidIndex() {
        List<String> pathologies = healthIndexProcessor.diagnose(15);
        assertEquals(2, pathologies.size());
        assertTrue(pathologies.contains("Cardiologie"));
        assertTrue(pathologies.contains("Traumatologie"));
    }

    @Test
    public void testDiagnoseWithInvalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> healthIndexProcessor.diagnose(-1));
    }

    @Test
    public void testDiagnoseWithNoPathology() {
        List<String> pathologies = healthIndexProcessor.diagnose(1);
        assertTrue(pathologies.isEmpty());
    }
}
