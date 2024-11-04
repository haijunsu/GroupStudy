package com.navysu.java.basic.algorithm;

public class CutRopeProblem {

    public static void main(String[] args) {
        System.out.println(cutRope(5));
    }

    /**
     * Cutting rope problem
     * 给定一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为
     * k[0],k[1],…,k[m]。请问k[0]* k[1] * * … *k[m]可能的最大乘积是多少？
     * 就是把一个整数n，拆成m个整数，m个整数的乘积最大，根据动态规划的算法就是
     * f(1)=0;
     * f(2)=1;
     * f(3)=2;
     * f(4)= 4;
     * 对于f(n) = max f(i)*f(n-i)
     * 对i从0到n-1进行遍历，计算出最大值就是f(n),这样子就可以得到f(n)的值，时间复杂度是n^2,空间复杂度是n。
     * 贪心算法就是根据公式计算当n>=5时，3(n-3)>=2(n-2)，并且3(n-3)>=4(n-4)，所以绳子就尽可能得去剪出3来，如果最后剪掉3，剩下1，就把最后的4按照，2和2来剪，这样会好一些。
     *
     * @param nums
     * @return
     */
    // Dynamic programming approach
    // Cutting rope into 3 parts and multiply them together
    public static int cutRope(int nums) {
        if (nums <= 3) {
            return nums - 1;
        }
        int a = nums / 3;
        int b = nums % 3;
        if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        } else if (b == 2) {
            return (int) Math.pow(3, a) * 2;
        }
        return (int) Math.pow(3, a);
    }

}
