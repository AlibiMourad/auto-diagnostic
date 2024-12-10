package com.hospital.auto.diagnostic.controller;

import com.hospital.auto.diagnostic.services.HealthIndexProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HealthIndexControllerTest {

    @Mock
    private HealthIndexProcessor processor;

    @InjectMocks
    private HealthIndexController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDiagnose_withValidHealthIndex() {
        int healthIndex = 50;
        List<String> pathologies = Arrays.asList("Pathology1", "Pathology2");

        when(processor.diagnose(healthIndex)).thenReturn(pathologies);

        ResponseEntity<String> response = controller.diagnose(healthIndex);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pathologies détectées: Pathology1, Pathology2", response.getBody());

        verify(processor, times(1)).diagnose(healthIndex);
    }

    @Test
    void testDiagnose_withNoPathologies() {
        int healthIndex = 50;
        List<String> pathologies = Collections.emptyList();

        when(processor.diagnose(healthIndex)).thenReturn(pathologies);

        ResponseEntity<String> response = controller.diagnose(healthIndex);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Aucune pathologie détectée.", response.getBody());

        verify(processor, times(1)).diagnose(healthIndex);
    }

    @Test
    void testDiagnose_withInvalidHealthIndex() {
        int healthIndex = -1;

        when(processor.diagnose(healthIndex)).thenThrow(new IllegalArgumentException("Invalid health index"));

        ResponseEntity<String> response = controller.diagnose(healthIndex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid health index", response.getBody());

        verify(processor, times(1)).diagnose(healthIndex);
    }

    @Test
    void testDiagnose_withUnexpectedError() {
        int healthIndex = 50;

        when(processor.diagnose(healthIndex)).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<String> response = controller.diagnose(healthIndex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred.", response.getBody());

        verify(processor, times(1)).diagnose(healthIndex);
    }
}
