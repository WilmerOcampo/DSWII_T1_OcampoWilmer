package com.wilmerocampo;

import com.wilmerocampo.service.GlucoseLevelService;
import com.wilmerocampo.thread.GlucoseLevelThread;

import java.util.Arrays;

public class Main {
    private static final int SIZE = 10;

    public static void main(String[] args) {
        GlucoseLevelService glucoseLevelService = new GlucoseLevelService();

        // Send glucose level to get diagnosis
        glucoseLevelService.patientDiagnosis(101);

        // Validate the correct classification of the system
        int[] array1 = glucoseLevelService.random(SIZE);
        int[] array2 = glucoseLevelService.random(SIZE);
        int[] array3 = glucoseLevelService.random(SIZE);
        int[] finalResult;

        System.out.println("\nVALIDATE THE CORRECT CLASSIFICATION OF THE SYSTEM:");
        System.out.println("Array1: " + Arrays.toString(array1));
        System.out.println("Array2: " + Arrays.toString(array2));
        System.out.println("Array3: " + Arrays.toString(array3));

        GlucoseLevelThread thread1 = new GlucoseLevelThread(array1);
        GlucoseLevelThread thread2 = new GlucoseLevelThread(array2);
        GlucoseLevelThread thread3 = new GlucoseLevelThread(array3);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        finalResult = new int[SIZE * 3];
        for (int i = 0; i < SIZE; i++) {
            finalResult[i] = thread1.getResult()[i];
            finalResult[SIZE + i] = thread2.getResult()[i];
            finalResult[2 * SIZE + i] = thread3.getResult()[i];
        }

        System.out.println("\nNORMAL = 0 | PRE-DIABETES = 1 | DIABETES = 2" +
                "\nResult thread1: " + Arrays.toString(thread1.getResult()) +
                "\nResult thread2: " + Arrays.toString(thread2.getResult()) +
                "\nResult thread3: " + Arrays.toString(thread3.getResult()) +
                "\nFinal result: " + Arrays.toString(finalResult));

        // Print classification of results
        System.out.println(glucoseLevelService.classifyResults(finalResult));
    }
}
