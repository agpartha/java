package io.anand.learn;

import java.util.Arrays;

public class Urlify {
    
    // This function would be an option if we were handed a char array
    // that has the original string and has enough spaces for expansion 
    // of space characters.
    // 
    // For the sake of this code, we build out that character array 
    // and encode it in place.
    public static String encodeUrlRev (String s, int num_spaces) {
        // each ' ' -> '%20' so get new array which we can fit all content.
        char [] eStr = Arrays.copyOf(s.toCharArray(), s.length() + 3 * num_spaces + 1);

        // Intent is to start copying the charaters from the end of the string to the end
        // of the array. when we see a ' ' we copy the escape sequence instead. We should 
        // not step on not yet copied data since the space should be large to cover all spaces
        for (int i = s.length(), j = s.length() + 3 * num_spaces; i >= 0; i--) {
            char ch = eStr[i];
            System.out.format("index: %d, character: %c, out index: %d => ", i, ch, j);
            if (' ' == ch) {
                System.out.println("Escaping");
                eStr[j--] = '0';
                eStr[j--] = '2';
                eStr[j--] = '%';
            } else {
                System.out.println("Copying");
                eStr[j--] = ch;
            }
        }
        // Have to get a new String to box the character array. No .toString() does not work!
        return new String(eStr);
    }

    public static String encodeUrl (String s, int num_spaces) {
        // each ' ' -> '%20' so get new array which we can fit all content.
        char [] eStr = new char [s.length() + 3 * num_spaces + 1];

        // Copy the characters from source string to the new array and replace 
        // the spaces with escape characters.
        for (int i = 0, j = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            System.out.format("index: %d, character: %c, out index: %d => ", i, ch, j);
            if (' ' == ch) {
                System.out.println("Escaping");
                eStr[j++] = '%';
                eStr[j++] = '2';
                eStr[j++] = '0';
            } else {
                System.out.println("Copying");
                eStr[j++] = ch;
            }
        }
        // Have to get a new String to box the character array. No .toString() does not work!
        return new String(eStr);
    }

    public static void main(String[] args) {
        String s = "Hello World with 4 spaces   ";
        System.out.format("\"%s\" --URLified--> \"%s\"", s, Urlify.encodeUrlRev(s, 7));
    }

}
