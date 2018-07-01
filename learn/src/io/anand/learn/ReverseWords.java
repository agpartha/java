package io.anand.learn;

import java.util.ArrayList;
import java.util.List;

public class ReverseWords {

    static public String reverse(String S) {
        String result = "";

        String words[] = new String [S.length()];

        boolean       inWord = false;
        char          word[] = new char[S.length()];
        int           numWords = 0;
        int           charCount = 0;
        // Capture the words and add them to the list
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            // If we have a space detected, if we are
            // collecting characters of a word, time to wrap
            // and add to the list
            if (' ' == c) {
                if (inWord) {
                    words[numWords++] = String.valueOf(word, 0, charCount);
                    inWord = false;
                    charCount = 0;
                }
            } else {
                word[charCount++] = c;
                inWord  = true;
            }
        }

        // If we were assembling a word, close it.
        if (charCount > 0)
            words[numWords++] = String.valueOf(word, 0, charCount);

        // We now have words in the list.
        // Just assemble the words backwards
        for (int i = numWords; i > 0; i--) {
            result += words[--numWords];
            if (i > 1) result += " ";
        }

        return result;
    }

    public static void main (String args []) {
        String input = "the sky is blue";

        System.out.println("Reversed words string: " + reverse(input) + ", Input string: " + input);
    }
}
