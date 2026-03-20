package com.example.norman_lee.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //TODO 5.4 Write unit tests to check calculateExchangeRate

    // valid conversion
    @Test
    public void calculateExchangeRate_validStrings_isCorrect() {
        double expected = 2.95;
        double actual = ExchangeRate.calculateExchangeRate("2.0", "5.9");
        assertEquals(expected, actual, 0.001);
    }
    // specific test conversion
    @Test
    public void calculateExchangeRate_specificStrings_isCorrect() {
        double expected = 0.33;
        double actual = ExchangeRate.calculateExchangeRate("3.0", "1.0");
        assertEquals(expected, actual, 0.001);
    }
    //empty string exception
    @Test(expected = NumberFormatException.class)
    public void calculateExchangeRate_emptyString_throwsNumberFormatException() {
        ExchangeRate.calculateExchangeRate("", "5.0");
    }
    // divide by zero exception
    @Test(expected = ArithmeticException.class)
    public void calculateExchangeRate_divideByZero_throwsArithmeticException() {
        ExchangeRate.calculateExchangeRate("0", "100.0");
    }
}