package io.anand.play;

import java.util.Arrays;
import java.util.HashMap;

public class PermutationStringCharacaters {
    
    public static boolean isPermutationHash (String s1, String s2) {     
        // If they are not ame size, just say No!
        if (s1.length() != s2.length())
            return false;
        
        // Map for characters and their counts.
        HashMap <Character, Integer> h = new HashMap <> ();
        Integer count;
        char ch;
        
        for (int i = 0; i < s1.length(); i ++) {
            // For first string, if we have seen this character before increment the count
            // else seed it with a 1.
            ch = s1.charAt(i);
            count = h.get(ch);
            if (null == count)
                h.put(ch, 1);
            else
                h.replace(ch, count + 1);

            // For second string characters we go the other way and decrement the count.
            // else seed it with a -1.
            ch = s2.charAt(i);
            count = h.get(ch);
            if (null == count)
                h.put(ch, -1);
            else
                h.replace(ch,  count - 1); 
        }

        // Intent is at the end of the loop all values in the hashmap 
        // must be zero (the adds from s1 and subs from s2).
        for (Integer i :h.values())
            if (0 != i)
                return false;
        
        // Iterate over the Strings.
        return true;
    }
    
    public static boolean isPermutationSort (String s1, String s2) {
        // If they are of different length straight No!
        if (s1.length() != s2.length())
            return false;

        // Get the underlying bytes for both
        byte [] s1Bytes = s1.getBytes();
        byte [] s2Bytes = s2.getBytes();
        
        // Sort both arrays of bytes. 
        Arrays.parallelSort(s1Bytes);
        Arrays.parallelSort(s2Bytes);
        
        // Compare the arrays and if they are different at any index
        for (int i = 0; i < s1Bytes.length; i++)
            if (s1Bytes[i] != s2Bytes[i])
                return false;
        return true;
    }

    public static void main(String[] args) {
        String s1 = "Hello World! ";
        String s2 = "World Hell o! ";
        
        System.out.format("permutationSort: String: \"%s\" and \"%s\" are%s permuations of each other\n", s1, s2, isPermutationSort(s1, s2) ? "": " NOT");
        System.out.format("permutationHash: String: \"%s\" and \"%s\" are%s permuations of each other\n", s1, s2, isPermutationHash(s1, s2) ? "": " NOT");
    }

}
