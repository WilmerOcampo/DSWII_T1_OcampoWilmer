package com.wilmerocampo.thread;

public class GlucoseLevelThread extends Thread {
    private final int[] array;
    private final int[] result;

    public GlucoseLevelThread(int[] array) {
        this.array = array;
        this.result = new int[array.length];
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length; i++) {
            int item = array[i];
            if (item <= 99) {
                result[i] = 0;
            } else if (item <= 125) {
                result[i] = 1;
            } else {
                result[i] = 2;
            }
        }
    }

    public int[] getResult() {
        return result;
    }
}
