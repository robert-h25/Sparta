package org.example.com.sparta.rh.hamcrest;

public class Calculator {

    private double num1, num2;
    public Calculator(double num1, double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public double getNum1() {
        return num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public boolean divisibleBy(){
        return num1 % num2 == 0;
    }

    public double add(){
        return num1 + num2;
    }
    public double subtract(){
        return num1 - num2;
    }

    @Override
    public String toString() {
        return "Calculator{"+"num1="+num1+", num2="+num2+"}";
    }
}
