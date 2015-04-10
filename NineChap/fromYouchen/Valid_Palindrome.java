public class Solution {
    public boolean isPalindrome(String s) {
        if(s.equals("") || s.length() == 1) return true;

		s = s.toLowerCase();
		int begin = 0, end = s.length() - 1;
		while(begin <= end){
			while((begin < end ) && (! Character.isLetterOrDigit(s.charAt(begin))  ) ) {
				begin++;
			}
			while((begin < end ) && (! Character.isLetterOrDigit(s.charAt(end))  ) ) {
				end--;
			}
			if(begin > end) break;
			if(s.charAt(begin) != s.charAt(end) )
				return false;
			begin++;
			end--;
		}
		return true;
    }
}