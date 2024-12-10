package com.hospital.auto.diagnostic;

import java.util.ArrayList;
import java.util.List;

public class HealthIndexProcessor {

    public List<String> diagnose(int healthIndex) {
        List<String> diagnoses = new ArrayList<>();

        if (healthIndex % 3 == 0) {
            diagnoses.add("Cardiologie");
        }
        if (healthIndex % 5 == 0) {
            diagnoses.add("Traumatologie");
        }

        return diagnoses;
    }

    public static void main(String[] args) {
        HealthIndexProcessor processor = new HealthIndexProcessor();

        int[] testIndices = {15, 33, 55};
        for (int index : testIndices) {
            System.out.println("Index " + index + " => " + processor.diagnose(index));
        }
    }
}
