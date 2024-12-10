package com.hospital.auto.diagnostic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour HealthIndexProcessor.
 */
public class HealthIndexProcessorTest {

    private final HealthIndexProcessor processor = new HealthIndexProcessor();

    /**
     * Génère les cas de test pour le test paramétré.
     * @return Un stream des cas de test.
     */
    static Stream<TestCase> provideTestCases() {
        return Stream.of(
                new TestCase(3, List.of("Cardiologie")),
                new TestCase(5, List.of("Traumatologie")),
                new TestCase(15, List.of("Cardiologie", "Traumatologie")),
                new TestCase(7, List.of()), // Aucun multiple
                new TestCase(33, List.of("Cardiologie")),
                new TestCase(55, List.of("Traumatologie"))
        );
    }

    /**
     * Test paramétré utilisant MethodSource.
     */
    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testDiagnose(TestCase testCase) {
        List<String> diagnoses = processor.diagnose(testCase.healthIndex);
        assertEquals(testCase.expectedPathologies, diagnoses,
                "Diagnoses incorrect for health index: " + testCase.healthIndex);
    }

    @Test
    public void testInvalidHealthIndexZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            processor.diagnose(0);
        });
        assertEquals("L'index de santé doit être un entier positif.", exception.getMessage());
    }

    @Test
    public void testInvalidHealthIndexNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            processor.diagnose(-10);
        });
        assertEquals("L'index de santé doit être un entier positif.", exception.getMessage());
    }

    /**
     * Classe interne pour représenter les cas de test.
     */
    private static class TestCase {
        int healthIndex;
        List<String> expectedPathologies;

        TestCase(int healthIndex, List<String> expectedPathologies) {
            this.healthIndex = healthIndex;
            this.expectedPathologies = expectedPathologies;
        }
    }
}
