package org.example.com.sparta.rh.exceptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Animal {
    private String name;
    private int age;
    private Date vaccinationDate;

    public Animal() {
    }

    public Animal(String name, int age, String dateString) throws ParseException {
        this.name = name;
        this.age = age;
        setVaccinationDate(dateString);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new NullPointerException("Name must not be null");
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must not be negative: " + age);
        }
        this.age = age;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String dateString) throws ParseException {
        this.vaccinationDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
    }

    @Override
    public String toString() {
        return "Animal{" + "name='" + name + '\'' + ", age=" + age + ", vacinationDate=" + vaccinationDate + '}';
    }
}
