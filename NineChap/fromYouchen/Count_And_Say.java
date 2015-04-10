/**
 * O(n)
 * O(n)
 * 233 ms
 * 
 * String
 * StringBuffer
 * 		append(), delete(0, sb.length());
 * 
 * Note:
 * 		Read question carefully.
 */
import java.util.*;
public class Count_and_Say {
	/**
	 * 1, 11, 21, 1211, 111221, ...
	 */
	public static void main(String[] args) {
		countAndSay(5);
	}
    public static String countAndSay(int n) {//20150122 13:38 - 14:38
    	
    	if(n == 1) return String.valueOf(n);
    	
        String str = String.valueOf(1);
        StringBuffer sb = new StringBuffer();
        
        for(int i = 0; i < n - 1; i++){//to get the nth sequence
        	
        	sb.delete(0, sb.length());
        	
            for(int j = 0; j < str.length(); j++){
                int j2 = j + 1;
                int count = 1;
                while( j2 < str.length() && str.charAt(j2) == str.charAt(j) ){
                    j2++;
                    count++;
                }
                
                sb.append(String.valueOf(count));
                sb.append(String.valueOf( str.charAt(j) ) );
                j = j2 - 1;
            }
            str = sb.toString();
            System.out.println(str);
        }
        return str;
    }
}