package com.navysu.java.basic.algorithm;

public class GenerateParenthesis {

    public static void main(String[] args) {
        generateParenthesis(3);
    }

    public static void generateParenthesis(int n) {
        generateParenthesis("", 0, 0, n);
    }

    public static void generateParenthesis(String str, int open, int close, int max) {
        if (str.length() == max * 2) {
            System.out.println(str);
            return;
        }
        if (open < max) {
            generateParenthesis(str + "(", open + 1, close, max);
        }
        if (close < open) {
            generateParenthesis(str + ")", open, close + 1, max);
        }
    }

}
