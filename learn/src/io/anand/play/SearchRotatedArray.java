package io.anand.play;

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

        if (a[mid] == key)
            return mid;

        boolean firstHalfSorted   = a[start] < a[mid];
        boolean secondHalfSorted  = a[mid] < a[end];

        boolean searchSecondHalf;
        if (firstHalfSorted && (key >= a[start]) && (key < a[mid]))
            // If the first half is regular sorted part (start < mid) and our key value is >= start of the section
            // and < middle value -> search start -> mid - 1
            searchSecondHalf = false;
        else if (secondHalfSorted && (key > a[mid]) && (key <= a[end]))
            // If the mid onward is regular sorted part (mid < end) and our key value is > mid of the section
            // and l<= end value -> search mid + 1 -> end
            searchSecondHalf = true;
        else if (!firstHalfSorted)
            // UnSorted first half and have eliminated the possibility of key in second sorted half.
            searchSecondHalf = false;
        else if (!secondHalfSorted)
            // Unsorted second half, and have eliminated the possibility of key in second sorted half.
            searchSecondHalf = true;
        else
            searchSecondHalf = true;

        // Adjust the next search indices
        if (searchSecondHalf)
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

            if (a[mid] == key)
                return mid;

            boolean firstHalfSorted   = a[start] < a[mid];
            boolean secondHalfSorted  = a[mid] < a[end];

            boolean searchSecondHalf;
            if (firstHalfSorted && (key >= a[start]) && (key < a[mid]))
                // If the first half is regular sorted part (start < mid) and our key value is >= start of the section
                // and < middle value -> search start -> mid - 1
                searchSecondHalf = false;
            else if (secondHalfSorted && (key > a[mid]) && (key <= a[end]))
                // If the mid onward is regular sorted part (mid < end) and our key value is > mid of the section
                // and l<= end value -> search mid + 1 -> end
                searchSecondHalf = true;
            else if (!firstHalfSorted)
                // UnSorted first half and have eliminated the possibility of key in second sorted half.
                searchSecondHalf = false;
            else if (!secondHalfSorted)
                // Unsorted second half, and have eliminated the possibility of key in second sorted half.
                searchSecondHalf = true;
            else
                searchSecondHalf = true;

            // Adjust the next search indices
            if (searchSecondHalf)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }

    private static boolean useRecursion = true;
    private static int binarySearchRotated (int [] a, int key) {
        if (useRecursion)
            return binSearchRecurse(a, key, 0, a.length - 1);
        return binSearchIter(a, key);
    }

    public static void main(String []args){
        int[] v1 = {6, 7, 1, 2, 3, 4, 5};
        System.out.println("Key(3) found at: "+binarySearchRotated(v1, 3));
        System.out.println("Key(6) found at: "+binarySearchRotated(v1, 6));
        int[] v2 = {4, 5, 6, 1, 2, 3};
        System.out.println("Key(3) found at: "+binarySearchRotated(v2, 3));
        System.out.println("Key(6) found at: "+binarySearchRotated(v2, 6));
    }
}
