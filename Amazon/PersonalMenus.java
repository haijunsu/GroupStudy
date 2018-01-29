/*
 * 大概意思是给人推荐菜谱，input是两个二维String 数组，第一个用来存菜名和对应的菜系，
 * 第二个用来存人名和这个人喜欢的菜系，要去返回一个二维String数组，存的值是人名对应可
 * 能喜欢的菜名(如果人名后面对应的是“*”，那么就表示所有菜系都要)。
 * input: String[][] menu, String[][] personPreferences
 * output: String[][] recommendation.
 * example 1:
 * input: {{"Pizza", "Italian"}, {"Pasta", "Italian"}, {"Burger", "American"}}, {{"Peter", "Italian"}, {"Adam", "American"}}.
 * Expected output: {{"Peter", "Pizza"}, {"Peter", "Pasta"}, {"Adam", "Burger"}}
 *
 * example 2:
 * input: {{"Pizza", "Italian"}, {"Pasta", "Italian"}, {"Burger", "American"}}, {{"Peter", "*"}}
 * Expected output: {{"Peter", "Pizza"}, {"Peter", "Pasta"}, {"Peter", "Burger"}}
 *
 * From 1point 3acres bbs
 *
 */
import java.util.*;

public class PersonalMenus {

    static String[][] findRecommedation(String[][] menu, String[][] personPreferences) {
        List<String[]> recoms = new ArrayList<String[]>();
        for (int i = 0; i < personPreferences.length; ++i) {
            for(int j = 0; j < menu.length; ++j) {
                if (personPreferences[i][1].equals(menu[j][1])
                        || "*".equals(personPreferences[i][1])) {
                    recoms.add(new String[]{personPreferences[i][0], menu[j][0]});
                }
            }
        }
        return recoms.toArray(new String[0][0]);
    }

    public static void main(String[] args) {
        String[][] menu = {{"Pizza", "Italian"}, {"Pasta", "Italian"}, {"Burger", "American"}};
		String[][] personpre = {{"Peter", "Italian"}, {"Adam", "American"}};
		String[][] expected = {{"Peter", "Pizza"}, {"Peter", "Pasta"}, {"Adam", "Burger"}};
        test(menu, personpre, expected);
        menu = new String[][]{{"Pizza", "Italian"}, {"Pasta", "Italian"}, {"Burger", "American"}};
		personpre = new String[][]{{"Peter", "*"}, {"Adam", "American"}};
		expected = new String[][]{{"Peter", "Pizza"}, {"Peter", "Pasta"}, {"Peter","Burger"}, {"Adam", "Burger"}};
        test(menu, personpre, expected);

    }

    static void test(String[][] menu, String[][] personpre, String[][] expected) {
        String[][] ans = findRecommedation(menu, personpre);
        System.out.println("Expected: " + Arrays.deepToString(expected) + ", your answer: " + Arrays.deepToString(ans));
        System.out.println(Arrays.deepToString(expected).equals(Arrays.deepToString(ans)) ? "Accept" : "Wrong answer");
    }
}
