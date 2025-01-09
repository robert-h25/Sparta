package org.example.com.sparta.rh.testFirstDevelopment;

public class Methods {
    // implement this method so that it returns true if x is greater than or equal to y
    public static boolean greaterEqual(int x, int y) {
        if (x >= y){
            return true;
        }else{
            return false;
        }
    }

    // implement this method so that it take an int as an input,
    // squares it, adds 101, divides the result by 7, then subtracts 4.
    // return a double.
    public static double BODMAS(int inputNumber) {
        double result = (((inputNumber * inputNumber)+101)/7.0)-4;
        return result;
    }

    // implement this method so that it returns true if input is "password"
    // regardless of case
    public static boolean checkInput(String input) {
        if(input.toLowerCase().equals("password")){
            return true;
        }else{
            return false;
        }
    }

    // implement this method so that it returns the sum of all the numbers from start to end
    public static int sumNums(int start, int end ) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }

    // implement this method so that it returns the sum
    // of all the numbers in the array nums
    public static int sumArray(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
