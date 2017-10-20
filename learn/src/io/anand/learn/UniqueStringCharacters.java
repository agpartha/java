package io.anand.learn;

import java.util.HashMap;
import java.util.HashSet;

public class UniqueStringCharacters {
  
    public static boolean hasUniqueCharHash (String s) {
        int count = 0;
        HashSet <Character> h = new HashSet <> ();
        for (int i = 0; i < s.length(); i ++) {
            count++;
            if (h.contains(s.charAt(i))) {
                System.out.format("Loop count : %d, string length: %d\n", count, s.length());
                return false;
            }
            h.add(s.charAt(i));
        }
        System.out.format("Loop count : %d, string length: %d\n", count, s.length());
        return true;
    }
    
    
    public static boolean hasUniqueCharBrute (String s) {
        int count = 0;
        for (int i = 0; i < s.length() - 1; i ++) {
            for (int j = i + 1; j < s.length(); j++) {
                count++;
                if (s.charAt(i) == s.charAt(j)) {
                    System.out.format("Loop count : %d, string length: %d\n", count, s.length());  
                    return false;
                }
            }
        }
        System.out.format("Loop count : %d, string length: %d\n", count, s.length());
        return true;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s = "This can ok";
        System.out.format("String: %s is %smade of unique characters using Brute\n", s, hasUniqueCharBrute(s) ? "" : "NOT ");
        System.out.format("String: %s is %smade of unique characters using Hash\n", s, hasUniqueCharHash(s) ? "" : "NOT ");
    }

}
