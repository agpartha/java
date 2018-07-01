package io.anand.learn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    static public int [] twoSum (int nums[], int target) {
        int result[] = new int [2];
        Map<Integer, Integer> numberIndex = new HashMap<>();

        // For each element, if we have a complement value to make up the sum
        // it must be already found in the map.
        // Else save it so that a future complement value of this current value
        // can find us!
        for (int i = 0; i < nums.length; i++) {
            Integer complementIndex = numberIndex.get(target - nums[i]);

            // Save ourselves for future suitor
            // Of course this means we cannot have duplicate values for any element.
            if (null == complementIndex) {
                numberIndex.put(nums[i], i);
 //               System.out.println("Adding map[" + i + "]: " + nums[i]);
                continue;
            }

            // Found our complement, pack up!
            result[0] = complementIndex;
            result[1] = i;
            System.out.println("Matched indices: " + result[0] + ", " + result[1] + ", Target: " + target + ", Input: " + Arrays.toString(nums));
            break;
        }
        return result;
    }

    public static void main (String args []) {
        int nums [] = { 1, 2, 3 , 4, 5, 6, 7};
        int target  = 5;

        twoSum(nums, target);
    }
}
