package io.anand.play;

import java.util.Arrays;

public class StringNumberAddition {
    /*
    //a is sorted array
    private static int binSearchRecurse(int[] a, int key, int start, int end) {

        // crossed indices means we are done searching.
        if (end < start)
            return -1;

        // Search for the value for the midpoint and if less than midpoint
        // search within that section, else the half after mid point
        //
        int mid = start + (end - start) / 2;
        System.out.println("start: " + start + ", end: " + end + ", mid: " + mid + ", value[mid]: " + a[mid]);

        // If the value is at any one of the locations we have access to, just check and be done with it.
        if (a[mid] == key)
            return mid;

        boolean firstHalfSorted   = a[start] < a[mid];
        boolean secondHalfSorted  = a[mid] < a[end];

        boolean searchFirstHalf;
        if (firstHalfSorted) {
            // If the first half is sorted and key value is in range
            if ((key >= a[start]) && (key < a[mid]))
                searchFirstHalf = true;
            else
                // Sorted, but key is out of range, only chance is second half.
                searchFirstHalf = false;
        } else if (secondHalfSorted)  {
            // If the second half is sorted and our key value is in range
            if ((key > a[mid]) && (key <= a[end]))
                searchFirstHalf = false;
            else
                // Sorted, but key is out of range, only chance is first half.
                searchFirstHalf = true;
        } else
            // Technically not valid since array is sorted at-least in one of the halves.
            return -1;

        // Adjust the next search indices
        if (searchFirstHalf)
            return binSearchRecurse(a, key, start, mid -1);
        else
            return binSearchRecurse(a, key,mid + 1, end);
    }

    //a is sorted array
    private static int binSearchIter(int[] a, int key) {
        int end = a.length - 1;
        int start = 0;

        // Edge cases
        if (1 == a.length)
            if (a[0] == key)
                return 0;
        // Search for the value for the modpoint and if less than midpoint
        // search within theat section, else the half after mid point
        //
        while (start <= end) {
            int mid = start + (end - start) / 2;
            System.out.println("start: " + start + ", end: " + end + ", mid: " + mid + ", value[mid]: " + a[mid]);

            // If the value is at any one of the locations we have access to, just check and be done with it.
            if (a[mid] == key)
                return mid;

            boolean firstHalfSorted   = a[start] < a[mid];
            boolean secondHalfSorted  = a[mid] < a[end];

            boolean searchFirstHalf;
            if (firstHalfSorted) {
                // If the first half is sorted and key value is in range
                if ((key >= a[start]) && (key < a[mid]))
                    searchFirstHalf = true;
                else
                    // Sorted, but key is out of range, only chance is second half
                    searchFirstHalf = false;
            } else if (secondHalfSorted)  {
                // If the second half is sorted and our key value is in range
                if ((key > a[mid]) && (key <= a[end]))
                    searchFirstHalf = false;
                else
                    // Sorted, but key is out of range, only chance is first half
                    searchFirstHalf = true;
            } else
                // Technically not valid since array is sorted at-least in one of the halves.
                return -1;

            // Adjust the next search indices
            if (searchFirstHalf)
                end = mid - 1;
            else
                start = mid + 1;
        }
        return -1;
    }

    private static boolean useRecursion = false;
    private static int binarySearchRotated (int [] a, int key) {
        if (useRecursion)
            return binSearchRecurse(a, key, 0, a.length - 1);
        return binSearchIter(a, key);
    }
     */

    // Start from the end of both strings and add them one place value
    // at a time, do account for carry
    private static int addStringNumbers(String numOne, String numTwo) {

        // Sanitize the inputs
        // Null strings, empty strings
        // non numeric characters in the input
        // define the expectation and return a defined output to indicate invalid input

        int len1    = numOne.length();
        int len2    = numTwo.length();
        int carry   = 0;
        int sum     = 0;

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

        int place = 1;
        // Will traverse both till we finish the shorter string length
        for (int i = len2 - 1; i >= 0; i --) {
            int val = carry + (numOne.charAt(i + diff) - '0') + (numTwo.charAt(i) - '0');
            carry   = val / 10;
            sum     = sum + (val % 10) * place;
            place   *= 10;
        }

        // Remaining digits from longer number
        // not a big one here, but can refactor this logic to reduce duplication and errors
        for (int i = diff - 1; i >= 0; i --) {
            int val = carry + (numOne.charAt(i) - '0');
            carry   = val / 10;
            sum     = sum + (val % 10) * place;
            place   *= 10;
        }

        // Last addition would have possible left acarry and do not
        // ignore the place value for this carry and add it
        sum += carry * place;
        return sum;
    }

    public static void main(String []args){
        String numOne = "9894";
        String numTwo = "1022";

        System.out.println("Input: number 1: " + numOne + ", number 2: " + numTwo);
        System.out.println("Sum: " + addStringNumbers(numOne, numTwo));
    }
}
