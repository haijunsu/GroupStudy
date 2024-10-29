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

    /**
     * Calculates the absolute value of an integer using bitwise operations.
     *
     * This function uses bitwise shift and XOR operations to calculate the absolute
     * value of an integer.
     * It works by first determining the sign of the input number by performing a
     * right shift operation on the number.
     * The sign is then used to perform XOR and subtraction operations to obtain the
     * absolute value.
     *
     * @param a The integer for which the absolute value needs to be calculated.
     * @return The absolute value of the input integer.
     */
    public int my_abs(int a) {
        int b = a >> 31;
        return (a ^ b) - b;
    }

    public int signReversal(int a) {
        return ~a + 1;
    }

    public int countSetBits(int a) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((a & (1 << i)) != 0) {
                count++;
            }
        }
        return count;
    }

    public int multiply(int a, int b) {
        int sum = 0;
        for (int i = 0; i < b; i++) {
            sum += a;
        }
        return sum;
    }

    public double getFloat(int a) {
        return Float.intBitsToFloat(a);
    }

    public int findMissingNumber(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return numbers.length * (numbers.length + 1) / 2 - sum;
    }

    public boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isPrime2(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds the single value in an array where every other number appears twice.
     *
     * This function uses the XOR operation to find the single value in an array.
     * XOR operation returns 1 if the corresponding bits of two operands are
     * different,
     * and 0 if they are the same. By applying XOR operation on all numbers in the
     * array,
     * the result will be the single value because all the other numbers will cancel
     * each other out.
     *
     * @param numbers The array of integers where exactly one number appears once
     *                and all others appear twice.
     * @return The single value in the array.
     */
    public int findSingleValue(int[] numbers) {

        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            result ^= numbers[i];
        }
        return result;
    }

    public int[] findTwoSingleNumbers(int[] numbers) {
        int[] result = new int[2];
        int xorResult = findSingleValue(numbers);
        int shiftPos = 0;
        while ((xorResult & 1) == 0) {
            xorResult >>= 1;
            shiftPos++;
        }
        for (int i = 0; i < numbers.length; i++) {
            if ((numbers[i] & (1 << shiftPos)) == 0) {
                result[0] ^= numbers[i];
            } else {
                result[1] ^= numbers[i];
            }
        }
        return result;
    }

    public boolean isPalindrome(int num) {
        int reverse = 0;
        int temp = num;
        while (temp != 0) {
            reverse = reverse * 10 + temp % 10;
            temp /= 10;
        }
        return num == reverse;
    }

    public void findAllPrimes(int num) {
        boolean[] notPrime = new boolean[num + 1];
        for (int i = 2; i < num; i++) {
            if (!notPrime[i]) {
                System.out.println(i);
                for (int j = i * i; j <= num; j += i) {
                    notPrime[j] = true;
                }
            }
        }
    }

    public int reverse(int num) {
        if (num < 0) {
            return -1 * reverse(~num + 1);
        }
        int reverse = 0;
        while (num != 0) {
            reverse = reverse * 10 + num % 10;
            num /= 10;
        }
        return reverse;
    }

    public int reverseRecursive(int num) {
        if (num == 0) {
            return 0;
        }
        return num % 10 + 10 * reverseRecursive(num / 10);
    }

    public static void main(String[] args) {
        BitOperations bo = new BitOperations();
        bo.swapNumber(5, 10);
        bo.findAllPrimes(100);
    }
}
