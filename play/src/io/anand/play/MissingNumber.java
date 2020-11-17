package io.anand.play;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MissingNumber {

        public static int getMissingNumber(int[] A) {
            // write your code in Java SE 8
            // Keep an expected number.
            // Since smallest possible positive number is 1
            int smallestPosNum = 1;
            Set<Integer> numSet = new HashSet<>();

            for (int i = 0; i < A.length; i++) {
                // if we did find this number, set the expectation to be next one.
                // However, the array may not be sorted and so we must remember to add the numbers seen
                // to some lookup.
                // Use a hash and check if we have seen it before.
                // Reason to lookup is the value we now want might have been seen. If this was sorted,
                // we have no reason that we will find a value earlier. But since this is not sorted,
                // we must lookup if we have seen this before.
                numSet.add(A[i]);
                if (numSet.contains(smallestPosNum)) {
                    smallestPosNum++;
                }
            }

            // We have to scan the lookup once we get to the end. The smallest number could have moved in lumps and miss
            // checking
            // [1,3,6,4,7,2]
            while (true) {
                if (numSet.contains(smallestPosNum)) {
                    smallestPosNum++;
                } else {
                    break;
                }
            }
            return smallestPosNum;
        }

    public static void main(String[] args) throws IOException {
        int num [] = {1,3,6,4,1,2};
        int result = getMissingNumber(num);

        System.out.println("Numbers: " + Arrays.toString(num) + ", Smallest Positive Missing Number: " + result);
    }


}
