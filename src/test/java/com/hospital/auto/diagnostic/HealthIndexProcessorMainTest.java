package com.hospital.auto.diagnostic;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests pour vérifier la méthode main de HealthIndexProcessor.
 */
public class HealthIndexProcessorMainTest {

    @Test
    public void testMainMethod() {
        // Capture de la sortie standard
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Appel de la méthode main
            HealthIndexProcessor.main(new String[]{});

            // Récupération de la sortie
            String output = outputStream.toString();

            // Vérification du contenu attendu dans la sortie
            assertTrue(output.contains("Index 3 => [Cardiologie]"));
            assertTrue(output.contains("Index 5 => [Traumatologie]"));
            assertTrue(output.contains("Index 15 => [Cardiologie, Traumatologie]"));
            assertTrue(output.contains("Index 7 => []"));
            assertTrue(output.contains("Index -1 => Erreur : L'index de santé doit être un entier positif."));
        } finally {
            // Rétablissement de la sortie standard
            System.setOut(originalOut);
        }
    }
}
