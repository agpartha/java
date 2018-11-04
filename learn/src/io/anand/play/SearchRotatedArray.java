package io.anand.play;

import java.util.Arrays;

public class SearchRotatedArray {
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
        /*
        if (a[start] == key)
            return start;
        if (a[end] == key)
            return end;
*/
        boolean firstHalfSorted   = a[start] < a[mid];
        boolean secondHalfSorted  = a[mid] < a[end];

        boolean searchFirstHalf;
        if (firstHalfSorted && (key >= a[start]) && (key < a[mid]))
            // If the first half is regular sorted part (start < mid) and our key value is >= start of the section
            // and < middle value -> search start -> mid - 1
            searchFirstHalf = true;
        else if (secondHalfSorted && (key > a[mid]) && (key <= a[end]))
            // If the mid onward is regular sorted part (mid < end) and our key value is > mid of the section
            // and l<= end value -> search mid + 1 -> end
            searchFirstHalf = false;
        else if (!firstHalfSorted)
            // UnSorted first half and have eliminated the possibility of key in second sorted half.
            searchFirstHalf = true;
        else if (!secondHalfSorted)
            // Unsorted second half, and have eliminated the possibility of key in second sorted half.
            searchFirstHalf = false;
        else
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
            /*
            if (a[start] == key)
                return start;
            if (a[end] == key)
                return end;
*/
            boolean firstHalfSorted   = a[start] < a[mid];
            boolean secondHalfSorted  = a[mid] < a[end];

            boolean searchFirstHalf;
            if (firstHalfSorted) {
                if ((key >= a[start]) && (key < a[mid]))
                    // If the first half is sorted  (start < mid) and our key value is >= start and < middle
                    searchFirstHalf = true;
                else
                    // Sorted, but key is out of range, only chance is second half.
                    searchFirstHalf = false;
            } else if (secondHalfSorted)  {
                    if ((key > a[mid]) && (key <= a[end]))
                    // If the second half is sorted (mid < end) and our key value is > middle and <= end
                    searchFirstHalf = false;
                else
                    // Sorted, but key is out of range, only chance is first half.
                    searchFirstHalf = true;
            } else
                // Technically not valid since array is sorted atleast in one of the halves.
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

    public static void main(String []args){
        int[] v1 = {6, 7, 1, 2, 3, 4, 5};
        System.out.println("Input: " + Arrays.toString(v1));
        System.out.println("Key(3) found at: "+binarySearchRotated(v1, 3));
        System.out.println("Key(6) found at: "+binarySearchRotated(v1, 6));
        int[] v2 = {4, 5, 6, 1, 2, 3};
        System.out.println("Input: " + Arrays.toString(v2));
        System.out.println("Key(3) found at: "+binarySearchRotated(v2, 3));
        System.out.println("Key(6) found at: "+binarySearchRotated(v2, 6));
    }
}
