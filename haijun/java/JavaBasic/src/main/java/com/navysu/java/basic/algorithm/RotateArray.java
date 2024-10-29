package com.navysu.java.basic.algorithm;

public class RotateArray {

    public void rotate(int[] nums, int k) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(result, 0, nums, 0, nums.length);
    }

    public void rotateInPlace(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    public void reverseArrayWithXOR(int[] array, int start, int end) {
        while (start < end) {
            if (array[start] != array[end]) {
                array[start] = array[start] ^ array[end];
                array[end] = array[start] ^ array[end];
                array[start] = array[start] ^ array[end];
            }
            start++;
            end--;
        }
    }

}
