/*
 * Q1 丢棒球砸砖块，貌似是地里没有出现过的题，输入是一个字符串数组，每一个值可能是一个整数，
 * 或者Z，或者X，或者+。整数代表现在拿的分，X代表当前成绩是前一个分数Double，
 * 代表当前成绩是前两个的和，Z代表移除前一个成绩，然后要求的是最后的总成绩
 * 例子： 输入 ["5", "-2", "4", "Z", "X", 9, "+", "+"]
 * 输出 27.
 * 5 : sum = 5.
 * -2 : sum = 5 - 2 = 3
 * 4 : sum = 3 + 4 = 7
 * Z : sum = 7 - 4 = 3
 * X : sum = 3 + -2 * 2 = -1 (因为4被移除了，前一个成绩是-2).
 * 9 : sum = -1 + 9 = 8
 * : sum = 8 + 9 - 4 = 13 (前两个成绩是9和-4)
 * : sum = 13 + 9 + 5 = 27 (前两个成绩是5 和 9)
 *
 */
import java.util.*;

public class BallScore {

    public static int solution(String[] scores) {
        int sum = 0;
        Stack<Integer> stk = new Stack<Integer>();
        for (String score : scores) {
            if (score.equals("Z")) {
                stk.pop();
            } else if (score.equals("X")) {
                stk.push(stk.peek() * 2);
            } else if (score.equals("+")) {
                int top = stk.pop();
                int value = top + stk.peek();
                stk.push(top);
                stk.push(value);
            } else {
                stk.push(Integer.parseInt(score));
            }
        }
        for (int score : stk) {
            sum += score;
        }
        return sum;
    }

    public static void main(String args[]) {
        String[] scores = new String[]{"5","-2","4","Z","X","9","+","+"};
        System.out.println("27");
        System.out.println(solution(scores));
    }
}
