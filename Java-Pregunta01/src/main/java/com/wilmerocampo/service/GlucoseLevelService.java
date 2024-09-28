package com.wilmerocampo.service;

import com.wilmerocampo.model.GlucoseLevel;

import java.util.Random;

public class GlucoseLevelService {

    // Patient diagnostic
    public void patientDiagnosis(int glucoseLevel) {
        GlucoseLevel level = new GlucoseLevel(glucoseLevel);
        System.out.println("\nPATIENT DIAGNOSIS: " +
                "\nGlucose Level: " + glucoseLevel +
                "\nPatient: " + level.getResult()
        );
    }

    // Generate random array of glucose levels
    public int[] random(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1, 400);
        }
        return array;
    }

    // Classification of results in percentages
    public String classifyResults(int[] array) {
        int c0 = 0;
        int c1 = 0;
        int c2 = 0;
        for (int i : array) {
            if (i == 0) {
                c0++;
            } else if (i == 1) {
                c1++;
            } else {
                c2++;
            }
        }

        double normal = (c0 / (double) array.length) * 100;
        double preDiabetes = (c1 / (double) array.length) * 100;
        double diabetes = (c2 / (double) array.length) * 100;

        return String.format("""
                
                CLASSIFICATION OF RESULTS: \
                
                Normal: %.2f%%\
                
                Pre-Diabetes: %.2f%%\
                
                Diabetes: %.2f%%""", normal, preDiabetes, diabetes);
    }
}
