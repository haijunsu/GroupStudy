/**
 * DO THIS AGAIN
 */
import java.util.*;
public class Solution {
    public boolean isNumber(String s) {
       
        /**
         * Copied from
         * https://oj.leetcode.com/discuss/9905/java-solution-with-one-line
         */
        return s.matches("(\\s*)[+|-]?((\\.[0-9]+)|([0-9]+(\\.[0-9]*)?))(e[+|-]?[0-9]+)?(\\s*)");
        //Original:
         //return s.matches("(\\s*)[+ -]? ((\\.[0-9]+) | ([0-9]+(\\.[0-9]*)?)) (e[+ -]?[0-9]+)? (\\s*)");
        
    }//method
}