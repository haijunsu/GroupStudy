/**
 * Big movie has been split several small movies. Each small movie has
 * been labeled with a character. Find the movie sequence.
 * Each movie has no more than two same labels (The definition was very confuse).
 * We can think the sequence as below:
 * 1. between same labels. Ex: a, b, c, a
 * 2. Sequences can be overlaps. The we consider they are in one sequence:
 *      Ex: a, b, c, a, b, c, a, b
 *
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

class MovieSplit {

    public static List<Integer> findSequence(List<Character> movies) {
        List<Integer> ans = new ArrayList<Integer>();
        int[][] pos = new int[256][2];
        char first = movies.get(0);
        for (int i = 1; i < movies.size(); ++i) {
            char curr = movies.get(i);
            if (curr == first) {
                pos[curr][1] = i;
            } else {
                if (pos[curr][0] == 0) {
                    pos[curr][0] = i;
                } else {
                    pos[curr][1] = i;
                }
            }
        }
        int[] currRange = pos[movies.get(0)];
        int i = 0;
        while (i < movies.size() - 1) {
            int[] nextRange = pos[movies.get(++i)];
            if (nextRange[0] > currRange[1]) {
                ans.add(currRange[1] - currRange[0] + 1);
                if (nextRange[1] == 0) {
                    // only once
                    ans.add(1);
                    if (i == movies.size() -1) {
                        break;
                    }
                    nextRange = pos[movies.get(++i)];
                }
                currRange = nextRange;
            } else if (nextRange[1] > currRange[1]) {
                // merge
                i = currRange[1] = nextRange[1];
            }
            if (currRange[1] == movies.size() - 1) {
                ans.add(currRange[1] - currRange[0] + 1);
                break;
            }
            // only show once at last position
            if (currRange[0] == movies.size() - 1) {
                ans.add(1);
                break;
            }
        }
        return ans;
    }

    public static void main(String args[]) {
        Character[] data = {'a', 'b', 'c'};
        Integer[] expected = {1, 1, 1};
        test(data, expected);
        data = new Character[]{'a', 'b', 'c', 'a'};
        expected = new Integer[]{4};
        test(data, expected);
        data = new Character[]{'a', 'b', 'a', 'b', 'd'};
        expected = new Integer[]{4, 1};
        test(data, expected);
        data = new Character[]{'a', 'b', 'a', 'b', 'c', 'b', 'a', 'c', 'a',
            'd', 'e', 'f', 'e', 'g', 'd', 'e',
            'h', 'i', 'j', 'h', 'k', 'l', 'i', 'j'};
        expected = new Integer[]{9, 7, 8};
        test(data, expected);

    }

    static void test(Character[] data, Integer[] expected) {
        List<Character> testList = Arrays.asList(data);
        List<Integer> expectList = Arrays.asList(expected);
        List<Integer> ans = findSequence(testList);
        System.out.println("Expected: " + expectList + ", your answer: " + ans);
        System.out.println(expectList.toString().equals(ans.toString())?"Accept":"Wrong answer");
    }
}
