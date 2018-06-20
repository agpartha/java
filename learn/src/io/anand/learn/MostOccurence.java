package io.anand.learn;

import java.util.*;

public class MostOccurence {

    /*
     Given a string S and an integer N, write a function that returns the N characters that occur most often in the string S in order of occurence count.

        For example f('ABCBCC', 1) should return 'C' and f('ABCBCC', 2) should return 'CB'.
     */

    private static class IntegerCount implements Comparable<IntegerCount> {
        Integer   c;
        Integer   count;

        public IntegerCount(Integer c, Integer count) {
            this.c      = c;
            this.count  = count;
        }

        @Override
        public int compareTo(IntegerCount that) {
            if (this.count < that.count)
                return 1;
            else if (this.count > that.count)
                return -1;
            return 0;
        }
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Parse the text string character by character and update the frequency map
        for (int i = 0; i < nums.length; i ++) {
            Integer c = nums[i];
            if (freqMap.containsKey(c))
                freqMap.put(c, freqMap.get(c) + 1);
            else
                freqMap.put(c, 1);
            System.out.println("Counting: Integer: " + c + ", frequency: " + freqMap.get(c));
        }

        // A =1 , B = 2, C = 3
        // C = 3, B = 2
        // CB
        // Get a sorted set based on the count
        // 1, 2, 3
        // 3, 2, 1
        SortedSet<IntegerCount> countSortedSet = new TreeSet<>();
        for (Integer c: freqMap.keySet()) {
            System.out.println("Sorting: Integer: " + c + ", count: " + freqMap.get(c));
            countSortedSet.add(new IntegerCount(c, freqMap.get(c)));
        }

        // Prepare the Integers selecting first 'count' characters
        List<Integer> result = new ArrayList<>();
        int i = 0;
        for (IntegerCount intCount: countSortedSet) {
            System.out.println("Building: Integer: " + intCount.c + ", count: " + intCount.count);
            if (i < k)
                result.add(i++, intCount.c);
        }
        return result;
    }

    public static void main(String[] args) {
        /* Test Data : 1
        String text  = "ABCBCC";
        int    count = 2;
        */

        /* Test Data : 2
        String text  = "ABCBCC";
        int    count = 1;
        */

        /* Test Data: 3
        String text  = "ABCDEEFGGHIIIKKLMNOPQ11";
        int    count = 5;
        */
        int nums[] = {1,1,1,2,2,3};
        int count = 2;

        System.out.println("Most Occurent Characters result: " + topKFrequent(nums, count) + ", for nums: " + Arrays.asList(nums) + ", count: " + count);

    }

}
