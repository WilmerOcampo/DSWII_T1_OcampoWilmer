package com.wilmerocampo.model;

public class GlucoseLevel {
    private final int glucoseLevel;
    private final String result;

    public GlucoseLevel(int glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
        this.result = message();
    }

    private String message() {
        if (glucoseLevel <= 99) {
            return "NORMAL";
        } else if (glucoseLevel <= 125) {
            return "PRE-DIABETES";
        } else {
            return "DIABETES";
        }
    }

    public String getResult() {
        return result;
    }
}
