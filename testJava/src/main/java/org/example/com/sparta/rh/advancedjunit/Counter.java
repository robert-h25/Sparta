package org.example.com.sparta.rh.advancedjunit;

public class Counter {

    private int count;

    public Counter(int start) { this.count = start; }

    public void increment() { count++; }

    public void decrement() { count--; }

    public int getCount() { return count; }

}