package io.anand.sandbox;

import java.util.Arrays;

public class BinarySearch {
    //a is sorted array
    private static int binSearch(int[] a, int key) {
        //TODO: Write - Your - Code
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
            System.out.println("start: " + start + ", end: " + end + ", mid: " + mid);

            if (a[mid] == key)
                return mid;
            if (key > a[mid])
                start = mid + 1;
            else
                end = mid -1;
        }
        return -1;
    }

    public static void main(String args[]) {
        int[] a = {1, 2, 4, 7, 8, 12, 15, 19, 24, 50, 69, 80, 100};
        int key = 89;
        int index = binSearch(a, key);

        System.out.println("Key: " + key + " at the array: " + index + ", in input: " + Arrays.toString(a));
    }
}
