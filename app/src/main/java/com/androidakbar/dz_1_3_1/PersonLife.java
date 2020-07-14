package com.androidakbar.dz_1_3_1;

public class PersonLife {
    private double weight;
    private int stepCount;

    public PersonLife(double weight, int stepCount) {
        this.weight = weight;
        this.stepCount = stepCount;
    }

    @Override
    public String toString() {
        return "Вес= " + weight +
                ", Количество пройденных шагов= " + stepCount;
    }
}
