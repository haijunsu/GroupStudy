package com.navysu.java.basic.algorithm;

/**
 * Give an list of characters and two characters, find the two characters that
 * have the minimum distance.
 *
 */
public class FindTwoCharactersMinDistance {

    public static void main(String[] args) {
        char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'c', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        System.out.println(findTwoCharactersMinDistance(chars, 'h', 'c'));
    }

    /**
     * Finds the minimum distance between two characters in a given character array.
     *
     * @param chars the character array
     * @param c1    the first character
     * @param c2    the second character
     * @return the minimum distance between c1 and c2
     */
    public static int findTwoCharactersMinDistance(char[] chars, char c1, char c2) {
        int minDistance = Integer.MAX_VALUE;
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c1) {
                index1 = i;
            } else if (chars[i] == c2) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(index1 - index2));
            }
        }
        return minDistance;
    }
}
