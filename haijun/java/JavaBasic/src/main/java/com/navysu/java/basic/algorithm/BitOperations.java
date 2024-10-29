package com.navysu.java.basic.algorithm;

public class BitOperations {

    public void swapNumber(int a, int b) {
        System.out.println("Before: " + a + " " + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("After: " + a + " " + b);
    }

    public boolean isEven(int a) {
        return (a & 1) == 0;
    }

    public boolean isOdd(int a) {
        return (a & 1) == 1;
    }

    public int findGCD(int a, int b) {
        if (b == 0)
            return a;
        return findGCD(b, a % b);
    }

    public int findLCM(int a, int b) {
        return (a * b) / findGCD(a, b);
    }

    public int toggleBit(int num, int index) {
        return num ^ (1 << index);
    }

    public static void main(String[] args) {
        BitOperations bo = new BitOperations();
        bo.swapNumber(5, 10);
    }
}
