/**
 * O(num1.length() * num2.length())
 * O(num1.length() + num2.length())
 * 249 ms
 * 
 * Note:
 *      This takes long time for solving.
 *      The key is the structure of solving this problem.
 *          reverse the string first is a smart and easy way to implement.
 *      DO THIS AGAIN.
 * 
 * Reference:
 *      http://www.cnblogs.com/springfor/p/3889706.html
 */
public class Solution {
    public String multiply(String num1, String num2) {//Mar 10 17 : 41 pause 17 : 50 resume 19:00
    
        num1 = new StringBuilder(num1).reverse().toString();
		num2 = new StringBuilder(num2).reverse().toString();

		int[] d = new int[num1.length() + num2.length()];
		for(int i = 0; i < num1.length(); i++){
			int a = num1.charAt(i) - '0';
			for(int j = 0; j < num2.length(); j++){
				int b = num2.charAt(j) - '0';

				d[i + j] += a * b;
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < d.length; i++){
			int digit = d[i] % 10;
			int carry = d[i] / 10;

			sb.insert(0, digit);
			
			/**
			 * not the last one, last one will not have any carries
			 *      since we knon that the total digit of answer will
			 *      not exceeds num1.length() + num2.length();
			 */
			if(i < d.length - 1)
			    d[i + 1] += carry;
		}

		//trim the starting zeros
		while(sb.length() > 0 && sb.charAt(0) == '0')
			sb.deleteCharAt(0);
		
		return (sb.length() == 0)? "0" : sb.toString();
        
        
        
        
        
        
        
        
        
        
        
        
        /**
         * 1st Trail. FAIL.
         */
        // int len1 = num1.length();
        // int len2 = num2.length();
        
        // StringBuffer s = new StringBuffer(len1 + len2);
        // for(int i = 0; i < s.capacity(); i++)
        // 	s.append("0");
        
        // int product = 0;
        // int carry = 0;
        // int curIndex = 0;
        
        // for(int i = len2 - 1; i >= 0; i--){
        //     for(int j = len1 - 1; j >= 0; j--){
        //         curIndex = s.capacity() - (len2 - i + (len1 - j - 1));
                
        //         product = (num1.charAt(j) - '0') * (num1.charAt(j) - '0') 
        //                 + (s.charAt(curIndex) - '0')
        //                 + carry;
                
        //         carry = product / 10;
        //         product %= 10;
        //         //current product(<=9) and carry(<=9).
                
        //         //modify the digit in place.
        //         s.setCharAt( curIndex, (char) product);
                
        //     }
        //     if(carry >= 1){
        //         s.setCharAt(curIndex - 1, (char) (carry + (s.charAt(curIndex - 1) - '0')) );
        //     }
        //     carry = 0;
        // }
        // // String ss = s.toString();
        
        // // int ii = 0;
        // // boolean iiChanged = false;
        // // while(ss.charAt(ii) == '0') {
        // //     ii++;
        // //     iiChanged = true;
        // // }
        // // if(ii == ss.length() ) return String.valueOf(0);
        // // else if(iiChanged) return ss.substring(ii - 1, ss.length());
        // // return ss;
        
        
        // return s.toString();
    }
}