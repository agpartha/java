package io.anand.learn;

import java.util.Arrays;

public class RemoveDuplicates {

    static public int removeDuplicates (int nums []) {
        int len = nums.length;

        if (2 > len)
            return len;

        int i = 0;
        int j = 1;

        while (j < len) {
            // Copy over the different numbers and advance our non-duplicate index
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j++];
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
        int result;
        System.out.println("Input: " + Arrays.toString(nums));
        result = removeDuplicates(nums);
        System.out.println("Result: " + Arrays.toString(Arrays.copyOf(nums, result)));
    }
}
