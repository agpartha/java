package io.anand.play;

import java.io.IOException;
import java.util.Arrays;

public class WinterSummer {
    static public int getSummerStart(int[] T) {
        // write your code in Java SE 8

        // Given Winter always is colder than summer and they are preceeding values
        int left, marker, right;

        left = 0;
        right = T.length - 1;

        while (left <= right) {
//        System.out.println("left: " + T[left] + ", right: " + T[right]);
            // Advance to summer till we see a temparature rising
            if (T[left] <= T[right])
                left++;
            // Advance to Winter till we see a temparature dropping
            if (T[right] > T[left])
                right--;
            // If we see a temperature from right dropping than one of left, we have crossed over.
            if (T[right] < T[left])
                break;
        }
        return right + 1;
    }


    public static void main(String[] args) throws IOException {
        int num [] = {5, 1, 0, -5, 2, 3, 8, 6};
        int result = getSummerStart(num);

        System.out.println("Temperatures: " + Arrays.toString(num) + ", Summer starts at: " + result);
    }
}
