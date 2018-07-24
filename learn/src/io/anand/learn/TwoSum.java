package io.anand.learn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    static public int [] twoSum_A (int nums[], int target) {
        // Sort the Array
        Arrays.sort(nums);

        int result[] = new int [2];
        result[0] = result[1] = -1;

        // Less than two numbers, bye bye
        if (nums.length < 2)
            return result;

        // For each number look for the complement from the end of the array.
        // ex: 1 + x = 10, we need 9 and if last value is 15, 9 most likely if there
        // will be earlier and so look leftward from the end.
        // On the other hand if 2 + x = 12 and last value is 6, we need to move till we hit
        // a value of 6 or more potentially being there.
        for (int left = 0, right = nums.length - 1; left < right;) {
            // What's the counterpart to left value that we need ?
            int needComplement = target - nums[left];

            // Did we find the right value ?
            if (needComplement == nums[right]) {
                result[0] = left;
                result[1] = right;
                break;
            }

            // Is that higher than the right most value ? If so
            // maybe our next candidate from left is the one so increment left
            if (needComplement > nums[right]) {
                left++;
                continue;
            }

            // Opposite case, if the value we need is already less than the value at the end,
            // we can move the end closer to bring the complement closer to what we want.
            if (needComplement < nums[right]) {
                right--;
                continue;
            }
        }

        System.out.println("TwoSum: Matched indices: " + result[0] + ", " + result[1] + ", Target: " + target + ", Input: " + Arrays.toString(nums));
        return result;
    }

    static public int [] twoSum (int nums[], int target) {
        int result[] = new int [2];
        Map<Integer, Integer> numberIndex = new HashMap<>();

        result[0] = result[1] = -1;

        // Less than two numbers, bye bye
        if (nums.length < 2)
            return result;

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
            System.out.println("TwoSum: Matched indices: " + result[0] + ", " + result[1] + ", Target: " + target + ", Input: " + Arrays.toString(nums));
            break;
        }
        return result;
    }


    static public int [] threeSum (int nums[], int target) {
        int result[] = new int [3];
        result[0] = result[1] = result[2] = -1;

        // Less than three numbers, bye bye
        if (nums.length < 3)
            return result;

        // For each element, if we have a complement value to make up the sum
        // potentially is in the remaining array and leverage twosum for finding it.
        // if not fund advance to next element and repeat.
        for (int i = 0; i < nums.length -1; i++) {
            int result2Sum[] = { -1, -1};
            int needComplement = target - nums[i];
            result2Sum = twoSum_A(Arrays.copyOfRange(nums, i + 1, nums.length), needComplement);
            if ( (result2Sum[0] != -1) && (result2Sum[1] != -1)) {
                result[0] = i;
                result[1] = result2Sum[0] + i + 1;
                result[2] = result2Sum[1] + i + 1;
                break;
            }
        }
        System.out.println("Three Sum: Matched indices: " + result[0] + ", " + result[1] + ", " + result[2] + ", Target: " + target + ", Input: " + Arrays.toString(nums));
        return result;
    }

    public static void main (String args []) {
        //int nums [] = { 1, 2, 3, 4, 5, 6, 7};
        int nums [] = { 1, 2, 6};
        int twoSmTarget    = 7;
        int threeSumTarget = 9;

        twoSum_A(nums, twoSmTarget);
        threeSum(nums, threeSumTarget);
    }
}
