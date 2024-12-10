package com.hospital.auto.diagnostic.controller;

import com.hospital.auto.diagnostic.services.HealthIndexProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthIndexControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private HealthIndexProcessor processor;

    @Test
    void testDiagnose_withValidHealthIndex() {
        int healthIndex = 50;
        List<String> pathologies = Arrays.asList("Pathology1", "Pathology2");

        when(processor.diagnose(healthIndex)).thenReturn(pathologies);

        ResponseEntity<String> response = restTemplate.getForEntity("/diagnose?healthIndex=" + healthIndex, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pathologies détectées: Pathology1, Pathology2", response.getBody());

        verify(processor, times(1)).diagnose(healthIndex);
    }

    @Test
    void testDiagnose_withNoPathologies() {
        int healthIndex = 50;
        List<String> pathologies = Collections.emptyList();

        when(processor.diagnose(healthIndex)).thenReturn(pathologies);

        ResponseEntity<String> response = restTemplate.getForEntity("/diagnose?healthIndex=" + healthIndex, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Aucune pathologie détectée.", response.getBody());

        verify(processor, times(1)).diagnose(healthIndex);
    }

    @Test
    void testDiagnose_withInvalidHealthIndex() {
        int healthIndex = -1;

        when(processor.diagnose(healthIndex)).thenThrow(new IllegalArgumentException("Invalid health index"));

        ResponseEntity<String> response = restTemplate.getForEntity("/diagnose?healthIndex=" + healthIndex, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid health index", response.getBody());

        verify(processor, times(1)).diagnose(healthIndex);
    }

    @Test
    void testDiagnose_withUnexpectedError() {
        int healthIndex = 50;

        when(processor.diagnose(healthIndex)).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<String> response = restTemplate.getForEntity("/diagnose?healthIndex=" + healthIndex, String.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred.", response.getBody());

        verify(processor, times(1)).diagnose(healthIndex);
    }
}