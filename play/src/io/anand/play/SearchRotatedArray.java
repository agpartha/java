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
    private static int binSearchIterVerbose(int[] a, int key) {
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

    //a is sorted array
    private static int binSearchIter(int[] a, int key) {
        int end = a.length - 1;
        int start = 0;

        // Edge cases
        if (1 == a.length)
            if (a[0] == key)
                return 0;
        // Search for the value for the modpoint and if less than midpoint
        // search within that section, else the half after mid point
        //
        while (start <= end) {
            int mid = start + (end - start) / 2;
            System.out.println("start: " + start + ", end: " + end + ", mid: " + mid + ", value[mid]: " + a[mid]);

            // If the value is at any one of the locations we have access to, just check and be done with it.
            if (a[mid] == key)
                return mid;

            // Start with base assumption, Of course we will validate further
            boolean searchFirstHalf = false;
            if (a[start] <= a[mid]) {
                // If the first half is sorted and key value is in range
                if ((key >= a[start]) && (key < a[mid]))
                    searchFirstHalf = true;
            } else if (a[mid] <= a[end])  {
                // If the second half is sorted and our key value is out of range
                if ((key > a[end]) || (key < a[mid]))
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


    private static int binSearchIter2(int[] a, int key) {
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

            // Start with base assumption, Of course we will validate further
            boolean searchFirstHalf = false;
            if ((a[start] <= a[mid]) && ((key >= a[start]) && (key < a[mid]))) {
                // If the first half is sorted and key value is in this range
                searchFirstHalf = true;
            } else if ((a[mid] <= a[end]) && ((key < a[mid]) || (key > a[end]))) {
                    // If the second half is sorted and key value is out of this range
                    searchFirstHalf = true;
            }

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

    private static int findPivot (int [] a) {
        int end = a.length - 1;
        int start = 0;
        
        // There is a pivot somewhere, do a regular binary search
        // When we see a mid, check if that value is meeting the condition of
        // mid-1 > mid < mid+1
        while (start < end) {
            int mid = start + (end - start) / 2;
            System.out.println("start: " + start + ", end: " + end + ", mid: " + mid + ", value[mid]: " + a[mid]);

            // Edge cases
            if (end == (start + 1))
                if (a[end] < a[start])
                    return end;
            else
                return -1;

            // Did we meet the pivot criteria
            if ((a[mid] < a[mid-1]) && (a[mid] < a[mid+1]))
                return mid;
            // Find the sorted part and look in the other section
            if (a[start] > a[mid])
                end = mid;
            else
                start = mid;
        }
        return -1;
    }

    public static void main(String []args){

        int[] v1 = {6, 7, 1, 2, 3, 4, 5};
        int p;
        
        System.out.println("Input: " + Arrays.toString(v1));
        p = findPivot(v1);
        System.out.println("Pivot: " + (p > 0 ? v1[p] + " at index: " + p : "Not Found"));
        System.out.println("Key(3) found at: "+binarySearchRotated(v1, 3));
        System.out.println("Key(6) found at: "+binarySearchRotated(v1, 6));
        System.out.println("Key(1) found at: "+binarySearchRotated(v1, 1));
        int[] v2 = {4, 5, 6, 1, 2, 3};
        System.out.println("Input: " + Arrays.toString(v2));
        p = findPivot(v2);
        System.out.println("Pivot: " + (p > 0 ? v2[p] + " at index: " + p : "Not Found"));
        System.out.println("Key(3) found at: "+binarySearchRotated(v2, 3));
        System.out.println("Key(6) found at: "+binarySearchRotated(v2, 6));
        System.out.println("Key(4) found at: "+binarySearchRotated(v2, 4));
        System.out.println("Key(90) found at: "+binarySearchRotated(v2, 90));
        System.out.println("Key(1) found at: "+binarySearchRotated(v2, 1));
        System.out.println("Key(-1) found at: "+binarySearchRotated(v2, -1));
        int[] v3 = {1, 2, 2, 2, 2};
        System.out.println("Input: " + Arrays.toString(v3));
        System.out.println("Key(2) found at: "+binarySearchRotated(v3, 2));
        p = findPivot(v3);
        System.out.println("Pivot: " + (p > 0 ? v3[p] + " at index: " + p : "Not Found"));
        int[] v4 = {3, 2};
        System.out.println("Input: " + Arrays.toString(v4));
        System.out.println("Key(2) found at: "+binarySearchRotated(v4, 2));
        p = findPivot(v4);
        System.out.println("Pivot: " + (p > 0 ? v4[p] + " at index: " + p : "Not Found"));
        int[] v5 = {3, 4, 2};
        System.out.println("Input: " + Arrays.toString(v5));
        System.out.println("Key(2) found at: "+binarySearchRotated(v5, 2));
        p = findPivot(v5);
        System.out.println("Pivot: " + (p > 0 ? v5[p] + " at index: " + p : "Not Found"));
    }
}
