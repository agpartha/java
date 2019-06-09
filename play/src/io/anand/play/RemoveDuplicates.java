package io.anand.play;

import java.util.Arrays;

public class RemoveDuplicates {

    static public int removeDuplicates (int nums []) {
        int len = nums.length;

        // We need atleast 2 to remove duplicates
        if (2 > len)
            return len;

        int i = 0;
        int j = 1;

        while (j < len) {
            // Copy over the different numbers and advance our non-duplicate index
            if (nums[j] != nums[i]) {
                i++;j++;
                // For the case where there are no duplicates, could we skip the copy ?
                // we can instead move i, j for this case.
                // copy it over is needed only if the j is not the successor,
                // which will be case if they differ by more than 1.
                if (1 != (j - i))
                    nums[i] = nums[j - 1];
            } else {
                // Keep traversing but keep non-duplicate index in place
                j++;
            }
        }
        //       System.out.println("Processed Array length: " + (i + 1));
        return i + 1;
    }

    public static void main (String args []) {
        int nums [] = {1,1,1,1,1,2,3,4,4,4,5,5,6,6};
//        int nums [] = {1,2,3,4,5,5,6,6};
//        int nums [] = {1,2,3,3,3,4,5,6,6,7,8};
//        int nums [] = {1,2,3,4,5,6};
//        int nums [] = {1,2};

        int result;
        System.out.println("Input: " + Arrays.toString(nums));
        result = removeDuplicates(nums);
        System.out.println("Result: " + Arrays.toString(Arrays.copyOf(nums, result)));
    }
}
