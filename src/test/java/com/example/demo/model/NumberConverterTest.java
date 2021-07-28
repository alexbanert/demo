package com.example.demo.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class NumberConverterTest {

    private NumberConverter numberConverter;

    @Before
    public void setup() {
        numberConverter = new NumberConverter();
    }

    @Test
    public void convertToRomanWhenInputPositive() {
        int input = 3999;
        String result = numberConverter.convertToRoman(input);

        assertEquals(result, "MMMCMXCIX");
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertToRomanWhenInputNegative() {
        int input = -1;
        numberConverter.convertToRoman(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertToRomanWhenInputZero() {
        int input = 0;
        numberConverter.convertToRoman(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertToRomanWhenInputTooBig() {
        int input = 4000;
        numberConverter.convertToRoman(input);
    }

    @Test
    public void convertToHex() {
        int input = 100;
        String result = numberConverter.convertToHex(input);
        assertEquals(result, "64");
    }
}