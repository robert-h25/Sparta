package com.sparta.rh.hamcrest;

import org.example.com.sparta.rh.hamcrest.Calculator;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class CalculatorTest {
    @Test
    void given2And6_Add_Returns8Pt0() {
        Calculator calc = new Calculator(6, 2);

        //Assertions.assertEquals(8.0, calc.add());
        MatcherAssert.assertThat(calc.add(), Matchers.equalTo(8.0));
    }

    @Test
    void given6And3_DivisibleBy_ReturnsTrue() {
        Calculator calc = new Calculator(6, 3);

        //Assertions.assertTrue(calc.divisibleBy());
        MatcherAssert.assertThat(calc.divisibleBy(), Matchers.equalTo(true));
    }

    @Test
    void given7And3_DivisibleBy_ReturnsFalse() {
        Calculator calc = new Calculator(7, 3);

        //Assertions.assertFalse(calc.divisibleBy());
        MatcherAssert.assertThat(calc.divisibleBy(), Matchers.equalTo(false));
    }

    @Test
    void given7And3_ToString_OutputContainsCalculator() {
        Calculator calc = new Calculator(7, 3);

        //Assertions.assertTrue(calc.toString().contains("Calculator"));
        MatcherAssert.assertThat(calc.toString(), Matchers.containsString("Calculator"));
    }

    @Test
    void moreStringMatchers() {

        var testString = "The quick brown fox jumps over the lazy dog.";

        MatcherAssert.assertThat(testString, startsWith("The"));

        MatcherAssert.assertThat(testString, endsWith("dog."));

        MatcherAssert.assertThat(testString, containsStringIgnoringCase("the quick"));

        MatcherAssert.assertThat(testString, stringContainsInOrder("quick", "jumps", "lazy"));

        MatcherAssert.assertThat(testString, not(emptyOrNullString()));

    }


}

