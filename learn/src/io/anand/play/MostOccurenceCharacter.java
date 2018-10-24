package io.anand.play;

import java.util.*;

public class MostOccurenceCharacter {

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

        @Override
        public String toString () {
            return "(CharCount: Char: " + this.c + ", Count: " + this.count + ")";
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
        List<CharCount> countSet = new ArrayList<>();
        for (Character c: freqMap.keySet()) {
            System.out.println("Sorting: Character: " + c + ", count: " + freqMap.get(c));
            countSet.add(new CharCount(c, freqMap.get(c)));
        }

        // Sort the list with our comparator for frequency
        Collections.sort(countSet);


        // Prepare the String selecting first 'count' characters
        StringBuilder sb = new StringBuilder();
        for (CharCount charCount: countSet) {
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
        String text  = "ABCDEEFGGHIIIKKLMNOPQQQQQ11";
        int    count = 6;

        System.out.println("Most Occurent Characters result: " + MostOccurentChars(text, count) + ", for text: " + text + ", count: " + count);

    }

}
