package com.hospital.auto.diagnostic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Stream;

/**
 * Classe pour diagnostiquer les pathologies en fonction de l'index de santé.
 */
public class HealthIndexProcessor {

    private static final Logger logger = LoggerFactory.getLogger(HealthIndexProcessor.class);

    /**
     * Diagnostique les pathologies à partir d'un index de santé.
     * 
     * @param healthIndex L'index de santé du patient.
     * @return Une liste des pathologies détectées.
     * @throws IllegalArgumentException Si l'index est invalide.
     */
    public List<String> diagnose(int healthIndex) {
        validateHealthIndex(healthIndex);

        return Stream.of(
                Pathology.CARDIOLOGY.diagnose(healthIndex),
                Pathology.TRAUMATOLOGY.diagnose(healthIndex)
        ).flatMap(Stream::ofNullable) // Ignore les nulls
         .toList();
    }

    /**
     * Valide que l'index de santé est positif.
     * 
     * @param healthIndex L'index de santé à valider.
     * @throws IllegalArgumentException Si l'index est non valide.
     */
    private void validateHealthIndex(int healthIndex) {
        if (healthIndex <= 0) {
            throw new IllegalArgumentException("L'index de santé doit être un entier positif.");
        }
    }

    /**
     * Point d'entrée principal pour exécuter les diagnostics.
     */
    public static void main(String[] args) {
        HealthIndexProcessor processor = new HealthIndexProcessor();

        int[] testIndices = {3, 5, 15, 7, 33, 55, -1, 0};
        for (int index : testIndices) {
            try {
                logger.info("Index {} => {}", index, processor.diagnose(index));
            } catch (IllegalArgumentException e) {
                logger.error("Index {} => Erreur : {}", index, e.getMessage());
            }
        }
    }

    /**
     * Enum pour définir les pathologies possibles.
     */
    private enum Pathology {
        CARDIOLOGY(3, "Cardiologie"),
        TRAUMATOLOGY(5, "Traumatologie");

        private final int factor;
        private final String description;

        Pathology(int factor, String description) {
            this.factor = factor;
            this.description = description;
        }

        public String diagnose(int healthIndex) {
            return healthIndex % factor == 0 ? description : null;
        }
    }
}
