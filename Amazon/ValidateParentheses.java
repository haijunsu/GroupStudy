/*
* Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
* The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
* Leetcode: https://leetcode.com/problems/valid-parentheses/description/
*/

import java.util.*;
public class ValidateParentheses {
    public static boolean validate(String str) {
        if (str == null || str.length() == 0) return true;
        int len = str.length();
        if (len % 2 != 0) return false;
        Stack<Character> stk = new Stack<Character>();
        for (int i = 0; i < len; ++i) {
            char posC = str.charAt(i);
            if (posC == '[') {
                stk.push(']');
            } else if (posC == '{') {
                stk.push('}');
            } else if (posC == '(') {
                stk.push(')');
            } else {
                if (stk.isEmpty() || stk.pop() != posC) {
                    return false;
                }
            }
        }
        return stk.isEmpty();
    }

    // for validation
    public static boolean validate2(String str) {
        if (str == null || str.length() == 0)
            return true;
        int len = str.length();
        if (len % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < len; ++i) {
            char tmp = str.charAt(i);
            if (tmp == '[') {
                stack.push(']');
            } else if (tmp == '{') {
                stack.push('}');
            } else if (tmp == '(') {
                stack.push(')');
            } else {
                if (stack.isEmpty() || tmp != stack.pop()) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


     public static void main(String []args){
        test("");
        test("[");
        test("[]");
        test("][");
        test("[]{}()");
        test("[[[]]]");
        test("[{[[]]}]()");
        test("[{[([])]}]()");
        test("[{[([[])]}]()");
     }

     static void test(String str) {
        boolean ans = validate(str);
        boolean ans2 = validate2(str);
        System.out.println("Expected: " + ans2 + ", your answer: " + ans);
        System.out.println(ans == ans2 ? "Accept" : "Wrong answer");
     }
}
