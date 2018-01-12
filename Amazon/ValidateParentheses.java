/*
* Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
* The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
* Leetcode: https://leetcode.com/problems/valid-parentheses/description/
*/

import java.util.*;
public class ValidateParentheses {

    public static boolean validate(String str) {
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
        System.out.println(validate(""));
        System.out.println(validate("["));
        System.out.println(validate("[]{}()"));
        System.out.println(validate("[]{}(){"));
     }
}
