// Simple Min-Heap implementation using Array Lists for Generic Types.
package io.anand.play;

import java.util.Arrays;

class PeakMountainArray {
    static public int peakIndexInMountainArray(int[] A) {

        int peakIndex = -1;
        int peakValue = 0;
        boolean gradientUp = false;

        for (int i = 1; (i < A.length); i ++) {
            // are we climbing or coming down
            gradientUp = (A[i] > A[i - 1]) ? true : false;
            if (gradientUp && (A[i] > peakValue)){
                System.out.println("New Peak at index: " + i + ", value: " + A[i] + ", gradientUp: " + gradientUp);
                peakIndex = i;
            }
        }
        // We detected a slow down from the peak of the mountain
        if (!gradientUp && (-1 != peakIndex))
            return peakIndex;
        else
            return 0;
    }

    public static void main(String[] args) {
        int A[] = {1, 3, 2, 2};
        System.out.println("Peak Index: " + peakIndexInMountainArray(A) + ", for the input array: " + Arrays.toString(A));
    }
}
