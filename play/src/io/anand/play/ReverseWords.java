package io.anand.play;

import java.util.ArrayList;
import java.util.List;

public class ReverseWords {

    static public String reverse(String S) {
        char          result[]      = new char[S.length()];
        char          word[]        = new char[S.length()];
        int           resultIndex   = result.length - 1;
        int           charCount     = 0;
        boolean       inWord        = false;


        // Capture the words and add them to the list
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            // If we have a space detected, if we are
            // collecting characters of a word, time to wrap
            // and add to the list
            if (' ' == c) {
                if (inWord) {
                    // capture the space if not first word
                    if (resultIndex != (result.length - 1))
                        result[resultIndex--] = c;
                    while (--charCount >= 0)
                        result[resultIndex--] = word[charCount];
                    inWord = false;
                    charCount = 0;
                }
            } else {
                word[charCount++] = c;
                inWord  = true;
            }
        }

        // last word if any can be now added to the begining
        if (charCount > 0) {
            if (resultIndex != (result.length - 1))
                result[resultIndex--] = ' ';
            while (--charCount >= 0)
                result[resultIndex--] = word[charCount];
        }

        // Get our resultIndex adjusted for length, we either have some string to interpet in the character array or empty string
        resultIndex += 1;
        return String.valueOf(result, resultIndex, result.length - resultIndex);
    }

    public static void main (String args []) {
        String input = " ! a hello     11    1212 1 1 1    9   ";

        System.out.println("Reversed words string: " + reverse(input) + ", Input string: " + input);
    }
}
