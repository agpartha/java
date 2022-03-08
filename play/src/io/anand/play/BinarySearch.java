package io.anand.play;

import java.util.Arrays;

public class BinarySearch {

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
        if (a[start] == key)
            return start;
        if (a[end] == key)
            return end;

        if (key > a[mid])
            return binSearchRecurse(a, key,mid + 1, end);
        else
            return binSearchRecurse(a, key, start, mid -1);
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
            if (a[start] == key)
                return start;
            if (a[end] == key)
                return end;

            if (key > a[mid])
                start = mid + 1;
            else
                end = mid -1;
        }
        return -1;
    }

    private static boolean useRecursion = true;
    private static int binSearch (int [] a, int key) {
        if (useRecursion)
            return binSearchRecurse(a, key, 0, a.length - 1);
        return binSearchIter(a, key);
    }

    public static void main(String args[]) {
        int[] a = {1, 2, 4, 7, 8, 12, 15, 19, 24, 50, 69, 80, 100};
        int key = 7;
        int index = binSearch(a, key);

        System.out.println("Key: " + key + " at the index: " + index + ", in input: " + Arrays.toString(a));
    }
}
