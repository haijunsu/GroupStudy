/*
 * Crack the interview 5.5
 * Determine the number of bits required to convert integer A to B.
 * Input: 31, 14
 * Output: 2
 */

class BitSwap {
    public static int bitSwapRequired(int a, int b) {
        int count = 0;
        for (int c = a^b; c != 0; c = c >> 1) {
            count += c & 1;
        }
        return count;
    }


    public static void main(String args[]) {
        System.out.println(bitSwapRequired(31, 14));
        System.out.println(bitSwapRequired(25, 14));
    }
}
