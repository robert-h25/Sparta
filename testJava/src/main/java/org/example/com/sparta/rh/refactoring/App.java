package org.example.com.sparta.rh.refactoring;

public class App {
    public static void main(String[] args) {
        int timeOfDay = 21;

        System.out.println((greeting(timeOfDay)));
    }

    public static String greeting(int timeOfDay) {
        String greeting = "";
        if (timeOfDay >= 5 && timeOfDay <= 12) {

            greeting = ("Good morning!");

        } else if (timeOfDay >= 12 && timeOfDay <= 18) {

            greeting =  "Good afternoon!";

        } else {

            greeting = ("Good evening!");

        }
        return greeting;
    }
}
