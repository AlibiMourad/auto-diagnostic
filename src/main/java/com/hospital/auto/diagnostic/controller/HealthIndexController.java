package com.hospital.auto.diagnostic.controller;

import com.hospital.auto.diagnostic.services.HealthIndexProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class HealthIndexController {

    private static final Logger logger = LoggerFactory.getLogger(HealthIndexController.class);

    private final HealthIndexProcessor processor;

    @Autowired
    public HealthIndexController(HealthIndexProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/diagnose")
    public ResponseEntity<String> diagnose(@RequestParam int healthIndex) {
        logger.info("Processing health index: {}", healthIndex);

        try {
            List<String> pathologies = processor.diagnose(healthIndex);
            String result = formatDiagnosisResult(pathologies);

            logger.info("Diagnosis result: {}", result);
            return ResponseEntity.ok(result);

        } catch (IllegalArgumentException e) {
            logger.error("Invalid health index: {}", healthIndex, e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while processing health index: {}", healthIndex, e);
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }

    /**
     * Formats the diagnosis result into a human-readable string.
     *
     * @param pathologies The list of detected pathologies.
     * @return The formatted diagnosis result.
     */
    private String formatDiagnosisResult(List<String> pathologies) {
        return Optional.of(pathologies)
                .filter(list -> !list.isEmpty())
                .map(list -> "Pathologies détectées: " + String.join(", ", list))
                .orElse("Aucune pathologie détectée.");
    }
}
