/**
 * O()
 * O()
 * 263 ms
 * 
 * Note:
 *      It's hard to think.
 *      最直观的方法是，用被除数逐个的减去除数，直到被除数小于0。这样做会超时。
 *
 *      那么如果每次不仅仅减去1个除数，计算速度就会增加，但是题目不能使用乘法，因此不能减去k*除数。
 *      我们可以对除数进行左移位操作，这样每次相当于减去2^k个除数，如何确定k呢，只要使
 *          (2^k)*除数 <=  当前被除数 < (2^(k+1))*除数.
 * 
 * Reference:
 *      http://www.cnblogs.com/springfor/p/3871008.html
 */
public class Solution {
                    //      100             13
    public int divide(int dividend, int divisor) {
        if(dividend == 0 || divisor == 0)// dividend < divisor should not be here, consider the case -7 / -88
			return 0;

		long ddL = Math.abs((long)dividend);//should cast dividend to long before Math.abs
		long drL = Math.abs((long)divisor);//should cast divisor to long before Math.abs

		if(ddL < drL)
			return 0;

		long sum, pow, res = 0;
		while(ddL >= drL){
			sum = drL;
			pow = 1;
			while(sum + sum <= ddL){
				sum += sum;
				pow += pow;
			}
			ddL -= sum;
			res += pow;
		}

		//check for the sign
		res = (( (dividend ^ divisor) >>> 31 ) == 1)? -res : res ;
		/**
		 * The above and below, either line works.
		 * 
		 * From Oracle Java Documentation:
		 * The unsigned right shift operator ">>>" shifts a ZERO into the leftmost position, 
		 *          while the leftmost position after ">>" depends on SIGN extension.
		 * 
		 * Therefore, if we use << instead of <<<, you need to consider the case -1 and 1.
		 *      because after >> 31 operation, the result will be like 111...111, at this moment,
		 *      you need to have &1 operation.
		 */
// 		 res = (( ((dividend ^ divisor) >> 31 ) & 1 ) == 1)? -res : res ;
		 
		if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
			return Integer.MAX_VALUE;

		return (int)res;
    }
}