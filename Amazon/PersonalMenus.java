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

class PairedValue{
    public String name;
    public String value;
}

public class PersonalMenus {

    static String[][] findRecommedation(String[][] menu, String[][] personPreferences) {
        List<PairedValue> pvList = new ArrayList<PairedValue>();
        for (int i = 0; i < personPreferences.length; ++i) {
            for (int j = 0; j < menu.length; j++) {
                if (menu[j][1].equals(personPreferences[i][1]) || "*".equals(personPreferences[i][1])) {
                    PairedValue pv = new PairedValue();
                    pv.name = personPreferences[i][0];
                    pv.value = menu[j][0];
                    pvList.add(pv);
                }
            }
        }

        String[][] result = new String[pvList.size()][2];
        int index = 0;
        for (PairedValue pv : pvList) {
            result[index][0] = pv.name;
            result[index][1] = pv.value;
            ++index;
        }
        return result;
    }

    public static void main(String[] args) {
        String[][] menu = {{"Pizza", "Italian"}, {"Pasta", "Italian"}, {"Burger", "American"}};
		String[][] personpre = {{"Peter", "Italian"}, {"Adam", "American"}};
		String[][] expected = {{"Peter", "Pizza"}, {"Peter", "Pasta"}, {"Adam", "Burger"}};
        printItems(expected);
		printItems(findRecommedation(menu, personpre));
        menu = new String[][]{{"Pizza", "Italian"}, {"Pasta", "Italian"}, {"Burger", "American"}};
		personpre = new String[][]{{"Peter", "*"}, {"Adam", "American"}};
		expected = new String[][]{{"Peter", "Pizza"}, {"Peter", "Pasta"}, {"Peter","Burger"}, {"Adam", "Burger"}};
        printItems(expected);
		printItems(findRecommedation(menu, personpre));

    }

    static void printItems(String[][] arr) {
        for (String[] item : arr) {
            System.out.print("{" + item[0] + ", " + item[1] + "}, ");
        }
        System.out.println();
    }
}
