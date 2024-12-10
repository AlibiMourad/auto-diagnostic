package com.hospital.auto.diagnostic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class HealthIndexProcessorTest {

    @Test
    public void testDiagnose() {
        HealthIndexProcessor processor = new HealthIndexProcessor();

        assertEquals(List.of("Cardiologie"), processor.diagnose(33));
        assertEquals(List.of("Traumatologie"), processor.diagnose(55));
        assertEquals(List.of("Cardiologie", "Traumatologie"), processor.diagnose(15));
        assertTrue(processor.diagnose(2).isEmpty());
    }
}
