package io.anand.play;

import java.io.IOException;
import java.util.Arrays;

public class MakeAnagram {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {

        // Use a hashmap, but since the character set is a-z that is only 26.
        // So we could use a simple integer array to act as balancing count
        // for each character that appears in string a increment the count at
        // a[i] - 'a'. Same time decrement the count if it is found in String b.
        // At the end
        // each character index in the array would be zero for matched characters, a -ve number
        // if only found in B, a +ve number if found only in A.
        // Sum of absolute vaues of the array would be the count.
        int charCount[] = new int [26];
        int i;

        int numValueBase = Character.getNumericValue('a');
        for (i = 0; ((i < a.length()) || (i < b.length())); i++) {
            if (i < a.length())
                charCount[Character.getNumericValue(a.charAt(i)) - numValueBase]++;
            if (i < b.length())
                charCount[Character.getNumericValue(b.charAt(i)) - numValueBase]--;
        }
        // Add up the non-zero counts with absolute values for -ve numbers.
        int result = 0;
        for (i = 0; i < charCount.length; i++)
            result += Math.abs(charCount[i]);
        return result;
    }

    public static void main(String[] args) throws IOException {

        String stringA = "ABCDEF";
        String stringB = "BCDE";
/*
        String stringA = "fcrxzwscanmligyxyvym";
        String stringB = "jxwtrhvujlmrpdoqbisbwhmgpmeoke";
*/
        System.out.println("Number of deletions: " + makeAnagram(stringA.toLowerCase(), stringB.toLowerCase()   ) + ", String A: " + stringA + ", String B: " + stringB );
    }
}
