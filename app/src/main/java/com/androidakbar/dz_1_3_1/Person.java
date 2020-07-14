package com.androidakbar.dz_1_3_1;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Имя: " + name +
                ", Возраст: " + age;
    }
}
