package com.example.covidtracker;

public class Case {

    private String state;
    private int positive;
    private int positiveIncrease;
    private int probableCases;
    private int deathConfirmed;
    private int recovered;

    public String getState() {
        return state;
    }

    public int getPositive() {
        return positive;
    }

    public int getPositiveIncrease() {
        return positiveIncrease;
    }

    public int getProbableCases() {
        return probableCases;
    }

    public int getDeathConfirmed() {
        return deathConfirmed;
    }

    public int getRecovered() { return recovered; }
}
