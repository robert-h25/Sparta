package com.sparta.rh.refactoring;


import org.example.com.sparta.rh.refactoring.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class AppTest {
    @Test
    @DisplayName("Check to see whether JUnit is setup correctly")
    void firstTest() {
        Assertions.assertTrue(true);
    }
    @Test
    @DisplayName("greeting, when given a time of 21, returns good evening")
    void greeting_GivenATimeOf21_ReturnsGoodEvening() {
        int time = 21;
        String expected = "Good evening!";
        String actual = App.greeting(time);

        Assertions.assertEquals(expected, actual);
    }
    @Test
    @DisplayName("greeting, when given a time of 0, returns good evening")
    public void greeting_GivenATimeOf0_ReturnGoodEvening() {
        int time = 0;
        String expected = "Good evening!";
        Assertions.assertEquals(expected, App.greeting(time));
    }
    @Test
    @DisplayName("greeting, when given a time of 4, returns good morning")
    public void greeting_GivenATimeOf4_ReturnGoodEvening() {
        int time = 4;
        String expected = "Good evening!";
        Assertions.assertEquals(expected, App.greeting(time));
    }
    @Test
    @DisplayName("greeting, when given a time of 5, returns good morning")
    public void greeting_GivenATimeOf5_ReturnGoodMorning() {
        int time = 5;
        String expected = "Good morning!";
        Assertions.assertEquals(expected, App.greeting(time));
    }

    @Test
    @DisplayName("greeting, when given a time of 11, returns good morning")
    public void greeting_GivenATimeOf11_ReturnGoodMorning() {
        int time = 11;
        String expected = "Good morning!";
        Assertions.assertEquals(expected, App.greeting(time));
    }

    @Test
    @DisplayName("greeting, when given a time of 12, returns good morning")
    public void greeting_GivenATimeOf12_ReturnGoodMorning() {
        int time = 12;
        String expected = "Good morning!";
        Assertions.assertEquals(expected, App.greeting(time));
    }

    @Test
    @DisplayName("greeting, when given a time of 18, returns good afternoon")
    public void greeting_GivenATimeOf18_ReturnGoodAfternoon() {
        int time = 18;
        String expected = "Good afternoon!";
        Assertions.assertEquals(expected, App.greeting(time));
    }

    @Test
    @DisplayName("greeting, when given a time of 18, returns good evening")
    public void greeting_GivenATimeOf19_ReturnGoodEvening() {
        int time = 19;
        String expected = "Good evening!";
        Assertions.assertEquals(expected, App.greeting(time));
    }
    @Test
    @DisplayName("greeting, when given a time of 23, returns good evening")
    public void greeting_GivenATimeOf23_ReturnGoodEvening() {
        int time = 23;
        String expected = "Good evening!";
        Assertions.assertEquals(expected, App.greeting(time));
    }



    @ParameterizedTest
    @ValueSource(ints = {0, 4})
    public void testEveningEarlyGreeting(int input) {
        Assertions.assertEquals("Good evening!", App.greeting(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 12})
    public void testMorningGreeting(int input) {
        Assertions.assertEquals("Good morning!", App.greeting(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {13, 18})
    public void testAfternoonGreeting(int input) {
        Assertions.assertEquals("Good afternoon!", App.greeting(input));
    }
    @ParameterizedTest
    @ValueSource(ints = {19, 23})
    public void testEveningGreeting(int input) {
        Assertions.assertEquals("Good evening!", App.greeting(input));
    }
    @ParameterizedTest
    @CsvSource({
            "5, Good morning!",
            "8, Good morning!",
            "12, Good morning!",
            "15, Good afternoon!",
            "18, Good afternoon!",
            "4, Good evening!",
            "19, Good evening!",
            "0, Good evening!"
    })
    public void testGreeting(int timeOfDay, String expectedGreeting) {
        Assertions.assertEquals(expectedGreeting, App.greeting(timeOfDay));
    }
}
