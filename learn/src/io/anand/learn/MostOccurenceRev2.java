package io.anand.learn;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class MostOccurenceRev2 {

    /*
     Given a string S and an integer N, write a function that returns the N characters that occur most often in the string S in order of occurence count.

        For example f('ABCBCC', 1) should return 'C' and f('ABCBCC', 2) should return 'CB'.
     */

    private static class CharCount implements Comparable<CharCount> {
        Character c;
        Integer   count;

        public CharCount (Character c, Integer count) {
            this.c      = c;
            this.count  = count;
        }

        @Override
        public int compareTo(CharCount that) {
            if (this.count < that.count)
                return 1;
            else if (this.count > that.count)
                return -1;
            return 0;
        }
    }

    public static String MostOccurentChars (String text, int count) {
        Map<Character, Integer> freqMap = new HashMap<>();

        // Parse the text string character by character and update the frequency map
        for (int i = 0; i < text.length(); i ++) {
            Character c = text.charAt(i);
            if (freqMap.containsKey(c))
                freqMap.put(c, freqMap.get(c) + 1);
            else
                freqMap.put(c, 1);
            System.out.println("Counting: Char: " + c + ", frequency: " + freqMap.get(c));
        }

        // A =1 , B = 2, C = 3
        // C = 3, B = 2
        // CB
        // Get a sorted set based on the count
        // 1, 2, 3
        // 3, 2, 1
        ArrayList<CharCount> countSortedSet = new TreeSet<>();
        for (Character c: freqMap.keySet()) {
            System.out.println("Sorting: Char: " + c + ", count: " + freqMap.get(c));
            countSortedSet.add(new CharCount(c, freqMap.get(c)));
        }

        // Prepare the String selecting first 'count' characters
        StringBuilder sb = new StringBuilder();
        for (CharCount charCount: countSortedSet) {
            System.out.println("Building: Char: " + charCount.c + ", count: " + charCount.count);
            if (0 < count--)
                sb.append(charCount.c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        /* Test Data : 1
        String text  = "ABCBCC";
        int    count = 2;
        */

        /* Test Data : 2
        String text  = "ABCBCC";
        int    count = 1;
        */

        /* Test Data: 3 */
        String text  = "ABCDEEFGGHIIIKKLMNOPQ11";
        int    count = 5;

        System.out.println("Most Occurent Characters result: " + MostOccurentChars(text, count) + ", for text: " + text + ", count: " + count);

    }

}
