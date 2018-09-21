package io.anand.learn;

import java.util.HashMap;
import java.util.HashSet;

public class PermutationPalindrome {

    public static boolean hasPalindromePermutation(String theString) {

        // check if any permutation of the input is a palindrome
        // Map for characters and their counts.
        HashMap <Character, Integer> h = new HashMap<>();

        for (int i = 0; i < theString.length(); i++) {
            // Keep a character count
            // For permutation to work, if we have even number of characters
            // or atmost only 1 character of single count we can form a
            // permutation palindrome.
            char ch = theString.charAt(i);
            Integer count = h.get(ch);
            if (null == count)
                h.put(ch, 1);
            else
                h.replace(ch, count + 1);
        }

        // Now walk the hash map and if we find any odd character count
        // we return false
        // OR more than one character is a single count.
        boolean singleCharFound = false;
        for (Integer count: h.values()) {
            if (1 == count) {
                if (!singleCharFound) {
                    singleCharFound = true;
                    continue;
                }
                else
                    return false;
            }
            if ((1 == (count % 2)))
                return false;
        }
        return true;
    }

    public static boolean hasPalindromePermutationSet(String theString) {

        // check if any permutation of the input is a palindrome
        // Map for characters and their counts.
        HashSet<Character> h = new HashSet<>();

        for (int i = 0; i < theString.length(); i++) {
            // Keep a Set
            // Add each character only if not already present in the set.
            // If present, remove the character.
            // In the end we should only have at most one character.
            char ch = theString.charAt(i);
            if (h.contains(ch))
                h.remove(ch);
            else
                h.add(ch);
        }

        // Set must at most contain 1 character.
        if (1 < h.size())
            return false;
        return true;
    }

    public static void main(String[] args) {
        String s1 = "civic";
        String s2 = "hello";

        System.out.format("String: \"%s\" %s a permutation palindrome \n", s1, hasPalindromePermutationSet(s1) ? "is": "is NOT");
        System.out.format("String: \"%s\" %s a permutation palindrome \n", s2, hasPalindromePermutationSet(s2) ? "is": "is NOT");
    }
}
