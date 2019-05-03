package io.anand.play;

import java.io.InputStreamReader;
import java.util.*;

public class MaxNumSlidingWindow {
    public static void find_max_sliding_window(int[] array, int window_size) {

        int[] result = new int[array.length - window_size + 1];
        int max = 0;
        int count = 1;
        int j = 0;

        //traverse the array while shifting the window forward
        for (int i = 0; i < array.length; i++) {
            //find maximum in the current window
            if (array[i] > max) {
                max = array[i];
            }
            if (count == window_size) {
                result[j++] = max;
                max = 0;
                // this seeks the array index back to next number in the sequence.
                i = i - count + 1;
                count = 0;
            }
            count++;
        }
        System.out.println(Arrays.toString(result) + ", ");
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 3, 2, 1, 2, 5};
        System.out.println("Window Size 4");
        find_max_sliding_window(array, 4);

        System.out.println("\nWindow Size 3");
        find_max_sliding_window(array, 3);

        System.out.println("\nWindow Size 5");
        find_max_sliding_window(array, 5);
    }
}
