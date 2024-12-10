package com.hospital.auto.diagnostic;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour traiter l'index de santé et déterminer les pathologies correspondantes.
 */
public class HealthIndexProcessor {

    /**
     * Diagnostique les pathologies en fonction de l'index de santé.
     * 
     * @param healthIndex L'index de santé à diagnostiquer.
     * @return Une liste des pathologies diagnostiquées.
     */
    public List<String> diagnose(int healthIndex) {
        List<String> diagnoses = new ArrayList<>();

        if (healthIndex <= 0) {
            return diagnoses; // Pas de pathologie pour des index non valides
        }

        if (healthIndex % 3 == 0) {
            diagnoses.add("Cardiologie");
        }
        if (healthIndex % 5 == 0) {
            diagnoses.add("Traumatologie");
        }

        return diagnoses;
    }

    /**
     * Point d'entrée principal pour tester la fonctionnalité.
     */
    public static void main(String[] args) {
        HealthIndexProcessor processor = new HealthIndexProcessor();

        // Tests simples avec des indices de santé prédéfinis
        int[] testIndices = {0, -15, 3, 5, 15, 7, 33, 55};
        for (int index : testIndices) {
            System.out.println("Index " + index + " => " + processor.diagnose(index));
        }
    }
}
