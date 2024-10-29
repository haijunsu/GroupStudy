package com.navysu.java.basic.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BitOperationsTest {

    private BitOperations bitOperations;

    @BeforeEach
    public void setUp() {
        bitOperations = new BitOperations();
    }

    @Test
    public void shouldToggleTheBitCorrectlyWhenTheIndexIs0() {
        int num = 0b10101010; // binary representation of 170
        int index = 0;
        int expected = 0b10101011; // binary representation of 171

        int result = bitOperations.toggleBit(num, index);

        assertEquals(expected, result);
    }

}