package io.anand.play;

import java.util.Arrays;

public class StringNumberAddition {
    // Needed for persisting across invocation
    static int carry    = 0;
    static int place    = 1;

    private static int addStringDigits (char digOne, char digTwo, int sum) {
        int val = carry + (digOne - '0') + digTwo - '0';
        carry   = val / 10;
        sum     += (val % 10) * place;
        place   *= 10;
        return sum;
    }

    // Start from the end of both strings and add them one place value
    // at a time, do account for carry
    private static int addStringNumbers (String numOne, String numTwo) {
        // Sanitize the inputs
        // Null strings, empty strings
        // non numeric characters in the input
        // define the expectation and return a defined output to indicate invalid input

        int sum     = 0;
        int len1    = numOne.length();
        int len2    = numTwo.length();
        // Determine the long string and make it as numOne
        // This helps rest of the logic to treat indices and
        // strings to pick for adding remaining digits from the longer
        // string
        // Add remaining digits from longer string
        if (len1 < len2) {
            String temp = numTwo;
            numTwo      = numOne;
            numTwo      = temp;
            int    len  = len2;
            len2        = len1;
            len1        = len;
        }

        // Now numOne is the longer string and len1 is it's length
        // Need the diff to use the offset for loop on longer string
        int diff = len1 - len2;

        // Will traverse both till we finish the shorter string length
        for (int i = len2 - 1; i >= 0; i --)
            sum = addStringDigits(numOne.charAt(i + diff), numTwo.charAt(i), sum);

        // Remaining digits from longer number
        // not a big one here, but can refactor this logic to reduce duplication and errors
        for (int i = diff - 1; i >= 0; i --)
            sum = addStringDigits(numOne.charAt(i + diff), '0', sum);

        // Last addition would have possible left acarry and do not
        // ignore the place value for this carry and add it
        return addStringDigits('0', '0', sum);
    }

    public static void main(String []args){
        String numOne = "9894";
        String numTwo = "817";

        System.out.println("Input: number 1: " + numOne + ", number 2: " + numTwo);
        System.out.println("Sum: " + addStringNumbers(numOne, numTwo));
    }
}
