/**
 * O(?)
 * O(?)
 * 198 ms
 * 
 * Note:
 *      This problem consider many cases, Be careful and consider fully.
 *      Not easy one.
 *      the Main idea is add one digit to string once you get one digit.
 * 
 * Reference:
 *      http://blog.csdn.net/ljiabin/article/details/42025037
 */
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {//Mar 11 - 17: 07 - 18:35
        if(numerator == 0) return "0";
		if(denominator == 0) return "";

		String ans = "";

		//if the answer is negative.
		if( (numerator < 0) ^ (denominator < 0) )
			ans += "-";

		//Consider the case of -2147483648 / -1
		//		the computational answer may overflow.
		long num = numerator, den = denominator;
		num = Math.abs(num);
		den = Math.abs(den);

		//the whole number part
		long wholeNumber = num / den;
		ans += String.valueOf(wholeNumber);

		//if the numerator is divisible by denominator.
		long rem = num % den;
		if(rem == 0) return ans;

		//the decimal part
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		ans += ".";

		while(rem != 0){
			
			if(map.containsKey(rem)){
				int beginIndex = map.get(rem);
				String part1 = ans.substring(0, beginIndex);
				String part2 = ans.substring(beginIndex, ans.length());
				ans = part1 + "(" + part2 + ")";
				return ans;
			}

			map.put(rem, ans.length());//use ans.length() is for finding the loop start index.
			rem = rem * 10;
			wholeNumber = rem / den;
			ans += String.valueOf(wholeNumber);
			rem = rem % den;
		}

		//no any loop found.
		return ans;
		
		
        // if(denominator == 0) return null;
        // if(numerator == 0) return "0";
        
        // double ans = (double) numerator / (double) denominator;
        // String str = String.valueOf(ans);
        
        // //dealing with the repeating part.
        // int dot = str.indexOf(".");
        
        // //if the result is like 2.0, just return 2
        // if(str.length() - 1 == dot + 1 && str.charAt(dot + 1) == '0')
        //     return str.substring(0, dot);
        
        // //if it does not like form of 2.0
        // if(str.length() - 1 >= dot + 2){
        //     int first = dot + 1;
        //     int second = first + 1;
        //     while(str.charAt(first) == str.charAt(second))
        // }
        
        // return str;
    }
}