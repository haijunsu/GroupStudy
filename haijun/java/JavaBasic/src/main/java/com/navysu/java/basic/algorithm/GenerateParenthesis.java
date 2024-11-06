package com.navysu.java.basic.algorithm;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 *
 * 输入：n = 3 输出：[ "((()))", 000111 "(()())", 001011 "(())()", 001101 "()(())",
 * 010011 "()()()" 010101 ]
 */
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
