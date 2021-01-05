package com.example.app;

import java.util.Calendar;

public class Client {

    private final String name;
    private final int yearOfBirth;

    public Client(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getAge(Calendar calendar) {
        return calendar.get(Calendar.YEAR) - this.yearOfBirth;
    }
}