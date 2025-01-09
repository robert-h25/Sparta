package org.example.com.sparta.rh.regex.Regex;

public class Demo {
    public static void main(String[] args) {
        String input = "Cathy,Phil, Nish,   Manish,Alex Blunt";
        String[] splitString = input.split("[a-zA-Z]+ *[a-zA-Z]+");

        int count = 1;
        for(String s : splitString) {
            System.out.println(count++ + ": " + s);
        }

        String postcode = "EN10 6LQ";
        String postcodeRegex = "^(([A-Z][0-9]{1,2})|(([A-Z][A-HJ-Y][0-9]{1,2})|(([A-Z][0-9][A-Z])|([A-Z][A-HJ-Y][0-9]?[A-Z])))) [0-9][A-Z]{2}$";

        if(postcode.matches(postcodeRegex)){
            System.out.println("Postcode matched");
        }
        else{
            System.out.println("Postcode not matched");
        }
    }
}
