package com.hospital.auto.diagnostic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Classe de test pour HealthIndexProcessor.
 */
public class HealthIndexProcessorTest {

    @Test
    public void testNoPathologiesForZero() {
        HealthIndexProcessor processor = new HealthIndexProcessor();
        assertTrue(processor.diagnose(0).isEmpty(), "Aucun pathologie pour un index de 0");
    }

    @Test
    public void testNoPathologiesForNegativeIndex() {
        HealthIndexProcessor processor = new HealthIndexProcessor();
        assertTrue(processor.diagnose(-15).isEmpty(), "Aucune pathologie pour un index négatif");
    }

    @Test
    public void testCardiologyOnly() {
        HealthIndexProcessor processor = new HealthIndexProcessor();
        assertEquals(List.of("Cardiologie"), processor.diagnose(3), "Cardiologie uniquement pour un multiple de 3");
    }

    @Test
    public void testTraumatologyOnly() {
        HealthIndexProcessor processor = new HealthIndexProcessor();
        assertEquals(List.of("Traumatologie"), processor.diagnose(5), "Traumatologie uniquement pour un multiple de 5");
    }

    @Test
    public void testCardiologyAndTraumatology() {
        HealthIndexProcessor processor = new HealthIndexProcessor();
        assertEquals(List.of("Cardiologie", "Traumatologie"), processor.diagnose(15), "Les deux pathologies pour un multiple de 3 et 5");
    }

    @Test
    public void testNoPathologiesForNonMultiples() {
        HealthIndexProcessor processor = new HealthIndexProcessor();
        assertTrue(processor.diagnose(7).isEmpty(), "Aucune pathologie pour un index non multiple de 3 ou 5");
    }

    @Test
    public void testLargeIndex() {
        HealthIndexProcessor processor = new HealthIndexProcessor();
        assertEquals(List.of("Cardiologie", "Traumatologie"), processor.diagnose(15000), "Grand index multiple de 3 et 5");
    }
}
