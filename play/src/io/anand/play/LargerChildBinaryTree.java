package io.anand.play;

/*
  Given a binary tree as array, find which sub child if root is larger and print "Left" or "Right"
 */

import java.util.Arrays;

public class LargerChildBinaryTree {

    public static long treeSum(long[] arr, int index) {
        long sum = 0;
        int i = index;

        if (i < arr.length) {
            // Add yourself
            if (-1 != arr[index]) {
                sum += arr[index];
                // Left sum (left is (index * 2) + 1)
                sum += treeSum(arr, (index * 2 + 1));
                // Right sum (index * 2 + 2)
                sum += treeSum(arr, (index * 2 + 2));
            }
        }
        return sum;
    }

    public static String largerChild(long[] arr) {
        // Type your solution here
        long leftSum = treeSum(arr, 1);
        long rightSum = treeSum(arr, 2);
        if (leftSum == rightSum)
            return "";
        else
            return leftSum > rightSum ? "Left" : "Right";
    }

    public static void main(String[] args) {
        long[] arr = {3,8,3,9,2,9,8};
        String largerChild = largerChild(arr);
        if ("".equals(largerChild))
            largerChild = "No";
        System.out.println (largerChild + " child is the larger in the binary tree: " + Arrays.toString(arr));
    }
}
