public class StringToInt {
    public static int toInt(String str) throws Exception {
        if (str == null) throw new Exception("Input Error");
        String strInt = str.trim();
        if (!strInt.matches("\\d+|^-\\d+")) {
            throw new Exception("Input Error (" + str +")");
        }
        boolean isNegative = strInt.startsWith("-");
        int pos  = 0;
        int len = strInt.length();
        String edgeInt = String.valueOf(Integer.MAX_VALUE);
        if (isNegative) {
            pos  = 1;
            edgeInt = String.valueOf(Integer.MIN_VALUE);
        }
        checkEdgeInt(edgeInt, strInt);
        int val = strInt.charAt(pos++) - '0';
        while (pos < len) {
            val  = val * 10 + strInt.charAt(pos++) - '0';
        }
        return isNegative? -1 * val : val;
    }

    private static void checkEdgeInt(String edgeInt, String val) throws Exception {
        if (val.length() < edgeInt.length()) return;
        if (val.length() == edgeInt.length()) {
           for (int i = 0; i < val.length(); ++i) {
                if (val.charAt(i) > edgeInt.charAt(i))
                    throw new Exception("Overflow (" + val + ")");
           }
           return;
        }
        throw new Exception("Too Long (" + val + ")");
    }

    public static void main(String args[]) {
        test("10234");
        test("-10234");
        test("10234 123");
        test("-10234 222");
        test("-1023422222222222222222222");
        test(String.valueOf(Integer.MAX_VALUE));
        test(String.valueOf(Integer.MIN_VALUE));
    }

    public static void test(String str) {
        System.out.println("Input: " + str);
        try {
           System.out.println("Result: " + toInt(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
