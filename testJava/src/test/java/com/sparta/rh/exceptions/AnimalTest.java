package com.sparta.rh.exceptions;

import org.example.com.sparta.rh.exceptions.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;

public class AnimalTest {
    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "10, 10"
    })
    @DisplayName("Given an age of 0 or above, setAge changes the age")
    public void setAgeHappyPath(int newAge, int expectedAge) throws ParseException {
        Animal sut = new Animal();
        sut.setAge(newAge);
        Assertions.assertEquals(expectedAge, sut.getAge());
    }
    @Test
    @DisplayName("Given an age below zero, setAge throws an IllegalArgumentException")
    public void setAgeSadPath() throws ParseException {
        Animal sut = new Animal();
        var exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sut.setAge(-1));
        Assertions.assertEquals("Age must not be negative: -1"  , exception.getMessage());
    }

    @Test
    @DisplayName("Given a null name, setName throws a NullPointerException")
    public void setNameNull() throws ParseException {
        Animal sut = new Animal();
        var exception = Assertions.assertThrows(NullPointerException.class, () -> sut.setName(null));
        Assertions.assertEquals("Name must not be null", exception.getMessage());

    }
    @Test
    @DisplayName("Given a name, setName returns the name")
    public void setName(){
        Animal sut = new Animal();
        sut.setName("Steve");
        Assertions.assertEquals("Steve", sut.getName());
    }
}
