package org.example.com.sparta.rh.exceptions;

import java.text.ParseException;

public class App {
    public static void main(String[] args) {
        try {
            Animal myHamster = new Animal("Hamish", 2, "27-07-2022");
            Animal myRabbit = new Animal(null, 1, "27-07-2022");
            Animal myOtherDog = new Animal("Fluffy", -2, "27-07-2022");
            Animal myOtherCat = new Animal("Snowflake", 12, "27 July, 2022");
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Invalid date format, should be \"dd-MM-yyyy\"");
        }
        System.out.println("Program is finished");
    }
}
