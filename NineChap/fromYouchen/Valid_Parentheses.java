/**
 * O(n)
 * 169 ms
 * 
 * String 
 * Stack
 *      push(<E> c), pop(), isEmpty()
 */
public class Solution {
    public boolean isValid(String s) {//30 min
        if(s.length() <= 1) return false;
        
        int pushed = 0;
        char popChar;
        Stack<Character> st = new Stack();
        for(char c : s.toCharArray()){
            if(c == '(' || c == '{' || c == '['){
                st.push(c);
                pushed++;
            }
            else{//c is ) } ]
                if( st.isEmpty() ) return false;
                popChar = st.pop();
                pushed--;
                if(c == ')' && popChar != '(') return false;
                if(c == '}' && popChar != '{') return false;
                if(c == ']' && popChar != '[') return false;
            }
        }
        return pushed == 0;
        /**
         * Copied from
         *      https://oj.leetcode.com/discuss/11196/my-solution-one-stack-case-statements-o-n
         */
         /*
        if(s == null)
        return true;

        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray())
            switch(c){
            case '(': case '{': case '[':
                stack.push(c); break;
            case ')': 
                if(stack.isEmpty() || stack.pop() != '(')
                    return false;
                break;
            case '}':
                if(stack.isEmpty() || stack.pop() != '{')
                    return false;
                break;
            case ']':
                if(stack.isEmpty() || stack.pop() != '[')
                    return false;
            }
        return stack.isEmpty();
        */
    }
}