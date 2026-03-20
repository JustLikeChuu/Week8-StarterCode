package com.example.norman_lee.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ExchangeRate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }
    public static double calculateExchangeRate(){
        return 2.95;
    }

    //TODO 3.9 Calculate the exchange rate
    public static double calculateExchangeRate(String A, String B)
            throws NumberFormatException, ArithmeticException {
        // takes care of empty string input
        if (A.isEmpty() || B.isEmpty()) {
            throw new NumberFormatException();
        }
        // takes care of divide by zero
        if (A.equals("0")) {
            throw new ArithmeticException();
        }
        // calculate the exchange rate
        // can also use big integer class
        double exchangeRate = Double.parseDouble(B) / Double.parseDouble(A);
        return Math.round(exchangeRate * 100.0) / 100.0; // rounds to 2d.p.
    }
}
