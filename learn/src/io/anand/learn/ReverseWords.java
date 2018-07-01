package io.anand.learn;

import java.util.ArrayList;
import java.util.List;

public class ReverseWords {

    static public String reverse(String S) {
        String result           = "";
        boolean       inWord    = false;
        char          word[]    = new char[S.length()];
        int           charCount = 0;

        // Capture the words and add them to the list
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            // If we have a space detected, if we are
            // collecting characters of a word, time to wrap
            // and add to the list
            if (' ' == c) {
                if (inWord) {
                    result = String.valueOf(word, 0, charCount) + (result.length() > 0 ? " " : "") + result;
                    inWord = false;
                    charCount = 0;
                }
            } else {
                word[charCount++] = c;
                inWord  = true;
            }
        }

        // last word if any can be now added to the begininig.
        return (charCount > 0 ? String.valueOf(word, 0, charCount) + (result.length() > 0 ? " " : ""): "") + result;
    }

    public static void main (String args []) {
        String input = "    a    1    ";

        System.out.println("Reversed words string: " + reverse(input) + ", Input string: " + input);
    }
}
