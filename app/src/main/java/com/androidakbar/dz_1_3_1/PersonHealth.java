package com.androidakbar.dz_1_3_1;

import java.util.Calendar;

public class PersonHealth {

    private int lowerPressure;
    private int upperPressure;
    private int pulse;
    private boolean tachycardia;
    private Calendar dataTime;

    public PersonHealth(int lowerPressure, int upperPressure, int pulse, boolean tachycardia, Calendar dataTime) {
        this.upperPressure = upperPressure;
        this.lowerPressure = lowerPressure;
        this.pulse = pulse;
        this.tachycardia = tachycardia;
        this.dataTime = dataTime;
    }

    @Override
    public String toString() {
        return "Индивидуальные показатели{" +
                "Давление " + lowerPressure +
                " на " + upperPressure +
                ", Пульсe=" + pulse +
                ", Тахикардия " + (tachycardia ? "имеется " : "отсутствует ") +
                ", Дата замера: " + dataTime.get(Calendar.DAY_OF_MONTH) +
                "-" + dataTime.get(Calendar.MONTH) +
                "-" + dataTime.get(Calendar.YEAR) +
                " " + dataTime.get(Calendar.HOUR_OF_DAY) +
                ":" + dataTime.get(Calendar.MINUTE);
    }
}
